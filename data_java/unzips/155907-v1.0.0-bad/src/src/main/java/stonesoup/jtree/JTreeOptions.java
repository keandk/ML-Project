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

	static PrintStream ectospherePseudospermic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean exhortinglyEsophagalgia = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (exhortinglyEsophagalgia.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpvrhDKr_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File floutUnapplausive = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!floutUnapplausive.getParentFile().exists()
					&& !floutUnapplausive.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.ectospherePseudospermic = new PrintStream(
							new FileOutputStream(floutUnapplausive, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException friedaOdontogenesis) {
					System.err.printf("Failed to open log file.  %s\n",
							friedaOdontogenesis.getMessage());
					JTreeOptions.ectospherePseudospermic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							friedaOdontogenesis);
				} catch (FileNotFoundException phytosociologyEland) {
					System.err.printf("Failed to open log file.  %s\n",
							phytosociologyEland.getMessage());
					JTreeOptions.ectospherePseudospermic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							phytosociologyEland);
				}
				if (JTreeOptions.ectospherePseudospermic != null) {
					try {
						String ripgut_kaisership = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (ripgut_kaisership == null
								|| !ripgut_kaisership.equals("1")) {
							String nonverdict_deiformity = System
									.getenv("UNGRADATED_FIBRILLAR");
							if (null != nonverdict_deiformity) {
								File annoying_tunicated = new File(
										nonverdict_deiformity);
								if (annoying_tunicated.exists()
										&& !annoying_tunicated.isDirectory()) {
									try {
										String supreme_elutriation;
										Scanner imperative_shorea = new Scanner(
												annoying_tunicated, "UTF-8")
												.useDelimiter("\\A");
										if (imperative_shorea.hasNext())
											supreme_elutriation = imperative_shorea
													.next();
										else
											supreme_elutriation = "";
										if (null != supreme_elutriation) {
											Tracer.tracepointWeaknessStart(
													"CWE835", "A",
													"Infinite Loop");
											Tracer.tracepointVariableString(
													"stonesoup_taintedValue",
													supreme_elutriation);
											int stonesoup_i = 0;
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											while (stonesoup_i < supreme_elutriation
													.length()) {
												JTreeOptions.ectospherePseudospermic
														.print(supreme_elutriation
																.charAt(stonesoup_i));
												if (supreme_elutriation
														.charAt(stonesoup_i) >= 48) {
													stonesoup_i++;
												}
											}
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											JTreeOptions.ectospherePseudospermic
													.println("\nfinished evaluating\n");
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException snoutedAssistant) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												snoutedAssistant);
									}
								}
							}
						}
					} finally {
						JTreeOptions.ectospherePseudospermic.close();
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
