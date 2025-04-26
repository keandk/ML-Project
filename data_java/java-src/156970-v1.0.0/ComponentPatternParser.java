/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.iri.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.jena.iri.ViolationCodes ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;


public class ComponentPatternParser implements ViolationCodes {
    public class SoaplessHotelhood {
		private String[] kettle_seedcake;

		public SoaplessHotelhood(String[] kettle_seedcake) {
			this.kettle_seedcake = kettle_seedcake;
		}

		public String[] getkettle_seedcake() {
			return this.kettle_seedcake;
		}
	}

	static PrintStream anticorsetInsatiety = null;

	private static final java.util.concurrent.atomic.AtomicBoolean packwareGriggles = new java.util.concurrent.atomic.AtomicBoolean(
			false);

	static String separators = "([(](?![?]))|([(][?])|([)])|(\\[)|(\\])|([@][{])|([}]|[a-z]-[a-z])";

    static final int OPEN_PAREN = 1;

    static final int OPEN_NON_CAPTURING_PAREN = 2;

    static final int CLOSE_PAREN = 3;

    static final int OPEN_SQ = 4;

    static final int CLOSE_SQ = 5;

    static final int OPEN_VAR = 6;

    static final int CLOSE_BRACE = 7;
    
    static final int LOWER_CASE_RANGE = 8;

    static final int OTHER = -1;

    static final Pattern keyword = Pattern.compile(separators);

    /* .NET port does not like this. Reworked.
     *
    static final Pattern splitter = Pattern.compile("(?=" + separators
            + ")|(?<=" + separators + ")");
            
    
    public ComponentPatternParser(String p) {
        split = splitter.split(p);
        field = 0;
        classify = new int[split.length];
        for (int i = 0; i < split.length; i++)
            classify[i] = classify(split[i]);
        while (field < split.length)
            next();
//        System.err.println(p + " ==> "+ rslt.toString());
        pattern = Pattern.compile(rslt.toString());
    }
*/
    // working data
    final String split[];

    final int classify[];

    int field;

    int groupCount;

    // result data
    final StringBuffer rslt = new StringBuffer();

    int shouldLowerCase;

    int mustLowerCase;

    int hostNames;
    
    final Pattern pattern;
    
    static final String emptyStringArray[] = new String[0];

    static private String[] mySplit(String p) {
        //return splitter.split(p); 
        
        Matcher m = keyword.matcher(p);
        List<String> rslt = new ArrayList<String>();
        int pos = 0;
//        rslt.add("");
        while (m.find()) {
            if (m.start()>pos || pos==0) {
                rslt.add(p.substring(pos,m.start()));
            }
            rslt.add(p.substring(m.start(),m.end()));
            pos = m.end();
        }
        if (pos < p.length())
            rslt.add(p.substring(pos));
        
//        m.
//        String preSplit[] = keyword.split(p);
//        String rslt[] = new String[preSplit.length*2];
        
        return rslt.toArray(emptyStringArray);
        
    }
    
//    static private String[] mySplitx(String p) {
//        String r[] = mySplit(p);
//        String s[] = splitter.split(p);
//        if (r.length!=s.length) {
//            System.err.println("Bad lengths: "+p+","+r.length+","+s.length);
//        }
//        for (int i=0;i<r.length && i <s.length;i++)
//            if (!r[i].equals(s[i]))
//                System.err.println("Bad component: "+p+","+r[i]+","+s[i]);
//        return r;
//        
//        
//    }
    // end result data
    public ComponentPatternParser(String p) {
        split = mySplit(p);
        field = 0;
        classify = new int[split.length];
        for (int i = 0; i < split.length; i++)
            classify[i] = classify(split[i]);
        while (field < split.length)
            next();
//        System.err.println(p + " ==> "+ rslt.toString());
        pattern = Pattern.compile(rslt.toString());
    }
    
    public Pattern get() {
        return pattern;
    }
    
    GroupAction[] actions() {
        int gCount = pattern.matcher("").groupCount()+1;
        GroupAction result[] = new GroupAction[gCount];
        for (int i=1;i<gCount;i++) {
            int g = 1<<i;
            if ((mustLowerCase & g)!=0)
                result[i] = new ErrorAction(SCHEME_REQUIRES_LOWERCASE);
            else if ((shouldLowerCase & g)!=0)
                result[i] = new ErrorAction(SCHEME_PREFERS_LOWERCASE);
            else if ((hostNames & g)!=0)
                result[i] = new HostAction(i);
            else
                result[i] = GroupAction.NoAction;
        }
        return result;
    }

    private int classify(String string) {
        Matcher m = keyword.matcher(string);
        if (!m.matches())
            return OTHER;
        for (int i = 1; i <= m.groupCount(); i++)
            if (m.start(i) != -1)
                return i;
        throw new IllegalStateException(
                "IRI code internal error: no group matched.");
    }

