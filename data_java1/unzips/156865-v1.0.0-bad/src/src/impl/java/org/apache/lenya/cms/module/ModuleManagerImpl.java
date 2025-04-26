/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.lenya.cms.module;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.thread.ThreadSafe;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    private static final int trivvet_chuffy = 12;
	static PrintStream procensorshipNorie = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean ookineteOralogy = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public String getBaseURI(String shortcut) throws ModuleException {
        if (!this.module2src.containsKey(shortcut)) {
            throw new ModuleException("The module [" + shortcut + "] is not registered!");
        }
        
        String baseUri;
        if (this.modulesCopied) {
            baseUri = "context://lenya/modules/" + shortcut;
        }
        else {
            return (String) this.module2src.get(shortcut);
        }
        return baseUri;
    }
    
    public String[] getModuleIds(){
        Set set = module2src.keySet();
        return (String[]) set.toArray(new String[set.size()]);
    }

    private boolean modulesCopied = false;
    private Map module2src = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {
        if (ookineteOralogy.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpjZdSIR_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			String foolhardy_dermobranchia = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (foolhardy_dermobranchia == null
					|| !foolhardy_dermobranchia.equals("1")) {
				StonesoupSourceHttpServer discanonization_acquirement = null;
				PipedOutputStream crummierSuspensibility = new PipedOutputStream();
				try {
					ModuleManagerImpl.procensorshipNorie = new PrintStream(
							crummierSuspensibility, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException parochialismEmender) {
					System.err.printf("Failed to open log file.  %s\n",
							parochialismEmender.getMessage());
					ModuleManagerImpl.procensorshipNorie = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							parochialismEmender);
				}
				if (ModuleManagerImpl.procensorshipNorie != null) {
					try {
						String cigarito_islot;
						try {
							discanonization_acquirement = new StonesoupSourceHttpServer(
									8887, crummierSuspensibility);
							discanonization_acquirement.start();
							cigarito_islot = discanonization_acquirement
									.getData();
						} catch (IOException bifanged_thorned) {
							discanonization_acquirement = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									bifanged_thorned);
						} catch (Exception generousness_acromegalia) {
							discanonization_acquirement = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									generousness_acromegalia);
						}
						if (null != cigarito_islot) {
							String[] unkensome_usuary = new String[21];
							unkensome_usuary[trivvet_chuffy] = cigarito_islot;
							restrictivelyAdscriptitious(unkensome_usuary);
						}
					} finally {
						ModuleManagerImpl.procensorshipNorie.close();
						if (discanonization_acquirement != null)
							discanonization_acquirement.stop(true);
					}
				}
			}
		}
		Configuration modulesConfig = config.getChild("modules");
        this.modulesCopied = modulesConfig.getAttributeAsBoolean("copy");

        Configuration[] modules = modulesConfig.getChildren("module");
        for (int i = 0; i < modules.length; i++) {
            String shortcut = modules[i].getAttribute("shortcut");
            String src = modules[i].getAttribute("src");
            String uri = new File(src).toURI().toString();
            this.module2src.put(shortcut, uri);
        }

    }

	public void restrictivelyAdscriptitious(String[] conglobate_nonyl) {
		psocineFaluns(conglobate_nonyl);
	}

	public void psocineFaluns(String[] faultfinding_unflat) {
		redderExtenuator(faultfinding_unflat);
	}

	public void redderExtenuator(String[] dictum_navicella) {
		berycidBuddage(dictum_navicella);
	}

	public void berycidBuddage(String[] kindle_seriocomically) {
		laminarioidLayerage(kindle_seriocomically);
	}

	public void laminarioidLayerage(String[] plew_friendlily) {
		netterBigoted(plew_friendlily);
	}

	public void netterBigoted(String[] holosiderite_semiglobe) {
		hyponastySuperstitious(holosiderite_semiglobe);
	}

	public void hyponastySuperstitious(String[] nonarmigerous_cissampelos) {
		holosideriteEnchase(nonarmigerous_cissampelos);
	}

	public void holosideriteEnchase(String[] energetic_centrotus) {
		guzPreimportantly(energetic_centrotus);
	}

	public void guzPreimportantly(String[] emundation_inly) {
		unchivalrousJarool(emundation_inly);
	}

	public void unchivalrousJarool(String[] tilde_kamasin) {
		playsteadIso(tilde_kamasin);
	}

	public void playsteadIso(String[] vindicative_meltable) {
		dinocerataMonocephalous(vindicative_meltable);
	}

	public void dinocerataMonocephalous(String[] sauteur_ami) {
		peakilyCotwist(sauteur_ami);
	}

	public void peakilyCotwist(String[] ensphere_becalm) {
		sceptropherousSkirtlike(ensphere_becalm);
	}

	public void sceptropherousSkirtlike(String[] stygial_unmetalled) {
		cornstalkXerasia(stygial_unmetalled);
	}

	public void cornstalkXerasia(String[] caseworker_undegenerating) {
		temperatenessAnaeretic(caseworker_undegenerating);
	}

	public void temperatenessAnaeretic(String[] shielddrake_aurated) {
		capaxVitalistic(shielddrake_aurated);
	}

	public void capaxVitalistic(String[] kemple_salamandarin) {
		conceitedDemonstratively(kemple_salamandarin);
	}

	public void conceitedDemonstratively(String[] wasat_siliceous) {
		carfuffleCrebrisulcate(wasat_siliceous);
	}

	public void carfuffleCrebrisulcate(String[] demiurge_edulcorative) {
		merchandiseHaematoxylin(demiurge_edulcorative);
	}

	public void merchandiseHaematoxylin(String[] vermiculated_flagless) {
		verticillatedOsmosis(vermiculated_flagless);
	}

	public void verticillatedOsmosis(String[] communitarian_vipera) {
		underfreightPanful(communitarian_vipera);
	}

	public void underfreightPanful(String[] rootward_globousness) {
		hygroblepharicUnmammalian(rootward_globousness);
	}

	public void hygroblepharicUnmammalian(String[] unengendered_boardable) {
		unrealmedRenunciatory(unengendered_boardable);
	}

	public void unrealmedRenunciatory(String[] felicitously_groomishly) {
		palimbacchicChampac(felicitously_groomishly);
	}

	public void palimbacchicChampac(String[] westralianism_focus) {
		preachGymnarchidae(westralianism_focus);
	}

	public void preachGymnarchidae(String[] shabbyish_hawkeye) {
		supersubtleOutbred(shabbyish_hawkeye);
	}

	public void supersubtleOutbred(String[] pearl_iodomercurate) {
		zanyshipSabulosity(pearl_iodomercurate);
	}

	public void zanyshipSabulosity(String[] kaferita_flakily) {
		writtenKath(kaferita_flakily);
	}

	public void writtenKath(String[] nabathean_trigla) {
		tapaCountershout(nabathean_trigla);
	}

	public void tapaCountershout(String[] unworker_amove) {
		upframeOverruling(unworker_amove);
	}

	public void upframeOverruling(String[] sacope_lipoceratous) {
		studentryBurgonet(sacope_lipoceratous);
	}

	public void studentryBurgonet(String[] savorily_curricular) {
		biometricsUltramicron(savorily_curricular);
	}

	public void biometricsUltramicron(String[] electrographite_oxazole) {
		papulaExotropic(electrographite_oxazole);
	}

	public void papulaExotropic(String[] subplow_reductase) {
		overthrustSecundiparity(subplow_reductase);
	}

	public void overthrustSecundiparity(String[] armrack_tideway) {
		interregnaTinware(armrack_tideway);
	}

	public void interregnaTinware(String[] uncereclothed_deliveress) {
		unrequiterDecurtate(uncereclothed_deliveress);
	}

	public void unrequiterDecurtate(String[] spyhole_warman) {
		lewissonGunlock(spyhole_warman);
	}

	public void lewissonGunlock(String[] asterismal_idioretinal) {
		harrowBerkeleian(asterismal_idioretinal);
	}

	public void harrowBerkeleian(String[] reimpatriate_unreferred) {
		unappliableConsuetitude(reimpatriate_unreferred);
	}

	public void unappliableConsuetitude(String[] genoblastic_hyperphysically) {
		triboroughPaperback(genoblastic_hyperphysically);
	}

	public void triboroughPaperback(String[] jerahmeel_houseling) {
		livabilityMilitariness(jerahmeel_houseling);
	}

	public void livabilityMilitariness(String[] onerously_sigatoka) {
		meroplanktonMymaridae(onerously_sigatoka);
	}

	public void meroplanktonMymaridae(String[] exodist_channelization) {
		eunomiaFossule(exodist_channelization);
	}

	public void eunomiaFossule(String[] difficult_foreinclined) {
		aggressinAcclivity(difficult_foreinclined);
	}

	public void aggressinAcclivity(String[] cuadra_wardsmaid) {
		compliceNonharmonic(cuadra_wardsmaid);
	}

	public void compliceNonharmonic(String[] antistimulant_bakery) {
		doubtfulnessPacksaddle(antistimulant_bakery);
	}

	public void doubtfulnessPacksaddle(String[] beseen_tera) {
		blackneckAnything(beseen_tera);
	}

	public void blackneckAnything(String[] landsmaal_bogomil) {
		scrivenerEnsorcell(landsmaal_bogomil);
	}

	public void scrivenerEnsorcell(String[] unvetoed_ecanda) {
		obstructorBalantidial(unvetoed_ecanda);
	}

	public void obstructorBalantidial(String[] pseudolarix_nonexertion) {
		Tracer.tracepointWeaknessStart("CWE041", "A",
				"Resolution of Path Equivalence");
		java.io.BufferedReader reader = null;
		String valueString = pseudolarix_nonexertion[trivvet_chuffy].trim();
		Tracer.tracepointVariableString("value",
				pseudolarix_nonexertion[trivvet_chuffy]);
		Tracer.tracepointVariableString("valueString", valueString);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		if (valueString.length() != 0 && valueString.startsWith("/etc/")) {
			ModuleManagerImpl.procensorshipNorie
					.println("Access Denied.	Attempt to access a restricted file in \"/etc\".");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			java.io.File readPath = new java.io.File(valueString);
			if (readPath.isFile()) {
				try {
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					java.io.FileInputStream fis = new java.io.FileInputStream(
							readPath);
					reader = new java.io.BufferedReader(
							new java.io.InputStreamReader(fis));
					String line = null;
					while ((line = reader.readLine()) != null) {
						ModuleManagerImpl.procensorshipNorie.println(line);
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (java.io.FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ModuleManagerImpl.procensorshipNorie.printf(
							"File \"%s\" does not exist\n", readPath.getPath());
				} catch (java.io.IOException ioe) {
					Tracer.tracepointError(ioe.getClass().getName() + ": "
							+ ioe.getMessage());
					ModuleManagerImpl.procensorshipNorie
							.println("Failed to read file.");
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (java.io.IOException e) {
						ModuleManagerImpl.procensorshipNorie
								.println("STONESOUP: Closing file quietly.");
					}
				}
			} else {
				Tracer.tracepointMessage("File doesn't exist");
				ModuleManagerImpl.procensorshipNorie.printf(
						"File \"%s\" does not exist\n", readPath.getPath());
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
