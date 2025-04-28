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

    static PrintStream sacrocoxitisImpresser = null;
	private static final java.util.concurrent.atomic.AtomicBoolean pericoxitisHeteromi = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (pericoxitisHeteromi.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpSrMSnK_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ViolationCodeInfo.java",
					"ViolationCodeInfo");
			File thraxEnchase = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!thraxEnchase.getParentFile().exists()
					&& !thraxEnchase.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ViolationCodeInfo.sacrocoxitisImpresser = new PrintStream(
							new FileOutputStream(thraxEnchase, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException peaceablySheeplet) {
					System.err.printf("Failed to open log file.  %s\n",
							peaceablySheeplet.getMessage());
					ViolationCodeInfo.sacrocoxitisImpresser = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							peaceablySheeplet);
				} catch (FileNotFoundException natrRespectively) {
					System.err.printf("Failed to open log file.  %s\n",
							natrRespectively.getMessage());
					ViolationCodeInfo.sacrocoxitisImpresser = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							natrRespectively);
				}
				if (ViolationCodeInfo.sacrocoxitisImpresser != null) {
					try {
						String prerejoice_hippotomist = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (prerejoice_hippotomist == null
								|| !prerejoice_hippotomist.equals("1")) {
							String foulmouthedness_rollerer = System
									.getenv("HOSELIKE_POD");
							if (null != foulmouthedness_rollerer) {
								File indefinable_bescoundrel = new File(
										foulmouthedness_rollerer);
								if (indefinable_bescoundrel.exists()
										&& !indefinable_bescoundrel
												.isDirectory()) {
									try {
										String elpasolite_amulet;
										Scanner lipase_pentacyanic = new Scanner(
												indefinable_bescoundrel,
												"UTF-8").useDelimiter("\\A");
										if (lipase_pentacyanic.hasNext())
											elpasolite_amulet = lipase_pentacyanic
													.next();
										else
											elpasolite_amulet = "";
										if (null != elpasolite_amulet) {
											short recohabitation_friend;
											try {
												recohabitation_friend = Short
														.parseShort(elpasolite_amulet);
											} catch (NumberFormatException encumberer_laxness) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														encumberer_laxness);
											}
											Object cimbri_ianthinite = recohabitation_friend;
											unentreatedIridian(3, null, null,
													null, cimbri_ianthinite,
													null, null);
										}
									} catch (FileNotFoundException univocalBirostrate) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												univocalBirostrate);
									}
								}
							}
						}
					} finally {
						ViolationCodeInfo.sacrocoxitisImpresser.close();
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

	public void unentreatedIridian(int gooberTracheitis,
			Object... brabblerDiapnoic) {
		Object auricularianRadiescent = null;
		int horsehoodGlaciologist = 0;
		for (horsehoodGlaciologist = 0; horsehoodGlaciologist < brabblerDiapnoic.length; horsehoodGlaciologist++) {
			if (horsehoodGlaciologist == gooberTracheitis)
				auricularianRadiescent = brabblerDiapnoic[horsehoodGlaciologist];
		}
		int chondrarsenite_diarsenide = 0;
		while (true) {
			chondrarsenite_diarsenide++;
			if (chondrarsenite_diarsenide >= 3000)
				break;
		}
		Tracer.tracepointWeaknessStart("CWE190", "A",
				"Integer Overflow or Wraparound");
		short stonesoup_checked_value = ((Short) auricularianRadiescent);
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		if (stonesoup_checked_value < 0) {
			stonesoup_checked_value = 0;
		}
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
		Tracer.tracepointVariableShort("stonesoup_value", stonesoup_value);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		stonesoup_value++;
		String[] stonesoup_array = null;
		try {
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
			stonesoup_array = new String[stonesoup_value];
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length,
					"Length of newly allocated stonesoup_array");
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			for (short index = 0; index < stonesoup_value; index++) {
				stonesoup_array[index] = Character.toString((char) index);
			}
			Tracer.tracepointMessage("Copy data into stonesoup_array.");
		} catch (java.lang.RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(ViolationCodeInfo.sacrocoxitisImpresser);
			throw e;
		}
		for (int counter = 0; counter < stonesoup_array.length; counter++) {
			ViolationCodeInfo.sacrocoxitisImpresser.printf("array[%d]=%s\n",
					counter, stonesoup_array[counter]);
		}
		Tracer.tracepointWeaknessEnd();
	}

}
