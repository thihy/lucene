package org.apache.lucene.facet.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PayloadAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.facet.index.params.CategoryListParams;
import org.apache.lucene.facet.index.params.FacetIndexingParams;
import org.apache.lucene.facet.taxonomy.CategoryPath;
import org.apache.lucene.facet.taxonomy.TaxonomyWriter;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.util.BytesRef;

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
public class FacetFields {

  // a TokenStream for writing the counting list payload
  private static final class CountingListStream extends TokenStream {
    private final PayloadAttribute payloadAtt = addAttribute(PayloadAttribute.class);
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private Iterator<Entry<String,BytesRef>> categoriesData;
    
    CountingListStream() {}
    
    @Override
    public boolean incrementToken() throws IOException {
      if (!categoriesData.hasNext()) {
        return false;
      }
      
      Entry<String,BytesRef> entry = categoriesData.next();
      termAtt.setEmpty().append(entry.getKey());
      payloadAtt.setPayload(entry.getValue());
      return true;
    }
    
    void setCategoriesData(HashMap<String,BytesRef> categoriesData) {
      this.categoriesData = categoriesData.entrySet().iterator();
    }
    
  }

  // The counting list is written in a payload, but we don't store it
  // nor need norms.
  private static final FieldType COUNTING_LIST_PAYLOAD_TYPE = new FieldType();
  static {
    COUNTING_LIST_PAYLOAD_TYPE.setIndexed(true);
    COUNTING_LIST_PAYLOAD_TYPE.setTokenized(true);
    COUNTING_LIST_PAYLOAD_TYPE.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
    COUNTING_LIST_PAYLOAD_TYPE.setStored(false);
    COUNTING_LIST_PAYLOAD_TYPE.setOmitNorms(true);
    COUNTING_LIST_PAYLOAD_TYPE.freeze();
  }
  
  // The drill-down field is added with a TokenStream, hence why it's based on
  // TextField type. However in practice, it is added just like StringField.
  // Therefore we set its IndexOptions to DOCS_ONLY.
  private static final FieldType DRILL_DOWN_TYPE = new FieldType(TextField.TYPE_NOT_STORED);
  static {
    // TODO: once we cutover to DocValues, we can set it to DOCS_ONLY for this 
    // FacetFields (not associations)
    DRILL_DOWN_TYPE.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS);
    DRILL_DOWN_TYPE.freeze();
  }
  
  protected final TaxonomyWriter taxonomyWriter;

  protected final FacetIndexingParams indexingParams;

  /**
   * Constructs a new instance with the {@link FacetIndexingParams#ALL_PARENTS
   * default} facet indexing params.
   * 
   * @param taxonomyWriter
   *          used to resolve given categories to ordinals
   */
  public FacetFields(TaxonomyWriter taxonomyWriter) {
    this(taxonomyWriter, FacetIndexingParams.ALL_PARENTS);
  }

  /**
   * Constructs a new instance with the given facet indexing params.
   * 
   * @param taxonomyWriter
   *          used to resolve given categories to ordinals
   * @param params
   *          determines under which fields the categories should be indexed
   */
  public FacetFields(TaxonomyWriter taxonomyWriter, FacetIndexingParams params) {
    this.taxonomyWriter = taxonomyWriter;
    this.indexingParams = params;
  }

  /**
   * Creates a mapping between a {@link CategoryListParams} and all
   * {@link CategoryPath categories} that are associated with it.
   */
  protected Map<CategoryListParams,Iterable<CategoryPath>> createCategoryListMapping(
      Iterable<CategoryPath> categories) {
    HashMap<CategoryListParams,Iterable<CategoryPath>> categoryLists = 
        new HashMap<CategoryListParams,Iterable<CategoryPath>>();
    for (CategoryPath cp : categories) {
      // each category may be indexed under a different field, so add it to the right list.
      CategoryListParams clp = indexingParams.getCategoryListParams(cp);
      List<CategoryPath> list = (List<CategoryPath>) categoryLists.get(clp);
      if (list == null) {
        list = new ArrayList<CategoryPath>();
        categoryLists.put(clp, list);
      }
      // DrillDownStream modifies the CategoryPath by calling trim(). That means
      // that the source category, as the app ses it, is modified. While for
      // most apps this is not a problem, we need to protect against it. If
      // CategoryPath will be made immutable, we can stop cloning.
      list.add(cp.clone());
    }
    return categoryLists;
  }
  
  /** Returns a {@link CategoryListBuilder} for encoding the given categories. */
  protected CategoryListBuilder getCategoryListBuilder(CategoryListParams categoryListParams, 
      Iterable<CategoryPath> categories /* needed for AssociationsFacetFields */) {
    return new CategoryListBuilder(categoryListParams, indexingParams, taxonomyWriter);
  }
  
  /**
   * Returns a {@link DrillDownStream} for writing the categories drill-down
   * terms.
   */
  protected DrillDownStream getDrillDownStream(Iterable<CategoryPath> categories) {
    return new DrillDownStream(categories, indexingParams);
  }
  
  /**
   * Returns the {@link FieldType} with which the drill-down terms should be
   * indexed. The default is {@link IndexOptions#DOCS_ONLY}.
   */
  protected FieldType fieldType() {
    return DRILL_DOWN_TYPE;
  }

  /** Adds the needed facet fields to the document. */
  public void addFields(Document doc, Iterable<CategoryPath> categories) throws IOException {
    if (categories == null) {
      throw new IllegalArgumentException("categories should not be null");
    }

    // TODO: add reuse capabilities to this class, per CLP objects:
    // - drill-down field
    // - counting list field
    // - DrillDownStream
    // - CountingListStream

    final Map<CategoryListParams,Iterable<CategoryPath>> categoryLists = createCategoryListMapping(categories);

    // for each CLP we add a different field for drill-down terms as well as for
    // counting list data.
    for (Entry<CategoryListParams, Iterable<CategoryPath>> e : categoryLists.entrySet()) {
      final CategoryListParams clp = e.getKey();
      final String field = clp.getTerm().field();

      // add the counting list data
      CategoryListBuilder categoriesPayloadBuilder = getCategoryListBuilder(clp, e.getValue());
      for (CategoryPath cp : e.getValue()) {
        int ordinal = taxonomyWriter.addCategory(cp);
        categoriesPayloadBuilder.handle(ordinal , cp);
      }
      HashMap<String,BytesRef> categoriesData = categoriesPayloadBuilder.finish();
      CountingListStream ts = new CountingListStream();
      ts.setCategoriesData(categoriesData);
      doc.add(new Field(field, ts, COUNTING_LIST_PAYLOAD_TYPE));
      
      // add the drill-down field
      DrillDownStream drillDownStream = getDrillDownStream(e.getValue());
      Field drillDown = new Field(field, drillDownStream, fieldType());
      doc.add(drillDown);
    }
  }

}
