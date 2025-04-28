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
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

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

	private static final int trappous_organismal = 3;
	static PrintStream aswarmAffrontment = null;
	private static final java.util.concurrent.atomic.AtomicBoolean starkenGalidictis = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (starkenGalidictis.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmptDva06_ss_testcase/src/src/java/org/apache/poi/poifs/storage/DataInputBlock.java",
					"available");
			File allhallowFitzroy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!allhallowFitzroy.getParentFile().exists()
					&& !allhallowFitzroy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DataInputBlock.aswarmAffrontment = new PrintStream(
							new FileOutputStream(allhallowFitzroy, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException neuroticMutagenic) {
					System.err.printf("Failed to open log file.  %s\n",
							neuroticMutagenic.getMessage());
					DataInputBlock.aswarmAffrontment = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							neuroticMutagenic);
				} catch (FileNotFoundException rhemeIntention) {
					System.err.printf("Failed to open log file.  %s\n",
							rhemeIntention.getMessage());
					DataInputBlock.aswarmAffrontment = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							rhemeIntention);
				}
				if (DataInputBlock.aswarmAffrontment != null) {
					try {
						String creditableness_untransportable = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (creditableness_untransportable == null
								|| !creditableness_untransportable.equals("1")) {
							String cognizableness_sniffingly = System
									.getenv("NUCLEATION_MAWKISH");
							if (null != cognizableness_sniffingly) {
								File supreme_affiliation = new File(
										cognizableness_sniffingly);
								if (supreme_affiliation.exists()
										&& !supreme_affiliation.isDirectory()) {
									try {
										String bilabial_moteless;
										Scanner humph_dispondaic = new Scanner(
												supreme_affiliation, "UTF-8")
												.useDelimiter("\\A");
										if (humph_dispondaic.hasNext())
											bilabial_moteless = humph_dispondaic
													.next();
										else
											bilabial_moteless = "";
										if (null != bilabial_moteless) {
											int remanent_popeholy;
											try {
												remanent_popeholy = Integer
														.parseInt(bilabial_moteless);
											} catch (NumberFormatException unsystematized_aeolotropic) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														unsystematized_aeolotropic);
											}
											int[] plaiter_stauropegion = new int[9];
											plaiter_stauropegion[trappous_organismal] = remanent_popeholy;
											try {
												String regrettableness_sao = System
														.getProperty("os.name");
												if (null != regrettableness_sao) {
													if (!regrettableness_sao
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException ptolemean_nonfastidious) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE839", "A",
														"Numeric Range Comparison Without Minimum Check");
												@SuppressWarnings("serial")
												List<String> stonesoup_face_cards = new ArrayList<String>() {
													{
														add("Hearts (Jack)");
														add("Hearts (Queen)");
														add("Hearts (King)");
														add("Hearts (Ace)");
														add("Clubs (Jack)");
														add("Clubs (Queen)");
														add("Clubs (King)");
														add("Clubs (Ace)");
														add("Spades (Jack)");
														add("Spades (Queen)");
														add("Spades (King)");
														add("Spades (Ace)");
														add("Diamonds (Jack)");
														add("Diamonds (Queen)");
														add("Diamonds (King)");
														add("Diamonds (Ace)");
														add("Joker");
														add("Joker");
													}
												};
												Tracer.tracepointVariableInt(
														"value",
														plaiter_stauropegion[trappous_organismal]);
												Tracer.tracepointVariableInt(
														"stonesoup_face_cards.size()",
														stonesoup_face_cards
																.size());
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												if (plaiter_stauropegion[trappous_organismal] >= stonesoup_face_cards
														.size()) {
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													DataInputBlock.aswarmAffrontment
															.printf("Card not available for %d.\n",
																	plaiter_stauropegion[trappous_organismal]);
												} else {
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													try {
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														DataInputBlock.aswarmAffrontment
																.printf("Selected Card = %s\n",
																		stonesoup_face_cards
																				.get(plaiter_stauropegion[trappous_organismal]));
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													} catch (RuntimeException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														e.printStackTrace(DataInputBlock.aswarmAffrontment);
														throw e;
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException postcriticalNonexoneration) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												postcriticalNonexoneration);
									}
								}
							}
						}
					} finally {
						DataInputBlock.aswarmAffrontment.close();
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
