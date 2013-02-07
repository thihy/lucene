package org.apache.lucene.index;

import org.apache.lucene.index.FieldInfo.DocValuesType;

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

// TODO: Move some properties from IndexableFieldType here, those regarding stored fields. 

/** 
 * Describes the properties of a stored field.
 * @lucene.experimental 
 */
public interface StorableFieldType {

  /** DocValues type; if non-null then the field's value
   *  will be indexed into docValues */
  public DocValuesType docValueType();
}
