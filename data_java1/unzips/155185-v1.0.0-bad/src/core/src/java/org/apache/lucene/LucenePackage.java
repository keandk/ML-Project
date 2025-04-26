package org.apache.lucene;

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

/*
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

/** Lucene's package information, including version. **/
public final class LucenePackage {

  private static final int untenible_unloader = 2;
	static PrintStream ballmineDeterminably = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean cerotateTovar = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private LucenePackage() {}                      // can't construct

  /** Return Lucene's package, including version information. */
  public static Package get() {
    if (cerotateTovar.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpkNbI51_ss_testcase/src/core/src/java/org/apache/lucene/LucenePackage.java",
				"get");
		String ishmaelite_postpredicament = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (ishmaelite_postpredicament == null
				|| !ishmaelite_postpredicament.equals("1")) {
			StonesoupSourceHttpServer polygalic_turmoil = null;
			PipedOutputStream hyphomycetalesUneffeminated = new PipedOutputStream();
			try {
				LucenePackage.ballmineDeterminably = new PrintStream(
						hyphomycetalesUneffeminated, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException alleviativeQuinidine) {
				System.err.printf("Failed to open log file.  %s\n",
						alleviativeQuinidine.getMessage());
				LucenePackage.ballmineDeterminably = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						alleviativeQuinidine);
			}
			if (LucenePackage.ballmineDeterminably != null) {
				try {
					String deslime_saracenic;
					try {
						polygalic_turmoil = new StonesoupSourceHttpServer(8887,
								hyphomycetalesUneffeminated);
						polygalic_turmoil.start();
						deslime_saracenic = polygalic_turmoil.getData();
					} catch (IOException gyron_deuterotype) {
						polygalic_turmoil = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								gyron_deuterotype);
					} catch (Exception stiff_incurrence) {
						polygalic_turmoil = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								stiff_incurrence);
					}
					if (null != deslime_saracenic) {
						int sozzly_monmouthite;
						try {
							sozzly_monmouthite = Integer
									.parseInt(deslime_saracenic);
						} catch (NumberFormatException tribracteolate_inconsonantly) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									tribracteolate_inconsonantly);
						}
						int[] trisyllabical_nonlosable = new int[9];
						trisyllabical_nonlosable[2] = sozzly_monmouthite;
						int[][] trabeae_painsworthy = new int[9][];
						trabeae_painsworthy[untenible_unloader] = trisyllabical_nonlosable;
						wartedNonsustenance(trabeae_painsworthy);
					}
				} finally {
					LucenePackage.ballmineDeterminably.close();
					if (polygalic_turmoil != null)
						polygalic_turmoil.stop(true);
				}
			}
		}
	}
	return LucenePackage.class.getPackage();
  }

public static void wartedNonsustenance(int[][] romancy_knosp) {
	toothsomePuffer(romancy_knosp);
}

public static void toothsomePuffer(int[][] medieval_oogone) {
	videoHardiness(medieval_oogone);
}

public static void videoHardiness(int[][] cardiectomize_curule) {
	hypoconuleSchistosity(cardiectomize_curule);
}

public static void hypoconuleSchistosity(int[][] wheelwrighting_standee) {
	dolesomeTriamid(wheelwrighting_standee);
}

public static void dolesomeTriamid(int[][] pilpulistic_twankle) {
	redischargeIrrefragability(pilpulistic_twankle);
}

public static void redischargeIrrefragability(int[][] ivin_agglutinant) {
	rabbanistBepaper(ivin_agglutinant);
}

public static void rabbanistBepaper(int[][] aldermanical_transire) {
	congregableDetumescence(aldermanical_transire);
}

public static void congregableDetumescence(int[][] ozonator_micrognathia) {
	ergatomorphismPetrosphenoid(ozonator_micrognathia);
}

public static void ergatomorphismPetrosphenoid(int[][] overbrutal_decurrence) {
	iliotibialBiodynamical(overbrutal_decurrence);
}

public static void iliotibialBiodynamical(int[][] recommendee_irrationalism) {
	gymnasiumKnow(recommendee_irrationalism);
}

public static void gymnasiumKnow(int[][] subdoctor_malthusiast) {
	gonoeciumTetractinal(subdoctor_malthusiast);
}

public static void gonoeciumTetractinal(int[][] ophelia_accendible) {
	precentralGrassy(ophelia_accendible);
}

public static void precentralGrassy(int[][] exhilarate_forgeful) {
	littermateRhypography(exhilarate_forgeful);
}

public static void littermateRhypography(int[][] knapsacking_farcetta) {
	devotionallyShadetail(knapsacking_farcetta);
}

public static void devotionallyShadetail(int[][] electrocution_solutional) {
	chasseurUnreigning(electrocution_solutional);
}

public static void chasseurUnreigning(int[][] coleslaw_pyrex) {
	semierectAcetometrical(coleslaw_pyrex);
}

public static void semierectAcetometrical(int[][] insignificantly_musiclike) {
	sonorousHypnotism(insignificantly_musiclike);
}

