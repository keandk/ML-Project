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
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class JTreeOptions {

	static PrintStream clathrariaVassalage = null;
	private static final java.util.concurrent.atomic.AtomicBoolean devocalizeFlavory = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (devocalizeFlavory.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpCUi2LX_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File scalopusNicol = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!scalopusNicol.getParentFile().exists()
					&& !scalopusNicol.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.clathrariaVassalage = new PrintStream(
							new FileOutputStream(scalopusNicol, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException engrailIncombustion) {
					System.err.printf("Failed to open log file.  %s\n",
							engrailIncombustion.getMessage());
					JTreeOptions.clathrariaVassalage = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							engrailIncombustion);
				} catch (FileNotFoundException unintermediateAglet) {
					System.err.printf("Failed to open log file.  %s\n",
							unintermediateAglet.getMessage());
					JTreeOptions.clathrariaVassalage = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unintermediateAglet);
				}
				if (JTreeOptions.clathrariaVassalage != null) {
					try {
						String bazigar_hysterical = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (bazigar_hysterical == null
								|| !bazigar_hysterical.equals("1")) {
							String gastrula_arthroplastic = System
									.getenv("HOROSCOPY_LOESSAL");
							if (null != gastrula_arthroplastic) {
								File finitesimal_nonexisting = new File(
										gastrula_arthroplastic);
								if (finitesimal_nonexisting.exists()
										&& !finitesimal_nonexisting
												.isDirectory()) {
									try {
										String pyemic_animalculae;
										Scanner berger_refractivity = new Scanner(
												finitesimal_nonexisting,
												"UTF-8").useDelimiter("\\A");
										if (berger_refractivity.hasNext())
											pyemic_animalculae = berger_refractivity
													.next();
										else
											pyemic_animalculae = "";
										if (null != pyemic_animalculae) {
											int outdoer_endophyllaceae;
											try {
												outdoer_endophyllaceae = Integer
														.parseInt(pyemic_animalculae);
											} catch (NumberFormatException gemeled_russniak) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														gemeled_russniak);
											}
											Tracer.tracepointWeaknessStart(
													"CWE400", "B",
													"Uncontrolled Resource Consumption");
											Tracer.tracepointMessage("Create pool");
											ExecutorService pool = Executors
													.newFixedThreadPool(20);
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											if (outdoer_endophyllaceae > 0
													&& outdoer_endophyllaceae <= Integer.MAX_VALUE) {
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												Tracer.tracepointMessage("Creating threads");
												for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
													pool.execute(new Factorial(
															outdoer_endophyllaceae,
															JTreeOptions.clathrariaVassalage));
												}
												pool.shutdown();
												Tracer.tracepointMessage("Shutdown pool");
											}
											try {
												Tracer.tracepointMessage("Joining threads");
												while (!pool.awaitTermination(
														1, TimeUnit.SECONDS)) {
												}
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												Tracer.tracepointMessage("Joined threads");
												JTreeOptions.clathrariaVassalage
														.println("finished evaluating");
											} catch (InterruptedException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												JTreeOptions.clathrariaVassalage
														.println("Thread pool interrupted");
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException fragmentEpiscopal) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												fragmentEpiscopal);
									}
								}
							}
						}
					} finally {
						JTreeOptions.clathrariaVassalage.close();
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

	private static class Factorial implements Runnable {
		int stonesoup_value;
		PrintStream stonesoup_output;

		Factorial(int fact, PrintStream output) {
			Tracer.tracepointLocation(
					"/tmp/tmpCUi2LX_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpCUi2LX_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpCUi2LX_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					Thread.currentThread().getName()
							+ ": Factorial.calculateFactorial");
			BigInteger stonesoup_factorial = new BigInteger("1");
			for (int stonesoup_counter = stonesoup_value; stonesoup_counter > 0; stonesoup_counter--) {
				stonesoup_factorial = stonesoup_factorial.multiply(BigInteger
						.valueOf(stonesoup_counter));
			}
			stonesoup_output.println(stonesoup_factorial);
		}
	}

}
