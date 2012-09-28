package org.apache.lucene.search.suggest.analyzing;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.TokenStreamToAutomaton;
import org.apache.lucene.search.spell.TermFreqIterator;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.fst.Sort;
import org.apache.lucene.store.ByteArrayDataInput;
import org.apache.lucene.store.ByteArrayDataOutput;
import org.apache.lucene.store.InputStreamDataInput;
import org.apache.lucene.store.OutputStreamDataOutput;
import org.apache.lucene.util.ArrayUtil;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.CharsRef;
import org.apache.lucene.util.IOUtils;
import org.apache.lucene.util.IntsRef;
import org.apache.lucene.util.UnicodeUtil;
import org.apache.lucene.util.automaton.Automaton;
import org.apache.lucene.util.automaton.BasicOperations;
import org.apache.lucene.util.automaton.SpecialOperations;
import org.apache.lucene.util.automaton.State;
import org.apache.lucene.util.automaton.Transition;
import org.apache.lucene.util.fst.Builder;
import org.apache.lucene.util.fst.ByteSequenceOutputs;
import org.apache.lucene.util.fst.FST.BytesReader;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.PairOutputs.Pair;
import org.apache.lucene.util.fst.PairOutputs;
import org.apache.lucene.util.fst.PositiveIntOutputs;
import org.apache.lucene.util.fst.Util.MinResult;
import org.apache.lucene.util.fst.Util;

/**
 * Suggester that first analyzes the surface form, adds the
 * analyzed form to a weighted FST, and then does the same
 * thing at lookup time.  This means lookup is based on the
 * analyzed form while suggestions are still the surface
 * form(s).
 *
 * <p>
 * This can result in powerful suggester functionality.  For
 * example, if you use an analyzer removing stop words, 
 * then the partial text "ghost chr..." could see the
 * suggestion "The Ghost of Christmas Past".  If
 * SynonymFilter is used to map wifi and wireless network to
 * hotspot then the partial text "wirele..." could suggest
 * "wifi router".  Token normalization like stemmers, accent
 * removal, etc., would allow suggestions to ignore such
 * variations.
 *
 * <p>
 * There are some limitations:
 * <ul>
 *
 *   <li> A lookup from a query like "net" in English won't
 *        be any different than "net " (ie, user added a
 *        trailing space) because analyzers don't reflect
 *        when they've seen a token separator and when they
 *        haven't.
 *
 *   <li> If you're using {@code StopFilter}, and the user will
 *        type "fast apple", but so far all they've typed is
 *        "fast a", again because the analyzer doesn't convey whether
 *        it's seen a token separator after the "a",
 *        {@code StopFilter} will remove that "a" causing
 *        far more matches than you'd expect.
 *
 *   <li> Lookups with the empty string return no results
 *        instead of all results.
 * 
 * @lucene.experimental
 */
public class AnalyzingSuggester extends Lookup {
 
  /**
   * FST<Weight,Surface>: 
   *  input is the analyzed form, with a null byte between terms
   *  weights are encoded as costs: (Integer.MAX_VALUE-weight)
   *  surface is the original, unanalyzed form.
   */
  private FST<Pair<Long,BytesRef>> fst = null;
  
  /** 
   * Analyzer that will be used for analyzing suggestions at
   * index time.
   */
  private final Analyzer indexAnalyzer;

  /** 
   * Analyzer that will be used for analyzing suggestions at
   * query time.
   */
  private final Analyzer queryAnalyzer;
  
  /** 
   * True if exact match suggestions should always be returned first.
   */
  private final boolean exactFirst;
  
  /** 
   * True if separator between tokens should be preservered.
   */
  private final boolean preserveSep;

  /** Include this flag in the options parameter to {@link
   *  #AnalyzingSuggester(Analyzer,Analyzer,int,int,int)} to always
   *  return the exact match first, regardless of score.  This
   *  has no performance impact but could result in
   *  low-quality suggestions. */
  public static final int EXACT_FIRST = 1;

