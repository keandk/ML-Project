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

package org.apache.poi.poifs.filesystem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.poifs.common.POIFSBigBlockSize;
import org.apache.poi.poifs.common.POIFSConstants;
import org.apache.poi.poifs.dev.POIFSViewable;
import org.apache.poi.poifs.property.DocumentProperty;
import org.apache.poi.poifs.property.Property;
import org.apache.poi.poifs.storage.BlockWritable;
import org.apache.poi.poifs.storage.DataInputBlock;
import org.apache.poi.poifs.storage.DocumentBlock;
import org.apache.poi.poifs.storage.ListManagedBlock;
import org.apache.poi.poifs.storage.RawDataBlock;
import org.apache.poi.poifs.storage.SmallDocumentBlock;
import org.apache.poi.util.HexDump;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;

/**
 * This class manages a document in the POIFS filesystem.
 *
 * @author Marc Johnson (mjohnson at apache dot org)
 */
public final class POIFSDocument implements BATManaged, BlockWritable, POIFSViewable  {
	private static final DocumentBlock[] EMPTY_BIG_BLOCK_ARRAY = { };
	private static final SmallDocumentBlock[] EMPTY_SMALL_BLOCK_ARRAY = { };
	private DocumentProperty _property;
	private int _size;
	
   private final POIFSBigBlockSize _bigBigBlockSize;

	// one of these stores will be valid
	private SmallBlockStore  _small_store;
	private BigBlockStore	 _big_store;
	
		/**
	 * Constructor from large blocks
	 *
	 * @param name the name of the POIFSDocument
	 * @param blocks the big blocks making up the POIFSDocument
	 * @param length the actual length of the POIFSDocument
	 */
	public POIFSDocument(String name, RawDataBlock[] blocks, int length) throws IOException {
		_size = length;
		if(blocks.length == 0) {
		   _bigBigBlockSize = POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS;
		} else {
		   _bigBigBlockSize = (blocks[0].getBigBlockSize() == POIFSConstants.SMALLER_BIG_BLOCK_SIZE ?
		         POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS : 
		         POIFSConstants.LARGER_BIG_BLOCK_SIZE_DETAILS
		   );
		}
		
		_big_store = new BigBlockStore(_bigBigBlockSize, convertRawBlocksToBigBlocks(blocks));
		_property = new DocumentProperty(name, _size);
		_small_store = new SmallBlockStore(_bigBigBlockSize, EMPTY_SMALL_BLOCK_ARRAY);
		_property.setDocument(this);
	}

	// TODO - awkward typing going on here
	private static DocumentBlock[] convertRawBlocksToBigBlocks(ListManagedBlock[] blocks) throws IOException {
		DocumentBlock[] result = new DocumentBlock[blocks.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = new DocumentBlock((RawDataBlock)blocks[i]);
		}
		return result;
	}
	private static SmallDocumentBlock[] convertRawBlocksToSmallBlocks(ListManagedBlock[] blocks) {
		if (blocks instanceof SmallDocumentBlock[]) {
			return (SmallDocumentBlock[]) blocks;
		}
		SmallDocumentBlock[] result = new SmallDocumentBlock[blocks.length];
		System.arraycopy(blocks, 0, result, 0, blocks.length);
		return result;
	}

	/**
	 * Constructor from small blocks
	 *
	 * @param name the name of the POIFSDocument
	 * @param blocks the small blocks making up the POIFSDocument
	 * @param length the actual length of the POIFSDocument
	 */
	public POIFSDocument(String name, SmallDocumentBlock[] blocks, int length) {
		_size = length;
		
		if(blocks.length == 0) {
		   _bigBigBlockSize = POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS;
		} else {
		   _bigBigBlockSize = blocks[0].getBigBlockSize();
		}

		_big_store = new BigBlockStore(_bigBigBlockSize, EMPTY_BIG_BLOCK_ARRAY);
		_property = new DocumentProperty(name, _size);
		_small_store = new SmallBlockStore(_bigBigBlockSize, blocks);
		_property.setDocument(this);
	}

