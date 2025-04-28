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

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    static PrintStream cowberryCylindromatous = null;
	private static final java.util.concurrent.atomic.AtomicBoolean incitiveFormica = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (incitiveFormica.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpK4Usdt_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File unuprightlyTunbellied = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unuprightlyTunbellied.getParentFile().exists()
					&& !unuprightlyTunbellied.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.cowberryCylindromatous = new PrintStream(
							new FileOutputStream(unuprightlyTunbellied, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException facadeMicrophonograph) {
					System.err.printf("Failed to open log file.  %s\n",
							facadeMicrophonograph.getMessage());
					ModuleManagerImpl.cowberryCylindromatous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							facadeMicrophonograph);
				} catch (FileNotFoundException athericerousGoutify) {
					System.err.printf("Failed to open log file.  %s\n",
							athericerousGoutify.getMessage());
					ModuleManagerImpl.cowberryCylindromatous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							athericerousGoutify);
				}
				if (ModuleManagerImpl.cowberryCylindromatous != null) {
					try {
						final String angiolymphoma_orlewise = System
								.getenv("DONAX_CURVIDENTATE");
						if (null != angiolymphoma_orlewise) {
							final Object tricliniarch_sulfazide = angiolymphoma_orlewise;
							boolean gantries_smytrie = false;
							shopman_dicyanogen: for (int unsubjectable_speckledness = 0; unsubjectable_speckledness < 10; unsubjectable_speckledness++)
								for (int mercedes_tromometrical = 0; mercedes_tromometrical < 10; mercedes_tromometrical++)
									if (unsubjectable_speckledness
											* mercedes_tromometrical == 63) {
										gantries_smytrie = true;
										break shopman_dicyanogen;
									}
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
							Tracer.tracepointVariableString("country_name",
									((String) tricliniarch_sulfazide));
							if (stonesoup_mysql_host == null
									|| stonesoup_mysql_user == null
									|| stonesoup_mysql_pass == null
									|| stonesoup_mysql_port == null
									|| stonesoup_mysql_dbname == null) {
								Tracer.tracepointError("Missing required database connection parameter(s).");
								ModuleManagerImpl.cowberryCylindromatous
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
									Class.forName("com.mysql.jdbc.Driver")
											.newInstance();
									Tracer.tracepointMessage("Establishing connection to database.");
									java.sql.Connection con = java.sql.DriverManager
											.getConnection(jdbc.toString(),
													stonesoup_mysql_user,
													stonesoup_mysql_pass);
									java.sql.Statement stmt = con
											.createStatement();
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String queryString = "SELECT * FROM Customers WHERE "
											+ "Country=\'"
											+ ((String) tricliniarch_sulfazide)
											+ "\'";
									Tracer.tracepointVariableString(
											"queryString", queryString);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									ModuleManagerImpl.cowberryCylindromatous
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
										resultSet = stmt.getResultSet();
										while (resultSet.next()) {
											metaData = resultSet.getMetaData();
											columnCount = metaData
													.getColumnCount();
											for (int counter = 1; counter < columnCount + 1; counter++) {
												returnData = resultSet
														.getString(counter);
												ModuleManagerImpl.cowberryCylindromatous
														.println(returnData);
											}
										}
										hasMoreResults = stmt.getMoreResults();
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									con.close();
								} catch (java.sql.SQLException se) {
									Tracer.tracepointError(se.getClass()
											.getName() + ": " + se.getMessage());
									ModuleManagerImpl.cowberryCylindromatous
											.println("STONESOUP: Error accessing database.");
									se.printStackTrace(ModuleManagerImpl.cowberryCylindromatous);
								} catch (ClassNotFoundException cnfe) {
									Tracer.tracepointError(cnfe.getClass()
											.getName()
											+ ": "
											+ cnfe.getMessage());
									ModuleManagerImpl.cowberryCylindromatous
											.println("STONESOUP: Error accessing database.");
									cnfe.printStackTrace(ModuleManagerImpl.cowberryCylindromatous);
								} catch (IllegalAccessException iae) {
									Tracer.tracepointError(iae.getClass()
											.getName()
											+ ": "
											+ iae.getMessage());
									ModuleManagerImpl.cowberryCylindromatous
											.println("STONESOUP: Error accessing database.");
									iae.printStackTrace(ModuleManagerImpl.cowberryCylindromatous);
								} catch (InstantiationException ie) {
									Tracer.tracepointError(ie.getClass()
											.getName() + ": " + ie.getMessage());
									ModuleManagerImpl.cowberryCylindromatous
											.println("STONESOUP: Error accessing database.");
									ie.printStackTrace(ModuleManagerImpl.cowberryCylindromatous);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ModuleManagerImpl.cowberryCylindromatous.close();
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
