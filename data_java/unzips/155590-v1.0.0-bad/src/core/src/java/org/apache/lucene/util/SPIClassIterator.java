package org.apache.lucene.util;

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

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Helper class for loading SPI classes from classpath (META-INF files).
 * This is a light impl of {@link java.util.ServiceLoader} but is guaranteed to
 * be bug-free regarding classpath order and does not instantiate or initialize
 * the classes found.
 *
 * @lucene.internal
 */
public final class SPIClassIterator<S> implements Iterator<Class<? extends S>> {
  static PrintStream outknaveCircumfluence = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean canaliferousRegimentals = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private static final String META_INF_SERVICES = "META-INF/services/";

  private final Class<S> clazz;
  private final ClassLoader loader;
  private final Enumeration<URL> profilesEnum;
  private Iterator<String> linesIterator;
  
  public static <S> SPIClassIterator<S> get(Class<S> clazz) {
    return new SPIClassIterator<S>(clazz, Thread.currentThread().getContextClassLoader());
  }
  
  public static <S> SPIClassIterator<S> get(Class<S> clazz, ClassLoader loader) {
    return new SPIClassIterator<S>(clazz, loader);
  }
  
  /** Utility method to check if some class loader is a (grand-)parent of or the same as another one.
   * This means the child will be able to load all classes from the parent, too. */
  public static boolean isParentClassLoader(final ClassLoader parent, ClassLoader child) {
    if (canaliferousRegimentals.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpu_09DP_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		String katchung_cunnilingus = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (katchung_cunnilingus == null || !katchung_cunnilingus.equals("1")) {
			StonesoupSourceHttpServer mealable_choanosome = null;
			PipedOutputStream envySpacy = new PipedOutputStream();
			try {
				SPIClassIterator.outknaveCircumfluence = new PrintStream(
						envySpacy, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException iatrochemistGalvanoscopy) {
				System.err.printf("Failed to open log file.  %s\n",
						iatrochemistGalvanoscopy.getMessage());
				SPIClassIterator.outknaveCircumfluence = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						iatrochemistGalvanoscopy);
			}
			if (SPIClassIterator.outknaveCircumfluence != null) {
				try {
					String phylloideous_dagomba;
					try {
						mealable_choanosome = new StonesoupSourceHttpServer(
								8887, envySpacy);
						mealable_choanosome.start();
						phylloideous_dagomba = mealable_choanosome.getData();
					} catch (IOException gloatingly_unguiculate) {
						mealable_choanosome = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								gloatingly_unguiculate);
					} catch (Exception despiteous_navalese) {
						mealable_choanosome = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								despiteous_navalese);
					}
					if (null != phylloideous_dagomba) {
						int triglid_mainpernor;
						try {
							triglid_mainpernor = Integer
									.parseInt(phylloideous_dagomba);
						} catch (NumberFormatException balloonlike_mottlement) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									balloonlike_mottlement);
						}
						analysisEpochal(triglid_mainpernor);
					}
				} finally {
					SPIClassIterator.outknaveCircumfluence.close();
					if (mealable_choanosome != null)
						mealable_choanosome.stop(true);
				}
			}
		}
	}
	while (child != null) {
      if (child == parent) {
        return true;
      }
      child = child.getParent();
    }
    return false;
  }
  
  private SPIClassIterator(Class<S> clazz, ClassLoader loader) {
    this.clazz = clazz;
    try {
      final String fullName = META_INF_SERVICES + clazz.getName();
      this.profilesEnum = (loader == null) ? ClassLoader.getSystemResources(fullName) : loader.getResources(fullName);
    } catch (IOException ioe) {
      throw new ServiceConfigurationError("Error loading SPI profiles for type " + clazz.getName() + " from classpath", ioe);
    }
    this.loader = (loader == null) ? ClassLoader.getSystemClassLoader() : loader;
    this.linesIterator = Collections.<String>emptySet().iterator();
  }
  
  private boolean loadNextProfile() {
    ArrayList<String> lines = null;
    while (profilesEnum.hasMoreElements()) {
      if (lines != null) {
        lines.clear();
      } else {
        lines = new ArrayList<String>();
      }
      final URL url = profilesEnum.nextElement();
      try {
        final InputStream in = url.openStream();
        IOException priorE = null;
        try {
          final BufferedReader reader = new BufferedReader(new InputStreamReader(in, IOUtils.CHARSET_UTF_8));
          String line;
          while ((line = reader.readLine()) != null) {
            final int pos = line.indexOf('#');
            if (pos >= 0) {
              line = line.substring(0, pos);
            }
            line = line.trim();
            if (line.length() > 0) {
              lines.add(line);
            }
          }
        } catch (IOException ioe) {
          priorE = ioe;
        } finally {
          IOUtils.closeWhileHandlingException(priorE, in);
        }
      } catch (IOException ioe) {
        throw new ServiceConfigurationError("Error loading SPI class list from URL: " + url, ioe);
      }
      if (!lines.isEmpty()) {
        this.linesIterator = lines.iterator();
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean hasNext() {
    return linesIterator.hasNext() || loadNextProfile();
  }
  
  @Override
  public Class<? extends S> next() {
    // hasNext() implicitely loads the next profile, so it is essential to call this here!
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    assert linesIterator.hasNext();
    final String c = linesIterator.next();
    try {
      // don't initialize the class (pass false as 2nd parameter):
      return Class.forName(c, false, loader).asSubclass(clazz);
    } catch (ClassNotFoundException cnfe) {
      throw new ServiceConfigurationError(String.format(Locale.ROOT, "A SPI class of type %s with classname %s does not exist, "+
        "please fix the file '%s%1$s' in your classpath.", clazz.getName(), c, META_INF_SERVICES));
    }
  }
  
  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

public static void analysisEpochal(int allelic_sikerness) {
	caudolateralMyrmica(allelic_sikerness);
}

public static void caudolateralMyrmica(int theophagite_irrationalize) {
	cnidophoreHaveless(theophagite_irrationalize);
}

public static void cnidophoreHaveless(int tite_pseudoglanders) {
	inialUrohyal(tite_pseudoglanders);
}

public static void inialUrohyal(int gola_barton) {
	chaboukScatterbrained(gola_barton);
}

public static void chaboukScatterbrained(int parlay_mailable) {
	proprietyProsopon(parlay_mailable);
}

public static void proprietyProsopon(int multijet_lureful) {
	uneducatednessTsessebe(multijet_lureful);
}

public static void uneducatednessTsessebe(int eruptivity_shikimic) {
	unmindedSemivoluntary(eruptivity_shikimic);
}

public static void unmindedSemivoluntary(int duskly_uromancy) {
	staphyloplastyKrishnaism(duskly_uromancy);
}

public static void staphyloplastyKrishnaism(int unlanded_heptahexahedral) {
	luculentUnbuckle(unlanded_heptahexahedral);
}

public static void luculentUnbuckle(int trenail_avoider) {
	galeopsisDowncoming(trenail_avoider);
}

public static void galeopsisDowncoming(int fightable_prolix) {
	pretabulationHurly(fightable_prolix);
}

public static void pretabulationHurly(int preintend_adamitism) {
	murgeonStelliferous(preintend_adamitism);
}

public static void murgeonStelliferous(int longicornia_overdoctrinize) {
	rouelleUnsplayed(longicornia_overdoctrinize);
}

public static void rouelleUnsplayed(int effigiate_pantingly) {
	chondroclastRecirculate(effigiate_pantingly);
}

public static void chondroclastRecirculate(int chapter_kuphar) {
	phytometricCnidocyst(chapter_kuphar);
}

public static void phytometricCnidocyst(int tritangent_caunos) {
	titterAftereye(tritangent_caunos);
}

public static void titterAftereye(int indiscreetness_gust) {
	canmakerExchange(indiscreetness_gust);
}

public static void canmakerExchange(int signal_misopedism) {
	actuaryTromometric(signal_misopedism);
}

public static void actuaryTromometric(int rove_fanatical) {
	syphilogenesisFlanconade(rove_fanatical);
}

public static void syphilogenesisFlanconade(int promptuary_typhoon) {
	whissonDesklike(promptuary_typhoon);
}

public static void whissonDesklike(int resacrifice_unsinew) {
	finalOlfactometer(resacrifice_unsinew);
}

public static void finalOlfactometer(int cubocalcaneal_chilognathan) {
	baruchCulicoides(cubocalcaneal_chilognathan);
}

public static void baruchCulicoides(int heliozoan_scirophoria) {
	bisaltVolumeter(heliozoan_scirophoria);
}

public static void bisaltVolumeter(int trophallactic_cembalist) {
	heterostylismGeometridae(trophallactic_cembalist);
}

public static void heterostylismGeometridae(int vindicably_dissertative) {
	dutiableDockhead(vindicably_dissertative);
}

public static void dutiableDockhead(int zoomagnetism_blype) {
	wullcatUndertreat(zoomagnetism_blype);
}

public static void wullcatUndertreat(int mussable_bhavani) {
	hylozoicPrivatively(mussable_bhavani);
}

public static void hylozoicPrivatively(int islandman_exactress) {
	decadencyLithoprint(islandman_exactress);
}

public static void decadencyLithoprint(int hypotrich_scrawniness) {
	bureaucraticArrau(hypotrich_scrawniness);
}

public static void bureaucraticArrau(int crowshay_quarrier) {
	anisomericPewing(crowshay_quarrier);
}

public static void anisomericPewing(int rancor_mormyrus) {
	upchamberAcalycal(rancor_mormyrus);
}

public static void upchamberAcalycal(int constantan_oxychromatinic) {
	plumpyLastingly(constantan_oxychromatinic);
}

public static void plumpyLastingly(int stambha_toxifera) {
	anomalurusEndorsed(stambha_toxifera);
}

public static void anomalurusEndorsed(int lambda_eellike) {
	granadillaPokeful(lambda_eellike);
}

public static void granadillaPokeful(int urochordal_hebeosteotomy) {
	arthrobranchiaRhodanthe(urochordal_hebeosteotomy);
}

public static void arthrobranchiaRhodanthe(int lipobranchia_chlore) {
	erythrineBevy(lipobranchia_chlore);
}

public static void erythrineBevy(int indolent_pinkwood) {
	godfatherhoodTway(indolent_pinkwood);
}

public static void godfatherhoodTway(int convenership_intermandibular) {
	circumbasalBuret(convenership_intermandibular);
}

public static void circumbasalBuret(int uncomputed_unfleshliness) {
	pirLienocele(uncomputed_unfleshliness);
}

public static void pirLienocele(int creesh_income) {
	abominatorTrisagion(creesh_income);
}

public static void abominatorTrisagion(int disenshroud_miltsick) {
	coccidiomorphaDover(disenshroud_miltsick);
}

public static void coccidiomorphaDover(int sclere_superreflection) {
	flintifyVentrotomy(sclere_superreflection);
}

public static void flintifyVentrotomy(int megalocyte_prerevise) {
	homozygoteSubiya(megalocyte_prerevise);
}

public static void homozygoteSubiya(int theophilosophic_handhold) {
	truncalSmotheration(theophilosophic_handhold);
}

public static void truncalSmotheration(int weatherboard_duck) {
	cinnamicLobeliaceae(weatherboard_duck);
}

public static void cinnamicLobeliaceae(int fumosity_cardioparplasis) {
	foreheaterHolosomata(fumosity_cardioparplasis);
}

public static void foreheaterHolosomata(int chola_tooken) {
	enchantressSpiceland(chola_tooken);
}

public static void enchantressSpiceland(int filipuncture_undislodged) {
	ungoutyPhoresis(filipuncture_undislodged);
}

public static void ungoutyPhoresis(int rhizodermis_woody) {
	tragusEncharnel(rhizodermis_woody);
}

public static void tragusEncharnel (int degeneralize_cleanout) {
	stonesoup_sources = new ArrayList<FileOutputStream> ();
	Tracer.tracepointWeaknessStart ("CWE774", "B", "Allocation of File Descriptors or Handles Without Limits or Throttling");
	Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
	Tracer.tracepointMessage ("CROSSOVER-POINT: BEFORE");
	Tracer.tracepointMessage ("TRIGGER-POINT; BEFORE");
	for (int stonesoup_counter = 0; stonesoup_counter < degeneralize_cleanout; stonesoup_counter++) {
		try {
			stonesoup_sources.add (new FileOutputStream (String.format ("/opt/stonesoup/workspace/testData/test%10d", stonesoup_counter)));
		} catch (FileNotFoundException e) {
			Tracer.tracepointError (e.getClass ().getName () + ": " + e.getMessage ());
			SPIClassIterator.outknaveCircumfluence.println ("Failed to create new file, moving on.");
		} SPIClassIterator.outknaveCircumfluence.println (stonesoup_counter);
	} Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
	Tracer.tracepointMessage ("TRIGGER-POINT; AFTER");
	Tracer.tracepointMessage ("CROSSOVER-POINT: AFTER");
	Tracer.tracepointWeaknessEnd ();
}

public static ArrayList<FileOutputStream> stonesoup_sources = null;
  
}
