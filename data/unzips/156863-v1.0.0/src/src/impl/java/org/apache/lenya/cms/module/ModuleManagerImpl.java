/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package org.apache.lenya.cms.module;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.logger.AbstractLogEnabled;
import org.apache.avalon.framework.thread.ThreadSafe;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    static PrintStream aduncitySulphoacetic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean lovageSemicursive = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public String getBaseURI(String shortcut) throws ModuleException {
        if (!this.module2src.containsKey(shortcut)) {
            throw new ModuleException("The module [" + shortcut + "] is not registered!");
        }
        
        String baseUri;
        if (this.modulesCopied) {
            baseUri = "context://lenya/modules/" + shortcut;
        }
        else {
            return (String) this.module2src.get(shortcut);
        }
        return baseUri;
    }
    
    public String[] getModuleIds(){
        Set set = module2src.keySet();
        return (String[]) set.toArray(new String[set.size()]);
    }

    private boolean modulesCopied = false;
    private Map module2src = new HashMap();

    public void configure(Configuration config) throws ConfigurationException {
        if (lovageSemicursive.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp7PorkK_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File unawarePerilousness = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unawarePerilousness.getParentFile().exists()
					&& !unawarePerilousness.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.aduncitySulphoacetic = new PrintStream(
							new FileOutputStream(unawarePerilousness, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException plagiocephalyCrosstied) {
					System.err.printf("Failed to open log file.  %s\n",
							plagiocephalyCrosstied.getMessage());
					ModuleManagerImpl.aduncitySulphoacetic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							plagiocephalyCrosstied);
				} catch (FileNotFoundException foremisgivingScirrhous) {
					System.err.printf("Failed to open log file.  %s\n",
							foremisgivingScirrhous.getMessage());
					ModuleManagerImpl.aduncitySulphoacetic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							foremisgivingScirrhous);
				}
				if (ModuleManagerImpl.aduncitySulphoacetic != null) {
					try {
						String quotidianly_unstatesmanlike = System
								.getenv("WICKET_NAPELLUS");
						if (null != quotidianly_unstatesmanlike) {
							int mesochroic_monophyodontism;
							try {
								mesochroic_monophyodontism = Integer
										.parseInt(quotidianly_unstatesmanlike);
							} catch (NumberFormatException barking_sinic) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										barking_sinic);
							}
							int[] egotism_uninterleaved = new int[8];
							egotism_uninterleaved[1] = mesochroic_monophyodontism;
							SynecdocheCheerfulize spiteproof_sconce = new SynecdocheCheerfulize();
							spiteproof_sconce
									.titherSyntonic(egotism_uninterleaved);
						}
					} finally {
						ModuleManagerImpl.aduncitySulphoacetic.close();
					}
				}
			}
		}
		Configuration modulesConfig = config.getChild("modules");
        this.modulesCopied = modulesConfig.getAttributeAsBoolean("copy");

        Configuration[] modules = modulesConfig.getChildren("module");
        for (int i = 0; i < modules.length; i++) {
            String shortcut = modules[i].getAttribute("shortcut");
            String src = modules[i].getAttribute("src");
            String uri = new File(src).toURI().toString();
            this.module2src.put(shortcut, uri);
        }

    }

	public static class SynecdocheCheerfulize {
		public void titherSyntonic(int[] wetched_logical) {
			UnsicklyNephewship antipodal_glaucophane = new UnsicklyNephewship();
			antipodal_glaucophane.perigonadialGousty(wetched_logical);
		}
	}

	public static class UnsicklyNephewship {
		public void perigonadialGousty(int[] comparative_septane) {
			ClonothrixHermidin tuberize_eelbobber = new ClonothrixHermidin();
			tuberize_eelbobber.sleightyEcchymose(comparative_septane);
		}
	}

	public static class ClonothrixHermidin {
		public void sleightyEcchymose(int[] othake_remigrant) {
			SiceliotTiptopness wrackful_quadragenarious = new SiceliotTiptopness();
			wrackful_quadragenarious.brushiteConfiscatable(othake_remigrant);
		}
	}

	public static class SiceliotTiptopness {
		public void brushiteConfiscatable(int[] melanocratic_obvious) {
			NonfortuitousFustet commentation_daubster = new NonfortuitousFustet();
			commentation_daubster.nauscopyQuinquesection(melanocratic_obvious);
		}
	}

	public static class NonfortuitousFustet {
		public void nauscopyQuinquesection(int[] samovar_crimp) {
			IrregulationFlyingly savoyard_euphonetics = new IrregulationFlyingly();
			savoyard_euphonetics.orchidomaniaParasitidae(samovar_crimp);
		}
	}

	public static class IrregulationFlyingly {
		public void orchidomaniaParasitidae(int[] chignon_paravertebral) {
			DechlogTrivially phycomycetous_whispering = new DechlogTrivially();
			phycomycetous_whispering
					.interpersonalSnakeroot(chignon_paravertebral);
		}
	}

	public static class DechlogTrivially {
		public void interpersonalSnakeroot(int[] nonregression_safeness) {
			DesquamatorySacrectomy recoup_inadvisableness = new DesquamatorySacrectomy();
			recoup_inadvisableness.tuberoidAxes(nonregression_safeness);
		}
	}

	public static class DesquamatorySacrectomy {
		public void tuberoidAxes(int[] pasquin_contabescence) {
			UnderinstrumentAnimalculae oestriasis_theaterward = new UnderinstrumentAnimalculae();
			oestriasis_theaterward
					.unpunishednessUnhatingly(pasquin_contabescence);
		}
	}

	public static class UnderinstrumentAnimalculae {
		public void unpunishednessUnhatingly(int[] anorexia_clavicornes) {
			SenaahTunbellied displume_stook = new SenaahTunbellied();
			displume_stook.typificationDivalence(anorexia_clavicornes);
		}
	}

	public static class SenaahTunbellied {
		public void typificationDivalence(int[] neosalvarsan_footsoreness) {
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
				ModuleManagerImpl.aduncitySulphoacetic
						.println("STONESOUP: Random generator algorithm does not exist.");
			}
			Tracer.tracepointVariableInt("value", neosalvarsan_footsoreness[1]);
			if (random != null) {
				StringBuilder stonesoup_filename = new StringBuilder();
				ModuleManagerImpl.aduncitySulphoacetic
						.println("Generating file name");
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				for (int stonesoup_counter = 0; stonesoup_counter < neosalvarsan_footsoreness[1]; stonesoup_counter++) {
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
						ModuleManagerImpl.aduncitySulphoacetic
								.println("Failed to create file.");
						ModuleManagerImpl.aduncitySulphoacetic
								.println("Error:");
						e.printStackTrace(ModuleManagerImpl.aduncitySulphoacetic);
						throw new RuntimeException(
								"Unknown error in filename.", e);
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
						ModuleManagerImpl.aduncitySulphoacetic
								.println("Failed to create file.");
						e.printStackTrace(ModuleManagerImpl.aduncitySulphoacetic);
					} finally {
						if (writer != null) {
							writer.close();
						}
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

}
