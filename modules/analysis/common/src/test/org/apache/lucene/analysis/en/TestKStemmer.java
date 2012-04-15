package org.apache.lucene.analysis.en;

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

import static org.apache.lucene.analysis.VocabularyAssert.assertVocabulary;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.MockTokenizer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.KeywordTokenizer;

/**
 * Tests for {@link KStemmer}
 */
public class TestKStemmer extends BaseTokenStreamTestCase {
  Analyzer a = new Analyzer() {
    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
      Tokenizer tokenizer = new MockTokenizer(reader, MockTokenizer.WHITESPACE, true);
      return new TokenStreamComponents(tokenizer, new KStemFilter(tokenizer));
    }
  };
 
  /** blast some random strings through the analyzer */
  public void testRandomStrings() throws Exception {
    checkRandomData(random(), a, 10000*RANDOM_MULTIPLIER);
  }
  
  /** 
   * test the kstemmer optimizations against a bunch of words
   * that were stemmed with the original java kstemmer (generated from
   * testCreateMap, commented out below).
   */
  public void testVocabulary() throws Exception {
    assertVocabulary(a, getDataFile("kstemTestData.zip"), "kstem_examples.txt");
  }
  
  public void testEmptyTerm() throws IOException {
    Analyzer a = new Analyzer() {
      @Override
      protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
        Tokenizer tokenizer = new KeywordTokenizer(reader);
        return new TokenStreamComponents(tokenizer, new KStemFilter(tokenizer));
      }
    };
    checkOneTermReuse(a, "", "");
  }

  /****** requires original java kstem source code to create map
  public void testCreateMap() throws Exception {
    String input = getBigDoc();
    Reader r = new StringReader(input);
    TokenFilter tf = new LowerCaseFilter(new LetterTokenizer(r));
    // tf = new KStemFilter(tf);

    KStemmer kstem = new KStemmer();
    Map<String,String> map = new TreeMap<String,String>();
    for(;;) {
      Token t = tf.next();
      if (t==null) break;
      String s = t.termText();
      if (map.containsKey(s)) continue;
      map.put(s, kstem.stem(s));
    }

    Writer out = new BufferedWriter(new FileWriter("kstem_examples.txt"));
    for (String key : map.keySet()) {
      out.write(key);
      out.write('\t');
      out.write(map.get(key));
      out.write('\n');
    }
    out.close();
  }
  ******/

}
