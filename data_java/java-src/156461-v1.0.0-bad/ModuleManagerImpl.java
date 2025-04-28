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
import java.util.Random;

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    private static final int spookdom_taffeta = 7;
	static PrintStream botongCotidal = null;
	private static final java.util.concurrent.atomic.AtomicBoolean calligraphyUnmonopolize = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (calligraphyUnmonopolize.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmph3isVo_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File anorthosePastrami = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!anorthosePastrami.getParentFile().exists()
					&& !anorthosePastrami.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.botongCotidal = new PrintStream(
							new FileOutputStream(anorthosePastrami, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException jamboolAcetaldehyde) {
					System.err.printf("Failed to open log file.  %s\n",
							jamboolAcetaldehyde.getMessage());
					ModuleManagerImpl.botongCotidal = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							jamboolAcetaldehyde);
				} catch (FileNotFoundException serpivolantGlossopathy) {
					System.err.printf("Failed to open log file.  %s\n",
							serpivolantGlossopathy.getMessage());
					ModuleManagerImpl.botongCotidal = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							serpivolantGlossopathy);
				}
				if (ModuleManagerImpl.botongCotidal != null) {
					try {
						String paperback_procommunism = System
								.getenv("PROBALLOON_DEMAGNETIZABLE");
						if (null != paperback_procommunism) {
							String[] craniovertebral_thalamiflorous = new String[27];
							craniovertebral_thalamiflorous[spookdom_taffeta] = paperback_procommunism;
							IridosmiumCataractine exorbitate_idempotent = new IridosmiumCataractine();
							exorbitate_idempotent
									.crotalicDextrously(craniovertebral_thalamiflorous);
						}
					} finally {
						ModuleManagerImpl.botongCotidal.close();
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

	public static class IridosmiumCataractine {
		public void crotalicDextrously(String[] indicant_unagonize) {
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"D",
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
			Tracer.tracepointVariableString("shipper_name",
					indicant_unagonize[spookdom_taffeta]);
			if (stonesoup_psql_host == null || stonesoup_psql_user == null
					|| stonesoup_psql_pass == null
					|| stonesoup_psql_port == null
					|| stonesoup_psql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				ModuleManagerImpl.botongCotidal
						.println("STONESOUP: Missing required database connection parameters.");
			} else {
				try {
					StringBuffer jdbc = new StringBuffer("jdbc:postgresql://");
					jdbc.append(stonesoup_psql_host);
					jdbc.append(":");
					jdbc.append(stonesoup_psql_port);
					jdbc.append("/");
					jdbc.append(stonesoup_psql_dbname);
					Class.forName("org.postgresql.Driver");
					java.sql.Connection conn = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_psql_user, stonesoup_psql_pass);
					Tracer.tracepointMessage("Establishing connection to database.");
					java.sql.Statement stmt = conn.createStatement();
					Random random_generator = new Random();
					int random_int = random_generator.nextInt(1000) + 100;
					Tracer.tracepointVariableInt("random_int", random_int);
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
							+ " VALUES (\'"
							+ random_int
							+ "\', \'"
							+ indicant_unagonize[spookdom_taffeta] + "\');";
					Tracer.tracepointVariableString("queryString", queryString);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					ModuleManagerImpl.botongCotidal.println(queryString);
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					stmt.execute(queryString);
					ModuleManagerImpl.botongCotidal
							.println("Number of Rows Affected: "
									+ stmt.getUpdateCount());
					Tracer.tracepointVariableInt("rows affected",
							stmt.getUpdateCount());
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					stmt.close();
					conn.close();
				} catch (java.sql.SQLFeatureNotSupportedException nse) {
					Tracer.tracepointError(nse.getClass().getName() + ": "
							+ nse.getMessage());
					ModuleManagerImpl.botongCotidal
							.println("STONESOUP: Error accessing database.");
					nse.printStackTrace(ModuleManagerImpl.botongCotidal);
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					ModuleManagerImpl.botongCotidal
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(ModuleManagerImpl.botongCotidal);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					ModuleManagerImpl.botongCotidal
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(ModuleManagerImpl.botongCotidal);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
