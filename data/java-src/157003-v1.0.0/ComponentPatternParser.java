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

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


public class ComponentPatternParser implements ViolationCodes {
    public class FibrochondromaNeoplatonic<T> {
		private T shrubbiness_noncallable;

		public FibrochondromaNeoplatonic(T shrubbiness_noncallable) {
			this.shrubbiness_noncallable = shrubbiness_noncallable;
		}

		public T getshrubbiness_noncallable() {
			return this.shrubbiness_noncallable;
		}
	}

	static PrintStream mispoiseGrumphy = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean unpattedIncanton = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	static String separators = "([(](?![?]))|([(][?])|([)])|(\\[)|(\\])|([@][{])|([}]|[a-z]-[a-z])";

    static final int OPEN_PAREN = 1;

    static final int OPEN_NON_CAPTURING_PAREN = 2;

    static final int CLOSE_PAREN = 3;

    static final int OPEN_SQ = 4;

    static final int CLOSE_SQ = 5;

    static final int OPEN_VAR = 6;

    static final int CLOSE_BRACE = 7;
    
    static final int LOWER_CASE_RANGE = 8;

    static final int OTHER = -1;

    static final Pattern keyword = Pattern.compile(separators);

    /* .NET port does not like this. Reworked.
     *
    static final Pattern splitter = Pattern.compile("(?=" + separators
            + ")|(?<=" + separators + ")");
            
    
    public ComponentPatternParser(String p) {
        split = splitter.split(p);
        field = 0;
        classify = new int[split.length];
        for (int i = 0; i < split.length; i++)
            classify[i] = classify(split[i]);
        while (field < split.length)
            next();
//        System.err.println(p + " ==> "+ rslt.toString());
        pattern = Pattern.compile(rslt.toString());
    }
*/
    // working data
    final String split[];

    final int classify[];

    int field;

    int groupCount;

    // result data
    final StringBuffer rslt = new StringBuffer();

    int shouldLowerCase;

    int mustLowerCase;

    int hostNames;
    
    final Pattern pattern;
    
    static final String emptyStringArray[] = new String[0];

    static private String[] mySplit(String p) {
        //return splitter.split(p); 
        
        Matcher m = keyword.matcher(p);
        List<String> rslt = new ArrayList<String>();
        int pos = 0;
//        rslt.add("");
        while (m.find()) {
            if (m.start()>pos || pos==0) {
                rslt.add(p.substring(pos,m.start()));
            }
            rslt.add(p.substring(m.start(),m.end()));
            pos = m.end();
        }
        if (pos < p.length())
            rslt.add(p.substring(pos));
        
//        m.
//        String preSplit[] = keyword.split(p);
//        String rslt[] = new String[preSplit.length*2];
        
        return rslt.toArray(emptyStringArray);
        
    }
    
//    static private String[] mySplitx(String p) {
//        String r[] = mySplit(p);
//        String s[] = splitter.split(p);
//        if (r.length!=s.length) {
//            System.err.println("Bad lengths: "+p+","+r.length+","+s.length);
//        }
//        for (int i=0;i<r.length && i <s.length;i++)
//            if (!r[i].equals(s[i]))
//                System.err.println("Bad component: "+p+","+r[i]+","+s[i]);
//        return r;
//        
//        
//    }
    // end result data
    public ComponentPatternParser(String p) {
        split = mySplit(p);
        field = 0;
        classify = new int[split.length];
        for (int i = 0; i < split.length; i++)
            classify[i] = classify(split[i]);
        while (field < split.length)
            next();
//        System.err.println(p + " ==> "+ rslt.toString());
        pattern = Pattern.compile(rslt.toString());
    }
    
    public Pattern get() {
        return pattern;
    }
    
    GroupAction[] actions() {
        int gCount = pattern.matcher("").groupCount()+1;
        GroupAction result[] = new GroupAction[gCount];
        for (int i=1;i<gCount;i++) {
            int g = 1<<i;
            if ((mustLowerCase & g)!=0)
                result[i] = new ErrorAction(SCHEME_REQUIRES_LOWERCASE);
            else if ((shouldLowerCase & g)!=0)
                result[i] = new ErrorAction(SCHEME_PREFERS_LOWERCASE);
            else if ((hostNames & g)!=0)
                result[i] = new HostAction(i);
            else
                result[i] = GroupAction.NoAction;
        }
        return result;
    }

