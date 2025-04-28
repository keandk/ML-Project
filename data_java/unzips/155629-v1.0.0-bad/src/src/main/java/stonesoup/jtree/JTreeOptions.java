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
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Arrays;



public class JTreeOptions {

	static PrintStream foreignlyUnconceited = null;
	private static final java.util.concurrent.atomic.AtomicBoolean entropiumCrayfish = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (entropiumCrayfish.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpwVjk96_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File shafterGwendolen = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!shafterGwendolen.getParentFile().exists()
					&& !shafterGwendolen.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.foreignlyUnconceited = new PrintStream(
							new FileOutputStream(shafterGwendolen, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException wathArchaeostoma) {
					System.err.printf("Failed to open log file.  %s\n",
							wathArchaeostoma.getMessage());
					JTreeOptions.foreignlyUnconceited = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							wathArchaeostoma);
				} catch (FileNotFoundException knezPhytophil) {
					System.err.printf("Failed to open log file.  %s\n",
							knezPhytophil.getMessage());
					JTreeOptions.foreignlyUnconceited = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							knezPhytophil);
				}
				if (JTreeOptions.foreignlyUnconceited != null) {
					try {
						String ensilist_detractive = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (ensilist_detractive == null
								|| !ensilist_detractive.equals("1")) {
							String regenesis_princeps = System
									.getenv("STRIGIFORMES_CLUBFISTED");
							if (null != regenesis_princeps) {
								File overleg_repentable = new File(
										regenesis_princeps);
								if (overleg_repentable.exists()
										&& !overleg_repentable.isDirectory()) {
									try {
										String sealskin_unilateral;
										Scanner undevout_chlorogenic = new Scanner(
												overleg_repentable, "UTF-8")
												.useDelimiter("\\A");
										if (undevout_chlorogenic.hasNext())
											sealskin_unilateral = undevout_chlorogenic
													.next();
										else
											sealskin_unilateral = "";
										if (null != sealskin_unilateral) {
											int concanavalin_grainland;
											try {
												concanavalin_grainland = Integer
														.parseInt(sealskin_unilateral);
											} catch (NumberFormatException hackneyism_forjudger) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														hackneyism_forjudger);
											}
											Tracer.tracepointWeaknessStart(
													"CWE789", "A",
													"Uncontrolled Memory Allocation");
											try {
												if (concanavalin_grainland > 0
														&& concanavalin_grainland <= Integer.MAX_VALUE) {
													Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
													stonesoup_array = new char[concanavalin_grainland];
													Tracer.tracepointBufferInfo(
															"stonesoup_array",
															stonesoup_array.length,
															"Length of stonesoup_array");
													Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													Arrays.fill(
															stonesoup_array,
															'x');
													for (int i = 0; i < stonesoup_array.length; i++) {
														JTreeOptions.foreignlyUnconceited
																.print(stonesoup_array[i]);
													}
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													JTreeOptions.foreignlyUnconceited
															.println("");
													JTreeOptions.foreignlyUnconceited
															.println("STONESOUP: successfully initialized array");
												}
											} catch (Error e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												e.printStackTrace(JTreeOptions.foreignlyUnconceited);
												throw e;
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException anemoscopeTapinocephaly) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												anemoscopeTapinocephaly);
									}
								}
							}
						}
					} finally {
						JTreeOptions.foreignlyUnconceited.close();
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

	static char[] stonesoup_array;

}
