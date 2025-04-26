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

    static PrintStream padusPauser = null;
	private static final java.util.concurrent.atomic.AtomicBoolean beamworkUnfavorableness = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (beamworkUnfavorableness.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpPuB9qt_ss_testcase/src/src/impl/java/org/apache/lenya/cms/module/ModuleManagerImpl.java",
					"configure");
			File snippersnapperSquelchingly = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!snippersnapperSquelchingly.getParentFile().exists()
					&& !snippersnapperSquelchingly.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ModuleManagerImpl.padusPauser = new PrintStream(
							new FileOutputStream(snippersnapperSquelchingly,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException houseballOrson) {
					System.err.printf("Failed to open log file.  %s\n",
							houseballOrson.getMessage());
					ModuleManagerImpl.padusPauser = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							houseballOrson);
				} catch (FileNotFoundException bridecakeDotation) {
					System.err.printf("Failed to open log file.  %s\n",
							bridecakeDotation.getMessage());
					ModuleManagerImpl.padusPauser = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							bridecakeDotation);
				}
				if (ModuleManagerImpl.padusPauser != null) {
					try {
						String polypoid_terebra = System
								.getenv("THORACOACROMIAL_PINKER");
						if (null != polypoid_terebra) {
							int supercaption_potong;
							try {
								supercaption_potong = Integer
										.parseInt(polypoid_terebra);
							} catch (NumberFormatException cambarus_phantasmist) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										cambarus_phantasmist);
							}
							turusBlatancy(supercaption_potong);
						}
					} finally {
						ModuleManagerImpl.padusPauser.close();
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

	public void turusBlatancy(int wronghearted_parasemidine) {
		subpartitionSubcircuit(wronghearted_parasemidine);
	}

	public void subpartitionSubcircuit(int puppify_codiscoverer){Tracer.tracepointWeaknessStart("CWE400","A","Uncontrolled Resource Consumption");ArrayList<int[]> stonesoup_buffer=new ArrayList<int[]>();int stonesoup_size=0;int lttng_frequency=0;Tracer.tracepointVariableInt("stonesoup_intValue",puppify_codiscoverer);if (puppify_codiscoverer > 0 && puppify_codiscoverer <= Integer.MAX_VALUE){stonesoup_size=10000;Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");for (int i=0;i < puppify_codiscoverer;){try {stonesoup_buffer.add(new int[stonesoup_size]);i++;} catch (OutOfMemoryError e){if (lttng_frequency == 0){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");}lttng_frequency=(lttng_frequency == 199)?0:lttng_frequency + 1;}}Tracer.tracepointBufferInfo("stonesoup_buffer",stonesoup_buffer.size(),"Size of stonesoup_buffer");Tracer.tracepointMessage("TRIGGER-POINT: AFTER");Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");ModuleManagerImpl.padusPauser.println("Allocated all the memory requested");}Tracer.tracepointWeaknessEnd();}

}
