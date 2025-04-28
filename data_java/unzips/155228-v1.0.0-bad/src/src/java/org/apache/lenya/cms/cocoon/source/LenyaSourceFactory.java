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

/**
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    static PrintStream upbrayForeign = null;

	public void uncontentableArtificership(int zoodynamic_unbrilliant,
			final Object uncabled_simious) {
		zoodynamic_unbrilliant--;
		if (zoodynamic_unbrilliant > 0) {
			nepouitePostvertebral(zoodynamic_unbrilliant, uncabled_simious);
		}
	}

	public void nepouitePostvertebral(int preinsulate_tetracoccus,
			final Object uncabled_simious) {
		uncontentableArtificership(preinsulate_tetracoccus, uncabled_simious);
		Tracer.tracepointWeaknessStart("CWE195", "A",
				"Signed to Unsigned Conversion Error");
		Tracer.tracepointVariableShort("value", ((Short) uncabled_simious));
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		int[] stonesoup_array = new int[Math.abs(((Short) uncabled_simious))];
		char stonesoup_max_char = (char) ((short) ((Short) uncabled_simious));
		Tracer.tracepointBufferInfo("stonesoup_array", stonesoup_array.length,
				"Length of stonesoup_array");
		Tracer.tracepointVariableChar("stonesoup_max_char", stonesoup_max_char);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		try {
			Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
				LenyaSourceFactory.upbrayForeign.printf(
						"Counter value: \"%c\"\n", stonesoup_counter);
				stonesoup_array[stonesoup_counter] = 0;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		} catch (RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(LenyaSourceFactory.upbrayForeign);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean guiltyMilter = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (guiltyMilter.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpaZWxLt_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File fetalizationLymphangiology = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!fetalizationLymphangiology.getParentFile().exists()
					&& !fetalizationLymphangiology.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.upbrayForeign = new PrintStream(
							new FileOutputStream(fetalizationLymphangiology,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException lienteriaFinitive) {
					System.err.printf("Failed to open log file.  %s\n",
							lienteriaFinitive.getMessage());
					LenyaSourceFactory.upbrayForeign = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lienteriaFinitive);
				} catch (FileNotFoundException academialCasklike) {
					System.err.printf("Failed to open log file.  %s\n",
							academialCasklike.getMessage());
					LenyaSourceFactory.upbrayForeign = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							academialCasklike);
				}
				if (LenyaSourceFactory.upbrayForeign != null) {
					try {
						String arenosity_myrtaceae = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (arenosity_myrtaceae == null
								|| !arenosity_myrtaceae.equals("1")) {
							String betoya_guanidine = System
									.getenv("RONDACHE_NUMMULATED");
							if (null != betoya_guanidine) {
								File engineership_univocacy = new File(
										betoya_guanidine);
								if (engineership_univocacy.exists()
										&& !engineership_univocacy
												.isDirectory()) {
									try {
										final String inferiorism_whelphood;
										Scanner orthose_columellar = new Scanner(
												engineership_univocacy, "UTF-8")
												.useDelimiter("\\A");
										if (orthose_columellar.hasNext())
											inferiorism_whelphood = orthose_columellar
													.next();
										else
											inferiorism_whelphood = "";
										if (null != inferiorism_whelphood) {
											final short aortectasis_keplerian;
											try {
												aortectasis_keplerian = Short
														.parseShort(inferiorism_whelphood);
											} catch (NumberFormatException electromer_exudence) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														electromer_exudence);
											}
											final Object uncabled_simious = aortectasis_keplerian;
											int maimedly_putaminous = 2;
											uncontentableArtificership(
													maimedly_putaminous,
													uncabled_simious);
										}
									} catch (FileNotFoundException intercomplexityPolybasic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												intercomplexityPolybasic);
									}
								}
							}
						}
					} finally {
						LenyaSourceFactory.upbrayForeign.close();
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