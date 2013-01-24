package org.apache.lucene.facet.index.params;

import java.io.IOException;
import java.io.Serializable;

import org.apache.lucene.facet.search.CategoryListIterator;
import org.apache.lucene.facet.search.DocValuesCategoryListIterator;
import org.apache.lucene.facet.util.PartitionsUtils;
import org.apache.lucene.util.encoding.DGapVInt8IntEncoder;
import org.apache.lucene.util.encoding.IntDecoder;
import org.apache.lucene.util.encoding.IntEncoder;
import org.apache.lucene.util.encoding.SortingIntEncoder;
import org.apache.lucene.util.encoding.UniqueValuesIntEncoder;

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
 * Contains parameters for a category list *
 * 
 * @lucene.experimental
 */
public class CategoryListParams implements Serializable {

  /** OrdinalPolicy defines which ordinals are encoded for every document. */
  public static enum OrdinalPolicy {
    /**
     * Encodes only the ordinal of leaf nodes. That is, the category A/B/C will
     * not encode the ordinals of A and A/B.
     * 
     * <p>
     * <b>NOTE:</b> this {@link OrdinalPolicy} requires a special collector or
     * accumulator, which will fix the parents' counts, unless you are not
     * interested in the parents counts.
     */
    NO_PARENTS,
    
    /**
     * Encodes the ordinals of all path components. That is, the category A/B/C
     * will encode the ordinals of A and A/B as well. This is the default
     * {@link OrdinalPolicy}.
     */
    ALL_PARENTS
  }
  
  /** The default field used to store the facets information. */
  public static final String DEFAULT_FIELD = "$facets";

  /**
   * The default {@link OrdinalPolicy} that's used when encoding a document's
   * category ordinals.
   */
  public static final OrdinalPolicy DEFAULT_ORDINAL_POLICY = OrdinalPolicy.ALL_PARENTS;
  
  public final String field;

  private final int hashCode;

  /** Constructs a default category list parameters object, using {@link #DEFAULT_FIELD}. */
  public CategoryListParams() {
    this(DEFAULT_FIELD);
  }

  /** Constructs a category list parameters object, using the given field. */
  public CategoryListParams(String field) {
    this.field = field;
    // Pre-compute the hashCode because these objects are immutable.  Saves
    // some time on the comparisons later.
    this.hashCode = field.hashCode();
  }
  
  /**
   * Allows to override how categories are encoded and decoded. A matching
   * {@link IntDecoder} is provided by the {@link IntEncoder}.
   * <p>
   * Default implementation creates a new Sorting(<b>Unique</b>(DGap)) encoder.
   * Uniqueness in this regard means when the same category appears twice in a
   * document, only one appearance would be encoded. This has effect on facet
   * counting results.
   * <p>
   * Some possible considerations when overriding may be:
   * <ul>
   * <li>an application "knows" that all categories are unique. So no need to
   * pass through the unique filter.</li>
   * <li>Another application might wish to count multiple occurrences of the
   * same category, or, use a faster encoding which will consume more space.</li>
   * </ul>
   * In any event when changing this value make sure you know what you are
   * doing, and test the results - e.g. counts, if the application is about
   * counting facets.
   */
  public IntEncoder createEncoder() {
    return new SortingIntEncoder(new UniqueValuesIntEncoder(new DGapVInt8IntEncoder()));
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof CategoryListParams)) {
      return false;
    }
    CategoryListParams other = (CategoryListParams) o;
    if (this.hashCode != other.hashCode) {
      return false;
    }
    
    // The above hashcodes might equal each other in the case of a collision,
    // so at this point only directly term equality testing will settle
    // the equality test.
    return field.equals(other.field);
  }

  @Override
  public int hashCode() {
    return this.hashCode;
  }

  /** Create the {@link CategoryListIterator} for the specified partition. */
  public CategoryListIterator createCategoryListIterator(int partition) throws IOException {
    String categoryListTermStr = PartitionsUtils.partitionName(partition);
    String docValuesField = field + categoryListTermStr;
    return new DocValuesCategoryListIterator(docValuesField, createEncoder().createMatchingDecoder());
  }
  
  /** Returns the {@link OrdinalPolicy} to use for this {@link CategoryListParams}. */
  public OrdinalPolicy getOrdinalPolicy() {
    return DEFAULT_ORDINAL_POLICY;
  }
  
  @Override
  public String toString() {
    return "field=" + field + " encoder=" + createEncoder() + " ordinalPolicy=" + getOrdinalPolicy();
  }
  
}