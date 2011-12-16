package org.apache.lucene.codecs;

/**
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
import java.io.IOException;

import org.apache.lucene.index.DocValues;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MergeState;
import org.apache.lucene.index.DocValue;
import org.apache.lucene.util.Bits;

/**
 * Abstract API that consumes {@link DocValue}s.
 * {@link DocValuesConsumer} are always associated with a specific field and
 * segments. Concrete implementations of this API write the given
 * {@link DocValue} into a implementation specific format depending on
 * the fields meta-data.
 * 
 * @lucene.experimental
 */
public abstract class DocValuesConsumer {

  /**
   * Adds the given {@link DocValue} instance to this
   * {@link DocValuesConsumer}
   * 
   * @param docID
   *          the document ID to add the value for. The docID must always
   *          increase or be <tt>0</tt> if it is the first call to this method.
   * @param docValue
   *          the value to add
   * @throws IOException
   *           if an {@link IOException} occurs
   */
  public abstract void add(int docID, DocValue docValue)
      throws IOException;

  /**
   * Called when the consumer of this API is doc with adding
   * {@link DocValue} to this {@link DocValuesConsumer}
   * 
   * @param docCount
   *          the total number of documents in this {@link DocValuesConsumer}.
   *          Must be greater than or equal the last given docID to
   *          {@link #add(int, DocValue)}.
   * @throws IOException
   */
  public abstract void finish(int docCount) throws IOException;

  /**
   * Merges the given {@link org.apache.lucene.index.MergeState} into
   * this {@link DocValuesConsumer}.
   * 
   * @param mergeState
   *          the state to merge
   * @param docValues docValues array containing one instance per reader (
   *          {@link org.apache.lucene.index.MergeState#readers}) or <code>null</code> if the reader has
   *          no {@link DocValues} instance.
   * @throws IOException
   *           if an {@link IOException} occurs
   */
  public void merge(MergeState mergeState, DocValues[] docValues) throws IOException {
    assert mergeState != null;
    boolean hasMerged = false;
    for(int readerIDX=0;readerIDX<mergeState.readers.size();readerIDX++) {
      final org.apache.lucene.index.MergeState.IndexReaderAndLiveDocs reader = mergeState.readers.get(readerIDX);
      if (docValues[readerIDX] != null) {
        hasMerged = true;
        merge(new SingleSubMergeState(docValues[readerIDX], mergeState.docBase[readerIDX], reader.reader.maxDoc(),
                                    reader.liveDocs));
      }
    }
    // only finish if no exception is thrown!
    if (hasMerged) {
      finish(mergeState.mergedDocCount);
    }
  }

  /**
   * Merges the given {@link SingleSubMergeState} into this {@link DocValuesConsumer}.
   * 
   * @param mergeState
   *          the {@link SingleSubMergeState} to merge
   * @throws IOException
   *           if an {@link IOException} occurs
   */
  // TODO: can't we have a default implementation here that merges naively with our apis?
  // this is how stored fields and term vectors work. its a pain to have to impl merging
  // (should be an optimization to override it)
  protected abstract void merge(SingleSubMergeState mergeState) throws IOException;

  /**
   * Specialized auxiliary MergeState is necessary since we don't want to
   * exploit internals up to the codecs consumer. An instance of this class is
   * created for each merged low level {@link IndexReader} we are merging to
   * support low level bulk copies.
   */
  public static class SingleSubMergeState {
    /**
     * the source reader for this MergeState - merged values should be read from
     * this instance
     */
    public final DocValues reader;
    /** the absolute docBase for this MergeState within the resulting segment */
    public final int docBase;
    /** the number of documents in this MergeState */
    public final int docCount;
    /** the not deleted bits for this MergeState */
    public final Bits liveDocs;

    public SingleSubMergeState(DocValues reader, int docBase, int docCount, Bits liveDocs) {
      assert reader != null;
      this.reader = reader;
      this.docBase = docBase;
      this.docCount = docCount;
      this.liveDocs = liveDocs;
    }
  }
}
