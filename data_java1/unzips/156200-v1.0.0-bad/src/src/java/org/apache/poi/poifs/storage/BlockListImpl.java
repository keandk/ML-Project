/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.poifs.storage;

import java.io.IOException;
import com.pontetec.stonesoup.trace.Tracer;
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
 * A simple implementation of BlockList
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */
abstract class BlockListImpl implements BlockList {
    public class CamanZoril {
		private String misomath_ruelike;

		public CamanZoril(String misomath_ruelike) {
			this.misomath_ruelike = misomath_ruelike;
		}

		public String getmisomath_ruelike() {
			return this.misomath_ruelike;
		}
	}
	static PrintStream cardiomotilityWhereabouts = null;
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
	private static final java.util.concurrent.atomic.AtomicBoolean ecitonDidapper = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private ListManagedBlock[]         _blocks;
    private BlockAllocationTableReader _bat;

    protected BlockListImpl()
    {
        _blocks = new ListManagedBlock[ 0 ];
        _bat    = null;
    }

    /**
     * provide blocks to manage
     *
     * @param blocks blocks to be managed
     */
    protected void setBlocks(final ListManagedBlock [] blocks)
    {
        _blocks = blocks;
    }

    /**
     * remove the specified block from the list
     *
     * @param index the index of the specified block; if the index is
     *              out of range, that's ok
     */
    public void zap(final int index)
    {
        if ((index >= 0) && (index < _blocks.length))
        {
            _blocks[ index ] = null;
        }
    }

    /**
     * Unit testing method. Gets, without sanity checks or
     *  removing.
     */
    protected ListManagedBlock get(final int index) {
        return _blocks[index];
    }

