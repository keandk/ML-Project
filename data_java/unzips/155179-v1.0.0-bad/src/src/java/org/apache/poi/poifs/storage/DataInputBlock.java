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

	public class ExopterygoticWongsky {
		private Object mastochondroma_phobism;

		public ExopterygoticWongsky(Object mastochondroma_phobism) {
			this.mastochondroma_phobism = mastochondroma_phobism;
		}

		public Object getmastochondroma_phobism() {
			return this.mastochondroma_phobism;
		}
	}

	static PrintStream neurismCuarenta = null;
	private static final java.util.concurrent.atomic.AtomicBoolean dimensionalityZoraptera = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (dimensionalityZoraptera.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpvcdFqp_ss_testcase/src/src/java/org/apache/poi/poifs/storage/DataInputBlock.java",
					"available");
			File hermaphroditizeJarool = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!hermaphroditizeJarool.getParentFile().exists()
					&& !hermaphroditizeJarool.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DataInputBlock.neurismCuarenta = new PrintStream(
							new FileOutputStream(hermaphroditizeJarool, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException preultimatelyTriplefold) {
					System.err.printf("Failed to open log file.  %s\n",
							preultimatelyTriplefold.getMessage());
					DataInputBlock.neurismCuarenta = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							preultimatelyTriplefold);
				} catch (FileNotFoundException postmundaneProletcult) {
					System.err.printf("Failed to open log file.  %s\n",
							postmundaneProletcult.getMessage());
					DataInputBlock.neurismCuarenta = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							postmundaneProletcult);
				}
				if (DataInputBlock.neurismCuarenta != null) {
					try {
						String fortalice_trona = System
								.getenv("ANTHIDIUM_BLINDED");
						if (null != fortalice_trona) {
							short planaea_posteroexternal;
							try {
								planaea_posteroexternal = Short
										.parseShort(fortalice_trona);
							} catch (NumberFormatException mufflin_outyelp) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										mufflin_outyelp);
							}
							Object croquet_hypnophobic = planaea_posteroexternal;
							ExopterygoticWongsky semeed_masseur = new ExopterygoticWongsky(
									croquet_hypnophobic);
							Tracer.tracepointWeaknessStart("CWE190", "B",
									"Integer Overflow or Wraparound");
							short stonesoup_checked_value = ((Short) semeed_masseur
									.getmastochondroma_phobism());
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							if (stonesoup_checked_value <= 0) {
								stonesoup_checked_value = 1;
								DataInputBlock.neurismCuarenta
										.println("resetting value to 1");
							}
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							short stonesoup_counter = 2;
							Tracer.tracepointVariableShort("stonesoup_counter",
									stonesoup_counter);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							int lttngCtr = 99;
							while (stonesoup_counter < 10) {
								DataInputBlock.neurismCuarenta.println("Loop #"
										+ stonesoup_counter);
								if (stonesoup_counter > 0) {
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									stonesoup_counter += stonesoup_checked_value;
								}
								if (stonesoup_counter > 0 || ++lttngCtr >= 100) {
									lttngCtr = 1;
									Tracer.tracepointVariableShort(
											"stonesoup_counter",
											stonesoup_counter);
								}
							}
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							Tracer.tracepointVariableShort("stonesoup_counter",
									stonesoup_counter);
							DataInputBlock.neurismCuarenta
									.println("finished evaluating");
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						DataInputBlock.neurismCuarenta.close();
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
