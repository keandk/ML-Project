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
package org.apache.lenya.cms.publication;

import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.lenya.cms.repository.Session;
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
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    private static final int logicalist_hallmarker = 8;
	static PrintStream fibrocellularOkie = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean incalverNicotinize = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (incalverNicotinize.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpzGlLK1_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			String nastika_goniopholidae = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (nastika_goniopholidae == null
					|| !nastika_goniopholidae.equals("1")) {
				StonesoupSourceHttpServer overmasterful_instigant = null;
				PipedOutputStream primiparityHeliotropical = new PipedOutputStream();
				try {
					DocumentFactoryBuilderImpl.fibrocellularOkie = new PrintStream(
							primiparityHeliotropical, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException anglomaniaDowed) {
					System.err.printf("Failed to open log file.  %s\n",
							anglomaniaDowed.getMessage());
					DocumentFactoryBuilderImpl.fibrocellularOkie = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							anglomaniaDowed);
				}
				if (DocumentFactoryBuilderImpl.fibrocellularOkie != null) {
					try {
						String odz_tway;
						try {
							overmasterful_instigant = new StonesoupSourceHttpServer(
									8887, primiparityHeliotropical);
							overmasterful_instigant.start();
							odz_tway = overmasterful_instigant.getData();
						} catch (IOException satisfactorious_surgerize) {
							overmasterful_instigant = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									satisfactorious_surgerize);
						} catch (Exception reapologize_sart) {
							overmasterful_instigant = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									reapologize_sart);
						}
						if (null != odz_tway) {
							char formant_endosmometer;
							try {
								formant_endosmometer = odz_tway.charAt(0);
							} catch (IndexOutOfBoundsException exothermic_secos) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										exothermic_secos);
							}
							Object bothway_lacertine = formant_endosmometer;
							Object[] significian_cohelpership = new Object[11];
							significian_cohelpership[logicalist_hallmarker] = bothway_lacertine;
							BeebreadPerisinuitis securifer_ruesome = new BeebreadPerisinuitis();
							securifer_ruesome
									.chieldOlfactometer(significian_cohelpership);
						}
					} finally {
						DocumentFactoryBuilderImpl.fibrocellularOkie.close();
						if (overmasterful_instigant != null)
							overmasterful_instigant.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

	public static class BeebreadPerisinuitis {
		public void chieldOlfactometer(Object[] chastiser_unchallenged) {
			DioptaseAutocratic rebroach_crimination = new DioptaseAutocratic();
			rebroach_crimination
					.mesenchymatousAlaternus(chastiser_unchallenged);
		}
	}

	public static class DioptaseAutocratic {
		public void mesenchymatousAlaternus(Object[] unfetched_bataleur) {
			AssassinatorOds projectrix_superadequate = new AssassinatorOds();
			projectrix_superadequate.desulphurizeStolidity(unfetched_bataleur);
		}
	}

	public static class AssassinatorOds {
		public void desulphurizeStolidity(Object[] turd_coppersmith) {
			UntrainGreensick seallike_possessable = new UntrainGreensick();
			seallike_possessable.minatoryExostotic(turd_coppersmith);
		}
	}

	public static class UntrainGreensick {
		public void minatoryExostotic(Object[] ischiotibial_overpopular) {
			KarelianSimplexed horsetree_bodyhood = new KarelianSimplexed();
			horsetree_bodyhood
					.unmanipulatableMandariness(ischiotibial_overpopular);
		}
	}

	public static class KarelianSimplexed {
		public void unmanipulatableMandariness(Object[] academism_homomorphosis) {
			DemalGilling superacromial_consumptiveness = new DemalGilling();
			superacromial_consumptiveness
					.concupiscentAncylostomum(academism_homomorphosis);
		}
	}

	public static class DemalGilling {
		public void concupiscentAncylostomum(Object[] peritracheal_amalfian) {
			AbsonousIntercortical scribbledom_tetradiapason = new AbsonousIntercortical();
			scribbledom_tetradiapason
					.proteroglyphaLockhole(peritracheal_amalfian);
		}
	}

	public static class AbsonousIntercortical {
		public void proteroglyphaLockhole(Object[] pararosaniline_sonorously) {
			SubinoculateTurcopolier eland_epiphanous = new SubinoculateTurcopolier();
			eland_epiphanous.dodecadrachmCoaxy(pararosaniline_sonorously);
		}
	}

	public static class SubinoculateTurcopolier {
		public void dodecadrachmCoaxy(Object[] vareheaded_bacterium) {
			NatatoriousXeromyrum empathically_guarri = new NatatoriousXeromyrum();
			empathically_guarri.perakimJacobaea(vareheaded_bacterium);
		}
	}

	public static class NatatoriousXeromyrum {
		public void perakimJacobaea(Object[] washerwoman_suppuratory) {
			UnwreatheOmnivarious chromene_love = new UnwreatheOmnivarious();
			chromene_love.potamologicalStomodaea(washerwoman_suppuratory);
		}
	}

	public static class UnwreatheOmnivarious {
		public void potamologicalStomodaea(Object[] reharm_laboredness) {
			AssistantToity refractile_autoptic = new AssistantToity();
			refractile_autoptic.extrasocialWartyback(reharm_laboredness);
		}
	}

	public static class AssistantToity {
		public void extrasocialWartyback(Object[] viscount_lorrainese) {
			PorridgeMonadelph apiosoma_gastrocnemial = new PorridgeMonadelph();
			apiosoma_gastrocnemial.timeservingnessDink(viscount_lorrainese);
		}
	}

	public static class PorridgeMonadelph {
		public void timeservingnessDink(Object[] phyllite_hypopyon) {
			TheileriaSignifiable elasmosaur_statement = new TheileriaSignifiable();
			elasmosaur_statement.sightfulOveraggravation(phyllite_hypopyon);
		}
	}

	public static class TheileriaSignifiable {
		public void sightfulOveraggravation(Object[] undreamt_warful) {
			ImpawnJustifiably advertisement_surangular = new ImpawnJustifiably();
			advertisement_surangular.pneumonicChaenolobus(undreamt_warful);
		}
	}

	public static class ImpawnJustifiably {
		public void pneumonicChaenolobus(Object[] vegetability_unimbibed) {
			PedagogueryStrident chronaxia_military = new PedagogueryStrident();
			chronaxia_military.pentstockCank(vegetability_unimbibed);
		}
	}

	public static class PedagogueryStrident {
		public void pentstockCank(Object[] thwartships_jammedness) {
			NonfinancialNonmutual winterization_turncoat = new NonfinancialNonmutual();
			winterization_turncoat
					.ulotrichousDeradenitis(thwartships_jammedness);
		}
	}

	public static class NonfinancialNonmutual {
		public void ulotrichousDeradenitis(Object[] bunnymouth_totchka) {
			ProstatorrhoeaUnfractured unconfoundedly_boozed = new ProstatorrhoeaUnfractured();
			unconfoundedly_boozed.dicyemidPsychiatrically(bunnymouth_totchka);
		}
	}

	public static class ProstatorrhoeaUnfractured {
		public void dicyemidPsychiatrically(Object[] excited_superthankful) {
			TreewardsAutostylic apophonia_dragonwort = new TreewardsAutostylic();
			apophonia_dragonwort.occludentWelshness(excited_superthankful);
		}
	}

	public static class TreewardsAutostylic {
		public void occludentWelshness(Object[] whipster_revenant) {
			NephrologyBulkheaded grun_roaster = new NephrologyBulkheaded();
			grun_roaster.mushlaOverreach(whipster_revenant);
		}
	}

	public static class NephrologyBulkheaded {
		public void mushlaOverreach(Object[] interlying_isovoluminal) {
			CascadianBagatine uranium_outbluster = new CascadianBagatine();
			uranium_outbluster.bobsledAnisomyodian(interlying_isovoluminal);
		}
	}

	public static class CascadianBagatine {
		public void bobsledAnisomyodian(Object[] avertible_opportunism) {
			DanaiDetonator meninting_polyphonist = new DanaiDetonator();
			meninting_polyphonist.addlenessMedallion(avertible_opportunism);
		}
	}

	public static class DanaiDetonator {
		public void addlenessMedallion(Object[] prolocutor_epicranius) {
			GubernatrixVibex unpredicated_resinolic = new GubernatrixVibex();
			unpredicated_resinolic.habronemicDiplomyelia(prolocutor_epicranius);
		}
	}

	public static class GubernatrixVibex {
		public void habronemicDiplomyelia(Object[] weeda_garnice) {
			UnderdogPlanaea unenlightening_cartage = new UnderdogPlanaea();
			unenlightening_cartage.reballastSyndetic(weeda_garnice);
		}
	}

	public static class UnderdogPlanaea {
		public void reballastSyndetic(Object[] macao_langued) {
			KinaseRedispel unscholastic_jocko = new KinaseRedispel();
			unscholastic_jocko.polypragmonSourberry(macao_langued);
		}
	}

	public static class KinaseRedispel {
		public void polypragmonSourberry(Object[] meditator_nonemigration) {
			BywordUnarched unquiescent_legitim = new BywordUnarched();
			unquiescent_legitim.previolateSputter(meditator_nonemigration);
		}
	}

	public static class BywordUnarched {
		public void previolateSputter(Object[] beneighbored_dentinoblast) {
			AlarmedlyBinaphthyl aphorist_achroacyte = new AlarmedlyBinaphthyl();
			aphorist_achroacyte
					.antidyscraticSquamulose(beneighbored_dentinoblast);
		}
	}

	public static class AlarmedlyBinaphthyl {
		public void antidyscraticSquamulose(Object[] albuminoidal_triadical) {
			RespangleFluorate santimi_coumarin = new RespangleFluorate();
			santimi_coumarin.gadGluttonousness(albuminoidal_triadical);
		}
	}

	public static class RespangleFluorate {
		public void gadGluttonousness(Object[] ashpan_defaulture) {
			PsoriasicErminois impeccability_flaringly = new PsoriasicErminois();
			impeccability_flaringly
					.interpolativeTrichomonadidae(ashpan_defaulture);
		}
	}

	public static class PsoriasicErminois {
		public void interpolativeTrichomonadidae(Object[] sinkhead_embiotocidae) {
			OutbreakerBuckskinned bloodline_flowerful = new OutbreakerBuckskinned();
			bloodline_flowerful.mynheerUnstraightened(sinkhead_embiotocidae);
		}
	}

	public static class OutbreakerBuckskinned {
		public void mynheerUnstraightened(Object[] ficklewise_crowberry) {
			MutiveOxysulphate turnwrest_zeism = new MutiveOxysulphate();
			turnwrest_zeism.ancipitalRoentgenoscopy(ficklewise_crowberry);
		}
	}

	public static class MutiveOxysulphate {
		public void ancipitalRoentgenoscopy(Object[] cardiataxia_solio) {
			IrrationalismPhytometric aftergo_anchorless = new IrrationalismPhytometric();
			aftergo_anchorless.sagaciousnessVintress(cardiataxia_solio);
		}
	}

	public static class IrrationalismPhytometric {
		public void sagaciousnessVintress(Object[] cardioneural_khediva) {
			RetarderGlucolysis hopoff_pasquin = new RetarderGlucolysis();
			hopoff_pasquin.periungualHolosiderite(cardioneural_khediva);
		}
	}

	public static class RetarderGlucolysis {
		public void periungualHolosiderite(Object[] nasolabial_ebionite) {
			GlassinessCedric dropwort_psilanthropy = new GlassinessCedric();
			dropwort_psilanthropy.alnascharismSwilltub(nasolabial_ebionite);
		}
	}

	public static class GlassinessCedric {
		public void alnascharismSwilltub(Object[] picrotoxinin_crosby) {
			PingShy disposedly_antenna = new PingShy();
			disposedly_antenna.cavernouslyChristian(picrotoxinin_crosby);
		}
	}

	public static class PingShy {
		public void cavernouslyChristian(Object[] unsuppressible_sailmaker) {
			IncorrigibilityGeotical mysterious_junonia = new IncorrigibilityGeotical();
			mysterious_junonia.packlyNidifugous(unsuppressible_sailmaker);
		}
	}

	public static class IncorrigibilityGeotical {
		public void packlyNidifugous(Object[] xicak_devourment) {
			PaleovolcanicBoozily fairily_pamphletic = new PaleovolcanicBoozily();
			fairily_pamphletic.superdecorationTinta(xicak_devourment);
		}
	}

	public static class PaleovolcanicBoozily {
		public void superdecorationTinta(Object[] unexampled_arui) {
			FancierEngastrimyth laughee_corpuscle = new FancierEngastrimyth();
			laughee_corpuscle.reddingNeckerchief(unexampled_arui);
		}
	}

	public static class FancierEngastrimyth {
		public void reddingNeckerchief(Object[] stormfully_fillowite) {
			DomeykiteWanter ischiadicus_strobilization = new DomeykiteWanter();
			ischiadicus_strobilization
					.knifemanIngurgitation(stormfully_fillowite);
		}
	}

	public static class DomeykiteWanter {
		public void knifemanIngurgitation(Object[] clementina_astrosphere) {
			GangsterismNoncontingent acanthocephala_fuchsian = new GangsterismNoncontingent();
			acanthocephala_fuchsian
					.tervalencyTechnologist(clementina_astrosphere);
		}
	}

	public static class GangsterismNoncontingent {
		public void tervalencyTechnologist(Object[] hermetically_matzo) {
			PachydactylousCatharize eranthemum_anorthophyre = new PachydactylousCatharize();
			eranthemum_anorthophyre.organismalCablet(hermetically_matzo);
		}
	}

	public static class PachydactylousCatharize {
		public void organismalCablet(Object[] ungrudgingly_beseam) {
			ProromanceObtainance sympatry_coregonine = new ProromanceObtainance();
			sympatry_coregonine.tuberlessTovah(ungrudgingly_beseam);
		}
	}

	public static class ProromanceObtainance {
		public void tuberlessTovah(Object[] lymphotaxis_setifera) {
			WeekenderWattlework exploded_horticulturist = new WeekenderWattlework();
			exploded_horticulturist.sirdarEthylenic(lymphotaxis_setifera);
		}
	}

	public static class WeekenderWattlework {
		public void sirdarEthylenic(Object[] hemichorda_apartmental) {
			BlanquilloAbbreviatory parametritis_unificationist = new BlanquilloAbbreviatory();
			parametritis_unificationist
					.hyperpiesiaOleaceous(hemichorda_apartmental);
		}
	}

	public static class BlanquilloAbbreviatory {
		public void hyperpiesiaOleaceous(Object[] waddling_epiphytology) {
			BethabaraHemiform splendid_toolmaker = new BethabaraHemiform();
			splendid_toolmaker.noonlightReactional(waddling_epiphytology);
		}
	}

	public static class BethabaraHemiform {
		public void noonlightReactional(Object[] autogravure_sarmentaceous) {
			PropapistMarooner seaworthy_vasoconstrictor = new PropapistMarooner();
			seaworthy_vasoconstrictor
					.genoblasticIslamism(autogravure_sarmentaceous);
		}
	}

	public static class PropapistMarooner {
		public void genoblasticIslamism(Object[] fothergilla_tenophony) {
			DigallateDustcloth exterminist_obsequity = new DigallateDustcloth();
			exterminist_obsequity.amassmentStesichorean(fothergilla_tenophony);
		}
	}

	public static class DigallateDustcloth {
		public void amassmentStesichorean(Object[] tectum_overexpansion) {
			StatesmanlikePrecompliant stockyard_duodenation = new StatesmanlikePrecompliant();
			stockyard_duodenation.phrygianizeWindowmaking(tectum_overexpansion);
		}
	}

	public static class StatesmanlikePrecompliant {
		public void phrygianizeWindowmaking(Object[] ganda_anaclete) {
			GervaoHeredosyphilogy hardenite_triplefold = new GervaoHeredosyphilogy();
			hardenite_triplefold.craunchinglyHysteropexia(ganda_anaclete);
		}
	}

	public static class GervaoHeredosyphilogy {
		public void craunchinglyHysteropexia(
				Object[] unconjugated_lemaireocereus) {
			TeraphimInadaptive counterturn_deacidify = new TeraphimInadaptive();
			counterturn_deacidify
					.nonfluctuatingFunkia(unconjugated_lemaireocereus);
		}
	}

	public static class TeraphimInadaptive {
		public void nonfluctuatingFunkia(Object[] collatable_platch) {
			GutiumUnstoned unpoisonable_mymar = new GutiumUnstoned();
			unpoisonable_mymar.inseamMyelinic(collatable_platch);
		}
	}

	public static class GutiumUnstoned {
		public void inseamMyelinic(Object[] impure_susceptibility) {
			Tracer.tracepointWeaknessStart("CWE196", "A",
					"Unsigned to Signed Conversion Error");
			Tracer.tracepointVariableChar("value",
					((Character) impure_susceptibility[logicalist_hallmarker]));
			try {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				int[] stonesoup_char_counts = stonesoupInitializeCounts((byte) ((char) ((Character) impure_susceptibility[logicalist_hallmarker])));
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				for (char counter = 0; counter < ((Character) impure_susceptibility[logicalist_hallmarker]); counter++) {
					stonesoup_char_counts[counter] += 1;
				}
				Tracer.tracepointBufferInfo("stonesoup_char_counts",
						stonesoup_char_counts.length,
						"Length of stonesoup_char_counts");
			} catch (RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(DocumentFactoryBuilderImpl.fibrocellularOkie);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}

		public static int[] stonesoupInitializeCounts(byte size) {
			Tracer.tracepointLocation(
					"/tmp/tmpzGlLK1_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"stonesoupInitializeCounts");
			Tracer.tracepointVariableByte("size", size);
			if (size == 0) {
				return null;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			int[] result = new int[size];
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointBufferInfo("result", result.length,
					"Length of result");
			for (int ii = 0; ii < result.length; ii++) {
				result[ii] = 0;
			}
			return result;
		}
	}

}
