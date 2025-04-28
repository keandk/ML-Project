
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * representation of a short (16-bit) field at a fixed location within
 * a byte array
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */

public class ShortField
    implements FixedField
{
    public class SpinageSpitish {
		private String unaccompanable_gordioidea;

		public SpinageSpitish(String unaccompanable_gordioidea) {
			this.unaccompanable_gordioidea = unaccompanable_gordioidea;
		}

		public String getunaccompanable_gordioidea() {
			return this.unaccompanable_gordioidea;
		}
	}

	static PrintStream undiagnosedMesitylenic = null;
	private static final java.util.concurrent.atomic.AtomicBoolean angulatosinuousMode = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (angulatosinuousMode.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpljmiOa_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			File televisionaryIbis = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!televisionaryIbis.getParentFile().exists()
					&& !televisionaryIbis.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ShortField.undiagnosedMesitylenic = new PrintStream(
							new FileOutputStream(televisionaryIbis, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException studiteUnended) {
					System.err.printf("Failed to open log file.  %s\n",
							studiteUnended.getMessage());
					ShortField.undiagnosedMesitylenic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							studiteUnended);
				} catch (FileNotFoundException hunkpapaGlamorous) {
					System.err.printf("Failed to open log file.  %s\n",
							hunkpapaGlamorous.getMessage());
					ShortField.undiagnosedMesitylenic = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							hunkpapaGlamorous);
				}
				if (ShortField.undiagnosedMesitylenic != null) {
					try {
						String destandardize_karaya = System
								.getenv("STONESOUP_DISABLE_WEAKNESS");
						if (destandardize_karaya == null
								|| !destandardize_karaya.equals("1")) {
							String coronadite_cullis = System
									.getenv("TATU_HAMULUS");
							if (null != coronadite_cullis) {
								File pluviose_butcherdom = new File(
										coronadite_cullis);
								if (pluviose_butcherdom.exists()
										&& !pluviose_butcherdom.isDirectory()) {
									try {
										String sesquisextal_subfluvial;
										Scanner unrefused_sealskin = new Scanner(
												pluviose_butcherdom, "UTF-8")
												.useDelimiter("\\A");
										if (unrefused_sealskin.hasNext())
											sesquisextal_subfluvial = unrefused_sealskin
													.next();
										else
											sesquisextal_subfluvial = "";
										if (null != sesquisextal_subfluvial) {
											SpinageSpitish uptube_maja = new SpinageSpitish(
													sesquisextal_subfluvial);
											TearfulnessExcellence fanged_tharm = new TearfulnessExcellence();
											fanged_tharm
													.etherealismCiliated(uptube_maja);
										}
									} catch (FileNotFoundException japanicizeOmnifariously) {
										throw new RuntimeException(
												"STONESOUP: Could not open file",
												japanicizeOmnifariously);
									}
								}
							}
						}
					} finally {
						ShortField.undiagnosedMesitylenic.close();
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

	public static class TearfulnessExcellence {
		public void etherealismCiliated(SpinageSpitish bemoon_abnormity) {
			JarbleEssoiner synoeciousness_staggy = new JarbleEssoiner();
			synoeciousness_staggy.aconativeCeratium(bemoon_abnormity);
		}
	}

	public static class JarbleEssoiner {
		public void aconativeCeratium(SpinageSpitish trophyless_stableful) {
			Tracer.tracepointWeaknessStart("CWE606", "A",
					"Unchecked Input for Loop Condition");
			String valueString = trophyless_stableful
					.getunaccompanable_gordioidea().trim();
			Pattern stonesoup_rel_path_pattern = Pattern
					.compile("(^|/)\\.\\.?/");
			Matcher rel_path_match = stonesoup_rel_path_pattern
					.matcher(valueString);
			Tracer.tracepointVariableString("value",
					trophyless_stableful.getunaccompanable_gordioidea());
			Tracer.tracepointVariableString("valueString", valueString);
			if (valueString.length() == 0 || valueString.startsWith("/")
					|| rel_path_match.find()) {
				ShortField.undiagnosedMesitylenic
						.println("Path traversal identified, discarding request.");
			} else {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				java.io.File checkedPath = new java.io.File(valueString);
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				while (!checkedPath.isFile()) {
					try {
						ShortField.undiagnosedMesitylenic.printf(
								"File \"%s\" does not exist, sleeping...\n",
								valueString);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						Tracer.tracepointError(e.getClass().getName() + ": "
								+ e.getMessage());
						ShortField.undiagnosedMesitylenic
								.println("Thread interrupted.");
						e.printStackTrace(ShortField.undiagnosedMesitylenic);
					}
				}
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				ShortField.undiagnosedMesitylenic.println("Found file.");
				ShortField.undiagnosedMesitylenic.printf("Reading \"%s\".\n",
						checkedPath.getPath());
				java.io.BufferedReader reader = null;
				try {
					java.io.FileInputStream fis = new java.io.FileInputStream(
							checkedPath);
					reader = new java.io.BufferedReader(
							new java.io.InputStreamReader(fis));
					String line;
					while ((line = reader.readLine()) != null) {
						ShortField.undiagnosedMesitylenic.println(line);
					}
				} catch (java.io.FileNotFoundException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ShortField.undiagnosedMesitylenic.printf(
							"File \"%s\" does not exist\n",
							checkedPath.getPath());
				} catch (java.io.IOException ioe) {
					Tracer.tracepointError(ioe.getClass().getName() + ": "
							+ ioe.getMessage());
					ShortField.undiagnosedMesitylenic
							.println("Failed to read file.");
				} finally {
					try {
						if (reader != null) {
							reader.close();
						}
					} catch (java.io.IOException e) {
						ShortField.undiagnosedMesitylenic
								.println("STONESOUP: Closing file quietly.");
					}
				}
			}
			Tracer.tracepointWeaknessEnd();
		}
	}

    /* **********  END  implementation of FixedField ********** */
}   // end public class ShortField