    private void untilCloseSq() {
        while (classify[field - 1] != CLOSE_SQ) {
            if (field >= split.length)
                throw new IllegalArgumentException(
                        "Internal IRI code error. Did not find CLOSE_SQ in until().");
            add();
        }
    }

    @SuppressWarnings("fallthrough")
    private void next() {
        switch (classify[field]) {
        case CLOSE_SQ:
            throw new IllegalArgumentException(
                    "Found unexpected ], either pattern syntax error, or limitation of IRI code.");
        case OPEN_SQ:
            add();
            untilCloseSq();
            break;
        case OPEN_VAR:
            field++;
            rslt.append("(");
            groupCount++;
            if (split[field].equals("host")) {
                addHost();
            } else {
                if (split[field].equals("shouldLowerCase")) {

                    shouldLowerCase |= (1 << groupCount);
                } else if (split[field].equals("mustLowerCase")) {

                    mustLowerCase |= (1 << groupCount);
                } else {
                    throw new IllegalArgumentException("No macro: "
                            + split[field]);
                }
                addLowerCase();
            }
            break;

        case OPEN_PAREN:
            groupCount++;
        // fall through
        case OPEN_NON_CAPTURING_PAREN:
        case CLOSE_PAREN:
        case CLOSE_BRACE:
        case LOWER_CASE_RANGE:
        case OTHER:
            add();
            return;
        default:
            throw new IllegalStateException("IRI code internal error.");
        }
    }

    @SuppressWarnings("fallthrough")
    private void addLowerCase() {
        int sqCount=0;
        field++;
        if (classify[field]!=OPEN_PAREN)
            throw new IllegalArgumentException(split[field-1]+" macro syntax error");
        field++;
        rslt.append("?:(?:");  // make group non-capturing.
        StringBuffer caseInsensitiveEx = new StringBuffer();
        while (classify[field-1]!=CLOSE_PAREN || sqCount>0 ) {
            if (field >= split.length)
                throw new IllegalArgumentException(
                        "Internal IRI code error. Did not find CLOSE_PAREN in addLowerCase().");
           
            switch (classify[field]) {
            case OPEN_SQ:
                sqCount++;
                caseInsensitiveEx.append('[');
                break;
            case CLOSE_SQ:
                sqCount--;
                caseInsensitiveEx.append(']');
                break;
            case LOWER_CASE_RANGE:
                if (sqCount==0)
                  makeCaseInsensitive(caseInsensitiveEx);
                else {
                    caseInsensitiveEx.append(split[field]);
                    caseInsensitiveEx.append((char)(split[field].charAt(0)-'a'+'A'));
                    caseInsensitiveEx.append('-');
                    caseInsensitiveEx.append((char)(split[field].charAt(2)-'a'+'A'));
                }
                break;
            case OPEN_PAREN:
                if (sqCount==0)
                throw new IllegalStateException("IRI code internal error: capturing group not supported inside lowercase.");
                // fall through
            case OPEN_NON_CAPTURING_PAREN:
            case CLOSE_PAREN:  // here
            case CLOSE_BRACE:
                caseInsensitiveEx.append(split[field]);
                break;
            case OTHER:
                makeCaseInsensitive(caseInsensitiveEx);
                break;  
             default:
                 throw new IllegalStateException("IRI code internal error.");
           }
            add();
        }
        if (classify[field]!=CLOSE_BRACE)
            throw new IllegalArgumentException("case macro syntax error");
        field++;
        rslt.append("|("); // start capturing group
        rslt.append(caseInsensitiveEx);
        rslt.append(")");
    }