  /** Include this flag in the options parameter to {@link
   *  #AnalyzingSuggester(Analyzer,Analyzer,int,int,int)} to preserve
   *  token separators when matching. */
  public static final int PRESERVE_SEP = 2;

  /** Represents the separation between tokens, if
   *  PRESERVE_SEP was specified */
  private static final int SEP_LABEL = 0xff;

  /** Marks end of the analyzed input and start of dedup
   *  byte. */
  private static final int END_BYTE = 0x0;

  /** Maximum number of dup surface forms (different surface
   *  forms for the same analyzed form). */
  private final int maxSurfaceFormsPerAnalyzedForm;

  /** Maximum graph paths to index for a single analyzed
   *  surface form.  This only matters if your analyzer
   *  makes lots of alternate paths (e.g. contains
   *  SynonymFilter). */
  private final int maxGraphExpansions;

  /**
   * Calls {@link #AnalyzingSuggester(Analyzer,Analyzer,int,int,int)
   * AnalyzingSuggester(analyzer, analyzer, EXACT_FIRST |
   * PRESERVE_SEP, 256, -1)}
   */
  public AnalyzingSuggester(Analyzer analyzer) {
    this(analyzer, analyzer, EXACT_FIRST | PRESERVE_SEP, 256, -1);
  }

  /**
   * Calls {@link #AnalyzingSuggester(Analyzer,Analyzer,int,int,int)
   * AnalyzingSuggester(indexAnalyzer, queryAnalyzer, EXACT_FIRST |
   * PRESERVE_SEP, 256, -1)}
   */
  public AnalyzingSuggester(Analyzer indexAnalyzer, Analyzer queryAnalyzer) {
    this(indexAnalyzer, queryAnalyzer, EXACT_FIRST | PRESERVE_SEP, 256, -1);
  }

  /**
   * Creates a new suggester.
   * 
   * @param indexAnalyzer Analyzer that will be used for
   *   analyzing suggestions while building the index.
   * @param queryAnalyzer Analyzer that will be used for
   *   analyzing query text during lookup
   * @param options see {@link #EXACT_FIRST}, {@link #PRESERVE_SEP}
   * @param maxSurfaceFormsPerAnalyzedForm Maximum number of
   *   surface forms to keep for a single analyzed form.
   *   When there are too many surface forms we discard the
   *   lowest weighted ones.
   * @param maxGraphExpansions Maximum number of graph paths
   *   to expand from the analyzed form.  Set this to -1 for
   *   no limit.
   */
  public AnalyzingSuggester(Analyzer indexAnalyzer, Analyzer queryAnalyzer, int options, int maxSurfaceFormsPerAnalyzedForm, int maxGraphExpansions) {
    this.indexAnalyzer = indexAnalyzer;
    this.queryAnalyzer = queryAnalyzer;
    if ((options & ~(EXACT_FIRST | PRESERVE_SEP)) != 0) {
      throw new IllegalArgumentException("options should only contain EXACT_FIRST and PRESERVE_SEP; got " + options);
    }
    this.exactFirst = (options & EXACT_FIRST) != 0;
    this.preserveSep = (options & PRESERVE_SEP) != 0;

    // NOTE: this is just an implementation limitation; if
    // somehow this is a problem we could fix it by using
    // more than one byte to disambiguate ... but 256 seems
    // like it should be way more then enough.
    if (maxSurfaceFormsPerAnalyzedForm <= 0 || maxSurfaceFormsPerAnalyzedForm > 256) {
      throw new IllegalArgumentException("maxSurfaceFormsPerAnalyzedForm must be > 0 and < 256 (got: " + maxSurfaceFormsPerAnalyzedForm + ")");
    }
    this.maxSurfaceFormsPerAnalyzedForm = maxSurfaceFormsPerAnalyzedForm;

    if (maxGraphExpansions < 1 && maxGraphExpansions != -1) {
      throw new IllegalArgumentException("maxGraphExpansions must -1 (no limit) or > 0 (got: " + maxGraphExpansions + ")");
    }
    this.maxGraphExpansions = maxGraphExpansions;
  }

