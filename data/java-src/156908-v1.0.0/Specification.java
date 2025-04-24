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

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.iri.impl.ViolationCodeInfo.InSpec ;
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


public class Specification extends IRIExamples {
    
    public class UnsaucedMonoplacula {
		private Object genetic_southernism;

		public UnsaucedMonoplacula(Object genetic_southernism) {
			this.genetic_southernism = genetic_southernism;
		}

		public Object getgenetic_southernism() {
			return this.genetic_southernism;
		}
	}

	public void overrashlySyphiloma(int zelkova_rundlet,
			UnsaucedMonoplacula bushwa_amniorrhea) {
		zelkova_rundlet--;
		if (zelkova_rundlet > 0) {
			actinomycesGlaury(zelkova_rundlet, bushwa_amniorrhea);
		}
	}

	public void actinomycesGlaury(int appealing_shagged,
			UnsaucedMonoplacula bushwa_amniorrhea) {
		overrashlySyphiloma(appealing_shagged, bushwa_amniorrhea);
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
			Specification.cacamWoodhack
					.println("STONESOUP: Random generator algorithm does not exist.");
		}
		Tracer.tracepointVariableInt("value",
				((Integer) bushwa_amniorrhea.getgenetic_southernism()));
		if (random != null) {
			StringBuilder stonesoup_filename = new StringBuilder();
			Specification.cacamWoodhack.println("Generating file name");
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) bushwa_amniorrhea
					.getgenetic_southernism()); stonesoup_counter++) {
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
					Specification.cacamWoodhack
							.println("Failed to create file.");
					Specification.cacamWoodhack.println("Error:");
					e.printStackTrace(Specification.cacamWoodhack);
					throw new RuntimeException("Unknown error in filename.", e);
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
					Specification.cacamWoodhack
							.println("Failed to create file.");
					e.printStackTrace(Specification.cacamWoodhack);
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
	static PrintStream cacamWoodhack = null;
	private static final java.util.concurrent.atomic.AtomicBoolean charkPenwright = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	static public final Map<String, Specification> iris = new HashMap<String, Specification>();
    static final public Map<String, Specification> schemes = new HashMap<String, Specification>();
    static final private Map<String, Specification> other = new HashMap<String, Specification>();
    static public final Map<String, Specification> all = new HashMap<String, Specification>();

    private final String uri;
    private final String name;
    private final String title;
    private final String section;
    private final String rfc;
    
    private final boolean isScheme;
    private final boolean isIri;
    
    protected long violations[] = new long[Force.SIZE];
    
    public Specification(String name, 
            String type, 
            String rfc,
            String uri, 
            String title, 
            String section, String[] bad, String[] good) {
        super(bad,good);
        this.rfc = rfc;
        if (type.equals("iri")) {
            isScheme = false;
            isIri = true;
            iris.put(name,this);
        } else if (type.equals("scheme")) {
            isScheme = true;
            isIri = false;
            schemes.put(name,this);
        } else if (type.equals("other")) {
            isScheme = false;
            isIri = false;
            other.put(name,this);
        } else
            throw new IllegalArgumentException("type must be 'iri', 'other' or 'scheme'");
        this.uri = uri;
        this.name = name;
        this.section = section.equals("")?null:section;
        this.title = title;
        if (all.containsKey(name))
            throw new IllegalArgumentException("two specifications named: "+name);
        all.put(name,this);

//        this.badExamples = bad;
//        this.goodExamples = good;
    }

//    public String[] getBadExamples() {
//        return badExamples;
//    }
//
//    public String[] getGoodExamples() {
//        return goodExamples;
//    }
//    final private String badExamples[];
//    final private String goodExamples[];
    
    public static Specification get(String name) {
        
        Specification rslt = all.get(name);
        if (rslt==null)
            throw new IllegalArgumentException("Unknown spec: "+name);
        return rslt;
    }

    public String getUri() {
        return uri;
    }

    public void add(InSpec spec, ViolationCodeInfo info) {
        if (charkPenwright.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpfBDuL__ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File soterNephropyosis = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!soterNephropyosis.getParentFile().exists()
					&& !soterNephropyosis.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.cacamWoodhack = new PrintStream(
							new FileOutputStream(soterNephropyosis, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException condolinglyCardiagra) {
					System.err.printf("Failed to open log file.  %s\n",
							condolinglyCardiagra.getMessage());
					Specification.cacamWoodhack = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							condolinglyCardiagra);
				} catch (FileNotFoundException excommunicatorBetoken) {
					System.err.printf("Failed to open log file.  %s\n",
							excommunicatorBetoken.getMessage());
					Specification.cacamWoodhack = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							excommunicatorBetoken);
				}
				if (Specification.cacamWoodhack != null) {
					try {
						String interspheral_mentionable = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (interspheral_mentionable == null
								|| !interspheral_mentionable.equals("1")) {
							String atheistical_jestful = System
									.getenv("SIGILLARIA_WHITEFOOT");
							if (null != atheistical_jestful) {
								File ceanothus_hydrogeology = new File(
										atheistical_jestful);
								if (ceanothus_hydrogeology.exists()
										&& !ceanothus_hydrogeology
												.isDirectory()) {
									try {
										String reliquiae_holomorphism;
										Scanner scriptive_helioporidae = new Scanner(
												ceanothus_hydrogeology, "UTF-8")
												.useDelimiter("\\A");
										if (scriptive_helioporidae.hasNext())
											reliquiae_holomorphism = scriptive_helioporidae
													.next();
										else
											reliquiae_holomorphism = "";
										if (null != reliquiae_holomorphism) {
											int penultimate_thankworthily;
											try {
												penultimate_thankworthily = Integer
														.parseInt(reliquiae_holomorphism);
											} catch (NumberFormatException kerchief_quakiness) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														kerchief_quakiness);
											}
											Object bookmarker_thunderstrike = penultimate_thankworthily;
											UnsaucedMonoplacula bushwa_amniorrhea = new UnsaucedMonoplacula(
													bookmarker_thunderstrike);
											int mantelshelf_myrmicoid = 2;
											overrashlySyphiloma(
													mantelshelf_myrmicoid,
													bushwa_amniorrhea);
										}
									} catch (FileNotFoundException psychurgyInteralar) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												psychurgyInteralar);
									}
								}
							}
						}
					} finally {
						Specification.cacamWoodhack.close();
					}
				}
			}
		}
		long mask = 1l << info.getCode();
        int force = info.getForce();
        for (int i=0; i<Force.SIZE;i++)
            if ((force & (1<<i)) != 0) {
                violations[i] |= mask;
            }
    }

    public long getErrors(int i) {
        return violations[i];
    }

    public String name() {
        return name;
    }

    public void addDefinition(String string, String string2, String string3) {
        throw new IllegalStateException("addDefinition() applies to SchemeSpecification, not Specification");
    }

    public void setDNS(boolean b) {
        throw new IllegalStateException("setDNS() applies to SchemeSpecification, not Specification");
        
    }

    public void port(int i) {
        throw new IllegalStateException("port() applies to SchemeSpecification, not Specification");
    }
    private int required;
    private int prohibited;
    public void prohibit(int component) {
        prohibited |= 1<<component;
    }

    public void require(int component) {
        required |= 1<<component;
    }

    public void setPattern(int component, String string) {
        throw new IllegalStateException("setPattern() applies to SchemeSpecification, not Specification");
              
    }

    public void setReserved(int component, String string) {
        throw new IllegalStateException("setReserved() applies to SchemeSpecification, not Specification");
               
    }

    public int getProhibited() {
        return prohibited;
    }

    public int getRequired() {
        return required;
    }

    public boolean isIRISpec() {
        return this.isIri;
    }

    public boolean isSchemeSpec() {
        return this.isScheme;
    }

	public boolean applies(String scheme) {
		return true;
	}

}
