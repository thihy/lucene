package org.apache.lucene.util.encoding;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.IntsRef;

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
 * Decodes values encoded with {@link SimpleIntEncoder}.
 * 
 * @lucene.experimental
 */
public class SimpleIntDecoder extends IntDecoder {

  @Override
  protected void doDecode(BytesRef buf, IntsRef values, int upto) {
    while (buf.offset < upto) {
      if (values.length == values.ints.length) {
        values.grow(values.length + 10); // grow by few items, however not too many
      }
      values.ints[values.length++] = 
          ((buf.bytes[buf.offset++] & 0xFF) << 24) | 
          ((buf.bytes[buf.offset++] & 0xFF) << 16) | 
          ((buf.bytes[buf.offset++] & 0xFF) <<  8) | 
          (buf.bytes[buf.offset++] & 0xFF);
    }
  }

  @Override
  public String toString() {
    return "Simple";
  }

}
