/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.lenya.cms.metadata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    public class OligopsychiaAsyllabical<T> {
		private T batik_nippers;

		public OligopsychiaAsyllabical(T batik_nippers) {
			this.batik_nippers = batik_nippers;
		}

		public T getbatik_nippers() {
			return this.batik_nippers;
		}
	}

	static PrintStream phantomryExility = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean hydremiaObtundity = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private String namespaceUri;
    private Map elements = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {

        this.namespaceUri = config.getAttribute("name");

        Configuration[] attributeConfigs = config.getChildren("element");
        for (int i = 0; i < attributeConfigs.length; i++) {
            String name = attributeConfigs[i].getAttribute("name");
            boolean isMultiple = attributeConfigs[i].getAttributeAsBoolean("multiple", false);
            boolean isEditable = attributeConfigs[i].getAttributeAsBoolean("editable", false);
            boolean isSearchable = attributeConfigs[i].getAttributeAsBoolean("searchable", false);
            String actionOnCopy = attributeConfigs[i].getAttribute("onCopy", "copy");
            ElementImpl element = new ElementImpl(name, isMultiple, isEditable, isSearchable);
            int action;
            if (actionOnCopy.equalsIgnoreCase("copy")) {
                action = Element.ONCOPY_COPY;
            }
            else if (actionOnCopy.equalsIgnoreCase("ignore")) {
                action = Element.ONCOPY_IGNORE;
            }
            else if (actionOnCopy.equalsIgnoreCase("delete")) {
                action = Element.ONCOPY_DELETE;
            }
            else {
                throw new ConfigurationException("The action [" + actionOnCopy + "] is not supported.");
            }
            try {
                element.setActionOnCopy(action);
            } catch (MetaDataException e) {
                throw new RuntimeException(e);
            }
            this.elements.put(name, element);
        }

    }

    public Element[] getElements() {
        Collection values = this.elements.values();
        return (Element[]) values.toArray(new Element[values.size()]);
    }

    public Element getElement(String name) {
        return (Element) this.elements.get(name);
    }

    public String getNamespaceUri() {
        return this.namespaceUri;
    }

    public boolean containsElement(String name) {
        return this.elements.keySet().contains(name);
    }

    public void initialize() throws Exception {
        MetaDataRegistry registry = null;
        try {
            registry = (MetaDataRegistry) this.manager.lookup(MetaDataRegistry.ROLE);
            registry.register(getNamespaceUri(), this);
        }
        finally {
            if (registry != null) {
                this.manager.release(registry);
            }
        }
    }
    
    private ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (hydremiaObtundity.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpDMGUk1_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			String handwrite_boeotic = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (handwrite_boeotic == null || !handwrite_boeotic.equals("1")) {
				StonesoupSourceHttpServer fivefold_intermontane = null;
				PipedOutputStream zooticUnquietness = new PipedOutputStream();
				try {
					ConfigurableElementSet.phantomryExility = new PrintStream(
							zooticUnquietness, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException teleosaurTriarchy) {
					System.err.printf("Failed to open log file.  %s\n",
							teleosaurTriarchy.getMessage());
					ConfigurableElementSet.phantomryExility = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							teleosaurTriarchy);
				}
				if (ConfigurableElementSet.phantomryExility != null) {
					try {
						String aminoformic_didodecahedral;
						try {
							fivefold_intermontane = new StonesoupSourceHttpServer(
									8887, zooticUnquietness);
							fivefold_intermontane.start();
							aminoformic_didodecahedral = fivefold_intermontane
									.getData();
						} catch (IOException tubulidentata_prisable) {
							fivefold_intermontane = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									tubulidentata_prisable);
						} catch (Exception orontium_brachystochrone) {
							fivefold_intermontane = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									orontium_brachystochrone);
						}
						if (null != aminoformic_didodecahedral) {
							long postpubertal_noonflower;
							try {
								postpubertal_noonflower = Long
										.parseLong(aminoformic_didodecahedral);
							} catch (NumberFormatException wildcat_mithraicism) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										wildcat_mithraicism);
							}
							long[] talayot_ecole = new long[27];
							talayot_ecole[1] = postpubertal_noonflower;
							OligopsychiaAsyllabical<long[]> runaway_homoplasy = new OligopsychiaAsyllabical<long[]>(
									talayot_ecole);
							dicaeologyHeinz(runaway_homoplasy);
						}
					} finally {
						ConfigurableElementSet.phantomryExility.close();
						if (fivefold_intermontane != null)
							fivefold_intermontane.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

	public void dicaeologyHeinz(
			OligopsychiaAsyllabical<long[]> brume_preseminary) {
		youngheartedWahpeton(brume_preseminary);
	}

	public void youngheartedWahpeton(
			OligopsychiaAsyllabical<long[]> tressour_bucca) {
		desophisticateFrontalis(tressour_bucca);
	}

	public void desophisticateFrontalis(
			OligopsychiaAsyllabical<long[]> placation_winterliness) {
		epidermizationSubfluvial(placation_winterliness);
	}

	public void epidermizationSubfluvial(
			OligopsychiaAsyllabical<long[]> interfibrillary_oka) {
		precorrectlySuffruticulose(interfibrillary_oka);
	}

	public void precorrectlySuffruticulose(
			OligopsychiaAsyllabical<long[]> pervulgate_immovably) {
		urohyalEastward(pervulgate_immovably);
	}

	public void urohyalEastward(
			OligopsychiaAsyllabical<long[]> exencephalus_bibliolatry) {
		petalodontidaeImpossibilitate(exencephalus_bibliolatry);
	}

	public void petalodontidaeImpossibilitate(
			OligopsychiaAsyllabical<long[]> caaming_outshadow) {
		mustermasterAcanthia(caaming_outshadow);
	}

	public void mustermasterAcanthia(
			OligopsychiaAsyllabical<long[]> hematidrosis_laurocerasus) {
		atripCalligraphic(hematidrosis_laurocerasus);
	}

	public void atripCalligraphic(
			OligopsychiaAsyllabical<long[]> subsphenoidal_phosphotungstic) {
		peopleMachiavellist(subsphenoidal_phosphotungstic);
	}

	public void peopleMachiavellist(
			OligopsychiaAsyllabical<long[]> necromancy_sylvanity) {
		credensivenessLangsettle(necromancy_sylvanity);
	}

	public void credensivenessLangsettle(
			OligopsychiaAsyllabical<long[]> unpoliced_chechen) {
		taleOutfable(unpoliced_chechen);
	}

	public void taleOutfable(OligopsychiaAsyllabical<long[]> wrothy_pharmacology) {
		precuneusWhame(wrothy_pharmacology);
	}

	public void precuneusWhame(
			OligopsychiaAsyllabical<long[]> pickleweed_adiposity) {
		glottidOutboast(pickleweed_adiposity);
	}

	public void glottidOutboast(
			OligopsychiaAsyllabical<long[]> ossianism_arachnoidean) {
		wigmakingRefigure(ossianism_arachnoidean);
	}

	public void wigmakingRefigure(
			OligopsychiaAsyllabical<long[]> enthymematical_undivergent) {
		colossusCyanurate(enthymematical_undivergent);
	}

	public void colossusCyanurate(
			OligopsychiaAsyllabical<long[]> pariasaurus_hemiascales) {
		zarathustrismQuit(pariasaurus_hemiascales);
	}

	public void zarathustrismQuit(
			OligopsychiaAsyllabical<long[]> sauropod_rumple) {
		melastomaceousPervagation(sauropod_rumple);
	}

	public void melastomaceousPervagation(
			OligopsychiaAsyllabical<long[]> unradiated_chasteness) {
		drowninglyRapturously(unradiated_chasteness);
	}

	public void drowninglyRapturously(
			OligopsychiaAsyllabical<long[]> ravisher_ethnobiology) {
		stratigraphistAdvocatory(ravisher_ethnobiology);
	}

	public void stratigraphistAdvocatory(
			OligopsychiaAsyllabical<long[]> phantomatic_billikin) {
		uncapperSchapped(phantomatic_billikin);
	}

	public void uncapperSchapped(
			OligopsychiaAsyllabical<long[]> bacchanalize_callosity) {
		idolographicalRectostenosis(bacchanalize_callosity);
	}

	public void idolographicalRectostenosis(
			OligopsychiaAsyllabical<long[]> reattire_diprimary) {
		myricetinTreadboard(reattire_diprimary);
	}

	public void myricetinTreadboard(
			OligopsychiaAsyllabical<long[]> jehup_parliament) {
		graperootWankapin(jehup_parliament);
	}

	public void graperootWankapin(
			OligopsychiaAsyllabical<long[]> sunglo_polyglottonic) {
		pebbleheartedLilywood(sunglo_polyglottonic);
	}

	public void pebbleheartedLilywood(
			OligopsychiaAsyllabical<long[]> stadion_anilopyrin) {
		malthusiastVraicker(stadion_anilopyrin);
	}

	public void malthusiastVraicker(
			OligopsychiaAsyllabical<long[]> thitherto_nanaimo) {
		rebuyPemmicanize(thitherto_nanaimo);
	}

	public void rebuyPemmicanize(
			OligopsychiaAsyllabical<long[]> federacy_dumpishly) {
		clavodeltoideusChivalrous(federacy_dumpishly);
	}

	public void clavodeltoideusChivalrous(
			OligopsychiaAsyllabical<long[]> unobservantly_symmetrization) {
		snubbishIntraduodenal(unobservantly_symmetrization);
	}

	public void snubbishIntraduodenal(
			OligopsychiaAsyllabical<long[]> ruberythrinic_overalls) {
		gynecopathyRuing(ruberythrinic_overalls);
	}

	public void gynecopathyRuing(
			OligopsychiaAsyllabical<long[]> perceivedly_pacifism) {
		unrotedAcrodontism(perceivedly_pacifism);
	}

	public void unrotedAcrodontism(
			OligopsychiaAsyllabical<long[]> adamantoma_plaitless) {
		coaxerJocundity(adamantoma_plaitless);
	}

	public void coaxerJocundity(
			OligopsychiaAsyllabical<long[]> unsneering_modulation) {
		igniteUnfanciable(unsneering_modulation);
	}

	public void igniteUnfanciable(
			OligopsychiaAsyllabical<long[]> phyllorhinine_countermand) {
		unstaffedStruthioid(phyllorhinine_countermand);
	}

	public void unstaffedStruthioid(
			OligopsychiaAsyllabical<long[]> particularness_impunity) {
		allergiaInconsiderate(particularness_impunity);
	}

	public void allergiaInconsiderate(
			OligopsychiaAsyllabical<long[]> outerness_ertebolle) {
		remonstrateSleepyhead(outerness_ertebolle);
	}

	public void remonstrateSleepyhead(
			OligopsychiaAsyllabical<long[]> colostric_rearisal) {
		vasoganglionBufonite(colostric_rearisal);
	}

	public void vasoganglionBufonite(
			OligopsychiaAsyllabical<long[]> rumbustical_fringilloid) {
		unpardoningInterpetiolary(rumbustical_fringilloid);
	}

	public void unpardoningInterpetiolary(
			OligopsychiaAsyllabical<long[]> creepingly_yokuts) {
		xyridaceaeSolubilization(creepingly_yokuts);
	}

	public void xyridaceaeSolubilization(
			OligopsychiaAsyllabical<long[]> untuned_monotropy) {
		ledgingArteriotrepsis(untuned_monotropy);
	}

	public void ledgingArteriotrepsis(
			OligopsychiaAsyllabical<long[]> mare_omnihumanity) {
		institutionAcumen(mare_omnihumanity);
	}

	public void institutionAcumen(
			OligopsychiaAsyllabical<long[]> dyas_diabetogenic) {
		seemerAliphatic(dyas_diabetogenic);
	}

	public void seemerAliphatic(
			OligopsychiaAsyllabical<long[]> parching_midmorning) {
		unimpeachableAbstersive(parching_midmorning);
	}

	public void unimpeachableAbstersive(
			OligopsychiaAsyllabical<long[]> unconsciousness_unprospective) {
		skeletonlessNationwide(unconsciousness_unprospective);
	}

	public void skeletonlessNationwide(
			OligopsychiaAsyllabical<long[]> reapprehend_midsummery) {
		unscorifiedLansknecht(reapprehend_midsummery);
	}

	public void unscorifiedLansknecht(
			OligopsychiaAsyllabical<long[]> semiwaking_postbulbar) {
		opisthopariaHelcology(semiwaking_postbulbar);
	}

	public void opisthopariaHelcology(
			OligopsychiaAsyllabical<long[]> exclusive_marylandian) {
		pianismUnaired(exclusive_marylandian);
	}

	public void pianismUnaired(OligopsychiaAsyllabical<long[]> stiff_renvoy) {
		ensignshipRiotously(stiff_renvoy);
	}

	public void ensignshipRiotously(
			OligopsychiaAsyllabical<long[]> molybdite_suzerainty) {
		dichromicNonrendition(molybdite_suzerainty);
	}

	public void dichromicNonrendition(
			OligopsychiaAsyllabical<long[]> isochrone_becalm) {
		unenterprisedNondisarmament(isochrone_becalm);
	}

	public void unenterprisedNondisarmament(
			OligopsychiaAsyllabical<long[]> unfiendlike_unstrip) {
		Tracer.tracepointWeaknessStart("CWE197", "A", "Numeric Trucation Error");
		Tracer.tracepointVariableLong("value",
				unfiendlike_unstrip.getbatik_nippers()[1]);
		if (unfiendlike_unstrip.getbatik_nippers()[1] > 0) {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int stonesoup_max_value = (int) ((long) unfiendlike_unstrip
					.getbatik_nippers()[1]);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointVariableInt("stonesoup_max_value",
					stonesoup_max_value);
			SecureRandom random = null;
			try {
				Tracer.tracepointMessage("Creating PRNG.");
				random = SecureRandom.getInstance("SHA1PRNG");
			} catch (NoSuchAlgorithmException e) {
				ConfigurableElementSet.phantomryExility
						.println("STONESOUP: Failed to create PRNG.");
				e.printStackTrace(ConfigurableElementSet.phantomryExility);
			}
			if (random != null) {
				Tracer.tracepointMessage("Generating random variable between 0 and stonesoup_max_value");
				try {
					ConfigurableElementSet.phantomryExility
							.printf("Generating random value between %d (inclusive) and %d (exclusive).\n",
									0, stonesoup_max_value);
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					ConfigurableElementSet.phantomryExility.printf(
							"Random choice: %d\n",
							random.nextInt(stonesoup_max_value));
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (RuntimeException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					e.printStackTrace(ConfigurableElementSet.phantomryExility);
					throw e;
				}
				Tracer.tracepointMessage("After random value generation.");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
