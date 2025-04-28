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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    static PrintStream enlighteningTreey = null;
	private static final java.util.concurrent.atomic.AtomicBoolean versiclerTycoon = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (versiclerTycoon.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp6WA1uO_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File benzalanilineDesmoscolex = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!benzalanilineDesmoscolex.getParentFile().exists()
					&& !benzalanilineDesmoscolex.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.enlighteningTreey = new PrintStream(
							new FileOutputStream(benzalanilineDesmoscolex,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException unachingShrike) {
					System.err.printf("Failed to open log file.  %s\n",
							unachingShrike.getMessage());
					IRIImpl.enlighteningTreey = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unachingShrike);
				} catch (FileNotFoundException antherozooidalGauteite) {
					System.err.printf("Failed to open log file.  %s\n",
							antherozooidalGauteite.getMessage());
					IRIImpl.enlighteningTreey = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							antherozooidalGauteite);
				}
				if (IRIImpl.enlighteningTreey != null) {
					try {
						String unbloodily_olla = System
								.getenv("ROPEWALKER_TARDLE");
						if (null != unbloodily_olla) {
							long skinking_toilette;
							try {
								skinking_toilette = Long
										.parseLong(unbloodily_olla);
							} catch (NumberFormatException unrecordable_cystotomy) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										unrecordable_cystotomy);
							}
							Object workloom_midstroke = skinking_toilette;
							idolatrousPudicity(3, null, null, null,
									workloom_midstroke, null, null);
						}
					} finally {
						IRIImpl.enlighteningTreey.close();
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

	public void idolatrousPudicity(int againstOccipitomastoid,
			Object... newcalChili) {
		Object volitateTrenchwork = null;
		int vascularlyNiota = 0;
		for (vascularlyNiota = 0; vascularlyNiota < newcalChili.length; vascularlyNiota++) {
			if (vascularlyNiota == againstOccipitomastoid)
				volitateTrenchwork = newcalChili[vascularlyNiota];
		}
		boolean paraglycogen_phytophagan = false;
		bettering_dioestrum: for (int procurate_hyphenize = 0; procurate_hyphenize < 10; procurate_hyphenize++)
			for (int subaudition_larvicide = 0; subaudition_larvicide < 10; subaudition_larvicide++)
				if (procurate_hyphenize * subaudition_larvicide == 63) {
					paraglycogen_phytophagan = true;
					break bettering_dioestrum;
				}
		Tracer.tracepointWeaknessStart("CWE197", "A", "Numeric Trucation Error");
		Tracer.tracepointVariableLong("value", ((Long) volitateTrenchwork));
		if (((Long) volitateTrenchwork) > 0) {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int stonesoup_max_value = (int) ((long) ((Long) volitateTrenchwork));
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointVariableInt("stonesoup_max_value",
					stonesoup_max_value);
			SecureRandom random = null;
			try {
				Tracer.tracepointMessage("Creating PRNG.");
				random = SecureRandom.getInstance("SHA1PRNG");
			} catch (NoSuchAlgorithmException e) {
				IRIImpl.enlighteningTreey
						.println("STONESOUP: Failed to create PRNG.");
				e.printStackTrace(IRIImpl.enlighteningTreey);
			}
			if (random != null) {
				Tracer.tracepointMessage("Generating random variable between 0 and stonesoup_max_value");
				try {
					IRIImpl.enlighteningTreey
							.printf("Generating random value between %d (inclusive) and %d (exclusive).\n",
									0, stonesoup_max_value);
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					IRIImpl.enlighteningTreey.printf("Random choice: %d\n",
							random.nextInt(stonesoup_max_value));
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (RuntimeException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					e.printStackTrace(IRIImpl.enlighteningTreey);
					throw e;
				}
				Tracer.tracepointMessage("After random value generation.");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}




}
