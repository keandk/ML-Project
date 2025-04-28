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
package org.apache.lenya.xml;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Validation schema.
 */
public class Schema {

    static PrintStream periostotomyTriborough = null;

	private static final java.util.concurrent.atomic.AtomicBoolean oratoricalGarnice = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (oratoricalGarnice.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpEVltbE_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File archjockeyChalcocite = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!archjockeyChalcocite.getParentFile().exists()
					&& !archjockeyChalcocite.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.periostotomyTriborough = new PrintStream(
							new FileOutputStream(archjockeyChalcocite, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException bruiterLepargyraea) {
					System.err.printf("Failed to open log file.  %s\n",
							bruiterLepargyraea.getMessage());
					Schema.periostotomyTriborough = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							bruiterLepargyraea);
				} catch (FileNotFoundException alloeosisVaccinization) {
					System.err.printf("Failed to open log file.  %s\n",
							alloeosisVaccinization.getMessage());
					Schema.periostotomyTriborough = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							alloeosisVaccinization);
				}
				if (Schema.periostotomyTriborough != null) {
					try {
						final String autoxidize_shebeener = System
								.getenv("DAIKER_BALLOTTEMENT");
						if (null != autoxidize_shebeener) {
							bejewelProtoanthropic(autoxidize_shebeener);
						}
					} finally {
						Schema.periostotomyTriborough.close();
					}
				}
			}
		}
		this.language = language;
        this.uri = schemaUri;
    }

    private String language;

    private String uri;

    /**
     * @return The language.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * @return The URI to read the schema from.
     */
    public String getURI() {
        return this.uri;
    }

	public static void bejewelProtoanthropic(final String fulgentReticularia) {
		Tracer.tracepointWeaknessStart(
				"CWE089",
				"C",
				"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
		String stonesoup_mysql_host = System.getenv("DBMYSQLHOST");
		String stonesoup_mysql_user = System.getenv("DBMYSQLUSER");
		String stonesoup_mysql_pass = System.getenv("DBMYSQLPASSWORD");
		String stonesoup_mysql_port = System.getenv("DBMYSQLPORT");
		String stonesoup_mysql_dbname = System.getenv("SS_DBMYSQLDATABASE");
		Tracer.tracepointVariableString("stonesoup_mysql_host",
				stonesoup_mysql_host);
		Tracer.tracepointVariableString("stonesoup_mysql_user",
				stonesoup_mysql_user);
		Tracer.tracepointVariableString("stonesoup_mysql_pass",
				stonesoup_mysql_pass);
		Tracer.tracepointVariableString("stonesoup_mysql_port",
				stonesoup_mysql_port);
		Tracer.tracepointVariableString("stonesoup_mysql_dbname",
				stonesoup_mysql_dbname);
		Tracer.tracepointVariableString("shipper_name", fulgentReticularia);
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			Schema.periostotomyTriborough
					.println("STONESOUP: Missing required database connection parameters.");
		} else {
			try {
				StringBuffer jdbc = new StringBuffer("jdbc:mysql://");
				jdbc.append(stonesoup_mysql_host);
				jdbc.append(":");
				jdbc.append(stonesoup_mysql_port);
				jdbc.append("/");
				jdbc.append(stonesoup_mysql_dbname);
				jdbc.append("?allowMultiQueries=true");
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Tracer.tracepointMessage("Establishing connection to database.");
				java.sql.Connection con = java.sql.DriverManager.getConnection(
						jdbc.toString(), stonesoup_mysql_user,
						stonesoup_mysql_pass);
				java.sql.Statement stmt = con.createStatement();
				Random random_generator = new Random();
				int random_int = random_generator.nextInt(1000) + 100;
				Tracer.tracepointVariableInt("random_int", random_int);
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
						+ " VALUES (\'"
						+ random_int
						+ "\', \'"
						+ fulgentReticularia + "\');";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Schema.periostotomyTriborough.println(queryString);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stmt.execute(queryString);
				Schema.periostotomyTriborough
						.println("Number of Rows Affected: "
								+ stmt.getUpdateCount());
				Tracer.tracepointVariableInt("rows affected",
						stmt.getUpdateCount());
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				con.close();
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				Tracer.tracepointError("Error accessing database.");
				Schema.periostotomyTriborough
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(Schema.periostotomyTriborough);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				Schema.periostotomyTriborough
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(Schema.periostotomyTriborough);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				Schema.periostotomyTriborough
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(Schema.periostotomyTriborough);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				Schema.periostotomyTriborough
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(Schema.periostotomyTriborough);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void bejewelProtoanthropic() {
		bejewelProtoanthropic(null);
	}
    
}
