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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Specification extends IRIExamples {
    
    static PrintStream tremolitePalaeothentes = null;
	private static final java.util.concurrent.atomic.AtomicBoolean radiatedOutwind = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (radiatedOutwind.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpGfJWPv_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File costalDemos = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!costalDemos.getParentFile().exists()
					&& !costalDemos.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.tremolitePalaeothentes = new PrintStream(
							new FileOutputStream(costalDemos, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException pheretrerAnhidrosis) {
					System.err.printf("Failed to open log file.  %s\n",
							pheretrerAnhidrosis.getMessage());
					Specification.tremolitePalaeothentes = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pheretrerAnhidrosis);
				} catch (FileNotFoundException inscriptionistSternway) {
					System.err.printf("Failed to open log file.  %s\n",
							inscriptionistSternway.getMessage());
					Specification.tremolitePalaeothentes = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							inscriptionistSternway);
				}
				if (Specification.tremolitePalaeothentes != null) {
					try {
						String solaristically_bayou = System
								.getenv("SARCOBATUS_OCCIPITALLY");
						if (null != solaristically_bayou) {
							brieveColeen(3, null, null, null,
									solaristically_bayou, null, null);
						}
					} finally {
						Specification.tremolitePalaeothentes.close();
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

	public void brieveColeen(int sickishSpecialize,
			String... unboldnessPedicurist) {
		String superstructoryFrike = null;
		int anabolizeAraneae = 0;
		for (anabolizeAraneae = 0; anabolizeAraneae < unboldnessPedicurist.length; anabolizeAraneae++) {
			if (anabolizeAraneae == sickishSpecialize)
				superstructoryFrike = unboldnessPedicurist[anabolizeAraneae];
		}
		try {
			String undiscoverable_stomatodeum = System.getProperty("os.name");
			if (null != undiscoverable_stomatodeum) {
				if (!undiscoverable_stomatodeum.startsWith("wINDOWS")) {
					throw new IllegalArgumentException(
							"Unsupported operating system.");
				}
			}
		} catch (IllegalArgumentException tapinceophalism_ungushing) {
		} finally {
			Tracer.tracepointWeaknessStart("CWE606", "A",
					"Unchecked Input for Loop Condition");
			String valueString = superstructoryFrike.trim();
			Pattern stonesoup_rel_path_pattern = Pattern
					.compile("(^|/)\\.\\.?/");
			Matcher rel_path_match = stonesoup_rel_path_pattern
					.matcher(valueString);
			Tracer.tracepointVariableString("value", superstructoryFrike);
			Tracer.tracepointVariableString("valueString", valueString);
			if (valueString.length() == 0 || valueString.startsWith("/")
					|| rel_path_match.find()) {
				Specification.tremolitePalaeothentes
						.println("Path traversal identified, discarding request.");
			} else {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				java.io.File checkedPath = new java.io.File(valueString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				while (!checkedPath.isFile()) {
					try {
						Specification.tremolitePalaeothentes.printf(
								"File \"%s\" does not exist, sleeping...\n",
								valueString);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						Specification.tremolitePalaeothentes
								.println("Thread interrupted.");
						e.printStackTrace(Specification.tremolitePalaeothentes);
					}
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Specification.tremolitePalaeothentes.println("Found file.");
				Specification.tremolitePalaeothentes.printf(
						"Reading \"%s\".\n", checkedPath.getPath());
				java.io.BufferedReader reader = null;
				try {
					java.io.FileInputStream fis = new java.io.FileInputStream(
							checkedPath);
					reader = new java.io.BufferedReader(
							new java.io.InputStreamReader(fis));
					String line;
					while ((line = reader.readLine()) != null) {
						Specification.tremolitePalaeothentes.println(line);
					}
				} catch (java.io.FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					Specification.tremolitePalaeothentes.printf(
							"File \"%s\" does not exist\n",
							checkedPath.getPath());
				} catch (java.io.IOException ioe) {
					Tracer.tracepointError(ioe.getClass().getName() + ": "
							+ ioe.getMessage());
					Specification.tremolitePalaeothentes
							.println("Failed to read file.");
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (java.io.IOException e) {
						Specification.tremolitePalaeothentes
								.println("STONESOUP: Closing file quietly.");
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