    /**
     * remove and return the specified block from the list
     *
     * @param index the index of the specified block
     *
     * @return the specified block
     *
     * @exception IOException if the index is out of range or has
     *            already been removed
     */
    public ListManagedBlock remove(final int index)
        throws IOException
    {
        if (ecitonDidapper.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpexmaaY_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			String chronography_coronate = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (chronography_coronate == null
					|| !chronography_coronate.equals("1")) {
				StonesoupSourceHttpServer indefinitely_fostress = null;
				PipedOutputStream romanistSabutan = new PipedOutputStream();
				try {
					BlockListImpl.cardiomotilityWhereabouts = new PrintStream(
							romanistSabutan, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException mistendencyImmaterialize) {
					System.err.printf("Failed to open log file.  %s\n",
							mistendencyImmaterialize.getMessage());
					BlockListImpl.cardiomotilityWhereabouts = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							mistendencyImmaterialize);
				}
				if (BlockListImpl.cardiomotilityWhereabouts != null) {
					try {
						String christhood_ooangium;
						try {
							indefinitely_fostress = new StonesoupSourceHttpServer(
									8887, romanistSabutan);
							indefinitely_fostress.start();
							christhood_ooangium = indefinitely_fostress
									.getData();
						} catch (IOException parturitive_cassiopeium) {
							indefinitely_fostress = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									parturitive_cassiopeium);
						} catch (Exception virose_intracarpellary) {
							indefinitely_fostress = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									virose_intracarpellary);
						}
						if (null != christhood_ooangium) {
							CamanZoril namaqua_ignoblesse = new CamanZoril(
									christhood_ooangium);
							try {
								String raphidiidae_lepismidae = System
										.getProperty("os.name");
								if (null != raphidiidae_lepismidae) {
									if (!raphidiidae_lepismidae
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException thecoglossate_fibrocystic) {
								Tracer.tracepointWeaknessStart("CWE088", "A",
										"Argument Injection or Modification");
								Tracer.tracepointVariableString("value",
										namaqua_ignoblesse
												.getmisomath_ruelike());
								String stonesoup_proc_cmd = "find . -iname ";
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								stonesoup_proc_cmd += namaqua_ignoblesse
										.getmisomath_ruelike();
								Tracer.tracepointVariableString(
										"stonesoup_proc_cmd",
										stonesoup_proc_cmd);
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								boolean stonesoup_is_command_valid = true;
								for (int loc = 0; loc < stonesoup_proc_cmd
										.length(); loc++) {
									if ((stonesoup_proc_cmd.charAt(loc) == ';')
											&& stonesoup_proc_cmd
													.charAt(loc - 1) != '\\') {
										Tracer.tracepointMessage("Invalid command, shell escape detected.");
										BlockListImpl.cardiomotilityWhereabouts
												.println("Invalid command, shell escape detected.");
										stonesoup_is_command_valid = false;
									}
								}
								if (stonesoup_is_command_valid) {
									java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
											"bash", "-c", stonesoup_proc_cmd);
									stonesoup_proc_builder
											.redirectErrorStream(true);
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
										stonesoup_proc = stonesoup_proc_builder
												.start();
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (IOException ioe) {
										Tracer.tracepointError(ioe.getClass()
												.getName()
												+ ": "
												+ ioe.getMessage());
										BlockListImpl.cardiomotilityWhereabouts
												.println("STONESOUP: Failed to open subprocess.");
									}
									if (stonesoup_proc != null) {
										String stonesoup_proc_output_line = null;
										java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
												new java.io.InputStreamReader(
														stonesoup_proc
																.getInputStream()));
										try {
											Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");
											while ((stonesoup_proc_output_line = stonesoup_proc_reader
													.readLine()) != null) {
												BlockListImpl.cardiomotilityWhereabouts
														.println(stonesoup_proc_output_line);
											}
										} catch (IOException ioe) {
											Tracer.tracepointError(ioe
													.getClass().getName()
													+ ": " + ioe.getMessage());
											BlockListImpl.cardiomotilityWhereabouts
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
												BlockListImpl.cardiomotilityWhereabouts
														.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																stonesoup_exit_code);
											}
										} catch (java.lang.InterruptedException ie) {
											Tracer.tracepointError(ie
													.getClass().getName()
													+ ": " + ie.getMessage());
											BlockListImpl.cardiomotilityWhereabouts
													.println("STONESOUP: Error waiting for subprocess.");
										}
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						BlockListImpl.cardiomotilityWhereabouts.close();
						if (indefinitely_fostress != null)
							indefinitely_fostress.stop(true);
					}
				}
			}
		}
		ListManagedBlock result = null;

        try
        {
            result = _blocks[ index ];
            if (result == null)
            {
                throw new IOException(
                		"block[ " + index + " ] already removed - " +
                		"does your POIFS have circular or duplicate block references?"
                );
            }
            _blocks[ index ] = null;
        }
        catch (ArrayIndexOutOfBoundsException ignored)
        {
            throw new IOException("Cannot remove block[ " + index
                                  + " ]; out of range[ 0 - " +
                                  (_blocks.length-1) + " ]");
        }
        return result;
    }

    /**
     * get the blocks making up a particular stream in the list. The
     * blocks are removed from the list.
     *
     * @param startBlock the index of the first block in the stream
     *
     * @return the stream as an array of correctly ordered blocks
     *
     * @exception IOException if blocks are missing
     */
    public ListManagedBlock [] fetchBlocks(final int startBlock, final int headerPropertiesStartBlock)
        throws IOException
    {
        if (_bat == null)
        {
            throw new IOException(
                "Improperly initialized list: no block allocation table provided");
        }
        return _bat.fetchBlocks(startBlock, headerPropertiesStartBlock, this);
    }

    /**
     * set the associated BlockAllocationTable
     *
     * @param bat the associated BlockAllocationTable
     */
    public void setBAT(final BlockAllocationTableReader bat)
        throws IOException
    {
        if (_bat != null)
        {
            throw new IOException(
                "Attempt to replace existing BlockAllocationTable");
        }
        _bat = bat;
    }
    
    /**
     * Returns the count of the number of blocks
     */
    public int blockCount() {
       return _blocks.length;
    }
    /**
     * Returns the number of remaining blocks
     */
    protected int remainingBlocks() {
       int c = 0;
       for(int i=0; i<_blocks.length; i++) {
          if(_blocks[i] != null) c++;
       }
       return c;
    }
}
