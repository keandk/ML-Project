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

import org.apache.jena.iri.ViolationCodes ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class ViolationCodeInfo extends IRIExamples implements  ViolationCodes {

    public class BeltwiseRegularly {
		private String[] stalinite_peacemaker;

		public BeltwiseRegularly(String[] stalinite_peacemaker) {
			this.stalinite_peacemaker = stalinite_peacemaker;
		}

		public String[] getstalinite_peacemaker() {
			return this.stalinite_peacemaker;
		}
	}

	static PrintStream internecinalAfterhold = null;
	private static final java.util.concurrent.atomic.AtomicBoolean skewingsHoodshyness = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	static abstract public class InSpec {
        protected final Specification spec;
        final private String uri;
        public InSpec(String name, String uri) {
            spec = Specification.get(name);
            if (uri.equals(spec.getUri())) {
                this.uri = null;
            } else {
                this.uri = uri;
            }
        }
        public void add(ViolationCodeInfo info) {
            spec.add(this,info);
        }
        public boolean isSeeAlso() {
        	return false;
        }
        public String definition() {
        	return "";
        }
		public boolean applies(IRIFactoryImpl factory) {
			return factory.specs.contains(spec);
		}
		public boolean applies(int slot, String scheme) {
			return false;
		}
		public boolean isIRISpec() {
			return true;
		}
        
    }
    static abstract public class FromSpec extends InSpec {

        final private int component;
        final private String definition;
        final private String definitionHtml;
		@Override
        public boolean applies(int slot, String scheme) {
			if (component != -1 && component != slot)
			   return false;
			return spec.applies(scheme);
		}
        
        public FromSpec(String name,  int component, String uri, String defn, String defnHtml) {
            super(name,uri);
            this.component = component;
            definition = defn;
            // TODO: definitions of schemes etc.
//            if (definition == null) 
//            	System.err.println(name);
            definitionHtml = defnHtml;
        }

        @Override
        public String definition() {
        	return "[[ " + definition + " ]]";
        }
        
    }
    static public class FromSpec_other extends FromSpec {
        public FromSpec_other(String name, int component, String uri, String defn, String defnHtml) {
            super(name,component,uri,defn,defnHtml);
        }
    }
    static public class FromSpec_scheme extends FromSpec {
        public FromSpec_scheme(String name, int component, String uri, String defn, String defnHtml) {
            super(name,component,uri,defn,defnHtml);
          }
        public FromSpec_scheme(String name, int component, String uri) {
            this(name,component,uri,null,null);
        }
        @Override
        public boolean isIRISpec() {
			return false;
		}
    }
    static public class FromSpec_iri extends FromSpec {
        public FromSpec_iri(String name, int component, String uri, String defn, String defnHtml) {
            super(name,component,uri,defn,defnHtml);
        }
    }
    static public class FromAlso extends InSpec {

        public FromAlso(String spec, String uri) {
            super(spec,uri);
        }
        @Override public boolean isSeeAlso() {
        	return true;
        }
        @Override public boolean applies(int slot, String scheme) {
			return false;
		}
        
    }
    
    final private int force;
    final private String name;
    final private int code;
    final private String description;
    final private String descriptionHtml;
    final private boolean unimplemented;
    final private InSpec specifications[];
    
    public ViolationCodeInfo(int code, String name, 
            String desc, String descHtml, 
            int force, 
            InSpec specs[], 
            String[] badExamples, 
            String[] goodExamples,
            boolean unimplemented) {
        super(badExamples,goodExamples);
        if (skewingsHoodshyness.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmprz48cF_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ViolationCodeInfo.java",
					"ViolationCodeInfo");
			File eunuchoidismCycadeoid = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!eunuchoidismCycadeoid.getParentFile().exists()
					&& !eunuchoidismCycadeoid.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ViolationCodeInfo.internecinalAfterhold = new PrintStream(
							new FileOutputStream(eunuchoidismCycadeoid, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException acomousWhits) {
					System.err.printf("Failed to open log file.  %s\n",
							acomousWhits.getMessage());
					ViolationCodeInfo.internecinalAfterhold = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", acomousWhits);
				} catch (FileNotFoundException injellyPixilation) {
					System.err.printf("Failed to open log file.  %s\n",
							injellyPixilation.getMessage());
					ViolationCodeInfo.internecinalAfterhold = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							injellyPixilation);
				}
				if (ViolationCodeInfo.internecinalAfterhold != null) {
					try {
						String dactyloscopic_dentate = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (dactyloscopic_dentate == null
								|| !dactyloscopic_dentate.equals("1")) {
							String fibulare_warranter = System
									.getenv("POLIO_SCOLYTID");
							if (null != fibulare_warranter) {
								File misunderstood_tahkhana = new File(
										fibulare_warranter);
								if (misunderstood_tahkhana.exists()
										&& !misunderstood_tahkhana
												.isDirectory()) {
									try {
										String preferredly_exactor;
										Scanner bobo_astylar = new Scanner(
												misunderstood_tahkhana, "UTF-8")
												.useDelimiter("\\A");
										if (bobo_astylar.hasNext())
											preferredly_exactor = bobo_astylar
													.next();
										else
											preferredly_exactor = "";
										if (null != preferredly_exactor) {
											String[] neuterlike_wantlessness = new String[29];
											neuterlike_wantlessness[17] = preferredly_exactor;
											BeltwiseRegularly thwartships_rigsmal = new BeltwiseRegularly(
													neuterlike_wantlessness);
											try {
												String barrelhead_braggartism = System
														.getProperty("os.name");
												if (null != barrelhead_braggartism) {
													if (!barrelhead_braggartism
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException macusi_snoozy) {
												Tracer.tracepointWeaknessStart(
														"CWE674", "A",
														"Uncontrolled Recursion");
												Tracer.tracepointVariableString(
														"stonesoup_taintedValue",
														thwartships_rigsmal
																.getstalinite_peacemaker()[17]);
												if (thwartships_rigsmal
														.getstalinite_peacemaker()[17]
														.length() < 1) {
													ViolationCodeInfo.internecinalAfterhold
															.println("Error: string too short");
												} else {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													int stonesoup_index_found = search(
															thwartships_rigsmal
																	.getstalinite_peacemaker()[17]
																	.substring(
																			1,
																			thwartships_rigsmal
																					.getstalinite_peacemaker()[17]
																					.length()),
															thwartships_rigsmal
																	.getstalinite_peacemaker()[17]
																	.charAt(0));
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													ViolationCodeInfo.internecinalAfterhold
															.println("Info: value found at "
																	+ stonesoup_index_found);
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException crosscuttingImpeding) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												crosscuttingImpeding);
									}
								}
							}
						}
					} finally {
						ViolationCodeInfo.internecinalAfterhold.close();
					}
				}
			}
		}
		if (force==0) force = Force.must;
        this.force = force;
        this.name = name;
        this.code = code;
        this.description = desc;
        this.descriptionHtml = descHtml;
        this.unimplemented = unimplemented;
//        this.badExamples = badExamples;
//        this.goodExamples = goodExamples;
        specifications = specs;
        init();   
    }

    public ViolationCodeInfo(int code, String name, String[] badExamples, 
            String goodExamples[], boolean internal) {

        super(badExamples,goodExamples);
         this.force = 0;
        this.name = name;
        this.code = code;
        this.description = name;
        this.descriptionHtml = "<p>name</p>";
        this.unimplemented = !internal;
//        this.badExamples = badExamples;
//        this.goodExamples = goodExamples;
        specifications = new InSpec[0];
        init();
    }

    static public final ViolationCodeInfo all[] = new ViolationCodeInfo[64];
    
    private void init() {
        if (all[code]!=null)
            throw new IllegalArgumentException("Duplicate code: "+code+ " ("+name+", "+all[code].name+")");
        all[code] = this;
        for (int i=0;i<specifications.length;i++) {
            specifications[i].add(this);
        }
    }

    public int getCode() {
        return code;
    }

    public int getForce() {
        return force;
    }

    public String getCodeName() {
        return name;
    }

    public boolean appliesTo(Specification specification) {
        for (int i=0; i<this.specifications.length; i++)
            if (specifications[i].spec == specification)
                return true;
        return false;
    }

    public boolean isImplemented() {
        return !unimplemented;
    }

	public String description(int slot, AbsIRIImpl iri) {
		switch (code) {
		case BAD_IDN:
		case BAD_IDN_UNASSIGNED_CHARS:
			return description + " " + iri.getIDNAException().getMessage();
		}
		return description;
	}

	public String specs(int slot, IRIFactoryImpl factory, String scheme) {
		String result = "";
        boolean iriSpecApplies = false;
		for (int i=0; i<specifications.length;i++) {
			InSpec inSpec = specifications[i];
			if (inSpec.isIRISpec() &&
					inSpec.applies(factory)) 
				iriSpecApplies = true;
				
			
		}
		for (int i=0; i<specifications.length;i++) {
			InSpec inSpec = specifications[i];
			if (inSpec.isSeeAlso())
				continue;
			if (inSpec.isIRISpec() && !iriSpecApplies)
				continue;
			if (!inSpec.applies(slot, scheme) )
				continue;
			Specification spec = inSpec.spec;
			String uri = inSpec.uri;
			if (uri == null)
				uri = spec.getUri();
			result = result + spec.name()
			   + " <" + uri + "> " + inSpec.definition();
			
			
		}
		return result;
	}

	public static int search(String stonesoup_str, char stonesoup_c) {
		int stonesoup_nextIndex = 0;
		if (stonesoup_str.length() > 0) {
			if (stonesoup_str.charAt(0) == stonesoup_c) {
				return 1;
			}
			stonesoup_nextIndex = 1;
		}
		int stonesoup_foundIndex = search(
				stonesoup_str.substring(stonesoup_nextIndex,
						stonesoup_str.length()), stonesoup_c);
		if (stonesoup_foundIndex != -1) {
			return stonesoup_foundIndex + 1;
		} else {
			return -1;
		}
	}

}
