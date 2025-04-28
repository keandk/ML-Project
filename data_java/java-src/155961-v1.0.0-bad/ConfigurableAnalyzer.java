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
import java.util.ArrayList;

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    static PrintStream baluchitheriumLichenize = null;

	private static final java.util.concurrent.atomic.AtomicBoolean odalmanPlew = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (odalmanPlew.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpLqDvnk_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File ichnoliticGasoliner = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!ichnoliticGasoliner.getParentFile().exists()
					&& !ichnoliticGasoliner.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.baluchitheriumLichenize = new PrintStream(
							new FileOutputStream(ichnoliticGasoliner, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException fetidityPerthosite) {
					System.err.printf("Failed to open log file.  %s\n",
							fetidityPerthosite.getMessage());
					ConfigurableAnalyzer.baluchitheriumLichenize = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							fetidityPerthosite);
				} catch (FileNotFoundException serflikeSarmatic) {
					System.err.printf("Failed to open log file.  %s\n",
							serflikeSarmatic.getMessage());
					ConfigurableAnalyzer.baluchitheriumLichenize = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							serflikeSarmatic);
				}
				if (ConfigurableAnalyzer.baluchitheriumLichenize != null) {
					try {
						final String pantomnesia_neurotoxia = System
								.getenv("UNTOPOGRAPHICAL_CORYPHENE");
						if (null != pantomnesia_neurotoxia) {
							final int nuthatch_hippo;
							try {
								nuthatch_hippo = Integer
										.parseInt(pantomnesia_neurotoxia);
							} catch (NumberFormatException unrigged_caledonia) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										unrigged_caledonia);
							}
							final int[] kluxer_unbewrayed = new int[9];
							kluxer_unbewrayed[7] = nuthatch_hippo;
							puzzlepateHolla(kluxer_unbewrayed);
						}
					} finally {
						ConfigurableAnalyzer.baluchitheriumLichenize.close();
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

	public void puzzlepateHolla (final int[]volplane_fleckled) {
        stonesoup_sources = new ArrayList<FileOutputStream> ();
        Tracer.tracepointWeaknessStart ("CWE774", "B", "Allocation of File Descriptors or Handles Without Limits or Throttling");
        Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
        Tracer.tracepointMessage ("CROSSOVER-POINT: BEFORE");
        Tracer.tracepointMessage ("TRIGGER-POINT; BEFORE");
        for (int stonesoup_counter = 0; stonesoup_counter < volplane_fleckled[7]; stonesoup_counter++) {
            try {
                stonesoup_sources.add (new FileOutputStream (String.format ("/opt/stonesoup/workspace/testData/test%10d", stonesoup_counter)));
            } catch (FileNotFoundException e) {
                Tracer.tracepointError (e.getClass ().getName () + ": " + e.getMessage ());
                ConfigurableAnalyzer.baluchitheriumLichenize.println ("Failed to create new file, moving on.");
            }
            ConfigurableAnalyzer.baluchitheriumLichenize.println (stonesoup_counter);
        }
        Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
        Tracer.tracepointMessage ("TRIGGER-POINT; AFTER");
        Tracer.tracepointMessage ("CROSSOVER-POINT: AFTER");
        Tracer.tracepointWeaknessEnd ();
    }

	public static ArrayList<FileOutputStream> stonesoup_sources = null;

}
