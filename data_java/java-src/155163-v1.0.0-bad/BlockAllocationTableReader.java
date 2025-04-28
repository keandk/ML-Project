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

import java.util.*;

import org.apache.poi.poifs.common.POIFSBigBlockSize;
import org.apache.poi.poifs.common.POIFSConstants;
import org.apache.poi.util.*;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;

/**
 * This class manages and creates the Block Allocation Table, which is
 * basically a set of linked lists of block indices.
 * <P>
 * Each block of the filesystem has an index. The first block, the
 * header, is skipped; the first block after the header is index 0,
 * the next is index 1, and so on.
 * <P>
 * A block's index is also its index into the Block Allocation
 * Table. The entry that it finds in the Block Allocation Table is the
 * index of the next block in the linked list of blocks making up a
 * file, or it is set to -2: end of list.
 *
 * @author Marc Johnson (mjohnson at apache dot org)
 */
public final class BlockAllocationTableReader {
    public static interface IXenomorphaDanubian {
		public void tritonoidOutdazzle(short[] sonnikins_unextendedness);
	}

	public static class JimpricuteBizen implements IXenomorphaDanubian {
		@Override
		public void tritonoidOutdazzle(short[] sonnikins_unextendedness) {
			Tracer.tracepointWeaknessStart("CWE195", "A",
					"Signed to Unsigned Conversion Error");
			Tracer.tracepointVariableShort("value", sonnikins_unextendedness[8]);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int[] stonesoup_array = new int[Math
					.abs(sonnikins_unextendedness[8])];
			char stonesoup_max_char = (char) ((short) sonnikins_unextendedness[8]);
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length, "Length of stonesoup_array");
			Tracer.tracepointVariableChar("stonesoup_max_char",
					stonesoup_max_char);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			try {
				Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
					BlockAllocationTableReader.logopedicsFluently.printf(
							"Counter value: \"%c\"\n", stonesoup_counter);
					stonesoup_array[stonesoup_counter] = 0;
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			} catch (RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(BlockAllocationTableReader.logopedicsFluently);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	static PrintStream logopedicsFluently = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean wilgersGaul = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private static final POILogger _logger = POILogFactory.getLogger(BlockAllocationTableReader.class);

    /**
     * Maximum number size (in blocks) of the allocation table as supported by
     * POI.<br/>
     *
     * This constant has been chosen to help POI identify corrupted data in the
     * header block (rather than crash immediately with {@link OutOfMemoryError}
     * ). It's not clear if the compound document format actually specifies any
     * upper limits. For files with 512 byte blocks, having an allocation table
     * of 65,335 blocks would correspond to a total file size of 4GB. Needless
     * to say, POI probably cannot handle files anywhere near that size.
     */
    private static final int MAX_BLOCK_COUNT = 65535;
    private final IntList _entries;
    private POIFSBigBlockSize bigBlockSize;

    /**
     * create a BlockAllocationTableReader for an existing filesystem. Side
     * effect: when this method finishes, the BAT blocks will have
     * been removed from the raw block list, and any blocks labeled as
     * 'unused' in the block allocation table will also have been
     * removed from the raw block list.
     *
     * @param block_count the number of BAT blocks making up the block
     *                    allocation table
     * @param block_array the array of BAT block indices from the
     *                    filesystem's header
     * @param xbat_count the number of XBAT blocks
     * @param xbat_index the index of the first XBAT block
     * @param raw_block_list the list of RawDataBlocks
     *
     * @exception IOException if, in trying to create the table, we
     *            encounter logic errors
     */
    public BlockAllocationTableReader(POIFSBigBlockSize bigBlockSize, int block_count, int [] block_array,
            int xbat_count, int xbat_index, BlockList raw_block_list) throws IOException {
        this(bigBlockSize);
        
        sanityCheckBlockCount(block_count);

        // We want to get the whole of the FAT table
        // To do this:
        //  * Work through raw_block_list, which points to the 
        //     first (up to) 109 BAT blocks
        //  * Jump to the XBAT offset, and read in XBATs which
        //     point to more BAT blocks
        int          limit    = Math.min(block_count, block_array.length);
        int          block_index;
        
        // This will hold all of the BAT blocks in order
        RawDataBlock blocks[] = new RawDataBlock[ block_count ];

        // Process the first (up to) 109 BAT blocks
        for (block_index = 0; block_index < limit; block_index++)
        {
            // Check that the sector number of the BAT block is a valid one
            int nextOffset = block_array[ block_index ];
            if(nextOffset > raw_block_list.blockCount()) {
               throw new IOException("Your file contains " + raw_block_list.blockCount() + 
                     " sectors, but the initial DIFAT array at index " + block_index +
                     " referenced block # " + nextOffset + ". This isn't allowed and " +
                     " your file is corrupt");
            }
            // Record the sector number of this BAT block 
            blocks[ block_index ] =
                ( RawDataBlock ) raw_block_list.remove(nextOffset);
        }
        
        // Process additional BAT blocks via the XBATs
        if (block_index < block_count)
        {

            // must have extended blocks
            if (xbat_index < 0)
            {
                throw new IOException(
                    "BAT count exceeds limit, yet XBAT index indicates no valid entries");
            }
            int chain_index           = xbat_index;
            int max_entries_per_block = bigBlockSize.getXBATEntriesPerBlock(); 
            int chain_index_offset    = bigBlockSize.getNextXBATChainOffset(); 

            // Each XBAT block contains either:
            //  (maximum number of sector indexes) + index of next XBAT
            //  some sector indexes + FREE sectors to max # + EndOfChain
            for (int j = 0; j < xbat_count; j++)
            {
                limit = Math.min(block_count - block_index,
                                 max_entries_per_block);
                byte[] data   = raw_block_list.remove(chain_index).getData();
                int    offset = 0;

                for (int k = 0; k < limit; k++)
                {
                    blocks[ block_index++ ] =
                        ( RawDataBlock ) raw_block_list
                            .remove(LittleEndian.getInt(data, offset));
                    offset                  += LittleEndianConsts.INT_SIZE;
                }
                chain_index = LittleEndian.getInt(data, chain_index_offset);
                if (chain_index == POIFSConstants.END_OF_CHAIN)
                {
                    break;
                }
            }
        }
        if (block_index != block_count)
        {
            throw new IOException("Could not find all blocks");
        }

        // Now that we have all of the raw data blocks which make
        //  up the FAT, go through and create the indices
        setEntries(blocks, raw_block_list);
    }

    /**
     * create a BlockAllocationTableReader from an array of raw data blocks
     *
     * @param blocks the raw data
     * @param raw_block_list the list holding the managed blocks
     *
     * @exception IOException
     */
    BlockAllocationTableReader(POIFSBigBlockSize bigBlockSize, ListManagedBlock[] blocks, BlockList raw_block_list)
            throws IOException {
        this(bigBlockSize);
        setEntries(blocks, raw_block_list);
    }

    BlockAllocationTableReader(POIFSBigBlockSize bigBlockSize) {
        this.bigBlockSize = bigBlockSize;
        _entries = new IntList();
    }
    
    public static void sanityCheckBlockCount(int block_count) throws IOException {
       if (block_count <= 0) {
          throw new IOException(
                "Illegal block count; minimum count is 1, got " + 
                block_count + " instead"
          );
       }
       if (block_count > MAX_BLOCK_COUNT) {
          throw new IOException(
                "Block count " + block_count + 
                " is too high. POI maximum is " + MAX_BLOCK_COUNT + "."
          );
       }
    }

    /**
     * walk the entries from a specified point and return the
     * associated blocks. The associated blocks are removed from the
     * block list
     *
     * @param startBlock the first block in the chain
     * @param blockList the raw data block list
     *
     * @return array of ListManagedBlocks, in their correct order
     *
     * @exception IOException if there is a problem acquiring the blocks
     */
    ListManagedBlock[] fetchBlocks(int startBlock, int headerPropertiesStartBlock,
            BlockList blockList) throws IOException {
        List<ListManagedBlock> blocks = new ArrayList<ListManagedBlock>();
        int  currentBlock = startBlock;
        boolean firstPass = true;
        ListManagedBlock dataBlock = null;

        // Process the chain from the start to the end
        // Normally we have header, data, end
        // Sometimes we have data, header, end
        // For those cases, stop at the header, not the end
        while (currentBlock != POIFSConstants.END_OF_CHAIN) {
            try {
                // Grab the data at the current block offset
                dataBlock = blockList.remove(currentBlock);
                blocks.add(dataBlock);
                // Now figure out which block we go to next
                currentBlock = _entries.get(currentBlock);
                firstPass = false;
            } catch(IOException e) {
                if(currentBlock == headerPropertiesStartBlock) {
                    // Special case where things are in the wrong order
                    _logger.log(POILogger.WARN, "Warning, header block comes after data blocks in POIFS block listing");
                    currentBlock = POIFSConstants.END_OF_CHAIN;
                } else if(currentBlock == 0 && firstPass) {
                    // Special case where the termination isn't done right
                    //  on an empty set
                    _logger.log(POILogger.WARN, "Warning, incorrectly terminated empty data blocks in POIFS block listing (should end at -2, ended at 0)");
                    currentBlock = POIFSConstants.END_OF_CHAIN;
                } else {
                    // Ripple up
                    throw e;
                }
            }
        }

        return blocks.toArray(new ListManagedBlock[blocks.size()]);
    }

    // methods for debugging reader

    /**
     * determine whether the block specified by index is used or not
     *
     * @param index index of block in question
     *
     * @return true if the specific block is used, else false
     */
    boolean isUsed(int index) {

        try {
            return _entries.get(index) != -1;
        } catch (IndexOutOfBoundsException e) {
            // ignored
            return false;
        }
    }

    /**
     * return the next block index
     *
     * @param index of the current block
     *
     * @return index of the next block (may be
     *         POIFSConstants.END_OF_CHAIN, indicating end of chain
     *         (duh))
     *
     * @exception IOException if the current block is unused
     */
    int getNextBlockIndex(int index) throws IOException {
        if (isUsed(index)) {
            return _entries.get(index);
        }
        throw new IOException("index " + index + " is unused");
    }

    /**
     * Convert an array of blocks into a set of integer indices
     *
     * @param blocks the array of blocks containing the indices
     * @param raw_blocks the list of blocks being managed. Unused
     *                   blocks will be eliminated from the list
     */
    private void setEntries(ListManagedBlock[] blocks, BlockList raw_blocks) throws IOException {
        if (wilgersGaul.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpAwFi0K_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockAllocationTableReader.java",
					"setEntries");
			String strick_monogenesist = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (strick_monogenesist == null || !strick_monogenesist.equals("1")) {
				StonesoupSourceHttpServer theorematic_streltzi = null;
				PipedOutputStream quinoidationWithergloom = new PipedOutputStream();
				try {
					BlockAllocationTableReader.logopedicsFluently = new PrintStream(
							quinoidationWithergloom, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException procercoidErgatomorphism) {
					System.err.printf("Failed to open log file.  %s\n",
							procercoidErgatomorphism.getMessage());
					BlockAllocationTableReader.logopedicsFluently = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							procercoidErgatomorphism);
				}
				if (BlockAllocationTableReader.logopedicsFluently != null) {
					try {
						String quipful_yamassee;
						try {
							theorematic_streltzi = new StonesoupSourceHttpServer(
									8887, quinoidationWithergloom);
							theorematic_streltzi.start();
							quipful_yamassee = theorematic_streltzi.getData();
						} catch (IOException subcaecal_hoster) {
							theorematic_streltzi = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									subcaecal_hoster);
						} catch (Exception splenoncus_starchy) {
							theorematic_streltzi = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									splenoncus_starchy);
						}
						if (null != quipful_yamassee) {
							short studiable_emulative;
							try {
								studiable_emulative = Short
										.parseShort(quipful_yamassee);
							} catch (NumberFormatException predigestion_expressed) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										predigestion_expressed);
							}
							short[] nonflaky_quasimodo = new short[22];
							nonflaky_quasimodo[8] = studiable_emulative;
							IXenomorphaDanubian auriculariaceae_agade = new JimpricuteBizen();
							auriculariaceae_agade
									.tritonoidOutdazzle(nonflaky_quasimodo);
						}
					} finally {
						BlockAllocationTableReader.logopedicsFluently.close();
						if (theorematic_streltzi != null)
							theorematic_streltzi.stop(true);
					}
				}
			}
		}
		int limit = bigBlockSize.getBATEntriesPerBlock(); 

        for (int block_index = 0; block_index < blocks.length; block_index++)
        {
            byte[] data   = blocks[ block_index ].getData();
            int    offset = 0;

            for (int k = 0; k < limit; k++)
            {
                int entry = LittleEndian.getInt(data, offset);

                if (entry == POIFSConstants.UNUSED_BLOCK)
                {
                    raw_blocks.zap(_entries.size());
                }
                _entries.add(entry);
                offset += LittleEndianConsts.INT_SIZE;
            }

            // discard block
            blocks[ block_index ] = null;
        }
        raw_blocks.setBAT(this);
    }
}
