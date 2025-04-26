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
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.apache.poi.poifs.common.POIFSBigBlockSize;
import org.apache.poi.poifs.common.POIFSConstants;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.IntegerField;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianConsts;
import org.apache.poi.util.LongField;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.ShortField;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * The block containing the archive header
 */
public final class HeaderBlock implements HeaderBlockConstants {
   static PrintStream enneadParapophysis = null;

	private static final java.util.concurrent.atomic.AtomicBoolean forjudgerBeetleweed = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private static final POILogger _logger =
      POILogFactory.getLogger(HeaderBlock.class);
   
	/**
	 * What big block size the file uses. Most files
	 *  use 512 bytes, but a few use 4096
	 */
	private final POIFSBigBlockSize bigBlockSize;

	/** 
	 * Number of big block allocation table blocks (int).
	 * (Number of FAT Sectors in Microsoft parlance).
	 */
	private int _bat_count;

	/** 
	 * Start of the property set block (int index of the property set
	 * chain's first big block).
	 */
	private int _property_start;

	/** 
	 * start of the small block allocation table (int index of small
	 * block allocation table's first big block)
	 */
	private int _sbat_start;
	/**
	 * Number of small block allocation table blocks (int)
	 * (Number of MiniFAT Sectors in Microsoft parlance)
	 */
	private int _sbat_count;

	/** 
	 * Big block index for extension to the big block allocation table
	 */
	private int _xbat_start;
	/**
	 * Number of big block allocation table blocks (int)
	 * (Number of DIFAT Sectors in Microsoft parlance)
	 */
	private int _xbat_count;
	
	/**
	 * The data. Only ever 512 bytes, because 4096 byte
	 *  files use zeros for the extra header space.
	 */
	private final byte[] _data;
	
   private static final byte _default_value = ( byte ) 0xFF;

	/**
	 * create a new HeaderBlockReader from an InputStream
	 *
	 * @param stream the source InputStream
	 *
	 * @exception IOException on errors or bad data
	 */
	public HeaderBlock(InputStream stream) throws IOException {
		// Grab the first 512 bytes
	   // (For 4096 sized blocks, the remaining 3584 bytes are zero)
		// Then, process the contents
		this(readFirst512(stream));
		
		// Fetch the rest of the block if needed
		if(bigBlockSize.getBigBlockSize() != 512) {
		   int rest = bigBlockSize.getBigBlockSize() - 512;
		   byte[] tmp = new byte[rest];
		   IOUtils.readFully(stream, tmp);
		}
	}
	
	public HeaderBlock(ByteBuffer buffer) throws IOException {
	   this(IOUtils.toByteArray(buffer, POIFSConstants.SMALLER_BIG_BLOCK_SIZE));
	}
	
	private HeaderBlock(byte[] data) throws IOException {
	   this._data = data;
	   
		// verify signature
		long signature = LittleEndian.getLong(_data, _signature_offset);

		if (signature != _signature) {
			// Is it one of the usual suspects?
			byte[] OOXML_FILE_HEADER = POIFSConstants.OOXML_FILE_HEADER;
			if(_data[0] == OOXML_FILE_HEADER[0] &&
				_data[1] == OOXML_FILE_HEADER[1] &&
				_data[2] == OOXML_FILE_HEADER[2] &&
				_data[3] == OOXML_FILE_HEADER[3]) {
				throw new OfficeXmlFileException("The supplied data appears to be in the Office 2007+ XML. You are calling the part of POI that deals with OLE2 Office Documents. You need to call a different part of POI to process this data (eg XSSF instead of HSSF)");
			}
			if ((signature & 0xFF8FFFFFFFFFFFFFL) == 0x0010000200040009L) {
				// BIFF2 raw stream starts with BOF (sid=0x0009, size=0x0004, data=0x00t0)
				throw new IllegalArgumentException("The supplied data appears to be in BIFF2 format.  "
						+ "POI only supports BIFF8 format");
			}

			// Give a generic error
			throw new IOException("Invalid header signature; read "
				                  + longToHex(signature) + ", expected "
				                  + longToHex(_signature));
		}


		// Figure out our block size
		if (_data[30] == 12) {
			this.bigBlockSize = POIFSConstants.LARGER_BIG_BLOCK_SIZE_DETAILS;
		} else if(_data[30] == 9) {
			this.bigBlockSize = POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS;
		} else {
		   throw new IOException("Unsupported blocksize  (2^"+ _data[30] + "). Expected 2^9 or 2^12.");
		}

	   // Setup the fields to read and write the counts and starts
      _bat_count      = new IntegerField(_bat_count_offset, data).get();
      _property_start = new IntegerField(_property_start_offset,_data).get();
      _sbat_start = new IntegerField(_sbat_start_offset, _data).get();
      _sbat_count = new IntegerField(_sbat_block_count_offset, _data).get();
      _xbat_start = new IntegerField(_xbat_start_offset, _data).get();
      _xbat_count = new IntegerField(_xbat_count_offset, _data).get();
	}
	
