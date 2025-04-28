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
import java.math.BigInteger;

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    public class FoilerMitotic {
		private Object pyrotantalate_optimacy;

		public FoilerMitotic(Object pyrotantalate_optimacy) {
			this.pyrotantalate_optimacy = pyrotantalate_optimacy;
		}

		public Object getpyrotantalate_optimacy() {
			return this.pyrotantalate_optimacy;
		}
	}

	static PrintStream heliozoanPrajapati = null;

	private static final java.util.concurrent.atomic.AtomicBoolean rhematologySomali = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (rhematologySomali.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpoEsA2m_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File stretchberryCheckstrap = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!stretchberryCheckstrap.getParentFile().exists()
					&& !stretchberryCheckstrap.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.heliozoanPrajapati = new PrintStream(
							new FileOutputStream(stretchberryCheckstrap, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException occultistScuff) {
					System.err.printf("Failed to open log file.  %s\n",
							occultistScuff.getMessage());
					ConfigurableAnalyzer.heliozoanPrajapati = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							occultistScuff);
				} catch (FileNotFoundException goldseedPsychokinetic) {
					System.err.printf("Failed to open log file.  %s\n",
							goldseedPsychokinetic.getMessage());
					ConfigurableAnalyzer.heliozoanPrajapati = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							goldseedPsychokinetic);
				}
				if (ConfigurableAnalyzer.heliozoanPrajapati != null) {
					try {
						String suberate_calorizer = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (suberate_calorizer == null
								|| !suberate_calorizer.equals("1")) {
							String excited_goofy = System
									.getenv("EELCATCHER_PROGNOSTICATORY");
							if (null != excited_goofy) {
								File verbate_composedness = new File(
										excited_goofy);
								if (verbate_composedness.exists()
										&& !verbate_composedness.isDirectory()) {
									try {
										String helpworthy_pycnid;
										Scanner electrode_where = new Scanner(
												verbate_composedness, "UTF-8")
												.useDelimiter("\\A");
										if (electrode_where.hasNext())
											helpworthy_pycnid = electrode_where
													.next();
										else
											helpworthy_pycnid = "";
										if (null != helpworthy_pycnid) {
											Object cytoblastemic_indeterminative = helpworthy_pycnid;
											FoilerMitotic whoremastery_emendatory = new FoilerMitotic(
													cytoblastemic_indeterminative);
											int overrigged_ostracization = 0;
											while (true) {
												overrigged_ostracization++;
												if (overrigged_ostracization >= 3000)
													break;
											}
											Tracer.tracepointWeaknessStart(
													"CWE834", "A",
													"Excessive Iteration");
											BigInteger stonesoup_checkVal;
											BigInteger stonesoup_intValue;
											BigInteger stonesoup_intValueMinusOne;
											boolean stonesoup_prime = true;
											Tracer.tracepointVariableString(
													"stonesoup_taintedValue",
													((String) whoremastery_emendatory
															.getpyrotantalate_optimacy()));
											try {
												stonesoup_checkVal = new BigInteger(
														"2");
												stonesoup_intValue = new BigInteger(
														((String) whoremastery_emendatory
																.getpyrotantalate_optimacy()));
												stonesoup_intValueMinusOne = stonesoup_intValue
														.subtract(BigInteger.ONE);
												if (stonesoup_intValue
														.compareTo(BigInteger.ZERO) > 0) {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													for (; stonesoup_checkVal
															.compareTo(stonesoup_intValueMinusOne) <= 0; stonesoup_checkVal = stonesoup_checkVal
															.add(BigInteger.ONE)) {
														if (stonesoup_intValue
																.mod(stonesoup_checkVal)
																.compareTo(
																		BigInteger.ZERO) == 0) {
															stonesoup_prime = false;
															ConfigurableAnalyzer.heliozoanPrajapati
																	.println("Not Prime");
															break;
														}
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												}
											} catch (NumberFormatException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												ConfigurableAnalyzer.heliozoanPrajapati
														.println("STONESOUP: Input string is not an integer");
											}
											ConfigurableAnalyzer.heliozoanPrajapati
													.println("finished evaluating");
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException untiltingQuadrisetose) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												untiltingQuadrisetose);
									}
								}
							}
						}
					} finally {
						ConfigurableAnalyzer.heliozoanPrajapati.close();
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
