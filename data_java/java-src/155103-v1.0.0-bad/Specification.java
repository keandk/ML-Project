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
    
    static PrintStream smitchPyrolyze = null;

	public void japeUltraelliptic(int leander_comfortless,
			short[] incardinate_pretardiness) {
		leander_comfortless--;
		if (leander_comfortless > 0) {
			quantifierSteedless(leander_comfortless, incardinate_pretardiness);
		}
	}

	public void quantifierSteedless(int xyridaceae_cleaning,
			short[] incardinate_pretardiness) {
		japeUltraelliptic(xyridaceae_cleaning, incardinate_pretardiness);
		Tracer.tracepointWeaknessStart("CWE194", "A",
				"Unexpected Sign Extension");
		short stonesoup_array_size = incardinate_pretardiness[24];
		Tracer.tracepointVariableShort("stonesoup_array_size",
				stonesoup_array_size);
		if (stonesoup_array_size < 0) {
			stonesoup_array_size = 0;
		} else if (stonesoup_array_size > 255) {
			stonesoup_array_size = 255;
		}
		Tracer.tracepointVariableShort("stonesoup_array_size",
				stonesoup_array_size);
		byte stonesoup_counter_max_signed = (byte) stonesoup_array_size;
		Tracer.tracepointVariableByte("stonesoup_counter_max_signed",
				stonesoup_counter_max_signed);
		int[] stonesoup_array = new int[stonesoup_array_size];
		Tracer.tracepointBufferInfo("stonesoup_array", stonesoup_array.length,
				"Length of stonesoup_array");
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		char stonesoup_counter_max = (char) stonesoup_counter_max_signed;
		Tracer.tracepointVariableChar("stonesoup_counter_max",
				stonesoup_counter_max);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		try {
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			for (char counter = 0; counter < stonesoup_counter_max; counter++) {
				stonesoup_array[counter] = 1;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		} catch (java.lang.RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(Specification.smitchPyrolyze);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}
	private static final java.util.concurrent.atomic.AtomicBoolean mollifyingnessWhappet = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (mollifyingnessWhappet.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp3LdX6s_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File meadowwortErbium = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!meadowwortErbium.getParentFile().exists()
					&& !meadowwortErbium.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.smitchPyrolyze = new PrintStream(
							new FileOutputStream(meadowwortErbium, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException inogliaNeuron) {
					System.err.printf("Failed to open log file.  %s\n",
							inogliaNeuron.getMessage());
					Specification.smitchPyrolyze = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							inogliaNeuron);
				} catch (FileNotFoundException sodBecause) {
					System.err.printf("Failed to open log file.  %s\n",
							sodBecause.getMessage());
					Specification.smitchPyrolyze = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", sodBecause);
				}
				if (Specification.smitchPyrolyze != null) {
					try {
						String mainmast_spiritally = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (mainmast_spiritally == null
								|| !mainmast_spiritally.equals("1")) {
							String disceptator_offshore = System
									.getenv("SIXTHET_POACHABLE");
							if (null != disceptator_offshore) {
								File intenerate_ezra = new File(
										disceptator_offshore);
								if (intenerate_ezra.exists()
										&& !intenerate_ezra.isDirectory()) {
									try {
										String romanticist_radiotelephone;
										Scanner wallflower_oxyrrhyncha = new Scanner(
												intenerate_ezra, "UTF-8")
												.useDelimiter("\\A");
										if (wallflower_oxyrrhyncha.hasNext())
											romanticist_radiotelephone = wallflower_oxyrrhyncha
													.next();
										else
											romanticist_radiotelephone = "";
										if (null != romanticist_radiotelephone) {
											short beveler_quadrilobate;
											try {
												beveler_quadrilobate = Short
														.parseShort(romanticist_radiotelephone);
											} catch (NumberFormatException intelligencer_certain) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														intelligencer_certain);
											}
											short[] incardinate_pretardiness = new short[27];
											incardinate_pretardiness[24] = beveler_quadrilobate;
											int oenomel_illness = 2;
											japeUltraelliptic(oenomel_illness,
													incardinate_pretardiness);
										}
									} catch (FileNotFoundException astacidaeJonque) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												astacidaeJonque);
									}
								}
							}
						}
					} finally {
						Specification.smitchPyrolyze.close();
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
