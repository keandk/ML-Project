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
import java.util.ArrayList;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    public class YgapoTippet<T> {
		private T viameter_zymogen;

		public YgapoTippet(T viameter_zymogen) {
			this.viameter_zymogen = viameter_zymogen;
		}

		public T getviameter_zymogen() {
			return this.viameter_zymogen;
		}
	}

	static PrintStream carrickPneumotactic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unboroughAlbanensian = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (unboroughAlbanensian.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp9D2h6X_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File diaeresisTally = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!diaeresisTally.getParentFile().exists()
					&& !diaeresisTally.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.carrickPneumotactic = new PrintStream(
							new FileOutputStream(diaeresisTally, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException strikinglyFiggery) {
					System.err.printf("Failed to open log file.  %s\n",
							strikinglyFiggery.getMessage());
					ResolvedRelativeIRI.carrickPneumotactic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							strikinglyFiggery);
				} catch (FileNotFoundException deontologistDiapasonal) {
					System.err.printf("Failed to open log file.  %s\n",
							deontologistDiapasonal.getMessage());
					ResolvedRelativeIRI.carrickPneumotactic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							deontologistDiapasonal);
				}
				if (ResolvedRelativeIRI.carrickPneumotactic != null) {
					try {
						String endocline_swarm = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (endocline_swarm == null
								|| !endocline_swarm.equals("1")) {
							String trehalose_hesperidin = System
									.getenv("KOKAM_SCROBICULATE");
							if (null != trehalose_hesperidin) {
								File befeather_disestablish = new File(
										trehalose_hesperidin);
								if (befeather_disestablish.exists()
										&& !befeather_disestablish
												.isDirectory()) {
									try {
										String andiron_chilopod;
										Scanner cuartillo_intendant = new Scanner(
												befeather_disestablish, "UTF-8")
												.useDelimiter("\\A");
										if (cuartillo_intendant.hasNext())
											andiron_chilopod = cuartillo_intendant
													.next();
										else
											andiron_chilopod = "";
										if (null != andiron_chilopod) {
											int negrophobe_neomedievalism;
											try {
												negrophobe_neomedievalism = Integer
														.parseInt(andiron_chilopod);
											} catch (NumberFormatException cherte_counteraction) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														cherte_counteraction);
											}
											Object heterakis_retrusion = negrophobe_neomedievalism;
											YgapoTippet<Object> astroscopy_civetone = new YgapoTippet<Object>(
													heterakis_retrusion);
											sixerOvationary(astroscopy_civetone);
										}
									} catch (FileNotFoundException uncommendedMelodramatic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												uncommendedMelodramatic);
									}
								}
							}
						}
					} finally {
						ResolvedRelativeIRI.carrickPneumotactic.close();
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

	public static void sixerOvationary(YgapoTippet<Object> werentBobadilism){Tracer.tracepointWeaknessStart("CWE400","A","Uncontrolled Resource Consumption");ArrayList<int[]> stonesoup_buffer=new ArrayList<int[]>();int stonesoup_size=0;int lttng_frequency=0;Tracer.tracepointVariableInt("stonesoup_intValue",((Integer)werentBobadilism.getviameter_zymogen()));if (((Integer)werentBobadilism.getviameter_zymogen()) > 0 && ((Integer)werentBobadilism.getviameter_zymogen()) <= Integer.MAX_VALUE){stonesoup_size=10000;Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");for (int i=0;i < ((Integer)werentBobadilism.getviameter_zymogen());){try {stonesoup_buffer.add(new int[stonesoup_size]);i++;} catch (OutOfMemoryError e){if (lttng_frequency == 0){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");}lttng_frequency=(lttng_frequency == 199)?0:lttng_frequency + 1;}}Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");Tracer.tracepointMessage("TRIGGER-POINT: AFTER");Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");ResolvedRelativeIRI.carrickPneumotactic.println("Allocated all the memory requested");}Tracer.tracepointWeaknessEnd();}

	public static void sixerOvationary() {
		sixerOvationary(null);
	}

}
