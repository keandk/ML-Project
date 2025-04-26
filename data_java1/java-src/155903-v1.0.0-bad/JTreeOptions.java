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
import java.io.IOException;



public class JTreeOptions {

	static PrintStream palmyrenianImeritian = null;
	private static final java.util.concurrent.atomic.AtomicBoolean bedeafUntender = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (bedeafUntender.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpZMd7Wf_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File haberdasheressGulfside = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!haberdasheressGulfside.getParentFile().exists()
					&& !haberdasheressGulfside.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.palmyrenianImeritian = new PrintStream(
							new FileOutputStream(haberdasheressGulfside, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException unhootedCohere) {
					System.err.printf("Failed to open log file.  %s\n",
							unhootedCohere.getMessage());
					JTreeOptions.palmyrenianImeritian = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unhootedCohere);
				} catch (FileNotFoundException happifyRadish) {
					System.err.printf("Failed to open log file.  %s\n",
							happifyRadish.getMessage());
					JTreeOptions.palmyrenianImeritian = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							happifyRadish);
				}
				if (JTreeOptions.palmyrenianImeritian != null) {
					try {
						String apophonia_construction = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (apophonia_construction == null
								|| !apophonia_construction.equals("1")) {
							String benzolate_cytology = System
									.getenv("AUTOGENETICALLY_PUTRIFORM");
							if (null != benzolate_cytology) {
								File spondee_pterylology = new File(
										benzolate_cytology);
								if (spondee_pterylology.exists()
										&& !spondee_pterylology.isDirectory()) {
									try {
										String adularescence_cacospermia;
										Scanner puzzlehead_asymptotically = new Scanner(
												spondee_pterylology, "UTF-8")
												.useDelimiter("\\A");
										if (puzzlehead_asymptotically.hasNext())
											adularescence_cacospermia = puzzlehead_asymptotically
													.next();
										else
											adularescence_cacospermia = "";
										if (null != adularescence_cacospermia) {
											int broombush_stereography;
											try {
												broombush_stereography = Integer
														.parseInt(adularescence_cacospermia);
											} catch (NumberFormatException homeric_myriadth) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														homeric_myriadth);
											}
											Tracer.tracepointWeaknessStart(
													"CWE774", "A",
													"Allocation of File Descriptors or Handles Without Limits or Throttling");
											FileOutputStream[] stonesoup_sources = new FileOutputStream[broombush_stereography];
											int stonesoup_active_files = 0;
											Tracer.tracepointBufferInfo(
													"stonesoup_sources",
													stonesoup_sources.length,
													"Length of stonesoup_sources");
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											for (int stonesoup_counter = 0; stonesoup_counter < broombush_stereography; stonesoup_counter++) {
												try {
													stonesoup_sources[stonesoup_counter] = new FileOutputStream(
															String.format(
																	"/opt/stonesoup/workspace/testData/test%10d",
																	stonesoup_counter));
												} catch (FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													JTreeOptions.palmyrenianImeritian
															.println("Failed to create new file.");
													e.printStackTrace(JTreeOptions.palmyrenianImeritian);
													throw new RuntimeException(
															e);
												}
												stonesoup_active_files++;
												JTreeOptions.palmyrenianImeritian
														.println(stonesoup_counter);
											}
											Tracer.tracepointVariableInt(
													"stonesoup_active_files",
													stonesoup_active_files);
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											for (int stonesoup_counter = 0; stonesoup_counter < stonesoup_active_files; stonesoup_counter++) {
												try {
													if (stonesoup_sources[stonesoup_counter] != null) {
														stonesoup_sources[stonesoup_counter]
																.close();
													}
												} catch (IOException e) {
													JTreeOptions.palmyrenianImeritian
															.println("Failed to close file.");
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException bureaucraticalHoistman) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												bureaucraticalHoistman);
									}
								}
							}
						}
					} finally {
						JTreeOptions.palmyrenianImeritian.close();
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
