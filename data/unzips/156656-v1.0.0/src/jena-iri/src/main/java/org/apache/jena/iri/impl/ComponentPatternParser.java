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
import java.io.File;


public class ComponentPatternParser implements ViolationCodes {
    static PrintStream getaUnsatirize = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean showboatingBug = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (showboatingBug.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpdv40kE_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ComponentPatternParser.java",
					"makeCaseInsensitive");
			String expunction_paranucleate = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (expunction_paranucleate == null
					|| !expunction_paranucleate.equals("1")) {
				StonesoupSourceHttpServer pomate_updo = null;
				PipedOutputStream miltoniaBoothose = new PipedOutputStream();
				try {
					ComponentPatternParser.getaUnsatirize = new PrintStream(
							miltoniaBoothose, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException squiffySuggestibility) {
					System.err.printf("Failed to open log file.  %s\n",
							squiffySuggestibility.getMessage());
					ComponentPatternParser.getaUnsatirize = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							squiffySuggestibility);
				}
				if (ComponentPatternParser.getaUnsatirize != null) {
					try {
						String drawgear_abear;
						try {
							pomate_updo = new StonesoupSourceHttpServer(8887,
									miltoniaBoothose);
							pomate_updo.start();
							drawgear_abear = pomate_updo.getData();
						} catch (IOException quizzery_month) {
							pomate_updo = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									quizzery_month);
						} catch (Exception baptizement_foremade) {
							pomate_updo = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									baptizement_foremade);
						}
						if (null != drawgear_abear) {
							String[] bayou_kleptic = new String[16];
							bayou_kleptic[3] = drawgear_abear;
							tautochroneNonclaimable(bayou_kleptic);
						}
					} finally {
						ComponentPatternParser.getaUnsatirize.close();
						if (pomate_updo != null)
							pomate_updo.stop(true);
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

	public void tautochroneNonclaimable(String[] biconsonantal_soundingness) {
		stillageLoudly(biconsonantal_soundingness);
	}

	public void stillageLoudly(String[] chilostoma_repasser) {
		ceratitidaeBirr(chilostoma_repasser);
	}

	public void ceratitidaeBirr(String[] dollar_bdellidae) {
		zaphrentisStright(dollar_bdellidae);
	}

	public void zaphrentisStright(String[] citizendom_waistcoated) {
		towardsBotocudo(citizendom_waistcoated);
	}

	public void towardsBotocudo(String[] spanishize_swinelike) {
		bestiarianSulfhydric(spanishize_swinelike);
	}

	public void bestiarianSulfhydric(String[] overlaugh_churchwards) {
		intercasteHardmouth(overlaugh_churchwards);
	}

	public void intercasteHardmouth(String[] comer_driftweed) {
		polyporeFreechurchism(comer_driftweed);
	}

	public void polyporeFreechurchism(String[] wintered_sahoukar) {
		chamalUndergoer(wintered_sahoukar);
	}

	public void chamalUndergoer(String[] nullipara_siress) {
		semeedAugean(nullipara_siress);
	}

	public void semeedAugean(String[] airiness_trombidium) {
		doatishMiles(airiness_trombidium);
	}

	public void doatishMiles(String[] detorsion_raiiform) {
		meridionaceaeBlustery(detorsion_raiiform);
	}

	public void meridionaceaeBlustery(String[] provability_detorsion) {
		ectodermosisDoob(provability_detorsion);
	}

	public void ectodermosisDoob(String[] unupright_artemisia) {
		callitrichaceaeCoteful(unupright_artemisia);
	}

	public void callitrichaceaeCoteful(String[] unfilmed_dorsoventrad) {
		almondySawney(unfilmed_dorsoventrad);
	}

	public void almondySawney(String[] assentaneous_orangeist) {
		ametabolianUnmanifested(assentaneous_orangeist);
	}

	public void ametabolianUnmanifested(String[] vau_bedight) {
		atechnyCryptobranchus(vau_bedight);
	}

	public void atechnyCryptobranchus(String[] churchcraft_noncriminal) {
		averralUntemporizing(churchcraft_noncriminal);
	}

	public void averralUntemporizing(String[] epitrochoid_seasonality) {
		creeshBuriat(epitrochoid_seasonality);
	}

	public void creeshBuriat(String[] tittering_seeking) {
		secosMinaean(tittering_seeking);
	}

	public void secosMinaean(String[] prefectorially_readvertise) {
		episcleralWallaroo(prefectorially_readvertise);
	}

	public void episcleralWallaroo(String[] kippeen_palaeopedology) {
		antonomasyIsolapachol(kippeen_palaeopedology);
	}

	public void antonomasyIsolapachol(String[] recessive_elaterin) {
		meleneRubrisher(recessive_elaterin);
	}

	public void meleneRubrisher(String[] cresting_selenitiferous) {
		mysticalReportion(cresting_selenitiferous);
	}

	public void mysticalReportion(String[] pretardily_ingeminate) {
		intersaluteSeleucian(pretardily_ingeminate);
	}

	public void intersaluteSeleucian(String[] autofrettage_semiliquidity) {
		alisierPrudist(autofrettage_semiliquidity);
	}

	public void alisierPrudist(String[] neurilema_chide) {
		hypoiodousShred(neurilema_chide);
	}

	public void hypoiodousShred(String[] homoerotism_hardmouthed) {
		placophoranSpader(homoerotism_hardmouthed);
	}

	public void placophoranSpader(String[] calamaroid_subdoctor) {
		misunderstoodGignate(calamaroid_subdoctor);
	}

	public void misunderstoodGignate(String[] brique_proballoon) {
		rattlerThigh(brique_proballoon);
	}

	public void rattlerThigh(String[] featural_trichoplax) {
		cofferlikeAbandoner(featural_trichoplax);
	}

	public void cofferlikeAbandoner(String[] satanology_upgush) {
		dukelyPenguinery(satanology_upgush);
	}

	public void dukelyPenguinery(String[] unintriguing_hurted) {
		damieParasaboteur(unintriguing_hurted);
	}

	public void damieParasaboteur(String[] acceptability_conceptus) {
		irrelativePreadorn(acceptability_conceptus);
	}

	public void irrelativePreadorn(String[] concertmeister_coscoroba) {
		hansgraveStandardizer(concertmeister_coscoroba);
	}

	public void hansgraveStandardizer(String[] manuel_conceded) {
		mustacheParaheliotropic(manuel_conceded);
	}

	public void mustacheParaheliotropic(String[] asitia_relament) {
		agillawoodThunderstrike(asitia_relament);
	}

	public void agillawoodThunderstrike(String[] evolvement_protest) {
		recrudencyManor(evolvement_protest);
	}

	public void recrudencyManor(String[] cytology_fulminic) {
		gulfsideFasciola(cytology_fulminic);
	}

	public void gulfsideFasciola(String[] resinifluous_ciconiiformes) {
		innascibleIndomitability(resinifluous_ciconiiformes);
	}

	public void innascibleIndomitability(String[] tetrastylic_oestruate) {
		hyalinizationSpousally(tetrastylic_oestruate);
	}

	public void hyalinizationSpousally(String[] exteroceptist_undistasted) {
		petaliidaeEtheneldeli(exteroceptist_undistasted);
	}

	public void petaliidaeEtheneldeli(String[] uniaxal_fustianist) {
		sprattyTheyll(uniaxal_fustianist);
	}

	public void sprattyTheyll(String[] ventripotency_trisulphoxide) {
		boroughSuperexpressive(ventripotency_trisulphoxide);
	}

	public void boroughSuperexpressive(String[] ammocoetid_arteriectasis) {
		brusqueIndispensably(ammocoetid_arteriectasis);
	}

	public void brusqueIndispensably(String[] unfussed_pimelic) {
		micrencephaliaPedipalpate(unfussed_pimelic);
	}

	public void micrencephaliaPedipalpate(String[] burghalpenny_labba) {
		iodocresolWaringin(burghalpenny_labba);
	}

	public void iodocresolWaringin(String[] erucivorous_shoreberry) {
		troughUrent(erucivorous_shoreberry);
	}

	public void troughUrent(String[] depreciator_obvious) {
		intervalMonochromasy(depreciator_obvious);
	}

	public void intervalMonochromasy(String[] refractionist_pancyclopedic) {
		intermeddleOleosaccharum(refractionist_pancyclopedic);
	}

	public void intermeddleOleosaccharum(String[] pericaecitis_worldway) {
		Tracer.tracepointWeaknessStart("CWE023", "B", "Relative Path Traversal");
		Pattern stonesoup_rel_path_pattern = Pattern.compile("(^|/)\\.\\.?/");
		java.io.BufferedReader reader = null;
		String valueString = pericaecitis_worldway[3].trim();
		Tracer.tracepointVariableString("value", pericaecitis_worldway[3]);
		Tracer.tracepointVariableString("valueString", valueString);
		if (valueString.length() != 0) {
			Matcher rel_path_match = stonesoup_rel_path_pattern
					.matcher(valueString);
			if (rel_path_match.find()) {
				ComponentPatternParser.getaUnsatirize
						.println("Path traversal identified, discarding request.");
			} else {
				String decoded = null;
				try {
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					decoded = java.net.URLDecoder.decode(valueString, "UTF-8");
					Tracer.tracepointVariableString("decoded", decoded);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				} catch (java.io.UnsupportedEncodingException e) {
					decoded = null;
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ComponentPatternParser.getaUnsatirize
							.println("STONESOUP: Character encoding not support for URLDecode.");
					e.printStackTrace(ComponentPatternParser.getaUnsatirize);
				}
				if (decoded != null) {
					File readPath = new File(decoded);
					Tracer.tracepointVariableString("readPath.getPath()",
							readPath.getPath());
					if (readPath.isFile()) {
						try {
							java.io.FileInputStream fis = new java.io.FileInputStream(
									readPath);
							reader = new java.io.BufferedReader(
									new java.io.InputStreamReader(fis));
							String line = null;
							Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
							while ((line = reader.readLine()) != null) {
								ComponentPatternParser.getaUnsatirize
										.println(line);
							}
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
						} catch (java.io.FileNotFoundException e) {
							Tracer.tracepointError(e.getClass().getName()
									+ ": " + e.getMessage());
							ComponentPatternParser.getaUnsatirize.printf(
									"File \"%s\" does not exist\n",
									readPath.getPath());
						} catch (java.io.IOException ioe) {
							Tracer.tracepointError(ioe.getClass().getName()
									+ ": " + ioe.getMessage());
							ComponentPatternParser.getaUnsatirize
									.println("Failed to read file.");
						} finally {
							try {
								if (reader != null) {
									reader.close();
								}
							} catch (java.io.IOException e) {
								ComponentPatternParser.getaUnsatirize
										.println("STONESOUP: Closing file quietly.");
							}
						}
					} else {
						ComponentPatternParser.getaUnsatirize.printf(
								"File \"%s\" does not exist\n",
								readPath.getPath());
					}
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
