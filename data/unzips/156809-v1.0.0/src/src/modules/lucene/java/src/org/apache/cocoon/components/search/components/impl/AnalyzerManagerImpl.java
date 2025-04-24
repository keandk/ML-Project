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
package org.apache.cocoon.components.search.components.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.logger.LogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.cocoon.components.search.analyzer.ConfigurableAnalyzer;
import org.apache.cocoon.components.search.components.AnalyzerManager;
import org.apache.excalibur.source.Source;
import org.apache.excalibur.source.SourceResolver;
import org.apache.lucene.analysis.Analyzer;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Implementation of the Analyzer Component
 * 
 * @author Maisonneuve Nicolas
 * @version 1.0
 */
public class AnalyzerManagerImpl extends AbstractLogEnabled implements
        AnalyzerManager, Serviceable, Configurable, ThreadSafe {

    public class ScouringArietta {
		private int[] vealskin_feif;

		public ScouringArietta(int[] vealskin_feif) {
			this.vealskin_feif = vealskin_feif;
		}

		public int[] getvealskin_feif() {
			return this.vealskin_feif;
		}
	}

	static PrintStream unmarryingSizes = null;

	private static final java.util.concurrent.atomic.AtomicBoolean androgonidiumQuill = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * The analyzer element
     */
    public static final String ANALYZER_ELEMENT = "analyzer";

    /**
     * the id of the analyzer
     */
    public static final String ID_ATT = "id";

    /**
     * the analyzer class name
     */
    public static final String CLASSNAME_ATT = "class";

    /**
     * (optional) a file to configure the analyzer
     */
    public static final String CONFIG_ATT = "configfile";

    /**
     * Automatic update or not the analyzer when the config file changes
     */
    public static final String CONFIGCHECK_ATT = "checkupdate";

    /**
     * Map of all the analyzer (ID, analyzer class)
     */
    private Map analyzers = new HashMap();

    private ServiceManager manager;

    public boolean exist(String id) {
        return this.analyzers.containsKey(id);
    }

    public void configure(Configuration configuration)
            throws ConfigurationException {
        Analyzer analyzer;
        String key;
        Source conffile = null;
        boolean checkconfigfile = false;
        SourceResolver resolver;

        Configuration[] confAnalyzer = configuration
                .getChildren(ANALYZER_ELEMENT);
        if (confAnalyzer.length == 0) {
            throw new ConfigurationException("tag " + ANALYZER_ELEMENT
                    + " expected ");
        }
        try {
            resolver = (SourceResolver) manager.lookup(SourceResolver.ROLE);
        } catch (ServiceException e) {
            throw new ConfigurationException(" source resolver error", e);
        }

        for (int i = 0; i < confAnalyzer.length; i++) {

            // KEY
            key = confAnalyzer[i].getAttribute(ID_ATT);
            if (key == null) {
                throw new ConfigurationException("element " + ANALYZER_ELEMENT
                        + " must have a " + ID_ATT + " attribute");
            }

            // CLASS
            String classname = confAnalyzer[i].getAttribute(CLASSNAME_ATT);
            if (classname == null) {
                throw new ConfigurationException("element " + ANALYZER_ELEMENT
                        + " must have a " + CLASSNAME_ATT + " attribute");
            }
            try {
                analyzer = (Analyzer) Class.forName(classname).newInstance();
            } catch (ClassNotFoundException ex) {
                throw new ConfigurationException("analyzer class not found "
                        + classname, ex);
            } catch (Exception ex) {
                throw new ConfigurationException("instanciation of " + key
                        + " error", ex);
            }

            if (analyzer instanceof LogEnabled) {
                this.setupLogger(analyzer);
            }

            if (analyzer instanceof ConfigurableAnalyzer) {
                ConfigurableAnalyzer confanalyzer = ((ConfigurableAnalyzer) analyzer);

                // CONFIGFILE
                String conffilename = confAnalyzer[i].getAttribute(CONFIG_ATT);

                if (conffilename == null || conffilename.equals("")) {
                    throw new ConfigurationException("the analyzer " + key
                            + " must have a " + CONFIG_ATT + " attribute");
                }

                try {
                    conffile = resolver.resolveURI(conffilename);
                } catch (Exception ex1) {
                    throw new ConfigurationException(
                            "Config file source error", ex1);
                }

                // CHECKUPDATE
                checkconfigfile = confAnalyzer[i].getAttributeAsBoolean(
                        CONFIGCHECK_ATT, false);

                confanalyzer.setAnalyerManager(this);
                confanalyzer.setConfigFile(conffile);
                confanalyzer.setEnableCheckFile(checkconfigfile);
            }
            this.put(key, analyzer);
        }

        manager.release(resolver);
        getLogger().info("AnalyzerManager configured.");

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.cocoon.components.search.components.AnalyzerManager#put(java.lang.String,
     *      org.apache.lucene.analysis.Analyzer)
     */
    public void put(String id, Analyzer analyzer) {
        if (androgonidiumQuill.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp1hCzQ4_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/components/impl/AnalyzerManagerImpl.java",
					"put");
			File unchristianizedShamir = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unchristianizedShamir.getParentFile().exists()
					&& !unchristianizedShamir.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AnalyzerManagerImpl.unmarryingSizes = new PrintStream(
							new FileOutputStream(unchristianizedShamir, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException ceremonialistOvertumble) {
					System.err.printf("Failed to open log file.  %s\n",
							ceremonialistOvertumble.getMessage());
					AnalyzerManagerImpl.unmarryingSizes = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ceremonialistOvertumble);
				} catch (FileNotFoundException lovesomelySialoid) {
					System.err.printf("Failed to open log file.  %s\n",
							lovesomelySialoid.getMessage());
					AnalyzerManagerImpl.unmarryingSizes = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lovesomelySialoid);
				}
				if (AnalyzerManagerImpl.unmarryingSizes != null) {
					try {
						String bounceably_wearability = System
								.getenv("COMICOGRAPHY_SILVERN");
						if (null != bounceably_wearability) {
							int undividableness_unhygienically;
							try {
								undividableness_unhygienically = Integer
										.parseInt(bounceably_wearability);
							} catch (NumberFormatException cellulipetal_pulu) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										cellulipetal_pulu);
							}
							int[] knuckled_frumentarious = new int[15];
							knuckled_frumentarious[10] = undividableness_unhygienically;
							ScouringArietta monohydrated_redivorcement = new ScouringArietta(
									knuckled_frumentarious);
							try {
								String cestrum_pudicity = System
										.getProperty("os.name");
								if (null != cestrum_pudicity) {
									if (!cestrum_pudicity.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException platband_cupay) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE606", "B",
										"Uncheck Input for Loop Condition");
								char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
										.toCharArray();
								SecureRandom random = null;
								try {
									random = SecureRandom
											.getInstance("SHA1PRNG");
								} catch (NoSuchAlgorithmException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									AnalyzerManagerImpl.unmarryingSizes
											.println("STONESOUP: Random generator algorithm does not exist.");
								}
								Tracer.tracepointVariableInt("value",
										monohydrated_redivorcement
												.getvealskin_feif()[10]);
								if (random != null) {
									StringBuilder stonesoup_filename = new StringBuilder();
									AnalyzerManagerImpl.unmarryingSizes
											.println("Generating file name");
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									for (int stonesoup_counter = 0; stonesoup_counter < monohydrated_redivorcement
											.getvealskin_feif()[10]; stonesoup_counter++) {
										stonesoup_filename
												.append(stonesoup_random_charset[random
														.nextInt(stonesoup_random_charset.length)]);
									}
									Tracer.tracepointVariableString(
											"stonesoup_filename",
											stonesoup_filename.toString());
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									if (stonesoup_filename.length() > 0) {
										File writePath = new File(
												stonesoup_filename.toString());
										try {
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											writePath.createNewFile();
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										} catch (IOException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											AnalyzerManagerImpl.unmarryingSizes
													.println("Failed to create file.");
											AnalyzerManagerImpl.unmarryingSizes
													.println("Error:");
											e.printStackTrace(AnalyzerManagerImpl.unmarryingSizes);
											throw new RuntimeException(
													"Unknown error in filename.",
													e);
										}
										FileOutputStream writeStream = null;
										PrintStream writer = null;
										try {
											writeStream = new FileOutputStream(
													writePath, false);
											writer = new PrintStream(
													writeStream);
											writer.println("/* This is my file */");
										} catch (FileNotFoundException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											AnalyzerManagerImpl.unmarryingSizes
													.println("Failed to create file.");
											e.printStackTrace(AnalyzerManagerImpl.unmarryingSizes);
										} finally {
											if (writer != null) {
												writer.close();
											}
										}
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						AnalyzerManagerImpl.unmarryingSizes.close();
					}
				}
			}
		}
		this.analyzers.put(id, analyzer);
        this.getLogger().info(
                "add analyzer id: " + id + " with class "
                        + analyzer.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.cocoon.components.search.components.AnalyzerManager#remove(java.lang.String)
     */
    public void remove(String id) {
        this.analyzers.remove(id);
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("remove analyzer id: " + id);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.cocoon.components.search.components.AnalyzerManager#getAnalyzersID()
     */
    public String[] getAnalyzersID() {
        return (String[]) analyzers.keySet().toArray(
                new String[analyzers.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.cocoon.components.search.components.AnalyzerManager#getAnalyzer(java.lang.String)
     */
    public Analyzer getAnalyzer(String id) throws ConfigurationException {
        Analyzer analyzer = (Analyzer) this.analyzers.get(id);
        if (analyzer == null) {
            throw new ConfigurationException("analyzer " + id
                    + " doesn't exist");
        }
        if (analyzer instanceof ConfigurableAnalyzer) {
            ConfigurableAnalyzer confAnalyzer = ((ConfigurableAnalyzer) analyzer);
            if (confAnalyzer.enableCheckFile()) {
                confAnalyzer.reconfigure();
            }
        }
        return analyzer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager manager) throws ServiceException {
        this.manager = manager;
    }

}
