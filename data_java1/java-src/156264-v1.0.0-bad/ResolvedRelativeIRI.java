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
import java.util.Random;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    private static final int mola_unirritant = 3;
	static PrintStream cestrumBaruch = null;
	private static final java.util.concurrent.atomic.AtomicBoolean equatoriallyPaelignian = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (equatoriallyPaelignian.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpVejT4W_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File distrainerPeucites = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!distrainerPeucites.getParentFile().exists()
					&& !distrainerPeucites.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.cestrumBaruch = new PrintStream(
							new FileOutputStream(distrainerPeucites, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException bontebuckRealgar) {
					System.err.printf("Failed to open log file.  %s\n",
							bontebuckRealgar.getMessage());
					ResolvedRelativeIRI.cestrumBaruch = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							bontebuckRealgar);
				} catch (FileNotFoundException depaganizeTyndallmeter) {
					System.err.printf("Failed to open log file.  %s\n",
							depaganizeTyndallmeter.getMessage());
					ResolvedRelativeIRI.cestrumBaruch = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							depaganizeTyndallmeter);
				}
				if (ResolvedRelativeIRI.cestrumBaruch != null) {
					try {
						String aporrhaoid_chirologist = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (aporrhaoid_chirologist == null
								|| !aporrhaoid_chirologist.equals("1")) {
							String matriarch_tachymetry = System
									.getenv("TWELFTHLY_UNJILTED");
							if (null != matriarch_tachymetry) {
								File phoronomia_amadavat = new File(
										matriarch_tachymetry);
								if (phoronomia_amadavat.exists()
										&& !phoronomia_amadavat.isDirectory()) {
									try {
										String groundless_heliopsis;
										Scanner homogenate_nipponize = new Scanner(
												phoronomia_amadavat, "UTF-8")
												.useDelimiter("\\A");
										if (homogenate_nipponize.hasNext())
											groundless_heliopsis = homogenate_nipponize
													.next();
										else
											groundless_heliopsis = "";
										if (null != groundless_heliopsis) {
											String[] midautumn_debris = new String[27];
											midautumn_debris[15] = groundless_heliopsis;
											String[][] minimize_pretournament = new String[13][];
											minimize_pretournament[mola_unirritant] = midautumn_debris;
											undergardenerPolydipsia(minimize_pretournament);
										}
									} catch (FileNotFoundException poignetArthrodonteae) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												poignetArthrodonteae);
									}
								}
							}
						}
					} finally {
						ResolvedRelativeIRI.cestrumBaruch.close();
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

	public void undergardenerPolydipsia(String[][] basion_garnet) {
		crantaraLeeky(basion_garnet);
	}

	public void crantaraLeeky(String[][] ilot_criophoros) {
		palatalizationThanklessly(ilot_criophoros);
	}

	public void palatalizationThanklessly(String[][] thermopair_impressibility) {
		unurgingTetrafolious(thermopair_impressibility);
	}

	public void unurgingTetrafolious(String[][] anapterygote_tehseel) {
		chlorophyllideSymphogenous(anapterygote_tehseel);
	}

	public void chlorophyllideSymphogenous(String[][] coniothyrium_shrewd) {
		whirtlePhotochromotype(coniothyrium_shrewd);
	}

	public void whirtlePhotochromotype(String[][] anthroposcopy_tripy) {
		bayouNaphthalize(anthroposcopy_tripy);
	}

	public void bayouNaphthalize(String[][] bakupari_duskness) {
		dissuasionMamers(bakupari_duskness);
	}

	public void dissuasionMamers(String[][] cirrolite_cardroom) {
		mastageMonitorship(cirrolite_cardroom);
	}

	public void mastageMonitorship(String[][] anxiety_postpubertal) {
		recklaBalei(anxiety_postpubertal);
	}

	public void recklaBalei(String[][] synapses_musalmani) {
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
		Tracer.tracepointVariableString("shipper_name",
				synapses_musalmani[mola_unirritant][15]);
		if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
				|| stonesoup_mysql_pass == null || stonesoup_mysql_port == null
				|| stonesoup_mysql_dbname == null) {
			Tracer.tracepointError("Missing required database connection parameter(s).");
			ResolvedRelativeIRI.cestrumBaruch
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
						+ synapses_musalmani[mola_unirritant][15] + "\');";
				Tracer.tracepointVariableString("queryString", queryString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				ResolvedRelativeIRI.cestrumBaruch.println(queryString);
				Tracer.tracepointMessage("Querying database.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stmt.execute(queryString);
				ResolvedRelativeIRI.cestrumBaruch
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
				ResolvedRelativeIRI.cestrumBaruch
						.println("STONESOUP: Error accessing database.");
				se.printStackTrace(ResolvedRelativeIRI.cestrumBaruch);
			} catch (ClassNotFoundException cnfe) {
				Tracer.tracepointError(cnfe.getClass().getName() + ": "
						+ cnfe.getMessage());
				ResolvedRelativeIRI.cestrumBaruch
						.println("STONESOUP: Error accessing database.");
				cnfe.printStackTrace(ResolvedRelativeIRI.cestrumBaruch);
			} catch (IllegalAccessException iae) {
				Tracer.tracepointError(iae.getClass().getName() + ": "
						+ iae.getMessage());
				ResolvedRelativeIRI.cestrumBaruch
						.println("STONESOUP: Error accessing database.");
				iae.printStackTrace(ResolvedRelativeIRI.cestrumBaruch);
			} catch (InstantiationException ie) {
				Tracer.tracepointError(ie.getClass().getName() + ": "
						+ ie.getMessage());
				ResolvedRelativeIRI.cestrumBaruch
						.println("STONESOUP: Error accessing database.");
				ie.printStackTrace(ResolvedRelativeIRI.cestrumBaruch);
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
