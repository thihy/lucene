package org.apache.solr.common.cloud;

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

import org.apache.solr.common.SolrException;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.Hash;

import java.util.Collection;
import java.util.Collections;

public abstract class HashBasedRouter extends DocRouter {

  @Override
  public Slice getTargetSlice(String id, SolrInputDocument sdoc, SolrParams params, DocCollection collection) {
    if (id == null) id = getId(sdoc, params);
    int hash = sliceHash(id, sdoc, params);
    return hashToSlice(hash, collection);
  }

  protected int sliceHash(String id, SolrInputDocument sdoc, SolrParams params) {
    return Hash.murmurhash3_x86_32(id, 0, id.length(), 0);
  }

  protected String getId(SolrInputDocument sdoc, SolrParams params) {
    Object  idObj = sdoc.getFieldValue("id");  // blech
    String id = idObj != null ? idObj.toString() : "null";  // should only happen on client side
    return id;
  }

  protected Slice hashToSlice(int hash, DocCollection collection) {
    for (Slice slice : collection.getSlices()) {
      Range range = slice.getRange();
      if (range != null && range.includes(hash)) return slice;
    }
    throw new SolrException(SolrException.ErrorCode.BAD_REQUEST, "No slice servicing hash code " + Integer.toHexString(hash) + " in " + collection);
  }


  @Override
  public Collection<Slice> getSearchSlices(String shardKey, SolrParams params, DocCollection collection) {
    if (shardKey == null) {
      // search across whole collection
      // TODO: this may need modification in the future when shard splitting could cause an overlap
      return collection.getSlices();
    }

    // use the shardKey as an id for plain hashing
    return Collections.singletonList(getTargetSlice(shardKey, null, params, collection));
  }
}
