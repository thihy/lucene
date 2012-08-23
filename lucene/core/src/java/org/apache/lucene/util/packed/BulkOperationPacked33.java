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
final class BulkOperationPacked33 extends BulkOperation {
    public int blockCount() {
      return 33;
    }

    public int valueCount() {
      return 64;
    }

    public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
      throw new UnsupportedOperationException();
    }

    public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
      throw new UnsupportedOperationException();
    }

    public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final long block0 = blocks[blocksOffset++];
        values[valuesOffset++] = block0 >>> 31;
        final long block1 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block0 & 2147483647L) << 2) | (block1 >>> 62);
        values[valuesOffset++] = (block1 >>> 29) & 8589934591L;
        final long block2 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block1 & 536870911L) << 4) | (block2 >>> 60);
        values[valuesOffset++] = (block2 >>> 27) & 8589934591L;
        final long block3 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block2 & 134217727L) << 6) | (block3 >>> 58);
        values[valuesOffset++] = (block3 >>> 25) & 8589934591L;
        final long block4 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block3 & 33554431L) << 8) | (block4 >>> 56);
        values[valuesOffset++] = (block4 >>> 23) & 8589934591L;
        final long block5 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block4 & 8388607L) << 10) | (block5 >>> 54);
        values[valuesOffset++] = (block5 >>> 21) & 8589934591L;
        final long block6 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block5 & 2097151L) << 12) | (block6 >>> 52);
        values[valuesOffset++] = (block6 >>> 19) & 8589934591L;
        final long block7 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block6 & 524287L) << 14) | (block7 >>> 50);
        values[valuesOffset++] = (block7 >>> 17) & 8589934591L;
        final long block8 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block7 & 131071L) << 16) | (block8 >>> 48);
        values[valuesOffset++] = (block8 >>> 15) & 8589934591L;
        final long block9 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block8 & 32767L) << 18) | (block9 >>> 46);
        values[valuesOffset++] = (block9 >>> 13) & 8589934591L;
        final long block10 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block9 & 8191L) << 20) | (block10 >>> 44);
        values[valuesOffset++] = (block10 >>> 11) & 8589934591L;
        final long block11 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block10 & 2047L) << 22) | (block11 >>> 42);
        values[valuesOffset++] = (block11 >>> 9) & 8589934591L;
        final long block12 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block11 & 511L) << 24) | (block12 >>> 40);
        values[valuesOffset++] = (block12 >>> 7) & 8589934591L;
        final long block13 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block12 & 127L) << 26) | (block13 >>> 38);
        values[valuesOffset++] = (block13 >>> 5) & 8589934591L;
        final long block14 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block13 & 31L) << 28) | (block14 >>> 36);
        values[valuesOffset++] = (block14 >>> 3) & 8589934591L;
        final long block15 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block14 & 7L) << 30) | (block15 >>> 34);
        values[valuesOffset++] = (block15 >>> 1) & 8589934591L;
        final long block16 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block15 & 1L) << 32) | (block16 >>> 32);
        final long block17 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block16 & 4294967295L) << 1) | (block17 >>> 63);
        values[valuesOffset++] = (block17 >>> 30) & 8589934591L;
        final long block18 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block17 & 1073741823L) << 3) | (block18 >>> 61);
        values[valuesOffset++] = (block18 >>> 28) & 8589934591L;
        final long block19 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block18 & 268435455L) << 5) | (block19 >>> 59);
        values[valuesOffset++] = (block19 >>> 26) & 8589934591L;
        final long block20 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block19 & 67108863L) << 7) | (block20 >>> 57);
        values[valuesOffset++] = (block20 >>> 24) & 8589934591L;
        final long block21 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block20 & 16777215L) << 9) | (block21 >>> 55);
        values[valuesOffset++] = (block21 >>> 22) & 8589934591L;
        final long block22 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block21 & 4194303L) << 11) | (block22 >>> 53);
        values[valuesOffset++] = (block22 >>> 20) & 8589934591L;
        final long block23 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block22 & 1048575L) << 13) | (block23 >>> 51);
        values[valuesOffset++] = (block23 >>> 18) & 8589934591L;
        final long block24 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block23 & 262143L) << 15) | (block24 >>> 49);
        values[valuesOffset++] = (block24 >>> 16) & 8589934591L;
        final long block25 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block24 & 65535L) << 17) | (block25 >>> 47);
        values[valuesOffset++] = (block25 >>> 14) & 8589934591L;
        final long block26 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block25 & 16383L) << 19) | (block26 >>> 45);
        values[valuesOffset++] = (block26 >>> 12) & 8589934591L;
        final long block27 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block26 & 4095L) << 21) | (block27 >>> 43);
        values[valuesOffset++] = (block27 >>> 10) & 8589934591L;
        final long block28 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block27 & 1023L) << 23) | (block28 >>> 41);
        values[valuesOffset++] = (block28 >>> 8) & 8589934591L;
        final long block29 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block28 & 255L) << 25) | (block29 >>> 39);
        values[valuesOffset++] = (block29 >>> 6) & 8589934591L;
        final long block30 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block29 & 63L) << 27) | (block30 >>> 37);
        values[valuesOffset++] = (block30 >>> 4) & 8589934591L;
        final long block31 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block30 & 15L) << 29) | (block31 >>> 35);
        values[valuesOffset++] = (block31 >>> 2) & 8589934591L;
        final long block32 = blocks[blocksOffset++];
        values[valuesOffset++] = ((block31 & 3L) << 31) | (block32 >>> 33);
        values[valuesOffset++] = block32 & 8589934591L;
      }
    }

    public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
      assert blocksOffset + 8 * iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        final long byte0 = blocks[blocksOffset++] & 0xFF;
        final long byte1 = blocks[blocksOffset++] & 0xFF;
        final long byte2 = blocks[blocksOffset++] & 0xFF;
        final long byte3 = blocks[blocksOffset++] & 0xFF;
        final long byte4 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte0 << 25) | (byte1 << 17) | (byte2 << 9) | (byte3 << 1) | (byte4 >>> 7);
        final long byte5 = blocks[blocksOffset++] & 0xFF;
        final long byte6 = blocks[blocksOffset++] & 0xFF;
        final long byte7 = blocks[blocksOffset++] & 0xFF;
        final long byte8 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte4 & 127) << 26) | (byte5 << 18) | (byte6 << 10) | (byte7 << 2) | (byte8 >>> 6);
        final long byte9 = blocks[blocksOffset++] & 0xFF;
        final long byte10 = blocks[blocksOffset++] & 0xFF;
        final long byte11 = blocks[blocksOffset++] & 0xFF;
        final long byte12 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte8 & 63) << 27) | (byte9 << 19) | (byte10 << 11) | (byte11 << 3) | (byte12 >>> 5);
        final long byte13 = blocks[blocksOffset++] & 0xFF;
        final long byte14 = blocks[blocksOffset++] & 0xFF;
        final long byte15 = blocks[blocksOffset++] & 0xFF;
        final long byte16 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte12 & 31) << 28) | (byte13 << 20) | (byte14 << 12) | (byte15 << 4) | (byte16 >>> 4);
        final long byte17 = blocks[blocksOffset++] & 0xFF;
        final long byte18 = blocks[blocksOffset++] & 0xFF;
        final long byte19 = blocks[blocksOffset++] & 0xFF;
        final long byte20 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte16 & 15) << 29) | (byte17 << 21) | (byte18 << 13) | (byte19 << 5) | (byte20 >>> 3);
        final long byte21 = blocks[blocksOffset++] & 0xFF;
        final long byte22 = blocks[blocksOffset++] & 0xFF;
        final long byte23 = blocks[blocksOffset++] & 0xFF;
        final long byte24 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte20 & 7) << 30) | (byte21 << 22) | (byte22 << 14) | (byte23 << 6) | (byte24 >>> 2);
        final long byte25 = blocks[blocksOffset++] & 0xFF;
        final long byte26 = blocks[blocksOffset++] & 0xFF;
        final long byte27 = blocks[blocksOffset++] & 0xFF;
        final long byte28 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte24 & 3) << 31) | (byte25 << 23) | (byte26 << 15) | (byte27 << 7) | (byte28 >>> 1);
        final long byte29 = blocks[blocksOffset++] & 0xFF;
        final long byte30 = blocks[blocksOffset++] & 0xFF;
        final long byte31 = blocks[blocksOffset++] & 0xFF;
        final long byte32 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte28 & 1) << 32) | (byte29 << 24) | (byte30 << 16) | (byte31 << 8) | byte32;
        final long byte33 = blocks[blocksOffset++] & 0xFF;
        final long byte34 = blocks[blocksOffset++] & 0xFF;
        final long byte35 = blocks[blocksOffset++] & 0xFF;
        final long byte36 = blocks[blocksOffset++] & 0xFF;
        final long byte37 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte33 << 25) | (byte34 << 17) | (byte35 << 9) | (byte36 << 1) | (byte37 >>> 7);
        final long byte38 = blocks[blocksOffset++] & 0xFF;
        final long byte39 = blocks[blocksOffset++] & 0xFF;
        final long byte40 = blocks[blocksOffset++] & 0xFF;
        final long byte41 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte37 & 127) << 26) | (byte38 << 18) | (byte39 << 10) | (byte40 << 2) | (byte41 >>> 6);
        final long byte42 = blocks[blocksOffset++] & 0xFF;
        final long byte43 = blocks[blocksOffset++] & 0xFF;
        final long byte44 = blocks[blocksOffset++] & 0xFF;
        final long byte45 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte41 & 63) << 27) | (byte42 << 19) | (byte43 << 11) | (byte44 << 3) | (byte45 >>> 5);
        final long byte46 = blocks[blocksOffset++] & 0xFF;
        final long byte47 = blocks[blocksOffset++] & 0xFF;
        final long byte48 = blocks[blocksOffset++] & 0xFF;
        final long byte49 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte45 & 31) << 28) | (byte46 << 20) | (byte47 << 12) | (byte48 << 4) | (byte49 >>> 4);
        final long byte50 = blocks[blocksOffset++] & 0xFF;
        final long byte51 = blocks[blocksOffset++] & 0xFF;
        final long byte52 = blocks[blocksOffset++] & 0xFF;
        final long byte53 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte49 & 15) << 29) | (byte50 << 21) | (byte51 << 13) | (byte52 << 5) | (byte53 >>> 3);
        final long byte54 = blocks[blocksOffset++] & 0xFF;
        final long byte55 = blocks[blocksOffset++] & 0xFF;
        final long byte56 = blocks[blocksOffset++] & 0xFF;
        final long byte57 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte53 & 7) << 30) | (byte54 << 22) | (byte55 << 14) | (byte56 << 6) | (byte57 >>> 2);
        final long byte58 = blocks[blocksOffset++] & 0xFF;
        final long byte59 = blocks[blocksOffset++] & 0xFF;
        final long byte60 = blocks[blocksOffset++] & 0xFF;
        final long byte61 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte57 & 3) << 31) | (byte58 << 23) | (byte59 << 15) | (byte60 << 7) | (byte61 >>> 1);
        final long byte62 = blocks[blocksOffset++] & 0xFF;
        final long byte63 = blocks[blocksOffset++] & 0xFF;
        final long byte64 = blocks[blocksOffset++] & 0xFF;
        final long byte65 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte61 & 1) << 32) | (byte62 << 24) | (byte63 << 16) | (byte64 << 8) | byte65;
        final long byte66 = blocks[blocksOffset++] & 0xFF;
        final long byte67 = blocks[blocksOffset++] & 0xFF;
        final long byte68 = blocks[blocksOffset++] & 0xFF;
        final long byte69 = blocks[blocksOffset++] & 0xFF;
        final long byte70 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte66 << 25) | (byte67 << 17) | (byte68 << 9) | (byte69 << 1) | (byte70 >>> 7);
        final long byte71 = blocks[blocksOffset++] & 0xFF;
        final long byte72 = blocks[blocksOffset++] & 0xFF;
        final long byte73 = blocks[blocksOffset++] & 0xFF;
        final long byte74 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte70 & 127) << 26) | (byte71 << 18) | (byte72 << 10) | (byte73 << 2) | (byte74 >>> 6);
        final long byte75 = blocks[blocksOffset++] & 0xFF;
        final long byte76 = blocks[blocksOffset++] & 0xFF;
        final long byte77 = blocks[blocksOffset++] & 0xFF;
        final long byte78 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte74 & 63) << 27) | (byte75 << 19) | (byte76 << 11) | (byte77 << 3) | (byte78 >>> 5);
        final long byte79 = blocks[blocksOffset++] & 0xFF;
        final long byte80 = blocks[blocksOffset++] & 0xFF;
        final long byte81 = blocks[blocksOffset++] & 0xFF;
        final long byte82 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte78 & 31) << 28) | (byte79 << 20) | (byte80 << 12) | (byte81 << 4) | (byte82 >>> 4);
        final long byte83 = blocks[blocksOffset++] & 0xFF;
        final long byte84 = blocks[blocksOffset++] & 0xFF;
        final long byte85 = blocks[blocksOffset++] & 0xFF;
        final long byte86 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte82 & 15) << 29) | (byte83 << 21) | (byte84 << 13) | (byte85 << 5) | (byte86 >>> 3);
        final long byte87 = blocks[blocksOffset++] & 0xFF;
        final long byte88 = blocks[blocksOffset++] & 0xFF;
        final long byte89 = blocks[blocksOffset++] & 0xFF;
        final long byte90 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte86 & 7) << 30) | (byte87 << 22) | (byte88 << 14) | (byte89 << 6) | (byte90 >>> 2);
        final long byte91 = blocks[blocksOffset++] & 0xFF;
        final long byte92 = blocks[blocksOffset++] & 0xFF;
        final long byte93 = blocks[blocksOffset++] & 0xFF;
        final long byte94 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte90 & 3) << 31) | (byte91 << 23) | (byte92 << 15) | (byte93 << 7) | (byte94 >>> 1);
        final long byte95 = blocks[blocksOffset++] & 0xFF;
        final long byte96 = blocks[blocksOffset++] & 0xFF;
        final long byte97 = blocks[blocksOffset++] & 0xFF;
        final long byte98 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte94 & 1) << 32) | (byte95 << 24) | (byte96 << 16) | (byte97 << 8) | byte98;
        final long byte99 = blocks[blocksOffset++] & 0xFF;
        final long byte100 = blocks[blocksOffset++] & 0xFF;
        final long byte101 = blocks[blocksOffset++] & 0xFF;
        final long byte102 = blocks[blocksOffset++] & 0xFF;
        final long byte103 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte99 << 25) | (byte100 << 17) | (byte101 << 9) | (byte102 << 1) | (byte103 >>> 7);
        final long byte104 = blocks[blocksOffset++] & 0xFF;
        final long byte105 = blocks[blocksOffset++] & 0xFF;
        final long byte106 = blocks[blocksOffset++] & 0xFF;
        final long byte107 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte103 & 127) << 26) | (byte104 << 18) | (byte105 << 10) | (byte106 << 2) | (byte107 >>> 6);
        final long byte108 = blocks[blocksOffset++] & 0xFF;
        final long byte109 = blocks[blocksOffset++] & 0xFF;
        final long byte110 = blocks[blocksOffset++] & 0xFF;
        final long byte111 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte107 & 63) << 27) | (byte108 << 19) | (byte109 << 11) | (byte110 << 3) | (byte111 >>> 5);
        final long byte112 = blocks[blocksOffset++] & 0xFF;
        final long byte113 = blocks[blocksOffset++] & 0xFF;
        final long byte114 = blocks[blocksOffset++] & 0xFF;
        final long byte115 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte111 & 31) << 28) | (byte112 << 20) | (byte113 << 12) | (byte114 << 4) | (byte115 >>> 4);
        final long byte116 = blocks[blocksOffset++] & 0xFF;
        final long byte117 = blocks[blocksOffset++] & 0xFF;
        final long byte118 = blocks[blocksOffset++] & 0xFF;
        final long byte119 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte115 & 15) << 29) | (byte116 << 21) | (byte117 << 13) | (byte118 << 5) | (byte119 >>> 3);
        final long byte120 = blocks[blocksOffset++] & 0xFF;
        final long byte121 = blocks[blocksOffset++] & 0xFF;
        final long byte122 = blocks[blocksOffset++] & 0xFF;
        final long byte123 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte119 & 7) << 30) | (byte120 << 22) | (byte121 << 14) | (byte122 << 6) | (byte123 >>> 2);
        final long byte124 = blocks[blocksOffset++] & 0xFF;
        final long byte125 = blocks[blocksOffset++] & 0xFF;
        final long byte126 = blocks[blocksOffset++] & 0xFF;
        final long byte127 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte123 & 3) << 31) | (byte124 << 23) | (byte125 << 15) | (byte126 << 7) | (byte127 >>> 1);
        final long byte128 = blocks[blocksOffset++] & 0xFF;
        final long byte129 = blocks[blocksOffset++] & 0xFF;
        final long byte130 = blocks[blocksOffset++] & 0xFF;
        final long byte131 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte127 & 1) << 32) | (byte128 << 24) | (byte129 << 16) | (byte130 << 8) | byte131;
        final long byte132 = blocks[blocksOffset++] & 0xFF;
        final long byte133 = blocks[blocksOffset++] & 0xFF;
        final long byte134 = blocks[blocksOffset++] & 0xFF;
        final long byte135 = blocks[blocksOffset++] & 0xFF;
        final long byte136 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte132 << 25) | (byte133 << 17) | (byte134 << 9) | (byte135 << 1) | (byte136 >>> 7);
        final long byte137 = blocks[blocksOffset++] & 0xFF;
        final long byte138 = blocks[blocksOffset++] & 0xFF;
        final long byte139 = blocks[blocksOffset++] & 0xFF;
        final long byte140 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte136 & 127) << 26) | (byte137 << 18) | (byte138 << 10) | (byte139 << 2) | (byte140 >>> 6);
        final long byte141 = blocks[blocksOffset++] & 0xFF;
        final long byte142 = blocks[blocksOffset++] & 0xFF;
        final long byte143 = blocks[blocksOffset++] & 0xFF;
        final long byte144 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte140 & 63) << 27) | (byte141 << 19) | (byte142 << 11) | (byte143 << 3) | (byte144 >>> 5);
        final long byte145 = blocks[blocksOffset++] & 0xFF;
        final long byte146 = blocks[blocksOffset++] & 0xFF;
        final long byte147 = blocks[blocksOffset++] & 0xFF;
        final long byte148 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte144 & 31) << 28) | (byte145 << 20) | (byte146 << 12) | (byte147 << 4) | (byte148 >>> 4);
        final long byte149 = blocks[blocksOffset++] & 0xFF;
        final long byte150 = blocks[blocksOffset++] & 0xFF;
        final long byte151 = blocks[blocksOffset++] & 0xFF;
        final long byte152 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte148 & 15) << 29) | (byte149 << 21) | (byte150 << 13) | (byte151 << 5) | (byte152 >>> 3);
        final long byte153 = blocks[blocksOffset++] & 0xFF;
        final long byte154 = blocks[blocksOffset++] & 0xFF;
        final long byte155 = blocks[blocksOffset++] & 0xFF;
        final long byte156 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte152 & 7) << 30) | (byte153 << 22) | (byte154 << 14) | (byte155 << 6) | (byte156 >>> 2);
        final long byte157 = blocks[blocksOffset++] & 0xFF;
        final long byte158 = blocks[blocksOffset++] & 0xFF;
        final long byte159 = blocks[blocksOffset++] & 0xFF;
        final long byte160 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte156 & 3) << 31) | (byte157 << 23) | (byte158 << 15) | (byte159 << 7) | (byte160 >>> 1);
        final long byte161 = blocks[blocksOffset++] & 0xFF;
        final long byte162 = blocks[blocksOffset++] & 0xFF;
        final long byte163 = blocks[blocksOffset++] & 0xFF;
        final long byte164 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte160 & 1) << 32) | (byte161 << 24) | (byte162 << 16) | (byte163 << 8) | byte164;
        final long byte165 = blocks[blocksOffset++] & 0xFF;
        final long byte166 = blocks[blocksOffset++] & 0xFF;
        final long byte167 = blocks[blocksOffset++] & 0xFF;
        final long byte168 = blocks[blocksOffset++] & 0xFF;
        final long byte169 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte165 << 25) | (byte166 << 17) | (byte167 << 9) | (byte168 << 1) | (byte169 >>> 7);
        final long byte170 = blocks[blocksOffset++] & 0xFF;
        final long byte171 = blocks[blocksOffset++] & 0xFF;
        final long byte172 = blocks[blocksOffset++] & 0xFF;
        final long byte173 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte169 & 127) << 26) | (byte170 << 18) | (byte171 << 10) | (byte172 << 2) | (byte173 >>> 6);
        final long byte174 = blocks[blocksOffset++] & 0xFF;
        final long byte175 = blocks[blocksOffset++] & 0xFF;
        final long byte176 = blocks[blocksOffset++] & 0xFF;
        final long byte177 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte173 & 63) << 27) | (byte174 << 19) | (byte175 << 11) | (byte176 << 3) | (byte177 >>> 5);
        final long byte178 = blocks[blocksOffset++] & 0xFF;
        final long byte179 = blocks[blocksOffset++] & 0xFF;
        final long byte180 = blocks[blocksOffset++] & 0xFF;
        final long byte181 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte177 & 31) << 28) | (byte178 << 20) | (byte179 << 12) | (byte180 << 4) | (byte181 >>> 4);
        final long byte182 = blocks[blocksOffset++] & 0xFF;
        final long byte183 = blocks[blocksOffset++] & 0xFF;
        final long byte184 = blocks[blocksOffset++] & 0xFF;
        final long byte185 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte181 & 15) << 29) | (byte182 << 21) | (byte183 << 13) | (byte184 << 5) | (byte185 >>> 3);
        final long byte186 = blocks[blocksOffset++] & 0xFF;
        final long byte187 = blocks[blocksOffset++] & 0xFF;
        final long byte188 = blocks[blocksOffset++] & 0xFF;
        final long byte189 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte185 & 7) << 30) | (byte186 << 22) | (byte187 << 14) | (byte188 << 6) | (byte189 >>> 2);
        final long byte190 = blocks[blocksOffset++] & 0xFF;
        final long byte191 = blocks[blocksOffset++] & 0xFF;
        final long byte192 = blocks[blocksOffset++] & 0xFF;
        final long byte193 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte189 & 3) << 31) | (byte190 << 23) | (byte191 << 15) | (byte192 << 7) | (byte193 >>> 1);
        final long byte194 = blocks[blocksOffset++] & 0xFF;
        final long byte195 = blocks[blocksOffset++] & 0xFF;
        final long byte196 = blocks[blocksOffset++] & 0xFF;
        final long byte197 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte193 & 1) << 32) | (byte194 << 24) | (byte195 << 16) | (byte196 << 8) | byte197;
        final long byte198 = blocks[blocksOffset++] & 0xFF;
        final long byte199 = blocks[blocksOffset++] & 0xFF;
        final long byte200 = blocks[blocksOffset++] & 0xFF;
        final long byte201 = blocks[blocksOffset++] & 0xFF;
        final long byte202 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte198 << 25) | (byte199 << 17) | (byte200 << 9) | (byte201 << 1) | (byte202 >>> 7);
        final long byte203 = blocks[blocksOffset++] & 0xFF;
        final long byte204 = blocks[blocksOffset++] & 0xFF;
        final long byte205 = blocks[blocksOffset++] & 0xFF;
        final long byte206 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte202 & 127) << 26) | (byte203 << 18) | (byte204 << 10) | (byte205 << 2) | (byte206 >>> 6);
        final long byte207 = blocks[blocksOffset++] & 0xFF;
        final long byte208 = blocks[blocksOffset++] & 0xFF;
        final long byte209 = blocks[blocksOffset++] & 0xFF;
        final long byte210 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte206 & 63) << 27) | (byte207 << 19) | (byte208 << 11) | (byte209 << 3) | (byte210 >>> 5);
        final long byte211 = blocks[blocksOffset++] & 0xFF;
        final long byte212 = blocks[blocksOffset++] & 0xFF;
        final long byte213 = blocks[blocksOffset++] & 0xFF;
        final long byte214 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte210 & 31) << 28) | (byte211 << 20) | (byte212 << 12) | (byte213 << 4) | (byte214 >>> 4);
        final long byte215 = blocks[blocksOffset++] & 0xFF;
        final long byte216 = blocks[blocksOffset++] & 0xFF;
        final long byte217 = blocks[blocksOffset++] & 0xFF;
        final long byte218 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte214 & 15) << 29) | (byte215 << 21) | (byte216 << 13) | (byte217 << 5) | (byte218 >>> 3);
        final long byte219 = blocks[blocksOffset++] & 0xFF;
        final long byte220 = blocks[blocksOffset++] & 0xFF;
        final long byte221 = blocks[blocksOffset++] & 0xFF;
        final long byte222 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte218 & 7) << 30) | (byte219 << 22) | (byte220 << 14) | (byte221 << 6) | (byte222 >>> 2);
        final long byte223 = blocks[blocksOffset++] & 0xFF;
        final long byte224 = blocks[blocksOffset++] & 0xFF;
        final long byte225 = blocks[blocksOffset++] & 0xFF;
        final long byte226 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte222 & 3) << 31) | (byte223 << 23) | (byte224 << 15) | (byte225 << 7) | (byte226 >>> 1);
        final long byte227 = blocks[blocksOffset++] & 0xFF;
        final long byte228 = blocks[blocksOffset++] & 0xFF;
        final long byte229 = blocks[blocksOffset++] & 0xFF;
        final long byte230 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte226 & 1) << 32) | (byte227 << 24) | (byte228 << 16) | (byte229 << 8) | byte230;
        final long byte231 = blocks[blocksOffset++] & 0xFF;
        final long byte232 = blocks[blocksOffset++] & 0xFF;
        final long byte233 = blocks[blocksOffset++] & 0xFF;
        final long byte234 = blocks[blocksOffset++] & 0xFF;
        final long byte235 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = (byte231 << 25) | (byte232 << 17) | (byte233 << 9) | (byte234 << 1) | (byte235 >>> 7);
        final long byte236 = blocks[blocksOffset++] & 0xFF;
        final long byte237 = blocks[blocksOffset++] & 0xFF;
        final long byte238 = blocks[blocksOffset++] & 0xFF;
        final long byte239 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte235 & 127) << 26) | (byte236 << 18) | (byte237 << 10) | (byte238 << 2) | (byte239 >>> 6);
        final long byte240 = blocks[blocksOffset++] & 0xFF;
        final long byte241 = blocks[blocksOffset++] & 0xFF;
        final long byte242 = blocks[blocksOffset++] & 0xFF;
        final long byte243 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte239 & 63) << 27) | (byte240 << 19) | (byte241 << 11) | (byte242 << 3) | (byte243 >>> 5);
        final long byte244 = blocks[blocksOffset++] & 0xFF;
        final long byte245 = blocks[blocksOffset++] & 0xFF;
        final long byte246 = blocks[blocksOffset++] & 0xFF;
        final long byte247 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte243 & 31) << 28) | (byte244 << 20) | (byte245 << 12) | (byte246 << 4) | (byte247 >>> 4);
        final long byte248 = blocks[blocksOffset++] & 0xFF;
        final long byte249 = blocks[blocksOffset++] & 0xFF;
        final long byte250 = blocks[blocksOffset++] & 0xFF;
        final long byte251 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte247 & 15) << 29) | (byte248 << 21) | (byte249 << 13) | (byte250 << 5) | (byte251 >>> 3);
        final long byte252 = blocks[blocksOffset++] & 0xFF;
        final long byte253 = blocks[blocksOffset++] & 0xFF;
        final long byte254 = blocks[blocksOffset++] & 0xFF;
        final long byte255 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte251 & 7) << 30) | (byte252 << 22) | (byte253 << 14) | (byte254 << 6) | (byte255 >>> 2);
        final long byte256 = blocks[blocksOffset++] & 0xFF;
        final long byte257 = blocks[blocksOffset++] & 0xFF;
        final long byte258 = blocks[blocksOffset++] & 0xFF;
        final long byte259 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte255 & 3) << 31) | (byte256 << 23) | (byte257 << 15) | (byte258 << 7) | (byte259 >>> 1);
        final long byte260 = blocks[blocksOffset++] & 0xFF;
        final long byte261 = blocks[blocksOffset++] & 0xFF;
        final long byte262 = blocks[blocksOffset++] & 0xFF;
        final long byte263 = blocks[blocksOffset++] & 0xFF;
        values[valuesOffset++] = ((byte259 & 1) << 32) | (byte260 << 24) | (byte261 << 16) | (byte262 << 8) | byte263;
      }
    }

    public void encode(int[] values, int valuesOffset, long[] blocks, int blocksOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 31) | ((values[valuesOffset] & 0xffffffffL) >>> 2);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 62) | ((values[valuesOffset++] & 0xffffffffL) << 29) | ((values[valuesOffset] & 0xffffffffL) >>> 4);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 60) | ((values[valuesOffset++] & 0xffffffffL) << 27) | ((values[valuesOffset] & 0xffffffffL) >>> 6);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 58) | ((values[valuesOffset++] & 0xffffffffL) << 25) | ((values[valuesOffset] & 0xffffffffL) >>> 8);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 56) | ((values[valuesOffset++] & 0xffffffffL) << 23) | ((values[valuesOffset] & 0xffffffffL) >>> 10);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 54) | ((values[valuesOffset++] & 0xffffffffL) << 21) | ((values[valuesOffset] & 0xffffffffL) >>> 12);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 52) | ((values[valuesOffset++] & 0xffffffffL) << 19) | ((values[valuesOffset] & 0xffffffffL) >>> 14);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 50) | ((values[valuesOffset++] & 0xffffffffL) << 17) | ((values[valuesOffset] & 0xffffffffL) >>> 16);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 48) | ((values[valuesOffset++] & 0xffffffffL) << 15) | ((values[valuesOffset] & 0xffffffffL) >>> 18);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 46) | ((values[valuesOffset++] & 0xffffffffL) << 13) | ((values[valuesOffset] & 0xffffffffL) >>> 20);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 44) | ((values[valuesOffset++] & 0xffffffffL) << 11) | ((values[valuesOffset] & 0xffffffffL) >>> 22);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 42) | ((values[valuesOffset++] & 0xffffffffL) << 9) | ((values[valuesOffset] & 0xffffffffL) >>> 24);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 40) | ((values[valuesOffset++] & 0xffffffffL) << 7) | ((values[valuesOffset] & 0xffffffffL) >>> 26);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 38) | ((values[valuesOffset++] & 0xffffffffL) << 5) | ((values[valuesOffset] & 0xffffffffL) >>> 28);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 36) | ((values[valuesOffset++] & 0xffffffffL) << 3) | ((values[valuesOffset] & 0xffffffffL) >>> 30);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 34) | ((values[valuesOffset++] & 0xffffffffL) << 1) | ((values[valuesOffset] & 0xffffffffL) >>> 32);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 32) | ((values[valuesOffset] & 0xffffffffL) >>> 1);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 63) | ((values[valuesOffset++] & 0xffffffffL) << 30) | ((values[valuesOffset] & 0xffffffffL) >>> 3);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 61) | ((values[valuesOffset++] & 0xffffffffL) << 28) | ((values[valuesOffset] & 0xffffffffL) >>> 5);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 59) | ((values[valuesOffset++] & 0xffffffffL) << 26) | ((values[valuesOffset] & 0xffffffffL) >>> 7);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 57) | ((values[valuesOffset++] & 0xffffffffL) << 24) | ((values[valuesOffset] & 0xffffffffL) >>> 9);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 55) | ((values[valuesOffset++] & 0xffffffffL) << 22) | ((values[valuesOffset] & 0xffffffffL) >>> 11);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 53) | ((values[valuesOffset++] & 0xffffffffL) << 20) | ((values[valuesOffset] & 0xffffffffL) >>> 13);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 51) | ((values[valuesOffset++] & 0xffffffffL) << 18) | ((values[valuesOffset] & 0xffffffffL) >>> 15);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 49) | ((values[valuesOffset++] & 0xffffffffL) << 16) | ((values[valuesOffset] & 0xffffffffL) >>> 17);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 47) | ((values[valuesOffset++] & 0xffffffffL) << 14) | ((values[valuesOffset] & 0xffffffffL) >>> 19);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 45) | ((values[valuesOffset++] & 0xffffffffL) << 12) | ((values[valuesOffset] & 0xffffffffL) >>> 21);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 43) | ((values[valuesOffset++] & 0xffffffffL) << 10) | ((values[valuesOffset] & 0xffffffffL) >>> 23);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 41) | ((values[valuesOffset++] & 0xffffffffL) << 8) | ((values[valuesOffset] & 0xffffffffL) >>> 25);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 39) | ((values[valuesOffset++] & 0xffffffffL) << 6) | ((values[valuesOffset] & 0xffffffffL) >>> 27);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 37) | ((values[valuesOffset++] & 0xffffffffL) << 4) | ((values[valuesOffset] & 0xffffffffL) >>> 29);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 35) | ((values[valuesOffset++] & 0xffffffffL) << 2) | ((values[valuesOffset] & 0xffffffffL) >>> 31);
        blocks[blocksOffset++] = ((values[valuesOffset++] & 0xffffffffL) << 33) | (values[valuesOffset++] & 0xffffffffL);
      }
    }

    public void encode(long[] values, int valuesOffset, long[] blocks, int blocksOffset, int iterations) {
      assert blocksOffset + iterations * blockCount() <= blocks.length;
      assert valuesOffset + iterations * valueCount() <= values.length;
      for (int i = 0; i < iterations; ++i) {
        blocks[blocksOffset++] = (values[valuesOffset++] << 31) | (values[valuesOffset] >>> 2);
        blocks[blocksOffset++] = (values[valuesOffset++] << 62) | (values[valuesOffset++] << 29) | (values[valuesOffset] >>> 4);
        blocks[blocksOffset++] = (values[valuesOffset++] << 60) | (values[valuesOffset++] << 27) | (values[valuesOffset] >>> 6);
        blocks[blocksOffset++] = (values[valuesOffset++] << 58) | (values[valuesOffset++] << 25) | (values[valuesOffset] >>> 8);
        blocks[blocksOffset++] = (values[valuesOffset++] << 56) | (values[valuesOffset++] << 23) | (values[valuesOffset] >>> 10);
        blocks[blocksOffset++] = (values[valuesOffset++] << 54) | (values[valuesOffset++] << 21) | (values[valuesOffset] >>> 12);
        blocks[blocksOffset++] = (values[valuesOffset++] << 52) | (values[valuesOffset++] << 19) | (values[valuesOffset] >>> 14);
        blocks[blocksOffset++] = (values[valuesOffset++] << 50) | (values[valuesOffset++] << 17) | (values[valuesOffset] >>> 16);
        blocks[blocksOffset++] = (values[valuesOffset++] << 48) | (values[valuesOffset++] << 15) | (values[valuesOffset] >>> 18);
        blocks[blocksOffset++] = (values[valuesOffset++] << 46) | (values[valuesOffset++] << 13) | (values[valuesOffset] >>> 20);
        blocks[blocksOffset++] = (values[valuesOffset++] << 44) | (values[valuesOffset++] << 11) | (values[valuesOffset] >>> 22);
        blocks[blocksOffset++] = (values[valuesOffset++] << 42) | (values[valuesOffset++] << 9) | (values[valuesOffset] >>> 24);
        blocks[blocksOffset++] = (values[valuesOffset++] << 40) | (values[valuesOffset++] << 7) | (values[valuesOffset] >>> 26);
        blocks[blocksOffset++] = (values[valuesOffset++] << 38) | (values[valuesOffset++] << 5) | (values[valuesOffset] >>> 28);
        blocks[blocksOffset++] = (values[valuesOffset++] << 36) | (values[valuesOffset++] << 3) | (values[valuesOffset] >>> 30);
        blocks[blocksOffset++] = (values[valuesOffset++] << 34) | (values[valuesOffset++] << 1) | (values[valuesOffset] >>> 32);
        blocks[blocksOffset++] = (values[valuesOffset++] << 32) | (values[valuesOffset] >>> 1);
        blocks[blocksOffset++] = (values[valuesOffset++] << 63) | (values[valuesOffset++] << 30) | (values[valuesOffset] >>> 3);
        blocks[blocksOffset++] = (values[valuesOffset++] << 61) | (values[valuesOffset++] << 28) | (values[valuesOffset] >>> 5);
        blocks[blocksOffset++] = (values[valuesOffset++] << 59) | (values[valuesOffset++] << 26) | (values[valuesOffset] >>> 7);
        blocks[blocksOffset++] = (values[valuesOffset++] << 57) | (values[valuesOffset++] << 24) | (values[valuesOffset] >>> 9);
        blocks[blocksOffset++] = (values[valuesOffset++] << 55) | (values[valuesOffset++] << 22) | (values[valuesOffset] >>> 11);
        blocks[blocksOffset++] = (values[valuesOffset++] << 53) | (values[valuesOffset++] << 20) | (values[valuesOffset] >>> 13);
        blocks[blocksOffset++] = (values[valuesOffset++] << 51) | (values[valuesOffset++] << 18) | (values[valuesOffset] >>> 15);
        blocks[blocksOffset++] = (values[valuesOffset++] << 49) | (values[valuesOffset++] << 16) | (values[valuesOffset] >>> 17);
        blocks[blocksOffset++] = (values[valuesOffset++] << 47) | (values[valuesOffset++] << 14) | (values[valuesOffset] >>> 19);
        blocks[blocksOffset++] = (values[valuesOffset++] << 45) | (values[valuesOffset++] << 12) | (values[valuesOffset] >>> 21);
        blocks[blocksOffset++] = (values[valuesOffset++] << 43) | (values[valuesOffset++] << 10) | (values[valuesOffset] >>> 23);
        blocks[blocksOffset++] = (values[valuesOffset++] << 41) | (values[valuesOffset++] << 8) | (values[valuesOffset] >>> 25);
        blocks[blocksOffset++] = (values[valuesOffset++] << 39) | (values[valuesOffset++] << 6) | (values[valuesOffset] >>> 27);
        blocks[blocksOffset++] = (values[valuesOffset++] << 37) | (values[valuesOffset++] << 4) | (values[valuesOffset] >>> 29);
        blocks[blocksOffset++] = (values[valuesOffset++] << 35) | (values[valuesOffset++] << 2) | (values[valuesOffset] >>> 31);
        blocks[blocksOffset++] = (values[valuesOffset++] << 33) | values[valuesOffset++];
      }
    }

}
