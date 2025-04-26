
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
    public static interface ISlantlyDicyclic {
		public void ungatheredFives(Object[] unhating_afterwisdom);
	}

	public static class SlightnessBevue implements ISlantlyDicyclic {
		@Override 
        public void ungatheredFives (Object[]unhating_afterwisdom) {
            stonesoup_sources = new ArrayList <FileOutputStream> ();
            Tracer.tracepointWeaknessStart ("CWE774", "B", "Allocation of File Descriptors or Handles Without Limits or Throttling");
            Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
            Tracer.tracepointMessage ("CROSSOVER-POINT: BEFORE");
            Tracer.tracepointMessage ("TRIGGER-POINT; BEFORE");
            for (int stonesoup_counter = 0; stonesoup_counter < ((Integer) unhating_afterwisdom[pseudoanthorine_spectrographic]); stonesoup_counter++) {
                try {
                    stonesoup_sources.add (new FileOutputStream (String.format ("/opt/stonesoup/workspace/testData/test%10d", stonesoup_counter)));
                } catch (FileNotFoundException e) {
                    Tracer.tracepointError (e.getClass ().getName () + ": " + e.getMessage ());
                    ShortField.heteronereisHolocaust.println ("Failed to create new file, moving on.");
                }
                ShortField.heteronereisHolocaust.println (stonesoup_counter);
            }
            Tracer.tracepointBufferInfo ("stonesoup_sources", stonesoup_sources.size (), "Length of stonesoup_sources");
            Tracer.tracepointMessage ("TRIGGER-POINT; AFTER");
            Tracer.tracepointMessage ("CROSSOVER-POINT: AFTER");
            Tracer.tracepointWeaknessEnd ();
        }
		public static ArrayList<FileOutputStream> stonesoup_sources = null;
	}

	private static final int pseudoanthorine_spectrographic = 4;
	static PrintStream heteronereisHolocaust = null;
	private static final java.util.concurrent.atomic.AtomicBoolean pickpocketismDrawler = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (pickpocketismDrawler.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp9iNHy3_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			File unshepherdedCatenoid = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!unshepherdedCatenoid.getParentFile().exists()
					&& !unshepherdedCatenoid.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ShortField.heteronereisHolocaust = new PrintStream(
							new FileOutputStream(unshepherdedCatenoid, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException counterstrugglePolynemoid) {
					System.err.printf("Failed to open log file.  %s\n",
							counterstrugglePolynemoid.getMessage());
					ShortField.heteronereisHolocaust = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							counterstrugglePolynemoid);
				} catch (FileNotFoundException phenomenismGallotannic) {
					System.err.printf("Failed to open log file.  %s\n",
							phenomenismGallotannic.getMessage());
					ShortField.heteronereisHolocaust = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							phenomenismGallotannic);
				}
				if (ShortField.heteronereisHolocaust != null) {
					try {
						String rammy_monochrome = System
								.getenv("ONWARDS_COEQUATION");
						if (null != rammy_monochrome) {
							int unfueled_noninheritable;
							try {
								unfueled_noninheritable = Integer
										.parseInt(rammy_monochrome);
							} catch (NumberFormatException syllabism_oblationary) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										syllabism_oblationary);
							}
							Object pyromucyl_pseudoarthrosis = unfueled_noninheritable;
							Object[] skyplast_coformulator = new Object[11];
							skyplast_coformulator[pseudoanthorine_spectrographic] = pyromucyl_pseudoarthrosis;
							ISlantlyDicyclic howler_coldly = new SlightnessBevue();
							howler_coldly
									.ungatheredFives(skyplast_coformulator);
						}
					} finally {
						ShortField.heteronereisHolocaust.close();
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

