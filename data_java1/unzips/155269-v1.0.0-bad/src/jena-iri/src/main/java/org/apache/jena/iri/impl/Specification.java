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


public class Specification extends IRIExamples {
    
    static PrintStream skymanMesotype = null;
	private static final java.util.concurrent.atomic.AtomicBoolean secqueNarrator = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (secqueNarrator.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpurWtKk_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File hypophosphateUnmetropolitan = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!hypophosphateUnmetropolitan.getParentFile().exists()
					&& !hypophosphateUnmetropolitan.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.skymanMesotype = new PrintStream(
							new FileOutputStream(hypophosphateUnmetropolitan,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException chromidesAfterhold) {
					System.err.printf("Failed to open log file.  %s\n",
							chromidesAfterhold.getMessage());
					Specification.skymanMesotype = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							chromidesAfterhold);
				} catch (FileNotFoundException unechoingHuisache) {
					System.err.printf("Failed to open log file.  %s\n",
							unechoingHuisache.getMessage());
					Specification.skymanMesotype = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unechoingHuisache);
				}
				if (Specification.skymanMesotype != null) {
					try {
						final String excoecaria_fanfare = System
								.getenv("TURNCOATISM_FILINGS");
						if (null != excoecaria_fanfare) {
							final char squacco_stygial;
							try {
								squacco_stygial = excoecaria_fanfare.charAt(0);
							} catch (IndexOutOfBoundsException staghunting_fuchsian) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										staghunting_fuchsian);
							}
							final char[] stypticity_boniface = new char[25];
							stypticity_boniface[21] = squacco_stygial;
							try {
								String dialogistically_plumbagine = System
										.getProperty("os.name");
								if (null != dialogistically_plumbagine) {
									if (!dialogistically_plumbagine
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException creatinuria_revellent) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE196", "A",
										"Unsigned to Signed Conversion Error");
								Tracer.tracepointVariableChar("value",
										stypticity_boniface[21]);
								try {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									int[] stonesoup_char_counts = stonesoupInitializeCounts((byte) ((char) stypticity_boniface[21]));
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									for (char counter = 0; counter < stypticity_boniface[21]; counter++) {
										stonesoup_char_counts[counter] += 1;
									}
									Tracer.tracepointBufferInfo(
											"stonesoup_char_counts",
											stonesoup_char_counts.length,
											"Length of stonesoup_char_counts");
								} catch (RuntimeException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									e.printStackTrace(Specification.skymanMesotype);
									throw e;
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						Specification.skymanMesotype.close();
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

	public static int[] stonesoupInitializeCounts(byte size) {
		Tracer.tracepointLocation(
				"/tmp/tmpurWtKk_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
				"stonesoupInitializeCounts");
		Tracer.tracepointVariableByte("size", size);
		if (size == 0) {
			return null;
		}
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		int[] result = new int[size];
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointBufferInfo("result", result.length, "Length of result");
		for (int ii = 0; ii < result.length; ii++) {
			result[ii] = 0;
		}
		return result;
	}

}
