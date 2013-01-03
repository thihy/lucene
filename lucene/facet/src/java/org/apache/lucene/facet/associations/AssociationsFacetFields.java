package org.apache.lucene.facet.associations;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.facet.index.CategoryListBuilder;
import org.apache.lucene.facet.index.DrillDownStream;
import org.apache.lucene.facet.index.FacetFields;
import org.apache.lucene.facet.index.params.CategoryListParams;
import org.apache.lucene.facet.index.params.FacetIndexingParams;
import org.apache.lucene.facet.taxonomy.CategoryPath;
import org.apache.lucene.facet.taxonomy.TaxonomyWriter;
import org.apache.lucene.index.FieldInfo.IndexOptions;

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
 * A utility class for adding facet fields to a document. Usually one field will
 * be added for all facets, however per the
 * {@link FacetIndexingParams#getCategoryListParams(CategoryPath)}, one field
 * may be added for every group of facets.
 * 
 * @lucene.experimental
 */
public class AssociationsFacetFields extends FacetFields {

  // The drill-down field is added with a TokenStream, hence why it's based on
  // TextField type. However for associations, we store a payload with the
  // association value, therefore we set IndexOptions to include positions.
  private static final FieldType DRILL_DOWN_TYPE = new FieldType(TextField.TYPE_NOT_STORED);
  static {
    DRILL_DOWN_TYPE.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
    DRILL_DOWN_TYPE.freeze();
  }
  
  /**
   * Constructs a new instance with the {@link FacetIndexingParams#ALL_PARENTS
   * default} facet indexing params.
   * 
   * @param taxonomyWriter
   *          used to resolve given categories to ordinals
   */
  public AssociationsFacetFields(TaxonomyWriter taxonomyWriter) {
    super(taxonomyWriter);
  }

  /**
   * Constructs a new instance with the given facet indexing params.
   * 
   * @param taxonomyWriter
   *          used to resolve given categories to ordinals
   * @param params
   *          determines under which fields the categories should be indexed
   */
  public AssociationsFacetFields(TaxonomyWriter taxonomyWriter, FacetIndexingParams params) {
    super(taxonomyWriter, params);
  }

  @Override
  protected Map<CategoryListParams,Iterable<CategoryPath>> createCategoryListMapping(
      Iterable<CategoryPath> categories) {
    CategoryAssociationsContainer categoryAssociations = (CategoryAssociationsContainer) categories;
    HashMap<CategoryListParams,Iterable<CategoryPath>> categoryLists = 
        new HashMap<CategoryListParams,Iterable<CategoryPath>>();
    for (CategoryPath cp : categories) {
      // each category may be indexed under a different field, so add it to the right list.
      CategoryListParams clp = indexingParams.getCategoryListParams(cp);
      CategoryAssociationsContainer clpContainer = (CategoryAssociationsContainer) categoryLists.get(clp);
      if (clpContainer == null) {
        clpContainer = new CategoryAssociationsContainer();
        categoryLists.put(clp, clpContainer);
      }
      // DrillDownStream modifies the CategoryPath by calling trim(). That means
      // that the source category, as the app ses it, is modified. While for
      // most apps this is not a problem, we need to protect against it. If
      // CategoryPath will be made immutable, we can stop cloning.
      cp = cp.clone();
      clpContainer.setAssociation(cp, categoryAssociations.getAssociation(cp));
    }
    return categoryLists;
  }
  
  /**
   * Returns a {@link CategoryListBuilder} for encoding the given categories and
   * associations.
   */
  @Override
  protected CategoryListBuilder getCategoryListBuilder(CategoryListParams categoryListParams, 
      Iterable<CategoryPath> categories) {
    return new AssociationsCategoryListBuilder((CategoryAssociationsContainer) categories, categoryListParams, 
        indexingParams, taxonomyWriter);
  }
  
  @Override
  protected DrillDownStream getDrillDownStream(Iterable<CategoryPath> categories) {
    return new AssociationsDrillDownStream((CategoryAssociationsContainer) categories, indexingParams);
  }
  
  @Override
  protected FieldType fieldType() {
    return DRILL_DOWN_TYPE;
  }

  @Override
  public void addFields(Document doc, Iterable<CategoryPath> categories) throws IOException {
    if (!(categories instanceof CategoryAssociationsContainer)) {
      throw new IllegalArgumentException("categories must be of type " + 
          CategoryAssociationsContainer.class.getSimpleName());
    }
    super.addFields(doc, categories);
  }

}
