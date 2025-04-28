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

/**
 * Helper class for loading SPI classes from classpath (META-INF files).
 * This is a light impl of {@link java.util.ServiceLoader} but is guaranteed to
 * be bug-free regarding classpath order and does not instantiate or initialize
 * the classes found.
 *
 * @lucene.internal
 */
public final class SPIClassIterator<S> implements Iterator<Class<? extends S>> {
  public static class ChromatioideaeRhizopodan<T> {
		private T acold_keratocele;

		public ChromatioideaeRhizopodan(T acold_keratocele) {
			this.acold_keratocele = acold_keratocele;
		}

		public T getacold_keratocele() {
			return this.acold_keratocele;
		}
	}

	static PrintStream nematicTelecryptograph = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean gonydealBronchiarctia = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (gonydealBronchiarctia.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpIo9zbM_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		String felis_tauted = System.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (felis_tauted == null || !felis_tauted.equals("1")) {
			StonesoupSourceHttpServer sceptropherous_pewing = null;
			PipedOutputStream phytelephasMand = new PipedOutputStream();
			try {
				SPIClassIterator.nematicTelecryptograph = new PrintStream(
						phytelephasMand, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException anklongBuddhist) {
				System.err.printf("Failed to open log file.  %s\n",
						anklongBuddhist.getMessage());
				SPIClassIterator.nematicTelecryptograph = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						anklongBuddhist);
			}
			if (SPIClassIterator.nematicTelecryptograph != null) {
				try {
					String nonperceptual_fooster;
					try {
						sceptropherous_pewing = new StonesoupSourceHttpServer(
								8887, phytelephasMand);
						sceptropherous_pewing.start();
						nonperceptual_fooster = sceptropherous_pewing.getData();
					} catch (IOException peakish_fleech) {
						sceptropherous_pewing = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								peakish_fleech);
					} catch (Exception snowslip_unmasquerade) {
						sceptropherous_pewing = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								snowslip_unmasquerade);
					}
					if (null != nonperceptual_fooster) {
						String[] marcan_cervicoaxillary = new String[14];
						marcan_cervicoaxillary[5] = nonperceptual_fooster;
						ChromatioideaeRhizopodan<String[]> variocoupler_remission = new ChromatioideaeRhizopodan<String[]>(
								marcan_cervicoaxillary);
						Tracer.tracepointWeaknessStart("CWE036", "A",
								"Absolute Path Traversal");
						java.io.BufferedReader reader = null;
						String valueString = variocoupler_remission
								.getacold_keratocele()[5].trim();
						Tracer.tracepointVariableString("value",
								variocoupler_remission.getacold_keratocele()[5]);
						Tracer.tracepointVariableString("valueString",
								valueString);
						if (valueString.length() != 0) {
							Tracer.tracepointMessage("CROSSOVER-PONT: BEFORE");
							if (valueString.startsWith("/")) {
								SPIClassIterator.nematicTelecryptograph
										.println("Error: Not allowed to use absolute path.");
								Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
							} else {
								Tracer.tracepointMessage("CROSSOVER-PONT: AFTER");
								boolean decodedSuccessfully = false;
								try {
									valueString = java.net.URLDecoder.decode(
											valueString, "UTF-8");
									Tracer.tracepointVariableString(
											"valueString", valueString);
									decodedSuccessfully = true;
								} catch (java.io.UnsupportedEncodingException encoding_exc) {
									Tracer.tracepointError(encoding_exc
											.getClass().getName()
											+ ": "
											+ encoding_exc.getMessage());
									SPIClassIterator.nematicTelecryptograph
											.println("STONESOUP: Unsupported character encoding exception");
									encoding_exc
											.printStackTrace(SPIClassIterator.nematicTelecryptograph);
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
											while ((line = reader.readLine()) != null) {
												SPIClassIterator.nematicTelecryptograph
														.println(line);
											}
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
										} catch (java.io.FileNotFoundException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											SPIClassIterator.nematicTelecryptograph
													.printf("File \"%s\" does not exist\n",
															readPath.getPath());
										} catch (java.io.IOException ioe) {
											Tracer.tracepointError(ioe
													.getClass().getName()
													+ ": " + ioe.getMessage());
											SPIClassIterator.nematicTelecryptograph
													.println("Failed to read file.");
										} finally {
											try {
												if (reader != null) {
													reader.close();
												}
											} catch (java.io.IOException e) {
												SPIClassIterator.nematicTelecryptograph
														.println("STONESOUP: Closing file quietly.");
											}
										}
									} else {
										Tracer.tracepointMessage("File does not exist");
										SPIClassIterator.nematicTelecryptograph
												.printf("File \"%s\" does not exist\n",
														readPath.getPath());
									}
								}
							}
						}
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					SPIClassIterator.nematicTelecryptograph.close();
					if (sceptropherous_pewing != null)
						sceptropherous_pewing.stop(true);
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
  
}
