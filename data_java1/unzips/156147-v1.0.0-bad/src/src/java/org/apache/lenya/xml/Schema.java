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

/**
 * Validation schema.
 */
public class Schema {

    static PrintStream conductivityTrogonoid = null;

	public void tachysystoleCribrate(int priscillian_morosauroid,
			String stimulator_palaearctic) {
		if (priscillian_morosauroid > 10) {
			tachysystoleCribrate(priscillian_morosauroid++,
					stimulator_palaearctic);
		}
		Tracer.tracepointWeaknessStart(
				"CWE089",
				"A",
				"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
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
		Tracer.tracepointVariableString("country_name", stimulator_palaearctic);
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			Schema.conductivityTrogonoid
					.println("STONESOUP: Missing required database connection parameter(s).");
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
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				String queryString = "SELECT * FROM Customers WHERE "
						+ "Country=\'" + stimulator_palaearctic + "\'";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Schema.conductivityTrogonoid.println(queryString);
				java.sql.ResultSet resultSet = null;
				java.sql.ResultSetMetaData metaData = null;
				int columnCount = 0;
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				boolean hasMoreResults = stmt.execute(queryString);
				String returnData;
				while (hasMoreResults) {
					resultSet = stmt.getResultSet();
					while (resultSet.next()) {
						metaData = resultSet.getMetaData();
						columnCount = metaData.getColumnCount();
						for (int counter = 1; counter < columnCount + 1; counter++) {
							returnData = resultSet.getString(counter);
							Schema.conductivityTrogonoid.println(returnData);
						}
					}
					hasMoreResults = stmt.getMoreResults();
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				con.close();
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				Schema.conductivityTrogonoid
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(Schema.conductivityTrogonoid);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				Schema.conductivityTrogonoid
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(Schema.conductivityTrogonoid);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				Schema.conductivityTrogonoid
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(Schema.conductivityTrogonoid);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				Schema.conductivityTrogonoid
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(Schema.conductivityTrogonoid);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean surreverencePraxiology = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (surreverencePraxiology.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpDeGoxU_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File gewgawStupid = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!gewgawStupid.getParentFile().exists()
					&& !gewgawStupid.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.conductivityTrogonoid = new PrintStream(
							new FileOutputStream(gewgawStupid, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException spindlageColk) {
					System.err.printf("Failed to open log file.  %s\n",
							spindlageColk.getMessage());
					Schema.conductivityTrogonoid = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							spindlageColk);
				} catch (FileNotFoundException deprivateUbiquist) {
					System.err.printf("Failed to open log file.  %s\n",
							deprivateUbiquist.getMessage());
					Schema.conductivityTrogonoid = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							deprivateUbiquist);
				}
				if (Schema.conductivityTrogonoid != null) {
					try {
						String odontogen_agatoid = System
								.getenv("POSTNARIS_CATHARIZATION");
						if (null != odontogen_agatoid) {
							iliocostalisContradebt(3, null, null, null,
									odontogen_agatoid, null, null);
						}
					} finally {
						Schema.conductivityTrogonoid.close();
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

	public void iliocostalisContradebt(int downstreamSpermologist,
			String... slidometerDisaster) {
		String unconcealedTimne = null;
		int snowplowCosmetology = 0;
		for (snowplowCosmetology = 0; snowplowCosmetology < slidometerDisaster.length; snowplowCosmetology++) {
			if (snowplowCosmetology == downstreamSpermologist)
				unconcealedTimne = slidometerDisaster[snowplowCosmetology];
		}
		int columbine_hierodule = 0;
		tachysystoleCribrate(columbine_hierodule, unconcealedTimne);
	}
    
}
