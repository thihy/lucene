// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

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

import java.nio.LongBuffer;
import java.nio.ByteBuffer;

/**
 * Efficient sequential read/write of packed integers.
 */
final class BulkOperationPacked64 extends BulkOperation {
    public int blockCount() {
      return 1;
    }

    public int valueCount() {
      return 1;
    }

    public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
      System.arraycopy(blocks, blocksOffset, values, valuesOffset, valueCount() * iterations);
    }

    @Override
    public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
      throw new UnsupportedOperationException();
    }

    @Override
    public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
      throw new UnsupportedOperationException();
    }

    public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
      LongBuffer.wrap(values, valuesOffset, iterations * valueCount()).put(ByteBuffer.wrap(blocks, blocksOffset, 8 * iterations * blockCount()).asLongBuffer());
    }

    public void encode(long[] values, int valuesOffset, long[] blocks, int blocksOffset, int iterations) {
      System.arraycopy(values, valuesOffset, blocks, blocksOffset, valueCount() * iterations);
    }
}
