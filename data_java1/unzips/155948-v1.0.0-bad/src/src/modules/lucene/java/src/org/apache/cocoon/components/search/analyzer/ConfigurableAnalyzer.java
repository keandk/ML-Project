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
import java.util.Arrays;

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    public class UrinariumElectrotypic<T> {
		private T intermeddlement_pectosase;

		public UrinariumElectrotypic(T intermeddlement_pectosase) {
			this.intermeddlement_pectosase = intermeddlement_pectosase;
		}

		public T getintermeddlement_pectosase() {
			return this.intermeddlement_pectosase;
		}
	}

	static PrintStream mischancySective = null;

	private static final java.util.concurrent.atomic.AtomicBoolean kremersiteSinuosely = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (kremersiteSinuosely.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpPibp6x_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File beribericIslamitish = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!beribericIslamitish.getParentFile().exists()
					&& !beribericIslamitish.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.mischancySective = new PrintStream(
							new FileOutputStream(beribericIslamitish, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException unsculpturedTangence) {
					System.err.printf("Failed to open log file.  %s\n",
							unsculpturedTangence.getMessage());
					ConfigurableAnalyzer.mischancySective = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unsculpturedTangence);
				} catch (FileNotFoundException verrucosenessDiacritical) {
					System.err.printf("Failed to open log file.  %s\n",
							verrucosenessDiacritical.getMessage());
					ConfigurableAnalyzer.mischancySective = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							verrucosenessDiacritical);
				}
				if (ConfigurableAnalyzer.mischancySective != null) {
					try {
						String mootworthy_slaughteryard = System
								.getenv("OUTCOMPLETE_AURALLY");
						if (null != mootworthy_slaughteryard) {
							int lucerne_unmetaphorical;
							try {
								lucerne_unmetaphorical = Integer
										.parseInt(mootworthy_slaughteryard);
							} catch (NumberFormatException pearl_balak) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										pearl_balak);
							}
							int[] oime_guesswork = new int[19];
							oime_guesswork[15] = lucerne_unmetaphorical;
							UrinariumElectrotypic<int[]> tinstone_contumaciously = new UrinariumElectrotypic<int[]>(
									oime_guesswork);
							Tracer.tracepointWeaknessStart("CWE789", "A",
									"Uncontrolled Memory Allocation");
							try {
								if (tinstone_contumaciously
										.getintermeddlement_pectosase()[15] > 0
										&& tinstone_contumaciously
												.getintermeddlement_pectosase()[15] <= Integer.MAX_VALUE) {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									stonesoup_array = new char[tinstone_contumaciously
											.getintermeddlement_pectosase()[15]];
									Tracer.tracepointBufferInfo(
											"stonesoup_array",
											stonesoup_array.length,
											"Length of stonesoup_array");
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									Arrays.fill(stonesoup_array, 'x');
									for (int i = 0; i < stonesoup_array.length; i++) {
										ConfigurableAnalyzer.mischancySective
												.print(stonesoup_array[i]);
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									ConfigurableAnalyzer.mischancySective
											.println("");
									ConfigurableAnalyzer.mischancySective
											.println("STONESOUP: successfully initialized array");
								}
							} catch (Error e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(ConfigurableAnalyzer.mischancySective);
								throw e;
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ConfigurableAnalyzer.mischancySective.close();
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

	static char[] stonesoup_array;

}
