package org.apache.lucene.index.sorter;

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

import java.io.IOException;
import java.util.Random;

import org.apache.lucene.analysis.MockAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.AtomicReader;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.NumericDocValues;
import org.apache.lucene.index.RandomIndexWriter;
import org.apache.lucene.index.SlowCompositeReaderWrapper;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.LuceneTestCase;
import org.apache.lucene.util._TestUtil;

public class TestSortingMergePolicy extends LuceneTestCase {

  private static final String DELETE_TERM = "abc";

  private Directory dir1, dir2;
  private Sorter sorter;
  private IndexReader reader;
  private IndexReader sortedReader;

  public void setUp() throws Exception {
    super.setUp();
    sorter = new NumericDocValuesSorter("ndv");
    createRandomIndexes();
  }

  private Document randomDocument() {
    final Document doc = new Document();
    doc.add(new NumericDocValuesField("ndv", random().nextLong()));
    doc.add(new StringField("s", rarely() ? DELETE_TERM : _TestUtil.randomSimpleString(random(), 3), Store.YES));
    return doc;
  }

  private void createRandomIndexes() throws IOException {
    dir1 = newDirectory();
    dir2 = newDirectory();
    final int numDocs = atLeast(100);
    final long seed = random().nextLong();
    final IndexWriterConfig iwc1 = newIndexWriterConfig(TEST_VERSION_CURRENT, new MockAnalyzer(new Random(seed)));
    final IndexWriterConfig iwc2 = newIndexWriterConfig(TEST_VERSION_CURRENT, new MockAnalyzer(new Random(seed)));
    iwc2.setMergePolicy(new SortingMergePolicy(iwc2.getMergePolicy(), sorter));
    final RandomIndexWriter iw1 = new RandomIndexWriter(new Random(seed), dir1, iwc1);
    final RandomIndexWriter iw2 = new RandomIndexWriter(new Random(seed), dir2, iwc2);
    for (int i = 0; i < numDocs; ++i) {
      final Document doc = randomDocument();
      iw1.addDocument(doc);
      iw2.addDocument(doc);
      if (i == numDocs / 2 || rarely()) {
        iw1.commit();
        iw2.commit();
      }
    }
    iw1.deleteDocuments(new Term("s", DELETE_TERM));
    iw2.deleteDocuments(new Term("s", DELETE_TERM));
    iw1.forceMerge(1);
    iw2.forceMerge(1);
    iw1.close();
    iw2.close();
    reader = DirectoryReader.open(dir1);
    sortedReader = DirectoryReader.open(dir2);
  }

  public void tearDown() throws Exception {
    reader.close();
    sortedReader.close();
    dir1.close();
    dir2.close();
    super.tearDown();
  }

  private void assertSorted(AtomicReader reader) throws IOException {
    final NumericDocValues ndv = reader.getNumericDocValues("ndv");
    for (int i = 1; i < reader.maxDoc(); ++i) {
      assertTrue(ndv.get(i-1) < ndv.get(i));
    }
  }

  public void testSortingMP() throws IOException {
    final AtomicReader sortedReader1 = SortingAtomicReader.wrap(SlowCompositeReaderWrapper.wrap(reader), sorter);
    final AtomicReader sortedReader2 = SlowCompositeReaderWrapper.wrap(sortedReader);

    assertSorted(sortedReader1);
    assertSorted(sortedReader2);
    assertReaderEquals("", sortedReader1, sortedReader2);
  }

}
