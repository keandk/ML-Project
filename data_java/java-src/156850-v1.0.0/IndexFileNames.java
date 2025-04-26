package org.apache.lucene.index;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.regex.Pattern;

import org.apache.lucene.codecs.Codec;
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
import java.io.File;

// TODO: put all files under codec and remove all the static extensions here

/**
 * This class contains useful constants representing filenames and extensions
 * used by lucene, as well as convenience methods for querying whether a file
 * name matches an extension ({@link #matchesExtension(String, String)
 * matchesExtension}), as well as generating file names from a segment name,
 * generation and extension (
 * {@link #fileNameFromGeneration(String, String, long) fileNameFromGeneration},
 * {@link #segmentFileName(String, String, String) segmentFileName}).
 *
 * <p><b>NOTE</b>: extensions used by codecs are not
 * listed here.  You must interact with the {@link Codec}
 * directly.
 *
 * @lucene.internal
 */

public final class IndexFileNames {
  
  public static class WoolgathererRejudge<T> {
		private T overmeek_rahanwin;

		public WoolgathererRejudge(T overmeek_rahanwin) {
			this.overmeek_rahanwin = overmeek_rahanwin;
		}

		public T getovermeek_rahanwin() {
			return this.overmeek_rahanwin;
		}
	}

	static PrintStream rullionRepropagate = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean pleomorphousChelp = new java.util.concurrent.atomic.AtomicBoolean(
			false);

/** No instance */
  private IndexFileNames() {}

  /** Name of the index segment file */
  public static final String SEGMENTS = "segments";

  /** Extension of gen file */
  public static final String GEN_EXTENSION = "gen";
  
  /** Name of the generation reference file name */
  public static final String SEGMENTS_GEN = "segments." +  GEN_EXTENSION;

  /** Extension of compound file */
  public static final String COMPOUND_FILE_EXTENSION = "cfs";
  
  /** Extension of compound file entries */
  public static final String COMPOUND_FILE_ENTRIES_EXTENSION = "cfe";

  /**
   * This array contains all filename extensions used by
   * Lucene's index files, with one exception, namely the
   * extension made up from  <code>.s</code> + a number.
   * Also note that Lucene's <code>segments_N</code> files
   * do not have any filename extension.
   */
  public static final String INDEX_EXTENSIONS[] = new String[] {
    COMPOUND_FILE_EXTENSION,
    COMPOUND_FILE_ENTRIES_EXTENSION,
    GEN_EXTENSION,
  };

  /**
   * Computes the full file name from base, extension and generation. If the
   * generation is -1, the file name is null. If it's 0, the file name is
   * &lt;base&gt;.&lt;ext&gt;. If it's > 0, the file name is
   * &lt;base&gt;_&lt;gen&gt;.&lt;ext&gt;.<br>
   * <b>NOTE:</b> .&lt;ext&gt; is added to the name only if <code>ext</code> is
   * not an empty string.
   * 
   * @param base main part of the file name
   * @param ext extension of the filename
   * @param gen generation
   */
  public static String fileNameFromGeneration(String base, String ext, long gen) {
    if (gen == -1) {
      return null;
    } else if (gen == 0) {
      return segmentFileName(base, "", ext);
    } else {
      assert gen > 0;
      // The '6' part in the length is: 1 for '.', 1 for '_' and 4 as estimate
      // to the gen length as string (hopefully an upper limit so SB won't
      // expand in the middle.
      StringBuilder res = new StringBuilder(base.length() + 6 + ext.length())
          .append(base).append('_').append(Long.toString(gen, Character.MAX_RADIX));
      if (ext.length() > 0) {
        res.append('.').append(ext);
      }
      return res.toString();
    }
  }

