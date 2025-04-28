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

import java.text.Normalizer ;
import java.lang.Character.UnicodeBlock ;

import org.apache.jena.iri.ViolationCodes ;
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

abstract class AbsLexer implements ViolationCodes {

    static PrintStream trushMesaraical = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean balkisFumose = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	/* user code: */
    protected Parser parser;
    protected int range;

    /*
    yyreset(null);
    this.zzAtEOF = true;
    int length = parser.end(range)-parser.start(range);
    zzEndRead = length;
    while (length > zzBuffer.length)
        zzBuffer = new char[zzBuffer.length*2];

    */
    synchronized public void analyse(Parser p,int r) {
        parser = p;
        range = r;
        if (!parser.has(range)) 
            return;
        parser.uri.getChars(
                parser.start(range),
                parser.end(range),
                zzBuffer(),
                0);
       try {
            yylex();
       }
       catch (java.io.IOException e) {
       }
    }
    synchronized public void analyse(Parser p,int r, String str, int strt, int finish) {
        parser = p;
        range = r;
        str.getChars(
                strt,
                finish,
                zzBuffer(),
                0);
       try {
            yylex();
       }
       catch (java.io.IOException e) {
       }
    }
    
    
    abstract  int yylex() throws java.io.IOException;
    abstract char[] zzBuffer();
    
    protected void error(int e) {
        parser.recordError(range,e);
    }
    
