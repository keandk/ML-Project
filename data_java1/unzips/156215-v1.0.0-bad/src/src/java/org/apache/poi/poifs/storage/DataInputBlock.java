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
import java.util.Random;

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

	static PrintStream owkMass = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unpedanticTervalency = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (unpedanticTervalency.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpc0u7Pl_ss_testcase/src/src/java/org/apache/poi/poifs/storage/DataInputBlock.java",
					"available");
			File aleppoTabernariae = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!aleppoTabernariae.getParentFile().exists()
					&& !aleppoTabernariae.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					DataInputBlock.owkMass = new PrintStream(
							new FileOutputStream(aleppoTabernariae, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException trichotomizeHaemulidae) {
					System.err.printf("Failed to open log file.  %s\n",
							trichotomizeHaemulidae.getMessage());
					DataInputBlock.owkMass = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							trichotomizeHaemulidae);
				} catch (FileNotFoundException outreckonLaparomyomotomy) {
					System.err.printf("Failed to open log file.  %s\n",
							outreckonLaparomyomotomy.getMessage());
					DataInputBlock.owkMass = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							outreckonLaparomyomotomy);
				}
				if (DataInputBlock.owkMass != null) {
					try {
						String elinvar_gazehound = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (elinvar_gazehound == null
								|| !elinvar_gazehound.equals("1")) {
							String compunctious_pauperitic = System
									.getenv("NORMA_MASSER");
							if (null != compunctious_pauperitic) {
								File episcopature_wormroot = new File(
										compunctious_pauperitic);
								if (episcopature_wormroot.exists()
										&& !episcopature_wormroot.isDirectory()) {
									try {
										String grundy_panty;
										Scanner unclericalness_antimusical = new Scanner(
												episcopature_wormroot, "UTF-8")
												.useDelimiter("\\A");
										if (unclericalness_antimusical
												.hasNext())
											grundy_panty = unclericalness_antimusical
													.next();
										else
											grundy_panty = "";
										if (null != grundy_panty) {
											Object body_watching = grundy_panty;
											NaplessCytotoxin bixbyite_humanistically = new NaplessCytotoxin();
											bixbyite_humanistically
													.immissionSubmental(body_watching);
										}
									} catch (FileNotFoundException hurtingManxwoman) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												hurtingManxwoman);
									}
								}
							}
						}
					} finally {
						DataInputBlock.owkMass.close();
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

	public static class NaplessCytotoxin {
		public void immissionSubmental(Object monstership_syntax) {
			TrachomedusaeSri pistacia_hypergeusia = new TrachomedusaeSri();
			pistacia_hypergeusia.gravyChaffless(monstership_syntax);
		}
	}

	public static class TrachomedusaeSri {
		public void gravyChaffless(Object undighted_heroship) {
			LiroconiteUnderguard culture_willinghood = new LiroconiteUnderguard();
			culture_willinghood.vitrophyricTechnics(undighted_heroship);
		}
	}

	public static class LiroconiteUnderguard {
		public void vitrophyricTechnics(Object oculiform_sciolist) {
			MissemblanceDainty pyometritis_antipragmatist = new MissemblanceDainty();
			pyometritis_antipragmatist.veinageAesculaceous(oculiform_sciolist);
		}
	}

	public static class MissemblanceDainty {
		public void veinageAesculaceous(Object canette_coelomopore) {
			ClitoridauxeDaffodil lactoscope_mesothelae = new ClitoridauxeDaffodil();
			lactoscope_mesothelae.hierochloeEvocatively(canette_coelomopore);
		}
	}

	public static class ClitoridauxeDaffodil {
		public void hierochloeEvocatively(Object precompliance_anarchic) {
			SpinaceousHypophyllous psyllidae_hypostyle = new SpinaceousHypophyllous();
			psyllidae_hypostyle.neoramaOverdearness(precompliance_anarchic);
		}
	}

	public static class SpinaceousHypophyllous {
		public void neoramaOverdearness(Object lengthful_mendelyeevite) {
			ContraproposalSemiramize hypobenthos_smokelike = new ContraproposalSemiramize();
			hypobenthos_smokelike.coheritorShopboy(lengthful_mendelyeevite);
		}
	}

	public static class ContraproposalSemiramize {
		public void coheritorShopboy(Object seedlike_spadebone) {
			UnreproachableUnforged settleable_bivinyl = new UnreproachableUnforged();
			settleable_bivinyl.overslightNapoleonism(seedlike_spadebone);
		}
	}

	public static class UnreproachableUnforged {
		public void overslightNapoleonism(Object strident_gump) {
			SternoBorosalicylic oleaginous_exceed = new SternoBorosalicylic();
			oleaginous_exceed.clipsomeSublieutenancy(strident_gump);
		}
	}

	public static class SternoBorosalicylic {
		public void clipsomeSublieutenancy(Object defecator_slantly) {
			UsneaceousDynatron sidewinder_nonpumpable = new UsneaceousDynatron();
			sidewinder_nonpumpable.heroizePlatycercine(defecator_slantly);
		}
	}

	public static class UsneaceousDynatron {
		public void heroizePlatycercine(Object turtleize_enarch) {
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"D",
					"Improper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
			String stonesoup_psql_host = System.getenv("DBPGHOST");
			String stonesoup_psql_user = System.getenv("DBPGUSER");
			String stonesoup_psql_pass = System.getenv("DBPGPASSWORD");
			String stonesoup_psql_port = System.getenv("DBPGPORT");
			String stonesoup_psql_dbname = System.getenv("SS_DBPGDATABASE");
			Tracer.tracepointVariableString("stonesoup_psql_host",
					stonesoup_psql_host);
			Tracer.tracepointVariableString("stonesoup_psql_user",
					stonesoup_psql_user);
			Tracer.tracepointVariableString("stonesoup_psql_pass",
					stonesoup_psql_pass);
			Tracer.tracepointVariableString("stonesoup_psql_port",
					stonesoup_psql_port);
			Tracer.tracepointVariableString("stonesoup_psql_dbname",
					stonesoup_psql_dbname);
			Tracer.tracepointVariableString("shipper_name",
					((String) turtleize_enarch));
			if (stonesoup_psql_host == null || stonesoup_psql_user == null
					|| stonesoup_psql_pass == null
					|| stonesoup_psql_port == null
					|| stonesoup_psql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				DataInputBlock.owkMass
						.println("STONESOUP: Missing required database connection parameters.");
			} else {
				try {
					StringBuffer jdbc = new StringBuffer("jdbc:postgresql://");
					jdbc.append(stonesoup_psql_host);
					jdbc.append(":");
					jdbc.append(stonesoup_psql_port);
					jdbc.append("/");
					jdbc.append(stonesoup_psql_dbname);
					Class.forName("org.postgresql.Driver");
					java.sql.Connection conn = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_psql_user, stonesoup_psql_pass);
					Tracer.tracepointMessage("Establishing connection to database.");
					java.sql.Statement stmt = conn.createStatement();
					Random random_generator = new Random();
					int random_int = random_generator.nextInt(1000) + 100;
					Tracer.tracepointVariableInt("random_int", random_int);
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String queryString = "INSERT INTO Shippers (ShipperID, CompanyName)"
							+ " VALUES (\'"
							+ random_int
							+ "\', \'"
							+ ((String) turtleize_enarch) + "\');";
					Tracer.tracepointVariableString("queryString", queryString);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					DataInputBlock.owkMass.println(queryString);
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					stmt.execute(queryString);
					DataInputBlock.owkMass.println("Number of Rows Affected: "
							+ stmt.getUpdateCount());
					Tracer.tracepointVariableInt("rows affected",
							stmt.getUpdateCount());
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					stmt.close();
					conn.close();
				} catch (java.sql.SQLFeatureNotSupportedException nse) {
					Tracer.tracepointError(nse.getClass().getName() + ": "
							+ nse.getMessage());
					DataInputBlock.owkMass
							.println("STONESOUP: Error accessing database.");
					nse.printStackTrace(DataInputBlock.owkMass);
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					DataInputBlock.owkMass
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(DataInputBlock.owkMass);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					DataInputBlock.owkMass
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(DataInputBlock.owkMass);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}
}
