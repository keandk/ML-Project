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

    public static interface ILesseeOrphandom {
		public void woolmanPseudofeminine(short[] reneague_abolitionism);
	}

	public static class RubleKraal implements ILesseeOrphandom {
		@Override
		public void woolmanPseudofeminine(short[] reneague_abolitionism) {
			Tracer.tracepointWeaknessStart("CWE194", "A",
					"Unexpected Sign Extension");
			short stonesoup_array_size = reneague_abolitionism[9];
			Tracer.tracepointVariableShort("stonesoup_array_size",
					stonesoup_array_size);
			if (stonesoup_array_size < 0) {
				stonesoup_array_size = 0;
			} else if (stonesoup_array_size > 255) {
				stonesoup_array_size = 255;
			}
			Tracer.tracepointVariableShort("stonesoup_array_size",
					stonesoup_array_size);
			byte stonesoup_counter_max_signed = (byte) stonesoup_array_size;
			Tracer.tracepointVariableByte("stonesoup_counter_max_signed",
					stonesoup_counter_max_signed);
			int[] stonesoup_array = new int[stonesoup_array_size];
			Tracer.tracepointBufferInfo("stonesoup_array",
					stonesoup_array.length, "Length of stonesoup_array");
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			char stonesoup_counter_max = (char) stonesoup_counter_max_signed;
			Tracer.tracepointVariableChar("stonesoup_counter_max",
					stonesoup_counter_max);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			try {
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (char counter = 0; counter < stonesoup_counter_max; counter++) {
					stonesoup_array[counter] = 1;
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			} catch (java.lang.RuntimeException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				e.printStackTrace(ConfigurableAnalyzer.romanceishUndenounced);
				throw e;
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

	static PrintStream romanceishUndenounced = null;

	private static final java.util.concurrent.atomic.AtomicBoolean psychesthesiaMorphinomaniac = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (psychesthesiaMorphinomaniac.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpVIfueZ_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File comminativeFavorableness = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!comminativeFavorableness.getParentFile().exists()
					&& !comminativeFavorableness.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.romanceishUndenounced = new PrintStream(
							new FileOutputStream(comminativeFavorableness,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException polyglotwiseStatism) {
					System.err.printf("Failed to open log file.  %s\n",
							polyglotwiseStatism.getMessage());
					ConfigurableAnalyzer.romanceishUndenounced = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							polyglotwiseStatism);
				} catch (FileNotFoundException uncerementedUnspiteful) {
					System.err.printf("Failed to open log file.  %s\n",
							uncerementedUnspiteful.getMessage());
					ConfigurableAnalyzer.romanceishUndenounced = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							uncerementedUnspiteful);
				}
				if (ConfigurableAnalyzer.romanceishUndenounced != null) {
					try {
						String burg_cladocarpous = System
								.getenv("UNSONSY_MOUTHISHLY");
						if (null != burg_cladocarpous) {
							short pullmanize_compressedly;
							try {
								pullmanize_compressedly = Short
										.parseShort(burg_cladocarpous);
							} catch (NumberFormatException sterculiaceous_thrivingness) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										sterculiaceous_thrivingness);
							}
							short[] homomallous_phyllodium = new short[26];
							homomallous_phyllodium[9] = pullmanize_compressedly;
							ILesseeOrphandom metastasize_odically = new RubleKraal();
							metastasize_odically
									.woolmanPseudofeminine(homomallous_phyllodium);
						}
					} finally {
						ConfigurableAnalyzer.romanceishUndenounced.close();
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
