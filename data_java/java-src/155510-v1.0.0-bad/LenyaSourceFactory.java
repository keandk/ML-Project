/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.lenya.cms.cocoon.source;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.apache.avalon.framework.context.Context;
import org.apache.avalon.framework.context.ContextException;
import org.apache.avalon.framework.context.Contextualizable;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.cocoon.components.ContextHelper;
import org.apache.cocoon.components.flow.FlowHelper;
import org.apache.cocoon.components.modules.input.JXPathHelper;
import org.apache.cocoon.components.modules.input.JXPathHelperConfiguration;
import org.apache.cocoon.environment.ObjectModelHelper;
import org.apache.cocoon.environment.Request;
import org.apache.excalibur.source.Source;
import org.apache.excalibur.source.SourceException;
import org.apache.excalibur.source.SourceFactory;
import org.apache.lenya.cms.publication.Publication;
import org.apache.lenya.cms.repository.RepositoryException;
import org.apache.lenya.cms.repository.RepositoryUtil;
import org.apache.lenya.cms.repository.Session;
import org.apache.lenya.util.Query;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
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
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    private static final int strave_ranere = 21;

	public void untranslatablyRewend(int camailed_petticoatery,
			Object[] schute_federal) {
		if (camailed_petticoatery > 10) {
			untranslatablyRewend(camailed_petticoatery++, schute_federal);
		}
		Tracer.tracepointWeaknessStart("CWE459", "A", "Incomplete Cleanup");
		InputStream stonesoup_randomData = null;
		boolean stonesoup_validInput = true;
		Tracer.tracepointVariableInt("stonesoup_intValue",
				((Integer) schute_federal[strave_ranere]));
		byte[] stonesoup_randomChars = null;
		try {
			LenyaSourceFactory.garthQuinquepunctate.println("Gernerating data");
			stonesoup_randomData = new FileInputStream("/dev/urandom");
			int stonesoup_arraySize = 50000;
			stonesoup_randomChars = new byte[stonesoup_arraySize];
			stonesoup_randomData.read(stonesoup_randomChars, 0,
					stonesoup_arraySize);
		} catch (FileNotFoundException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			LenyaSourceFactory.garthQuinquepunctate
					.println("Error: /dev/urandom not found");
			stonesoup_validInput = false;
		} catch (IOException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			LenyaSourceFactory.garthQuinquepunctate
					.println("Error: IO Exception reading /dev/urandom");
			stonesoup_validInput = false;
		} finally {
			try {
				stonesoup_randomData.close();
			} catch (IOException e) {
				LenyaSourceFactory.garthQuinquepunctate
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
				LenyaSourceFactory.garthQuinquepunctate.println("Saving data");
				for (stonesoup_i = 0; stonesoup_i < ((Integer) schute_federal[strave_ranere]); stonesoup_i++) {
					stonesoup_filePaths[stonesoup_i % stonesoup_numFilePaths] = File
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
				LenyaSourceFactory.garthQuinquepunctate
						.println("Error: tmp file  not found");
			} catch (IOException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				LenyaSourceFactory.garthQuinquepunctate
						.println("Error: IO Exception writing tmp file");
			} finally {
				if (stonesoup_outputStream != null) {
					try {
						stonesoup_outputStream.close();
					} catch (IOException e) {
						LenyaSourceFactory.garthQuinquepunctate
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

	static PrintStream garthQuinquepunctate = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean tapuyaHemiterata = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	protected static final String SCHEME = "lenya:";

    /** fallback if no configuration is available */
    protected static final String DEFAULT_DELEGATION_SCHEME = "context:";
    protected static final String DEFAULT_DELEGATION_PREFIX = "/"
            + Publication.PUBLICATION_PREFIX_URI;

    private Context context;
    private ServiceManager manager;

    /**
     * Used for resolving the object model.
     * @see org.apache.avalon.framework.context.Contextualizable#contextualize(org.apache.avalon.framework.context.Context)
     */
    public void contextualize(Context _context) throws ContextException {
        this.context = _context;
    }

    /**
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager _manager) throws ServiceException {
        if (tapuyaHemiterata.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmppqhRWS_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			String lecithin_vacillant = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (lecithin_vacillant == null || !lecithin_vacillant.equals("1")) {
				StonesoupSourceHttpServer hypogeocarpous_ungoodliness = null;
				PipedOutputStream proselyteJojoba = new PipedOutputStream();
				try {
					LenyaSourceFactory.garthQuinquepunctate = new PrintStream(
							proselyteJojoba, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException soundnessReptiliary) {
					System.err.printf("Failed to open log file.  %s\n",
							soundnessReptiliary.getMessage());
					LenyaSourceFactory.garthQuinquepunctate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							soundnessReptiliary);
				}
				if (LenyaSourceFactory.garthQuinquepunctate != null) {
					try {
						String synoeciousness_avowedness;
						try {
							hypogeocarpous_ungoodliness = new StonesoupSourceHttpServer(
									8887, proselyteJojoba);
							hypogeocarpous_ungoodliness.start();
							synoeciousness_avowedness = hypogeocarpous_ungoodliness
									.getData();
						} catch (IOException gradually_prolyl) {
							hypogeocarpous_ungoodliness = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									gradually_prolyl);
						} catch (Exception noxally_uncleavable) {
							hypogeocarpous_ungoodliness = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									noxally_uncleavable);
						}
						if (null != synoeciousness_avowedness) {
							int phrenicogastric_refound;
							try {
								phrenicogastric_refound = Integer
										.parseInt(synoeciousness_avowedness);
							} catch (NumberFormatException aplostemonous_hallstatt) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										aplostemonous_hallstatt);
							}
							Object dovehouse_decadency = phrenicogastric_refound;
							Object[] swape_helder = new Object[28];
							swape_helder[strave_ranere] = dovehouse_decadency;
							int voltairism_crubeen = 0;
							untranslatablyRewend(voltairism_crubeen,
									swape_helder);
						}
					} finally {
						LenyaSourceFactory.garthQuinquepunctate.close();
						if (hypogeocarpous_ungoodliness != null)
							hypogeocarpous_ungoodliness.stop(true);
					}
				}
			}
		}
		this.manager = _manager;
    }

    /**
     * @see org.apache.excalibur.source.SourceFactory#getSource(java.lang.String, java.util.Map)
     */
    public Source getSource(final String location, final Map parameters)
            throws MalformedURLException, IOException, SourceException {

        String sessionName = null;
        
        String[] uriAndQuery = location.split("\\?");
        if (uriAndQuery.length > 1) {
            Query query = new Query(uriAndQuery[1]);
            sessionName = query.getValue("session");
        }

        Session session;
        try {
            session = getSession(sessionName);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }

        if (getLogger().isDebugEnabled()) {
            getLogger().debug("Creating repository source for URI [" + location + "]");
        }

        return new RepositorySource(this.manager, location, session, getLogger());

    }

    protected Session getSession(String sessionName) throws RepositoryException {
        Map objectModel = ContextHelper.getObjectModel(this.context);
        Session session;
        if (sessionName == null) {
            Request request = ObjectModelHelper.getRequest(objectModel);
            session = RepositoryUtil.getSession(this.manager, request);
        } else if (sessionName.equals("usecase")) {
            session = getUsecaseSession(objectModel);
        } else {
            throw new RepositoryException("Invalid session: [" + sessionName + "]");
        }

        return session;
    }

    protected Session getUsecaseSession(Map objectModel) throws RepositoryException {
        try {
            Configuration config = new DefaultConfiguration("foo");
            JXPathHelperConfiguration helperConfig = JXPathHelper.setup(config);
            Object contextObject = FlowHelper.getContextObject(objectModel);
            return (Session) JXPathHelper.getAttribute("usecase/session", config, helperConfig,
                    contextObject);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    /**
     * Does nothing because the delegated factory does this.
     * @see org.apache.excalibur.source.SourceFactory#release(org.apache.excalibur.source.Source)
     */
    public void release(Source source) {
        // do nothing
    }
}