  /** Returns byte size of the underlying FST. */
  public long sizeInBytes() {
    return fst == null ? 0 : fst.sizeInBytes();
  }

  // Replaces SEP with epsilon or remaps them if
  // we were asked to preserve them:
  private void replaceSep(Automaton a) {

    State[] states = a.getNumberedStates();

    // Go in reverse topo sort so we know we only have to
    // make one pass:
    for(int stateNumber=states.length-1;stateNumber >=0;stateNumber--) {
      final State state = states[stateNumber];
      List<Transition> newTransitions = new ArrayList<Transition>();
      for(Transition t : state.getTransitions()) {
        assert t.getMin() == t.getMax();
        if (t.getMin() == TokenStreamToAutomaton.POS_SEP) {
          if (preserveSep) {
            // Remap to SEP_LABEL:
            t = new Transition(SEP_LABEL, t.getDest());
          } else {
            // NOTE: sort of weird because this will grow
            // the transition array we are iterating over,
            // but because we are going in reverse topo sort
            // it will not add any SEP/HOLE transitions:
            state.addEpsilon(t.getDest());
            a.setDeterministic(false);
            t = null;
          }
        } else if (t.getMin() == TokenStreamToAutomaton.HOLE) {

          // Just remove the hole: there will then be two
          // SEP tokens next to each other, which will only
          // match another hole at search time.  Note that
          // it will also match an empty-string token ... if
          // that's somehow a problem we can always map HOLE
          // to a dedicated byte (and escape it in the
          // input).

          // NOTE: sort of weird because this will grow
          // the transition array we are iterating over,
          // but because we are going in reverse topo sort
          // it will not add any SEP/HOLE transitions:
          state.addEpsilon(t.getDest());
          a.setDeterministic(false);
          t = null;
        }
        if (t != null) {
          newTransitions.add(t);
        }
      }
      state.resetTransitions();
      state.setTransitions(newTransitions.toArray(new Transition[newTransitions.size()]));
    }
  }

  /** Just escapes the bytes we steal (0xff, 0x0). */
  private static final class  EscapingTokenStreamToAutomaton extends TokenStreamToAutomaton {

    final BytesRef spare = new BytesRef();

    @Override
    protected BytesRef changeToken(BytesRef in) {
      int upto = 0;
      for(int i=0;i<in.length;i++) {
        byte b = in.bytes[in.offset+i];
        if (b == (byte) 0xff) {
          if (spare.bytes.length == upto) {
            spare.grow(upto+2);
          }
          spare.bytes[upto++] = (byte) 0xff;
          spare.bytes[upto++] = b;
        } else {
          if (spare.bytes.length == upto) {
            spare.grow(upto+1);
          }
          spare.bytes[upto++] = b;
        }
      }
      spare.offset = 0;
      spare.length = upto;
      return spare;
    }
  }
  
