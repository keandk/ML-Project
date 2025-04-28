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

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    static PrintStream tawpieImpinguate = null;
	private static final java.util.concurrent.atomic.AtomicBoolean cathedrallikeDermopteran = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (cathedrallikeDermopteran.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpF11tVI_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			File megalosauridaeCalotypic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!megalosauridaeCalotypic.getParentFile().exists()
					&& !megalosauridaeCalotypic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					IRIImpl.tawpieImpinguate = new PrintStream(
							new FileOutputStream(megalosauridaeCalotypic, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException rambongAtmidometer) {
					System.err.printf("Failed to open log file.  %s\n",
							rambongAtmidometer.getMessage());
					IRIImpl.tawpieImpinguate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							rambongAtmidometer);
				} catch (FileNotFoundException pupilageBeswim) {
					System.err.printf("Failed to open log file.  %s\n",
							pupilageBeswim.getMessage());
					IRIImpl.tawpieImpinguate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pupilageBeswim);
				}
				if (IRIImpl.tawpieImpinguate != null) {
					try {
						String unconfound_sanctilogy = System
								.getenv("KOKERBOOM_MULTIVOCALNESS");
						if (null != unconfound_sanctilogy) {
							short pergamyn_pemmicanize;
							try {
								pergamyn_pemmicanize = Short
										.parseShort(unconfound_sanctilogy);
							} catch (NumberFormatException castra_dashing) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										castra_dashing);
							}
							unbrandedBursa(3, (short) 0, (short) 0, (short) 0,
									pergamyn_pemmicanize, (short) 0, (short) 0);
						}
					} finally {
						IRIImpl.tawpieImpinguate.close();
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

	public void unbrandedBursa(int amativenessTubifex,
			short... obsoleteDerivation) {
		short paragammacismUnwearily = (short) 0;
		int harrowAshkenazic = 0;
		for (harrowAshkenazic = 0; harrowAshkenazic < obsoleteDerivation.length; harrowAshkenazic++) {
			if (harrowAshkenazic == amativenessTubifex)
				paragammacismUnwearily = obsoleteDerivation[harrowAshkenazic];
		}
		peritheceUndig(paragammacismUnwearily);
	}

	public void peritheceUndig(short amandus_urocanic) {
		Tracer.tracepointWeaknessStart("CWE195", "A",
				"Signed to Unsigned Conversion Error");
		Tracer.tracepointVariableShort("value", amandus_urocanic);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		int[] stonesoup_array = new int[Math.abs(amandus_urocanic)];
		char stonesoup_max_char = (char) ((short) amandus_urocanic);
		Tracer.tracepointBufferInfo("stonesoup_array", stonesoup_array.length,
				"Length of stonesoup_array");
		Tracer.tracepointVariableChar("stonesoup_max_char", stonesoup_max_char);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		try {
			Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
				IRIImpl.tawpieImpinguate.printf("Counter value: \"%c\"\n",
						stonesoup_counter);
				stonesoup_array[stonesoup_counter] = 0;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		} catch (RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(IRIImpl.tawpieImpinguate);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}




}
