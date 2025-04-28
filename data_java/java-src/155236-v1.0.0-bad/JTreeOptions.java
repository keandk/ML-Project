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

	static PrintStream daktylonFeued = null;
	private static final java.util.concurrent.atomic.AtomicBoolean depetticoatPreresemble = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (depetticoatPreresemble.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpeMhF9h_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File enaliosaurEleusinion = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!enaliosaurEleusinion.getParentFile().exists()
					&& !enaliosaurEleusinion.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.daktylonFeued = new PrintStream(
							new FileOutputStream(enaliosaurEleusinion, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException clearingBioclimatology) {
					System.err.printf("Failed to open log file.  %s\n",
							clearingBioclimatology.getMessage());
					JTreeOptions.daktylonFeued = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							clearingBioclimatology);
				} catch (FileNotFoundException brainwaterHausmannite) {
					System.err.printf("Failed to open log file.  %s\n",
							brainwaterHausmannite.getMessage());
					JTreeOptions.daktylonFeued = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							brainwaterHausmannite);
				}
				if (JTreeOptions.daktylonFeued != null) {
					try {
						String undeliberating_citrullin = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (undeliberating_citrullin == null
								|| !undeliberating_citrullin.equals("1")) {
							String closestool_pomegranate = System
									.getenv("UROGLAUCIN_XANTHOCHROOUS");
							if (null != closestool_pomegranate) {
								File cholelithotomy_parochine = new File(
										closestool_pomegranate);
								if (cholelithotomy_parochine.exists()
										&& !cholelithotomy_parochine
												.isDirectory()) {
									try {
										String unacceptance_alkahestical;
										Scanner catalanganes_unapprisedly = new Scanner(
												cholelithotomy_parochine,
												"UTF-8").useDelimiter("\\A");
										if (catalanganes_unapprisedly.hasNext())
											unacceptance_alkahestical = catalanganes_unapprisedly
													.next();
										else
											unacceptance_alkahestical = "";
										if (null != unacceptance_alkahestical) {
											short unforsaken_energeia;
											try {
												unforsaken_energeia = Short
														.parseShort(unacceptance_alkahestical);
											} catch (NumberFormatException insalutary_waterfront) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														insalutary_waterfront);
											}
											Tracer.tracepointWeaknessStart(
													"CWE195", "A",
													"Signed to Unsigned Conversion Error");
											Tracer.tracepointVariableShort(
													"value",
													unforsaken_energeia);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											int[] stonesoup_array = new int[Math
													.abs(unforsaken_energeia)];
											char stonesoup_max_char = (char) ((short) unforsaken_energeia);
											Tracer.tracepointBufferInfo(
													"stonesoup_array",
													stonesoup_array.length,
													"Length of stonesoup_array");
											Tracer.tracepointVariableChar(
													"stonesoup_max_char",
													stonesoup_max_char);
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											try {
												Tracer.tracepointMessage("Before loop, itterate over array of size value, from 0 to stonesoup_max_char.");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												for (char stonesoup_counter = 0; stonesoup_counter < stonesoup_max_char; stonesoup_counter++) {
													JTreeOptions.daktylonFeued
															.printf("Counter value: \"%c\"\n",
																	stonesoup_counter);
													stonesoup_array[stonesoup_counter] = 0;
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											} catch (RuntimeException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												e.printStackTrace(JTreeOptions.daktylonFeued);
												throw e;
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException lymphorrhagicAchango) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												lymphorrhagicAchango);
									}
								}
							}
						}
					} finally {
						JTreeOptions.daktylonFeued.close();
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
