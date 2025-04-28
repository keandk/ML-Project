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

	static PrintStream barishUnboundless = null;
	private static final java.util.concurrent.atomic.AtomicBoolean tetartemorionAerially = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (tetartemorionAerially.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpPAhpC4_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File adenographicIntramedullary = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!adenographicIntramedullary.getParentFile().exists()
					&& !adenographicIntramedullary.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.barishUnboundless = new PrintStream(
							new FileOutputStream(adenographicIntramedullary,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException reverberativeLidded) {
					System.err.printf("Failed to open log file.  %s\n",
							reverberativeLidded.getMessage());
					JTreeOptions.barishUnboundless = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							reverberativeLidded);
				} catch (FileNotFoundException mescalismLeibnitzianism) {
					System.err.printf("Failed to open log file.  %s\n",
							mescalismLeibnitzianism.getMessage());
					JTreeOptions.barishUnboundless = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							mescalismLeibnitzianism);
				}
				if (JTreeOptions.barishUnboundless != null) {
					try {
						String rondino_counterindicate = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (rondino_counterindicate == null
								|| !rondino_counterindicate.equals("1")) {
							String pseudoinfluenza_meconioid = System
									.getenv("THOUGHTY_HETEROCENTRIC");
							if (null != pseudoinfluenza_meconioid) {
								File investigatorial_neighborship = new File(
										pseudoinfluenza_meconioid);
								if (investigatorial_neighborship.exists()
										&& !investigatorial_neighborship
												.isDirectory()) {
									try {
										String jumada_unequaled;
										Scanner aesthetical_dextrously = new Scanner(
												investigatorial_neighborship,
												"UTF-8").useDelimiter("\\A");
										if (aesthetical_dextrously.hasNext())
											jumada_unequaled = aesthetical_dextrously
													.next();
										else
											jumada_unequaled = "";
										if (null != jumada_unequaled) {
											short presophomore_campground;
											try {
												presophomore_campground = Short
														.parseShort(jumada_unequaled);
											} catch (NumberFormatException skimmity_pleurobrachia) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														skimmity_pleurobrachia);
											}
											Tracer.tracepointWeaknessStart(
													"CWE191", "A",
													"Integer Underflow (Wrap or Wraparound)");
											short stonesoup_checked_value = presophomore_campground;
											Tracer.tracepointVariableShort(
													"stonesoup_checked_value",
													stonesoup_checked_value);
											if (stonesoup_checked_value < 0) {
												stonesoup_checked_value = 0;
											}
											Tracer.tracepointVariableShort(
													"stonesoup_checked_value",
													stonesoup_checked_value);
											Short[] stonesoup_some_values = new Short[] {
													0, 1, 2, 3, 4, 5, 6, 7, 8,
													9, 10, 11, 12, 13, 14, 15,
													16, 17, 18, 19, 20 };
											short stonesoup_counter = -20;
											short stonesoup_offset = 40;
											Tracer.tracepointBufferInfo(
													"stonesoup_some_values",
													stonesoup_some_values.length,
													"Length of stonesoup_some_values");
											Tracer.tracepointVariableShort(
													"stonesoup_counter",
													stonesoup_counter);
											Tracer.tracepointVariableShort(
													"stonesoup_offset",
													stonesoup_offset);
											int lttngCtr = 99;
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
											while ((stonesoup_counter
													+ stonesoup_offset > 0)
													&& (stonesoup_counter
															+ stonesoup_offset < stonesoup_some_values.length)) {
												JTreeOptions.barishUnboundless
														.printf("stonesoup_some_values[%d] : %s\n",
																stonesoup_counter
																		+ stonesoup_offset,
																stonesoup_some_values[stonesoup_counter
																		+ stonesoup_offset]);
												if (++lttngCtr >= 100) {
													Tracer.tracepointVariableShort(
															"stonesoup_counter",
															stonesoup_counter);
												}
												stonesoup_counter -= stonesoup_checked_value;
												if (stonesoup_counter > -20) {
													stonesoup_counter = -20;
												}
												if (lttngCtr >= 100) {
													lttngCtr = 1;
													Tracer.tracepointVariableShort(
															"stonesoup_counter",
															stonesoup_counter);
												}
											}
											Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											Tracer.tracepointBufferInfo(
													"stonesoup_some_values",
													stonesoup_some_values.length,
													"Length of stonesoup_some_values");
											Tracer.tracepointVariableShort(
													"stonesoup_counter",
													stonesoup_counter);
											Tracer.tracepointVariableShort(
													"stonesoup_offset",
													stonesoup_offset);
											JTreeOptions.barishUnboundless
													.println("finished evaluating");
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException unwarbledGlen) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												unwarbledGlen);
									}
								}
							}
						}
					} finally {
						JTreeOptions.barishUnboundless.close();
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
