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
    
    private static final int repack_sampleman = 11;
	static PrintStream samarkandDynamitical = null;
	private static final java.util.concurrent.atomic.AtomicBoolean thermatologyPredisposedness = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (thermatologyPredisposedness.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpor_V9g_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File celloidGenitival = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!celloidGenitival.getParentFile().exists()
					&& !celloidGenitival.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.samarkandDynamitical = new PrintStream(
							new FileOutputStream(celloidGenitival, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException papyrotamiaMytilotoxine) {
					System.err.printf("Failed to open log file.  %s\n",
							papyrotamiaMytilotoxine.getMessage());
					Specification.samarkandDynamitical = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							papyrotamiaMytilotoxine);
				} catch (FileNotFoundException prematerialPleatless) {
					System.err.printf("Failed to open log file.  %s\n",
							prematerialPleatless.getMessage());
					Specification.samarkandDynamitical = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							prematerialPleatless);
				}
				if (Specification.samarkandDynamitical != null) {
					try {
						String odontosis_metanilic = System
								.getenv("HARDHEAD_TIBIOFEMORAL");
						if (null != odontosis_metanilic) {
							int unsurmountable_deamination;
							try {
								unsurmountable_deamination = Integer
										.parseInt(odontosis_metanilic);
							} catch (NumberFormatException hypsophyllar_hygrostomia) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										hypsophyllar_hygrostomia);
							}
							int[] gymnophiona_sov = new int[22];
							gymnophiona_sov[repack_sampleman] = unsurmountable_deamination;
							int asymptotically_whistler = 0;
							while (true) {
								asymptotically_whistler++;
								if (asymptotically_whistler >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart("CWE369", "A",
									"Divide By Zero");
							Tracer.tracepointVariableInt("value",
									gymnophiona_sov[repack_sampleman]);
							if (gymnophiona_sov[repack_sampleman] != 0) {
								try {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									int random = (8191 * gymnophiona_sov[repack_sampleman])
											% (1 << 15);
									Tracer.tracepointVariableInt("random",
											random);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									int factor = (1 << 31) % random;
									Tracer.tracepointVariableInt("factor",
											factor);
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									Specification.samarkandDynamitical.printf(
											"Random Factor: %d\n", factor);
								} catch (java.lang.RuntimeException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									e.printStackTrace(Specification.samarkandDynamitical);
									throw e;
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						Specification.samarkandDynamitical.close();
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
