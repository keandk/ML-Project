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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    public class OrganogenistMacroconidial<T> {
		private T paravaginitis_nonconcurrency;

		public OrganogenistMacroconidial(T paravaginitis_nonconcurrency) {
			this.paravaginitis_nonconcurrency = paravaginitis_nonconcurrency;
		}

		public T getparavaginitis_nonconcurrency() {
			return this.paravaginitis_nonconcurrency;
		}
	}

	public void fistuleNappishness(int pericarp_diplomatist,
			OrganogenistMacroconidial<int[]> wormholed_bolboxalis) {
		pericarp_diplomatist--;
		if (pericarp_diplomatist > 0) {
			aforeDaygoing(pericarp_diplomatist, wormholed_bolboxalis);
		}
	}

	public void aforeDaygoing(int facultate_subscription,
			OrganogenistMacroconidial<int[]> wormholed_bolboxalis) {
		fistuleNappishness(facultate_subscription, wormholed_bolboxalis);
		Tracer.tracepointWeaknessStart("CWE606", "B",
				"Uncheck Input for Loop Condition");
		char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
				.toCharArray();
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			ConfigurableAnalyzer.bubalisParticularity
					.println("STONESOUP: Random generator algorithm does not exist.");
		}
		Tracer.tracepointVariableInt("value",
				wormholed_bolboxalis.getparavaginitis_nonconcurrency()[4]);
		if (random != null) {
			StringBuilder stonesoup_filename = new StringBuilder();
			ConfigurableAnalyzer.bubalisParticularity
					.println("Generating file name");
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < wormholed_bolboxalis
					.getparavaginitis_nonconcurrency()[4]; stonesoup_counter++) {
				stonesoup_filename.append(stonesoup_random_charset[random
						.nextInt(stonesoup_random_charset.length)]);
			}
			Tracer.tracepointVariableString("stonesoup_filename",
					stonesoup_filename.toString());
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			if (stonesoup_filename.length() > 0) {
				File writePath = new File(stonesoup_filename.toString());
				try {
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					writePath.createNewFile();
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (IOException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ConfigurableAnalyzer.bubalisParticularity
							.println("Failed to create file.");
					ConfigurableAnalyzer.bubalisParticularity.println("Error:");
					e.printStackTrace(ConfigurableAnalyzer.bubalisParticularity);
					throw new RuntimeException("Unknown error in filename.", e);
				}
				FileOutputStream writeStream = null;
				PrintStream writer = null;
				try {
					writeStream = new FileOutputStream(writePath, false);
					writer = new PrintStream(writeStream);
					writer.println("/* This is my file */");
				} catch (FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ConfigurableAnalyzer.bubalisParticularity
							.println("Failed to create file.");
					e.printStackTrace(ConfigurableAnalyzer.bubalisParticularity);
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream bubalisParticularity = null;

	private static final java.util.concurrent.atomic.AtomicBoolean osteochondromaMolluscoid = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (osteochondromaMolluscoid.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpQthgkW_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File proconsulateRaceway = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!proconsulateRaceway.getParentFile().exists()
					&& !proconsulateRaceway.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.bubalisParticularity = new PrintStream(
							new FileOutputStream(proconsulateRaceway, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException nuciformTransfugitive) {
					System.err.printf("Failed to open log file.  %s\n",
							nuciformTransfugitive.getMessage());
					ConfigurableAnalyzer.bubalisParticularity = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nuciformTransfugitive);
				} catch (FileNotFoundException refreshfullyLamellation) {
					System.err.printf("Failed to open log file.  %s\n",
							refreshfullyLamellation.getMessage());
					ConfigurableAnalyzer.bubalisParticularity = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							refreshfullyLamellation);
				}
				if (ConfigurableAnalyzer.bubalisParticularity != null) {
					try {
						String togue_permanganate = System
								.getenv("PEDATISECTED_UNJAGGED");
						if (null != togue_permanganate) {
							int reshuffle_robinet;
							try {
								reshuffle_robinet = Integer
										.parseInt(togue_permanganate);
							} catch (NumberFormatException farinaceously_iguanodontoid) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										farinaceously_iguanodontoid);
							}
							int[] dissilient_aeroscepsis = new int[29];
							dissilient_aeroscepsis[4] = reshuffle_robinet;
							OrganogenistMacroconidial<int[]> wormholed_bolboxalis = new OrganogenistMacroconidial<int[]>(
									dissilient_aeroscepsis);
							int aljoba_fliting = 2;
							fistuleNappishness(aljoba_fliting,
									wormholed_bolboxalis);
						}
					} finally {
						ConfigurableAnalyzer.bubalisParticularity.close();
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
