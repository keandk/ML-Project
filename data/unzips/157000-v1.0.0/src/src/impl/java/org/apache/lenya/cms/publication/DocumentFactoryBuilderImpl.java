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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    public class LabbaBubo<T> {
		private T predikant_lightfulness;

		public LabbaBubo(T predikant_lightfulness) {
			this.predikant_lightfulness = predikant_lightfulness;
		}

		public T getpredikant_lightfulness() {
			return this.predikant_lightfulness;
		}
	}

	static PrintStream prosecretinScoinson = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean chaetognathPicturedrome = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (chaetognathPicturedrome.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp7cgK3c_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			String agnatic_fearfulness = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (agnatic_fearfulness == null || !agnatic_fearfulness.equals("1")) {
				StonesoupSourceHttpServer deplorer_twicet = null;
				PipedOutputStream rheocratSabaeanism = new PipedOutputStream();
				try {
					DocumentFactoryBuilderImpl.prosecretinScoinson = new PrintStream(
							rheocratSabaeanism, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException etaballiAlginic) {
					System.err.printf("Failed to open log file.  %s\n",
							etaballiAlginic.getMessage());
					DocumentFactoryBuilderImpl.prosecretinScoinson = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							etaballiAlginic);
				}
				if (DocumentFactoryBuilderImpl.prosecretinScoinson != null) {
					try {
						String phytomastigina_rebop;
						try {
							deplorer_twicet = new StonesoupSourceHttpServer(
									8887, rheocratSabaeanism);
							deplorer_twicet.start();
							phytomastigina_rebop = deplorer_twicet.getData();
						} catch (IOException annulment_overattachment) {
							deplorer_twicet = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									annulment_overattachment);
						} catch (Exception pronaval_lyceum) {
							deplorer_twicet = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									pronaval_lyceum);
						}
						if (null != phytomastigina_rebop) {
							int unvarnishedly_conyrine;
							try {
								unvarnishedly_conyrine = Integer
										.parseInt(phytomastigina_rebop);
							} catch (NumberFormatException artilleryman_melophonist) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										artilleryman_melophonist);
							}
							Object upmost_peponida = unvarnishedly_conyrine;
							LabbaBubo<Object> septarian_chattertonian = new LabbaBubo<Object>(
									upmost_peponida);
							try {
								String fluotitanate_clown = System
										.getProperty("os.name");
								if (null != fluotitanate_clown) {
									if (!fluotitanate_clown
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException taoism_porteacid) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE606", "B",
										"Uncheck Input for Loop Condition");
								char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
										.toCharArray();
								SecureRandom random = null;
								try {
									random = SecureRandom
											.getInstance("SHA1PRNG");
								} catch (NoSuchAlgorithmException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									DocumentFactoryBuilderImpl.prosecretinScoinson
											.println("STONESOUP: Random generator algorithm does not exist.");
								}
								Tracer.tracepointVariableInt("value",
										((Integer) septarian_chattertonian
												.getpredikant_lightfulness()));
								if (random != null) {
									StringBuilder stonesoup_filename = new StringBuilder();
									DocumentFactoryBuilderImpl.prosecretinScoinson
											.println("Generating file name");
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) septarian_chattertonian
											.getpredikant_lightfulness()); stonesoup_counter++) {
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
											DocumentFactoryBuilderImpl.prosecretinScoinson
													.println("Failed to create file.");
											DocumentFactoryBuilderImpl.prosecretinScoinson
													.println("Error:");
											e.printStackTrace(DocumentFactoryBuilderImpl.prosecretinScoinson);
											throw new RuntimeException(
													"Unknown error in filename.",
													e);
										}
										FileOutputStream writeStream = null;
										PrintStream writer = null;
										try {
											writeStream = new FileOutputStream(
													writePath, false);
											writer = new PrintStream(
													writeStream);
											writer.println("/* This is my file */");
										} catch (FileNotFoundException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											DocumentFactoryBuilderImpl.prosecretinScoinson
													.println("Failed to create file.");
											e.printStackTrace(DocumentFactoryBuilderImpl.prosecretinScoinson);
										} finally {
											if (writer != null) {
												writer.close();
											}
										}
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.prosecretinScoinson.close();
						if (deplorer_twicet != null)
							deplorer_twicet.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
