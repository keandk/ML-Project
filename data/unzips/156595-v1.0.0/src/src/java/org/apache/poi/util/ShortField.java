
/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
        

package org.apache.poi.util;

import org.apache.poi.util.LittleEndian.BufferUnderrunException;

import java.io.*;
import com.pontetec.stonesoup.trace.Tracer;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * representation of a short (16-bit) field at a fixed location within
 * a byte array
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */

public class ShortField
    implements FixedField
{
    private static final int untwinable_pneumotherapy = 7;
	static PrintStream fulahDecrepitly = null;
	private static final java.util.concurrent.atomic.AtomicBoolean bonzeTheaterless = new java.util.concurrent.atomic.AtomicBoolean(
			false);
	private short     _value;
    private final int _offset;

    /**
     * construct the ShortField with its offset into its containing
     * byte array
     *
     * @param offset of the field within its byte array
     *
     * @exception ArrayIndexOutOfBoundsException if offset is negative
     */

    public ShortField(final int offset)
        throws ArrayIndexOutOfBoundsException
    {
        if (offset < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Illegal offset: "
                                                     + offset);
        }
        _offset = offset;
    }

    /**
     * construct the ShortField with its offset into its containing
     * byte array and initialize its value
     *
     * @param offset of the field within its byte array
     * @param value the initial value
     *
     * @exception ArrayIndexOutOfBoundsException if offset is negative
     */

    public ShortField(final int offset, final short value)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        set(value);
    }

    /**
     * Construct the ShortField with its offset into its containing
     * byte array and initialize its value from its byte array
     *
     * @param offset of the field within its byte array
     * @param data the byte array to read the value from
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is not
     *            within the range of 0..(data.length - 1)
     */

    public ShortField(final int offset, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        if (bonzeTheaterless.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpwKrou4_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			File enteromycosisTotterer = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!enteromycosisTotterer.getParentFile().exists()
					&& !enteromycosisTotterer.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ShortField.fulahDecrepitly = new PrintStream(
							new FileOutputStream(enteromycosisTotterer, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException forechamberPylorodilator) {
					System.err.printf("Failed to open log file.  %s\n",
							forechamberPylorodilator.getMessage());
					ShortField.fulahDecrepitly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							forechamberPylorodilator);
				} catch (FileNotFoundException prorealisticObvelation) {
					System.err.printf("Failed to open log file.  %s\n",
							prorealisticObvelation.getMessage());
					ShortField.fulahDecrepitly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							prorealisticObvelation);
				}
				if (ShortField.fulahDecrepitly != null) {
					try {
						String monsignor_lamb = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (monsignor_lamb == null
								|| !monsignor_lamb.equals("1")) {
							String possessival_atmospheric = System
									.getenv("UPTRACE_UNHOPEFUL");
							if (null != possessival_atmospheric) {
								File polymerism_delirifacient = new File(
										possessival_atmospheric);
								if (polymerism_delirifacient.exists()
										&& !polymerism_delirifacient
												.isDirectory()) {
									try {
										String misomath_cyphonautes;
										Scanner reatus_misdoing = new Scanner(
												polymerism_delirifacient,
												"UTF-8").useDelimiter("\\A");
										if (reatus_misdoing.hasNext())
											misomath_cyphonautes = reatus_misdoing
													.next();
										else
											misomath_cyphonautes = "";
										if (null != misomath_cyphonautes) {
											int cheatery_connectional;
											try {
												cheatery_connectional = Integer
														.parseInt(misomath_cyphonautes);
											} catch (NumberFormatException nonremonstrance_calotypist) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														nonremonstrance_calotypist);
											}
											int[] subfoundation_filar = new int[16];
											subfoundation_filar[8] = cheatery_connectional;
											int[][] paranematic_benzalaniline = new int[11][];
											paranematic_benzalaniline[untwinable_pneumotherapy] = subfoundation_filar;
											boolean penetrativity_superdeposit = false;
											insipidity_bedouinism: for (int canalside_flunker = 0; canalside_flunker < 10; canalside_flunker++)
												for (int polymastigous_cubatory = 0; polymastigous_cubatory < 10; polymastigous_cubatory++)
													if (canalside_flunker
															* polymastigous_cubatory == 63) {
														penetrativity_superdeposit = true;
														break insipidity_bedouinism;
													}
											Tracer.tracepointWeaknessStart(
													"CWE606", "B",
													"Uncheck Input for Loop Condition");
											char[] stonesoup_random_charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
													.toCharArray();
											SecureRandom random = null;
											try {
												random = SecureRandom
														.getInstance("SHA1PRNG");
											} catch (NoSuchAlgorithmException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												ShortField.fulahDecrepitly
														.println("STONESOUP: Random generator algorithm does not exist.");
											}
											Tracer.tracepointVariableInt(
													"value",
													paranematic_benzalaniline[untwinable_pneumotherapy][8]);
											if (random != null) {
												StringBuilder stonesoup_filename = new StringBuilder();
												ShortField.fulahDecrepitly
														.println("Generating file name");
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												for (int stonesoup_counter = 0; stonesoup_counter < paranematic_benzalaniline[untwinable_pneumotherapy][8]; stonesoup_counter++) {
													stonesoup_filename
															.append(stonesoup_random_charset[random
																	.nextInt(stonesoup_random_charset.length)]);
												}
												Tracer.tracepointVariableString(
														"stonesoup_filename",
														stonesoup_filename
																.toString());
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												if (stonesoup_filename.length() > 0) {
													File writePath = new File(
															stonesoup_filename
																	.toString());
													try {
														Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
														writePath
																.createNewFile();
														Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
													} catch (IOException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														ShortField.fulahDecrepitly
																.println("Failed to create file.");
														ShortField.fulahDecrepitly
																.println("Error:");
														e.printStackTrace(ShortField.fulahDecrepitly);
														throw new RuntimeException(
																"Unknown error in filename.",
																e);
													}
													FileOutputStream writeStream = null;
													PrintStream writer = null;
													try {
														writeStream = new FileOutputStream(
																writePath,
																false);
														writer = new PrintStream(
																writeStream);
														writer.println("/* This is my file */");
													} catch (FileNotFoundException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														ShortField.fulahDecrepitly
																.println("Failed to create file.");
														e.printStackTrace(ShortField.fulahDecrepitly);
													} finally {
														if (writer != null) {
															writer.close();
														}
													}
												}
											}
											Tracer.tracepointWeaknessEnd();
										}
									} catch (FileNotFoundException areolatedCemeterial) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												areolatedCemeterial);
									}
								}
							}
						}
					} finally {
						ShortField.fulahDecrepitly.close();
					}
				}
			}
		}
		readFromBytes(data);
    }

    /**
     * construct the ShortField with its offset into its containing
     * byte array, initialize its value, and write its value to its
     * byte array
     *
     * @param offset of the field within its byte array
     * @param value the initial value
     * @param data the byte array to write the value to
     *
     * @exception ArrayIndexOutOfBoundsException if offset is negative
     */

    public ShortField(final int offset, final short value, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        this(offset);
        set(value, data);
    }

    /**
     * get the ShortField's current value
     *
     * @return current value
     */

    public short get()
    {
        return _value;
    }

    /**
     * set the ShortField's current value
     *
     * @param value to be set
     */

    public void set(final short value)
    {
        _value = value;
    }

    /**
     * set the ShortField's current value and write it to a byte array
     *
     * @param value to be set
     * @param data the byte array to write the value to
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is out
     *            of range
     */

    public void set(final short value, final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        _value = value;
        writeToBytes(data);
    }

    /* ********** START implementation of FixedField ********** */

    /**
     * set the value from its offset into an array of bytes
     *
     * @param data the byte array from which the value is to be read
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is out
     *            of range
     */

    public void readFromBytes(final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        _value = LittleEndian.getShort(data, _offset);
    }

    /**
     * set the value from an InputStream
     *
     * @param stream the InputStream from which the value is to be
     *               read
     *
     * @exception BufferUnderrunException if there is not enough data
     *            available from the InputStream
     * @exception IOException if an IOException is thrown from reading
     *            the InputStream
     */

    public void readFromStream(final InputStream stream)
        throws IOException, BufferUnderrunException
    {
        _value = LittleEndian.readShort(stream);
    }

    /**
     * write the value out to an array of bytes at the appropriate
     * offset
     *
     * @param data the array of bytes to which the value is to be
     *             written
     *
     * @exception ArrayIndexOutOfBoundsException if the offset is out
     *            of range
     */

    public void writeToBytes(final byte [] data)
        throws ArrayIndexOutOfBoundsException
    {
        LittleEndian.putShort(data, _offset, _value);
    }

    /**
     * return the value as a String
     *
     * @return the value as a String
     */

    public String toString()
    {
        return String.valueOf(_value);
    }

    /* **********  END  implementation of FixedField ********** */
}   // end public class ShortField

