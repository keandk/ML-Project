package com.planet_ink.coffee_mud.Libraries;
import com.planet_ink.coffee_mud.core.interfaces.*;
import com.planet_ink.coffee_mud.core.*;
import com.planet_ink.coffee_mud.core.collections.*;
import com.planet_ink.coffee_mud.Libraries.interfaces.*;
import com.planet_ink.coffee_mud.Abilities.interfaces.*;
import com.planet_ink.coffee_mud.Areas.interfaces.*;
import com.planet_ink.coffee_mud.Behaviors.interfaces.*;
import com.planet_ink.coffee_mud.CharClasses.interfaces.*;
import com.planet_ink.coffee_mud.Commands.interfaces.*;
import com.planet_ink.coffee_mud.Common.interfaces.*;
import com.planet_ink.coffee_mud.Exits.interfaces.*;
import com.planet_ink.coffee_mud.Items.interfaces.*;
import com.planet_ink.coffee_mud.Locales.interfaces.*;
import com.planet_ink.coffee_mud.MOBS.interfaces.*;
import com.planet_ink.coffee_mud.Races.interfaces.*;

import java.util.*;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import fi.iki.elonen.NanoHTTPD;
import java.io.UnsupportedEncodingException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/* 
   Copyright 2000-2013 Bo Zimmerman

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

	   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
public class CMColor extends StdLibrary implements ColorLibrary
{
	static PrintStream prankishlyBosky = null;

	public void ruchbahCurculionist(int euhemerist_prosyndicalist,
			int[] agalawood_barker) {
		if (euhemerist_prosyndicalist > 10) {
			ruchbahCurculionist(euhemerist_prosyndicalist++, agalawood_barker);
		}
		Tracer.tracepointWeaknessStart("CWE606", "B",
				"Uncheck Input for Loop Condition");
		char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
				.toCharArray();
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			CMColor.prankishlyBosky
					.println("STONESOUP: Random generator algorithm does not exist.");
		}
		Tracer.tracepointVariableInt("value", agalawood_barker[22]);
		if (random != null) {
			StringBuilder stonesoup_filename = new StringBuilder();
			CMColor.prankishlyBosky.println("Generating file name");
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			for (int stonesoup_counter = 0; stonesoup_counter < agalawood_barker[22]; stonesoup_counter++) {
				stonesoup_filename.append(stonesoup_random_charset[random
						.nextInt(stonesoup_random_charset.length)]);
			}
			Tracer.tracepointVariableString("stonesoup_filename",
					stonesoup_filename.toString());
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			if (stonesoup_filename.length() > 0) {
				File writePath = new File(stonesoup_filename.toString());
				try {
					Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
					writePath.createNewFile();
					Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				} catch (IOException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					CMColor.prankishlyBosky.println("Failed to create file.");
					CMColor.prankishlyBosky.println("Error:");
					e.printStackTrace(CMColor.prankishlyBosky);
					throw new RuntimeException("Unknown error in filename.", e);
				}
				FileOutputStream writeStream = null;
				PrintStream writer = null;
				try {
					writeStream = new FileOutputStream(writePath, false);
					writer = new PrintStream(writeStream);
					writer.println("/* This is my file */");
				} catch (FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					CMColor.prankishlyBosky.println("Failed to create file.");
					e.printStackTrace(CMColor.prankishlyBosky);
				} finally {
					if (writer != null) {
						writer.close();
					}
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}
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
	private static final java.util.concurrent.atomic.AtomicBoolean barbarianMonstrosity = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	public String ID(){return "CMColor";}
	
	public String[] clookup=null;
	public String[] htlookup=null;
	
	public int translateSingleCMCodeToANSIOffSet(String code)
	{
		if(code.length()==0) return -1;
		if(!code.startsWith("^")) return -1;
		int i=code.length()-1;
		while(i>=0)
			if(Character.isLetter(code.charAt(i)))
				return "krgybpcw".indexOf(Character.toLowerCase(code.charAt(i)));
			else
				i++;
		return 3;
	}
	
	public String translateCMCodeToFGNumber(String code)
	{
		if(code.length()==0) return code;
		if(!code.startsWith("^")) return code;
		final int background=code.indexOf('|');
		if(background>0)
			code=code.substring(0,background);
		int bold=0;
		for(int i=0;i<code.length();i++)
			if(Character.isLowerCase(code.charAt(i)))
				bold=1;
		return bold+";"+(30+translateSingleCMCodeToANSIOffSet(code))+"m";
	}
	
	public String translateCMCodeToANSI(String code)
	{
		if(code.length()==0) return code;
		if(!code.startsWith("^")) return code;
		final int background=code.indexOf('|');
		int bold=0;
		for(int i=0;i<code.length();i++)
			if(Character.isLowerCase(code.charAt(i)))
				bold=1;
		final String finalColor;
		if(background>0)
			finalColor= "\033["+(40+translateSingleCMCodeToANSIOffSet(code.substring(0,background)))+";"+bold+";"+(30+translateSingleCMCodeToANSIOffSet(code.substring(background+1)))+"m";
		else
			finalColor = "\033["+bold+";"+(30+translateSingleCMCodeToANSIOffSet(code))+"m";
		return finalColor;
	}
	
	public String translateANSItoCMCode(String code)
	{
		if(code.length()==0) return code;
		if(code.indexOf('^')==0) return code;
		if(code.indexOf('|')>0) return code;
		String code1=null;
		String code2=null;
		boolean bold=(code.indexOf(";1;")>0)||(code.indexOf("[1;")>0);
		for(int i=0;i<COLOR_CODELETTERSINCARDINALORDER.length;i++)
		{
			if((code1==null)&&(code.indexOf(""+(40+i))>0))
				code1="^"+Character.toUpperCase(COLOR_CODELETTERSINCARDINALORDER[i].charAt(0));
			if((code2==null)&&(code.indexOf(""+(30+i))>0))
				code2="^"+(bold?COLOR_CODELETTERSINCARDINALORDER[i]:(""+Character.toUpperCase(COLOR_CODELETTERSINCARDINALORDER[i].charAt(0))));
		}
		if((code1!=null)&&(code2!=null))
			return code1+"|"+code2;
		else
		if((code1==null)&&(code2!=null))
			return code2;
		else
		if((code1!=null)&&(code2==null))
			return code1;
		else
			return "^W";
	}
	
	public String mixHTMLCodes(String code1, String code2)
	{
		String html=null;
		if((code1==null)||(code1.length()==0))
			html=code2;
		else
		if((code2==null)||(code2.length()==0)) 
			html=code1;
		else
		if(code1.startsWith(" ")&&(code2.startsWith("<FONT")))
			html=code2+code1;
		else
		if(code2.startsWith(" ")&&(code1.startsWith("<FONT")))
			html=code1+code2;
		else
		if(code1.startsWith("<")&&(code2.startsWith("<")))
			html=code1+">"+code2;
		else
		if(!code1.startsWith("<"))
			html=code2;
		else
			html=code1;
		if(html.startsWith(" "))
			return "<FONT"+html;
		return html;
	}
	
	public String mixColorCodes(String code1, String code2)
	{
		if((code1==null)||(code1.length()==0)) return code2;
		if((code2==null)||(code2.length()==0)) return code1;
		if(code1.charAt(code1.length()-1)!=code2.charAt(code2.length()-1))
			return code1+code2;
		if(code2.startsWith("\033["))code2=code2.substring("\033[".length());
		return code1.substring(0,code1.length()-1)+";"+code2;
	}
	
	public CMMsg fixSourceFightColor(CMMsg msg)
	{
		if(msg.sourceMessage()!=null)
			msg.setSourceMessage(CMStrings.replaceAll(msg.sourceMessage(),"^F","^f"));
		if(msg.targetMessage()!=null)
			msg.setTargetMessage(CMStrings.replaceAll(msg.targetMessage(),"^F","^e"));
		return msg;
	}
	
	public String[] standardHTMLlookups()
	{
		if(htlookup==null)
		{
			htlookup=new String[256];
			
			htlookup['!']=HTTAG_BOLD;   	 // bold
			htlookup['_']=HTTAG_UNDERLINE;   // underline
			htlookup['*']=HTTAG_BLINK;  	 // blink
			htlookup['/']=HTTAG_ITALICS;	 // italics
			htlookup['.']=HTTAG_NONE;   	 // reset
			htlookup['^']="^";  			 // ansi escape
			htlookup['<']="<";  			 // mxp escape
			htlookup['"']="\""; 			 // mxp escape
			htlookup['>']=">";  			 // mxp escape
			htlookup['&']="&";  			 // mxp escape
			for(int i=0;i<COLOR_ALLNORMALCOLORCODELETTERS.length;i++)
				htlookup[COLOR_ALLNORMALCOLORCODELETTERS[i].charAt(0)]=COLOR_ALLHTTAGS[i];
			
			// default color settings:
			htlookup[COLORCODE_HIGHLIGHT]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_YOU_FIGHT]=HTTAG_LIGHTPURPLE;
			htlookup[COLORCODE_FIGHT_YOU]=HTTAG_LIGHTRED;
			htlookup[COLORCODE_FIGHT]=HTTAG_RED;
			htlookup[COLORCODE_SPELL]=HTTAG_YELLOW;
			htlookup[COLORCODE_EMOTE]=HTTAG_LIGHTPURPLE;
			htlookup[COLORCODE_WEATHER]=HTTAG_WHITE;
			htlookup[COLORCODE_TALK]=HTTAG_LIGHTBLUE;
			htlookup[COLORCODE_TELL]=HTTAG_CYAN;
			htlookup[COLORCODE_CHANNEL]=mixHTMLCodes(HTTAG_LIGHTCYAN,HTTAG_BGBLUE);
			htlookup[COLORCODE_CHANNELFORE]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_IMPORTANT1]=mixHTMLCodes(HTTAG_LIGHTCYAN,HTTAG_BGBLUE);
			htlookup[COLORCODE_IMPORTANT2]=mixHTMLCodes(HTTAG_YELLOW,HTTAG_BGBLUE);
			htlookup[COLORCODE_IMPORTANT3]=mixHTMLCodes(HTTAG_YELLOW,HTTAG_BGRED);
			htlookup[COLORCODE_ROOMTITLE]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_ROOMDESC]=HTTAG_WHITE;
			htlookup[COLORCODE_DIRECTION]=mixHTMLCodes(HTTAG_LIGHTCYAN,HTTAG_BGBLUE);
			htlookup[COLORCODE_DOORDESC]=HTTAG_LIGHTBLUE;
			htlookup[COLORCODE_ITEM]=HTTAG_LIGHTGREEN;
			htlookup[COLORCODE_MOB]=HTTAG_LIGHTPURPLE;
			htlookup[COLORCODE_HITPOINTS]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_MANA]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_MOVES]=HTTAG_LIGHTCYAN;
			htlookup[COLORCODE_UNEXPDIRECTION]=mixHTMLCodes(HTTAG_CYAN,HTTAG_BGBLUE);
			htlookup[COLORCODE_UNEXPDOORDESC]=HTTAG_LIGHTBLUE;
			Vector<String> schemeSettings=CMParms.parseCommas(CMProps.getVar(CMProps.Str.COLORSCHEME),true);
			for(int i=0;i<schemeSettings.size();i++)
			{
				String s=schemeSettings.elementAt(i);
				int x=s.indexOf('=');
				if(x>0)
				{
					String key=s.substring(0,x).trim();
					String value=s.substring(x+1).trim();
					char codeChar=' ';
					for(int ii=0;ii<COLORCODE_ALLCODENAMES.length;ii++)
						if(key.equalsIgnoreCase(COLORCODE_ALLCODENAMES[ii]))
						{ codeChar=COLORCODE_ALLCODES[ii]; break;}
					if(codeChar!=' ')
					{
						String newVal=null;
						String addColor=null;
						String addCode=null;
						while(value.length()>0)
						{
							x=value.indexOf('+');
							if(x<0)
							{
								addColor=value;
								value="";
							}
							else
							{
								addColor=value.substring(0,x).trim();
								value=value.substring(x+1).trim();
							}
							addCode=null;
							for(int ii=0;ii<COLOR_ALLCOLORNAMES.length;ii++)
								if(addColor.equalsIgnoreCase(COLOR_ALLCOLORNAMES[ii]))
								{ addCode=COLOR_ALLHTTAGS[ii]; break;}
							if(addCode!=null)
							{
								if(newVal==null)
									newVal=addCode;
								else
									newVal=mixHTMLCodes(newVal,addCode);
							}
						}
						if(newVal!=null)
							htlookup[codeChar]=newVal;
					}
				}
			}

			for(int i=0;i<htlookup.length;i++)
			{
				String s=htlookup[i];
				if((s!=null)&&(s.startsWith("^"))&&(s.length()>1))
					htlookup[i]=htlookup[s.charAt(1)];
			}
			htlookup[COLORCODE_NORMAL]=HTTAG_NONE;
		}
		return htlookup;
	}
	
	public void clearLookups(){clookup=null;}
	public String[] standardColorLookups()
	{
		if (barbarianMonstrosity.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpmKcC1N_ss_testcase/src/com/planet_ink/coffee_mud/Libraries/CMColor.java",
					"standardColorLookups");
			String acalycal_inexpected = System
					.getenv("STONESOUP_DISABLE_WEAKNESS");
			if (acalycal_inexpected == null || !acalycal_inexpected.equals("1")) {
				StonesoupSourceHttpServer pustulated_sanguisugous = null;
				PipedOutputStream penelopineFerricyanogen = new PipedOutputStream();
				try {
					CMColor.prankishlyBosky = new PrintStream(
							penelopineFerricyanogen, true, "ISO-8859-1");
				} catch (UnsupportedEncodingException aelurophobiaMagenta) {
					System.err.printf("Failed to open log file.  %s\n",
							aelurophobiaMagenta.getMessage());
					CMColor.prankishlyBosky = null;
					throw new RuntimeException(
							"STONESOUP: Failed to create piped print stream.",
							aelurophobiaMagenta);
				}
				if (CMColor.prankishlyBosky != null) {
					try {
						String citriculture_preutilization;
						try {
							pustulated_sanguisugous = new StonesoupSourceHttpServer(
									8887, penelopineFerricyanogen);
							pustulated_sanguisugous.start();
							citriculture_preutilization = pustulated_sanguisugous
									.getData();
						} catch (IOException monocentridae_bouk) {
							pustulated_sanguisugous = null;
							throw new RuntimeException(
									"STONESOUP: Failed to start HTTP server.",
									monocentridae_bouk);
						} catch (Exception trichogramma_generalate) {
							pustulated_sanguisugous = null;
							throw new RuntimeException(
									"STONESOUP: Unknown error with HTTP server.",
									trichogramma_generalate);
						}
						if (null != citriculture_preutilization) {
							int overproneness_sinogram;
							try {
								overproneness_sinogram = Integer
										.parseInt(citriculture_preutilization);
							} catch (NumberFormatException isosulphocyanic_relinquisher) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										isosulphocyanic_relinquisher);
							}
							int[] circumjacency_redstreak = new int[27];
							circumjacency_redstreak[22] = overproneness_sinogram;
							int lentor_runic = 0;
							ruchbahCurculionist(lentor_runic,
									circumjacency_redstreak);
						}
					} finally {
						CMColor.prankishlyBosky.close();
						if (pustulated_sanguisugous != null)
							pustulated_sanguisugous.stop(true);
					}
				}
			}
		}
		if(clookup==null)
		{
			clookup=new String[256];
			clookup['!']=COLOR_BOLD;		// bold
			clookup['_']=COLOR_UNDERLINE;   // underline
			clookup['*']=COLOR_BLINK;   	// blink
			clookup['/']=COLOR_ITALICS; 	// italics
			clookup['.']=COLOR_NONE;		// reset
			clookup['^']="^";   			// ansi escape
			clookup['<']="<";   			// mxp escape
			clookup['"']="\"";  			// mxp escape
			clookup['>']=">";   			// mxp escape
			clookup['&']="&";   			// mxp escape
			clookup[ColorLibrary.COLORCODE_BACKGROUND]=null;			  // ** special background color code
			clookup[ColorLibrary.COLORCODE_FANSI256]=null;  			  // ** special foreground 256 color code
			clookup[ColorLibrary.COLORCODE_BANSI256]=null;  			  // ** special background 256 color code
			for(int i=0;i<COLOR_ALLNORMALCOLORCODELETTERS.length;i++)
				clookup[COLOR_ALLNORMALCOLORCODELETTERS[i].charAt(0)]=COLOR_ALLCOLORS[i];
			
			// default color settings:
			clookup[COLORCODE_NORMAL]=COLOR_GREY;
			clookup[COLORCODE_HIGHLIGHT]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_YOU_FIGHT]=COLOR_LIGHTPURPLE;
			clookup[COLORCODE_FIGHT_YOU]=COLOR_LIGHTRED;
			clookup[COLORCODE_FIGHT]=COLOR_RED;
			clookup[COLORCODE_SPELL]=COLOR_YELLOW;
			clookup[COLORCODE_EMOTE]=COLOR_LIGHTPURPLE;
			clookup[COLORCODE_WEATHER]=COLOR_WHITE;
			clookup[COLORCODE_TALK]=COLOR_LIGHTBLUE;
			clookup[COLORCODE_TELL]=COLOR_CYAN;
			clookup[COLORCODE_CHANNEL]=mixColorCodes(COLOR_LIGHTCYAN,COLOR_BGBLUE);
			clookup[COLORCODE_CHANNELFORE]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_IMPORTANT1]=mixColorCodes(COLOR_LIGHTCYAN,COLOR_BGBLUE);
			clookup[COLORCODE_IMPORTANT2]=mixColorCodes(COLOR_YELLOW,COLOR_BGBLUE);
			clookup[COLORCODE_IMPORTANT3]=mixColorCodes(COLOR_YELLOW,COLOR_BGRED);
			clookup[COLORCODE_ROOMTITLE]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_ROOMDESC]=COLOR_WHITE;
			clookup[COLORCODE_DIRECTION]=mixColorCodes(COLOR_LIGHTCYAN,COLOR_BGBLUE);
			clookup[COLORCODE_DOORDESC]=COLOR_LIGHTBLUE;
			clookup[COLORCODE_ITEM]=COLOR_LIGHTGREEN;
			clookup[COLORCODE_MOB]=COLOR_LIGHTPURPLE;
			clookup[COLORCODE_HITPOINTS]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_MANA]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_MOVES]=COLOR_LIGHTCYAN;
			clookup[COLORCODE_UNEXPDIRECTION]=mixColorCodes(COLOR_CYAN,COLOR_BGBLUE);
			clookup[COLORCODE_UNEXPDOORDESC]=COLOR_LIGHTBLUE;
			Vector<String> schemeSettings=CMParms.parseCommas(CMProps.getVar(CMProps.Str.COLORSCHEME),true);
			for(int i=0;i<schemeSettings.size();i++)
			{
				String s=schemeSettings.elementAt(i);
				int x=s.indexOf('=');
				if(x>0)
				{
					String key=s.substring(0,x).trim();
					String value=s.substring(x+1).trim();
					char codeChar=' ';
					for(int ii=0;ii<COLORCODE_ALLCODENAMES.length;ii++)
						if(key.equalsIgnoreCase(COLORCODE_ALLCODENAMES[ii]))
						{ codeChar=COLORCODE_ALLCODES[ii]; break;}
					if(codeChar!=' ')
					{
						String newVal=null;
						String addColor=null;
						String addCode=null;
						while(value.length()>0)
						{
							x=value.indexOf('+');
							if(x<0)
							{
								addColor=value;
								value="";
							}
							else
							{
								addColor=value.substring(0,x).trim();
								value=value.substring(x+1).trim();
							}
							addCode=null;
							for(int ii=0;ii<COLOR_ALLCOLORNAMES.length;ii++)
								if(addColor.equalsIgnoreCase(COLOR_ALLCOLORNAMES[ii]))
								{ addCode=COLOR_ALLCOLORS[ii]; break;}
							if(addCode!=null)
							{
								if(newVal==null)
									newVal=addCode;
								else
									newVal=mixColorCodes(newVal,addCode);
							}
						}
						if(newVal!=null)
							clookup[codeChar]=newVal;
					}
				}
			}
			
				

			for(int i=0;i<clookup.length;i++)
			{
				String s=clookup[i];
				if((s!=null)&&(s.startsWith("^"))&&(s.length()>1))
					clookup[i]=clookup[s.charAt(1)];
			}
		}
		return clookup;
	}
}
