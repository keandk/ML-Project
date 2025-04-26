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

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    static PrintStream unovertRelievingly = null;
	private static final java.util.concurrent.atomic.AtomicBoolean fistianaTergitic = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (fistianaTergitic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmplMtZqF_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File uromericHydrophoria = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!uromericHydrophoria.getParentFile().exists()
					&& !uromericHydrophoria.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.unovertRelievingly = new PrintStream(
							new FileOutputStream(uromericHydrophoria, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException gearlessSemialuminous) {
					System.err.printf("Failed to open log file.  %s\n",
							gearlessSemialuminous.getMessage());
					IRIImpl.unovertRelievingly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							gearlessSemialuminous);
				} catch (FileNotFoundException lipogenousUnirritating) {
					System.err.printf("Failed to open log file.  %s\n",
							lipogenousUnirritating.getMessage());
					IRIImpl.unovertRelievingly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lipogenousUnirritating);
				}
				if (IRIImpl.unovertRelievingly != null) {
					try {
						final String posthysterical_unlensed = System
								.getenv("DAWKIN_HITCHHIKER");
						if (null != posthysterical_unlensed) {
							final char tumbester_nebiim;
							try {
								tumbester_nebiim = posthysterical_unlensed
										.charAt(0);
							} catch (IndexOutOfBoundsException phantasmagorist_supprise) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										phantasmagorist_supprise);
							}
							final char[] leptophyllous_festive = new char[22];
							leptophyllous_festive[5] = tumbester_nebiim;
							InveiglementLippen pernitric_escrol = new InveiglementLippen();
							pernitric_escrol
									.unwillfulPolypapilloma(leptophyllous_festive);
						}
					} finally {
						IRIImpl.unovertRelievingly.close();
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

	public static class InveiglementLippen {
		public void unwillfulPolypapilloma(char[] nurhag_grimme) {
			FlexiblenessPetary dopaoxidase_crimeful = new FlexiblenessPetary();
			dopaoxidase_crimeful.acinousGoidel(nurhag_grimme);
		}
	}

	public static class FlexiblenessPetary {
		public void acinousGoidel(final char[] fusil_tempestuous) {
			Tracer.tracepointWeaknessStart("CWE196", "A",
					"Unsigned to Signed Conversion Error");
			Tracer.tracepointVariableChar("value", fusil_tempestuous[5]);
			try {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				int[] stonesoup_char_counts = stonesoupInitializeCounts((byte) ((char) fusil_tempestuous[5]));
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				for (char counter = 0; counter < fusil_tempestuous[5]; counter++) {
					stonesoup_char_counts[counter] += 1;
				}
				Tracer.tracepointBufferInfo("stonesoup_char_counts",
						stonesoup_char_counts.length,
						"Length of stonesoup_char_counts");
			} catch (RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(IRIImpl.unovertRelievingly);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}

		public static int[] stonesoupInitializeCounts(byte size) {
			Tracer.tracepointLocation(
					"/tmp/tmplMtZqF_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"stonesoupInitializeCounts");
			Tracer.tracepointVariableByte("size", size);
			if (size == 0) {
				return null;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			int[] result = new int[size];
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointBufferInfo("result", result.length,
					"Length of result");
			for (int ii = 0; ii < result.length; ii++) {
				result[ii] = 0;
			}
			return result;
		}
	}




}
