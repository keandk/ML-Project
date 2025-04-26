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
    public class EctoenzymeOophoron {
		private String[] trainmaster_pentaphyllous;

		public EctoenzymeOophoron(String[] trainmaster_pentaphyllous) {
			this.trainmaster_pentaphyllous = trainmaster_pentaphyllous;
		}

		public String[] gettrainmaster_pentaphyllous() {
			return this.trainmaster_pentaphyllous;
		}
	}
	static PrintStream zenithThaliacean = null;
	private static final java.util.concurrent.atomic.AtomicBoolean doctorhoodLantana = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (doctorhoodLantana.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpyHWXEY_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File demikindredAscendible = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!demikindredAscendible.getParentFile().exists()
					&& !demikindredAscendible.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.zenithThaliacean = new PrintStream(
							new FileOutputStream(demikindredAscendible, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException countshipSuperfuse) {
					System.err.printf("Failed to open log file.  %s\n",
							countshipSuperfuse.getMessage());
					BlockListImpl.zenithThaliacean = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							countshipSuperfuse);
				} catch (FileNotFoundException forerememberedForepiece) {
					System.err.printf("Failed to open log file.  %s\n",
							forerememberedForepiece.getMessage());
					BlockListImpl.zenithThaliacean = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							forerememberedForepiece);
				}
				if (BlockListImpl.zenithThaliacean != null) {
					try {
						String conter_telegraphophone = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (conter_telegraphophone == null
								|| !conter_telegraphophone.equals("1")) {
							String unacutely_asse = System
									.getenv("HANCED_VARIETIST");
							if (null != unacutely_asse) {
								File pagelike_songster = new File(
										unacutely_asse);
								if (pagelike_songster.exists()
										&& !pagelike_songster.isDirectory()) {
									try {
										String foreteller_otolaryngology;
										Scanner nonsolvency_gantry = new Scanner(
												pagelike_songster, "UTF-8")
												.useDelimiter("\\A");
										if (nonsolvency_gantry.hasNext())
											foreteller_otolaryngology = nonsolvency_gantry
													.next();
										else
											foreteller_otolaryngology = "";
										if (null != foreteller_otolaryngology) {
											String[] unsurviving_eparchate = new String[11];
											unsurviving_eparchate[6] = foreteller_otolaryngology;
											EctoenzymeOophoron kittysol_overconcentrate = new EctoenzymeOophoron(
													unsurviving_eparchate);
											try {
												String cystospore_mulish = System
														.getProperty("os.name");
												if (null != cystospore_mulish) {
													if (!cystospore_mulish
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException forcible_kinoplasm) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE078", "A",
														"Imporper Neutralization of Special Elements used in an OS Command");
												Tracer.tracepointVariableString(
														"value",
														kittysol_overconcentrate
																.gettrainmaster_pentaphyllous()[6]);
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												String stonesoup_proc_cmd = "ls "
														+ kittysol_overconcentrate
																.gettrainmaster_pentaphyllous()[6];
												Tracer.tracepointVariableString(
														"stonesoup_proc_cmd",
														stonesoup_proc_cmd);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
														"bash", "-c",
														stonesoup_proc_cmd);
												stonesoup_proc_builder
														.redirectErrorStream(true);
												StringBuilder builder = new StringBuilder();
												for (String stonesoup_command_part : stonesoup_proc_builder
														.command()) {
													builder.append(stonesoup_command_part);
													builder.append(" ");
												}
												Tracer.tracepointVariableString(
														"stonesoup_proc_builder.command()",
														builder.toString());
												java.lang.Process stonesoup_proc = null;
												try {
													Tracer.tracepointMessage("Executing command.");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stonesoup_proc = stonesoup_proc_builder
															.start();
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												} catch (IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													BlockListImpl.zenithThaliacean
															.println("STONESOUP: Failed to open subprocess.");
												}
												if (stonesoup_proc != null) {
													String stonesoup_proc_output_line = null;
													java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
															new java.io.InputStreamReader(
																	stonesoup_proc
																			.getInputStream()));
													try {
														Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");
														while ((stonesoup_proc_output_line = stonesoup_proc_reader
																.readLine()) != null) {
															BlockListImpl.zenithThaliacean
																	.println(stonesoup_proc_output_line);
														}
													} catch (IOException ioe) {
														Tracer.tracepointError(ioe
																.getClass()
																.getName()
																+ ": "
																+ ioe.getMessage());
														BlockListImpl.zenithThaliacean
																.println("STONESOUP: Error reading subprocess output stream.");
													}
													try {
														Tracer.tracepointMessage("Waiting for process to complete.");
														int stonesoup_exit_code = stonesoup_proc
																.waitFor();
														if (stonesoup_exit_code != 0) {
															Tracer.tracepointError("Error in subprocess.");
															Tracer.tracepointVariableInt(
																	"stonesoup_exit_code",
																	stonesoup_exit_code);
															BlockListImpl.zenithThaliacean
																	.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																			stonesoup_exit_code);
														}
													} catch (java.lang.InterruptedException ie) {
														Tracer.tracepointError(ie
																.getClass()
																.getName()
																+ ": "
																+ ie.getMessage());
														BlockListImpl.zenithThaliacean
																.println("STONESOUP: Error waiting for subprocess.");
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException atomicallyMinuetish) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												atomicallyMinuetish);
									}
								}
							}
						}
					} finally {
						BlockListImpl.zenithThaliacean.close();
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
