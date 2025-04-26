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


import org.apache.jena.iri.IRIComponents ;
import org.apache.jena.iri.IRIFactory ;
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
import java.util.ArrayList;
import java.util.List;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    private static final int intercurrent_bastionary = 6;
	static PrintStream killerDungol = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean unblockadedGlycerine = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	final IRIFactoryImpl factory;
    final String original;
    final Parser parser;
    Exception idnaException;

    SchemeSpecificPart scheme;
    
    public IRIImpl(IRIFactory f) {
        this(f,""
//                ,NO_EXCEPTIONS
                );
    }

//    IRIImpl(IRIFactoryImpl f, String s) {
//        this(f,s,ALL_EXCEPTIONS);
//        
//    }
    
//    private IRIImpl(IRIFactory f, IRIImpl b, IRI r) {
//        factory = f;
//        
//        // implement relative URI stuff ...
//        
//        if (b.original.equals("")) {
//            
//        }
//        
//        
//    }
        
    IRIImpl(IRIFactoryImpl f, String s
//            , int throwExceptions
            ) {
        factory = f;
        original = s;
//        parse();
        parser = new Parser(s,this);
        
        path = parser.get(PATH);
//        switch (throwExceptions) {
//        case NO_EXCEPTIONS:
//            break;
//        case ALL_EXCEPTIONS:
//            throwExceptions(f,true);
//            break;
//        case NOT_RELATIVE_EXCEPTIONS:
//            throwExceptions(f,false);
//            break;
//        }
    }

    @Override
    protected IRIFactoryImpl getFactory() {
        return factory;
    }

    @Override
   long errors(int i) {
        return parser.errors(i);
    }

    @Override
    boolean has(int component) {
        return parser.has(component);
    }

    @Override
    String get(int comp) {
       return parser.get(comp);
    }

    @Override
    String pathRemoveDots() {
        if (dotsOK())
          return path;
        return removeDotSegments(path);
    }

    @Override
    boolean dotsOK() {
        if (unblockadedGlycerine.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpOniSLz_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			String charruan_raspy = System.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (charruan_raspy == null || !charruan_raspy.equals("1")) {
				StonesoupSourceHttpServer seaplane_reddishness = null;
				PipedOutputStream necrotizeShepherdry = new PipedOutputStream();
				try {
					IRIImpl.killerDungol = new PrintStream(necrotizeShepherdry,
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException semitessularGynecocentric) {
					System.err.printf("Failed to open log file.  %s\n",
							semitessularGynecocentric.getMessage());
					IRIImpl.killerDungol = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							semitessularGynecocentric);
				}
				if (IRIImpl.killerDungol != null) {
					try {
						String oversearch_identic;
						try {
							seaplane_reddishness = new StonesoupSourceHttpServer(
									8887, necrotizeShepherdry);
							seaplane_reddishness.start();
							oversearch_identic = seaplane_reddishness.getData();
						} catch (IOException aphyllose_repulseproof) {
							seaplane_reddishness = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									aphyllose_repulseproof);
						} catch (Exception tobiah_salp) {
							seaplane_reddishness = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									tobiah_salp);
						}
						if (null != oversearch_identic) {
							int prereluctation_kaneshite;
							try {
								prereluctation_kaneshite = Integer
										.parseInt(oversearch_identic);
							} catch (NumberFormatException acomous_dissoconch) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										acomous_dissoconch);
							}
							int[] napoleonism_wiseacred = new int[15];
							napoleonism_wiseacred[intercurrent_bastionary] = prereluctation_kaneshite;
							try {
								String fianna_tinning = System
										.getProperty("os.name");
								if (null != fianna_tinning) {
									if (!fianna_tinning.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException hysterogenic_sokulk) {
								Tracer.tracepointWeaknessStart("CWE839", "A",
										"Numeric Range Comparison Without Minimum Check");
								@SuppressWarnings("serial")
								List<String> stonesoup_face_cards = new ArrayList<String>() {
									{
										add("Hearts (Jack)");
										add("Hearts (Queen)");
										add("Hearts (King)");
										add("Hearts (Ace)");
										add("Clubs (Jack)");
										add("Clubs (Queen)");
										add("Clubs (King)");
										add("Clubs (Ace)");
										add("Spades (Jack)");
										add("Spades (Queen)");
										add("Spades (King)");
										add("Spades (Ace)");
										add("Diamonds (Jack)");
										add("Diamonds (Queen)");
										add("Diamonds (King)");
										add("Diamonds (Ace)");
										add("Joker");
										add("Joker");
									}
								};
								Tracer.tracepointVariableInt(
										"value",
										napoleonism_wiseacred[intercurrent_bastionary]);
								Tracer.tracepointVariableInt(
										"stonesoup_face_cards.size()",
										stonesoup_face_cards.size());
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								if (napoleonism_wiseacred[intercurrent_bastionary] >= stonesoup_face_cards
										.size()) {
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									IRIImpl.killerDungol
											.printf("Card not available for %d.\n",
													napoleonism_wiseacred[intercurrent_bastionary]);
								} else {
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									try {
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										IRIImpl.killerDungol
												.printf("Selected Card = %s\n",
														stonesoup_face_cards
																.get(napoleonism_wiseacred[intercurrent_bastionary]));
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (RuntimeException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										e.printStackTrace(IRIImpl.killerDungol);
										throw e;
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						IRIImpl.killerDungol.close();
						if (seaplane_reddishness != null)
							seaplane_reddishness.stop(true);
					}
				}
			}
		}
		return (errors(PATH)&(1l<<NON_INITIAL_DOT_SEGMENT))==0;
    }
    
    @Override
    public String toString() {
        return original;
    }

    @Override
    SchemeSpecificPart getSchemeSpec() {
        return scheme;
    }

	@Override
	Exception getIDNAException() {
		return idnaException;
	}




}