  @Override
  public void build(TermFreqIterator iterator) throws IOException {
    String prefix = getClass().getSimpleName();
    File directory = Sort.defaultTempDir();
    File tempInput = File.createTempFile(prefix, ".input", directory);
    File tempSorted = File.createTempFile(prefix, ".sorted", directory);
    
    Sort.ByteSequencesWriter writer = new Sort.ByteSequencesWriter(tempInput);
    Sort.ByteSequencesReader reader = null;
    BytesRef scratch = new BytesRef();

    TokenStreamToAutomaton ts2a = new EscapingTokenStreamToAutomaton();

    // analyzed sequence + 0(byte) + weight(int) + surface + analyzedLength(short) 
    boolean success = false;
    byte buffer[] = new byte[8];
    try {
      ByteArrayDataOutput output = new ByteArrayDataOutput(buffer);
      BytesRef surfaceForm;
      while ((surfaceForm = iterator.next()) != null) {

        // Analyze surface form:
        TokenStream ts = indexAnalyzer.tokenStream("", new StringReader(surfaceForm.utf8ToString()));

        // Create corresponding automaton: labels are bytes
        // from each analyzed token, with byte 0 used as
        // separator between tokens:
        Automaton automaton = ts2a.toAutomaton(ts);
        ts.end();
        ts.close();

        replaceSep(automaton);

        assert SpecialOperations.isFinite(automaton);

        // Get all paths from the automaton (there can be
        // more than one path, eg if the analyzer created a
        // graph using SynFilter or WDF):

        // TODO: we could walk & add simultaneously, so we
        // don't have to alloc [possibly biggish]
        // intermediate HashSet in RAM:
        Set<IntsRef> paths = SpecialOperations.getFiniteStrings(automaton, maxGraphExpansions);
        for (IntsRef path : paths) {

          Util.toBytesRef(path, scratch);
          
          // length of the analyzed text (FST input)
          short analyzedLength = (short) scratch.length;
          // compute the required length:
          // analyzed sequence + 12 (separator) + weight (4) + surface + analyzedLength (short)
          int requiredLength = analyzedLength + 2 + 4 + surfaceForm.length + 2;
          
          buffer = ArrayUtil.grow(buffer, requiredLength);
          
          output.reset(buffer);
          output.writeBytes(scratch.bytes, scratch.offset, scratch.length);
          output.writeByte((byte)0); // separator: not used, just for sort order
          output.writeByte((byte)0); // separator: not used, just for sort order

          // NOTE: important that writeInt is big-endian,
          // because this means we sort secondarily by
          // cost ascending (= weight descending) so that
          // when we discard too many surface forms for a
          // single analyzed form we are discarding the
          // least weight ones:
          output.writeInt(encodeWeight(iterator.weight()));

          output.writeBytes(surfaceForm.bytes, surfaceForm.offset, surfaceForm.length);
          output.writeShort(analyzedLength);
          writer.write(buffer, 0, output.getPosition());
        }
      }
      writer.close();

      // Sort all input/output pairs (required by FST.Builder):
      new Sort().sort(tempInput, tempSorted);
      reader = new Sort.ByteSequencesReader(tempSorted);
     
      PairOutputs<Long,BytesRef> outputs = new PairOutputs<Long,BytesRef>(PositiveIntOutputs.getSingleton(true), ByteSequenceOutputs.getSingleton());
      Builder<Pair<Long,BytesRef>> builder = new Builder<Pair<Long,BytesRef>>(FST.INPUT_TYPE.BYTE1, outputs);

      // Build FST:
      BytesRef previous = null;
      BytesRef analyzed = new BytesRef();
      BytesRef surface = new BytesRef();
      IntsRef scratchInts = new IntsRef();
      ByteArrayDataInput input = new ByteArrayDataInput();

      int dedup = 0;
      while (reader.read(scratch)) {
        input.reset(scratch.bytes, scratch.offset, scratch.length);
        input.setPosition(input.length()-2);
        short analyzedLength = input.readShort();

        analyzed.bytes = scratch.bytes;
        analyzed.offset = scratch.offset;
        analyzed.length = analyzedLength;
        
        input.setPosition(analyzedLength + 2); // analyzed sequence + separator
        long cost = input.readInt();
   
        surface.bytes = scratch.bytes;
        surface.offset = input.getPosition();
        surface.length = input.length() - input.getPosition() - 2;

        if (previous == null) {
          previous = new BytesRef();
          previous.copyBytes(analyzed);
        } else if (analyzed.equals(previous)) {
          dedup++;
          if (dedup >= maxSurfaceFormsPerAnalyzedForm) {
            // More than maxSurfaceFormsPerAnalyzedForm
            // dups: skip the rest:
            continue;
          }
        } else {
          dedup = 0;
          previous.copyBytes(analyzed);
        }

        analyzed.grow(analyzed.length+2);

        // TODO: I think we can avoid the extra 2 bytes when
        // there is no dup (dedup==0), but we'd have to fix
        // the exactFirst logic ... which would be sort of
        // hairy because we'd need to special case the two
        // (dup/not dup)...

        // NOTE: must be byte 0 so we sort before whatever
        // is next
        analyzed.bytes[analyzed.length] = 0;
        analyzed.bytes[analyzed.length+1] = (byte) dedup;
        analyzed.length += 2;

        Util.toIntsRef(analyzed, scratchInts);
        //System.out.println("ADD: " + scratchInts + " -> " + cost + ": " + surface.utf8ToString());
        builder.add(scratchInts, outputs.newPair(cost, BytesRef.deepCopyOf(surface)));
      }
      fst = builder.finish();

      //Util.dotToFile(fst, "/tmp/suggest.dot");
      
      success = true;
    } finally {
      if (success) {
        IOUtils.close(reader, writer);
      } else {
        IOUtils.closeWhileHandlingException(reader, writer);
      }
      
      tempInput.delete();
      tempSorted.delete();
    }
  }

