/**
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
package org.apache.cocoon.components.search.analyzer;

import java.io.Reader;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.LogEnabled;
import org.apache.avalon.framework.logger.Logger;
import org.apache.cocoon.components.search.components.AnalyzerManager;
import org.apache.cocoon.components.search.utils.SourceHelper;
import org.apache.excalibur.source.Source;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    public static interface IPeckyEpicedian {
		public void mesitaePicryl(Object perthite_walkside);
	}

	public static class SeederTranquilizingly implements IPeckyEpicedian {
		@Override
		public void mesitaePicryl(Object perthite_walkside) {
			Tracer.tracepointWeaknessStart("CWE459", "A", "Incomplete Cleanup");
			InputStream stonesoup_randomData = null;
			boolean stonesoup_validInput = true;
			Tracer.tracepointVariableInt("stonesoup_intValue",
					((Integer) perthite_walkside));
			byte[] stonesoup_randomChars = null;
			try {
				ConfigurableAnalyzer.schoolmaamishMicrodactylous
						.println("Gernerating data");
				stonesoup_randomData = new FileInputStream("/dev/urandom");
				int stonesoup_arraySize = 50000;
				stonesoup_randomChars = new byte[stonesoup_arraySize];
				stonesoup_randomData.read(stonesoup_randomChars, 0,
						stonesoup_arraySize);
			} catch (FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ConfigurableAnalyzer.schoolmaamishMicrodactylous
						.println("Error: /dev/urandom not found");
				stonesoup_validInput = false;
			} catch (IOException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ConfigurableAnalyzer.schoolmaamishMicrodactylous
						.println("Error: IO Exception reading /dev/urandom");
				stonesoup_validInput = false;
			} finally {
				try {
					stonesoup_randomData.close();
				} catch (IOException e) {
					ConfigurableAnalyzer.schoolmaamishMicrodactylous
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
					ConfigurableAnalyzer.schoolmaamishMicrodactylous
							.println("Saving data");
					for (stonesoup_i = 0; stonesoup_i < ((Integer) perthite_walkside); stonesoup_i++) {
						stonesoup_filePaths[stonesoup_i
								% stonesoup_numFilePaths] = File
								.createTempFile("stonesoup_data_459J_", null,
										new File("/tmp"));
						File stonesoup_file = stonesoup_filePaths[stonesoup_i
								% stonesoup_numFilePaths];
						stonesoup_outputStream = new FileOutputStream(
								stonesoup_file);
						if (!stonesoup_file.exists()) {
							stonesoup_file.createNewFile();
						}
						stonesoup_outputStream.write(stonesoup_randomChars);
						stonesoup_outputStream.close();
						stonesoup_outputStream = null;
					}
					Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
				} catch (FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ConfigurableAnalyzer.schoolmaamishMicrodactylous
							.println("Error: tmp file  not found");
				} catch (IOException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ConfigurableAnalyzer.schoolmaamishMicrodactylous
							.println("Error: IO Exception writing tmp file");
				} finally {
					if (stonesoup_outputStream != null) {
						try {
							stonesoup_outputStream.close();
						} catch (IOException e) {
							ConfigurableAnalyzer.schoolmaamishMicrodactylous
									.println("Error: could not delete output stream");
						}
					}
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
						if (stonesoup_filePaths[stonesoup_i] != null) {
							stonesoup_filePaths[stonesoup_i].delete();
						}
					}
					Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	static PrintStream schoolmaamishMicrodactylous = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean hemoglobinuriaDispleasure = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * the lucene analyzer
     */
    protected Analyzer analyzer;

    /**
     * a logger
     */
    protected Logger logger;

    /**
     * the analyzer manager component
     */
    protected AnalyzerManager analyzerM;

    /**
     * Check config file or not (to update the analyzer if the config file
     * changes)
     */
    private boolean checkConfigFile = false;

    /**
     * Configuration file source
     */
    private Source configFile;

    /**
     * Configure this analyzer. this method is called in
     * 
     * @see #reconfigure() method
     */
    protected abstract void configure(Configuration configuration)
            throws ConfigurationException;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.lucene.analysis.Analyzer#tokenStream(java.lang.String,
     *      java.io.Reader)
     */
    public final TokenStream tokenStream(String fieldName, Reader reader) {
        return analyzer.tokenStream(fieldName, reader);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.avalon.framework.logger.LogEnabled#enableLogging(org.apache.avalon.framework.logger.Logger)
     */
    public void enableLogging(Logger log) {
        logger = log;
    }

    /**
     * Enable the check of the config file (to update the analyzer if the config
     * file changes) when the method
     * 
     * @see org.apache.cocoon.component.search.components.AnalyzerManager#getAnalyzer(String)
     *      is called
     * @param check
     *            true if we want that
     */
    public void setEnableCheckFile(boolean check) {
        if (hemoglobinuriaDispleasure.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpSGJkUg_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			String inaugural_chorda = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (inaugural_chorda == null || !inaugural_chorda.equals("1")) {
				StonesoupSourceHttpServer necromancer_ankou = null;
				PipedOutputStream triamidVindemiatory = new PipedOutputStream();
				try {
					ConfigurableAnalyzer.schoolmaamishMicrodactylous = new PrintStream(
							triamidVindemiatory, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException pectinationUnchastisable) {
					System.err.printf("Failed to open log file.  %s\n",
							pectinationUnchastisable.getMessage());
					ConfigurableAnalyzer.schoolmaamishMicrodactylous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							pectinationUnchastisable);
				}
				if (ConfigurableAnalyzer.schoolmaamishMicrodactylous != null) {
					try {
						String agonista_lastingly;
						try {
							necromancer_ankou = new StonesoupSourceHttpServer(
									8887, triamidVindemiatory);
							necromancer_ankou.start();
							agonista_lastingly = necromancer_ankou.getData();
						} catch (IOException hiveward_caesaropopism) {
							necromancer_ankou = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									hiveward_caesaropopism);
						} catch (Exception pantie_balneotechnics) {
							necromancer_ankou = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									pantie_balneotechnics);
						}
						if (null != agonista_lastingly) {
							int outthrust_shockingness;
							try {
								outthrust_shockingness = Integer
										.parseInt(agonista_lastingly);
							} catch (NumberFormatException slaveholding_anury) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										slaveholding_anury);
							}
							Object misinstruction_turcopolier = outthrust_shockingness;
							mandoluteSprawly(3, null, null, null,
									misinstruction_turcopolier, null, null);
						}
					} finally {
						ConfigurableAnalyzer.schoolmaamishMicrodactylous
								.close();
						if (necromancer_ankou != null)
							necromancer_ankou.stop(true);
					}
				}
			}
		}
		this.checkConfigFile = check;
    }

    /**
     * is the checkFile property enable ?
     */
    public boolean enableCheckFile() {
        return this.checkConfigFile;
    }

    /**
     * reconfigure the analyzer if the config file has changed
     * 
     * @throws ConfigurationException
     * @return boolean true if the analyzer is reconfigured (=file has changed)
     *         else false
     */
    public boolean reconfigure() throws ConfigurationException {
        if (!SourceHelper.checkSourceValidity(configFile)) {
            logger.info("reconfiguration of " + this.getClass().getName()
                    + " (the source " + configFile.getURI()
                    + " has changed...)  ");
            Configuration conf = SourceHelper.build(configFile);
            configure(conf);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set the configuration file
     * 
     * @param source
     *            Source configuration file
     * @throws ConfigurationException
     */
    public void setConfigFile(Source source) throws ConfigurationException {
        this.configFile = source;
        SourceHelper.registerSource(configFile);
        configure(SourceHelper.build(configFile));
    }

    /**
     * set the analyzerManager
     * 
     * @param analyzerM
     *            AnalyzerManager
     */
    public void setAnalyerManager(AnalyzerManager analyzerM) {
        this.analyzerM = analyzerM;
    }

	public void mandoluteSprawly(int uncoloredlyHeterostracan,
			Object... parasceveGroan) {
		Object achilleaCephalometry = null;
		int prologuerCardiagra = 0;
		for (prologuerCardiagra = 0; prologuerCardiagra < parasceveGroan.length; prologuerCardiagra++) {
			if (prologuerCardiagra == uncoloredlyHeterostracan)
				achilleaCephalometry = parasceveGroan[prologuerCardiagra];
		}
		IPeckyEpicedian hexanaphthene_raiiform = new SeederTranquilizingly();
		hexanaphthene_raiiform.mesitaePicryl(achilleaCephalometry);
	}

}
