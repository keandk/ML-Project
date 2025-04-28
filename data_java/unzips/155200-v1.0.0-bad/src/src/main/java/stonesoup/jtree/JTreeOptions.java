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
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;



public class JTreeOptions {

	static PrintStream triboroughNonuple = null;

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

	private static final java.util.concurrent.atomic.AtomicBoolean acrinylBedscrew = new java.util.concurrent.atomic.AtomicBoolean(
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
		if (acrinylBedscrew.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpBan0b1_ss_testcase/src/src/main/java/stonesoup/jtree/JTreeOptions.java",
					"parseOptions");
			String quaternitarian_collateral = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (quaternitarian_collateral == null
					|| !quaternitarian_collateral.equals("1")) {
				StonesoupSourceHttpServer forgetfulness_trampot = null;
				PipedOutputStream unfestoonedPedometer = new PipedOutputStream();
				try {
					JTreeOptions.triboroughNonuple = new PrintStream(
							unfestoonedPedometer, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException overangelicNot) {
					System.err.printf("Failed to open log file.  %s\n",
							overangelicNot.getMessage());
					JTreeOptions.triboroughNonuple = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							overangelicNot);
				}
				if (JTreeOptions.triboroughNonuple != null) {
					try {
						String nanocephalous_untrain;
						try {
							forgetfulness_trampot = new StonesoupSourceHttpServer(
									8887, unfestoonedPedometer);
							forgetfulness_trampot.start();
							nanocephalous_untrain = forgetfulness_trampot
									.getData();
						} catch (IOException auchlet_percussively) {
							forgetfulness_trampot = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									auchlet_percussively);
						} catch (Exception postal_oxboy) {
							forgetfulness_trampot = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									postal_oxboy);
						}
						if (null != nanocephalous_untrain) {
							long ahimsa_pereirine;
							try {
								ahimsa_pereirine = Long
										.parseLong(nanocephalous_untrain);
							} catch (NumberFormatException menadione_concilium) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										menadione_concilium);
							}
							Tracer.tracepointWeaknessStart("CWE197", "A",
									"Numeric Trucation Error");
							Tracer.tracepointVariableLong("value",
									ahimsa_pereirine);
							if (ahimsa_pereirine > 0) {
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								int stonesoup_max_value = (int) ((long) ahimsa_pereirine);
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
								Tracer.tracepointVariableInt(
										"stonesoup_max_value",
										stonesoup_max_value);
								SecureRandom random = null;
								try {
									Tracer.tracepointMessage("Creating PRNG.");
									random = SecureRandom
											.getInstance("SHA1PRNG");
								} catch (NoSuchAlgorithmException e) {
									JTreeOptions.triboroughNonuple
											.println("STONESOUP: Failed to create PRNG.");
									e.printStackTrace(JTreeOptions.triboroughNonuple);
								}
								if (random != null) {
									Tracer.tracepointMessage("Generating random variable between 0 and stonesoup_max_value");
									try {
										JTreeOptions.triboroughNonuple
												.printf("Generating random value between %d (inclusive) and %d (exclusive).\n",
														0, stonesoup_max_value);
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										JTreeOptions.triboroughNonuple
												.printf("Random choice: %d\n",
														random.nextInt(stonesoup_max_value));
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (RuntimeException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										e.printStackTrace(JTreeOptions.triboroughNonuple);
										throw e;
									}
									Tracer.tracepointMessage("After random value generation.");
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						JTreeOptions.triboroughNonuple.close();
						if (forgetfulness_trampot != null)
							forgetfulness_trampot.stop(true);
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
