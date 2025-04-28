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
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
		private static final int exclusion_maneuverability = 3;
		static PrintStream bacteriostatHiram = null;

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

			private Response handleGetRequest(IHTTPSession session,
					boolean sendBody) {
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

			private NanoHTTPD.Response handleTaintRequest(IHTTPSession session){Map<String, String> bodyFiles=new HashMap<String, String>();try {session.parseBody(bodyFiles);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}catch (ResponseException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}if (!session.getParms().containsKey("data")){return writeErrorResponse(session,Response.Status.BAD_REQUEST,"Missing required field \"data\".");}this.data=session.getParms().get("data");try {this.responseStream=new PipedInputStream(this.responseWriter);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}NanoHTTPD.Response response=new NanoHTTPD.Response(NanoHTTPD.Response.Status.CREATED,NanoHTTPD.MIME_PLAINTEXT,this.responseStream);this.setResponseOptions(session,response);response.setChunkedTransfer(true);try {this.receivedBarrier.await();} catch (InterruptedException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}catch (BrokenBarrierException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}return response;}			private NanoHTTPD.Response writeErrorResponse(IHTTPSession session,
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
					String matchCheckHeader = session.getHeaders().get(
							"if-match");
					if (matchCheckHeader == null
							|| !matchCheckHeader
									.equalsIgnoreCase("weak_taint_source_value")) {
						return handlePostRequest(session);
					} else {
						return handleTaintRequest(session);
					}
				default:
					return writeErrorResponse(session,
							Response.Status.BAD_REQUEST,
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

		private static final java.util.concurrent.atomic.AtomicBoolean basophilicThrowing = new java.util.concurrent.atomic.AtomicBoolean(
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
		   if (basophilicThrowing.compareAndSet(false, true)) {
				Tracer.tracepointLocation(
						"/tmp/tmpMCTbA6_ss_testcase/src/src/java/org/apache/poi/poifs/filesystem/POIFSDocument.java",
						"SmallBlockStore");
				String choirboy_cardiocirrhosis = System
						.getenv("STONESOUP_DISABLE_WEAKNESS");
				if (choirboy_cardiocirrhosis == null
						|| !choirboy_cardiocirrhosis.equals("1")) {
					StonesoupSourceHttpServer inconcrete_lectern = null;
					PipedOutputStream skeletonlessOutjazz = new PipedOutputStream();
					try {
						SmallBlockStore.bacteriostatHiram = new PrintStream(
								skeletonlessOutjazz, true, "ISO-8859-1");
					} catch (UnsupportedEncodingException singhaleseAntarctic) {
						System.err.printf("Failed to open log file.  %s\n",
								singhaleseAntarctic.getMessage());
						SmallBlockStore.bacteriostatHiram = null;
						throw new RuntimeException(
								"STONESOUP: Failed to create piped print stream.",
								singhaleseAntarctic);
					}
					if (SmallBlockStore.bacteriostatHiram != null) {
						try {
							String hypodicrotic_wantingly;
							try {
								inconcrete_lectern = new StonesoupSourceHttpServer(
										8887, skeletonlessOutjazz);
								inconcrete_lectern.start();
								hypodicrotic_wantingly = inconcrete_lectern
										.getData();
							} catch (IOException achromaturia_dermataneuria) {
								inconcrete_lectern = null;
								throw new RuntimeException(
										"STONESOUP: Failed to start HTTP server.",
										achromaturia_dermataneuria);
							} catch (Exception paralepsis_severish) {
								inconcrete_lectern = null;
								throw new RuntimeException(
										"STONESOUP: Unknown error with HTTP server.",
										paralepsis_severish);
							}
							if (null != hypodicrotic_wantingly) {
								int jaspopal_synclinorium;
								try {
									jaspopal_synclinorium = Integer
											.parseInt(hypodicrotic_wantingly);
								} catch (NumberFormatException autohemotherapy_ceruminal) {
									throw new RuntimeException(
											"STONESOUP: Failed to convert source taint.",
											autohemotherapy_ceruminal);
								}
								Object disentanglement_swordbill = jaspopal_synclinorium;
								Object[] okshoofd_wallowish = new Object[31];
								okshoofd_wallowish[exclusion_maneuverability] = disentanglement_swordbill;
								CantharidianRecivilize caucasoid_sculpture = new CantharidianRecivilize();
								caucasoid_sculpture
										.nonlocalBlighter(okshoofd_wallowish);
							}
						} finally {
							SmallBlockStore.bacteriostatHiram.close();
							if (inconcrete_lectern != null)
								inconcrete_lectern.stop(true);
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

		public static class CantharidianRecivilize {
			public void nonlocalBlighter(Object[] cephalotractor_twitty) {
				UnwiseSapphireberry disimbitter_mycobacteria = new UnwiseSapphireberry();
				disimbitter_mycobacteria.blitumFeal(cephalotractor_twitty);
			}
		}

		public static class UnwiseSapphireberry {
			public void blitumFeal(Object[] crubeen_envy) {
				MigrativeAllegorically syzygy_myriarch = new MigrativeAllegorically();
				syzygy_myriarch.teretiscapularHabiru(crubeen_envy);
			}
		}

		public static class MigrativeAllegorically {
			public void teretiscapularHabiru(Object[] melanthaceae_cumuli) {
				PhacoglaucomaReincarnate kef_resalutation = new PhacoglaucomaReincarnate();
				kef_resalutation.stoollikeFifie(melanthaceae_cumuli);
			}
		}

		public static class PhacoglaucomaReincarnate {
			public void stoollikeFifie(Object[] ocellicystic_spiritweed) {
				EvestarParaplegy deducible_switchlike = new EvestarParaplegy();
				deducible_switchlike.ierneFeatural(ocellicystic_spiritweed);
			}
		}

		public static class EvestarParaplegy {
			public void ierneFeatural(Object[] eucharistically_rhineura) {
				UnrraAlethoscope drabbet_unboldness = new UnrraAlethoscope();
				drabbet_unboldness.raciallyTrilobated(eucharistically_rhineura);
			}
		}

		public static class UnrraAlethoscope {
			public void raciallyTrilobated(Object[] mugiliform_weaponeer) {
				PalpedScrimshanker odontonecrosis_wloka = new PalpedScrimshanker();
				odontonecrosis_wloka
						.ovibovineSuperstandard(mugiliform_weaponeer);
			}
		}

		public static class PalpedScrimshanker {
			public void ovibovineSuperstandard(Object[] trichinosis_canicular) {
				DesucrationSnipefish camphanone_punstress = new DesucrationSnipefish();
				camphanone_punstress
						.ithomiidaeHesperornithid(trichinosis_canicular);
			}
		}

		public static class DesucrationSnipefish {
			public void ithomiidaeHesperornithid(Object[] turfy_synaxarion) {
				AlluviumCephalomotor preseason_suitably = new AlluviumCephalomotor();
				preseason_suitably.profitableBemeal(turfy_synaxarion);
			}
		}

		public static class AlluviumCephalomotor {
			public void profitableBemeal(Object[] kindheartedly_syngraph) {
				SphenotemporalKaryokinetic overexpectantly_summist = new SphenotemporalKaryokinetic();
				overexpectantly_summist
						.unstartingImparalleled(kindheartedly_syngraph);
			}
		}

		public static class SphenotemporalKaryokinetic {
			public void unstartingImparalleled(Object[] learnedness_indefectibly) {
				AlbinUnvetoed flayer_moneysaving = new AlbinUnvetoed();
				flayer_moneysaving.afterworkLow(learnedness_indefectibly);
			}
		}

		public static class AlbinUnvetoed {
			public void afterworkLow(Object[] mnemotechnic_joltiness) {
				CopywiseHoller photosynthesize_nutriment = new CopywiseHoller();
				photosynthesize_nutriment
						.execrationTwankle(mnemotechnic_joltiness);
			}
		}

		public static class CopywiseHoller {
			public void execrationTwankle(Object[] smallhearted_duodecimality) {
				DisagreerSquintly kitchenman_chadacryst = new DisagreerSquintly();
				kitchenman_chadacryst
						.cephalotaceaeStoriation(smallhearted_duodecimality);
			}
		}

		public static class DisagreerSquintly {
			public void cephalotaceaeStoriation(Object[] drokpa_babylonish) {
				BurelCaddie subcreek_klom = new BurelCaddie();
				subcreek_klom.ptyalagogueCacajao(drokpa_babylonish);
			}
		}

		public static class BurelCaddie {
			public void ptyalagogueCacajao(Object[] vila_neologism) {
				PygmoidCreatedness tortureproof_sure = new PygmoidCreatedness();
				tortureproof_sure.regretterPrunetol(vila_neologism);
			}
		}

		public static class PygmoidCreatedness {
			public void regretterPrunetol(Object[] trophoblastic_oscitate) {
				HillsmanMicrosomia esthesiometric_uncomplex = new HillsmanMicrosomia();
				esthesiometric_uncomplex
						.sulfapyrazineErotopath(trophoblastic_oscitate);
			}
		}

		public static class HillsmanMicrosomia {
			public void sulfapyrazineErotopath(Object[] ortolan_screwstock) {
				ChoreographicAnchoritish sporangidium_expressively = new ChoreographicAnchoritish();
				sporangidium_expressively.conductorSkiapod(ortolan_screwstock);
			}
		}

		public static class ChoreographicAnchoritish {
			public void conductorSkiapod(Object[] blackstrap_widower) {
				AegrotantMelaleuca aclinic_buttonball = new AegrotantMelaleuca();
				aclinic_buttonball.kisraBulbocapnin(blackstrap_widower);
			}
		}

		public static class AegrotantMelaleuca {
			public void kisraBulbocapnin(Object[] housemother_periostea) {
				HusoNonclastic snailish_monosymmetric = new HusoNonclastic();
				snailish_monosymmetric
						.circumrenalSuasory(housemother_periostea);
			}
		}

		public static class HusoNonclastic {
			public void circumrenalSuasory(Object[] medusiferous_lyricism) {
				BlushwortJerseyed bolthole_acredula = new BlushwortJerseyed();
				bolthole_acredula.unopeningRustless(medusiferous_lyricism);
			}
		}

		public static class BlushwortJerseyed {
			public void unopeningRustless(Object[] antikinase_acystia) {
				TangantanganNonspecie unplat_aparai = new TangantanganNonspecie();
				unplat_aparai.bailorAnarchize(antikinase_acystia);
			}
		}

		public static class TangantanganNonspecie {
			public void bailorAnarchize(Object[] rattlebones_crowstepped) {
				UlerythemaIntrospectional unwilting_ophionid = new UlerythemaIntrospectional();
				unwilting_ophionid
						.rabelaisianismNeomenian(rattlebones_crowstepped);
			}
		}

		public static class UlerythemaIntrospectional {
			public void rabelaisianismNeomenian(Object[] elaeodochon_strass) {
				ParacholiaRaiser tunicle_alymphia = new ParacholiaRaiser();
				tunicle_alymphia.semimemberTigerfoot(elaeodochon_strass);
			}
		}

		public static class ParacholiaRaiser {
			public void semimemberTigerfoot(Object[] hydraulicon_unexpressive) {
				UnshowyVulnerable receivables_hiramite = new UnshowyVulnerable();
				receivables_hiramite
						.polyplacophoraFuniculate(hydraulicon_unexpressive);
			}
		}

		public static class UnshowyVulnerable {
			public void polyplacophoraFuniculate(
					Object[] palaeognathic_individualist) {
				ShillCasuistical northerly_glottalize = new ShillCasuistical();
				northerly_glottalize.salonFeverfew(palaeognathic_individualist);
			}
		}

		public static class ShillCasuistical {
			public void salonFeverfew(Object[] sunfishery_sulfindigotate) {
				RealisticizeApocrypha normality_ermani = new RealisticizeApocrypha();
				normality_ermani
						.oscillometricCorpuscle(sunfishery_sulfindigotate);
			}
		}

		public static class RealisticizeApocrypha {
			public void oscillometricCorpuscle(Object[] japanicize_unsteck) {
				HardheadedlyHoydenism bald_unsubduedly = new HardheadedlyHoydenism();
				bald_unsubduedly.intermeddlinglyBrainer(japanicize_unsteck);
			}
		}

		public static class HardheadedlyHoydenism {
			public void intermeddlinglyBrainer(Object[] achango_freckling) {
				PronelyFeif vinousness_mycelium = new PronelyFeif();
				vinousness_mycelium.neuralistRoundhouse(achango_freckling);
			}
		}

		public static class PronelyFeif {
			public void neuralistRoundhouse(Object[] spalding_norgine) {
				HymenophyllumTarkhan emptier_hydrosalpinx = new HymenophyllumTarkhan();
				emptier_hydrosalpinx.cymbalineHiddenite(spalding_norgine);
			}
		}

		public static class HymenophyllumTarkhan {
			public void cymbalineHiddenite(Object[] gastrocnemius_dextrin) {
				OotocoideanScalding unmodish_favonian = new OotocoideanScalding();
				unmodish_favonian.moodOrgia(gastrocnemius_dextrin);
			}
		}

		public static class OotocoideanScalding {
			public void moodOrgia(Object[] drumbledore_capucine) {
				AnacampticHouseboating ave_miro = new AnacampticHouseboating();
				ave_miro.roomedQuadruplicity(drumbledore_capucine);
			}
		}

		public static class AnacampticHouseboating {
			public void roomedQuadruplicity(Object[] smashery_lameter) {
				TransnatationFilaceous metaluminate_kapp = new TransnatationFilaceous();
				metaluminate_kapp.paleontologicRemiped(smashery_lameter);
			}
		}

		public static class TransnatationFilaceous {
			public void paleontologicRemiped(Object[] ekronite_unyeaned) {
				BanalQuasimodo equivocalness_plasmodia = new BanalQuasimodo();
				equivocalness_plasmodia
						.antiketogenicHomerist(ekronite_unyeaned);
			}
		}

		public static class BanalQuasimodo {
			public void antiketogenicHomerist(Object[] prefiguration_hidated) {
				UnpretentiouslyDipartite cribrate_brisque = new UnpretentiouslyDipartite();
				cribrate_brisque.quadratureBolo(prefiguration_hidated);
			}
		}

		public static class UnpretentiouslyDipartite {
			public void quadratureBolo(Object[] underserve_whaur) {
				TheoricallySrivatsan treator_crossopterygii = new TheoricallySrivatsan();
				treator_crossopterygii.antipatriarchUteralgia(underserve_whaur);
			}
		}

		public static class TheoricallySrivatsan {
			public void antipatriarchUteralgia(Object[] apophyllous_limacel) {
				StephanieRother gaullist_trophotropic = new StephanieRother();
				gaullist_trophotropic.stagecoachingBasion(apophyllous_limacel);
			}
		}

		public static class StephanieRother {
			public void stagecoachingBasion(Object[] trackwork_special) {
				PorphyriticSupraglacial trishna_rhinology = new PorphyriticSupraglacial();
				trishna_rhinology.siphonariidaeCeremonialist(trackwork_special);
			}
		}

		public static class PorphyriticSupraglacial {
			public void siphonariidaeCeremonialist(Object[] triradiated_jeames) {
				TellachPaular usucaption_equipotential = new TellachPaular();
				usucaption_equipotential.tahkhanaStigmal(triradiated_jeames);
			}
		}

		public static class TellachPaular {
			public void tahkhanaStigmal(Object[] retractile_phoronomia) {
				TheroidAnticarious proofread_emissaryship = new TheroidAnticarious();
				proofread_emissaryship.quadrableDoatish(retractile_phoronomia);
			}
		}

		public static class TheroidAnticarious {
			public void quadrableDoatish(Object[] divaricately_encephalometer) {
				UnapostolicLacis rhesus_vale = new UnapostolicLacis();
				rhesus_vale.subdivideRevoluted(divaricately_encephalometer);
			}
		}

		public static class UnapostolicLacis {
			public void subdivideRevoluted(Object[] expeditionist_banxring) {
				GnathalDecantherous poophytic_trojan = new GnathalDecantherous();
				poophytic_trojan.endotrophicCrankous(expeditionist_banxring);
			}
		}

		public static class GnathalDecantherous {
			public void endotrophicCrankous(Object[] noctivagant_coabsume) {
				BedralWarlikely baptistic_hedge = new BedralWarlikely();
				baptistic_hedge.federacyWander(noctivagant_coabsume);
			}
		}

		public static class BedralWarlikely {
			public void federacyWander(Object[] bepewed_celerity) {
				BeglerbeyDownright noninsect_unreeled = new BeglerbeyDownright();
				noninsect_unreeled.becollierCataclysmist(bepewed_celerity);
			}
		}

		public static class BeglerbeyDownright {
			public void becollierCataclysmist(Object[] ungirded_hylozoism) {
				SyngnathidQuadi underhangman_doctrinality = new SyngnathidQuadi();
				underhangman_doctrinality
						.pharyngotherapyCeratomania(ungirded_hylozoism);
			}
		}

		public static class SyngnathidQuadi {
			public void pharyngotherapyCeratomania(Object[] subdeltaic_athirst) {
				OptionalizeSupereminence interlinguistic_tarsonemus = new OptionalizeSupereminence();
				interlinguistic_tarsonemus
						.hydriotaphiaFibroadenia(subdeltaic_athirst);
			}
		}

		public static class OptionalizeSupereminence {
			public void hydriotaphiaFibroadenia(Object[] nirvana_indeliberation) {
				PicrylMononuclear orational_comtism = new PicrylMononuclear();
				orational_comtism.rectostenosisLogical(nirvana_indeliberation);
			}
		}

		public static class PicrylMononuclear {
			public void rectostenosisLogical(Object[] diuresis_florula) {
				EternizeLuger skewings_protopodial = new EternizeLuger();
				skewings_protopodial.honorariaChondrofetal(diuresis_florula);
			}
		}

		public static class EternizeLuger {
			public void honorariaChondrofetal(Object[] germanesque_tinkling) {
				DiscohexasterMeeks berascal_truncatellidae = new DiscohexasterMeeks();
				berascal_truncatellidae
						.botargoZygnemataceae(germanesque_tinkling);
			}
		}

		public static class DiscohexasterMeeks {
			public void botargoZygnemataceae(Object[] bottlemaking_ramular) {
				OmphalomesaraicEpigraphic nayarit_customable = new OmphalomesaraicEpigraphic();
				nayarit_customable.bagaDispersible(bottlemaking_ramular);
			}
		}

		public static class OmphalomesaraicEpigraphic {
			public void bagaDispersible(Object[] zyrian_bombination) {
				RearerAntiphysical brideweed_albuminoidal = new RearerAntiphysical();
				brideweed_albuminoidal.championizeVenutian(zyrian_bombination);
			}
		}

		public static class RearerAntiphysical {
			public void championizeVenutian(Object[] interglyph_quinnipiac) {
				Tracer.tracepointWeaknessStart("CWE459", "A",
						"Incomplete Cleanup");
				InputStream stonesoup_randomData = null;
				boolean stonesoup_validInput = true;
				Tracer.tracepointVariableInt(
						"stonesoup_intValue",
						((Integer) interglyph_quinnipiac[exclusion_maneuverability]));
				byte[] stonesoup_randomChars = null;
				try {
					SmallBlockStore.bacteriostatHiram
							.println("Gernerating data");
					stonesoup_randomData = new FileInputStream("/dev/urandom");
					int stonesoup_arraySize = 50000;
					stonesoup_randomChars = new byte[stonesoup_arraySize];
					stonesoup_randomData.read(stonesoup_randomChars, 0,
							stonesoup_arraySize);
				} catch (FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					SmallBlockStore.bacteriostatHiram
							.println("Error: /dev/urandom not found");
					stonesoup_validInput = false;
				} catch (IOException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					SmallBlockStore.bacteriostatHiram
							.println("Error: IO Exception reading /dev/urandom");
					stonesoup_validInput = false;
				} finally {
					try {
						stonesoup_randomData.close();
					} catch (IOException e) {
						SmallBlockStore.bacteriostatHiram
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
						SmallBlockStore.bacteriostatHiram
								.println("Saving data");
						for (stonesoup_i = 0; stonesoup_i < ((Integer) interglyph_quinnipiac[exclusion_maneuverability]); stonesoup_i++) {
							stonesoup_filePaths[stonesoup_i
									% stonesoup_numFilePaths] = File
									.createTempFile("stonesoup_data_459J_",
											null, new File("/tmp"));
							File stonesoup_file = stonesoup_filePaths[stonesoup_i
									% stonesoup_numFilePaths];
							stonesoup_outputStream = new FileOutputStream(
									stonesoup_file);
							if (!stonesoup_file.exists()) {
								stonesoup_file.createNewFile();
							}
							stonesoup_outputStream.write(stonesoup_randomChars);
							stonesoup_outputStream.close();
							stonesoup_outputStream = null;
						}
						Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
					} catch (FileNotFoundException e) {
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						SmallBlockStore.bacteriostatHiram
								.println("Error: tmp file  not found");
					} catch (IOException e) {
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						SmallBlockStore.bacteriostatHiram
								.println("Error: IO Exception writing tmp file");
					} finally {
						if (stonesoup_outputStream != null) {
							try {
								stonesoup_outputStream.close();
							} catch (IOException e) {
								SmallBlockStore.bacteriostatHiram
										.println("Error: could not delete output stream");
							}
						}
						Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
						Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
						for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
							if (stonesoup_filePaths[stonesoup_i] != null) {
								stonesoup_filePaths[stonesoup_i].delete();
							}
						}
						Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
						Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
						Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					}
				}
				Tracer.tracepointWeaknessEnd();
			}
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
