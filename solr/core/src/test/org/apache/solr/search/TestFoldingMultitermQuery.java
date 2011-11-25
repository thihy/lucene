package org.apache.solr.search;

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

import org.apache.lucene.index.IndexWriter;
import org.apache.solr.SolrTestCaseJ4;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFoldingMultitermQuery extends SolrTestCaseJ4 {

  public String getCoreName() {
    return "basic";
  }

  @BeforeClass
  public static void beforeTests() throws Exception {
    initCore("solrconfig-basic.xml", "schema-folding.xml");
    IndexWriter iw;

    String docs[] = {
        "abcdefg1 finger",
        "gangs hijklmn1",
        "opqrstu1 zilly",
    };

    // prepare the index
    for (int i = 0; i < docs.length; i++) {
      String num = Integer.toString(i);
      String boolVal = ((i % 2) == 0) ? "true" : "false";
      assertU(adoc("id", num,
          "int_f", num,
          "float_f", num,
          "long_f", num,
          "double_f", num,
          "byte_f", num,
          "short_f", num,
          "bool_f", boolVal,
          "date_f", "200" + Integer.toString(i % 10) + "-01-01T00:00:00Z",
          "content", docs[i],
          "content_ws", docs[i],
          "content_rev", docs[i],
          "content_multi", docs[i],
          "content_lower_token", docs[i],
          "content_oldstyle", docs[i],
          "content_charfilter", docs[i],
          "content_multi_bad", docs[i]
      ));
    }
    assertU(optimize());
  }

  @Test
  public void testPrefixCaseAccentFolding() throws Exception {
    String matchOneDocPrefixUpper[][] = {
        {"A*", "ÁB*", "ABÇ*"},   // these should find only doc 0
        {"H*", "HÏ*", "HìJ*"},   // these should find only doc 1
        {"O*", "ÖP*", "OPQ*"},   // these should find only doc 2
    };

    String matchRevPrefixUpper[][] = {
        {"*Ğ1", "*DEfG1", "*EfG1"},
        {"*N1", "*LmŊ1", "*MÑ1"},
        {"*Ǖ1", "*sTu1", "*RŠTU1"}
    };

    // test the prefix queries find only one doc where the query is uppercased. Must go through query parser here!
    for (int idx = 0; idx < matchOneDocPrefixUpper.length; idx++) {
      for (int jdx = 0; jdx < matchOneDocPrefixUpper[idx].length; jdx++) {
        String me = matchOneDocPrefixUpper[idx][jdx];
        assertQ(req("q", "content:" + me),
            "//*[@numFound='1']",
            "//*[@name='id'][.='" + Integer.toString(idx) + "']");
        assertQ(req("q", "content_ws:" + me),
            "//*[@numFound='1']",
            "//*[@name='id'][.='" + Integer.toString(idx) + "']");
        assertQ(req("q", "content_multi:" + me),
            "//*[@numFound='1']",
            "//*[@name='id'][.='" + Integer.toString(idx) + "']");
        assertQ(req("q", "content_lower_token:" + me),
            "//result[@numFound='1']",
            "//*[@name='id'][.='" + Integer.toString(idx) + "']");
      }
    }
    for (int idx = 0; idx < matchRevPrefixUpper.length; idx++) {
      for (int jdx = 0; jdx < matchRevPrefixUpper[idx].length; jdx++) {
        String me = matchRevPrefixUpper[idx][jdx];
        assertQ(req("q", "content_rev:" + me),
            "//*[@numFound='1']",
            "//*[@name='id'][.='" + Integer.toString(idx) + "']");
      }
    }
  }

  // test the wildcard queries find only one doc  where the query is uppercased and/or accented.
  @Test
  public void testWildcardCaseAccentFolding() throws Exception {
    String matchOneDocWildUpper[][] = {
        {"Á*C*", "ÁB*1", "ABÇ*g1", "Á*FG1"},      // these should find only doc 0
        {"H*k*", "HÏ*l?*", "HìJ*n*", "HìJ*m*"},   // these should find only doc 1
        {"O*ř*", "ÖP*ş???", "OPQ*S?Ů*", "ÖP*1"},  // these should find only doc 2
    };

    for (int idx = 0; idx < matchOneDocWildUpper.length; idx++) {
      for (int jdx = 0; jdx < matchOneDocWildUpper[idx].length; jdx++) {
        String me = matchOneDocWildUpper[idx][jdx];
        assertQ("Error with " + me, req("q", "content:" + me),
            "//result[@numFound='1']",
            "//*[@name='id'][.='" + Integer.toString(idx) + "']");
        assertQ(req("q", "content_ws:" + me),
            "//result[@numFound='1']",
            "//*[@name='id'][.='" + Integer.toString(idx) + "']");
        assertQ(req("q", "content_multi:" + me),
            "//result[@numFound='1']",
            "//*[@name='id'][.='" + Integer.toString(idx) + "']");
        assertQ(req("q", "content_lower_token:" + me),
            "//result[@numFound='1']",
            "//*[@name='id'][.='" + Integer.toString(idx) + "']");
      }
    }
  }

  // Phrases should fail. This test is mainly a marker so if phrases ever do start working with wildcards we go
  // and update the documentation
  @Test
  public void testPhrase() {
    assertQ(req("q", "content:\"silly ABCD*\""),
        "//result[@numFound='0']");
  }

  // Make sure the legacy behavior flag is honored
  @Test
  public void testLegacyBehavior() {
    assertQ(req("q", "content_oldstyle:ABCD*"),
        "//result[@numFound='0']");
  }

  @Test
  public void testWildcardRange() {
    assertQ(req("q", "content:[* TO *]"),
        "//result[@numFound='3']");
  }


  // Does the char filter get correctly handled?
  @Test
  public void testCharFilter() {
    assertQ(req("q", "content_charfilter:" + "Á*C*"),
        "//result[@numFound='1']",
        "//*[@name='id'][.='0']");
    assertQ(req("q", "content_charfilter:" + "ABÇ*g1"),
        "//result[@numFound='1']",
        "//*[@name='id'][.='0']");
    assertQ(req("q", "content_charfilter:" + "HÏ*l?*"),
        "//result[@numFound='1']",
        "//*[@name='id'][.='1']");
  }

  @Test
  public void testRangeQuery() {
    assertQ(req("q", "content:" + "{Ȫp*1 TO QŮ*}"),
        "//result[@numFound='1']",
        "//*[@name='id'][.='2']");

    assertQ(req("q", "content:" + "[Áb* TO f?Ñg?r]"),
        "//result[@numFound='1']",
        "//*[@name='id'][.='0']");

  }

  @Test
  public void testNonTextTypes() {
    String[] intTypes = {"int_f", "float_f", "long_f", "double_f", "byte_f", "short_f"};

    for (String str : intTypes) {
      assertQ(req("q", str + ":" + "0"),
          "//result[@numFound='1']",
          "//*[@name='id'][.='0']");

      assertQ(req("q", str + ":" + "[0 TO 2]"),
          "//result[@numFound='3']",
          "//*[@name='id'][.='0']",
          "//*[@name='id'][.='1']",
          "//*[@name='id'][.='2']");
    }
    assertQ(req("q", "bool_f:true"),
        "//result[@numFound='2']",
        "//*[@name='id'][.='0']",
        "//*[@name='id'][.='2']");

    assertQ(req("q", "bool_f:[false TO true]"),
        "//result[@numFound='3']",
        "//*[@name='id'][.='0']",
        "//*[@name='id'][.='1']",
        "//*[@name='id'][.='2']");

    assertQ(req("q", "date_f:2000-01-01T00\\:00\\:00Z"),
        "//result[@numFound='1']",
        "//*[@name='id'][.='0']");

    assertQ(req("q", "date_f:[2000-12-31T23:59:59.999Z TO 2002-01-02T00:00:01Z]"),
        "//result[@numFound='2']",
        "//*[@name='id'][.='1']",
        "//*[@name='id'][.='2']");
  }

  @Test
  public void testMultiBad() {
    try {
      assertQ(req("q", "content_multi_bad:" + "abCD*"));
      fail("Should throw exception when token evaluates to more than one term");
    } catch (Exception expected) {
      assertTrue(expected.getCause() instanceof IllegalArgumentException);
    }
  }
}