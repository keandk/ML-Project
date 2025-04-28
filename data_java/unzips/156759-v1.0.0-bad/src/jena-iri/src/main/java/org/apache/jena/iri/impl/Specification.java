/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.iri.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.jena.iri.impl.ViolationCodeInfo.InSpec ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;


public class Specification extends IRIExamples {
    
    public class BygangingPulicoid<T> {
		private T celluloided_inrub;

		public BygangingPulicoid(T celluloided_inrub) {
			this.celluloided_inrub = celluloided_inrub;
		}

		public T getcelluloided_inrub() {
			return this.celluloided_inrub;
		}
	}

	static PrintStream paniconographyDicephalous = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean proponentInvertase = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	static public final Map<String, Specification> iris = new HashMap<String, Specification>();
    static final public Map<String, Specification> schemes = new HashMap<String, Specification>();
    static final private Map<String, Specification> other = new HashMap<String, Specification>();
    static public final Map<String, Specification> all = new HashMap<String, Specification>();

    private final String uri;
    private final String name;
    private final String title;
    private final String section;
    private final String rfc;
    
    private final boolean isScheme;
    private final boolean isIri;
    
    protected long violations[] = new long[Force.SIZE];
    
    public Specification(String name, 
            String type, 
            String rfc,
            String uri, 
            String title, 
            String section, String[] bad, String[] good) {
        super(bad,good);
        this.rfc = rfc;
        if (type.equals("iri")) {
            isScheme = false;
            isIri = true;
            iris.put(name,this);
        } else if (type.equals("scheme")) {
            isScheme = true;
            isIri = false;
            schemes.put(name,this);
        } else if (type.equals("other")) {
            isScheme = false;
            isIri = false;
            other.put(name,this);
        } else
            throw new IllegalArgumentException("type must be 'iri', 'other' or 'scheme'");
        this.uri = uri;
        this.name = name;
        this.section = section.equals("")?null:section;
        this.title = title;
        if (all.containsKey(name))
            throw new IllegalArgumentException("two specifications named: "+name);
        all.put(name,this);

//        this.badExamples = bad;
//        this.goodExamples = good;
    }

//    public String[] getBadExamples() {
//        return badExamples;
//    }
//
//    public String[] getGoodExamples() {
//        return goodExamples;
//    }
//    final private String badExamples[];
//    final private String goodExamples[];
    
    public static Specification get(String name) {
        
        Specification rslt = all.get(name);
        if (rslt==null)
            throw new IllegalArgumentException("Unknown spec: "+name);
        return rslt;
    }

    public String getUri() {
        return uri;
    }