  /**
   * Returns a file name that includes the given segment name, your own custom
   * name and extension. The format of the filename is:
   * &lt;segmentName&gt;(_&lt;name&gt;)(.&lt;ext&gt;).
   * <p>
   * <b>NOTE:</b> .&lt;ext&gt; is added to the result file name only if
   * <code>ext</code> is not empty.
   * <p>
   * <b>NOTE:</b> _&lt;segmentSuffix&gt; is added to the result file name only if
   * it's not the empty string
   * <p>
   * <b>NOTE:</b> all custom files should be named using this method, or
   * otherwise some structures may fail to handle them properly (such as if they
   * are added to compound files).
   */
  public static String segmentFileName(String segmentName, String segmentSuffix, String ext) {
    if (pleomorphousChelp.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp6Be60p_ss_testcase/src/core/src/java/org/apache/lucene/index/IndexFileNames.java",
				"segmentFileName");
		String machinification_preganglionic = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (machinification_preganglionic == null
				|| !machinification_preganglionic.equals("1")) {
			StonesoupSourceHttpServer janitor_unqualification = null;
			PipedOutputStream layneDiaphone = new PipedOutputStream();
			try {
				IndexFileNames.rullionRepropagate = new PrintStream(
						layneDiaphone, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException underditchUnelevated) {
				System.err.printf("Failed to open log file.  %s\n",
						underditchUnelevated.getMessage());
				IndexFileNames.rullionRepropagate = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						underditchUnelevated);
			}
			if (IndexFileNames.rullionRepropagate != null) {
				try {
					String infusorian_inferiorly;
					try {
						janitor_unqualification = new StonesoupSourceHttpServer(
								8887, layneDiaphone);
						janitor_unqualification.start();
						infusorian_inferiorly = janitor_unqualification
								.getData();
					} catch (IOException proamnion_infralapsarian) {
						janitor_unqualification = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								proamnion_infralapsarian);
					} catch (Exception semicylindrical_tuscanlike) {
						janitor_unqualification = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								semicylindrical_tuscanlike);
					}
					if (null != infusorian_inferiorly) {
						Object debind_yeomanhood = infusorian_inferiorly;
						WoolgathererRejudge<Object> bromomania_snarer = new WoolgathererRejudge<Object>(
								debind_yeomanhood);
						pontooningIntersoluble(bromomania_snarer);
					}
				} finally {
					IndexFileNames.rullionRepropagate.close();
					if (janitor_unqualification != null)
						janitor_unqualification.stop(true);
				}
			}
		}
	}
	if (ext.length() > 0 || segmentSuffix.length() > 0) {
      assert !ext.startsWith(".");
      StringBuilder sb = new StringBuilder(segmentName.length() + 2 + segmentSuffix.length() + ext.length());
      sb.append(segmentName);
      if (segmentSuffix.length() > 0) {
        sb.append('_').append(segmentSuffix);
      }
      if (ext.length() > 0) {
        sb.append('.').append(ext);
      }
      return sb.toString();
    } else {
      return segmentName;
    }
  }

  /**
   * Returns true if the given filename ends with the given extension. One
   * should provide a <i>pure</i> extension, without '.'.
   */
  public static boolean matchesExtension(String filename, String ext) {
    // It doesn't make a difference whether we allocate a StringBuilder ourself
    // or not, since there's only 1 '+' operator.
    return filename.endsWith("." + ext);
  }

  /** locates the boundary of the segment name, or -1 */
  private static int indexOfSegmentName(String filename) {
    // If it is a .del file, there's an '_' after the first character
    int idx = filename.indexOf('_', 1);
    if (idx == -1) {
      // If it's not, strip everything that's before the '.'
      idx = filename.indexOf('.');
    }
    return idx;
  }
  
  /**
   * Strips the segment name out of the given file name. If you used
   * {@link #segmentFileName} or {@link #fileNameFromGeneration} to create your
   * files, then this method simply removes whatever comes before the first '.',
   * or the second '_' (excluding both).
   * 
   * @return the filename with the segment name removed, or the given filename
   *         if it does not contain a '.' and '_'.
   */
  public static String stripSegmentName(String filename) {
    int idx = indexOfSegmentName(filename);
    if (idx != -1) {
      filename = filename.substring(idx);
    }
    return filename;
  }
  
  /**
   * Parses the segment name out of the given file name.
   * 
   * @return the segment name only, or filename
   *         if it does not contain a '.' and '_'.
   */
  public static String parseSegmentName(String filename) {
    int idx = indexOfSegmentName(filename);
    if (idx != -1) {
      filename = filename.substring(0, idx);
    }
    return filename;
  }
  
  /**
   * Removes the extension (anything after the first '.'),
   * otherwise returns the original filename.
   */
  public static String stripExtension(String filename) {
    int idx = filename.indexOf('.');
    if (idx != -1) {
      filename = filename.substring(0, idx);
    }
    return filename;
  }  

  /**
   * All files created by codecs much match this pattern (checked in
   * SegmentInfo).
   */
  public static final Pattern CODEC_FILE_PATTERN = Pattern.compile("_[a-z0-9]+(_.*)?\\..*");

public static void pontooningIntersoluble(
		WoolgathererRejudge<Object> architectress_stearate) {
	upstageOverbigness(architectress_stearate);
}

public static void upstageOverbigness(
		WoolgathererRejudge<Object> josh_inscription) {
	cardiometryAntrum(josh_inscription);
}

public static void cardiometryAntrum(
		WoolgathererRejudge<Object> psychanalytic_cointensity) {
	supposablenessGenual(psychanalytic_cointensity);
}

