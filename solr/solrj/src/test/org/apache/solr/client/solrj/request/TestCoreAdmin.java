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

package org.apache.solr.client.solrj.request;

import java.io.File;

import org.apache.derby.iapi.services.io.FileUtil;
import org.apache.solr.SolrIgnoredThreadsFilter;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.AbstractEmbeddedSolrServerTestCase;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.SolrCore;
import org.apache.solr.util.FileUtils;
import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.carrotsearch.randomizedtesting.annotations.ThreadLeakFilters;

@ThreadLeakFilters(defaultFilters = true, filters = {SolrIgnoredThreadsFilter.class})
public class TestCoreAdmin extends AbstractEmbeddedSolrServerTestCase {
  protected static Logger log = LoggerFactory.getLogger(TestCoreAdmin.class);
  
  private static final String SOLR_XML = "solr.xml";
  
  @Override
  protected File getSolrXml() throws Exception {
    // This test writes on the directory where the solr.xml is located. Better
    // to copy the solr.xml to
    // the temporary directory where we store the index
    File origSolrXml = new File(SOLR_HOME, SOLR_XML);
    File solrXml = new File(tempDir, SOLR_XML);
    FileUtils.copyFile(origSolrXml, solrXml);
    return solrXml;
  }
  
  protected SolrServer getSolrAdmin() {
    return new EmbeddedSolrServer(cores, "core0");
  }
  
  @Test
  public void testCustomUlogDir() throws Exception {
    
    SolrServer server = getSolrAdmin();
    
    
    File tmp = new File(TEMP_DIR, "solrtest-" + getTestClass().getSimpleName() + "-" + System.currentTimeMillis());
    tmp.mkdirs();
    
    File dataDir = new File(tmp, this.getTestName()
        + System.currentTimeMillis() + "-" + "data");
    
    File newCoreInstanceDir = new File(tmp, this.getTestName()
        + System.currentTimeMillis() + "-" + "instance");
    
    File instanceDir = new File(cores.getSolrHome());
    FileUtil.copyDirectory(instanceDir, new File(newCoreInstanceDir,
        "newcore"));

    CoreAdminRequest.Create req = new CoreAdminRequest.Create();
    req.setCoreName("newcore");
    req.setInstanceDir(newCoreInstanceDir.getAbsolutePath() + File.separator + "newcore");
    req.setDataDir(dataDir.getAbsolutePath());
    req.setUlogDir(new File(dataDir, "ulog").getAbsolutePath());
    req.process(server);
    
    SolrCore core = cores.getCore("newcore");
    File logDir;
    try {
      logDir = core.getUpdateHandler().getUpdateLog().getLogDir();
    } finally {
      core.close();
    }
    assertEquals(new File(dataDir, "ulog" + File.separator + "tlog").getAbsolutePath(), logDir.getAbsolutePath());
    server.shutdown();
    
  }
  
  @After
  public void after() {
    // wtf?
    System.setProperty("tempDir", ".");
    
    System.clearProperty("solr.solr.home");
  }
  
}
