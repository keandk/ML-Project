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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

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

	public class TelotremataCassowary {
		private int pharmacognosia_inciter;

		public TelotremataCassowary(int pharmacognosia_inciter) {
			this.pharmacognosia_inciter = pharmacognosia_inciter;
		}

		public int getpharmacognosia_inciter() {
			return this.pharmacognosia_inciter;
		}
	}

	static PrintStream chonolithMonoeciousness = null;
	private static final java.util.concurrent.atomic.AtomicBoolean brownianShafter = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (brownianShafter.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpBHGrtw_ss_testcase/src/src/java/org/apache/poi/poifs/storage/DataInputBlock.java",
					"available");
			File epithumeticExegetically = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!epithumeticExegetically.getParentFile().exists()
					&& !epithumeticExegetically.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DataInputBlock.chonolithMonoeciousness = new PrintStream(
							new FileOutputStream(epithumeticExegetically, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException derivantHuaracho) {
					System.err.printf("Failed to open log file.  %s\n",
							derivantHuaracho.getMessage());
					DataInputBlock.chonolithMonoeciousness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							derivantHuaracho);
				} catch (FileNotFoundException acardiacThymallidae) {
					System.err.printf("Failed to open log file.  %s\n",
							acardiacThymallidae.getMessage());
					DataInputBlock.chonolithMonoeciousness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							acardiacThymallidae);
				}
				if (DataInputBlock.chonolithMonoeciousness != null) {
					try {
						String mercuriate_submuriate = System
								.getenv("SCHOOLMAAM_BALSAMOUS");
						if (null != mercuriate_submuriate) {
							int flavorer_predisturbance;
							try {
								flavorer_predisturbance = Integer
										.parseInt(mercuriate_submuriate);
							} catch (NumberFormatException voluminosity_charger) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										voluminosity_charger);
							}
							TelotremataCassowary lifeguard_structuralism = new TelotremataCassowary(
									flavorer_predisturbance);
							try {
								String felonry_leanish = System
										.getProperty("os.name");
								if (null != felonry_leanish) {
									if (!felonry_leanish.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException prepigmental_bromization) {
								Tracer.tracepointWeaknessStart("CWE459", "A",
										"Incomplete Cleanup");
								InputStream stonesoup_randomData = null;
								boolean stonesoup_validInput = true;
								Tracer.tracepointVariableInt(
										"stonesoup_intValue",
										lifeguard_structuralism
												.getpharmacognosia_inciter());
								byte[] stonesoup_randomChars = null;
								try {
									DataInputBlock.chonolithMonoeciousness
											.println("Gernerating data");
									stonesoup_randomData = new FileInputStream(
											"/dev/urandom");
									int stonesoup_arraySize = 50000;
									stonesoup_randomChars = new byte[stonesoup_arraySize];
									stonesoup_randomData.read(
											stonesoup_randomChars, 0,
											stonesoup_arraySize);
								} catch (FileNotFoundException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									DataInputBlock.chonolithMonoeciousness
											.println("Error: /dev/urandom not found");
									stonesoup_validInput = false;
								} catch (IOException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									DataInputBlock.chonolithMonoeciousness
											.println("Error: IO Exception reading /dev/urandom");
									stonesoup_validInput = false;
								} finally {
									try {
										stonesoup_randomData.close();
									} catch (IOException e) {
										DataInputBlock.chonolithMonoeciousness
												.println("Error: Cannot close /dev/urandom");
										stonesoup_validInput = false;
									}
								}
								if (stonesoup_validInput) {
									int stonesoup_numFilePaths = 50;
									File[] stonesoup_filePaths = new File[stonesoup_numFilePaths];
									int stonesoup_i = 0;
									OutputStream stonesoup_outputStream = null;
									try {
										DataInputBlock.chonolithMonoeciousness
												.println("Saving data");
										for (stonesoup_i = 0; stonesoup_i < lifeguard_structuralism
												.getpharmacognosia_inciter(); stonesoup_i++) {
											stonesoup_filePaths[stonesoup_i
													% stonesoup_numFilePaths] = File
													.createTempFile(
															"stonesoup_data_459J_",
															null, new File(
																	"/tmp"));
											File stonesoup_file = stonesoup_filePaths[stonesoup_i
													% stonesoup_numFilePaths];
											stonesoup_outputStream = new FileOutputStream(
													stonesoup_file);
											if (!stonesoup_file.exists()) {
												stonesoup_file.createNewFile();
											}
											stonesoup_outputStream
													.write(stonesoup_randomChars);
											stonesoup_outputStream.close();
											stonesoup_outputStream = null;
										}
										Tracer.tracepointVariableInt(
												"stonesoup_i", stonesoup_i);
									} catch (FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										DataInputBlock.chonolithMonoeciousness
												.println("Error: tmp file  not found");
									} catch (IOException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										DataInputBlock.chonolithMonoeciousness
												.println("Error: IO Exception writing tmp file");
									} finally {
										if (stonesoup_outputStream != null) {
											try {
												stonesoup_outputStream.close();
											} catch (IOException e) {
												DataInputBlock.chonolithMonoeciousness
														.println("Error: could not delete output stream");
											}
										}
										Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
											if (stonesoup_filePaths[stonesoup_i] != null) {
												stonesoup_filePaths[stonesoup_i]
														.delete();
											}
										}
										Tracer.tracepointVariableInt(
												"stonesoup_i", stonesoup_i);
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						DataInputBlock.chonolithMonoeciousness.close();
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
