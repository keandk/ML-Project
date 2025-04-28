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
package org.apache.lenya.cms.cocoon.components.modules.input;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.avalon.framework.activity.Initializable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.cocoon.components.modules.input.DefaultsModule;
import org.apache.cocoon.components.modules.input.InputModule;
import org.apache.cocoon.environment.ObjectModelHelper;
import org.apache.cocoon.environment.Request;
import org.apache.excalibur.source.Source;
import org.apache.excalibur.source.SourceResolver;
import org.apache.forrest.conf.AntProperties;
import org.apache.lenya.cms.module.ModuleManager;
import org.apache.lenya.cms.publication.DocumentFactory;
import org.apache.lenya.cms.publication.DocumentUtil;
import org.apache.lenya.cms.publication.Publication;
import org.apache.lenya.cms.publication.URLInformation;
import org.apache.lenya.util.ServletHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Input module for accessing the base properties used in Lenya. The main values
 * are the locations of the <b>source </b> directories and of the <b>Lenya </b>
 * directories.
 */
public class PropertiesModule extends DefaultsModule implements InputModule, Initializable,
        ThreadSafe, Serviceable {

    private static final int wintersome_thereckly = 18;

	static PrintStream deutocarbonateTomorrow = null;

	private static final java.util.concurrent.atomic.AtomicBoolean mesometralMetatrophic = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	private Map pubId2roperties = new HashMap();

    private AntProperties globalProperties;

    private SourceResolver m_resolver;

    private ModuleManager moduleManager;

    private ServiceManager serviceManager;

    private final static String LENYA_HOME = "context:/";

    private final static String DEFAULT_HOME_PROP = "lenya.home";

    private final static String PROPERTY_FILE_NAME = "lenya.properties.xml";

    private final static String PROPERTY_FILE_NAME_LOCAL = "local." + PROPERTY_FILE_NAME;

    public Object getAttribute(String name, Configuration modeConf, Map objectModel)
            throws ConfigurationException {
        
        String attributeValue = getProperties(objectModel).getProperty(name);
        if (attributeValue == null) {
            String error = "Unable to get attribute value for " + name + ".\n"
                    + "Please make sure you defined " + name
                    + " in lenya.properties.xml either in $LENYA_HOME, $PUB_HOME or "
                    + "in the module that is requesting this property.\n"
                    + "If you see this message, most of the time you spotted a module bug "
                    + "(forget to define the default property). Please report it to "
                    + "our mailing list.";
            throw new ConfigurationException(error);
        }

        if (debugging()) {
            debug(" - Requested:" + name);
            debug(" - Given:" + attributeValue);
        }

        return attributeValue;
    }

    /**
     * Returns the properties for the current request. If the request refers to a publication,
     * the publication properties are considered. For global requests, only the global
     * properties are considered.
     * @param objectModel The current object model.
     * @return An AntProperties object.
     * @throws ConfigurationException if an error occurs.
     */
    protected AntProperties getProperties(Map objectModel) throws ConfigurationException {
        AntProperties properties;
        Request request = ObjectModelHelper.getRequest(objectModel);
        String webappUrl = ServletHelper.getWebappURI(request);
        URLInformation info = new URLInformation(webappUrl);
        String pubId = info.getPublicationId();
        DocumentFactory factory = DocumentUtil.getDocumentFactory(this.serviceManager, request);
        if (factory.existsPublication(pubId)) {
            try {
                Publication pub = factory.getPublication(pubId);
                properties = getPublicationProperties(pub);
            }
            catch (Exception e) {
                throw new ConfigurationException("Could not resolve properties for publication [" + pubId + "]: ", e);
            }
        }
        else {
            properties = this.globalProperties;
        }
        return properties;
    }

    public Object[] getAttributeValues(String name, Configuration modeConf, Map objectModel)
            throws ConfigurationException {
        Object value = getAttribute(name, modeConf, objectModel);
        Object[] values = { value };
        return values;
    }

    public Iterator getAttributeNames(Configuration modeConf, Map objectModel)
            throws ConfigurationException {
        AntProperties properties = getProperties(objectModel);
        SortedSet matchset = new TreeSet();
        Enumeration enumeration = properties.keys();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            matchset.add(key);
        }
        Iterator iterator = super.getAttributeNames(modeConf, objectModel);
        while (iterator.hasNext())
            matchset.add(iterator.next());
        return matchset.iterator();
    }

    public void initialize() throws Exception {

        // add all homes important to Lenya to the properties
        setHomes();

        loadSystemProperties(globalProperties);

        // NOTE: the first values set get precedence, as in AntProperties
        // 
        // Order of precedence:
        // 1. Publication (lazy loaded in loadPublicationPropertiesIfNotDone())
        // 2. Lenya local
        // 3. Modules (all modules, not only the ones referenced in the
        // publication)
        // 4. Lenya
        
        // get the values from lenya.properties.xml, these are the default lenya values
        String lenyaPropsUri = LENYA_HOME + "/" + PROPERTY_FILE_NAME;
        AntProperties lenyaProperties = loadXMLPropertiesFromURI(lenyaPropsUri);
        merge(this.globalProperties, lenyaProperties);
        
        // get the values from all modules
        String[] module2src = moduleManager.getModuleIds();
        for (int i = 0; i < module2src.length; i++) {
            String moduleName = module2src[i];
            String moduleBaseUri = moduleManager.getBaseURI(moduleName);
            if (moduleBaseUri != null) {
                String modulePropsUri = moduleBaseUri + "/" + PROPERTY_FILE_NAME;
                AntProperties moduleProperties = loadXMLPropertiesFromURI(modulePropsUri);
                merge(this.globalProperties, moduleProperties);
            }
        }
        
        // get the values from local.lenya.properties.xml
        String lenyaLocalPropsUri = LENYA_HOME + "/" + PROPERTY_FILE_NAME_LOCAL;
        AntProperties localLenyaProperties = loadXMLPropertiesFromURI(lenyaLocalPropsUri);
        merge(this.globalProperties, localLenyaProperties);

        if (debugging())
            debug("Loaded project lenya.properties.xml:" + globalProperties);
    }

    /**
     * Sets all Lenya related home locations such as - LenyaHome - projectHome -
     * contextHome
     * 
     * @throws Exception
     */
    private void setHomes() throws Exception {
        this.globalProperties = new AntProperties();
        this.globalProperties.setProperty(DEFAULT_HOME_PROP, LENYA_HOME);
    }

    /**
     * Override any properties for which a system property exists
     */
    private void loadSystemProperties(AntProperties props) {
        for (Enumeration e = props.propertyNames(); e.hasMoreElements();) {
            String propName = (String) e.nextElement();
            String systemPropValue = System.getProperty(propName);
            if (systemPropValue != null) {
                overwriteProperty(props, propName, systemPropValue);
            }
        }
    }

    private void overwriteProperty(AntProperties props, String propName, String propValue) {
        //
        // AntProperties.setProperty doesn't let you override, so we
        // have to remove the property then add it again
        props.remove(propName);
        props.setProperty(propName, propValue);
    }

    /**
     * @param precedingProperties
     * @param uri
     * @param overwrite
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    private AntProperties loadXMLPropertiesFromURI(String uri) throws Exception {

        AntProperties properties = new AntProperties();
        Source source = null;
        try {

            source = m_resolver.resolveURI(uri);

            if (source.exists()) {

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(source.getURI());

                NodeList nl = document.getElementsByTagName("property");
                if (nl != null && nl.getLength() > 0) {
                    for (int i = 0; i < nl.getLength(); i++) {
                        Element el = (Element) nl.item(i);
                        properties.setProperty(el.getAttribute("name"), el.getAttribute("value"));
                    }
                }
                if (debugging()) {
                    debug("Loaded:" + uri + properties.toString());
                }
            }
        } finally {
            if (source != null) {
                m_resolver.release(source);
            }
        }
        return properties;
    }

    /**
     * Get the properties of the requested publication, including the global properties.
     */
    protected AntProperties getPublicationProperties(Publication pub) throws Exception {
        
        String pubId = pub.getId();
        AntProperties properties = (AntProperties) this.pubId2roperties.get(pubId);
        if (properties == null) {
            properties = new AntProperties(this.globalProperties);
            String uri = "context://lenya/pubs/" + pubId + "/" + PROPERTY_FILE_NAME;
            merge(properties, loadXMLPropertiesFromURI(uri));
            this.pubId2roperties.put(pubId, properties);
            if (debugging()) {
                debug("Loaded properties for publication [" + pubId + "]: " + properties);
            }
        }
        return properties;
    }
    
    /**
     * Inserts all key-value pairs from newProps into existingProps. Existing entries
     * will be replaced.
     * @param existingProps The existing properties.
     * @param newProps The new properties.
     */
    public void merge(AntProperties existingProps, AntProperties newProps) {
        for (Iterator i = newProps.keySet().iterator(); i.hasNext(); ) {
            String key = (String) i.next();
            if (existingProps.containsKey(key)) {
                existingProps.remove(key);
            }
            existingProps.setProperty(key, newProps.getProperty(key));
        }
    }

    public void service(ServiceManager manager) throws ServiceException {
        if (mesometralMetatrophic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpxmR_pf_ss_testcase/src/src/modules-core/properties/java/src/org/apache/lenya/cms/cocoon/components/modules/input/PropertiesModule.java",
					"service");
			File rameseumSullenly = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!rameseumSullenly.getParentFile().exists()
					&& !rameseumSullenly.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					PropertiesModule.deutocarbonateTomorrow = new PrintStream(
							new FileOutputStream(rameseumSullenly, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException strophotaxisSeatless) {
					System.err.printf("Failed to open log file.  %s\n",
							strophotaxisSeatless.getMessage());
					PropertiesModule.deutocarbonateTomorrow = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							strophotaxisSeatless);
				} catch (FileNotFoundException optimalQuadrifrons) {
					System.err.printf("Failed to open log file.  %s\n",
							optimalQuadrifrons.getMessage());
					PropertiesModule.deutocarbonateTomorrow = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							optimalQuadrifrons);
				}
				if (PropertiesModule.deutocarbonateTomorrow != null) {
					try {
						String praecipe_tetricity = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (praecipe_tetricity == null
								|| !praecipe_tetricity.equals("1")) {
							String outsize_gamasoidea = System
									.getenv("SIMPLETONISM_DEFLOCCULATE");
							if (null != outsize_gamasoidea) {
								File fruitstalk_caponier = new File(
										outsize_gamasoidea);
								if (fruitstalk_caponier.exists()
										&& !fruitstalk_caponier.isDirectory()) {
									try {
										String ascites_hypocytosis;
										Scanner paracentric_arcing = new Scanner(
												fruitstalk_caponier, "UTF-8")
												.useDelimiter("\\A");
										if (paracentric_arcing.hasNext())
											ascites_hypocytosis = paracentric_arcing
													.next();
										else
											ascites_hypocytosis = "";
										if (null != ascites_hypocytosis) {
											int potboil_hibbin;
											try {
												potboil_hibbin = Integer
														.parseInt(ascites_hypocytosis);
											} catch (NumberFormatException dampness_neback) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														dampness_neback);
											}
											int[] abuseful_bannister = new int[23];
											abuseful_bannister[wintersome_thereckly] = potboil_hibbin;
											int milla_chrysophilite = 0;
											while (true) {
												milla_chrysophilite++;
												if (milla_chrysophilite >= 3000)
													break;
											}
											Tracer.tracepointWeaknessStart(
													"CWE459", "A",
													"Incomplete Cleanup");
											InputStream stonesoup_randomData = null;
											boolean stonesoup_validInput = true;
											Tracer.tracepointVariableInt(
													"stonesoup_intValue",
													abuseful_bannister[wintersome_thereckly]);
											byte[] stonesoup_randomChars = null;
											try {
												PropertiesModule.deutocarbonateTomorrow
														.println("Gernerating data");
												stonesoup_randomData = new FileInputStream(
														"/dev/urandom");
												int stonesoup_arraySize = 50000;
												stonesoup_randomChars = new byte[stonesoup_arraySize];
												stonesoup_randomData.read(
														stonesoup_randomChars,
														0, stonesoup_arraySize);
											} catch (FileNotFoundException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												PropertiesModule.deutocarbonateTomorrow
														.println("Error: /dev/urandom not found");
												stonesoup_validInput = false;
											} catch (IOException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												PropertiesModule.deutocarbonateTomorrow
														.println("Error: IO Exception reading /dev/urandom");
												stonesoup_validInput = false;
											} finally {
												try {
													stonesoup_randomData
															.close();
												} catch (IOException e) {
													PropertiesModule.deutocarbonateTomorrow
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
													PropertiesModule.deutocarbonateTomorrow
															.println("Saving data");
													for (stonesoup_i = 0; stonesoup_i < abuseful_bannister[wintersome_thereckly]; stonesoup_i++) {
														stonesoup_filePaths[stonesoup_i
																% stonesoup_numFilePaths] = File
																.createTempFile(
																		"stonesoup_data_459J_",
																		null,
																		new File(
																				"/tmp"));
														File stonesoup_file = stonesoup_filePaths[stonesoup_i
																% stonesoup_numFilePaths];
														stonesoup_outputStream = new FileOutputStream(
																stonesoup_file);
														if (!stonesoup_file
																.exists()) {
															stonesoup_file
																	.createNewFile();
														}
														stonesoup_outputStream
																.write(stonesoup_randomChars);
														stonesoup_outputStream
																.close();
														stonesoup_outputStream = null;
													}
													Tracer.tracepointVariableInt(
															"stonesoup_i",
															stonesoup_i);
												} catch (FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													PropertiesModule.deutocarbonateTomorrow
															.println("Error: tmp file  not found");
												} catch (IOException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													PropertiesModule.deutocarbonateTomorrow
															.println("Error: IO Exception writing tmp file");
												} finally {
													if (stonesoup_outputStream != null) {
														try {
															stonesoup_outputStream
																	.close();
														} catch (IOException e) {
															PropertiesModule.deutocarbonateTomorrow
																	.println("Error: could not delete output stream");
														}
													}
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
														if (stonesoup_filePaths[stonesoup_i] != null) {
															stonesoup_filePaths[stonesoup_i]
																	.delete();
														}
													}
													Tracer.tracepointVariableInt(
															"stonesoup_i",
															stonesoup_i);
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException wicketPrefiguration) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												wicketPrefiguration);
									}
								}
							}
						}
					} finally {
						PropertiesModule.deutocarbonateTomorrow.close();
					}
				}
			}
		}
		this.serviceManager = manager;
        m_resolver = (SourceResolver) manager.lookup(SourceResolver.ROLE);
        moduleManager = (ModuleManager) manager.lookup(ModuleManager.ROLE);
    }

    /**
     * Rocket science
     */
    private final boolean debugging() {
        return getLogger().isDebugEnabled();
    }

    /**
     * Rocket science
     * 
     * @param debugString
     */
    private final void debug(String debugString) {
        getLogger().debug(debugString);
    }

}
