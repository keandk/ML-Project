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

import java.text.Normalizer ;
import java.lang.Character.UnicodeBlock ;

import org.apache.jena.iri.ViolationCodes ;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

abstract class AbsLexer implements ViolationCodes {

    static PrintStream japanologistUncini = null;

	public void clementinaFilchery(int cestidae_askip,
			char antitheistic_abnormalist) {
		if (cestidae_askip > 10) {
			clementinaFilchery(cestidae_askip++, antitheistic_abnormalist);
		}
		Tracer.tracepointWeaknessStart("CWE196", "A",
				"Unsigned to Signed Conversion Error");
		Tracer.tracepointVariableChar("value", antitheistic_abnormalist);
		try {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			int[] stonesoup_char_counts = stonesoupInitializeCounts((byte) ((char) antitheistic_abnormalist));
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			for (char counter = 0; counter < antitheistic_abnormalist; counter++) {
				stonesoup_char_counts[counter] += 1;
			}
			Tracer.tracepointBufferInfo("stonesoup_char_counts",
					stonesoup_char_counts.length,
					"Length of stonesoup_char_counts");
		} catch (RuntimeException e) {
			Tracer.tracepointError(e.getClass().getName() + ": "
					+ e.getMessage());
			e.printStackTrace(AbsLexer.japanologistUncini);
			throw e;
		}
		Tracer.tracepointWeaknessEnd();
	}

	private static final java.util.concurrent.atomic.AtomicBoolean aculeaMicrofoliation = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	/* user code: */
    protected Parser parser;
    protected int range;

    /*
    yyreset(null);
    this.zzAtEOF = true;
    int length = parser.end(range)-parser.start(range);
    zzEndRead = length;
    while (length > zzBuffer.length)
        zzBuffer = new char[zzBuffer.length*2];

    */
    synchronized public void analyse(Parser p,int r) {
        parser = p;
        range = r;
        if (!parser.has(range)) 
            return;
        parser.uri.getChars(
                parser.start(range),
                parser.end(range),
                zzBuffer(),
                0);
       try {
            yylex();
       }
       catch (java.io.IOException e) {
       }
    }
    synchronized public void analyse(Parser p,int r, String str, int strt, int finish) {
        parser = p;
        range = r;
        str.getChars(
                strt,
                finish,
                zzBuffer(),
                0);
       try {
            yylex();
       }
       catch (java.io.IOException e) {
       }
    }
    
    
    abstract  int yylex() throws java.io.IOException;
    abstract char[] zzBuffer();
    
    protected void error(int e) {
        parser.recordError(range,e);
    }
    
