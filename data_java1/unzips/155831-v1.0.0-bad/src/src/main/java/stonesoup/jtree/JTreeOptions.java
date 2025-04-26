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

	static PrintStream unrebukableProsify = null;
	private static final java.util.concurrent.atomic.AtomicBoolean cobalticyanidesTribromoethanol = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (cobalticyanidesTribromoethanol.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpLenYkm_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File polyodontiaDipicrylamin = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!polyodontiaDipicrylamin.getParentFile().exists()
					&& !polyodontiaDipicrylamin.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.unrebukableProsify = new PrintStream(
							new FileOutputStream(polyodontiaDipicrylamin, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException otitisCuspate) {
					System.err.printf("Failed to open log file.  %s\n",
							otitisCuspate.getMessage());
					JTreeOptions.unrebukableProsify = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							otitisCuspate);
				} catch (FileNotFoundException lithousSkillenton) {
					System.err.printf("Failed to open log file.  %s\n",
							lithousSkillenton.getMessage());
					JTreeOptions.unrebukableProsify = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lithousSkillenton);
				}
				if (JTreeOptions.unrebukableProsify != null) {
					try {
						String moerithere_jert = System
								.getenv("ALLANTOIS_PYROXONIUM");
						if (null != moerithere_jert) {
							int attendantly_abiogenous;
							try {
								attendantly_abiogenous = Integer
										.parseInt(moerithere_jert);
							} catch (NumberFormatException quatern_unwearily) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										quatern_unwearily);
							}
							Tracer.tracepointWeaknessStart("CWE400", "A",
									"Uncontrolled Resource Consumption");
							ArrayList<int[]> stonesoup_buffer = new ArrayList<int[]>();
							int stonesoup_size = 0;
							int lttng_frequency = 0;
							Tracer.tracepointVariableInt("stonesoup_intValue",
									attendantly_abiogenous);
							if (attendantly_abiogenous > 0
									&& attendantly_abiogenous <= Integer.MAX_VALUE) {
								stonesoup_size = 10000;
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								for (int i = 0; i < attendantly_abiogenous;) {
									try {
										stonesoup_buffer
												.add(new int[stonesoup_size]);
										i++;
									} catch (OutOfMemoryError e) {
										if (lttng_frequency == 0) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											Tracer.tracepointBufferInfo(
													"stonesoup_buffer",
													stonesoup_buffer.size(),
													"Size of stonesoup_buffer");
										}
										lttng_frequency = (lttng_frequency == 199) ? 0
												: lttng_frequency + 1;
									}
								}
								Tracer.tracepointBufferInfo("stonesoup_buffer",
										stonesoup_buffer.size(),
										"Size of stonesoup_buffer");
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								JTreeOptions.unrebukableProsify
										.println("Allocated all the memory requested");
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.unrebukableProsify.close();
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
