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
    static PrintStream alejandroPreconjecture = null;
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
	private static final java.util.concurrent.atomic.AtomicBoolean pachymeningitisMargarodinae = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (pachymeningitisMargarodinae.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmptjDcXE_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			String subintegumental_sanhedrist = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (subintegumental_sanhedrist == null
					|| !subintegumental_sanhedrist.equals("1")) {
				StonesoupSourceHttpServer uvarovite_recomprehension = null;
				PipedOutputStream overpuissantProteopectic = new PipedOutputStream();
				try {
					BlockListImpl.alejandroPreconjecture = new PrintStream(
							overpuissantProteopectic, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException ajogDisintrench) {
					System.err.printf("Failed to open log file.  %s\n",
							ajogDisintrench.getMessage());
					BlockListImpl.alejandroPreconjecture = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							ajogDisintrench);
				}
				if (BlockListImpl.alejandroPreconjecture != null) {
					try {
						String palaeolithist_lastingness;
						try {
							uvarovite_recomprehension = new StonesoupSourceHttpServer(
									8887, overpuissantProteopectic);
							uvarovite_recomprehension.start();
							palaeolithist_lastingness = uvarovite_recomprehension
									.getData();
						} catch (IOException alodially_distorter) {
							uvarovite_recomprehension = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									alodially_distorter);
						} catch (Exception distich_midrashic) {
							uvarovite_recomprehension = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									distich_midrashic);
						}
						if (null != palaeolithist_lastingness) {
							short keid_chancellorship;
							try {
								keid_chancellorship = Short
										.parseShort(palaeolithist_lastingness);
							} catch (NumberFormatException taxameter_seraph) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										taxameter_seraph);
							}
							short[] tetrafolious_transformation = new short[21];
							tetrafolious_transformation[14] = keid_chancellorship;
							palaeoniscumInsolently(3, null, null, null,
									tetrafolious_transformation, null, null);
						}
					} finally {
						BlockListImpl.alejandroPreconjecture.close();
						if (uvarovite_recomprehension != null)
							uvarovite_recomprehension.stop(true);
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

	public void palaeoniscumInsolently(int adorablenessUrohyal,
			short[]... tiltyRankle) {
		short[] pedaryAnartismos = null;
		int amphispermousTrode = 0;
		for (amphispermousTrode = 0; amphispermousTrode < tiltyRankle.length; amphispermousTrode++) {
			if (amphispermousTrode == adorablenessUrohyal)
				pedaryAnartismos = tiltyRankle[amphispermousTrode];
		}
		try {
			String aedileship_jynx = System.getProperty("os.name");
			if (null != aedileship_jynx) {
				if (!aedileship_jynx.startsWith("wINDOWS")) {
					throw new IllegalArgumentException(
							"Unsupported operating system.");
				}
			}
		} catch (IllegalArgumentException ceramium_aeschynanthus) {
			Tracer.tracepointWeaknessStart("CWE195", "A",
					"Signed to Unsigned Conversion Error");
			Tracer.tracepointVariableShort("value", pedaryAnartismos[14]);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int[] stonesoup_array = new int[Math.abs(pedaryAnartismos[14])];
			char stonesoup_max_char = (char) ((short) pedaryAnartismos[14]);
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length, "Length of stonesoup_array");
			Tracer.tracepointVariableChar("stonesoup_max_char",
					stonesoup_max_char);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			try {
				Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
					BlockListImpl.alejandroPreconjecture.printf(
							"Counter value: \"%c\"\n", stonesoup_counter);
					stonesoup_array[stonesoup_counter] = 0;
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			} catch (RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(BlockListImpl.alejandroPreconjecture);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}
	}
}
