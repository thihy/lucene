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
final class BulkOperationPacked13 extends BulkOperation {
    @Override
    public int blockCount() {
      return 13;
    }

    @Override
    public int valueCount() {
      return 64;
    }

    @Override
    public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final long block0 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (block0 >>> 51);
        values[valuesOffset++] = (int) ((block0 >>> 38) & 8191L);
        values[valuesOffset++] = (int) ((block0 >>> 25) & 8191L);
        values[valuesOffset++] = (int) ((block0 >>> 12) & 8191L);
        final long block1 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block0 & 4095L) << 1) | (block1 >>> 63));
        values[valuesOffset++] = (int) ((block1 >>> 50) & 8191L);
        values[valuesOffset++] = (int) ((block1 >>> 37) & 8191L);
        values[valuesOffset++] = (int) ((block1 >>> 24) & 8191L);
        values[valuesOffset++] = (int) ((block1 >>> 11) & 8191L);
        final long block2 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block1 & 2047L) << 2) | (block2 >>> 62));
        values[valuesOffset++] = (int) ((block2 >>> 49) & 8191L);
        values[valuesOffset++] = (int) ((block2 >>> 36) & 8191L);
        values[valuesOffset++] = (int) ((block2 >>> 23) & 8191L);
        values[valuesOffset++] = (int) ((block2 >>> 10) & 8191L);
        final long block3 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block2 & 1023L) << 3) | (block3 >>> 61));
        values[valuesOffset++] = (int) ((block3 >>> 48) & 8191L);
        values[valuesOffset++] = (int) ((block3 >>> 35) & 8191L);
        values[valuesOffset++] = (int) ((block3 >>> 22) & 8191L);
        values[valuesOffset++] = (int) ((block3 >>> 9) & 8191L);
        final long block4 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block3 & 511L) << 4) | (block4 >>> 60));
        values[valuesOffset++] = (int) ((block4 >>> 47) & 8191L);
        values[valuesOffset++] = (int) ((block4 >>> 34) & 8191L);
        values[valuesOffset++] = (int) ((block4 >>> 21) & 8191L);
        values[valuesOffset++] = (int) ((block4 >>> 8) & 8191L);
        final long block5 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block4 & 255L) << 5) | (block5 >>> 59));
        values[valuesOffset++] = (int) ((block5 >>> 46) & 8191L);
        values[valuesOffset++] = (int) ((block5 >>> 33) & 8191L);
        values[valuesOffset++] = (int) ((block5 >>> 20) & 8191L);
        values[valuesOffset++] = (int) ((block5 >>> 7) & 8191L);
        final long block6 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block5 & 127L) << 6) | (block6 >>> 58));
        values[valuesOffset++] = (int) ((block6 >>> 45) & 8191L);
        values[valuesOffset++] = (int) ((block6 >>> 32) & 8191L);
        values[valuesOffset++] = (int) ((block6 >>> 19) & 8191L);
        values[valuesOffset++] = (int) ((block6 >>> 6) & 8191L);
        final long block7 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block6 & 63L) << 7) | (block7 >>> 57));
        values[valuesOffset++] = (int) ((block7 >>> 44) & 8191L);
        values[valuesOffset++] = (int) ((block7 >>> 31) & 8191L);
        values[valuesOffset++] = (int) ((block7 >>> 18) & 8191L);
        values[valuesOffset++] = (int) ((block7 >>> 5) & 8191L);
        final long block8 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block7 & 31L) << 8) | (block8 >>> 56));
        values[valuesOffset++] = (int) ((block8 >>> 43) & 8191L);
        values[valuesOffset++] = (int) ((block8 >>> 30) & 8191L);
        values[valuesOffset++] = (int) ((block8 >>> 17) & 8191L);
        values[valuesOffset++] = (int) ((block8 >>> 4) & 8191L);
        final long block9 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block8 & 15L) << 9) | (block9 >>> 55));
        values[valuesOffset++] = (int) ((block9 >>> 42) & 8191L);
        values[valuesOffset++] = (int) ((block9 >>> 29) & 8191L);
        values[valuesOffset++] = (int) ((block9 >>> 16) & 8191L);
        values[valuesOffset++] = (int) ((block9 >>> 3) & 8191L);
        final long block10 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block9 & 7L) << 10) | (block10 >>> 54));
        values[valuesOffset++] = (int) ((block10 >>> 41) & 8191L);
        values[valuesOffset++] = (int) ((block10 >>> 28) & 8191L);
        values[valuesOffset++] = (int) ((block10 >>> 15) & 8191L);
        values[valuesOffset++] = (int) ((block10 >>> 2) & 8191L);
        final long block11 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block10 & 3L) << 11) | (block11 >>> 53));
        values[valuesOffset++] = (int) ((block11 >>> 40) & 8191L);
        values[valuesOffset++] = (int) ((block11 >>> 27) & 8191L);
        values[valuesOffset++] = (int) ((block11 >>> 14) & 8191L);
        values[valuesOffset++] = (int) ((block11 >>> 1) & 8191L);
        final long block12 = blocks[blocksOffset++];
        values[valuesOffset++] = (int) (((block11 & 1L) << 12) | (block12 >>> 52));
        values[valuesOffset++] = (int) ((block12 >>> 39) & 8191L);
        values[valuesOffset++] = (int) ((block12 >>> 26) & 8191L);
        values[valuesOffset++] = (int) ((block12 >>> 13) & 8191L);
        values[valuesOffset++] = (int) (block12 & 8191L);
      }
    }

    @Override
    public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
      assert blocksOffset + 8 * iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final int byte0 = blocks[blocksOffset++] & 0xFF;
        final int byte1 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte0 << 5) | (byte1 >>> 3);
        final int byte2 = blocks[blocksOffset++] & 0xFF;
        final int byte3 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte1 & 7) << 10) | (byte2 << 2) | (byte3 >>> 6);
        final int byte4 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte3 & 63) << 7) | (byte4 >>> 1);
        final int byte5 = blocks[blocksOffset++] & 0xFF;
        final int byte6 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte4 & 1) << 12) | (byte5 << 4) | (byte6 >>> 4);
        final int byte7 = blocks[blocksOffset++] & 0xFF;
        final int byte8 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte6 & 15) << 9) | (byte7 << 1) | (byte8 >>> 7);
        final int byte9 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte8 & 127) << 6) | (byte9 >>> 2);
        final int byte10 = blocks[blocksOffset++] & 0xFF;
        final int byte11 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte9 & 3) << 11) | (byte10 << 3) | (byte11 >>> 5);
        final int byte12 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte11 & 31) << 8) | byte12;
        final int byte13 = blocks[blocksOffset++] & 0xFF;
        final int byte14 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte13 << 5) | (byte14 >>> 3);
        final int byte15 = blocks[blocksOffset++] & 0xFF;
        final int byte16 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte14 & 7) << 10) | (byte15 << 2) | (byte16 >>> 6);
        final int byte17 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte16 & 63) << 7) | (byte17 >>> 1);
        final int byte18 = blocks[blocksOffset++] & 0xFF;
        final int byte19 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte17 & 1) << 12) | (byte18 << 4) | (byte19 >>> 4);
        final int byte20 = blocks[blocksOffset++] & 0xFF;
        final int byte21 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte19 & 15) << 9) | (byte20 << 1) | (byte21 >>> 7);
        final int byte22 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte21 & 127) << 6) | (byte22 >>> 2);
        final int byte23 = blocks[blocksOffset++] & 0xFF;
        final int byte24 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte22 & 3) << 11) | (byte23 << 3) | (byte24 >>> 5);
        final int byte25 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte24 & 31) << 8) | byte25;
        final int byte26 = blocks[blocksOffset++] & 0xFF;
        final int byte27 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte26 << 5) | (byte27 >>> 3);
        final int byte28 = blocks[blocksOffset++] & 0xFF;
        final int byte29 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte27 & 7) << 10) | (byte28 << 2) | (byte29 >>> 6);
        final int byte30 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte29 & 63) << 7) | (byte30 >>> 1);
        final int byte31 = blocks[blocksOffset++] & 0xFF;
        final int byte32 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte30 & 1) << 12) | (byte31 << 4) | (byte32 >>> 4);
        final int byte33 = blocks[blocksOffset++] & 0xFF;
        final int byte34 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte32 & 15) << 9) | (byte33 << 1) | (byte34 >>> 7);
        final int byte35 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte34 & 127) << 6) | (byte35 >>> 2);
        final int byte36 = blocks[blocksOffset++] & 0xFF;
        final int byte37 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte35 & 3) << 11) | (byte36 << 3) | (byte37 >>> 5);
        final int byte38 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte37 & 31) << 8) | byte38;
        final int byte39 = blocks[blocksOffset++] & 0xFF;
        final int byte40 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte39 << 5) | (byte40 >>> 3);
        final int byte41 = blocks[blocksOffset++] & 0xFF;
        final int byte42 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte40 & 7) << 10) | (byte41 << 2) | (byte42 >>> 6);
        final int byte43 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte42 & 63) << 7) | (byte43 >>> 1);
        final int byte44 = blocks[blocksOffset++] & 0xFF;
        final int byte45 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte43 & 1) << 12) | (byte44 << 4) | (byte45 >>> 4);
        final int byte46 = blocks[blocksOffset++] & 0xFF;
        final int byte47 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte45 & 15) << 9) | (byte46 << 1) | (byte47 >>> 7);
        final int byte48 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte47 & 127) << 6) | (byte48 >>> 2);
        final int byte49 = blocks[blocksOffset++] & 0xFF;
        final int byte50 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte48 & 3) << 11) | (byte49 << 3) | (byte50 >>> 5);
        final int byte51 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte50 & 31) << 8) | byte51;
        final int byte52 = blocks[blocksOffset++] & 0xFF;
        final int byte53 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte52 << 5) | (byte53 >>> 3);
        final int byte54 = blocks[blocksOffset++] & 0xFF;
        final int byte55 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte53 & 7) << 10) | (byte54 << 2) | (byte55 >>> 6);
        final int byte56 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte55 & 63) << 7) | (byte56 >>> 1);
        final int byte57 = blocks[blocksOffset++] & 0xFF;
        final int byte58 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte56 & 1) << 12) | (byte57 << 4) | (byte58 >>> 4);
        final int byte59 = blocks[blocksOffset++] & 0xFF;
        final int byte60 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte58 & 15) << 9) | (byte59 << 1) | (byte60 >>> 7);
        final int byte61 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte60 & 127) << 6) | (byte61 >>> 2);
        final int byte62 = blocks[blocksOffset++] & 0xFF;
        final int byte63 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte61 & 3) << 11) | (byte62 << 3) | (byte63 >>> 5);
        final int byte64 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte63 & 31) << 8) | byte64;
        final int byte65 = blocks[blocksOffset++] & 0xFF;
        final int byte66 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte65 << 5) | (byte66 >>> 3);
        final int byte67 = blocks[blocksOffset++] & 0xFF;
        final int byte68 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte66 & 7) << 10) | (byte67 << 2) | (byte68 >>> 6);
        final int byte69 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte68 & 63) << 7) | (byte69 >>> 1);
        final int byte70 = blocks[blocksOffset++] & 0xFF;
        final int byte71 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte69 & 1) << 12) | (byte70 << 4) | (byte71 >>> 4);
        final int byte72 = blocks[blocksOffset++] & 0xFF;
        final int byte73 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte71 & 15) << 9) | (byte72 << 1) | (byte73 >>> 7);
        final int byte74 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte73 & 127) << 6) | (byte74 >>> 2);
        final int byte75 = blocks[blocksOffset++] & 0xFF;
        final int byte76 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte74 & 3) << 11) | (byte75 << 3) | (byte76 >>> 5);
        final int byte77 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte76 & 31) << 8) | byte77;
        final int byte78 = blocks[blocksOffset++] & 0xFF;
        final int byte79 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte78 << 5) | (byte79 >>> 3);
        final int byte80 = blocks[blocksOffset++] & 0xFF;
        final int byte81 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte79 & 7) << 10) | (byte80 << 2) | (byte81 >>> 6);
        final int byte82 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte81 & 63) << 7) | (byte82 >>> 1);
        final int byte83 = blocks[blocksOffset++] & 0xFF;
        final int byte84 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte82 & 1) << 12) | (byte83 << 4) | (byte84 >>> 4);
        final int byte85 = blocks[blocksOffset++] & 0xFF;
        final int byte86 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte84 & 15) << 9) | (byte85 << 1) | (byte86 >>> 7);
        final int byte87 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte86 & 127) << 6) | (byte87 >>> 2);
        final int byte88 = blocks[blocksOffset++] & 0xFF;
        final int byte89 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte87 & 3) << 11) | (byte88 << 3) | (byte89 >>> 5);
        final int byte90 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte89 & 31) << 8) | byte90;
        final int byte91 = blocks[blocksOffset++] & 0xFF;
        final int byte92 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte91 << 5) | (byte92 >>> 3);
        final int byte93 = blocks[blocksOffset++] & 0xFF;
        final int byte94 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte92 & 7) << 10) | (byte93 << 2) | (byte94 >>> 6);
        final int byte95 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte94 & 63) << 7) | (byte95 >>> 1);
        final int byte96 = blocks[blocksOffset++] & 0xFF;
        final int byte97 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte95 & 1) << 12) | (byte96 << 4) | (byte97 >>> 4);
        final int byte98 = blocks[blocksOffset++] & 0xFF;
        final int byte99 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte97 & 15) << 9) | (byte98 << 1) | (byte99 >>> 7);
        final int byte100 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte99 & 127) << 6) | (byte100 >>> 2);
        final int byte101 = blocks[blocksOffset++] & 0xFF;
        final int byte102 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte100 & 3) << 11) | (byte101 << 3) | (byte102 >>> 5);
        final int byte103 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte102 & 31) << 8) | byte103;
      }
    }

    @Override
    public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final long block0 = blocks[blocksOffset++];
        values[valuesOffset++] = block0 >>> 51;
        values[valuesOffset++] = (block0 >>> 38) & 8191L;
        values[valuesOffset++] = (block0 >>> 25) & 8191L;
        values[valuesOffset++] = (block0 >>> 12) & 8191L;
        final long block1 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block0 & 4095L) << 1) | (block1 >>> 63);
        values[valuesOffset++] = (block1 >>> 50) & 8191L;
        values[valuesOffset++] = (block1 >>> 37) & 8191L;
        values[valuesOffset++] = (block1 >>> 24) & 8191L;
        values[valuesOffset++] = (block1 >>> 11) & 8191L;
        final long block2 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block1 & 2047L) << 2) | (block2 >>> 62);
        values[valuesOffset++] = (block2 >>> 49) & 8191L;
        values[valuesOffset++] = (block2 >>> 36) & 8191L;
        values[valuesOffset++] = (block2 >>> 23) & 8191L;
        values[valuesOffset++] = (block2 >>> 10) & 8191L;
        final long block3 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block2 & 1023L) << 3) | (block3 >>> 61);
        values[valuesOffset++] = (block3 >>> 48) & 8191L;
        values[valuesOffset++] = (block3 >>> 35) & 8191L;
        values[valuesOffset++] = (block3 >>> 22) & 8191L;
        values[valuesOffset++] = (block3 >>> 9) & 8191L;
        final long block4 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block3 & 511L) << 4) | (block4 >>> 60);
        values[valuesOffset++] = (block4 >>> 47) & 8191L;
        values[valuesOffset++] = (block4 >>> 34) & 8191L;
        values[valuesOffset++] = (block4 >>> 21) & 8191L;
        values[valuesOffset++] = (block4 >>> 8) & 8191L;
        final long block5 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block4 & 255L) << 5) | (block5 >>> 59);
        values[valuesOffset++] = (block5 >>> 46) & 8191L;
        values[valuesOffset++] = (block5 >>> 33) & 8191L;
        values[valuesOffset++] = (block5 >>> 20) & 8191L;
        values[valuesOffset++] = (block5 >>> 7) & 8191L;
        final long block6 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block5 & 127L) << 6) | (block6 >>> 58);
        values[valuesOffset++] = (block6 >>> 45) & 8191L;
        values[valuesOffset++] = (block6 >>> 32) & 8191L;
        values[valuesOffset++] = (block6 >>> 19) & 8191L;
        values[valuesOffset++] = (block6 >>> 6) & 8191L;
        final long block7 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block6 & 63L) << 7) | (block7 >>> 57);
        values[valuesOffset++] = (block7 >>> 44) & 8191L;
        values[valuesOffset++] = (block7 >>> 31) & 8191L;
        values[valuesOffset++] = (block7 >>> 18) & 8191L;
        values[valuesOffset++] = (block7 >>> 5) & 8191L;
        final long block8 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block7 & 31L) << 8) | (block8 >>> 56);
        values[valuesOffset++] = (block8 >>> 43) & 8191L;
        values[valuesOffset++] = (block8 >>> 30) & 8191L;
        values[valuesOffset++] = (block8 >>> 17) & 8191L;
        values[valuesOffset++] = (block8 >>> 4) & 8191L;
        final long block9 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block8 & 15L) << 9) | (block9 >>> 55);
        values[valuesOffset++] = (block9 >>> 42) & 8191L;
        values[valuesOffset++] = (block9 >>> 29) & 8191L;
        values[valuesOffset++] = (block9 >>> 16) & 8191L;
        values[valuesOffset++] = (block9 >>> 3) & 8191L;
        final long block10 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block9 & 7L) << 10) | (block10 >>> 54);
        values[valuesOffset++] = (block10 >>> 41) & 8191L;
        values[valuesOffset++] = (block10 >>> 28) & 8191L;
        values[valuesOffset++] = (block10 >>> 15) & 8191L;
        values[valuesOffset++] = (block10 >>> 2) & 8191L;
        final long block11 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block10 & 3L) << 11) | (block11 >>> 53);
        values[valuesOffset++] = (block11 >>> 40) & 8191L;
        values[valuesOffset++] = (block11 >>> 27) & 8191L;
        values[valuesOffset++] = (block11 >>> 14) & 8191L;
        values[valuesOffset++] = (block11 >>> 1) & 8191L;
        final long block12 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block11 & 1L) << 12) | (block12 >>> 52);
        values[valuesOffset++] = (block12 >>> 39) & 8191L;
        values[valuesOffset++] = (block12 >>> 26) & 8191L;
        values[valuesOffset++] = (block12 >>> 13) & 8191L;
        values[valuesOffset++] = block12 & 8191L;
      }
    }

    @Override
    public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
      assert blocksOffset + 8 * iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final long byte0 = blocks[blocksOffset++] & 0xFF;
        final long byte1 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte0 << 5) | (byte1 >>> 3);
        final long byte2 = blocks[blocksOffset++] & 0xFF;
        final long byte3 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte1 & 7) << 10) | (byte2 << 2) | (byte3 >>> 6);
        final long byte4 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte3 & 63) << 7) | (byte4 >>> 1);
        final long byte5 = blocks[blocksOffset++] & 0xFF;
        final long byte6 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte4 & 1) << 12) | (byte5 << 4) | (byte6 >>> 4);
        final long byte7 = blocks[blocksOffset++] & 0xFF;
        final long byte8 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte6 & 15) << 9) | (byte7 << 1) | (byte8 >>> 7);
        final long byte9 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte8 & 127) << 6) | (byte9 >>> 2);
        final long byte10 = blocks[blocksOffset++] & 0xFF;
        final long byte11 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte9 & 3) << 11) | (byte10 << 3) | (byte11 >>> 5);
        final long byte12 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte11 & 31) << 8) | byte12;
        final long byte13 = blocks[blocksOffset++] & 0xFF;
        final long byte14 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte13 << 5) | (byte14 >>> 3);
        final long byte15 = blocks[blocksOffset++] & 0xFF;
        final long byte16 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte14 & 7) << 10) | (byte15 << 2) | (byte16 >>> 6);
        final long byte17 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte16 & 63) << 7) | (byte17 >>> 1);
        final long byte18 = blocks[blocksOffset++] & 0xFF;
        final long byte19 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte17 & 1) << 12) | (byte18 << 4) | (byte19 >>> 4);
        final long byte20 = blocks[blocksOffset++] & 0xFF;
        final long byte21 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte19 & 15) << 9) | (byte20 << 1) | (byte21 >>> 7);
        final long byte22 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte21 & 127) << 6) | (byte22 >>> 2);
        final long byte23 = blocks[blocksOffset++] & 0xFF;
        final long byte24 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte22 & 3) << 11) | (byte23 << 3) | (byte24 >>> 5);
        final long byte25 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte24 & 31) << 8) | byte25;
        final long byte26 = blocks[blocksOffset++] & 0xFF;
        final long byte27 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte26 << 5) | (byte27 >>> 3);
        final long byte28 = blocks[blocksOffset++] & 0xFF;
        final long byte29 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte27 & 7) << 10) | (byte28 << 2) | (byte29 >>> 6);
        final long byte30 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte29 & 63) << 7) | (byte30 >>> 1);
        final long byte31 = blocks[blocksOffset++] & 0xFF;
        final long byte32 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte30 & 1) << 12) | (byte31 << 4) | (byte32 >>> 4);
        final long byte33 = blocks[blocksOffset++] & 0xFF;
        final long byte34 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte32 & 15) << 9) | (byte33 << 1) | (byte34 >>> 7);
        final long byte35 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte34 & 127) << 6) | (byte35 >>> 2);
        final long byte36 = blocks[blocksOffset++] & 0xFF;
        final long byte37 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte35 & 3) << 11) | (byte36 << 3) | (byte37 >>> 5);
        final long byte38 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte37 & 31) << 8) | byte38;
        final long byte39 = blocks[blocksOffset++] & 0xFF;
        final long byte40 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte39 << 5) | (byte40 >>> 3);
        final long byte41 = blocks[blocksOffset++] & 0xFF;
        final long byte42 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte40 & 7) << 10) | (byte41 << 2) | (byte42 >>> 6);
        final long byte43 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte42 & 63) << 7) | (byte43 >>> 1);
        final long byte44 = blocks[blocksOffset++] & 0xFF;
        final long byte45 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte43 & 1) << 12) | (byte44 << 4) | (byte45 >>> 4);
        final long byte46 = blocks[blocksOffset++] & 0xFF;
        final long byte47 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte45 & 15) << 9) | (byte46 << 1) | (byte47 >>> 7);
        final long byte48 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte47 & 127) << 6) | (byte48 >>> 2);
        final long byte49 = blocks[blocksOffset++] & 0xFF;
        final long byte50 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte48 & 3) << 11) | (byte49 << 3) | (byte50 >>> 5);
        final long byte51 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte50 & 31) << 8) | byte51;
        final long byte52 = blocks[blocksOffset++] & 0xFF;
        final long byte53 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte52 << 5) | (byte53 >>> 3);
        final long byte54 = blocks[blocksOffset++] & 0xFF;
        final long byte55 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte53 & 7) << 10) | (byte54 << 2) | (byte55 >>> 6);
        final long byte56 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte55 & 63) << 7) | (byte56 >>> 1);
        final long byte57 = blocks[blocksOffset++] & 0xFF;
        final long byte58 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte56 & 1) << 12) | (byte57 << 4) | (byte58 >>> 4);
        final long byte59 = blocks[blocksOffset++] & 0xFF;
        final long byte60 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte58 & 15) << 9) | (byte59 << 1) | (byte60 >>> 7);
        final long byte61 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte60 & 127) << 6) | (byte61 >>> 2);
        final long byte62 = blocks[blocksOffset++] & 0xFF;
        final long byte63 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte61 & 3) << 11) | (byte62 << 3) | (byte63 >>> 5);
        final long byte64 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte63 & 31) << 8) | byte64;
        final long byte65 = blocks[blocksOffset++] & 0xFF;
        final long byte66 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte65 << 5) | (byte66 >>> 3);
        final long byte67 = blocks[blocksOffset++] & 0xFF;
        final long byte68 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte66 & 7) << 10) | (byte67 << 2) | (byte68 >>> 6);
        final long byte69 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte68 & 63) << 7) | (byte69 >>> 1);
        final long byte70 = blocks[blocksOffset++] & 0xFF;
        final long byte71 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte69 & 1) << 12) | (byte70 << 4) | (byte71 >>> 4);
        final long byte72 = blocks[blocksOffset++] & 0xFF;
        final long byte73 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte71 & 15) << 9) | (byte72 << 1) | (byte73 >>> 7);
        final long byte74 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte73 & 127) << 6) | (byte74 >>> 2);
        final long byte75 = blocks[blocksOffset++] & 0xFF;
        final long byte76 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte74 & 3) << 11) | (byte75 << 3) | (byte76 >>> 5);
        final long byte77 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte76 & 31) << 8) | byte77;
        final long byte78 = blocks[blocksOffset++] & 0xFF;
        final long byte79 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte78 << 5) | (byte79 >>> 3);
        final long byte80 = blocks[blocksOffset++] & 0xFF;
        final long byte81 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte79 & 7) << 10) | (byte80 << 2) | (byte81 >>> 6);
        final long byte82 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte81 & 63) << 7) | (byte82 >>> 1);
        final long byte83 = blocks[blocksOffset++] & 0xFF;
        final long byte84 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte82 & 1) << 12) | (byte83 << 4) | (byte84 >>> 4);
        final long byte85 = blocks[blocksOffset++] & 0xFF;
        final long byte86 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte84 & 15) << 9) | (byte85 << 1) | (byte86 >>> 7);
        final long byte87 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte86 & 127) << 6) | (byte87 >>> 2);
        final long byte88 = blocks[blocksOffset++] & 0xFF;
        final long byte89 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte87 & 3) << 11) | (byte88 << 3) | (byte89 >>> 5);
        final long byte90 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte89 & 31) << 8) | byte90;
        final long byte91 = blocks[blocksOffset++] & 0xFF;
        final long byte92 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte91 << 5) | (byte92 >>> 3);
        final long byte93 = blocks[blocksOffset++] & 0xFF;
        final long byte94 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte92 & 7) << 10) | (byte93 << 2) | (byte94 >>> 6);
        final long byte95 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte94 & 63) << 7) | (byte95 >>> 1);
        final long byte96 = blocks[blocksOffset++] & 0xFF;
        final long byte97 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte95 & 1) << 12) | (byte96 << 4) | (byte97 >>> 4);
        final long byte98 = blocks[blocksOffset++] & 0xFF;
        final long byte99 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte97 & 15) << 9) | (byte98 << 1) | (byte99 >>> 7);
        final long byte100 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte99 & 127) << 6) | (byte100 >>> 2);
        final long byte101 = blocks[blocksOffset++] & 0xFF;
        final long byte102 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte100 & 3) << 11) | (byte101 << 3) | (byte102 >>> 5);
        final long byte103 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte102 & 31) << 8) | byte103;
      }
    }

    @Override
    public void encode(int[] values, int valuesOffset, long[] blocks, int blocksOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 51) | ((values[valuesOffset++] & 0xffffffffL) << 38) | ((values[valuesOffset++] & 0xffffffffL) << 25) | ((values[valuesOffset++] & 0xffffffffL) << 12) | ((values[valuesOffset] & 0xffffffffL) >>> 1);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 63) | ((values[valuesOffset++] & 0xffffffffL) << 50) | ((values[valuesOffset++] & 0xffffffffL) << 37) | ((values[valuesOffset++] & 0xffffffffL) << 24) | ((values[valuesOffset++] & 0xffffffffL) << 11) | ((values[valuesOffset] & 0xffffffffL) >>> 2);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 62) | ((values[valuesOffset++] & 0xffffffffL) << 49) | ((values[valuesOffset++] & 0xffffffffL) << 36) | ((values[valuesOffset++] & 0xffffffffL) << 23) | ((values[valuesOffset++] & 0xffffffffL) << 10) | ((values[valuesOffset] & 0xffffffffL) >>> 3);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 61) | ((values[valuesOffset++] & 0xffffffffL) << 48) | ((values[valuesOffset++] & 0xffffffffL) << 35) | ((values[valuesOffset++] & 0xffffffffL) << 22) | ((values[valuesOffset++] & 0xffffffffL) << 9) | ((values[valuesOffset] & 0xffffffffL) >>> 4);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 60) | ((values[valuesOffset++] & 0xffffffffL) << 47) | ((values[valuesOffset++] & 0xffffffffL) << 34) | ((values[valuesOffset++] & 0xffffffffL) << 21) | ((values[valuesOffset++] & 0xffffffffL) << 8) | ((values[valuesOffset] & 0xffffffffL) >>> 5);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 59) | ((values[valuesOffset++] & 0xffffffffL) << 46) | ((values[valuesOffset++] & 0xffffffffL) << 33) | ((values[valuesOffset++] & 0xffffffffL) << 20) | ((values[valuesOffset++] & 0xffffffffL) << 7) | ((values[valuesOffset] & 0xffffffffL) >>> 6);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 58) | ((values[valuesOffset++] & 0xffffffffL) << 45) | ((values[valuesOffset++] & 0xffffffffL) << 32) | ((values[valuesOffset++] & 0xffffffffL) << 19) | ((values[valuesOffset++] & 0xffffffffL) << 6) | ((values[valuesOffset] & 0xffffffffL) >>> 7);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 57) | ((values[valuesOffset++] & 0xffffffffL) << 44) | ((values[valuesOffset++] & 0xffffffffL) << 31) | ((values[valuesOffset++] & 0xffffffffL) << 18) | ((values[valuesOffset++] & 0xffffffffL) << 5) | ((values[valuesOffset] & 0xffffffffL) >>> 8);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 56) | ((values[valuesOffset++] & 0xffffffffL) << 43) | ((values[valuesOffset++] & 0xffffffffL) << 30) | ((values[valuesOffset++] & 0xffffffffL) << 17) | ((values[valuesOffset++] & 0xffffffffL) << 4) | ((values[valuesOffset] & 0xffffffffL) >>> 9);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 55) | ((values[valuesOffset++] & 0xffffffffL) << 42) | ((values[valuesOffset++] & 0xffffffffL) << 29) | ((values[valuesOffset++] & 0xffffffffL) << 16) | ((values[valuesOffset++] & 0xffffffffL) << 3) | ((values[valuesOffset] & 0xffffffffL) >>> 10);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 54) | ((values[valuesOffset++] & 0xffffffffL) << 41) | ((values[valuesOffset++] & 0xffffffffL) << 28) | ((values[valuesOffset++] & 0xffffffffL) << 15) | ((values[valuesOffset++] & 0xffffffffL) << 2) | ((values[valuesOffset] & 0xffffffffL) >>> 11);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 53) | ((values[valuesOffset++] & 0xffffffffL) << 40) | ((values[valuesOffset++] & 0xffffffffL) << 27) | ((values[valuesOffset++] & 0xffffffffL) << 14) | ((values[valuesOffset++] & 0xffffffffL) << 1) | ((values[valuesOffset] & 0xffffffffL) >>> 12);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 52) | ((values[valuesOffset++] & 0xffffffffL) << 39) | ((values[valuesOffset++] & 0xffffffffL) << 26) | ((values[valuesOffset++] & 0xffffffffL) << 13) | (values[valuesOffset++] & 0xffffffffL);
      }
    }

    @Override
    public void encode(long[] values, int valuesOffset, long[] blocks, int blocksOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        blocks[blocksOffset++] = (values[valuesOffset++] << 51) | (values[valuesOffset++] << 38) | (values[valuesOffset++] << 25) | (values[valuesOffset++] << 12) | (values[valuesOffset] >>> 1);
        blocks[blocksOffset++] = (values[valuesOffset++] << 63) | (values[valuesOffset++] << 50) | (values[valuesOffset++] << 37) | (values[valuesOffset++] << 24) | (values[valuesOffset++] << 11) | (values[valuesOffset] >>> 2);
        blocks[blocksOffset++] = (values[valuesOffset++] << 62) | (values[valuesOffset++] << 49) | (values[valuesOffset++] << 36) | (values[valuesOffset++] << 23) | (values[valuesOffset++] << 10) | (values[valuesOffset] >>> 3);
        blocks[blocksOffset++] = (values[valuesOffset++] << 61) | (values[valuesOffset++] << 48) | (values[valuesOffset++] << 35) | (values[valuesOffset++] << 22) | (values[valuesOffset++] << 9) | (values[valuesOffset] >>> 4);
        blocks[blocksOffset++] = (values[valuesOffset++] << 60) | (values[valuesOffset++] << 47) | (values[valuesOffset++] << 34) | (values[valuesOffset++] << 21) | (values[valuesOffset++] << 8) | (values[valuesOffset] >>> 5);
        blocks[blocksOffset++] = (values[valuesOffset++] << 59) | (values[valuesOffset++] << 46) | (values[valuesOffset++] << 33) | (values[valuesOffset++] << 20) | (values[valuesOffset++] << 7) | (values[valuesOffset] >>> 6);
        blocks[blocksOffset++] = (values[valuesOffset++] << 58) | (values[valuesOffset++] << 45) | (values[valuesOffset++] << 32) | (values[valuesOffset++] << 19) | (values[valuesOffset++] << 6) | (values[valuesOffset] >>> 7);
        blocks[blocksOffset++] = (values[valuesOffset++] << 57) | (values[valuesOffset++] << 44) | (values[valuesOffset++] << 31) | (values[valuesOffset++] << 18) | (values[valuesOffset++] << 5) | (values[valuesOffset] >>> 8);
        blocks[blocksOffset++] = (values[valuesOffset++] << 56) | (values[valuesOffset++] << 43) | (values[valuesOffset++] << 30) | (values[valuesOffset++] << 17) | (values[valuesOffset++] << 4) | (values[valuesOffset] >>> 9);
        blocks[blocksOffset++] = (values[valuesOffset++] << 55) | (values[valuesOffset++] << 42) | (values[valuesOffset++] << 29) | (values[valuesOffset++] << 16) | (values[valuesOffset++] << 3) | (values[valuesOffset] >>> 10);
        blocks[blocksOffset++] = (values[valuesOffset++] << 54) | (values[valuesOffset++] << 41) | (values[valuesOffset++] << 28) | (values[valuesOffset++] << 15) | (values[valuesOffset++] << 2) | (values[valuesOffset] >>> 11);
        blocks[blocksOffset++] = (values[valuesOffset++] << 53) | (values[valuesOffset++] << 40) | (values[valuesOffset++] << 27) | (values[valuesOffset++] << 14) | (values[valuesOffset++] << 1) | (values[valuesOffset] >>> 12);
        blocks[blocksOffset++] = (values[valuesOffset++] << 52) | (values[valuesOffset++] << 39) | (values[valuesOffset++] << 26) | (values[valuesOffset++] << 13) | values[valuesOffset++];
      }
    }

}
