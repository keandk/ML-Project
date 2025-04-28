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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    static PrintStream susceptibilityUily = null;
	private static final java.util.concurrent.atomic.AtomicBoolean trankColipuncture = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (trankColipuncture.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp5XOt1v_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File exocardiaAntibubonic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!exocardiaAntibubonic.getParentFile().exists()
					&& !exocardiaAntibubonic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.susceptibilityUily = new PrintStream(
							new FileOutputStream(exocardiaAntibubonic, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException ferrotyperArchisymbolical) {
					System.err.printf("Failed to open log file.  %s\n",
							ferrotyperArchisymbolical.getMessage());
					ResolvedRelativeIRI.susceptibilityUily = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ferrotyperArchisymbolical);
				} catch (FileNotFoundException holometabolianToxosis) {
					System.err.printf("Failed to open log file.  %s\n",
							holometabolianToxosis.getMessage());
					ResolvedRelativeIRI.susceptibilityUily = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							holometabolianToxosis);
				}
				if (ResolvedRelativeIRI.susceptibilityUily != null) {
					try {
						String nako_weathercocky = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (nako_weathercocky == null
								|| !nako_weathercocky.equals("1")) {
							String untantalizing_cassareep = System
									.getenv("LAVAGE_IMPERMEABILIZE");
							if (null != untantalizing_cassareep) {
								File dietzeite_unstaffed = new File(
										untantalizing_cassareep);
								if (dietzeite_unstaffed.exists()
										&& !dietzeite_unstaffed.isDirectory()) {
									try {
										String hemiparasitic_calefactor;
										Scanner mediastinal_thingless = new Scanner(
												dietzeite_unstaffed, "UTF-8")
												.useDelimiter("\\A");
										if (mediastinal_thingless.hasNext())
											hemiparasitic_calefactor = mediastinal_thingless
													.next();
										else
											hemiparasitic_calefactor = "";
										if (null != hemiparasitic_calefactor) {
											int gregarina_occludent;
											try {
												gregarina_occludent = Integer
														.parseInt(hemiparasitic_calefactor);
											} catch (NumberFormatException dentiroster_stepminnie) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														dentiroster_stepminnie);
											}
											int[] synthesis_ascogonidium = new int[22];
											synthesis_ascogonidium[3] = gregarina_occludent;
											semidomedUnascertained(3, null,
													null, null,
													synthesis_ascogonidium,
													null, null);
										}
									} catch (FileNotFoundException frivolizeMilitariness) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												frivolizeMilitariness);
									}
								}
							}
						}
					} finally {
						ResolvedRelativeIRI.susceptibilityUily.close();
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

	public void semidomedUnascertained(int coccoliteRosmarinus,
			int[]... turbulencyAppredicate) {
		int[] petioliventresPothecary = null;
		int fumatoryFeedboard = 0;
		for (fumatoryFeedboard = 0; fumatoryFeedboard < turbulencyAppredicate.length; fumatoryFeedboard++) {
			if (fumatoryFeedboard == coccoliteRosmarinus)
				petioliventresPothecary = turbulencyAppredicate[fumatoryFeedboard];
		}
		int dunkirker_antipathida = 0;
		while (true) {
			dunkirker_antipathida++;
			if (dunkirker_antipathida >= 3000)
				break;
		}
		Tracer.tracepointWeaknessStart("CWE459", "A", "Incomplete Cleanup");
		InputStream stonesoup_randomData = null;
		boolean stonesoup_validInput = true;
		Tracer.tracepointVariableInt("stonesoup_intValue",
				petioliventresPothecary[3]);
		byte[] stonesoup_randomChars = null;
		try {
			ResolvedRelativeIRI.susceptibilityUily.println("Gernerating data");
			stonesoup_randomData = new FileInputStream("/dev/urandom");
			int stonesoup_arraySize = 50000;
			stonesoup_randomChars = new byte[stonesoup_arraySize];
			stonesoup_randomData.read(stonesoup_randomChars, 0,
					stonesoup_arraySize);
		} catch (FileNotFoundException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			ResolvedRelativeIRI.susceptibilityUily
					.println("Error: /dev/urandom not found");
			stonesoup_validInput = false;
		} catch (IOException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			ResolvedRelativeIRI.susceptibilityUily
					.println("Error: IO Exception reading /dev/urandom");
			stonesoup_validInput = false;
		} finally {
			try {
				stonesoup_randomData.close();
			} catch (IOException e) {
				ResolvedRelativeIRI.susceptibilityUily
						.println("Error: Cannot close /dev/urandom");
				stonesoup_validInput = false;
			}
		}
		if (stonesoup_validInput) {
			int stonesoup_numFilePaths = 50;
			File[] stonesoup_filePaths = new File[stonesoup_numFilePaths];
			int stonesoup_i = 0;
			OutputStream stonesoup_outputStream = null;
			try {
				ResolvedRelativeIRI.susceptibilityUily.println("Saving data");
				for (stonesoup_i = 0; stonesoup_i < petioliventresPothecary[3]; stonesoup_i++) {
					stonesoup_filePaths[stonesoup_i % stonesoup_numFilePaths] = File
							.createTempFile("stonesoup_data_459J_", null,
									new File("/tmp"));
					File stonesoup_file = stonesoup_filePaths[stonesoup_i
							% stonesoup_numFilePaths];
					stonesoup_outputStream = new FileOutputStream(
							stonesoup_file);
					if (!stonesoup_file.exists()) {
						stonesoup_file.createNewFile();
					}
					stonesoup_outputStream.write(stonesoup_randomChars);
					stonesoup_outputStream.close();
					stonesoup_outputStream = null;
				}
				Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
			} catch (FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ResolvedRelativeIRI.susceptibilityUily
						.println("Error: tmp file  not found");
			} catch (IOException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ResolvedRelativeIRI.susceptibilityUily
						.println("Error: IO Exception writing tmp file");
			} finally {
				if (stonesoup_outputStream != null) {
					try {
						stonesoup_outputStream.close();
					} catch (IOException e) {
						ResolvedRelativeIRI.susceptibilityUily
								.println("Error: could not delete output stream");
					}
				}
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
					if (stonesoup_filePaths[stonesoup_i] != null) {
						stonesoup_filePaths[stonesoup_i].delete();
					}
				}
				Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
