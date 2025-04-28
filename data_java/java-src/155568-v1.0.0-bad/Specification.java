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
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Specification extends IRIExamples {
    
    public class DreamageTrecentist {
		private Object bacillar_nippers;

		public DreamageTrecentist(Object bacillar_nippers) {
			this.bacillar_nippers = bacillar_nippers;
		}

		public Object getbacillar_nippers() {
			return this.bacillar_nippers;
		}
	}

	public void thinkerCatkinate(int dentated_unsided,
			DreamageTrecentist ruffer_subimpressed) {
		dentated_unsided--;
		if (dentated_unsided > 0) {
			pteranodontHydroxylic(dentated_unsided, ruffer_subimpressed);
		}
	}

	public void pteranodontHydroxylic(int trill_kaleidophon,
			DreamageTrecentist ruffer_subimpressed) {
		thinkerCatkinate(trill_kaleidophon, ruffer_subimpressed);
		Tracer.tracepointWeaknessStart("CWE400", "B",
				"Uncontrolled Resource Consumption");
		Tracer.tracepointMessage("Create pool");
		ExecutorService pool = Executors.newFixedThreadPool(20);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		if (((Integer) ruffer_subimpressed.getbacillar_nippers()) > 0
				&& ((Integer) ruffer_subimpressed.getbacillar_nippers()) <= Integer.MAX_VALUE) {
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Creating threads");
			for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
				pool.execute(new Factorial(((Integer) ruffer_subimpressed
						.getbacillar_nippers()), Specification.hereditaristBice));
			}
			pool.shutdown();
			Tracer.tracepointMessage("Shutdown pool");
		}
		try {
			Tracer.tracepointMessage("Joining threads");
			while (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("Joined threads");
			Specification.hereditaristBice.println("finished evaluating");
		} catch (InterruptedException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			Specification.hereditaristBice.println("Thread pool interrupted");
		}
		Tracer.tracepointWeaknessEnd();
	}
	static PrintStream hereditaristBice = null;
	private static final java.util.concurrent.atomic.AtomicBoolean barbaraTrypanosoma = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (barbaraTrypanosoma.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpBUqV0h_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			File chickenbillSaboted = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!chickenbillSaboted.getParentFile().exists()
					&& !chickenbillSaboted.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Specification.hereditaristBice = new PrintStream(
							new FileOutputStream(chickenbillSaboted, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException schizotrichiaPeashooter) {
					System.err.printf("Failed to open log file.  %s\n",
							schizotrichiaPeashooter.getMessage());
					Specification.hereditaristBice = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							schizotrichiaPeashooter);
				} catch (FileNotFoundException supereminenceLimequat) {
					System.err.printf("Failed to open log file.  %s\n",
							supereminenceLimequat.getMessage());
					Specification.hereditaristBice = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							supereminenceLimequat);
				}
				if (Specification.hereditaristBice != null) {
					try {
						String leeangle_schilling = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (leeangle_schilling == null
								|| !leeangle_schilling.equals("1")) {
							String bromeigon_tweet = System
									.getenv("IMPANATE_LAPAROCOLECTOMY");
							if (null != bromeigon_tweet) {
								File thievable_paintable = new File(
										bromeigon_tweet);
								if (thievable_paintable.exists()
										&& !thievable_paintable.isDirectory()) {
									try {
										String potdar_ulmo;
										Scanner gamodesmic_nonaerating = new Scanner(
												thievable_paintable, "UTF-8")
												.useDelimiter("\\A");
										if (gamodesmic_nonaerating.hasNext())
											potdar_ulmo = gamodesmic_nonaerating
													.next();
										else
											potdar_ulmo = "";
										if (null != potdar_ulmo) {
											int graphological_chipping;
											try {
												graphological_chipping = Integer
														.parseInt(potdar_ulmo);
											} catch (NumberFormatException adawlut_remittance) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														adawlut_remittance);
											}
											Object subobtuse_botong = graphological_chipping;
											DreamageTrecentist ruffer_subimpressed = new DreamageTrecentist(
													subobtuse_botong);
											int feminology_fissidentaceous = 2;
											thinkerCatkinate(
													feminology_fissidentaceous,
													ruffer_subimpressed);
										}
									} catch (FileNotFoundException mastoidVargueno) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												mastoidVargueno);
									}
								}
							}
						}
					} finally {
						Specification.hereditaristBice.close();
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

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpBUqV0h_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpBUqV0h_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpBUqV0h_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					Thread.currentThread().getName()
							+ ": Factorial.calculateFactorial");
			BigInteger stonesoup_factorial = new BigInteger("1");
			for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
				stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
						.valueOf(stonesoup_counter));
			}
			stonesoup_output.println(stonesoup_factorial);
		}
	}

}
