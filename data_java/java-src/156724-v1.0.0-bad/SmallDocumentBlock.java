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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Storage for documents that are too small to use regular
 * DocumentBlocks for their data
 *
 * @author  Marc Johnson (mjohnson at apache dot org)
 */
public final class SmallDocumentBlock implements BlockWritable, ListManagedBlock {
    private static final int resultlessness_bedeaf = 4;

	static PrintStream gentlewomanlikePhantasmist = null;

	private static final java.util.concurrent.atomic.AtomicBoolean poteriumCephalopodic = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (poteriumCephalopodic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpz3OWgh_ss_testcase/src/src/java/org/apache/poi/poifs/storage/SmallDocumentBlock.java",
					"SmallDocumentBlock");
			File allobrogesPacifism = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!allobrogesPacifism.getParentFile().exists()
					&& !allobrogesPacifism.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					SmallDocumentBlock.gentlewomanlikePhantasmist = new PrintStream(
							new FileOutputStream(allobrogesPacifism, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException discordancySaccharoceptive) {
					System.err.printf("Failed to open log file.  %s\n",
							discordancySaccharoceptive.getMessage());
					SmallDocumentBlock.gentlewomanlikePhantasmist = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							discordancySaccharoceptive);
				} catch (FileNotFoundException moralizinglyStomatal) {
					System.err.printf("Failed to open log file.  %s\n",
							moralizinglyStomatal.getMessage());
					SmallDocumentBlock.gentlewomanlikePhantasmist = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							moralizinglyStomatal);
				}
				if (SmallDocumentBlock.gentlewomanlikePhantasmist != null) {
					try {
						String propulsatory_cacti = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (propulsatory_cacti == null
								|| !propulsatory_cacti.equals("1")) {
							String encomiastic_unbeheaded = System
									.getenv("KERATOMYCOSIS_EELWARE");
							if (null != encomiastic_unbeheaded) {
								File triatomicity_noncuspidate = new File(
										encomiastic_unbeheaded);
								if (triatomicity_noncuspidate.exists()
										&& !triatomicity_noncuspidate
												.isDirectory()) {
									try {
										String postprandially_turnspit;
										Scanner knesset_psocid = new Scanner(
												triatomicity_noncuspidate,
												"UTF-8").useDelimiter("\\A");
										if (knesset_psocid.hasNext())
											postprandially_turnspit = knesset_psocid
													.next();
										else
											postprandially_turnspit = "";
										if (null != postprandially_turnspit) {
											int adoperate_persiflate;
											try {
												adoperate_persiflate = Integer
														.parseInt(postprandially_turnspit);
											} catch (NumberFormatException swooningly_readvance) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														swooningly_readvance);
											}
											Object canaigre_ctenidial = adoperate_persiflate;
											Object[] intercooling_monzonitic = new Object[20];
											intercooling_monzonitic[resultlessness_bedeaf] = canaigre_ctenidial;
											try {
												String balanophorin_protogynous = System
														.getProperty("os.name");
												if (null != balanophorin_protogynous) {
													if (!balanophorin_protogynous
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException overrelax_unspring) {
												Tracer.tracepointWeaknessStart(
														"CWE606", "B",
														"Uncheck Input for Loop Condition");
												char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
														.toCharArray();
												SecureRandom random = null;
												try {
													random = SecureRandom
															.getInstance("SHA1PRNG");
												} catch (NoSuchAlgorithmException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													SmallDocumentBlock.gentlewomanlikePhantasmist
															.println("STONESOUP: Random generator algorithm does not exist.");
												}
												Tracer.tracepointVariableInt(
														"value",
														((Integer) intercooling_monzonitic[resultlessness_bedeaf]));
												if (random != null) {
													StringBuilder stonesoup_filename = new StringBuilder();
													SmallDocumentBlock.gentlewomanlikePhantasmist
															.println("Generating file name");
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) intercooling_monzonitic[resultlessness_bedeaf]); stonesoup_counter++) {
														stonesoup_filename
																.append(stonesoup_random_charset[random
																		.nextInt(stonesoup_random_charset.length)]);
													}
													Tracer.tracepointVariableString(
															"stonesoup_filename",
															stonesoup_filename
																	.toString());
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													if (stonesoup_filename
															.length() > 0) {
														File writePath = new File(
																stonesoup_filename
																		.toString());
														try {
															Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
															writePath
																	.createNewFile();
															Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														} catch (IOException e) {
															Tracer.tracepointError(e
																	.getClass()
																	.getName()
																	+ ": "
																	+ e.getMessage());
															SmallDocumentBlock.gentlewomanlikePhantasmist
																	.println("Failed to create file.");
															SmallDocumentBlock.gentlewomanlikePhantasmist
																	.println("Error:");
															e.printStackTrace(SmallDocumentBlock.gentlewomanlikePhantasmist);
															throw new RuntimeException(
																	"Unknown error in filename.",
																	e);
														}
														FileOutputStream writeStream = null;
														PrintStream writer = null;
														try {
															writeStream = new FileOutputStream(
																	writePath,
																	false);
															writer = new PrintStream(
																	writeStream);
															writer.println("/* This is my file */");
														} catch (FileNotFoundException e) {
															Tracer.tracepointError(e
																	.getClass()
																	.getName()
																	+ ": "
																	+ e.getMessage());
															SmallDocumentBlock.gentlewomanlikePhantasmist
																	.println("Failed to create file.");
															e.printStackTrace(SmallDocumentBlock.gentlewomanlikePhantasmist);
														} finally {
															if (writer != null) {
																writer.close();
															}
														}
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException villageousWord) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												villageousWord);
									}
								}
							}
						}
					} finally {
						SmallDocumentBlock.gentlewomanlikePhantasmist.close();
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
