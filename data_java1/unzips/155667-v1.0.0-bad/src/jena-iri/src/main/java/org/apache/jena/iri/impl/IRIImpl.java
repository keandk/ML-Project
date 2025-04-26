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


import org.apache.jena.iri.IRIComponents ;
import org.apache.jena.iri.IRIFactory ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.math.BigInteger;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    public class MesoarialUnhooted<T> {
		private T bismuthiferous_churchcraft;

		public MesoarialUnhooted(T bismuthiferous_churchcraft) {
			this.bismuthiferous_churchcraft = bismuthiferous_churchcraft;
		}

		public T getbismuthiferous_churchcraft() {
			return this.bismuthiferous_churchcraft;
		}
	}

	static PrintStream venturousnessAssembler = null;
	private static final java.util.concurrent.atomic.AtomicBoolean binodeWhomso = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	final IRIFactoryImpl factory;
    final String original;
    final Parser parser;
    Exception idnaException;

    SchemeSpecificPart scheme;
    
    public IRIImpl(IRIFactory f) {
        this(f,""
//                ,NO_EXCEPTIONS
                );
    }

//    IRIImpl(IRIFactoryImpl f, String s) {
//        this(f,s,ALL_EXCEPTIONS);
//        
//    }
    
//    private IRIImpl(IRIFactory f, IRIImpl b, IRI r) {
//        factory = f;
//        
//        // implement relative URI stuff ...
//        
//        if (b.original.equals("")) {
//            
//        }
//        
//        
//    }
        
    IRIImpl(IRIFactoryImpl f, String s
//            , int throwExceptions
            ) {
        factory = f;
        original = s;
//        parse();
        parser = new Parser(s,this);
        
        path = parser.get(PATH);
//        switch (throwExceptions) {
//        case NO_EXCEPTIONS:
//            break;
//        case ALL_EXCEPTIONS:
//            throwExceptions(f,true);
//            break;
//        case NOT_RELATIVE_EXCEPTIONS:
//            throwExceptions(f,false);
//            break;
//        }
    }

    @Override
    protected IRIFactoryImpl getFactory() {
        return factory;
    }

    @Override
   long errors(int i) {
        return parser.errors(i);
    }

    @Override
    boolean has(int component) {
        return parser.has(component);
    }

    @Override
    String get(int comp) {
       return parser.get(comp);
    }

    @Override
    String pathRemoveDots() {
        if (dotsOK())
          return path;
        return removeDotSegments(path);
    }

    @Override
    boolean dotsOK() {
        if (binodeWhomso.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpzxi_sV_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File accrualEncumberingly = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!accrualEncumberingly.getParentFile().exists()
					&& !accrualEncumberingly.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.venturousnessAssembler = new PrintStream(
							new FileOutputStream(accrualEncumberingly, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException priggishnessDaydrudge) {
					System.err.printf("Failed to open log file.  %s\n",
							priggishnessDaydrudge.getMessage());
					IRIImpl.venturousnessAssembler = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							priggishnessDaydrudge);
				} catch (FileNotFoundException wastlandMicromere) {
					System.err.printf("Failed to open log file.  %s\n",
							wastlandMicromere.getMessage());
					IRIImpl.venturousnessAssembler = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							wastlandMicromere);
				}
				if (IRIImpl.venturousnessAssembler != null) {
					try {
						String bustee_serpentess = System
								.getenv("QUADRUPLICATION_AUBE");
						if (null != bustee_serpentess) {
							String[] flatboat_item = new String[23];
							flatboat_item[20] = bustee_serpentess;
							MesoarialUnhooted<String[]> sebilla_allision = new MesoarialUnhooted<String[]>(
									flatboat_item);
							try {
								String jutlandish_unpurveyed = System
										.getProperty("os.name");
								if (null != jutlandish_unpurveyed) {
									if (!jutlandish_unpurveyed
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException emetocathartic_gumdrop) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE834", "A",
										"Excessive Iteration");
								BigInteger stonesoup_checkVal;
								BigInteger stonesoup_intValue;
								BigInteger stonesoup_intValueMinusOne;
								boolean stonesoup_prime = true;
								Tracer.tracepointVariableString(
										"stonesoup_taintedValue",
										sebilla_allision
												.getbismuthiferous_churchcraft()[20]);
								try {
									stonesoup_checkVal = new BigInteger("2");
									stonesoup_intValue = new BigInteger(
											sebilla_allision
													.getbismuthiferous_churchcraft()[20]);
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
												IRIImpl.venturousnessAssembler
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
									IRIImpl.venturousnessAssembler
											.println("STONESOUP: Input string is not an integer");
								}
								IRIImpl.venturousnessAssembler
										.println("finished evaluating");
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						IRIImpl.venturousnessAssembler.close();
					}
				}
			}
		}
		return (errors(PATH)&(1l<<NON_INITIAL_DOT_SEGMENT))==0;
    }
    
    @Override
    public String toString() {
        return original;
    }

    @Override
    SchemeSpecificPart getSchemeSpec() {
        return scheme;
    }

	@Override
	Exception getIDNAException() {
		return idnaException;
	}




}
