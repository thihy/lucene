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
final class BulkOperationPackedSingleBlock2 extends BulkOperationPackedSingleBlock {

    public BulkOperationPackedSingleBlock2() {
      super(2);
    }

    @Override
    public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final long block = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (block & 3L);
        values[valuesOffset++] = (int) ((block >>> 2) & 3L);
        values[valuesOffset++] = (int) ((block >>> 4) & 3L);
        values[valuesOffset++] = (int) ((block >>> 6) & 3L);
        values[valuesOffset++] = (int) ((block >>> 8) & 3L);
        values[valuesOffset++] = (int) ((block >>> 10) & 3L);
        values[valuesOffset++] = (int) ((block >>> 12) & 3L);
        values[valuesOffset++] = (int) ((block >>> 14) & 3L);
        values[valuesOffset++] = (int) ((block >>> 16) & 3L);
        values[valuesOffset++] = (int) ((block >>> 18) & 3L);
        values[valuesOffset++] = (int) ((block >>> 20) & 3L);
        values[valuesOffset++] = (int) ((block >>> 22) & 3L);
        values[valuesOffset++] = (int) ((block >>> 24) & 3L);
        values[valuesOffset++] = (int) ((block >>> 26) & 3L);
        values[valuesOffset++] = (int) ((block >>> 28) & 3L);
        values[valuesOffset++] = (int) ((block >>> 30) & 3L);
        values[valuesOffset++] = (int) ((block >>> 32) & 3L);
        values[valuesOffset++] = (int) ((block >>> 34) & 3L);
        values[valuesOffset++] = (int) ((block >>> 36) & 3L);
        values[valuesOffset++] = (int) ((block >>> 38) & 3L);
        values[valuesOffset++] = (int) ((block >>> 40) & 3L);
        values[valuesOffset++] = (int) ((block >>> 42) & 3L);
        values[valuesOffset++] = (int) ((block >>> 44) & 3L);
        values[valuesOffset++] = (int) ((block >>> 46) & 3L);
        values[valuesOffset++] = (int) ((block >>> 48) & 3L);
        values[valuesOffset++] = (int) ((block >>> 50) & 3L);
        values[valuesOffset++] = (int) ((block >>> 52) & 3L);
        values[valuesOffset++] = (int) ((block >>> 54) & 3L);
        values[valuesOffset++] = (int) ((block >>> 56) & 3L);
        values[valuesOffset++] = (int) ((block >>> 58) & 3L);
        values[valuesOffset++] = (int) ((block >>> 60) & 3L);
        values[valuesOffset++] = (int) (block >>> 62);
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
        values[valuesOffset++] = byte0 & 3;
        values[valuesOffset++] = (byte0 >>> 2) & 3;
        values[valuesOffset++] = (byte0 >>> 4) & 3;
        values[valuesOffset++] = byte0 >>> 6;
        values[valuesOffset++] = byte1 & 3;
        values[valuesOffset++] = (byte1 >>> 2) & 3;
        values[valuesOffset++] = (byte1 >>> 4) & 3;
        values[valuesOffset++] = byte1 >>> 6;
        values[valuesOffset++] = byte2 & 3;
        values[valuesOffset++] = (byte2 >>> 2) & 3;
        values[valuesOffset++] = (byte2 >>> 4) & 3;
        values[valuesOffset++] = byte2 >>> 6;
        values[valuesOffset++] = byte3 & 3;
        values[valuesOffset++] = (byte3 >>> 2) & 3;
        values[valuesOffset++] = (byte3 >>> 4) & 3;
        values[valuesOffset++] = byte3 >>> 6;
        values[valuesOffset++] = byte4 & 3;
        values[valuesOffset++] = (byte4 >>> 2) & 3;
        values[valuesOffset++] = (byte4 >>> 4) & 3;
        values[valuesOffset++] = byte4 >>> 6;
        values[valuesOffset++] = byte5 & 3;
        values[valuesOffset++] = (byte5 >>> 2) & 3;
        values[valuesOffset++] = (byte5 >>> 4) & 3;
        values[valuesOffset++] = byte5 >>> 6;
        values[valuesOffset++] = byte6 & 3;
        values[valuesOffset++] = (byte6 >>> 2) & 3;
        values[valuesOffset++] = (byte6 >>> 4) & 3;
        values[valuesOffset++] = byte6 >>> 6;
        values[valuesOffset++] = byte7 & 3;
        values[valuesOffset++] = (byte7 >>> 2) & 3;
        values[valuesOffset++] = (byte7 >>> 4) & 3;
        values[valuesOffset++] = byte7 >>> 6;
      }
    }

    @Override
    public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final long block = blocks[blocksOffset++];
        values[valuesOffset++] = block & 3L;
        values[valuesOffset++] = (block >>> 2) & 3L;
        values[valuesOffset++] = (block >>> 4) & 3L;
        values[valuesOffset++] = (block >>> 6) & 3L;
        values[valuesOffset++] = (block >>> 8) & 3L;
        values[valuesOffset++] = (block >>> 10) & 3L;
        values[valuesOffset++] = (block >>> 12) & 3L;
        values[valuesOffset++] = (block >>> 14) & 3L;
        values[valuesOffset++] = (block >>> 16) & 3L;
        values[valuesOffset++] = (block >>> 18) & 3L;
        values[valuesOffset++] = (block >>> 20) & 3L;
        values[valuesOffset++] = (block >>> 22) & 3L;
        values[valuesOffset++] = (block >>> 24) & 3L;
        values[valuesOffset++] = (block >>> 26) & 3L;
        values[valuesOffset++] = (block >>> 28) & 3L;
        values[valuesOffset++] = (block >>> 30) & 3L;
        values[valuesOffset++] = (block >>> 32) & 3L;
        values[valuesOffset++] = (block >>> 34) & 3L;
        values[valuesOffset++] = (block >>> 36) & 3L;
        values[valuesOffset++] = (block >>> 38) & 3L;
        values[valuesOffset++] = (block >>> 40) & 3L;
        values[valuesOffset++] = (block >>> 42) & 3L;
        values[valuesOffset++] = (block >>> 44) & 3L;
        values[valuesOffset++] = (block >>> 46) & 3L;
        values[valuesOffset++] = (block >>> 48) & 3L;
        values[valuesOffset++] = (block >>> 50) & 3L;
        values[valuesOffset++] = (block >>> 52) & 3L;
        values[valuesOffset++] = (block >>> 54) & 3L;
        values[valuesOffset++] = (block >>> 56) & 3L;
        values[valuesOffset++] = (block >>> 58) & 3L;
        values[valuesOffset++] = (block >>> 60) & 3L;
        values[valuesOffset++] = block >>> 62;
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
        values[valuesOffset++] = byte0 & 3;
        values[valuesOffset++] = (byte0 >>> 2) & 3;
        values[valuesOffset++] = (byte0 >>> 4) & 3;
        values[valuesOffset++] = byte0 >>> 6;
        values[valuesOffset++] = byte1 & 3;
        values[valuesOffset++] = (byte1 >>> 2) & 3;
        values[valuesOffset++] = (byte1 >>> 4) & 3;
        values[valuesOffset++] = byte1 >>> 6;
        values[valuesOffset++] = byte2 & 3;
        values[valuesOffset++] = (byte2 >>> 2) & 3;
        values[valuesOffset++] = (byte2 >>> 4) & 3;
        values[valuesOffset++] = byte2 >>> 6;
        values[valuesOffset++] = byte3 & 3;
        values[valuesOffset++] = (byte3 >>> 2) & 3;
        values[valuesOffset++] = (byte3 >>> 4) & 3;
        values[valuesOffset++] = byte3 >>> 6;
        values[valuesOffset++] = byte4 & 3;
        values[valuesOffset++] = (byte4 >>> 2) & 3;
        values[valuesOffset++] = (byte4 >>> 4) & 3;
        values[valuesOffset++] = byte4 >>> 6;
        values[valuesOffset++] = byte5 & 3;
        values[valuesOffset++] = (byte5 >>> 2) & 3;
        values[valuesOffset++] = (byte5 >>> 4) & 3;
        values[valuesOffset++] = byte5 >>> 6;
        values[valuesOffset++] = byte6 & 3;
        values[valuesOffset++] = (byte6 >>> 2) & 3;
        values[valuesOffset++] = (byte6 >>> 4) & 3;
        values[valuesOffset++] = byte6 >>> 6;
        values[valuesOffset++] = byte7 & 3;
        values[valuesOffset++] = (byte7 >>> 2) & 3;
        values[valuesOffset++] = (byte7 >>> 4) & 3;
        values[valuesOffset++] = byte7 >>> 6;
      }
    }

}
