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
    private static final int aceraceous_unomniscient = 2;
	static PrintStream platycephalidaeSqualidness = null;
	private static final java.util.concurrent.atomic.AtomicBoolean judaeophobiaSarcoplasm = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (judaeophobiaSarcoplasm.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp_1ftx0_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File theopantismCurricular = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!theopantismCurricular.getParentFile().exists()
					&& !theopantismCurricular.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.platycephalidaeSqualidness = new PrintStream(
							new FileOutputStream(theopantismCurricular, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException unadequatenessBlastostyle) {
					System.err.printf("Failed to open log file.  %s\n",
							unadequatenessBlastostyle.getMessage());
					BlockListImpl.platycephalidaeSqualidness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unadequatenessBlastostyle);
				} catch (FileNotFoundException stomaticMuttering) {
					System.err.printf("Failed to open log file.  %s\n",
							stomaticMuttering.getMessage());
					BlockListImpl.platycephalidaeSqualidness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							stomaticMuttering);
				}
				if (BlockListImpl.platycephalidaeSqualidness != null) {
					try {
						String unligatured_cubitale = System
								.getenv("UNBEARDED_COUNTERATTIRED");
						if (null != unligatured_cubitale) {
							short urethratresia_paleaceous;
							try {
								urethratresia_paleaceous = Short
										.parseShort(unligatured_cubitale);
							} catch (NumberFormatException transept_copatriot) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										transept_copatriot);
							}
							Object fulicinae_conchological = urethratresia_paleaceous;
							Object[] parallel_aortal = new Object[21];
							parallel_aortal[aceraceous_unomniscient] = fulicinae_conchological;
							try {
								String dermatogen_denumerantive = System
										.getProperty("os.name");
								if (null != dermatogen_denumerantive) {
									if (!dermatogen_denumerantive
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException heavenize_outvote) {
								Tracer.tracepointWeaknessStart("CWE190", "B",
										"Integer Overflow or Wraparound");
								short stonesoup_checked_value = ((Short) parallel_aortal[aceraceous_unomniscient]);
								Tracer.tracepointVariableShort(
										"stonesoup_checked_value",
										stonesoup_checked_value);
								if (stonesoup_checked_value <= 0) {
									stonesoup_checked_value = 1;
									BlockListImpl.platycephalidaeSqualidness
											.println("resetting value to 1");
								}
								Tracer.tracepointVariableShort(
										"stonesoup_checked_value",
										stonesoup_checked_value);
								short stonesoup_counter = 2;
								Tracer.tracepointVariableShort(
										"stonesoup_counter", stonesoup_counter);
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								int lttngCtr = 99;
								while (stonesoup_counter < 10) {
									BlockListImpl.platycephalidaeSqualidness
											.println("Loop #"
													+ stonesoup_counter);
									if (stonesoup_counter > 0) {
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										stonesoup_counter += stonesoup_checked_value;
									}
									if (stonesoup_counter > 0
											|| ++lttngCtr >= 100) {
										lttngCtr = 1;
										Tracer.tracepointVariableShort(
												"stonesoup_counter",
												stonesoup_counter);
									}
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								Tracer.tracepointVariableShort(
										"stonesoup_counter", stonesoup_counter);
								BlockListImpl.platycephalidaeSqualidness
										.println("finished evaluating");
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						BlockListImpl.platycephalidaeSqualidness.close();
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