  @Override
  public boolean store(OutputStream output) throws IOException {
    try {
      fst.save(new OutputStreamDataOutput(output));
    } finally {
      IOUtils.close(output);
    }
    return true;
  }

  @Override
  public boolean load(InputStream input) throws IOException {
    try {
      this.fst = new FST<Pair<Long,BytesRef>>(new InputStreamDataInput(input), new PairOutputs<Long,BytesRef>(PositiveIntOutputs.getSingleton(true), ByteSequenceOutputs.getSingleton()));
    } finally {
      IOUtils.close(input);
    }
    return true;
  }

  @Override
  public List<LookupResult> lookup(final CharSequence key, boolean onlyMorePopular, int num) {
    assert num > 0;

    //System.out.println("lookup key=" + key + " num=" + num);

    try {

      // TODO: is there a Reader from a CharSequence?
      // Turn tokenstream into automaton:
      TokenStream ts = queryAnalyzer.tokenStream("", new StringReader(key.toString()));
      Automaton automaton = (new EscapingTokenStreamToAutomaton()).toAutomaton(ts);
      ts.end();
      ts.close();

      // TODO: we could use the end offset to "guess"
      // whether the final token was a partial token; this
      // would only be a heuristic ... but maybe an OK one.
      // This way we could eg differentiate "net" from "net ",
      // which we can't today...

      replaceSep(automaton);

      // TODO: we can optimize this somewhat by determinizing
      // while we convert
      BasicOperations.determinize(automaton);

      final CharsRef spare = new CharsRef();

      //System.out.println("  now intersect exactFirst=" + exactFirst);
    
      // Intersect automaton w/ suggest wFST and get all
      // prefix starting nodes & their outputs:
      final List<FSTUtil.Path<Pair<Long,BytesRef>>> prefixPaths;
      prefixPaths = FSTUtil.intersectPrefixPaths(automaton, fst);

      //System.out.println("  prefixPaths: " + prefixPaths.size());

      BytesReader bytesReader = fst.getBytesReader(0);

      FST.Arc<Pair<Long,BytesRef>> scratchArc = new FST.Arc<Pair<Long,BytesRef>>();

      List<LookupResult> results = new ArrayList<LookupResult>();

      if (exactFirst) {

        Util.TopNSearcher<Pair<Long,BytesRef>> searcher;
        searcher = new Util.TopNSearcher<Pair<Long,BytesRef>>(fst, num, weightComparator);

        int count = 0;
        for (FSTUtil.Path<Pair<Long,BytesRef>> path : prefixPaths) {
          if (fst.findTargetArc(END_BYTE, path.fstNode, scratchArc, bytesReader) != null) {
            // This node has END_BYTE arc leaving, meaning it's an
            // "exact" match:
            count++;
          }
        }

        searcher = new Util.TopNSearcher<Pair<Long,BytesRef>>(fst, count * maxSurfaceFormsPerAnalyzedForm, weightComparator);

        // NOTE: we could almost get away with only using
        // the first start node.  The only catch is if
        // maxSurfaceFormsPerAnalyzedForm had kicked in and
        // pruned our exact match from one of these nodes
        // ...:
        for (FSTUtil.Path<Pair<Long,BytesRef>> path : prefixPaths) {
          if (fst.findTargetArc(END_BYTE, path.fstNode, scratchArc, bytesReader) != null) {
            // This node has END_BYTE arc leaving, meaning it's an
            // "exact" match:
            searcher.addStartPaths(scratchArc, fst.outputs.add(path.output, scratchArc.output), false, path.input);
          }
        }

        MinResult<Pair<Long,BytesRef>> completions[] = searcher.search();

        // NOTE: this is rather inefficient: we enumerate
        // every matching "exactly the same analyzed form"
        // path, and then do linear scan to see if one of
        // these exactly matches the input.  It should be
        // possible (though hairy) to do something similar
        // to getByOutput, since the surface form is encoded
        // into the FST output, so we more efficiently hone
        // in on the exact surface-form match.  Still, I
        // suspect very little time is spent in this linear
        // seach: it's bounded by how many prefix start
        // nodes we have and the
        // maxSurfaceFormsPerAnalyzedForm:
        for(MinResult<Pair<Long,BytesRef>> completion : completions) {
          spare.grow(completion.output.output2.length);
          UnicodeUtil.UTF8toUTF16(completion.output.output2, spare);
          if (CHARSEQUENCE_COMPARATOR.compare(spare, key) == 0) {
            results.add(new LookupResult(spare.toString(), decodeWeight(completion.output.output1)));
            break;
          }
        }

        if (results.size() == num) {
          // That was quick:
          return results;
        }
      }

      Util.TopNSearcher<Pair<Long,BytesRef>> searcher;
      searcher = new Util.TopNSearcher<Pair<Long,BytesRef>>(fst,
                                                            num - results.size(),
                                                            weightComparator) {
        private final Set<BytesRef> seen = new HashSet<BytesRef>();

        @Override
        protected boolean acceptResult(IntsRef input, Pair<Long,BytesRef> output) {
          
          // Dedup: when the input analyzes to a graph we
          // can get duplicate surface forms:
          if (seen.contains(output.output2)) {
            return false;
          }
          seen.add(output.output2);
          
          if (!exactFirst) {
            return true;
          } else {
            // In exactFirst mode, don't accept any paths
            // matching the surface form since that will
            // create duplicate results:
            spare.grow(output.output2.length);
            UnicodeUtil.UTF8toUTF16(output.output2, spare);
            return CHARSEQUENCE_COMPARATOR.compare(spare, key) != 0;
          }
        }
      };

      for (FSTUtil.Path<Pair<Long,BytesRef>> path : prefixPaths) {
        searcher.addStartPaths(path.fstNode, path.output, true, path.input);
      }

      MinResult<Pair<Long,BytesRef>> completions[] = searcher.search();

      for(MinResult<Pair<Long,BytesRef>> completion : completions) {
        spare.grow(completion.output.output2.length);
        UnicodeUtil.UTF8toUTF16(completion.output.output2, spare);
        LookupResult result = new LookupResult(spare.toString(), decodeWeight(completion.output.output1));
        //System.out.println("    result=" + result);
        results.add(result);
      }

      return results;
    } catch (IOException bogus) {
      throw new RuntimeException(bogus);
    }
  }

  /**
   * Returns the weight associated with an input string,
   * or null if it does not exist.
   */
  public Object get(CharSequence key) {
    throw new UnsupportedOperationException();
  }
  
  /** cost -> weight */
  private static int decodeWeight(long encoded) {
    return (int)(Integer.MAX_VALUE - encoded);
  }
  
  /** weight -> cost */
  private static int encodeWeight(long value) {
    if (value < 0 || value > Integer.MAX_VALUE) {
      throw new UnsupportedOperationException("cannot encode value: " + value);
    }
    return Integer.MAX_VALUE - (int)value;
  }
   
  static final Comparator<Pair<Long,BytesRef>> weightComparator = new Comparator<Pair<Long,BytesRef>> () {
    public int compare(Pair<Long,BytesRef> left, Pair<Long,BytesRef> right) {
      return left.output1.compareTo(right.output1);
    }
  };
}
