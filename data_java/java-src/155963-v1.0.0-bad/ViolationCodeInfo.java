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
import java.util.ArrayList;

public class ViolationCodeInfo extends IRIExamples implements  ViolationCodes {

    public class VasopressorVermicle {
		private Object confessant_esophagotomy;

		public VasopressorVermicle(Object confessant_esophagotomy) {
			this.confessant_esophagotomy = confessant_esophagotomy;
		}

		public Object getconfessant_esophagotomy() {
			return this.confessant_esophagotomy;
		}
	}

	static PrintStream misdeliverySkewwhiff = null;
	private static final java.util.concurrent.atomic.AtomicBoolean characterAerophilately = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (characterAerophilately.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpsKPjNe_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ViolationCodeInfo.java",
					"ViolationCodeInfo");
			File vegetabilityDreyfusism = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!vegetabilityDreyfusism.getParentFile().exists()
					&& !vegetabilityDreyfusism.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ViolationCodeInfo.misdeliverySkewwhiff = new PrintStream(
							new FileOutputStream(vegetabilityDreyfusism, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException punterCuneoscaphoid) {
					System.err.printf("Failed to open log file.  %s\n",
							punterCuneoscaphoid.getMessage());
					ViolationCodeInfo.misdeliverySkewwhiff = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							punterCuneoscaphoid);
				} catch (FileNotFoundException jumartThinker) {
					System.err.printf("Failed to open log file.  %s\n",
							jumartThinker.getMessage());
					ViolationCodeInfo.misdeliverySkewwhiff = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							jumartThinker);
				}
				if (ViolationCodeInfo.misdeliverySkewwhiff != null) {
					try {
						String conjunctivitis_leatherbark = System
								.getenv("LAVATIONAL_ANGELENO");
						if (null != conjunctivitis_leatherbark) {
							int sigmoidal_coincider;
							try {
								sigmoidal_coincider = Integer
										.parseInt(conjunctivitis_leatherbark);
							} catch (NumberFormatException redarken_hanna) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										redarken_hanna);
							}
							Object gobleted_frumentarious = sigmoidal_coincider;
							VasopressorVermicle urochrome_jeffery = new VasopressorVermicle(
									gobleted_frumentarious);
							italianateFillet(urochrome_jeffery);
						}
					} finally {
						ViolationCodeInfo.misdeliverySkewwhiff.close();
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

	public void italianateFillet(VasopressorVermicle semiprimigenous_chelidonian) {
		philhymnicElastose(semiprimigenous_chelidonian);
	}

	public void philhymnicElastose(VasopressorVermicle tenderly_jacksaw) {
		indispensablyHysterogenic(tenderly_jacksaw);
	}

	public void indispensablyHysterogenic(
			VasopressorVermicle mesovarium_escambio) {
		ionosphereArapunga(mesovarium_escambio);
	}

	public void ionosphereArapunga(VasopressorVermicle deplaceable_mandarindom) {
		sulphophthaleinRegicidism(deplaceable_mandarindom);
	}

	public void sulphophthaleinRegicidism(
			VasopressorVermicle inamoration_protorthopteron) {
		malbrouckMatrilinear(inamoration_protorthopteron);
	}

	public void malbrouckMatrilinear(VasopressorVermicle iceleaf_bregmate) {
		premongolianSeeded(iceleaf_bregmate);
	}

	public void premongolianSeeded(VasopressorVermicle mormyrus_polyonomy) {
		chevagePrecedentary(mormyrus_polyonomy);
	}

	public void chevagePrecedentary(VasopressorVermicle colonization_waapa) {
		predenialGravelish(colonization_waapa);
	}

	public void predenialGravelish(VasopressorVermicle dysmorphism_schizothymic) {
		antistrikeInsulize(dysmorphism_schizothymic);
	}

	public void antistrikeInsulize (VasopressorVermicle ladyclock_choreographic) {
		stonesoup_sources = new ArrayList<FileOutputStream>();
		Tracer.tracepointWeaknessStart ("CWE774", "B", "Allocation of File Descriptors or Handles Without Limits or Throttling");
		Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
		Tracer.tracepointMessage ("CROSSOVER-POINT: BEFORE");
		Tracer.tracepointMessage ("TRIGGER-POINT; BEFORE");
		for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) ladyclock_choreographic.getconfessant_esophagotomy ()); stonesoup_counter++) {
			try {
				stonesoup_sources.add (new FileOutputStream (String.format ("/opt/stonesoup/workspace/testData/test%10d", stonesoup_counter)));
			} catch (FileNotFoundException e) {
				Tracer.tracepointError (e.getClass ().getName () + ": " + e.getMessage ());
				ViolationCodeInfo.misdeliverySkewwhiff.println ("Failed to create new file, moving on.");
			}
			ViolationCodeInfo.misdeliverySkewwhiff.println (stonesoup_counter);
		}
		Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
		Tracer.tracepointMessage ("TRIGGER-POINT; AFTER");
		Tracer.tracepointMessage ("CROSSOVER-POINT: AFTER");
		Tracer.tracepointWeaknessEnd ();
	}

	public static ArrayList<FileOutputStream> stonesoup_sources = null;

}
