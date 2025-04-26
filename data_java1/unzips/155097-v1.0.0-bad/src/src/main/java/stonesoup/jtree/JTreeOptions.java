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

	static PrintStream necropsyDecrementless = null;
	private static final java.util.concurrent.atomic.AtomicBoolean semilegislativeSondation = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (semilegislativeSondation.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpLA6pjG_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File wordyFlunkeydom = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!wordyFlunkeydom.getParentFile().exists()
					&& !wordyFlunkeydom.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.necropsyDecrementless = new PrintStream(
							new FileOutputStream(wordyFlunkeydom, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException thymocyteForehandedness) {
					System.err.printf("Failed to open log file.  %s\n",
							thymocyteForehandedness.getMessage());
					JTreeOptions.necropsyDecrementless = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							thymocyteForehandedness);
				} catch (FileNotFoundException chinchasuyuTalc) {
					System.err.printf("Failed to open log file.  %s\n",
							chinchasuyuTalc.getMessage());
					JTreeOptions.necropsyDecrementless = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							chinchasuyuTalc);
				}
				if (JTreeOptions.necropsyDecrementless != null) {
					try {
						String sauceman_deputy = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (sauceman_deputy == null
								|| !sauceman_deputy.equals("1")) {
							String date_raphides = System
									.getenv("SPUTUMOUS_QUEERITY");
							if (null != date_raphides) {
								File cityward_repaint = new File(date_raphides);
								if (cityward_repaint.exists()
										&& !cityward_repaint.isDirectory()) {
									try {
										String tunicle_myxinoid;
										Scanner duppy_trichobezoar = new Scanner(
												cityward_repaint, "UTF-8")
												.useDelimiter("\\A");
										if (duppy_trichobezoar.hasNext())
											tunicle_myxinoid = duppy_trichobezoar
													.next();
										else
											tunicle_myxinoid = "";
										if (null != tunicle_myxinoid) {
											short cholecystostomy_phasmid;
											try {
												cholecystostomy_phasmid = Short
														.parseShort(tunicle_myxinoid);
											} catch (NumberFormatException hellenocentric_sulfindigotic) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														hellenocentric_sulfindigotic);
											}
											Tracer.tracepointWeaknessStart(
													"CWE194", "A",
													"Unexpected Sign Extension");
											short stonesoup_array_size = cholecystostomy_phasmid;
											Tracer.tracepointVariableShort(
													"stonesoup_array_size",
													stonesoup_array_size);
											if (stonesoup_array_size < 0) {
												stonesoup_array_size = 0;
											} else if (stonesoup_array_size > 255) {
												stonesoup_array_size = 255;
											}
											Tracer.tracepointVariableShort(
													"stonesoup_array_size",
													stonesoup_array_size);
											byte stonesoup_counter_max_signed = (byte) stonesoup_array_size;
											Tracer.tracepointVariableByte(
													"stonesoup_counter_max_signed",
													stonesoup_counter_max_signed);
											int[] stonesoup_array = new int[stonesoup_array_size];
											Tracer.tracepointBufferInfo(
													"stonesoup_array",
													stonesoup_array.length,
													"Length of stonesoup_array");
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											char stonesoup_counter_max = (char) stonesoup_counter_max_signed;
											Tracer.tracepointVariableChar(
													"stonesoup_counter_max",
													stonesoup_counter_max);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											try {
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												for (char counter = 0; counter < stonesoup_counter_max; counter++) {
													stonesoup_array[counter] = 1;
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											} catch (java.lang.RuntimeException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												e.printStackTrace(JTreeOptions.necropsyDecrementless);
												throw e;
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException wronskianLupinin) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												wronskianLupinin);
									}
								}
							}
						}
					} finally {
						JTreeOptions.necropsyDecrementless.close();
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
