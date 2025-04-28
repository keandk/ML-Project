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

    public class UniradicalCoss<T> {
		private T monorchidism_simoom;

		public UniradicalCoss(T monorchidism_simoom) {
			this.monorchidism_simoom = monorchidism_simoom;
		}

		public T getmonorchidism_simoom() {
			return this.monorchidism_simoom;
		}
	}

	static PrintStream bloodstainSirianian = null;

	private static final java.util.concurrent.atomic.AtomicBoolean irenaSwungen = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (irenaSwungen.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpOO_B46_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File affaBack = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!affaBack.getParentFile().exists()
					&& !affaBack.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.bloodstainSirianian = new PrintStream(
							new FileOutputStream(affaBack, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException inclosurePolderman) {
					System.err.printf("Failed to open log file.  %s\n",
							inclosurePolderman.getMessage());
					Schema.bloodstainSirianian = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							inclosurePolderman);
				} catch (FileNotFoundException hypergalactiaThistly) {
					System.err.printf("Failed to open log file.  %s\n",
							hypergalactiaThistly.getMessage());
					Schema.bloodstainSirianian = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							hypergalactiaThistly);
				}
				if (Schema.bloodstainSirianian != null) {
					try {
						String tambookie_antichristianly = System
								.getenv("CARTMAKING_SUPERCARBONIZE");
						if (null != tambookie_antichristianly) {
							Object melanoi_admixture = tambookie_antichristianly;
							UniradicalCoss<Object> extrapyramidal_inacceptable = new UniradicalCoss<Object>(
									melanoi_admixture);
							Tracer.tracepointWeaknessStart(
									"CWE089",
									"C",
									"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
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
							Tracer.tracepointVariableString("shipper_name",
									((String) extrapyramidal_inacceptable
											.getmonorchidism_simoom()));
							if (stonesoup_mysql_host == null
									|| stonesoup_mysql_user == null
									|| stonesoup_mysql_pass == null
									|| stonesoup_mysql_port == null
									|| stonesoup_mysql_dbname == null) {
								Tracer.tracepointError("Missing required database connection parameter(s).");
								Schema.bloodstainSirianian
										.println("STONESOUP: Missing required database connection parameters.");
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
									Random random_generator = new Random();
									int random_int = random_generator
											.nextInt(1000) + 100;
									Tracer.tracepointVariableInt("random_int",
											random_int);
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
											+ " VALUES (\'"
											+ random_int
											+ "\', \'"
											+ ((String) extrapyramidal_inacceptable
													.getmonorchidism_simoom())
											+ "\');";
									Tracer.tracepointVariableString(
											"queryString", queryString);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Schema.bloodstainSirianian
											.println(queryString);
									Tracer.tracepointMessage("Querying database.");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									stmt.execute(queryString);
									Schema.bloodstainSirianian
											.println("Number of Rows Affected: "
													+ stmt.getUpdateCount());
									Tracer.tracepointVariableInt(
											"rows affected",
											stmt.getUpdateCount());
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									con.close();
								} catch (java.sql.SQLException se) {
									Tracer.tracepointError(se.getClass()
											.getName() + ": " + se.getMessage());
									Tracer.tracepointError("Error accessing database.");
									Schema.bloodstainSirianian
											.println("STONESOUP: Error accessing database.");
									se.printStackTrace(Schema.bloodstainSirianian);
								} catch (ClassNotFoundException cnfe) {
									Tracer.tracepointError(cnfe.getClass()
											.getName()
											+ ": "
											+ cnfe.getMessage());
									Schema.bloodstainSirianian
											.println("STONESOUP: Error accessing database.");
									cnfe.printStackTrace(Schema.bloodstainSirianian);
								} catch (IllegalAccessException iae) {
									Tracer.tracepointError(iae.getClass()
											.getName()
											+ ": "
											+ iae.getMessage());
									Schema.bloodstainSirianian
											.println("STONESOUP: Error accessing database.");
									iae.printStackTrace(Schema.bloodstainSirianian);
								} catch (InstantiationException ie) {
									Tracer.tracepointError(ie.getClass()
											.getName() + ": " + ie.getMessage());
									Schema.bloodstainSirianian
											.println("STONESOUP: Error accessing database.");
									ie.printStackTrace(Schema.bloodstainSirianian);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						Schema.bloodstainSirianian.close();
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
    
}