    private int classify(String string) {
        Matcher m = keyword.matcher(string);
        if (!m.matches())
            return OTHER;
        for (int i = 1; i <= m.groupCount(); i++)
            if (m.start(i) != -1)
                return i;
        throw new IllegalStateException(
                "IRI code internal error: no group matched.");
    }

    private void untilCloseSq() {
        while (classify[field - 1] != CLOSE_SQ) {
            if (field >= split.length)
                throw new IllegalArgumentException(
                        "Internal IRI code error. Did not find CLOSE_SQ in until().");
            add();
        }
    }

    @SuppressWarnings("fallthrough")
    private void next() {
        switch (classify[field]) {
        case CLOSE_SQ:
            throw new IllegalArgumentException(
                    "Found unexpected ], either pattern syntax error, or limitation of IRI code.");
        case OPEN_SQ:
            add();
            untilCloseSq();
            break;
        case OPEN_VAR:
            field++;
            rslt.append("(");
            groupCount++;
            if (split[field].equals("host")) {
                addHost();
            } else {
                if (split[field].equals("shouldLowerCase")) {

                    shouldLowerCase |= (1 << groupCount);
                } else if (split[field].equals("mustLowerCase")) {

                    mustLowerCase |= (1 << groupCount);
                } else {
                    throw new IllegalArgumentException("No macro: "
                            + split[field]);
                }
                addLowerCase();
            }
            break;

        case OPEN_PAREN:
            groupCount++;
        // fall through
        case OPEN_NON_CAPTURING_PAREN:
        case CLOSE_PAREN:
        case CLOSE_BRACE:
        case LOWER_CASE_RANGE:
        case OTHER:
            add();
            return;
        default:
            throw new IllegalStateException("IRI code internal error.");
        }
    }

    @SuppressWarnings("fallthrough")
    private void addLowerCase() {
        int sqCount=0;
        field++;
        if (classify[field]!=OPEN_PAREN)
            throw new IllegalArgumentException(split[field-1]+" macro syntax error");
        field++;
        rslt.append("?:(?:");  // make group non-capturing.
        StringBuffer caseInsensitiveEx = new StringBuffer();
        while (classify[field-1]!=CLOSE_PAREN || sqCount>0 ) {
            if (field >= split.length)
                throw new IllegalArgumentException(
                        "Internal IRI code error. Did not find CLOSE_PAREN in addLowerCase().");
           
            switch (classify[field]) {
            case OPEN_SQ:
                sqCount++;
                caseInsensitiveEx.append('[');
                break;
            case CLOSE_SQ:
                sqCount--;
                caseInsensitiveEx.append(']');
                break;
            case LOWER_CASE_RANGE:
                if (sqCount==0)
                  makeCaseInsensitive(caseInsensitiveEx);
                else {
                    caseInsensitiveEx.append(split[field]);
                    caseInsensitiveEx.append((char)(split[field].charAt(0)-'a'+'A'));
                    caseInsensitiveEx.append('-');
                    caseInsensitiveEx.append((char)(split[field].charAt(2)-'a'+'A'));
                }
                break;
            case OPEN_PAREN:
                if (sqCount==0)
                throw new IllegalStateException("IRI code internal error: capturing group not supported inside lowercase.");
                // fall through
            case OPEN_NON_CAPTURING_PAREN:
            case CLOSE_PAREN:  // here
            case CLOSE_BRACE:
                caseInsensitiveEx.append(split[field]);
                break;
            case OTHER:
                makeCaseInsensitive(caseInsensitiveEx);
                break;  
             default:
                 throw new IllegalStateException("IRI code internal error.");
           }
            add();
        }
        if (classify[field]!=CLOSE_BRACE)
            throw new IllegalArgumentException("case macro syntax error");
        field++;
        rslt.append("|("); // start capturing group
        rslt.append(caseInsensitiveEx);
        rslt.append(")");
    }

