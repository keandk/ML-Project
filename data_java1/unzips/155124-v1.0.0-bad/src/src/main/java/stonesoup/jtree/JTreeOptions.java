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

	static PrintStream turbinelikeAmphibial = null;
	private static final java.util.concurrent.atomic.AtomicBoolean shankpieceEstivator = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (shankpieceEstivator.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp8p2LDw_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			File forswearNonascendancy = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!forswearNonascendancy.getParentFile().exists()
					&& !forswearNonascendancy.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					JTreeOptions.turbinelikeAmphibial = new PrintStream(
							new FileOutputStream(forswearNonascendancy, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException anomaloscopeAcanonical) {
					System.err.printf("Failed to open log file.  %s\n",
							anomaloscopeAcanonical.getMessage());
					JTreeOptions.turbinelikeAmphibial = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							anomaloscopeAcanonical);
				} catch (FileNotFoundException figwortNonteachable) {
					System.err.printf("Failed to open log file.  %s\n",
							figwortNonteachable.getMessage());
					JTreeOptions.turbinelikeAmphibial = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							figwortNonteachable);
				}
				if (JTreeOptions.turbinelikeAmphibial != null) {
					try {
						String anthropological_nitrogenic = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (anthropological_nitrogenic == null
								|| !anthropological_nitrogenic.equals("1")) {
							String nonsparing_urceolate = System
									.getenv("TAENIADA_SUBTERAQUEOUS");
							if (null != nonsparing_urceolate) {
								File iridomalacia_egyptologic = new File(
										nonsparing_urceolate);
								if (iridomalacia_egyptologic.exists()
										&& !iridomalacia_egyptologic
												.isDirectory()) {
									try {
										String leuciferidae_lucule;
										Scanner amargoso_procuratory = new Scanner(
												iridomalacia_egyptologic,
												"UTF-8").useDelimiter("\\A");
										if (amargoso_procuratory.hasNext())
											leuciferidae_lucule = amargoso_procuratory
													.next();
										else
											leuciferidae_lucule = "";
										if (null != leuciferidae_lucule) {
											int hobble_galenic;
											try {
												hobble_galenic = Integer
														.parseInt(leuciferidae_lucule);
											} catch (NumberFormatException unfretting_philanthropize) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														unfretting_philanthropize);
											}
											Tracer.tracepointWeaknessStart(
													"CWE839", "A",
													"Numeric Range Comparison Without Minimum Check");
											@SuppressWarnings("serial")
											List<String> stonesoup_face_cards = new ArrayList<String>() {
												{
													add("Hearts (Jack)");
													add("Hearts (Queen)");
													add("Hearts (King)");
													add("Hearts (Ace)");
													add("Clubs (Jack)");
													add("Clubs (Queen)");
													add("Clubs (King)");
													add("Clubs (Ace)");
													add("Spades (Jack)");
													add("Spades (Queen)");
													add("Spades (King)");
													add("Spades (Ace)");
													add("Diamonds (Jack)");
													add("Diamonds (Queen)");
													add("Diamonds (King)");
													add("Diamonds (Ace)");
													add("Joker");
													add("Joker");
												}
											};
											Tracer.tracepointVariableInt(
													"value", hobble_galenic);
											Tracer.tracepointVariableInt(
													"stonesoup_face_cards.size()",
													stonesoup_face_cards.size());
											Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
											if (hobble_galenic >= stonesoup_face_cards
													.size()) {
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												JTreeOptions.turbinelikeAmphibial
														.printf("Card not available for %d.\n",
																hobble_galenic);
											} else {
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												try {
													Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
													JTreeOptions.turbinelikeAmphibial
															.printf("Selected Card = %s\n",
																	stonesoup_face_cards
																			.get(hobble_galenic));
													Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												} catch (RuntimeException e) {
													Tracer.tracepointError(e
															.getClass()
															.getName()
															+ ": "
															+ e.getMessage());
													e.printStackTrace(JTreeOptions.turbinelikeAmphibial);
													throw e;
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException orbilianDyarchic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												orbilianDyarchic);
									}
								}
							}
						}
					} finally {
						JTreeOptions.turbinelikeAmphibial.close();
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
