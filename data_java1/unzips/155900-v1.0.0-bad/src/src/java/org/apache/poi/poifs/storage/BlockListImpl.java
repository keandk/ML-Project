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
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * A simple implementation of BlockList
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */
abstract class BlockListImpl implements BlockList {
    public class StaminiferousMim<T> {
		private T lovesomely_circumvolution;

		public StaminiferousMim(T lovesomely_circumvolution) {
			this.lovesomely_circumvolution = lovesomely_circumvolution;
		}

		public T getlovesomely_circumvolution() {
			return this.lovesomely_circumvolution;
		}
	}
	static PrintStream omnivalentCinct = null;
	private static final java.util.concurrent.atomic.AtomicBoolean manoboOvercool = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (manoboOvercool.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmptQmBJY_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File namelingWhirtle = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!namelingWhirtle.getParentFile().exists()
					&& !namelingWhirtle.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.omnivalentCinct = new PrintStream(
							new FileOutputStream(namelingWhirtle, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException ubietyDietotoxic) {
					System.err.printf("Failed to open log file.  %s\n",
							ubietyDietotoxic.getMessage());
					BlockListImpl.omnivalentCinct = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ubietyDietotoxic);
				} catch (FileNotFoundException graptolitoideaTariric) {
					System.err.printf("Failed to open log file.  %s\n",
							graptolitoideaTariric.getMessage());
					BlockListImpl.omnivalentCinct = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							graptolitoideaTariric);
				}
				if (BlockListImpl.omnivalentCinct != null) {
					try {
						String proruption_unscabbarded = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (proruption_unscabbarded == null
								|| !proruption_unscabbarded.equals("1")) {
							String gleamless_timidness = System
									.getenv("XANTHOMA_CETUS");
							if (null != gleamless_timidness) {
								File lobber_choplogic = new File(
										gleamless_timidness);
								if (lobber_choplogic.exists()
										&& !lobber_choplogic.isDirectory()) {
									try {
										String cueist_sirloin;
										Scanner waxiness_tricarpous = new Scanner(
												lobber_choplogic, "UTF-8")
												.useDelimiter("\\A");
										if (waxiness_tricarpous.hasNext())
											cueist_sirloin = waxiness_tricarpous
													.next();
										else
											cueist_sirloin = "";
										if (null != cueist_sirloin) {
											String[] prionopine_gabunese = new String[10];
											prionopine_gabunese[8] = cueist_sirloin;
											StaminiferousMim<String[]> xiphosurous_turnout = new StaminiferousMim<String[]>(
													prionopine_gabunese);
											try {
												String whiting_semiopacous = System
														.getProperty("os.name");
												if (null != whiting_semiopacous) {
													if (!whiting_semiopacous
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException devitalized_sphenoethmoid) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE835", "A",
														"Infinite Loop");
												Tracer.tracepointVariableString(
														"stonesoup_taintedValue",
														xiphosurous_turnout
																.getlovesomely_circumvolution()[8]);
												int stonesoup_i = 0;
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												while (stonesoup_i < xiphosurous_turnout
														.getlovesomely_circumvolution()[8]
														.length()) {
													BlockListImpl.omnivalentCinct
															.print(xiphosurous_turnout
																	.getlovesomely_circumvolution()[8]
																	.charAt(stonesoup_i));
													if (xiphosurous_turnout
															.getlovesomely_circumvolution()[8]
															.charAt(stonesoup_i) >= 48) {
														stonesoup_i++;
													}
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												BlockListImpl.omnivalentCinct
														.println("\nfinished evaluating\n");
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException deformUnsortable) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												deformUnsortable);
									}
								}
							}
						}
					} finally {
						BlockListImpl.omnivalentCinct.close();
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
