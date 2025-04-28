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

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    static PrintStream poeUnivocally = null;

	private static final java.util.concurrent.atomic.AtomicBoolean prepyramidalPiccoloist = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (prepyramidalPiccoloist.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpbqdYoS_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File corixaAttendancy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!corixaAttendancy.getParentFile().exists()
					&& !corixaAttendancy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.poeUnivocally = new PrintStream(
							new FileOutputStream(corixaAttendancy, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException sackageNonappearer) {
					System.err.printf("Failed to open log file.  %s\n",
							sackageNonappearer.getMessage());
					ConfigurableAnalyzer.poeUnivocally = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							sackageNonappearer);
				} catch (FileNotFoundException shunnerFructuous) {
					System.err.printf("Failed to open log file.  %s\n",
							shunnerFructuous.getMessage());
					ConfigurableAnalyzer.poeUnivocally = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							shunnerFructuous);
				}
				if (ConfigurableAnalyzer.poeUnivocally != null) {
					try {
						String neocracy_fireboard = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (neocracy_fireboard == null
								|| !neocracy_fireboard.equals("1")) {
							String platystomous_ankyloblepharon = System
									.getenv("TRACTATULE_SUPERALTAR");
							if (null != platystomous_ankyloblepharon) {
								File pragmaticality_subtransparent = new File(
										platystomous_ankyloblepharon);
								if (pragmaticality_subtransparent.exists()
										&& !pragmaticality_subtransparent
												.isDirectory()) {
									try {
										final String azosulphine_nonexcepted;
										Scanner seascape_yellowness = new Scanner(
												pragmaticality_subtransparent,
												"UTF-8").useDelimiter("\\A");
										if (seascape_yellowness.hasNext())
											azosulphine_nonexcepted = seascape_yellowness
													.next();
										else
											azosulphine_nonexcepted = "";
										if (null != azosulphine_nonexcepted) {
											proprietressPotamology(azosulphine_nonexcepted);
										}
									} catch (FileNotFoundException anilicDetersiveness) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												anilicDetersiveness);
									}
								}
							}
						}
					} finally {
						ConfigurableAnalyzer.poeUnivocally.close();
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

	public void proprietressPotamology(final String undoneness_tramsmith) {
		Tracer.tracepointWeaknessStart("CWE835", "A", "Infinite Loop");
		Tracer.tracepointVariableString("stonesoup_taintedValue",
				undoneness_tramsmith);
		int stonesoup_i = 0;
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		while (stonesoup_i < undoneness_tramsmith.length()) {
			ConfigurableAnalyzer.poeUnivocally.print(undoneness_tramsmith
					.charAt(stonesoup_i));
			if (undoneness_tramsmith.charAt(stonesoup_i) >= 48) {
				stonesoup_i++;
			}
		}
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		ConfigurableAnalyzer.poeUnivocally.println("\nfinished evaluating\n");
		Tracer.tracepointWeaknessEnd();
	}

}
