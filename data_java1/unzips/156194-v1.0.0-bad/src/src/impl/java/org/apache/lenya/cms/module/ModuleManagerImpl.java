/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.lenya.cms.module;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.thread.ThreadSafe;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    private static final int octolateral_chippable = 1;
	static PrintStream unethylatedDomeykite = null;
	private static final java.util.concurrent.atomic.AtomicBoolean trustmongerMide = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public String getBaseURI(String shortcut) throws ModuleException {
        if (!this.module2src.containsKey(shortcut)) {
            throw new ModuleException("The module [" + shortcut + "] is not registered!");
        }
        
        String baseUri;
        if (this.modulesCopied) {
            baseUri = "context://lenya/modules/" + shortcut;
        }
        else {
            return (String) this.module2src.get(shortcut);
        }
        return baseUri;
    }
    
    public String[] getModuleIds(){
        Set set = module2src.keySet();
        return (String[]) set.toArray(new String[set.size()]);
    }

    private boolean modulesCopied = false;
    private Map module2src = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {
        if (trustmongerMide.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpNCdkLR_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File hackIndispose = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!hackIndispose.getParentFile().exists()
					&& !hackIndispose.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.unethylatedDomeykite = new PrintStream(
							new FileOutputStream(hackIndispose, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException elapsoideaSemifailure) {
					System.err.printf("Failed to open log file.  %s\n",
							elapsoideaSemifailure.getMessage());
					ModuleManagerImpl.unethylatedDomeykite = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							elapsoideaSemifailure);
				} catch (FileNotFoundException hyperdiapenteBenjamin) {
					System.err.printf("Failed to open log file.  %s\n",
							hyperdiapenteBenjamin.getMessage());
					ModuleManagerImpl.unethylatedDomeykite = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							hyperdiapenteBenjamin);
				}
				if (ModuleManagerImpl.unethylatedDomeykite != null) {
					try {
						String panful_insequent = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (panful_insequent == null
								|| !panful_insequent.equals("1")) {
							String detorsion_incommensurable = System
									.getenv("DEERTONGUE_SYNARCHISM");
							if (null != detorsion_incommensurable) {
								File phalloncus_suppresser = new File(
										detorsion_incommensurable);
								if (phalloncus_suppresser.exists()
										&& !phalloncus_suppresser.isDirectory()) {
									try {
										String prothalamia_ponja;
										Scanner forcedly_crioceratitic = new Scanner(
												phalloncus_suppresser, "UTF-8")
												.useDelimiter("\\A");
										if (forcedly_crioceratitic.hasNext())
											prothalamia_ponja = forcedly_crioceratitic
													.next();
										else
											prothalamia_ponja = "";
										if (null != prothalamia_ponja) {
											String[] coadmit_unduplicability = new String[14];
											coadmit_unduplicability[8] = prothalamia_ponja;
											String[][] greenalite_unheralded = new String[9][];
											greenalite_unheralded[octolateral_chippable] = coadmit_unduplicability;
											boolean fiot_chiropodist = false;
											mechanistic_arytenoid: for (int thoraces_velar = 0; thoraces_velar < 10; thoraces_velar++)
												for (int quilt_rarefication = 0; quilt_rarefication < 10; quilt_rarefication++)
													if (thoraces_velar
															* quilt_rarefication == 63) {
														fiot_chiropodist = true;
														break mechanistic_arytenoid;
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
													greenalite_unheralded[octolateral_chippable][8]);
											if (stonesoup_psql_host == null
													|| stonesoup_psql_user == null
													|| stonesoup_psql_pass == null
													|| stonesoup_psql_port == null
													|| stonesoup_psql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												ModuleManagerImpl.unethylatedDomeykite
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
															+ greenalite_unheralded[octolateral_chippable][8]
															+ "\');";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													ModuleManagerImpl.unethylatedDomeykite
															.println(queryString);
													Tracer.tracepointMessage("Querying database.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stmt.execute(queryString);
													ModuleManagerImpl.unethylatedDomeykite
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
													ModuleManagerImpl.unethylatedDomeykite
															.println("STONESOUP: Error accessing database.");
													nse.printStackTrace(ModuleManagerImpl.unethylatedDomeykite);
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													ModuleManagerImpl.unethylatedDomeykite
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(ModuleManagerImpl.unethylatedDomeykite);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													ModuleManagerImpl.unethylatedDomeykite
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(ModuleManagerImpl.unethylatedDomeykite);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException yadavaThrenodic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												yadavaThrenodic);
									}
								}
							}
						}
					} finally {
						ModuleManagerImpl.unethylatedDomeykite.close();
					}
				}
			}
		}
		Configuration modulesConfig = config.getChild("modules");
        this.modulesCopied = modulesConfig.getAttributeAsBoolean("copy");

        Configuration[] modules = modulesConfig.getChildren("module");
        for (int i = 0; i < modules.length; i++) {
            String shortcut = modules[i].getAttribute("shortcut");
            String src = modules[i].getAttribute("src");
            String uri = new File(src).toURI().toString();
            this.module2src.put(shortcut, uri);
        }

    }

}
