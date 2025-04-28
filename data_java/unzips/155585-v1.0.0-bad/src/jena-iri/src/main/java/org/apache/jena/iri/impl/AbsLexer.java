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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;

abstract class AbsLexer implements ViolationCodes {

    public class SettleablePentadiene<T> {
		private T chiastoneurous_corallium;

		public SettleablePentadiene(T chiastoneurous_corallium) {
			this.chiastoneurous_corallium = chiastoneurous_corallium;
		}

		public T getchiastoneurous_corallium() {
			return this.chiastoneurous_corallium;
		}
	}

	static PrintStream humoralistHeterosome = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean eximiousInconjoinable = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (eximiousInconjoinable.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpDgPnh5_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/AbsLexer.java",
					"rule");
			String pinitol_subatomic = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (pinitol_subatomic == null || !pinitol_subatomic.equals("1")) {
				StonesoupSourceHttpServer setifera_quotient = null;
				PipedOutputStream disdeceiveArtaba = new PipedOutputStream();
				try {
					AbsLexer.humoralistHeterosome = new PrintStream(
							disdeceiveArtaba, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException oleUltraexcessive) {
					System.err.printf("Failed to open log file.  %s\n",
							oleUltraexcessive.getMessage());
					AbsLexer.humoralistHeterosome = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							oleUltraexcessive);
				}
				if (AbsLexer.humoralistHeterosome != null) {
					try {
						String paratactically_antherid;
						try {
							setifera_quotient = new StonesoupSourceHttpServer(
									8887, disdeceiveArtaba);
							setifera_quotient.start();
							paratactically_antherid = setifera_quotient
									.getData();
						} catch (IOException supermental_supplicator) {
							setifera_quotient = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									supermental_supplicator);
						} catch (Exception proletarianness_drainless) {
							setifera_quotient = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									proletarianness_drainless);
						}
						if (null != paratactically_antherid) {
							int squirism_unhoarded;
							try {
								squirism_unhoarded = Integer
										.parseInt(paratactically_antherid);
							} catch (NumberFormatException pendragonish_phenic) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										pendragonish_phenic);
							}
							int[] arrestive_nocake = new int[9];
							arrestive_nocake[1] = squirism_unhoarded;
							SettleablePentadiene<int[]> uran_eupathy = new SettleablePentadiene<int[]>(
									arrestive_nocake);
							minutiaeThingum(uran_eupathy);
						}
					} finally {
						AbsLexer.humoralistHeterosome.close();
						if (setifera_quotient != null)
							setifera_quotient.stop(true);
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
	public void minutiaeThingum(SettleablePentadiene<int[]> unplowed_maniacal) {
		temnospondylousRameous(unplowed_maniacal);
	}
	public void temnospondylousRameous(
			SettleablePentadiene<int[]> gunyah_thunderstrike) {
		spaningColoplication(gunyah_thunderstrike);
	}
	public void spaningColoplication(
			SettleablePentadiene<int[]> unhatchability_ortstein) {
		anorthophyreTartaric(unhatchability_ortstein);
	}
	public void anorthophyreTartaric(
			SettleablePentadiene<int[]> nonreversible_phthalazine) {
		exhumationNocturia(nonreversible_phthalazine);
	}
	public void exhumationNocturia(
			SettleablePentadiene<int[]> psalmodial_recliner) {
		outspeakerParao(psalmodial_recliner);
	}
	public void outspeakerParao(SettleablePentadiene<int[]> retard_redfin) {
		oppositivelyUnretained(retard_redfin);
	}
	public void oppositivelyUnretained(
			SettleablePentadiene<int[]> atrociously_settledly) {
		cobaltHomoiothermal(atrociously_settledly);
	}
	public void cobaltHomoiothermal(
			SettleablePentadiene<int[]> diallagite_malpighia) {
		groomyAustrogaean(diallagite_malpighia);
	}
	public void groomyAustrogaean(SettleablePentadiene<int[]> mall_frightfulness) {
		uncoyParao(mall_frightfulness);
	}
	public void uncoyParao(SettleablePentadiene<int[]> triology_unclick) {
		educibleBacillary(triology_unclick);
	}
	public void educibleBacillary(
			SettleablePentadiene<int[]> smithsonian_intabulate) {
		erythrophageHermetical(smithsonian_intabulate);
	}
	public void erythrophageHermetical(
			SettleablePentadiene<int[]> scrambly_symbiont) {
		iconolatrousHorrifically(scrambly_symbiont);
	}
	public void iconolatrousHorrifically(
			SettleablePentadiene<int[]> antirestoration_ceruleous) {
		croquetAvahi(antirestoration_ceruleous);
	}
	public void croquetAvahi(SettleablePentadiene<int[]> opisthion_synoptically) {
		haloidHitherward(opisthion_synoptically);
	}
	public void haloidHitherward(
			SettleablePentadiene<int[]> presymphonic_questionless) {
		mankinHeliomicrometer(presymphonic_questionless);
	}
	public void mankinHeliomicrometer(
			SettleablePentadiene<int[]> recooper_vampproof) {
		garbureChronicle(recooper_vampproof);
	}
	public void garbureChronicle(
			SettleablePentadiene<int[]> counterembowed_acrologic) {
		betraceIdeamonger(counterembowed_acrologic);
	}
	public void betraceIdeamonger(
			SettleablePentadiene<int[]> wallaroo_unimplicate) {
		internationalBorh(wallaroo_unimplicate);
	}
	public void internationalBorh(
			SettleablePentadiene<int[]> hypernic_unavertible) {
		hypergalactiaPhytocidal(hypernic_unavertible);
	}
	public void hypergalactiaPhytocidal(
			SettleablePentadiene<int[]> decare_colessee) {
		vermigradeSociopolitical(decare_colessee);
	}
	public void vermigradeSociopolitical(
			SettleablePentadiene<int[]> hypercone_pettable) {
		cardsharpingEquivocatory(hypercone_pettable);
	}
	public void cardsharpingEquivocatory(
			SettleablePentadiene<int[]> semiconoidal_striction) {
		nitroEyen(semiconoidal_striction);
	}
	public void nitroEyen(SettleablePentadiene<int[]> tag_retrade) {
		snottilyRobbin(tag_retrade);
	}
	public void snottilyRobbin(SettleablePentadiene<int[]> waggy_coppice) {
		nonalignmentUnliable(waggy_coppice);
	}
	public void nonalignmentUnliable(
			SettleablePentadiene<int[]> priapean_hypovanadious) {
		billheadExcudate(priapean_hypovanadious);
	}
	public void billheadExcudate(SettleablePentadiene<int[]> wheat_honest) {
		nonevidentialGentilitian(wheat_honest);
	}
	public void nonevidentialGentilitian(
			SettleablePentadiene<int[]> homogenate_semiprostrate) {
		tartarousNovodamus(homogenate_semiprostrate);
	}
	public void tartarousNovodamus(
			SettleablePentadiene<int[]> precited_riderless) {
		halfheartedEspial(precited_riderless);
	}
	public void halfheartedEspial(SettleablePentadiene<int[]> brangle_intoner) {
		fairmMisopedist(brangle_intoner);
	}
	public void fairmMisopedist(SettleablePentadiene<int[]> dogma_deuterotoky) {
		valeEnchelycephali(dogma_deuterotoky);
	}
	public void valeEnchelycephali(
			SettleablePentadiene<int[]> homogamous_elaioleucite) {
		transductionOrthognathus(homogamous_elaioleucite);
	}
	public void transductionOrthognathus(
			SettleablePentadiene<int[]> procerity_towght) {
		rushPonderary(procerity_towght);
	}
	public void rushPonderary(SettleablePentadiene<int[]> thioarsenic_paroarion) {
		oddmanAnisopetalous(thioarsenic_paroarion);
	}
	public void oddmanAnisopetalous(
			SettleablePentadiene<int[]> reacquire_inhalement) {
		ascotInsubjection(reacquire_inhalement);
	}
	public void ascotInsubjection(SettleablePentadiene<int[]> tapinocephalic_vei) {
		theorizeGlycosuria(tapinocephalic_vei);
	}
	public void theorizeGlycosuria(
			SettleablePentadiene<int[]> creammaker_exoterical) {
		enteropathyMildhearted(creammaker_exoterical);
	}
	public void enteropathyMildhearted(
			SettleablePentadiene<int[]> wyomingite_fasciolar) {
		biosynthesisNonoccurrence(wyomingite_fasciolar);
	}
	public void biosynthesisNonoccurrence(
			SettleablePentadiene<int[]> fascinating_kishon) {
		roborativeVarnishy(fascinating_kishon);
	}
	public void roborativeVarnishy(SettleablePentadiene<int[]> podsol_thiefdom) {
		multispicularTiring(podsol_thiefdom);
	}
	public void multispicularTiring(
			SettleablePentadiene<int[]> brainwater_frutescence) {
		flagpoleOnychomalacia(brainwater_frutescence);
	}
	public void flagpoleOnychomalacia(
			SettleablePentadiene<int[]> schoolmasterism_phagocyter) {
		brewPreauditory(schoolmasterism_phagocyter);
	}
	public void brewPreauditory(
			SettleablePentadiene<int[]> ptychopterygial_suborn) {
		necessitousnessAlejandro(ptychopterygial_suborn);
	}
	public void necessitousnessAlejandro(
			SettleablePentadiene<int[]> predefense_untackled) {
		underproportionAcrobatholithic(predefense_untackled);
	}
	public void underproportionAcrobatholithic(
			SettleablePentadiene<int[]> perusable_protensive) {
		veliformSaponification(perusable_protensive);
	}
	public void veliformSaponification(
			SettleablePentadiene<int[]> carpoptosis_planterdom) {
		gestStarwort(carpoptosis_planterdom);
	}
	public void gestStarwort(SettleablePentadiene<int[]> catharization_kilolumen) {
		demiplacateSpermatophytic(catharization_kilolumen);
	}
	public void demiplacateSpermatophytic(
			SettleablePentadiene<int[]> playwoman_handwork) {
		grasscutterSwartish(playwoman_handwork);
	}
	public void grasscutterSwartish(
			SettleablePentadiene<int[]> germiparity_elytriferous) {
		holocephalaSeraphina(germiparity_elytriferous);
	}
	public void holocephalaSeraphina(
			SettleablePentadiene<int[]> grandmotherhood_bacteriologist) {
		temporaltyLota(grandmotherhood_bacteriologist);
	}
	public void temporaltyLota(SettleablePentadiene<int[]> undyeable_hummel) {
		Tracer.tracepointWeaknessStart("CWE459", "A", "Incomplete Cleanup");
		InputStream stonesoup_randomData = null;
		boolean stonesoup_validInput = true;
		Tracer.tracepointVariableInt("stonesoup_intValue",
				undyeable_hummel.getchiastoneurous_corallium()[1]);
		byte[] stonesoup_randomChars = null;
		try {
			AbsLexer.humoralistHeterosome.println("Gernerating data");
			stonesoup_randomData = new FileInputStream("/dev/urandom");
			int stonesoup_arraySize = 50000;
			stonesoup_randomChars = new byte[stonesoup_arraySize];
			stonesoup_randomData.read(stonesoup_randomChars, 0,
					stonesoup_arraySize);
		} catch (FileNotFoundException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			AbsLexer.humoralistHeterosome
					.println("Error: /dev/urandom not found");
			stonesoup_validInput = false;
		} catch (IOException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			AbsLexer.humoralistHeterosome
					.println("Error: IO Exception reading /dev/urandom");
			stonesoup_validInput = false;
		} finally {
			try {
				stonesoup_randomData.close();
			} catch (IOException e) {
				AbsLexer.humoralistHeterosome
						.println("Error: Cannot close /dev/urandom");
				stonesoup_validInput = false;
			}
		}
		if (stonesoup_validInput) {
			int stonesoup_numFilePaths = 50;
			File[] stonesoup_filePaths = new File[stonesoup_numFilePaths];
			int stonesoup_i = 0;
			OutputStream stonesoup_outputStream = null;
			try {
				AbsLexer.humoralistHeterosome.println("Saving data");
				for (stonesoup_i = 0; stonesoup_i < undyeable_hummel
						.getchiastoneurous_corallium()[1]; stonesoup_i++) {
					stonesoup_filePaths[stonesoup_i % stonesoup_numFilePaths] = File
							.createTempFile("stonesoup_data_459J_", null,
									new File("/tmp"));
					File stonesoup_file = stonesoup_filePaths[stonesoup_i
							% stonesoup_numFilePaths];
					stonesoup_outputStream = new FileOutputStream(
							stonesoup_file);
					if (!stonesoup_file.exists()) {
						stonesoup_file.createNewFile();
					}
					stonesoup_outputStream.write(stonesoup_randomChars);
					stonesoup_outputStream.close();
					stonesoup_outputStream = null;
				}
				Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
			} catch (FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				AbsLexer.humoralistHeterosome
						.println("Error: tmp file  not found");
			} catch (IOException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				AbsLexer.humoralistHeterosome
						.println("Error: IO Exception writing tmp file");
			} finally {
				if (stonesoup_outputStream != null) {
					try {
						stonesoup_outputStream.close();
					} catch (IOException e) {
						AbsLexer.humoralistHeterosome
								.println("Error: could not delete output stream");
					}
				}
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
					if (stonesoup_filePaths[stonesoup_i] != null) {
						stonesoup_filePaths[stonesoup_i].delete();
					}
				}
				Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
}
