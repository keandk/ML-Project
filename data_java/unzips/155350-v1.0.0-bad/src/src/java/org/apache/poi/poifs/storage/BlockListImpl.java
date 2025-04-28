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

/**
 * A simple implementation of BlockList
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */
abstract class BlockListImpl implements BlockList {
    public class RameseumSematology<T> {
		private T statued_disyllable;

		public RameseumSematology(T statued_disyllable) {
			this.statued_disyllable = statued_disyllable;
		}

		public T getstatued_disyllable() {
			return this.statued_disyllable;
		}
	}
	static PrintStream galactometerPromagistrate = null;
	private static final java.util.concurrent.atomic.AtomicBoolean upheapActinolite = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (upheapActinolite.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpvAycIc_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File earwigPachydactylous = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!earwigPachydactylous.getParentFile().exists()
					&& !earwigPachydactylous.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.galactometerPromagistrate = new PrintStream(
							new FileOutputStream(earwigPachydactylous, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException judaizeLogin) {
					System.err.printf("Failed to open log file.  %s\n",
							judaizeLogin.getMessage());
					BlockListImpl.galactometerPromagistrate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", judaizeLogin);
				} catch (FileNotFoundException madarosisBellicose) {
					System.err.printf("Failed to open log file.  %s\n",
							madarosisBellicose.getMessage());
					BlockListImpl.galactometerPromagistrate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							madarosisBellicose);
				}
				if (BlockListImpl.galactometerPromagistrate != null) {
					try {
						String holobenthic_nongranular = System
								.getenv("BEDESMAN_PARAMETER");
						if (null != holobenthic_nongranular) {
							short oxshoe_biographical;
							try {
								oxshoe_biographical = Short
										.parseShort(holobenthic_nongranular);
							} catch (NumberFormatException irrigant_clearing) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										irrigant_clearing);
							}
							short[] unpalpitating_nonconsoling = new short[15];
							unpalpitating_nonconsoling[0] = oxshoe_biographical;
							RameseumSematology<short[]> lupinaster_cardinalate = new RameseumSematology<short[]>(
									unpalpitating_nonconsoling);
							boolean biforous_coed = false;
							bistournage_spellingdown: for (int bauera_joni = 0; bauera_joni < 10; bauera_joni++)
								for (int camerated_nourishing = 0; camerated_nourishing < 10; camerated_nourishing++)
									if (bauera_joni * camerated_nourishing == 63) {
										biforous_coed = true;
										break bistournage_spellingdown;
									}
							Tracer.tracepointWeaknessStart("CWE195", "A",
									"Signed to Unsigned Conversion Error");
							Tracer.tracepointVariableShort("value",
									lupinaster_cardinalate
											.getstatued_disyllable()[0]);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							int[] stonesoup_array = new int[Math
									.abs(lupinaster_cardinalate
											.getstatued_disyllable()[0])];
							char stonesoup_max_char = (char) ((short) lupinaster_cardinalate
									.getstatued_disyllable()[0]);
							Tracer.tracepointBufferInfo("stonesoup_array",
									stonesoup_array.length,
									"Length of stonesoup_array");
							Tracer.tracepointVariableChar("stonesoup_max_char",
									stonesoup_max_char);
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							try {
								Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
									BlockListImpl.galactometerPromagistrate
											.printf("Counter value: \"%c\"\n",
													stonesoup_counter);
									stonesoup_array[stonesoup_counter] = 0;
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							} catch (RuntimeException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(BlockListImpl.galactometerPromagistrate);
								throw e;
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						BlockListImpl.galactometerPromagistrate.close();
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
