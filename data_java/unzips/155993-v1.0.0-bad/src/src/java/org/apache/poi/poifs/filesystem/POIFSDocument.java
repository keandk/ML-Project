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
import java.util.Scanner;
import java.util.NoSuchElementException;

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
		public class ThurificatiFavosely {
			private int[] opisthotonic_preener;

			public ThurificatiFavosely(int[] opisthotonic_preener) {
				this.opisthotonic_preener = opisthotonic_preener;
			}

			public int[] getopisthotonic_preener() {
				return this.opisthotonic_preener;
			}
		}

		static PrintStream rhizocaulusStrobilization = null;
		private static final java.util.concurrent.atomic.AtomicBoolean fairylandWoolpress = new java.util.concurrent.atomic.AtomicBoolean(
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
		   if (fairylandWoolpress.compareAndSet(false, true)) {
				Tracer.tracepointLocation(
						"/tmp/tmppSCxfH_ss_testcase/src/src/java/org/apache/poi/poifs/filesystem/POIFSDocument.java",
						"SmallBlockStore");
				File unrepentedFrogged = new File(
						"/opt/stonesoup/workspace/testData/logfile.txt");
				if (!unrepentedFrogged.getParentFile().exists()
						&& !unrepentedFrogged.getParentFile().mkdirs()) {
					System.err
							.println("Failed to create parent log directory!");
					throw new RuntimeException(
							"STONESOUP: Failed to create log directory.");
				} else {
					try {
						SmallBlockStore.rhizocaulusStrobilization = new PrintStream(
								new FileOutputStream(unrepentedFrogged, false),
								true, "ISO-8859-1");
					} catch (UnsupportedEncodingException mesneArchidiaconal) {
						System.err.printf("Failed to open log file.  %s\n",
								mesneArchidiaconal.getMessage());
						SmallBlockStore.rhizocaulusStrobilization = null;
						throw new RuntimeException(
								"STONESOUP: Failed to open log file.",
								mesneArchidiaconal);
					} catch (FileNotFoundException orectiveChallengee) {
						System.err.printf("Failed to open log file.  %s\n",
								orectiveChallengee.getMessage());
						SmallBlockStore.rhizocaulusStrobilization = null;
						throw new RuntimeException(
								"STONESOUP: Failed to open log file.",
								orectiveChallengee);
					}
					if (SmallBlockStore.rhizocaulusStrobilization != null) {
						try {
							String horizonless_katacrotic = System
									.getenv("STONESOUP_DISABLE_WEAKNESS");
							if (horizonless_katacrotic == null
									|| !horizonless_katacrotic.equals("1")) {
								String unvitiated_unbowed = System
										.getenv("COLLETOTRICHUM_BOLL");
								if (null != unvitiated_unbowed) {
									File lull_vertebre = new File(
											unvitiated_unbowed);
									if (lull_vertebre.exists()
											&& !lull_vertebre.isDirectory()) {
										try {
											String theriotrophical_intersex;
											Scanner tenderheartedly_redrape = new Scanner(
													lull_vertebre, "UTF-8")
													.useDelimiter("\\A");
											if (tenderheartedly_redrape
													.hasNext())
												theriotrophical_intersex = tenderheartedly_redrape
														.next();
											else
												theriotrophical_intersex = "";
											if (null != theriotrophical_intersex) {
												int multifidus_mislest;
												try {
													multifidus_mislest = Integer
															.parseInt(theriotrophical_intersex);
												} catch (NumberFormatException twinkler_curstful) {
													throw new RuntimeException(
															"STONESOUP: Failed to convert source taint.",
															twinkler_curstful);
												}
												int[] unnoosed_illiberalize = new int[15];
												unnoosed_illiberalize[1] = multifidus_mislest;
												ThurificatiFavosely fruiterer_landolphia = new ThurificatiFavosely(
														unnoosed_illiberalize);
												DeaminationPlanetology outbearing_numerically = new DeaminationPlanetology();
												outbearing_numerically
														.allegoristerPensility(fruiterer_landolphia);
											}
										} catch (FileNotFoundException collectiveGalidictis) {
											throw new RuntimeException(
													"STONESOUP: Could not open file",
													collectiveGalidictis);
										}
									}
								}
							}
						} finally {
							SmallBlockStore.rhizocaulusStrobilization.close();
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

		public static class DeaminationPlanetology {
			public void allegoristerPensility(
					ThurificatiFavosely beelzebub_quadrable) {
				AtticomastoidUpdrink peripatize_reconfound = new AtticomastoidUpdrink();
				peripatize_reconfound
						.presentialnessUncoronated(beelzebub_quadrable);
			}
		}

		public static class AtticomastoidUpdrink {
			public void presentialnessUncoronated(
					ThurificatiFavosely graphite_sheeting) {
				FimbleSheet fosterland_knudsen = new FimbleSheet();
				fosterland_knudsen.rhineMerosystematic(graphite_sheeting);
			}
		}

		public static class FimbleSheet {
			public void rhineMerosystematic(
					ThurificatiFavosely knickerbocker_butyrin) {
				CommonnessUnprickled unsacrilegious_dianisidine = new CommonnessUnprickled();
				unsacrilegious_dianisidine
						.overveilMonaxonial(knickerbocker_butyrin);
			}
		}

		public static class CommonnessUnprickled {
			public void overveilMonaxonial(
					ThurificatiFavosely overnight_pariasaurus) {
				UnthrongedCartonnage undrubbed_inaugural = new UnthrongedCartonnage();
				undrubbed_inaugural
						.headmarkAlbuminaturia(overnight_pariasaurus);
			}
		}

		public static class UnthrongedCartonnage {
			public void headmarkAlbuminaturia(
					ThurificatiFavosely troutbird_crocodile) {
				AnterointeriorDisinteresting satyashodak_goosishness = new AnterointeriorDisinteresting();
				satyashodak_goosishness.ceylaniteBogum(troutbird_crocodile);
			}
		}

		public static class AnterointeriorDisinteresting {
			public void ceylaniteBogum(ThurificatiFavosely washed_princeps) {
				StomodaeaPatellaroid windable_disbudder = new StomodaeaPatellaroid();
				windable_disbudder.cheloneBasale(washed_princeps);
			}
		}

		public static class StomodaeaPatellaroid {
			public void cheloneBasale(ThurificatiFavosely overpotential_angolar) {
				DefrayableUnindifferency mediocre_isokeraunic = new DefrayableUnindifferency();
				mediocre_isokeraunic.rainprooferIonic(overpotential_angolar);
			}
		}

		public static class DefrayableUnindifferency {
			public void rainprooferIonic(ThurificatiFavosely isocarpic_acceptive) {
				DecastDeoccidentalize dihalo_decrepitly = new DecastDeoccidentalize();
				dihalo_decrepitly.schappeNecrophilism(isocarpic_acceptive);
			}
		}

		public static class DecastDeoccidentalize {
			public void schappeNecrophilism(
					ThurificatiFavosely tanacetin_inerrable) {
				MolluscumSplenoncus featherhead_rellyanite = new MolluscumSplenoncus();
				featherhead_rellyanite.uniteableFleaweed(tanacetin_inerrable);
			}
		}

		public static class MolluscumSplenoncus {
			public void uniteableFleaweed(
					ThurificatiFavosely cotranslator_ethography) {
				ApexedDecarbonator deadishness_overfamous = new ApexedDecarbonator();
				deadishness_overfamous
						.bivalenceHymnless(cotranslator_ethography);
			}
		}

		public static class ApexedDecarbonator {
			public void bivalenceHymnless(
					ThurificatiFavosely determinably_reluctant) {
				DrawtubeSnabble protectionism_fake = new DrawtubeSnabble();
				protectionism_fake.accumulativeChlamys(determinably_reluctant);
			}
		}

		public static class DrawtubeSnabble {
			public void accumulativeChlamys(
					ThurificatiFavosely sinoidal_admissory) {
				PseudoankylosisJugulares mechanomorphic_egalitarian = new PseudoankylosisJugulares();
				mechanomorphic_egalitarian
						.alcoholyticEnteric(sinoidal_admissory);
			}
		}

		public static class PseudoankylosisJugulares {
			public void alcoholyticEnteric(
					ThurificatiFavosely quadricapsular_tinselwork) {
				UnimbruedVisigoth governess_lipocardiac = new UnimbruedVisigoth();
				governess_lipocardiac
						.serrationPeopler(quadricapsular_tinselwork);
			}
		}

		public static class UnimbruedVisigoth {
			public void serrationPeopler(
					ThurificatiFavosely radicated_bicarbonate) {
				PolychromatizeSpinales emm_narcotist = new PolychromatizeSpinales();
				emm_narcotist.petrobrusianWeedling(radicated_bicarbonate);
			}
		}

		public static class PolychromatizeSpinales {
			public void petrobrusianWeedling(
					ThurificatiFavosely waverable_enunciation) {
				PyrometryTridermic warblelike_calaverite = new PyrometryTridermic();
				warblelike_calaverite
						.preaciditySphagnology(waverable_enunciation);
			}
		}

		public static class PyrometryTridermic {
			public void preaciditySphagnology(
					ThurificatiFavosely ravensara_holothoracic) {
				InartisticalityRomney lionheart_endosmosic = new InartisticalityRomney();
				lionheart_endosmosic.groomyHypnoidize(ravensara_holothoracic);
			}
		}

		public static class InartisticalityRomney {
			public void groomyHypnoidize(
					ThurificatiFavosely praepostorial_neglection) {
				BawcockCausse pulvinus_didromy = new BawcockCausse();
				pulvinus_didromy.dimittisViscacha(praepostorial_neglection);
			}
		}

		public static class BawcockCausse {
			public void dimittisViscacha(ThurificatiFavosely guaco_blepharedema) {
				VolumenometryForeshadow reingress_ogpu = new VolumenometryForeshadow();
				reingress_ogpu.hempenMetapectic(guaco_blepharedema);
			}
		}

		public static class VolumenometryForeshadow {
			public void hempenMetapectic(ThurificatiFavosely sirian_notopterid) {
				JammerYamel effloresce_microphone = new JammerYamel();
				effloresce_microphone.chinwoodAmapa(sirian_notopterid);
			}
		}

		public static class JammerYamel {
			public void chinwoodAmapa(ThurificatiFavosely spermatolytic_chane) {
				TilakaRamify unacidulated_unyearning = new TilakaRamify();
				unacidulated_unyearning.subclamatoresSerge(spermatolytic_chane);
			}
		}

		public static class TilakaRamify {
			public void subclamatoresSerge(ThurificatiFavosely harebell_whau) {
				PhonoliteDislocation reinette_broadtail = new PhonoliteDislocation();
				reinette_broadtail.uncrumplingSneesty(harebell_whau);
			}
		}

		public static class PhonoliteDislocation {
			public void uncrumplingSneesty(
					ThurificatiFavosely unvalidated_irrigant) {
				NonbetrayalPrediscount anthriscus_willie = new NonbetrayalPrediscount();
				anthriscus_willie.hexathlonOversheet(unvalidated_irrigant);
			}
		}

		public static class NonbetrayalPrediscount {
			public void hexathlonOversheet(
					ThurificatiFavosely revestry_pronephric) {
				EryngiumUnwish menaccanite_millenarianism = new EryngiumUnwish();
				menaccanite_millenarianism
						.shilhaHemopoietic(revestry_pronephric);
			}
		}

		public static class EryngiumUnwish {
			public void shilhaHemopoietic(
					ThurificatiFavosely sparassodonta_pussyfoot) {
				TarsalgiaTrophotropic pseudomorphosis_cherte = new TarsalgiaTrophotropic();
				pseudomorphosis_cherte.poleEmulate(sparassodonta_pussyfoot);
			}
		}

		public static class TarsalgiaTrophotropic {
			public void poleEmulate(ThurificatiFavosely tubeworks_pugnacity) {
				ReductasePolyphonism bairnteam_undreamt = new ReductasePolyphonism();
				bairnteam_undreamt.inalienabilityAntidotal(tubeworks_pugnacity);
			}
		}

		public static class ReductasePolyphonism {
			public void inalienabilityAntidotal(
					ThurificatiFavosely saltpan_ingiving) {
				DowinessHydriotaphia factionistism_deucedly = new DowinessHydriotaphia();
				factionistism_deucedly.bristlewortFoamy(saltpan_ingiving);
			}
		}

		public static class DowinessHydriotaphia {
			public void bristlewortFoamy(ThurificatiFavosely clomb_letterweight) {
				UncitizenlikeSecretum episclera_holothecal = new UncitizenlikeSecretum();
				episclera_holothecal.infuriatinglyCoppery(clomb_letterweight);
			}
		}

		public static class UncitizenlikeSecretum {
			public void infuriatinglyCoppery(ThurificatiFavosely bipont_blair) {
				CoadjutressRedivide unindigent_unrelevant = new CoadjutressRedivide();
				unindigent_unrelevant.unwishedImperforation(bipont_blair);
			}
		}

		public static class CoadjutressRedivide {
			public void unwishedImperforation(
					ThurificatiFavosely bloomsbury_overstaff) {
				OutjumpErythron hyracidae_peridiniales = new OutjumpErythron();
				hyracidae_peridiniales
						.maxillolabialDissensualize(bloomsbury_overstaff);
			}
		}

		public static class OutjumpErythron {
			public void maxillolabialDissensualize(
					ThurificatiFavosely manei_taskmistress) {
				OccipitallyCochliodus peduncular_helicinidae = new OccipitallyCochliodus();
				peduncular_helicinidae.undisrobedKend(manei_taskmistress);
			}
		}

		public static class OccipitallyCochliodus {
			public void undisrobedKend(ThurificatiFavosely perissodactyla_shrag) {
				MaghribCupping prytanis_formulizer = new MaghribCupping();
				prytanis_formulizer.insanitarinessJoyancy(perissodactyla_shrag);
			}
		}

		public static class MaghribCupping {
			public void insanitarinessJoyancy(
					ThurificatiFavosely adsorbent_interneuronic) {
				CasuariiformesAcrocephalous lat_frondescence = new CasuariiformesAcrocephalous();
				lat_frondescence
						.coexplosionLaboulbenia(adsorbent_interneuronic);
			}
		}

		public static class CasuariiformesAcrocephalous {
			public void coexplosionLaboulbenia(
					ThurificatiFavosely flareback_gandergoose) {
				DolichocephalicSeraglio soundingly_subsultorily = new DolichocephalicSeraglio();
				soundingly_subsultorily
						.unspeakingHeterorhachis(flareback_gandergoose);
			}
		}

		public static class DolichocephalicSeraglio {
			public void unspeakingHeterorhachis(
					ThurificatiFavosely rectilineal_interpage) {
				CaoutchoucSemifictional metabiology_limnanthemum = new CaoutchoucSemifictional();
				metabiology_limnanthemum
						.nonforestedHoplonemertean(rectilineal_interpage);
			}
		}

		public static class CaoutchoucSemifictional {
			public void nonforestedHoplonemertean(
					ThurificatiFavosely strafe_nonrevaluation) {
				GlossolaryngealEpithelium spuddy_debbie = new GlossolaryngealEpithelium();
				spuddy_debbie.smokelessPreserver(strafe_nonrevaluation);
			}
		}

		public static class GlossolaryngealEpithelium {
			public void smokelessPreserver(
					ThurificatiFavosely asphyxied_pyrophobia) {
				RetinoscopePhylactery unsystematic_rolliche = new RetinoscopePhylactery();
				unsystematic_rolliche
						.expedientistVibrissa(asphyxied_pyrophobia);
			}
		}

		public static class RetinoscopePhylactery {
			public void expedientistVibrissa(
					ThurificatiFavosely peakedness_thorax) {
				ReprescribeMajuscule ductless_caeciform = new ReprescribeMajuscule();
				ductless_caeciform.cavaliershipConjoin(peakedness_thorax);
			}
		}

		public static class ReprescribeMajuscule {
			public void cavaliershipConjoin(
					ThurificatiFavosely peristylar_vauntmure) {
				RubberneckRaiiform misconfident_pumpless = new RubberneckRaiiform();
				misconfident_pumpless
						.wrinkledGlyphography(peristylar_vauntmure);
			}
		}

		public static class RubberneckRaiiform {
			public void wrinkledGlyphography(
					ThurificatiFavosely crossbowman_acupuncturation) {
				PedionHyperthermal unbrooded_flawful = new PedionHyperthermal();
				unbrooded_flawful.nulliparaPig(crossbowman_acupuncturation);
			}
		}

		public static class PedionHyperthermal {
			public void nulliparaPig(ThurificatiFavosely communicable_peguan) {
				FlickertailHomewardly albright_unblended = new FlickertailHomewardly();
				albright_unblended.nanticokeTad(communicable_peguan);
			}
		}

		public static class FlickertailHomewardly {
			public void nanticokeTad(ThurificatiFavosely squarrose_siphonaptera) {
				InsolateCraterid autoirrigation_organotin = new InsolateCraterid();
				autoirrigation_organotin
						.dispatchfulSimultaneously(squarrose_siphonaptera);
			}
		}

		public static class InsolateCraterid {
			public void dispatchfulSimultaneously(
					ThurificatiFavosely overawe_discommodity) {
				DentiformTreatise homologic_cliftonia = new DentiformTreatise();
				homologic_cliftonia.unsociologicalSubnex(overawe_discommodity);
			}
		}

		public static class DentiformTreatise {
			public void unsociologicalSubnex(
					ThurificatiFavosely quadricostate_uteromania) {
				AlbrightGalilean recaption_semicostiferous = new AlbrightGalilean();
				recaption_semicostiferous
						.piazzaedMycetozoa(quadricostate_uteromania);
			}
		}

		public static class AlbrightGalilean {
			public void piazzaedMycetozoa(
					ThurificatiFavosely daleman_rhabdomyoma) {
				GuahivoTimist genecologic_culpa = new GuahivoTimist();
				genecologic_culpa.fluidramNonocculting(daleman_rhabdomyoma);
			}
		}

		public static class GuahivoTimist {
			public void fluidramNonocculting(
					ThurificatiFavosely photics_homoblasty) {
				UnderpeepDespiteful organum_pabulary = new UnderpeepDespiteful();
				organum_pabulary.unsymbolicallyAutoxeny(photics_homoblasty);
			}
		}

		public static class UnderpeepDespiteful {
			public void unsymbolicallyAutoxeny(
					ThurificatiFavosely ballerina_canonistic) {
				NonclinicalRibaldry zyrian_rebeautify = new NonclinicalRibaldry();
				zyrian_rebeautify.resinbushPhoenicite(ballerina_canonistic);
			}
		}

		public static class NonclinicalRibaldry {
			public void resinbushPhoenicite(ThurificatiFavosely provostal_aqua) {
				UnderpitchPseudocandid confessional_forjudge = new UnderpitchPseudocandid();
				confessional_forjudge.antichlorineUnaffable(provostal_aqua);
			}
		}

		public static class UnderpitchPseudocandid {
			public void antichlorineUnaffable(
					ThurificatiFavosely yajnopavita_bursattee) {
				UnconsultedSwosh unerected_diabolization = new UnconsultedSwosh();
				unerected_diabolization.isocracyPomology(yajnopavita_bursattee);
			}
		}

		public static class UnconsultedSwosh {
			public void isocracyPomology(
					ThurificatiFavosely scyphistoma_diplogangliate) {
				DentirosterApoaconitine yuit_occulting = new DentirosterApoaconitine();
				yuit_occulting
						.untutelarSynecdochism(scyphistoma_diplogangliate);
			}
		}

		public static class DentirosterApoaconitine {
			public void untutelarSynecdochism(
					ThurificatiFavosely steadyingly_phoronic) {
				Tracer.tracepointWeaknessStart("CWE774", "A",
						"Allocation of File Descriptors or Handles Without Limits or Throttling");
				FileOutputStream[] stonesoup_sources = new FileOutputStream[steadyingly_phoronic
						.getopisthotonic_preener()[1]];
				int stonesoup_active_files = 0;
				Tracer.tracepointBufferInfo("stonesoup_sources",
						stonesoup_sources.length, "Length of stonesoup_sources");
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (int stonesoup_counter = 0; stonesoup_counter < steadyingly_phoronic
						.getopisthotonic_preener()[1]; stonesoup_counter++) {
					try {
						stonesoup_sources[stonesoup_counter] = new FileOutputStream(
								String.format(
										"/opt/stonesoup/workspace/testData/test%10d",
										stonesoup_counter));
					} catch (FileNotFoundException e) {
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						SmallBlockStore.rhizocaulusStrobilization
								.println("Failed to create new file.");
						e.printStackTrace(SmallBlockStore.rhizocaulusStrobilization);
						throw new RuntimeException(e);
					}
					stonesoup_active_files++;
					SmallBlockStore.rhizocaulusStrobilization
							.println(stonesoup_counter);
				}
				Tracer.tracepointVariableInt("stonesoup_active_files",
						stonesoup_active_files);
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
					try {
						if (stonesoup_sources[stonesoup_counter] != null) {
							stonesoup_sources[stonesoup_counter].close();
						}
					} catch (IOException e) {
						SmallBlockStore.rhizocaulusStrobilization
								.println("Failed to close file.");
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
