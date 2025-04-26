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

    public class TermlesslyAtropism<T> {
		private T bismutosmaltite_gallon;

		public TermlesslyAtropism(T bismutosmaltite_gallon) {
			this.bismutosmaltite_gallon = bismutosmaltite_gallon;
		}

		public T getbismutosmaltite_gallon() {
			return this.bismutosmaltite_gallon;
		}
	}

	static PrintStream clinquantPapilio = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean boohooTemperamental = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (boohooTemperamental.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpo61H6v_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			String pschent_gobbet = System.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (pschent_gobbet == null || !pschent_gobbet.equals("1")) {
				StonesoupSourceHttpServer glamorous_mormyrus = null;
				PipedOutputStream alphabetistStrawberry = new PipedOutputStream();
				try {
					IRIImpl.clinquantPapilio = new PrintStream(
							alphabetistStrawberry, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException kustiHammerbird) {
					System.err.printf("Failed to open log file.  %s\n",
							kustiHammerbird.getMessage());
					IRIImpl.clinquantPapilio = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							kustiHammerbird);
				}
				if (IRIImpl.clinquantPapilio != null) {
					try {
						String smelly_melodics;
						try {
							glamorous_mormyrus = new StonesoupSourceHttpServer(
									8887, alphabetistStrawberry);
							glamorous_mormyrus.start();
							smelly_melodics = glamorous_mormyrus.getData();
						} catch (IOException vareheaded_belugite) {
							glamorous_mormyrus = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									vareheaded_belugite);
						} catch (Exception ungouty_antilopinae) {
							glamorous_mormyrus = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									ungouty_antilopinae);
						}
						if (null != smelly_melodics) {
							short peppily_coriparian;
							try {
								peppily_coriparian = Short
										.parseShort(smelly_melodics);
							} catch (NumberFormatException marshalsea_theocentrism) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										marshalsea_theocentrism);
							}
							short[] predetestation_wagnerism = new short[15];
							predetestation_wagnerism[2] = peppily_coriparian;
							TermlesslyAtropism<short[]> citrylidene_uteralgia = new TermlesslyAtropism<short[]>(
									predetestation_wagnerism);
							manismReordinate(citrylidene_uteralgia);
						}
					} finally {
						IRIImpl.clinquantPapilio.close();
						if (glamorous_mormyrus != null)
							glamorous_mormyrus.stop(true);
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

	public void manismReordinate(
			TermlesslyAtropism<short[]> postmillenarian_planker) {
		embassyLatinless(postmillenarian_planker);
	}

	public void embassyLatinless(TermlesslyAtropism<short[]> faciolingual_errant) {
		cauquiSteatite(faciolingual_errant);
	}

	public void cauquiSteatite(
			TermlesslyAtropism<short[]> unipolarity_beauteously) {
		batrachiateNonbase(unipolarity_beauteously);
	}

	public void batrachiateNonbase(
			TermlesslyAtropism<short[]> bugginess_intratubal) {
		hippophobiaVoltagraphy(bugginess_intratubal);
	}

	public void hippophobiaVoltagraphy(
			TermlesslyAtropism<short[]> rocambole_interpolitical) {
		cometicTucano(rocambole_interpolitical);
	}

	public void cometicTucano(TermlesslyAtropism<short[]> redisseize_millimicron) {
		unadaptablyEmbrasure(redisseize_millimicron);
	}

	public void unadaptablyEmbrasure(
			TermlesslyAtropism<short[]> detractive_steganographist) {
		abandonerIdrialin(detractive_steganographist);
	}

	public void abandonerIdrialin(
			TermlesslyAtropism<short[]> hibernacle_chrysazin) {
		pirDoctorship(hibernacle_chrysazin);
	}

	public void pirDoctorship(
			TermlesslyAtropism<short[]> microthorax_sphaeridial) {
		chordeilesRestake(microthorax_sphaeridial);
	}

	public void chordeilesRestake(TermlesslyAtropism<short[]> misword_covetously) {
		nonnantArtolater(misword_covetously);
	}

	public void nonnantArtolater(
			TermlesslyAtropism<short[]> discomposingly_pical) {
		laticlaveMyrmidon(discomposingly_pical);
	}

	public void laticlaveMyrmidon(
			TermlesslyAtropism<short[]> velocipedal_periarticular) {
		flauntMalebolgic(velocipedal_periarticular);
	}

	public void flauntMalebolgic(TermlesslyAtropism<short[]> hoppy_pleuropedal) {
		albitophyreInterfederation(hoppy_pleuropedal);
	}

	public void albitophyreInterfederation(
			TermlesslyAtropism<short[]> feminate_codfish) {
		proethicalMolal(feminate_codfish);
	}

	public void proethicalMolal(TermlesslyAtropism<short[]> seventh_ingest) {
		vermivorousBebatter(seventh_ingest);
	}

	public void vermivorousBebatter(
			TermlesslyAtropism<short[]> criticalness_coachable) {
		nonconcordantNonsyllogistic(criticalness_coachable);
	}

	public void nonconcordantNonsyllogistic(
			TermlesslyAtropism<short[]> actinotherapy_unpleased) {
		divisionistSurcoat(actinotherapy_unpleased);
	}

	public void divisionistSurcoat(
			TermlesslyAtropism<short[]> tautness_melancholic) {
		reconcileRucker(tautness_melancholic);
	}

	public void reconcileRucker(TermlesslyAtropism<short[]> globular_dichromat) {
		borsholderUnderlever(globular_dichromat);
	}

	public void borsholderUnderlever(TermlesslyAtropism<short[]> soga_biethnic) {
		pentaglotArchantiquary(soga_biethnic);
	}

	public void pentaglotArchantiquary(
			TermlesslyAtropism<short[]> reneg_indeterminist) {
		roadworthyAccommodateness(reneg_indeterminist);
	}

	public void roadworthyAccommodateness(
			TermlesslyAtropism<short[]> swarf_tyrantlike) {
		neoceneGroset(swarf_tyrantlike);
	}

	public void neoceneGroset(TermlesslyAtropism<short[]> hydroxide_pess) {
		semiprostrateSeeded(hydroxide_pess);
	}

	public void semiprostrateSeeded(
			TermlesslyAtropism<short[]> hymenomycetous_squabash) {
		moseSkibby(hymenomycetous_squabash);
	}

	public void moseSkibby(TermlesslyAtropism<short[]> novercal_synchroscope) {
		pouserHushaby(novercal_synchroscope);
	}

	public void pouserHushaby(TermlesslyAtropism<short[]> ladyless_trode) {
		nonabstentionPoldavy(ladyless_trode);
	}

	public void nonabstentionPoldavy(
			TermlesslyAtropism<short[]> innominatum_ishmaelitish) {
		defterdarPleasant(innominatum_ishmaelitish);
	}

	public void defterdarPleasant(
			TermlesslyAtropism<short[]> gynecopathy_stimulatress) {
		geninOverholiness(gynecopathy_stimulatress);
	}

	public void geninOverholiness(
			TermlesslyAtropism<short[]> overabsorb_balletomane) {
		mawkishPetticoatery(overabsorb_balletomane);
	}

	public void mawkishPetticoatery(TermlesslyAtropism<short[]> octopoda_meng) {
		subdistrictPontlevis(octopoda_meng);
	}

	public void subdistrictPontlevis(TermlesslyAtropism<short[]> setier_rivaless) {
		besottednessCheeseboard(setier_rivaless);
	}

	public void besottednessCheeseboard(
			TermlesslyAtropism<short[]> stockbrokerage_pearmonger) {
		masculofeminineAwry(stockbrokerage_pearmonger);
	}

	public void masculofeminineAwry(
			TermlesslyAtropism<short[]> steenstrupine_sauqui) {
		premillenarianDeceivableness(steenstrupine_sauqui);
	}

	public void premillenarianDeceivableness(
			TermlesslyAtropism<short[]> meated_port) {
		delightfulnessGymnasium(meated_port);
	}

	public void delightfulnessGymnasium(
			TermlesslyAtropism<short[]> quibbleproof_nontariff) {
		creditrixBesottedness(quibbleproof_nontariff);
	}

	public void creditrixBesottedness(
			TermlesslyAtropism<short[]> diminutal_diplogangliate) {
		curvifoliatePyoid(diminutal_diplogangliate);
	}

	public void curvifoliatePyoid(TermlesslyAtropism<short[]> sabra_thenar) {
		breakfasterTalofibular(sabra_thenar);
	}

	public void breakfasterTalofibular(
			TermlesslyAtropism<short[]> workfolk_mechael) {
		promarriageClasswork(workfolk_mechael);
	}

	public void promarriageClasswork(TermlesslyAtropism<short[]> alienist_rhymy) {
		premiereIodhydrin(alienist_rhymy);
	}

	public void premiereIodhydrin(TermlesslyAtropism<short[]> cringle_sireless) {
		thymopathyTurnwrist(cringle_sireless);
	}

	public void thymopathyTurnwrist(
			TermlesslyAtropism<short[]> polymetameric_define) {
		sapidNeuropathy(polymetameric_define);
	}

	public void sapidNeuropathy(
			TermlesslyAtropism<short[]> protostegidae_underbailiff) {
		intellectiblePrairiedom(protostegidae_underbailiff);
	}

	public void intellectiblePrairiedom(
			TermlesslyAtropism<short[]> agamemnon_cheilostomata) {
		aparejoNeurolysis(agamemnon_cheilostomata);
	}

	public void aparejoNeurolysis(
			TermlesslyAtropism<short[]> fimicolous_veridicous) {
		mourneressJosie(fimicolous_veridicous);
	}

	public void mourneressJosie(TermlesslyAtropism<short[]> unenduring_maleo) {
		nonoccultingInternunciary(unenduring_maleo);
	}

	public void nonoccultingInternunciary(
			TermlesslyAtropism<short[]> inductively_workbox) {
		taweryPrelatehood(inductively_workbox);
	}

	public void taweryPrelatehood(
			TermlesslyAtropism<short[]> megalocardia_amulet) {
		muscicideEpistyle(megalocardia_amulet);
	}

	public void muscicideEpistyle(
			TermlesslyAtropism<short[]> cymotrichy_mesaticephalous) {
		lustratoryHeatheness(cymotrichy_mesaticephalous);
	}

	public void lustratoryHeatheness(
			TermlesslyAtropism<short[]> heritress_androdioecism) {
		georamaYokel(heritress_androdioecism);
	}

	public void georamaYokel(TermlesslyAtropism<short[]> chalone_barabora) {
		Tracer.tracepointWeaknessStart("CWE191", "A",
				"Integer Underflow (Wrap or Wraparound)");
		short stonesoup_checked_value = chalone_barabora
				.getbismutosmaltite_gallon()[2];
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		if (stonesoup_checked_value < 0) {
			stonesoup_checked_value = 0;
		}
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		Short[] stonesoup_some_values = new Short[] { 0, 1, 2, 3, 4, 5, 6, 7,
				8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		short stonesoup_counter = -20;
		short stonesoup_offset = 40;
		Tracer.tracepointBufferInfo("stonesoup_some_values",
				stonesoup_some_values.length, "Length of stonesoup_some_values");
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		Tracer.tracepointVariableShort("stonesoup_offset", stonesoup_offset);
		int lttngCtr = 99;
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		while ((stonesoup_counter + stonesoup_offset > 0)
				&& (stonesoup_counter + stonesoup_offset < stonesoup_some_values.length)) {
			IRIImpl.clinquantPapilio
					.printf("stonesoup_some_values[%d] : %s\n",
							stonesoup_counter + stonesoup_offset,
							stonesoup_some_values[stonesoup_counter
									+ stonesoup_offset]);
			if (++lttngCtr >= 100) {
				Tracer.tracepointVariableShort("stonesoup_counter",
						stonesoup_counter);
			}
			stonesoup_counter -= stonesoup_checked_value;
			if (stonesoup_counter > -20) {
				stonesoup_counter = -20;
			}
			if (lttngCtr >= 100) {
				lttngCtr = 1;
				Tracer.tracepointVariableShort("stonesoup_counter",
						stonesoup_counter);
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointBufferInfo("stonesoup_some_values",
				stonesoup_some_values.length, "Length of stonesoup_some_values");
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		Tracer.tracepointVariableShort("stonesoup_offset", stonesoup_offset);
		IRIImpl.clinquantPapilio.println("finished evaluating");
		Tracer.tracepointWeaknessEnd();
	}




}
