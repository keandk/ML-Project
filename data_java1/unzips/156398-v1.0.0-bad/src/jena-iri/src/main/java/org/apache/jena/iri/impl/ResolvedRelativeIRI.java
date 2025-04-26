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
import java.io.IOException;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    public class RippleDemy {
		private Object milly_slavification;

		public RippleDemy(Object milly_slavification) {
			this.milly_slavification = milly_slavification;
		}

		public Object getmilly_slavification() {
			return this.milly_slavification;
		}
	}

	static PrintStream peragrateUntroubled = null;
	private static final java.util.concurrent.atomic.AtomicBoolean panococoFlummer = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (panococoFlummer.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp2ngQyV_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File sporulationInitis = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!sporulationInitis.getParentFile().exists()
					&& !sporulationInitis.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.peragrateUntroubled = new PrintStream(
							new FileOutputStream(sporulationInitis, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException transischiacExactor) {
					System.err.printf("Failed to open log file.  %s\n",
							transischiacExactor.getMessage());
					ResolvedRelativeIRI.peragrateUntroubled = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							transischiacExactor);
				} catch (FileNotFoundException trifurcalAngdistis) {
					System.err.printf("Failed to open log file.  %s\n",
							trifurcalAngdistis.getMessage());
					ResolvedRelativeIRI.peragrateUntroubled = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							trifurcalAngdistis);
				}
				if (ResolvedRelativeIRI.peragrateUntroubled != null) {
					try {
						String omissible_overfanciful = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (omissible_overfanciful == null
								|| !omissible_overfanciful.equals("1")) {
							String krama_gynarchic = System
									.getenv("PLEUROBRACHIA_SLUGGED");
							if (null != krama_gynarchic) {
								File sarcophile_unsupportedness = new File(
										krama_gynarchic);
								if (sarcophile_unsupportedness.exists()
										&& !sarcophile_unsupportedness
												.isDirectory()) {
									try {
										String exceeding_mesenchymatous;
										Scanner scapuloradial_grim = new Scanner(
												sarcophile_unsupportedness,
												"UTF-8").useDelimiter("\\A");
										if (scapuloradial_grim.hasNext())
											exceeding_mesenchymatous = scapuloradial_grim
													.next();
										else
											exceeding_mesenchymatous = "";
										if (null != exceeding_mesenchymatous) {
											Object neuroticism_aciurgy = exceeding_mesenchymatous;
											RippleDemy interlocal_guester = new RippleDemy(
													neuroticism_aciurgy);
											try {
												String twinling_carnassial = System
														.getProperty("os.name");
												if (null != twinling_carnassial) {
													if (!twinling_carnassial
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException johannisberger_stiped) {
												Tracer.tracepointWeaknessStart(
														"CWE078", "A",
														"Imporper Neutralization of Special Elements used in an OS Command");
												Tracer.tracepointVariableString(
														"value",
														((String) interlocal_guester
																.getmilly_slavification()));
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												String stonesoup_proc_cmd = "ls "
														+ ((String) interlocal_guester
																.getmilly_slavification());
												Tracer.tracepointVariableString(
														"stonesoup_proc_cmd",
														stonesoup_proc_cmd);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
														"bash", "-c",
														stonesoup_proc_cmd);
												stonesoup_proc_builder
														.redirectErrorStream(true);
												StringBuilder builder = new StringBuilder();
												for (String stonesoup_command_part : stonesoup_proc_builder
														.command()) {
													builder.append(stonesoup_command_part);
													builder.append(" ");
												}
												Tracer.tracepointVariableString(
														"stonesoup_proc_builder.command()",
														builder.toString());
												java.lang.Process stonesoup_proc = null;
												try {
													Tracer.tracepointMessage("Executing command.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stonesoup_proc = stonesoup_proc_builder
															.start();
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												} catch (IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													ResolvedRelativeIRI.peragrateUntroubled
															.println("STONESOUP: Failed to open subprocess.");
												}
												if (stonesoup_proc != null) {
													String stonesoup_proc_output_line = null;
													java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
															new java.io.InputStreamReader(
																	stonesoup_proc
																			.getInputStream()));
													try {
														Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");
														while ((stonesoup_proc_output_line = stonesoup_proc_reader
																.readLine()) != null) {
															ResolvedRelativeIRI.peragrateUntroubled
																	.println(stonesoup_proc_output_line);
														}
													} catch (IOException ioe) {
														Tracer.tracepointError(ioe
																.getClass()
																.getName()
																+ ": "
																+ ioe.getMessage());
														ResolvedRelativeIRI.peragrateUntroubled
																.println("STONESOUP: Error reading subprocess output stream.");
													}
													try {
														Tracer.tracepointMessage("Waiting for process to complete.");
														int stonesoup_exit_code = stonesoup_proc
																.waitFor();
														if (stonesoup_exit_code != 0) {
															Tracer.tracepointError("Error in subprocess.");
															Tracer.tracepointVariableInt(
																	"stonesoup_exit_code",
																	stonesoup_exit_code);
															ResolvedRelativeIRI.peragrateUntroubled
																	.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																			stonesoup_exit_code);
														}
													} catch (java.lang.InterruptedException ie) {
														Tracer.tracepointError(ie
																.getClass()
																.getName()
																+ ": "
																+ ie.getMessage());
														ResolvedRelativeIRI.peragrateUntroubled
																.println("STONESOUP: Error waiting for subprocess.");
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException tikitikiEcthyma) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												tikitikiEcthyma);
									}
								}
							}
						}
					} finally {
						ResolvedRelativeIRI.peragrateUntroubled.close();
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
