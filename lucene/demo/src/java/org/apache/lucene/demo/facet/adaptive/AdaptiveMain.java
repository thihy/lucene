package org.apache.lucene.demo.facet.adaptive;

import java.util.List;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import org.apache.lucene.demo.facet.ExampleResult;
import org.apache.lucene.demo.facet.ExampleUtils;
import org.apache.lucene.demo.facet.simple.SimpleIndexer;
import org.apache.lucene.demo.facet.simple.SimpleSearcher;
import org.apache.lucene.facet.search.AdaptiveFacetsAccumulator;
import org.apache.lucene.facet.search.results.FacetResult;

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

/**
 * Driver for the adaptive sample, using the {@link AdaptiveFacetsAccumulator}.
 * Indexing is the same as in {@link SimpleSearcher}
 * 
 * @lucene.experimental
 */
public class AdaptiveMain {
  
  /** Sole constructor */
  public AdaptiveMain() {}

  /**
   * Driver for the adaptive sample.
   * @throws Exception on error (no detailed exception handling here for sample simplicity
   */
  public static void main(String[] args) throws Exception {
    new AdaptiveMain().runSample();
    ExampleUtils.log("DONE");
  }

  /** Runs the adaptive sample and returns the facet results */
  public ExampleResult runSample() throws Exception {

    // create Directories for the search index and for the taxonomy index
    Directory indexDir = new RAMDirectory();
    Directory taxoDir = new RAMDirectory();

    // index the sample documents
    ExampleUtils.log("index the adaptive sample documents...");
    SimpleIndexer.index(indexDir, taxoDir);

    ExampleUtils.log("search the adaptive sample documents...");
    List<FacetResult> facetRes = AdaptiveSearcher.searchWithFacets(indexDir, taxoDir);

    ExampleResult res = new ExampleResult();
    res.setFacetResults(facetRes);
    return res;
  }

}
