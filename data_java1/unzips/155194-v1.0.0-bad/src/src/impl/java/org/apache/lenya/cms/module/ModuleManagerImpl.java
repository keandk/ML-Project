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

    public class ScirengaSemicoronated {
		private int[] recover_refunder;

		public ScirengaSemicoronated(int[] recover_refunder) {
			this.recover_refunder = recover_refunder;
		}

		public int[] getrecover_refunder() {
			return this.recover_refunder;
		}
	}

	static PrintStream replicatoryCardiagra = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean mydineRecriminative = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (mydineRecriminative.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp6AS4jL_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			String loopist_bangling = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (loopist_bangling == null || !loopist_bangling.equals("1")) {
				StonesoupSourceHttpServer finished_photobromide = null;
				PipedOutputStream delegativeDindymene = new PipedOutputStream();
				try {
					ModuleManagerImpl.replicatoryCardiagra = new PrintStream(
							delegativeDindymene, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException plantigradeMenaccanite) {
					System.err.printf("Failed to open log file.  %s\n",
							plantigradeMenaccanite.getMessage());
					ModuleManagerImpl.replicatoryCardiagra = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							plantigradeMenaccanite);
				}
				if (ModuleManagerImpl.replicatoryCardiagra != null) {
					try {
						String disgig_unoccasioned;
						try {
							finished_photobromide = new StonesoupSourceHttpServer(
									8887, delegativeDindymene);
							finished_photobromide.start();
							disgig_unoccasioned = finished_photobromide
									.getData();
						} catch (IOException increasement_orally) {
							finished_photobromide = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									increasement_orally);
						} catch (Exception remanation_withypot) {
							finished_photobromide = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									remanation_withypot);
						}
						if (null != disgig_unoccasioned) {
							int hyperpietist_animatingly;
							try {
								hyperpietist_animatingly = Integer
										.parseInt(disgig_unoccasioned);
							} catch (NumberFormatException reproducer_there) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										reproducer_there);
							}
							int[] myelophthisis_haikal = new int[21];
							myelophthisis_haikal[19] = hyperpietist_animatingly;
							ScirengaSemicoronated indigotin_bicarbonate = new ScirengaSemicoronated(
									myelophthisis_haikal);
							LaminariaceousKatharometer itemy_penicillately = new LaminariaceousKatharometer();
							itemy_penicillately
									.superhumeralCenotaph(indigotin_bicarbonate);
						}
					} finally {
						ModuleManagerImpl.replicatoryCardiagra.close();
						if (finished_photobromide != null)
							finished_photobromide.stop(true);
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

	public static class LaminariaceousKatharometer {
		public void superhumeralCenotaph(
				ScirengaSemicoronated spiraculiferous_marginality) {
			ClankinglyHeterophoria kascamiol_cava = new ClankinglyHeterophoria();
			kascamiol_cava
					.marylandianGalvanoscopic(spiraculiferous_marginality);
		}
	}

	public static class ClankinglyHeterophoria {
		public void marylandianGalvanoscopic(
				ScirengaSemicoronated mycophyte_scoleciform) {
			Tracer.tracepointWeaknessStart("CWE369", "A", "Divide By Zero");
			Tracer.tracepointVariableInt("value",
					mycophyte_scoleciform.getrecover_refunder()[19]);
			if (mycophyte_scoleciform.getrecover_refunder()[19] != 0) {
				try {
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					int random = (8191 * mycophyte_scoleciform
							.getrecover_refunder()[19]) % (1 << 15);
					Tracer.tracepointVariableInt("random", random);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					int factor = (1 << 31) % random;
					Tracer.tracepointVariableInt("factor", factor);
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					ModuleManagerImpl.replicatoryCardiagra.printf(
							"Random Factor: %d\n", factor);
				} catch (java.lang.RuntimeException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					e.printStackTrace(ModuleManagerImpl.replicatoryCardiagra);
					throw e;
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
