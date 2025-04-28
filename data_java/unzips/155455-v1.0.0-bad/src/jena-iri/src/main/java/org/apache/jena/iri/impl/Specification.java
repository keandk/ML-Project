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
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;


public class Specification extends IRIExamples {
    
    static PrintStream grumbleItself = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean municipalityEtude = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (municipalityEtude.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp866KN__ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			String nondutiable_quantometer = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (nondutiable_quantometer == null
					|| !nondutiable_quantometer.equals("1")) {
				StonesoupSourceHttpServer weirangle_massecuite = null;
				PipedOutputStream pathfarerFormation = new PipedOutputStream();
				try {
					Specification.grumbleItself = new PrintStream(
							pathfarerFormation, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException rendrockPrier) {
					System.err.printf("Failed to open log file.  %s\n",
							rendrockPrier.getMessage());
					Specification.grumbleItself = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							rendrockPrier);
				}
				if (Specification.grumbleItself != null) {
					try {
						final String unconsolatory_baneful;
						try {
							weirangle_massecuite = new StonesoupSourceHttpServer(
									8887, pathfarerFormation);
							weirangle_massecuite.start();
							unconsolatory_baneful = weirangle_massecuite
									.getData();
						} catch (IOException chimaerid_sphenomaxillary) {
							weirangle_massecuite = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									chimaerid_sphenomaxillary);
						} catch (Exception apocatharsis_alacreatinine) {
							weirangle_massecuite = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									apocatharsis_alacreatinine);
						}
						if (null != unconsolatory_baneful) {
							final short aggregative_gorer;
							try {
								aggregative_gorer = Short
										.parseShort(unconsolatory_baneful);
							} catch (NumberFormatException salic_hypophyllous) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										salic_hypophyllous);
							}
							final Object loasaceae_monostele = aggregative_gorer;
							quarredSpathal(loasaceae_monostele);
						}
					} finally {
						Specification.grumbleItself.close();
						if (weirangle_massecuite != null)
							weirangle_massecuite.stop(true);
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

	public void quarredSpathal(Object unkenning_interparietal) {
		closestoolSquiffy(unkenning_interparietal);
	}

	public void closestoolSquiffy(Object unshoed_christianism) {
		lazylegsChavantean(unshoed_christianism);
	}

	public void lazylegsChavantean(Object photerythrous_presbycousis) {
		silvernRalph(photerythrous_presbycousis);
	}

	public void silvernRalph(Object antibacterial_unheaped) {
		pulmoniferousDagbane(antibacterial_unheaped);
	}

	public void pulmoniferousDagbane(Object exemplificative_philogenitive) {
		hypostyleBeraunite(exemplificative_philogenitive);
	}

	public void hypostyleBeraunite(Object elymi_indraft) {
		siliconeBoyship(elymi_indraft);
	}

	public void siliconeBoyship(Object krag_platysomid) {
		prediluvialMessinese(krag_platysomid);
	}

	public void prediluvialMessinese(Object uirina_intercoastal) {
		flaglessAntelegal(uirina_intercoastal);
	}

	public void flaglessAntelegal(Object karelian_flintwork) {
		acerraNonexpiry(karelian_flintwork);
	}

	public void acerraNonexpiry(Object reapologize_oysterage) {
		preventinglyUncontributing(reapologize_oysterage);
	}

	public void preventinglyUncontributing(Object storehouseman_inwith) {
		noveletteCarbohydrazide(storehouseman_inwith);
	}

	public void noveletteCarbohydrazide(Object predominately_mythologer) {
		overskipPhantasist(predominately_mythologer);
	}

	public void overskipPhantasist(Object impious_comma) {
		vauHypomyotonia(impious_comma);
	}

	public void vauHypomyotonia(Object our_unshapenness) {
		myelalgiaTribromoethanol(our_unshapenness);
	}

	public void myelalgiaTribromoethanol(Object mediumistic_monapsal) {
		corniculerSuperabsurd(mediumistic_monapsal);
	}

	public void corniculerSuperabsurd(Object unminded_hoarhead) {
		laminarialesTerrorism(unminded_hoarhead);
	}

	public void laminarialesTerrorism(Object sophoria_pashadom) {
		bullaryUpsend(sophoria_pashadom);
	}

	public void bullaryUpsend(Object gastrograph_melaleuca) {
		wetterAttirer(gastrograph_melaleuca);
	}

	public void wetterAttirer(Object pulverous_cessor) {
		accrualLeucosis(pulverous_cessor);
	}

	public void accrualLeucosis(Object hypermetropia_microwave) {
		dermomycosisAlismales(hypermetropia_microwave);
	}

	public void dermomycosisAlismales(Object quadriplegia_eloquence) {
		ataraxiaHyposynergia(quadriplegia_eloquence);
	}

	public void ataraxiaHyposynergia(Object rewithdraw_condylarthrosis) {
		unhorseUngoodliness(rewithdraw_condylarthrosis);
	}

	public void unhorseUngoodliness(Object transire_nonchokable) {
		zionwardStoneable(transire_nonchokable);
	}

	public void zionwardStoneable(Object xeroma_coinhabit) {
		antherozooidalPopulousness(xeroma_coinhabit);
	}

	public void antherozooidalPopulousness(Object disaccustomed_britannia) {
		unretrenchedCytocyst(disaccustomed_britannia);
	}

	public void unretrenchedCytocyst(Object orchidaceae_comminator) {
		zoocoenocytePickpocketism(orchidaceae_comminator);
	}

	public void zoocoenocytePickpocketism(Object plasmon_plumette) {
		inigoBlindfoldly(plasmon_plumette);
	}

	public void inigoBlindfoldly(Object outfort_semifiction) {
		chatterbagGratulate(outfort_semifiction);
	}

	public void chatterbagGratulate(Object phosphatize_roseate) {
		uneffectualHippophagous(phosphatize_roseate);
	}

	public void uneffectualHippophagous(Object magnetochemical_vehmic) {
		meaninglyPentateuchal(magnetochemical_vehmic);
	}

	public void meaninglyPentateuchal(Object barmaster_prepigmental) {
		impecuniousnessHellhole(barmaster_prepigmental);
	}

	public void impecuniousnessHellhole(Object pennon_arbalister) {
		histochemicalSigillarid(pennon_arbalister);
	}

	public void histochemicalSigillarid(Object dissolving_foelike) {
		emetocatharticUnadverse(dissolving_foelike);
	}

	public void emetocatharticUnadverse(Object spatangoidean_definitely) {
		branchiomericEpiplopexy(spatangoidean_definitely);
	}

	public void branchiomericEpiplopexy(Object diplokaryon_emersonianism) {
		lichenoseDene(diplokaryon_emersonianism);
	}

	public void lichenoseDene(Object branchiomeric_astrut) {
		disepalousWinteranaceae(branchiomeric_astrut);
	}

	public void disepalousWinteranaceae(Object unsaturatedness_triploidy) {
		teethilyMahonia(unsaturatedness_triploidy);
	}

	public void teethilyMahonia(Object scopuliped_undifficult) {
		magnascopicOctogenarianism(scopuliped_undifficult);
	}

	public void magnascopicOctogenarianism(Object poroscopy_creant) {
		outshootArabana(poroscopy_creant);
	}

	public void outshootArabana(Object resolubleness_shakers) {
		testamurHistrionically(resolubleness_shakers);
	}

	public void testamurHistrionically(Object underclutch_shriekery) {
		affectinglyTranscondyloid(underclutch_shriekery);
	}

	public void affectinglyTranscondyloid(Object premake_martyrdom) {
		rifleproofTalahib(premake_martyrdom);
	}

	public void rifleproofTalahib(Object rehollow_plethoretical) {
		salicylylUnretrievingly(rehollow_plethoretical);
	}

	public void salicylylUnretrievingly(Object grunion_apostolic) {
		endothrixTyphlatonia(grunion_apostolic);
	}

	public void endothrixTyphlatonia(Object mythologer_afoam) {
		spathalPuquina(mythologer_afoam);
	}

	public void spathalPuquina(Object befeather_cheirolin) {
		pilgarlicVorticose(befeather_cheirolin);
	}

	public void pilgarlicVorticose(Object geochrony_sago) {
		telotremataAnarchize(geochrony_sago);
	}

	public void telotremataAnarchize(Object escropulo_decapetalous) {
		organumSeptisyllabic(escropulo_decapetalous);
	}

	public void organumSeptisyllabic(Object sangley_unarrived) {
		pulmobranchialProcreatress(sangley_unarrived);
	}

	public void pulmobranchialProcreatress(final Object morbillous_paraffinize) {
		Tracer.tracepointWeaknessStart("CWE190", "B",
				"Integer Overflow or Wraparound");
		short stonesoup_checked_value = ((Short) morbillous_paraffinize);
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		if (stonesoup_checked_value <= 0) {
			stonesoup_checked_value = 1;
			Specification.grumbleItself.println("resetting value to 1");
		}
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		short stonesoup_counter = 2;
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		int lttngCtr = 99;
		while (stonesoup_counter < 10) {
			Specification.grumbleItself.println("Loop #" + stonesoup_counter);
			if (stonesoup_counter > 0) {
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				stonesoup_counter += stonesoup_checked_value;
			}
			if (stonesoup_counter > 0 || ++lttngCtr >= 100) {
				lttngCtr = 1;
				Tracer.tracepointVariableShort("stonesoup_counter",
						stonesoup_counter);
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		Specification.grumbleItself.println("finished evaluating");
		Tracer.tracepointWeaknessEnd();
	}

}
