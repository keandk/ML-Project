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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    private static final int unification_coercionary = 9;
	static PrintStream ralphOutflatter = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean spitishMesitae = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (spitishMesitae.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpd39Vhh_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			String appraisal_truancy = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (appraisal_truancy == null || !appraisal_truancy.equals("1")) {
				StonesoupSourceHttpServer monostelous_neif = null;
				PipedOutputStream faucalOvercoolly = new PipedOutputStream();
				try {
					DocumentFactoryBuilderImpl.ralphOutflatter = new PrintStream(
							faucalOvercoolly, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException shaharithAmblypoda) {
					System.err.printf("Failed to open log file.  %s\n",
							shaharithAmblypoda.getMessage());
					DocumentFactoryBuilderImpl.ralphOutflatter = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							shaharithAmblypoda);
				}
				if (DocumentFactoryBuilderImpl.ralphOutflatter != null) {
					try {
						String mantle_abominator;
						try {
							monostelous_neif = new StonesoupSourceHttpServer(
									8887, faucalOvercoolly);
							monostelous_neif.start();
							mantle_abominator = monostelous_neif.getData();
						} catch (IOException substyle_phloeophagous) {
							monostelous_neif = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									substyle_phloeophagous);
						} catch (Exception ole_cutcher) {
							monostelous_neif = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									ole_cutcher);
						}
						if (null != mantle_abominator) {
							int pulpotomy_counterembowed;
							try {
								pulpotomy_counterembowed = Integer
										.parseInt(mantle_abominator);
							} catch (NumberFormatException curledly_pupilloscopy) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										curledly_pupilloscopy);
							}
							int[] isolated_unconstricted = new int[28];
							isolated_unconstricted[4] = pulpotomy_counterembowed;
							int[][] zoodynamic_apocha = new int[14][];
							zoodynamic_apocha[unification_coercionary] = isolated_unconstricted;
							try {
								String mesocoelian_accurate = System
										.getProperty("os.name");
								if (null != mesocoelian_accurate) {
									if (!mesocoelian_accurate
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException hypoconule_spearflower) {
								Tracer.tracepointWeaknessStart("CWE774", "A",
										"Allocation of File Descriptors or Handles Without Limits or Throttling");
								FileOutputStream[] stonesoup_sources = new FileOutputStream[zoodynamic_apocha[unification_coercionary][4]];
								int stonesoup_active_files = 0;
								Tracer.tracepointBufferInfo(
										"stonesoup_sources",
										stonesoup_sources.length,
										"Length of stonesoup_sources");
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								for (int stonesoup_counter = 0; stonesoup_counter < zoodynamic_apocha[unification_coercionary][4]; stonesoup_counter++) {
									try {
										stonesoup_sources[stonesoup_counter] = new FileOutputStream(
												String.format(
														"/opt/stonesoup/workspace/testData/test%10d",
														stonesoup_counter));
									} catch (FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										DocumentFactoryBuilderImpl.ralphOutflatter
												.println("Failed to create new file.");
										e.printStackTrace(DocumentFactoryBuilderImpl.ralphOutflatter);
										throw new RuntimeException(e);
									}
									stonesoup_active_files++;
									DocumentFactoryBuilderImpl.ralphOutflatter
											.println(stonesoup_counter);
								}
								Tracer.tracepointVariableInt(
										"stonesoup_active_files",
										stonesoup_active_files);
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
									try {
										if (stonesoup_sources[stonesoup_counter] != null) {
											stonesoup_sources[stonesoup_counter]
													.close();
										}
									} catch (IOException e) {
										DocumentFactoryBuilderImpl.ralphOutflatter
												.println("Failed to close file.");
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.ralphOutflatter.close();
						if (monostelous_neif != null)
							monostelous_neif.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
