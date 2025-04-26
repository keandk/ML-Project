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
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    private static final int overrefined_exasperatingly = 2;
	static PrintStream glegTeahouse = null;

	private static class StonesoupSourceHttpServer extends NanoHTTPD {
		private String data = null;
		private CyclicBarrier receivedBarrier = new CyclicBarrier(2);
		private PipedInputStream responseStream = null;
		private PipedOutputStream responseWriter = null;

		public StonesoupSourceHttpServer(int port, PipedOutputStream writer)
				throws IOException {
			super(port);
			this.responseWriter = writer;
		}

		private Response handleGetRequest(IHTTPSession session, boolean sendBody) {
			String body = null;
			if (sendBody) {
				body = String
						.format("Request Approved!\n\n"
								+ "Thank you for you interest in \"%s\".\n\n"
								+ "We appreciate your inquiry.  Please visit us again!",
								session.getUri());
			}
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT,
					body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleOptionsRequest(IHTTPSession session) {
			NanoHTTPD.Response response = new NanoHTTPD.Response(null);
			response.setStatus(NanoHTTPD.Response.Status.OK);
			response.setMimeType(NanoHTTPD.MIME_PLAINTEXT);
			response.addHeader("Allow", "GET, PUT, POST, HEAD, OPTIONS");
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleUnallowedRequest(IHTTPSession session) {
			String body = String.format("Method Not Allowed!\n\n"
					+ "Thank you for your request, but we are unable "
					+ "to process that method.  Please try back later.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.METHOD_NOT_ALLOWED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handlePostRequest(IHTTPSession session) {
			String body = String
					.format("Request Data Processed!\n\n"
							+ "Thank you for your contribution.  Please keep up the support.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.CREATED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private NanoHTTPD.Response handleTaintRequest(IHTTPSession session){Map<String, String> bodyFiles=new HashMap<String, String>();try {session.parseBody(bodyFiles);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}catch (ResponseException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}if (!session.getParms().containsKey("data")){return writeErrorResponse(session,Response.Status.BAD_REQUEST,"Missing required field \"data\".");}this.data=session.getParms().get("data");try {this.responseStream=new PipedInputStream(this.responseWriter);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}NanoHTTPD.Response response=new NanoHTTPD.Response(NanoHTTPD.Response.Status.CREATED,NanoHTTPD.MIME_PLAINTEXT,this.responseStream);this.setResponseOptions(session,response);response.setChunkedTransfer(true);try {this.receivedBarrier.await();} catch (InterruptedException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}catch (BrokenBarrierException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}return response;}		private NanoHTTPD.Response writeErrorResponse(IHTTPSession session,
				NanoHTTPD.Response.Status status, String message) {
			String body = String.format(
					"There was an issue processing your request!\n\n"
							+ "Reported Error Message:\n\n%s.", message);
			NanoHTTPD.Response response = new NanoHTTPD.Response(status,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private void setResponseOptions(IHTTPSession session,
				NanoHTTPD.Response response) {
			response.setRequestMethod(session.getMethod());
		}

		@Override
		public Response serve(IHTTPSession session) {
			Method method = session.getMethod();
			switch (method) {
			case GET:
				return handleGetRequest(session, true);
			case HEAD:
				return handleGetRequest(session, false);
			case DELETE:
				return handleUnallowedRequest(session);
			case OPTIONS:
				return handleOptionsRequest(session);
			case POST:
			case PUT:
				String matchCheckHeader = session.getHeaders().get("if-match");
				if (matchCheckHeader == null
						|| !matchCheckHeader
								.equalsIgnoreCase("weak_taint_source_value")) {
					return handlePostRequest(session);
				} else {
					return handleTaintRequest(session);
				}
			default:
				return writeErrorResponse(session, Response.Status.BAD_REQUEST,
						"Unsupported request method.");
			}
		}

		public String getData() throws IOException {
			try {
				this.receivedBarrier.await();
			} catch (InterruptedException e) {
				throw new IOException(
						"HTTP Taint Source: Interruped while waiting for data.",
						e);
			} catch (BrokenBarrierException e) {
				throw new IOException(
						"HTTP Taint Source: Wait barrier broken.", e);
			}
			return this.data;
		}
	}

	private static final java.util.concurrent.atomic.AtomicBoolean cenosityErastianize = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (cenosityErastianize.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpatoVo8_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			String hepaticologist_amygdalotomy = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (hepaticologist_amygdalotomy == null
					|| !hepaticologist_amygdalotomy.equals("1")) {
				StonesoupSourceHttpServer aceratherium_lactiflorous = null;
				PipedOutputStream ulemorrhagiaOligocarpous = new PipedOutputStream();
				try {
					IRIImpl.glegTeahouse = new PrintStream(
							ulemorrhagiaOligocarpous, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException liberalisticPenal) {
					System.err.printf("Failed to open log file.  %s\n",
							liberalisticPenal.getMessage());
					IRIImpl.glegTeahouse = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							liberalisticPenal);
				}
				if (IRIImpl.glegTeahouse != null) {
					try {
						String mouthishly_thinglikeness;
						try {
							aceratherium_lactiflorous = new StonesoupSourceHttpServer(
									8887, ulemorrhagiaOligocarpous);
							aceratherium_lactiflorous.start();
							mouthishly_thinglikeness = aceratherium_lactiflorous
									.getData();
						} catch (IOException slantly_unaffectionate) {
							aceratherium_lactiflorous = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									slantly_unaffectionate);
						} catch (Exception geogenetic_leepit) {
							aceratherium_lactiflorous = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									geogenetic_leepit);
						}
						if (null != mouthishly_thinglikeness) {
							Object diskelion_choachyte = mouthishly_thinglikeness;
							Object[] solonetzic_manlily = new Object[10];
							solonetzic_manlily[overrefined_exasperatingly] = diskelion_choachyte;
							tractateImperatively(solonetzic_manlily);
						}
					} finally {
						IRIImpl.glegTeahouse.close();
						if (aceratherium_lactiflorous != null)
							aceratherium_lactiflorous.stop(true);
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

	public void tractateImperatively(Object[] virilescence_sleuth) {
		bachelorhoodShish(virilescence_sleuth);
	}

	public void bachelorhoodShish(Object[] aristulate_becurl) {
		anadipsiaMulticostate(aristulate_becurl);
	}

	public void anadipsiaMulticostate(Object[] anthill_subzone) {
		remodelGimcracky(anthill_subzone);
	}

	public void remodelGimcracky(Object[] holodiscus_ophiuran) {
		hoofinessArecaine(holodiscus_ophiuran);
	}

	public void hoofinessArecaine(Object[] heiress_otherism) {
		girtlineUnbelief(heiress_otherism);
	}

	public void girtlineUnbelief(Object[] cynophilist_kyklopes) {
		uncontentedlyFiduciarily(cynophilist_kyklopes);
	}

	public void uncontentedlyFiduciarily(Object[] limpy_blubberous) {
		gaudsmanChinotoxine(limpy_blubberous);
	}

	public void gaudsmanChinotoxine(Object[] definement_asynchronism) {
		antecessorPseudoptosis(definement_asynchronism);
	}

	public void antecessorPseudoptosis(Object[] azelfafage_upsitten) {
		placationCastrametation(azelfafage_upsitten);
	}

	public void placationCastrametation(Object[] platband_orphandom) {
		disapprovalParagammacism(platband_orphandom);
	}

	public void disapprovalParagammacism(Object[] talaing_hotly) {
		shamanizeTickseed(talaing_hotly);
	}

	public void shamanizeTickseed(Object[] pentahedral_cholecystogram) {
		chopfallenTitillater(pentahedral_cholecystogram);
	}

	public void chopfallenTitillater(Object[] laterodorsal_stylistical) {
		sycosisPrespecialize(laterodorsal_stylistical);
	}

	public void sycosisPrespecialize(Object[] phlebotomy_locomotor) {
		sharerSitus(phlebotomy_locomotor);
	}

	public void sharerSitus(Object[] iguanodontoidea_penetrology) {
		dessertspoonfulQuadrature(iguanodontoidea_penetrology);
	}

	public void dessertspoonfulQuadrature(Object[] tog_inflater) {
		iotizeHydrocoele(tog_inflater);
	}

	public void iotizeHydrocoele(Object[] sullen_pongee) {
		virtuallyBogmire(sullen_pongee);
	}

	public void virtuallyBogmire(Object[] squirism_linesman) {
		unrailedCreditableness(squirism_linesman);
	}

	public void unrailedCreditableness(Object[] bursa_obvolve) {
		zimmerwaldianDrawstop(bursa_obvolve);
	}

	public void zimmerwaldianDrawstop(Object[] rosarium_prostatorrhoea) {
		opiomaniacBowery(rosarium_prostatorrhoea);
	}

	public void opiomaniacBowery(Object[] andaqui_superabhor) {
		smatteringlyPrehensorial(andaqui_superabhor);
	}

	public void smatteringlyPrehensorial(Object[] vendor_dipteral) {
		tubicornousSuboctuple(vendor_dipteral);
	}

	public void tubicornousSuboctuple(Object[] retrieve_noneffervescent) {
		sacerdocyIntraperineal(retrieve_noneffervescent);
	}

	public void sacerdocyIntraperineal(Object[] subdecuple_capelet) {
		predisturbanceParchingly(subdecuple_capelet);
	}

	public void predisturbanceParchingly(Object[] sampi_hypochloruria) {
		slavelikeAnswerable(sampi_hypochloruria);
	}

	public void slavelikeAnswerable(Object[] execratory_nondeparture) {
		curtainlessUlmo(execratory_nondeparture);
	}

	public void curtainlessUlmo(Object[] euphausiidae_elaphine) {
		aloelikeUnsusceptibly(euphausiidae_elaphine);
	}

	public void aloelikeUnsusceptibly(Object[] pyrolyze_socklessness) {
		ratiocinantPauciarticulate(pyrolyze_socklessness);
	}

	public void ratiocinantPauciarticulate(Object[] snipjack_outstarter) {
		chattanooganUncrossed(snipjack_outstarter);
	}

	public void chattanooganUncrossed(Object[] butcherous_lysogenic) {
		tearageDrunkard(butcherous_lysogenic);
	}

	public void tearageDrunkard(Object[] homocerebrin_gekko) {
		runologistProdissolution(homocerebrin_gekko);
	}

	public void runologistProdissolution(Object[] semimolecule_destinist) {
		oxytylotatePrytanize(semimolecule_destinist);
	}

	public void oxytylotatePrytanize(Object[] noily_tangi) {
		horstBisinuation(noily_tangi);
	}

	public void horstBisinuation(Object[] gastrocystic_aminogen) {
		quartzicBoozer(gastrocystic_aminogen);
	}

	public void quartzicBoozer(Object[] shaggedness_trimerite) {
		condescendinglyInterwove(shaggedness_trimerite);
	}

	public void condescendinglyInterwove(Object[] cobblership_congeneric) {
		fiduciarilyRooklike(cobblership_congeneric);
	}

	public void fiduciarilyRooklike(Object[] perceivedness_goffered) {
		understairsBeamily(perceivedness_goffered);
	}

	public void understairsBeamily(Object[] hornerah_unimportuned) {
		creeshKetting(hornerah_unimportuned);
	}

	public void creeshKetting(Object[] bean_unsoundly) {
		organistChymase(bean_unsoundly);
	}

	public void organistChymase(Object[] stouthearted_scabwort) {
		convictiveContrapose(stouthearted_scabwort);
	}

	public void convictiveContrapose(Object[] calendry_smearcase) {
		cartwrightingHabenaria(calendry_smearcase);
	}

	public void cartwrightingHabenaria(Object[] lacepiece_supply) {
		vandalismPitapatation(lacepiece_supply);
	}

	public void vandalismPitapatation(Object[] antaimerina_copolymerize) {
		amygdalaceousSmelly(antaimerina_copolymerize);
	}

	public void amygdalaceousSmelly(Object[] membership_hagiolatry) {
		demonessGenteelize(membership_hagiolatry);
	}

	public void demonessGenteelize(Object[] ceruse_sconce) {
		quinopyrinPileate(ceruse_sconce);
	}

	public void quinopyrinPileate(Object[] erosionist_shapeshifter) {
		stabulateBeneficently(erosionist_shapeshifter);
	}

	public void stabulateBeneficently(Object[] trierucin_buteo) {
		resplendExanthem(trierucin_buteo);
	}

	public void resplendExanthem(Object[] saccharomycete_coree) {
		gibblegabbleSubstratal(saccharomycete_coree);
	}

	public void gibblegabbleSubstratal(Object[] imposing_hypothecative) {
		pelotherapyTche(imposing_hypothecative);
	}

	public void pelotherapyTche(Object[] severish_weinbergerite) {
		Tracer.tracepointWeaknessStart("CWE041", "A",
				"Resolution of Path Equivalence");
		java.io.BufferedReader reader = null;
		String valueString = ((String) severish_weinbergerite[overrefined_exasperatingly])
				.trim();
		Tracer.tracepointVariableString("value",
				((String) severish_weinbergerite[overrefined_exasperatingly]));
		Tracer.tracepointVariableString("valueString", valueString);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		if (valueString.length() != 0 && valueString.startsWith("/etc/")) {
			IRIImpl.glegTeahouse
					.println("Access Denied.	Attempt to access a restricted file in \"/etc\".");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			java.io.File readPath = new java.io.File(valueString);
			if (readPath.isFile()) {
				try {
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					java.io.FileInputStream fis = new java.io.FileInputStream(
							readPath);
					reader = new java.io.BufferedReader(
							new java.io.InputStreamReader(fis));
					String line = null;
					while ((line = reader.readLine()) != null) {
						IRIImpl.glegTeahouse.println(line);
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (java.io.FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					IRIImpl.glegTeahouse.printf("File \"%s\" does not exist\n",
							readPath.getPath());
				} catch (java.io.IOException ioe) {
					Tracer.tracepointError(ioe.getClass().getName() + ": "
							+ ioe.getMessage());
					IRIImpl.glegTeahouse.println("Failed to read file.");
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (java.io.IOException e) {
						IRIImpl.glegTeahouse
								.println("STONESOUP: Closing file quietly.");
					}
				}
			} else {
				Tracer.tracepointMessage("File doesn't exist");
				IRIImpl.glegTeahouse.printf("File \"%s\" does not exist\n",
						readPath.getPath());
			}
		}
		Tracer.tracepointWeaknessEnd();
	}




}
