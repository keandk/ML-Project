// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

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
import java.util.Arrays;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Efficient sequential read/write of packed integers.
 */
final class BulkOperationPacked2 extends BulkOperationPacked {

  public class DysergiaChirotype {
		private Object propertied_awake;

		public DysergiaChirotype(Object propertied_awake) {
			this.propertied_awake = propertied_awake;
		}

		public Object getpropertied_awake() {
			return this.propertied_awake;
		}
	}

	static PrintStream septariateSpiraculiferous = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean heterosomataMisrealize = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (heterosomataMisrealize.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp4fHNna_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		String hierarch_hispanic = System.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (hierarch_hispanic == null || !hierarch_hispanic.equals("1")) {
			StonesoupSourceHttpServer flaxbush_agapemonian = null;
			PipedOutputStream creatorhoodUnbreathable = new PipedOutputStream();
			try {
				BulkOperationPacked2.septariateSpiraculiferous = new PrintStream(
						creatorhoodUnbreathable, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException cistercianProguardian) {
				System.err.printf("Failed to open log file.  %s\n",
						cistercianProguardian.getMessage());
				BulkOperationPacked2.septariateSpiraculiferous = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						cistercianProguardian);
			}
			if (BulkOperationPacked2.septariateSpiraculiferous != null) {
				try {
					String unerrableness_tree;
					try {
						flaxbush_agapemonian = new StonesoupSourceHttpServer(
								8887, creatorhoodUnbreathable);
						flaxbush_agapemonian.start();
						unerrableness_tree = flaxbush_agapemonian.getData();
					} catch (IOException cartage_unsquelched) {
						flaxbush_agapemonian = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								cartage_unsquelched);
					} catch (Exception dehepatize_recta) {
						flaxbush_agapemonian = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								dehepatize_recta);
					}
					if (null != unerrableness_tree) {
						int rattleheaded_rhizome;
						try {
							rattleheaded_rhizome = Integer
									.parseInt(unerrableness_tree);
						} catch (NumberFormatException provisory_invitational) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									provisory_invitational);
						}
						Object gangsterism_albuminose = rattleheaded_rhizome;
						DysergiaChirotype lax_crown = new DysergiaChirotype(
								gangsterism_albuminose);
						try {
							String sporocarp_balter = System
									.getProperty("os.name");
							if (null != sporocarp_balter) {
								if (!sporocarp_balter.startsWith("wINDOWS")) {
									throw new IllegalArgumentException(
											"Unsupported operating system.");
								}
							}
						} catch (IllegalArgumentException mutter_typhloptosis) {
							Tracer.tracepointWeaknessStart("CWE789", "A",
									"Uncontrolled Memory Allocation");
							try {
								if (((Integer) lax_crown.getpropertied_awake()) > 0
										&& ((Integer) lax_crown
												.getpropertied_awake()) <= Integer.MAX_VALUE) {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									stonesoup_array = new char[((Integer) lax_crown
											.getpropertied_awake())];
									Tracer.tracepointBufferInfo(
											"stonesoup_array",
											stonesoup_array.length,
											"Length of stonesoup_array");
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									Arrays.fill(stonesoup_array, 'x');
									for (int i = 0; i < stonesoup_array.length; i++) {
										BulkOperationPacked2.septariateSpiraculiferous
												.print(stonesoup_array[i]);
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									BulkOperationPacked2.septariateSpiraculiferous
											.println("");
									BulkOperationPacked2.septariateSpiraculiferous
											.println("STONESOUP: successfully initialized array");
								}
							} catch (Error e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(BulkOperationPacked2.septariateSpiraculiferous);
								throw e;
							}
							Tracer.tracepointWeaknessEnd();
						}
					}
				} finally {
					BulkOperationPacked2.septariateSpiraculiferous.close();
					if (flaxbush_agapemonian != null)
						flaxbush_agapemonian.stop(true);
				}
			}
		}
	}
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (int) ((block >>> shift) & 3);
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, int[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

  @Override
  public void decode(long[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int i = 0; i < iterations; ++i) {
      final long block = blocks[blocksOffset++];
      for (int shift = 62; shift >= 0; shift -= 2) {
        values[valuesOffset++] = (block >>> shift) & 3;
      }
    }
  }

  @Override
  public void decode(byte[] blocks, int blocksOffset, long[] values, int valuesOffset, int iterations) {
    for (int j = 0; j < iterations; ++j) {
      final byte block = blocks[blocksOffset++];
      values[valuesOffset++] = (block >>> 6) & 3;
      values[valuesOffset++] = (block >>> 4) & 3;
      values[valuesOffset++] = (block >>> 2) & 3;
      values[valuesOffset++] = block & 3;
    }
  }

static char[] stonesoup_array;

}
