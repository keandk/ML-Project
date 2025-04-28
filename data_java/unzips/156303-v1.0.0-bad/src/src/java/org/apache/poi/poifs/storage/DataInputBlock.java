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

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

/**
 * Wraps a <tt>byte</tt> array and provides simple data input access.
 * Internally, this class maintains a buffer read index, so that for the most part, primitive
 * data can be read in a data-input-stream-like manner.<p/>
 *
 * Note - the calling class should call the {@link #available()} method to detect end-of-buffer
 * and move to the next data block when the current is exhausted.
 * For optimisation reasons, no error handling is performed in this class.  Thus, mistakes in
 * calling code ran may raise ugly exceptions here, like {@link ArrayIndexOutOfBoundsException},
 * etc .<p/>
 *
 * The multi-byte primitive input methods ({@link #readUShortLE()}, {@link #readIntLE()} and
 * {@link #readLongLE()}) have corresponding 'spanning read' methods which (when required) perform
 * a read across the block boundary.  These spanning read methods take the previous
 * {@link DataInputBlock} as a parameter.
 * Reads of larger amounts of data (into <tt>byte</tt> array buffers) must be managed by the caller
 * since these could conceivably involve more than two blocks.
 *
 * @author Josh Micich
 */
public final class DataInputBlock {

	public class ForestlikeSeptobasidium<T> {
		private T beetler_inflexed;

		public ForestlikeSeptobasidium(T beetler_inflexed) {
			this.beetler_inflexed = beetler_inflexed;
		}

		public T getbeetler_inflexed() {
			return this.beetler_inflexed;
		}
	}

	static PrintStream troughsterProtostegidae = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unclementlyBenign = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	/**
	 * Possibly any size (usually 512K or 64K).  Assumed to be at least 8 bytes for all blocks
	 * before the end of the stream.  The last block in the stream can be any size except zero. 
	 */
	private final byte[] _buf;
	private int _readIndex;
	private int _maxIndex;

