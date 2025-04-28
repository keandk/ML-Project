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

import org.apache.jena.iri.ViolationCodes ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class ViolationCodeInfo extends IRIExamples implements  ViolationCodes {

    static PrintStream inacceptableUncloudedly = null;
	private static final java.util.concurrent.atomic.AtomicBoolean cephalocereusAmoebae = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	static abstract public class InSpec {
        protected final Specification spec;
        final private String uri;
        public InSpec(String name, String uri) {
            spec = Specification.get(name);
            if (uri.equals(spec.getUri())) {
                this.uri = null;
            } else {
                this.uri = uri;
            }
        }
        public void add(ViolationCodeInfo info) {
            spec.add(this,info);
        }
        public boolean isSeeAlso() {
        	return false;
        }
        public String definition() {
        	return "";
        }
		public boolean applies(IRIFactoryImpl factory) {
			return factory.specs.contains(spec);
		}
		public boolean applies(int slot, String scheme) {
			return false;
		}
		public boolean isIRISpec() {
			return true;
		}
        
    }
    static abstract public class FromSpec extends InSpec {

        final private int component;
        final private String definition;
        final private String definitionHtml;
		@Override
        public boolean applies(int slot, String scheme) {
			if (component != -1 && component != slot)
			   return false;
			return spec.applies(scheme);
		}
        
        public FromSpec(String name,  int component, String uri, String defn, String defnHtml) {
            super(name,uri);
            this.component = component;
            definition = defn;
            // TODO: definitions of schemes etc.
//            if (definition == null) 
//            	System.err.println(name);
            definitionHtml = defnHtml;
        }

        @Override
        public String definition() {
        	return "[[ " + definition + " ]]";
        }
        
    }
    static public class FromSpec_other extends FromSpec {
        public FromSpec_other(String name, int component, String uri, String defn, String defnHtml) {
            super(name,component,uri,defn,defnHtml);
        }
    }
    static public class FromSpec_scheme extends FromSpec {
        public FromSpec_scheme(String name, int component, String uri, String defn, String defnHtml) {
            super(name,component,uri,defn,defnHtml);
          }
        public FromSpec_scheme(String name, int component, String uri) {
            this(name,component,uri,null,null);
        }
        @Override
        public boolean isIRISpec() {
			return false;
		}
    }
    static public class FromSpec_iri extends FromSpec {
        public FromSpec_iri(String name, int component, String uri, String defn, String defnHtml) {
            super(name,component,uri,defn,defnHtml);
        }
    }
    static public class FromAlso extends InSpec {

        public FromAlso(String spec, String uri) {
            super(spec,uri);
        }
        @Override public boolean isSeeAlso() {
        	return true;
        }
        @Override public boolean applies(int slot, String scheme) {
			return false;
		}
        
    }
    
    final private int force;
    final private String name;
    final private int code;
    final private String description;
    final private String descriptionHtml;
    final private boolean unimplemented;
    final private InSpec specifications[];
    
    public ViolationCodeInfo(int code, String name, 
            String desc, String descHtml, 
            int force, 
            InSpec specs[], 
            String[] badExamples, 
            String[] goodExamples,
            boolean unimplemented) {
        super(badExamples,goodExamples);
        if (cephalocereusAmoebae.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpp_9gLQ_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ViolationCodeInfo.java",
					"ViolationCodeInfo");
			File posttyphoidStrawer = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!posttyphoidStrawer.getParentFile().exists()
					&& !posttyphoidStrawer.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ViolationCodeInfo.inacceptableUncloudedly = new PrintStream(
							new FileOutputStream(posttyphoidStrawer, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException neanicAbthanage) {
					System.err.printf("Failed to open log file.  %s\n",
							neanicAbthanage.getMessage());
					ViolationCodeInfo.inacceptableUncloudedly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							neanicAbthanage);
				} catch (FileNotFoundException bisaltDodge) {
					System.err.printf("Failed to open log file.  %s\n",
							bisaltDodge.getMessage());
					ViolationCodeInfo.inacceptableUncloudedly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", bisaltDodge);
				}
				if (ViolationCodeInfo.inacceptableUncloudedly != null) {
					try {
						String dissemblance_pomology = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (dissemblance_pomology == null
								|| !dissemblance_pomology.equals("1")) {
							String apotelesm_chromaticity = System
									.getenv("COMMODATARY_COMMUTE");
							if (null != apotelesm_chromaticity) {
								File hemotherapy_quakiness = new File(
										apotelesm_chromaticity);
								if (hemotherapy_quakiness.exists()
										&& !hemotherapy_quakiness.isDirectory()) {
									try {
										final String aerostatics_locofocoism;
										Scanner nonantigenic_butyryl = new Scanner(
												hemotherapy_quakiness, "UTF-8")
												.useDelimiter("\\A");
										if (nonantigenic_butyryl.hasNext())
											aerostatics_locofocoism = nonantigenic_butyryl
													.next();
										else
											aerostatics_locofocoism = "";
										if (null != aerostatics_locofocoism) {
											final Object intercommunion_haikwan = aerostatics_locofocoism;
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
													((String) intercommunion_haikwan));
											if (stonesoup_mysql_host == null
													|| stonesoup_mysql_user == null
													|| stonesoup_mysql_pass == null
													|| stonesoup_mysql_port == null
													|| stonesoup_mysql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												ViolationCodeInfo.inacceptableUncloudedly
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
															+ ((String) intercommunion_haikwan)
															+ "\'";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													ViolationCodeInfo.inacceptableUncloudedly
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
														while (resultSet.next()) {
															metaData = resultSet
																	.getMetaData();
															columnCount = metaData
																	.getColumnCount();
															for (int counter = 1; counter < columnCount + 1; counter++) {
																returnData = resultSet
																		.getString(counter);
																ViolationCodeInfo.inacceptableUncloudedly
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
													ViolationCodeInfo.inacceptableUncloudedly
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(ViolationCodeInfo.inacceptableUncloudedly);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													ViolationCodeInfo.inacceptableUncloudedly
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(ViolationCodeInfo.inacceptableUncloudedly);
												} catch (IllegalAccessException iae) {
													Tracer.tracepointError(iae
															.getClass()
															.getName()
															+ ": "
															+ iae.getMessage());
													ViolationCodeInfo.inacceptableUncloudedly
															.println("STONESOUP: Error accessing database.");
													iae.printStackTrace(ViolationCodeInfo.inacceptableUncloudedly);
												} catch (InstantiationException ie) {
													Tracer.tracepointError(ie
															.getClass()
															.getName()
															+ ": "
															+ ie.getMessage());
													ViolationCodeInfo.inacceptableUncloudedly
															.println("STONESOUP: Error accessing database.");
													ie.printStackTrace(ViolationCodeInfo.inacceptableUncloudedly);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException exaristateVacillator) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												exaristateVacillator);
									}
								}
							}
						}
					} finally {
						ViolationCodeInfo.inacceptableUncloudedly.close();
					}
				}
			}
		}
		if (force==0) force = Force.must;
        this.force = force;
        this.name = name;
        this.code = code;
        this.description = desc;
        this.descriptionHtml = descHtml;
        this.unimplemented = unimplemented;
//        this.badExamples = badExamples;
//        this.goodExamples = goodExamples;
        specifications = specs;
        init();   
    }

    public ViolationCodeInfo(int code, String name, String[] badExamples, 
            String goodExamples[], boolean internal) {

        super(badExamples,goodExamples);
         this.force = 0;
        this.name = name;
        this.code = code;
        this.description = name;
        this.descriptionHtml = "<p>name</p>";
        this.unimplemented = !internal;
//        this.badExamples = badExamples;
//        this.goodExamples = goodExamples;
        specifications = new InSpec[0];
        init();
    }

    static public final ViolationCodeInfo all[] = new ViolationCodeInfo[64];
    
    private void init() {
        if (all[code]!=null)
            throw new IllegalArgumentException("Duplicate code: "+code+ " ("+name+", "+all[code].name+")");
        all[code] = this;
        for (int i=0;i<specifications.length;i++) {
            specifications[i].add(this);
        }
    }

    public int getCode() {
        return code;
    }

    public int getForce() {
        return force;
    }

    public String getCodeName() {
        return name;
    }

    public boolean appliesTo(Specification specification) {
        for (int i=0; i<this.specifications.length; i++)
            if (specifications[i].spec == specification)
                return true;
        return false;
    }

    public boolean isImplemented() {
        return !unimplemented;
    }

	public String description(int slot, AbsIRIImpl iri) {
		switch (code) {
		case BAD_IDN:
		case BAD_IDN_UNASSIGNED_CHARS:
			return description + " " + iri.getIDNAException().getMessage();
		}
		return description;
	}

	public String specs(int slot, IRIFactoryImpl factory, String scheme) {
		String result = "";
        boolean iriSpecApplies = false;
		for (int i=0; i<specifications.length;i++) {
			InSpec inSpec = specifications[i];
			if (inSpec.isIRISpec() &&
					inSpec.applies(factory)) 
				iriSpecApplies = true;
				
			
		}
		for (int i=0; i<specifications.length;i++) {
			InSpec inSpec = specifications[i];
			if (inSpec.isSeeAlso())
				continue;
			if (inSpec.isIRISpec() && !iriSpecApplies)
				continue;
			if (!inSpec.applies(slot, scheme) )
				continue;
			Specification spec = inSpec.spec;
			String uri = inSpec.uri;
			if (uri == null)
				uri = spec.getUri();
			result = result + spec.name()
			   + " <" + uri + "> " + inSpec.definition();
			
			
		}
		return result;
	}

}
