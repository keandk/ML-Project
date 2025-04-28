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
import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



public class JTreeOptions {

	static PrintStream cryophorusTyphonic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean melologueMacrocyst = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (melologueMacrocyst.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpthc1xt_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File biffinSullenly = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!biffinSullenly.getParentFile().exists()
					&& !biffinSullenly.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.cryophorusTyphonic = new PrintStream(
							new FileOutputStream(biffinSullenly, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException diactScumbling) {
					System.err.printf("Failed to open log file.  %s\n",
							diactScumbling.getMessage());
					JTreeOptions.cryophorusTyphonic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							diactScumbling);
				} catch (FileNotFoundException trabeaeFestivally) {
					System.err.printf("Failed to open log file.  %s\n",
							trabeaeFestivally.getMessage());
					JTreeOptions.cryophorusTyphonic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							trabeaeFestivally);
				}
				if (JTreeOptions.cryophorusTyphonic != null) {
					try {
						String uncollectedly_shiftlessness = System
								.getenv("CHIEFLING_BEPAID");
						if (null != uncollectedly_shiftlessness) {
							int intranational_tabic;
							try {
								intranational_tabic = Integer
										.parseInt(uncollectedly_shiftlessness);
							} catch (NumberFormatException aethusa_fid) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										aethusa_fid);
							}
							Tracer.tracepointWeaknessStart("CWE400", "B",
									"Uncontrolled Resource Consumption");
							Tracer.tracepointMessage("Create pool");
							ExecutorService pool = Executors
									.newFixedThreadPool(20);
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							if (intranational_tabic > 0
									&& intranational_tabic <= Integer.MAX_VALUE) {
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
								Tracer.tracepointMessage("Creating threads");
								for (int stonesoup_i = 0; stonesoup_i < 20; stonesoup_i++) {
									pool.execute(new Factorial(
											intranational_tabic,
											JTreeOptions.cryophorusTyphonic));
								}
								pool.shutdown();
								Tracer.tracepointMessage("Shutdown pool");
							}
							try {
								Tracer.tracepointMessage("Joining threads");
								while (!pool.awaitTermination(1,
										TimeUnit.SECONDS)) {
								}
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("Joined threads");
								JTreeOptions.cryophorusTyphonic
										.println("finished evaluating");
							} catch (InterruptedException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								JTreeOptions.cryophorusTyphonic
										.println("Thread pool interrupted");
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.cryophorusTyphonic.close();
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
					"/tmp/tmpthc1xt_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"Factorial.ctor");
			this.stonesoup_value = fact;
			this.stonesoup_output = output;
		}

		@Override
		public void run() {
			Tracer.tracepointLocation(
					"/tmp/tmpthc1xt_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					Thread.currentThread().getName() + ": Factorial.run");
			calculateFactorial();
		}

		public void calculateFactorial() {
			Tracer.tracepointLocation(
					"/tmp/tmpthc1xt_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
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
