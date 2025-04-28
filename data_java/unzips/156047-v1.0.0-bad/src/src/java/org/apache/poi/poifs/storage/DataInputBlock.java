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

	public class ParatomialCommendam {
		private String[] cite_ceratoid;

		public ParatomialCommendam(String[] cite_ceratoid) {
			this.cite_ceratoid = cite_ceratoid;
		}

		public String[] getcite_ceratoid() {
			return this.cite_ceratoid;
		}
	}

	static PrintStream submarineRotarianism = null;
	private static final java.util.concurrent.atomic.AtomicBoolean thanklesslyEthylamine = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (thanklesslyEthylamine.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpskqVSk_ss_testcase/src/src/java/org/apache/poi/poifs/storage/DataInputBlock.java",
					"available");
			File karayaSubcellar = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!karayaSubcellar.getParentFile().exists()
					&& !karayaSubcellar.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DataInputBlock.submarineRotarianism = new PrintStream(
							new FileOutputStream(karayaSubcellar, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException condylomeApricate) {
					System.err.printf("Failed to open log file.  %s\n",
							condylomeApricate.getMessage());
					DataInputBlock.submarineRotarianism = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							condylomeApricate);
				} catch (FileNotFoundException strigUnelusive) {
					System.err.printf("Failed to open log file.  %s\n",
							strigUnelusive.getMessage());
					DataInputBlock.submarineRotarianism = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							strigUnelusive);
				}
				if (DataInputBlock.submarineRotarianism != null) {
					try {
						String splanchnography_marianic = System
								.getenv("SUPERENROLLMENT_RANSOMER");
						if (null != splanchnography_marianic) {
							String[] evolution_sequelae = new String[9];
							evolution_sequelae[1] = splanchnography_marianic;
							ParatomialCommendam strepsiptera_cujam = new ParatomialCommendam(
									evolution_sequelae);
							try {
								String equiponderation_nuditarian = System
										.getProperty("os.name");
								if (null != equiponderation_nuditarian) {
									if (!equiponderation_nuditarian
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException overdoor_ravish) {
							} finally {
								Tracer.tracepointWeaknessStart(
										"CWE089",
										"A",
										"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
								String stonesoup_mysql_host = System
										.getenv("DBMYSQLHOST");
								String stonesoup_mysql_user = System
										.getenv("DBMYSQLUSER");
								String stonesoup_mysql_pass = System
										.getenv("DBMYSQLPASSWORD");
								String stonesoup_mysql_port = System
										.getenv("DBMYSQLPORT");
								String stonesoup_mysql_dbname = System
										.getenv("SS_DBMYSQLDATABASE");
								Tracer.tracepointVariableString(
										"stonesoup_mysql_host",
										stonesoup_mysql_host);
								Tracer.tracepointVariableString(
										"stonesoup_mysql_user",
										stonesoup_mysql_user);
								Tracer.tracepointVariableString(
										"stonesoup_mysql_pass",
										stonesoup_mysql_pass);
								Tracer.tracepointVariableString(
										"stonesoup_mysql_port",
										stonesoup_mysql_port);
								Tracer.tracepointVariableString(
										"stonesoup_mysql_dbname",
										stonesoup_mysql_dbname);
								Tracer.tracepointVariableString(
										"country_name",
										strepsiptera_cujam.getcite_ceratoid()[1]);
								if (stonesoup_mysql_host == null
										|| stonesoup_mysql_user == null
										|| stonesoup_mysql_pass == null
										|| stonesoup_mysql_port == null
										|| stonesoup_mysql_dbname == null) {
									Tracer.tracepointError("Missing required database connection parameter(s).");
									DataInputBlock.submarineRotarianism
											.println("STONESOUP: Missing required database connection parameter(s).");
								} else {
									try {
										StringBuffer jdbc = new StringBuffer(
												"jdbc:mysql://");
										jdbc.append(stonesoup_mysql_host);
										jdbc.append(":");
										jdbc.append(stonesoup_mysql_port);
										jdbc.append("/");
										jdbc.append(stonesoup_mysql_dbname);
										jdbc.append("?allowMultiQueries=true");
										Class.forName("com.mysql.jdbc.Driver")
												.newInstance();
										Tracer.tracepointMessage("Establishing connection to database.");
										java.sql.Connection con = java.sql.DriverManager
												.getConnection(jdbc.toString(),
														stonesoup_mysql_user,
														stonesoup_mysql_pass);
										java.sql.Statement stmt = con
												.createStatement();
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										String queryString = "SELECT * FROM Customers WHERE "
												+ "Country=\'"
												+ strepsiptera_cujam
														.getcite_ceratoid()[1]
												+ "\'";
										Tracer.tracepointVariableString(
												"queryString", queryString);
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
										DataInputBlock.submarineRotarianism
												.println(queryString);
										java.sql.ResultSet resultSet = null;
										java.sql.ResultSetMetaData metaData = null;
										int columnCount = 0;
										Tracer.tracepointMessage("Querying database.");
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										boolean hasMoreResults = stmt
												.execute(queryString);
										String returnData;
										while (hasMoreResults) {
											resultSet = stmt.getResultSet();
											while (resultSet.next()) {
												metaData = resultSet
														.getMetaData();
												columnCount = metaData
														.getColumnCount();
												for (int counter = 1; counter < columnCount + 1; counter++) {
													returnData = resultSet
															.getString(counter);
													DataInputBlock.submarineRotarianism
															.println(returnData);
												}
											}
											hasMoreResults = stmt
													.getMoreResults();
										}
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										con.close();
									} catch (java.sql.SQLException se) {
										Tracer.tracepointError(se.getClass()
												.getName()
												+ ": "
												+ se.getMessage());
										DataInputBlock.submarineRotarianism
												.println("STONESOUP: Error accessing database.");
										se.printStackTrace(DataInputBlock.submarineRotarianism);
									} catch (ClassNotFoundException cnfe) {
										Tracer.tracepointError(cnfe.getClass()
												.getName()
												+ ": "
												+ cnfe.getMessage());
										DataInputBlock.submarineRotarianism
												.println("STONESOUP: Error accessing database.");
										cnfe.printStackTrace(DataInputBlock.submarineRotarianism);
									} catch (IllegalAccessException iae) {
										Tracer.tracepointError(iae.getClass()
												.getName()
												+ ": "
												+ iae.getMessage());
										DataInputBlock.submarineRotarianism
												.println("STONESOUP: Error accessing database.");
										iae.printStackTrace(DataInputBlock.submarineRotarianism);
									} catch (InstantiationException ie) {
										Tracer.tracepointError(ie.getClass()
												.getName()
												+ ": "
												+ ie.getMessage());
										DataInputBlock.submarineRotarianism
												.println("STONESOUP: Error accessing database.");
										ie.printStackTrace(DataInputBlock.submarineRotarianism);
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						DataInputBlock.submarineRotarianism.close();
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
