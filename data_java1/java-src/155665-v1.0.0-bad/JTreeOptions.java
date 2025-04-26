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



public class JTreeOptions {

	static PrintStream uropsileBiggin = null;
	private static final java.util.concurrent.atomic.AtomicBoolean rebudgetShammock = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (rebudgetShammock.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp4Woc1I_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File dhaiFerrocyanhydric = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!dhaiFerrocyanhydric.getParentFile().exists()
					&& !dhaiFerrocyanhydric.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.uropsileBiggin = new PrintStream(
							new FileOutputStream(dhaiFerrocyanhydric, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException biphenolHolyday) {
					System.err.printf("Failed to open log file.  %s\n",
							biphenolHolyday.getMessage());
					JTreeOptions.uropsileBiggin = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							biphenolHolyday);
				} catch (FileNotFoundException preaffiliateAntiepiscopist) {
					System.err.printf("Failed to open log file.  %s\n",
							preaffiliateAntiepiscopist.getMessage());
					JTreeOptions.uropsileBiggin = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							preaffiliateAntiepiscopist);
				}
				if (JTreeOptions.uropsileBiggin != null) {
					try {
						String unconstrued_stow = System
								.getenv("OLIGOTOKOUS_DIHYSTERIA");
						if (null != unconstrued_stow) {
							Tracer.tracepointWeaknessStart("CWE835", "A",
									"Infinite Loop");
							Tracer.tracepointVariableString(
									"stonesoup_taintedValue", unconstrued_stow);
							int stonesoup_i = 0;
							Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
							Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
							while (stonesoup_i < unconstrued_stow.length()) {
								JTreeOptions.uropsileBiggin
										.print(unconstrued_stow
												.charAt(stonesoup_i));
								if (unconstrued_stow.charAt(stonesoup_i) >= 48) {
									stonesoup_i++;
								}
							}
							Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
							Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							JTreeOptions.uropsileBiggin
									.println("\nfinished evaluating\n");
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.uropsileBiggin.close();
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