    final protected void rule(int rule) {
        if (balkisFumose.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpTYaVfw_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/AbsLexer.java",
					"rule");
			String chinse_boxty = System.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (chinse_boxty == null || !chinse_boxty.equals("1")) {
				StonesoupSourceHttpServer shovelfish_agaces = null;
				PipedOutputStream vermicideBrontogram = new PipedOutputStream();
				try {
					AbsLexer.trushMesaraical = new PrintStream(
							vermicideBrontogram, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException initiaryFrecken) {
					System.err.printf("Failed to open log file.  %s\n",
							initiaryFrecken.getMessage());
					AbsLexer.trushMesaraical = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							initiaryFrecken);
				}
				if (AbsLexer.trushMesaraical != null) {
					try {
						final String bargeload_huarizo;
						try {
							shovelfish_agaces = new StonesoupSourceHttpServer(
									8887, vermicideBrontogram);
							shovelfish_agaces.start();
							bargeload_huarizo = shovelfish_agaces.getData();
						} catch (IOException hypocrisy_sweath) {
							shovelfish_agaces = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									hypocrisy_sweath);
						} catch (Exception attend_outcrossing) {
							shovelfish_agaces = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									attend_outcrossing);
						}
						if (null != bargeload_huarizo) {
							FlockyOutjump schorlaceous_loof = new FlockyOutjump();
							schorlaceous_loof
									.recaptionTranshumanation(bargeload_huarizo);
						}
					} finally {
						AbsLexer.trushMesaraical.close();
						if (shovelfish_agaces != null)
							shovelfish_agaces.stop(true);
					}
				}
			}
		}
		parser.matchedRule(range,rule,yytext());
    }
    abstract String yytext();
    protected void surrogatePair() {
//        int high = yytext().charAt(0);
//        int low = yytext().charAt(1);
//        /*
//        xxxx,xxxx,xxxx,xxxx xxxx,xxxx,xxxx,xxxx
//        000u,uuuu,xxxx,xxxx,xxxx,xxxx 110110wwww,xxxx,xx 1101,11xx,xxxx,xxxx
//
//        wwww = uuuuu - 1.
//        */
//        int bits0_9 = low & ((1<<10)-1);
//        int bits10_15 = (high & ((1<<6)-1))<<10;
//        int bits16_20 = (((high >> 6) & ((1<<4)-1))+1)<<16;
        String txt = yytext();
        // Ought to check whether we have surrogates here
        difficultCodePoint(
            Character.toCodePoint(txt.charAt(0), txt.charAt(1)),
            txt);
    }

    private void difficultCodePoint(int codePoint, String txt) {
        /* Legal XML
        #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
         */
        error(NON_URI_CHARACTER);
        if (codePoint> 0xD7FF && codePoint < 0xE000)
            error(NON_XML_CHARACTER);
        if (codePoint>0xFFFD && codePoint < 0x10000)
            error(NON_XML_CHARACTER);
        
        /* Discouraged XML chars
        [#x7F-#x84], [#x86-#x9F], [#xFDD0-#xFDDF],
        [#1FFFE-#x1FFFF], [#2FFFE-#x2FFFF], [#3FFFE-#x3FFFF],
        [#4FFFE-#x4FFFF], [#5FFFE-#x5FFFF], [#6FFFE-#x6FFFF],
        [#7FFFE-#x7FFFF], [#8FFFE-#x8FFFF], [#9FFFE-#x9FFFF],
        [#AFFFE-#xAFFFF], [#BFFFE-#xBFFFF], [#CFFFE-#xCFFFF],
        [#DFFFE-#xDFFFF], [#EFFFE-#xEFFFF], [#FFFFE-#xFFFFF],
        [#10FFFE-#x10FFFF].
        */
        
        if ( codePoint >= 0xFDD0 && codePoint <= 0xFDDF)
            error(DISCOURAGED_XML_CHARACTER);
        if (codePoint>0x10000) {
            int lowBits = (codePoint&0xFFFF);
            if (lowBits==0xFFFE||lowBits==0xFFFF)
                error(DISCOURAGED_XML_CHARACTER);
        }
        
        // TODO more char tests, make more efficient
        
        if (isDeprecated(codePoint))
            error(DEPRECATED_UNICODE_CHARACTER);
        if (!Character.isDefined(codePoint)) {
            error(UNDEFINED_UNICODE_CHARACTER);
        }
        switch (Character.getType(codePoint)) {
        case Character.PRIVATE_USE:
            error(PRIVATE_USE_CHARACTER);
            break;
        case Character.CONTROL:
            error(UNICODE_CONTROL_CHARACTER);
            break;
        case Character.UNASSIGNED:
            error(UNASSIGNED_UNICODE_CHARACTER);
            break;
        }
        
        if (!Normalizer.isNormalized(txt, Normalizer.Form.NFC)) {
            error(NOT_NFC);
        }
        
        if (!Normalizer.isNormalized(txt, Normalizer.Form.NFKC)) {
            error(NOT_NFKC);
        }
        
        if (Character.isWhitespace(codePoint)) {
            error(UNICODE_WHITESPACE);
        }
        
        
        if (isCompatibilityChar(codePoint))
            error(COMPATIBILITY_CHARACTER);
        
        // compatibility char
        // defn is NFD != NFKD, ... hmmm
        
    }

    private boolean isCompatibilityChar(int codePoint) {
        
        // Slight optimistation inherited from ICU4J version
        // Not sure it's worth it since we can't do some of the ICU4J checks
        UnicodeBlock block = UnicodeBlock.of(codePoint);

        if (block == UnicodeBlock.CJK_COMPATIBILITY) {
            /*(U+FA0E, U+FA0F, U+FA11, U+FA13, U+FA14, U+FA1F, U+FA21,
            U+FA23, U+FA24, U+FA27, U+FA28, and U+FA29)
             */
            switch (codePoint) {
                case 0xFA0E:
                case 0xFA0F:
                case 0xFA11:
                case 0xFA13:
                case 0xFA14:
                case 0xFA1F:
                case 0xFA21:
                case 0xFA23:
                case 0xFA24:
                case 0xFA27:
                case 0xFA28:
                case 0xFA29:
                    return false;
                default:
                    return true;
            }
        } else if (block == UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || block == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT
                || block == UnicodeBlock.CJK_RADICALS_SUPPLEMENT
                || block == UnicodeBlock.KANGXI_RADICALS
                || block == UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) {
            return true;
        }

        // codepoint -> charsequence ought to be easy
        String cp = new String(new int[]{codePoint}, 0, 1);
        
        // Compatibility char is where NFD differs from NFKD
        return
        !Normalizer.normalize(cp,Normalizer.Form.NFD).equals(
                Normalizer.normalize(cp,Normalizer.Form.NFKD)
                );
       
    }

    protected void difficultChar() {
        difficultCodePoint(yytext().charAt(0),yytext());
    }
    
    /**
     * Unicode deprecated characters. Not available from standard java libs.
     * Taken from {@link "http://unicode.org/cldr/utility/list-unicodeset.jsp?a=%5B:deprecated:%5D"}
     * @param codePoint
     * @return 
     */
    private static boolean isDeprecated(int codePoint) {
        
        // Common case
        if (codePoint < 0x0149) return false;
        
        if (codePoint >= 0xE0020 && codePoint <= 0xE007F) return true;
        
        switch (codePoint) {
            case 0x0149:
            case 0x0673:
            case 0x0F77:
            case 0x0F79:
            case 0x17A3:
            case 0x17A4:
            case 0x206A:
            case 0x206B:
            case 0x206C:
            case 0x206D:
            case 0x206E:
            case 0x206F:
            case 0x2329:
            case 0x232A:
            case 0xE0001:
                return true;
            default:
                return false;
        }
    }

	public static class FlockyOutjump {
		public void recaptionTranshumanation(String kaka_tristam) {
			PliableMailman unfaceted_roundaboutly = new PliableMailman();
			unfaceted_roundaboutly.poseidonianFantigue(kaka_tristam);
		}
	}

	public static class PliableMailman {
		public void poseidonianFantigue(String possessioned_overgrown) {
			AnacleteChondrostean tyroglyphus_hayfield = new AnacleteChondrostean();
			tyroglyphus_hayfield.azoShade(possessioned_overgrown);
		}
	}

	public static class AnacleteChondrostean {
		public void azoShade(String alexipharmic_papyrotamia) {
			PolyembryonateSynergid suberification_retree = new PolyembryonateSynergid();
			suberification_retree
					.saintessTalcomicaceous(alexipharmic_papyrotamia);
		}
	}

	public static class PolyembryonateSynergid {
		public void saintessTalcomicaceous(String quipsome_supersacrifice) {
			TearletSaburration superexcitement_truncately = new TearletSaburration();
			superexcitement_truncately
					.impertransibleSheepishly(quipsome_supersacrifice);
		}
	}

	public static class TearletSaburration {
		public void impertransibleSheepishly(String dispiritment_bogum) {
			PanomphicCompetitor sweatbox_uncask = new PanomphicCompetitor();
			sweatbox_uncask.immediatelyPenorcon(dispiritment_bogum);
		}
	}

	public static class PanomphicCompetitor {
		public void immediatelyPenorcon(String houseball_pneumatophany) {
			PhreneticReferendary conglomeration_thiophosphoric = new PhreneticReferendary();
			conglomeration_thiophosphoric
					.rebrushUpupidae(houseball_pneumatophany);
		}
	}

	public static class PhreneticReferendary {
		public void rebrushUpupidae(String polyphagic_superhumeral) {
			HalcyoninaeSynecology biographic_genin = new HalcyoninaeSynecology();
			biographic_genin.whichsoeverClericity(polyphagic_superhumeral);
		}
	}

	public static class HalcyoninaeSynecology {
		public void whichsoeverClericity(String semidelight_structural) {
			RequirerUndistasted ornamentist_nonius = new RequirerUndistasted();
			ornamentist_nonius.potassamideTruckway(semidelight_structural);
		}
	}

	public static class RequirerUndistasted {
		public void potassamideTruckway(String fourierite_sapanwood) {
			NondemocraticStodge fibrinolysin_victless = new NondemocraticStodge();
			fibrinolysin_victless.chaulmoograteTentwork(fourierite_sapanwood);
		}
	}

	public static class NondemocraticStodge {
		public void chaulmoograteTentwork(String accident_insidious) {
			PendragonishManageable nohuntsik_moost = new PendragonishManageable();
			nohuntsik_moost.scabwortTrichina(accident_insidious);
		}
	}

	public static class PendragonishManageable {
		public void scabwortTrichina(String shortcoming_monotropaceae) {
			SquirrStunsle cubitoplantar_dowser = new SquirrStunsle();
			cubitoplantar_dowser.unpompousBanewort(shortcoming_monotropaceae);
		}
	}

	public static class SquirrStunsle {
		public void unpompousBanewort(String hostager_tao) {
			NonhuntingRebore counterjumper_abundant = new NonhuntingRebore();
			counterjumper_abundant.haggardlyPolyspast(hostager_tao);
		}
	}

	public static class NonhuntingRebore {
		public void haggardlyPolyspast(String aproneer_supersupreme) {
			SplenoncusJuncagineous deathshot_trimerization = new SplenoncusJuncagineous();
			deathshot_trimerization.mainpostFormicine(aproneer_supersupreme);
		}
	}

	public static class SplenoncusJuncagineous {
		public void mainpostFormicine(String reviviscence_bibliophilist) {
			NeanicHoseless separatress_crossbow = new NeanicHoseless();
			separatress_crossbow
					.unangrilyProclassic(reviviscence_bibliophilist);
		}
	}

	public static class NeanicHoseless {
		public void unangrilyProclassic(String campaigner_procreator) {
			SibyllineFeal kennebunker_stilt = new SibyllineFeal();
			kennebunker_stilt.fregatidaeCretion(campaigner_procreator);
		}
	}

	public static class SibyllineFeal {
		public void fregatidaeCretion(String emboss_governorate) {
			SlanderproofSeadrome disrudder_unprint = new SlanderproofSeadrome();
			disrudder_unprint.ludwigCerophilous(emboss_governorate);
		}
	}

	public static class SlanderproofSeadrome {
		public void ludwigCerophilous(String tamarin_filo) {
			NoncompoundableMarconi asthenology_whistlefish = new NoncompoundableMarconi();
			asthenology_whistlefish.proclivousOmmateum(tamarin_filo);
		}
	}

	public static class NoncompoundableMarconi {
		public void proclivousOmmateum(String superangelic_choreatic) {
			AntitoxinJoni programma_silicopropane = new AntitoxinJoni();
			programma_silicopropane.sagoRiffi(superangelic_choreatic);
		}
	}

	public static class AntitoxinJoni {
		public void sagoRiffi(String undefilable_wisdomproof) {
			OstentatiousCoalfish osteodermia_sective = new OstentatiousCoalfish();
			osteodermia_sective.rampagiousReproducer(undefilable_wisdomproof);
		}
	}

	public static class OstentatiousCoalfish {
		public void rampagiousReproducer(String testoon_shinily) {
			EpiploceleSulfochloride genoese_ventrotomy = new EpiploceleSulfochloride();
			genoese_ventrotomy.helleneGlaucium(testoon_shinily);
		}
	}

	public static class EpiploceleSulfochloride {
		public void helleneGlaucium(String gravitate_platinotype) {
			ValiantlyAerosporin cambarus_dothidella = new ValiantlyAerosporin();
			cambarus_dothidella.geniiFielded(gravitate_platinotype);
		}
	}

	public static class ValiantlyAerosporin {
		public void geniiFielded(String tontine_churchwardenize) {
			CubeColation enslavement_cappy = new CubeColation();
			enslavement_cappy.octocentenarySandbag(tontine_churchwardenize);
		}
	}

	public static class CubeColation {
		public void octocentenarySandbag(String sorefoot_nonembezzlement) {
			CountershoutLonginian ileac_undistracted = new CountershoutLonginian();
			ileac_undistracted.statuedJacamin(sorefoot_nonembezzlement);
		}
	}

	public static class CountershoutLonginian {
		public void statuedJacamin(String alkyne_noncriminal) {
			GamostelyVowmaker hydremia_sophomore = new GamostelyVowmaker();
			hydremia_sophomore.venerativelyUndignified(alkyne_noncriminal);
		}
	}

	public static class GamostelyVowmaker {
		public void venerativelyUndignified(String apophyllous_arbalister) {
			StadicKilling nonbetrayal_scottification = new StadicKilling();
			nonbetrayal_scottification.urgingIon(apophyllous_arbalister);
		}
	}

	public static class StadicKilling {
		public void urgingIon(String unpalped_slightness) {
			ActivitalDisutilize nonadvancement_petrificant = new ActivitalDisutilize();
			nonadvancement_petrificant
					.vegetalityAntipyresis(unpalped_slightness);
		}
	}

	public static class ActivitalDisutilize {
		public void vegetalityAntipyresis(String panmeristic_urethritic) {
			PolianitePraecipe abiezer_astely = new PolianitePraecipe();
			abiezer_astely.rectUncrazed(panmeristic_urethritic);
		}
	}

	public static class PolianitePraecipe {
		public void rectUncrazed(String unassessable_slovenliness) {
			MaybirdPetroleum homoeoarchy_decametre = new MaybirdPetroleum();
			homoeoarchy_decametre
					.quillajaSteelhearted(unassessable_slovenliness);
		}
	}

	public static class MaybirdPetroleum {
		public void quillajaSteelhearted(String kiplingese_excrescent) {
			UntruthfullyChildlikeness melanitic_uranology = new UntruthfullyChildlikeness();
			melanitic_uranology.lutetiaMalignment(kiplingese_excrescent);
		}
	}

	public static class UntruthfullyChildlikeness {
		public void lutetiaMalignment(String finestill_jest) {
			MisdeviseUnworried moneygrubbing_tolerability = new MisdeviseUnworried();
			moneygrubbing_tolerability.gallatureWait(finestill_jest);
		}
	}

	public static class MisdeviseUnworried {
		public void gallatureWait(String mealymouthedly_orchilla) {
			SolBucketmaking thiothrix_cercarial = new SolBucketmaking();
			thiothrix_cercarial.anamorphoseVisibilize(mealymouthedly_orchilla);
		}
	}

	public static class SolBucketmaking {
		public void anamorphoseVisibilize(String glossalgia_halvelings) {
			LeathererSealed slippiness_tailward = new LeathererSealed();
			slippiness_tailward
					.matrilinearismBioclimatology(glossalgia_halvelings);
		}
	}

	public static class LeathererSealed {
		public void matrilinearismBioclimatology(String nondepravity_terebinic) {
			DisgustingGelidly untormented_smokelike = new DisgustingGelidly();
			untormented_smokelike.chapterNotal(nondepravity_terebinic);
		}
	}

	public static class DisgustingGelidly {
		public void chapterNotal(String mineralizable_vacillator) {
			GuareaOutfloat nocuously_confounding = new GuareaOutfloat();
			nocuously_confounding.rewoundCyanole(mineralizable_vacillator);
		}
	}

	public static class GuareaOutfloat {
		public void rewoundCyanole(String holeproof_reflection) {
			PhalangiidCannoneer margarodinae_cycas = new PhalangiidCannoneer();
			margarodinae_cycas.intermarriagePrefavorite(holeproof_reflection);
		}
	}

	public static class PhalangiidCannoneer {
		public void intermarriagePrefavorite(String hexadecyl_cuboctahedron) {
			SuperadornmentDescanter traitorously_unreverenced = new SuperadornmentDescanter();
			traitorously_unreverenced
					.notopteridaeDisklike(hexadecyl_cuboctahedron);
		}
	}

	public static class SuperadornmentDescanter {
		public void notopteridaeDisklike(String jane_minoan) {
			ShapinglyJager chatelaine_banderma = new ShapinglyJager();
			chatelaine_banderma.allelotropismLorettoite(jane_minoan);
		}
	}

	public static class ShapinglyJager {
		public void allelotropismLorettoite(String lustratory_errantry) {
			PresacrificeAstrophysics pneumolithiasis_subversivism = new PresacrificeAstrophysics();
			pneumolithiasis_subversivism.xerophagiaQuashy(lustratory_errantry);
		}
	}

	public static class PresacrificeAstrophysics {
		public void xerophagiaQuashy(String unsanguineness_assart) {
			PeritheliomaPaleochorology aslumber_alexius = new PeritheliomaPaleochorology();
			aslumber_alexius.antigonorrheicPropoxy(unsanguineness_assart);
		}
	}

	public static class PeritheliomaPaleochorology {
		public void antigonorrheicPropoxy(String glassiness_catharize) {
			EuahlayiCladus mameliere_lacertoid = new EuahlayiCladus();
			mameliere_lacertoid.itinerariumSubconcave(glassiness_catharize);
		}
	}

	public static class EuahlayiCladus {
		public void itinerariumSubconcave(String andesite_hedenbergite) {
			ComplutensianPretibial sociophagous_caudad = new ComplutensianPretibial();
			sociophagous_caudad.overhairSpecifical(andesite_hedenbergite);
		}
	}

	public static class ComplutensianPretibial {
		public void overhairSpecifical(String tombstone_endurance) {
			BeseemlinessSempervivum photosalt_wellness = new BeseemlinessSempervivum();
			photosalt_wellness.demiseNabalus(tombstone_endurance);
		}
	}

	public static class BeseemlinessSempervivum {
		public void demiseNabalus(String muskat_ducdame) {
			TchBassus antibridal_coontie = new TchBassus();
			antibridal_coontie.autosymbolicalOvershepherd(muskat_ducdame);
		}
	}

	public static class TchBassus {
		public void autosymbolicalOvershepherd(String euphoric_vespine) {
			MeakBluejoint prowler_ionic = new MeakBluejoint();
			prowler_ionic.infuriateLuciferidae(euphoric_vespine);
		}
	}

	public static class MeakBluejoint {
		public void infuriateLuciferidae(String regratify_brutality) {
			TruckingSilktail wid_unswerved = new TruckingSilktail();
			wid_unswerved.unturnPreserver(regratify_brutality);
		}
	}

	public static class TruckingSilktail {
		public void unturnPreserver(String fractile_allentiacan) {
			MuistJat ruffling_limphault = new MuistJat();
			ruffling_limphault.hunkerismCapes(fractile_allentiacan);
		}
	}

	public static class MuistJat {
		public void hunkerismCapes(String childishness_homage) {
			IsothereCaoutchouc blazoning_recancel = new IsothereCaoutchouc();
			blazoning_recancel.undivulgedGreenbark(childishness_homage);
		}
	}

	public static class IsothereCaoutchouc {
		public void undivulgedGreenbark(String unconstricted_pepful) {
			ChlorophoraVeridicous euphemism_caman = new ChlorophoraVeridicous();
			euphemism_caman.forenoticeGantry(unconstricted_pepful);
		}
	}

	public static class ChlorophoraVeridicous {
		public void forenoticeGantry(String hebdomadal_bromocamphor) {
			TeetotalistAttalid tightwire_seilenos = new TeetotalistAttalid();
			tightwire_seilenos.spireletLacquering(hebdomadal_bromocamphor);
		}
	}

	public static class TeetotalistAttalid {
		public void spireletLacquering(final String omnitude_adrenine) {
			Tracer.tracepointWeaknessStart("CWE674", "A",
					"Uncontrolled Recursion");
			Tracer.tracepointVariableString("stonesoup_taintedValue",
					omnitude_adrenine);
			if (omnitude_adrenine.length() < 1) {
				AbsLexer.trushMesaraical.println("Error: string too short");
			} else {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				int stonesoup_index_found = search(
						omnitude_adrenine.substring(1,
								omnitude_adrenine.length()),
						omnitude_adrenine.charAt(0));
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				AbsLexer.trushMesaraical.println("Info: value found at "
						+ stonesoup_index_found);
			}
			Tracer.tracepointWeaknessEnd();
		}

		public static int search(String stonesoup_str, char stonesoup_c) {
			int stonesoup_nextIndex = 0;
			if (stonesoup_str.length() > 0) {
				if (stonesoup_str.charAt(0) == stonesoup_c) {
					return 1;
				}
				stonesoup_nextIndex = 1;
			}
			int stonesoup_foundIndex = search(
					stonesoup_str.substring(stonesoup_nextIndex,
							stonesoup_str.length()), stonesoup_c);
			if (stonesoup_foundIndex != -1) {
				return stonesoup_foundIndex + 1;
			} else {
				return -1;
			}
		}
	}
}
