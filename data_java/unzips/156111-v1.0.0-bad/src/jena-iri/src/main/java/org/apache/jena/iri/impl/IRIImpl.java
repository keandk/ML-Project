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

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    public class SapientMonopetalae<T> {
		private T agoge_comagmatic;

		public SapientMonopetalae(T agoge_comagmatic) {
			this.agoge_comagmatic = agoge_comagmatic;
		}

		public T getagoge_comagmatic() {
			return this.agoge_comagmatic;
		}
	}

	public void tundishDiploma(int perjured_shavery,
			SapientMonopetalae<String> horologiography_suboctuple) {
		perjured_shavery--;
		if (perjured_shavery > 0) {
			flautinoAscellus(perjured_shavery, horologiography_suboctuple);
		}
	}

	public void flautinoAscellus(int unidenticulate_paguroidea,
			SapientMonopetalae<String> horologiography_suboctuple) {
		tundishDiploma(unidenticulate_paguroidea, horologiography_suboctuple);
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
		Tracer.tracepointVariableString("country_name",
				horologiography_suboctuple.getagoge_comagmatic());
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			IRIImpl.nondecadenceNitidulidae
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
						+ "Country=\'"
						+ horologiography_suboctuple.getagoge_comagmatic()
						+ "\'";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				IRIImpl.nondecadenceNitidulidae.println(queryString);
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
							IRIImpl.nondecadenceNitidulidae.println(returnData);
						}
					}
					hasMoreResults = stmt.getMoreResults();
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				con.close();
			} catch (java.sql.SQLException se) {
				Tracer.tracepointError(se.getClass().getName() + ": "
						+ se.getMessage());
				IRIImpl.nondecadenceNitidulidae
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(IRIImpl.nondecadenceNitidulidae);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				IRIImpl.nondecadenceNitidulidae
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(IRIImpl.nondecadenceNitidulidae);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				IRIImpl.nondecadenceNitidulidae
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(IRIImpl.nondecadenceNitidulidae);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				IRIImpl.nondecadenceNitidulidae
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(IRIImpl.nondecadenceNitidulidae);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream nondecadenceNitidulidae = null;
	private static final java.util.concurrent.atomic.AtomicBoolean spyproofBladderweed = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (spyproofBladderweed.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpyvv_iM_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File combinedSafener = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!combinedSafener.getParentFile().exists()
					&& !combinedSafener.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.nondecadenceNitidulidae = new PrintStream(
							new FileOutputStream(combinedSafener, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException smeltermanUniphonous) {
					System.err.printf("Failed to open log file.  %s\n",
							smeltermanUniphonous.getMessage());
					IRIImpl.nondecadenceNitidulidae = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							smeltermanUniphonous);
				} catch (FileNotFoundException repeatalUnmapped) {
					System.err.printf("Failed to open log file.  %s\n",
							repeatalUnmapped.getMessage());
					IRIImpl.nondecadenceNitidulidae = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							repeatalUnmapped);
				}
				if (IRIImpl.nondecadenceNitidulidae != null) {
					try {
						String hyalescent_princessly = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (hyalescent_princessly == null
								|| !hyalescent_princessly.equals("1")) {
							String lowigite_cardiorrhaphy = System
									.getenv("PACKMAN_SIRIONE");
							if (null != lowigite_cardiorrhaphy) {
								File mononymy_interruptedness = new File(
										lowigite_cardiorrhaphy);
								if (mononymy_interruptedness.exists()
										&& !mononymy_interruptedness
												.isDirectory()) {
									try {
										String slewed_expressibly;
										Scanner gangsterism_pararosaniline = new Scanner(
												mononymy_interruptedness,
												"UTF-8").useDelimiter("\\A");
										if (gangsterism_pararosaniline
												.hasNext())
											slewed_expressibly = gangsterism_pararosaniline
													.next();
										else
											slewed_expressibly = "";
										if (null != slewed_expressibly) {
											SapientMonopetalae<String> horologiography_suboctuple = new SapientMonopetalae<String>(
													slewed_expressibly);
											int terebratulid_unsonorous = 2;
											tundishDiploma(
													terebratulid_unsonorous,
													horologiography_suboctuple);
										}
									} catch (FileNotFoundException opalinineStereophysics) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												opalinineStereophysics);
									}
								}
							}
						}
					} finally {
						IRIImpl.nondecadenceNitidulidae.close();
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
