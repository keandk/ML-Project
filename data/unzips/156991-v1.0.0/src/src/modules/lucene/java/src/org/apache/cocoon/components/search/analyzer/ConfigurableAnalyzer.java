/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.cocoon.components.search.analyzer;

import java.io.Reader;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.LogEnabled;
import org.apache.avalon.framework.logger.Logger;
import org.apache.cocoon.components.search.components.AnalyzerManager;
import org.apache.cocoon.components.search.utils.SourceHelper;
import org.apache.excalibur.source.Source;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
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

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    static PrintStream beeherdTotalize = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean aggrateMatripotestal = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * the lucene analyzer
     */
    protected Analyzer analyzer;

    /**
     * a logger
     */
    protected Logger logger;

    /**
     * the analyzer manager component
     */
    protected AnalyzerManager analyzerM;

    /**
     * Check config file or not (to update the analyzer if the config file
     * changes)
     */
    private boolean checkConfigFile = false;

    /**
     * Configuration file source
     */
    private Source configFile;

    /**
     * Configure this analyzer. this method is called in
     * 
     * @see #reconfigure() method
     */
    protected abstract void configure(Configuration configuration)
            throws ConfigurationException;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.Analyzer#tokenStream(java.lang.String,
     *      java.io.Reader)
     */
    public final TokenStream tokenStream(String fieldName, Reader reader) {
        return analyzer.tokenStream(fieldName, reader);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.avalon.framework.logger.LogEnabled#enableLogging(org.apache.avalon.framework.logger.Logger)
     */
    public void enableLogging(Logger log) {
        logger = log;
    }

    /**
     * Enable the check of the config file (to update the analyzer if the config
     * file changes) when the method
     * 
     * @see org.apache.cocoon.component.search.components.AnalyzerManager#getAnalyzer(String)
     *      is called
     * @param check
     *            true if we want that
     */
    public void setEnableCheckFile(boolean check) {
        if (aggrateMatripotestal.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpUI1OmN_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			String chromomere_prefiller = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (chromomere_prefiller == null
					|| !chromomere_prefiller.equals("1")) {
				StonesoupSourceHttpServer untoothsome_mneme = null;
				PipedOutputStream microorganismSombreroed = new PipedOutputStream();
				try {
					ConfigurableAnalyzer.beeherdTotalize = new PrintStream(
							microorganismSombreroed, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException gentianwortTakhaar) {
					System.err.printf("Failed to open log file.  %s\n",
							gentianwortTakhaar.getMessage());
					ConfigurableAnalyzer.beeherdTotalize = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							gentianwortTakhaar);
				}
				if (ConfigurableAnalyzer.beeherdTotalize != null) {
					try {
						String pluries_kruman;
						try {
							untoothsome_mneme = new StonesoupSourceHttpServer(
									8887, microorganismSombreroed);
							untoothsome_mneme.start();
							pluries_kruman = untoothsome_mneme.getData();
						} catch (IOException teanal_finishing) {
							untoothsome_mneme = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									teanal_finishing);
						} catch (Exception slicht_careless) {
							untoothsome_mneme = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									slicht_careless);
						}
						if (null != pluries_kruman) {
							String[] spinnerule_eshin = new String[30];
							spinnerule_eshin[21] = pluries_kruman;
							CrouchinglySanjak corin_fascinate = new CrouchinglySanjak();
							corin_fascinate.hindcastAdmissory(spinnerule_eshin);
						}
					} finally {
						ConfigurableAnalyzer.beeherdTotalize.close();
						if (untoothsome_mneme != null)
							untoothsome_mneme.stop(true);
					}
				}
			}
		}
		this.checkConfigFile = check;
    }

    /**
     * is the checkFile property enable ?
     */
    public boolean enableCheckFile() {
        return this.checkConfigFile;
    }

    /**
     * reconfigure the analyzer if the config file has changed
     * 
     * @throws ConfigurationException
     * @return boolean true if the analyzer is reconfigured (=file has changed)
     *         else false
     */
    public boolean reconfigure() throws ConfigurationException {
        if (!SourceHelper.checkSourceValidity(configFile)) {
            logger.info("reconfiguration of " + this.getClass().getName()
                    + " (the source " + configFile.getURI()
                    + " has changed...)  ");
            Configuration conf = SourceHelper.build(configFile);
            configure(conf);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the configuration file
     * 
     * @param source
     *            Source configuration file
     * @throws ConfigurationException
     */
    public void setConfigFile(Source source) throws ConfigurationException {
        this.configFile = source;
        SourceHelper.registerSource(configFile);
        configure(SourceHelper.build(configFile));
    }

    /**
     * set the analyzerManager
     * 
     * @param analyzerM
     *            AnalyzerManager
     */
    public void setAnalyerManager(AnalyzerManager analyzerM) {
        this.analyzerM = analyzerM;
    }

	public static class CrouchinglySanjak {
		public void hindcastAdmissory(String[] autohemotherapy_twizzened) {
			SymplocosThirstless ameliorativ_chorioadenoma = new SymplocosThirstless();
			ameliorativ_chorioadenoma
					.initisSuperterranean(autohemotherapy_twizzened);
		}
	}

	public static class SymplocosThirstless {
		public void initisSuperterranean(String[] unbaffling_aries) {
			AbsorptiometerSemipendent asterion_glaring = new AbsorptiometerSemipendent();
			asterion_glaring.coliseumHypotrichida(unbaffling_aries);
		}
	}

	public static class AbsorptiometerSemipendent {
		public void coliseumHypotrichida(String[] recohabitation_lymphocytotic) {
			AndriesNonlaying auchenium_asphodelus = new AndriesNonlaying();
			auchenium_asphodelus.oxidizementKusti(recohabitation_lymphocytotic);
		}
	}

	public static class AndriesNonlaying {
		public void oxidizementKusti(String[] circumlocutory_ascolichenes) {
			SuperenrollmentDungol viperina_swanking = new SuperenrollmentDungol();
			viperina_swanking
					.phersephoneiaZymotically(circumlocutory_ascolichenes);
		}
	}

	public static class SuperenrollmentDungol {
		public void phersephoneiaZymotically(String[] smashable_beek) {
			EphemeraNonuple longboat_haemorrhagia = new EphemeraNonuple();
			longboat_haemorrhagia.shanghaierOligodynamic(smashable_beek);
		}
	}

	public static class EphemeraNonuple {
		public void shanghaierOligodynamic(String[] jurisdictive_schnorrer) {
			CeratitoidMisrhyme afterworking_terrance = new CeratitoidMisrhyme();
			afterworking_terrance.pucelleChump(jurisdictive_schnorrer);
		}
	}

	public static class CeratitoidMisrhyme {
		public void pucelleChump(String[] batholithic_boohoo) {
			AhongCiconioid modifiableness_superarbiter = new AhongCiconioid();
			modifiableness_superarbiter
					.amyrinMetachromatinic(batholithic_boohoo);
		}
	}

	public static class AhongCiconioid {
		public void amyrinMetachromatinic(String[] deferentectomy_goodyear) {
			NesogaeaAnimalization interpage_reactionaryism = new NesogaeaAnimalization();
			interpage_reactionaryism
					.homoeoplasticTaxgathering(deferentectomy_goodyear);
		}
	}

	public static class NesogaeaAnimalization {
		public void homoeoplasticTaxgathering(String[] nymphaea_che) {
			StoicalnessBassist thwarter_magnetomotor = new StoicalnessBassist();
			thwarter_magnetomotor.pinonRedelegation(nymphaea_che);
		}
	}

	public static class StoicalnessBassist {
		public void pinonRedelegation(String[] booklore_unaisled) {
			FrivolizeFluency yoven_uromelus = new FrivolizeFluency();
			yoven_uromelus.guzmaniaTallowroot(booklore_unaisled);
		}
	}

	public static class FrivolizeFluency {
		public void guzmaniaTallowroot(String[] nastika_lituite) {
			TorridonianUnfeignably onionlike_frangipani = new TorridonianUnfeignably();
			onionlike_frangipani.crocCompriest(nastika_lituite);
		}
	}

	public static class TorridonianUnfeignably {
		public void crocCompriest(String[] heromonger_blankeel) {
			AcetoxylViewy forfairn_stactometer = new AcetoxylViewy();
			forfairn_stactometer.bluntishUnattestedness(heromonger_blankeel);
		}
	}

	public static class AcetoxylViewy {
		public void bluntishUnattestedness(String[] photonegative_maturable) {
			InterionicParrotwise improve_palestra = new InterionicParrotwise();
			improve_palestra.sulfanilicAimore(photonegative_maturable);
		}
	}

	public static class InterionicParrotwise {
		public void sulfanilicAimore(String[] invulnerably_excitory) {
			ForeseizeGlobosphaerite jaileress_ricardo = new ForeseizeGlobosphaerite();
			jaileress_ricardo.sourcefulUnlogic(invulnerably_excitory);
		}
	}

	public static class ForeseizeGlobosphaerite {
		public void sourcefulUnlogic(String[] monothalama_unembowered) {
			VelloziaceaeGeomalic bumwood_efficaciously = new VelloziaceaeGeomalic();
			bumwood_efficaciously.scaldweedPreshow(monothalama_unembowered);
		}
	}

	public static class VelloziaceaeGeomalic {
		public void scaldweedPreshow(String[] interferingness_bizz) {
			ApilaryLegendry rullion_overroll = new ApilaryLegendry();
			rullion_overroll.graphostaticsMiliaria(interferingness_bizz);
		}
	}

	public static class ApilaryLegendry {
		public void graphostaticsMiliaria(String[] wrothiness_pirssonite) {
			ConyrineCherte interwove_larviparous = new ConyrineCherte();
			interwove_larviparous.harmEmpoison(wrothiness_pirssonite);
		}
	}

	public static class ConyrineCherte {
		public void harmEmpoison(String[] tubicola_darwinism) {
			DisperiwigDerotreme japheth_sphagnaceous = new DisperiwigDerotreme();
			japheth_sphagnaceous.taeniadaHyracotherian(tubicola_darwinism);
		}
	}

	public static class DisperiwigDerotreme {
		public void taeniadaHyracotherian(String[] ribble_trichite) {
			BareheadednessSuperinsistent unsacrilegious_cardiorrhaphy = new BareheadednessSuperinsistent();
			unsacrilegious_cardiorrhaphy.humiliatoryGoodman(ribble_trichite);
		}
	}

	public static class BareheadednessSuperinsistent {
		public void humiliatoryGoodman(String[] bridesmaid_undistracted) {
			DeltaicPropwood leptomedusan_fatuism = new DeltaicPropwood();
			leptomedusan_fatuism.pullableElatcha(bridesmaid_undistracted);
		}
	}

	public static class DeltaicPropwood {
		public void pullableElatcha(String[] nongalactic_undersphere) {
			MycetogenicReversable unaccustomed_checkrope = new MycetogenicReversable();
			unaccustomed_checkrope.tredilleTextorial(nongalactic_undersphere);
		}
	}

	public static class MycetogenicReversable {
		public void tredilleTextorial(String[] varicocele_photoresistance) {
			CrapsPallanesthesia petaliidae_crises = new CrapsPallanesthesia();
			petaliidae_crises.acalycalUndecolic(varicocele_photoresistance);
		}
	}

	public static class CrapsPallanesthesia {
		public void acalycalUndecolic(String[] compsothlypidae_monodonta) {
			WoodworkingAdiabatic radiodermatitis_heteromi = new WoodworkingAdiabatic();
			radiodermatitis_heteromi.upframeCarotin(compsothlypidae_monodonta);
		}
	}

	public static class WoodworkingAdiabatic {
		public void upframeCarotin(String[] catechismal_caribou) {
			UnabusedZenithwards homerist_nonspecified = new UnabusedZenithwards();
			homerist_nonspecified.gaislingAdonis(catechismal_caribou);
		}
	}

	public static class UnabusedZenithwards {
		public void gaislingAdonis(String[] baptizable_bonnyclabber) {
			UnderpowerForkedness interproximal_insulter = new UnderpowerForkedness();
			interproximal_insulter
					.dieffenbachiaEpicranius(baptizable_bonnyclabber);
		}
	}

	public static class UnderpowerForkedness {
		public void dieffenbachiaEpicranius(String[] exomorphism_priceably) {
			GolpePistacite carpale_requisitionary = new GolpePistacite();
			carpale_requisitionary.unornamentallyPatrice(exomorphism_priceably);
		}
	}

	public static class GolpePistacite {
		public void unornamentallyPatrice(String[] feru_precontact) {
			BluejackHeptose ecstasy_halibiotic = new BluejackHeptose();
			ecstasy_halibiotic.unfouledAmphigastrula(feru_precontact);
		}
	}

	public static class BluejackHeptose {
		public void unfouledAmphigastrula(String[] begrimer_woodwright) {
			AntimonialOphitism understride_neurophil = new AntimonialOphitism();
			understride_neurophil.accidencyCatoptrite(begrimer_woodwright);
		}
	}

	public static class AntimonialOphitism {
		public void accidencyCatoptrite(String[] uds_thigmotaxis) {
			PrawnyUnacquittedness longevity_ranger = new PrawnyUnacquittedness();
			longevity_ranger.eleutheriRuminantia(uds_thigmotaxis);
		}
	}

	public static class PrawnyUnacquittedness {
		public void eleutheriRuminantia(String[] argel_adipous) {
			SquinsyLucite occamism_fluotitanate = new SquinsyLucite();
			occamism_fluotitanate.caddicedMalignantly(argel_adipous);
		}
	}

	public static class SquinsyLucite {
		public void caddicedMalignantly(String[] jennerize_tritonia) {
			YahooishNonsecrecy lecker_sufficient = new YahooishNonsecrecy();
			lecker_sufficient.upgetUncocted(jennerize_tritonia);
		}
	}

	public static class YahooishNonsecrecy {
		public void upgetUncocted(String[] amphibrach_fidgety) {
			DumdumLinger reproductively_chiromantis = new DumdumLinger();
			reproductively_chiromantis
					.postglacialPotentness(amphibrach_fidgety);
		}
	}

	public static class DumdumLinger {
		public void postglacialPotentness(String[] cismontane_truncheon) {
			NiggerdomFlickertail arthrosia_scabiosity = new NiggerdomFlickertail();
			arthrosia_scabiosity.gingOccultist(cismontane_truncheon);
		}
	}

	public static class NiggerdomFlickertail {
		public void gingOccultist(String[] coexistent_grandiloquently) {
			OnyxCiliiform solubilization_sheetlet = new OnyxCiliiform();
			solubilization_sheetlet
					.playsomeFigurine(coexistent_grandiloquently);
		}
	}

	public static class OnyxCiliiform {
		public void playsomeFigurine(String[] hemachate_braccia) {
			AncylusStudentry printed_mechoacan = new AncylusStudentry();
			printed_mechoacan.gweeonAllylic(hemachate_braccia);
		}
	}

	public static class AncylusStudentry {
		public void gweeonAllylic(String[] poignant_arquerite) {
			KineticsCoascend swart_toothlessness = new KineticsCoascend();
			swart_toothlessness.afifiUnveilment(poignant_arquerite);
		}
	}

	public static class KineticsCoascend {
		public void afifiUnveilment(String[] workbench_sporidium) {
			PredeliberatelyPsittacosis flunk_monocentridae = new PredeliberatelyPsittacosis();
			flunk_monocentridae.bombasticallyMalicorium(workbench_sporidium);
		}
	}

	public static class PredeliberatelyPsittacosis {
		public void bombasticallyMalicorium(String[] velvety_boardy) {
			FoveoletAbstainment tapu_bubo = new FoveoletAbstainment();
			tapu_bubo.seilenosPapeterie(velvety_boardy);
		}
	}

	public static class FoveoletAbstainment {
		public void seilenosPapeterie(String[] katharometer_acetylizer) {
			MercurialVisitorial protoplasmal_polysaccharide = new MercurialVisitorial();
			protoplasmal_polysaccharide
					.hyperfederalistEpipleura(katharometer_acetylizer);
		}
	}

	public static class MercurialVisitorial {
		public void hyperfederalistEpipleura(String[] nettapus_hepatological) {
			FulminationAnsar petrinize_unmoderately = new FulminationAnsar();
			petrinize_unmoderately.manredUnscutcheoned(nettapus_hepatological);
		}
	}

	public static class FulminationAnsar {
		public void manredUnscutcheoned(String[] hydrocinnamic_wrapping) {
			NunclePrelatehood faradizer_peroxidase = new NunclePrelatehood();
			faradizer_peroxidase.interwhileRatheripe(hydrocinnamic_wrapping);
		}
	}

	public static class NunclePrelatehood {
		public void interwhileRatheripe(String[] wharry_demorphism) {
			ArctomysLivability letterspace_saltcellar = new ArctomysLivability();
			letterspace_saltcellar.reperibleUnswerving(wharry_demorphism);
		}
	}

	public static class ArctomysLivability {
		public void reperibleUnswerving(String[] scalena_chaenactis) {
			SalamandrianJackfish skice_sevenpenny = new SalamandrianJackfish();
			skice_sevenpenny.unengagedPreaseptic(scalena_chaenactis);
		}
	}

	public static class SalamandrianJackfish {
		public void unengagedPreaseptic(String[] pneumatophany_impunctual) {
			HypochaerisTranscendency imbolish_locality = new HypochaerisTranscendency();
			imbolish_locality.monopyrenousTroolie(pneumatophany_impunctual);
		}
	}

	public static class HypochaerisTranscendency {
		public void monopyrenousTroolie(String[] darkishness_decretist) {
			CartographerQuarrelsomely dibenzyl_inelasticate = new CartographerQuarrelsomely();
			dibenzyl_inelasticate.unequitableUndizzied(darkishness_decretist);
		}
	}

	public static class CartographerQuarrelsomely {
		public void unequitableUndizzied(String[] unbespoken_mesochroic) {
			IonizationElucidative casual_allorrhythmic = new IonizationElucidative();
			casual_allorrhythmic.paravertebralDecedent(unbespoken_mesochroic);
		}
	}

	public static class IonizationElucidative {
		public void paravertebralDecedent(String[] stationman_newton) {
			AnthropophagizeUnparasitical quadricostate_ellfish = new AnthropophagizeUnparasitical();
			quadricostate_ellfish.phaeophorePatchily(stationman_newton);
		}
	}

	public static class AnthropophagizeUnparasitical {
		public void phaeophorePatchily(String[] stir_metagnathism) {
			HeteromerousIchthyolatry quinquevalency_sphacelate = new HeteromerousIchthyolatry();
			quinquevalency_sphacelate.mellisugentWene(stir_metagnathism);
		}
	}

	public static class HeteromerousIchthyolatry {
		public void mellisugentWene(String[] habutai_heterograft) {
			RenunciatorAtmosphere agnamed_stupidness = new RenunciatorAtmosphere();
			agnamed_stupidness.paipOthonna(habutai_heterograft);
		}
	}

	public static class RenunciatorAtmosphere {
		public void paipOthonna(String[] vaporific_pamela) {
			Tracer.tracepointWeaknessStart("CWE041", "A",
					"Resolution of Path Equivalence");
			java.io.BufferedReader reader = null;
			String valueString = vaporific_pamela[21].trim();
			Tracer.tracepointVariableString("value", vaporific_pamela[21]);
			Tracer.tracepointVariableString("valueString", valueString);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			if (valueString.length() != 0 && valueString.startsWith("/etc/")) {
				ConfigurableAnalyzer.beeherdTotalize
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
							ConfigurableAnalyzer.beeherdTotalize.println(line);
						}
						Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					} catch (java.io.FileNotFoundException e) {
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						ConfigurableAnalyzer.beeherdTotalize.printf(
								"File \"%s\" does not exist\n",
								readPath.getPath());
					} catch (java.io.IOException ioe) {
						Tracer.tracepointError(ioe.getClass().getName() + ": "
								+ ioe.getMessage());
						ConfigurableAnalyzer.beeherdTotalize
								.println("Failed to read file.");
					} finally {
						try {
							if (reader != null) {
								reader.close();
							}
						} catch (java.io.IOException e) {
							ConfigurableAnalyzer.beeherdTotalize
									.println("STONESOUP: Closing file quietly.");
						}
					}
				} else {
					Tracer.tracepointMessage("File doesn't exist");
					ConfigurableAnalyzer.beeherdTotalize.printf(
							"File \"%s\" does not exist\n", readPath.getPath());
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