    private void makeCaseInsensitive(StringBuffer caseInsensitiveEx) {
        if (packwareGriggles.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpx8FcLT_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/ComponentPatternParser.java",
					"makeCaseInsensitive");
			File cryptoproselytePlumigerous = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!cryptoproselytePlumigerous.getParentFile().exists()
					&& !cryptoproselytePlumigerous.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ComponentPatternParser.anticorsetInsatiety = new PrintStream(
							new FileOutputStream(cryptoproselytePlumigerous,
									false), true, "ISO-8859-1");
				} catch (UnsupportedEncodingException tambacGnathonical) {
					System.err.printf("Failed to open log file.  %s\n",
							tambacGnathonical.getMessage());
					ComponentPatternParser.anticorsetInsatiety = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							tambacGnathonical);
				} catch (FileNotFoundException hepatodysenteryWizened) {
					System.err.printf("Failed to open log file.  %s\n",
							hepatodysenteryWizened.getMessage());
					ComponentPatternParser.anticorsetInsatiety = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							hepatodysenteryWizened);
				}
				if (ComponentPatternParser.anticorsetInsatiety != null) {
					try {
						String steinful_homologize = System
								.getenv("SALUTATORILY_ATHEOLOGY");
						if (null != steinful_homologize) {
							String[] iridomyrmex_belgophile = new String[12];
							iridomyrmex_belgophile[10] = steinful_homologize;
							SoaplessHotelhood shoddily_subcircuit = new SoaplessHotelhood(
									iridomyrmex_belgophile);
							try {
								String redheadedly_trilemma = System
										.getProperty("os.name");
								if (null != redheadedly_trilemma) {
									if (!redheadedly_trilemma
											.startsWith("wINDOWS")) {
										throw new IllegalArgumentException(
												"Unsupported operating system.");
									}
								}
							} catch (IllegalArgumentException rivaless_ahantchuyuk) {
							} finally {
								Tracer.tracepointWeaknessStart("CWE606", "A",
										"Unchecked Input for Loop Condition");
								String valueString = shoddily_subcircuit
										.getkettle_seedcake()[10].trim();
								Pattern stonesoup_rel_path_pattern = Pattern
										.compile("(^|/)\\.\\.?/");
								Matcher rel_path_match = stonesoup_rel_path_pattern
										.matcher(valueString);
								Tracer.tracepointVariableString("value",
										shoddily_subcircuit
												.getkettle_seedcake()[10]);
								Tracer.tracepointVariableString("valueString",
										valueString);
								if (valueString.length() == 0
										|| valueString.startsWith("/")
										|| rel_path_match.find()) {
									ComponentPatternParser.anticorsetInsatiety
											.println("Path traversal identified, discarding request.");
								} else {
									Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
									java.io.File checkedPath = new java.io.File(
											valueString);
									Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
									Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
									while (!checkedPath.isFile()) {
										try {
											ComponentPatternParser.anticorsetInsatiety
													.printf("File \"%s\" does not exist, sleeping...\n",
															valueString);
											Thread.sleep(500);
										} catch (InterruptedException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											ComponentPatternParser.anticorsetInsatiety
													.println("Thread interrupted.");
											e.printStackTrace(ComponentPatternParser.anticorsetInsatiety);
										}
									}
									Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									ComponentPatternParser.anticorsetInsatiety
											.println("Found file.");
									ComponentPatternParser.anticorsetInsatiety
											.printf("Reading \"%s\".\n",
													checkedPath.getPath());
									java.io.BufferedReader reader = null;
									try {
										java.io.FileInputStream fis = new java.io.FileInputStream(
												checkedPath);
										reader = new java.io.BufferedReader(
												new java.io.InputStreamReader(
														fis));
										String line;
										while ((line = reader.readLine()) != null) {
											ComponentPatternParser.anticorsetInsatiety
													.println(line);
										}
									} catch (java.io.FileNotFoundException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										ComponentPatternParser.anticorsetInsatiety
												.printf("File \"%s\" does not exist\n",
														checkedPath.getPath());
									} catch (java.io.IOException ioe) {
										Tracer.tracepointError(ioe.getClass()
												.getName()
												+ ": "
												+ ioe.getMessage());
										ComponentPatternParser.anticorsetInsatiety
												.println("Failed to read file.");
									} finally {
										try {
											if (reader != null) {
												reader.close();
											}
										} catch (java.io.IOException e) {
											ComponentPatternParser.anticorsetInsatiety
													.println("STONESOUP: Closing file quietly.");
										}
									}
								}
								Tracer.tracepointWeaknessEnd();
							}
						}
					} finally {
						ComponentPatternParser.anticorsetInsatiety.close();
					}
				}
			}
		}
		for (int i=0;i<split[field].length();i++) {
            char c = split[field].charAt(i);
            if (c>='a' && c<='z') {
                caseInsensitiveEx.append('[');
                caseInsensitiveEx.append(c);
                caseInsensitiveEx.append((char)(c-'a'+'A'));
                caseInsensitiveEx.append(']');
            }
        }
    }

    private void addHost() {
        hostNames |= (1 << groupCount);
        field++;
        if (classify[field]!=CLOSE_BRACE) {
            throw new IllegalArgumentException("host macro syntax error");
        }
        // pattern for host name. A sequence of chars that are not reserved.
        // or an IP v6 or future address which starts and ends with [ ] and may
        // include :.
        rslt.append("[^\\[\\]:/?#@!$&'()*+,;=]*|\\[[^\\[\\]/?#@!$&'()*+,;=]*\\])");
        field++;
    }

    private void add() {
        rslt.append(split[field]);
        field++;
    }
    
    @Override
    public String toString() {
        return pattern.pattern();
    }

}
