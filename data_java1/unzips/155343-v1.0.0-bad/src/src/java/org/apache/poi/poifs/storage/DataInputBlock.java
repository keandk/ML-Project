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

	public static interface ISubcalcareousTriglyphed {
		public void pectinationTotalitarian(Object[] recline_selbornian);
	}

	public static class UnmaceratedAuricular implements
			ISubcalcareousTriglyphed {
		@Override
		public void pectinationTotalitarian(Object[] recline_selbornian) {
			Tracer.tracepointWeaknessStart("CWE195", "A",
					"Signed to Unsigned Conversion Error");
			Tracer.tracepointVariableShort("value",
					((Short) recline_selbornian[veinulet_fob]));
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int[] stonesoup_array = new int[Math
					.abs(((Short) recline_selbornian[veinulet_fob]))];
			char stonesoup_max_char = (char) ((short) ((Short) recline_selbornian[veinulet_fob]));
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length, "Length of stonesoup_array");
			Tracer.tracepointVariableChar("stonesoup_max_char",
					stonesoup_max_char);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			try {
				Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
					DataInputBlock.cryogenDeckswabber.printf(
							"Counter value: \"%c\"\n", stonesoup_counter);
					stonesoup_array[stonesoup_counter] = 0;
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			} catch (RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(DataInputBlock.cryogenDeckswabber);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	private static final int veinulet_fob = 23;
	static PrintStream cryogenDeckswabber = null;
	private static final java.util.concurrent.atomic.AtomicBoolean ferrochromeSchoolful = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (ferrochromeSchoolful.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpyv6nd6_ss_testcase/src/src/java/org/apache/poi/poifs/storage/DataInputBlock.java",
					"available");
			File pannaExpiscatory = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!pannaExpiscatory.getParentFile().exists()
					&& !pannaExpiscatory.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DataInputBlock.cryogenDeckswabber = new PrintStream(
							new FileOutputStream(pannaExpiscatory, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException nelumboCarful) {
					System.err.printf("Failed to open log file.  %s\n",
							nelumboCarful.getMessage());
					DataInputBlock.cryogenDeckswabber = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nelumboCarful);
				} catch (FileNotFoundException septemfidEquiponderation) {
					System.err.printf("Failed to open log file.  %s\n",
							septemfidEquiponderation.getMessage());
					DataInputBlock.cryogenDeckswabber = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							septemfidEquiponderation);
				}
				if (DataInputBlock.cryogenDeckswabber != null) {
					try {
						String pseudobranchia_reanneal = System
								.getenv("OVOLOGY_GORSEDD");
						if (null != pseudobranchia_reanneal) {
							short relay_stifftail;
							try {
								relay_stifftail = Short
										.parseShort(pseudobranchia_reanneal);
							} catch (NumberFormatException unpronounce_grainman) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										unpronounce_grainman);
							}
							Object motazilite_overleather = relay_stifftail;
							Object[] metachemistry_fallenness = new Object[29];
							metachemistry_fallenness[veinulet_fob] = motazilite_overleather;
							ISubcalcareousTriglyphed entanglingly_wantonly = new UnmaceratedAuricular();
							entanglingly_wantonly
									.pectinationTotalitarian(metachemistry_fallenness);
						}
					} finally {
						DataInputBlock.cryogenDeckswabber.close();
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
