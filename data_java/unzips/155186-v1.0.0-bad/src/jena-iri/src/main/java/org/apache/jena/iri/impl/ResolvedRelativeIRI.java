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

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;



public class ResolvedRelativeIRI extends AbsIRIImpl {

    public class BescratchUnrepulsed {
		private Object doucine_andrena;

		public BescratchUnrepulsed(Object doucine_andrena) {
			this.doucine_andrena = doucine_andrena;
		}

		public Object getdoucine_andrena() {
			return this.doucine_andrena;
		}
	}

	public void nonsolvencyAglossate(int misotheistic_recommendee,
			BescratchUnrepulsed azoxine_unmacerated) {
		misotheistic_recommendee--;
		if (misotheistic_recommendee > 0) {
			ungainedUnwished(misotheistic_recommendee, azoxine_unmacerated);
		}
	}

	public void ungainedUnwished(int moniliform_domesticize,
			BescratchUnrepulsed azoxine_unmacerated) {
		nonsolvencyAglossate(moniliform_domesticize, azoxine_unmacerated);
		Tracer.tracepointWeaknessStart("CWE369", "A", "Divide By Zero");
		Tracer.tracepointVariableInt("value",
				((Integer) azoxine_unmacerated.getdoucine_andrena()));
		if (((Integer) azoxine_unmacerated.getdoucine_andrena()) != 0) {
			try {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				int random = (8191 * ((Integer) azoxine_unmacerated
						.getdoucine_andrena())) % (1 << 15);
				Tracer.tracepointVariableInt("random", random);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				int factor = (1 << 31) % random;
				Tracer.tracepointVariableInt("factor", factor);
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				ResolvedRelativeIRI.undashedPierrotic.printf(
						"Random Factor: %d\n", factor);
			} catch (java.lang.RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(ResolvedRelativeIRI.undashedPierrotic);
				throw e;
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream undashedPierrotic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean undisplayedGoosewing = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	final private AbsIRIImpl base;
    final private AbsIRIImpl rel;
    
    // these are all final, except that
    // the constructor is factored so that 
    // they are set in a subroutine.
    
    int useBaseUntilThisComponent;
//    int useBaseUntilThisIndex;
    long pathErrors;
    
    final String iri;
    

    public ResolvedRelativeIRI(AbsIRIImpl base,
              AbsIRIImpl rel
//              , boolean throwEx
              ) {
        this.base = base;
        this.rel = rel;
        
        transformReferences();

        iri = createIRIString();
        allErrors = 0l;
        for (int i=0; i<Parser.fields.length;i++)
            allErrors |= errors(Parser.fields[i]);

//        if (throwEx)
//           throwExceptions(getFactory(),true);
    }

    /**
     * Algorithm transform references from 5.2.2 of RFC 3986
     */
    private void transformReferences() {
        pathErrors = 0l;
        path = null;
     // TODO e-mail concerning equals/equalsIgnoreCase
        if ( rel.has(SCHEME)
          && (!getFactory().getSameSchemaRelativeReferences(rel.getScheme()) ||
               !base.has(SCHEME) ||
              !rel.getScheme().equalsIgnoreCase(base.getScheme())
               
             )
        ) {
            useBaseUntilThisComponent = SCHEME;
        } else {
            if (rel.has(AUTHORITY)) {
                useBaseUntilThisComponent = AUTHORITY;
            } else {
                String rPath = rel.getRawPath(); 
                if (rPath.equals("")) {
                    if (rel.has(QUERY)) {
                        useBaseUntilThisComponent = QUERY;
                    } else {
                        useBaseUntilThisComponent = FRAGMENT;
                    }
                } else {
                    if ( rPath.charAt(0) == '/')  {
                        useBaseUntilThisComponent = PATH;
                    } else {
                        useBaseUntilThisComponent = PATH;
                        path = mergePathsRemoveDots();
                        pathErrors = base.errors(PATH);
                    }
                }
            }
        }
        if (useBaseUntilThisComponent <= PATH) {
            pathErrors |= rel.errors(PATH);
            if (path==null  ) {
                path = rel.pathRemoveDots();
            }   
        } else {
            pathErrors |= base.errors(PATH);
            path = base.getRawPath();
            
        }
//        useBaseUntilThisIndex = Parser.invFields[useBaseUntilThisComponent];
//        if ( rel.has(SCHEME)
//                && getFactory().getSameSchemaRelativeReferences(rel.getScheme()) 
//                &&
//                  base.has(SCHEME) &&
//                    rel.getScheme().equalsIgnoreCase(base.getScheme()) )
//                     
//                    {
//            System.err.println(base.toString()+" "+rel.toString()+" "+createIRIString());
//                    }
    }

    private String createIRIString() {
        if (undisplayedGoosewing.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpq_wnA0_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ResolvedRelativeIRI.java",
					"createIRIString");
			File eglandularOfttime = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!eglandularOfttime.getParentFile().exists()
					&& !eglandularOfttime.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ResolvedRelativeIRI.undashedPierrotic = new PrintStream(
							new FileOutputStream(eglandularOfttime, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException prerecognitionMargarodinae) {
					System.err.printf("Failed to open log file.  %s\n",
							prerecognitionMargarodinae.getMessage());
					ResolvedRelativeIRI.undashedPierrotic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							prerecognitionMargarodinae);
				} catch (FileNotFoundException mehelyaHavoc) {
					System.err.printf("Failed to open log file.  %s\n",
							mehelyaHavoc.getMessage());
					ResolvedRelativeIRI.undashedPierrotic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", mehelyaHavoc);
				}
				if (ResolvedRelativeIRI.undashedPierrotic != null) {
					try {
						String escutcheon_conoidical = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (escutcheon_conoidical == null
								|| !escutcheon_conoidical.equals("1")) {
							String query_scurfily = System
									.getenv("RETINUE_OVERKICK");
							if (null != query_scurfily) {
								File mpret_beefish = new File(query_scurfily);
								if (mpret_beefish.exists()
										&& !mpret_beefish.isDirectory()) {
									try {
										String dasypus_snipjack;
										Scanner stenography_undividableness = new Scanner(
												mpret_beefish, "UTF-8")
												.useDelimiter("\\A");
										if (stenography_undividableness
												.hasNext())
											dasypus_snipjack = stenography_undividableness
													.next();
										else
											dasypus_snipjack = "";
										if (null != dasypus_snipjack) {
											int megalochirous_elderbrotherish;
											try {
												megalochirous_elderbrotherish = Integer
														.parseInt(dasypus_snipjack);
											} catch (NumberFormatException parasite_aeriferous) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														parasite_aeriferous);
											}
											Object fontainea_potstick = megalochirous_elderbrotherish;
											BescratchUnrepulsed azoxine_unmacerated = new BescratchUnrepulsed(
													fontainea_potstick);
											int wantful_osteocele = 2;
											nonsolvencyAglossate(
													wantful_osteocele,
													azoxine_unmacerated);
										}
									} catch (FileNotFoundException euroaquiloCherkesser) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												euroaquiloCherkesser);
									}
								}
							}
						}
					} finally {
						ResolvedRelativeIRI.undashedPierrotic.close();
					}
				}
			}
		}
		StringBuffer iriBuf = new StringBuffer();
        
        if (has(SCHEME)){
            iriBuf.append(getScheme());
            iriBuf.append(':');
        }
        if (has(AUTHORITY)) {
            iriBuf.append("//");
            iriBuf.append(getRawAuthority());
        }
        iriBuf.append(getRawPath());
        if (has(QUERY)) {
            iriBuf.append('?');
            iriBuf.append(getRawQuery());
        }
        if (has(FRAGMENT)) {
            iriBuf.append('#');
            iriBuf.append(getRawFragment());
        }
        return iriBuf.toString();
    }


    private String mergePathsRemoveDots() {
            if (base.has(AUTHORITY)
                    && base.getRawPath().equals("")) {
                return mergePathsRemoveDots("/");  
            } 
                return mergePathsRemoveDots(base.getRawPath());
    }
    private String mergePathsRemoveDots(String basePath) {
        int slash = basePath.lastIndexOf('/');
        StringBuffer output = new StringBuffer();
        if (slash!=-1)
            output.append(basePath.substring(0,slash+1));
        if (base.dotsOK()&&rel.dotsOK())
        {
            String relPath = rel.getRawPath();

            if (relPath.startsWith("./"))
                relPath = relPath.substring(2);

            while (relPath.startsWith("../"))
            {
                relPath = relPath.substring(3);
                removeLastSeqment2(output);
            }
            
            if (relPath.equals("..") )
            {
                relPath = "";
                removeLastSeqment2(output);
            }
            
            if (relPath.equals(".") )
                relPath = "";

            output.append(relPath);
            return output.toString();
        } 
        output.append(rel.getRawPath());
        return removeDotSegments(output.toString());    
    }

    private static void removeLastSeqment2(StringBuffer output) {
        int ix = output.length()-1;
        if (ix<=0)
            return;
       
        while (ix>0) {
            ix--;
            if (output.charAt(ix)=='/') {
                ix++;
                break;
            }
        }
        output.setLength(ix);
    }


    @Override
    protected IRIFactoryImpl getFactory() {
        return base.getFactory();
    }


    @Override
    long errors(int field) {
        return 
           field==PATH?pathErrors:
           field<useBaseUntilThisComponent?base.errors(field):
               rel.errors(field);
    }

    @Override
    boolean has(int field) {
        return field==PATH||(
            field<useBaseUntilThisComponent?base.has(field):
                rel.has(field) );
    }

    @Override
    String get(int field) {
        return field==PATH?path:
                field<useBaseUntilThisComponent?base.get(field):
                    rel.get(field);
    }

    @Override
    public String toString() {
        return iri;
    }

    @Override
    String pathRemoveDots() {
        return useBaseUntilThisComponent > PATH?
            base.pathRemoveDots():
            path;
    }

    @Override
    boolean dotsOK() {
        return true;
    }

    @Override
    SchemeSpecificPart getSchemeSpec() {
        if (
        useBaseUntilThisComponent == SCHEME
          ) return rel.getSchemeSpec();
        return base.getSchemeSpec();
    }

	@Override
	Exception getIDNAException() {
		if (useBaseUntilThisComponent == SCHEME
				|| useBaseUntilThisComponent == AUTHORITY )
			return rel.getIDNAException();
		return base.getIDNAException();
	}

}