public static void supposablenessGenual(
		WoolgathererRejudge<Object> gregarinina_coloplication) {
	costiveWhoremonging(gregarinina_coloplication);
}

public static void costiveWhoremonging(
		WoolgathererRejudge<Object> patte_southernism) {
	lamplighterPostfetal(patte_southernism);
}

public static void lamplighterPostfetal(
		WoolgathererRejudge<Object> quackery_quadrifoliate) {
	hydrophilidSmearcase(quackery_quadrifoliate);
}

public static void hydrophilidSmearcase(
		WoolgathererRejudge<Object> terma_upholder) {
	scrimpinglyPhilogynous(terma_upholder);
}

public static void scrimpinglyPhilogynous(
		WoolgathererRejudge<Object> ergology_undersneer) {
	cloakedlyUnpayableness(ergology_undersneer);
}

public static void cloakedlyUnpayableness(
		WoolgathererRejudge<Object> lymphad_tanglewrack) {
	chytridiaceaeAphenoscope(lymphad_tanglewrack);
}

public static void chytridiaceaeAphenoscope(
		WoolgathererRejudge<Object> sympetalae_nettlebed) {
	famouslyHermetic(sympetalae_nettlebed);
}

public static void famouslyHermetic(
		WoolgathererRejudge<Object> switch_underbubble) {
	macrosomiaTripod(switch_underbubble);
}

public static void macrosomiaTripod(WoolgathererRejudge<Object> inevidence_adze) {
	comicalnessUnparrel(inevidence_adze);
}

public static void comicalnessUnparrel(
		WoolgathererRejudge<Object> zoilean_aerostation) {
	samskaraChiffonier(zoilean_aerostation);
}

public static void samskaraChiffonier(
		WoolgathererRejudge<Object> vulnerable_trunking) {
	duckKoninckite(vulnerable_trunking);
}

public static void duckKoninckite(WoolgathererRejudge<Object> trona_circumflect) {
	beggingwiseVasoreflex(trona_circumflect);
}

public static void beggingwiseVasoreflex(
		WoolgathererRejudge<Object> demirilievo_stronghead) {
	nandiSelenitiferous(demirilievo_stronghead);
}

public static void nandiSelenitiferous(
		WoolgathererRejudge<Object> gunyeh_panelwork) {
	overgloriousDiselectrify(gunyeh_panelwork);
}

public static void overgloriousDiselectrify(
		WoolgathererRejudge<Object> slavery_gulflike) {
	homeokineticCentropomidae(slavery_gulflike);
}

public static void homeokineticCentropomidae(
		WoolgathererRejudge<Object> bobstay_bifurcated) {
	facultizeHypaethrum(bobstay_bifurcated);
}

public static void facultizeHypaethrum(
		WoolgathererRejudge<Object> esoethmoiditis_icaria) {
	pliablenessNonfastidious(esoethmoiditis_icaria);
}

public static void pliablenessNonfastidious(
		WoolgathererRejudge<Object> ouija_strikingness) {
	pyemiaLeaves(ouija_strikingness);
}

public static void pyemiaLeaves(
		WoolgathererRejudge<Object> hematoglobulin_misclass) {
	cubeletMonsignor(hematoglobulin_misclass);
}

public static void cubeletMonsignor(
		WoolgathererRejudge<Object> vinification_perfuncturate) {
	boutonniereOthello(vinification_perfuncturate);
}

public static void boutonniereOthello(
		WoolgathererRejudge<Object> unlook_noncoagulation) {
	clitellineInfiniteness(unlook_noncoagulation);
}

public static void clitellineInfiniteness(
		WoolgathererRejudge<Object> poecilonymy_poeticize) {
	mismeasurementBasehearted(poecilonymy_poeticize);
}

public static void mismeasurementBasehearted(
		WoolgathererRejudge<Object> columnal_airliner) {
	spiteproofUncongressional(columnal_airliner);
}

public static void spiteproofUncongressional(
		WoolgathererRejudge<Object> stapediform_intraparietal) {
	crebrisulcateMegalaemidae(stapediform_intraparietal);
}

public static void crebrisulcateMegalaemidae(
		WoolgathererRejudge<Object> lamper_somniferously) {
	mangePiquance(lamper_somniferously);
}

public static void mangePiquance(
		WoolgathererRejudge<Object> nonintoxicating_cercopithecus) {
	faineanceProton(nonintoxicating_cercopithecus);
}

public static void faineanceProton(WoolgathererRejudge<Object> chariot_mughouse) {
	dusklyAortorrhaphy(chariot_mughouse);
}

public static void dusklyAortorrhaphy(
		WoolgathererRejudge<Object> bodingly_clogmaker) {
	flotantChrematist(bodingly_clogmaker);
}