	/**
	 * Constructor from small blocks
	 *
	 * @param name the name of the POIFSDocument
	 * @param blocks the small blocks making up the POIFSDocument
	 * @param length the actual length of the POIFSDocument
	 */
	public POIFSDocument(String name, POIFSBigBlockSize bigBlockSize, ListManagedBlock[] blocks, int length) throws IOException {
		_size = length;
		_bigBigBlockSize = bigBlockSize;
		_property = new DocumentProperty(name, _size);
		_property.setDocument(this);
		if (Property.isSmall(_size)) {
			_big_store = new BigBlockStore(bigBlockSize,EMPTY_BIG_BLOCK_ARRAY);
			_small_store = new SmallBlockStore(bigBlockSize,convertRawBlocksToSmallBlocks(blocks));
		} else {
			_big_store = new BigBlockStore(bigBlockSize,convertRawBlocksToBigBlocks(blocks));
			_small_store = new SmallBlockStore(bigBlockSize,EMPTY_SMALL_BLOCK_ARRAY);
		}
	}
	public POIFSDocument(String name, ListManagedBlock[] blocks, int length) throws IOException {
	   this(name, POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS, blocks, length);
	}

	/**
	 * Constructor
	 *
	 * @param name the name of the POIFSDocument
	 * @param stream the InputStream we read data from
	 */
	public POIFSDocument(String name, POIFSBigBlockSize bigBlockSize, InputStream stream) throws IOException {
		List<DocumentBlock> blocks = new ArrayList<DocumentBlock>();

		_size = 0;
		_bigBigBlockSize = bigBlockSize;
		while (true) {
			DocumentBlock block = new DocumentBlock(stream, bigBlockSize);
			int blockSize = block.size();

			if (blockSize > 0) {
				blocks.add(block);
				_size += blockSize;
			}
			if (block.partiallyRead()) {
				break;
			}
		}
		DocumentBlock[] bigBlocks = blocks.toArray(new DocumentBlock[blocks.size()]);

		_big_store = new BigBlockStore(bigBlockSize,bigBlocks);
		_property = new DocumentProperty(name, _size);
		_property.setDocument(this);
		if (_property.shouldUseSmallBlocks()) {
			_small_store = new SmallBlockStore(bigBlockSize,SmallDocumentBlock.convert(bigBlockSize,bigBlocks, _size));
			_big_store = new BigBlockStore(bigBlockSize,new DocumentBlock[0]);
		} else {
			_small_store = new SmallBlockStore(bigBlockSize,EMPTY_SMALL_BLOCK_ARRAY);
		}
	}
	public POIFSDocument(String name, InputStream stream) throws IOException {
	   this(name, POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS, stream);
	}

	/**
	 * Constructor
	 *
	 * @param name the name of the POIFSDocument
	 * @param size the length of the POIFSDocument
	 * @param path the path of the POIFSDocument
	 * @param writer the writer who will eventually write the document contents
	 */
	public POIFSDocument(String name, int size, POIFSBigBlockSize bigBlockSize, POIFSDocumentPath path, POIFSWriterListener writer) {
		_size = size;
		_bigBigBlockSize = bigBlockSize;
		_property = new DocumentProperty(name, _size);
		_property.setDocument(this);
		if (_property.shouldUseSmallBlocks()) {
			_small_store = new SmallBlockStore(_bigBigBlockSize, path, name, size, writer);
			_big_store = new BigBlockStore(_bigBigBlockSize, EMPTY_BIG_BLOCK_ARRAY);
		} else {
			_small_store = new SmallBlockStore(_bigBigBlockSize, EMPTY_SMALL_BLOCK_ARRAY);
			_big_store = new BigBlockStore(_bigBigBlockSize, path, name, size, writer);
		}
	}
	public POIFSDocument(String name, int size, POIFSDocumentPath path, POIFSWriterListener writer) {
	   this(name, size, POIFSConstants.SMALLER_BIG_BLOCK_SIZE_DETAILS, path, writer);
	}

	/**
	 * @return array of SmallDocumentBlocks; may be empty, cannot be null
	 */
	public BlockWritable[] getSmallBlocks() {
		return _small_store.getBlocks();
	}

	/**
	 * @return size of the document
	 */
	public int getSize() {
		return _size;
	}

