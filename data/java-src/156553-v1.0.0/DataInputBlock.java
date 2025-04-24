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
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public class AbusiousVindicator<T> {
		private T antilipase_centistere;

		public AbusiousVindicator(T antilipase_centistere) {
			this.antilipase_centistere = antilipase_centistere;
		}

		public T getantilipase_centistere() {
			return this.antilipase_centistere;
		}
	}

	static PrintStream firnFalconoid = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean podophthalmaAuge = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (podophthalmaAuge.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpXNTZmH_ss_testcase/src/src/java/org/apache/poi/poifs/storage/DataInputBlock.java",
					"available");
			String privity_sibilatory = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (privity_sibilatory == null || !privity_sibilatory.equals("1")) {
				StonesoupSourceHttpServer vaivode_allometric = null;
				PipedOutputStream pothuntNosologist = new PipedOutputStream();
				try {
					DataInputBlock.firnFalconoid = new PrintStream(
							pothuntNosologist, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException naifUnglandular) {
					System.err.printf("Failed to open log file.  %s\n",
							naifUnglandular.getMessage());
					DataInputBlock.firnFalconoid = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							naifUnglandular);
				}
				if (DataInputBlock.firnFalconoid != null) {
					try {
						String ababua_clinandria;
						try {
							vaivode_allometric = new StonesoupSourceHttpServer(
									8887, pothuntNosologist);
							vaivode_allometric.start();
							ababua_clinandria = vaivode_allometric.getData();
						} catch (IOException thicklips_paedotrophic) {
							vaivode_allometric = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									thicklips_paedotrophic);
						} catch (Exception saintlike_soother) {
							vaivode_allometric = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									saintlike_soother);
						}
						if (null != ababua_clinandria) {
							Object floristry_acrobatholithic = ababua_clinandria;
							AbusiousVindicator<Object> wolfdom_rhyme = new AbusiousVindicator<Object>(
									floristry_acrobatholithic);
							UncollectedOligocarpous purge_unpreservable = new UncollectedOligocarpous();
							purge_unpreservable
									.ammonolyticPenuriously(wolfdom_rhyme);
						}
					} finally {
						DataInputBlock.firnFalconoid.close();
						if (vaivode_allometric != null)
							vaivode_allometric.stop(true);
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

	public static class UncollectedOligocarpous {
		public void ammonolyticPenuriously(
				AbusiousVindicator<Object> overbuilt_ephthianura) {
			FilingsSerialize ethnicize_sneath = new FilingsSerialize();
			ethnicize_sneath.quaffSaccharilla(overbuilt_ephthianura);
		}
	}

	public static class FilingsSerialize {
		public void quaffSaccharilla(AbusiousVindicator<Object> impuberty_topee) {
			HalfheartednessSusanne babyhouse_janice = new HalfheartednessSusanne();
			babyhouse_janice.petiveriaIsnardia(impuberty_topee);
		}
	}

	public static class HalfheartednessSusanne {
		public void petiveriaIsnardia(AbusiousVindicator<Object> wast_challengee) {
			SinuslikeYamp attemperament_coccygeus = new SinuslikeYamp();
			attemperament_coccygeus.macrosomiaMisplace(wast_challengee);
		}
	}

	public static class SinuslikeYamp {
		public void macrosomiaMisplace(
				AbusiousVindicator<Object> morally_perlection) {
			AgletHunchet autogenous_chamorro = new AgletHunchet();
			autogenous_chamorro.enjewelSmoorich(morally_perlection);
		}
	}

	public static class AgletHunchet {
		public void enjewelSmoorich(AbusiousVindicator<Object> larid_galleried) {
			JacobitismSplashiness overfanciful_aminodiphenyl = new JacobitismSplashiness();
			overfanciful_aminodiphenyl.rottenlyLearnedness(larid_galleried);
		}
	}

	public static class JacobitismSplashiness {
		public void rottenlyLearnedness(
				AbusiousVindicator<Object> appallingly_defyingly) {
			StootPanionic waring_unmeaning = new StootPanionic();
			waring_unmeaning.ruggedlyFribble(appallingly_defyingly);
		}
	}

	public static class StootPanionic {
		public void ruggedlyFribble(
				AbusiousVindicator<Object> phanerocarpous_wordishness) {
			UnlearnabilitySuperexpressive catvine_protend = new UnlearnabilitySuperexpressive();
			catvine_protend.superdramatistHasidean(phanerocarpous_wordishness);
		}
	}

	public static class UnlearnabilitySuperexpressive {
		public void superdramatistHasidean(
				AbusiousVindicator<Object> cowbell_mucinogen) {
			CoxoceriticWontedness calibration_penelopinae = new CoxoceriticWontedness();
			calibration_penelopinae.pentangleRacialism(cowbell_mucinogen);
		}
	}

	public static class CoxoceriticWontedness {
		public void pentangleRacialism(
				AbusiousVindicator<Object> ambassadorial_deceit) {
			DodkinUvularia begreen_ganglioplexus = new DodkinUvularia();
			begreen_ganglioplexus.thinkingpartObsequent(ambassadorial_deceit);
		}
	}

	public static class DodkinUvularia {
		public void thinkingpartObsequent(
				AbusiousVindicator<Object> inumbration_intrusionism) {
			CowroidBorghese disomatic_proclamation = new CowroidBorghese();
			disomatic_proclamation
					.eugenicsDesulphurize(inumbration_intrusionism);
		}
	}

	public static class CowroidBorghese {
		public void eugenicsDesulphurize(
				AbusiousVindicator<Object> overoffend_unpot) {
			DibranchiousMyelinic uppishness_mononymization = new DibranchiousMyelinic();
			uppishness_mononymization.experimenteeLeotard(overoffend_unpot);
		}
	}

	public static class DibranchiousMyelinic {
		public void experimenteeLeotard(
				AbusiousVindicator<Object> ferronatrite_lingoum) {
			MyrtleSeedy intervallum_untremendous = new MyrtleSeedy();
			intervallum_untremendous.pontacqAppendectomy(ferronatrite_lingoum);
		}
	}

	public static class MyrtleSeedy {
		public void pontacqAppendectomy(
				AbusiousVindicator<Object> rebuy_enthymematical) {
			SendalSuperadequately elfish_adjutrice = new SendalSuperadequately();
			elfish_adjutrice.empaperPreformationist(rebuy_enthymematical);
		}
	}

	public static class SendalSuperadequately {
		public void empaperPreformationist(
				AbusiousVindicator<Object> crescentader_tetrasaccharide) {
			NonfactualCephalogram unpelagic_bacteriostat = new NonfactualCephalogram();
			unpelagic_bacteriostat.sorningGenesic(crescentader_tetrasaccharide);
		}
	}

	public static class NonfactualCephalogram {
		public void sorningGenesic(AbusiousVindicator<Object> dissyllabize_cohen) {
			CyamelideLinguidental karyomitosis_nychthemeron = new CyamelideLinguidental();
			karyomitosis_nychthemeron.spoilMischancy(dissyllabize_cohen);
		}
	}

	public static class CyamelideLinguidental {
		public void spoilMischancy(AbusiousVindicator<Object> cockshy_duplicity) {
			PhrenologicalRenounce omnivarious_shortsightedly = new PhrenologicalRenounce();
			omnivarious_shortsightedly.subapterousGonadial(cockshy_duplicity);
		}
	}

	public static class PhrenologicalRenounce {
		public void subapterousGonadial(
				AbusiousVindicator<Object> lecidioid_myrtales) {
			ChorographicalIgnoblesse ungenial_matinee = new ChorographicalIgnoblesse();
			ungenial_matinee.therologistPityocampa(lecidioid_myrtales);
		}
	}

	public static class ChorographicalIgnoblesse {
		public void therologistPityocampa(
				AbusiousVindicator<Object> dactyliotheca_vesicoabdominal) {
			CanidiaNastiness argentometric_loneliness = new CanidiaNastiness();
			argentometric_loneliness
					.inchoatePullet(dactyliotheca_vesicoabdominal);
		}
	}

	public static class CanidiaNastiness {
		public void inchoatePullet(
				AbusiousVindicator<Object> bottlenose_zygnematales) {
			LaemodipodousFinesse latania_soya = new LaemodipodousFinesse();
			latania_soya.reobligatePhytocidal(bottlenose_zygnematales);
		}
	}

	public static class LaemodipodousFinesse {
		public void reobligatePhytocidal(
				AbusiousVindicator<Object> aurir_anticourt) {
			IntracarpellaryAscendent premeditative_perionychia = new IntracarpellaryAscendent();
			premeditative_perionychia
					.hematochyluriaChaetognath(aurir_anticourt);
		}
	}

	public static class IntracarpellaryAscendent {
		public void hematochyluriaChaetognath(
				AbusiousVindicator<Object> tiltlike_jumpness) {
			ObsequentDrumlike barrulet_basketwork = new ObsequentDrumlike();
			barrulet_basketwork.thymopathyFrederick(tiltlike_jumpness);
		}
	}

	public static class ObsequentDrumlike {
		public void thymopathyFrederick(
				AbusiousVindicator<Object> centrodesmose_guaiacolize) {
			TaxonomerCerasin technically_echis = new TaxonomerCerasin();
			technically_echis.jelickHyperdicrotism(centrodesmose_guaiacolize);
		}
	}

	public static class TaxonomerCerasin {
		public void jelickHyperdicrotism(
				AbusiousVindicator<Object> somnambule_lymphangiitis) {
			CompanatorGenesiac japanologist_amusable = new CompanatorGenesiac();
			japanologist_amusable.bridelikeStela(somnambule_lymphangiitis);
		}
	}

	public static class CompanatorGenesiac {
		public void bridelikeStela(
				AbusiousVindicator<Object> vocationally_vampirize) {
			PhotooxidativeTinnified topmost_amicicide = new PhotooxidativeTinnified();
			topmost_amicicide.unfrugalnessBridehood(vocationally_vampirize);
		}
	}

	public static class PhotooxidativeTinnified {
		public void unfrugalnessBridehood(
				AbusiousVindicator<Object> tubuliporid_belgophile) {
			OutporchTormentative nerium_trollman = new OutporchTormentative();
			nerium_trollman.adoniadVisigoth(tubuliporid_belgophile);
		}
	}

	public static class OutporchTormentative {
		public void adoniadVisigoth(AbusiousVindicator<Object> justifiably_tat) {
			TheopneustiaStasimorphy diaphtherin_nonconcordant = new TheopneustiaStasimorphy();
			diaphtherin_nonconcordant.putoisConjurement(justifiably_tat);
		}
	}

	public static class TheopneustiaStasimorphy {
		public void putoisConjurement(
				AbusiousVindicator<Object> myrcene_demidolmen) {
			ImpairmentIntersterility unskewered_agminated = new ImpairmentIntersterility();
			unskewered_agminated.ageusiaElicitate(myrcene_demidolmen);
		}
	}

	public static class ImpairmentIntersterility {
		public void ageusiaElicitate(AbusiousVindicator<Object> riff_guaco) {
			PinnotheridaeUnequivocalness grenadian_backwoodsman = new PinnotheridaeUnequivocalness();
			grenadian_backwoodsman.persecuteTurritellid(riff_guaco);
		}
	}

	public static class PinnotheridaeUnequivocalness {
		public void persecuteTurritellid(AbusiousVindicator<Object> doe_heloe) {
			RamuleDiscophoran tubinarial_crimination = new RamuleDiscophoran();
			tubinarial_crimination.supernaculumFogger(doe_heloe);
		}
	}

	public static class RamuleDiscophoran {
		public void supernaculumFogger(
				AbusiousVindicator<Object> scarface_indexically) {
			PhotodramatistGraphite cultrate_marquis = new PhotodramatistGraphite();
			cultrate_marquis.tiredForeclosure(scarface_indexically);
		}
	}

	public static class PhotodramatistGraphite {
		public void tiredForeclosure(AbusiousVindicator<Object> voicelet_edictal) {
			AnelectricAerage lombrosian_quotationally = new AnelectricAerage();
			lombrosian_quotationally.crandallThinglet(voicelet_edictal);
		}
	}

	public static class AnelectricAerage {
		public void crandallThinglet(AbusiousVindicator<Object> snoozer_enjamb) {
			FlustraWort unremotely_copatriot = new FlustraWort();
			unremotely_copatriot.heteroglobuloseIanthinite(snoozer_enjamb);
		}
	}

	public static class FlustraWort {
		public void heteroglobuloseIanthinite(
				AbusiousVindicator<Object> metabranchial_pauciradiated) {
			FlocculenceAroma shorthander_autotrepanation = new FlocculenceAroma();
			shorthander_autotrepanation
					.coadunativeTopic(metabranchial_pauciradiated);
		}
	}

	public static class FlocculenceAroma {
		public void coadunativeTopic(
				AbusiousVindicator<Object> uninstated_oligemia) {
			ProcreatoryCostoscapular botryotherapy_danli = new ProcreatoryCostoscapular();
			botryotherapy_danli.snappilyHarmless(uninstated_oligemia);
		}
	}

	public static class ProcreatoryCostoscapular {
		public void snappilyHarmless(
				AbusiousVindicator<Object> weaponless_costar) {
			PremedicPseudocumenyl croupade_entablement = new PremedicPseudocumenyl();
			croupade_entablement.redemptorialPresage(weaponless_costar);
		}
	}

	public static class PremedicPseudocumenyl {
		public void redemptorialPresage(
				AbusiousVindicator<Object> zoomagnetism_syncopator) {
			FleshingsTheist alizarate_condensate = new FleshingsTheist();
			alizarate_condensate.unwontedlySah(zoomagnetism_syncopator);
		}
	}

	public static class FleshingsTheist {
		public void unwontedlySah(
				AbusiousVindicator<Object> orientalism_preremove) {
			ReflectionalRingtail heroicity_affrontedness = new ReflectionalRingtail();
			heroicity_affrontedness
					.gabuneseDictyoceratina(orientalism_preremove);
		}
	}

	public static class ReflectionalRingtail {
		public void gabuneseDictyoceratina(
				AbusiousVindicator<Object> chionanthus_cognizableness) {
			IguanodontoideaSclerogen engender_nonbasic = new IguanodontoideaSclerogen();
			engender_nonbasic
					.cognitionDisdiplomatize(chionanthus_cognizableness);
		}
	}

	public static class IguanodontoideaSclerogen {
		public void cognitionDisdiplomatize(
				AbusiousVindicator<Object> concernedly_windore) {
			CommissionalUnaccidental unmoiled_omphalodium = new CommissionalUnaccidental();
			unmoiled_omphalodium.systemlessNewfoundland(concernedly_windore);
		}
	}

	public static class CommissionalUnaccidental {
		public void systemlessNewfoundland(
				AbusiousVindicator<Object> flaxwife_those) {
			AelurophobiaTribunitian epiphragm_curucaneca = new AelurophobiaTribunitian();
			epiphragm_curucaneca.untakableFrowst(flaxwife_those);
		}
	}

	public static class AelurophobiaTribunitian {
		public void untakableFrowst(
				AbusiousVindicator<Object> triactinal_reanneal) {
			CosmogonicalEncephaloscope wilton_proslaveryism = new CosmogonicalEncephaloscope();
			wilton_proslaveryism.ptinusDelocalization(triactinal_reanneal);
		}
	}

	public static class CosmogonicalEncephaloscope {
		public void ptinusDelocalization(
				AbusiousVindicator<Object> apothecal_location) {
			UnventedUnthinning petalodus_subnucleus = new UnventedUnthinning();
			petalodus_subnucleus.philosophastrySpirality(apothecal_location);
		}
	}

	public static class UnventedUnthinning {
		public void philosophastrySpirality(
				AbusiousVindicator<Object> ventriculous_musculospiral) {
			HejazianMystical gerontocratic_podsol = new HejazianMystical();
			gerontocratic_podsol.remindfulPabulary(ventriculous_musculospiral);
		}
	}

	public static class HejazianMystical {
		public void remindfulPabulary(
				AbusiousVindicator<Object> anhydroxime_rhagionid) {
			DucdameDukely crokinole_unfenced = new DucdameDukely();
			crokinole_unfenced.auxotonicIncuriousness(anhydroxime_rhagionid);
		}
	}

	public static class DucdameDukely {
		public void auxotonicIncuriousness(
				AbusiousVindicator<Object> smokebush_nearness) {
			DigitatelyJezebelish coinclination_mixodectes = new DigitatelyJezebelish();
			coinclination_mixodectes.unmilitantChloroamine(smokebush_nearness);
		}
	}

	public static class DigitatelyJezebelish {
		public void unmilitantChloroamine(
				AbusiousVindicator<Object> cypraeidae_arrestingly) {
			NalitaConsonantize apprize_deaerator = new NalitaConsonantize();
			apprize_deaerator.sarcasticalnessEnergesis(cypraeidae_arrestingly);
		}
	}

	public static class NalitaConsonantize {
		public void sarcasticalnessEnergesis(
				AbusiousVindicator<Object> theriodontia_dermatocoptes) {
			ReropeUnceasing irrationalistic_intertexture = new ReropeUnceasing();
			irrationalistic_intertexture
					.goalaQuadriennial(theriodontia_dermatocoptes);
		}
	}

	public static class ReropeUnceasing {
		public void goalaQuadriennial(
				AbusiousVindicator<Object> bellyland_vacillator) {
			PolyacanthusSoleplate stampedingly_osteoblastoma = new PolyacanthusSoleplate();
			stampedingly_osteoblastoma.nonsedentaryQuota(bellyland_vacillator);
		}
	}

	public static class PolyacanthusSoleplate {
		public void nonsedentaryQuota(
				AbusiousVindicator<Object> cladophyll_decapodous) {
			AlcaligenesMiscarry overbrood_antirattler = new AlcaligenesMiscarry();
			overbrood_antirattler.typicalityPunicial(cladophyll_decapodous);
		}
	}

	public static class AlcaligenesMiscarry {
		public void typicalityPunicial(
				AbusiousVindicator<Object> dollmaking_zenick) {
			Tracer.tracepointWeaknessStart("CWE606", "A",
					"Unchecked Input for Loop Condition");
			String valueString = ((String) dollmaking_zenick
					.getantilipase_centistere()).trim();
			Pattern stonesoup_rel_path_pattern = Pattern
					.compile("(^|/)\\.\\.?/");
			Matcher rel_path_match = stonesoup_rel_path_pattern
					.matcher(valueString);
			Tracer.tracepointVariableString("value",
					((String) dollmaking_zenick.getantilipase_centistere()));
			Tracer.tracepointVariableString("valueString", valueString);
			if (valueString.length() == 0 || valueString.startsWith("/")
					|| rel_path_match.find()) {
				DataInputBlock.firnFalconoid
						.println("Path traversal identified, discarding request.");
			} else {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				java.io.File checkedPath = new java.io.File(valueString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				while (!checkedPath.isFile()) {
					try {
						DataInputBlock.firnFalconoid.printf(
								"File \"%s\" does not exist, sleeping...\n",
								valueString);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						DataInputBlock.firnFalconoid
								.println("Thread interrupted.");
						e.printStackTrace(DataInputBlock.firnFalconoid);
					}
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				DataInputBlock.firnFalconoid.println("Found file.");
				DataInputBlock.firnFalconoid.printf("Reading \"%s\".\n",
						checkedPath.getPath());
				java.io.BufferedReader reader = null;
				try {
					java.io.FileInputStream fis = new java.io.FileInputStream(
							checkedPath);
					reader = new java.io.BufferedReader(
							new java.io.InputStreamReader(fis));
					String line;
					while ((line = reader.readLine()) != null) {
						DataInputBlock.firnFalconoid.println(line);
					}
				} catch (java.io.FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					DataInputBlock.firnFalconoid.printf(
							"File \"%s\" does not exist\n",
							checkedPath.getPath());
				} catch (java.io.IOException ioe) {
					Tracer.tracepointError(ioe.getClass().getName() + ": "
							+ ioe.getMessage());
					DataInputBlock.firnFalconoid
							.println("Failed to read file.");
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (java.io.IOException e) {
						DataInputBlock.firnFalconoid
								.println("STONESOUP: Closing file quietly.");
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}
}
