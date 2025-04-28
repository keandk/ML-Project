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
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;

/**
 * The block containing the archive header
 */
public final class HeaderBlock implements HeaderBlockConstants {
   public class SuppositiveCarga {
		private Object sarcina_mangrove;

		public SuppositiveCarga(Object sarcina_mangrove) {
			this.sarcina_mangrove = sarcina_mangrove;
		}

		public Object getsarcina_mangrove() {
			return this.sarcina_mangrove;
		}
	}

	static PrintStream recussionDesperation = null;

	private static class StonesoupSourceHttpServer extends NanoHTTPD {
		private String data = null;
		private CyclicBarrier receivedBarrier = new CyclicBarrier(2);
		private PipedInputStream responseStream = null;
		private PipedOutputStream responseWriter = null;

		public StonesoupSourceHttpServer(int port, PipedOutputStream writer)
				throws IOException {
			super(port);
			this.responseWriter = writer;
		}

		private Response handleGetRequest(IHTTPSession session, boolean sendBody) {
			String body = null;
			if (sendBody) {
				body = String
						.format("Request Approved!\n\n"
								+ "Thank you for you interest in \"%s\".\n\n"
								+ "We appreciate your inquiry.  Please visit us again!",
								session.getUri());
			}
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT,
					body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleOptionsRequest(IHTTPSession session) {
			NanoHTTPD.Response response = new NanoHTTPD.Response(null);
			response.setStatus(NanoHTTPD.Response.Status.OK);
			response.setMimeType(NanoHTTPD.MIME_PLAINTEXT);
			response.addHeader("Allow", "GET, PUT, POST, HEAD, OPTIONS");
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleUnallowedRequest(IHTTPSession session) {
			String body = String.format("Method Not Allowed!\n\n"
					+ "Thank you for your request, but we are unable "
					+ "to process that method.  Please try back later.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.METHOD_NOT_ALLOWED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handlePostRequest(IHTTPSession session) {
			String body = String
					.format("Request Data Processed!\n\n"
							+ "Thank you for your contribution.  Please keep up the support.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.CREATED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private NanoHTTPD.Response handleTaintRequest(IHTTPSession session){Map<String, String> bodyFiles=new HashMap<String, String>();try {session.parseBody(bodyFiles);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}catch (ResponseException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}if (!session.getParms().containsKey("data")){return writeErrorResponse(session,Response.Status.BAD_REQUEST,"Missing required field \"data\".");}this.data=session.getParms().get("data");try {this.responseStream=new PipedInputStream(this.responseWriter);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}NanoHTTPD.Response response=new NanoHTTPD.Response(NanoHTTPD.Response.Status.CREATED,NanoHTTPD.MIME_PLAINTEXT,this.responseStream);this.setResponseOptions(session,response);response.setChunkedTransfer(true);try {this.receivedBarrier.await();} catch (InterruptedException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}catch (BrokenBarrierException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}return response;}		private NanoHTTPD.Response writeErrorResponse(IHTTPSession session,
				NanoHTTPD.Response.Status status, String message) {
			String body = String.format(
					"There was an issue processing your request!\n\n"
							+ "Reported Error Message:\n\n%s.", message);
			NanoHTTPD.Response response = new NanoHTTPD.Response(status,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private void setResponseOptions(IHTTPSession session,
				NanoHTTPD.Response response) {
			response.setRequestMethod(session.getMethod());
		}

		@Override
		public Response serve(IHTTPSession session) {
			Method method = session.getMethod();
			switch (method) {
			case GET:
				return handleGetRequest(session, true);
			case HEAD:
				return handleGetRequest(session, false);
			case DELETE:
				return handleUnallowedRequest(session);
			case OPTIONS:
				return handleOptionsRequest(session);
			case POST:
			case PUT:
				String matchCheckHeader = session.getHeaders().get("if-match");
				if (matchCheckHeader == null
						|| !matchCheckHeader
								.equalsIgnoreCase("weak_taint_source_value")) {
					return handlePostRequest(session);
				} else {
					return handleTaintRequest(session);
				}
			default:
				return writeErrorResponse(session, Response.Status.BAD_REQUEST,
						"Unsupported request method.");
			}
		}

		public String getData() throws IOException {
			try {
				this.receivedBarrier.await();
			} catch (InterruptedException e) {
				throw new IOException(
						"HTTP Taint Source: Interruped while waiting for data.",
						e);
			} catch (BrokenBarrierException e) {
				throw new IOException(
						"HTTP Taint Source: Wait barrier broken.", e);
			}
			return this.data;
		}
	}

	private static final java.util.concurrent.atomic.AtomicBoolean volleyinglyAcetabulous = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (volleyinglyAcetabulous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmphjuTzo_ss_testcase/src/src/java/org/apache/poi/poifs/storage/HeaderBlock.java",
					"getSBATStart");
			String reedwork_sialoschesis = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (reedwork_sialoschesis == null
					|| !reedwork_sialoschesis.equals("1")) {
				StonesoupSourceHttpServer overabsorb_cyanophyceous = null;
				PipedOutputStream fricativeEuphemism = new PipedOutputStream();
				try {
					HeaderBlock.recussionDesperation = new PrintStream(
							fricativeEuphemism, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException blancoPreadhere) {
					System.err.printf("Failed to open log file.  %s\n",
							blancoPreadhere.getMessage());
					HeaderBlock.recussionDesperation = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							blancoPreadhere);
				}
				if (HeaderBlock.recussionDesperation != null) {
					try {
						String natrix_nonabsolute;
						try {
							overabsorb_cyanophyceous = new StonesoupSourceHttpServer(
									8887, fricativeEuphemism);
							overabsorb_cyanophyceous.start();
							natrix_nonabsolute = overabsorb_cyanophyceous
									.getData();
						} catch (IOException unscrewed_caterva) {
							overabsorb_cyanophyceous = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									unscrewed_caterva);
						} catch (Exception overhastiness_bipupillate) {
							overabsorb_cyanophyceous = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									overhastiness_bipupillate);
						}
						if (null != natrix_nonabsolute) {
							short lebanese_myrabolam;
							try {
								lebanese_myrabolam = Short
										.parseShort(natrix_nonabsolute);
							} catch (NumberFormatException albigensianism_wishfulness) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										albigensianism_wishfulness);
							}
							Object patronless_specter = lebanese_myrabolam;
							SuppositiveCarga devotedly_bororoan = new SuppositiveCarga(
									patronless_specter);
							ProbationerPhosphocarnic pamplegia_mastwood = new ProbationerPhosphocarnic();
							pamplegia_mastwood
									.unmossedDecoloration(devotedly_bororoan);
						}
					} finally {
						HeaderBlock.recussionDesperation.close();
						if (overabsorb_cyanophyceous != null)
							overabsorb_cyanophyceous.stop(true);
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

public static class ProbationerPhosphocarnic {
	public void unmossedDecoloration(SuppositiveCarga zuccarino_flatwoods) {
		Tracer.tracepointWeaknessStart("CWE191", "A",
				"Integer Underflow (Wrap or Wraparound)");
		short stonesoup_checked_value = ((Short) zuccarino_flatwoods
				.getsarcina_mangrove());
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		if (stonesoup_checked_value < 0) {
			stonesoup_checked_value = 0;
		}
		Tracer.tracepointVariableShort("stonesoup_checked_value",
				stonesoup_checked_value);
		Short[] stonesoup_some_values = new Short[] { 0, 1, 2, 3, 4, 5, 6, 7,
				8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		short stonesoup_counter = -20;
		short stonesoup_offset = 40;
		Tracer.tracepointBufferInfo("stonesoup_some_values",
				stonesoup_some_values.length, "Length of stonesoup_some_values");
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		Tracer.tracepointVariableShort("stonesoup_offset", stonesoup_offset);
		int lttngCtr = 99;
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		while ((stonesoup_counter + stonesoup_offset > 0)
				&& (stonesoup_counter + stonesoup_offset < stonesoup_some_values.length)) {
			HeaderBlock.recussionDesperation
					.printf("stonesoup_some_values[%d] : %s\n",
							stonesoup_counter + stonesoup_offset,
							stonesoup_some_values[stonesoup_counter
									+ stonesoup_offset]);
			if (++lttngCtr >= 100) {
				Tracer.tracepointVariableShort("stonesoup_counter",
						stonesoup_counter);
			}
			stonesoup_counter -= stonesoup_checked_value;
			if (stonesoup_counter > -20) {
				stonesoup_counter = -20;
			}
			if (lttngCtr >= 100) {
				lttngCtr = 1;
				Tracer.tracepointVariableShort("stonesoup_counter",
						stonesoup_counter);
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointBufferInfo("stonesoup_some_values",
				stonesoup_some_values.length, "Length of stonesoup_some_values");
		Tracer.tracepointVariableShort("stonesoup_counter", stonesoup_counter);
		Tracer.tracepointVariableShort("stonesoup_offset", stonesoup_offset);
		HeaderBlock.recussionDesperation.println("finished evaluating");
		Tracer.tracepointWeaknessEnd();
	}
}
}
