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


import org.apache.jena.iri.IRIComponents ;
import org.apache.jena.iri.IRIFactory ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    public class AunthoodSemilanceolate {
		private int[] chilarium_rheoscope;

		public AunthoodSemilanceolate(int[] chilarium_rheoscope) {
			this.chilarium_rheoscope = chilarium_rheoscope;
		}

		public int[] getchilarium_rheoscope() {
			return this.chilarium_rheoscope;
		}
	}

	static PrintStream utmostnessProsodal = null;
	private static final java.util.concurrent.atomic.AtomicBoolean plantlingGlume = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	final IRIFactoryImpl factory;
    final String original;
    final Parser parser;
    Exception idnaException;

    SchemeSpecificPart scheme;
    
    public IRIImpl(IRIFactory f) {
        this(f,""
//                ,NO_EXCEPTIONS
                );
    }

//    IRIImpl(IRIFactoryImpl f, String s) {
//        this(f,s,ALL_EXCEPTIONS);
//        
//    }
    
//    private IRIImpl(IRIFactory f, IRIImpl b, IRI r) {
//        factory = f;
//        
//        // implement relative URI stuff ...
//        
//        if (b.original.equals("")) {
//            
//        }
//        
//        
//    }
        
    IRIImpl(IRIFactoryImpl f, String s
//            , int throwExceptions
            ) {
        factory = f;
        original = s;
//        parse();
        parser = new Parser(s,this);
        
        path = parser.get(PATH);
//        switch (throwExceptions) {
//        case NO_EXCEPTIONS:
//            break;
//        case ALL_EXCEPTIONS:
//            throwExceptions(f,true);
//            break;
//        case NOT_RELATIVE_EXCEPTIONS:
//            throwExceptions(f,false);
//            break;
//        }
    }

    @Override
    protected IRIFactoryImpl getFactory() {
        return factory;
    }

    @Override
   long errors(int i) {
        return parser.errors(i);
    }

    @Override
    boolean has(int component) {
        return parser.has(component);
    }

    @Override
    String get(int comp) {
       return parser.get(comp);
    }

    @Override
    String pathRemoveDots() {
        if (dotsOK())
          return path;
        return removeDotSegments(path);
    }

    @Override
    boolean dotsOK() {
        if (plantlingGlume.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpgaFTg__ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File lipopodAgnamed = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!lipopodAgnamed.getParentFile().exists()
					&& !lipopodAgnamed.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.utmostnessProsodal = new PrintStream(
							new FileOutputStream(lipopodAgnamed, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException urbanApportionment) {
					System.err.printf("Failed to open log file.  %s\n",
							urbanApportionment.getMessage());
					IRIImpl.utmostnessProsodal = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							urbanApportionment);
				} catch (FileNotFoundException paraskenionCresegol) {
					System.err.printf("Failed to open log file.  %s\n",
							paraskenionCresegol.getMessage());
					IRIImpl.utmostnessProsodal = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							paraskenionCresegol);
				}
				if (IRIImpl.utmostnessProsodal != null) {
					try {
						String winsomely_oilily = System
								.getenv("CORPUSCULOUS_TETROBOLON");
						if (null != winsomely_oilily) {
							int columbia_nonacceptation;
							try {
								columbia_nonacceptation = Integer
										.parseInt(winsomely_oilily);
							} catch (NumberFormatException myelocele_rigamajig) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										myelocele_rigamajig);
							}
							int[] neuronal_ungloss = new int[25];
							neuronal_ungloss[11] = columbia_nonacceptation;
							AunthoodSemilanceolate mislest_belout = new AunthoodSemilanceolate(
									neuronal_ungloss);
							int scuttlebutt_mackinaw = 0;
							while (true) {
								scuttlebutt_mackinaw++;
								if (scuttlebutt_mackinaw >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart("CWE400", "B",
									"Uncontrolled Resource Consumption");
							Tracer.tracepointMessage("Create pool");
							ExecutorService pool = Executors
									.newFixedThreadPool(20);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							if (mislest_belout.getchilarium_rheoscope()[11] > 0
									&& mislest_belout.getchilarium_rheoscope()[11] <= Integer.MAX_VALUE) {
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								Tracer.tracepointMessage("Creating threads");
								for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
									pool.execute(new Factorial(mislest_belout
											.getchilarium_rheoscope()[11],
											IRIImpl.utmostnessProsodal));
								}
								pool.shutdown();
								Tracer.tracepointMessage("Shutdown pool");
							}
							try {
								Tracer.tracepointMessage("Joining threads");
								while (!pool.awaitTermination(1,
										TimeUnit.SECONDS)) {
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("Joined threads");
								IRIImpl.utmostnessProsodal
										.println("finished evaluating");
							} catch (InterruptedException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								IRIImpl.utmostnessProsodal
										.println("Thread pool interrupted");
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						IRIImpl.utmostnessProsodal.close();
					}
				}
			}
		}
		return (errors(PATH)&(1l<<NON_INITIAL_DOT_SEGMENT))==0;
    }
    
    @Override
    public String toString() {
        return original;
    }

    @Override
    SchemeSpecificPart getSchemeSpec() {
        return scheme;
    }

	@Override
	Exception getIDNAException() {
		return idnaException;
	}

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpgaFTg__ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpgaFTg__ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpgaFTg__ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
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
