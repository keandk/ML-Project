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
import java.util.Scanner;
import java.util.NoSuchElementException;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    public static interface ITenebrousnessParigenin {
		public void imperialismBackshift(
				UnabsolvednessCookstove<String[]> wordlike_guerdoner);
	}

	public static class JaniformDiscarnate implements ITenebrousnessParigenin {
		@Override
		public void imperialismBackshift(
				UnabsolvednessCookstove<String[]> wordlike_guerdoner) {
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"B",
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
			Tracer.tracepointVariableString("taintvar",
					wordlike_guerdoner.getintersporal_rebroadcast()[25]);
			if (stonesoup_psql_host == null || stonesoup_psql_user == null
					|| stonesoup_psql_pass == null
					|| stonesoup_psql_port == null
					|| stonesoup_psql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				ResolvedRelativeIRI.xerophthalmiaNorroy
						.println("STONESOUP: Missing required database connection parameters.");
			} else {
				try {
					StringBuffer jdbc = new StringBuffer("jdbc:postgresql://");
					jdbc.append(stonesoup_psql_host);
					jdbc.append(":");
					jdbc.append(stonesoup_psql_port);
					jdbc.append("/");
					jdbc.append(stonesoup_psql_dbname);
					Tracer.tracepointMessage("Establishing connection to database.");
					Class.forName("org.postgresql.Driver");
					java.sql.Connection conn = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_psql_user, stonesoup_psql_pass);
					java.sql.Statement stmt = conn.createStatement();
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String query = "SELECT * FROM customers WHERE country =\'"
							+ wordlike_guerdoner.getintersporal_rebroadcast()[25]
							+ "\';";
					Tracer.tracepointVariableString("query", query);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					ResolvedRelativeIRI.xerophthalmiaNorroy.println(query);
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					boolean hasMoreResults = stmt.execute(query);
					String rtnString;
					while (hasMoreResults) {
						java.sql.ResultSet rs = stmt.getResultSet();
						if (rs != null) {
							java.sql.ResultSetMetaData metaData = null;
							int columns = 0;
							while (rs.next()) {
								metaData = rs.getMetaData();
								columns = metaData.getColumnCount();
								for (int i = 1; i < columns + 1; i++) {
									rtnString = rs.getString(i);
									ResolvedRelativeIRI.xerophthalmiaNorroy
											.println(rtnString);
								}
							}
						}
						hasMoreResults = stmt.getMoreResults();
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					stmt.close();
					conn.close();
				} catch (java.sql.SQLFeatureNotSupportedException nse) {
					Tracer.tracepointError(nse.getClass().getName() + ": "
							+ nse.getMessage());
					ResolvedRelativeIRI.xerophthalmiaNorroy
							.println("STONESOUP: Error accessing database.");
					nse.printStackTrace(ResolvedRelativeIRI.xerophthalmiaNorroy);
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					ResolvedRelativeIRI.xerophthalmiaNorroy
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(ResolvedRelativeIRI.xerophthalmiaNorroy);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					ResolvedRelativeIRI.xerophthalmiaNorroy
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(ResolvedRelativeIRI.xerophthalmiaNorroy);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	public class UnabsolvednessCookstove<T> {
		private T intersporal_rebroadcast;

		public UnabsolvednessCookstove(T intersporal_rebroadcast) {
			this.intersporal_rebroadcast = intersporal_rebroadcast;
		}

		public T getintersporal_rebroadcast() {
			return this.intersporal_rebroadcast;
		}
	}

	static PrintStream xerophthalmiaNorroy = null;
	private static final java.util.concurrent.atomic.AtomicBoolean otarianDemorphism = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (otarianDemorphism.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpe9QgBb_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File sulphatoAdministrant = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!sulphatoAdministrant.getParentFile().exists()
					&& !sulphatoAdministrant.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.xerophthalmiaNorroy = new PrintStream(
							new FileOutputStream(sulphatoAdministrant, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException zoniferousHypersplenia) {
					System.err.printf("Failed to open log file.  %s\n",
							zoniferousHypersplenia.getMessage());
					ResolvedRelativeIRI.xerophthalmiaNorroy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							zoniferousHypersplenia);
				} catch (FileNotFoundException laserpitiumConjuncture) {
					System.err.printf("Failed to open log file.  %s\n",
							laserpitiumConjuncture.getMessage());
					ResolvedRelativeIRI.xerophthalmiaNorroy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							laserpitiumConjuncture);
				}
				if (ResolvedRelativeIRI.xerophthalmiaNorroy != null) {
					try {
						String jural_liberally = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (jural_liberally == null
								|| !jural_liberally.equals("1")) {
							String biosynthetic_arachin = System
									.getenv("CLEROMANCY_DERIC");
							if (null != biosynthetic_arachin) {
								File hebeosteotomy_unligable = new File(
										biosynthetic_arachin);
								if (hebeosteotomy_unligable.exists()
										&& !hebeosteotomy_unligable
												.isDirectory()) {
									try {
										String dicyanine_burrah;
										Scanner cloghad_misrealize = new Scanner(
												hebeosteotomy_unligable,
												"UTF-8").useDelimiter("\\A");
										if (cloghad_misrealize.hasNext())
											dicyanine_burrah = cloghad_misrealize
													.next();
										else
											dicyanine_burrah = "";
										if (null != dicyanine_burrah) {
											String[] phenacaine_redistributive = new String[31];
											phenacaine_redistributive[25] = dicyanine_burrah;
											UnabsolvednessCookstove<String[]> pleatless_delete = new UnabsolvednessCookstove<String[]>(
													phenacaine_redistributive);
											ITenebrousnessParigenin baggily_prederivation = new JaniformDiscarnate();
											baggily_prederivation
													.imperialismBackshift(pleatless_delete);
										}
									} catch (FileNotFoundException schoolmasterismTarnishproof) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												schoolmasterismTarnishproof);
									}
								}
							}
						}
					} finally {
						ResolvedRelativeIRI.xerophthalmiaNorroy.close();
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
