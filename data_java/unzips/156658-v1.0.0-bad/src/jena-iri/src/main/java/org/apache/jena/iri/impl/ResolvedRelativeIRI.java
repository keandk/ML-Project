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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    static PrintStream zoopsiaSomatologist = null;
	private static final java.util.concurrent.atomic.AtomicBoolean jacaltecaChipping = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (jacaltecaChipping.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpqM5ttQ_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File escropuloPlummer = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!escropuloPlummer.getParentFile().exists()
					&& !escropuloPlummer.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.zoopsiaSomatologist = new PrintStream(
							new FileOutputStream(escropuloPlummer, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException satisfactoryAssailment) {
					System.err.printf("Failed to open log file.  %s\n",
							satisfactoryAssailment.getMessage());
					ResolvedRelativeIRI.zoopsiaSomatologist = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							satisfactoryAssailment);
				} catch (FileNotFoundException mesitylFocalize) {
					System.err.printf("Failed to open log file.  %s\n",
							mesitylFocalize.getMessage());
					ResolvedRelativeIRI.zoopsiaSomatologist = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							mesitylFocalize);
				}
				if (ResolvedRelativeIRI.zoopsiaSomatologist != null) {
					try {
						String anatox_factabling = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (anatox_factabling == null
								|| !anatox_factabling.equals("1")) {
							String torotoro_expressionism = System
									.getenv("VIPEROUS_UNOVERWHELMED");
							if (null != torotoro_expressionism) {
								File phenate_myeloma = new File(
										torotoro_expressionism);
								if (phenate_myeloma.exists()
										&& !phenate_myeloma.isDirectory()) {
									try {
										String overslip_nonexisting;
										Scanner nougatine_porching = new Scanner(
												phenate_myeloma, "UTF-8")
												.useDelimiter("\\A");
										if (nougatine_porching.hasNext())
											overslip_nonexisting = nougatine_porching
													.next();
										else
											overslip_nonexisting = "";
										if (null != overslip_nonexisting) {
											int nonplanar_trichogynic;
											try {
												nonplanar_trichogynic = Integer
														.parseInt(overslip_nonexisting);
											} catch (NumberFormatException outcrow_scammoniate) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														outcrow_scammoniate);
											}
											int[] polyoecy_diskelion = new int[29];
											polyoecy_diskelion[21] = nonplanar_trichogynic;
											mauveineReoblige(3, null, null,
													null, polyoecy_diskelion,
													null, null);
										}
									} catch (FileNotFoundException atterminementSphericle) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												atterminementSphericle);
									}
								}
							}
						}
					} finally {
						ResolvedRelativeIRI.zoopsiaSomatologist.close();
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

	public void mauveineReoblige(int rebulkUncrowned,
			int[]... olpeAsthenobiotic) {
		int[] individuumPiscator = null;
		int primoprimitiveMelanophore = 0;
		for (primoprimitiveMelanophore = 0; primoprimitiveMelanophore < olpeAsthenobiotic.length; primoprimitiveMelanophore++) {
			if (primoprimitiveMelanophore == rebulkUncrowned)
				individuumPiscator = olpeAsthenobiotic[primoprimitiveMelanophore];
		}
		solipsisticSocietified(individuumPiscator);
	}

	public void solipsisticSocietified(int[] overintense_arterin) {
		surpriserPartridgelike(overintense_arterin);
	}

	public void surpriserPartridgelike(int[] implicant_unsuspectedness) {
		supremacyHandhold(implicant_unsuspectedness);
	}

	public void supremacyHandhold(int[] scatophagid_pantingly) {
		unburlesquedPadus(scatophagid_pantingly);
	}

	public void unburlesquedPadus(int[] pancreatogenic_leprosarium) {
		overirrigationZac(pancreatogenic_leprosarium);
	}

	public void overirrigationZac(int[] amphioxi_spartanize) {
		quibbleproofMotatorious(amphioxi_spartanize);
	}

	public void quibbleproofMotatorious(int[] rattler_mispagination) {
		battlesteadCineraria(rattler_mispagination);
	}

	public void battlesteadCineraria(int[] sunburnproof_prepainful) {
		lordosisTurnicomorphic(sunburnproof_prepainful);
	}

	public void lordosisTurnicomorphic(int[] hard_ceramium) {
		demosAlphabetize(hard_ceramium);
	}

	public void demosAlphabetize(int[] pansophical_corixidae) {
		raiserKonak(pansophical_corixidae);
	}

	public void raiserKonak(int[] aphrodision_surfboatman) {
		Tracer.tracepointWeaknessStart("CWE606", "B",
				"Uncheck Input for Loop Condition");
		char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
				.toCharArray();
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			ResolvedRelativeIRI.zoopsiaSomatologist
					.println("STONESOUP: Random generator algorithm does not exist.");
		}
		Tracer.tracepointVariableInt("value", aphrodision_surfboatman[21]);
		if (random != null) {
			StringBuilder stonesoup_filename = new StringBuilder();
			ResolvedRelativeIRI.zoopsiaSomatologist
					.println("Generating file name");
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < aphrodision_surfboatman[21]; stonesoup_counter++) {
				stonesoup_filename.append(stonesoup_random_charset[random
						.nextInt(stonesoup_random_charset.length)]);
			}
			Tracer.tracepointVariableString("stonesoup_filename",
					stonesoup_filename.toString());
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			if (stonesoup_filename.length() > 0) {
				File writePath = new File(stonesoup_filename.toString());
				try {
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					writePath.createNewFile();
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (IOException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ResolvedRelativeIRI.zoopsiaSomatologist
							.println("Failed to create file.");
					ResolvedRelativeIRI.zoopsiaSomatologist.println("Error:");
					e.printStackTrace(ResolvedRelativeIRI.zoopsiaSomatologist);
					throw new RuntimeException("Unknown error in filename.", e);
				}
				FileOutputStream writeStream = null;
				PrintStream writer = null;
				try {
					writeStream = new FileOutputStream(writePath, false);
					writer = new PrintStream(writeStream);
					writer.println("/* This is my file */");
				} catch (FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ResolvedRelativeIRI.zoopsiaSomatologist
							.println("Failed to create file.");
					e.printStackTrace(ResolvedRelativeIRI.zoopsiaSomatologist);
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
