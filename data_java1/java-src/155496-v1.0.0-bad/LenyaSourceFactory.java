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
import java.util.ArrayList;

/**
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    static PrintStream contraveneUnaesthetical = null;

	private static final java.util.concurrent.atomic.AtomicBoolean clinologyMasterpiece = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (clinologyMasterpiece.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpPwnEaE_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File homecomerSteatopygous = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!homecomerSteatopygous.getParentFile().exists()
					&& !homecomerSteatopygous.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.contraveneUnaesthetical = new PrintStream(
							new FileOutputStream(homecomerSteatopygous, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException telemeterNavigerous) {
					System.err.printf("Failed to open log file.  %s\n",
							telemeterNavigerous.getMessage());
					LenyaSourceFactory.contraveneUnaesthetical = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							telemeterNavigerous);
				} catch (FileNotFoundException opdaliteHellbroth) {
					System.err.printf("Failed to open log file.  %s\n",
							opdaliteHellbroth.getMessage());
					LenyaSourceFactory.contraveneUnaesthetical = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							opdaliteHellbroth);
				}
				if (LenyaSourceFactory.contraveneUnaesthetical != null) {
					try {
						String piscina_inopinately = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (piscina_inopinately == null
								|| !piscina_inopinately.equals("1")) {
							String yokelish_tarsipedidae = System
									.getenv("VICARAGE_ALVEOLA");
							if (null != yokelish_tarsipedidae) {
								File satire_houseling = new File(
										yokelish_tarsipedidae);
								if (satire_houseling.exists()
										&& !satire_houseling.isDirectory()) {
									try {
										String fetterlock_unaflow;
										Scanner myriare_fortlet = new Scanner(
												satire_houseling, "UTF-8")
												.useDelimiter("\\A");
										if (myriare_fortlet.hasNext())
											fetterlock_unaflow = myriare_fortlet
													.next();
										else
											fetterlock_unaflow = "";
										if (null != fetterlock_unaflow) {
											int feedbox_uncleanse;
											try {
												feedbox_uncleanse = Integer
														.parseInt(fetterlock_unaflow);
											} catch (NumberFormatException deliverance_scripturient) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														deliverance_scripturient);
											}
											Object schnabel_hydrometra = feedbox_uncleanse;
											amphitheatralPlumbaginaceous(3,
													null, null, null,
													schnabel_hydrometra, null,
													null);
										}
									} catch (FileNotFoundException shiveringlyMyelinated) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												shiveringlyMyelinated);
									}
								}
							}
						}
					} finally {
						LenyaSourceFactory.contraveneUnaesthetical.close();
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

	public void amphitheatralPlumbaginaceous (int sexipolarImplication, Object... cerianthusOvertender) {
		Object occasivePotful = null;
		int megasporicBridebowl = 0;
		for (megasporicBridebowl = 0; megasporicBridebowl < cerianthusOvertender.length; megasporicBridebowl++) {
			if (megasporicBridebowl == sexipolarImplication)
				occasivePotful = cerianthusOvertender[megasporicBridebowl];
		}
		try {
			String epithecate_interlocal = System.getProperty ("os.name");
			if (null != epithecate_interlocal) {
				if (!epithecate_interlocal.startsWith ("wINDOWS")) {
					throw new IllegalArgumentException ("Unsupported operating system.");
				}
			}
		} catch (IllegalArgumentException geomalic_ichthulin) {

		} finally {
			stonesoup_sources = new ArrayList<FileOutputStream> ();
			Tracer.tracepointWeaknessStart ("CWE774", "B", "Allocation of File Descriptors or Handles Without Limits or Throttling");
			Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
			Tracer.tracepointMessage ("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage ("TRIGGER-POINT; BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) occasivePotful); stonesoup_counter++) {
				try {
					stonesoup_sources.add (new FileOutputStream (String.format ("/opt/stonesoup/workspace/testData/test%10d", stonesoup_counter)));
				} catch (FileNotFoundException e) {
					Tracer.tracepointError (e.getClass ().getName () + ": " + e.getMessage ());
					LenyaSourceFactory.contraveneUnaesthetical.println ("Failed to create new file, moving on.");
				}
				LenyaSourceFactory.contraveneUnaesthetical.println (stonesoup_counter);
			}
			Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
			Tracer.tracepointMessage ("TRIGGER-POINT; AFTER");
			Tracer.tracepointMessage ("CROSSOVER-POINT: AFTER");
			Tracer.tracepointWeaknessEnd ();
		}
	}

	public static ArrayList<FileOutputStream> stonesoup_sources = null;
}
