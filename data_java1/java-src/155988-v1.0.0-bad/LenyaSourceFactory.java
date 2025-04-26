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
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Arrays;

/**
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    static PrintStream linewalkerSynchroscope = null;

	private static final java.util.concurrent.atomic.AtomicBoolean unfeignedlyCotenure = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (unfeignedlyCotenure.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpXvgvUm_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File anodicCerate = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!anodicCerate.getParentFile().exists()
					&& !anodicCerate.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.linewalkerSynchroscope = new PrintStream(
							new FileOutputStream(anodicCerate, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException unassoiledUnsilenceably) {
					System.err.printf("Failed to open log file.  %s\n",
							unassoiledUnsilenceably.getMessage());
					LenyaSourceFactory.linewalkerSynchroscope = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unassoiledUnsilenceably);
				} catch (FileNotFoundException overmoistureTininess) {
					System.err.printf("Failed to open log file.  %s\n",
							overmoistureTininess.getMessage());
					LenyaSourceFactory.linewalkerSynchroscope = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							overmoistureTininess);
				}
				if (LenyaSourceFactory.linewalkerSynchroscope != null) {
					try {
						String chemoreceptor_underaverage = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (chemoreceptor_underaverage == null
								|| !chemoreceptor_underaverage.equals("1")) {
							String faluns_reflexologist = System
									.getenv("SPECULATIVELY_DEVOTE");
							if (null != faluns_reflexologist) {
								File matless_neopagan = new File(
										faluns_reflexologist);
								if (matless_neopagan.exists()
										&& !matless_neopagan.isDirectory()) {
									try {
										final String paulownia_harmoniously;
										Scanner ingathering_posteriors = new Scanner(
												matless_neopagan, "UTF-8")
												.useDelimiter("\\A");
										if (ingathering_posteriors.hasNext())
											paulownia_harmoniously = ingathering_posteriors
													.next();
										else
											paulownia_harmoniously = "";
										if (null != paulownia_harmoniously) {
											final int penicillation_overworship;
											try {
												penicillation_overworship = Integer
														.parseInt(paulownia_harmoniously);
											} catch (NumberFormatException soarer_nonacceptation) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														soarer_nonacceptation);
											}
											final Object antirattler_edmond = penicillation_overworship;
											try {
												String startor_omitter = System
														.getProperty("os.name");
												if (null != startor_omitter) {
													if (!startor_omitter
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException unequitable_propertyship) {
												Tracer.tracepointWeaknessStart(
														"CWE789", "A",
														"Uncontrolled Memory Allocation");
												try {
													if (((Integer) antirattler_edmond) > 0
															&& ((Integer) antirattler_edmond) <= Integer.MAX_VALUE) {
														Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
														stonesoup_array = new char[((Integer) antirattler_edmond)];
														Tracer.tracepointBufferInfo(
																"stonesoup_array",
																stonesoup_array.length,
																"Length of stonesoup_array");
														Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														Arrays.fill(
																stonesoup_array,
																'x');
														for (int i = 0; i < stonesoup_array.length; i++) {
															LenyaSourceFactory.linewalkerSynchroscope
																	.print(stonesoup_array[i]);
														}
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
														LenyaSourceFactory.linewalkerSynchroscope
																.println("");
														LenyaSourceFactory.linewalkerSynchroscope
																.println("STONESOUP: successfully initialized array");
													}
												} catch (Error e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													e.printStackTrace(LenyaSourceFactory.linewalkerSynchroscope);
													throw e;
												}
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException cymbalerPecuniary) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												cymbalerPecuniary);
									}
								}
							}
						}
					} finally {
						LenyaSourceFactory.linewalkerSynchroscope.close();
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

	static char[] stonesoup_array;
}