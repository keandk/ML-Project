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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    static PrintStream proparasceveInhabitress = null;
	private static final java.util.concurrent.atomic.AtomicBoolean monosymmetricalPomo = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (monosymmetricalPomo.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpC3TUHm_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File incitiveMulciber = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!incitiveMulciber.getParentFile().exists()
					&& !incitiveMulciber.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.proparasceveInhabitress = new PrintStream(
							new FileOutputStream(incitiveMulciber, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException criminologyStrad) {
					System.err.printf("Failed to open log file.  %s\n",
							criminologyStrad.getMessage());
					IRIImpl.proparasceveInhabitress = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							criminologyStrad);
				} catch (FileNotFoundException brigandineUnrebukable) {
					System.err.printf("Failed to open log file.  %s\n",
							brigandineUnrebukable.getMessage());
					IRIImpl.proparasceveInhabitress = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							brigandineUnrebukable);
				}
				if (IRIImpl.proparasceveInhabitress != null) {
					try {
						String proscriber_hypothecatory = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (proscriber_hypothecatory == null
								|| !proscriber_hypothecatory.equals("1")) {
							String funereally_tactometer = System
									.getenv("CIRCUMAVIATE_CALIBRATION");
							if (null != funereally_tactometer) {
								File perfectiveness_psychopetal = new File(
										funereally_tactometer);
								if (perfectiveness_psychopetal.exists()
										&& !perfectiveness_psychopetal
												.isDirectory()) {
									try {
										String substyle_anthropogenic;
										Scanner scurvily_coquicken = new Scanner(
												perfectiveness_psychopetal,
												"UTF-8").useDelimiter("\\A");
										if (scurvily_coquicken.hasNext())
											substyle_anthropogenic = scurvily_coquicken
													.next();
										else
											substyle_anthropogenic = "";
										if (null != substyle_anthropogenic) {
											int snooperscope_intermaxilla;
											try {
												snooperscope_intermaxilla = Integer
														.parseInt(substyle_anthropogenic);
											} catch (NumberFormatException curtilage_philatelist) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														curtilage_philatelist);
											}
											int[] stoot_sledder = new int[11];
											stoot_sledder[10] = snooperscope_intermaxilla;
											StromateoidMoonjah workplace_caunos = new StromateoidMoonjah();
											workplace_caunos
													.theosophicPreacher(stoot_sledder);
										}
									} catch (FileNotFoundException muddShaking) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												muddShaking);
									}
								}
							}
						}
					} finally {
						IRIImpl.proparasceveInhabitress.close();
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

	public static class StromateoidMoonjah {
		public void theosophicPreacher(int[] indecorum_unschool) {
			OncologicalIrreverently tautometer_probationer = new OncologicalIrreverently();
			tautometer_probationer.aspiculousLeiotrichinae(indecorum_unschool);
		}
	}

	public static class OncologicalIrreverently {
		public void aspiculousLeiotrichinae(int[] acetoxyl_germiparity) {
			StringybarkAdze weirangle_generalcy = new StringybarkAdze();
			weirangle_generalcy.veineryEnglishman(acetoxyl_germiparity);
		}
	}

	public static class StringybarkAdze {
		public void veineryEnglishman(int[] cacoglossia_hierarchal) {
			RidablyTemperament grope_trianthous = new RidablyTemperament();
			grope_trianthous.seaterTrombonist(cacoglossia_hierarchal);
		}
	}

	public static class RidablyTemperament {
		public void seaterTrombonist(int[] unfeasible_postdental) {
			ChafflessVoraciously contratempo_lombardian = new ChafflessVoraciously();
			contratempo_lombardian.ununiformEmpaper(unfeasible_postdental);
		}
	}

	public static class ChafflessVoraciously {
		public void ununiformEmpaper(int[] pectoralgia_leaseholder) {
			TextualSockeye unshortened_acraspedote = new TextualSockeye();
			unshortened_acraspedote
					.greenboneOverfullness(pectoralgia_leaseholder);
		}
	}

	public static class TextualSockeye {
		public void greenboneOverfullness(int[] frication_casse) {
			NectariniidaeIsocephalous upgrave_anamnia = new NectariniidaeIsocephalous();
			upgrave_anamnia.drowninglyGypsyweed(frication_casse);
		}
	}

	public static class NectariniidaeIsocephalous {
		public void drowninglyGypsyweed(int[] nonappearing_blowy) {
			LeviticallySirloin supervirulent_cupping = new LeviticallySirloin();
			supervirulent_cupping.womanishlyChanguina(nonappearing_blowy);
		}
	}

	public static class LeviticallySirloin {
		public void womanishlyChanguina(int[] hypersthenite_ethnologically) {
			DraggedSensist canelo_halawi = new DraggedSensist();
			canelo_halawi.inquestualInvidiously(hypersthenite_ethnologically);
		}
	}

	public static class DraggedSensist {
		public void inquestualInvidiously(int[] behaviored_semiupright) {
			JollopAbsampere woolulose_persecute = new JollopAbsampere();
			woolulose_persecute.genyantrumSwami(behaviored_semiupright);
		}
	}

	public static class JollopAbsampere {
		public void genyantrumSwami(int[] bureaucratic_anthill) {
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
				IRIImpl.proparasceveInhabitress
						.println("STONESOUP: Random generator algorithm does not exist.");
			}
			Tracer.tracepointVariableInt("value", bureaucratic_anthill[10]);
			if (random != null) {
				StringBuilder stonesoup_filename = new StringBuilder();
				IRIImpl.proparasceveInhabitress.println("Generating file name");
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				for (int stonesoup_counter = 0; stonesoup_counter < bureaucratic_anthill[10]; stonesoup_counter++) {
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
						IRIImpl.proparasceveInhabitress
								.println("Failed to create file.");
						IRIImpl.proparasceveInhabitress.println("Error:");
						e.printStackTrace(IRIImpl.proparasceveInhabitress);
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
						IRIImpl.proparasceveInhabitress
								.println("Failed to create file.");
						e.printStackTrace(IRIImpl.proparasceveInhabitress);
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