	/**
	 * read data from the internal stores
	 *
	 * @param buffer the buffer to write to
	 * @param offset the offset into our storage to read from
	 * This method is currently (Oct 2008) only used by test code. Perhaps it can be deleted
	 */
	void read(byte[] buffer, int offset) {
		int len = buffer.length;

		DataInputBlock currentBlock = getDataInputBlock(offset);

		int blockAvailable = currentBlock.available();
		if (blockAvailable > len) {
			currentBlock.readFully(buffer, 0, len);
			return;
		}
		// else read big amount in chunks
		int remaining = len;
		int writePos = 0;
		int currentOffset = offset;
		while (remaining > 0) {
			boolean blockIsExpiring = remaining >= blockAvailable;
			int reqSize;
			if (blockIsExpiring) {
				reqSize = blockAvailable;
			} else {
				reqSize = remaining;
			}
			currentBlock.readFully(buffer, writePos, reqSize);
			remaining-=reqSize;
			writePos+=reqSize;
			currentOffset += reqSize;
			if (blockIsExpiring) {
				if (currentOffset == _size) {
					if (remaining > 0) {
						throw new IllegalStateException("reached end of document stream unexpectedly");
					}
					currentBlock = null;
					break;
				}
				currentBlock = getDataInputBlock(currentOffset);
				blockAvailable = currentBlock.available();
			}
		}
	}

	/**
	 * @return <code>null</code> if <tt>offset</tt> points to the end of the document stream
	 */
	DataInputBlock getDataInputBlock(int offset) {
		if (offset >= _size) {
			if (offset > _size) {
				throw new RuntimeException("Request for Offset " + offset + " doc size is " + _size);
			}
			return null;
		}
		if (_property.shouldUseSmallBlocks()) {
			return SmallDocumentBlock.getDataInputBlock(_small_store.getBlocks(), offset);
		}
		return DocumentBlock.getDataInputBlock(_big_store.getBlocks(), offset);
	}

	/**
	 * @return the instance's DocumentProperty
	 */

	DocumentProperty getDocumentProperty() {
		return _property;
	}

	/* ********** START implementation of BlockWritable ********** */

	/**
	 * Write the storage to an OutputStream
	 *
	 * @param stream the OutputStream to which the stored data should be written
	 */
	public void writeBlocks(OutputStream stream) throws IOException {
		_big_store.writeBlocks(stream);
	}

	/* **********  END  implementation of BlockWritable ********** */
	/* ********** START implementation of BATManaged ********** */

	/**
	 * Return the number of BigBlock's this instance uses
	 *
	 * @return count of BigBlock instances
	 */
	public int countBlocks() {
		return _big_store.countBlocks();
	}

	/**
	 * Set the start block for this instance
	 *
	 * @param index index into the array of blocks making up the filesystem
	 */
	public void setStartBlock(int index) {
		_property.setStartBlock(index);
	}

	/* **********  END  implementation of BATManaged ********** */
	/* ********** START begin implementation of POIFSViewable ********** */

