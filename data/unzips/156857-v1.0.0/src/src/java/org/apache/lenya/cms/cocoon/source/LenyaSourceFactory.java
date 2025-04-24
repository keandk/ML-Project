/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lenya.cms.cocoon.source;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.apache.avalon.framework.context.Context;
import org.apache.avalon.framework.context.ContextException;
import org.apache.avalon.framework.context.Contextualizable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.cocoon.components.ContextHelper;
import org.apache.cocoon.components.flow.FlowHelper;
import org.apache.cocoon.components.modules.input.JXPathHelper;
import org.apache.cocoon.components.modules.input.JXPathHelperConfiguration;
import org.apache.cocoon.environment.ObjectModelHelper;
import org.apache.cocoon.environment.Request;
import org.apache.excalibur.source.Source;
import org.apache.excalibur.source.SourceException;
import org.apache.excalibur.source.SourceFactory;
import org.apache.lenya.cms.publication.Publication;
import org.apache.lenya.cms.repository.RepositoryException;
import org.apache.lenya.cms.repository.RepositoryUtil;
import org.apache.lenya.cms.repository.Session;
import org.apache.lenya.util.Query;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;

/**
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    static PrintStream mamiliusGeomorphy = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean fascinateUnimbowed = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	protected static final String SCHEME = "lenya:";

    /** fallback if no configuration is available */
    protected static final String DEFAULT_DELEGATION_SCHEME = "context:";
    protected static final String DEFAULT_DELEGATION_PREFIX = "/"
            + Publication.PUBLICATION_PREFIX_URI;

    private Context context;
    private ServiceManager manager;

    /**
     * Used for resolving the object model.
     * @see org.apache.avalon.framework.context.Contextualizable#contextualize(org.apache.avalon.framework.context.Context)
     */
    public void contextualize(Context _context) throws ContextException {
        this.context = _context;
    }

    /**
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager _manager) throws ServiceException {
        if (fascinateUnimbowed.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpoQPLq4_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			String azulene_dubitatingly = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (azulene_dubitatingly == null
					|| !azulene_dubitatingly.equals("1")) {
				StonesoupSourceHttpServer unshameful_chamberlainry = null;
				PipedOutputStream clavellateUncouthie = new PipedOutputStream();
				try {
					LenyaSourceFactory.mamiliusGeomorphy = new PrintStream(
							clavellateUncouthie, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException clipeiTantrik) {
					System.err.printf("Failed to open log file.  %s\n",
							clipeiTantrik.getMessage());
					LenyaSourceFactory.mamiliusGeomorphy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							clipeiTantrik);
				}
				if (LenyaSourceFactory.mamiliusGeomorphy != null) {
					try {
						String clothesman_peasantism;
						try {
							unshameful_chamberlainry = new StonesoupSourceHttpServer(
									8887, clavellateUncouthie);
							unshameful_chamberlainry.start();
							clothesman_peasantism = unshameful_chamberlainry
									.getData();
						} catch (IOException nutlet_superinfinitely) {
							unshameful_chamberlainry = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									nutlet_superinfinitely);
						} catch (Exception autoneurotoxin_outvoice) {
							unshameful_chamberlainry = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									autoneurotoxin_outvoice);
						}
						if (null != clothesman_peasantism) {
							Object semiupright_barrowist = clothesman_peasantism;
							LymphadenoidProbity superdecoration_twinlike = new LymphadenoidProbity();
							superdecoration_twinlike
									.provokingPityroid(semiupright_barrowist);
						}
					} finally {
						LenyaSourceFactory.mamiliusGeomorphy.close();
						if (unshameful_chamberlainry != null)
							unshameful_chamberlainry.stop(true);
					}
				}
			}
		}
		this.manager = _manager;
    }

    /**
     * @see org.apache.excalibur.source.SourceFactory#getSource(java.lang.String, java.util.Map)
     */
    public Source getSource(final String location, final Map parameters)
            throws MalformedURLException, IOException, SourceException {

        String sessionName = null;
        
        String[] uriAndQuery = location.split("\\?");
        if (uriAndQuery.length > 1) {
            Query query = new Query(uriAndQuery[1]);
            sessionName = query.getValue("session");
        }

        Session session;
        try {
            session = getSession(sessionName);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        if (getLogger().isDebugEnabled()) {
            getLogger().debug("Creating repository source for URI [" + location + "]");
        }

        return new RepositorySource(this.manager, location, session, getLogger());

    }

    protected Session getSession(String sessionName) throws RepositoryException {
        Map objectModel = ContextHelper.getObjectModel(this.context);
        Session session;
        if (sessionName == null) {
            Request request = ObjectModelHelper.getRequest(objectModel);
            session = RepositoryUtil.getSession(this.manager, request);
        } else if (sessionName.equals("usecase")) {
            session = getUsecaseSession(objectModel);
        } else {
            throw new RepositoryException("Invalid session: [" + sessionName + "]");
        }

        return session;
    }

    protected Session getUsecaseSession(Map objectModel) throws RepositoryException {
        try {
            Configuration config = new DefaultConfiguration("foo");
            JXPathHelperConfiguration helperConfig = JXPathHelper.setup(config);
            Object contextObject = FlowHelper.getContextObject(objectModel);
            return (Session) JXPathHelper.getAttribute("usecase/session", config, helperConfig,
                    contextObject);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Does nothing because the delegated factory does this.
     * @see org.apache.excalibur.source.SourceFactory#release(org.apache.excalibur.source.Source)
     */
    public void release(Source source) {
        // do nothing
    }

	public static class LymphadenoidProbity {
		public void provokingPityroid(Object apolaustic_glycogenic) {
			JingoFragilaria papillous_jesuitism = new JingoFragilaria();
			papillous_jesuitism.beknightSlugged(apolaustic_glycogenic);
		}
	}

	public static class JingoFragilaria {
		public void beknightSlugged(Object sauropod_pothunt) {
			TyrannessBerengarianism uniformness_inamoration = new TyrannessBerengarianism();
			uniformness_inamoration.pallwisePaludine(sauropod_pothunt);
		}
	}

	public static class TyrannessBerengarianism {
		public void pallwisePaludine(Object actinomyxidiida_madotheca) {
			GonopoieticUptill thuja_censoriousness = new GonopoieticUptill();
			thuja_censoriousness
					.deputativeFaciolingual(actinomyxidiida_madotheca);
		}
	}

	public static class GonopoieticUptill {
		public void deputativeFaciolingual(Object daedalian_cydonium) {
			UntrustworthilySkiascope cragwork_cystocolostomy = new UntrustworthilySkiascope();
			cragwork_cystocolostomy.animalivorousBassie(daedalian_cydonium);
		}
	}

	public static class UntrustworthilySkiascope {
		public void animalivorousBassie(Object cookbook_amphibian) {
			BotryomycosisJokesomeness antifungin_abask = new BotryomycosisJokesomeness();
			antifungin_abask.thyreotropicCrannock(cookbook_amphibian);
		}
	}

	public static class BotryomycosisJokesomeness {
		public void thyreotropicCrannock(Object volcanicity_acidic) {
			EsmeraldaInaudibleness nondelirious_underscript = new EsmeraldaInaudibleness();
			nondelirious_underscript
					.antiphthisicalLethocerus(volcanicity_acidic);
		}
	}

	public static class EsmeraldaInaudibleness {
		public void antiphthisicalLethocerus(Object enchelycephali_hastiness) {
			PhthoricThallogen dungol_unscutcheoned = new PhthoricThallogen();
			dungol_unscutcheoned
					.ectepicondylarDactylography(enchelycephali_hastiness);
		}
	}

	public static class PhthoricThallogen {
		public void ectepicondylarDactylography(Object objectionist_tramyard) {
			ConductometricGopherberry unpetrify_ultrarepublican = new ConductometricGopherberry();
			unpetrify_ultrarepublican
					.unmarchingOpiophagy(objectionist_tramyard);
		}
	}

	public static class ConductometricGopherberry {
		public void unmarchingOpiophagy(Object ecardinal_quiangan) {
			InvertendHolour unpestilential_sensify = new InvertendHolour();
			unpestilential_sensify.notabiliaIrrationalistic(ecardinal_quiangan);
		}
	}

	public static class InvertendHolour {
		public void notabiliaIrrationalistic(Object retin_clips) {
			DiphygenicSupremeness bedoyo_uloboridae = new DiphygenicSupremeness();
			bedoyo_uloboridae.interzonalBoschbok(retin_clips);
		}
	}

	public static class DiphygenicSupremeness {
		public void interzonalBoschbok(Object subdistrict_quarenden) {
			BytimeTurreted unsheeting_ingathering = new BytimeTurreted();
			unsheeting_ingathering.suchBlemisher(subdistrict_quarenden);
		}
	}

	public static class BytimeTurreted {
		public void suchBlemisher(Object heteromorphosis_gemlike) {
			SmilodonTrilophodont cyprinodont_violature = new SmilodonTrilophodont();
			cyprinodont_violature
					.nonplushedUnplannedness(heteromorphosis_gemlike);
		}
	}

	public static class SmilodonTrilophodont {
		public void nonplushedUnplannedness(Object erythrolysis_derringer) {
			MyeloplastMiraclemonger intaker_atabrine = new MyeloplastMiraclemonger();
			intaker_atabrine.procurateChlorophane(erythrolysis_derringer);
		}
	}

	public static class MyeloplastMiraclemonger {
		public void procurateChlorophane(Object heumite_phasianinae) {
			IntertrafficPlastering rainy_choreograph = new IntertrafficPlastering();
			rainy_choreograph.benzothiopheneShabbyish(heumite_phasianinae);
		}
	}

	public static class IntertrafficPlastering {
		public void benzothiopheneShabbyish(Object steeving_tepidness) {
			SubauditionHallmark kirkman_smokelessly = new SubauditionHallmark();
			kirkman_smokelessly.syndicalistPatelloid(steeving_tepidness);
		}
	}

	public static class SubauditionHallmark {
		public void syndicalistPatelloid(Object dullity_maltose) {
			AllomorphismEremic pyopericardium_agglutinative = new AllomorphismEremic();
			pyopericardium_agglutinative
					.saltatorialPeriproctous(dullity_maltose);
		}
	}

	public static class AllomorphismEremic {
		public void saltatorialPeriproctous(Object humorful_hydranth) {
			PhymatorhysinTrialism tucktoo_octine = new PhymatorhysinTrialism();
			tucktoo_octine.catacumbalHypermetropical(humorful_hydranth);
		}
	}

	public static class PhymatorhysinTrialism {
		public void catacumbalHypermetropical(Object abthanage_hintedly) {
			SuperfluentApodeme harnesser_teratogenesis = new SuperfluentApodeme();
			harnesser_teratogenesis
					.archreactionaryUnresponsive(abthanage_hintedly);
		}
	}

	public static class SuperfluentApodeme {
		public void archreactionaryUnresponsive(Object presuperfluity_unjapanned) {
			UnderproportionCocked kikawaeo_syruped = new UnderproportionCocked();
			kikawaeo_syruped.autotriploidTuckshop(presuperfluity_unjapanned);
		}
	}

	public static class UnderproportionCocked {
		public void autotriploidTuckshop(Object isothermous_necropolis) {
			CaproinUnconsumable multure_nestorine = new CaproinUnconsumable();
			multure_nestorine.minceDeckhouse(isothermous_necropolis);
		}
	}

	public static class CaproinUnconsumable {
		public void minceDeckhouse(Object motorization_pulpousness) {
			QuincunciallySplenotomy anticipatively_pharyngectomy = new QuincunciallySplenotomy();
			anticipatively_pharyngectomy
					.befilthRepenetrate(motorization_pulpousness);
		}
	}

	public static class QuincunciallySplenotomy {
		public void befilthRepenetrate(Object fevergum_mercurial) {
			BurlinessUnmendableness nonbetrayal_quicksilverish = new BurlinessUnmendableness();
			nonbetrayal_quicksilverish.tropoylFluky(fevergum_mercurial);
		}
	}

	public static class BurlinessUnmendableness {
		public void tropoylFluky(Object tauricornous_commove) {
			XanthoproteicDebamboozle iceleaf_exemption = new XanthoproteicDebamboozle();
			iceleaf_exemption.cowroidSemiconductor(tauricornous_commove);
		}
	}

	public static class XanthoproteicDebamboozle {
		public void cowroidSemiconductor(Object renascence_baleen) {
			GratulationEndodermis oxymuriatic_sain = new GratulationEndodermis();
			oxymuriatic_sain.arbitragistNephridium(renascence_baleen);
		}
	}

	public static class GratulationEndodermis {
		public void arbitragistNephridium(Object unberth_ligation) {
			ScripturalismAntireticular misarchist_essoiner = new ScripturalismAntireticular();
			misarchist_essoiner.xiphosuraZunyite(unberth_ligation);
		}
	}

	public static class ScripturalismAntireticular {
		public void xiphosuraZunyite(Object acology_extruding) {
			ColosseumTalc glam_cystidean = new ColosseumTalc();
			glam_cystidean.tingiblePrimateship(acology_extruding);
		}
	}

	public static class ColosseumTalc {
		public void tingiblePrimateship(Object antirumor_unimbibed) {
			LumberyardFirca pawnee_hypenantron = new LumberyardFirca();
			pawnee_hypenantron.unsophisticallyGoodyear(antirumor_unimbibed);
		}
	}

	public static class LumberyardFirca {
		public void unsophisticallyGoodyear(Object rackettail_unexampledness) {
			GrillworkExpostulative ivory_sextipolar = new GrillworkExpostulative();
			ivory_sextipolar.chalinineBejant(rackettail_unexampledness);
		}
	}

	public static class GrillworkExpostulative {
		public void chalinineBejant(Object clinger_reviser) {
			ZepRamate overinterest_hyaenidae = new ZepRamate();
			overinterest_hyaenidae.aeolismBaal(clinger_reviser);
		}
	}

	public static class ZepRamate {
		public void aeolismBaal(Object phalaenopsis_bee) {
			PrecontinentalEphydriad bracteal_matchcloth = new PrecontinentalEphydriad();
			bracteal_matchcloth.osteoblastomaUndoctor(phalaenopsis_bee);
		}
	}

	public static class PrecontinentalEphydriad {
		public void osteoblastomaUndoctor(Object patrilinear_encyclopediast) {
			ChlorideMongolization flooring_paedonymy = new ChlorideMongolization();
			flooring_paedonymy.greenthPretabulate(patrilinear_encyclopediast);
		}
	}

	public static class ChlorideMongolization {
		public void greenthPretabulate(Object rereeve_life) {
			FoamlikeShippon ressala_microangstrom = new FoamlikeShippon();
			ressala_microangstrom.unshapedNeoplasty(rereeve_life);
		}
	}

	public static class FoamlikeShippon {
		public void unshapedNeoplasty(Object apyonin_kapp) {
			BankruptlyPlanetabler dihydrol_hammersmith = new BankruptlyPlanetabler();
			dihydrol_hammersmith.notchboardStyrol(apyonin_kapp);
		}
	}

	public static class BankruptlyPlanetabler {
		public void notchboardStyrol(Object phasmatida_opiomania) {
			ChrysocracyAltisonant subrisive_amytal = new ChrysocracyAltisonant();
			subrisive_amytal.aggrandizementOldhamite(phasmatida_opiomania);
		}
	}

	public static class ChrysocracyAltisonant {
		public void aggrandizementOldhamite(Object tripeman_alpax) {
			StomperSarmenta membraniform_sinuate = new StomperSarmenta();
			membraniform_sinuate.praedialistMisbehave(tripeman_alpax);
		}
	}

	public static class StomperSarmenta {
		public void praedialistMisbehave(Object cognizableness_unmultiplied) {
			PitmirkCeratoblast lobopodium_retroreception = new PitmirkCeratoblast();
			lobopodium_retroreception
					.gyratoryFather(cognizableness_unmultiplied);
		}
	}

	public static class PitmirkCeratoblast {
		public void gyratoryFather(Object blubberer_unkenneled) {
			ItchingUndertakable antitype_distinctionless = new ItchingUndertakable();
			antitype_distinctionless
					.geographicalOsteoglossidae(blubberer_unkenneled);
		}
	}

	public static class ItchingUndertakable {
		public void geographicalOsteoglossidae(Object cannot_gameless) {
			FogmanParallelograph archaeologic_ennomic = new FogmanParallelograph();
			archaeologic_ennomic.endolaryngealUltrared(cannot_gameless);
		}
	}

	public static class FogmanParallelograph {
		public void endolaryngealUltrared(Object satyrinae_teleoceras) {
			CornaceaeGeerah superastonish_adipous = new CornaceaeGeerah();
			superastonish_adipous.aleurometerHaptene(satyrinae_teleoceras);
		}
	}

	public static class CornaceaeGeerah {
		public void aleurometerHaptene(Object preinvent_byordinar) {
			HematothoraxInexpedience phenate_tyromancy = new HematothoraxInexpedience();
			phenate_tyromancy.pathetistAmaryllideous(preinvent_byordinar);
		}
	}

	public static class HematothoraxInexpedience {
		public void pathetistAmaryllideous(Object digitated_lade) {
			MisperceptionLymphous pontal_basoko = new MisperceptionLymphous();
			pontal_basoko.manualismGorillian(digitated_lade);
		}
	}

	public static class MisperceptionLymphous {
		public void manualismGorillian(Object boardable_matrilinearism) {
			DeadishCastorized quibbleproof_ricketily = new DeadishCastorized();
			quibbleproof_ricketily.uterineSubcaudal(boardable_matrilinearism);
		}
	}

	public static class DeadishCastorized {
		public void uterineSubcaudal(Object charthouse_biocentric) {
			SubradularHistogen dandyish_dugdug = new SubradularHistogen();
			dandyish_dugdug
					.ultramontanistPostsuppurative(charthouse_biocentric);
		}
	}

	public static class SubradularHistogen {
		public void ultramontanistPostsuppurative(Object tribunitive_study) {
			FusibleLeucocarpous umpirism_kluxer = new FusibleLeucocarpous();
			umpirism_kluxer.rancorConcamerated(tribunitive_study);
		}
	}

	public static class FusibleLeucocarpous {
		public void rancorConcamerated(Object redeflect_riesling) {
			ReplaitOppositively irenicist_drinkproof = new ReplaitOppositively();
			irenicist_drinkproof.metacentricityActuarial(redeflect_riesling);
		}
	}

	public static class ReplaitOppositively {
		public void metacentricityActuarial(Object unfallenness_influencive) {
			WaterproofingCollock timework_pierce = new WaterproofingCollock();
			timework_pierce.scotsCurine(unfallenness_influencive);
		}
	}

	public static class WaterproofingCollock {
		public void scotsCurine(Object symphysiotomy_carnaged) {
			DemisecondSinologist pranceful_anerythroplasia = new DemisecondSinologist();
			pranceful_anerythroplasia.copiablePhilliloo(symphysiotomy_carnaged);
		}
	}

	public static class DemisecondSinologist {
		public void copiablePhilliloo(Object spanishize_unscarfed) {
			PersimmonFrameableness diedral_debrief = new PersimmonFrameableness();
			diedral_debrief.antediluvialDisconanthous(spanishize_unscarfed);
		}
	}

	public static class PersimmonFrameableness {
		public void antediluvialDisconanthous(Object redraw_metosteal) {
			HomalosterniiTaximeter bosthoon_monocephalous = new HomalosterniiTaximeter();
			bosthoon_monocephalous.nephralgicOsteoglossidae(redraw_metosteal);
		}
	}

	public static class HomalosterniiTaximeter {
		public void nephralgicOsteoglossidae(Object doit_dovyalis) {
			Tracer.tracepointWeaknessStart("CWE036", "A",
					"Absolute Path Traversal");
			java.io.BufferedReader reader = null;
			String valueString = ((String) doit_dovyalis).trim();
			Tracer.tracepointVariableString("value", ((String) doit_dovyalis));
			Tracer.tracepointVariableString("valueString", valueString);
			if (valueString.length() != 0) {
				Tracer.tracepointMessage("CROSSOVER-PONT: BEFORE");
				if (valueString.startsWith("/")) {
					LenyaSourceFactory.mamiliusGeomorphy
							.println("Error: Not allowed to use absolute path.");
					Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
				} else {
					Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
					boolean decodedSuccessfully = false;
					try {
						valueString = java.net.URLDecoder.decode(valueString,
								"UTF-8");
						Tracer.tracepointVariableString("valueString",
								valueString);
						decodedSuccessfully = true;
					} catch (java.io.UnsupportedEncodingException encoding_exc) {
						Tracer.tracepointError(encoding_exc.getClass()
								.getName() + ": " + encoding_exc.getMessage());
						LenyaSourceFactory.mamiliusGeomorphy
								.println("STONESOUP: Unsupported character encoding exception");
						encoding_exc
								.printStackTrace(LenyaSourceFactory.mamiliusGeomorphy);
					}
					if (decodedSuccessfully) {
						java.io.File readPath = new java.io.File(valueString);
						if (readPath.isFile()) {
							try {
								java.io.FileInputStream fis = new java.io.FileInputStream(
										readPath);
								reader = new java.io.BufferedReader(
										new java.io.InputStreamReader(fis));
								String line = null;
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								while ((line = reader.readLine()) != null) {
									LenyaSourceFactory.mamiliusGeomorphy
											.println(line);
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							} catch (java.io.FileNotFoundException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								LenyaSourceFactory.mamiliusGeomorphy.printf(
										"File \"%s\" does not exist\n",
										readPath.getPath());
							} catch (java.io.IOException ioe) {
								Tracer.tracepointError(ioe.getClass().getName()
										+ ": " + ioe.getMessage());
								LenyaSourceFactory.mamiliusGeomorphy
										.println("Failed to read file.");
							} finally {
								try {
									if (reader != null) {
										reader.close();
									}
								} catch (java.io.IOException e) {
									LenyaSourceFactory.mamiliusGeomorphy
											.println("STONESOUP: Closing file quietly.");
								}
							}
						} else {
							Tracer.tracepointMessage("File does not exist");
							LenyaSourceFactory.mamiliusGeomorphy.printf(
									"File \"%s\" does not exist\n",
									readPath.getPath());
						}
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}
}