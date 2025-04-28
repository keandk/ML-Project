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
  public static class InvulnerablyNontaxability<T> {
		private T anthropopathic_geomorphogeny;

		public InvulnerablyNontaxability(T anthropopathic_geomorphogeny) {
			this.anthropopathic_geomorphogeny = anthropopathic_geomorphogeny;
		}

		public T getanthropopathic_geomorphogeny() {
			return this.anthropopathic_geomorphogeny;
		}
	}

	public static void mourneressConcernedly(int uncurtain_sorehearted,
			InvulnerablyNontaxability<Object> ewelease_hawk) {
		if (uncurtain_sorehearted > 10) {
			mourneressConcernedly(uncurtain_sorehearted++, ewelease_hawk);
		}
		Tracer.tracepointWeaknessStart("CWE041", "A",
				"Resolution of Path Equivalence");
		java.io.BufferedReader reader = null;
		String valueString = ((String) ewelease_hawk
				.getanthropopathic_geomorphogeny()).trim();
		Tracer.tracepointVariableString("value",
				((String) ewelease_hawk.getanthropopathic_geomorphogeny()));
		Tracer.tracepointVariableString("valueString", valueString);
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		if (valueString.length() != 0 && valueString.startsWith("/etc/")) {
			SPIClassIterator.tentfulOctacolic
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
						SPIClassIterator.tentfulOctacolic.println(line);
					}
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (java.io.FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					SPIClassIterator.tentfulOctacolic.printf(
							"File \"%s\" does not exist\n", readPath.getPath());
				} catch (java.io.IOException ioe) {
					Tracer.tracepointError(ioe.getClass().getName() + ": "
							+ ioe.getMessage());
					SPIClassIterator.tentfulOctacolic
							.println("Failed to read file.");
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (java.io.IOException e) {
						SPIClassIterator.tentfulOctacolic
								.println("STONESOUP: Closing file quietly.");
					}
				}
			} else {
				Tracer.tracepointMessage("File doesn't exist");
				SPIClassIterator.tentfulOctacolic.printf(
						"File \"%s\" does not exist\n", readPath.getPath());
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream tentfulOctacolic = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean sesquisulphideRepromulgate = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (sesquisulphideRepromulgate.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpZmxOBY_ss_testcase/src/core/src/java/org/apache/lucene/util/SPIClassIterator.java",
				"isParentClassLoader");
		String synodalist_insequent = System
				.getenv("STONESOUP_DISABLE_WEAKNESS");
		if (synodalist_insequent == null || !synodalist_insequent.equals("1")) {
			StonesoupSourceHttpServer helenium_compendent = null;
			PipedOutputStream phoronomicallyLazuline = new PipedOutputStream();
			try {
				SPIClassIterator.tentfulOctacolic = new PrintStream(
						phoronomicallyLazuline, true, "ISO-8859-1");
			} catch (UnsupportedEncodingException alnascharismPalinal) {
				System.err.printf("Failed to open log file.  %s\n",
						alnascharismPalinal.getMessage());
				SPIClassIterator.tentfulOctacolic = null;
				throw new RuntimeException(
						"STONESOUP: Failed to create piped print stream.",
						alnascharismPalinal);
			}
			if (SPIClassIterator.tentfulOctacolic != null) {
				try {
					String aeronaut_insensateness;
					try {
						helenium_compendent = new StonesoupSourceHttpServer(
								8887, phoronomicallyLazuline);
						helenium_compendent.start();
						aeronaut_insensateness = helenium_compendent.getData();
					} catch (IOException fungal_valeur) {
						helenium_compendent = null;
						throw new RuntimeException(
								"STONESOUP: Failed to start HTTP server.",
								fungal_valeur);
					} catch (Exception cottoneer_semivegetable) {
						helenium_compendent = null;
						throw new RuntimeException(
								"STONESOUP: Unknown error with HTTP server.",
								cottoneer_semivegetable);
					}
					if (null != aeronaut_insensateness) {
						Object swung_wagonmaking = aeronaut_insensateness;
						InvulnerablyNontaxability<Object> squabbler_muckthrift = new InvulnerablyNontaxability<Object>(
								swung_wagonmaking);
						int divalence_nonperishable = 0;
						mourneressConcernedly(divalence_nonperishable,
								squabbler_muckthrift);
					}
				} finally {
					SPIClassIterator.tentfulOctacolic.close();
					if (helenium_compendent != null)
						helenium_compendent.stop(true);
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
