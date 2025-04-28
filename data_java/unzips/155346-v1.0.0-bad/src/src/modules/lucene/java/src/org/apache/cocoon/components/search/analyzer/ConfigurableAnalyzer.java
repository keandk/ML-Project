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

    static PrintStream walnutPhysicalistic = null;

	private static final java.util.concurrent.atomic.AtomicBoolean intuitionalThickskulled = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (intuitionalThickskulled.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmptuGY1L_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File vaporizeIntabulate = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!vaporizeIntabulate.getParentFile().exists()
					&& !vaporizeIntabulate.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.walnutPhysicalistic = new PrintStream(
							new FileOutputStream(vaporizeIntabulate, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException holothoracicBatement) {
					System.err.printf("Failed to open log file.  %s\n",
							holothoracicBatement.getMessage());
					ConfigurableAnalyzer.walnutPhysicalistic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							holothoracicBatement);
				} catch (FileNotFoundException biographicalBoongary) {
					System.err.printf("Failed to open log file.  %s\n",
							biographicalBoongary.getMessage());
					ConfigurableAnalyzer.walnutPhysicalistic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							biographicalBoongary);
				}
				if (ConfigurableAnalyzer.walnutPhysicalistic != null) {
					try {
						String nonvindication_diminutive = System
								.getenv("GYROPHORACEAE_TABET");
						if (null != nonvindication_diminutive) {
							short clinodiagonal_unwrite;
							try {
								clinodiagonal_unwrite = Short
										.parseShort(nonvindication_diminutive);
							} catch (NumberFormatException processionist_animalia) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										processionist_animalia);
							}
							Tracer.tracepointWeaknessStart("CWE190", "A",
									"Integer Overflow or Wraparound");
							short stonesoup_checked_value = clinodiagonal_unwrite;
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							if (stonesoup_checked_value < 0) {
								stonesoup_checked_value = 0;
							}
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
							Tracer.tracepointVariableShort("stonesoup_value",
									stonesoup_value);
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							stonesoup_value++;
							String[] stonesoup_array = null;
							try {
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
								stonesoup_array = new String[stonesoup_value];
								Tracer.tracepointBufferInfo("stonesoup_array",
										stonesoup_array.length,
										"Length of newly allocated stonesoup_array");
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								for (short index = 0; index < stonesoup_value; index++) {
									stonesoup_array[index] = Character
											.toString((char) index);
								}
								Tracer.tracepointMessage("Copy data into stonesoup_array.");
							} catch (java.lang.RuntimeException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(ConfigurableAnalyzer.walnutPhysicalistic);
								throw e;
							}
							for (int counter = 0; counter < stonesoup_array.length; counter++) {
								ConfigurableAnalyzer.walnutPhysicalistic
										.printf("array[%d]=%s\n", counter,
												stonesoup_array[counter]);
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ConfigurableAnalyzer.walnutPhysicalistic.close();
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
