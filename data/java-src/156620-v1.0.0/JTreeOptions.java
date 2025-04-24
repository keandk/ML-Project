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
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class JTreeOptions {

	static PrintStream gortoniteEcchondrotome = null;
	private static final java.util.concurrent.atomic.AtomicBoolean unkindredForewarm = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (unkindredForewarm.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpP2F6JM_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File patroonThermic = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!patroonThermic.getParentFile().exists()
					&& !patroonThermic.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.gortoniteEcchondrotome = new PrintStream(
							new FileOutputStream(patroonThermic, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException subbromidePinna) {
					System.err.printf("Failed to open log file.  %s\n",
							subbromidePinna.getMessage());
					JTreeOptions.gortoniteEcchondrotome = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							subbromidePinna);
				} catch (FileNotFoundException cloysomeErgomaniac) {
					System.err.printf("Failed to open log file.  %s\n",
							cloysomeErgomaniac.getMessage());
					JTreeOptions.gortoniteEcchondrotome = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							cloysomeErgomaniac);
				}
				if (JTreeOptions.gortoniteEcchondrotome != null) {
					try {
						String culprit_margin = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (culprit_margin == null
								|| !culprit_margin.equals("1")) {
							String phasemy_holohedrism = System
									.getenv("URCEUS_TRADING");
							if (null != phasemy_holohedrism) {
								File behaviored_questionnaire = new File(
										phasemy_holohedrism);
								if (behaviored_questionnaire.exists()
										&& !behaviored_questionnaire
												.isDirectory()) {
									try {
										String terminable_simpleheartedly;
										Scanner disemboguement_astelic = new Scanner(
												behaviored_questionnaire,
												"UTF-8").useDelimiter("\\A");
										if (disemboguement_astelic.hasNext())
											terminable_simpleheartedly = disemboguement_astelic
													.next();
										else
											terminable_simpleheartedly = "";
										if (null != terminable_simpleheartedly) {
											Tracer.tracepointWeaknessStart(
													"CWE606", "A",
													"Unchecked Input for Loop Condition");
											String valueString = terminable_simpleheartedly
													.trim();
											Pattern stonesoup_rel_path_pattern = Pattern
													.compile("(^|/)\\.\\.?/");
											Matcher rel_path_match = stonesoup_rel_path_pattern
													.matcher(valueString);
											Tracer.tracepointVariableString(
													"value",
													terminable_simpleheartedly);
											Tracer.tracepointVariableString(
													"valueString", valueString);
											if (valueString.length() == 0
													|| valueString
															.startsWith("/")
													|| rel_path_match.find()) {
												JTreeOptions.gortoniteEcchondrotome
														.println("Path traversal identified, discarding request.");
											} else {
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												java.io.File checkedPath = new java.io.File(
														valueString);
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												while (!checkedPath.isFile()) {
													try {
														JTreeOptions.gortoniteEcchondrotome
																.printf("File \"%s\" does not exist, sleeping...\n",
																		valueString);
														Thread.sleep(500);
													} catch (InterruptedException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														JTreeOptions.gortoniteEcchondrotome
																.println("Thread interrupted.");
														e.printStackTrace(JTreeOptions.gortoniteEcchondrotome);
													}
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												JTreeOptions.gortoniteEcchondrotome
														.println("Found file.");
												JTreeOptions.gortoniteEcchondrotome
														.printf("Reading \"%s\".\n",
																checkedPath
																		.getPath());
												java.io.BufferedReader reader = null;
												try {
													java.io.FileInputStream fis = new java.io.FileInputStream(
															checkedPath);
													reader = new java.io.BufferedReader(
															new java.io.InputStreamReader(
																	fis));
													String line;
													while ((line = reader
															.readLine()) != null) {
														JTreeOptions.gortoniteEcchondrotome
																.println(line);
													}
												} catch (java.io.FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													JTreeOptions.gortoniteEcchondrotome
															.printf("File \"%s\" does not exist\n",
																	checkedPath
																			.getPath());
												} catch (java.io.IOException ioe) {
													Tracer.tracepointError(ioe
															.getClass()
															.getName()
															+ ": "
															+ ioe.getMessage());
													JTreeOptions.gortoniteEcchondrotome
															.println("Failed to read file.");
												} finally {
													try {
														if (reader != null) {
															reader.close();
														}
													} catch (java.io.IOException e) {
														JTreeOptions.gortoniteEcchondrotome
																.println("STONESOUP: Closing file quietly.");
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException jargonerCalool) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												jargonerCalool);
									}
								}
							}
						}
					} finally {
						JTreeOptions.gortoniteEcchondrotome.close();
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
