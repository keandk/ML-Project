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
import java.util.Arrays;


public class Specification extends IRIExamples {
    
    private static final int typhoon_decoratively = 15;

	public void quashySapropelic(int ross_pentene, int[] sike_secessioner) {
		if (ross_pentene > 10) {
			quashySapropelic(ross_pentene++, sike_secessioner);
		}
		Tracer.tracepointWeaknessStart("CWE789", "A",
				"Uncontrolled Memory Allocation");
		try {
			if (sike_secessioner[typhoon_decoratively] > 0
					&& sike_secessioner[typhoon_decoratively] <= Integer.MAX_VALUE) {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				stonesoup_array = new char[sike_secessioner[typhoon_decoratively]];
				Tracer.tracepointBufferInfo("stonesoup_array",
						stonesoup_array.length, "Length of stonesoup_array");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				Arrays.fill(stonesoup_array, 'x');
				for (int i = 0; i < stonesoup_array.length; i++) {
					Specification.afterburnerTinamine.print(stonesoup_array[i]);
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Specification.afterburnerTinamine.println("");
				Specification.afterburnerTinamine
						.println("STONESOUP: successfully initialized array");
			}
		} catch (Error e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(Specification.afterburnerTinamine);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}
	static PrintStream afterburnerTinamine = null;
	private static final java.util.concurrent.atomic.AtomicBoolean colludeRadication = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (colludeRadication.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp9uKqeC_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File ladderlikeUngnostic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!ladderlikeUngnostic.getParentFile().exists()
					&& !ladderlikeUngnostic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.afterburnerTinamine = new PrintStream(
							new FileOutputStream(ladderlikeUngnostic, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException unmelodizedEternize) {
					System.err.printf("Failed to open log file.  %s\n",
							unmelodizedEternize.getMessage());
					Specification.afterburnerTinamine = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unmelodizedEternize);
				} catch (FileNotFoundException extraphysicalVentriloquistic) {
					System.err.printf("Failed to open log file.  %s\n",
							extraphysicalVentriloquistic.getMessage());
					Specification.afterburnerTinamine = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							extraphysicalVentriloquistic);
				}
				if (Specification.afterburnerTinamine != null) {
					try {
						String disepalous_therence = System
								.getenv("UNFRUITFUL_DISEMBOCATION");
						if (null != disepalous_therence) {
							int archpatriarch_nympha;
							try {
								archpatriarch_nympha = Integer
										.parseInt(disepalous_therence);
							} catch (NumberFormatException acerbate_halawi) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										acerbate_halawi);
							}
							int[] preadjustment_poetasterism = new int[22];
							preadjustment_poetasterism[typhoon_decoratively] = archpatriarch_nympha;
							int overcount_bemoon = 0;
							quashySapropelic(overcount_bemoon,
									preadjustment_poetasterism);
						}
					} finally {
						Specification.afterburnerTinamine.close();
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
	static char[] stonesoup_array;

}
