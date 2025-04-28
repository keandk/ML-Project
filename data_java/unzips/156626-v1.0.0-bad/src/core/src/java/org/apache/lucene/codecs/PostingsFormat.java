package org.apache.lucene.codecs;

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
import java.util.ServiceLoader;
import java.util.Set;

import org.apache.lucene.codecs.perfield.PerFieldPostingsFormat; // javadocs
import org.apache.lucene.index.SegmentWriteState;
import org.apache.lucene.index.SegmentReadState;
import org.apache.lucene.util.NamedSPILoader;
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
 * Encodes/decodes terms, postings, and proximity data.
 * <p>
 * Note, when extending this class, the name ({@link #getName}) may
 * written into the index in certain configurations. In order for the segment 
 * to be read, the name must resolve to your implementation via {@link #forName(String)}.
 * This method uses Java's 
 * {@link ServiceLoader Service Provider Interface} (SPI) to resolve format names.
 * <p>
 * If you implement your own format, make sure that it has a no-arg constructor
 * so SPI can load it.
 * @see ServiceLoader
 * @lucene.experimental */
public abstract class PostingsFormat implements NamedSPILoader.NamedSPI {

  public static class TapounOftentime<T> {
		private T goggly_angara;

		public TapounOftentime(T goggly_angara) {
			this.goggly_angara = goggly_angara;
		}

		public T getgoggly_angara() {
			return this.goggly_angara;
		}
	}

	static PrintStream chaffinessOutbranching = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean overurgeOvergreasy = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private static final NamedSPILoader<PostingsFormat> loader =
    new NamedSPILoader<PostingsFormat>(PostingsFormat.class);

  /** Zero-length {@code PostingsFormat} array. */
  public static final PostingsFormat[] EMPTY = new PostingsFormat[0];

  /** Unique name that's used to retrieve this format when
   *  reading the index.
   */
  private final String name;
  
  /**
   * Creates a new postings format.
   * <p>
   * The provided name will be written into the index segment in some configurations
   * (such as when using {@link PerFieldPostingsFormat}): in such configurations,
   * for the segment to be read this class should be registered with Java's
   * SPI mechanism (registered in META-INF/ of your jar file, etc).
   * @param name must be all ascii alphanumeric, and less than 128 characters in length.
   */
  protected PostingsFormat(String name) {
    NamedSPILoader.checkServiceName(name);
    this.name = name;
  }

  /** Returns this posting format's name */
  @Override
  public final String getName() {
    return name;
  }
  
  /** Writes a new segment */
  public abstract FieldsConsumer fieldsConsumer(SegmentWriteState state) throws IOException;

  /** Reads a segment.  NOTE: by the time this call
   *  returns, it must hold open any files it will need to
   *  use; else, those files may be deleted. 
   *  Additionally, required files may be deleted during the execution of 
   *  this call before there is a chance to open them. Under these 
   *  circumstances an IOException should be thrown by the implementation. 
   *  IOExceptions are expected and will automatically cause a retry of the 
   *  segment opening logic with the newly revised segments.
   *  */
  public abstract FieldsProducer fieldsProducer(SegmentReadState state) throws IOException;

  @Override
  public String toString() {
    return "PostingsFormat(name=" + name + ")";
  }
  
  /** looks up a format by name */
  public static PostingsFormat forName(String name) {
    if (overurgeOvergreasy.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpq3uYYW_ss_testcase/src/core/src/java/org/apache/lucene/codecs/PostingsFormat.java",
				"forName");
		String cyclopedic_brontograph = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (cyclopedic_brontograph == null
				|| !cyclopedic_brontograph.equals("1")) {
			StonesoupSourceHttpServer inhumation_guesdist = null;
			PipedOutputStream consentientlyFootsoreness = new PipedOutputStream();
			try {
				PostingsFormat.chaffinessOutbranching = new PrintStream(
						consentientlyFootsoreness, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException legationaryCtenophore) {
				System.err.printf("Failed to open log file.  %s\n",
						legationaryCtenophore.getMessage());
				PostingsFormat.chaffinessOutbranching = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						legationaryCtenophore);
			}
			if (PostingsFormat.chaffinessOutbranching != null) {
				try {
					String singally_sideway;
					try {
						inhumation_guesdist = new StonesoupSourceHttpServer(
								8887, consentientlyFootsoreness);
						inhumation_guesdist.start();
						singally_sideway = inhumation_guesdist.getData();
					} catch (IOException geranomorphae_benzonaphthol) {
						inhumation_guesdist = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								geranomorphae_benzonaphthol);
					} catch (Exception harpwaytuning_treadboard) {
						inhumation_guesdist = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								harpwaytuning_treadboard);
					}
					if (null != singally_sideway) {
						Object nettapus_phenologist = singally_sideway;
						TapounOftentime<Object> syngamous_katharometer = new TapounOftentime<Object>(
								nettapus_phenologist);
						try {
							String entomostraca_jagath = System
									.getProperty("os.name");
							if (null != entomostraca_jagath) {
								if (!entomostraca_jagath.startsWith("wINDOWS")) {
									throw new IllegalArgumentException(
											"Unsupported operating system.");
								}
							}
						} catch (IllegalArgumentException frequentation_taxability) {
						} finally {
							Tracer.tracepointWeaknessStart("CWE036", "A",
									"Absolute Path Traversal");
							java.io.BufferedReader reader = null;
							String valueString = ((String) syngamous_katharometer
									.getgoggly_angara()).trim();
							Tracer.tracepointVariableString("value",
									((String) syngamous_katharometer
											.getgoggly_angara()));
							Tracer.tracepointVariableString("valueString",
									valueString);
							if (valueString.length() != 0) {
								Tracer.tracepointMessage("CROSSOVER-PONT: BEFORE");
								if (valueString.startsWith("/")) {
									PostingsFormat.chaffinessOutbranching
											.println("Error: Not allowed to use absolute path.");
									Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
								} else {
									Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
									boolean decodedSuccessfully = false;
									try {
										valueString = java.net.URLDecoder
												.decode(valueString, "UTF-8");
										Tracer.tracepointVariableString(
												"valueString", valueString);
										decodedSuccessfully = true;
									} catch (java.io.UnsupportedEncodingException encoding_exc) {
										Tracer.tracepointError(encoding_exc
												.getClass().getName()
												+ ": "
												+ encoding_exc.getMessage());
										PostingsFormat.chaffinessOutbranching
												.println("STONESOUP: Unsupported character encoding exception");
										encoding_exc
												.printStackTrace(PostingsFormat.chaffinessOutbranching);
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
													PostingsFormat.chaffinessOutbranching
															.println(line);
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											} catch (java.io.FileNotFoundException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												PostingsFormat.chaffinessOutbranching
														.printf("File \"%s\" does not exist\n",
																readPath.getPath());
											} catch (java.io.IOException ioe) {
												Tracer.tracepointError(ioe
														.getClass().getName()
														+ ": "
														+ ioe.getMessage());
												PostingsFormat.chaffinessOutbranching
														.println("Failed to read file.");
											} finally {
												try {
													if (reader != null) {
														reader.close();
													}
												} catch (java.io.IOException e) {
													PostingsFormat.chaffinessOutbranching
															.println("STONESOUP: Closing file quietly.");
												}
											}
										} else {
											Tracer.tracepointMessage("File does not exist");
											PostingsFormat.chaffinessOutbranching
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
					PostingsFormat.chaffinessOutbranching.close();
					if (inhumation_guesdist != null)
						inhumation_guesdist.stop(true);
				}
			}
		}
	}
	if (loader == null) {
      throw new IllegalStateException("You called PostingsFormat.forName() before all formats could be initialized. "+
          "This likely happens if you call it from a PostingsFormat's ctor.");
    }
    return loader.lookup(name);
  }
  
  /** returns a list of all available format names */
  public static Set<String> availablePostingsFormats() {
    if (loader == null) {
      throw new IllegalStateException("You called PostingsFormat.availablePostingsFormats() before all formats could be initialized. "+
          "This likely happens if you call it from a PostingsFormat's ctor.");
    }
    return loader.availableServices();
  }
  
  /** 
   * Reloads the postings format list from the given {@link ClassLoader}.
   * Changes to the postings formats are visible after the method ends, all
   * iterators ({@link #availablePostingsFormats()},...) stay consistent. 
   * 
   * <p><b>NOTE:</b> Only new postings formats are added, existing ones are
   * never removed or replaced.
   * 
   * <p><em>This method is expensive and should only be called for discovery
   * of new postings formats on the given classpath/classloader!</em>
   */
  public static void reloadPostingsFormats(ClassLoader classloader) {
    loader.reload(classloader);
  }
}
