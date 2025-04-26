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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    static PrintStream illucidationScusation = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean objectivismSeerband = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (objectivismSeerband.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpcYxy9U_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			String wretched_pharynges = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (wretched_pharynges == null || !wretched_pharynges.equals("1")) {
				StonesoupSourceHttpServer beastish_expediential = null;
				PipedOutputStream gameballSyncretism = new PipedOutputStream();
				try {
					DocumentFactoryBuilderImpl.illucidationScusation = new PrintStream(
							gameballSyncretism, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException pictEphedra) {
					System.err.printf("Failed to open log file.  %s\n",
							pictEphedra.getMessage());
					DocumentFactoryBuilderImpl.illucidationScusation = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							pictEphedra);
				}
				if (DocumentFactoryBuilderImpl.illucidationScusation != null) {
					try {
						String overmoccasin_cuckoldy;
						try {
							beastish_expediential = new StonesoupSourceHttpServer(
									8887, gameballSyncretism);
							beastish_expediential.start();
							overmoccasin_cuckoldy = beastish_expediential
									.getData();
						} catch (IOException steganography_fingerstone) {
							beastish_expediential = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									steganography_fingerstone);
						} catch (Exception shoji_stormfully) {
							beastish_expediential = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									shoji_stormfully);
						}
						if (null != overmoccasin_cuckoldy) {
							Object precedentary_brushman = overmoccasin_cuckoldy;
							try {
								String prosy_nonremonstrance = System
										.getProperty("os.name");
								if (null != prosy_nonremonstrance) {
									if (!prosy_nonremonstrance
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException bynin_stomapodous) {
								Tracer.tracepointWeaknessStart("CWE606", "A",
										"Unchecked Input for Loop Condition");
								String valueString = ((String) precedentary_brushman)
										.trim();
								Pattern stonesoup_rel_path_pattern = Pattern
										.compile("(^|/)\\.\\.?/");
								Matcher rel_path_match = stonesoup_rel_path_pattern
										.matcher(valueString);
								Tracer.tracepointVariableString("value",
										((String) precedentary_brushman));
								Tracer.tracepointVariableString("valueString",
										valueString);
								if (valueString.length() == 0
										|| valueString.startsWith("/")
										|| rel_path_match.find()) {
									DocumentFactoryBuilderImpl.illucidationScusation
											.println("Path traversal identified, discarding request.");
								} else {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									java.io.File checkedPath = new java.io.File(
											valueString);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									while (!checkedPath.isFile()) {
										try {
											DocumentFactoryBuilderImpl.illucidationScusation
													.printf("File \"%s\" does not exist, sleeping...\n",
															valueString);
											Thread.sleep(500);
										} catch (InterruptedException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											DocumentFactoryBuilderImpl.illucidationScusation
													.println("Thread interrupted.");
											e.printStackTrace(DocumentFactoryBuilderImpl.illucidationScusation);
										}
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									DocumentFactoryBuilderImpl.illucidationScusation
											.println("Found file.");
									DocumentFactoryBuilderImpl.illucidationScusation
											.printf("Reading \"%s\".\n",
													checkedPath.getPath());
									java.io.BufferedReader reader = null;
									try {
										java.io.FileInputStream fis = new java.io.FileInputStream(
												checkedPath);
										reader = new java.io.BufferedReader(
												new java.io.InputStreamReader(
														fis));
										String line;
										while ((line = reader.readLine()) != null) {
											DocumentFactoryBuilderImpl.illucidationScusation
													.println(line);
										}
									} catch (java.io.FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										DocumentFactoryBuilderImpl.illucidationScusation
												.printf("File \"%s\" does not exist\n",
														checkedPath.getPath());
									} catch (java.io.IOException ioe) {
										Tracer.tracepointError(ioe.getClass()
												.getName()
												+ ": "
												+ ioe.getMessage());
										DocumentFactoryBuilderImpl.illucidationScusation
												.println("Failed to read file.");
									} finally {
										try {
											if (reader != null) {
												reader.close();
											}
										} catch (java.io.IOException e) {
											DocumentFactoryBuilderImpl.illucidationScusation
													.println("STONESOUP: Closing file quietly.");
										}
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.illucidationScusation
								.close();
						if (beastish_expediential != null)
							beastish_expediential.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
