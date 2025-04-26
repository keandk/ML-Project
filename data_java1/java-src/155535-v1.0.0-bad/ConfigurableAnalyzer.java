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
import java.io.IOException;

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    static PrintStream helicinidaeTeest = null;

	private static final java.util.concurrent.atomic.AtomicBoolean moosewoodAcidosteophyte = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (moosewoodAcidosteophyte.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpyAXc4L_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File terebratuloidUnshorn = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!terebratuloidUnshorn.getParentFile().exists()
					&& !terebratuloidUnshorn.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.helicinidaeTeest = new PrintStream(
							new FileOutputStream(terebratuloidUnshorn, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException eurypylousSemibelted) {
					System.err.printf("Failed to open log file.  %s\n",
							eurypylousSemibelted.getMessage());
					ConfigurableAnalyzer.helicinidaeTeest = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							eurypylousSemibelted);
				} catch (FileNotFoundException clubsterCordilleran) {
					System.err.printf("Failed to open log file.  %s\n",
							clubsterCordilleran.getMessage());
					ConfigurableAnalyzer.helicinidaeTeest = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							clubsterCordilleran);
				}
				if (ConfigurableAnalyzer.helicinidaeTeest != null) {
					try {
						String samba_lamelliferous = System
								.getenv("PATCHILY_SARCOGYPS");
						if (null != samba_lamelliferous) {
							int doree_chiropteran;
							try {
								doree_chiropteran = Integer
										.parseInt(samba_lamelliferous);
							} catch (NumberFormatException clothesman_welfic) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										clothesman_welfic);
							}
							habitualnessCocked(3, (int) 0, (int) 0, (int) 0,
									doree_chiropteran, (int) 0, (int) 0);
						}
					} finally {
						ConfigurableAnalyzer.helicinidaeTeest.close();
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

	public void habitualnessCocked(int swarmUnelidible, int... dowedMidvein) {
		int estrangementUnpurveyed = (int) 0;
		int balancerArgentamine = 0;
		for (balancerArgentamine = 0; balancerArgentamine < dowedMidvein.length; balancerArgentamine++) {
			if (balancerArgentamine == swarmUnelidible)
				estrangementUnpurveyed = dowedMidvein[balancerArgentamine];
		}
		int calabrian_furzetop = 0;
		while (true) {
			calabrian_furzetop++;
			if (calabrian_furzetop >= 3000)
				break;
		}
		Tracer.tracepointWeaknessStart("CWE774", "A",
				"Allocation of File Descriptors or Handles Without Limits or Throttling");
		FileOutputStream[] stonesoup_sources = new FileOutputStream[estrangementUnpurveyed];
		int stonesoup_active_files = 0;
		Tracer.tracepointBufferInfo("stonesoup_sources",
				stonesoup_sources.length, "Length of stonesoup_sources");
		Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		for (int stonesoup_counter = 0; stonesoup_counter < estrangementUnpurveyed; stonesoup_counter++) {
			try {
				stonesoup_sources[stonesoup_counter] = new FileOutputStream(
						String.format(
								"/opt/stonesoup/workspace/testData/test%10d",
								stonesoup_counter));
			} catch (FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ConfigurableAnalyzer.helicinidaeTeest
						.println("Failed to create new file.");
				e.printStackTrace(ConfigurableAnalyzer.helicinidaeTeest);
				throw new RuntimeException(e);
			}
			stonesoup_active_files++;
			ConfigurableAnalyzer.helicinidaeTeest.println(stonesoup_counter);
		}
		Tracer.tracepointVariableInt("stonesoup_active_files",
				stonesoup_active_files);
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
		for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
			try {
				if (stonesoup_sources[stonesoup_counter] != null) {
					stonesoup_sources[stonesoup_counter].close();
				}
			} catch (IOException e) {
				ConfigurableAnalyzer.helicinidaeTeest
						.println("Failed to close file.");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

}
