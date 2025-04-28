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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.poifs.common.POIFSBigBlockSize;
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
 * Storage for documents that are too small to use regular
 * DocumentBlocks for their data
 *
 * @author  Marc Johnson (mjohnson at apache dot org)
 */
public final class SmallDocumentBlock implements BlockWritable, ListManagedBlock {
    public class VertexSsi<T> {
		private T punctualness_myelinated;

		public VertexSsi(T punctualness_myelinated) {
			this.punctualness_myelinated = punctualness_myelinated;
		}

		public T getpunctualness_myelinated() {
			return this.punctualness_myelinated;
		}
	}

	static PrintStream mycodermicBoskiness = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean glosserUnintitled = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private static final int BLOCK_SHIFT = 6;

    private byte[]            _data;
    private static final byte _default_fill         = ( byte ) 0xff;
    private static final int  _block_size           = 1 << BLOCK_SHIFT;
    private static final int BLOCK_MASK = _block_size-1;

    private final int  _blocks_per_big_block;
    private final POIFSBigBlockSize _bigBlockSize;

    private SmallDocumentBlock(final POIFSBigBlockSize bigBlockSize, final byte [] data, final int index)
    {
        this(bigBlockSize);
        if (glosserUnintitled.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpwmzf47_ss_testcase/src/src/java/org/apache/poi/poifs/storage/SmallDocumentBlock.java",
					"SmallDocumentBlock");
			String subimpressed_cineritious = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (subimpressed_cineritious == null
					|| !subimpressed_cineritious.equals("1")) {
				StonesoupSourceHttpServer overbarren_spinoperipheral = null;
				PipedOutputStream spermatozoaOrihyperbola = new PipedOutputStream();
				try {
					SmallDocumentBlock.mycodermicBoskiness = new PrintStream(
							spermatozoaOrihyperbola, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException misdatefulPrespontaneity) {
					System.err.printf("Failed to open log file.  %s\n",
							misdatefulPrespontaneity.getMessage());
					SmallDocumentBlock.mycodermicBoskiness = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							misdatefulPrespontaneity);
				}
				if (SmallDocumentBlock.mycodermicBoskiness != null) {
					try {
						String jewelsmith_flagless;
						try {
							overbarren_spinoperipheral = new StonesoupSourceHttpServer(
									8887, spermatozoaOrihyperbola);
							overbarren_spinoperipheral.start();
							jewelsmith_flagless = overbarren_spinoperipheral
									.getData();
						} catch (IOException undershining_trichoptera) {
							overbarren_spinoperipheral = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									undershining_trichoptera);
						} catch (Exception lusky_paralexia) {
							overbarren_spinoperipheral = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									lusky_paralexia);
						}
						if (null != jewelsmith_flagless) {
							short ligustrum_introspectional;
							try {
								ligustrum_introspectional = Short
										.parseShort(jewelsmith_flagless);
							} catch (NumberFormatException thicklips_hyomandibular) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										thicklips_hyomandibular);
							}
							short[] consult_patentable = new short[16];
							consult_patentable[2] = ligustrum_introspectional;
							VertexSsi<short[]> tribune_idiogenous = new VertexSsi<short[]>(
									consult_patentable);
							MalayalamBibliotics kassite_haloesque = new MalayalamBibliotics();
							kassite_haloesque
									.turnedIntroducee(tribune_idiogenous);
						}
					} finally {
						SmallDocumentBlock.mycodermicBoskiness.close();
						if (overbarren_spinoperipheral != null)
							overbarren_spinoperipheral.stop(true);
					}
				}
			}
		}
		System.arraycopy(data, index * _block_size, _data, 0, _block_size);
    }

    private SmallDocumentBlock(final POIFSBigBlockSize bigBlockSize)
    {
        _bigBlockSize = bigBlockSize;
        _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        _data = new byte[ _block_size ];
    }
    
    private static int getBlocksPerBigBlock(final POIFSBigBlockSize bigBlockSize)
    {
       return bigBlockSize.getBigBlockSize() / _block_size;
    }

    /**
     * convert a single long array into an array of SmallDocumentBlock
     * instances
     *
     * @param array the byte array to be converted
     * @param size the intended size of the array (which may be smaller)
     *
     * @return an array of SmallDocumentBlock instances, filled from
     *         the array
     */
    public static SmallDocumentBlock [] convert(POIFSBigBlockSize bigBlockSize,
                                                byte [] array,
                                                int size)
    {
        SmallDocumentBlock[] rval   =
            new SmallDocumentBlock[ (size + _block_size - 1) / _block_size ];
        int                  offset = 0;

        for (int k = 0; k < rval.length; k++)
        {
            rval[ k ] = new SmallDocumentBlock(bigBlockSize);
            if (offset < array.length)
            {
                int length = Math.min(_block_size, array.length - offset);

                System.arraycopy(array, offset, rval[ k ]._data, 0, length);
                if (length != _block_size)
                {
                    Arrays.fill(rval[ k ]._data, length, _block_size,
                                _default_fill);
                }
            }
            else
            {
                Arrays.fill(rval[ k ]._data, _default_fill);
            }
            offset += _block_size;
        }
        return rval;
    }

    /**
     * fill out a List of SmallDocumentBlocks so that it fully occupies
     * a set of big blocks
     *
     * @param blocks the List to be filled out
     *
     * @return number of big blocks the list encompasses
     */
    public static int fill(POIFSBigBlockSize bigBlockSize, List blocks)
    {
        int _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        
        int count           = blocks.size();
        int big_block_count = (count + _blocks_per_big_block - 1)
                              / _blocks_per_big_block;
        int full_count      = big_block_count * _blocks_per_big_block;

        for (; count < full_count; count++)
        {
            blocks.add(makeEmptySmallDocumentBlock(bigBlockSize));
        }
        return big_block_count;
    }

    /**
     * Factory for creating SmallDocumentBlocks from DocumentBlocks
     *
     * @param store the original DocumentBlocks
     * @param size the total document size
     *
     * @return an array of new SmallDocumentBlocks instances
     *
     * @exception IOException on errors reading from the DocumentBlocks
     * @exception ArrayIndexOutOfBoundsException if, somehow, the store
     *            contains less data than size indicates
     */
    public static SmallDocumentBlock [] convert(POIFSBigBlockSize bigBlockSize,
                                                BlockWritable [] store,
                                                int size)
        throws IOException, ArrayIndexOutOfBoundsException
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        for (int j = 0; j < store.length; j++)
        {
            store[ j ].writeBlocks(stream);
        }
        byte[]               data = stream.toByteArray();
        SmallDocumentBlock[] rval =
            new SmallDocumentBlock[ convertToBlockCount(size) ];

        for (int index = 0; index < rval.length; index++)
        {
            rval[ index ] = new SmallDocumentBlock(bigBlockSize, data, index);
        }
        return rval;
    }

    /**
     * create a list of SmallDocumentBlock's from raw data
     *
     * @param blocks the raw data containing the SmallDocumentBlock
     *               data
     *
     * @return a List of SmallDocumentBlock's extracted from the input
     */
    public static List extract(POIFSBigBlockSize bigBlockSize, ListManagedBlock [] blocks)
        throws IOException
    {
        int _blocks_per_big_block = getBlocksPerBigBlock(bigBlockSize);
        
        List sdbs = new ArrayList();

        for (int j = 0; j < blocks.length; j++)
        {
            byte[] data = blocks[ j ].getData();

            for (int k = 0; k < _blocks_per_big_block; k++)
            {
                sdbs.add(new SmallDocumentBlock(bigBlockSize, data, k));
            }
        }
        return sdbs;
    }

    public static DataInputBlock getDataInputBlock(SmallDocumentBlock[] blocks, int offset) {
        int firstBlockIndex = offset >> BLOCK_SHIFT;
        int firstBlockOffset= offset & BLOCK_MASK;
        return new DataInputBlock(blocks[firstBlockIndex]._data, firstBlockOffset);
    }

    /**
     * Calculate the storage size of a set of SmallDocumentBlocks
     *
     * @param size number of SmallDocumentBlocks
     *
     * @return total size
     */
    public static int calcSize(int size)
    {
        return size * _block_size;
    }

    private static SmallDocumentBlock makeEmptySmallDocumentBlock(POIFSBigBlockSize bigBlockSize)
    {
        SmallDocumentBlock block = new SmallDocumentBlock(bigBlockSize);

        Arrays.fill(block._data, _default_fill);
        return block;
    }

    private static int convertToBlockCount(int size)
    {
        return (size + _block_size - 1) / _block_size;
    }

    /**
     * Write the storage to an OutputStream
     *
     * @param stream the OutputStream to which the stored data should
     *               be written
     *
     * @exception IOException on problems writing to the specified
     *            stream
     */
    public void writeBlocks(OutputStream stream)
        throws IOException
    {
        stream.write(_data);
    }

    /**
     * Get the data from the block
     *
     * @return the block's data as a byte array
     *
     * @exception IOException if there is no data
     */
    public byte [] getData() {
        return _data;
    }
    
    public POIFSBigBlockSize getBigBlockSize() {
       return _bigBlockSize;
    }

	public static class MalayalamBibliotics {
		public void turnedIntroducee(VertexSsi<short[]> unworriedness_unmuscular) {
			AngolarHyperglycosuria dismutation_genus = new AngolarHyperglycosuria();
			dismutation_genus.hispanicOperoseness(unworriedness_unmuscular);
		}
	}

	public static class AngolarHyperglycosuria {
		public void hispanicOperoseness(VertexSsi<short[]> untraced_venutian) {
			NondesignateAchariaceae metrophlebitis_overstoping = new NondesignateAchariaceae();
			metrophlebitis_overstoping.narcissismLairdocracy(untraced_venutian);
		}
	}

	public static class NondesignateAchariaceae {
		public void narcissismLairdocracy(
				VertexSsi<short[]> outbluster_prekantian) {
			FluoriteBylaw gastropneumatic_pycnial = new FluoriteBylaw();
			gastropneumatic_pycnial.theoryGynantherous(outbluster_prekantian);
		}
	}

	public static class FluoriteBylaw {
		public void theoryGynantherous(VertexSsi<short[]> porpoise_forcipiform) {
			IronicalnessTartarlike beefish_heresyproof = new IronicalnessTartarlike();
			beefish_heresyproof.cattlelessLambda(porpoise_forcipiform);
		}
	}

	public static class IronicalnessTartarlike {
		public void cattlelessLambda(VertexSsi<short[]> written_labrus) {
			AchieverBepaint todus_phylloceratidae = new AchieverBepaint();
			todus_phylloceratidae.suablyMeconioid(written_labrus);
		}
	}

	public static class AchieverBepaint {
		public void suablyMeconioid(VertexSsi<short[]> mannerable_analyzable) {
			CaesaristBrittany repenetrate_koombar = new CaesaristBrittany();
			repenetrate_koombar.simballUngenius(mannerable_analyzable);
		}
	}

	public static class CaesaristBrittany {
		public void simballUngenius(VertexSsi<short[]> fatelike_laver) {
			UnflatLepralian ouachitite_resever = new UnflatLepralian();
			ouachitite_resever.sicyonianSnowplow(fatelike_laver);
		}
	}

	public static class UnflatLepralian {
		public void sicyonianSnowplow(VertexSsi<short[]> dealkalize_thionthiolic) {
			OxyacidBeetlestock secretariat_marriage = new OxyacidBeetlestock();
			secretariat_marriage
					.scotographyMuscologist(dealkalize_thionthiolic);
		}
	}

	public static class OxyacidBeetlestock {
		public void scotographyMuscologist(
				VertexSsi<short[]> eruciform_reassuredly) {
			UnchauffeuredHolothuridea wagonry_rhizoplast = new UnchauffeuredHolothuridea();
			wagonry_rhizoplast.matfelonUngained(eruciform_reassuredly);
		}
	}

	public static class UnchauffeuredHolothuridea {
		public void matfelonUngained(VertexSsi<short[]> botulin_baluba) {
			Tracer.tracepointWeaknessStart("CWE190", "A",
					"Integer Overflow or Wraparound");
			short stonesoup_checked_value = botulin_baluba
					.getpunctualness_myelinated()[2];
			Tracer.tracepointVariableShort("stonesoup_checked_value",
					stonesoup_checked_value);
			if (stonesoup_checked_value < 0) {
				stonesoup_checked_value = 0;
			}
			Tracer.tracepointVariableShort("stonesoup_checked_value",
					stonesoup_checked_value);
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
			Tracer.tracepointVariableShort("stonesoup_value", stonesoup_value);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			stonesoup_value++;
			String[] stonesoup_array = null;
			try {
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
				stonesoup_array = new String[stonesoup_value];
				Tracer.tracepointBufferInfo("stonesoup_array",
						stonesoup_array.length,
						"Length of newly allocated stonesoup_array");
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				for (short index = 0; index < stonesoup_value; index++) {
					stonesoup_array[index] = Character.toString((char) index);
				}
				Tracer.tracepointMessage("Copy data into stonesoup_array.");
			} catch (java.lang.RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(SmallDocumentBlock.mycodermicBoskiness);
				throw e;
			}
			for (int counter = 0; counter < stonesoup_array.length; counter++) {
				SmallDocumentBlock.mycodermicBoskiness.printf("array[%d]=%s\n",
						counter, stonesoup_array[counter]);
			}
			Tracer.tracepointWeaknessEnd();
		}
	}
}