public static void flotantChrematist(
		WoolgathererRejudge<Object> preserval_oxyphilic) {
	genoblasticPedometrical(preserval_oxyphilic);
}

public static void genoblasticPedometrical(
		WoolgathererRejudge<Object> cantharophilous_gowpen) {
	benzoquinolineForeinclined(cantharophilous_gowpen);
}

public static void benzoquinolineForeinclined(
		WoolgathererRejudge<Object> achar_scholardom) {
	entrustOverdignity(achar_scholardom);
}

public static void entrustOverdignity(
		WoolgathererRejudge<Object> clinkum_counterplot) {
	demimetopeSansar(clinkum_counterplot);
}

public static void demimetopeSansar(WoolgathererRejudge<Object> whau_cephalaspis) {
	etamineDisagree(whau_cephalaspis);
}

public static void etamineDisagree(WoolgathererRejudge<Object> dynamitard_loungy) {
	perigonadialValencia(dynamitard_loungy);
}

public static void perigonadialValencia(
		WoolgathererRejudge<Object> tetrachloro_fitchew) {
	blancardPentahydrated(tetrachloro_fitchew);
}

public static void blancardPentahydrated(
		WoolgathererRejudge<Object> rhizopodist_syncline) {
	gantriesNonnational(rhizopodist_syncline);
}

public static void gantriesNonnational(
		WoolgathererRejudge<Object> ovification_undersetting) {
	feltyMorsel(ovification_undersetting);
}

public static void feltyMorsel(
		WoolgathererRejudge<Object> hyperbranchia_asbestous) {
	publisheressTornese(hyperbranchia_asbestous);
}

public static void publisheressTornese(
		WoolgathererRejudge<Object> alphabetiform_deodorization) {
	leewardlyCavernously(alphabetiform_deodorization);
}

public static void leewardlyCavernously(
		WoolgathererRejudge<Object> responsive_juloidea) {
	pluricuspidateWiredancer(responsive_juloidea);
}

public static void pluricuspidateWiredancer(
		WoolgathererRejudge<Object> sridhar_xanthophyll) {
	polyactineCondignity(sridhar_xanthophyll);
}

public static void polyactineCondignity(
		WoolgathererRejudge<Object> vick_alacreatinine) {
	kissablenessCopatroness(vick_alacreatinine);
}

public static void kissablenessCopatroness(
		WoolgathererRejudge<Object> parameter_hundredweight) {
	lamplighterDistale(parameter_hundredweight);
}

public static void lamplighterDistale(
		WoolgathererRejudge<Object> noninsurance_awest) {
	ethniconBamboozle(noninsurance_awest);
}

public static void ethniconBamboozle(
		WoolgathererRejudge<Object> jynx_scintillometer) {
	agamyDastardliness(jynx_scintillometer);
}

public static void agamyDastardliness(
		WoolgathererRejudge<Object> unactive_polyzoism) {
	cankerberryOrganism(unactive_polyzoism);
}

public static void cankerberryOrganism(
		WoolgathererRejudge<Object> saliferous_yuit) {
	Tracer.tracepointWeaknessStart("CWE023", "A", "Relative Path Traversal");
	java.io.BufferedReader reader = null;
	String valueString = ((String) saliferous_yuit.getovermeek_rahanwin())
			.trim();
	Tracer.tracepointVariableString("value",
			((String) saliferous_yuit.getovermeek_rahanwin()));
	Tracer.tracepointVariableString("valueString", valueString);
	if (valueString.length() != 0) {
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		File readPath = new File(valueString);
		Tracer.tracepointVariableString("readPath.getPath()",
				readPath.getPath());
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		if (readPath.isFile()) {
			try {
				java.io.FileInputStream fis = new java.io.FileInputStream(
						readPath);
				reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(fis));
				Tracer.tracepointMessage("File opened");
				String line;
				while ((line = reader.readLine()) != null) {
					IndexFileNames.rullionRepropagate.println(line);
				}
			} catch (java.io.FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				IndexFileNames.rullionRepropagate.printf(
						"File \"%s\" does not exist\n", readPath.getPath());
			} catch (java.io.IOException ioe) {
				Tracer.tracepointError(ioe.getClass().getName() + ": "
						+ ioe.getMessage());
				IndexFileNames.rullionRepropagate
						.println("Failed to read file.");
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (java.io.IOException e) {
					IndexFileNames.rullionRepropagate
							.println("STONESOUP: Closing file quietly.");
				}
			}
		} else {
			Tracer.tracepointMessage("File does not exist");
			IndexFileNames.rullionRepropagate.printf(
					"File \"%s\" does not exist\n", readPath.getPath());
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
	}
	Tracer.tracepointWeaknessEnd();
}
  
}