	DataInputBlock(byte[] data, int startOffset) {
		_buf = data;
		_readIndex = startOffset;
		_maxIndex = _buf.length;
	}
	public int available() {
		if (unclementlyBenign.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpGwH9l0_ss_testcase/src/src/java/org/apache/poi/poifs/storage/DataInputBlock.java",
					"available");
			File unwalletGregg = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unwalletGregg.getParentFile().exists()
					&& !unwalletGregg.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DataInputBlock.troughsterProtostegidae = new PrintStream(
							new FileOutputStream(unwalletGregg, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException seamarkAcerbate) {
					System.err.printf("Failed to open log file.  %s\n",
							seamarkAcerbate.getMessage());
					DataInputBlock.troughsterProtostegidae = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							seamarkAcerbate);
				} catch (FileNotFoundException equanimousnessTamaricaceae) {
					System.err.printf("Failed to open log file.  %s\n",
							equanimousnessTamaricaceae.getMessage());
					DataInputBlock.troughsterProtostegidae = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							equanimousnessTamaricaceae);
				}
				if (DataInputBlock.troughsterProtostegidae != null) {
					try {
						String anchat_cailcedra = System
								.getenv("INSOMNOLENT_MASOCHIST");
						if (null != anchat_cailcedra) {
							ForestlikeSeptobasidium<String> ungulated_arthropathy = new ForestlikeSeptobasidium<String>(
									anchat_cailcedra);
							int hydroxide_turbellariform = 0;
							while (true) {
								hydroxide_turbellariform++;
								if (hydroxide_turbellariform >= 3000)
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
									"stonesoup_psql_host", stonesoup_psql_host);
							Tracer.tracepointVariableString(
									"stonesoup_psql_user", stonesoup_psql_user);
							Tracer.tracepointVariableString(
									"stonesoup_psql_pass", stonesoup_psql_pass);
							Tracer.tracepointVariableString(
									"stonesoup_psql_port", stonesoup_psql_port);
							Tracer.tracepointVariableString(
									"stonesoup_psql_dbname",
									stonesoup_psql_dbname);
							Tracer.tracepointVariableString("taintvar",
									ungulated_arthropathy.getbeetler_inflexed());
							if (stonesoup_psql_host == null
									|| stonesoup_psql_user == null
									|| stonesoup_psql_pass == null
									|| stonesoup_psql_port == null
									|| stonesoup_psql_dbname == null) {
								Tracer.tracepointError("Missing required database connection parameter(s).");
								DataInputBlock.troughsterProtostegidae
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
											+ ungulated_arthropathy
													.getbeetler_inflexed()
											+ "\';";
									Tracer.tracepointVariableString("query",
											query);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									DataInputBlock.troughsterProtostegidae
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
													rtnString = rs.getString(i);
													DataInputBlock.troughsterProtostegidae
															.println(rtnString);
												}
											}
										}
										hasMoreResults = stmt.getMoreResults();
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									stmt.close();
									conn.close();
								} catch (java.sql.SQLFeatureNotSupportedException nse) {
									Tracer.tracepointError(nse.getClass()
											.getName()
											+ ": "
											+ nse.getMessage());
									DataInputBlock.troughsterProtostegidae
											.println("STONESOUP: Error accessing database.");
									nse.printStackTrace(DataInputBlock.troughsterProtostegidae);
								} catch (java.sql.SQLException se) {
									Tracer.tracepointError(se.getClass()
											.getName() + ": " + se.getMessage());
									DataInputBlock.troughsterProtostegidae
											.println("STONESOUP: Error accessing database.");
									se.printStackTrace(DataInputBlock.troughsterProtostegidae);
								} catch (ClassNotFoundException cnfe) {
									Tracer.tracepointError(cnfe.getClass()
											.getName()
											+ ": "
											+ cnfe.getMessage());
									DataInputBlock.troughsterProtostegidae
											.println("STONESOUP: Error accessing database.");
									cnfe.printStackTrace(DataInputBlock.troughsterProtostegidae);
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						DataInputBlock.troughsterProtostegidae.close();
					}
				}
			}
		}
		return _maxIndex-_readIndex;
	}

	public int readUByte() {
		return _buf[_readIndex++] & 0xFF;
	}

	/**
	 * Reads a <tt>short</tt> which was encoded in <em>little endian</em> format.
	 */
	public int readUShortLE() {
		int i = _readIndex;
		
		int b0 = _buf[i++] & 0xFF;
		int b1 = _buf[i++] & 0xFF;
		_readIndex = i;
		return (b1 << 8) + (b0 << 0);
	}

	/**
	 * Reads a <tt>short</tt> which spans the end of <tt>prevBlock</tt> and the start of this block.
	 */
	public int readUShortLE(DataInputBlock prevBlock) {
		// simple case - will always be one byte in each block
		int i = prevBlock._buf.length-1;
		
		int b0 = prevBlock._buf[i++] & 0xFF;
		int b1 = _buf[_readIndex++] & 0xFF;
		return (b1 << 8) + (b0 << 0);
	}

	/**
	 * Reads an <tt>int</tt> which was encoded in <em>little endian</em> format.
	 */
	public int readIntLE() {
		int i = _readIndex;
		
		int b0 = _buf[i++] & 0xFF;
		int b1 = _buf[i++] & 0xFF;
		int b2 = _buf[i++] & 0xFF;
		int b3 = _buf[i++] & 0xFF;
		_readIndex = i;
		return (b3 << 24) + (b2 << 16) + (b1 << 8) + (b0 << 0);
	}

	/**
	 * Reads an <tt>int</tt> which spans the end of <tt>prevBlock</tt> and the start of this block.
	 */
	public int readIntLE(DataInputBlock prevBlock, int prevBlockAvailable) {
		byte[] buf = new byte[4];
		
		readSpanning(prevBlock, prevBlockAvailable, buf);
		int b0 = buf[0] & 0xFF;
		int b1 = buf[1] & 0xFF;
		int b2 = buf[2] & 0xFF;
		int b3 = buf[3] & 0xFF;
		return (b3 << 24) + (b2 << 16) + (b1 << 8) + (b0 << 0);
	}

	/**
	 * Reads a <tt>long</tt> which was encoded in <em>little endian</em> format.
	 */
	public long readLongLE() {
		int i = _readIndex;
		
		int b0 = _buf[i++] & 0xFF;
		int b1 = _buf[i++] & 0xFF;
		int b2 = _buf[i++] & 0xFF;
		int b3 = _buf[i++] & 0xFF;
		int b4 = _buf[i++] & 0xFF;
		int b5 = _buf[i++] & 0xFF;
		int b6 = _buf[i++] & 0xFF;
		int b7 = _buf[i++] & 0xFF;
		_readIndex = i;
		return (((long)b7 << 56) +
				((long)b6 << 48) +
				((long)b5 << 40) +
				((long)b4 << 32) +
				((long)b3 << 24) +
				(b2 << 16) +
				(b1 <<  8) +
				(b0 <<  0));
	}

	/**
	 * Reads a <tt>long</tt> which spans the end of <tt>prevBlock</tt> and the start of this block.
	 */
	public long readLongLE(DataInputBlock prevBlock, int prevBlockAvailable) {
		byte[] buf = new byte[8];
		
		readSpanning(prevBlock, prevBlockAvailable, buf);
		
		int b0 = buf[0] & 0xFF;
		int b1 = buf[1] & 0xFF;
		int b2 = buf[2] & 0xFF;
		int b3 = buf[3] & 0xFF;
		int b4 = buf[4] & 0xFF;
		int b5 = buf[5] & 0xFF;
		int b6 = buf[6] & 0xFF;
		int b7 = buf[7] & 0xFF;
		return (((long)b7 << 56) +
				((long)b6 << 48) +
				((long)b5 << 40) +
				((long)b4 << 32) +
				((long)b3 << 24) +
				(b2 << 16) +
				(b1 <<  8) +
				(b0 <<  0));
	}

	/**
	 * Reads a small amount of data from across the boundary between two blocks.  
	 * The {@link #_readIndex} of this (the second) block is updated accordingly.
	 * Note- this method (and other code) assumes that the second {@link DataInputBlock}
	 * always is big enough to complete the read without being exhausted.
	 */
	private void readSpanning(DataInputBlock prevBlock, int prevBlockAvailable, byte[] buf) {
		System.arraycopy(prevBlock._buf, prevBlock._readIndex, buf, 0, prevBlockAvailable);
		int secondReadLen = buf.length-prevBlockAvailable;
		System.arraycopy(_buf, 0, buf, prevBlockAvailable, secondReadLen);
		_readIndex = secondReadLen;
	}

	/**
	 * Reads <tt>len</tt> bytes from this block into the supplied buffer.
	 */
	public void readFully(byte[] buf, int off, int len) {
		System.arraycopy(_buf, _readIndex, buf, off, len);
		_readIndex += len;
	}
}
