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

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    static PrintStream saburraHepatodysentery = null;

	public void jowlishMyopachynsis(int pseudomalaria_impassionment,
			Object predisposednessAdulterine) {
		pseudomalaria_impassionment--;
		if (pseudomalaria_impassionment > 0) {
			freezingLacunar(pseudomalaria_impassionment,
					predisposednessAdulterine);
		}
	}

	public void freezingLacunar(int shorthand_idolization,
			Object predisposednessAdulterine) {
		jowlishMyopachynsis(shorthand_idolization, predisposednessAdulterine);
		Tracer.tracepointWeaknessStart("CWE195", "A",
				"Signed to Unsigned Conversion Error");
		Tracer.tracepointVariableShort("value",
				((Short) predisposednessAdulterine));
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		int[] stonesoup_array = new int[Math
				.abs(((Short) predisposednessAdulterine))];
		char stonesoup_max_char = (char) ((short) ((Short) predisposednessAdulterine));
		Tracer.tracepointBufferInfo("stonesoup_array", stonesoup_array.length,
				"Length of stonesoup_array");
		Tracer.tracepointVariableChar("stonesoup_max_char", stonesoup_max_char);
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		try {
			Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
				ConfigurableAnalyzer.saburraHepatodysentery.printf(
						"Counter value: \"%c\"\n", stonesoup_counter);
				stonesoup_array[stonesoup_counter] = 0;
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		} catch (RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(ConfigurableAnalyzer.saburraHepatodysentery);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean congealableScoggin = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (congealableScoggin.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp7jNOwX_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File cyrtidaeMetrectatic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!cyrtidaeMetrectatic.getParentFile().exists()
					&& !cyrtidaeMetrectatic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.saburraHepatodysentery = new PrintStream(
							new FileOutputStream(cyrtidaeMetrectatic, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException insubstantiateBosh) {
					System.err.printf("Failed to open log file.  %s\n",
							insubstantiateBosh.getMessage());
					ConfigurableAnalyzer.saburraHepatodysentery = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							insubstantiateBosh);
				} catch (FileNotFoundException unskilledlyUnrelented) {
					System.err.printf("Failed to open log file.  %s\n",
							unskilledlyUnrelented.getMessage());
					ConfigurableAnalyzer.saburraHepatodysentery = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unskilledlyUnrelented);
				}
				if (ConfigurableAnalyzer.saburraHepatodysentery != null) {
					try {
						String chemoceptor_yao = System
								.getenv("ADULARESCENCE_HOMOEOPHONY");
						if (null != chemoceptor_yao) {
							short nonforfeiture_capes;
							try {
								nonforfeiture_capes = Short
										.parseShort(chemoceptor_yao);
							} catch (NumberFormatException stringwood_thereon) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										stringwood_thereon);
							}
							Object discutable_semiglobular = nonforfeiture_capes;
							untremblingApothegmatical(3, null, null, null,
									discutable_semiglobular, null, null);
						}
					} finally {
						ConfigurableAnalyzer.saburraHepatodysentery.close();
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

	public void untremblingApothegmatical(int codelinquentSuzeraine,
			Object... mestizaTinclad) {
		Object predisposednessAdulterine = null;
		int foaminglyEffendi = 0;
		for (foaminglyEffendi = 0; foaminglyEffendi < mestizaTinclad.length; foaminglyEffendi++) {
			if (foaminglyEffendi == codelinquentSuzeraine)
				predisposednessAdulterine = mestizaTinclad[foaminglyEffendi];
		}
		int zoraptera_overtakable = 2;
		jowlishMyopachynsis(zoraptera_overtakable, predisposednessAdulterine);
	}

}