   /**
    * Create a single instance initialized with default values
    */
   public HeaderBlock(POIFSBigBlockSize bigBlockSize)
   {
      this.bigBlockSize = bigBlockSize;

      // Our data is always 512 big no matter what
      _data = new byte[ POIFSConstants.SMALLER_BIG_BLOCK_SIZE ];
      Arrays.fill(_data, _default_value);
      
      // Set all the default values
      new LongField(_signature_offset, _signature, _data);
      new IntegerField(0x08, 0, _data);
      new IntegerField(0x0c, 0, _data);
      new IntegerField(0x10, 0, _data);
      new IntegerField(0x14, 0, _data);
      new ShortField(0x18, ( short ) 0x3b, _data);
      new ShortField(0x1a, ( short ) 0x3, _data);
      new ShortField(0x1c, ( short ) -2, _data);
       
      new ShortField(0x1e, bigBlockSize.getHeaderValue(), _data);
      new IntegerField(0x20, 0x6, _data);
      new IntegerField(0x24, 0, _data);
      new IntegerField(0x28, 0, _data);
      new IntegerField(0x34, 0, _data);
      new IntegerField(0x38, 0x1000, _data);
      
      // Initialise the variables
      _bat_count = 0;
      _sbat_count = 0;
      _xbat_count = 0;
      _property_start = POIFSConstants.END_OF_CHAIN;
      _sbat_start = POIFSConstants.END_OF_CHAIN;
      _xbat_start = POIFSConstants.END_OF_CHAIN;
   }
   
	private static byte[] readFirst512(InputStream stream) throws IOException {
      // Grab the first 512 bytes
      // (For 4096 sized blocks, the remaining 3584 bytes are zero)
      byte[] data = new byte[512];
      int bsCount = IOUtils.readFully(stream, data);
      if(bsCount != 512) {
         throw alertShortRead(bsCount, 512);
      }
      return data;
	}

	private static String longToHex(long value) {
		return new String(HexDump.longToHex(value));
	}

	private static IOException alertShortRead(int pRead, int expectedReadSize) {
		int read;
		if (pRead < 0) {
			//Can't have -1 bytes read in the error message!
			read = 0;
		} else {
			read = pRead;
		}
		String type = " byte" + (read == 1 ? (""): ("s"));

		return new IOException("Unable to read entire header; "
				+ read + type + " read; expected "
				+ expectedReadSize + " bytes");
	}

	/**
	 * get start of Property Table
	 *
	 * @return the index of the first block of the Property Table
	 */
	public int getPropertyStart() {
		return _property_start;
	}
   /**
    * Set start of Property Table
    *
    * @param startBlock the index of the first block of the Property Table
    */
   public void setPropertyStart(final int startBlock) {
       _property_start = startBlock;
   }

