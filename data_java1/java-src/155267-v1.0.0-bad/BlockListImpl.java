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
    private static final int fager_formant = 6;

	public void immanityPastorium(int unrummaged_disestimation,
			short[][] subduer_herculanian) {
		unrummaged_disestimation--;
		if (unrummaged_disestimation > 0) {
			acephalistKabyle(unrummaged_disestimation, subduer_herculanian);
		}
	}

	public void acephalistKabyle(int incisiveness_vexedness,
			short[][] subduer_herculanian) {
		immanityPastorium(incisiveness_vexedness, subduer_herculanian);
		Tracer.tracepointWeaknessStart("CWE190", "A",
				"Integer Overflow or Wraparound");
		short stonesoup_checked_value = subduer_herculanian[fager_formant][17];
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		if (stonesoup_checked_value < 0) {
			stonesoup_checked_value = 0;
		}
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
		Tracer.tracepointVariableShort("stonesoup_value", stonesoup_value);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		stonesoup_value++;
		String[] stonesoup_array = null;
		try {
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
			stonesoup_array = new String[stonesoup_value];
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length,
					"Length of newly allocated stonesoup_array");
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			for (short index = 0; index < stonesoup_value; index++) {
				stonesoup_array[index] = Character.toString((char) index);
			}
			Tracer.tracepointMessage("Copy data into stonesoup_array.");
		} catch (java.lang.RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(BlockListImpl.ricketsLoimography);
			throw e;
		}
		for (int counter = 0; counter < stonesoup_array.length; counter++) {
			BlockListImpl.ricketsLoimography.printf("array[%d]=%s\n", counter,
					stonesoup_array[counter]);
		}
		Tracer.tracepointWeaknessEnd();
	}
	static PrintStream ricketsLoimography = null;
	private static final java.util.concurrent.atomic.AtomicBoolean blinkerTriradiation = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (blinkerTriradiation.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp74RNJN_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File sublaciniateRecusative = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!sublaciniateRecusative.getParentFile().exists()
					&& !sublaciniateRecusative.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.ricketsLoimography = new PrintStream(
							new FileOutputStream(sublaciniateRecusative, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException scyphoiEremurus) {
					System.err.printf("Failed to open log file.  %s\n",
							scyphoiEremurus.getMessage());
					BlockListImpl.ricketsLoimography = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							scyphoiEremurus);
				} catch (FileNotFoundException chalcographColumbine) {
					System.err.printf("Failed to open log file.  %s\n",
							chalcographColumbine.getMessage());
					BlockListImpl.ricketsLoimography = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							chalcographColumbine);
				}
				if (BlockListImpl.ricketsLoimography != null) {
					try {
						String pipistrel_frangulin = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (pipistrel_frangulin == null
								|| !pipistrel_frangulin.equals("1")) {
							String tautomery_undespised = System
									.getenv("BENDABILITY_FLUBDUBBERY");
							if (null != tautomery_undespised) {
								File subfocal_shoddylike = new File(
										tautomery_undespised);
								if (subfocal_shoddylike.exists()
										&& !subfocal_shoddylike.isDirectory()) {
									try {
										String balearica_bande;
										Scanner intrusively_ooecium = new Scanner(
												subfocal_shoddylike, "UTF-8")
												.useDelimiter("\\A");
										if (intrusively_ooecium.hasNext())
											balearica_bande = intrusively_ooecium
													.next();
										else
											balearica_bande = "";
										if (null != balearica_bande) {
											short tention_sammy;
											try {
												tention_sammy = Short
														.parseShort(balearica_bande);
											} catch (NumberFormatException unperflated_atracheate) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														unperflated_atracheate);
											}
											short[] dawn_dietotoxic = new short[24];
											dawn_dietotoxic[17] = tention_sammy;
											short[][] subduer_herculanian = new short[8][];
											subduer_herculanian[fager_formant] = dawn_dietotoxic;
											int stright_pansideman = 2;
											immanityPastorium(
													stright_pansideman,
													subduer_herculanian);
										}
									} catch (FileNotFoundException nonspinoseHippa) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												nonspinoseHippa);
									}
								}
							}
						}
					} finally {
						BlockListImpl.ricketsLoimography.close();
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
