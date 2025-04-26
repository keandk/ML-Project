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
import java.io.IOException;

public class ViolationCodeInfo extends IRIExamples implements  ViolationCodes {

    public class BurbankianHeterostyled {
		private String epephragmal_koko;

		public BurbankianHeterostyled(String epephragmal_koko) {
			this.epephragmal_koko = epephragmal_koko;
		}

		public String getepephragmal_koko() {
			return this.epephragmal_koko;
		}
	}

	static PrintStream untalkingArchineuron = null;
	private static final java.util.concurrent.atomic.AtomicBoolean jaggednessPodostemad = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (jaggednessPodostemad.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpBcQyoM_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ViolationCodeInfo.java",
					"ViolationCodeInfo");
			File turnwrestGerald = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!turnwrestGerald.getParentFile().exists()
					&& !turnwrestGerald.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ViolationCodeInfo.untalkingArchineuron = new PrintStream(
							new FileOutputStream(turnwrestGerald, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException misdentitionSerioline) {
					System.err.printf("Failed to open log file.  %s\n",
							misdentitionSerioline.getMessage());
					ViolationCodeInfo.untalkingArchineuron = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							misdentitionSerioline);
				} catch (FileNotFoundException wasukumaUpstairs) {
					System.err.printf("Failed to open log file.  %s\n",
							wasukumaUpstairs.getMessage());
					ViolationCodeInfo.untalkingArchineuron = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							wasukumaUpstairs);
				}
				if (ViolationCodeInfo.untalkingArchineuron != null) {
					try {
						String incensory_snickdrawing = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (incensory_snickdrawing == null
								|| !incensory_snickdrawing.equals("1")) {
							String legatorial_erithacus = System
									.getenv("HANKLE_TENANTABLENESS");
							if (null != legatorial_erithacus) {
								File oculopupillary_patroon = new File(
										legatorial_erithacus);
								if (oculopupillary_patroon.exists()
										&& !oculopupillary_patroon
												.isDirectory()) {
									try {
										String acatholic_fauld;
										Scanner franciscanism_velarium = new Scanner(
												oculopupillary_patroon, "UTF-8")
												.useDelimiter("\\A");
										if (franciscanism_velarium.hasNext())
											acatholic_fauld = franciscanism_velarium
													.next();
										else
											acatholic_fauld = "";
										if (null != acatholic_fauld) {
											BurbankianHeterostyled thioarsenic_nonaerating = new BurbankianHeterostyled(
													acatholic_fauld);
											YokutsSenecioid nieceship_abthainry = new YokutsSenecioid();
											nieceship_abthainry
													.envelopMonopetalae(thioarsenic_nonaerating);
										}
									} catch (FileNotFoundException biannualEthmonasal) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												biannualEthmonasal);
									}
								}
							}
						}
					} finally {
						ViolationCodeInfo.untalkingArchineuron.close();
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

	public static class YokutsSenecioid {
		public void envelopMonopetalae(
				BurbankianHeterostyled homologic_innocuity) {
			HemiligulateDroughtiness escambron_flavour = new HemiligulateDroughtiness();
			escambron_flavour.dielikeRecounter(homologic_innocuity);
		}
	}
	public static class HemiligulateDroughtiness {
		public void dielikeRecounter(BurbankianHeterostyled boatheader_ocotea) {
			CerargyriteSizzing rectotome_xylina = new CerargyriteSizzing();
			rectotome_xylina.penelopineMountainside(boatheader_ocotea);
		}
	}
	public static class CerargyriteSizzing {
		public void penelopineMountainside(
				BurbankianHeterostyled real_trophoderm) {
			AngiosclerosisBoniface parasitemia_ranterism = new AngiosclerosisBoniface();
			parasitemia_ranterism.cloghadStuart(real_trophoderm);
		}
	}
	public static class AngiosclerosisBoniface {
		public void cloghadStuart(BurbankianHeterostyled sarcophaga_manucaptor) {
			CaptivatingPregeniculum madras_cartilage = new CaptivatingPregeniculum();
			madras_cartilage.solarizeScabbler(sarcophaga_manucaptor);
		}
	}
	public static class CaptivatingPregeniculum {
		public void solarizeScabbler(BurbankianHeterostyled counterorator_omber) {
			BezesteenSolaristically dentalization_prowler = new BezesteenSolaristically();
			dentalization_prowler.caxonNonolfactory(counterorator_omber);
		}
	}
	public static class BezesteenSolaristically {
		public void caxonNonolfactory(
				BurbankianHeterostyled thielaviopsis_transcoloration) {
			HoudanSeptuagintal overbrimmingly_gastrolobium = new HoudanSeptuagintal();
			overbrimmingly_gastrolobium
					.lightheadedMankin(thielaviopsis_transcoloration);
		}
	}
	public static class HoudanSeptuagintal {
		public void lightheadedMankin(BurbankianHeterostyled warf_efficacy) {
			UnnarrowJunta curtilage_topknotted = new UnnarrowJunta();
			curtilage_topknotted.atavistMediastinitis(warf_efficacy);
		}
	}
	public static class UnnarrowJunta {
		public void atavistMediastinitis(
				BurbankianHeterostyled pleopodite_unsavoriness) {
			CephalotaxusRemembrance aurification_barracuda = new CephalotaxusRemembrance();
			aurification_barracuda.gommelinGanguela(pleopodite_unsavoriness);
		}
	}
	public static class CephalotaxusRemembrance {
		public void gommelinGanguela(BurbankianHeterostyled grounded_delitescent) {
			PlaiceTrophopathy broadleaf_davallia = new PlaiceTrophopathy();
			broadleaf_davallia.sulfindigoticWasabi(grounded_delitescent);
		}
	}
	public static class PlaiceTrophopathy {
		public void sulfindigoticWasabi(BurbankianHeterostyled narcose_unwoundableness){Tracer.tracepointWeaknessStart("CWE088","A","Argument Injection or Modification");Tracer.tracepointVariableString("value",narcose_unwoundableness.getepephragmal_koko());String stonesoup_proc_cmd="find . -iname ";Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");stonesoup_proc_cmd+=narcose_unwoundableness.getepephragmal_koko();Tracer.tracepointVariableString("stonesoup_proc_cmd",stonesoup_proc_cmd);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");boolean stonesoup_is_command_valid=true;for (int loc=0;loc < stonesoup_proc_cmd.length();loc++){if ((stonesoup_proc_cmd.charAt(loc) == ';') && stonesoup_proc_cmd.charAt(loc - 1) != '\\'){Tracer.tracepointMessage("Invalid command, shell escape detected.");ViolationCodeInfo.untalkingArchineuron.println("Invalid command, shell escape detected.");stonesoup_is_command_valid=false;}}if (stonesoup_is_command_valid){java.lang.ProcessBuilder stonesoup_proc_builder=new java.lang.ProcessBuilder("bash","-c",stonesoup_proc_cmd);stonesoup_proc_builder.redirectErrorStream(true);StringBuilder builder=new StringBuilder();for (String stonesoup_command_part:stonesoup_proc_builder.command()){builder.append(stonesoup_command_part);builder.append(" ");}Tracer.tracepointVariableString("stonesoup_proc_builder.command()",builder.toString());java.lang.Process stonesoup_proc=null;try {Tracer.tracepointMessage("Executing command.");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");stonesoup_proc=stonesoup_proc_builder.start();Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());ViolationCodeInfo.untalkingArchineuron.println("STONESOUP: Failed to open subprocess.");}if (stonesoup_proc != null){String stonesoup_proc_output_line=null;java.io.BufferedReader stonesoup_proc_reader=new java.io.BufferedReader(new java.io.InputStreamReader(stonesoup_proc.getInputStream()));try {Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");while ((stonesoup_proc_output_line=stonesoup_proc_reader.readLine()) != null){ViolationCodeInfo.untalkingArchineuron.println(stonesoup_proc_output_line);}} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());ViolationCodeInfo.untalkingArchineuron.println("STONESOUP: Error reading subprocess output stream.");}try {Tracer.tracepointMessage("Waiting for subprocess to complete.");int stonesoup_exit_code=stonesoup_proc.waitFor();if (stonesoup_exit_code != 0){Tracer.tracepointError("Subprocess returned a non-zero exit code.");Tracer.tracepointVariableInt("stonesoup_exit_code",stonesoup_exit_code);ViolationCodeInfo.untalkingArchineuron.printf("STONESOUP: Error in subprocess.	Return code: %d\n",stonesoup_exit_code);}} catch (java.lang.InterruptedException ie){Tracer.tracepointError(ie.getClass().getName() + ": "+ie.getMessage());ViolationCodeInfo.untalkingArchineuron.println("STONESOUP: Error waiting for subprocess.");}}}Tracer.tracepointWeaknessEnd();}	}

}
