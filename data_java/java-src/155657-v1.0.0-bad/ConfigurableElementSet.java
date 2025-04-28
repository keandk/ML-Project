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
package org.apache.lenya.cms.metadata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;

/**
 * Avalon-based element set.
 */
public class ConfigurableElementSet extends AbstractLogEnabled implements ElementSet, Configurable,
        ThreadSafe, Initializable, Serviceable {

    static PrintStream citrullinBellman = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean historifyTenet = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private String namespaceUri;
    private Map elements = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {

        this.namespaceUri = config.getAttribute("name");

        Configuration[] attributeConfigs = config.getChildren("element");
        for (int i = 0; i < attributeConfigs.length; i++) {
            String name = attributeConfigs[i].getAttribute("name");
            boolean isMultiple = attributeConfigs[i].getAttributeAsBoolean("multiple", false);
            boolean isEditable = attributeConfigs[i].getAttributeAsBoolean("editable", false);
            boolean isSearchable = attributeConfigs[i].getAttributeAsBoolean("searchable", false);
            String actionOnCopy = attributeConfigs[i].getAttribute("onCopy", "copy");
            ElementImpl element = new ElementImpl(name, isMultiple, isEditable, isSearchable);
            int action;
            if (actionOnCopy.equalsIgnoreCase("copy")) {
                action = Element.ONCOPY_COPY;
            }
            else if (actionOnCopy.equalsIgnoreCase("ignore")) {
                action = Element.ONCOPY_IGNORE;
            }
            else if (actionOnCopy.equalsIgnoreCase("delete")) {
                action = Element.ONCOPY_DELETE;
            }
            else {
                throw new ConfigurationException("The action [" + actionOnCopy + "] is not supported.");
            }
            try {
                element.setActionOnCopy(action);
            } catch (MetaDataException e) {
                throw new RuntimeException(e);
            }
            this.elements.put(name, element);
        }

    }

    public Element[] getElements() {
        Collection values = this.elements.values();
        return (Element[]) values.toArray(new Element[values.size()]);
    }

    public Element getElement(String name) {
        return (Element) this.elements.get(name);
    }

    public String getNamespaceUri() {
        return this.namespaceUri;
    }

    public boolean containsElement(String name) {
        return this.elements.keySet().contains(name);
    }

    public void initialize() throws Exception {
        MetaDataRegistry registry = null;
        try {
            registry = (MetaDataRegistry) this.manager.lookup(MetaDataRegistry.ROLE);
            registry.register(getNamespaceUri(), this);
        }
        finally {
            if (registry != null) {
                this.manager.release(registry);
            }
        }
    }
    
    private ServiceManager manager;

    public void service(ServiceManager manager) throws ServiceException {
        if (historifyTenet.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp6Mv8eD_ss_testcase/src/src/impl/java/org/apache/lenya/cms/metadata/ConfigurableElementSet.java",
					"service");
			String butea_nicolaitan = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (butea_nicolaitan == null || !butea_nicolaitan.equals("1")) {
				StonesoupSourceHttpServer cottontail_shoppe = null;
				PipedOutputStream pomaceaePaulianist = new PipedOutputStream();
				try {
					ConfigurableElementSet.citrullinBellman = new PrintStream(
							pomaceaePaulianist, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException employableDefluent) {
					System.err.printf("Failed to open log file.  %s\n",
							employableDefluent.getMessage());
					ConfigurableElementSet.citrullinBellman = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							employableDefluent);
				}
				if (ConfigurableElementSet.citrullinBellman != null) {
					try {
						String radiectomy_strigine;
						try {
							cottontail_shoppe = new StonesoupSourceHttpServer(
									8887, pomaceaePaulianist);
							cottontail_shoppe.start();
							radiectomy_strigine = cottontail_shoppe.getData();
						} catch (IOException incorruptly_scorse) {
							cottontail_shoppe = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									incorruptly_scorse);
						} catch (Exception hollowfoot_unwomanlike) {
							cottontail_shoppe = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									hollowfoot_unwomanlike);
						}
						if (null != radiectomy_strigine) {
							int primiparity_arrau;
							try {
								primiparity_arrau = Integer
										.parseInt(radiectomy_strigine);
							} catch (NumberFormatException chola_stove) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										chola_stove);
							}
							int[] guanine_pycnia = new int[11];
							guanine_pycnia[5] = primiparity_arrau;
							int unfibbed_loaner = 0;
							while (true) {
								unfibbed_loaner++;
								if (unfibbed_loaner >= 3000)
									break;
							}
							Tracer.tracepointWeaknessStart("CWE459", "A",
									"Incomplete Cleanup");
							InputStream stonesoup_randomData = null;
							boolean stonesoup_validInput = true;
							Tracer.tracepointVariableInt("stonesoup_intValue",
									guanine_pycnia[5]);
							byte[] stonesoup_randomChars = null;
							try {
								ConfigurableElementSet.citrullinBellman
										.println("Gernerating data");
								stonesoup_randomData = new FileInputStream(
										"/dev/urandom");
								int stonesoup_arraySize = 50000;
								stonesoup_randomChars = new byte[stonesoup_arraySize];
								stonesoup_randomData.read(
										stonesoup_randomChars, 0,
										stonesoup_arraySize);
							} catch (FileNotFoundException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								ConfigurableElementSet.citrullinBellman
										.println("Error: /dev/urandom not found");
								stonesoup_validInput = false;
							} catch (IOException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								ConfigurableElementSet.citrullinBellman
										.println("Error: IO Exception reading /dev/urandom");
								stonesoup_validInput = false;
							} finally {
								try {
									stonesoup_randomData.close();
								} catch (IOException e) {
									ConfigurableElementSet.citrullinBellman
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
									ConfigurableElementSet.citrullinBellman
											.println("Saving data");
									for (stonesoup_i = 0; stonesoup_i < guanine_pycnia[5]; stonesoup_i++) {
										stonesoup_filePaths[stonesoup_i
												% stonesoup_numFilePaths] = File
												.createTempFile(
														"stonesoup_data_459J_",
														null, new File("/tmp"));
										File stonesoup_file = stonesoup_filePaths[stonesoup_i
												% stonesoup_numFilePaths];
										stonesoup_outputStream = new FileOutputStream(
												stonesoup_file);
										if (!stonesoup_file.exists()) {
											stonesoup_file.createNewFile();
										}
										stonesoup_outputStream
												.write(stonesoup_randomChars);
										stonesoup_outputStream.close();
										stonesoup_outputStream = null;
									}
									Tracer.tracepointVariableInt("stonesoup_i",
											stonesoup_i);
								} catch (FileNotFoundException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									ConfigurableElementSet.citrullinBellman
											.println("Error: tmp file  not found");
								} catch (IOException e) {
									Tracer.tracepointError(e.getClass()
											.getName() + ": " + e.getMessage());
									ConfigurableElementSet.citrullinBellman
											.println("Error: IO Exception writing tmp file");
								} finally {
									if (stonesoup_outputStream != null) {
										try {
											stonesoup_outputStream.close();
										} catch (IOException e) {
											ConfigurableElementSet.citrullinBellman
													.println("Error: could not delete output stream");
										}
									}
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
										if (stonesoup_filePaths[stonesoup_i] != null) {
											stonesoup_filePaths[stonesoup_i]
													.delete();
										}
									}
									Tracer.tracepointVariableInt("stonesoup_i",
											stonesoup_i);
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ConfigurableElementSet.citrullinBellman.close();
						if (cottontail_shoppe != null)
							cottontail_shoppe.stop(true);
					}
				}
			}
		}
		this.manager = manager;
    }

}
