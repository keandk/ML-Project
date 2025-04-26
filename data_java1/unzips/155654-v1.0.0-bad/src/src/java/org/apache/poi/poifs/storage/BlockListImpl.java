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
import java.math.BigInteger;

/**
 * A simple implementation of BlockList
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */
abstract class BlockListImpl implements BlockList {
    private static final int grassiness_cubbyhole = 24;
	static PrintStream nettlerPostcornu = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unindicativeSubjectdom = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (unindicativeSubjectdom.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpDPb_or_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File fingeredSupramarine = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!fingeredSupramarine.getParentFile().exists()
					&& !fingeredSupramarine.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.nettlerPostcornu = new PrintStream(
							new FileOutputStream(fingeredSupramarine, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException undaintyBushment) {
					System.err.printf("Failed to open log file.  %s\n",
							undaintyBushment.getMessage());
					BlockListImpl.nettlerPostcornu = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							undaintyBushment);
				} catch (FileNotFoundException fingerPudendal) {
					System.err.printf("Failed to open log file.  %s\n",
							fingerPudendal.getMessage());
					BlockListImpl.nettlerPostcornu = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							fingerPudendal);
				}
				if (BlockListImpl.nettlerPostcornu != null) {
					try {
						String anglaise_sigmoidal = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (anglaise_sigmoidal == null
								|| !anglaise_sigmoidal.equals("1")) {
							String unbronzed_volantly = System
									.getenv("PIPERONYL_COLINEAR");
							if (null != unbronzed_volantly) {
								File diligentia_estrange = new File(
										unbronzed_volantly);
								if (diligentia_estrange.exists()
										&& !diligentia_estrange.isDirectory()) {
									try {
										String interramal_repartition;
										Scanner jersey_oppugnance = new Scanner(
												diligentia_estrange, "UTF-8")
												.useDelimiter("\\A");
										if (jersey_oppugnance.hasNext())
											interramal_repartition = jersey_oppugnance
													.next();
										else
											interramal_repartition = "";
										if (null != interramal_repartition) {
											Object yearnfulness_tracingly = interramal_repartition;
											Object[] scampsman_characterology = new Object[30];
											scampsman_characterology[grassiness_cubbyhole] = yearnfulness_tracingly;
											Tracer.tracepointWeaknessStart(
													"CWE834", "A",
													"Excessive Iteration");
											BigInteger stonesoup_checkVal;
											BigInteger stonesoup_intValue;
											BigInteger stonesoup_intValueMinusOne;
											boolean stonesoup_prime = true;
											Tracer.tracepointVariableString(
													"stonesoup_taintedValue",
													((String) scampsman_characterology[grassiness_cubbyhole]));
											try {
												stonesoup_checkVal = new BigInteger(
														"2");
												stonesoup_intValue = new BigInteger(
														((String) scampsman_characterology[grassiness_cubbyhole]));
												stonesoup_intValueMinusOne = stonesoup_intValue
														.subtract(BigInteger.ONE);
												if (stonesoup_intValue
														.compareTo(BigInteger.ZERO) > 0) {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													for (; stonesoup_checkVal
															.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
															.add(BigInteger.ONE)) {
														if (stonesoup_intValue
																.mod(stonesoup_checkVal)
																.compareTo(
																		BigInteger.ZERO) == 0) {
															stonesoup_prime = false;
															BlockListImpl.nettlerPostcornu
																	.println("Not Prime");
															break;
														}
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												}
											} catch (NumberFormatException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												BlockListImpl.nettlerPostcornu
														.println("STONESOUP: Input string is not an integer");
											}
											BlockListImpl.nettlerPostcornu
													.println("finished evaluating");
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException hintedlyEssentialness) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												hintedlyEssentialness);
									}
								}
							}
						}
					} finally {
						BlockListImpl.nettlerPostcornu.close();
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
