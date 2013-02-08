package org.apache.lucene.facet.search;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.ConstantScoreQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;

import org.apache.lucene.facet.params.CategoryListParams;
import org.apache.lucene.facet.params.FacetIndexingParams;
import org.apache.lucene.facet.params.FacetSearchParams;
import org.apache.lucene.facet.taxonomy.CategoryPath;

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
 * Utility class for creating drill-down {@link Query queries} or {@link Term
 * terms} over {@link CategoryPath}. This can be used to e.g. narrow down a
 * user's search to selected categories.
 * <p>
 * <b>NOTE:</b> if you choose to create your own {@link Query} by calling
 * {@link #term}, it is recommended to wrap it with {@link ConstantScoreQuery}
 * and set the {@link ConstantScoreQuery#setBoost(float) boost} to {@code 0.0f},
 * so that it does not affect the scores of the documents.
 * 
 * @lucene.experimental
 */
public final class DrillDown {

  /**
   * @see #term(FacetIndexingParams, CategoryPath)
   */
  public static final Term term(FacetSearchParams sParams, CategoryPath path) {
    return term(sParams.indexingParams, path);
  }

  /** Return a drill-down {@link Term} for a category. */
  public static final Term term(FacetIndexingParams iParams, CategoryPath path) {
    CategoryListParams clp = iParams.getCategoryListParams(path);
    char[] buffer = new char[path.fullPathLength()];
    iParams.drillDownTermText(path, buffer);
    return new Term(clp.field, String.valueOf(buffer));
  }
  
  /**
   * Wraps a given {@link Query} by a drill-down query over the given
   * categories. {@link Occur} defines the relationship between the cateories
   * (e.g. {@code OR} or {@code AND}. If you need to construct a more
   * complicated relationship, e.g. {@code AND} of {@code ORs}), call this
   * method with every group of categories with the same relationship and then
   * construct a {@link BooleanQuery} which will wrap all returned queries. It
   * is advised to construct that boolean query with coord disabled, and also
   * wrap the final query with {@link ConstantScoreQuery} and set its boost to
   * {@code 0.0f}.
   * <p>
   * <b>NOTE:</b> {@link Occur} only makes sense when there is more than one
   * {@link CategoryPath} given.
   * <p>
   * <b>NOTE:</b> {@code baseQuery} can be {@code null}, in which case only the
   * {@link Query} over the categories will is returned.
   */
  public static final Query query(FacetIndexingParams iParams, Query baseQuery, Occur occur, CategoryPath... paths) {
    if (paths == null || paths.length == 0) {
      throw new IllegalArgumentException("Empty category path not allowed for drill down query!");
    }
    
    final Query q;
    if (paths.length == 1) {
      q = new TermQuery(term(iParams, paths[0]));
    } else {
      BooleanQuery bq = new BooleanQuery(true); // disable coord
      for (CategoryPath cp : paths) {
        bq.add(new TermQuery(term(iParams, cp)), occur);
      }
      q = bq;
    }

    final ConstantScoreQuery drillDownQuery = new ConstantScoreQuery(q);
    drillDownQuery.setBoost(0.0f);

    if (baseQuery == null) {
      return drillDownQuery;
    } else {
      BooleanQuery res = new BooleanQuery(true);
      res.add(baseQuery, Occur.MUST);
      res.add(drillDownQuery, Occur.MUST);
      return res;
    }
  }

  /**
   * @see #query
   */
  public static final Query query(FacetSearchParams sParams, Query baseQuery, Occur occur, CategoryPath... paths) {
    return query(sParams.indexingParams, baseQuery, occur, paths);
  }

}
