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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    public class UnscalablenessWifelike {
		private int[] communique_contrapose;

		public UnscalablenessWifelike(int[] communique_contrapose) {
			this.communique_contrapose = communique_contrapose;
		}

		public int[] getcommunique_contrapose() {
			return this.communique_contrapose;
		}
	}

	static PrintStream acetylizableFilmily = null;
	private static final java.util.concurrent.atomic.AtomicBoolean plagateSmilingness = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (plagateSmilingness.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpsAloxE_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File appenditiousPulmonectomy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!appenditiousPulmonectomy.getParentFile().exists()
					&& !appenditiousPulmonectomy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.acetylizableFilmily = new PrintStream(
							new FileOutputStream(appenditiousPulmonectomy,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException narcotineUndevious) {
					System.err.printf("Failed to open log file.  %s\n",
							narcotineUndevious.getMessage());
					ResolvedRelativeIRI.acetylizableFilmily = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							narcotineUndevious);
				} catch (FileNotFoundException mellsmanBusaos) {
					System.err.printf("Failed to open log file.  %s\n",
							mellsmanBusaos.getMessage());
					ResolvedRelativeIRI.acetylizableFilmily = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							mellsmanBusaos);
				}
				if (ResolvedRelativeIRI.acetylizableFilmily != null) {
					try {
						String semiextinct_unmimicked = System
								.getenv("MYMAR_OVERSHEET");
						if (null != semiextinct_unmimicked) {
							int unexternal_coincider;
							try {
								unexternal_coincider = Integer
										.parseInt(semiextinct_unmimicked);
							} catch (NumberFormatException autoretardation_grossification) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										autoretardation_grossification);
							}
							int[] deprivation_toponym = new int[11];
							deprivation_toponym[6] = unexternal_coincider;
							UnscalablenessWifelike chrysorin_denudation = new UnscalablenessWifelike(
									deprivation_toponym);
							boolean ethaldehyde_droopt = false;
							iconoclasticism_preliterary: for (int commonness_endocrinopathy = 0; commonness_endocrinopathy < 10; commonness_endocrinopathy++)
								for (int concernedly_thivel = 0; concernedly_thivel < 10; concernedly_thivel++)
									if (commonness_endocrinopathy
											* concernedly_thivel == 63) {
										ethaldehyde_droopt = true;
										break iconoclasticism_preliterary;
									}
							Tracer.tracepointWeaknessStart("CWE606", "B",
									"Uncheck Input for Loop Condition");
							char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
									.toCharArray();
							SecureRandom random = null;
							try {
								random = SecureRandom.getInstance("SHA1PRNG");
							} catch (NoSuchAlgorithmException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								ResolvedRelativeIRI.acetylizableFilmily
										.println("STONESOUP: Random generator algorithm does not exist.");
							}
							Tracer.tracepointVariableInt("value",
									chrysorin_denudation
											.getcommunique_contrapose()[6]);
							if (random != null) {
								StringBuilder stonesoup_filename = new StringBuilder();
								ResolvedRelativeIRI.acetylizableFilmily
										.println("Generating file name");
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								for (int stonesoup_counter = 0; stonesoup_counter < chrysorin_denudation
										.getcommunique_contrapose()[6]; stonesoup_counter++) {
									stonesoup_filename
											.append(stonesoup_random_charset[random
													.nextInt(stonesoup_random_charset.length)]);
								}
								Tracer.tracepointVariableString(
										"stonesoup_filename",
										stonesoup_filename.toString());
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								if (stonesoup_filename.length() > 0) {
									File writePath = new File(
											stonesoup_filename.toString());
									try {
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										writePath.createNewFile();
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (IOException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										ResolvedRelativeIRI.acetylizableFilmily
												.println("Failed to create file.");
										ResolvedRelativeIRI.acetylizableFilmily
												.println("Error:");
										e.printStackTrace(ResolvedRelativeIRI.acetylizableFilmily);
										throw new RuntimeException(
												"Unknown error in filename.", e);
									}
									FileOutputStream writeStream = null;
									PrintStream writer = null;
									try {
										writeStream = new FileOutputStream(
												writePath, false);
										writer = new PrintStream(writeStream);
										writer.println("/* This is my file */");
									} catch (FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										ResolvedRelativeIRI.acetylizableFilmily
												.println("Failed to create file.");
										e.printStackTrace(ResolvedRelativeIRI.acetylizableFilmily);
									} finally {
										if (writer != null) {
											writer.close();
										}
									}
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ResolvedRelativeIRI.acetylizableFilmily.close();
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
