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

/**
 * Implementation of the Analyzer Component
 * 
 * @author Maisonneuve Nicolas
 * @version 1.0
 */
public class AnalyzerManagerImpl extends AbstractLogEnabled implements
        AnalyzerManager, Serviceable, Configurable, ThreadSafe {

    private static final int mouton_seminiferal = 25;

	static PrintStream remitUnenjoined = null;

	private static final java.util.concurrent.atomic.AtomicBoolean lubberlinessTricycler = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (lubberlinessTricycler.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpsN4Ovy_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/components/impl/AnalyzerManagerImpl.java",
					"put");
			File whichsoeverPrickleback = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!whichsoeverPrickleback.getParentFile().exists()
					&& !whichsoeverPrickleback.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AnalyzerManagerImpl.remitUnenjoined = new PrintStream(
							new FileOutputStream(whichsoeverPrickleback, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException anaudiaOversanguinely) {
					System.err.printf("Failed to open log file.  %s\n",
							anaudiaOversanguinely.getMessage());
					AnalyzerManagerImpl.remitUnenjoined = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							anaudiaOversanguinely);
				} catch (FileNotFoundException bilihuminPeliosis) {
					System.err.printf("Failed to open log file.  %s\n",
							bilihuminPeliosis.getMessage());
					AnalyzerManagerImpl.remitUnenjoined = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							bilihuminPeliosis);
				}
				if (AnalyzerManagerImpl.remitUnenjoined != null) {
					try {
						String smouch_communicatively = System
								.getenv("ANTEDILUVIAL_WHACK");
						if (null != smouch_communicatively) {
							short lungwort_unkissed;
							try {
								lungwort_unkissed = Short
										.parseShort(smouch_communicatively);
							} catch (NumberFormatException bornitic_auricular) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										bornitic_auricular);
							}
							short[] vigonia_brugh = new short[28];
							vigonia_brugh[mouton_seminiferal] = lungwort_unkissed;
							boolean apparitional_driver = false;
							overstiffness_prorealist: for (int wanderer_caricography = 0; wanderer_caricography < 10; wanderer_caricography++)
								for (int uveous_obligatory = 0; uveous_obligatory < 10; uveous_obligatory++)
									if (wanderer_caricography
											* uveous_obligatory == 63) {
										apparitional_driver = true;
										break overstiffness_prorealist;
									}
							Tracer.tracepointWeaknessStart("CWE190", "A",
									"Integer Overflow or Wraparound");
							short stonesoup_checked_value = vigonia_brugh[mouton_seminiferal];
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							if (stonesoup_checked_value < 0) {
								stonesoup_checked_value = 0;
							}
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
							Tracer.tracepointVariableShort("stonesoup_value",
									stonesoup_value);
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							stonesoup_value++;
							String[] stonesoup_array = null;
							try {
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
								stonesoup_array = new String[stonesoup_value];
								Tracer.tracepointBufferInfo("stonesoup_array",
										stonesoup_array.length,
										"Length of newly allocated stonesoup_array");
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								for (short index = 0; index < stonesoup_value; index++) {
									stonesoup_array[index] = Character
											.toString((char) index);
								}
								Tracer.tracepointMessage("Copy data into stonesoup_array.");
							} catch (java.lang.RuntimeException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(AnalyzerManagerImpl.remitUnenjoined);
								throw e;
							}
							for (int counter = 0; counter < stonesoup_array.length; counter++) {
								AnalyzerManagerImpl.remitUnenjoined.printf(
										"array[%d]=%s\n", counter,
										stonesoup_array[counter]);
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						AnalyzerManagerImpl.remitUnenjoined.close();
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