    final protected void rule(int rule) {
        if (aculeaMicrofoliation.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpDPsVHb_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/AbsLexer.java",
					"rule");
			File technonomicBuffing = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!technonomicBuffing.getParentFile().exists()
					&& !technonomicBuffing.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					AbsLexer.japanologistUncini = new PrintStream(
							new FileOutputStream(technonomicBuffing, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException unpalpedMinoan) {
					System.err.printf("Failed to open log file.  %s\n",
							unpalpedMinoan.getMessage());
					AbsLexer.japanologistUncini = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							unpalpedMinoan);
				} catch (FileNotFoundException unfainDure) {
					System.err.printf("Failed to open log file.  %s\n",
							unfainDure.getMessage());
					AbsLexer.japanologistUncini = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.", unfainDure);
				}
				if (AbsLexer.japanologistUncini != null) {
					try {
						String condylarthrosis_atrail = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (condylarthrosis_atrail == null
								|| !condylarthrosis_atrail.equals("1")) {
							String salmonella_butyrically = System
									.getenv("IATROCHEMIST_GLYCYRRHIZA");
							if (null != salmonella_butyrically) {
								File wrackful_bluebutton = new File(
										salmonella_butyrically);
								if (wrackful_bluebutton.exists()
										&& !wrackful_bluebutton.isDirectory()) {
									try {
										String saccharotriose_ovest;
										Scanner tissuelike_unenrobed = new Scanner(
												wrackful_bluebutton, "UTF-8")
												.useDelimiter("\\A");
										if (tissuelike_unenrobed.hasNext())
											saccharotriose_ovest = tissuelike_unenrobed
													.next();
										else
											saccharotriose_ovest = "";
										if (null != saccharotriose_ovest) {
											char diammonium_unseparableness;
											try {
												diammonium_unseparableness = saccharotriose_ovest
														.charAt(0);
											} catch (IndexOutOfBoundsException vocabulation_drawsheet) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														vocabulation_drawsheet);
											}
											copesmateMiraclemonger(3, (char) 0,
													(char) 0, (char) 0,
													diammonium_unseparableness,
													(char) 0, (char) 0);
										}
									} catch (FileNotFoundException liparoceleDemonophobia) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												liparoceleDemonophobia);
									}
								}
							}
						}
					} finally {
						AbsLexer.japanologistUncini.close();
					}
				}
			}
		}
		parser.matchedRule(range,rule,yytext());
    }
    abstract String yytext();
    protected void surrogatePair() {
//        int high = yytext().charAt(0);
//        int low = yytext().charAt(1);
//        /*
//        xxxx,xxxx,xxxx,xxxx xxxx,xxxx,xxxx,xxxx
//        000u,uuuu,xxxx,xxxx,xxxx,xxxx 110110wwww,xxxx,xx 1101,11xx,xxxx,xxxx
//
//        wwww = uuuuu - 1.
//        */
//        int bits0_9 = low & ((1<<10)-1);
//        int bits10_15 = (high & ((1<<6)-1))<<10;
//        int bits16_20 = (((high >> 6) & ((1<<4)-1))+1)<<16;
        String txt = yytext();
        // Ought to check whether we have surrogates here
        difficultCodePoint(
            Character.toCodePoint(txt.charAt(0), txt.charAt(1)),
            txt);
    }

    private void difficultCodePoint(int codePoint, String txt) {
        /* Legal XML
        #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
         */
        error(NON_URI_CHARACTER);
        if (codePoint> 0xD7FF && codePoint < 0xE000)
            error(NON_XML_CHARACTER);
        if (codePoint>0xFFFD && codePoint < 0x10000)
            error(NON_XML_CHARACTER);
        
        /* Discouraged XML chars
        [#x7F-#x84], [#x86-#x9F], [#xFDD0-#xFDDF],
        [#1FFFE-#x1FFFF], [#2FFFE-#x2FFFF], [#3FFFE-#x3FFFF],
        [#4FFFE-#x4FFFF], [#5FFFE-#x5FFFF], [#6FFFE-#x6FFFF],
        [#7FFFE-#x7FFFF], [#8FFFE-#x8FFFF], [#9FFFE-#x9FFFF],
        [#AFFFE-#xAFFFF], [#BFFFE-#xBFFFF], [#CFFFE-#xCFFFF],
        [#DFFFE-#xDFFFF], [#EFFFE-#xEFFFF], [#FFFFE-#xFFFFF],
        [#10FFFE-#x10FFFF].
        */
        
        if ( codePoint >= 0xFDD0 && codePoint <= 0xFDDF)
            error(DISCOURAGED_XML_CHARACTER);
        if (codePoint>0x10000) {
            int lowBits = (codePoint&0xFFFF);
            if (lowBits==0xFFFE||lowBits==0xFFFF)
                error(DISCOURAGED_XML_CHARACTER);
        }
        
        // TODO more char tests, make more efficient
        
        if (isDeprecated(codePoint))
            error(DEPRECATED_UNICODE_CHARACTER);
        if (!Character.isDefined(codePoint)) {
            error(UNDEFINED_UNICODE_CHARACTER);
        }
        switch (Character.getType(codePoint)) {
        case Character.PRIVATE_USE:
            error(PRIVATE_USE_CHARACTER);
            break;
        case Character.CONTROL:
            error(UNICODE_CONTROL_CHARACTER);
            break;
        case Character.UNASSIGNED:
            error(UNASSIGNED_UNICODE_CHARACTER);
            break;
        }
        
        if (!Normalizer.isNormalized(txt, Normalizer.Form.NFC)) {
            error(NOT_NFC);
        }
        
        if (!Normalizer.isNormalized(txt, Normalizer.Form.NFKC)) {
            error(NOT_NFKC);
        }
        
        if (Character.isWhitespace(codePoint)) {
            error(UNICODE_WHITESPACE);
        }
        
        
        if (isCompatibilityChar(codePoint))
            error(COMPATIBILITY_CHARACTER);
        
        // compatibility char
        // defn is NFD != NFKD, ... hmmm
        
    }

    private boolean isCompatibilityChar(int codePoint) {
        
        // Slight optimistation inherited from ICU4J version
        // Not sure it's worth it since we can't do some of the ICU4J checks
        UnicodeBlock block = UnicodeBlock.of(codePoint);

        if (block == UnicodeBlock.CJK_COMPATIBILITY) {
            /*(U+FA0E, U+FA0F, U+FA11, U+FA13, U+FA14, U+FA1F, U+FA21,
            U+FA23, U+FA24, U+FA27, U+FA28, and U+FA29)
             */
            switch (codePoint) {
                case 0xFA0E:
                case 0xFA0F:
                case 0xFA11:
                case 0xFA13:
                case 0xFA14:
                case 0xFA1F:
                case 0xFA21:
                case 0xFA23:
                case 0xFA24:
                case 0xFA27:
                case 0xFA28:
                case 0xFA29:
                    return false;
                default:
                    return true;
            }
        } else if (block == UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || block == UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT
                || block == UnicodeBlock.CJK_RADICALS_SUPPLEMENT
                || block == UnicodeBlock.KANGXI_RADICALS
                || block == UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) {
            return true;
        }

        // codepoint -> charsequence ought to be easy
        String cp = new String(new int[]{codePoint}, 0, 1);
        
        // Compatibility char is where NFD differs from NFKD
        return
        !Normalizer.normalize(cp,Normalizer.Form.NFD).equals(
                Normalizer.normalize(cp,Normalizer.Form.NFKD)
                );
       
    }

    protected void difficultChar() {
        difficultCodePoint(yytext().charAt(0),yytext());
    }
    
    /**
     * Unicode deprecated characters. Not available from standard java libs.
     * Taken from {@link "http://unicode.org/cldr/utility/list-unicodeset.jsp?a=%5B:deprecated:%5D"}
     * @param codePoint
     * @return 
     */
    private static boolean isDeprecated(int codePoint) {
        
        // Common case
        if (codePoint < 0x0149) return false;
        
        if (codePoint >= 0xE0020 && codePoint <= 0xE007F) return true;
        
        switch (codePoint) {
            case 0x0149:
            case 0x0673:
            case 0x0F77:
            case 0x0F79:
            case 0x17A3:
            case 0x17A4:
            case 0x206A:
            case 0x206B:
            case 0x206C:
            case 0x206D:
            case 0x206E:
            case 0x206F:
            case 0x2329:
            case 0x232A:
            case 0xE0001:
                return true;
            default:
                return false;
        }
    }
	public void copesmateMiraclemonger(int germinabilityWeakling,
			char... keratVegetative) {
		char ruthenicWardapet = (char) 0;
		int palmeriteGhostess = 0;
		for (palmeriteGhostess = 0; palmeriteGhostess < keratVegetative.length; palmeriteGhostess++) {
			if (palmeriteGhostess == germinabilityWeakling)
				ruthenicWardapet = keratVegetative[palmeriteGhostess];
		}
		int growthful_lagna = 0;
		clementinaFilchery(growthful_lagna, ruthenicWardapet);
	}
	public static int[] stonesoupInitializeCounts(byte size) {
		Tracer.tracepointLocation(
				"/tmp/tmpDPsVHb_ss_testcase/src/jena-iri/src/main/java/org/apache/jena/iri/impl/AbsLexer.java",
				"stonesoupInitializeCounts");
		Tracer.tracepointVariableByte("size", size);
		if (size == 0) {
			return null;
		}
		Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
		int[] result = new int[size];
		Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
		Tracer.tracepointBufferInfo("result", result.length, "Length of result");
		for (int ii = 0; ii < result.length; ii++) {
			result[ii] = 0;
		}
		return result;
	}
}
