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

    public static interface IOverhighVoluptuous {
		public void rehydrationUnrepentant(
				final String[] multilocation_polyphonia);
	}

	public static class EsterizationUltrasterile implements IOverhighVoluptuous {
		@Override
		public void rehydrationUnrepentant(
				final String[] multilocation_polyphonia) {
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"B",
					"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
			String stonesoup_psql_host = System.getenv("DBPGHOST");
			String stonesoup_psql_user = System.getenv("DBPGUSER");
			String stonesoup_psql_pass = System.getenv("DBPGPASSWORD");
			String stonesoup_psql_port = System.getenv("DBPGPORT");
			String stonesoup_psql_dbname = System.getenv("SS_DBPGDATABASE");
			Tracer.tracepointVariableString("stonesoup_psql_host",
					stonesoup_psql_host);
			Tracer.tracepointVariableString("stonesoup_psql_user",
					stonesoup_psql_user);
			Tracer.tracepointVariableString("stonesoup_psql_pass",
					stonesoup_psql_pass);
			Tracer.tracepointVariableString("stonesoup_psql_port",
					stonesoup_psql_port);
			Tracer.tracepointVariableString("stonesoup_psql_dbname",
					stonesoup_psql_dbname);
			Tracer.tracepointVariableString("taintvar",
					multilocation_polyphonia[4]);
			if (stonesoup_psql_host == null || stonesoup_psql_user == null
					|| stonesoup_psql_pass == null
					|| stonesoup_psql_port == null
					|| stonesoup_psql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				AnalyzerManagerImpl.wrossleHelpworthy
						.println("STONESOUP: Missing required database connection parameters.");
			} else {
				try {
					StringBuffer jdbc = new StringBuffer("jdbc:postgresql://");
					jdbc.append(stonesoup_psql_host);
					jdbc.append(":");
					jdbc.append(stonesoup_psql_port);
					jdbc.append("/");
					jdbc.append(stonesoup_psql_dbname);
					Tracer.tracepointMessage("Establishing connection to database.");
					Class.forName("org.postgresql.Driver");
					java.sql.Connection conn = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_psql_user, stonesoup_psql_pass);
					java.sql.Statement stmt = conn.createStatement();
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String query = "SELECT * FROM customers WHERE country =\'"
							+ multilocation_polyphonia[4] + "\';";
					Tracer.tracepointVariableString("query", query);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					AnalyzerManagerImpl.wrossleHelpworthy.println(query);
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					boolean hasMoreResults = stmt.execute(query);
					String rtnString;
					while (hasMoreResults) {
						java.sql.ResultSet rs = stmt.getResultSet();
						if (rs != null) {
							java.sql.ResultSetMetaData metaData = null;
							int columns = 0;
							while (rs.next()) {
								metaData = rs.getMetaData();
								columns = metaData.getColumnCount();
								for (int i = 1; i < columns + 1; i++) {
									rtnString = rs.getString(i);
									AnalyzerManagerImpl.wrossleHelpworthy
											.println(rtnString);
								}
							}
						}
						hasMoreResults = stmt.getMoreResults();
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					stmt.close();
					conn.close();
				} catch (java.sql.SQLFeatureNotSupportedException nse) {
					Tracer.tracepointError(nse.getClass().getName() + ": "
							+ nse.getMessage());
					AnalyzerManagerImpl.wrossleHelpworthy
							.println("STONESOUP: Error accessing database.");
					nse.printStackTrace(AnalyzerManagerImpl.wrossleHelpworthy);
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					AnalyzerManagerImpl.wrossleHelpworthy
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(AnalyzerManagerImpl.wrossleHelpworthy);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					AnalyzerManagerImpl.wrossleHelpworthy
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(AnalyzerManagerImpl.wrossleHelpworthy);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	static PrintStream wrossleHelpworthy = null;

	private static final java.util.concurrent.atomic.AtomicBoolean meritedlyDarkishness = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (meritedlyDarkishness.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpT5nKNZ_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/components/impl/AnalyzerManagerImpl.java",
					"put");
			File spermocarpHemihydrate = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!spermocarpHemihydrate.getParentFile().exists()
					&& !spermocarpHemihydrate.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AnalyzerManagerImpl.wrossleHelpworthy = new PrintStream(
							new FileOutputStream(spermocarpHemihydrate, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException neoplatonistNuncupate) {
					System.err.printf("Failed to open log file.  %s\n",
							neoplatonistNuncupate.getMessage());
					AnalyzerManagerImpl.wrossleHelpworthy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							neoplatonistNuncupate);
				} catch (FileNotFoundException striaeDegressive) {
					System.err.printf("Failed to open log file.  %s\n",
							striaeDegressive.getMessage());
					AnalyzerManagerImpl.wrossleHelpworthy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							striaeDegressive);
				}
				if (AnalyzerManagerImpl.wrossleHelpworthy != null) {
					try {
						final String merogastrula_leaven = System
								.getenv("HORMOGON_TANTALIZINGLY");
						if (null != merogastrula_leaven) {
							final String[] oxyacanthine_undethronable = new String[21];
							oxyacanthine_undethronable[4] = merogastrula_leaven;
							IOverhighVoluptuous thumbbird_polarid = new EsterizationUltrasterile();
							thumbbird_polarid
									.rehydrationUnrepentant(oxyacanthine_undethronable);
						}
					} finally {
						AnalyzerManagerImpl.wrossleHelpworthy.close();
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
