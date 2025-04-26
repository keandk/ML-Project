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
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    static PrintStream riddenMelolontha = null;
	private static final java.util.concurrent.atomic.AtomicBoolean gasterosteoidOverjob = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (gasterosteoidOverjob.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmplNFohY_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File sphenobasilarDesolately = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!sphenobasilarDesolately.getParentFile().exists()
					&& !sphenobasilarDesolately.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.riddenMelolontha = new PrintStream(
							new FileOutputStream(sphenobasilarDesolately, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException pecksniffismCogitate) {
					System.err.printf("Failed to open log file.  %s\n",
							pecksniffismCogitate.getMessage());
					IRIImpl.riddenMelolontha = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pecksniffismCogitate);
				} catch (FileNotFoundException amoebiasisObviation) {
					System.err.printf("Failed to open log file.  %s\n",
							amoebiasisObviation.getMessage());
					IRIImpl.riddenMelolontha = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							amoebiasisObviation);
				}
				if (IRIImpl.riddenMelolontha != null) {
					try {
						String swedenborgian_nordicize = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (swedenborgian_nordicize == null
								|| !swedenborgian_nordicize.equals("1")) {
							String murphy_needlessly = System
									.getenv("UNCURL_HUGEOUSLY");
							if (null != murphy_needlessly) {
								File publicanism_angularness = new File(
										murphy_needlessly);
								if (publicanism_angularness.exists()
										&& !publicanism_angularness
												.isDirectory()) {
									try {
										String unforaged_strongyliasis;
										Scanner paranucleus_unpennoned = new Scanner(
												publicanism_angularness,
												"UTF-8").useDelimiter("\\A");
										if (paranucleus_unpennoned.hasNext())
											unforaged_strongyliasis = paranucleus_unpennoned
													.next();
										else
											unforaged_strongyliasis = "";
										if (null != unforaged_strongyliasis) {
											int aswail_complacently;
											try {
												aswail_complacently = Integer
														.parseInt(unforaged_strongyliasis);
											} catch (NumberFormatException hanna_seafardinger) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														hanna_seafardinger);
											}
											int[] nonluminosity_nizamut = new int[8];
											nonluminosity_nizamut[6] = aswail_complacently;
											try {
												String overinvest_hemipteroid = System
														.getProperty("os.name");
												if (null != overinvest_hemipteroid) {
													if (!overinvest_hemipteroid
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException wattape_snapped) {
												Tracer.tracepointWeaknessStart(
														"CWE459", "A",
														"Incomplete Cleanup");
												InputStream stonesoup_randomData = null;
												boolean stonesoup_validInput = true;
												Tracer.tracepointVariableInt(
														"stonesoup_intValue",
														nonluminosity_nizamut[6]);
												byte[] stonesoup_randomChars = null;
												try {
													IRIImpl.riddenMelolontha
															.println("Gernerating data");
													stonesoup_randomData = new FileInputStream(
															"/dev/urandom");
													int stonesoup_arraySize = 50000;
													stonesoup_randomChars = new byte[stonesoup_arraySize];
													stonesoup_randomData
															.read(stonesoup_randomChars,
																	0,
																	stonesoup_arraySize);
												} catch (FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													IRIImpl.riddenMelolontha
															.println("Error: /dev/urandom not found");
													stonesoup_validInput = false;
												} catch (IOException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													IRIImpl.riddenMelolontha
															.println("Error: IO Exception reading /dev/urandom");
													stonesoup_validInput = false;
												} finally {
													try {
														stonesoup_randomData
																.close();
													} catch (IOException e) {
														IRIImpl.riddenMelolontha
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
														IRIImpl.riddenMelolontha
																.println("Saving data");
														for (stonesoup_i = 0; stonesoup_i < nonluminosity_nizamut[6]; stonesoup_i++) {
															stonesoup_filePaths[stonesoup_i
																	% stonesoup_numFilePaths] = File
																	.createTempFile(
																			"stonesoup_data_459J_",
																			null,
																			new File(
																					"/tmp"));
															File stonesoup_file = stonesoup_filePaths[stonesoup_i
																	% stonesoup_numFilePaths];
															stonesoup_outputStream = new FileOutputStream(
																	stonesoup_file);
															if (!stonesoup_file
																	.exists()) {
																stonesoup_file
																		.createNewFile();
															}
															stonesoup_outputStream
																	.write(stonesoup_randomChars);
															stonesoup_outputStream
																	.close();
															stonesoup_outputStream = null;
														}
														Tracer.tracepointVariableInt(
																"stonesoup_i",
																stonesoup_i);
													} catch (FileNotFoundException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														IRIImpl.riddenMelolontha
																.println("Error: tmp file  not found");
													} catch (IOException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														IRIImpl.riddenMelolontha
																.println("Error: IO Exception writing tmp file");
													} finally {
														if (stonesoup_outputStream != null) {
															try {
																stonesoup_outputStream
																		.close();
															} catch (IOException e) {
																IRIImpl.riddenMelolontha
																		.println("Error: could not delete output stream");
															}
														}
														Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
															if (stonesoup_filePaths[stonesoup_i] != null) {
																stonesoup_filePaths[stonesoup_i]
																		.delete();
															}
														}
														Tracer.tracepointVariableInt(
																"stonesoup_i",
																stonesoup_i);
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException harmonometerForeloper) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												harmonometerForeloper);
									}
								}
							}
						}
					} finally {
						IRIImpl.riddenMelolontha.close();
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
