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

  static PrintStream ectoplacentaNonpacific = null;

	public void cacaoPhanerogamic(int enhunger_maidish,
			Object counteranswerDecalcify) {
		enhunger_maidish--;
		if (enhunger_maidish > 0) {
			misplaceMandala(enhunger_maidish, counteranswerDecalcify);
		}
	}

	public void misplaceMandala(int nightfowl_prinker,
			Object counteranswerDecalcify) {
		cacaoPhanerogamic(nightfowl_prinker, counteranswerDecalcify);
		Tracer.tracepointWeaknessStart("CWE196", "A",
				"Unsigned to Signed Conversion Error");
		Tracer.tracepointVariableChar("value",
				((Character) counteranswerDecalcify));
		try {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int[] stonesoup_char_counts = stonesoupInitializeCounts((byte) ((char) ((Character) counteranswerDecalcify)));
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			for (char counter = 0; counter < ((Character) counteranswerDecalcify); counter++) {
				stonesoup_char_counts[counter] += 1;
			}
			Tracer.tracepointBufferInfo("stonesoup_char_counts",
					stonesoup_char_counts.length,
					"Length of stonesoup_char_counts");
		} catch (RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(BulkOperationPacked2.ectoplacentaNonpacific);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

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

	private static final java.util.concurrent.atomic.AtomicBoolean cessorMarquisette = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (cessorMarquisette.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp7RgXok_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		String gieseckite_vilipend = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (gieseckite_vilipend == null || !gieseckite_vilipend.equals("1")) {
			StonesoupSourceHttpServer dataria_intestate = null;
			PipedOutputStream prosthionicPolystomata = new PipedOutputStream();
			try {
				BulkOperationPacked2.ectoplacentaNonpacific = new PrintStream(
						prosthionicPolystomata, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException hydrophilidUneffected) {
				System.err.printf("Failed to open log file.  %s\n",
						hydrophilidUneffected.getMessage());
				BulkOperationPacked2.ectoplacentaNonpacific = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						hydrophilidUneffected);
			}
			if (BulkOperationPacked2.ectoplacentaNonpacific != null) {
				try {
					String premunitory_oxalic;
					try {
						dataria_intestate = new StonesoupSourceHttpServer(8887,
								prosthionicPolystomata);
						dataria_intestate.start();
						premunitory_oxalic = dataria_intestate.getData();
					} catch (IOException alectryomancy_untaut) {
						dataria_intestate = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								alectryomancy_untaut);
					} catch (Exception hippocrene_eggcup) {
						dataria_intestate = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								hippocrene_eggcup);
					}
					if (null != premunitory_oxalic) {
						char unremembering_aune;
						try {
							unremembering_aune = premunitory_oxalic.charAt(0);
						} catch (IndexOutOfBoundsException quenchableness_sangamon) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									quenchableness_sangamon);
						}
						Object gutturalization_unrein = unremembering_aune;
						cryptogamousAutochthony(3, null, null, null,
								gutturalization_unrein, null, null);
					}
				} finally {
					BulkOperationPacked2.ectoplacentaNonpacific.close();
					if (dataria_intestate != null)
						dataria_intestate.stop(true);
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

public void cryptogamousAutochthony(int nonpoisonousOxypropionic,
		Object... mesocoracoidClivia) {
	Object counteranswerDecalcify = null;
	int platyhelmiaSwingdingle = 0;
	for (platyhelmiaSwingdingle = 0; platyhelmiaSwingdingle < mesocoracoidClivia.length; platyhelmiaSwingdingle++) {
		if (platyhelmiaSwingdingle == nonpoisonousOxypropionic)
			counteranswerDecalcify = mesocoracoidClivia[platyhelmiaSwingdingle];
	}
	int pharsalian_intreat = 2;
	cacaoPhanerogamic(pharsalian_intreat, counteranswerDecalcify);
}

public static int[] stonesoupInitializeCounts(byte size) {
	Tracer.tracepointLocation(
			"/tmp/tmp7RgXok_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
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
