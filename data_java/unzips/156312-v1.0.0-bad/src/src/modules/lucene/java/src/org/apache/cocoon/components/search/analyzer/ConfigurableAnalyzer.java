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

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    static PrintStream alimenterThundercloud = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean girlfullySarcasm = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (girlfullySarcasm.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpJZ7EQd_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			String parine_bicycler = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (parine_bicycler == null || !parine_bicycler.equals("1")) {
				StonesoupSourceHttpServer sclerotinial_secretly = null;
				PipedOutputStream appendotomeSynecdoche = new PipedOutputStream();
				try {
					ConfigurableAnalyzer.alimenterThundercloud = new PrintStream(
							appendotomeSynecdoche, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException resorcinePerpend) {
					System.err.printf("Failed to open log file.  %s\n",
							resorcinePerpend.getMessage());
					ConfigurableAnalyzer.alimenterThundercloud = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							resorcinePerpend);
				}
				if (ConfigurableAnalyzer.alimenterThundercloud != null) {
					try {
						String sterno_thence;
						try {
							sclerotinial_secretly = new StonesoupSourceHttpServer(
									8887, appendotomeSynecdoche);
							sclerotinial_secretly.start();
							sterno_thence = sclerotinial_secretly.getData();
						} catch (IOException pawing_deuteroscopic) {
							sclerotinial_secretly = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									pawing_deuteroscopic);
						} catch (Exception nonstriped_sou) {
							sclerotinial_secretly = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									nonstriped_sou);
						}
						if (null != sterno_thence) {
							Object incasement_painstaking = sterno_thence;
							sextennialStreke(incasement_painstaking);
						}
					} finally {
						ConfigurableAnalyzer.alimenterThundercloud.close();
						if (sclerotinial_secretly != null)
							sclerotinial_secretly.stop(true);
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

	public void sextennialStreke(Object monorhyme_washtray) {
		ascendinglyRamequin(monorhyme_washtray);
	}

	public void ascendinglyRamequin(Object dysphoria_boviform) {
		raptoriousSlicht(dysphoria_boviform);
	}

	public void raptoriousSlicht(Object mossful_gastropexy) {
		hotchpotchlyYapped(mossful_gastropexy);
	}

	public void hotchpotchlyYapped(Object bewhig_fanmaker) {
		codicilicExarchist(bewhig_fanmaker);
	}

	public void codicilicExarchist(Object milligramage_vitrage) {
		engulfmentInterpterygoid(milligramage_vitrage);
	}

	public void engulfmentInterpterygoid(Object excusal_tautologize) {
		tapeProvability(excusal_tautologize);
	}

	public void tapeProvability(Object bettongia_ammophila) {
		oxazoleArrowleaf(bettongia_ammophila);
	}

	public void oxazoleArrowleaf(Object precorrectly_sublime) {
		actiniformCentistere(precorrectly_sublime);
	}

	public void actiniformCentistere(Object metagaster_furciform) {
		cystophorePropulsory(metagaster_furciform);
	}

	public void cystophorePropulsory(Object presatisfaction_rackett) {
		capitonidaeUnfleeting(presatisfaction_rackett);
	}

	public void capitonidaeUnfleeting(Object rebrandish_microstylous) {
		malvaceousDionysiacal(rebrandish_microstylous);
	}

	public void malvaceousDionysiacal(Object helplessly_inquiline) {
		tichorrhineMaximus(helplessly_inquiline);
	}

	public void tichorrhineMaximus(Object unbeginning_preferee) {
		ringingCounterterm(unbeginning_preferee);
	}

	public void ringingCounterterm(Object red_polyscopic) {
		pouringlyMonetite(red_polyscopic);
	}

	public void pouringlyMonetite(Object cloisterliness_vacillating) {
		unarmoredShadetail(cloisterliness_vacillating);
	}

	public void unarmoredShadetail(Object muntingia_petrarchistic) {
		deschampsiaRewrap(muntingia_petrarchistic);
	}

	public void deschampsiaRewrap(Object trepanning_nasalism) {
		gasolieryGhostflower(trepanning_nasalism);
	}

	public void gasolieryGhostflower(Object xanthate_milliamp) {
		scarabeeCreaturely(xanthate_milliamp);
	}

	public void scarabeeCreaturely(Object velardenite_pelides) {
		nisanUrachus(velardenite_pelides);
	}

	public void nisanUrachus(Object nummulites_backdown) {
		rekillDriftweed(nummulites_backdown);
	}

	public void rekillDriftweed(Object abnaki_rediscuss) {
		hydrofluoridUnharnessed(abnaki_rediscuss);
	}

	public void hydrofluoridUnharnessed(Object metonymically_radiographic) {
		intermeddlementWayside(metonymically_radiographic);
	}

	public void intermeddlementWayside(Object tibia_triconch) {
		pictoricalControllability(tibia_triconch);
	}

	public void pictoricalControllability(Object theopneusted_presymphonic) {
		phobosPalomino(theopneusted_presymphonic);
	}

	public void phobosPalomino(Object flavorful_occipitoatloid) {
		piperidineChylify(flavorful_occipitoatloid);
	}

	public void piperidineChylify(Object posterioristic_colza) {
		uremicInquiline(posterioristic_colza);
	}

	public void uremicInquiline(Object prenomination_hellroot) {
		olynthianSnow(prenomination_hellroot);
	}

	public void olynthianSnow(Object tease_benightment) {
		spermatothecaOutwaste(tease_benightment);
	}

	public void spermatothecaOutwaste(Object urbicolae_prodeportation) {
		parameseGrumblingly(urbicolae_prodeportation);
	}

	public void parameseGrumblingly(Object mojarra_truculent) {
		hildegardeQuercitannic(mojarra_truculent);
	}

	public void hildegardeQuercitannic(Object coalternative_orthoxylene) {
		shoverSaponaceousness(coalternative_orthoxylene);
	}

	public void shoverSaponaceousness(Object poliorcetics_transportative) {
		heterogamicUngazing(poliorcetics_transportative);
	}

	public void heterogamicUngazing(Object brandling_ungenteelness) {
		enticefulUmbrine(brandling_ungenteelness);
	}

	public void enticefulUmbrine(Object maximist_phemie) {
		homeoidalReobtainment(maximist_phemie);
	}

	public void homeoidalReobtainment(Object scientifical_pierrotic) {
		sprawlinglyDeny(scientifical_pierrotic);
	}

	public void sprawlinglyDeny(Object yellowbird_metopion) {
		metateAvizandum(yellowbird_metopion);
	}

	public void metateAvizandum(Object punctated_reimmigrant) {
		mystacoceteIdiophanism(punctated_reimmigrant);
	}

	public void mystacoceteIdiophanism(Object metrophlebitis_figuredly) {
		methylolUranography(metrophlebitis_figuredly);
	}

	public void methylolUranography(Object unofficialdom_retinoscope) {
		interocularArchivist(unofficialdom_retinoscope);
	}

	public void interocularArchivist(Object pinnipedian_semishaft) {
		mescalismApeak(pinnipedian_semishaft);
	}

	public void mescalismApeak(Object jejunal_uppishness) {
		stigmatismCain(jejunal_uppishness);
	}

	public void stigmatismCain(Object laboulbenia_scripulum) {
		multilightedTribunitive(laboulbenia_scripulum);
	}

	public void multilightedTribunitive(Object epigraphic_seleucian) {
		genteelizeCharmingness(epigraphic_seleucian);
	}

	public void genteelizeCharmingness(Object brilliantine_analysis) {
		octoadErythrinus(brilliantine_analysis);
	}

	public void octoadErythrinus(Object batement_obvolution) {
		twentyThreat(batement_obvolution);
	}

	public void twentyThreat(Object provostal_monistic) {
		afterwisdomPolyuric(provostal_monistic);
	}

	public void afterwisdomPolyuric(Object oligotokous_prepotent) {
		fetalismChesterlite(oligotokous_prepotent);
	}

	public void fetalismChesterlite(Object homology_gasiform) {
		ichnologyStinginess(homology_gasiform);
	}

	public void ichnologyStinginess(Object mofussil_decenniad) {
		misphraseVerminiferous(mofussil_decenniad);
	}

	public void misphraseVerminiferous(Object lymphopenia_ergostat){Tracer.tracepointWeaknessStart("CWE088","A","Argument Injection or Modification");Tracer.tracepointVariableString("value",((String)lymphopenia_ergostat));String stonesoup_proc_cmd="find . -iname ";Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");stonesoup_proc_cmd+=((String)lymphopenia_ergostat);Tracer.tracepointVariableString("stonesoup_proc_cmd",stonesoup_proc_cmd);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");boolean stonesoup_is_command_valid=true;for (int loc=0;loc < stonesoup_proc_cmd.length();loc++){if ((stonesoup_proc_cmd.charAt(loc) == ';') && stonesoup_proc_cmd.charAt(loc - 1) != '\\'){Tracer.tracepointMessage("Invalid command, shell escape detected.");ConfigurableAnalyzer.alimenterThundercloud.println("Invalid command, shell escape detected.");stonesoup_is_command_valid=false;}}if (stonesoup_is_command_valid){java.lang.ProcessBuilder stonesoup_proc_builder=new java.lang.ProcessBuilder("bash","-c",stonesoup_proc_cmd);stonesoup_proc_builder.redirectErrorStream(true);StringBuilder builder=new StringBuilder();for (String stonesoup_command_part:stonesoup_proc_builder.command()){builder.append(stonesoup_command_part);builder.append(" ");}Tracer.tracepointVariableString("stonesoup_proc_builder.command()",builder.toString());java.lang.Process stonesoup_proc=null;try {Tracer.tracepointMessage("Executing command.");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");stonesoup_proc=stonesoup_proc_builder.start();Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());ConfigurableAnalyzer.alimenterThundercloud.println("STONESOUP: Failed to open subprocess.");}if (stonesoup_proc != null){String stonesoup_proc_output_line=null;java.io.BufferedReader stonesoup_proc_reader=new java.io.BufferedReader(new java.io.InputStreamReader(stonesoup_proc.getInputStream()));try {Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");while ((stonesoup_proc_output_line=stonesoup_proc_reader.readLine()) != null){ConfigurableAnalyzer.alimenterThundercloud.println(stonesoup_proc_output_line);}} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());ConfigurableAnalyzer.alimenterThundercloud.println("STONESOUP: Error reading subprocess output stream.");}try {Tracer.tracepointMessage("Waiting for subprocess to complete.");int stonesoup_exit_code=stonesoup_proc.waitFor();if (stonesoup_exit_code != 0){Tracer.tracepointError("Subprocess returned a non-zero exit code.");Tracer.tracepointVariableInt("stonesoup_exit_code",stonesoup_exit_code);ConfigurableAnalyzer.alimenterThundercloud.printf("STONESOUP: Error in subprocess.	Return code: %d\n",stonesoup_exit_code);}} catch (java.lang.InterruptedException ie){Tracer.tracepointError(ie.getClass().getName() + ": "+ie.getMessage());ConfigurableAnalyzer.alimenterThundercloud.println("STONESOUP: Error waiting for subprocess.");}}}Tracer.tracepointWeaknessEnd();}

}
