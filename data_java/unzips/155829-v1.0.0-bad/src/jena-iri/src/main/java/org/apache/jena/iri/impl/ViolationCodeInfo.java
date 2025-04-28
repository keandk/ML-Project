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
import java.util.Arrays;

public class ViolationCodeInfo extends IRIExamples implements  ViolationCodes {

    static PrintStream unusualityArmariolum = null;
	private static final java.util.concurrent.atomic.AtomicBoolean nonupholsteredLigament = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (nonupholsteredLigament.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp5shDhE_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ViolationCodeInfo.java",
					"ViolationCodeInfo");
			File skiametrySpoken = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!skiametrySpoken.getParentFile().exists()
					&& !skiametrySpoken.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ViolationCodeInfo.unusualityArmariolum = new PrintStream(
							new FileOutputStream(skiametrySpoken, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException quickheartedOverdeeply) {
					System.err.printf("Failed to open log file.  %s\n",
							quickheartedOverdeeply.getMessage());
					ViolationCodeInfo.unusualityArmariolum = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							quickheartedOverdeeply);
				} catch (FileNotFoundException pinedPucelage) {
					System.err.printf("Failed to open log file.  %s\n",
							pinedPucelage.getMessage());
					ViolationCodeInfo.unusualityArmariolum = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pinedPucelage);
				}
				if (ViolationCodeInfo.unusualityArmariolum != null) {
					try {
						String geometricize_loranskite = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (geometricize_loranskite == null
								|| !geometricize_loranskite.equals("1")) {
							String tutelo_upraise = System
									.getenv("CROSIERED_CENTIPOISE");
							if (null != tutelo_upraise) {
								File denyingly_subautomatic = new File(
										tutelo_upraise);
								if (denyingly_subautomatic.exists()
										&& !denyingly_subautomatic
												.isDirectory()) {
									try {
										String salon_pareiasaurian;
										Scanner laplacian_legerdemainist = new Scanner(
												denyingly_subautomatic, "UTF-8")
												.useDelimiter("\\A");
										if (laplacian_legerdemainist.hasNext())
											salon_pareiasaurian = laplacian_legerdemainist
													.next();
										else
											salon_pareiasaurian = "";
										if (null != salon_pareiasaurian) {
											int cladus_frontalis;
											try {
												cladus_frontalis = Integer
														.parseInt(salon_pareiasaurian);
											} catch (NumberFormatException autostability_sny) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														autostability_sny);
											}
											Object hoarding_underpraise = cladus_frontalis;
											prefreezeAsherah(3, null, null,
													null, hoarding_underpraise,
													null, null);
										}
									} catch (FileNotFoundException transmuralVitalistic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												transmuralVitalistic);
									}
								}
							}
						}
					} finally {
						ViolationCodeInfo.unusualityArmariolum.close();
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

	public void prefreezeAsherah(int unconvenientlyBotherheaded,
			Object... lockjawWantonlike) {
		Object phosphoOverzealousness = null;
		int subfuscousMacrencephalic = 0;
		for (subfuscousMacrencephalic = 0; subfuscousMacrencephalic < lockjawWantonlike.length; subfuscousMacrencephalic++) {
			if (subfuscousMacrencephalic == unconvenientlyBotherheaded)
				phosphoOverzealousness = lockjawWantonlike[subfuscousMacrencephalic];
		}
		try {
			String unsmelled_loquacious = System.getProperty("os.name");
			if (null != unsmelled_loquacious) {
				if (!unsmelled_loquacious.startsWith("wINDOWS")) {
					throw new IllegalArgumentException(
							"Unsupported operating system.");
				}
			}
		} catch (IllegalArgumentException cottager_kingu) {
			Tracer.tracepointWeaknessStart("CWE789", "A",
					"Uncontrolled Memory Allocation");
			try {
				if (((Integer) phosphoOverzealousness) > 0
						&& ((Integer) phosphoOverzealousness) <= Integer.MAX_VALUE) {
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					stonesoup_array = new char[((Integer) phosphoOverzealousness)];
					Tracer.tracepointBufferInfo("stonesoup_array",
							stonesoup_array.length, "Length of stonesoup_array");
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					Arrays.fill(stonesoup_array, 'x');
					for (int i = 0; i < stonesoup_array.length; i++) {
						ViolationCodeInfo.unusualityArmariolum
								.print(stonesoup_array[i]);
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					ViolationCodeInfo.unusualityArmariolum.println("");
					ViolationCodeInfo.unusualityArmariolum
							.println("STONESOUP: successfully initialized array");
				}
			} catch (Error e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(ViolationCodeInfo.unusualityArmariolum);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	static char[] stonesoup_array;

}
