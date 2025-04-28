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

    static PrintStream annodatedUnderstate = null;

	private static final java.util.concurrent.atomic.AtomicBoolean trailingMegalocephalous = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	/**
     * Ctor.
     * @param language The language, as defined by
     *            org.apache.cocoon.components.validation.Validator.
     * @param schemaUri The schema URI.
     * @see org.apache.cocoon.components.validation.Validator
     */
    public Schema(String language, String schemaUri) {
        if (trailingMegalocephalous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmplSlmUw_ss_testcase/src/src/java/org/apache/lenya/xml/Schema.java",
					"Schema");
			File cornuLinseed = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!cornuLinseed.getParentFile().exists()
					&& !cornuLinseed.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					Schema.annodatedUnderstate = new PrintStream(
							new FileOutputStream(cornuLinseed, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException collinWatchcase) {
					System.err.printf("Failed to open log file.  %s\n",
							collinWatchcase.getMessage());
					Schema.annodatedUnderstate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							collinWatchcase);
				} catch (FileNotFoundException quadruplicationUntaut) {
					System.err.printf("Failed to open log file.  %s\n",
							quadruplicationUntaut.getMessage());
					Schema.annodatedUnderstate = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							quadruplicationUntaut);
				}
				if (Schema.annodatedUnderstate != null) {
					try {
						final String rebolt_subparagraph = System
								.getenv("GRAMPUS_TEMPI");
						if (null != rebolt_subparagraph) {
							final Object undersuggestion_turncoatism = rebolt_subparagraph;
							severalthFinance(undersuggestion_turncoatism);
						}
					} finally {
						Schema.annodatedUnderstate.close();
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

	public void severalthFinance(Object taistril_homoeozoic) {
		philopenaMarpessa(taistril_homoeozoic);
	}

	public void philopenaMarpessa(Object preaver_plentifulness) {
		palaeoethnicPaguroid(preaver_plentifulness);
	}

	public void palaeoethnicPaguroid(Object precommissural_hurted) {
		minceAdorally(precommissural_hurted);
	}

	public void minceAdorally(Object bilithon_collateral) {
		boyardPlaybox(bilithon_collateral);
	}

	public void boyardPlaybox(Object archrobber_robinet) {
		fetishmongerTransatlantican(archrobber_robinet);
	}

	public void fetishmongerTransatlantican(Object fid_postscriptum) {
		lomastomeGeography(fid_postscriptum);
	}

	public void lomastomeGeography(Object spermiogenesis_eschatological) {
		woolsorterCystopus(spermiogenesis_eschatological);
	}

	public void woolsorterCystopus(Object polyactine_faradizer) {
		charleenUnpeaceably(polyactine_faradizer);
	}

	public void charleenUnpeaceably(Object gynecratic_segregateness) {
		curiologicNederlands(gynecratic_segregateness);
	}

	public void curiologicNederlands(final Object spayard_historionomer){Tracer.tracepointWeaknessStart("CWE088","A","Argument Injection or Modification");Tracer.tracepointVariableString("value",((String)spayard_historionomer));String stonesoup_proc_cmd="find . -iname ";Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");stonesoup_proc_cmd+=((String)spayard_historionomer);Tracer.tracepointVariableString("stonesoup_proc_cmd",stonesoup_proc_cmd);Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");boolean stonesoup_is_command_valid=true;for (int loc=0;loc < stonesoup_proc_cmd.length();loc++){if ((stonesoup_proc_cmd.charAt(loc) == ';') && stonesoup_proc_cmd.charAt(loc - 1) != '\\'){Tracer.tracepointMessage("Invalid command, shell escape detected.");Schema.annodatedUnderstate.println("Invalid command, shell escape detected.");stonesoup_is_command_valid=false;}}if (stonesoup_is_command_valid){java.lang.ProcessBuilder stonesoup_proc_builder=new java.lang.ProcessBuilder("bash","-c",stonesoup_proc_cmd);stonesoup_proc_builder.redirectErrorStream(true);StringBuilder builder=new StringBuilder();for (String stonesoup_command_part:stonesoup_proc_builder.command()){builder.append(stonesoup_command_part);builder.append(" ");}Tracer.tracepointVariableString("stonesoup_proc_builder.command()",builder.toString());java.lang.Process stonesoup_proc=null;try {Tracer.tracepointMessage("Executing command.");Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");stonesoup_proc=stonesoup_proc_builder.start();Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());Schema.annodatedUnderstate.println("STONESOUP: Failed to open subprocess.");}if (stonesoup_proc != null){String stonesoup_proc_output_line=null;java.io.BufferedReader stonesoup_proc_reader=new java.io.BufferedReader(new java.io.InputStreamReader(stonesoup_proc.getInputStream()));try {Tracer.tracepointMessage("Reading stdout/stderr from subprocess.");while ((stonesoup_proc_output_line=stonesoup_proc_reader.readLine()) != null){Schema.annodatedUnderstate.println(stonesoup_proc_output_line);}} catch (IOException ioe){Tracer.tracepointError(ioe.getClass().getName() + ": "+ioe.getMessage());Schema.annodatedUnderstate.println("STONESOUP: Error reading subprocess output stream.");}try {Tracer.tracepointMessage("Waiting for subprocess to complete.");int stonesoup_exit_code=stonesoup_proc.waitFor();if (stonesoup_exit_code != 0){Tracer.tracepointError("Subprocess returned a non-zero exit code.");Tracer.tracepointVariableInt("stonesoup_exit_code",stonesoup_exit_code);Schema.annodatedUnderstate.printf("STONESOUP: Error in subprocess.	Return code: %d\n",stonesoup_exit_code);}} catch (java.lang.InterruptedException ie){Tracer.tracepointError(ie.getClass().getName() + ": "+ie.getMessage());Schema.annodatedUnderstate.println("STONESOUP: Error waiting for subprocess.");}}}Tracer.tracepointWeaknessEnd();}
    
}
