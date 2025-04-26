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

    private static final int costar_archimime = 8;

	static PrintStream riddelMiscalculation = null;

	private static final java.util.concurrent.atomic.AtomicBoolean pristodusBatodendron = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (pristodusBatodendron.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpfCCDPe_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File harigaldsPodesta = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!harigaldsPodesta.getParentFile().exists()
					&& !harigaldsPodesta.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.riddelMiscalculation = new PrintStream(
							new FileOutputStream(harigaldsPodesta, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException otostealHypochloric) {
					System.err.printf("Failed to open log file.  %s\n",
							otostealHypochloric.getMessage());
					ConfigurableAnalyzer.riddelMiscalculation = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							otostealHypochloric);
				} catch (FileNotFoundException dismissiblePrevascular) {
					System.err.printf("Failed to open log file.  %s\n",
							dismissiblePrevascular.getMessage());
					ConfigurableAnalyzer.riddelMiscalculation = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							dismissiblePrevascular);
				}
				if (ConfigurableAnalyzer.riddelMiscalculation != null) {
					try {
						String misopolemical_nikolai = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (misopolemical_nikolai == null
								|| !misopolemical_nikolai.equals("1")) {
							String teretifolious_pedelion = System
									.getenv("SCOTSWOMAN_KAYVAN");
							if (null != teretifolious_pedelion) {
								File undermade_toeboard = new File(
										teretifolious_pedelion);
								if (undermade_toeboard.exists()
										&& !undermade_toeboard.isDirectory()) {
									try {
										String unevenly_signalman;
										Scanner becarpet_parsony = new Scanner(
												undermade_toeboard, "UTF-8")
												.useDelimiter("\\A");
										if (becarpet_parsony.hasNext())
											unevenly_signalman = becarpet_parsony
													.next();
										else
											unevenly_signalman = "";
										if (null != unevenly_signalman) {
											char punchboard_sycones;
											try {
												punchboard_sycones = unevenly_signalman
														.charAt(0);
											} catch (IndexOutOfBoundsException danseuse_farcetta) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														danseuse_farcetta);
											}
											char[] eucryphia_scuta = new char[20];
											eucryphia_scuta[costar_archimime] = punchboard_sycones;
											int meliphanite_conics = 0;
											while (true) {
												meliphanite_conics++;
												if (meliphanite_conics >= 3000)
													break;
											}
											Tracer.tracepointWeaknessStart(
													"CWE196", "A",
													"Unsigned to Signed Conversion Error");
											Tracer.tracepointVariableChar(
													"value",
													eucryphia_scuta[costar_archimime]);
											try {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												int[] stonesoup_char_counts = stonesoupInitializeCounts((byte) ((char) eucryphia_scuta[costar_archimime]));
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												for (char counter = 0; counter < eucryphia_scuta[costar_archimime]; counter++) {
													stonesoup_char_counts[counter] += 1;
												}
												Tracer.tracepointBufferInfo(
														"stonesoup_char_counts",
														stonesoup_char_counts.length,
														"Length of stonesoup_char_counts");
											} catch (RuntimeException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												e.printStackTrace(ConfigurableAnalyzer.riddelMiscalculation);
												throw e;
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException hourUnmold) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												hourUnmold);
									}
								}
							}
						}
					} finally {
						ConfigurableAnalyzer.riddelMiscalculation.close();
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

	public static int[] stonesoupInitializeCounts(byte size) {
		Tracer.tracepointLocation(
				"/tmp/tmpfCCDPe_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
				"stonesoupInitializeCounts");
		Tracer.tracepointVariableByte("size", size);
		if (size == 0) {
			return null;
		}
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		int[] result = new int[size];
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointBufferInfo("result", result.length, "Length of result");
		for (int ii = 0; ii < result.length; ii++) {
			result[ii] = 0;
		}
		return result;
	}

}
