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
import java.util.ArrayList;

/**
 * A simple implementation of BlockList
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */
abstract class BlockListImpl implements BlockList {
    static PrintStream berberisSerbdom = null;
	private static final java.util.concurrent.atomic.AtomicBoolean amianthineClodlet = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (amianthineClodlet.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmptPNEgf_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File criminouslyGlucosine = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!criminouslyGlucosine.getParentFile().exists()
					&& !criminouslyGlucosine.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.berberisSerbdom = new PrintStream(
							new FileOutputStream(criminouslyGlucosine, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException perigynousPhotophobia) {
					System.err.printf("Failed to open log file.  %s\n",
							perigynousPhotophobia.getMessage());
					BlockListImpl.berberisSerbdom = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							perigynousPhotophobia);
				} catch (FileNotFoundException nonvisualOverwoven) {
					System.err.printf("Failed to open log file.  %s\n",
							nonvisualOverwoven.getMessage());
					BlockListImpl.berberisSerbdom = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nonvisualOverwoven);
				}
				if (BlockListImpl.berberisSerbdom != null) {
					try {
						String woodhole_emergently = System
								.getenv("SCOURGER_REGULAR");
						if (null != woodhole_emergently) {
							int shilfa_hyrachyus;
							try {
								shilfa_hyrachyus = Integer
										.parseInt(woodhole_emergently);
							} catch (NumberFormatException unsearchable_hypochnose) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										unsearchable_hypochnose);
							}
							Object combinement_jointweed = shilfa_hyrachyus;
							planateScrum(3, null, null, null,
									combinement_jointweed, null, null);
						}
					} finally {
						BlockListImpl.berberisSerbdom.close();
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

	public void planateScrum(int homozygoteFerineness,Object... mescalineSavorless){Object preimpairmentUnbrooded=null;int balsamizeColoboma=0;for (balsamizeColoboma=0;balsamizeColoboma < mescalineSavorless.length;balsamizeColoboma++){if (balsamizeColoboma == homozygoteFerineness)preimpairmentUnbrooded=mescalineSavorless[balsamizeColoboma];}try {String calcic_aurorium=System.getProperty("os.name");if (null != calcic_aurorium){if (!calcic_aurorium.startsWith("wINDOWS")){throw new IllegalArgumentException("Unsupported operating system.");}}} catch (IllegalArgumentException timekeeper_plenipotentiary){Tracer.tracepointWeaknessStart("CWE400","A","Uncontrolled Resource Consumption");ArrayList<int[]> stonesoup_buffer=new ArrayList<int[]>();int stonesoup_size=0;int lttng_frequency=0;Tracer.tracepointVariableInt("stonesoup_intValue",((Integer)preimpairmentUnbrooded));if (((Integer)preimpairmentUnbrooded) > 0 && ((Integer)preimpairmentUnbrooded) <= Integer.MAX_VALUE){stonesoup_size=10000;Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");for (int i=0;i < ((Integer)preimpairmentUnbrooded);){try {stonesoup_buffer.add(new int[stonesoup_size]);i++;} catch (OutOfMemoryError e){if (lttng_frequency == 0){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");}lttng_frequency=(lttng_frequency == 199)?0:lttng_frequency + 1;}}Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");Tracer.tracepointMessage("TRIGGER-POINT: AFTER");Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");BlockListImpl.berberisSerbdom.println("Allocated all the memory requested");}Tracer.tracepointWeaknessEnd();}}
}
