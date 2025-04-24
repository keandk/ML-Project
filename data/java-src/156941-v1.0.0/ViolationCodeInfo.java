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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViolationCodeInfo extends IRIExamples implements  ViolationCodes {

    public class BackhatchConvertism {
		private String[] predelegation_disembocation;

		public BackhatchConvertism(String[] predelegation_disembocation) {
			this.predelegation_disembocation = predelegation_disembocation;
		}

		public String[] getpredelegation_disembocation() {
			return this.predelegation_disembocation;
		}
	}

	static PrintStream unvariedlyAbandoner = null;
	private static final java.util.concurrent.atomic.AtomicBoolean mendelyeeviteIntellection = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (mendelyeeviteIntellection.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpEvRh2l_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ViolationCodeInfo.java",
					"ViolationCodeInfo");
			File robotisticCracking = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!robotisticCracking.getParentFile().exists()
					&& !robotisticCracking.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ViolationCodeInfo.unvariedlyAbandoner = new PrintStream(
							new FileOutputStream(robotisticCracking, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException mistakablyGauby) {
					System.err.printf("Failed to open log file.  %s\n",
							mistakablyGauby.getMessage());
					ViolationCodeInfo.unvariedlyAbandoner = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							mistakablyGauby);
				} catch (FileNotFoundException sunbowProatheist) {
					System.err.printf("Failed to open log file.  %s\n",
							sunbowProatheist.getMessage());
					ViolationCodeInfo.unvariedlyAbandoner = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							sunbowProatheist);
				}
				if (ViolationCodeInfo.unvariedlyAbandoner != null) {
					try {
						String pabulary_pseudocumenyl = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (pabulary_pseudocumenyl == null
								|| !pabulary_pseudocumenyl.equals("1")) {
							String shittimwood_mabellona = System
									.getenv("CERATOSA_REES");
							if (null != shittimwood_mabellona) {
								File attery_disgulf = new File(
										shittimwood_mabellona);
								if (attery_disgulf.exists()
										&& !attery_disgulf.isDirectory()) {
									try {
										String rolliche_unscabbarded;
										Scanner syntheticism_unenraged = new Scanner(
												attery_disgulf, "UTF-8")
												.useDelimiter("\\A");
										if (syntheticism_unenraged.hasNext())
											rolliche_unscabbarded = syntheticism_unenraged
													.next();
										else
											rolliche_unscabbarded = "";
										if (null != rolliche_unscabbarded) {
											String[] interlocular_uncriticized = new String[29];
											interlocular_uncriticized[11] = rolliche_unscabbarded;
											BackhatchConvertism unsensibly_palicourea = new BackhatchConvertism(
													interlocular_uncriticized);
											boolean unflossy_unshowy = false;
											affectingly_hemocytogenesis: for (int nondictation_crimp = 0; nondictation_crimp < 10; nondictation_crimp++)
												for (int airedale_lipogenetic = 0; airedale_lipogenetic < 10; airedale_lipogenetic++)
													if (nondictation_crimp
															* airedale_lipogenetic == 63) {
														unflossy_unshowy = true;
														break affectingly_hemocytogenesis;
													}
											Tracer.tracepointWeaknessStart(
													"CWE606", "A",
													"Unchecked Input for Loop Condition");
											String valueString = unsensibly_palicourea
													.getpredelegation_disembocation()[11]
													.trim();
											Pattern stonesoup_rel_path_pattern = Pattern
													.compile("(^|/)\\.\\.?/");
											Matcher rel_path_match = stonesoup_rel_path_pattern
													.matcher(valueString);
											Tracer.tracepointVariableString(
													"value",
													unsensibly_palicourea
															.getpredelegation_disembocation()[11]);
											Tracer.tracepointVariableString(
													"valueString", valueString);
											if (valueString.length() == 0
													|| valueString
															.startsWith("/")
													|| rel_path_match.find()) {
												ViolationCodeInfo.unvariedlyAbandoner
														.println("Path traversal identified, discarding request.");
											} else {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												java.io.File checkedPath = new java.io.File(
														valueString);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												while (!checkedPath.isFile()) {
													try {
														ViolationCodeInfo.unvariedlyAbandoner
																.printf("File \"%s\" does not exist, sleeping...\n",
																		valueString);
														Thread.sleep(500);
													} catch (InterruptedException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														ViolationCodeInfo.unvariedlyAbandoner
																.println("Thread interrupted.");
														e.printStackTrace(ViolationCodeInfo.unvariedlyAbandoner);
													}
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												ViolationCodeInfo.unvariedlyAbandoner
														.println("Found file.");
												ViolationCodeInfo.unvariedlyAbandoner
														.printf("Reading \"%s\".\n",
																checkedPath
																		.getPath());
												java.io.BufferedReader reader = null;
												try {
													java.io.FileInputStream fis = new java.io.FileInputStream(
															checkedPath);
													reader = new java.io.BufferedReader(
															new java.io.InputStreamReader(
																	fis));
													String line;
													while ((line = reader
															.readLine()) != null) {
														ViolationCodeInfo.unvariedlyAbandoner
																.println(line);
													}
												} catch (java.io.FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													ViolationCodeInfo.unvariedlyAbandoner
															.printf("File \"%s\" does not exist\n",
																	checkedPath
																			.getPath());
												} catch (java.io.IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													ViolationCodeInfo.unvariedlyAbandoner
															.println("Failed to read file.");
												} finally {
													try {
														if (reader != null) {
															reader.close();
														}
													} catch (java.io.IOException e) {
														ViolationCodeInfo.unvariedlyAbandoner
																.println("STONESOUP: Closing file quietly.");
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException oomycetousOpisthorchis) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												oomycetousOpisthorchis);
									}
								}
							}
						}
					} finally {
						ViolationCodeInfo.unvariedlyAbandoner.close();
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

}
