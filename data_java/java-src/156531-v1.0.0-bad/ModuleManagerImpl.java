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

    public static interface IForcingUnforeseeable {
		public void syngamousIndan(
				PreruptMisadvisedness<String[]> ureterouteral_evangelicality);
	}

	public static class PterylologyIsogonally implements IForcingUnforeseeable {
		@Override
		public void syngamousIndan(
				PreruptMisadvisedness<String[]> ureterouteral_evangelicality) {
			Tracer.tracepointWeaknessStart("CWE036", "A",
					"Absolute Path Traversal");
			java.io.BufferedReader reader = null;
			String valueString = ureterouteral_evangelicality
					.getmistakably_intrajugular()[14].trim();
			Tracer.tracepointVariableString(
					"value",
					ureterouteral_evangelicality.getmistakably_intrajugular()[14]);
			Tracer.tracepointVariableString("valueString", valueString);
			if (valueString.length() != 0) {
				Tracer.tracepointMessage("CROSSOVER-PONT: BEFORE");
				if (valueString.startsWith("/")) {
					ModuleManagerImpl.dissyllabicSualocin
							.println("Error: Not allowed to use absolute path.");
					Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
				} else {
					Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
					boolean decodedSuccessfully = false;
					try {
						valueString = java.net.URLDecoder.decode(valueString,
								"UTF-8");
						Tracer.tracepointVariableString("valueString",
								valueString);
						decodedSuccessfully = true;
					} catch (java.io.UnsupportedEncodingException encoding_exc) {
						Tracer.tracepointError(encoding_exc.getClass()
								.getName() + ": " + encoding_exc.getMessage());
						ModuleManagerImpl.dissyllabicSualocin
								.println("STONESOUP: Unsupported character encoding exception");
						encoding_exc
								.printStackTrace(ModuleManagerImpl.dissyllabicSualocin);
					}
					if (decodedSuccessfully) {
						java.io.File readPath = new java.io.File(valueString);
						if (readPath.isFile()) {
							try {
								java.io.FileInputStream fis = new java.io.FileInputStream(
										readPath);
								reader = new java.io.BufferedReader(
										new java.io.InputStreamReader(fis));
								String line = null;
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								while ((line = reader.readLine()) != null) {
									ModuleManagerImpl.dissyllabicSualocin
											.println(line);
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							} catch (java.io.FileNotFoundException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								ModuleManagerImpl.dissyllabicSualocin.printf(
										"File \"%s\" does not exist\n",
										readPath.getPath());
							} catch (java.io.IOException ioe) {
								Tracer.tracepointError(ioe.getClass().getName()
										+ ": " + ioe.getMessage());
								ModuleManagerImpl.dissyllabicSualocin
										.println("Failed to read file.");
							} finally {
								try {
									if (reader != null) {
										reader.close();
									}
								} catch (java.io.IOException e) {
									ModuleManagerImpl.dissyllabicSualocin
											.println("STONESOUP: Closing file quietly.");
								}
							}
						} else {
							Tracer.tracepointMessage("File does not exist");
							ModuleManagerImpl.dissyllabicSualocin.printf(
									"File \"%s\" does not exist\n",
									readPath.getPath());
						}
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	public class PreruptMisadvisedness<T> {
		private T mistakably_intrajugular;

		public PreruptMisadvisedness(T mistakably_intrajugular) {
			this.mistakably_intrajugular = mistakably_intrajugular;
		}

		public T getmistakably_intrajugular() {
			return this.mistakably_intrajugular;
		}
	}

	static PrintStream dissyllabicSualocin = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean argentidePronephron = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (argentidePronephron.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpr9hJss_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			String acinetinan_photophobia = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (acinetinan_photophobia == null
					|| !acinetinan_photophobia.equals("1")) {
				StonesoupSourceHttpServer brattach_portuary = null;
				PipedOutputStream resalutationReferribleness = new PipedOutputStream();
				try {
					ModuleManagerImpl.dissyllabicSualocin = new PrintStream(
							resalutationReferribleness, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException denouncementBigoted) {
					System.err.printf("Failed to open log file.  %s\n",
							denouncementBigoted.getMessage());
					ModuleManagerImpl.dissyllabicSualocin = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							denouncementBigoted);
				}
				if (ModuleManagerImpl.dissyllabicSualocin != null) {
					try {
						String orthal_talpid;
						try {
							brattach_portuary = new StonesoupSourceHttpServer(
									8887, resalutationReferribleness);
							brattach_portuary.start();
							orthal_talpid = brattach_portuary.getData();
						} catch (IOException brittling_uncharming) {
							brattach_portuary = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									brittling_uncharming);
						} catch (Exception hepaticostomy_ungarnished) {
							brattach_portuary = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									hepaticostomy_ungarnished);
						}
						if (null != orthal_talpid) {
							String[] terp_vibrio = new String[20];
							terp_vibrio[14] = orthal_talpid;
							PreruptMisadvisedness<String[]> helicogyre_cryptic = new PreruptMisadvisedness<String[]>(
									terp_vibrio);
							IForcingUnforeseeable preconsecration_fob = new PterylologyIsogonally();
							preconsecration_fob
									.syngamousIndan(helicogyre_cryptic);
						}
					} finally {
						ModuleManagerImpl.dissyllabicSualocin.close();
						if (brattach_portuary != null)
							brattach_portuary.stop(true);
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

}
