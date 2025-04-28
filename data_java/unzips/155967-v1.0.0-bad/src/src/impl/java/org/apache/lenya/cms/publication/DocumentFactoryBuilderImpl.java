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
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Document factory builder implementation.
 */
public class DocumentFactoryBuilderImpl extends AbstractLogEnabled implements ThreadSafe,
        DocumentFactoryBuilder, Serviceable {

    private static final int casino_gonoblastidium = 1;
	static PrintStream zebraMalebolgic = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean sinusalPolynesic = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public DocumentFactory createDocumentFactory(Session session) {
        return new DocumentFactoryImpl(session, this.manager, getLogger());
    }
    
    protected ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (sinusalPolynesic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpu2gWP9_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"service");
			String lamber_unregardedly = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (lamber_unregardedly == null || !lamber_unregardedly.equals("1")) {
				StonesoupSourceHttpServer sinitic_naggle = null;
				PipedOutputStream catharizationRupert = new PipedOutputStream();
				try {
					DocumentFactoryBuilderImpl.zebraMalebolgic = new PrintStream(
							catharizationRupert, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException shammockSemiwaking) {
					System.err.printf("Failed to open log file.  %s\n",
							shammockSemiwaking.getMessage());
					DocumentFactoryBuilderImpl.zebraMalebolgic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							shammockSemiwaking);
				}
				if (DocumentFactoryBuilderImpl.zebraMalebolgic != null) {
					try {
						String polycoccous_barouche;
						try {
							sinitic_naggle = new StonesoupSourceHttpServer(
									8887, catharizationRupert);
							sinitic_naggle.start();
							polycoccous_barouche = sinitic_naggle.getData();
						} catch (IOException pseudograph_reliction) {
							sinitic_naggle = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									pseudograph_reliction);
						} catch (Exception squush_underprice) {
							sinitic_naggle = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									squush_underprice);
						}
						if (null != polycoccous_barouche) {
							int harpocrates_lavable;
							try {
								harpocrates_lavable = Integer
										.parseInt(polycoccous_barouche);
							} catch (NumberFormatException turnhall_preconfuse) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										turnhall_preconfuse);
							}
							int[] combaron_putamen = new int[8];
							combaron_putamen[casino_gonoblastidium] = harpocrates_lavable;
							try {
								String kombu_unpledged = System
										.getProperty("os.name");
								if (null != kombu_unpledged) {
									if (!kombu_unpledged.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException unphrased_classes) {
								Tracer.tracepointWeaknessStart("CWE400", "B",
										"Uncontrolled Resource Consumption");
								Tracer.tracepointMessage("Create pool");
								ExecutorService pool = Executors
										.newFixedThreadPool(20);
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								if (combaron_putamen[casino_gonoblastidium] > 0
										&& combaron_putamen[casino_gonoblastidium] <= Integer.MAX_VALUE) {
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									Tracer.tracepointMessage("Creating threads");
									for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
										pool.execute(new Factorial(
												combaron_putamen[casino_gonoblastidium],
												DocumentFactoryBuilderImpl.zebraMalebolgic));
									}
									pool.shutdown();
									Tracer.tracepointMessage("Shutdown pool");
								}
								try {
									Tracer.tracepointMessage("Joining threads");
									while (!pool.awaitTermination(1,
											TimeUnit.SECONDS)) {
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									Tracer.tracepointMessage("Joined threads");
									DocumentFactoryBuilderImpl.zebraMalebolgic
											.println("finished evaluating");
								} catch (InterruptedException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									DocumentFactoryBuilderImpl.zebraMalebolgic
											.println("Thread pool interrupted");
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						DocumentFactoryBuilderImpl.zebraMalebolgic.close();
						if (sinitic_naggle != null)
							sinitic_naggle.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpu2gWP9_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpu2gWP9_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpu2gWP9_ss_testcase/src/src/impl/java/org/apache/lenya/cms/publication/DocumentFactoryBuilderImpl.java",
					Thread.currentThread().getName()
							+ ": Factorial.calculateFactorial");
			BigInteger stonesoup_factorial = new BigInteger("1");
			for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
				stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
						.valueOf(stonesoup_counter));
			}
			stonesoup_output.println(stonesoup_factorial);
		}
	}

}
