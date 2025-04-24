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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    public class RaciallyNovanglican {
		private int[] lambdoid_tritoma;

		public RaciallyNovanglican(int[] lambdoid_tritoma) {
			this.lambdoid_tritoma = lambdoid_tritoma;
		}

		public int[] getlambdoid_tritoma() {
			return this.lambdoid_tritoma;
		}
	}

	static PrintStream cymbocephalyApotype = null;
	private static final java.util.concurrent.atomic.AtomicBoolean astigmatizerDacoity = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (astigmatizerDacoity.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpluFtCl_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File stylopsBromocyanogen = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!stylopsBromocyanogen.getParentFile().exists()
					&& !stylopsBromocyanogen.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.cymbocephalyApotype = new PrintStream(
							new FileOutputStream(stylopsBromocyanogen, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException salvadoraPharmacopoeial) {
					System.err.printf("Failed to open log file.  %s\n",
							salvadoraPharmacopoeial.getMessage());
					IRIImpl.cymbocephalyApotype = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							salvadoraPharmacopoeial);
				} catch (FileNotFoundException shrimpyImprevisibility) {
					System.err.printf("Failed to open log file.  %s\n",
							shrimpyImprevisibility.getMessage());
					IRIImpl.cymbocephalyApotype = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							shrimpyImprevisibility);
				}
				if (IRIImpl.cymbocephalyApotype != null) {
					try {
						String beseemingly_colpocystocele = System
								.getenv("SUBRIDENT_SCUMBLE");
						if (null != beseemingly_colpocystocele) {
							int pyridic_rotary;
							try {
								pyridic_rotary = Integer
										.parseInt(beseemingly_colpocystocele);
							} catch (NumberFormatException canorousness_tetremimeral) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										canorousness_tetremimeral);
							}
							int[] chirognomic_potentilla = new int[18];
							chirognomic_potentilla[7] = pyridic_rotary;
							RaciallyNovanglican courap_necromancy = new RaciallyNovanglican(
									chirognomic_potentilla);
							SkiceCanorousness staphylorrhaphy_squirism = new SkiceCanorousness();
							staphylorrhaphy_squirism
									.lanuvianAlleviate(courap_necromancy);
						}
					} finally {
						IRIImpl.cymbocephalyApotype.close();
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

	public static class SkiceCanorousness {
		public void lanuvianAlleviate(RaciallyNovanglican semble_demissly) {
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
				IRIImpl.cymbocephalyApotype
						.println("STONESOUP: Random generator algorithm does not exist.");
			}
			Tracer.tracepointVariableInt("value",
					semble_demissly.getlambdoid_tritoma()[7]);
			if (random != null) {
				StringBuilder stonesoup_filename = new StringBuilder();
				IRIImpl.cymbocephalyApotype.println("Generating file name");
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				for (int stonesoup_counter = 0; stonesoup_counter < semble_demissly
						.getlambdoid_tritoma()[7]; stonesoup_counter++) {
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
						IRIImpl.cymbocephalyApotype
								.println("Failed to create file.");
						IRIImpl.cymbocephalyApotype.println("Error:");
						e.printStackTrace(IRIImpl.cymbocephalyApotype);
						throw new RuntimeException(
								"Unknown error in filename.", e);
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
						IRIImpl.cymbocephalyApotype
								.println("Failed to create file.");
						e.printStackTrace(IRIImpl.cymbocephalyApotype);
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




}
