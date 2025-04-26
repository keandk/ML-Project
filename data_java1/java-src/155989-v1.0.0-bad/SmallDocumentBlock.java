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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.poifs.common.POIFSBigBlockSize;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Storage for documents that are too small to use regular
 * DocumentBlocks for their data
 *
 * @author  Marc Johnson (mjohnson at apache dot org)
 */
public final class SmallDocumentBlock implements BlockWritable, ListManagedBlock {
    private static final int mismate_oscheolith = 13;

	static PrintStream naphthylamineIncidentalist = null;

	private static final java.util.concurrent.atomic.AtomicBoolean dorsicollarHypostome = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private static final int BLOCK_SHIFT = 6;

    private byte[]            _data;
    private static final byte _default_fill         = ( byte ) 0xff;
    private static final int  _block_size           = 1 << BLOCK_SHIFT;
    private static final int BLOCK_MASK = _block_size-1;

    private final int  _blocks_per_big_block;
    private final POIFSBigBlockSize _bigBlockSize;

    private SmallDocumentBlock(final POIFSBigBlockSize bigBlockSize, final byte [] data, final int index)
    {
        this(bigBlockSize);
        if (dorsicollarHypostome.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpZelLmg_ss_testcase/src/src/java/org/apache/poi/poifs/storage/SmallDocumentBlock.java",
					"SmallDocumentBlock");
			File pushingnessPanocha = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!pushingnessPanocha.getParentFile().exists()
					&& !pushingnessPanocha.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					SmallDocumentBlock.naphthylamineIncidentalist = new PrintStream(
							new FileOutputStream(pushingnessPanocha, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException bokomConglutin) {
					System.err.printf("Failed to open log file.  %s\n",
							bokomConglutin.getMessage());
					SmallDocumentBlock.naphthylamineIncidentalist = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							bokomConglutin);
				} catch (FileNotFoundException coadjacenceTechnochemistry) {
					System.err.printf("Failed to open log file.  %s\n",
							coadjacenceTechnochemistry.getMessage());
					SmallDocumentBlock.naphthylamineIncidentalist = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							coadjacenceTechnochemistry);
				}
				if (SmallDocumentBlock.naphthylamineIncidentalist != null) {
					try {
						String demy_veigle = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (demy_veigle == null || !demy_veigle.equals("1")) {
							String martite_interlocutorily = System
									.getenv("RUBBISHY_DERMATOSKELETON");
							if (null != martite_interlocutorily) {
								File unpencilled_apperceptive = new File(
										martite_interlocutorily);
								if (unpencilled_apperceptive.exists()
										&& !unpencilled_apperceptive
												.isDirectory()) {
									try {
										String kayvan_reobtain;
										Scanner curratow_abundant = new Scanner(
												unpencilled_apperceptive,
												"UTF-8").useDelimiter("\\A");
										if (curratow_abundant.hasNext())
											kayvan_reobtain = curratow_abundant
													.next();
										else
											kayvan_reobtain = "";
										if (null != kayvan_reobtain) {
											int recoupment_kinesic;
											try {
												recoupment_kinesic = Integer
														.parseInt(kayvan_reobtain);
											} catch (NumberFormatException babel_antidrag) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														babel_antidrag);
											}
											int[] mothproof_maratha = new int[16];
											mothproof_maratha[mismate_oscheolith] = recoupment_kinesic;
											int greaseless_lobopodium = 0;
											while (true) {
												greaseless_lobopodium++;
												if (greaseless_lobopodium >= 3000)
													break;
											}
											Tracer.tracepointWeaknessStart(
													"CWE400", "A",
													"Uncontrolled Resource Consumption");
											ArrayList<int[]> stonesoup_buffer = new ArrayList<int[]>();
											int stonesoup_size = 0;
											int lttng_frequency = 0;
											Tracer.tracepointVariableInt(
													"stonesoup_intValue",
													mothproof_maratha[mismate_oscheolith]);
											if (mothproof_maratha[mismate_oscheolith] > 0
													&& mothproof_maratha[mismate_oscheolith] <= Integer.MAX_VALUE) {
												stonesoup_size = 10000;
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												for (int i = 0; i < mothproof_maratha[mismate_oscheolith];) {
													try {
														stonesoup_buffer
																.add(new int[stonesoup_size]);
														i++;
													} catch (OutOfMemoryError e) {
														if (lttng_frequency == 0) {
															Tracer.tracepointError(e
																	.getClass()
																	.getName()
																	+ ": "
																	+ e.getMessage());
															Tracer.tracepointBufferInfo(
																	"stonesoup_buffer",
																	stonesoup_buffer
																			.size(),
																	"Size of stonesoup_buffer");
														}
														lttng_frequency = (lttng_frequency == 199) ? 0
																: lttng_frequency + 1;
													}
												}
												Tracer.tracepointBufferInfo(
														"stonesoup_buffer",
														stonesoup_buffer.size(),
														"Size of stonesoup_buffer");
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												SmallDocumentBlock.naphthylamineIncidentalist
														.println("Allocated all the memory requested");
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException megaeraPatellar) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												megaeraPatellar);
									}
								}
							}
						}
					} finally {
						SmallDocumentBlock.naphthylamineIncidentalist.close();
					}
				}
			}
		}
		System.arraycopy(data, index * _block_size, _data, 0, _block_size);
    }

    private SmallDocumentBlock(final POIFSBigBlockSize bigBlockSize)
    {
        _bigBlockSize = bigBlockSize;
        _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        _data = new byte[ _block_size ];
    }
    
    private static int getBlocksPerBigBlock(final POIFSBigBlockSize bigBlockSize)
    {
       return bigBlockSize.getBigBlockSize() / _block_size;
    }

    /**
     * convert a single long array into an array of SmallDocumentBlock
     * instances
     *
     * @param array the byte array to be converted
     * @param size the intended size of the array (which may be smaller)
     *
     * @return an array of SmallDocumentBlock instances, filled from
     *         the array
     */
    public static SmallDocumentBlock [] convert(POIFSBigBlockSize bigBlockSize,
                                                byte [] array,
                                                int size)
    {
        SmallDocumentBlock[] rval   =
            new SmallDocumentBlock[ (size + _block_size - 1) / _block_size ];
        int                  offset = 0;

        for (int k = 0; k < rval.length; k++)
        {
            rval[ k ] = new SmallDocumentBlock(bigBlockSize);
            if (offset < array.length)
            {
                int length = Math.min(_block_size, array.length - offset);

                System.arraycopy(array, offset, rval[ k ]._data, 0, length);
                if (length != _block_size)
                {
                    Arrays.fill(rval[ k ]._data, length, _block_size,
                                _default_fill);
                }
            }
            else
            {
                Arrays.fill(rval[ k ]._data, _default_fill);
            }
            offset += _block_size;
        }
        return rval;
    }

    /**
     * fill out a List of SmallDocumentBlocks so that it fully occupies
     * a set of big blocks
     *
     * @param blocks the List to be filled out
     *
     * @return number of big blocks the list encompasses
     */
    public static int fill(POIFSBigBlockSize bigBlockSize, List blocks)
    {
        int _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        
        int count           = blocks.size();
        int big_block_count = (count + _blocks_per_big_block - 1)
                              / _blocks_per_big_block;
        int full_count      = big_block_count * _blocks_per_big_block;

        for (; count < full_count; count++)
        {
            blocks.add(makeEmptySmallDocumentBlock(bigBlockSize));
        }
        return big_block_count;
    }

    /**
     * Factory for creating SmallDocumentBlocks from DocumentBlocks
     *
     * @param store the original DocumentBlocks
     * @param size the total document size
     *
     * @return an array of new SmallDocumentBlocks instances
     *
     * @exception IOException on errors reading from the DocumentBlocks
     * @exception ArrayIndexOutOfBoundsException if, somehow, the store
     *            contains less data than size indicates
     */
    public static SmallDocumentBlock [] convert(POIFSBigBlockSize bigBlockSize,
                                                BlockWritable [] store,
                                                int size)
        throws IOException, ArrayIndexOutOfBoundsException
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        for (int j = 0; j < store.length; j++)
        {
            store[ j ].writeBlocks(stream);
        }
        byte[]               data = stream.toByteArray();
        SmallDocumentBlock[] rval =
            new SmallDocumentBlock[ convertToBlockCount(size) ];

        for (int index = 0; index < rval.length; index++)
        {
            rval[ index ] = new SmallDocumentBlock(bigBlockSize, data, index);
        }
        return rval;
    }

    /**
     * create a list of SmallDocumentBlock's from raw data
     *
     * @param blocks the raw data containing the SmallDocumentBlock
     *               data
     *
     * @return a List of SmallDocumentBlock's extracted from the input
     */
    public static List extract(POIFSBigBlockSize bigBlockSize, ListManagedBlock [] blocks)
        throws IOException
    {
        int _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        
        List sdbs = new ArrayList();

        for (int j = 0; j < blocks.length; j++)
        {
            byte[] data = blocks[ j ].getData();

            for (int k = 0; k < _blocks_per_big_block; k++)
            {
                sdbs.add(new SmallDocumentBlock(bigBlockSize, data, k));
            }
        }
        return sdbs;
    }

    public static DataInputBlock getDataInputBlock(SmallDocumentBlock[] blocks, int offset) {
        int firstBlockIndex = offset >> BLOCK_SHIFT;
        int firstBlockOffset= offset & BLOCK_MASK;
        return new DataInputBlock(blocks[firstBlockIndex]._data, firstBlockOffset);
    }

    /**
     * Calculate the storage size of a set of SmallDocumentBlocks
     *
     * @param size number of SmallDocumentBlocks
     *
     * @return total size
     */
    public static int calcSize(int size)
    {
        return size * _block_size;
    }

    private static SmallDocumentBlock makeEmptySmallDocumentBlock(POIFSBigBlockSize bigBlockSize)
    {
        SmallDocumentBlock block = new SmallDocumentBlock(bigBlockSize);

        Arrays.fill(block._data, _default_fill);
        return block;
    }

    private static int convertToBlockCount(int size)
    {
        return (size + _block_size - 1) / _block_size;
    }

    /**
     * Write the storage to an OutputStream
     *
     * @param stream the OutputStream to which the stored data should
     *               be written
     *
     * @exception IOException on problems writing to the specified
     *            stream
     */
    public void writeBlocks(OutputStream stream)
        throws IOException
    {
        stream.write(_data);
    }

    /**
     * Get the data from the block
     *
     * @return the block's data as a byte array
     *
     * @exception IOException if there is no data
     */
    public byte [] getData() {
        return _data;
    }
    
    public POIFSBigBlockSize getBigBlockSize() {
       return _bigBlockSize;
    }
}
