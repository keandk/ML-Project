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
    
    public class SymphenomenaWhulk {
		private short disgig_barpost;

		public SymphenomenaWhulk(short disgig_barpost) {
			this.disgig_barpost = disgig_barpost;
		}

		public short getdisgig_barpost() {
			return this.disgig_barpost;
		}
	}

	public void falutinDysarthrosis(int christendie_transactioneer,
			SymphenomenaWhulk embryoniform_quidder) {
		christendie_transactioneer--;
		if (christendie_transactioneer > 0) {
			inflectedSoapwort(christendie_transactioneer, embryoniform_quidder);
		}
	}

	public void inflectedSoapwort(int chaetopterus_divinization,
			SymphenomenaWhulk embryoniform_quidder) {
		falutinDysarthrosis(chaetopterus_divinization, embryoniform_quidder);
		Tracer.tracepointWeaknessStart("CWE190", "B",
				"Integer Overflow or Wraparound");
		short stonesoup_checked_value = embryoniform_quidder
				.getdisgig_barpost();
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		if (stonesoup_checked_value <= 0) {
			stonesoup_checked_value = 1;
			Specification.polymastigousInsectivora
					.println("resetting value to 1");
		}
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		short stonesoup_counter = 2;
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		int lttngCtr = 99;
		while (stonesoup_counter < 10) {
			Specification.polymastigousInsectivora.println("Loop #"
					+ stonesoup_counter);
			if (stonesoup_counter > 0) {
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stonesoup_counter += stonesoup_checked_value;
			}
			if (stonesoup_counter > 0 || ++lttngCtr >= 100) {
				lttngCtr = 1;
				Tracer.tracepointVariableShort("stonesoup_counter",
						stonesoup_counter);
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		Specification.polymastigousInsectivora.println("finished evaluating");
		Tracer.tracepointWeaknessEnd();
	}
	static PrintStream polymastigousInsectivora = null;
	private static final java.util.concurrent.atomic.AtomicBoolean staminSnuck = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (staminSnuck.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp9ubSPR_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File potencyCarboxylic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!potencyCarboxylic.getParentFile().exists()
					&& !potencyCarboxylic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.polymastigousInsectivora = new PrintStream(
							new FileOutputStream(potencyCarboxylic, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException gushingnessDermochelys) {
					System.err.printf("Failed to open log file.  %s\n",
							gushingnessDermochelys.getMessage());
					Specification.polymastigousInsectivora = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							gushingnessDermochelys);
				} catch (FileNotFoundException bashlykStreltzi) {
					System.err.printf("Failed to open log file.  %s\n",
							bashlykStreltzi.getMessage());
					Specification.polymastigousInsectivora = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							bashlykStreltzi);
				}
				if (Specification.polymastigousInsectivora != null) {
					try {
						String josefite_annunciable = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (josefite_annunciable == null
								|| !josefite_annunciable.equals("1")) {
							String naif_stratigraphy = System
									.getenv("OTOCRANIUM_BUTTERBUR");
							if (null != naif_stratigraphy) {
								File menyanthaceae_buplever = new File(
										naif_stratigraphy);
								if (menyanthaceae_buplever.exists()
										&& !menyanthaceae_buplever
												.isDirectory()) {
									try {
										String windgalled_attemperator;
										Scanner shifting_terminally = new Scanner(
												menyanthaceae_buplever, "UTF-8")
												.useDelimiter("\\A");
										if (shifting_terminally.hasNext())
											windgalled_attemperator = shifting_terminally
													.next();
										else
											windgalled_attemperator = "";
										if (null != windgalled_attemperator) {
											short eleutheri_nitrosify;
											try {
												eleutheri_nitrosify = Short
														.parseShort(windgalled_attemperator);
											} catch (NumberFormatException unfailableness_uncentred) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														unfailableness_uncentred);
											}
											SymphenomenaWhulk embryoniform_quidder = new SymphenomenaWhulk(
													eleutheri_nitrosify);
											int balanceable_smartweed = 2;
											falutinDysarthrosis(
													balanceable_smartweed,
													embryoniform_quidder);
										}
									} catch (FileNotFoundException inkishDorsointestinal) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												inkishDorsointestinal);
									}
								}
							}
						}
					} finally {
						Specification.polymastigousInsectivora.close();
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
