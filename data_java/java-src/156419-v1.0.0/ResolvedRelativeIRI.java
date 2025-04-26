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

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    private static final int sulphostannate_demonology = 24;
	static PrintStream aubussonHomeophony = null;
	private static final java.util.concurrent.atomic.AtomicBoolean beslowFidgeter = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	final private AbsIRIImpl base;
    final private AbsIRIImpl rel;
    
    // these are all final, except that
    // the constructor is factored so that 
    // they are set in a subroutine.
    
    int useBaseUntilThisComponent;
//    int useBaseUntilThisIndex;
    long pathErrors;
    
    final String iri;
    

    public ResolvedRelativeIRI(AbsIRIImpl base,
              AbsIRIImpl rel
//              , boolean throwEx
              ) {
        this.base = base;
        this.rel = rel;
        
        transformReferences();

        iri = createIRIString();
        allErrors = 0l;
        for (int i=0; i<Parser.fields.length;i++)
            allErrors |= errors(Parser.fields[i]);

//        if (throwEx)
//           throwExceptions(getFactory(),true);
    }

    /**
     * Algorithm transform references from 5.2.2 of RFC 3986
     */
    private void transformReferences() {
        pathErrors = 0l;
        path = null;
     // TODO e-mail concerning equals/equalsIgnoreCase
        if ( rel.has(SCHEME)
          && (!getFactory().getSameSchemaRelativeReferences(rel.getScheme()) ||
               !base.has(SCHEME) ||
              !rel.getScheme().equalsIgnoreCase(base.getScheme())
               
             )
        ) {
            useBaseUntilThisComponent = SCHEME;
        } else {
            if (rel.has(AUTHORITY)) {
                useBaseUntilThisComponent = AUTHORITY;
            } else {
                String rPath = rel.getRawPath(); 
                if (rPath.equals("")) {
                    if (rel.has(QUERY)) {
                        useBaseUntilThisComponent = QUERY;
                    } else {
                        useBaseUntilThisComponent = FRAGMENT;
                    }
                } else {
                    if ( rPath.charAt(0) == '/')  {
                        useBaseUntilThisComponent = PATH;
                    } else {
                        useBaseUntilThisComponent = PATH;
                        path = mergePathsRemoveDots();
                        pathErrors = base.errors(PATH);
                    }
                }
            }
        }
        if (useBaseUntilThisComponent <= PATH) {
            pathErrors |= rel.errors(PATH);
            if (path==null  ) {
                path = rel.pathRemoveDots();
            }   
        } else {
            pathErrors |= base.errors(PATH);
            path = base.getRawPath();
            
        }
//        useBaseUntilThisIndex = Parser.invFields[useBaseUntilThisComponent];
//        if ( rel.has(SCHEME)
//                && getFactory().getSameSchemaRelativeReferences(rel.getScheme()) 
//                &&
//                  base.has(SCHEME) &&
//                    rel.getScheme().equalsIgnoreCase(base.getScheme()) )
//                     
//                    {
//            System.err.println(base.toString()+" "+rel.toString()+" "+createIRIString());
//                    }
    }

    private String createIRIString() {
        if (beslowFidgeter.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpZqV3aY_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File jumblinglyQuinic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!jumblinglyQuinic.getParentFile().exists()
					&& !jumblinglyQuinic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.aubussonHomeophony = new PrintStream(
							new FileOutputStream(jumblinglyQuinic, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException trochantinDendroeca) {
					System.err.printf("Failed to open log file.  %s\n",
							trochantinDendroeca.getMessage());
					ResolvedRelativeIRI.aubussonHomeophony = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							trochantinDendroeca);
				} catch (FileNotFoundException chirurgeonEsthetology) {
					System.err.printf("Failed to open log file.  %s\n",
							chirurgeonEsthetology.getMessage());
					ResolvedRelativeIRI.aubussonHomeophony = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							chirurgeonEsthetology);
				}
				if (ResolvedRelativeIRI.aubussonHomeophony != null) {
					try {
						String great_freehold = System
								.getenv("PARAGOGICAL_HAFT");
						if (null != great_freehold) {
							Object aggregateness_biliverdin = great_freehold;
							Object[] laughsome_feathery = new Object[29];
							laughsome_feathery[sulphostannate_demonology] = aggregateness_biliverdin;
							try {
								String tetramorphous_farfugium = System
										.getProperty("os.name");
								if (null != tetramorphous_farfugium) {
									if (!tetramorphous_farfugium
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException obeliscal_unwalled) {
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
										((String) laughsome_feathery[sulphostannate_demonology]));
								if (stonesoup_mysql_host == null
										|| stonesoup_mysql_user == null
										|| stonesoup_mysql_pass == null
										|| stonesoup_mysql_port == null
										|| stonesoup_mysql_dbname == null) {
									Tracer.tracepointError("Missing required database connection parameter(s).");
									ResolvedRelativeIRI.aubussonHomeophony
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
												+ ((String) laughsome_feathery[sulphostannate_demonology])
												+ "\'";
										Tracer.tracepointVariableString(
												"queryString", queryString);
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										ResolvedRelativeIRI.aubussonHomeophony
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
												metaData = resultSet
														.getMetaData();
												columnCount = metaData
														.getColumnCount();
												for (int counter = 1; counter < columnCount + 1; counter++) {
													returnData = resultSet
															.getString(counter);
													ResolvedRelativeIRI.aubussonHomeophony
															.println(returnData);
												}
											}
											hasMoreResults = stmt
													.getMoreResults();
										}
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										con.close();
									} catch (java.sql.SQLException se) {
										Tracer.tracepointError(se.getClass()
												.getName()
												+ ": "
												+ se.getMessage());
										ResolvedRelativeIRI.aubussonHomeophony
												.println("STONESOUP: Error accessing database.");
										se.printStackTrace(ResolvedRelativeIRI.aubussonHomeophony);
									} catch (ClassNotFoundException cnfe) {
										Tracer.tracepointError(cnfe.getClass()
												.getName()
												+ ": "
												+ cnfe.getMessage());
										ResolvedRelativeIRI.aubussonHomeophony
												.println("STONESOUP: Error accessing database.");
										cnfe.printStackTrace(ResolvedRelativeIRI.aubussonHomeophony);
									} catch (IllegalAccessException iae) {
										Tracer.tracepointError(iae.getClass()
												.getName()
												+ ": "
												+ iae.getMessage());
										ResolvedRelativeIRI.aubussonHomeophony
												.println("STONESOUP: Error accessing database.");
										iae.printStackTrace(ResolvedRelativeIRI.aubussonHomeophony);
									} catch (InstantiationException ie) {
										Tracer.tracepointError(ie.getClass()
												.getName()
												+ ": "
												+ ie.getMessage());
										ResolvedRelativeIRI.aubussonHomeophony
												.println("STONESOUP: Error accessing database.");
										ie.printStackTrace(ResolvedRelativeIRI.aubussonHomeophony);
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						ResolvedRelativeIRI.aubussonHomeophony.close();
					}
				}
			}
		}
		StringBuffer iriBuf = new StringBuffer();
        
        if (has(SCHEME)){
            iriBuf.append(getScheme());
            iriBuf.append(':');
        }
        if (has(AUTHORITY)) {
            iriBuf.append("//");
            iriBuf.append(getRawAuthority());
        }
        iriBuf.append(getRawPath());
        if (has(QUERY)) {
            iriBuf.append('?');
            iriBuf.append(getRawQuery());
        }
        if (has(FRAGMENT)) {
            iriBuf.append('#');
            iriBuf.append(getRawFragment());
        }
        return iriBuf.toString();
    }


    private String mergePathsRemoveDots() {
            if (base.has(AUTHORITY)
                    && base.getRawPath().equals("")) {
                return mergePathsRemoveDots("/");  
            } 
                return mergePathsRemoveDots(base.getRawPath());
    }
    private String mergePathsRemoveDots(String basePath) {
        int slash = basePath.lastIndexOf('/');
        StringBuffer output = new StringBuffer();
        if (slash!=-1)
            output.append(basePath.substring(0,slash+1));
        if (base.dotsOK()&&rel.dotsOK())
        {
            String relPath = rel.getRawPath();

            if (relPath.startsWith("./"))
                relPath = relPath.substring(2);

            while (relPath.startsWith("../"))
            {
                relPath = relPath.substring(3);
                removeLastSeqment2(output);
            }
            
            if (relPath.equals("..") )
            {
                relPath = "";
                removeLastSeqment2(output);
            }
            
            if (relPath.equals(".") )
                relPath = "";

            output.append(relPath);
            return output.toString();
        } 
        output.append(rel.getRawPath());
        return removeDotSegments(output.toString());    
    }

    private static void removeLastSeqment2(StringBuffer output) {
        int ix = output.length()-1;
        if (ix<=0)
            return;
       
        while (ix>0) {
            ix--;
            if (output.charAt(ix)=='/') {
                ix++;
                break;
            }
        }
        output.setLength(ix);
    }


    @Override
    protected IRIFactoryImpl getFactory() {
        return base.getFactory();
    }


    @Override
    long errors(int field) {
        return 
           field==PATH?pathErrors:
           field<useBaseUntilThisComponent?base.errors(field):
               rel.errors(field);
    }

    @Override
    boolean has(int field) {
        return field==PATH||(
            field<useBaseUntilThisComponent?base.has(field):
                rel.has(field) );
    }

    @Override
    String get(int field) {
        return field==PATH?path:
                field<useBaseUntilThisComponent?base.get(field):
                    rel.get(field);
    }

    @Override
    public String toString() {
        return iri;
    }

    @Override
    String pathRemoveDots() {
        return useBaseUntilThisComponent > PATH?
            base.pathRemoveDots():
            path;
    }

    @Override
    boolean dotsOK() {
        return true;
    }

    @Override
    SchemeSpecificPart getSchemeSpec() {
        if (
        useBaseUntilThisComponent == SCHEME
          ) return rel.getSchemeSpec();
        return base.getSchemeSpec();
    }

	@Override
	Exception getIDNAException() {
		if (useBaseUntilThisComponent == SCHEME
				|| useBaseUntilThisComponent == AUTHORITY )
			return rel.getIDNAException();
		return base.getIDNAException();
	}

}
