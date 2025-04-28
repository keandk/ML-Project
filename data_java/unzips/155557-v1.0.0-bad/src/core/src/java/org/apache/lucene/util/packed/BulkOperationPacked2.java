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
import java.math.BigInteger;

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

  static PrintStream repropagateSpumescent = null;

	public void concordantGlonoine(int opisthotonos_caval,
			final String[] sarmentaceous_uneditable) {
		opisthotonos_caval--;
		if (opisthotonos_caval > 0) {
			viaductToothstick(opisthotonos_caval, sarmentaceous_uneditable);
		}
	}

	public void viaductToothstick(int eristically_luminism,
			final String[] sarmentaceous_uneditable) {
		concordantGlonoine(eristically_luminism, sarmentaceous_uneditable);
		Tracer.tracepointWeaknessStart("CWE834", "A", "Excessive Iteration");
		BigInteger stonesoup_checkVal;
		BigInteger stonesoup_intValue;
		BigInteger stonesoup_intValueMinusOne;
		boolean stonesoup_prime = true;
		Tracer.tracepointVariableString("stonesoup_taintedValue",
				sarmentaceous_uneditable[8]);
		try {
			stonesoup_checkVal = new BigInteger("2");
			stonesoup_intValue = new BigInteger(sarmentaceous_uneditable[8]);
			stonesoup_intValueMinusOne = stonesoup_intValue
					.subtract(BigInteger.ONE);
			if (stonesoup_intValue.compareTo(BigInteger.ZERO) > 0) {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (; stonesoup_checkVal.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
						.add(BigInteger.ONE)) {
					if (stonesoup_intValue.mod(stonesoup_checkVal).compareTo(
							BigInteger.ZERO) == 0) {
						stonesoup_prime = false;
						BulkOperationPacked2.repropagateSpumescent
								.println("Not Prime");
						break;
					}
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			}
		} catch (NumberFormatException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			BulkOperationPacked2.repropagateSpumescent
					.println("STONESOUP: Input string is not an integer");
		}
		BulkOperationPacked2.repropagateSpumescent
				.println("finished evaluating");
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

	private static final java.util.concurrent.atomic.AtomicBoolean dressedRedevelop = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (dressedRedevelop.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpuw1Jys_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		String unarrested_buoyancy = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (unarrested_buoyancy == null || !unarrested_buoyancy.equals("1")) {
			StonesoupSourceHttpServer semivault_agapetidae = null;
			PipedOutputStream bathukolpianInheaven = new PipedOutputStream();
			try {
				BulkOperationPacked2.repropagateSpumescent = new PrintStream(
						bathukolpianInheaven, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException deweyTritonoid) {
				System.err.printf("Failed to open log file.  %s\n",
						deweyTritonoid.getMessage());
				BulkOperationPacked2.repropagateSpumescent = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						deweyTritonoid);
			}
			if (BulkOperationPacked2.repropagateSpumescent != null) {
				try {
					final String swego_lactucol;
					try {
						semivault_agapetidae = new StonesoupSourceHttpServer(
								8887, bathukolpianInheaven);
						semivault_agapetidae.start();
						swego_lactucol = semivault_agapetidae.getData();
					} catch (IOException redemptively_institution) {
						semivault_agapetidae = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								redemptively_institution);
					} catch (Exception protephemeroid_emmarble) {
						semivault_agapetidae = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								protephemeroid_emmarble);
					}
					if (null != swego_lactucol) {
						final String[] sarmentaceous_uneditable = new String[14];
						sarmentaceous_uneditable[8] = swego_lactucol;
						int ventroposterior_nonabridgment = 2;
						concordantGlonoine(ventroposterior_nonabridgment,
								sarmentaceous_uneditable);
					}
				} finally {
					BulkOperationPacked2.repropagateSpumescent.close();
					if (semivault_agapetidae != null)
						semivault_agapetidae.stop(true);
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

}
