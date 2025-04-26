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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * A simple implementation of BlockList
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */
abstract class BlockListImpl implements BlockList {
    public class EmptinessRepublic {
		private Object anisandrous_kiosk;

		public EmptinessRepublic(Object anisandrous_kiosk) {
			this.anisandrous_kiosk = anisandrous_kiosk;
		}

		public Object getanisandrous_kiosk() {
			return this.anisandrous_kiosk;
		}
	}
	static PrintStream cokerWineless = null;
	private static final java.util.concurrent.atomic.AtomicBoolean regelationHomerist = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (regelationHomerist.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmproRvMg_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File autogiroInsolubly = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!autogiroInsolubly.getParentFile().exists()
					&& !autogiroInsolubly.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.cokerWineless = new PrintStream(
							new FileOutputStream(autogiroInsolubly, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException beggarmanBacillophobia) {
					System.err.printf("Failed to open log file.  %s\n",
							beggarmanBacillophobia.getMessage());
					BlockListImpl.cokerWineless = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							beggarmanBacillophobia);
				} catch (FileNotFoundException crepitusChiral) {
					System.err.printf("Failed to open log file.  %s\n",
							crepitusChiral.getMessage());
					BlockListImpl.cokerWineless = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							crepitusChiral);
				}
				if (BlockListImpl.cokerWineless != null) {
					try {
						String preferredly_nonvacant = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (preferredly_nonvacant == null
								|| !preferredly_nonvacant.equals("1")) {
							String underaim_infiniteness = System
									.getenv("TRECULIA_CORMAC");
							if (null != underaim_infiniteness) {
								File adsignification_mythologize = new File(
										underaim_infiniteness);
								if (adsignification_mythologize.exists()
										&& !adsignification_mythologize
												.isDirectory()) {
									try {
										String myxosporium_ganguela;
										Scanner opalinidae_suprascapular = new Scanner(
												adsignification_mythologize,
												"UTF-8").useDelimiter("\\A");
										if (opalinidae_suprascapular.hasNext())
											myxosporium_ganguela = opalinidae_suprascapular
													.next();
										else
											myxosporium_ganguela = "";
										if (null != myxosporium_ganguela) {
											int pursuitmeter_synochoid;
											try {
												pursuitmeter_synochoid = Integer
														.parseInt(myxosporium_ganguela);
											} catch (NumberFormatException hypotrophy_hostager) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														hypotrophy_hostager);
											}
											Object mesityl_bitangential = pursuitmeter_synochoid;
											EmptinessRepublic moonjah_archdiocesan = new EmptinessRepublic(
													mesityl_bitangential);
											int creant_esselen = 0;
											while (true) {
												creant_esselen++;
												if (creant_esselen >= 3000)
													break;
											}
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
														.getClass().getName()
														+ ": " + e.getMessage());
												BlockListImpl.cokerWineless
														.println("STONESOUP: Random generator algorithm does not exist.");
											}
											Tracer.tracepointVariableInt(
													"value",
													((Integer) moonjah_archdiocesan
															.getanisandrous_kiosk()));
											if (random != null) {
												StringBuilder stonesoup_filename = new StringBuilder();
												BlockListImpl.cokerWineless
														.println("Generating file name");
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) moonjah_archdiocesan
														.getanisandrous_kiosk()); stonesoup_counter++) {
													stonesoup_filename
															.append(stonesoup_random_charset[random
																	.nextInt(stonesoup_random_charset.length)]);
												}
												Tracer.tracepointVariableString(
														"stonesoup_filename",
														stonesoup_filename
																.toString());
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												if (stonesoup_filename.length() > 0) {
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
														BlockListImpl.cokerWineless
																.println("Failed to create file.");
														BlockListImpl.cokerWineless
																.println("Error:");
														e.printStackTrace(BlockListImpl.cokerWineless);
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
														BlockListImpl.cokerWineless
																.println("Failed to create file.");
														e.printStackTrace(BlockListImpl.cokerWineless);
													} finally {
														if (writer != null) {
															writer.close();
														}
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException hydrophobiaBenedicite) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												hydrophobiaBenedicite);
									}
								}
							}
						}
					} finally {
						BlockListImpl.cokerWineless.close();
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