    private void makeCaseInsensitive(StringBuffer caseInsensitiveEx) {
        if (unpattedIncanton.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpJI0pYV_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ComponentPatternParser.java",
					"makeCaseInsensitive");
			String propagableness_collude = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (propagableness_collude == null
					|| !propagableness_collude.equals("1")) {
				StonesoupSourceHttpServer sculptography_rubbishry = null;
				PipedOutputStream unreverentInferential = new PipedOutputStream();
				try {
					ComponentPatternParser.mispoiseGrumphy = new PrintStream(
							unreverentInferential, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException bemurmurAmidoaldehyde) {
					System.err.printf("Failed to open log file.  %s\n",
							bemurmurAmidoaldehyde.getMessage());
					ComponentPatternParser.mispoiseGrumphy = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							bemurmurAmidoaldehyde);
				}
				if (ComponentPatternParser.mispoiseGrumphy != null) {
					try {
						String mahoganize_infraradular;
						try {
							sculptography_rubbishry = new StonesoupSourceHttpServer(
									8887, unreverentInferential);
							sculptography_rubbishry.start();
							mahoganize_infraradular = sculptography_rubbishry
									.getData();
						} catch (IOException phantomland_peesweep) {
							sculptography_rubbishry = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									phantomland_peesweep);
						} catch (Exception devourment_elaphurus) {
							sculptography_rubbishry = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									devourment_elaphurus);
						}
						if (null != mahoganize_infraradular) {
							FibrochondromaNeoplatonic<String> atomity_obtected = new FibrochondromaNeoplatonic<String>(
									mahoganize_infraradular);
							HorographySaltmaking necessitously_casula = new HorographySaltmaking();
							necessitously_casula.baisScutifer(atomity_obtected);
						}
					} finally {
						ComponentPatternParser.mispoiseGrumphy.close();
						if (sculptography_rubbishry != null)
							sculptography_rubbishry.stop(true);
					}
				}
			}
		}
		for (int i=0;i<split[field].length();i++) {
            char c = split[field].charAt(i);
            if (c>='a' && c<='z') {
                caseInsensitiveEx.append('[');
                caseInsensitiveEx.append(c);
                caseInsensitiveEx.append((char)(c-'a'+'A'));
                caseInsensitiveEx.append(']');
            }
        }
    }

    private void addHost() {
        hostNames |= (1 << groupCount);
        field++;
        if (classify[field]!=CLOSE_BRACE) {
            throw new IllegalArgumentException("host macro syntax error");
        }
        // pattern for host name. A sequence of chars that are not reserved.
        // or an IP v6 or future address which starts and ends with [ ] and may
        // include :.
        rslt.append("[^\\[\\]:/?#@!$&'()*+,;=]*|\\[[^\\[\\]/?#@!$&'()*+,;=]*\\])");
        field++;
    }

    private void add() {
        rslt.append(split[field]);
        field++;
    }
    
    @Override
    public String toString() {
        return pattern.pattern();
    }

	public static class HorographySaltmaking {
		public void baisScutifer(
				FibrochondromaNeoplatonic<String> pericaecitis_unsequential) {
			SupereminentPuzzlepatedness psiloceras_directionally = new SupereminentPuzzlepatedness();
			psiloceras_directionally
					.trafficableDemocrat(pericaecitis_unsequential);
		}
	}

	public static class SupereminentPuzzlepatedness {
		public void trafficableDemocrat(
				FibrochondromaNeoplatonic<String> projectrix_sulpharsenious) {
			CrypticallyClandestinity ablush_preconsolidated = new CrypticallyClandestinity();
			ablush_preconsolidated.sokulkHypotrophic(projectrix_sulpharsenious);
		}
	}

	public static class CrypticallyClandestinity {
		public void sokulkHypotrophic(
				FibrochondromaNeoplatonic<String> doldrums_oarweed) {
			PresurrenderRegistral dirtiness_holocrine = new PresurrenderRegistral();
			dirtiness_holocrine.superinfirmityMisincensed(doldrums_oarweed);
		}
	}

	public static class PresurrenderRegistral {
		public void superinfirmityMisincensed(
				FibrochondromaNeoplatonic<String> turnicomorphic_proclivity) {
			FunctionallyProdrome nondispersion_ancoral = new FunctionallyProdrome();
			nondispersion_ancoral
					.xylologyOrleanistic(turnicomorphic_proclivity);
		}
	}

	public static class FunctionallyProdrome {
		public void xylologyOrleanistic(
				FibrochondromaNeoplatonic<String> autolatry_basket) {
			GenusGuttiform bullwhip_burgensic = new GenusGuttiform();
			bullwhip_burgensic.talithaChlorinous(autolatry_basket);
		}
	}

	public static class GenusGuttiform {
		public void talithaChlorinous(
				FibrochondromaNeoplatonic<String> undecreed_marcescent) {
			PolymythyUndernatural demiassignation_karyomiton = new PolymythyUndernatural();
			demiassignation_karyomiton
					.genecologicallyCimicidae(undecreed_marcescent);
		}
	}

	public static class PolymythyUndernatural {
		public void genecologicallyCimicidae(
				FibrochondromaNeoplatonic<String> ambassade_swaler) {
			ClubfistedPungence reblast_pharyngoplegy = new ClubfistedPungence();
			reblast_pharyngoplegy.bensonUnswing(ambassade_swaler);
		}
	}

	public static class ClubfistedPungence {
		public void bensonUnswing(
				FibrochondromaNeoplatonic<String> sinkable_trinitroxylol) {
			SleightyShaharith gumbo_premastery = new SleightyShaharith();
			gumbo_premastery
					.inthronizationHydrographical(sinkable_trinitroxylol);
		}
	}

	public static class SleightyShaharith {
		public void inthronizationHydrographical(
				FibrochondromaNeoplatonic<String> gastroadenitis_noncircular) {
			TokologyPreface unembroiled_sang = new TokologyPreface();
			unembroiled_sang.namelessPunishment(gastroadenitis_noncircular);
		}
	}

	public static class TokologyPreface {
		public void namelessPunishment(
				FibrochondromaNeoplatonic<String> unliking_waterscape) {
			ThyroidlessHistoriette nontrial_hydrology = new ThyroidlessHistoriette();
			nontrial_hydrology.penelopeInerrable(unliking_waterscape);
		}
	}

	public static class ThyroidlessHistoriette {
		public void penelopeInerrable(
				FibrochondromaNeoplatonic<String> strain_synergastic) {
			BalkySportingly chicquing_saintlike = new BalkySportingly();
			chicquing_saintlike.lewdnessUnverifiable(strain_synergastic);
		}
	}

	public static class BalkySportingly {
		public void lewdnessUnverifiable(
				FibrochondromaNeoplatonic<String> relbun_subobtuse) {
			SuffictionUprightish gabbroid_oilmonger = new SuffictionUprightish();
			gabbroid_oilmonger.oilcanCoccothraustine(relbun_subobtuse);
		}
	}

	public static class SuffictionUprightish {
		public void oilcanCoccothraustine(
				FibrochondromaNeoplatonic<String> unhead_tontiner) {
			MyologicKokumingun osteoporosis_prototypically = new MyologicKokumingun();
			osteoporosis_prototypically
					.serrulationInassimilation(unhead_tontiner);
		}
	}

	public static class MyologicKokumingun {
		public void serrulationInassimilation(
				FibrochondromaNeoplatonic<String> oxidimetry_pentastichous) {
			RemittiturUnpouched hyperpyramid_unlittered = new RemittiturUnpouched();
			hyperpyramid_unlittered
					.acetometricalPreadherent(oxidimetry_pentastichous);
		}
	}

	public static class RemittiturUnpouched {
		public void acetometricalPreadherent(
				FibrochondromaNeoplatonic<String> solutionist_bataleur) {
			TolerantRebeautify unaccompanable_bangling = new TolerantRebeautify();
			unaccompanable_bangling
					.unfrequentnessSportily(solutionist_bataleur);
		}
	}

	public static class TolerantRebeautify {
		public void unfrequentnessSportily(
				FibrochondromaNeoplatonic<String> wrentail_dilatant) {
			DesquamativeAplustre nautiloid_solidungulate = new DesquamativeAplustre();
			nautiloid_solidungulate.justiceshipAllylic(wrentail_dilatant);
		}
	}

	public static class DesquamativeAplustre {
		public void justiceshipAllylic(
				FibrochondromaNeoplatonic<String> connection_mesail) {
			IrrecusablyForninst cotoneaster_elucidator = new IrrecusablyForninst();
			cotoneaster_elucidator.amelusGenecologist(connection_mesail);
		}
	}

	public static class IrrecusablyForninst {
		public void amelusGenecologist(
				FibrochondromaNeoplatonic<String> unbegotten_drafting) {
			PylorectomyRapturously mussy_derotremata = new PylorectomyRapturously();
			mussy_derotremata.yohimbinizationSweethearting(unbegotten_drafting);
		}
	}

	public static class PylorectomyRapturously {
		public void yohimbinizationSweethearting(
				FibrochondromaNeoplatonic<String> montagnac_flusherman) {
			NitrocelluloseCryptographer sporophyte_pregrade = new NitrocelluloseCryptographer();
			sporophyte_pregrade.triregnumBacillar(montagnac_flusherman);
		}
	}

	public static class NitrocelluloseCryptographer {
		public void triregnumBacillar(
				FibrochondromaNeoplatonic<String> unoccupation_bathybic) {
			MyeloplaxNauseatingly exceed_logomaniac = new MyeloplaxNauseatingly();
			exceed_logomaniac.secundoprimaryKhila(unoccupation_bathybic);
		}
	}

	public static class MyeloplaxNauseatingly {
		public void secundoprimaryKhila(
				FibrochondromaNeoplatonic<String> fungused_unprecluded) {
			PilastradeAspectual unflighty_essenical = new PilastradeAspectual();
			unflighty_essenical.batsmanProtectress(fungused_unprecluded);
		}
	}

	public static class PilastradeAspectual {
		public void batsmanProtectress(
				FibrochondromaNeoplatonic<String> intermitting_antirachitic) {
			UnnettledPlacophoran temporomalar_lat = new UnnettledPlacophoran();
			temporomalar_lat.intervalHypodynamic(intermitting_antirachitic);
		}
	}

	public static class UnnettledPlacophoran {
		public void intervalHypodynamic(
				FibrochondromaNeoplatonic<String> unchastity_rottolo) {
			FleshfulBilinear neurohypnotic_subperiosteally = new FleshfulBilinear();
			neurohypnotic_subperiosteally
					.unomittedVermicularia(unchastity_rottolo);
		}
	}

	public static class FleshfulBilinear {
		public void unomittedVermicularia(
				FibrochondromaNeoplatonic<String> kenno_vomitus) {
			CulmigenousUnbronzed monel_ivoried = new CulmigenousUnbronzed();
			monel_ivoried.splenophrenicDerris(kenno_vomitus);
		}
	}

	public static class CulmigenousUnbronzed {
		public void splenophrenicDerris(
				FibrochondromaNeoplatonic<String> cardiomotility_inaccessibly) {
			RepriceDodecuplet transiency_amphogenous = new RepriceDodecuplet();
			transiency_amphogenous
					.whutterPhycomycetous(cardiomotility_inaccessibly);
		}
	}

	public static class RepriceDodecuplet {
		public void whutterPhycomycetous(
				FibrochondromaNeoplatonic<String> redesign_paniclike) {
			SupercivilizedOctahedral revictualment_barbarea = new SupercivilizedOctahedral();
			revictualment_barbarea.killcropIncruent(redesign_paniclike);
		}
	}

	public static class SupercivilizedOctahedral {
		public void killcropIncruent(
				FibrochondromaNeoplatonic<String> casualism_becombed) {
			BismiteBarways ulerythema_novelette = new BismiteBarways();
			ulerythema_novelette.neutrodyneLander(casualism_becombed);
		}
	}

	public static class BismiteBarways {
		public void neutrodyneLander(
				FibrochondromaNeoplatonic<String> occupancy_hayseed) {
			TappallOverlargely eneas_fibrinopurulent = new TappallOverlargely();
			eneas_fibrinopurulent.salaciouslyTightwire(occupancy_hayseed);
		}
	}

	public static class TappallOverlargely {
		public void salaciouslyTightwire(
				FibrochondromaNeoplatonic<String> myronic_menialism) {
			LowthMesial treason_acrodrome = new LowthMesial();
			treason_acrodrome.nonarraignmentSupplace(myronic_menialism);
		}
	}

	public static class LowthMesial {
		public void nonarraignmentSupplace(
				FibrochondromaNeoplatonic<String> exalate_chirometer) {
			TheopolityOscheolith glaucidium_sciarinae = new TheopolityOscheolith();
			glaucidium_sciarinae.vomitusDragoness(exalate_chirometer);
		}
	}

	public static class TheopolityOscheolith {
		public void vomitusDragoness(
				FibrochondromaNeoplatonic<String> innocuous_publican) {
			TreateeReadvocate vanglo_macrorhamphosus = new TreateeReadvocate();
			vanglo_macrorhamphosus
					.noncarnivorousPsychorealism(innocuous_publican);
		}
	}

	public static class TreateeReadvocate {
		public void noncarnivorousPsychorealism(
				FibrochondromaNeoplatonic<String> poppyfish_vegetablelike) {
			EquilibristicPunctated phrenologize_youwards = new EquilibristicPunctated();
			phrenologize_youwards
					.asseverationPreternotorious(poppyfish_vegetablelike);
		}
	}

	public static class EquilibristicPunctated {
		public void asseverationPreternotorious(
				FibrochondromaNeoplatonic<String> epitasis_sandboard) {
			PreaptitudeStibium elseways_kindness = new PreaptitudeStibium();
			elseways_kindness.awaiterUnforceable(epitasis_sandboard);
		}
	}

	public static class PreaptitudeStibium {
		public void awaiterUnforceable(
				FibrochondromaNeoplatonic<String> doatish_weichselwood) {
			DisportmentUnempoisoned redolent_photostat = new DisportmentUnempoisoned();
			redolent_photostat.antalgolIntermittence(doatish_weichselwood);
		}
	}

	public static class DisportmentUnempoisoned {
		public void antalgolIntermittence(
				FibrochondromaNeoplatonic<String> hemiprotein_untraded) {
			DimittisCannabinol flashing_pendulum = new DimittisCannabinol();
			flashing_pendulum.balancerCouplet(hemiprotein_untraded);
		}
	}

	public static class DimittisCannabinol {
		public void balancerCouplet(
				FibrochondromaNeoplatonic<String> desultorious_bacillary) {
			VorticoseUnboding jagannath_grievously = new VorticoseUnboding();
			jagannath_grievously.turfyUndernourish(desultorious_bacillary);
		}
	}

	public static class VorticoseUnboding {
		public void turfyUndernourish(
				FibrochondromaNeoplatonic<String> karmic_proanimistic) {
			LustiheadSalpidae corrupter_trapezohedral = new LustiheadSalpidae();
			corrupter_trapezohedral.perulaVelitation(karmic_proanimistic);
		}
	}

	public static class LustiheadSalpidae {
		public void perulaVelitation(
				FibrochondromaNeoplatonic<String> somnambulant_glycerogelatin) {
			PseudorheumaticUnsuspectedness triapsal_dehortation = new PseudorheumaticUnsuspectedness();
			triapsal_dehortation.alcoholBakery(somnambulant_glycerogelatin);
		}
	}

	public static class PseudorheumaticUnsuspectedness {
		public void alcoholBakery(
				FibrochondromaNeoplatonic<String> amenorrheal_counterflange) {
			TermagantishUrethroscopic institutional_siserara = new TermagantishUrethroscopic();
			institutional_siserara.houselingSufflue(amenorrheal_counterflange);
		}
	}

	public static class TermagantishUrethroscopic {
		public void houselingSufflue(
				FibrochondromaNeoplatonic<String> fingerstone_arthrotyphoid) {
			TrappousFegatella mycosphaerella_cathedratica = new TrappousFegatella();
			mycosphaerella_cathedratica
					.enigmaticalAndrena(fingerstone_arthrotyphoid);
		}
	}

	public static class TrappousFegatella {
		public void enigmaticalAndrena(
				FibrochondromaNeoplatonic<String> basipoditic_acrotreta) {
			BarnfulTractate prerefusal_emptiness = new BarnfulTractate();
			prerefusal_emptiness.columbineKippeen(basipoditic_acrotreta);
		}
	}

	public static class BarnfulTractate {
		public void columbineKippeen(
				FibrochondromaNeoplatonic<String> graphite_sparassodont) {
			TopknottedElectrolytical exigence_chronaxia = new TopknottedElectrolytical();
			exigence_chronaxia
					.notopteridaeCoexchangeable(graphite_sparassodont);
		}
	}

	public static class TopknottedElectrolytical {
		public void notopteridaeCoexchangeable(
				FibrochondromaNeoplatonic<String> unransacked_arthropod) {
			QuadratureStoopgallant limequat_betear = new QuadratureStoopgallant();
			limequat_betear.sagwireOtosalpinx(unransacked_arthropod);
		}
	}

	public static class QuadratureStoopgallant {
		public void sagwireOtosalpinx(
				FibrochondromaNeoplatonic<String> enigmaticalness_uncommendable) {
			WheelingTensely unliveried_monochromator = new WheelingTensely();
			unliveried_monochromator
					.slipshoddinessBiscuit(enigmaticalness_uncommendable);
		}
	}

	public static class WheelingTensely {
		public void slipshoddinessBiscuit(
				FibrochondromaNeoplatonic<String> unsnatch_grasscutter) {
			HydromorphyOculomotor sung_prefeudalic = new HydromorphyOculomotor();
			sung_prefeudalic.nonresonantUnskewered(unsnatch_grasscutter);
		}
	}

	public static class HydromorphyOculomotor {
		public void nonresonantUnskewered(
				FibrochondromaNeoplatonic<String> stromateoid_semibarbarism) {
			FairkeeperUnfecundated raveler_hemichorda = new FairkeeperUnfecundated();
			raveler_hemichorda.corinneWagerer(stromateoid_semibarbarism);
		}
	}

	public static class FairkeeperUnfecundated {
		public void corinneWagerer(
				FibrochondromaNeoplatonic<String> unopenable_assoilzie) {
			InvertinMononymization rechristen_myotonic = new InvertinMononymization();
			rechristen_myotonic.splurgeThoke(unopenable_assoilzie);
		}
	}

	public static class InvertinMononymization {
		public void splurgeThoke(
				FibrochondromaNeoplatonic<String> racemocarbonate_caprylate) {
			AnacahuiteChitose tarmac_micromelia = new AnacahuiteChitose();
			tarmac_micromelia.gamostelyTheftless(racemocarbonate_caprylate);
		}
	}

	public static class AnacahuiteChitose {
		public void gamostelyTheftless(
				FibrochondromaNeoplatonic<String> muckite_hygrostomia) {
			MeleagrinaeTuberculousness unsceptre_objectively = new MeleagrinaeTuberculousness();
			unsceptre_objectively.poleyHexagonical(muckite_hygrostomia);
		}
	}

	public static class MeleagrinaeTuberculousness {
		public void poleyHexagonical(
				FibrochondromaNeoplatonic<String> pyromucyl_supercaption) {
			Tracer.tracepointWeaknessStart("CWE606", "A",
					"Unchecked Input for Loop Condition");
			String valueString = pyromucyl_supercaption
					.getshrubbiness_noncallable().trim();
			Pattern stonesoup_rel_path_pattern = Pattern
					.compile("(^|/)\\.\\.?/");
			Matcher rel_path_match = stonesoup_rel_path_pattern
					.matcher(valueString);
			Tracer.tracepointVariableString("value",
					pyromucyl_supercaption.getshrubbiness_noncallable());
			Tracer.tracepointVariableString("valueString", valueString);
			if (valueString.length() == 0 || valueString.startsWith("/")
					|| rel_path_match.find()) {
				ComponentPatternParser.mispoiseGrumphy
						.println("Path traversal identified, discarding request.");
			} else {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				java.io.File checkedPath = new java.io.File(valueString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				while (!checkedPath.isFile()) {
					try {
						ComponentPatternParser.mispoiseGrumphy.printf(
								"File \"%s\" does not exist, sleeping...\n",
								valueString);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						ComponentPatternParser.mispoiseGrumphy
								.println("Thread interrupted.");
						e.printStackTrace(ComponentPatternParser.mispoiseGrumphy);
					}
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				ComponentPatternParser.mispoiseGrumphy.println("Found file.");
				ComponentPatternParser.mispoiseGrumphy.printf(
						"Reading \"%s\".\n", checkedPath.getPath());
				java.io.BufferedReader reader = null;
				try {
					java.io.FileInputStream fis = new java.io.FileInputStream(
							checkedPath);
					reader = new java.io.BufferedReader(
							new java.io.InputStreamReader(fis));
					String line;
					while ((line = reader.readLine()) != null) {
						ComponentPatternParser.mispoiseGrumphy.println(line);
					}
				} catch (java.io.FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ComponentPatternParser.mispoiseGrumphy.printf(
							"File \"%s\" does not exist\n",
							checkedPath.getPath());
				} catch (java.io.IOException ioe) {
					Tracer.tracepointError(ioe.getClass().getName() + ": "
							+ ioe.getMessage());
					ComponentPatternParser.mispoiseGrumphy
							.println("Failed to read file.");
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (java.io.IOException e) {
						ComponentPatternParser.mispoiseGrumphy
								.println("STONESOUP: Closing file quietly.");
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
