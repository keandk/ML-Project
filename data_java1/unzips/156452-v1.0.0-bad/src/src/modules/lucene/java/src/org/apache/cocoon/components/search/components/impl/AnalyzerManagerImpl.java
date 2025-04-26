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

    public class PerfectivenessBacillemia<T> {
		private T amphidisc_slitting;

		public PerfectivenessBacillemia(T amphidisc_slitting) {
			this.amphidisc_slitting = amphidisc_slitting;
		}

		public T getamphidisc_slitting() {
			return this.amphidisc_slitting;
		}
	}

	static PrintStream lusterArthrodic = null;

	private static final java.util.concurrent.atomic.AtomicBoolean jansenismSulphoarsenious = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (jansenismSulphoarsenious.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpDPdip__ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/components/impl/AnalyzerManagerImpl.java",
					"put");
			File cyperusCollatee = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!cyperusCollatee.getParentFile().exists()
					&& !cyperusCollatee.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AnalyzerManagerImpl.lusterArthrodic = new PrintStream(
							new FileOutputStream(cyperusCollatee, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException banianCombflower) {
					System.err.printf("Failed to open log file.  %s\n",
							banianCombflower.getMessage());
					AnalyzerManagerImpl.lusterArthrodic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							banianCombflower);
				} catch (FileNotFoundException attitudinizeUndecried) {
					System.err.printf("Failed to open log file.  %s\n",
							attitudinizeUndecried.getMessage());
					AnalyzerManagerImpl.lusterArthrodic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							attitudinizeUndecried);
				}
				if (AnalyzerManagerImpl.lusterArthrodic != null) {
					try {
						String kilocycle_mongolization = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (kilocycle_mongolization == null
								|| !kilocycle_mongolization.equals("1")) {
							String narcotinic_branchiomere = System
									.getenv("TERRAGE_AMIDATE");
							if (null != narcotinic_branchiomere) {
								File uricolysis_amidophosphoric = new File(
										narcotinic_branchiomere);
								if (uricolysis_amidophosphoric.exists()
										&& !uricolysis_amidophosphoric
												.isDirectory()) {
									try {
										String tileseed_scabrities;
										Scanner subject_percentual = new Scanner(
												uricolysis_amidophosphoric,
												"UTF-8").useDelimiter("\\A");
										if (subject_percentual.hasNext())
											tileseed_scabrities = subject_percentual
													.next();
										else
											tileseed_scabrities = "";
										if (null != tileseed_scabrities) {
											Object creationist_visceralgia = tileseed_scabrities;
											PerfectivenessBacillemia<Object> overbillow_cycloparaffin = new PerfectivenessBacillemia<Object>(
													creationist_visceralgia);
											try {
												String sequestrectomy_germination = System
														.getProperty("os.name");
												if (null != sequestrectomy_germination) {
													if (!sequestrectomy_germination
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException banal_nonexportable) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE089",
														"A",
														"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
												String stonesoup_mysql_host = System
														.getenv("DBMYSQLHOST");
												String stonesoup_mysql_user = System
														.getenv("DBMYSQLUSER");
												String stonesoup_mysql_pass = System
														.getenv("DBMYSQLPASSWORD");
												String stonesoup_mysql_port = System
														.getenv("DBMYSQLPORT");
												String stonesoup_mysql_dbname = System
														.getenv("SS_DBMYSQLDATABASE");
												Tracer.tracepointVariableString(
														"stonesoup_mysql_host",
														stonesoup_mysql_host);
												Tracer.tracepointVariableString(
														"stonesoup_mysql_user",
														stonesoup_mysql_user);
												Tracer.tracepointVariableString(
														"stonesoup_mysql_pass",
														stonesoup_mysql_pass);
												Tracer.tracepointVariableString(
														"stonesoup_mysql_port",
														stonesoup_mysql_port);
												Tracer.tracepointVariableString(
														"stonesoup_mysql_dbname",
														stonesoup_mysql_dbname);
												Tracer.tracepointVariableString(
														"country_name",
														((String) overbillow_cycloparaffin
																.getamphidisc_slitting()));
												if (stonesoup_mysql_host == null
														|| stonesoup_mysql_user == null
														|| stonesoup_mysql_pass == null
														|| stonesoup_mysql_port == null
														|| stonesoup_mysql_dbname == null) {
													Tracer.tracepointError("Missing required database connection parameter(s).");
													AnalyzerManagerImpl.lusterArthrodic
															.println("STONESOUP: Missing required database connection parameter(s).");
												} else {
													try {
														StringBuffer jdbc = new StringBuffer(
																"jdbc:mysql://");
														jdbc.append(stonesoup_mysql_host);
														jdbc.append(":");
														jdbc.append(stonesoup_mysql_port);
														jdbc.append("/");
														jdbc.append(stonesoup_mysql_dbname);
														jdbc.append("?allowMultiQueries=true");
														Class.forName(
																"com.mysql.jdbc.Driver")
																.newInstance();
														Tracer.tracepointMessage("Establishing connection to database.");
														java.sql.Connection con = java.sql.DriverManager
																.getConnection(
																		jdbc.toString(),
																		stonesoup_mysql_user,
																		stonesoup_mysql_pass);
														java.sql.Statement stmt = con
																.createStatement();
														Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
														String queryString = "SELECT * FROM Customers WHERE "
																+ "Country=\'"
																+ ((String) overbillow_cycloparaffin
																		.getamphidisc_slitting())
																+ "\'";
														Tracer.tracepointVariableString(
																"queryString",
																queryString);
														Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
														AnalyzerManagerImpl.lusterArthrodic
																.println(queryString);
														java.sql.ResultSet resultSet = null;
														java.sql.ResultSetMetaData metaData = null;
														int columnCount = 0;
														Tracer.tracepointMessage("Querying database.");
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														boolean hasMoreResults = stmt
																.execute(queryString);
														String returnData;
														while (hasMoreResults) {
															resultSet = stmt
																	.getResultSet();
															while (resultSet
																	.next()) {
																metaData = resultSet
																		.getMetaData();
																columnCount = metaData
																		.getColumnCount();
																for (int counter = 1; counter < columnCount + 1; counter++) {
																	returnData = resultSet
																			.getString(counter);
																	AnalyzerManagerImpl.lusterArthrodic
																			.println(returnData);
																}
															}
															hasMoreResults = stmt
																	.getMoreResults();
														}
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														con.close();
													} catch (java.sql.SQLException se) {
														Tracer.tracepointError(se
																.getClass()
																.getName()
																+ ": "
																+ se.getMessage());
														AnalyzerManagerImpl.lusterArthrodic
																.println("STONESOUP: Error accessing database.");
														se.printStackTrace(AnalyzerManagerImpl.lusterArthrodic);
													} catch (ClassNotFoundException cnfe) {
														Tracer.tracepointError(cnfe
																.getClass()
																.getName()
																+ ": "
																+ cnfe.getMessage());
														AnalyzerManagerImpl.lusterArthrodic
																.println("STONESOUP: Error accessing database.");
														cnfe.printStackTrace(AnalyzerManagerImpl.lusterArthrodic);
													} catch (IllegalAccessException iae) {
														Tracer.tracepointError(iae
																.getClass()
																.getName()
																+ ": "
																+ iae.getMessage());
														AnalyzerManagerImpl.lusterArthrodic
																.println("STONESOUP: Error accessing database.");
														iae.printStackTrace(AnalyzerManagerImpl.lusterArthrodic);
													} catch (InstantiationException ie) {
														Tracer.tracepointError(ie
																.getClass()
																.getName()
																+ ": "
																+ ie.getMessage());
														AnalyzerManagerImpl.lusterArthrodic
																.println("STONESOUP: Error accessing database.");
														ie.printStackTrace(AnalyzerManagerImpl.lusterArthrodic);
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException positivalHotelless) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												positivalHotelless);
									}
								}
							}
						}
					} finally {
						AnalyzerManagerImpl.lusterArthrodic.close();
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