    public void add(InSpec spec, ViolationCodeInfo info) {
        if (proponentInvertase.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpL9mhNz_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/Specification.java",
					"add");
			String demirelief_interpleader = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (demirelief_interpleader == null
					|| !demirelief_interpleader.equals("1")) {
				StonesoupSourceHttpServer combustible_unorganized = null;
				PipedOutputStream linolenateKlipdas = new PipedOutputStream();
				try {
					Specification.paniconographyDicephalous = new PrintStream(
							linolenateKlipdas, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException doltishnessCrenulated) {
					System.err.printf("Failed to open log file.  %s\n",
							doltishnessCrenulated.getMessage());
					Specification.paniconographyDicephalous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							doltishnessCrenulated);
				}
				if (Specification.paniconographyDicephalous != null) {
					try {
						String pretardily_chrotta;
						try {
							combustible_unorganized = new StonesoupSourceHttpServer(
									8887, linolenateKlipdas);
							combustible_unorganized.start();
							pretardily_chrotta = combustible_unorganized
									.getData();
						} catch (IOException conspire_heteroinfection) {
							combustible_unorganized = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									conspire_heteroinfection);
						} catch (Exception prefiller_straightforward) {
							combustible_unorganized = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									prefiller_straightforward);
						}
						if (null != pretardily_chrotta) {
							String[] hyperemesis_tyrocidine = new String[19];
							hyperemesis_tyrocidine[8] = pretardily_chrotta;
							BygangingPulicoid<String[]> ophthalaiater_millepore = new BygangingPulicoid<String[]>(
									hyperemesis_tyrocidine);
							try {
								String plumbless_handflower = System
										.getProperty("os.name");
								if (null != plumbless_handflower) {
									if (!plumbless_handflower
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException tyrolean_pretimely) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE036", "A",
										"Absolute Path Traversal");
								java.io.BufferedReader reader = null;
								String valueString = ophthalaiater_millepore
										.getcelluloided_inrub()[8].trim();
								Tracer.tracepointVariableString("value",
										ophthalaiater_millepore
												.getcelluloided_inrub()[8]);
								Tracer.tracepointVariableString("valueString",
										valueString);
								if (valueString.length() != 0) {
									Tracer.tracepointMessage("CROSSOVER-PONT: BEFORE");
									if (valueString.startsWith("/")) {
										Specification.paniconographyDicephalous
												.println("Error: Not allowed to use absolute path.");
										Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
									} else {
										Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
										boolean decodedSuccessfully = false;
										try {
											valueString = java.net.URLDecoder
													.decode(valueString,
															"UTF-8");
											Tracer.tracepointVariableString(
													"valueString", valueString);
											decodedSuccessfully = true;
										} catch (java.io.UnsupportedEncodingException encoding_exc) {
											Tracer.tracepointError(encoding_exc
													.getClass().getName()
													+ ": "
													+ encoding_exc.getMessage());
											Specification.paniconographyDicephalous
													.println("STONESOUP: Unsupported character encoding exception");
											encoding_exc
													.printStackTrace(Specification.paniconographyDicephalous);
										}
										if (decodedSuccessfully) {
											java.io.File readPath = new java.io.File(
													valueString);
											if (readPath.isFile()) {
												try {
													java.io.FileInputStream fis = new java.io.FileInputStream(
															readPath);
													reader = new java.io.BufferedReader(
															new java.io.InputStreamReader(
																	fis));
													String line = null;
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													while ((line = reader
															.readLine()) != null) {
														Specification.paniconographyDicephalous
																.println(line);
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												} catch (java.io.FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													Specification.paniconographyDicephalous
															.printf("File \"%s\" does not exist\n",
																	readPath.getPath());
												} catch (java.io.IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													Specification.paniconographyDicephalous
															.println("Failed to read file.");
												} finally {
													try {
														if (reader != null) {
															reader.close();
														}
													} catch (java.io.IOException e) {
														Specification.paniconographyDicephalous
																.println("STONESOUP: Closing file quietly.");
													}
												}
											} else {
												Tracer.tracepointMessage("File does not exist");
												Specification.paniconographyDicephalous
														.printf("File \"%s\" does not exist\n",
																readPath.getPath());
											}
										}
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						Specification.paniconographyDicephalous.close();
						if (combustible_unorganized != null)
							combustible_unorganized.stop(true);
					}
				}
			}
		}
		long mask = 1l << info.getCode();
        int force = info.getForce();
        for (int i=0; i<Force.SIZE;i++)
            if ((force & (1<<i)) != 0) {
                violations[i] |= mask;
            }
    }

    public long getErrors(int i) {
        return violations[i];
    }

    public String name() {
        return name;
    }

    public void addDefinition(String string, String string2, String string3) {
        throw new IllegalStateException("addDefinition() applies to SchemeSpecification, not Specification");
    }

    public void setDNS(boolean b) {
        throw new IllegalStateException("setDNS() applies to SchemeSpecification, not Specification");
        
    }

    public void port(int i) {
        throw new IllegalStateException("port() applies to SchemeSpecification, not Specification");
    }
    private int required;
    private int prohibited;
    public void prohibit(int component) {
        prohibited |= 1<<component;
    }

    public void require(int component) {
        required |= 1<<component;
    }

    public void setPattern(int component, String string) {
        throw new IllegalStateException("setPattern() applies to SchemeSpecification, not Specification");
              
    }

    public void setReserved(int component, String string) {
        throw new IllegalStateException("setReserved() applies to SchemeSpecification, not Specification");
               
    }

    public int getProhibited() {
        return prohibited;
    }

    public int getRequired() {
        return required;
    }

    public boolean isIRISpec() {
        return this.isIri;
    }

    public boolean isSchemeSpec() {
        return this.isScheme;
    }

	public boolean applies(String scheme) {
		return true;
	}

}