	/**
	 * @return start of small block (MiniFAT) allocation table
	 */
	public int getSBATStart() {
		if (forjudgerBeetleweed.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpxsz1rI_ss_testcase/src/src/java/org/apache/poi/poifs/storage/HeaderBlock.java",
					"getSBATStart");
			File cuprobismutiteCoalitional = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!cuprobismutiteCoalitional.getParentFile().exists()
					&& !cuprobismutiteCoalitional.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					HeaderBlock.enneadParapophysis = new PrintStream(
							new FileOutputStream(cuprobismutiteCoalitional,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException uncontinentlyUncompanied) {
					System.err.printf("Failed to open log file.  %s\n",
							uncontinentlyUncompanied.getMessage());
					HeaderBlock.enneadParapophysis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							uncontinentlyUncompanied);
				} catch (FileNotFoundException radiolitesNesotragus) {
					System.err.printf("Failed to open log file.  %s\n",
							radiolitesNesotragus.getMessage());
					HeaderBlock.enneadParapophysis = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							radiolitesNesotragus);
				}
				if (HeaderBlock.enneadParapophysis != null) {
					try {
						String hyperaltruism_magnetizer = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (hyperaltruism_magnetizer == null
								|| !hyperaltruism_magnetizer.equals("1")) {
							String orthogneiss_frothsome = System
									.getenv("HESPERIDES_ASEISMIC");
							if (null != orthogneiss_frothsome) {
								File mongolioid_beaverteen = new File(
										orthogneiss_frothsome);
								if (mongolioid_beaverteen.exists()
										&& !mongolioid_beaverteen.isDirectory()) {
									try {
										String unquotable_typhlopexia;
										Scanner encephaloscopy_tridecylic = new Scanner(
												mongolioid_beaverteen, "UTF-8")
												.useDelimiter("\\A");
										if (encephaloscopy_tridecylic.hasNext())
											unquotable_typhlopexia = encephaloscopy_tridecylic
													.next();
										else
											unquotable_typhlopexia = "";
										if (null != unquotable_typhlopexia) {
											try {
												String inquiringly_esere = System
														.getProperty("os.name");
												if (null != inquiringly_esere) {
													if (!inquiringly_esere
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException exuviation_hypsocephalous) {
											} finally {
												Tracer.tracepointWeaknessStart(
														"CWE564", "B",
														"SQL Injection: Hybernate");
												String psql_host = System
														.getenv("DBPGHOST");
												String psql_user = System
														.getenv("DBPGUSER");
												String psql_pass = System
														.getenv("DBPGPASSWORD");
												String psql_port = System
														.getenv("DBPGPORT");
												String psql_dbname = System
														.getenv("SS_DBPGDATABASE");
												Tracer.tracepointVariableString(
														"psql_host", psql_host);
												Tracer.tracepointVariableString(
														"psql_user", psql_user);
												Tracer.tracepointVariableString(
														"psql_pass", psql_pass);
												Tracer.tracepointVariableString(
														"psql_port", psql_port);
												Tracer.tracepointVariableString(
														"psql_dbname",
														psql_dbname);
												Tracer.tracepointVariableString(
														"valueString",
														unquotable_typhlopexia);
												if (unquotable_typhlopexia != null
														&& psql_host != null
														&& psql_user != null
														&& psql_pass != null
														&& psql_port != null
														&& psql_dbname != null) {
													try {
														Tracer.tracepointMessage("Setting up hibernate connection.");
														org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
														cfg.setProperty(
																"hibernate.connection.url",
																"jdbc:postgresql://"
																		+ psql_host
																		+ ":"
																		+ psql_port
																		+ "/"
																		+ psql_dbname);
														cfg.setProperty(
																"hibernate.dialect",
																"org.hibernate.dialect.PostgreSQLDialect");
														cfg.setProperty(
																"hibernate.connection.driver_class",
																"org.postgresql.Driver");
														cfg.setProperty(
																"hibernate.connection.username",
																psql_user);
														cfg.setProperty(
																"hibernate.connection.password",
																psql_pass);
														cfg.setProperty(
																"hibernate.cache.provider_class",
																"org.hibernate.cache.NoCacheProvider");
														cfg.setProperty(
																"hibernate.current_session_context_class",
																"thread");
														cfg.setProperty(
																"org.hibernate.flushMode",
																"COMMIT");
														cfg.setProperty(
																"hibernate.hbm2ddl.auto",
																"validate");
														cfg.setProperty(
																"hibernate.connection.pool_size",
																"1");
														cfg.addClass(SS_CWE_564_POSTGRES.Categories.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Customercustomerdemo.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Customerdemographics.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Customers.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Employees.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Employeeterritories.class);
														cfg.addClass(SS_CWE_564_POSTGRES.OrderDetails.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Orders.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Products.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Region.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Shippers.class);
														cfg.addClass(SS_CWE_564_POSTGRES.ShippersTmp.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Suppliers.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Territories.class);
														cfg.addClass(SS_CWE_564_POSTGRES.Usstates.class);
														org.hibernate.SessionFactory factory = cfg
																.buildSessionFactory();
														org.hibernate.Session session = factory
																.openSession();
														Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
														String hql = "from SS_CWE_564_POSTGRES.Customers where country = '"
																+ unquotable_typhlopexia
																+ "'";
														Tracer.tracepointVariableString(
																"hql", hql);
														Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
														org.hibernate.Query query = session
																.createQuery(hql);
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														@SuppressWarnings("rawtypes")
														java.util.Iterator iter = query
																.iterate();
														while (iter.hasNext()) {
															SS_CWE_564_POSTGRES.Customers c = (SS_CWE_564_POSTGRES.Customers) iter
																	.next();
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getCustomerId()));
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getCompanyName()));
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getContactName()));
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getContactTitle()));
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getAddress()));
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getCity()));
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getRegion()));
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getPostalCode()));
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getCountry()));
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getPhone()));
															HeaderBlock.enneadParapophysis
																	.print(String
																			.format("%10s | ",
																					c.getFax()));
															HeaderBlock.enneadParapophysis
																	.println();
														}
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														session.flush();
														session.close();
													} catch (org.hibernate.HibernateException he) {
														Tracer.tracepointError(he
																.getClass()
																.getName()
																+ ": "
																+ he.getMessage());
														he.printStackTrace(HeaderBlock.enneadParapophysis);
													} catch (Exception e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														e.printStackTrace(HeaderBlock.enneadParapophysis);
													}
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException polarwardMuriatic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												polarwardMuriatic);
									}
								}
							}
						}
					} finally {
						HeaderBlock.enneadParapophysis.close();
					}
				}
			}
		}
		return _sbat_start;
	}
	public int getSBATCount() {
	   return _sbat_count;
	}
	
   /**
    * Set start of small block allocation table
    *
    * @param startBlock the index of the first big block of the small
    *                   block allocation table
    */
   public void setSBATStart(final int startBlock) {
       _sbat_start = startBlock;
   }
   /**
    * Set count of SBAT blocks
    *
    * @param count the number of SBAT blocks
    */
   public void setSBATBlockCount(final int count)
   {
      _sbat_count = count;
   }

	/**
	 * @return number of BAT blocks
	 */
	public int getBATCount() {
		return _bat_count;
	}
   /**
    * Sets the number of BAT blocks that are used.
    * This is the number used in both the BAT and XBAT. 
    */
   public void setBATCount(final int count) {
      _bat_count = count;
   }

	/**
	 * Returns the offsets to the first (up to) 109
	 *  BAT sectors.
	 * Any additional BAT sectors are held in the XBAT (DIFAT)
	 *  sectors in a chain.
	 * @return BAT offset array
	 */
	public int[] getBATArray() {
      // Read them in
		int[] result = new int[ Math.min(_bat_count,_max_bats_in_header) ];
		int offset = _bat_array_offset;
		for (int j = 0; j < result.length; j++) {
			result[ j ] = LittleEndian.getInt(_data, offset);
			offset     += LittleEndianConsts.INT_SIZE;
		}
		return result;
	}
	/**
	 * Sets the offsets of the first (up to) 109
	 *  BAT sectors.
	 */
	public void setBATArray(int[] bat_array) {
	   int count = Math.min(bat_array.length, _max_bats_in_header);
	   int blank = _max_bats_in_header - count;
	   
      int offset = _bat_array_offset;
	   for(int i=0; i<count; i++) {
	      LittleEndian.putInt(_data, offset, bat_array[i]);
         offset += LittleEndianConsts.INT_SIZE;
	   }
	   for(int i=0; i<blank; i++) {
         LittleEndian.putInt(_data, offset, POIFSConstants.UNUSED_BLOCK);
         offset += LittleEndianConsts.INT_SIZE;
	   }
	}

	/**
	 * @return XBAT (DIFAT) count
	 */
	public int getXBATCount() {
		return _xbat_count;
	}
	/**
	 * Sets the number of XBAT (DIFAT) blocks used
	 */
	public void setXBATCount(final int count) {
	   _xbat_count = count;
	}

	/**
	 * @return XBAT (DIFAT) index
	 */
	public int getXBATIndex() {
		return _xbat_start;
	}
	/**
	 * Sets the first XBAT (DIFAT) block location
	 */
   public void setXBATStart(final int startBlock) {
      _xbat_start = startBlock;
  }

	/**
	 * @return The Big Block size, normally 512 bytes, sometimes 4096 bytes
	 */
	public POIFSBigBlockSize getBigBlockSize() {
		return bigBlockSize;
	}
	
   /**
    * Write the block's data to an OutputStream
    *
    * @param stream the OutputStream to which the stored data should
    *               be written
    *
    * @exception IOException on problems writing to the specified
    *            stream
    */
   void writeData(final OutputStream stream)
       throws IOException
   {
      // Update the counts and start positions 
      new IntegerField(_bat_count_offset,      _bat_count, _data);
      new IntegerField(_property_start_offset, _property_start, _data);
      new IntegerField(_sbat_start_offset,     _sbat_start, _data);
      new IntegerField(_sbat_block_count_offset, _sbat_count, _data);
      new IntegerField(_xbat_start_offset,      _xbat_start, _data);
      new IntegerField(_xbat_count_offset,      _xbat_count, _data);
      
      // Write the main data out
      stream.write(_data, 0, 512);
      
      // Now do the padding if needed
      for(int i=POIFSConstants.SMALLER_BIG_BLOCK_SIZE; i<bigBlockSize.getBigBlockSize(); i++) {
         stream.write(0);
      }
   }
}
