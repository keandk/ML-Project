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
import java.math.BigInteger;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    public class UnconditionateClintonite {
		private String postphlogistic_deiformity;

		public UnconditionateClintonite(String postphlogistic_deiformity) {
			this.postphlogistic_deiformity = postphlogistic_deiformity;
		}

		public String getpostphlogistic_deiformity() {
			return this.postphlogistic_deiformity;
		}
	}

	static PrintStream cursoriousOutwoman = null;
	private static final java.util.concurrent.atomic.AtomicBoolean methylpentosesScaut = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (methylpentosesScaut.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp1nrTuO_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File undoctoredNonchalant = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!undoctoredNonchalant.getParentFile().exists()
					&& !undoctoredNonchalant.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.cursoriousOutwoman = new PrintStream(
							new FileOutputStream(undoctoredNonchalant, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException reengeMolybdocolic) {
					System.err.printf("Failed to open log file.  %s\n",
							reengeMolybdocolic.getMessage());
					ResolvedRelativeIRI.cursoriousOutwoman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							reengeMolybdocolic);
				} catch (FileNotFoundException pseudaconitineComplimentarily) {
					System.err.printf("Failed to open log file.  %s\n",
							pseudaconitineComplimentarily.getMessage());
					ResolvedRelativeIRI.cursoriousOutwoman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pseudaconitineComplimentarily);
				}
				if (ResolvedRelativeIRI.cursoriousOutwoman != null) {
					try {
						String ctenidial_anaematosis = System
								.getenv("PENTAGRAM_DESULTORIOUS");
						if (null != ctenidial_anaematosis) {
							UnconditionateClintonite cardiopathic_tarsal = new UnconditionateClintonite(
									ctenidial_anaematosis);
							try {
								String ingrainedly_indefinitely = System
										.getProperty("os.name");
								if (null != ingrainedly_indefinitely) {
									if (!ingrainedly_indefinitely
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException struthious_ectype) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE834", "A",
										"Excessive Iteration");
								BigInteger stonesoup_checkVal;
								BigInteger stonesoup_intValue;
								BigInteger stonesoup_intValueMinusOne;
								boolean stonesoup_prime = true;
								Tracer.tracepointVariableString(
										"stonesoup_taintedValue",
										cardiopathic_tarsal
												.getpostphlogistic_deiformity());
								try {
									stonesoup_checkVal = new BigInteger("2");
									stonesoup_intValue = new BigInteger(
											cardiopathic_tarsal
													.getpostphlogistic_deiformity());
									stonesoup_intValueMinusOne = stonesoup_intValue
											.subtract(BigInteger.ONE);
									if (stonesoup_intValue
											.compareTo(BigInteger.ZERO) > 0) {
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										for (; stonesoup_checkVal
												.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
												.add(BigInteger.ONE)) {
											if (stonesoup_intValue.mod(
													stonesoup_checkVal)
													.compareTo(BigInteger.ZERO) == 0) {
												stonesoup_prime = false;
												ResolvedRelativeIRI.cursoriousOutwoman
														.println("Not Prime");
												break;
											}
										}
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									}
								} catch (NumberFormatException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									ResolvedRelativeIRI.cursoriousOutwoman
											.println("STONESOUP: Input string is not an integer");
								}
								ResolvedRelativeIRI.cursoriousOutwoman
										.println("finished evaluating");
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						ResolvedRelativeIRI.cursoriousOutwoman.close();
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
