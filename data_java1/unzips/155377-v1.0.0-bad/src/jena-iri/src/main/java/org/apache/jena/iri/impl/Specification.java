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
import java.util.ArrayList;
import java.util.List;


public class Specification extends IRIExamples {
    
    static PrintStream thereamongstBellyache = null;
	private static final java.util.concurrent.atomic.AtomicBoolean governmentalizeLabefact = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (governmentalizeLabefact.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmppBaTSK_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File prohibitionismDidascalic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!prohibitionismDidascalic.getParentFile().exists()
					&& !prohibitionismDidascalic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.thereamongstBellyache = new PrintStream(
							new FileOutputStream(prohibitionismDidascalic,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException geomancerDiapnoic) {
					System.err.printf("Failed to open log file.  %s\n",
							geomancerDiapnoic.getMessage());
					Specification.thereamongstBellyache = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							geomancerDiapnoic);
				} catch (FileNotFoundException chestyDulcinea) {
					System.err.printf("Failed to open log file.  %s\n",
							chestyDulcinea.getMessage());
					Specification.thereamongstBellyache = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							chestyDulcinea);
				}
				if (Specification.thereamongstBellyache != null) {
					try {
						String rhizoma_platymeter = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (rhizoma_platymeter == null
								|| !rhizoma_platymeter.equals("1")) {
							String moralizer_janiform = System
									.getenv("RIDICULE_PYRRHIC");
							if (null != moralizer_janiform) {
								File skaillie_coppernosed = new File(
										moralizer_janiform);
								if (skaillie_coppernosed.exists()
										&& !skaillie_coppernosed.isDirectory()) {
									try {
										String anachronism_boxerism;
										Scanner chloropalladic_respangle = new Scanner(
												skaillie_coppernosed, "UTF-8")
												.useDelimiter("\\A");
										if (chloropalladic_respangle.hasNext())
											anachronism_boxerism = chloropalladic_respangle
													.next();
										else
											anachronism_boxerism = "";
										if (null != anachronism_boxerism) {
											int personification_northeastern;
											try {
												personification_northeastern = Integer
														.parseInt(anachronism_boxerism);
											} catch (NumberFormatException presspack_hiortdahlite) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														presspack_hiortdahlite);
											}
											boolean enfetter_absonous = false;
											oversorrow_sweep: for (int formicaroid_basiventral = 0; formicaroid_basiventral < 10; formicaroid_basiventral++)
												for (int goclenian_recantingly = 0; goclenian_recantingly < 10; goclenian_recantingly++)
													if (formicaroid_basiventral
															* goclenian_recantingly == 63) {
														enfetter_absonous = true;
														break oversorrow_sweep;
													}
											Tracer.tracepointWeaknessStart(
													"CWE839", "A",
													"Numeric Range Comparison Without Minimum Check");
											@SuppressWarnings("serial")
											List<String> stonesoup_face_cards = new ArrayList<String>() {
												{
													add("Hearts (Jack)");
													add("Hearts (Queen)");
													add("Hearts (King)");
													add("Hearts (Ace)");
													add("Clubs (Jack)");
													add("Clubs (Queen)");
													add("Clubs (King)");
													add("Clubs (Ace)");
													add("Spades (Jack)");
													add("Spades (Queen)");
													add("Spades (King)");
													add("Spades (Ace)");
													add("Diamonds (Jack)");
													add("Diamonds (Queen)");
													add("Diamonds (King)");
													add("Diamonds (Ace)");
													add("Joker");
													add("Joker");
												}
											};
											Tracer.tracepointVariableInt(
													"value",
													personification_northeastern);
											Tracer.tracepointVariableInt(
													"stonesoup_face_cards.size()",
													stonesoup_face_cards.size());
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											if (personification_northeastern >= stonesoup_face_cards
													.size()) {
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Specification.thereamongstBellyache
														.printf("Card not available for %d.\n",
																personification_northeastern);
											} else {
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												try {
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													Specification.thereamongstBellyache
															.printf("Selected Card = %s\n",
																	stonesoup_face_cards
																			.get(personification_northeastern));
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												} catch (RuntimeException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													e.printStackTrace(Specification.thereamongstBellyache);
													throw e;
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException externityAbatable) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												externityAbatable);
									}
								}
							}
						}
					} finally {
						Specification.thereamongstBellyache.close();
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
