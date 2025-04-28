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

	static PrintStream univorousTylopoda = null;
	private static final java.util.concurrent.atomic.AtomicBoolean perfectlyCapparidaceae = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (perfectlyCapparidaceae.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpsxYs1__ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File antichurchCottagers = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!antichurchCottagers.getParentFile().exists()
					&& !antichurchCottagers.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.univorousTylopoda = new PrintStream(
							new FileOutputStream(antichurchCottagers, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException preignitionCuneate) {
					System.err.printf("Failed to open log file.  %s\n",
							preignitionCuneate.getMessage());
					JTreeOptions.univorousTylopoda = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							preignitionCuneate);
				} catch (FileNotFoundException administrantSublieutenancy) {
					System.err.printf("Failed to open log file.  %s\n",
							administrantSublieutenancy.getMessage());
					JTreeOptions.univorousTylopoda = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							administrantSublieutenancy);
				}
				if (JTreeOptions.univorousTylopoda != null) {
					try {
						String digenetica_livability = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (digenetica_livability == null
								|| !digenetica_livability.equals("1")) {
							String amender_fenestrated = System
									.getenv("NECROMANCY_CLOCKLIKE");
							if (null != amender_fenestrated) {
								File positive_astatize = new File(
										amender_fenestrated);
								if (positive_astatize.exists()
										&& !positive_astatize.isDirectory()) {
									try {
										String populousness_convallaria;
										Scanner lithesome_cretinoid = new Scanner(
												positive_astatize, "UTF-8")
												.useDelimiter("\\A");
										if (lithesome_cretinoid.hasNext())
											populousness_convallaria = lithesome_cretinoid
													.next();
										else
											populousness_convallaria = "";
										if (null != populousness_convallaria) {
											short oopak_floccillation;
											try {
												oopak_floccillation = Short
														.parseShort(populousness_convallaria);
											} catch (NumberFormatException shadrach_caulotaxis) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														shadrach_caulotaxis);
											}
											Tracer.tracepointWeaknessStart(
													"CWE190", "B",
													"Integer Overflow or Wraparound");
											short stonesoup_checked_value = oopak_floccillation;
											Tracer.tracepointVariableShort(
													"stonesoup_checked_value",
													stonesoup_checked_value);
											if (stonesoup_checked_value <= 0) {
												stonesoup_checked_value = 1;
												JTreeOptions.univorousTylopoda
														.println("resetting value to 1");
											}
											Tracer.tracepointVariableShort(
													"stonesoup_checked_value",
													stonesoup_checked_value);
											short stonesoup_counter = 2;
											Tracer.tracepointVariableShort(
													"stonesoup_counter",
													stonesoup_counter);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											int lttngCtr = 99;
											while (stonesoup_counter < 10) {
												JTreeOptions.univorousTylopoda
														.println("Loop #"
																+ stonesoup_counter);
												if (stonesoup_counter > 0) {
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													stonesoup_counter += stonesoup_checked_value;
												}
												if (stonesoup_counter > 0
														|| ++lttngCtr >= 100) {
													lttngCtr = 1;
													Tracer.tracepointVariableShort(
															"stonesoup_counter",
															stonesoup_counter);
												}
											}
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											Tracer.tracepointVariableShort(
													"stonesoup_counter",
													stonesoup_counter);
											JTreeOptions.univorousTylopoda
													.println("finished evaluating");
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException amnionataPericyclone) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												amnionataPericyclone);
									}
								}
							}
						}
					} finally {
						JTreeOptions.univorousTylopoda.close();
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
