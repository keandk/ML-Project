
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
    public class LengthsmanIslamitish<T> {
		private T anaphylactoid_acalypha;

		public LengthsmanIslamitish(T anaphylactoid_acalypha) {
			this.anaphylactoid_acalypha = anaphylactoid_acalypha;
		}

		public T getanaphylactoid_acalypha() {
			return this.anaphylactoid_acalypha;
		}
	}

	static PrintStream relesseePlugtree = null;
	private static final java.util.concurrent.atomic.AtomicBoolean percentualAlemite = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (percentualAlemite.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp025sLF_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			File heterocephalousPokable = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!heterocephalousPokable.getParentFile().exists()
					&& !heterocephalousPokable.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ShortField.relesseePlugtree = new PrintStream(
							new FileOutputStream(heterocephalousPokable, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException abroniaGelandesprung) {
					System.err.printf("Failed to open log file.  %s\n",
							abroniaGelandesprung.getMessage());
					ShortField.relesseePlugtree = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							abroniaGelandesprung);
				} catch (FileNotFoundException nobPyrotantalate) {
					System.err.printf("Failed to open log file.  %s\n",
							nobPyrotantalate.getMessage());
					ShortField.relesseePlugtree = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nobPyrotantalate);
				}
				if (ShortField.relesseePlugtree != null) {
					try {
						String albuminate_pocketful = System
								.getenv("ATEETER_IMMUTABILITY");
						if (null != albuminate_pocketful) {
							long scabiosa_synoptically;
							try {
								scabiosa_synoptically = Long
										.parseLong(albuminate_pocketful);
							} catch (NumberFormatException inchpin_lassieish) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										inchpin_lassieish);
							}
							Object pileate_metalization = scabiosa_synoptically;
							LengthsmanIslamitish<Object> enarch_athermic = new LengthsmanIslamitish<Object>(
									pileate_metalization);
							Tracer.tracepointWeaknessStart("CWE197", "A",
									"Numeric Trucation Error");
							Tracer.tracepointVariableLong("value",
									((Long) enarch_athermic
											.getanaphylactoid_acalypha()));
							if (((Long) enarch_athermic
									.getanaphylactoid_acalypha()) > 0) {
								Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
								int stonesoup_max_value = (int) ((long) ((Long) enarch_athermic
										.getanaphylactoid_acalypha()));
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
									ShortField.relesseePlugtree
											.println("STONESOUP: Failed to create PRNG.");
									e.printStackTrace(ShortField.relesseePlugtree);
								}
								if (random != null) {
									Tracer.tracepointMessage("Generating random variable between 0 and stonesoup_max_value");
									try {
										ShortField.relesseePlugtree
												.printf("Generating random value between %d (inclusive) and %d (exclusive).\n",
														0, stonesoup_max_value);
										Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
										ShortField.relesseePlugtree
												.printf("Random choice: %d\n",
														random.nextInt(stonesoup_max_value));
										Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
									} catch (RuntimeException e) {
										Tracer.tracepointError(e.getClass()
												.getName()
												+ ": "
												+ e.getMessage());
										e.printStackTrace(ShortField.relesseePlugtree);
										throw e;
									}
									Tracer.tracepointMessage("After random value generation.");
								}
							}
							Tracer.tracepointWeaknessEnd();
						}
					} finally {
						ShortField.relesseePlugtree.close();
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

