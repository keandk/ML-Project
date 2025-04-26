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
import java.util.Arrays;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    static PrintStream surfboatmanHuntsman = null;
	private static final java.util.concurrent.atomic.AtomicBoolean jasperatedLogomach = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (jasperatedLogomach.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpoqBot7_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File freakfulWoddie = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!freakfulWoddie.getParentFile().exists()
					&& !freakfulWoddie.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.surfboatmanHuntsman = new PrintStream(
							new FileOutputStream(freakfulWoddie, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException histaminaseCondominate) {
					System.err.printf("Failed to open log file.  %s\n",
							histaminaseCondominate.getMessage());
					ResolvedRelativeIRI.surfboatmanHuntsman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							histaminaseCondominate);
				} catch (FileNotFoundException steadimentVestigial) {
					System.err.printf("Failed to open log file.  %s\n",
							steadimentVestigial.getMessage());
					ResolvedRelativeIRI.surfboatmanHuntsman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							steadimentVestigial);
				}
				if (ResolvedRelativeIRI.surfboatmanHuntsman != null) {
					try {
						String consentment_gurnetty = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (consentment_gurnetty == null
								|| !consentment_gurnetty.equals("1")) {
							String capriciously_reddishness = System
									.getenv("PUCKISHLY_FLACKED");
							if (null != capriciously_reddishness) {
								File osteotomist_propynoic = new File(
										capriciously_reddishness);
								if (osteotomist_propynoic.exists()
										&& !osteotomist_propynoic.isDirectory()) {
									try {
										String cynomys_somesthetic;
										Scanner inartisticality_advancingly = new Scanner(
												osteotomist_propynoic, "UTF-8")
												.useDelimiter("\\A");
										if (inartisticality_advancingly
												.hasNext())
											cynomys_somesthetic = inartisticality_advancingly
													.next();
										else
											cynomys_somesthetic = "";
										if (null != cynomys_somesthetic) {
											int waterchat_aerodonetics;
											try {
												waterchat_aerodonetics = Integer
														.parseInt(cynomys_somesthetic);
											} catch (NumberFormatException amalrician_agoraphobia) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														amalrician_agoraphobia);
											}
											Object agapemonite_paludinal = waterchat_aerodonetics;
											cotemporaneanSquark(3, null, null,
													null,
													agapemonite_paludinal,
													null, null);
										}
									} catch (FileNotFoundException convalescencePresentiality) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												convalescencePresentiality);
									}
								}
							}
						}
					} finally {
						ResolvedRelativeIRI.surfboatmanHuntsman.close();
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

	public void cotemporaneanSquark(int kafUninitialled,
			Object... shatterpatedCastigate) {
		Object antidomesticUreic = null;
		int implosionOnward = 0;
		for (implosionOnward = 0; implosionOnward < shatterpatedCastigate.length; implosionOnward++) {
			if (implosionOnward == kafUninitialled)
				antidomesticUreic = shatterpatedCastigate[implosionOnward];
		}
		citrinousBrangler(antidomesticUreic);
	}

	public static void citrinousBrangler(Object cataclinalDignification) {
		Tracer.tracepointWeaknessStart("CWE789", "A",
				"Uncontrolled Memory Allocation");
		try {
			if (((Integer) cataclinalDignification) > 0
					&& ((Integer) cataclinalDignification) <= Integer.MAX_VALUE) {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				stonesoup_array = new char[((Integer) cataclinalDignification)];
				Tracer.tracepointBufferInfo("stonesoup_array",
						stonesoup_array.length, "Length of stonesoup_array");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				Arrays.fill(stonesoup_array, 'x');
				for (int i = 0; i < stonesoup_array.length; i++) {
					ResolvedRelativeIRI.surfboatmanHuntsman
							.print(stonesoup_array[i]);
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				ResolvedRelativeIRI.surfboatmanHuntsman.println("");
				ResolvedRelativeIRI.surfboatmanHuntsman
						.println("STONESOUP: successfully initialized array");
			}
		} catch (Error e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(ResolvedRelativeIRI.surfboatmanHuntsman);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static void citrinousBrangler() {
		citrinousBrangler(null);
	}

	static char[] stonesoup_array;

}
