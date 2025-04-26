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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;



public class JTreeOptions {

	static PrintStream molassiedGloryingly = null;
	private static final java.util.concurrent.atomic.AtomicBoolean vivificatorHalophyte = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (vivificatorHalophyte.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpuJfBmR_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File proctatresiaPropylite = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!proctatresiaPropylite.getParentFile().exists()
					&& !proctatresiaPropylite.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.molassiedGloryingly = new PrintStream(
							new FileOutputStream(proctatresiaPropylite, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException marikinaAbram) {
					System.err.printf("Failed to open log file.  %s\n",
							marikinaAbram.getMessage());
					JTreeOptions.molassiedGloryingly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							marikinaAbram);
				} catch (FileNotFoundException croakyPaleogeography) {
					System.err.printf("Failed to open log file.  %s\n",
							croakyPaleogeography.getMessage());
					JTreeOptions.molassiedGloryingly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							croakyPaleogeography);
				}
				if (JTreeOptions.molassiedGloryingly != null) {
					try {
						String substitutable_deadheartedly = System
								.getenv("INERASABLENESS_TRISULCATE");
						if (null != substitutable_deadheartedly) {
							int saxtie_norroy;
							try {
								saxtie_norroy = Integer
										.parseInt(substitutable_deadheartedly);
							} catch (NumberFormatException latiseptate_untrammeledness) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										latiseptate_untrammeledness);
							}
							Tracer.tracepointWeaknessStart("CWE606", "B",
									"Uncheck Input for Loop Condition");
							char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
									.toCharArray();
							SecureRandom random = null;
							try {
								random = SecureRandom.getInstance("SHA1PRNG");
							} catch (NoSuchAlgorithmException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								JTreeOptions.molassiedGloryingly
										.println("STONESOUP: Random generator algorithm does not exist.");
							}
							Tracer.tracepointVariableInt("value", saxtie_norroy);
							if (random != null) {
								StringBuilder stonesoup_filename = new StringBuilder();
								JTreeOptions.molassiedGloryingly
										.println("Generating file name");
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								for (int stonesoup_counter = 0; stonesoup_counter < saxtie_norroy; stonesoup_counter++) {
									stonesoup_filename
											.append(stonesoup_random_charset[random
													.nextInt(stonesoup_random_charset.length)]);
								}
								Tracer.tracepointVariableString(
										"stonesoup_filename",
										stonesoup_filename.toString());
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								if (stonesoup_filename.length() > 0) {
									File writePath = new File(
											stonesoup_filename.toString());
									try {
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										writePath.createNewFile();
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (IOException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										JTreeOptions.molassiedGloryingly
												.println("Failed to create file.");
										JTreeOptions.molassiedGloryingly
												.println("Error:");
										e.printStackTrace(JTreeOptions.molassiedGloryingly);
										throw new RuntimeException(
												"Unknown error in filename.", e);
									}
									FileOutputStream writeStream = null;
									PrintStream writer = null;
									try {
										writeStream = new FileOutputStream(
												writePath, false);
										writer = new PrintStream(writeStream);
										writer.println("/* This is my file */");
									} catch (FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										JTreeOptions.molassiedGloryingly
												.println("Failed to create file.");
										e.printStackTrace(JTreeOptions.molassiedGloryingly);
									} finally {
										if (writer != null) {
											writer.close();
										}
									}
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.molassiedGloryingly.close();
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