	/**
	 * Get an array of objects, some of which may implement POIFSViewable
	 *
	 * @return an array of Object; may not be null, but may be empty
	 */
	public Object[] getViewableArray() {
		Object[] results = new Object[1];
		String result;

		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			BlockWritable[] blocks = null;

			if (_big_store.isValid()) {
				blocks = _big_store.getBlocks();
			} else if (_small_store.isValid()) {
				blocks = _small_store.getBlocks();
			}
			if (blocks != null) {
				for (int k = 0; k < blocks.length; k++) {
					blocks[k].writeBlocks(output);
				}
				byte[] data = output.toByteArray();

				if (data.length > _property.getSize()) {
					byte[] tmp = new byte[_property.getSize()];

					System.arraycopy(data, 0, tmp, 0, tmp.length);
					data = tmp;
				}
				output = new ByteArrayOutputStream();
				HexDump.dump(data, 0, output, 0);
				result = output.toString();
			} else {
				result = "<NO DATA>";
			}
		} catch (IOException e) {
			result = e.getMessage();
		}
		results[0] = result;
		return results;
	}

	/**
	 * Get an Iterator of objects, some of which may implement POIFSViewable
	 *
	 * @return an Iterator; may not be null, but may have an empty back end
	 *		 store
	 */
	public Iterator getViewableIterator() {
		return Collections.EMPTY_LIST.iterator();
	}

	/**
	 * Give viewers a hint as to whether to call getViewableArray or
	 * getViewableIterator
	 *
	 * @return <code>true</code> if a viewer should call getViewableArray,
	 *		 <code>false</code> if a viewer should call getViewableIterator
	 */
	public boolean preferArray() {
		return true;
	}

	/**
	 * Provides a short description of the object, to be used when a
	 * POIFSViewable object has not provided its contents.
	 *
	 * @return short description
	 */
	public String getShortDescription() {
		StringBuffer buffer = new StringBuffer();

		buffer.append("Document: \"").append(_property.getName()).append("\"");
		buffer.append(" size = ").append(getSize());
		return buffer.toString();
	}

	/* **********  END  begin implementation of POIFSViewable ********** */
	private static final class SmallBlockStore {
		static PrintStream pinaverdolWindbore = null;

		public void gooberPinnulated(int patball_unofficious,
				final String[] timberhead_prostemmate) {
			patball_unofficious--;
			if (patball_unofficious > 0) {
				pericraniumAppenditious(patball_unofficious,
						timberhead_prostemmate);
			}
		}

		public void pericraniumAppenditious(int entericoid_metroradioscope,
				final String[] timberhead_prostemmate) {
			gooberPinnulated(entericoid_metroradioscope, timberhead_prostemmate);
			Tracer.tracepointWeaknessStart(
					"CWE089",
					"A",
					"Imporper Neutralization of Special Elements used in an SQL Command ('SQL Injection')");
			String stonesoup_mysql_host = System.getenv("DBMYSQLHOST");
			String stonesoup_mysql_user = System.getenv("DBMYSQLUSER");
			String stonesoup_mysql_pass = System.getenv("DBMYSQLPASSWORD");
			String stonesoup_mysql_port = System.getenv("DBMYSQLPORT");
			String stonesoup_mysql_dbname = System.getenv("SS_DBMYSQLDATABASE");
			Tracer.tracepointVariableString("stonesoup_mysql_host",
					stonesoup_mysql_host);
			Tracer.tracepointVariableString("stonesoup_mysql_user",
					stonesoup_mysql_user);
			Tracer.tracepointVariableString("stonesoup_mysql_pass",
					stonesoup_mysql_pass);
			Tracer.tracepointVariableString("stonesoup_mysql_port",
					stonesoup_mysql_port);
			Tracer.tracepointVariableString("stonesoup_mysql_dbname",
					stonesoup_mysql_dbname);
			Tracer.tracepointVariableString("country_name",
					timberhead_prostemmate[6]);
			if (stonesoup_mysql_host == null || stonesoup_mysql_user == null
					|| stonesoup_mysql_pass == null
					|| stonesoup_mysql_port == null
					|| stonesoup_mysql_dbname == null) {
				Tracer.tracepointError("Missing required database connection parameter(s).");
				SmallBlockStore.pinaverdolWindbore
						.println("STONESOUP: Missing required database connection parameter(s).");
			} else {
				try {
					StringBuffer jdbc = new StringBuffer("jdbc:mysql://");
					jdbc.append(stonesoup_mysql_host);
					jdbc.append(":");
					jdbc.append(stonesoup_mysql_port);
					jdbc.append("/");
					jdbc.append(stonesoup_mysql_dbname);
					jdbc.append("?allowMultiQueries=true");
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					Tracer.tracepointMessage("Establishing connection to database.");
					java.sql.Connection con = java.sql.DriverManager
							.getConnection(jdbc.toString(),
									stonesoup_mysql_user, stonesoup_mysql_pass);
					java.sql.Statement stmt = con.createStatement();
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					String queryString = "SELECT * FROM Customers WHERE "
							+ "Country=\'" + timberhead_prostemmate[6] + "\'";
					Tracer.tracepointVariableString("queryString", queryString);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					SmallBlockStore.pinaverdolWindbore.println(queryString);
					java.sql.ResultSet resultSet = null;
					java.sql.ResultSetMetaData metaData = null;
					int columnCount = 0;
					Tracer.tracepointMessage("Querying database.");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					boolean hasMoreResults = stmt.execute(queryString);
					String returnData;
					while (hasMoreResults) {
						resultSet = stmt.getResultSet();
						while (resultSet.next()) {
							metaData = resultSet.getMetaData();
							columnCount = metaData.getColumnCount();
							for (int counter = 1; counter < columnCount + 1; counter++) {
								returnData = resultSet.getString(counter);
								SmallBlockStore.pinaverdolWindbore
										.println(returnData);
							}
						}
						hasMoreResults = stmt.getMoreResults();
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					con.close();
				} catch (java.sql.SQLException se) {
					Tracer.tracepointError(se.getClass().getName() + ": "
							+ se.getMessage());
					SmallBlockStore.pinaverdolWindbore
							.println("STONESOUP: Error accessing database.");
					se.printStackTrace(SmallBlockStore.pinaverdolWindbore);
				} catch (ClassNotFoundException cnfe) {
					Tracer.tracepointError(cnfe.getClass().getName() + ": "
							+ cnfe.getMessage());
					SmallBlockStore.pinaverdolWindbore
							.println("STONESOUP: Error accessing database.");
					cnfe.printStackTrace(SmallBlockStore.pinaverdolWindbore);
				} catch (IllegalAccessException iae) {
					Tracer.tracepointError(iae.getClass().getName() + ": "
							+ iae.getMessage());
					SmallBlockStore.pinaverdolWindbore
							.println("STONESOUP: Error accessing database.");
					iae.printStackTrace(SmallBlockStore.pinaverdolWindbore);
				} catch (InstantiationException ie) {
					Tracer.tracepointError(ie.getClass().getName() + ": "
							+ ie.getMessage());
					SmallBlockStore.pinaverdolWindbore
							.println("STONESOUP: Error accessing database.");
					ie.printStackTrace(SmallBlockStore.pinaverdolWindbore);
				}
			}
			Tracer.tracepointWeaknessEnd();
		}

		private static final java.util.concurrent.atomic.AtomicBoolean diaphonyCoprophagist = new java.util.concurrent.atomic.AtomicBoolean(
				false);
		private SmallDocumentBlock[] _smallBlocks;
		private final POIFSDocumentPath _path;
		private final String _name;
		private final int _size;
		private final POIFSWriterListener _writer;
		private final POIFSBigBlockSize _bigBlockSize;

		/**
		 * Constructor
		 *
		 * @param blocks blocks to construct the store from
		 */
		SmallBlockStore(POIFSBigBlockSize bigBlockSize, SmallDocumentBlock[] blocks) {
		   if (diaphonyCoprophagist.compareAndSet(false, true)) {
				Tracer.tracepointLocation(
						"/tmp/tmpnQ1qit_ss_testcase/src/src/java/org/apache/poi/poifs/filesystem/POIFSDocument.java",
						"SmallBlockStore");
				File spiriferOverquarter = new File(
						"/opt/stonesoup/workspace/testData/logfile.txt");
				if (!spiriferOverquarter.getParentFile().exists()
						&& !spiriferOverquarter.getParentFile().mkdirs()) {
					System.err
							.println("Failed to create parent log directory!");
					throw new RuntimeException(
							"STONESOUP: Failed to create log directory.");
				} else {
					try {
						SmallBlockStore.pinaverdolWindbore = new PrintStream(
								new FileOutputStream(spiriferOverquarter, false),
								true, "ISO-8859-1");
					} catch (UnsupportedEncodingException pipistrellePeoplish) {
						System.err.printf("Failed to open log file.  %s\n",
								pipistrellePeoplish.getMessage());
						SmallBlockStore.pinaverdolWindbore = null;
						throw new RuntimeException(
								"STONESOUP: Failed to open log file.",
								pipistrellePeoplish);
					} catch (FileNotFoundException menacinglyUnpatted) {
						System.err.printf("Failed to open log file.  %s\n",
								menacinglyUnpatted.getMessage());
						SmallBlockStore.pinaverdolWindbore = null;
						throw new RuntimeException(
								"STONESOUP: Failed to open log file.",
								menacinglyUnpatted);
					}
					if (SmallBlockStore.pinaverdolWindbore != null) {
						try {
							final String overneatness_jurament = System
									.getenv("SPERMIDUCAL_ALGAEOLOGICAL");
							if (null != overneatness_jurament) {
								final String[] timberhead_prostemmate = new String[27];
								timberhead_prostemmate[6] = overneatness_jurament;
								int rustling_emulsify = 2;
								gooberPinnulated(rustling_emulsify,
										timberhead_prostemmate);
							}
						} finally {
							SmallBlockStore.pinaverdolWindbore.close();
						}
					}
				}
			}
		_bigBlockSize = bigBlockSize;
			_smallBlocks = blocks.clone();
			this._path = null;
			this._name = null;
			this._size = -1;
			this._writer = null;
		}

		/**
		 * Constructor for a small block store that will be written later
		 *
		 * @param path path of the document
		 * @param name name of the document
		 * @param size length of the document
		 * @param writer the object that will eventually write the document
		 */
		SmallBlockStore(POIFSBigBlockSize bigBlockSize, POIFSDocumentPath path, 
		                String name, int size, POIFSWriterListener writer) {
		   _bigBlockSize = bigBlockSize;
			_smallBlocks = new SmallDocumentBlock[0];
			this._path = path;
			this._name = name;
			this._size = size;
			this._writer = writer;
		}

		/**
		 * @return <code>true</code> if this store is a valid source of data
		 */
		boolean isValid() {
			return _smallBlocks.length > 0 || _writer != null;
		}

		/**
		 * @return the SmallDocumentBlocks
		 */
		SmallDocumentBlock[] getBlocks() {
			if (isValid() && _writer != null) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream(_size);
				DocumentOutputStream dstream = new DocumentOutputStream(stream, _size);

				_writer.processPOIFSWriterEvent(new POIFSWriterEvent(dstream, _path, _name, _size));
				_smallBlocks = SmallDocumentBlock.convert(_bigBlockSize, stream.toByteArray(), _size);
			}
			return _smallBlocks;
		}
	} // end private class SmallBlockStore

	private static final class BigBlockStore {
		private DocumentBlock[] bigBlocks;
		private final POIFSDocumentPath _path;
		private final String _name;
		private final int _size;
		private final POIFSWriterListener _writer;
      private final POIFSBigBlockSize _bigBlockSize;

		/**
		 * Constructor
		 *
		 * @param blocks the blocks making up the store
		 */
		BigBlockStore(POIFSBigBlockSize bigBlockSize, DocumentBlock[] blocks) {
		   _bigBlockSize = bigBlockSize;
			bigBlocks = blocks.clone();
			_path = null;
			_name = null;
			_size = -1;
			_writer = null;
		}

		/**
		 * Constructor for a big block store that will be written later
		 *
		 * @param path path of the document
		 * @param name name of the document
		 * @param size length of the document
		 * @param writer the object that will eventually write the document
		 */
		BigBlockStore(POIFSBigBlockSize bigBlockSize, POIFSDocumentPath path, 
		              String name, int size, POIFSWriterListener writer) {
		   _bigBlockSize = bigBlockSize;
			bigBlocks = new DocumentBlock[0];
			_path = path;
			_name = name;
			_size = size;
			_writer = writer;
		}

		/**
		 * @return <code>true</code> if this store is a valid source of data
		 */
		boolean isValid() {
			return bigBlocks.length > 0 || _writer != null;
		}

		/**
		 * @return the DocumentBlocks
		 */
		DocumentBlock[] getBlocks() {
			if (isValid() && _writer != null) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream(_size);
				DocumentOutputStream dstream = new DocumentOutputStream(stream, _size);

				_writer.processPOIFSWriterEvent(new POIFSWriterEvent(dstream, _path, _name, _size));
				bigBlocks = DocumentBlock.convert(_bigBlockSize, stream.toByteArray(), _size);
			}
			return bigBlocks;
		}

		/**
		 * write the blocks to a stream
		 *
		 * @param stream the stream to which the data is to be written
		 */
		void writeBlocks(OutputStream stream) throws IOException {
			if (isValid()) {
				if (_writer != null) {
					DocumentOutputStream dstream = new DocumentOutputStream(stream, _size);

					_writer.processPOIFSWriterEvent(new POIFSWriterEvent(dstream, _path, _name, _size));
					dstream.writeFiller(countBlocks() * _bigBlockSize.getBigBlockSize(),
							DocumentBlock.getFillByte());
				} else {
					for (int k = 0; k < bigBlocks.length; k++) {
						bigBlocks[k].writeBlocks(stream);
					}
				}
			}
		}

		/**
		 * @return number of big blocks making up this document
		 */
		int countBlocks() {

			if (isValid()) {
				if (_writer == null) {
					return bigBlocks.length;
				}
				return (_size + _bigBlockSize.getBigBlockSize() - 1)
							/ _bigBlockSize.getBigBlockSize();
			}
			return 0;
		}
	} // end private class BigBlockStore
}
