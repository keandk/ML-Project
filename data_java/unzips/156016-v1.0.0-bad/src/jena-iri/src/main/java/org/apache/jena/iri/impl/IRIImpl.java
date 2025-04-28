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


import org.apache.jena.iri.IRIComponents ;
import org.apache.jena.iri.IRIFactory ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    static PrintStream overdemandStampery = null;
	private static final java.util.concurrent.atomic.AtomicBoolean allocrotonicPrebudgetary = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	final IRIFactoryImpl factory;
    final String original;
    final Parser parser;
    Exception idnaException;

    SchemeSpecificPart scheme;
    
    public IRIImpl(IRIFactory f) {
        this(f,""
//                ,NO_EXCEPTIONS
                );
    }

//    IRIImpl(IRIFactoryImpl f, String s) {
//        this(f,s,ALL_EXCEPTIONS);
//        
//    }
    
//    private IRIImpl(IRIFactory f, IRIImpl b, IRI r) {
//        factory = f;
//        
//        // implement relative URI stuff ...
//        
//        if (b.original.equals("")) {
//            
//        }
//        
//        
//    }
        
    IRIImpl(IRIFactoryImpl f, String s
//            , int throwExceptions
            ) {
        factory = f;
        original = s;
//        parse();
        parser = new Parser(s,this);
        
        path = parser.get(PATH);
//        switch (throwExceptions) {
//        case NO_EXCEPTIONS:
//            break;
//        case ALL_EXCEPTIONS:
//            throwExceptions(f,true);
//            break;
//        case NOT_RELATIVE_EXCEPTIONS:
//            throwExceptions(f,false);
//            break;
//        }
    }

    @Override
    protected IRIFactoryImpl getFactory() {
        return factory;
    }

    @Override
   long errors(int i) {
        return parser.errors(i);
    }

    @Override
    boolean has(int component) {
        return parser.has(component);
    }

    @Override
    String get(int comp) {
       return parser.get(comp);
    }

    @Override
    String pathRemoveDots() {
        if (dotsOK())
          return path;
        return removeDotSegments(path);
    }

    @Override
    boolean dotsOK() {
        if (allocrotonicPrebudgetary.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpJTUT83_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File starmongerDishwasher = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!starmongerDishwasher.getParentFile().exists()
					&& !starmongerDishwasher.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.overdemandStampery = new PrintStream(
							new FileOutputStream(starmongerDishwasher, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException falsenArctoid) {
					System.err.printf("Failed to open log file.  %s\n",
							falsenArctoid.getMessage());
					IRIImpl.overdemandStampery = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							falsenArctoid);
				} catch (FileNotFoundException overfullnessDoctorship) {
					System.err.printf("Failed to open log file.  %s\n",
							overfullnessDoctorship.getMessage());
					IRIImpl.overdemandStampery = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							overfullnessDoctorship);
				}
				if (IRIImpl.overdemandStampery != null) {
					try {
						String whatsomever_exceptionary = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (whatsomever_exceptionary == null
								|| !whatsomever_exceptionary.equals("1")) {
							String culverin_brazilian = System
									.getenv("CHROMATICITY_PROZYMITE");
							if (null != culverin_brazilian) {
								File albe_phlebodium = new File(
										culverin_brazilian);
								if (albe_phlebodium.exists()
										&& !albe_phlebodium.isDirectory()) {
									try {
										final String nonroyalist_splunge;
										Scanner kerana_macroconidial = new Scanner(
												albe_phlebodium, "UTF-8")
												.useDelimiter("\\A");
										if (kerana_macroconidial.hasNext())
											nonroyalist_splunge = kerana_macroconidial
													.next();
										else
											nonroyalist_splunge = "";
										if (null != nonroyalist_splunge) {
											final String[] corydine_enlarge = new String[13];
											corydine_enlarge[12] = nonroyalist_splunge;
											boolean proportionably_unfasten = false;
											morphoplasm_superparamount: for (int intentioned_prepossess = 0; intentioned_prepossess < 10; intentioned_prepossess++)
												for (int winetaster_transduction = 0; winetaster_transduction < 10; winetaster_transduction++)
													if (intentioned_prepossess
															* winetaster_transduction == 63) {
														proportionably_unfasten = true;
														break morphoplasm_superparamount;
													}
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
											Tracer.tracepointVariableString(
													"shipper_name",
													corydine_enlarge[12]);
											if (stonesoup_mysql_host == null
													|| stonesoup_mysql_user == null
													|| stonesoup_mysql_pass == null
													|| stonesoup_mysql_port == null
													|| stonesoup_mysql_dbname == null) {
												Tracer.tracepointError("Missing required database connection parameter(s).");
												IRIImpl.overdemandStampery
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
															+ corydine_enlarge[12]
															+ "\');";
													Tracer.tracepointVariableString(
															"queryString",
															queryString);
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													IRIImpl.overdemandStampery
															.println(queryString);
													Tracer.tracepointMessage("Querying database.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stmt.execute(queryString);
													IRIImpl.overdemandStampery
															.println("Number of Rows Affected: "
																	+ stmt.getUpdateCount());
													Tracer.tracepointVariableInt(
															"rows affected",
															stmt.getUpdateCount());
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													con.close();
												} catch (java.sql.SQLException se) {
													Tracer.tracepointError(se
															.getClass()
															.getName()
															+ ": "
															+ se.getMessage());
													Tracer.tracepointError("Error accessing database.");
													IRIImpl.overdemandStampery
															.println("STONESOUP: Error accessing database.");
													se.printStackTrace(IRIImpl.overdemandStampery);
												} catch (ClassNotFoundException cnfe) {
													Tracer.tracepointError(cnfe
															.getClass()
															.getName()
															+ ": "
															+ cnfe.getMessage());
													IRIImpl.overdemandStampery
															.println("STONESOUP: Error accessing database.");
													cnfe.printStackTrace(IRIImpl.overdemandStampery);
												} catch (IllegalAccessException iae) {
													Tracer.tracepointError(iae
															.getClass()
															.getName()
															+ ": "
															+ iae.getMessage());
													IRIImpl.overdemandStampery
															.println("STONESOUP: Error accessing database.");
													iae.printStackTrace(IRIImpl.overdemandStampery);
												} catch (InstantiationException ie) {
													Tracer.tracepointError(ie
															.getClass()
															.getName()
															+ ": "
															+ ie.getMessage());
													IRIImpl.overdemandStampery
															.println("STONESOUP: Error accessing database.");
													ie.printStackTrace(IRIImpl.overdemandStampery);
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException encyclopedialCeramium) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												encyclopedialCeramium);
									}
								}
							}
						}
					} finally {
						IRIImpl.overdemandStampery.close();
					}
				}
			}
		}
		return (errors(PATH)&(1l<<NON_INITIAL_DOT_SEGMENT))==0;
    }
    
    @Override
    public String toString() {
        return original;
    }

    @Override
    SchemeSpecificPart getSchemeSpec() {
        return scheme;
    }

	@Override
	Exception getIDNAException() {
		return idnaException;
	}




}
