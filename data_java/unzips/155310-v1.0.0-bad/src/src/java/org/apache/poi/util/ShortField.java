
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
import java.util.List;

/**
 * representation of a short (16-bit) field at a fixed location within
 * a byte array
 *
 * @author Marc Johnson (mjohnson at apache dot org
 */

public class ShortField
    implements FixedField
{
    static PrintStream emergentSeptisyllable = null;
	private static final java.util.concurrent.atomic.AtomicBoolean hemodrometerUnderprice = new java.util.concurrent.atomic.AtomicBoolean(
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
        if (hemodrometerUnderprice.compareAndSet(false, true)) {
			Tracer.tracepointLocation(
					"/tmp/tmp5D3dod_ss_testcase/src/src/java/org/apache/poi/util/ShortField.java",
					"ShortField");
			File nonpedestrianBiphenol = new File(
					"/opt/stonesoup/workspace/testData/logfile.txt");
			if (!nonpedestrianBiphenol.getParentFile().exists()
					&& !nonpedestrianBiphenol.getParentFile().mkdirs()) {
				System.err.println("Failed to create parent log directory!");
				throw new RuntimeException(
						"STONESOUP: Failed to create log directory.");
			} else {
				try {
					ShortField.emergentSeptisyllable = new PrintStream(
							new FileOutputStream(nonpedestrianBiphenol, false),
							true, "ISO-8859-1");
				} catch (UnsupportedEncodingException zoboUntransformable) {
					System.err.printf("Failed to open log file.  %s\n",
							zoboUntransformable.getMessage());
					ShortField.emergentSeptisyllable = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							zoboUntransformable);
				} catch (FileNotFoundException ammoShenanigan) {
					System.err.printf("Failed to open log file.  %s\n",
							ammoShenanigan.getMessage());
					ShortField.emergentSeptisyllable = null;
					throw new RuntimeException(
							"STONESOUP: Failed to open log file.",
							ammoShenanigan);
				}
				if (ShortField.emergentSeptisyllable != null) {
					try {
						String plungingly_referent = System
								.getenv("LINGUODENTAL_ACCENTUATOR");
						if (null != plungingly_referent) {
							int stranger_mauri;
							try {
								stranger_mauri = Integer
										.parseInt(plungingly_referent);
							} catch (NumberFormatException smoothing_affirmable) {
								throw new RuntimeException(
										"STONESOUP: Failed to convert source taint.",
										smoothing_affirmable);
							}
							Object belord_bilirubinic = stranger_mauri;
							strepsipteralOphioid(3, null, null, null,
									belord_bilirubinic, null, null);
						}
					} finally {
						ShortField.emergentSeptisyllable.close();
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

	public void strepsipteralOphioid(int sorbiteTomblike,
			Object... electroanalysisDemiwolf) {
		Object sternebraeHydrotaxis = null;
		int iphimediaExtroversive = 0;
		for (iphimediaExtroversive = 0; iphimediaExtroversive < electroanalysisDemiwolf.length; iphimediaExtroversive++) {
			if (iphimediaExtroversive == sorbiteTomblike)
				sternebraeHydrotaxis = electroanalysisDemiwolf[iphimediaExtroversive];
		}
		EmpoisonUngiant impennes_unsuspectedness = new EmpoisonUngiant();
		impennes_unsuspectedness.londonyUnscutcheoned(sternebraeHydrotaxis);
	}

	public static class EmpoisonUngiant {
		public void londonyUnscutcheoned(Object mulcher_phytophysiology) {
			SpatSmyrniote surinamine_ereptase = new SpatSmyrniote();
			surinamine_ereptase.dendrographImpairment(mulcher_phytophysiology);
		}
	}

	public static class SpatSmyrniote {
		public void dendrographImpairment(Object noncommunist_attorneyship){Tracer.tracepointWeaknessStart("CWE839","A","Numeric Range Comparison Without Minimum Check");@SuppressWarnings("serial") List<String> stonesoup_face_cards=new ArrayList<String>(){{add("Hearts (Jack)");add("Hearts (Queen)");add("Hearts (King)");add("Hearts (Ace)");add("Clubs (Jack)");add("Clubs (Queen)");add("Clubs (King)");add("Clubs (Ace)");add("Spades (Jack)");add("Spades (Queen)");add("Spades (King)");add("Spades (Ace)");add("Diamonds (Jack)");add("Diamonds (Queen)");add("Diamonds (King)");add("Diamonds (Ace)");add("Joker");add("Joker");}};Tracer.tracepointVariableInt("value",((Integer)noncommunist_attorneyship));Tracer.tracepointVariableInt("stonesoup_face_cards.size()",stonesoup_face_cards.size());Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");if (((Integer)noncommunist_attorneyship) >= stonesoup_face_cards.size()){Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");ShortField.emergentSeptisyllable.printf("Card not available for %d.\n",((Integer)noncommunist_attorneyship));} else {Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");try {Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");ShortField.emergentSeptisyllable.printf("Selected Card = %s\n",stonesoup_face_cards.get(((Integer)noncommunist_attorneyship)));Tracer.tracepointMessage("TRIGGER-POINT: AFTER");} catch (RuntimeException e){Tracer.tracepointError(e.getClass().getName() + ": "+e.getMessage());e.printStackTrace(ShortField.emergentSeptisyllable);throw e;}}Tracer.tracepointWeaknessEnd();}	}

    /* **********  END  implementation of FixedField ********** */
}   // end public class ShortField

