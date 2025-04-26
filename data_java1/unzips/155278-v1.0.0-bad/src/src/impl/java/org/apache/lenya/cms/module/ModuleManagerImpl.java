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
import java.util.ArrayList;
import java.util.List;

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    public static interface IMazzinistMyriad {
		public void roseletToxicohemia(
				PalaeozoicDeluster<Integer> goodeniaceous_internuncioship);
	}

	public static class BiorbitalLadyship implements IMazzinistMyriad {
		@Override public void roseletToxicohemia(PalaeozoicDeluster<Integer> goodeniaceous_internuncioship){Tracer.tracepointWeaknessStart("CWE839","A","Numeric Range Comparison Without Minimum Check");@SuppressWarnings("serial") List<String> stonesoup_face_cards=new ArrayList<String>(){{add("Hearts (Jack)");add("Hearts (Queen)");add("Hearts (King)");add("Hearts (Ace)");add("Clubs (Jack)");add("Clubs (Queen)");add("Clubs (King)");add("Clubs (Ace)");add("Spades (Jack)");add("Spades (Queen)");add("Spades (King)");add("Spades (Ace)");add("Diamonds (Jack)");add("Diamonds (Queen)");add("Diamonds (King)");add("Diamonds (Ace)");add("Joker");add("Joker");}};Tracer.tracepointVariableInt("value",goodeniaceous_internuncioship.getexpeditation_autoscopic());Tracer.tracepointVariableInt("stonesoup_face_cards.size()",stonesoup_face_cards.size());Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");if (goodeniaceous_internuncioship.getexpeditation_autoscopic() >= stonesoup_face_cards.size()){Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");ModuleManagerImpl.ekoiUnderfed.printf("Card not available for %d.\n",goodeniaceous_internuncioship.getexpeditation_autoscopic());} else {Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");try {Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");ModuleManagerImpl.ekoiUnderfed.printf("Selected Card = %s\n",stonesoup_face_cards.get(goodeniaceous_internuncioship.getexpeditation_autoscopic()));Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (RuntimeException e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(ModuleManagerImpl.ekoiUnderfed);throw e;}}Tracer.tracepointWeaknessEnd();}	}

	public class PalaeozoicDeluster<T> {
		private T expeditation_autoscopic;

		public PalaeozoicDeluster(T expeditation_autoscopic) {
			this.expeditation_autoscopic = expeditation_autoscopic;
		}

		public T getexpeditation_autoscopic() {
			return this.expeditation_autoscopic;
		}
	}

	static PrintStream ekoiUnderfed = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unforgettableBuccaneerish = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (unforgettableBuccaneerish.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp1YkiSd_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File homoousianistOverlewd = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!homoousianistOverlewd.getParentFile().exists()
					&& !homoousianistOverlewd.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.ekoiUnderfed = new PrintStream(
							new FileOutputStream(homoousianistOverlewd, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException longwortSavoy) {
					System.err.printf("Failed to open log file.  %s\n",
							longwortSavoy.getMessage());
					ModuleManagerImpl.ekoiUnderfed = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							longwortSavoy);
				} catch (FileNotFoundException initiatorQuartile) {
					System.err.printf("Failed to open log file.  %s\n",
							initiatorQuartile.getMessage());
					ModuleManagerImpl.ekoiUnderfed = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							initiatorQuartile);
				}
				if (ModuleManagerImpl.ekoiUnderfed != null) {
					try {
						String ruffling_atomistics = System
								.getenv("GLANDULOUS_ROISTEROUSLY");
						if (null != ruffling_atomistics) {
							int trainmaster_perknite;
							try {
								trainmaster_perknite = Integer
										.parseInt(ruffling_atomistics);
							} catch (NumberFormatException optionalize_sialoid) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										optionalize_sialoid);
							}
							PalaeozoicDeluster<Integer> reaccomplish_pyrologist = new PalaeozoicDeluster<Integer>(
									trainmaster_perknite);
							IMazzinistMyriad selenigenous_preheater = new BiorbitalLadyship();
							selenigenous_preheater
									.roseletToxicohemia(reaccomplish_pyrologist);
						}
					} finally {
						ModuleManagerImpl.ekoiUnderfed.close();
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

}
