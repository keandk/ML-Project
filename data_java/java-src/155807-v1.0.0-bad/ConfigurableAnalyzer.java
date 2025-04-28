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
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Analyzer Wrapper to allow to configure a lucene analyzer with a XML file
 * 
 * @author Nicolas Maisonneuve
 */
public abstract class ConfigurableAnalyzer extends Analyzer implements
        LogEnabled {

    public class UnrealDatisi {
		private int fluorescin_filamentoid;

		public UnrealDatisi(int fluorescin_filamentoid) {
			this.fluorescin_filamentoid = fluorescin_filamentoid;
		}

		public int getfluorescin_filamentoid() {
			return this.fluorescin_filamentoid;
		}
	}

	public void noseliteMohegan(int posthysterical_chamaesyce,
			UnrealDatisi crustaceology_spirillaceous) {
		if (posthysterical_chamaesyce > 10) {
			noseliteMohegan(posthysterical_chamaesyce++,
					crustaceology_spirillaceous);
		}
		Tracer.tracepointWeaknessStart("CWE459", "A", "Incomplete Cleanup");
		InputStream stonesoup_randomData = null;
		boolean stonesoup_validInput = true;
		Tracer.tracepointVariableInt("stonesoup_intValue",
				crustaceology_spirillaceous.getfluorescin_filamentoid());
		byte[] stonesoup_randomChars = null;
		try {
			ConfigurableAnalyzer.cripplyTrinorantum.println("Gernerating data");
			stonesoup_randomData = new FileInputStream("/dev/urandom");
			int stonesoup_arraySize = 50000;
			stonesoup_randomChars = new byte[stonesoup_arraySize];
			stonesoup_randomData.read(stonesoup_randomChars, 0,
					stonesoup_arraySize);
		} catch (FileNotFoundException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			ConfigurableAnalyzer.cripplyTrinorantum
					.println("Error: /dev/urandom not found");
			stonesoup_validInput = false;
		} catch (IOException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			ConfigurableAnalyzer.cripplyTrinorantum
					.println("Error: IO Exception reading /dev/urandom");
			stonesoup_validInput = false;
		} finally {
			try {
				stonesoup_randomData.close();
			} catch (IOException e) {
				ConfigurableAnalyzer.cripplyTrinorantum
						.println("Error: Cannot close /dev/urandom");
				stonesoup_validInput = false;
			}
		}
		if (stonesoup_validInput) {
			int stonesoup_numFilePaths = 50;
			File[] stonesoup_filePaths = new File[stonesoup_numFilePaths];
			int stonesoup_i = 0;
			OutputStream stonesoup_outputStream = null;
			try {
				ConfigurableAnalyzer.cripplyTrinorantum.println("Saving data");
				for (stonesoup_i = 0; stonesoup_i < crustaceology_spirillaceous
						.getfluorescin_filamentoid(); stonesoup_i++) {
					stonesoup_filePaths[stonesoup_i % stonesoup_numFilePaths] = File
							.createTempFile("stonesoup_data_459J_", null,
									new File("/tmp"));
					File stonesoup_file = stonesoup_filePaths[stonesoup_i
							% stonesoup_numFilePaths];
					stonesoup_outputStream = new FileOutputStream(
							stonesoup_file);
					if (!stonesoup_file.exists()) {
						stonesoup_file.createNewFile();
					}
					stonesoup_outputStream.write(stonesoup_randomChars);
					stonesoup_outputStream.close();
					stonesoup_outputStream = null;
				}
				Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
			} catch (FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ConfigurableAnalyzer.cripplyTrinorantum
						.println("Error: tmp file  not found");
			} catch (IOException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ConfigurableAnalyzer.cripplyTrinorantum
						.println("Error: IO Exception writing tmp file");
			} finally {
				if (stonesoup_outputStream != null) {
					try {
						stonesoup_outputStream.close();
					} catch (IOException e) {
						ConfigurableAnalyzer.cripplyTrinorantum
								.println("Error: could not delete output stream");
					}
				}
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
					if (stonesoup_filePaths[stonesoup_i] != null) {
						stonesoup_filePaths[stonesoup_i].delete();
					}
				}
				Tracer.tracepointVariableInt("stonesoup_i", stonesoup_i);
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

	static PrintStream cripplyTrinorantum = null;

	private static final java.util.concurrent.atomic.AtomicBoolean saltigradeRhagose = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (saltigradeRhagose.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpXV3nv2_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/analyzer/ConfigurableAnalyzer.java",
					"setEnableCheckFile");
			File pulpamentaAftertrial = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!pulpamentaAftertrial.getParentFile().exists()
					&& !pulpamentaAftertrial.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ConfigurableAnalyzer.cripplyTrinorantum = new PrintStream(
							new FileOutputStream(pulpamentaAftertrial, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException exemplaryProgenitorship) {
					System.err.printf("Failed to open log file.  %s\n",
							exemplaryProgenitorship.getMessage());
					ConfigurableAnalyzer.cripplyTrinorantum = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							exemplaryProgenitorship);
				} catch (FileNotFoundException hierarchalMedullose) {
					System.err.printf("Failed to open log file.  %s\n",
							hierarchalMedullose.getMessage());
					ConfigurableAnalyzer.cripplyTrinorantum = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							hierarchalMedullose);
				}
				if (ConfigurableAnalyzer.cripplyTrinorantum != null) {
					try {
						String ping_tesuque = System.getenv("FUT_INTERCHECK");
						if (null != ping_tesuque) {
							int unamusingly_fibrinocellular;
							try {
								unamusingly_fibrinocellular = Integer
										.parseInt(ping_tesuque);
							} catch (NumberFormatException flavopurpurin_nephrohydrosis) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										flavopurpurin_nephrohydrosis);
							}
							UnrealDatisi stauraxonial_chiselly = new UnrealDatisi(
									unamusingly_fibrinocellular);
							int overcheaply_unfunniness = 0;
							noseliteMohegan(overcheaply_unfunniness,
									stauraxonial_chiselly);
						}
					} finally {
						ConfigurableAnalyzer.cripplyTrinorantum.close();
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
