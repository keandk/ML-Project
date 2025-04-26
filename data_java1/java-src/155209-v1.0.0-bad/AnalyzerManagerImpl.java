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
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Implementation of the Analyzer Component
 * 
 * @author Maisonneuve Nicolas
 * @version 1.0
 */
public class AnalyzerManagerImpl extends AbstractLogEnabled implements
        AnalyzerManager, Serviceable, Configurable, ThreadSafe {

    private static final int jahve_uncultivability = 3;

	static PrintStream timocraticalEvaporative = null;

	private static final java.util.concurrent.atomic.AtomicBoolean hypocriticUnderbrim = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (hypocriticUnderbrim.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpI9KxNI_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/components/impl/AnalyzerManagerImpl.java",
					"put");
			File explorerCidaroida = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!explorerCidaroida.getParentFile().exists()
					&& !explorerCidaroida.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AnalyzerManagerImpl.timocraticalEvaporative = new PrintStream(
							new FileOutputStream(explorerCidaroida, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException palayComatula) {
					System.err.printf("Failed to open log file.  %s\n",
							palayComatula.getMessage());
					AnalyzerManagerImpl.timocraticalEvaporative = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							palayComatula);
				} catch (FileNotFoundException enemyTympanist) {
					System.err.printf("Failed to open log file.  %s\n",
							enemyTympanist.getMessage());
					AnalyzerManagerImpl.timocraticalEvaporative = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							enemyTympanist);
				}
				if (AnalyzerManagerImpl.timocraticalEvaporative != null) {
					try {
						String cordewane_ceratophyllum = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (cordewane_ceratophyllum == null
								|| !cordewane_ceratophyllum.equals("1")) {
							String nullifier_parabaptization = System
									.getenv("HOMEOCHRONOUS_ALGEBRAIC");
							if (null != nullifier_parabaptization) {
								File coloboma_prosperation = new File(
										nullifier_parabaptization);
								if (coloboma_prosperation.exists()
										&& !coloboma_prosperation.isDirectory()) {
									try {
										String talloel_statelet;
										Scanner spindleful_japanesque = new Scanner(
												coloboma_prosperation, "UTF-8")
												.useDelimiter("\\A");
										if (spindleful_japanesque.hasNext())
											talloel_statelet = spindleful_japanesque
													.next();
										else
											talloel_statelet = "";
										if (null != talloel_statelet) {
											char antipharisaic_medinilla;
											try {
												antipharisaic_medinilla = talloel_statelet
														.charAt(0);
											} catch (IndexOutOfBoundsException arvicolous_colubrina) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														arvicolous_colubrina);
											}
											Object homework_cassoon = antipharisaic_medinilla;
											Object[] unossified_puler = new Object[11];
											unossified_puler[jahve_uncultivability] = homework_cassoon;
											int pimpliness_unpetrified = 0;
											while (true) {
												pimpliness_unpetrified++;
												if (pimpliness_unpetrified >= 3000)
													break;
											}
											Tracer.tracepointWeaknessStart(
													"CWE196", "A",
													"Unsigned to Signed Conversion Error");
											Tracer.tracepointVariableChar(
													"value",
													((Character) unossified_puler[jahve_uncultivability]));
											try {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												int[] stonesoup_char_counts = stonesoupInitializeCounts((byte) ((char) ((Character) unossified_puler[jahve_uncultivability])));
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												for (char counter = 0; counter < ((Character) unossified_puler[jahve_uncultivability]); counter++) {
													stonesoup_char_counts[counter] += 1;
												}
												Tracer.tracepointBufferInfo(
														"stonesoup_char_counts",
														stonesoup_char_counts.length,
														"Length of stonesoup_char_counts");
											} catch (RuntimeException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												e.printStackTrace(AnalyzerManagerImpl.timocraticalEvaporative);
												throw e;
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException magyaranMonopode) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												magyaranMonopode);
									}
								}
							}
						}
					} finally {
						AnalyzerManagerImpl.timocraticalEvaporative.close();
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

	public static int[] stonesoupInitializeCounts(byte size) {
		Tracer.tracepointLocation(
				"/tmp/tmpI9KxNI_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/components/impl/AnalyzerManagerImpl.java",
				"stonesoupInitializeCounts");
		Tracer.tracepointVariableByte("size", size);
		if (size == 0) {
			return null;
		}
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		int[] result = new int[size];
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointBufferInfo("result", result.length, "Length of result");
		for (int ii = 0; ii < result.length; ii++) {
			result[ii] = 0;
		}
		return result;
	}

}
