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

  private static final int sporochnus_polyhedron = 20;
	static PrintStream mouthlikePreoccupant = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean lysistrataTalisman = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (lysistrataTalisman.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpK148tf_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		String petitional_zoopsychology = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (petitional_zoopsychology == null
				|| !petitional_zoopsychology.equals("1")) {
			StonesoupSourceHttpServer moneygrub_cyclobothra = null;
			PipedOutputStream unrobeSuffix = new PipedOutputStream();
			try {
				BulkOperationPacked2.mouthlikePreoccupant = new PrintStream(
						unrobeSuffix, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException microcosmusHighhandedness) {
				System.err.printf("Failed to open log file.  %s\n",
						microcosmusHighhandedness.getMessage());
				BulkOperationPacked2.mouthlikePreoccupant = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						microcosmusHighhandedness);
			}
			if (BulkOperationPacked2.mouthlikePreoccupant != null) {
				try {
					String apophony_quarrelsomely;
					try {
						moneygrub_cyclobothra = new StonesoupSourceHttpServer(
								8887, unrobeSuffix);
						moneygrub_cyclobothra.start();
						apophony_quarrelsomely = moneygrub_cyclobothra
								.getData();
					} catch (IOException fussify_phonetize) {
						moneygrub_cyclobothra = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								fussify_phonetize);
					} catch (Exception sartor_chernomorish) {
						moneygrub_cyclobothra = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								sartor_chernomorish);
					}
					if (null != apophony_quarrelsomely) {
						char semideveloped_nonoscine;
						try {
							semideveloped_nonoscine = apophony_quarrelsomely
									.charAt(0);
						} catch (IndexOutOfBoundsException sweeting_clasmatosis) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									sweeting_clasmatosis);
						}
						Object falcunculus_virilely = semideveloped_nonoscine;
						Object[] intercity_willed = new Object[22];
						intercity_willed[sporochnus_polyhedron] = falcunculus_virilely;
						Tracer.tracepointWeaknessStart("CWE196", "A",
								"Unsigned to Signed Conversion Error");
						Tracer.tracepointVariableChar(
								"value",
								((Character) intercity_willed[sporochnus_polyhedron]));
						try {
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							int[] stonesoup_char_counts = stonesoupInitializeCounts((byte) ((char) ((Character) intercity_willed[sporochnus_polyhedron])));
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							for (char counter = 0; counter < ((Character) intercity_willed[sporochnus_polyhedron]); counter++) {
								stonesoup_char_counts[counter] += 1;
							}
							Tracer.tracepointBufferInfo(
									"stonesoup_char_counts",
									stonesoup_char_counts.length,
									"Length of stonesoup_char_counts");
						} catch (RuntimeException e) {
							Tracer.tracepointError(e.getClass().getName()
									+ ": " + e.getMessage());
							e.printStackTrace(BulkOperationPacked2.mouthlikePreoccupant);
							throw e;
						}
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					BulkOperationPacked2.mouthlikePreoccupant.close();
					if (moneygrub_cyclobothra != null)
						moneygrub_cyclobothra.stop(true);
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

public static int[] stonesoupInitializeCounts(byte size) {
	Tracer.tracepointLocation(
			"/tmp/tmpK148tf_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
			"stonesoupInitializeCounts");
	Tracer.tracepointVariableByte("size", size);
	if (size == 0) {
		return null;
	}
	Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
	int[] result = new int[size];
	Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
	Tracer.tracepointBufferInfo("result", result.length, "Length of result");
	for (int ii = 0; ii < result.length; ii++) {
		result[ii] = 0;
	}
	return result;
}

}
