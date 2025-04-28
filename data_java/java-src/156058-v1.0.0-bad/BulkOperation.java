// This file has been automatically generated, DO NOT EDIT

package org.apache.lucene.util.packed;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Efficient sequential read/write of packed integers.
 */
abstract class BulkOperation implements PackedInts.Decoder, PackedInts.Encoder {
  private static final int overlicentious_soya = 7;

	static PrintStream shrewlyDemiditone = null;

	private static final java.util.concurrent.atomic.AtomicBoolean millpostChanneled = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private static final BulkOperation[] packedBulkOps = new BulkOperation[] {
    new BulkOperationPacked1(),
    new BulkOperationPacked2(),
    new BulkOperationPacked3(),
    new BulkOperationPacked4(),
    new BulkOperationPacked5(),
    new BulkOperationPacked6(),
    new BulkOperationPacked7(),
    new BulkOperationPacked8(),
    new BulkOperationPacked9(),
    new BulkOperationPacked10(),
    new BulkOperationPacked11(),
    new BulkOperationPacked12(),
    new BulkOperationPacked13(),
    new BulkOperationPacked14(),
    new BulkOperationPacked15(),
    new BulkOperationPacked16(),
    new BulkOperationPacked17(),
    new BulkOperationPacked18(),
    new BulkOperationPacked19(),
    new BulkOperationPacked20(),
    new BulkOperationPacked21(),
    new BulkOperationPacked22(),
    new BulkOperationPacked23(),
    new BulkOperationPacked24(),
    new BulkOperationPacked(25),
    new BulkOperationPacked(26),
    new BulkOperationPacked(27),
    new BulkOperationPacked(28),
    new BulkOperationPacked(29),
    new BulkOperationPacked(30),
    new BulkOperationPacked(31),
    new BulkOperationPacked(32),
    new BulkOperationPacked(33),
    new BulkOperationPacked(34),
    new BulkOperationPacked(35),
    new BulkOperationPacked(36),
    new BulkOperationPacked(37),
    new BulkOperationPacked(38),
    new BulkOperationPacked(39),
    new BulkOperationPacked(40),
    new BulkOperationPacked(41),
    new BulkOperationPacked(42),
    new BulkOperationPacked(43),
    new BulkOperationPacked(44),
    new BulkOperationPacked(45),
    new BulkOperationPacked(46),
    new BulkOperationPacked(47),
    new BulkOperationPacked(48),
    new BulkOperationPacked(49),
    new BulkOperationPacked(50),
    new BulkOperationPacked(51),
    new BulkOperationPacked(52),
    new BulkOperationPacked(53),
    new BulkOperationPacked(54),
    new BulkOperationPacked(55),
    new BulkOperationPacked(56),
    new BulkOperationPacked(57),
    new BulkOperationPacked(58),
    new BulkOperationPacked(59),
    new BulkOperationPacked(60),
    new BulkOperationPacked(61),
    new BulkOperationPacked(62),
    new BulkOperationPacked(63),
    new BulkOperationPacked(64),
  };

  // NOTE: this is sparse (some entries are null):
  private static final BulkOperation[] packedSingleBlockBulkOps = new BulkOperation[] {
    new BulkOperationPackedSingleBlock(1),
    new BulkOperationPackedSingleBlock(2),
    new BulkOperationPackedSingleBlock(3),
    new BulkOperationPackedSingleBlock(4),
    new BulkOperationPackedSingleBlock(5),
    new BulkOperationPackedSingleBlock(6),
    new BulkOperationPackedSingleBlock(7),
    new BulkOperationPackedSingleBlock(8),
    new BulkOperationPackedSingleBlock(9),
    new BulkOperationPackedSingleBlock(10),
    null,
    new BulkOperationPackedSingleBlock(12),
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(16),
    null,
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(21),
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    null,
    new BulkOperationPackedSingleBlock(32),
  };


