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
import java.util.Arrays;



public class JTreeOptions {

	static PrintStream thalassocracyTrichorrhexic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean malleableizedCyanometric = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (malleableizedCyanometric.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpEVZcBY_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File adjudicativeCodworm = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!adjudicativeCodworm.getParentFile().exists()
					&& !adjudicativeCodworm.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.thalassocracyTrichorrhexic = new PrintStream(
							new FileOutputStream(adjudicativeCodworm, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException wisseAcinacifolious) {
					System.err.printf("Failed to open log file.  %s\n",
							wisseAcinacifolious.getMessage());
					JTreeOptions.thalassocracyTrichorrhexic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							wisseAcinacifolious);
				} catch (FileNotFoundException ventriloquistTriglyphed) {
					System.err.printf("Failed to open log file.  %s\n",
							ventriloquistTriglyphed.getMessage());
					JTreeOptions.thalassocracyTrichorrhexic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ventriloquistTriglyphed);
				}
				if (JTreeOptions.thalassocracyTrichorrhexic != null) {
					try {
						String arteriodialysis_salpidae = System
								.getenv("ELECTROCUTION_SUPERAQUEOUS");
						if (null != arteriodialysis_salpidae) {
							int walletful_crossway;
							try {
								walletful_crossway = Integer
										.parseInt(arteriodialysis_salpidae);
							} catch (NumberFormatException artocarpous_burbankian) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										artocarpous_burbankian);
							}
							Tracer.tracepointWeaknessStart("CWE789", "A",
									"Uncontrolled Memory Allocation");
							try {
								if (walletful_crossway > 0
										&& walletful_crossway <= Integer.MAX_VALUE) {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									stonesoup_array = new char[walletful_crossway];
									Tracer.tracepointBufferInfo(
											"stonesoup_array",
											stonesoup_array.length,
											"Length of stonesoup_array");
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									Arrays.fill(stonesoup_array, 'x');
									for (int i = 0; i < stonesoup_array.length; i++) {
										JTreeOptions.thalassocracyTrichorrhexic
												.print(stonesoup_array[i]);
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									JTreeOptions.thalassocracyTrichorrhexic
											.println("");
									JTreeOptions.thalassocracyTrichorrhexic
											.println("STONESOUP: successfully initialized array");
								}
							} catch (Error e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								e.printStackTrace(JTreeOptions.thalassocracyTrichorrhexic);
								throw e;
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.thalassocracyTrichorrhexic.close();
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

	static char[] stonesoup_array;

}
