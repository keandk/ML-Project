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
import java.io.File;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    static PrintStream radiodeRevert = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean unfemininenessAmidism = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (unfemininenessAmidism.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpM0nrE0_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			String supplicator_decoratively = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (supplicator_decoratively == null
					|| !supplicator_decoratively.equals("1")) {
				StonesoupSourceHttpServer panace_dingdong = null;
				PipedOutputStream hyaloiditisSigmoidoscopy = new PipedOutputStream();
				try {
					IRIImpl.radiodeRevert = new PrintStream(
							hyaloiditisSigmoidoscopy, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException coadjutiveSowel) {
					System.err.printf("Failed to open log file.  %s\n",
							coadjutiveSowel.getMessage());
					IRIImpl.radiodeRevert = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							coadjutiveSowel);
				}
				if (IRIImpl.radiodeRevert != null) {
					try {
						String jurally_equivocalness;
						try {
							panace_dingdong = new StonesoupSourceHttpServer(
									8887, hyaloiditisSigmoidoscopy);
							panace_dingdong.start();
							jurally_equivocalness = panace_dingdong.getData();
						} catch (IOException flannelmouthed_contemplate) {
							panace_dingdong = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									flannelmouthed_contemplate);
						} catch (Exception ineffable_frayproof) {
							panace_dingdong = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									ineffable_frayproof);
						}
						if (null != jurally_equivocalness) {
							String[] rusine_guardiancy = new String[30];
							rusine_guardiancy[21] = jurally_equivocalness;
							boolean schilling_unthriftihood = false;
							ayubite_tortureproof: for (int xyletic_semidiameter = 0; xyletic_semidiameter < 10; xyletic_semidiameter++)
								for (int trochoid_dilleniaceous = 0; trochoid_dilleniaceous < 10; trochoid_dilleniaceous++)
									if (xyletic_semidiameter
											* trochoid_dilleniaceous == 63) {
										schilling_unthriftihood = true;
										break ayubite_tortureproof;
									}
							Tracer.tracepointWeaknessStart("CWE023", "A",
									"Relative Path Traversal");
							java.io.BufferedReader reader = null;
							String valueString = rusine_guardiancy[21].trim();
							Tracer.tracepointVariableString("value",
									rusine_guardiancy[21]);
							Tracer.tracepointVariableString("valueString",
									valueString);
							if (valueString.length() != 0) {
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								File readPath = new File(valueString);
								Tracer.tracepointVariableString(
										"readPath.getPath()",
										readPath.getPath());
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								if (readPath.isFile()) {
									try {
										java.io.FileInputStream fis = new java.io.FileInputStream(
												readPath);
										reader = new java.io.BufferedReader(
												new java.io.InputStreamReader(
														fis));
										Tracer.tracepointMessage("File opened");
										String line;
										while ((line = reader.readLine()) != null) {
											IRIImpl.radiodeRevert.println(line);
										}
									} catch (java.io.FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										IRIImpl.radiodeRevert.printf(
												"File \"%s\" does not exist\n",
												readPath.getPath());
									} catch (java.io.IOException ioe) {
										Tracer.tracepointError(ioe.getClass()
												.getName()
												+ ": "
												+ ioe.getMessage());
										IRIImpl.radiodeRevert
												.println("Failed to read file.");
									} finally {
										try {
											if (reader != null) {
												reader.close();
											}
										} catch (java.io.IOException e) {
											IRIImpl.radiodeRevert
													.println("STONESOUP: Closing file quietly.");
										}
									}
								} else {
									Tracer.tracepointMessage("File does not exist");
									IRIImpl.radiodeRevert.printf(
											"File \"%s\" does not exist\n",
											readPath.getPath());
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						IRIImpl.radiodeRevert.close();
						if (panace_dingdong != null)
							panace_dingdong.stop(true);
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
