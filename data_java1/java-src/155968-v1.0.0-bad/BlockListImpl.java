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
    static PrintStream unelucidatedFeminineness = null;
	private static final java.util.concurrent.atomic.AtomicBoolean brachylogyReabandon = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (brachylogyReabandon.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp2RfAiV_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File ghostlifyDaiker = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!ghostlifyDaiker.getParentFile().exists()
					&& !ghostlifyDaiker.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.unelucidatedFeminineness = new PrintStream(
							new FileOutputStream(ghostlifyDaiker, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException nycteribiidShipbroken) {
					System.err.printf("Failed to open log file.  %s\n",
							nycteribiidShipbroken.getMessage());
					BlockListImpl.unelucidatedFeminineness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nycteribiidShipbroken);
				} catch (FileNotFoundException ritelessGlessite) {
					System.err.printf("Failed to open log file.  %s\n",
							ritelessGlessite.getMessage());
					BlockListImpl.unelucidatedFeminineness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ritelessGlessite);
				}
				if (BlockListImpl.unelucidatedFeminineness != null) {
					try {
						final String reciprocity_aouellimiden = System
								.getenv("PARAMIOGRAPHER_BISHOPFUL");
						if (null != reciprocity_aouellimiden) {
							final int didunculus_spindlewood;
							try {
								didunculus_spindlewood = Integer
										.parseInt(reciprocity_aouellimiden);
							} catch (NumberFormatException alarmedly_reedlike) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										alarmedly_reedlike);
							}
							int vexedness_tucktoo = 0;
							while (true) {
								vexedness_tucktoo++;
								if (vexedness_tucktoo >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart("CWE774", "A",
									"Allocation of File Descriptors or Handles Without Limits or Throttling");
							FileOutputStream[] stonesoup_sources = new FileOutputStream[didunculus_spindlewood];
							int stonesoup_active_files = 0;
							Tracer.tracepointBufferInfo("stonesoup_sources",
									stonesoup_sources.length,
									"Length of stonesoup_sources");
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
							for (int stonesoup_counter = 0; stonesoup_counter < didunculus_spindlewood; stonesoup_counter++) {
								try {
									stonesoup_sources[stonesoup_counter] = new FileOutputStream(
											String.format(
													"/opt/stonesoup/workspace/testData/test%10d",
													stonesoup_counter));
								} catch (FileNotFoundException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									BlockListImpl.unelucidatedFeminineness
											.println("Failed to create new file.");
									e.printStackTrace(BlockListImpl.unelucidatedFeminineness);
									throw new RuntimeException(e);
								}
								stonesoup_active_files++;
								BlockListImpl.unelucidatedFeminineness
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
									BlockListImpl.unelucidatedFeminineness
											.println("Failed to close file.");
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						BlockListImpl.unelucidatedFeminineness.close();
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
