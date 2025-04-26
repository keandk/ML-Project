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
package org.apache.lenya.xml;

import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Validation schema.
 */
public class Schema {

    static PrintStream rhineuraBalolo = null;

	public void ungenialitySpiracular(int cosinusoid_microliter,
			String[] excursionistEmancipatory) {
		cosinusoid_microliter--;
		if (cosinusoid_microliter > 0) {
			defigurationInferentialist(cosinusoid_microliter,
					excursionistEmancipatory);
		}
	}

	public void defigurationInferentialist(int supplanter_naric,String[] excursionistEmancipatory){ungenialitySpiracular(supplanter_naric,excursionistEmancipatory);Tracer.tracepointWeaknessStart("CWE078","A","Imporper Neutralization of Special Elements used in an OS Command");Tracer.tracepointVariableString("value",excursionistEmancipatory[11]);Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");String stonesoup_proc_cmd="ls " + excursionistEmancipatory[11];Tracer.tracepointVariableString("stonesoup_proc_cmd",stonesoup_proc_cmd);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");java.lang.ProcessBuilder stonesoup_proc_builder=new java.lang.ProcessBuilder("bash","-c",stonesoup_proc_cmd);stonesoup_proc_builder.redirectErrorStream(true);StringBuilder builder=new StringBuilder();for (String stonesoup_command_part:stonesoup_proc_builder.command()){builder.append(stonesoup_command_part);builder.append(" ");}Tracer.tracepointVariableString("stonesoup_proc_builder.command()",builder.toString());java.lang.Process stonesoup_proc=null;try {Tracer.tracepointMessage("Executing command.");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");stonesoup_proc=stonesoup_proc_builder.start();Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());Schema.rhineuraBalolo.println("STONESOUP: Failed to open subprocess.");}if (stonesoup_proc != null){String stonesoup_proc_output_line=null;java.io.BufferedReader stonesoup_proc_reader=new java.io.BufferedReader(new java.io.InputStreamReader(stonesoup_proc.getInputStream()));try {Tracer.tracepointMessage("Reading stdout/stderr from the subprocess.");while ((stonesoup_proc_output_line=stonesoup_proc_reader.readLine()) != null){Schema.rhineuraBalolo.println(stonesoup_proc_output_line);}} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());Schema.rhineuraBalolo.println("STONESOUP: Error reading subprocess output stream.");}try {Tracer.tracepointMessage("Waiting for process to complete.");int stonesoup_exit_code=stonesoup_proc.waitFor();if (stonesoup_exit_code != 0){Tracer.tracepointError("Error in subprocess.");Tracer.tracepointVariableInt("stonesoup_exit_code",stonesoup_exit_code);Schema.rhineuraBalolo.printf("STONESOUP: Error in subprocess.	Return code: %d\n",stonesoup_exit_code);}} catch (java.lang.InterruptedException ie){Tracer.tracepointError(ie.getClass().getName() + ": "+ie.getMessage());Schema.rhineuraBalolo.println("STONESOUP: Error waiting for subprocess.");}}Tracer.tracepointWeaknessEnd();}

	private static final java.util.concurrent.atomic.AtomicBoolean idiophanismMorningly = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (idiophanismMorningly.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpGlj4DC_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File sissyishIncept = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!sissyishIncept.getParentFile().exists()
					&& !sissyishIncept.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.rhineuraBalolo = new PrintStream(
							new FileOutputStream(sissyishIncept, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException atherisStatehouse) {
					System.err.printf("Failed to open log file.  %s\n",
							atherisStatehouse.getMessage());
					Schema.rhineuraBalolo = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							atherisStatehouse);
				} catch (FileNotFoundException blamefulnessPygopodous) {
					System.err.printf("Failed to open log file.  %s\n",
							blamefulnessPygopodous.getMessage());
					Schema.rhineuraBalolo = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							blamefulnessPygopodous);
				}
				if (Schema.rhineuraBalolo != null) {
					try {
						String crystallometry_sozzly = System
								.getenv("STORABLE_PEWTERER");
						if (null != crystallometry_sozzly) {
							String[] evalue_bridgeable = new String[31];
							evalue_bridgeable[11] = crystallometry_sozzly;
							inspiredlyObjectival(3, null, null, null,
									evalue_bridgeable, null, null);
						}
					} finally {
						Schema.rhineuraBalolo.close();
					}
				}
			}
		}
		this.language = language;
        this.uri = schemaUri;
    }

    private String language;

    private String uri;

    /**
     * @return The language.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public String getLanguage() {
        return this.language;
    }

    /**
     * @return The URI to read the schema from.
     */
    public String getURI() {
        return this.uri;
    }

	public void inspiredlyObjectival(int flindosaBiconsonantal,
			String[]... theorumHologastrula) {
		String[] excursionistEmancipatory = null;
		int schizogenouslyExpensive = 0;
		for (schizogenouslyExpensive = 0; schizogenouslyExpensive < theorumHologastrula.length; schizogenouslyExpensive++) {
			if (schizogenouslyExpensive == flindosaBiconsonantal)
				excursionistEmancipatory = theorumHologastrula[schizogenouslyExpensive];
		}
		int ostearthritis_yellowrump = 2;
		ungenialitySpiracular(ostearthritis_yellowrump,
				excursionistEmancipatory);
	}
    
}
