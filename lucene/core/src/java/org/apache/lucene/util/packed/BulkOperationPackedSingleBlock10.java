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

/**
 * Efficient sequential read/write of packed integers.
 */
final class BulkOperationPackedSingleBlock10 extends BulkOperationPackedSingleBlock {

    public BulkOperationPackedSingleBlock10() {
      super(10);
    }

    @Override
    public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final long block = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (block & 1023L);
        values[valuesOffset++] = (int) ((block >>> 10) & 1023L);
        values[valuesOffset++] = (int) ((block >>> 20) & 1023L);
        values[valuesOffset++] = (int) ((block >>> 30) & 1023L);
        values[valuesOffset++] = (int) ((block >>> 40) & 1023L);
        values[valuesOffset++] = (int) (block >>> 50);
      }
    }

    @Override
    public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
      assert blocksOffset + 8 * iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final int byte7 = blocks[blocksOffset++] & 0xFF;
        final int byte6 = blocks[blocksOffset++] & 0xFF;
        final int byte5 = blocks[blocksOffset++] & 0xFF;
        final int byte4 = blocks[blocksOffset++] & 0xFF;
        final int byte3 = blocks[blocksOffset++] & 0xFF;
        final int byte2 = blocks[blocksOffset++] & 0xFF;
        final int byte1 = blocks[blocksOffset++] & 0xFF;
        final int byte0 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = byte0 | ((byte1 & 3) << 8);
        values[valuesOffset++] = (byte1 >>> 2) | ((byte2 & 15) << 6);
        values[valuesOffset++] = (byte2 >>> 4) | ((byte3 & 63) << 4);
        values[valuesOffset++] = (byte3 >>> 6) | (byte4 << 2);
        values[valuesOffset++] = byte5 | ((byte6 & 3) << 8);
        values[valuesOffset++] = (byte6 >>> 2) | ((byte7 & 15) << 6);
      }
    }

    @Override
    public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final long block = blocks[blocksOffset++];
        values[valuesOffset++] = block & 1023L;
        values[valuesOffset++] = (block >>> 10) & 1023L;
        values[valuesOffset++] = (block >>> 20) & 1023L;
        values[valuesOffset++] = (block >>> 30) & 1023L;
        values[valuesOffset++] = (block >>> 40) & 1023L;
        values[valuesOffset++] = block >>> 50;
      }
    }

    @Override
    public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
      assert blocksOffset + 8 * iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final int byte7 = blocks[blocksOffset++] & 0xFF;
        final int byte6 = blocks[blocksOffset++] & 0xFF;
        final int byte5 = blocks[blocksOffset++] & 0xFF;
        final int byte4 = blocks[blocksOffset++] & 0xFF;
        final int byte3 = blocks[blocksOffset++] & 0xFF;
        final int byte2 = blocks[blocksOffset++] & 0xFF;
        final int byte1 = blocks[blocksOffset++] & 0xFF;
        final int byte0 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = byte0 | ((byte1 & 3) << 8);
        values[valuesOffset++] = (byte1 >>> 2) | ((byte2 & 15) << 6);
        values[valuesOffset++] = (byte2 >>> 4) | ((byte3 & 63) << 4);
        values[valuesOffset++] = (byte3 >>> 6) | (byte4 << 2);
        values[valuesOffset++] = byte5 | ((byte6 & 3) << 8);
        values[valuesOffset++] = (byte6 >>> 2) | ((byte7 & 15) << 6);
      }
    }

}
