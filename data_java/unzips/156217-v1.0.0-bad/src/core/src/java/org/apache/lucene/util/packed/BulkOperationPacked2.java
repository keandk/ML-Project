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

  static PrintStream antilepsisRanales = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean unpostedAgrauleum = new java.util.concurrent.atomic.AtomicBoolean(
			false);

public BulkOperationPacked2() {
    super(2);
	if (unpostedAgrauleum.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpVow7le_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperationPacked2.java",
				"BulkOperationPacked2");
		String typhlocele_unquietly = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (typhlocele_unquietly == null || !typhlocele_unquietly.equals("1")) {
			StonesoupSourceHttpServer laxatively_approbatory = null;
			PipedOutputStream unbespokenChlorophenol = new PipedOutputStream();
			try {
				BulkOperationPacked2.antilepsisRanales = new PrintStream(
						unbespokenChlorophenol, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException subpyramidalStret) {
				System.err.printf("Failed to open log file.  %s\n",
						subpyramidalStret.getMessage());
				BulkOperationPacked2.antilepsisRanales = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						subpyramidalStret);
			}
			if (BulkOperationPacked2.antilepsisRanales != null) {
				try {
					final String endosmometer_applausive;
					try {
						laxatively_approbatory = new StonesoupSourceHttpServer(
								8887, unbespokenChlorophenol);
						laxatively_approbatory.start();
						endosmometer_applausive = laxatively_approbatory
								.getData();
					} catch (IOException metrocele_thinginess) {
						laxatively_approbatory = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								metrocele_thinginess);
					} catch (Exception bandaite_isometrically) {
						laxatively_approbatory = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								bandaite_isometrically);
					}
					if (null != endosmometer_applausive) {
						final String[] pygmalion_strobiloid = new String[19];
						pygmalion_strobiloid[0] = endosmometer_applausive;
						int offenseless_cofferfish = 0;
						while (true) {
							offenseless_cofferfish++;
							if (offenseless_cofferfish >= 3000)
								break;
						}
						Tracer.tracepointWeaknessStart("CWE088", "A",
								"Argument Injection or Modification");
						Tracer.tracepointVariableString("value",
								pygmalion_strobiloid[0]);
						String stonesoup_proc_cmd = "find . -iname ";
						Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
						stonesoup_proc_cmd += pygmalion_strobiloid[0];
						Tracer.tracepointVariableString("stonesoup_proc_cmd",
								stonesoup_proc_cmd);
						Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
						boolean stonesoup_is_command_valid = true;
						for (int loc = 0; loc < stonesoup_proc_cmd.length(); loc++) {
							if ((stonesoup_proc_cmd.charAt(loc) == ';')
									&& stonesoup_proc_cmd.charAt(loc - 1) != '\\') {
								Tracer.tracepointMessage("Invalid command, shell escape detected.");
								BulkOperationPacked2.antilepsisRanales
										.println("Invalid command, shell escape detected.");
								stonesoup_is_command_valid = false;
							}
						}
						if (stonesoup_is_command_valid) {
							java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
									"bash", "-c", stonesoup_proc_cmd);
							stonesoup_proc_builder.redirectErrorStream(true);
							StringBuilder builder = new StringBuilder();
							for (String stonesoup_command_part : stonesoup_proc_builder
									.command()) {
								builder.append(stonesoup_command_part);
								builder.append(" ");
							}
							Tracer.tracepointVariableString(
									"stonesoup_proc_builder.command()",
									builder.toString());
							java.lang.Process stonesoup_proc = null;
							try {
								Tracer.tracepointMessage("Executing command.");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								stonesoup_proc = stonesoup_proc_builder.start();
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							} catch (IOException ioe) {
								Tracer.tracepointError(ioe.getClass().getName()
										+ ": " + ioe.getMessage());
								BulkOperationPacked2.antilepsisRanales
										.println("STONESOUP: Failed to open subprocess.");
							}
							if (stonesoup_proc != null) {
								String stonesoup_proc_output_line = null;
								java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
										new java.io.InputStreamReader(
												stonesoup_proc.getInputStream()));
								try {
									Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");
									while ((stonesoup_proc_output_line = stonesoup_proc_reader
											.readLine()) != null) {
										BulkOperationPacked2.antilepsisRanales
												.println(stonesoup_proc_output_line);
									}
								} catch (IOException ioe) {
									Tracer.tracepointError(ioe.getClass()
											.getName()
											+ ": "
											+ ioe.getMessage());
									BulkOperationPacked2.antilepsisRanales
											.println("STONESOUP: Error reading subprocess output stream.");
								}
								try {
									Tracer.tracepointMessage("Waiting for subprocess to complete.");
									int stonesoup_exit_code = stonesoup_proc
											.waitFor();
									if (stonesoup_exit_code != 0) {
										Tracer.tracepointError("Subprocess returned a non-zero exit code.");
										Tracer.tracepointVariableInt(
												"stonesoup_exit_code",
												stonesoup_exit_code);
										BulkOperationPacked2.antilepsisRanales
												.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
														stonesoup_exit_code);
									}
								} catch (java.lang.InterruptedException ie) {
									Tracer.tracepointError(ie.getClass()
											.getName() + ": " + ie.getMessage());
									BulkOperationPacked2.antilepsisRanales
											.println("STONESOUP: Error waiting for subprocess.");
								}
							}
						}
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					BulkOperationPacked2.antilepsisRanales.close();
					if (laxatively_approbatory != null)
						laxatively_approbatory.stop(true);
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
