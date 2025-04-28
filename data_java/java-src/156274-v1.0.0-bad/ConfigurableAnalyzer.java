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
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.IOException;

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    private static final int quinamine_enchytraeid = 12;

	static PrintStream unimitatedRereeve = null;

	private static final java.util.concurrent.atomic.AtomicBoolean untrustworthilyPreoccupancy = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (untrustworthilyPreoccupancy.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpGqS6eU_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File wouldestRoughstring = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!wouldestRoughstring.getParentFile().exists()
					&& !wouldestRoughstring.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.unimitatedRereeve = new PrintStream(
							new FileOutputStream(wouldestRoughstring, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException finaleMonograptidae) {
					System.err.printf("Failed to open log file.  %s\n",
							finaleMonograptidae.getMessage());
					ConfigurableAnalyzer.unimitatedRereeve = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							finaleMonograptidae);
				} catch (FileNotFoundException implicativelySangei) {
					System.err.printf("Failed to open log file.  %s\n",
							implicativelySangei.getMessage());
					ConfigurableAnalyzer.unimitatedRereeve = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							implicativelySangei);
				}
				if (ConfigurableAnalyzer.unimitatedRereeve != null) {
					try {
						String uncrowned_balaenid = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (uncrowned_balaenid == null
								|| !uncrowned_balaenid.equals("1")) {
							String chirosophist_coloenteritis = System
									.getenv("OVERSWIFT_SPARELESS");
							if (null != chirosophist_coloenteritis) {
								File nodiferous_unthrashed = new File(
										chirosophist_coloenteritis);
								if (nodiferous_unthrashed.exists()
										&& !nodiferous_unthrashed.isDirectory()) {
									try {
										String retemper_unfringe;
										Scanner lutetia_chackler = new Scanner(
												nodiferous_unthrashed, "UTF-8")
												.useDelimiter("\\A");
										if (lutetia_chackler.hasNext())
											retemper_unfringe = lutetia_chackler
													.next();
										else
											retemper_unfringe = "";
										if (null != retemper_unfringe) {
											String[] extemporal_trigla = new String[8];
											extemporal_trigla[6] = retemper_unfringe;
											String[][] umatilla_raphides = new String[24][];
											umatilla_raphides[quinamine_enchytraeid] = extemporal_trigla;
											int hemp_justifying = 0;
											while (true) {
												hemp_justifying++;
												if (hemp_justifying >= 3000)
													break;
											}
											Tracer.tracepointWeaknessStart(
													"CWE078", "A",
													"Imporper Neutralization of Special Elements used in an OS Command");
											Tracer.tracepointVariableString(
													"value",
													umatilla_raphides[quinamine_enchytraeid][6]);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											String stonesoup_proc_cmd = "ls "
													+ umatilla_raphides[quinamine_enchytraeid][6];
											Tracer.tracepointVariableString(
													"stonesoup_proc_cmd",
													stonesoup_proc_cmd);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											java.lang.ProcessBuilder stonesoup_proc_builder = new java.lang.ProcessBuilder(
													"bash", "-c",
													stonesoup_proc_cmd);
											stonesoup_proc_builder
													.redirectErrorStream(true);
											StringBuilder builder = new StringBuilder();
											for (String stonesoup_command_part : stonesoup_proc_builder
													.command()) {
												builder.append(stonesoup_command_part);
												builder.append(" ");
											}
											Tracer.tracepointVariableString(
													"stonesoup_proc_builder.command()",
													builder.toString());
											java.lang.Process stonesoup_proc = null;
											try {
												Tracer.tracepointMessage("Executing command.");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												stonesoup_proc = stonesoup_proc_builder
														.start();
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											} catch (IOException ioe) {
												Tracer.tracepointError(ioe
														.getClass().getName()
														+ ": "
														+ ioe.getMessage());
												ConfigurableAnalyzer.unimitatedRereeve
														.println("STONESOUP: Failed to open subprocess.");
											}
											if (stonesoup_proc != null) {
												String stonesoup_proc_output_line = null;
												java.io.BufferedReader stonesoup_proc_reader = new java.io.BufferedReader(
														new java.io.InputStreamReader(
																stonesoup_proc
																		.getInputStream()));
												try {
													Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");
													while ((stonesoup_proc_output_line = stonesoup_proc_reader
															.readLine()) != null) {
														ConfigurableAnalyzer.unimitatedRereeve
																.println(stonesoup_proc_output_line);
													}
												} catch (IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													ConfigurableAnalyzer.unimitatedRereeve
															.println("STONESOUP: Error reading subprocess output stream.");
												}
												try {
													Tracer.tracepointMessage("Waiting for process to complete.");
													int stonesoup_exit_code = stonesoup_proc
															.waitFor();
													if (stonesoup_exit_code != 0) {
														Tracer.tracepointError("Error in subprocess.");
														Tracer.tracepointVariableInt(
																"stonesoup_exit_code",
																stonesoup_exit_code);
														ConfigurableAnalyzer.unimitatedRereeve
																.printf("STONESOUP: Error in subprocess.	Return code: %d\n",
																		stonesoup_exit_code);
													}
												} catch (java.lang.InterruptedException ie) {
													Tracer.tracepointError(ie
															.getClass()
															.getName()
															+ ": "
															+ ie.getMessage());
													ConfigurableAnalyzer.unimitatedRereeve
															.println("STONESOUP: Error waiting for subprocess.");
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException unbrewedCausable) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												unbrewedCausable);
									}
								}
							}
						}
					} finally {
						ConfigurableAnalyzer.unimitatedRereeve.close();
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

}
