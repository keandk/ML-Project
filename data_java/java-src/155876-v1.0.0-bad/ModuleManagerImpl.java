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

/**
 * Module manager implementation.
 */
public class ModuleManagerImpl extends AbstractLogEnabled implements ModuleManager, ThreadSafe,
        Configurable {

    public static interface IUromantistRestraining {
		public void selfhealAppositional(final int[] unrussian_phalangiid);
	}

	public static class ExarationLucifee implements IUromantistRestraining {
		@Override
		public void selfhealAppositional (final int[]unrussian_phalangiid) {
			stonesoup_sources = new ArrayList<FileOutputStream>();
			Tracer.tracepointWeaknessStart ("CWE774", "B", "Allocation of File Descriptors or Handles Without Limits or Throttling");
			Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
			Tracer.tracepointMessage ("CROSSOVER-POINT: BEFORE");
			Tracer.tracepointMessage ("TRIGGER-POINT; BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < unrussian_phalangiid[11]; stonesoup_counter++)
			{
				try {
					stonesoup_sources.add (new FileOutputStream (String.format ("/opt/stonesoup/workspace/testData/test%10d", stonesoup_counter)));
				} catch (FileNotFoundException e) {
					Tracer.tracepointError (e.getClass ().getName () + ": " + e.getMessage ());
					ModuleManagerImpl.naillikeLevitically.println ("Failed to create new file, moving on.");
				}
				ModuleManagerImpl.naillikeLevitically.println (stonesoup_counter);
			}
			Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
			Tracer.tracepointMessage ("TRIGGER-POINT; AFTER");
			Tracer.tracepointMessage ("CROSSOVER-POINT: AFTER");
			Tracer.tracepointWeaknessEnd ();
		}
		public static ArrayList<FileOutputStream> stonesoup_sources = null;
	}

	static PrintStream naillikeLevitically = null;
	private static final java.util.concurrent.atomic.AtomicBoolean tunnelmakerPosthypophyseal = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (tunnelmakerPosthypophyseal.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpTvVeTz_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File boroughmasterPelorization = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!boroughmasterPelorization.getParentFile().exists()
					&& !boroughmasterPelorization.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.naillikeLevitically = new PrintStream(
							new FileOutputStream(boroughmasterPelorization,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException anisamideSolenidae) {
					System.err.printf("Failed to open log file.  %s\n",
							anisamideSolenidae.getMessage());
					ModuleManagerImpl.naillikeLevitically = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							anisamideSolenidae);
				} catch (FileNotFoundException pigeonholerPolystomium) {
					System.err.printf("Failed to open log file.  %s\n",
							pigeonholerPolystomium.getMessage());
					ModuleManagerImpl.naillikeLevitically = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							pigeonholerPolystomium);
				}
				if (ModuleManagerImpl.naillikeLevitically != null) {
					try {
						final String preception_spheniscidae = System
								.getenv("CONNECTIONAL_PRAY");
						if (null != preception_spheniscidae) {
							final int drapery_bronchoscopy;
							try {
								drapery_bronchoscopy = Integer
										.parseInt(preception_spheniscidae);
							} catch (NumberFormatException phantasmata_benward) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										phantasmata_benward);
							}
							final int[] solay_prefectly = new int[12];
							solay_prefectly[11] = drapery_bronchoscopy;
							IUromantistRestraining michoacan_polyarch = new ExarationLucifee();
							michoacan_polyarch
									.selfhealAppositional(solay_prefectly);
						}
					} finally {
						ModuleManagerImpl.naillikeLevitically.close();
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
