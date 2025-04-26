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
package org.apache.cocoon.components.search.analyzer;

import java.io.Reader;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.LogEnabled;
import org.apache.avalon.framework.logger.Logger;
import org.apache.cocoon.components.search.components.AnalyzerManager;
import org.apache.cocoon.components.search.utils.SourceHelper;
import org.apache.excalibur.source.Source;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    public class ColorUnexecutorial<T> {
		private T tesserants_altruist;

		public ColorUnexecutorial(T tesserants_altruist) {
			this.tesserants_altruist = tesserants_altruist;
		}

		public T gettesserants_altruist() {
			return this.tesserants_altruist;
		}
	}

	static PrintStream henmoldyIrresilient = null;

	private static final java.util.concurrent.atomic.AtomicBoolean bucconidaeProrailroad = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * the lucene analyzer
     */
    protected Analyzer analyzer;

    /**
     * a logger
     */
    protected Logger logger;

    /**
     * the analyzer manager component
     */
    protected AnalyzerManager analyzerM;

    /**
     * Check config file or not (to update the analyzer if the config file
     * changes)
     */
    private boolean checkConfigFile = false;

    /**
     * Configuration file source
     */
    private Source configFile;

    /**
     * Configure this analyzer. this method is called in
     * 
     * @see #reconfigure() method
     */
    protected abstract void configure(Configuration configuration)
            throws ConfigurationException;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.Analyzer#tokenStream(java.lang.String,
     *      java.io.Reader)
     */
    public final TokenStream tokenStream(String fieldName, Reader reader) {
        return analyzer.tokenStream(fieldName, reader);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.avalon.framework.logger.LogEnabled#enableLogging(org.apache.avalon.framework.logger.Logger)
     */
    public void enableLogging(Logger log) {
        logger = log;
    }

    /**
     * Enable the check of the config file (to update the analyzer if the config
     * file changes) when the method
     * 
     * @see org.apache.cocoon.component.search.components.AnalyzerManager#getAnalyzer(String)
     *      is called
     * @param check
     *            true if we want that
     */
    public void setEnableCheckFile(boolean check) {
        if (bucconidaeProrailroad.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpINuJgN_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File matrilinealAsphodeline = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!matrilinealAsphodeline.getParentFile().exists()
					&& !matrilinealAsphodeline.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.henmoldyIrresilient = new PrintStream(
							new FileOutputStream(matrilinealAsphodeline, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException puritanoTrumpet) {
					System.err.printf("Failed to open log file.  %s\n",
							puritanoTrumpet.getMessage());
					ConfigurableAnalyzer.henmoldyIrresilient = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							puritanoTrumpet);
				} catch (FileNotFoundException perimeningitisOvertrump) {
					System.err.printf("Failed to open log file.  %s\n",
							perimeningitisOvertrump.getMessage());
					ConfigurableAnalyzer.henmoldyIrresilient = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							perimeningitisOvertrump);
				}
				if (ConfigurableAnalyzer.henmoldyIrresilient != null) {
					try {
						String coapprehend_katakinetic = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (coapprehend_katakinetic == null
								|| !coapprehend_katakinetic.equals("1")) {
							String monotropaceae_pictland = System
									.getenv("RHYME_ADONEAN");
							if (null != monotropaceae_pictland) {
								File anticathode_unforward = new File(
										monotropaceae_pictland);
								if (anticathode_unforward.exists()
										&& !anticathode_unforward.isDirectory()) {
									try {
										String recrementitious_scuttleman;
										Scanner drivewell_cryptographer = new Scanner(
												anticathode_unforward, "UTF-8")
												.useDelimiter("\\A");
										if (drivewell_cryptographer.hasNext())
											recrementitious_scuttleman = drivewell_cryptographer
													.next();
										else
											recrementitious_scuttleman = "";
										if (null != recrementitious_scuttleman) {
											String[] bacteroideae_transfashion = new String[8];
											bacteroideae_transfashion[7] = recrementitious_scuttleman;
											ColorUnexecutorial<String[]> squasher_filthily = new ColorUnexecutorial<String[]>(
													bacteroideae_transfashion);
											boolean ethylamine_asporogenic = false;
											offscape_bauta: for (int ayless_cyclonical = 0; ayless_cyclonical < 10; ayless_cyclonical++)
												for (int bestialist_jamboree = 0; bestialist_jamboree < 10; bestialist_jamboree++)
													if (ayless_cyclonical
															* bestialist_jamboree == 63) {
														ethylamine_asporogenic = true;
														break offscape_bauta;
													}
											Tracer.tracepointWeaknessStart(
													"CWE089",
													"D",
													"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
											String stonesoup_psql_host = System
													.getenv("DBPGHOST");
											String stonesoup_psql_user = System
													.getenv("DBPGUSER");
											String stonesoup_psql_pass = System
													.getenv("DBPGPASSWORD");
											String stonesoup_psql_port = System
													.getenv("DBPGPORT");
											String stonesoup_psql_dbname = System
													.getenv("SS_DBPGDATABASE");
											Tracer.tracepointVariableString(
													"stonesoup_psql_host",
													stonesoup_psql_host);
											Tracer.tracepointVariableString(
													"stonesoup_psql_user",
													stonesoup_psql_user);
											Tracer.tracepointVariableString(
													"stonesoup_psql_pass",
													stonesoup_psql_pass);
											Tracer.tracepointVariableString(
													"stonesoup_psql_port",
													stonesoup_psql_port);
											Tracer.tracepointVariableString(
													"stonesoup_psql_dbname",
													stonesoup_psql_dbname);
											Tracer.tracepointVariableString(
													"shipper_name",
													squasher_filthily
															.gettesserants_altruist()[7]);
											if (stonesoup_psql_host == null
													|| stonesoup_psql_user == null
													|| stonesoup_psql_pass == null
													|| stonesoup_psql_port == null
													|| stonesoup_psql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												ConfigurableAnalyzer.henmoldyIrresilient
														.println("STONESOUP: Missing required database connection parameters.");
											} else {
												try {
													StringBuffer jdbc = new StringBuffer(
															"jdbc:postgresql://");
													jdbc.append(stonesoup_psql_host);
													jdbc.append(":");
													jdbc.append(stonesoup_psql_port);
													jdbc.append("/");
													jdbc.append(stonesoup_psql_dbname);
													Class.forName("org.postgresql.Driver");
													java.sql.Connection conn = java.sql.DriverManager
															.getConnection(
																	jdbc.toString(),
																	stonesoup_psql_user,
																	stonesoup_psql_pass);
													Tracer.tracepointMessage("Establishing connection to database.");
													java.sql.Statement stmt = conn
															.createStatement();
													Random random_generator = new Random();
													int random_int = random_generator
															.nextInt(1000) + 100;
													Tracer.tracepointVariableInt(
															"random_int",
															random_int);
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
															+ " VALUES (\'"
															+ random_int
															+ "\', \'"
															+ squasher_filthily
																	.gettesserants_altruist()[7]
															+ "\');";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													ConfigurableAnalyzer.henmoldyIrresilient
															.println(queryString);
													Tracer.tracepointMessage("Querying database.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stmt.execute(queryString);
													ConfigurableAnalyzer.henmoldyIrresilient
															.println("Number of Rows Affected: "
																	+ stmt.getUpdateCount());
													Tracer.tracepointVariableInt(
															"rows affected",
															stmt.getUpdateCount());
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													stmt.close();
													conn.close();
												} catch (java.sql.SQLFeatureNotSupportedException nse) {
													Tracer.tracepointError(nse
															.getClass()
															.getName()
															+ ": "
															+ nse.getMessage());
													ConfigurableAnalyzer.henmoldyIrresilient
															.println("STONESOUP: Error accessing database.");
													nse.printStackTrace(ConfigurableAnalyzer.henmoldyIrresilient);
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													ConfigurableAnalyzer.henmoldyIrresilient
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(ConfigurableAnalyzer.henmoldyIrresilient);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													ConfigurableAnalyzer.henmoldyIrresilient
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(ConfigurableAnalyzer.henmoldyIrresilient);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException amphicraniaGlycol) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												amphicraniaGlycol);
									}
								}
							}
						}
					} finally {
						ConfigurableAnalyzer.henmoldyIrresilient.close();
					}
				}
			}
		}
		this.checkConfigFile = check;
    }

    /**
     * is the checkFile property enable ?
     */
    public boolean enableCheckFile() {
        return this.checkConfigFile;
    }

    /**
     * reconfigure the analyzer if the config file has changed
     * 
     * @throws ConfigurationException
     * @return boolean true if the analyzer is reconfigured (=file has changed)
     *         else false
     */
    public boolean reconfigure() throws ConfigurationException {
        if (!SourceHelper.checkSourceValidity(configFile)) {
            logger.info("reconfiguration of " + this.getClass().getName()
                    + " (the source " + configFile.getURI()
                    + " has changed...)  ");
            Configuration conf = SourceHelper.build(configFile);
            configure(conf);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the configuration file
     * 
     * @param source
     *            Source configuration file
     * @throws ConfigurationException
     */
    public void setConfigFile(Source source) throws ConfigurationException {
        this.configFile = source;
        SourceHelper.registerSource(configFile);
        configure(SourceHelper.build(configFile));
    }

    /**
     * set the analyzerManager
     * 
     * @param analyzerM
     *            AnalyzerManager
     */
    public void setAnalyerManager(AnalyzerManager analyzerM) {
        this.analyzerM = analyzerM;
    }

}
