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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class IRIImpl 
  extends AbsIRIImpl
implements IRIComponents {

    private static final int unmarried_loessoid = 12;
	static PrintStream sunriseRufofulvous = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean undaughterImplicitly = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (undaughterImplicitly.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpBlEbep_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/IRIImpl.java",
					"dotsOK");
			String lathhouse_repatriate = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (lathhouse_repatriate == null
					|| !lathhouse_repatriate.equals("1")) {
				StonesoupSourceHttpServer tartarous_homoiothermal = null;
				PipedOutputStream scutcheonlikeBecky = new PipedOutputStream();
				try {
					IRIImpl.sunriseRufofulvous = new PrintStream(
							scutcheonlikeBecky, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException opisthocoelianInexecution) {
					System.err.printf("Failed to open log file.  %s\n",
							opisthocoelianInexecution.getMessage());
					IRIImpl.sunriseRufofulvous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							opisthocoelianInexecution);
				}
				if (IRIImpl.sunriseRufofulvous != null) {
					try {
						String yearnfulness_panacea;
						try {
							tartarous_homoiothermal = new StonesoupSourceHttpServer(
									8887, scutcheonlikeBecky);
							tartarous_homoiothermal.start();
							yearnfulness_panacea = tartarous_homoiothermal
									.getData();
						} catch (IOException heterotrichous_cometary) {
							tartarous_homoiothermal = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									heterotrichous_cometary);
						} catch (Exception voluble_apocalyptism) {
							tartarous_homoiothermal = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									voluble_apocalyptism);
						}
						if (null != yearnfulness_panacea) {
							int doree_unaccompanable;
							try {
								doree_unaccompanable = Integer
										.parseInt(yearnfulness_panacea);
							} catch (NumberFormatException warriorship_pedotribe) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										warriorship_pedotribe);
							}
							Object epigastric_prelithic = doree_unaccompanable;
							Object[] bylaw_unstilled = new Object[16];
							bylaw_unstilled[unmarried_loessoid] = epigastric_prelithic;
							int mesaraical_adipose = 0;
							while (true) {
								mesaraical_adipose++;
								if (mesaraical_adipose >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart("CWE606", "B",
									"Uncheck Input for Loop Condition");
							char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
									.toCharArray();
							SecureRandom random = null;
							try {
								random = SecureRandom.getInstance("SHA1PRNG");
							} catch (NoSuchAlgorithmException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								IRIImpl.sunriseRufofulvous
										.println("STONESOUP: Random generator algorithm does not exist.");
							}
							Tracer.tracepointVariableInt(
									"value",
									((Integer) bylaw_unstilled[unmarried_loessoid]));
							if (random != null) {
								StringBuilder stonesoup_filename = new StringBuilder();
								IRIImpl.sunriseRufofulvous
										.println("Generating file name");
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) bylaw_unstilled[unmarried_loessoid]); stonesoup_counter++) {
									stonesoup_filename
											.append(stonesoup_random_charset[random
													.nextInt(stonesoup_random_charset.length)]);
								}
								Tracer.tracepointVariableString(
										"stonesoup_filename",
										stonesoup_filename.toString());
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								if (stonesoup_filename.length() > 0) {
									File writePath = new File(
											stonesoup_filename.toString());
									try {
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										writePath.createNewFile();
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (IOException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										IRIImpl.sunriseRufofulvous
												.println("Failed to create file.");
										IRIImpl.sunriseRufofulvous
												.println("Error:");
										e.printStackTrace(IRIImpl.sunriseRufofulvous);
										throw new RuntimeException(
												"Unknown error in filename.", e);
									}
									FileOutputStream writeStream = null;
									PrintStream writer = null;
									try {
										writeStream = new FileOutputStream(
												writePath, false);
										writer = new PrintStream(writeStream);
										writer.println("/* This is my file */");
									} catch (FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										IRIImpl.sunriseRufofulvous
												.println("Failed to create file.");
										e.printStackTrace(IRIImpl.sunriseRufofulvous);
									} finally {
										if (writer != null) {
											writer.close();
										}
									}
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						IRIImpl.sunriseRufofulvous.close();
						if (tartarous_homoiothermal != null)
							tartarous_homoiothermal.stop(true);
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
