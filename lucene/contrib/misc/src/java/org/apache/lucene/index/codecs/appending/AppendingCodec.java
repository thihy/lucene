package org.apache.lucene.index.codecs.appending;

/**
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

import org.apache.lucene.index.codecs.Codec;
import org.apache.lucene.index.codecs.DefaultDocValuesFormat;
import org.apache.lucene.index.codecs.DefaultFieldInfosFormat;
import org.apache.lucene.index.codecs.DefaultStoredFieldsFormat;
import org.apache.lucene.index.codecs.DefaultTermVectorsFormat;
import org.apache.lucene.index.codecs.DocValuesFormat;
import org.apache.lucene.index.codecs.FieldInfosFormat;
import org.apache.lucene.index.codecs.StoredFieldsFormat;
import org.apache.lucene.index.codecs.PostingsFormat;
import org.apache.lucene.index.codecs.SegmentInfosFormat;
import org.apache.lucene.index.codecs.TermVectorsFormat;
import org.apache.lucene.index.codecs.lucene40.Lucene40Codec;

/**
 * This codec extends {@link Lucene40Codec} to work on append-only outputs, such
 * as plain output streams and append-only filesystems.
 * 
 * @lucene.experimental
 */
public class AppendingCodec extends Codec {
  public AppendingCodec() {
    super("Appending");
  }

  private final PostingsFormat postings = new AppendingPostingsFormat();
  private final SegmentInfosFormat infos = new AppendingSegmentInfosFormat();
  private final StoredFieldsFormat fields = new DefaultStoredFieldsFormat();
  private final FieldInfosFormat fieldInfos = new DefaultFieldInfosFormat();
  private final TermVectorsFormat vectors = new DefaultTermVectorsFormat();
  private final DocValuesFormat docValues = new DefaultDocValuesFormat();
  
  @Override
  public PostingsFormat postingsFormat() {
    return postings;
  }

  @Override
  public StoredFieldsFormat storedFieldsFormat() {
    return fields;
  }
  
  @Override
  public TermVectorsFormat termVectorsFormat() {
    return vectors;
  }

  @Override
  public DocValuesFormat docValuesFormat() {
    return docValues;
  }

  @Override
  public SegmentInfosFormat segmentInfosFormat() {
    return infos;
  }
  
  @Override
  public FieldInfosFormat fieldInfosFormat() {
    return fieldInfos;
  }
}
