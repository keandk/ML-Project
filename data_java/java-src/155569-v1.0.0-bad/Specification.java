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


public class Specification extends IRIExamples {
    
    public class IntoxicatedBoundedly {
		private String[] termlessly_surquidry;

		public IntoxicatedBoundedly(String[] termlessly_surquidry) {
			this.termlessly_surquidry = termlessly_surquidry;
		}

		public String[] gettermlessly_surquidry() {
			return this.termlessly_surquidry;
		}
	}

	static PrintStream hieroglyphologyColligible = null;
	private static final java.util.concurrent.atomic.AtomicBoolean bishoplingFlavine = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (bishoplingFlavine.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpGH9FtW_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File biarcuateNonextradition = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!biarcuateNonextradition.getParentFile().exists()
					&& !biarcuateNonextradition.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.hieroglyphologyColligible = new PrintStream(
							new FileOutputStream(biarcuateNonextradition, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException revolatilizeMetallism) {
					System.err.printf("Failed to open log file.  %s\n",
							revolatilizeMetallism.getMessage());
					Specification.hieroglyphologyColligible = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							revolatilizeMetallism);
				} catch (FileNotFoundException cominternTuberosity) {
					System.err.printf("Failed to open log file.  %s\n",
							cominternTuberosity.getMessage());
					Specification.hieroglyphologyColligible = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cominternTuberosity);
				}
				if (Specification.hieroglyphologyColligible != null) {
					try {
						String pigeonholer_scorbutus = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (pigeonholer_scorbutus == null
								|| !pigeonholer_scorbutus.equals("1")) {
							String lissencephalous_unguligrade = System
									.getenv("SOUTHEASTWARDLY_PALLIARD");
							if (null != lissencephalous_unguligrade) {
								File tolsester_olenellus = new File(
										lissencephalous_unguligrade);
								if (tolsester_olenellus.exists()
										&& !tolsester_olenellus.isDirectory()) {
									try {
										String givey_affronter;
										Scanner radiculectomy_eucharistically = new Scanner(
												tolsester_olenellus, "UTF-8")
												.useDelimiter("\\A");
										if (radiculectomy_eucharistically
												.hasNext())
											givey_affronter = radiculectomy_eucharistically
													.next();
										else
											givey_affronter = "";
										if (null != givey_affronter) {
											String[] dingledangle_dermatozoon = new String[20];
											dingledangle_dermatozoon[7] = givey_affronter;
											IntoxicatedBoundedly estimate_perpetually = new IntoxicatedBoundedly(
													dingledangle_dermatozoon);
											eupatridGoniostat(estimate_perpetually);
										}
									} catch (FileNotFoundException intuitionisticInterlinguist) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												intuitionisticInterlinguist);
									}
								}
							}
						}
					} finally {
						Specification.hieroglyphologyColligible.close();
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

	public void eupatridGoniostat(IntoxicatedBoundedly nahuatlecan_mytilus) {
		overlainFelliducous(nahuatlecan_mytilus);
	}

	public void overlainFelliducous(IntoxicatedBoundedly hagiographal_unchastity) {
		aurideCoreveler(hagiographal_unchastity);
	}

	public void aurideCoreveler(IntoxicatedBoundedly diplanar_cassis) {
		unartisticalJactancy(diplanar_cassis);
	}

	public void unartisticalJactancy(IntoxicatedBoundedly ventriculous_progamete) {
		cupressineousPatera(ventriculous_progamete);
	}

	public void cupressineousPatera(IntoxicatedBoundedly avoucher_iridization) {
		sulaFormica(avoucher_iridization);
	}

	public void sulaFormica(IntoxicatedBoundedly pothousey_adela) {
		affecterRoutivarite(pothousey_adela);
	}

	public void affecterRoutivarite(
			IntoxicatedBoundedly selectionism_formularist) {
		elotilloUnbrave(selectionism_formularist);
	}

	public void elotilloUnbrave(IntoxicatedBoundedly jodel_stupefiedness) {
		adamTheophany(jodel_stupefiedness);
	}

	public void adamTheophany(IntoxicatedBoundedly seasonal_fothergilla) {
		chordacentrousHoodwort(seasonal_fothergilla);
	}

	public void chordacentrousHoodwort(IntoxicatedBoundedly alveolar_impingence) {
		Tracer.tracepointWeaknessStart("CWE835", "A", "Infinite Loop");
		Tracer.tracepointVariableString("stonesoup_taintedValue",
				alveolar_impingence.gettermlessly_surquidry()[7]);
		int stonesoup_i = 0;
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		while (stonesoup_i < alveolar_impingence.gettermlessly_surquidry()[7]
				.length()) {
			Specification.hieroglyphologyColligible.print(alveolar_impingence
					.gettermlessly_surquidry()[7].charAt(stonesoup_i));
			if (alveolar_impingence.gettermlessly_surquidry()[7]
					.charAt(stonesoup_i) >= 48) {
				stonesoup_i++;
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Specification.hieroglyphologyColligible
				.println("\nfinished evaluating\n");
		Tracer.tracepointWeaknessEnd();
	}

}
