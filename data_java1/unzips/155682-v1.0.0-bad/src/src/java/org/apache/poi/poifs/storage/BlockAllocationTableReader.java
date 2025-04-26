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
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

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
    static PrintStream columbineFarfugium = null;

	private static final java.util.concurrent.atomic.AtomicBoolean carmagnoleSporoduct = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (carmagnoleSporoduct.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpdT5zj__ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockAllocationTableReader.java",
					"setEntries");
			File plankbuiltSurcoat = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!plankbuiltSurcoat.getParentFile().exists()
					&& !plankbuiltSurcoat.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockAllocationTableReader.columbineFarfugium = new PrintStream(
							new FileOutputStream(plankbuiltSurcoat, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException overwhelmCicuta) {
					System.err.printf("Failed to open log file.  %s\n",
							overwhelmCicuta.getMessage());
					BlockAllocationTableReader.columbineFarfugium = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							overwhelmCicuta);
				} catch (FileNotFoundException caproinShowance) {
					System.err.printf("Failed to open log file.  %s\n",
							caproinShowance.getMessage());
					BlockAllocationTableReader.columbineFarfugium = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							caproinShowance);
				}
				if (BlockAllocationTableReader.columbineFarfugium != null) {
					try {
						String platycodon_fannia = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (platycodon_fannia == null
								|| !platycodon_fannia.equals("1")) {
							String cathectic_creatorhood = System
									.getenv("DISCOMFORTING_DOGGREL");
							if (null != cathectic_creatorhood) {
								File unsurcharged_meditator = new File(
										cathectic_creatorhood);
								if (unsurcharged_meditator.exists()
										&& !unsurcharged_meditator
												.isDirectory()) {
									try {
										String rheme_shaharith;
										Scanner feudalize_ptyalectasis = new Scanner(
												unsurcharged_meditator, "UTF-8")
												.useDelimiter("\\A");
										if (feudalize_ptyalectasis.hasNext())
											rheme_shaharith = feudalize_ptyalectasis
													.next();
										else
											rheme_shaharith = "";
										if (null != rheme_shaharith) {
											int tearably_duadic;
											try {
												tearably_duadic = Integer
														.parseInt(rheme_shaharith);
											} catch (NumberFormatException untoned_nihilianism) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														untoned_nihilianism);
											}
											Tracer.tracepointWeaknessStart(
													"CWE774", "A",
													"Allocation of File Descriptors or Handles Without Limits or Throttling");
											FileOutputStream[] stonesoup_sources = new FileOutputStream[tearably_duadic];
											int stonesoup_active_files = 0;
											Tracer.tracepointBufferInfo(
													"stonesoup_sources",
													stonesoup_sources.length,
													"Length of stonesoup_sources");
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											for (int stonesoup_counter = 0; stonesoup_counter < tearably_duadic; stonesoup_counter++) {
												try {
													stonesoup_sources[stonesoup_counter] = new FileOutputStream(
															String.format(
																	"/opt/stonesoup/workspace/testData/test%10d",
																	stonesoup_counter));
												} catch (FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													BlockAllocationTableReader.columbineFarfugium
															.println("Failed to create new file.");
													e.printStackTrace(BlockAllocationTableReader.columbineFarfugium);
													throw new RuntimeException(
															e);
												}
												stonesoup_active_files++;
												BlockAllocationTableReader.columbineFarfugium
														.println(stonesoup_counter);
											}
											Tracer.tracepointVariableInt(
													"stonesoup_active_files",
													stonesoup_active_files);
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
												try {
													if (stonesoup_sources[stonesoup_counter] != null) {
														stonesoup_sources[stonesoup_counter]
																.close();
													}
												} catch (IOException e) {
													BlockAllocationTableReader.columbineFarfugium
															.println("Failed to close file.");
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException peacemakingRefrenation) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												peacemakingRefrenation);
									}
								}
							}
						}
					} finally {
						BlockAllocationTableReader.columbineFarfugium.close();
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
