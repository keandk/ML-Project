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

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    private static final int spealbone_loudish = 27;
	static PrintStream naphthalenicOligopepsia = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean pulverulentlyScissorbird = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (pulverulentlyScissorbird.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpZSg3Pp_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			String macromeral_henfish = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (macromeral_henfish == null || !macromeral_henfish.equals("1")) {
				StonesoupSourceHttpServer zelkova_trichomatose = null;
				PipedOutputStream undercoachmanBlueleg = new PipedOutputStream();
				try {
					DocumentFactoryBuilderImpl.naphthalenicOligopepsia = new PrintStream(
							undercoachmanBlueleg, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException pomaceaeAnthropometer) {
					System.err.printf("Failed to open log file.  %s\n",
							pomaceaeAnthropometer.getMessage());
					DocumentFactoryBuilderImpl.naphthalenicOligopepsia = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							pomaceaeAnthropometer);
				}
				if (DocumentFactoryBuilderImpl.naphthalenicOligopepsia != null) {
					try {
						String ringgiving_prophet;
						try {
							zelkova_trichomatose = new StonesoupSourceHttpServer(
									8887, undercoachmanBlueleg);
							zelkova_trichomatose.start();
							ringgiving_prophet = zelkova_trichomatose.getData();
						} catch (IOException chetverik_football) {
							zelkova_trichomatose = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									chetverik_football);
						} catch (Exception hexammine_scudder) {
							zelkova_trichomatose = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									hexammine_scudder);
						}
						if (null != ringgiving_prophet) {
							Object teave_hemorrhoidal = ringgiving_prophet;
							Object[] flype_archaeostoma = new Object[29];
							flype_archaeostoma[spealbone_loudish] = teave_hemorrhoidal;
							Tracer.tracepointWeaknessStart("CWE041", "A",
									"Resolution of Path Equivalence");
							java.io.BufferedReader reader = null;
							String valueString = ((String) flype_archaeostoma[spealbone_loudish])
									.trim();
							Tracer.tracepointVariableString(
									"value",
									((String) flype_archaeostoma[spealbone_loudish]));
							Tracer.tracepointVariableString("valueString",
									valueString);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							if (valueString.length() != 0
									&& valueString.startsWith("/etc/")) {
								DocumentFactoryBuilderImpl.naphthalenicOligopepsia
										.println("Access Denied.	Attempt to access a restricted file in \"/etc\".");
							} else {
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								java.io.File readPath = new java.io.File(
										valueString);
								if (readPath.isFile()) {
									try {
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										java.io.FileInputStream fis = new java.io.FileInputStream(
												readPath);
										reader = new java.io.BufferedReader(
												new java.io.InputStreamReader(
														fis));
										String line = null;
										while ((line = reader.readLine()) != null) {
											DocumentFactoryBuilderImpl.naphthalenicOligopepsia
													.println(line);
										}
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (java.io.FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										DocumentFactoryBuilderImpl.naphthalenicOligopepsia
												.printf("File \"%s\" does not exist\n",
														readPath.getPath());
									} catch (java.io.IOException ioe) {
										Tracer.tracepointError(ioe.getClass()
												.getName()
												+ ": "
												+ ioe.getMessage());
										DocumentFactoryBuilderImpl.naphthalenicOligopepsia
												.println("Failed to read file.");
									} finally {
										try {
											if (reader != null) {
												reader.close();
											}
										} catch (java.io.IOException e) {
											DocumentFactoryBuilderImpl.naphthalenicOligopepsia
													.println("STONESOUP: Closing file quietly.");
										}
									}
								} else {
									Tracer.tracepointMessage("File doesn't exist");
									DocumentFactoryBuilderImpl.naphthalenicOligopepsia
											.printf("File \"%s\" does not exist\n",
													readPath.getPath());
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						DocumentFactoryBuilderImpl.naphthalenicOligopepsia
								.close();
						if (zelkova_trichomatose != null)
							zelkova_trichomatose.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
