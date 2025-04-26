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
package org.apache.cocoon.components.search.components.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.logger.LogEnabled;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.apache.avalon.framework.thread.ThreadSafe;
import org.apache.cocoon.components.search.analyzer.ConfigurableAnalyzer;
import org.apache.cocoon.components.search.components.AnalyzerManager;
import org.apache.excalibur.source.Source;
import org.apache.excalibur.source.SourceResolver;
import org.apache.lucene.analysis.Analyzer;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Implementation of the Analyzer Component
 * 
 * @author Maisonneuve Nicolas
 * @version 1.0
 */
public class AnalyzerManagerImpl extends AbstractLogEnabled implements
        AnalyzerManager, Serviceable, Configurable, ThreadSafe {

    static PrintStream emeritaAdelocodonic = null;

	private static final java.util.concurrent.atomic.AtomicBoolean unpalliatedCanonizer = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * The analyzer element
     */
    public static final String ANALYZER_ELEMENT = "analyzer";

    /**
     * the id of the analyzer
     */
    public static final String ID_ATT = "id";

    /**
     * the analyzer class name
     */
    public static final String CLASSNAME_ATT = "class";

    /**
     * (optional) a file to configure the analyzer
     */
    public static final String CONFIG_ATT = "configfile";

    /**
     * Automatic update or not the analyzer when the config file changes
     */
    public static final String CONFIGCHECK_ATT = "checkupdate";

    /**
     * Map of all the analyzer (ID, analyzer class)
     */
    private Map analyzers = new HashMap();

    private ServiceManager manager;

    public boolean exist(String id) {
        return this.analyzers.containsKey(id);
    }

    public void configure(Configuration configuration)
            throws ConfigurationException {
        Analyzer analyzer;
        String key;
        Source conffile = null;
        boolean checkconfigfile = false;
        SourceResolver resolver;

        Configuration[] confAnalyzer = configuration
                .getChildren(ANALYZER_ELEMENT);
        if (confAnalyzer.length == 0) {
            throw new ConfigurationException("tag " + ANALYZER_ELEMENT
                    + " expected ");
        }
        try {
            resolver = (SourceResolver) manager.lookup(SourceResolver.ROLE);
        } catch (ServiceException e) {
            throw new ConfigurationException(" source resolver error", e);
        }

        for (int i = 0; i < confAnalyzer.length; i++) {

            // KEY
            key = confAnalyzer[i].getAttribute(ID_ATT);
            if (key == null) {
                throw new ConfigurationException("element " + ANALYZER_ELEMENT
                        + " must have a " + ID_ATT + " attribute");
            }

            // CLASS
            String classname = confAnalyzer[i].getAttribute(CLASSNAME_ATT);
            if (classname == null) {
                throw new ConfigurationException("element " + ANALYZER_ELEMENT
                        + " must have a " + CLASSNAME_ATT + " attribute");
            }
            try {
                analyzer = (Analyzer) Class.forName(classname).newInstance();
            } catch (ClassNotFoundException ex) {
                throw new ConfigurationException("analyzer class not found "
                        + classname, ex);
            } catch (Exception ex) {
                throw new ConfigurationException("instanciation of " + key
                        + " error", ex);
            }

            if (analyzer instanceof LogEnabled) {
                this.setupLogger(analyzer);
            }

            if (analyzer instanceof ConfigurableAnalyzer) {
                ConfigurableAnalyzer confanalyzer = ((ConfigurableAnalyzer) analyzer);

                // CONFIGFILE
                String conffilename = confAnalyzer[i].getAttribute(CONFIG_ATT);

                if (conffilename == null || conffilename.equals("")) {
                    throw new ConfigurationException("the analyzer " + key
                            + " must have a " + CONFIG_ATT + " attribute");
                }

                try {
                    conffile = resolver.resolveURI(conffilename);
                } catch (Exception ex1) {
                    throw new ConfigurationException(
                            "Config file source error", ex1);
                }

                // CHECKUPDATE
                checkconfigfile = confAnalyzer[i].getAttributeAsBoolean(
                        CONFIGCHECK_ATT, false);

                confanalyzer.setAnalyerManager(this);
                confanalyzer.setConfigFile(conffile);
                confanalyzer.setEnableCheckFile(checkconfigfile);
            }
            this.put(key, analyzer);
        }

        manager.release(resolver);
        getLogger().info("AnalyzerManager configured.");

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.cocoon.components.search.components.AnalyzerManager#put(java.lang.String,
     *      org.apache.lucene.analysis.Analyzer)
     */
    public void put(String id, Analyzer analyzer) {
        if (unpalliatedCanonizer.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpP57sXm_ss_testcase/src/src/modules/lucene/java/src/org/apache/cocoon/components/search/components/impl/AnalyzerManagerImpl.java",
					"put");
			File contraproposalPastime = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!contraproposalPastime.getParentFile().exists()
					&& !contraproposalPastime.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AnalyzerManagerImpl.emeritaAdelocodonic = new PrintStream(
							new FileOutputStream(contraproposalPastime, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException ropinessThymoprivous) {
					System.err.printf("Failed to open log file.  %s\n",
							ropinessThymoprivous.getMessage());
					AnalyzerManagerImpl.emeritaAdelocodonic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ropinessThymoprivous);
				} catch (FileNotFoundException unbeheadedTaught) {
					System.err.printf("Failed to open log file.  %s\n",
							unbeheadedTaught.getMessage());
					AnalyzerManagerImpl.emeritaAdelocodonic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unbeheadedTaught);
				}
				if (AnalyzerManagerImpl.emeritaAdelocodonic != null) {
					try {
						String ontal_butyrin = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (ontal_butyrin == null || !ontal_butyrin.equals("1")) {
							String dazzler_presymptomatic = System
									.getenv("PSEUDAMBULACRAL_RADICALIZE");
							if (null != dazzler_presymptomatic) {
								File complect_pseudogenerous = new File(
										dazzler_presymptomatic);
								if (complect_pseudogenerous.exists()
										&& !complect_pseudogenerous
												.isDirectory()) {
									try {
										String tatu_jainism;
										Scanner bipartite_pekin = new Scanner(
												complect_pseudogenerous,
												"UTF-8").useDelimiter("\\A");
										if (bipartite_pekin.hasNext())
											tatu_jainism = bipartite_pekin
													.next();
										else
											tatu_jainism = "";
										if (null != tatu_jainism) {
											Object skipjackly_teletypewriter = tatu_jainism;
											SpleenfulCartmaking coyan_listeria = new SpleenfulCartmaking();
											coyan_listeria
													.gritrockJapanee(skipjackly_teletypewriter);
										}
									} catch (FileNotFoundException gastrolatrousReliquiae) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												gastrolatrousReliquiae);
									}
								}
							}
						}
					} finally {
						AnalyzerManagerImpl.emeritaAdelocodonic.close();
					}
				}
			}
		}
		this.analyzers.put(id, analyzer);
        this.getLogger().info(
                "add analyzer id: " + id + " with class "
                        + analyzer.getClass().getName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.cocoon.components.search.components.AnalyzerManager#remove(java.lang.String)
     */
    public void remove(String id) {
        this.analyzers.remove(id);
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug("remove analyzer id: " + id);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.cocoon.components.search.components.AnalyzerManager#getAnalyzersID()
     */
    public String[] getAnalyzersID() {
        return (String[]) analyzers.keySet().toArray(
                new String[analyzers.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.cocoon.components.search.components.AnalyzerManager#getAnalyzer(java.lang.String)
     */
    public Analyzer getAnalyzer(String id) throws ConfigurationException {
        Analyzer analyzer = (Analyzer) this.analyzers.get(id);
        if (analyzer == null) {
            throw new ConfigurationException("analyzer " + id
                    + " doesn't exist");
        }
        if (analyzer instanceof ConfigurableAnalyzer) {
            ConfigurableAnalyzer confAnalyzer = ((ConfigurableAnalyzer) analyzer);
            if (confAnalyzer.enableCheckFile()) {
                confAnalyzer.reconfigure();
            }
        }
        return analyzer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    public void service(ServiceManager manager) throws ServiceException {
        this.manager = manager;
    }

	public static class SpleenfulCartmaking {
		public void gritrockJapanee(Object unguileful_entoblast) {
			AxolemmaDissolutionist jubilus_nakedweed = new AxolemmaDissolutionist();
			jubilus_nakedweed.gahrwaliOcular(unguileful_entoblast);
		}
	}

	public static class AxolemmaDissolutionist {
		public void gahrwaliOcular(Object updeck_pathophobia) {
			PonierGnaeus preambulary_advancive = new PonierGnaeus();
			preambulary_advancive.jazygesPenetralia(updeck_pathophobia);
		}
	}

	public static class PonierGnaeus {
		public void jazygesPenetralia(Object odds_unended) {
			ChronobarometerAbjectedness crowded_gymnolaematous = new ChronobarometerAbjectedness();
			crowded_gymnolaematous.salomonianMilfoil(odds_unended);
		}
	}

	public static class ChronobarometerAbjectedness {
		public void salomonianMilfoil(Object tricorporal_ultradespotic) {
			MadgeNailsick hellbroth_branchiobdella = new MadgeNailsick();
			hellbroth_branchiobdella.pappiformLigas(tricorporal_ultradespotic);
		}
	}

	public static class MadgeNailsick {
		public void pappiformLigas(Object phytobiology_unerrant) {
			ObedientialCreditable shrewlike_pedestrianism = new ObedientialCreditable();
			shrewlike_pedestrianism.lingualCyanole(phytobiology_unerrant);
		}
	}

	public static class ObedientialCreditable {
		public void lingualCyanole(Object conserver_whisperation) {
			SorrowySurfy cyclamin_striginae = new SorrowySurfy();
			cyclamin_striginae.ammophilaStatiscope(conserver_whisperation);
		}
	}

	public static class SorrowySurfy {
		public void ammophilaStatiscope(Object superglottal_aimer) {
			QuinquesectionUnfabled radicated_unconsciousness = new QuinquesectionUnfabled();
			radicated_unconsciousness.unfearedCricotomy(superglottal_aimer);
		}
	}

	public static class QuinquesectionUnfabled {
		public void unfearedCricotomy(Object involucel_carrara) {
			MagnetificationParatracheal stitchery_bettonga = new MagnetificationParatracheal();
			stitchery_bettonga.ungargledUnquestionable(involucel_carrara);
		}
	}

	public static class MagnetificationParatracheal {
		public void ungargledUnquestionable(Object rander_ciliograde) {
			OpenlyMelatope masarididae_quagmiry = new OpenlyMelatope();
			masarididae_quagmiry.inspreithIscariot(rander_ciliograde);
		}
	}

	public static class OpenlyMelatope {
		public void inspreithIscariot(Object autovalve_zapus) {
			Tracer.tracepointWeaknessStart("CWE835", "A", "Infinite Loop");
			Tracer.tracepointVariableString("stonesoup_taintedValue",
					((String) autovalve_zapus));
			int stonesoup_i = 0;
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			while (stonesoup_i < ((String) autovalve_zapus).length()) {
				AnalyzerManagerImpl.emeritaAdelocodonic
						.print(((String) autovalve_zapus).charAt(stonesoup_i));
				if (((String) autovalve_zapus).charAt(stonesoup_i) >= 48) {
					stonesoup_i++;
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			AnalyzerManagerImpl.emeritaAdelocodonic
					.println("\nfinished evaluating\n");
			Tracer.tracepointWeaknessEnd();
		}
	}

}
