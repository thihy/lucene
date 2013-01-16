package org.apache.lucene.facet.search.aggregator.associations;

import java.io.IOException;

import org.apache.lucene.facet.associations.CategoryIntAssociation;
import org.apache.lucene.facet.associations.IntAssociationsIterator;
import org.apache.lucene.facet.index.params.CategoryListParams;
import org.apache.lucene.facet.search.aggregator.Aggregator;
import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.util.IntsRef;
import org.apache.lucene.util.collections.IntToIntMap;

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
 * An {@link Aggregator} which computes the weight of a category as the sum of
 * the integer values associated with it in the result documents.
 * 
 * @lucene.experimental
 */
public class AssociationIntSumAggregator implements Aggregator {

  protected final String field;
  protected final int[] sumArray;
  protected final IntAssociationsIterator associations;

  public AssociationIntSumAggregator(int[] sumArray) throws IOException {
    this(CategoryListParams.DEFAULT_FIELD, sumArray);
  }
  
  public AssociationIntSumAggregator(String field, int[] sumArray) throws IOException {
    this.field = field;
    associations = new IntAssociationsIterator(field, new CategoryIntAssociation());
    this.sumArray = sumArray;
  }

  @Override
  public void aggregate(int docID, float score, IntsRef ordinals) throws IOException {
    IntToIntMap values = associations.getAssociations(docID);
    if (values != null) {
      for (int i = 0; i < ordinals.length; i++) {
        int ord = ordinals.ints[i];
        if (values.containsKey(ord)) {
          sumArray[ord] += values.get(ord);
        }
      }
    }
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    AssociationIntSumAggregator that = (AssociationIntSumAggregator) obj;
    return that.field.equals(field) && that.sumArray == sumArray;
  }

  @Override
  public int hashCode() {
    return field.hashCode();
  }

  @Override
  public boolean setNextReader(AtomicReaderContext context) throws IOException {
    return associations.setNextReader(context);
  }

}
