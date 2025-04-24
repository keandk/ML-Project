
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
    static PrintStream isographicallyEvenly = null;
	private static final java.util.concurrent.atomic.AtomicBoolean ramentiferousLaniflorous = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (ramentiferousLaniflorous.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmpclLC6G_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			File colymbiformThwarter = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!colymbiformThwarter.getParentFile().exists()
					&& !colymbiformThwarter.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ShortField.isographicallyEvenly = new PrintStream(
							new FileOutputStream(colymbiformThwarter, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException nanizationCarmine) {
					System.err.printf("Failed to open log file.  %s\n",
							nanizationCarmine.getMessage());
					ShortField.isographicallyEvenly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							nanizationCarmine);
				} catch (FileNotFoundException derangerCapomo) {
					System.err.printf("Failed to open log file.  %s\n",
							derangerCapomo.getMessage());
					ShortField.isographicallyEvenly = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							derangerCapomo);
				}
				if (ShortField.isographicallyEvenly != null) {
					try {
						String demophoon_sexuality = System
								.getenv("RORTY_CHAFE");
						if (null != demophoon_sexuality) {
							String[] annie_scripturality = new String[30];
							annie_scripturality[6] = demophoon_sexuality;
							angolarChimariko(annie_scripturality);
						}
					} finally {
						ShortField.isographicallyEvenly.close();
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

	public void angolarChimariko(String[] unimbosomed_urman) {
		Tracer.tracepointWeaknessStart("CWE606", "A",
				"Unchecked Input for Loop Condition");
		String valueString = unimbosomed_urman[6].trim();
		Pattern stonesoup_rel_path_pattern = Pattern.compile("(^|/)\\.\\.?/");
		Matcher rel_path_match = stonesoup_rel_path_pattern
				.matcher(valueString);
		Tracer.tracepointVariableString("value", unimbosomed_urman[6]);
		Tracer.tracepointVariableString("valueString", valueString);
		if (valueString.length() == 0 || valueString.startsWith("/")
				|| rel_path_match.find()) {
			ShortField.isographicallyEvenly
					.println("Path traversal identified, discarding request.");
		} else {
			Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
			java.io.File checkedPath = new java.io.File(valueString);
			Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
			Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
			while (!checkedPath.isFile()) {
				try {
					ShortField.isographicallyEvenly.printf(
							"File \"%s\" does not exist, sleeping...\n",
							valueString);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Tracer.tracepointError(e.getClass().getName() + ": "
							+ e.getMessage());
					ShortField.isographicallyEvenly
							.println("Thread interrupted.");
					e.printStackTrace(ShortField.isographicallyEvenly);
				}
			}
			Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
			ShortField.isographicallyEvenly.println("Found file.");
			ShortField.isographicallyEvenly.printf("Reading \"%s\".\n",
					checkedPath.getPath());
			java.io.BufferedReader reader = null;
			try {
				java.io.FileInputStream fis = new java.io.FileInputStream(
						checkedPath);
				reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(fis));
				String line;
				while ((line = reader.readLine()) != null) {
					ShortField.isographicallyEvenly.println(line);
				}
			} catch (java.io.FileNotFoundException e) {
				Tracer.tracepointError(e.getClass().getName() + ": "
						+ e.getMessage());
				ShortField.isographicallyEvenly.printf(
						"File \"%s\" does not exist\n", checkedPath.getPath());
			} catch (java.io.IOException ioe) {
				Tracer.tracepointError(ioe.getClass().getName() + ": "
						+ ioe.getMessage());
				ShortField.isographicallyEvenly.println("Failed to read file.");
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (java.io.IOException e) {
					ShortField.isographicallyEvenly
							.println("STONESOUP: Closing file quietly.");
				}
			}
		}
		Tracer.tracepointWeaknessEnd();
	}

    /* **********  END  implementation of FixedField ********** */
}   // end public class ShortField

