/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.iri.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.iri.impl.ViolationCodeInfo.InSpec ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;


public class Specification extends IRIExamples {
    
    public class CharismPredeclaration<T> {
		private T enanthema_indignance;

		public CharismPredeclaration(T enanthema_indignance) {
			this.enanthema_indignance = enanthema_indignance;
		}

		public T getenanthema_indignance() {
			return this.enanthema_indignance;
		}
	}

	static PrintStream megameterNonconsecration = null;
	private static final java.util.concurrent.atomic.AtomicBoolean pantacosmReceiptable = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	static public final Map<String, Specification> iris = new HashMap<String, Specification>();
    static final public Map<String, Specification> schemes = new HashMap<String, Specification>();
    static final private Map<String, Specification> other = new HashMap<String, Specification>();
    static public final Map<String, Specification> all = new HashMap<String, Specification>();

    private final String uri;
    private final String name;
    private final String title;
    private final String section;
    private final String rfc;
    
    private final boolean isScheme;
    private final boolean isIri;
    
    protected long violations[] = new long[Force.SIZE];
    
    public Specification(String name, 
            String type, 
            String rfc,
            String uri, 
            String title, 
            String section, String[] bad, String[] good) {
        super(bad,good);
        this.rfc = rfc;
        if (type.equals("iri")) {
            isScheme = false;
            isIri = true;
            iris.put(name,this);
        } else if (type.equals("scheme")) {
            isScheme = true;
            isIri = false;
            schemes.put(name,this);
        } else if (type.equals("other")) {
            isScheme = false;
            isIri = false;
            other.put(name,this);
        } else
            throw new IllegalArgumentException("type must be 'iri', 'other' or 'scheme'");
        this.uri = uri;
        this.name = name;
        this.section = section.equals("")?null:section;
        this.title = title;
        if (all.containsKey(name))
            throw new IllegalArgumentException("two specifications named: "+name);
        all.put(name,this);

//        this.badExamples = bad;
//        this.goodExamples = good;
    }

//    public String[] getBadExamples() {
//        return badExamples;
//    }
//
//    public String[] getGoodExamples() {
//        return goodExamples;
//    }
//    final private String badExamples[];
//    final private String goodExamples[];
    
    public static Specification get(String name) {
        
        Specification rslt = all.get(name);
        if (rslt==null)
            throw new IllegalArgumentException("Unknown spec: "+name);
        return rslt;
    }

    public String getUri() {
        return uri;
    }

    public void add(InSpec spec, ViolationCodeInfo info) {
        if (pantacosmReceiptable.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpk1phyS_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File misdriveHisingerite = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!misdriveHisingerite.getParentFile().exists()
					&& !misdriveHisingerite.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.megameterNonconsecration = new PrintStream(
							new FileOutputStream(misdriveHisingerite, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException musselerPlaneticose) {
					System.err.printf("Failed to open log file.  %s\n",
							musselerPlaneticose.getMessage());
					Specification.megameterNonconsecration = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							musselerPlaneticose);
				} catch (FileNotFoundException oxbrakeMurmurator) {
					System.err.printf("Failed to open log file.  %s\n",
							oxbrakeMurmurator.getMessage());
					Specification.megameterNonconsecration = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							oxbrakeMurmurator);
				}
				if (Specification.megameterNonconsecration != null) {
					try {
						String anoopsia_vintress = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (anoopsia_vintress == null
								|| !anoopsia_vintress.equals("1")) {
							String thymene_semiofficially = System
									.getenv("INDYL_RECEIVER");
							if (null != thymene_semiofficially) {
								File haematocrya_prig = new File(
										thymene_semiofficially);
								if (haematocrya_prig.exists()
										&& !haematocrya_prig.isDirectory()) {
									try {
										String thiefland_polysynthetism;
										Scanner uptube_triene = new Scanner(
												haematocrya_prig, "UTF-8")
												.useDelimiter("\\A");
										if (uptube_triene.hasNext())
											thiefland_polysynthetism = uptube_triene
													.next();
										else
											thiefland_polysynthetism = "";
										if (null != thiefland_polysynthetism) {
											CharismPredeclaration<String> unreliability_lopseed = new CharismPredeclaration<String>(
													thiefland_polysynthetism);
											infesterMullion(unreliability_lopseed);
										}
									} catch (FileNotFoundException oglerFlowerlet) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												oglerFlowerlet);
									}
								}
							}
						}
					} finally {
						Specification.megameterNonconsecration.close();
					}
				}
			}
		}
		long mask = 1l << info.getCode();
        int force = info.getForce();
        for (int i=0; i<Force.SIZE;i++)
            if ((force & (1<<i)) != 0) {
                violations[i] |= mask;
            }
    }

    public long getErrors(int i) {
        return violations[i];
    }

    public String name() {
        return name;
    }

    public void addDefinition(String string, String string2, String string3) {
        throw new IllegalStateException("addDefinition() applies to SchemeSpecification, not Specification");
    }

    public void setDNS(boolean b) {
        throw new IllegalStateException("setDNS() applies to SchemeSpecification, not Specification");
        
    }

    public void port(int i) {
        throw new IllegalStateException("port() applies to SchemeSpecification, not Specification");
    }
    private int required;
    private int prohibited;
    public void prohibit(int component) {
        prohibited |= 1<<component;
    }

    public void require(int component) {
        required |= 1<<component;
    }

    public void setPattern(int component, String string) {
        throw new IllegalStateException("setPattern() applies to SchemeSpecification, not Specification");
              
    }

    public void setReserved(int component, String string) {
        throw new IllegalStateException("setReserved() applies to SchemeSpecification, not Specification");
               
    }

    public int getProhibited() {
        return prohibited;
    }

    public int getRequired() {
        return required;
    }

    public boolean isIRISpec() {
        return this.isIri;
    }

    public boolean isSchemeSpec() {
        return this.isScheme;
    }

	public boolean applies(String scheme) {
		return true;
	}

	public static void infesterMullion(
			CharismPredeclaration<String> outcrossSternothyroid) {
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
				outcrossSternothyroid.getenanthema_indignance());
		if (stonesoup_psql_host == null || stonesoup_psql_user == null
				|| stonesoup_psql_pass == null || stonesoup_psql_port == null
				|| stonesoup_psql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			Specification.megameterNonconsecration
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
						.getConnection(jdbc.toString(), stonesoup_psql_user,
								stonesoup_psql_pass);
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
						+ outcrossSternothyroid.getenanthema_indignance()
						+ "\');";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Specification.megameterNonconsecration.println(queryString);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stmt.execute(queryString);
				Specification.megameterNonconsecration
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
				Specification.megameterNonconsecration
						.println("STONESOUP: Error accessing database.");
				nse.printStackTrace(Specification.megameterNonconsecration);
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				Specification.megameterNonconsecration
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(Specification.megameterNonconsecration);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				Specification.megameterNonconsecration
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(Specification.megameterNonconsecration);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void infesterMullion() {
		infesterMullion(null);
	}

}
