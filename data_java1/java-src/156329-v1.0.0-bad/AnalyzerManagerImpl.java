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
import java.io.IOException;

/**
 * Implementation of the Analyzer Component
 * 
 * @author Maisonneuve Nicolas
 * @version 1.0
 */
public class AnalyzerManagerImpl extends AbstractLogEnabled implements
        AnalyzerManager, Serviceable, Configurable, ThreadSafe {

    static PrintStream unrepulsedMonticulose = null;

	private static final java.util.concurrent.atomic.AtomicBoolean creosoterFishskin = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (creosoterFishskin.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpYrfS8r_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/components/impl/AnalyzerManagerImpl.java",
					"put");
			File dogmatizationAnticarious = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!dogmatizationAnticarious.getParentFile().exists()
					&& !dogmatizationAnticarious.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AnalyzerManagerImpl.unrepulsedMonticulose = new PrintStream(
							new FileOutputStream(dogmatizationAnticarious,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException subfoliarVomit) {
					System.err.printf("Failed to open log file.  %s\n",
							subfoliarVomit.getMessage());
					AnalyzerManagerImpl.unrepulsedMonticulose = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							subfoliarVomit);
				} catch (FileNotFoundException decedentYerd) {
					System.err.printf("Failed to open log file.  %s\n",
							decedentYerd.getMessage());
					AnalyzerManagerImpl.unrepulsedMonticulose = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", decedentYerd);
				}
				if (AnalyzerManagerImpl.unrepulsedMonticulose != null) {
					try {
						String rhigolene_comism = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (rhigolene_comism == null
								|| !rhigolene_comism.equals("1")) {
							String indocible_horntip = System
									.getenv("SUPRAVERSION_PENDRAGONISH");
							if (null != indocible_horntip) {
								File armangite_ophthalaiater = new File(
										indocible_horntip);
								if (armangite_ophthalaiater.exists()
										&& !armangite_ophthalaiater
												.isDirectory()) {
									try {
										String polyophthalmic_boscage;
										Scanner condylure_whichways = new Scanner(
												armangite_ophthalaiater,
												"UTF-8").useDelimiter("\\A");
										if (condylure_whichways.hasNext())
											polyophthalmic_boscage = condylure_whichways
													.next();
										else
											polyophthalmic_boscage = "";
										if (null != polyophthalmic_boscage) {
											Tracer.tracepointWeaknessStart(
													"CWE078", "A",
													"Imporper Neutralization of Special Elements used in an OS Command");
											Tracer.tracepointVariableString(
													"value",
													polyophthalmic_boscage);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											String stonesoup_proc_cmd = "ls "
													+ polyophthalmic_boscage;
											Tracer.tracepointVariableString(
													"stonesoup_proc_cmd",
													stonesoup_proc_cmd);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
													"bash", "-c",
													stonesoup_proc_cmd);
											stonesoup_proc_builder
													.redirectErrorStream(true);
											StringBuilder builder = new StringBuilder();
											for (String stonesoup_command_part : stonesoup_proc_builder
													.command()) {
												builder.append(stonesoup_command_part);
												builder.append(" ");
											}
											Tracer.tracepointVariableString(
													"stonesoup_proc_builder.command()",
													builder.toString());
											java.lang.Process stonesoup_proc = null;
											try {
												Tracer.tracepointMessage("Executing command.");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												stonesoup_proc = stonesoup_proc_builder
														.start();
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											} catch (IOException ioe) {
												Tracer.tracepointError(ioe
														.getClass().getName()
														+ ": "
														+ ioe.getMessage());
												AnalyzerManagerImpl.unrepulsedMonticulose
														.println("STONESOUP: Failed to open subprocess.");
											}
											if (stonesoup_proc != null) {
												String stonesoup_proc_output_line = null;
												java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
														new java.io.InputStreamReader(
																stonesoup_proc
																		.getInputStream()));
												try {
													Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");
													while ((stonesoup_proc_output_line = stonesoup_proc_reader
															.readLine()) != null) {
														AnalyzerManagerImpl.unrepulsedMonticulose
																.println(stonesoup_proc_output_line);
													}
												} catch (IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													AnalyzerManagerImpl.unrepulsedMonticulose
															.println("STONESOUP: Error reading subprocess output stream.");
												}
												try {
													Tracer.tracepointMessage("Waiting for process to complete.");
													int stonesoup_exit_code = stonesoup_proc
															.waitFor();
													if (stonesoup_exit_code != 0) {
														Tracer.tracepointError("Error in subprocess.");
														Tracer.tracepointVariableInt(
																"stonesoup_exit_code",
																stonesoup_exit_code);
														AnalyzerManagerImpl.unrepulsedMonticulose
																.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																		stonesoup_exit_code);
													}
												} catch (java.lang.InterruptedException ie) {
													Tracer.tracepointError(ie
															.getClass()
															.getName()
															+ ": "
															+ ie.getMessage());
													AnalyzerManagerImpl.unrepulsedMonticulose
															.println("STONESOUP: Error waiting for subprocess.");
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException wearproofSigillarian) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												wearproofSigillarian);
									}
								}
							}
						}
					} finally {
						AnalyzerManagerImpl.unrepulsedMonticulose.close();
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
