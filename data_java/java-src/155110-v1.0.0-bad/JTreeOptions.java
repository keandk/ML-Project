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



public class JTreeOptions {

	static PrintStream intonatorResolvent = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unwomanizedDiaschisis = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (unwomanizedDiaschisis.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmppdWzly_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File sequesteredMannify = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!sequesteredMannify.getParentFile().exists()
					&& !sequesteredMannify.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.intonatorResolvent = new PrintStream(
							new FileOutputStream(sequesteredMannify, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException lithanthraxExcretes) {
					System.err.printf("Failed to open log file.  %s\n",
							lithanthraxExcretes.getMessage());
					JTreeOptions.intonatorResolvent = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							lithanthraxExcretes);
				} catch (FileNotFoundException brooklessUnrule) {
					System.err.printf("Failed to open log file.  %s\n",
							brooklessUnrule.getMessage());
					JTreeOptions.intonatorResolvent = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							brooklessUnrule);
				}
				if (JTreeOptions.intonatorResolvent != null) {
					try {
						String stragular_bedscrew = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (stragular_bedscrew == null
								|| !stragular_bedscrew.equals("1")) {
							String filo_semiquietism = System
									.getenv("REDELIVERER_PAINTING");
							if (null != filo_semiquietism) {
								File redeflect_pyritization = new File(
										filo_semiquietism);
								if (redeflect_pyritization.exists()
										&& !redeflect_pyritization
												.isDirectory()) {
									try {
										String masaridid_hyperbranchia;
										Scanner subspecies_axofugal = new Scanner(
												redeflect_pyritization, "UTF-8")
												.useDelimiter("\\A");
										if (subspecies_axofugal.hasNext())
											masaridid_hyperbranchia = subspecies_axofugal
													.next();
										else
											masaridid_hyperbranchia = "";
										if (null != masaridid_hyperbranchia) {
											short headquarters_ashlaring;
											try {
												headquarters_ashlaring = Short
														.parseShort(masaridid_hyperbranchia);
											} catch (NumberFormatException romancelet_rah) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														romancelet_rah);
											}
											Tracer.tracepointWeaknessStart(
													"CWE190", "A",
													"Integer Overflow or Wraparound");
											short stonesoup_checked_value = headquarters_ashlaring;
											Tracer.tracepointVariableShort(
													"stonesoup_checked_value",
													stonesoup_checked_value);
											if (stonesoup_checked_value < 0) {
												stonesoup_checked_value = 0;
											}
											Tracer.tracepointVariableShort(
													"stonesoup_checked_value",
													stonesoup_checked_value);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											short stonesoup_value = (short) (((short) (Short.MAX_VALUE) - (short) 500) + stonesoup_checked_value);
											Tracer.tracepointVariableShort(
													"stonesoup_value",
													stonesoup_value);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											stonesoup_value++;
											String[] stonesoup_array = null;
											try {
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												Tracer.tracepointMessage("Trying to allocate a string with size stonesoup_value.");
												stonesoup_array = new String[stonesoup_value];
												Tracer.tracepointBufferInfo(
														"stonesoup_array",
														stonesoup_array.length,
														"Length of newly allocated stonesoup_array");
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												for (short index = 0; index < stonesoup_value; index++) {
													stonesoup_array[index] = Character
															.toString((char) index);
												}
												Tracer.tracepointMessage("Copy data into stonesoup_array.");
											} catch (java.lang.RuntimeException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												e.printStackTrace(JTreeOptions.intonatorResolvent);
												throw e;
											}
											for (int counter = 0; counter < stonesoup_array.length; counter++) {
												JTreeOptions.intonatorResolvent
														.printf("array[%d]=%s\n",
																counter,
																stonesoup_array[counter]);
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException miscognizantGhostly) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												miscognizantGhostly);
									}
								}
							}
						}
					} finally {
						JTreeOptions.intonatorResolvent.close();
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
