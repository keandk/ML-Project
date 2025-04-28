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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    public class PoulteressUnget<T> {
		private T rauraci_blowtorch;

		public PoulteressUnget(T rauraci_blowtorch) {
			this.rauraci_blowtorch = rauraci_blowtorch;
		}

		public T getrauraci_blowtorch() {
			return this.rauraci_blowtorch;
		}
	}

	static PrintStream sortilegyNonmechanical = null;
	private static final java.util.concurrent.atomic.AtomicBoolean preinitiationGamogenetical = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (preinitiationGamogenetical.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpOCRE_N_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File predetentionNongospel = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!predetentionNongospel.getParentFile().exists()
					&& !predetentionNongospel.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.sortilegyNonmechanical = new PrintStream(
							new FileOutputStream(predetentionNongospel, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException phosphotungsticGranitelike) {
					System.err.printf("Failed to open log file.  %s\n",
							phosphotungsticGranitelike.getMessage());
					ResolvedRelativeIRI.sortilegyNonmechanical = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							phosphotungsticGranitelike);
				} catch (FileNotFoundException dipodomyinaeCretism) {
					System.err.printf("Failed to open log file.  %s\n",
							dipodomyinaeCretism.getMessage());
					ResolvedRelativeIRI.sortilegyNonmechanical = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							dipodomyinaeCretism);
				}
				if (ResolvedRelativeIRI.sortilegyNonmechanical != null) {
					try {
						String deviant_indefensive = System
								.getenv("HOMOEOCHROMATIC_MULTIVOCALNESS");
						if (null != deviant_indefensive) {
							int reinduce_portionless;
							try {
								reinduce_portionless = Integer
										.parseInt(deviant_indefensive);
							} catch (NumberFormatException heterogenous_virginal) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										heterogenous_virginal);
							}
							PoulteressUnget<Integer> emancipatory_upfling = new PoulteressUnget<Integer>(
									reinduce_portionless);
							boolean developability_ceramicite = false;
							giblet_magnetod: for (int autoturning_gentility = 0; autoturning_gentility < 10; autoturning_gentility++)
								for (int postapoplectic_brinishness = 0; postapoplectic_brinishness < 10; postapoplectic_brinishness++)
									if (autoturning_gentility
											* postapoplectic_brinishness == 63) {
										developability_ceramicite = true;
										break giblet_magnetod;
									}
							Tracer.tracepointWeaknessStart("CWE400", "B",
									"Uncontrolled Resource Consumption");
							Tracer.tracepointMessage("Create pool");
							ExecutorService pool = Executors
									.newFixedThreadPool(20);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							if (emancipatory_upfling.getrauraci_blowtorch() > 0
									&& emancipatory_upfling
											.getrauraci_blowtorch() <= Integer.MAX_VALUE) {
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								Tracer.tracepointMessage("Creating threads");
								for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
									pool.execute(new Factorial(
											emancipatory_upfling
													.getrauraci_blowtorch(),
											ResolvedRelativeIRI.sortilegyNonmechanical));
								}
								pool.shutdown();
								Tracer.tracepointMessage("Shutdown pool");
							}
							try {
								Tracer.tracepointMessage("Joining threads");
								while (!pool.awaitTermination(1,
										TimeUnit.SECONDS)) {
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("Joined threads");
								ResolvedRelativeIRI.sortilegyNonmechanical
										.println("finished evaluating");
							} catch (InterruptedException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								ResolvedRelativeIRI.sortilegyNonmechanical
										.println("Thread pool interrupted");
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ResolvedRelativeIRI.sortilegyNonmechanical.close();
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

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpOCRE_N_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpOCRE_N_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpOCRE_N_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					Thread.currentThread().getName()
							+ ": Factorial.calculateFactorial");
			BigInteger stonesoup_factorial = new BigInteger("1");
			for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
				stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
						.valueOf(stonesoup_counter));
			}
			stonesoup_output.println(stonesoup_factorial);
		}
	}

}