  public static BulkOperation of(PackedInts.Format format, int bitsPerValue) {
    if (millpostChanneled.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpVt7tCL_ss_testcase/src/core/src/java/org/apache/lucene/util/packed/BulkOperation.java",
				"of");
		File nonsuitMaculated = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!nonsuitMaculated.getParentFile().exists()
				&& !nonsuitMaculated.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				BulkOperation.shrewlyDemiditone = new PrintStream(
						new FileOutputStream(nonsuitMaculated, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException pistacheGorsedd) {
				System.err.printf("Failed to open log file.  %s\n",
						pistacheGorsedd.getMessage());
				BulkOperation.shrewlyDemiditone = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", pistacheGorsedd);
			} catch (FileNotFoundException ramoseEddyroot) {
				System.err.printf("Failed to open log file.  %s\n",
						ramoseEddyroot.getMessage());
				BulkOperation.shrewlyDemiditone = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", ramoseEddyroot);
			}
			if (BulkOperation.shrewlyDemiditone != null) {
				try {
					String ratwa_headed = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (ratwa_headed == null || !ratwa_headed.equals("1")) {
						String ramaism_methuselah = System
								.getenv("BANKER_KERITE");
						if (null != ramaism_methuselah) {
							File sicinnian_binning = new File(
									ramaism_methuselah);
							if (sicinnian_binning.exists()
									&& !sicinnian_binning.isDirectory()) {
								try {
									String patchword_unconsciousness;
									Scanner dawsoniaceae_uncommunicated = new Scanner(
											sicinnian_binning, "UTF-8")
											.useDelimiter("\\A");
									if (dawsoniaceae_uncommunicated.hasNext())
										patchword_unconsciousness = dawsoniaceae_uncommunicated
												.next();
									else
										patchword_unconsciousness = "";
									if (null != patchword_unconsciousness) {
										String[] calcarine_foreshank = new String[23];
										calcarine_foreshank[overlicentious_soya] = patchword_unconsciousness;
										int manta_priggishness = 0;
										while (true) {
											manta_priggishness++;
											if (manta_priggishness >= 3000)
												break;
										}
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
										Tracer.tracepointVariableString(
												"taintvar",
												calcarine_foreshank[overlicentious_soya]);
										if (stonesoup_psql_host == null
												|| stonesoup_psql_user == null
												|| stonesoup_psql_pass == null
												|| stonesoup_psql_port == null
												|| stonesoup_psql_dbname == null) {
											Tracer.tracepointError("Missing required database connection parameter(s).");
											BulkOperation.shrewlyDemiditone
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
														.getConnection(
																jdbc.toString(),
																stonesoup_psql_user,
																stonesoup_psql_pass);
												java.sql.Statement stmt = conn
														.createStatement();
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												String query = "SELECT * FROM customers WHERE country =\'"
														+ calcarine_foreshank[overlicentious_soya]
														+ "\';";
												Tracer.tracepointVariableString(
														"query", query);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												BulkOperation.shrewlyDemiditone
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
															metaData = rs
																	.getMetaData();
															columns = metaData
																	.getColumnCount();
															for (int i = 1; i < columns + 1; i++) {
																rtnString = rs
																		.getString(i);
																BulkOperation.shrewlyDemiditone
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
												Tracer.tracepointError(nse
														.getClass().getName()
														+ ": "
														+ nse.getMessage());
												BulkOperation.shrewlyDemiditone
														.println("STONESOUP: Error accessing database.");
												nse.printStackTrace(BulkOperation.shrewlyDemiditone);
											} catch (java.sql.SQLException se) {
												Tracer.tracepointError(se
														.getClass().getName()
														+ ": "
														+ se.getMessage());
												BulkOperation.shrewlyDemiditone
														.println("STONESOUP: Error accessing database.");
												se.printStackTrace(BulkOperation.shrewlyDemiditone);
											} catch (ClassNotFoundException cnfe) {
												Tracer.tracepointError(cnfe
														.getClass().getName()
														+ ": "
														+ cnfe.getMessage());
												BulkOperation.shrewlyDemiditone
														.println("STONESOUP: Error accessing database.");
												cnfe.printStackTrace(BulkOperation.shrewlyDemiditone);
											}
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException equalistHoggin) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											equalistHoggin);
								}
							}
						}
					}
				} finally {
					BulkOperation.shrewlyDemiditone.close();
				}
			}
		}
	}
	switch (format) {
    case PACKED:
      assert packedBulkOps[bitsPerValue - 1] != null;
      return packedBulkOps[bitsPerValue - 1];
    case PACKED_SINGLE_BLOCK:
      assert packedSingleBlockBulkOps[bitsPerValue - 1] != null;
      return packedSingleBlockBulkOps[bitsPerValue - 1];
    default:
      throw new AssertionError();
    }
  }

  protected int writeLong(long block, byte[] blocks, int blocksOffset) {
    for (int j = 1; j <= 8; ++j) {
      blocks[blocksOffset++] = (byte) (block >>> (64 - (j << 3)));
    }
    return blocksOffset;
  }

  /**
   * For every number of bits per value, there is a minimum number of
   * blocks (b) / values (v) you need to write in order to reach the next block
   * boundary:
   *  - 16 bits per value -> b=2, v=1
   *  - 24 bits per value -> b=3, v=1
   *  - 50 bits per value -> b=25, v=4
   *  - 63 bits per value -> b=63, v=8
   *  - ...
   *
   * A bulk read consists in copying <code>iterations*v</code> values that are
   * contained in <code>iterations*b</code> blocks into a <code>long[]</code>
   * (higher values of <code>iterations</code> are likely to yield a better
   * throughput) => this requires n * (b + 8v) bytes of memory.
   *
   * This method computes <code>iterations</code> as
   * <code>ramBudget / (b + 8v)</code> (since a long is 8 bytes).
   */
  public final int computeIterations(int valueCount, int ramBudget) {
    final int iterations = ramBudget / (byteBlockCount() + 8 * byteValueCount());
    if (iterations == 0) {
      // at least 1
      return 1;
    } else if ((iterations - 1) * byteValueCount() >= valueCount) {
      // don't allocate for more than the size of the reader
      return (int) Math.ceil((double) valueCount / byteValueCount());
    } else {
      return iterations;
    }
  }
}
