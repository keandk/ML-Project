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
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;



public class JTreeOptions {

	static PrintStream patriarchicalOverattachment = null;

	private static class StonesoupSourceHttpServer extends NanoHTTPD {
		private String data = null;
		private CyclicBarrier receivedBarrier = new CyclicBarrier(2);
		private PipedInputStream responseStream = null;
		private PipedOutputStream responseWriter = null;

		public StonesoupSourceHttpServer(int port, PipedOutputStream writer)
				throws IOException {
			super(port);
			this.responseWriter = writer;
		}

		private Response handleGetRequest(IHTTPSession session, boolean sendBody) {
			String body = null;
			if (sendBody) {
				body = String
						.format("Request Approved!\n\n"
								+ "Thank you for you interest in \"%s\".\n\n"
								+ "We appreciate your inquiry.  Please visit us again!",
								session.getUri());
			}
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.OK, NanoHTTPD.MIME_PLAINTEXT,
					body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleOptionsRequest(IHTTPSession session) {
			NanoHTTPD.Response response = new NanoHTTPD.Response(null);
			response.setStatus(NanoHTTPD.Response.Status.OK);
			response.setMimeType(NanoHTTPD.MIME_PLAINTEXT);
			response.addHeader("Allow", "GET, PUT, POST, HEAD, OPTIONS");
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handleUnallowedRequest(IHTTPSession session) {
			String body = String.format("Method Not Allowed!\n\n"
					+ "Thank you for your request, but we are unable "
					+ "to process that method.  Please try back later.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.METHOD_NOT_ALLOWED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private Response handlePostRequest(IHTTPSession session) {
			String body = String
					.format("Request Data Processed!\n\n"
							+ "Thank you for your contribution.  Please keep up the support.");
			NanoHTTPD.Response response = new NanoHTTPD.Response(
					NanoHTTPD.Response.Status.CREATED,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private NanoHTTPD.Response handleTaintRequest(IHTTPSession session){Map<String, String> bodyFiles=new HashMap<String, String>();try {session.parseBody(bodyFiles);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}catch (ResponseException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to parse body.\n" + e.getMessage());}if (!session.getParms().containsKey("data")){return writeErrorResponse(session,Response.Status.BAD_REQUEST,"Missing required field \"data\".");}this.data=session.getParms().get("data");try {this.responseStream=new PipedInputStream(this.responseWriter);} catch (IOException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}NanoHTTPD.Response response=new NanoHTTPD.Response(NanoHTTPD.Response.Status.CREATED,NanoHTTPD.MIME_PLAINTEXT,this.responseStream);this.setResponseOptions(session,response);response.setChunkedTransfer(true);try {this.receivedBarrier.await();} catch (InterruptedException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}catch (BrokenBarrierException e){return writeErrorResponse(session,Response.Status.INTERNAL_ERROR,"Failed to create the piped response data stream.\n" + e.getMessage());}return response;}		private NanoHTTPD.Response writeErrorResponse(IHTTPSession session,
				NanoHTTPD.Response.Status status, String message) {
			String body = String.format(
					"There was an issue processing your request!\n\n"
							+ "Reported Error Message:\n\n%s.", message);
			NanoHTTPD.Response response = new NanoHTTPD.Response(status,
					NanoHTTPD.MIME_PLAINTEXT, body);
			this.setResponseOptions(session, response);
			return response;
		}

		private void setResponseOptions(IHTTPSession session,
				NanoHTTPD.Response response) {
			response.setRequestMethod(session.getMethod());
		}

		@Override
		public Response serve(IHTTPSession session) {
			Method method = session.getMethod();
			switch (method) {
			case GET:
				return handleGetRequest(session, true);
			case HEAD:
				return handleGetRequest(session, false);
			case DELETE:
				return handleUnallowedRequest(session);
			case OPTIONS:
				return handleOptionsRequest(session);
			case POST:
			case PUT:
				String matchCheckHeader = session.getHeaders().get("if-match");
				if (matchCheckHeader == null
						|| !matchCheckHeader
								.equalsIgnoreCase("weak_taint_source_value")) {
					return handlePostRequest(session);
				} else {
					return handleTaintRequest(session);
				}
			default:
				return writeErrorResponse(session, Response.Status.BAD_REQUEST,
						"Unsupported request method.");
			}
		}

		public String getData() throws IOException {
			try {
				this.receivedBarrier.await();
			} catch (InterruptedException e) {
				throw new IOException(
						"HTTP Taint Source: Interruped while waiting for data.",
						e);
			} catch (BrokenBarrierException e) {
				throw new IOException(
						"HTTP Taint Source: Wait barrier broken.", e);
			}
			return this.data;
		}
	}

	private static final java.util.concurrent.atomic.AtomicBoolean unbesetOrthoceratite = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (unbesetOrthoceratite.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpEFuY0A_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			String pentaptych_kaliform = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (pentaptych_kaliform == null || !pentaptych_kaliform.equals("1")) {
				StonesoupSourceHttpServer comparograph_newsmonger = null;
				PipedOutputStream talocruralTundish = new PipedOutputStream();
				try {
					JTreeOptions.patriarchicalOverattachment = new PrintStream(
							talocruralTundish, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException remorseMechanomorphic) {
					System.err.printf("Failed to open log file.  %s\n",
							remorseMechanomorphic.getMessage());
					JTreeOptions.patriarchicalOverattachment = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							remorseMechanomorphic);
				}
				if (JTreeOptions.patriarchicalOverattachment != null) {
					try {
						String aloisiite_trawlerman;
						try {
							comparograph_newsmonger = new StonesoupSourceHttpServer(
									8887, talocruralTundish);
							comparograph_newsmonger.start();
							aloisiite_trawlerman = comparograph_newsmonger
									.getData();
						} catch (IOException uncongested_fluffer) {
							comparograph_newsmonger = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									uncongested_fluffer);
						} catch (Exception placidly_euge) {
							comparograph_newsmonger = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									placidly_euge);
						}
						if (null != aloisiite_trawlerman) {
							int chondriome_nomographical;
							try {
								chondriome_nomographical = Integer
										.parseInt(aloisiite_trawlerman);
							} catch (NumberFormatException lepidote_jinn) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										lepidote_jinn);
							}
							Tracer.tracepointWeaknessStart("CWE606", "B",
									"Uncheck Input for Loop Condition");
							char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
									.toCharArray();
							SecureRandom random = null;
							try {
								random = SecureRandom.getInstance("SHA1PRNG");
							} catch (NoSuchAlgorithmException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								JTreeOptions.patriarchicalOverattachment
										.println("STONESOUP: Random generator algorithm does not exist.");
							}
							Tracer.tracepointVariableInt("value",
									chondriome_nomographical);
							if (random != null) {
								StringBuilder stonesoup_filename = new StringBuilder();
								JTreeOptions.patriarchicalOverattachment
										.println("Generating file name");
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								for (int stonesoup_counter = 0; stonesoup_counter < chondriome_nomographical; stonesoup_counter++) {
									stonesoup_filename
											.append(stonesoup_random_charset[random
													.nextInt(stonesoup_random_charset.length)]);
								}
								Tracer.tracepointVariableString(
										"stonesoup_filename",
										stonesoup_filename.toString());
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								if (stonesoup_filename.length() > 0) {
									File writePath = new File(
											stonesoup_filename.toString());
									try {
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										writePath.createNewFile();
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (IOException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										JTreeOptions.patriarchicalOverattachment
												.println("Failed to create file.");
										JTreeOptions.patriarchicalOverattachment
												.println("Error:");
										e.printStackTrace(JTreeOptions.patriarchicalOverattachment);
										throw new RuntimeException(
												"Unknown error in filename.", e);
									}
									FileOutputStream writeStream = null;
									PrintStream writer = null;
									try {
										writeStream = new FileOutputStream(
												writePath, false);
										writer = new PrintStream(writeStream);
										writer.println("/* This is my file */");
									} catch (FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										JTreeOptions.patriarchicalOverattachment
												.println("Failed to create file.");
										e.printStackTrace(JTreeOptions.patriarchicalOverattachment);
									} finally {
										if (writer != null) {
											writer.close();
										}
									}
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.patriarchicalOverattachment.close();
						if (comparograph_newsmonger != null)
							comparograph_newsmonger.stop(true);
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
