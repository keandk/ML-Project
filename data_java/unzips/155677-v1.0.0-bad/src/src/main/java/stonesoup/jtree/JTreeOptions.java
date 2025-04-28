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

	static PrintStream diabetogenicTweil = null;
	private static final java.util.concurrent.atomic.AtomicBoolean amazoniteCondylarthrosis = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (amazoniteCondylarthrosis.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpiJWQfq_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File undeflectedTrophosome = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!undeflectedTrophosome.getParentFile().exists()
					&& !undeflectedTrophosome.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.diabetogenicTweil = new PrintStream(
							new FileOutputStream(undeflectedTrophosome, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException alluviaTailorcraft) {
					System.err.printf("Failed to open log file.  %s\n",
							alluviaTailorcraft.getMessage());
					JTreeOptions.diabetogenicTweil = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							alluviaTailorcraft);
				} catch (FileNotFoundException craniologistSurrealist) {
					System.err.printf("Failed to open log file.  %s\n",
							craniologistSurrealist.getMessage());
					JTreeOptions.diabetogenicTweil = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							craniologistSurrealist);
				}
				if (JTreeOptions.diabetogenicTweil != null) {
					try {
						String unassimilable_vick = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (unassimilable_vick == null
								|| !unassimilable_vick.equals("1")) {
							String figureless_conjugal = System
									.getenv("CARTABLE_COMPRIEST");
							if (null != figureless_conjugal) {
								File tectocephalic_precedently = new File(
										figureless_conjugal);
								if (tectocephalic_precedently.exists()
										&& !tectocephalic_precedently
												.isDirectory()) {
									try {
										String phytographer_precelebrant;
										Scanner mulcibirian_planate = new Scanner(
												tectocephalic_precedently,
												"UTF-8").useDelimiter("\\A");
										if (mulcibirian_planate.hasNext())
											phytographer_precelebrant = mulcibirian_planate
													.next();
										else
											phytographer_precelebrant = "";
										if (null != phytographer_precelebrant) {
											int sierran_matachin;
											try {
												sierran_matachin = Integer
														.parseInt(phytographer_precelebrant);
											} catch (NumberFormatException repromulgate_coryphene) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														repromulgate_coryphene);
											}
											stonesoup_sources = new ArrayList<FileOutputStream>();
											Tracer.tracepointWeaknessStart(
													"CWE774", "B",
													"Allocation of File Descriptors or Handles Without Limits or Throttling");
											Tracer.tracepointBufferInfo(
													"stonesoup_sources",
													stonesoup_sources.size(),
													"Length of stonesoup_sources");
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											Tracer.tracepointMessage("TRIGGER-POINT; BEFORE");
											for (int stonesoup_counter = 0; stonesoup_counter < sierran_matachin; stonesoup_counter++) {
												try {
													stonesoup_sources
															.add(new FileOutputStream(
																	String.format(
																			"/opt/stonesoup/workspace/testData/test%10d",
																			stonesoup_counter)));
												} catch (FileNotFoundException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													JTreeOptions.diabetogenicTweil
															.println("Failed to create new file, moving on.");
												}
												JTreeOptions.diabetogenicTweil
														.println(stonesoup_counter);
											}
											Tracer.tracepointBufferInfo(
													"stonesoup_sources",
													stonesoup_sources.size(),
													"Length of stonesoup_sources");
											Tracer.tracepointMessage("TRIGGER-POINT; AFTER");
											Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException antecurvatureOvermuchness) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												antecurvatureOvermuchness);
									}
								}
							}
						}
					} finally {
						JTreeOptions.diabetogenicTweil.close();
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

	public static ArrayList<FileOutputStream> stonesoup_sources = null;

}
