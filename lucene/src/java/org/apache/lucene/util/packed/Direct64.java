package org.apache.lucene.util.packed;

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

import org.apache.lucene.store.IndexInput;
import org.apache.lucene.util.RamUsageEstimator;

import java.io.IOException;
import java.util.Arrays;

/**
 * Direct wrapping of 32 bit values to a backing array of ints.
 */

class Direct64 extends PackedInts.ReaderImpl
        implements PackedInts.Mutable {
  private long[] blocks;
  private static final int BITS_PER_VALUE = 64;

  public Direct64(int valueCount) {
    super(valueCount, BITS_PER_VALUE);
    blocks = new long[valueCount];
  }

  public Direct64(IndexInput in, int valueCount) throws IOException {
    super(valueCount, BITS_PER_VALUE);
    long[] blocks = new long[valueCount];
    for(int i=0;i<valueCount;i++) {
      blocks[i] = in.readLong();
    }

    this.blocks = blocks;
  }


  /**
   * Creates an array backed by the given blocks.
   * </p><p>
   * Note: The blocks are used directly, so changes to the given block will
   * affect the structure.
   * @param blocks   used as the internal backing array.
   */
  public Direct64(long[] blocks) {
    super(blocks.length, BITS_PER_VALUE);
    this.blocks = blocks;
  }

  public long get(final int index) {
    return blocks[index];
  }

  public void set(final int index, final long value) {
    blocks[index] = value;
  }

  public long ramBytesUsed() {
    return RamUsageEstimator.NUM_BYTES_ARRAY_HEADER +
            blocks.length * RamUsageEstimator.NUM_BYTES_LONG;
  }

  public void clear() {
    Arrays.fill(blocks, 0L);
  }
}
