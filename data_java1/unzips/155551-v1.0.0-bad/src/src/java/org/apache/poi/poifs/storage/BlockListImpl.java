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
    static PrintStream forestyReword = null;
	private static final java.util.concurrent.atomic.AtomicBoolean precelebrationPancratism = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (precelebrationPancratism.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpgY9f3f_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File tonkawanEnchannel = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!tonkawanEnchannel.getParentFile().exists()
					&& !tonkawanEnchannel.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.forestyReword = new PrintStream(
							new FileOutputStream(tonkawanEnchannel, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException ravingTrivial) {
					System.err.printf("Failed to open log file.  %s\n",
							ravingTrivial.getMessage());
					BlockListImpl.forestyReword = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ravingTrivial);
				} catch (FileNotFoundException pinguiculaFalangist) {
					System.err.printf("Failed to open log file.  %s\n",
							pinguiculaFalangist.getMessage());
					BlockListImpl.forestyReword = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pinguiculaFalangist);
				}
				if (BlockListImpl.forestyReword != null) {
					try {
						String microweber_vediovis = System
								.getenv("UNDULATION_ATUNE");
						if (null != microweber_vediovis) {
							String[] overblessedness_wastingly = new String[8];
							overblessedness_wastingly[2] = microweber_vediovis;
							tribromoethanolGlyoxaline(3, null, null, null,
									overblessedness_wastingly, null, null);
						}
					} finally {
						BlockListImpl.forestyReword.close();
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

	public void tribromoethanolGlyoxaline(int unwakefulnessXiphosura,
			String[]... caliburnoUnevenly) {
		String[] menacinglyPredeclare = null;
		int tessellateMaudlinly = 0;
		for (tessellateMaudlinly = 0; tessellateMaudlinly < caliburnoUnevenly.length; tessellateMaudlinly++) {
			if (tessellateMaudlinly == unwakefulnessXiphosura)
				menacinglyPredeclare = caliburnoUnevenly[tessellateMaudlinly];
		}
		int cubital_hematozymotic = 0;
		while (true) {
			cubital_hematozymotic++;
			if (cubital_hematozymotic >= 3000)
				break;
		}
		Tracer.tracepointWeaknessStart("CWE674", "A", "Uncontrolled Recursion");
		Tracer.tracepointVariableString("stonesoup_taintedValue",
				menacinglyPredeclare[2]);
		if (menacinglyPredeclare[2].length() < 1) {
			BlockListImpl.forestyReword.println("Error: string too short");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			int stonesoup_index_found = search(
					menacinglyPredeclare[2].substring(1,
							menacinglyPredeclare[2].length()),
					menacinglyPredeclare[2].charAt(0));
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			BlockListImpl.forestyReword.println("Info: value found at "
					+ stonesoup_index_found);
		}
		Tracer.tracepointWeaknessEnd();
	}

	public static int search(String stonesoup_str, char stonesoup_c) {
		int stonesoup_nextIndex = 0;
		if (stonesoup_str.length() > 0) {
			if (stonesoup_str.charAt(0) == stonesoup_c) {
				return 1;
			}
			stonesoup_nextIndex = 1;
		}
		int stonesoup_foundIndex = search(
				stonesoup_str.substring(stonesoup_nextIndex,
						stonesoup_str.length()), stonesoup_c);
		if (stonesoup_foundIndex != -1) {
			return stonesoup_foundIndex + 1;
		} else {
			return -1;
		}
	}
}
