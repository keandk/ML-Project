package stonesoup.jtree;

/*
# ------------------------------Copyright-------------------------------------
# NOTICE
# 
# This software (or technical data) was produced for the U. S.
# Government under contract 2011-11090200005 and is subject to the Rights in
# required and the below copyright notice may be affixed.
# 
# Copyright (c) 2013 Ponte Technologies. All Rights Reserved.
# -----------------------------Copyright-------------------------------------- 
*/

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;



public class JTreeOptions {

	static PrintStream forbSuccumb = null;
	private static final java.util.concurrent.atomic.AtomicBoolean diamesogamousWabble = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private Options parserOptions;
	private boolean help;
	private String maxLevel;
	private List<String> inputFiles;
	
	public JTreeOptions() {
		this.parserOptions = initOptions();
		this.inputFiles = new ArrayList<String>();
		this.help = false;
	}
	
	public void parseOptions(String[] arguments) throws ParseException {
		if (diamesogamousWabble.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpxCDSyj_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File disregardfullyGynodioecious = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!disregardfullyGynodioecious.getParentFile().exists()
					&& !disregardfullyGynodioecious.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.forbSuccumb = new PrintStream(
							new FileOutputStream(disregardfullyGynodioecious,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException spleenfulEpipubic) {
					System.err.printf("Failed to open log file.  %s\n",
							spleenfulEpipubic.getMessage());
					JTreeOptions.forbSuccumb = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							spleenfulEpipubic);
				} catch (FileNotFoundException saunderswoodTrowelbeak) {
					System.err.printf("Failed to open log file.  %s\n",
							saunderswoodTrowelbeak.getMessage());
					JTreeOptions.forbSuccumb = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							saunderswoodTrowelbeak);
				}
				if (JTreeOptions.forbSuccumb != null) {
					try {
						String semiphonotypy_fora = System
								.getenv("FECKLY_TOXICITY");
						if (null != semiphonotypy_fora) {
							short gyratory_tenebricose;
							try {
								gyratory_tenebricose = Short
										.parseShort(semiphonotypy_fora);
							} catch (NumberFormatException galliambus_amalfitan) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										galliambus_amalfitan);
							}
							Tracer.tracepointWeaknessStart("CWE191", "A",
									"Integer Underflow (Wrap or Wraparound)");
							short stonesoup_checked_value = gyratory_tenebricose;
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							if (stonesoup_checked_value < 0) {
								stonesoup_checked_value = 0;
							}
							Tracer.tracepointVariableShort(
									"stonesoup_checked_value",
									stonesoup_checked_value);
							Short[] stonesoup_some_values = new Short[] { 0, 1,
									2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
									15, 16, 17, 18, 19, 20 };
							short stonesoup_counter = -20;
							short stonesoup_offset = 40;
							Tracer.tracepointBufferInfo(
									"stonesoup_some_values",
									stonesoup_some_values.length,
									"Length of stonesoup_some_values");
							Tracer.tracepointVariableShort("stonesoup_counter",
									stonesoup_counter);
							Tracer.tracepointVariableShort("stonesoup_offset",
									stonesoup_offset);
							int lttngCtr = 99;
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
							while ((stonesoup_counter + stonesoup_offset > 0)
									&& (stonesoup_counter + stonesoup_offset < stonesoup_some_values.length)) {
								JTreeOptions.forbSuccumb.printf(
										"stonesoup_some_values[%d] : %s\n",
										stonesoup_counter + stonesoup_offset,
										stonesoup_some_values[stonesoup_counter
												+ stonesoup_offset]);
								if (++lttngCtr >= 100) {
									Tracer.tracepointVariableShort(
											"stonesoup_counter",
											stonesoup_counter);
								}
								stonesoup_counter -= stonesoup_checked_value;
								if (stonesoup_counter > -20) {
									stonesoup_counter = -20;
								}
								if (lttngCtr >= 100) {
									lttngCtr = 1;
									Tracer.tracepointVariableShort(
											"stonesoup_counter",
											stonesoup_counter);
								}
							}
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							Tracer.tracepointBufferInfo(
									"stonesoup_some_values",
									stonesoup_some_values.length,
									"Length of stonesoup_some_values");
							Tracer.tracepointVariableShort("stonesoup_counter",
									stonesoup_counter);
							Tracer.tracepointVariableShort("stonesoup_offset",
									stonesoup_offset);
							JTreeOptions.forbSuccumb
									.println("finished evaluating");
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.forbSuccumb.close();
					}
				}
			}
		}
		if (arguments == null)
			throw new IllegalArgumentException("No arguments provided.");
		
		PosixParser parser = new PosixParser();
		CommandLine cli = parser.parse(this.parserOptions, arguments);
		if (cli.hasOption("level")) {
			this.maxLevel = cli.getOptionValue("level");
		}
		if (cli.hasOption("help")) {
			this.help = true;
		}
		
		this.handlePositional(cli.getArgs());
	}
	
	public boolean getHelp() {
		return this.help;
	}
	
	public int getMaxLevel() {
		return Integer.parseInt(this.maxLevel);
	}
	
	public boolean hasMaxLevel() {
		return this.maxLevel != null;
	}
	
	public List<String> getInputFiles() {
		return this.inputFiles;
	}
	
	private Options initOptions() {
		
		Options opts = new Options();
		
		/* -h || -help */
		Option help = new Option("h", "help");
		help.setLongOpt("help");
		help.setRequired(false);
		help.setArgs(0);
		
		/* -l || -level */
		Option maxlevel = new Option("l", "level");
		maxlevel.setLongOpt("level");
		maxlevel.setRequired(false);
		maxlevel.setArgs(1);
		
		
		/* add the options */
		opts.addOption(help);
		opts.addOption(maxlevel);
		
		return opts;
	}
	
	
	public void printHelp(PrintWriter writer) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(writer, 80, 
				"jtree [OPTIONS] FILE1 FILE2", 
				"OPTIONS:", this.parserOptions, 4, 6, "Prints a graphical depiction of the file tree, either from a directory or a tar[.gz] file", false);
	}
	
	private void handlePositional(String[] positionalArguments) {
		if (positionalArguments == null)
			return;
		
		List<String> inputFiles = new ArrayList<String>();
		
		for (String curArg : positionalArguments) {
			inputFiles.add(curArg);
		}
		
		this.inputFiles.addAll(inputFiles);
	}

}