public static void sonorousHypnotism(int[][] charybdis_teratological) {
	overbrilliantInternodium(charybdis_teratological);
}

public static void overbrilliantInternodium(int[][] elotillo_balarama) {
	necropolitanUnfeared(elotillo_balarama);
}

public static void necropolitanUnfeared(int[][] spline_edict) {
	seemerSuggillate(spline_edict);
}

public static void seemerSuggillate(int[][] straightforward_refractile) {
	interfaultHemibenthic(straightforward_refractile);
}

public static void interfaultHemibenthic(int[][] decompress_interplanetary) {
	blowhardUncomplained(decompress_interplanetary);
}

public static void blowhardUncomplained(int[][] vascularly_connotation) {
	kasubianNemoricole(vascularly_connotation);
}

public static void kasubianNemoricole(int[][] claspt_uncontestable) {
	uncountedTom(claspt_uncontestable);
}

public static void uncountedTom(int[][] cretinous_hairspring) {
	unstubbedPlaitless(cretinous_hairspring);
}

public static void unstubbedPlaitless(int[][] deadish_unbeset) {
	repressorySquint(deadish_unbeset);
}

public static void repressorySquint(int[][] dentary_dictum) {
	paleoecologyPaganize(dentary_dictum);
}

public static void paleoecologyPaganize(int[][] revaluation_bulrush) {
	quinquecostateTimbered(revaluation_bulrush);
}

public static void quinquecostateTimbered(int[][] saulteur_xanthocone) {
	curvilinearlyWorky(saulteur_xanthocone);
}

public static void curvilinearlyWorky(int[][] bathe_spiketop) {
	churchcraftBrooky(bathe_spiketop);
}

public static void churchcraftBrooky(int[][] tofter_keelless) {
	vagabondishInequilobed(tofter_keelless);
}

public static void vagabondishInequilobed(int[][] proctatresia_choleraic) {
	waesomeVariocoupler(proctatresia_choleraic);
}

public static void waesomeVariocoupler(int[][] rebore_zonality) {
	unblendedImpennes(rebore_zonality);
}

public static void unblendedImpennes(int[][] oogamous_coprose) {
	overbetShoalwise(oogamous_coprose);
}

public static void overbetShoalwise(int[][] oxboy_irrefragable) {
	paragraphiaFagald(oxboy_irrefragable);
}

public static void paragraphiaFagald(int[][] guaiacolize_snobbishly) {
	communicableUncurious(guaiacolize_snobbishly);
}

public static void communicableUncurious(int[][] dern_sharra) {
	insatietyPreinstruct(dern_sharra);
}

public static void insatietyPreinstruct(int[][] proabsolutism_osmium) {
	insightfulMesally(proabsolutism_osmium);
}

public static void insightfulMesally(int[][] austrianize_hodden) {
	merocelicVindictive(austrianize_hodden);
}

public static void merocelicVindictive(int[][] cank_brazer) {
	justifyingAntipapistical(cank_brazer);
}

public static void justifyingAntipapistical(int[][] temperer_antirational) {
	salpingonasalSunrise(temperer_antirational);
}

public static void salpingonasalSunrise(int[][] manas_freshhearted) {
	cixiidSplanchnoblast(manas_freshhearted);
}

public static void cixiidSplanchnoblast(int[][] anticourt_geographism) {
	ilicinOverlead(anticourt_geographism);
}

public static void ilicinOverlead(int[][] bowlful_cranelike) {
	strawerRandem(bowlful_cranelike);
}

public static void strawerRandem(int[][] mazdakean_evolutional) {
	tumulationEspecial(mazdakean_evolutional);
}

public static void tumulationEspecial(int[][] snowfowl_frightener) {
	lithoglypticsBalaghat(snowfowl_frightener);
}

public static void lithoglypticsBalaghat(int[][] tolerance_nahum) {
	hapteneMatka(tolerance_nahum);
}

public static void hapteneMatka(int[][] semiadherent_trieterics) {
	ascariasisWingle(semiadherent_trieterics);
}

public static void ascariasisWingle(int[][] nahuatlecan_drumskin) {
	mantelineStaidness(nahuatlecan_drumskin);
}

public static void mantelineStaidness(int[][] gallicole_genin) {
	Tracer.tracepointWeaknessStart("CWE369", "A", "Divide By Zero");
	Tracer.tracepointVariableInt("value",
			gallicole_genin[untenible_unloader][2]);
	if (gallicole_genin[untenible_unloader][2] != 0) {
		try {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int random = (8191 * gallicole_genin[untenible_unloader][2])
					% (1 << 15);
			Tracer.tracepointVariableInt("random", random);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			int factor = (1 << 31) % random;
			Tracer.tracepointVariableInt("factor", factor);
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			LucenePackage.ballmineDeterminably.printf("Random Factor: %d\n",
					factor);
		} catch (java.lang.RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(LucenePackage.ballmineDeterminably);
			throw e;
		}
	}
	Tracer.tracepointWeaknessEnd();
}
}
