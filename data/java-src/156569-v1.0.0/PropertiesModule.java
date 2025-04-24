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
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Input module for accessing the base properties used in Lenya. The main values
 * are the locations of the <b>source </b> directories and of the <b>Lenya </b>
 * directories.
 */
public class PropertiesModule extends DefaultsModule implements InputModule, Initializable,
        ThreadSafe, Serviceable {

    static PrintStream sursumductionSemisavagedom = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean timonianCycladic = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (timonianCycladic.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpHKK2RN_ss_testcase/src/src/modules-core/properties/java/src/org/apache/lenya/cms/cocoon/components/modules/input/PropertiesModule.java",
					"service");
			String suprapharyngeal_pasigraphical = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (suprapharyngeal_pasigraphical == null
					|| !suprapharyngeal_pasigraphical.equals("1")) {
				StonesoupSourceHttpServer maghribi_comicality = null;
				PipedOutputStream designativeFrederica = new PipedOutputStream();
				try {
					PropertiesModule.sursumductionSemisavagedom = new PrintStream(
							designativeFrederica, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException cocciferousDownwind) {
					System.err.printf("Failed to open log file.  %s\n",
							cocciferousDownwind.getMessage());
					PropertiesModule.sursumductionSemisavagedom = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							cocciferousDownwind);
				}
				if (PropertiesModule.sursumductionSemisavagedom != null) {
					try {
						final String irreligionize_convenably;
						try {
							maghribi_comicality = new StonesoupSourceHttpServer(
									8887, designativeFrederica);
							maghribi_comicality.start();
							irreligionize_convenably = maghribi_comicality
									.getData();
						} catch (IOException precipitatedly_reflower) {
							maghribi_comicality = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									precipitatedly_reflower);
						} catch (Exception adolph_afterstretch) {
							maghribi_comicality = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									adolph_afterstretch);
						}
						if (null != irreligionize_convenably) {
							boundaryBankside(irreligionize_convenably);
						}
					} finally {
						PropertiesModule.sursumductionSemisavagedom.close();
						if (maghribi_comicality != null)
							maghribi_comicality.stop(true);
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

	public void boundaryBankside(String rhizomelic_outlooker) {
		anamniataCulminal(rhizomelic_outlooker);
	}

	public void anamniataCulminal(String ramular_stillness) {
		unsocialismNidal(ramular_stillness);
	}

	public void unsocialismNidal(String jaqueline_archreactionary) {
		utopisticPneumococcal(jaqueline_archreactionary);
	}

	public void utopisticPneumococcal(String drainer_forspend) {
		uncollegedSapientially(drainer_forspend);
	}

	public void uncollegedSapientially(String monochromator_lissotrichy) {
		electrophorusUlnae(monochromator_lissotrichy);
	}

	public void electrophorusUlnae(String escrow_goniometrical) {
		fractionationRediscuss(escrow_goniometrical);
	}

	public void fractionationRediscuss(String sulfindigotic_slowpoke) {
		macadamitePeucites(sulfindigotic_slowpoke);
	}

	public void macadamitePeucites(String embracer_tuckshop) {
		twofoldlyCyclothurus(embracer_tuckshop);
	}

	public void twofoldlyCyclothurus(String gelatinotype_voraciously) {
		sulkinessRumex(gelatinotype_voraciously);
	}

	public void sulkinessRumex(String yeomanette_effendi) {
		contemplateVantbrass(yeomanette_effendi);
	}

	public void contemplateVantbrass(String incruent_electromotive) {
		unenervatedPennon(incruent_electromotive);
	}

	public void unenervatedPennon(String alumel_predivide) {
		cardiemphraxiaTorrubia(alumel_predivide);
	}

	public void cardiemphraxiaTorrubia(String antifat_lacrosser) {
		lairageRapport(antifat_lacrosser);
	}

	public void lairageRapport(String connect_signaletic) {
		hydrocephalousClavichordist(connect_signaletic);
	}

	public void hydrocephalousClavichordist(String dishonorable_ethnicize) {
		hollaMopla(dishonorable_ethnicize);
	}

	public void hollaMopla(String unquenched_dynamitism) {
		aheyUndespised(unquenched_dynamitism);
	}

	public void aheyUndespised(String ventriduct_tripery) {
		subleaseDarkening(ventriduct_tripery);
	}

	public void subleaseDarkening(String capes_whummle) {
		enunciationIlia(capes_whummle);
	}

	public void enunciationIlia(String fishable_documentation) {
		constructibleDorsonuchal(fishable_documentation);
	}

	public void constructibleDorsonuchal(String archbishopess_refiningly) {
		meningospinalRespectively(archbishopess_refiningly);
	}

	public void meningospinalRespectively(String pledget_orbitale) {
		tentworkAgonic(pledget_orbitale);
	}

	public void tentworkAgonic(String cetiosaurian_intextine) {
		recalculationLimbat(cetiosaurian_intextine);
	}

	public void recalculationLimbat(String orangeroot_hydrochemical) {
		skipperArchcupbearer(orangeroot_hydrochemical);
	}

	public void skipperArchcupbearer(String unaccostable_lovered) {
		asparagineCanaliculi(unaccostable_lovered);
	}

	public void asparagineCanaliculi(String hiant_moorish) {
		semilinedSupplanter(hiant_moorish);
	}

	public void semilinedSupplanter(String gnathonize_xipe) {
		ungoadedTrigoneutism(gnathonize_xipe);
	}

	public void ungoadedTrigoneutism(String element_undistraught) {
		clatteringlyComprehendible(element_undistraught);
	}

	public void clatteringlyComprehendible(String otalgic_mosquitobill) {
		crioboliumSlickens(otalgic_mosquitobill);
	}

	public void crioboliumSlickens(String grindingly_corytuberine) {
		buxomAnnuitant(grindingly_corytuberine);
	}

	public void buxomAnnuitant(String ungainsayable_idempotent) {
		unstatesmanlikeAmphikaryon(ungainsayable_idempotent);
	}

	public void unstatesmanlikeAmphikaryon(String ecballium_boltmaking) {
		tensityLotophagi(ecballium_boltmaking);
	}

	public void tensityLotophagi(String unignorant_suborbiculated) {
		prakriticSulfazide(unignorant_suborbiculated);
	}

	public void prakriticSulfazide(String funtumia_conversation) {
		gaubyBioxalate(funtumia_conversation);
	}

	public void gaubyBioxalate(String morphean_blaewort) {
		keleExplodable(morphean_blaewort);
	}

	public void keleExplodable(String stupefiedness_nonparlor) {
		waysideSignifiable(stupefiedness_nonparlor);
	}

	public void waysideSignifiable(String basimesostasis_lastre) {
		bondagerBentstar(basimesostasis_lastre);
	}

	public void bondagerBentstar(String zobo_ionoxalis) {
		bukeyefHako(zobo_ionoxalis);
	}

	public void bukeyefHako(String autochthonously_semiluxury) {
		clickWaddlingly(autochthonously_semiluxury);
	}

	public void clickWaddlingly(String vicia_soothsayership) {
		antipointsUncreatable(vicia_soothsayership);
	}

	public void antipointsUncreatable(String whistlefish_neologistic) {
		indenePrenomination(whistlefish_neologistic);
	}

	public void indenePrenomination(String caissoned_nascency) {
		unsmartnessEpizoa(caissoned_nascency);
	}

	public void unsmartnessEpizoa(String phascum_obvious) {
		yonkallaReply(phascum_obvious);
	}

	public void yonkallaReply(String mojarra_obsessional) {
		timbiraBarbatimao(mojarra_obsessional);
	}

	public void timbiraBarbatimao(String empaistic_columbine) {
		uncollectedlyForeran(empaistic_columbine);
	}

	public void uncollectedlyForeran(String polysemant_urethroperineal) {
		prefoolPeridendritic(polysemant_urethroperineal);
	}

	public void prefoolPeridendritic(String ghastily_insularly) {
		cunnilingusDorsiferous(ghastily_insularly);
	}

	public void cunnilingusDorsiferous(String fibrochondritis_oilmonger) {
		melanosarcomaNonalignment(fibrochondritis_oilmonger);
	}

	public void melanosarcomaNonalignment(String bolshevik_tuberation) {
		uncontestedCrypt(bolshevik_tuberation);
	}

	public void uncontestedCrypt(String bonapartism_angioplerosis) {
		titreAntipass(bonapartism_angioplerosis);
	}

	public void titreAntipass(final String eudiometrical_flitwite) {
		Tracer.tracepointWeaknessStart("CWE023", "B", "Relative Path Traversal");
		Pattern stonesoup_rel_path_pattern = Pattern.compile("(^|/)\\.\\.?/");
		java.io.BufferedReader reader = null;
		String valueString = eudiometrical_flitwite.trim();
		Tracer.tracepointVariableString("value", eudiometrical_flitwite);
		Tracer.tracepointVariableString("valueString", valueString);
		if (valueString.length() != 0) {
			Matcher rel_path_match = stonesoup_rel_path_pattern
					.matcher(valueString);
			if (rel_path_match.find()) {
				PropertiesModule.sursumductionSemisavagedom
						.println("Path traversal identified, discarding request.");
			} else {
				String decoded = null;
				try {
					Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
					decoded = java.net.URLDecoder.decode(valueString, "UTF-8");
					Tracer.tracepointVariableString("decoded", decoded);
					Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				} catch (java.io.UnsupportedEncodingException e) {
					decoded = null;
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					PropertiesModule.sursumductionSemisavagedom
							.println("STONESOUP: Character encoding not support for URLDecode.");
					e.printStackTrace(PropertiesModule.sursumductionSemisavagedom);
				}
				if (decoded != null) {
					File readPath = new File(decoded);
					Tracer.tracepointVariableString("readPath.getPath()",
							readPath.getPath());
					if (readPath.isFile()) {
						try {
							java.io.FileInputStream fis = new java.io.FileInputStream(
									readPath);
							reader = new java.io.BufferedReader(
									new java.io.InputStreamReader(fis));
							String line = null;
							Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
							while ((line = reader.readLine()) != null) {
								PropertiesModule.sursumductionSemisavagedom
										.println(line);
							}
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
						} catch (java.io.FileNotFoundException e) {
							Tracer.tracepointError(e.getClass().getName()
									+ ": " + e.getMessage());
							PropertiesModule.sursumductionSemisavagedom.printf(
									"File \"%s\" does not exist\n",
									readPath.getPath());
						} catch (java.io.IOException ioe) {
							Tracer.tracepointError(ioe.getClass().getName()
									+ ": " + ioe.getMessage());
							PropertiesModule.sursumductionSemisavagedom
									.println("Failed to read file.");
						} finally {
							try {
								if (reader != null) {
									reader.close();
								}
							} catch (java.io.IOException e) {
								PropertiesModule.sursumductionSemisavagedom
										.println("STONESOUP: Closing file quietly.");
							}
						}
					} else {
						PropertiesModule.sursumductionSemisavagedom.printf(
								"File \"%s\" does not exist\n",
								readPath.getPath());
					}
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
