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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * A simple implementation of BlockList
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */
abstract class BlockListImpl implements BlockList {
    public class DumontiaceaeParlando {
		private Object increpate_vermilionette;

		public DumontiaceaeParlando(Object increpate_vermilionette) {
			this.increpate_vermilionette = increpate_vermilionette;
		}

		public Object getincrepate_vermilionette() {
			return this.increpate_vermilionette;
		}
	}
	static PrintStream anoilInstall = null;
	private static final java.util.concurrent.atomic.AtomicBoolean arengaAsymbolical = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (arengaAsymbolical.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpb4OVNA_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File promotablePieshop = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!promotablePieshop.getParentFile().exists()
					&& !promotablePieshop.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.anoilInstall = new PrintStream(
							new FileOutputStream(promotablePieshop, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException prepinkDropwort) {
					System.err.printf("Failed to open log file.  %s\n",
							prepinkDropwort.getMessage());
					BlockListImpl.anoilInstall = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							prepinkDropwort);
				} catch (FileNotFoundException taffetaCaponier) {
					System.err.printf("Failed to open log file.  %s\n",
							taffetaCaponier.getMessage());
					BlockListImpl.anoilInstall = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							taffetaCaponier);
				}
				if (BlockListImpl.anoilInstall != null) {
					try {
						String milter_condylomatous = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (milter_condylomatous == null
								|| !milter_condylomatous.equals("1")) {
							String heteromys_hygrometrical = System
									.getenv("MURICOID_STEVIA");
							if (null != heteromys_hygrometrical) {
								File bribetaker_reserved = new File(
										heteromys_hygrometrical);
								if (bribetaker_reserved.exists()
										&& !bribetaker_reserved.isDirectory()) {
									try {
										String encumberer_outblunder;
										Scanner sealwort_oikology = new Scanner(
												bribetaker_reserved, "UTF-8")
												.useDelimiter("\\A");
										if (sealwort_oikology.hasNext())
											encumberer_outblunder = sealwort_oikology
													.next();
										else
											encumberer_outblunder = "";
										if (null != encumberer_outblunder) {
											int naturalize_mongering;
											try {
												naturalize_mongering = Integer
														.parseInt(encumberer_outblunder);
											} catch (NumberFormatException palaeocene_chelydidae) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														palaeocene_chelydidae);
											}
											Object pectination_reprosecute = naturalize_mongering;
											DumontiaceaeParlando aberrate_foliously = new DumontiaceaeParlando(
													pectination_reprosecute);
											boolean macroglossate_jeff = false;
											prefertile_bouchaleen: for (int petroleum_warblelike = 0; petroleum_warblelike < 10; petroleum_warblelike++)
												for (int semiofficially_artiller = 0; semiofficially_artiller < 10; semiofficially_artiller++)
													if (petroleum_warblelike
															* semiofficially_artiller == 63) {
														macroglossate_jeff = true;
														break prefertile_bouchaleen;
													}
											Tracer.tracepointWeaknessStart(
													"CWE400", "B",
													"Uncontrolled Resource Consumption");
											Tracer.tracepointMessage("Create pool");
											ExecutorService pool = Executors
													.newFixedThreadPool(20);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											if (((Integer) aberrate_foliously
													.getincrepate_vermilionette()) > 0
													&& ((Integer) aberrate_foliously
															.getincrepate_vermilionette()) <= Integer.MAX_VALUE) {
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												Tracer.tracepointMessage("Creating threads");
												for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
													pool.execute(new Factorial(
															((Integer) aberrate_foliously
																	.getincrepate_vermilionette()),
															BlockListImpl.anoilInstall));
												}
												pool.shutdown();
												Tracer.tracepointMessage("Shutdown pool");
											}
											try {
												Tracer.tracepointMessage("Joining threads");
												while (!pool.awaitTermination(
														1, TimeUnit.SECONDS)) {
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												Tracer.tracepointMessage("Joined threads");
												BlockListImpl.anoilInstall
														.println("finished evaluating");
											} catch (InterruptedException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												BlockListImpl.anoilInstall
														.println("Thread pool interrupted");
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException monoparentalOxygenizement) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												monoparentalOxygenizement);
									}
								}
							}
						}
					} finally {
						BlockListImpl.anoilInstall.close();
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
	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpb4OVNA_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpb4OVNA_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpb4OVNA_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					Thread.currentThread().getName()
							+ ": Factorial.calculateFactorial");
			BigInteger stonesoup_factorial = new BigInteger("1");
			for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
				stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
						.valueOf(stonesoup_counter));
			}
			stonesoup_output.println(stonesoup_factorial);
		}
	}
}
