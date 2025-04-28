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

/**
 * A factory for the "lenya" scheme (virtual protocol), which is used to resolve any src="lenya:..."
 * attributes in sitemaps. This implementation constructs the path to the source document from the
 * page envelope and delegates any further resolving to the "context" source resolver of Cocoon.
 * 
 * @version $Id: LenyaSourceFactory.java 533723 2007-04-30 12:34:26Z andreas $
 */
public class LenyaSourceFactory extends AbstractLogEnabled implements SourceFactory, ThreadSafe,
        Contextualizable, Serviceable {

    static PrintStream fallageTremellaceous = null;

	private static final java.util.concurrent.atomic.AtomicBoolean periodicalismMicroangstrom = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (periodicalismMicroangstrom.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmprmS5eB_ss_testcase/src/src/java/org/apache/lenya/cms/cocoon/source/LenyaSourceFactory.java",
					"service");
			File dysplasiaKuichua = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!dysplasiaKuichua.getParentFile().exists()
					&& !dysplasiaKuichua.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					LenyaSourceFactory.fallageTremellaceous = new PrintStream(
							new FileOutputStream(dysplasiaKuichua, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException luteciaManganeisen) {
					System.err.printf("Failed to open log file.  %s\n",
							luteciaManganeisen.getMessage());
					LenyaSourceFactory.fallageTremellaceous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							luteciaManganeisen);
				} catch (FileNotFoundException unrollmentKnead) {
					System.err.printf("Failed to open log file.  %s\n",
							unrollmentKnead.getMessage());
					LenyaSourceFactory.fallageTremellaceous = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unrollmentKnead);
				}
				if (LenyaSourceFactory.fallageTremellaceous != null) {
					try {
						String bolled_kusti = System
								.getenv("TYCHOPOTAMIC_SPANE");
						if (null != bolled_kusti) {
							String[] breck_twelfthtide = new String[22];
							breck_twelfthtide[18] = bolled_kusti;
							vacuateErythrorrhexis(3, null, null, null,
									breck_twelfthtide, null, null);
						}
					} finally {
						LenyaSourceFactory.fallageTremellaceous.close();
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

	public void vacuateErythrorrhexis(int grithbreachRegush,String[]... bygoneScoon){String[] privinessNonsecretarial=null;int tetralemmaFluorite=0;for (tetralemmaFluorite=0;tetralemmaFluorite < bygoneScoon.length;tetralemmaFluorite++){if (tetralemmaFluorite == grithbreachRegush)privinessNonsecretarial=bygoneScoon[tetralemmaFluorite];}boolean fitch_sumptuosity=false;guenon_chordata: for (int mongholian_hepar=0;mongholian_hepar < 10;mongholian_hepar++)for (int asself_foollike=0;asself_foollike < 10;asself_foollike++)if (mongholian_hepar * asself_foollike == 63){fitch_sumptuosity=true;break guenon_chordata;}Tracer.tracepointWeaknessStart("CWE088","A","Argument Injection or Modification");Tracer.tracepointVariableString("value",privinessNonsecretarial[18]);String stonesoup_proc_cmd="find . -iname ";Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");stonesoup_proc_cmd+=privinessNonsecretarial[18];Tracer.tracepointVariableString("stonesoup_proc_cmd",stonesoup_proc_cmd);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");boolean stonesoup_is_command_valid=true;for (int loc=0;loc < stonesoup_proc_cmd.length();loc++){if ((stonesoup_proc_cmd.charAt(loc) == ';') && stonesoup_proc_cmd.charAt(loc - 1) != '\\'){Tracer.tracepointMessage("Invalid command, shell escape detected.");LenyaSourceFactory.fallageTremellaceous.println("Invalid command, shell escape detected.");stonesoup_is_command_valid=false;}}if (stonesoup_is_command_valid){java.lang.ProcessBuilder stonesoup_proc_builder=new java.lang.ProcessBuilder("bash","-c",stonesoup_proc_cmd);stonesoup_proc_builder.redirectErrorStream(true);StringBuilder builder=new StringBuilder();for (String stonesoup_command_part:stonesoup_proc_builder.command()){builder.append(stonesoup_command_part);builder.append(" ");}Tracer.tracepointVariableString("stonesoup_proc_builder.command()",builder.toString());java.lang.Process stonesoup_proc=null;try {Tracer.tracepointMessage("Executing command.");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");stonesoup_proc=stonesoup_proc_builder.start();Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());LenyaSourceFactory.fallageTremellaceous.println("STONESOUP: Failed to open subprocess.");}if (stonesoup_proc != null){String stonesoup_proc_output_line=null;java.io.BufferedReader stonesoup_proc_reader=new java.io.BufferedReader(new java.io.InputStreamReader(stonesoup_proc.getInputStream()));try {Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");while ((stonesoup_proc_output_line=stonesoup_proc_reader.readLine()) != null){LenyaSourceFactory.fallageTremellaceous.println(stonesoup_proc_output_line);}} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());LenyaSourceFactory.fallageTremellaceous.println("STONESOUP: Error reading subprocess output stream.");}try {Tracer.tracepointMessage("Waiting for subprocess to complete.");int stonesoup_exit_code=stonesoup_proc.waitFor();if (stonesoup_exit_code != 0){Tracer.tracepointError("Subprocess returned a non-zero exit code.");Tracer.tracepointVariableInt("stonesoup_exit_code",stonesoup_exit_code);LenyaSourceFactory.fallageTremellaceous.printf("STONESOUP: Error in subprocess.	Return code: %d\n",stonesoup_exit_code);}} catch (java.lang.InterruptedException ie){Tracer.tracepointError(ie.getClass().getName() + ": "+ie.getMessage());LenyaSourceFactory.fallageTremellaceous.println("STONESOUP: Error waiting for subprocess.");}}}Tracer.tracepointWeaknessEnd();}
}