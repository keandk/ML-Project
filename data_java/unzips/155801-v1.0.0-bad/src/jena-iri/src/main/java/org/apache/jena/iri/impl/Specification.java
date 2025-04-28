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
import java.math.BigInteger;


public class Specification extends IRIExamples {
    
    public class FluorescigenicSporulate<T> {
		private T amylopsin_photosynthetic;

		public FluorescigenicSporulate(T amylopsin_photosynthetic) {
			this.amylopsin_photosynthetic = amylopsin_photosynthetic;
		}

		public T getamylopsin_photosynthetic() {
			return this.amylopsin_photosynthetic;
		}
	}

	static PrintStream karmathianTeerer = null;
	private static final java.util.concurrent.atomic.AtomicBoolean meratiaRetardate = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (meratiaRetardate.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpD9zaUG_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File unpetitionedSlottery = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unpetitionedSlottery.getParentFile().exists()
					&& !unpetitionedSlottery.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.karmathianTeerer = new PrintStream(
							new FileOutputStream(unpetitionedSlottery, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException endocoeliacMicrology) {
					System.err.printf("Failed to open log file.  %s\n",
							endocoeliacMicrology.getMessage());
					Specification.karmathianTeerer = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							endocoeliacMicrology);
				} catch (FileNotFoundException pachyemiaLariat) {
					System.err.printf("Failed to open log file.  %s\n",
							pachyemiaLariat.getMessage());
					Specification.karmathianTeerer = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pachyemiaLariat);
				}
				if (Specification.karmathianTeerer != null) {
					try {
						String neurogastric_blennosis = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (neurogastric_blennosis == null
								|| !neurogastric_blennosis.equals("1")) {
							String chromomere_preopinionated = System
									.getenv("EVESTAR_THURROCK");
							if (null != chromomere_preopinionated) {
								File severish_signlike = new File(
										chromomere_preopinionated);
								if (severish_signlike.exists()
										&& !severish_signlike.isDirectory()) {
									try {
										String counterthrust_semblably;
										Scanner superclass_zieger = new Scanner(
												severish_signlike, "UTF-8")
												.useDelimiter("\\A");
										if (superclass_zieger.hasNext())
											counterthrust_semblably = superclass_zieger
													.next();
										else
											counterthrust_semblably = "";
										if (null != counterthrust_semblably) {
											String[] keracele_demitoilet = new String[27];
											keracele_demitoilet[9] = counterthrust_semblably;
											FluorescigenicSporulate<String[]> gastroplenic_prepeople = new FluorescigenicSporulate<String[]>(
													keracele_demitoilet);
											try {
												String raiser_onyx = System
														.getProperty("os.name");
												if (null != raiser_onyx) {
													if (!raiser_onyx
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException undefective_sudoric) {
												Tracer.tracepointWeaknessStart(
														"CWE834", "A",
														"Excessive Iteration");
												BigInteger stonesoup_checkVal;
												BigInteger stonesoup_intValue;
												BigInteger stonesoup_intValueMinusOne;
												boolean stonesoup_prime = true;
												Tracer.tracepointVariableString(
														"stonesoup_taintedValue",
														gastroplenic_prepeople
																.getamylopsin_photosynthetic()[9]);
												try {
													stonesoup_checkVal = new BigInteger(
															"2");
													stonesoup_intValue = new BigInteger(
															gastroplenic_prepeople
																	.getamylopsin_photosynthetic()[9]);
													stonesoup_intValueMinusOne = stonesoup_intValue
															.subtract(BigInteger.ONE);
													if (stonesoup_intValue
															.compareTo(BigInteger.ZERO) > 0) {
														Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														for (; stonesoup_checkVal
																.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
																.add(BigInteger.ONE)) {
															if (stonesoup_intValue
																	.mod(stonesoup_checkVal)
																	.compareTo(
																			BigInteger.ZERO) == 0) {
																stonesoup_prime = false;
																Specification.karmathianTeerer
																		.println("Not Prime");
																break;
															}
														}
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													}
												} catch (NumberFormatException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													Specification.karmathianTeerer
															.println("STONESOUP: Input string is not an integer");
												}
												Specification.karmathianTeerer
														.println("finished evaluating");
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException preanestheticMolybdonosus) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												preanestheticMolybdonosus);
									}
								}
							}
						}
					} finally {
						Specification.karmathianTeerer.close();
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
