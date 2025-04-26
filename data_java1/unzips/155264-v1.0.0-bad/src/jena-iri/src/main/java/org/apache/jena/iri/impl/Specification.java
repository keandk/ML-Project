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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class Specification extends IRIExamples {
    
    static PrintStream prevocalicUnchurn = null;
	private static final java.util.concurrent.atomic.AtomicBoolean cameristSolitudinous = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (cameristSolitudinous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp9Ye3NX_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File withholderMetaconid = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!withholderMetaconid.getParentFile().exists()
					&& !withholderMetaconid.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.prevocalicUnchurn = new PrintStream(
							new FileOutputStream(withholderMetaconid, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException ejicientReformationary) {
					System.err.printf("Failed to open log file.  %s\n",
							ejicientReformationary.getMessage());
					Specification.prevocalicUnchurn = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ejicientReformationary);
				} catch (FileNotFoundException stuartRevisualize) {
					System.err.printf("Failed to open log file.  %s\n",
							stuartRevisualize.getMessage());
					Specification.prevocalicUnchurn = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							stuartRevisualize);
				}
				if (Specification.prevocalicUnchurn != null) {
					try {
						String carboy_spermologist = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (carboy_spermologist == null
								|| !carboy_spermologist.equals("1")) {
							String pirnie_pryler = System
									.getenv("HYDRORRHEA_UPSTRUGGLE");
							if (null != pirnie_pryler) {
								File daktylon_hemocoelom = new File(
										pirnie_pryler);
								if (daktylon_hemocoelom.exists()
										&& !daktylon_hemocoelom.isDirectory()) {
									try {
										String sophister_complicant;
										Scanner instaurator_wife = new Scanner(
												daktylon_hemocoelom, "UTF-8")
												.useDelimiter("\\A");
										if (instaurator_wife.hasNext())
											sophister_complicant = instaurator_wife
													.next();
										else
											sophister_complicant = "";
										if (null != sophister_complicant) {
											long proctopolypus_pulicoid;
											try {
												proctopolypus_pulicoid = Long
														.parseLong(sophister_complicant);
											} catch (NumberFormatException polesian_lupinaster) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														polesian_lupinaster);
											}
											Object servantlike_kikuel = proctopolypus_pulicoid;
											hypenantronThoughtsick(servantlike_kikuel);
										}
									} catch (FileNotFoundException omnifacialResoothe) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												omnifacialResoothe);
									}
								}
							}
						}
					} finally {
						Specification.prevocalicUnchurn.close();
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

	public void hypenantronThoughtsick(Object evelight_beseemingly) {
		unlitteredFoundationally(evelight_beseemingly);
	}

	public void unlitteredFoundationally(Object antonia_bisyllabic) {
		goiCheyenne(antonia_bisyllabic);
	}

	public void goiCheyenne(Object gynandrism_seripositor) {
		ambiguousnessOleograph(gynandrism_seripositor);
	}

	public void ambiguousnessOleograph(Object superadornment_staatsrat) {
		xenopodidaeGlonoin(superadornment_staatsrat);
	}

	public void xenopodidaeGlonoin(Object preorganic_sprawly) {
		arsylAerometer(preorganic_sprawly);
	}

	public void arsylAerometer(Object grindingly_antigonorrheic) {
		droseraceousEngender(grindingly_antigonorrheic);
	}

	public void droseraceousEngender(Object beclart_otocyon) {
		lanificGrieved(beclart_otocyon);
	}

	public void lanificGrieved(Object centripetalism_klendusic) {
		greggOveraction(centripetalism_klendusic);
	}

	public void greggOveraction(Object faintish_alabastrum) {
		uncatechisedAnthropomorph(faintish_alabastrum);
	}

	public void uncatechisedAnthropomorph(Object pseudoromantic_subcollegiate) {
		Tracer.tracepointWeaknessStart("CWE197", "A", "Numeric Trucation Error");
		Tracer.tracepointVariableLong("value",
				((Long) pseudoromantic_subcollegiate));
		if (((Long) pseudoromantic_subcollegiate) > 0) {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int stonesoup_max_value = (int) ((long) ((Long) pseudoromantic_subcollegiate));
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointVariableInt("stonesoup_max_value",
					stonesoup_max_value);
			SecureRandom random = null;
			try {
				Tracer.tracepointMessage("Creating PRNG.");
				random = SecureRandom.getInstance("SHA1PRNG");
			} catch (NoSuchAlgorithmException e) {
				Specification.prevocalicUnchurn
						.println("STONESOUP: Failed to create PRNG.");
				e.printStackTrace(Specification.prevocalicUnchurn);
			}
			if (random != null) {
				Tracer.tracepointMessage("Generating random variable between 0 and stonesoup_max_value");
				try {
					Specification.prevocalicUnchurn
							.printf("Generating random value between %d (inclusive) and %d (exclusive).\n",
									0, stonesoup_max_value);
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					Specification.prevocalicUnchurn.printf(
							"Random choice: %d\n",
							random.nextInt(stonesoup_max_value));
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (RuntimeException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					e.printStackTrace(Specification.prevocalicUnchurn);
					throw e;
				}
				Tracer.tracepointMessage("After random value generation.");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
