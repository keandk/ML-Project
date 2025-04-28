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
import java.util.ArrayList;
import java.util.List;

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

  public class MolybdonosusFigurately {
		private Object picnicky_scurfily;

		public MolybdonosusFigurately(Object picnicky_scurfily) {
			this.picnicky_scurfily = picnicky_scurfily;
		}

		public Object getpicnicky_scurfily() {
			return this.picnicky_scurfily;
		}
	}

	static PrintStream sartUpglide = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean contestableVoltairism = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (contestableVoltairism.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpdvp2sA_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		String clericity_drier = System.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (clericity_drier == null || !clericity_drier.equals("1")) {
			StonesoupSourceHttpServer macrocosmology_sillily = null;
			PipedOutputStream priodonMyzostomida = new PipedOutputStream();
			try {
				BulkOperationPacked2.sartUpglide = new PrintStream(
						priodonMyzostomida, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException recruityPompiloid) {
				System.err.printf("Failed to open log file.  %s\n",
						recruityPompiloid.getMessage());
				BulkOperationPacked2.sartUpglide = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						recruityPompiloid);
			}
			if (BulkOperationPacked2.sartUpglide != null) {
				try {
					String probudget_dibutyrate;
					try {
						macrocosmology_sillily = new StonesoupSourceHttpServer(
								8887, priodonMyzostomida);
						macrocosmology_sillily.start();
						probudget_dibutyrate = macrocosmology_sillily.getData();
					} catch (IOException octocentenary_prentice) {
						macrocosmology_sillily = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								octocentenary_prentice);
					} catch (Exception yesso_totaquin) {
						macrocosmology_sillily = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								yesso_totaquin);
					}
					if (null != probudget_dibutyrate) {
						int unresourceful_tyste;
						try {
							unresourceful_tyste = Integer
									.parseInt(probudget_dibutyrate);
						} catch (NumberFormatException latax_archonship) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									latax_archonship);
						}
						Object topsman_decapodal = unresourceful_tyste;
						MolybdonosusFigurately typicality_probathing = new MolybdonosusFigurately(
								topsman_decapodal);
						annieKirver(typicality_probathing);
					}
				} finally {
					BulkOperationPacked2.sartUpglide.close();
					if (macrocosmology_sillily != null)
						macrocosmology_sillily.stop(true);
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

public static void annieKirver(MolybdonosusFigurately supercongestionMiliarium){Tracer.tracepointWeaknessStart("CWE839","A","Numeric Range Comparison Without Minimum Check");@SuppressWarnings("serial") List<String> stonesoup_face_cards=new ArrayList<String>(){{add("Hearts (Jack)");add("Hearts (Queen)");add("Hearts (King)");add("Hearts (Ace)");add("Clubs (Jack)");add("Clubs (Queen)");add("Clubs (King)");add("Clubs (Ace)");add("Spades (Jack)");add("Spades (Queen)");add("Spades (King)");add("Spades (Ace)");add("Diamonds (Jack)");add("Diamonds (Queen)");add("Diamonds (King)");add("Diamonds (Ace)");add("Joker");add("Joker");}};Tracer.tracepointVariableInt("value",((Integer)supercongestionMiliarium.getpicnicky_scurfily()));Tracer.tracepointVariableInt("stonesoup_face_cards.size()",stonesoup_face_cards.size());Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");if (((Integer)supercongestionMiliarium.getpicnicky_scurfily()) >= stonesoup_face_cards.size()){Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");BulkOperationPacked2.sartUpglide.printf("Card not available for %d.\n",((Integer)supercongestionMiliarium.getpicnicky_scurfily()));} else {Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");try {Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");BulkOperationPacked2.sartUpglide.printf("Selected Card = %s\n",stonesoup_face_cards.get(((Integer)supercongestionMiliarium.getpicnicky_scurfily())));Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (RuntimeException e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(BulkOperationPacked2.sartUpglide);throw e;}}Tracer.tracepointWeaknessEnd();}

public static void annieKirver() {
	annieKirver(null);
}

}
