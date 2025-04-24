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
    public class UnsectarianizeGrandmotherhood {
		private String braccio_vipresident;

		public UnsectarianizeGrandmotherhood(String braccio_vipresident) {
			this.braccio_vipresident = braccio_vipresident;
		}

		public String getbraccio_vipresident() {
			return this.braccio_vipresident;
		}
	}
	static PrintStream reparatoryLonesomely = null;
	private static final java.util.concurrent.atomic.AtomicBoolean homogeneticalMugience = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (homogeneticalMugience.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpkynN6z_ss_testcase/src/src/java/org/apache/poi/poifs/storage/BlockListImpl.java",
					"remove");
			File caffeoneBassus = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!caffeoneBassus.getParentFile().exists()
					&& !caffeoneBassus.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					BlockListImpl.reparatoryLonesomely = new PrintStream(
							new FileOutputStream(caffeoneBassus, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException supracensoriousReversedly) {
					System.err.printf("Failed to open log file.  %s\n",
							supracensoriousReversedly.getMessage());
					BlockListImpl.reparatoryLonesomely = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							supracensoriousReversedly);
				} catch (FileNotFoundException pythogeneticKekotene) {
					System.err.printf("Failed to open log file.  %s\n",
							pythogeneticKekotene.getMessage());
					BlockListImpl.reparatoryLonesomely = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pythogeneticKekotene);
				}
				if (BlockListImpl.reparatoryLonesomely != null) {
					try {
						String unrebated_wasteland = System
								.getenv("OVERSIZE_REHARM");
						if (null != unrebated_wasteland) {
							UnsectarianizeGrandmotherhood vernine_albanite = new UnsectarianizeGrandmotherhood(
									unrebated_wasteland);
							try {
								String psychesthesia_satyromaniac = System
										.getProperty("os.name");
								if (null != psychesthesia_satyromaniac) {
									if (!psychesthesia_satyromaniac
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException satrapess_sudanic) {
								Tracer.tracepointWeaknessStart(
										"CWE089",
										"B",
										"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
								String stonesoup_psql_host = System
										.getenv("DBPGHOST");
								String stonesoup_psql_user = System
										.getenv("DBPGUSER");
								String stonesoup_psql_pass = System
										.getenv("DBPGPASSWORD");
								String stonesoup_psql_port = System
										.getenv("DBPGPORT");
								String stonesoup_psql_dbname = System
										.getenv("SS_DBPGDATABASE");
								Tracer.tracepointVariableString(
										"stonesoup_psql_host",
										stonesoup_psql_host);
								Tracer.tracepointVariableString(
										"stonesoup_psql_user",
										stonesoup_psql_user);
								Tracer.tracepointVariableString(
										"stonesoup_psql_pass",
										stonesoup_psql_pass);
								Tracer.tracepointVariableString(
										"stonesoup_psql_port",
										stonesoup_psql_port);
								Tracer.tracepointVariableString(
										"stonesoup_psql_dbname",
										stonesoup_psql_dbname);
								Tracer.tracepointVariableString("taintvar",
										vernine_albanite
												.getbraccio_vipresident());
								if (stonesoup_psql_host == null
										|| stonesoup_psql_user == null
										|| stonesoup_psql_pass == null
										|| stonesoup_psql_port == null
										|| stonesoup_psql_dbname == null) {
									Tracer.tracepointError("Missing required database connection parameter(s).");
									BlockListImpl.reparatoryLonesomely
											.println("STONESOUP: Missing required database connection parameters.");
								} else {
									try {
										StringBuffer jdbc = new StringBuffer(
												"jdbc:postgresql://");
										jdbc.append(stonesoup_psql_host);
										jdbc.append(":");
										jdbc.append(stonesoup_psql_port);
										jdbc.append("/");
										jdbc.append(stonesoup_psql_dbname);
										Tracer.tracepointMessage("Establishing connection to database.");
										Class.forName("org.postgresql.Driver");
										java.sql.Connection conn = java.sql.DriverManager
												.getConnection(jdbc.toString(),
														stonesoup_psql_user,
														stonesoup_psql_pass);
										java.sql.Statement stmt = conn
												.createStatement();
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										String query = "SELECT * FROM customers WHERE country =\'"
												+ vernine_albanite
														.getbraccio_vipresident()
												+ "\';";
										Tracer.tracepointVariableString(
												"query", query);
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										BlockListImpl.reparatoryLonesomely
												.println(query);
										Tracer.tracepointMessage("Querying database.");
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										boolean hasMoreResults = stmt
												.execute(query);
										String rtnString;
										while (hasMoreResults) {
											java.sql.ResultSet rs = stmt
													.getResultSet();
											if (rs != null) {
												java.sql.ResultSetMetaData metaData = null;
												int columns = 0;
												while (rs.next()) {
													metaData = rs.getMetaData();
													columns = metaData
															.getColumnCount();
													for (int i = 1; i < columns + 1; i++) {
														rtnString = rs
																.getString(i);
														BlockListImpl.reparatoryLonesomely
																.println(rtnString);
													}
												}
											}
											hasMoreResults = stmt
													.getMoreResults();
										}
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										stmt.close();
										conn.close();
									} catch (java.sql.SQLFeatureNotSupportedException nse) {
										Tracer.tracepointError(nse.getClass()
												.getName()
												+ ": "
												+ nse.getMessage());
										BlockListImpl.reparatoryLonesomely
												.println("STONESOUP: Error accessing database.");
										nse.printStackTrace(BlockListImpl.reparatoryLonesomely);
									} catch (java.sql.SQLException se) {
										Tracer.tracepointError(se.getClass()
												.getName()
												+ ": "
												+ se.getMessage());
										BlockListImpl.reparatoryLonesomely
												.println("STONESOUP: Error accessing database.");
										se.printStackTrace(BlockListImpl.reparatoryLonesomely);
									} catch (ClassNotFoundException cnfe) {
										Tracer.tracepointError(cnfe.getClass()
												.getName()
												+ ": "
												+ cnfe.getMessage());
										BlockListImpl.reparatoryLonesomely
												.println("STONESOUP: Error accessing database.");
										cnfe.printStackTrace(BlockListImpl.reparatoryLonesomely);
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						BlockListImpl.reparatoryLonesomely.close();
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
