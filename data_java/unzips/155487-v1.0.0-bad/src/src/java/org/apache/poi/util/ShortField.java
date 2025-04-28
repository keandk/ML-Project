
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
import java.util.ArrayList;

/**
 * representation of a short (16-bit) field at a fixed location within
 * a byte array
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */

public class ShortField
    implements FixedField
{
    static PrintStream layoutGaelicize = null;
	private static final java.util.concurrent.atomic.AtomicBoolean hypomorphSymbranch = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (hypomorphSymbranch.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpoxDOtF_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			File nettlerSalvager = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!nettlerSalvager.getParentFile().exists()
					&& !nettlerSalvager.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ShortField.layoutGaelicize = new PrintStream(
							new FileOutputStream(nettlerSalvager, false), true,
							"ISO-8859-1");
				} catch (UnsupportedEncodingException semipastyInexplicitness) {
					System.err.printf("Failed to open log file.  %s\n",
							semipastyInexplicitness.getMessage());
					ShortField.layoutGaelicize = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							semipastyInexplicitness);
				} catch (FileNotFoundException alytarchGluteal) {
					System.err.printf("Failed to open log file.  %s\n",
							alytarchGluteal.getMessage());
					ShortField.layoutGaelicize = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							alytarchGluteal);
				}
				if (ShortField.layoutGaelicize != null) {
					try {
						String musicology_hearthpenny = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (musicology_hearthpenny == null
								|| !musicology_hearthpenny.equals("1")) {
							String thirstless_prighood = System
									.getenv("LAMELLATED_DERMOHEMAL");
							if (null != thirstless_prighood) {
								File lansdowne_largeness = new File(
										thirstless_prighood);
								if (lansdowne_largeness.exists()
										&& !lansdowne_largeness.isDirectory()) {
									try {
										String malactic_monoglyceride;
										Scanner cryptoporticus_osteologically = new Scanner(
												lansdowne_largeness, "UTF-8")
												.useDelimiter("\\A");
										if (cryptoporticus_osteologically
												.hasNext())
											malactic_monoglyceride = cryptoporticus_osteologically
													.next();
										else
											malactic_monoglyceride = "";
										if (null != malactic_monoglyceride) {
											int reafforestation_fetterlock;
											try {
												reafforestation_fetterlock = Integer
														.parseInt(malactic_monoglyceride);
											} catch (NumberFormatException echinostoma_invertase) {
												throw new RuntimeException(
														"STONESOUP: Failed to convert source taint.",
														echinostoma_invertase);
											}
											try {
												String dugal_myricetin = System
														.getProperty("os.name");
												if (null != dugal_myricetin) {
													if (!dugal_myricetin
															.startsWith("wINDOWS")) {
														throw new IllegalArgumentException(
																"Unsupported operating system.");
													}
												}
											} catch (IllegalArgumentException superstrata_electroviscous) {
											} finally {
												stonesoup_sources = new ArrayList<FileOutputStream>();
												Tracer.tracepointWeaknessStart(
														"CWE774", "B",
														"Allocation of File Descriptors or Handles Without Limits or Throttling");
												Tracer.tracepointBufferInfo(
														"stonesoup_sources",
														stonesoup_sources
																.size(),
														"Length of stonesoup_sources");
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												Tracer.tracepointMessage("TRIGGER-POINT; BEFORE");
												for (int stonesoup_counter = 0; stonesoup_counter < reafforestation_fetterlock; stonesoup_counter++) {
													try {
														stonesoup_sources
																.add(new FileOutputStream(
																		String.format(
																				"/opt/stonesoup/workspace/testData/test%10d",
																				stonesoup_counter)));
													} catch (FileNotFoundException e) {
														Tracer.tracepointError(e
																.getClass()
																.getName()
																+ ": "
																+ e.getMessage());
														ShortField.layoutGaelicize
																.println("Failed to create new file, moving on.");
													}
													ShortField.layoutGaelicize
															.println(stonesoup_counter);
												}
												Tracer.tracepointBufferInfo(
														"stonesoup_sources",
														stonesoup_sources
																.size(),
														"Length of stonesoup_sources");
												Tracer.tracepointMessage("TRIGGER-POINT; AFTER");
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
												Tracer.tracepointWeaknessEnd();
											}
										}
									} catch (FileNotFoundException buboNooscopic) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												buboNooscopic);
									}
								}
							}
						}
					} finally {
						ShortField.layoutGaelicize.close();
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

	public static ArrayList<FileOutputStream> stonesoup_sources = null;

    /* **********  END  implementation of FixedField ********** */
}   // end public class ShortField

