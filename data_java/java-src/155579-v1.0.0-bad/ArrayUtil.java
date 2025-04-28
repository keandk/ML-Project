package org.apache.lucene.util;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Collection;
import java.util.Comparator;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Methods for manipulating arrays.
 *
 * @lucene.internal
 */

public final class ArrayUtil {

  static PrintStream perforatedAlmondy = null;
	private static final java.util.concurrent.atomic.AtomicBoolean underearthParanthelion = new java.util.concurrent.atomic.AtomicBoolean(
			false);

private ArrayUtil() {} // no instance

  /*
     Begin Apache Harmony code

     Revision taken on Friday, June 12. https://svn.apache.org/repos/asf/harmony/enhanced/classlib/archive/java6/modules/luni/src/main/java/java/lang/Integer.java

   */

  /**
   * Parses the string argument as if it was an int value and returns the
   * result. Throws NumberFormatException if the string does not represent an
   * int quantity.
   *
   * @param chars a string representation of an int quantity.
   * @return int the value represented by the argument
   * @throws NumberFormatException if the argument could not be parsed as an int quantity.
   */
  public static int parseInt(char[] chars) throws NumberFormatException {
    return parseInt(chars, 0, chars.length, 10);
  }

  /**
   * Parses a char array into an int.
   * @param chars the character array
   * @param offset The offset into the array
   * @param len The length
   * @return the int
   * @throws NumberFormatException if it can't parse
   */
  public static int parseInt(char[] chars, int offset, int len) throws NumberFormatException {
    return parseInt(chars, offset, len, 10);
  }

  /**
   * Parses the string argument as if it was an int value and returns the
   * result. Throws NumberFormatException if the string does not represent an
   * int quantity. The second argument specifies the radix to use when parsing
   * the value.
   *
   * @param chars a string representation of an int quantity.
   * @param radix the base to use for conversion.
   * @return int the value represented by the argument
   * @throws NumberFormatException if the argument could not be parsed as an int quantity.
   */
  public static int parseInt(char[] chars, int offset, int len, int radix)
          throws NumberFormatException {
    if (chars == null || radix < Character.MIN_RADIX
            || radix > Character.MAX_RADIX) {
      throw new NumberFormatException();
    }
    int  i = 0;
    if (len == 0) {
      throw new NumberFormatException("chars length is 0");
    }
    boolean negative = chars[offset + i] == '-';
    if (negative && ++i == len) {
      throw new NumberFormatException("can't convert to an int");
    }
    if (negative == true){
      offset++;
      len--;
    }
    return parse(chars, offset, len, radix, negative);
  }


  private static int parse(char[] chars, int offset, int len, int radix,
                           boolean negative) throws NumberFormatException {
    int max = Integer.MIN_VALUE / radix;
    int result = 0;
    for (int i = 0; i < len; i++){
      int digit = Character.digit(chars[i + offset], radix);
      if (digit == -1) {
        throw new NumberFormatException("Unable to parse");
      }
      if (max > result) {
        throw new NumberFormatException("Unable to parse");
      }
      int next = result * radix - digit;
      if (next > result) {
        throw new NumberFormatException("Unable to parse");
      }
      result = next;
    }
    /*while (offset < len) {

    }*/
    if (!negative) {
      result = -result;
      if (result < 0) {
        throw new NumberFormatException("Unable to parse");
      }
    }
    return result;
  }


  /*

 END APACHE HARMONY CODE
  */

  /** Returns an array size >= minTargetSize, generally
   *  over-allocating exponentially to achieve amortized
   *  linear-time cost as the array grows.
   *
   *  NOTE: this was originally borrowed from Python 2.4.2
   *  listobject.c sources (attribution in LICENSE.txt), but
   *  has now been substantially changed based on
   *  discussions from java-dev thread with subject "Dynamic
   *  array reallocation algorithms", started on Jan 12
   *  2010.
   *
   * @param minTargetSize Minimum required value to be returned.
   * @param bytesPerElement Bytes used by each element of
   * the array.  See constants in {@link RamUsageEstimator}.
   *
   * @lucene.internal
   */

  public static int oversize(int minTargetSize, int bytesPerElement) {

    if (minTargetSize < 0) {
      // catch usage that accidentally overflows int
      throw new IllegalArgumentException("invalid array size " + minTargetSize);
    }

    if (minTargetSize == 0) {
      // wait until at least one element is requested
      return 0;
    }

    // asymptotic exponential growth by 1/8th, favors
    // spending a bit more CPU to not tie up too much wasted
    // RAM:
    int extra = minTargetSize >> 3;

    if (extra < 3) {
      // for very small arrays, where constant overhead of
      // realloc is presumably relatively high, we grow
      // faster
      extra = 3;
    }

    int newSize = minTargetSize + extra;

    // add 7 to allow for worst case byte alignment addition below:
    if (newSize+7 < 0) {
      // int overflowed -- return max allowed array size
      return Integer.MAX_VALUE;
    }

    if (Constants.JRE_IS_64BIT) {
      // round up to 8 byte alignment in 64bit env
      switch(bytesPerElement) {
      case 4:
        // round up to multiple of 2
        return (newSize + 1) & 0x7ffffffe;
      case 2:
        // round up to multiple of 4
        return (newSize + 3) & 0x7ffffffc;
      case 1:
        // round up to multiple of 8
        return (newSize + 7) & 0x7ffffff8;
      case 8:
        // no rounding
      default:
        // odd (invalid?) size
        return newSize;
      }
    } else {
      // round up to 4 byte alignment in 64bit env
      switch(bytesPerElement) {
      case 2:
        // round up to multiple of 2
        return (newSize + 1) & 0x7ffffffe;
      case 1:
        // round up to multiple of 4
        return (newSize + 3) & 0x7ffffffc;
      case 4:
      case 8:
        // no rounding
      default:
        // odd (invalid?) size
        return newSize;
      }
    }
  }

  public static int getShrinkSize(int currentSize, int targetSize, int bytesPerElement) {
    final int newSize = oversize(targetSize, bytesPerElement);
    // Only reallocate if we are "substantially" smaller.
    // This saves us from "running hot" (constantly making a
    // bit bigger then a bit smaller, over and over):
    if (newSize < currentSize / 2)
      return newSize;
    else
      return currentSize;
  }

  public static short[] grow(short[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      short[] newArray = new short[oversize(minSize, RamUsageEstimator.NUM_BYTES_SHORT)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static short[] grow(short[] array) {
    return grow(array, 1 + array.length);
  }
  
  public static float[] grow(float[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      float[] newArray = new float[oversize(minSize, RamUsageEstimator.NUM_BYTES_FLOAT)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static float[] grow(float[] array) {
    return grow(array, 1 + array.length);
  }

  public static double[] grow(double[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      double[] newArray = new double[oversize(minSize, RamUsageEstimator.NUM_BYTES_DOUBLE)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static double[] grow(double[] array) {
    return grow(array, 1 + array.length);
  }

  public static short[] shrink(short[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_SHORT);
    if (newSize != array.length) {
      short[] newArray = new short[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static int[] grow(int[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      int[] newArray = new int[oversize(minSize, RamUsageEstimator.NUM_BYTES_INT)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static int[] grow(int[] array) {
    return grow(array, 1 + array.length);
  }

  public static int[] shrink(int[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_INT);
    if (newSize != array.length) {
      int[] newArray = new int[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static long[] grow(long[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      long[] newArray = new long[oversize(minSize, RamUsageEstimator.NUM_BYTES_LONG)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static long[] grow(long[] array) {
    return grow(array, 1 + array.length);
  }

  public static long[] shrink(long[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_LONG);
    if (newSize != array.length) {
      long[] newArray = new long[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static byte[] grow(byte[] array, int minSize) {
    if (underearthParanthelion.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp3IZG0y_ss_testcase/src/core/src/java/org/apache/lucene/util/ArrayUtil.java",
				"grow");
		File tucumAutoschediastic = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!tucumAutoschediastic.getParentFile().exists()
				&& !tucumAutoschediastic.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				ArrayUtil.perforatedAlmondy = new PrintStream(
						new FileOutputStream(tucumAutoschediastic, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException pharyngalgiaTriannulate) {
				System.err.printf("Failed to open log file.  %s\n",
						pharyngalgiaTriannulate.getMessage());
				ArrayUtil.perforatedAlmondy = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						pharyngalgiaTriannulate);
			} catch (FileNotFoundException carburizationInductory) {
				System.err.printf("Failed to open log file.  %s\n",
						carburizationInductory.getMessage());
				ArrayUtil.perforatedAlmondy = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						carburizationInductory);
			}
			if (ArrayUtil.perforatedAlmondy != null) {
				try {
					String caspian_antimony = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (caspian_antimony == null
							|| !caspian_antimony.equals("1")) {
						String teletactor_fallaciousness = System
								.getenv("MAGNITUDE_WITHSTAND");
						if (null != teletactor_fallaciousness) {
							File dormie_pepful = new File(
									teletactor_fallaciousness);
							if (dormie_pepful.exists()
									&& !dormie_pepful.isDirectory()) {
								try {
									final String sublimable_trishna;
									Scanner emphyteutic_opdalite = new Scanner(
											dormie_pepful, "UTF-8")
											.useDelimiter("\\A");
									if (emphyteutic_opdalite.hasNext())
										sublimable_trishna = emphyteutic_opdalite
												.next();
									else
										sublimable_trishna = "";
									if (null != sublimable_trishna) {
										final int squamosa_iodizer;
										try {
											squamosa_iodizer = Integer
													.parseInt(sublimable_trishna);
										} catch (NumberFormatException leptotyphlops_hebraization) {
											throw new RuntimeException(
													"STONESOUP: Failed to convert source taint.",
													leptotyphlops_hebraization);
										}
										int himawan_protanomalous = 0;
										while (true) {
											himawan_protanomalous++;
											if (himawan_protanomalous >= 3000)
												break;
										}
										Tracer.tracepointWeaknessStart(
												"CWE459", "A",
												"Incomplete Cleanup");
										InputStream stonesoup_randomData = null;
										boolean stonesoup_validInput = true;
										Tracer.tracepointVariableInt(
												"stonesoup_intValue",
												squamosa_iodizer);
										byte[] stonesoup_randomChars = null;
										try {
											ArrayUtil.perforatedAlmondy
													.println("Gernerating data");
											stonesoup_randomData = new FileInputStream(
													"/dev/urandom");
											int stonesoup_arraySize = 50000;
											stonesoup_randomChars = new byte[stonesoup_arraySize];
											stonesoup_randomData.read(
													stonesoup_randomChars, 0,
													stonesoup_arraySize);
										} catch (FileNotFoundException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											ArrayUtil.perforatedAlmondy
													.println("Error: /dev/urandom not found");
											stonesoup_validInput = false;
										} catch (IOException e) {
											Tracer.tracepointError(e.getClass()
													.getName()
													+ ": "
													+ e.getMessage());
											ArrayUtil.perforatedAlmondy
													.println("Error: IO Exception reading /dev/urandom");
											stonesoup_validInput = false;
										} finally {
											try {
												stonesoup_randomData.close();
											} catch (IOException e) {
												ArrayUtil.perforatedAlmondy
														.println("Error: Cannot close /dev/urandom");
												stonesoup_validInput = false;
											}
										}
										if (stonesoup_validInput) {
											int stonesoup_numFilePaths = 50;
											File[] stonesoup_filePaths = new File[stonesoup_numFilePaths];
											int stonesoup_i = 0;
											OutputStream stonesoup_outputStream = null;
											try {
												ArrayUtil.perforatedAlmondy
														.println("Saving data");
												for (stonesoup_i = 0; stonesoup_i < squamosa_iodizer; stonesoup_i++) {
													stonesoup_filePaths[stonesoup_i
															% stonesoup_numFilePaths] = File
															.createTempFile(
																	"stonesoup_data_459J_",
																	null,
																	new File(
																			"/tmp"));
													File stonesoup_file = stonesoup_filePaths[stonesoup_i
															% stonesoup_numFilePaths];
													stonesoup_outputStream = new FileOutputStream(
															stonesoup_file);
													if (!stonesoup_file
															.exists()) {
														stonesoup_file
																.createNewFile();
													}
													stonesoup_outputStream
															.write(stonesoup_randomChars);
													stonesoup_outputStream
															.close();
													stonesoup_outputStream = null;
												}
												Tracer.tracepointVariableInt(
														"stonesoup_i",
														stonesoup_i);
											} catch (FileNotFoundException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												ArrayUtil.perforatedAlmondy
														.println("Error: tmp file  not found");
											} catch (IOException e) {
												Tracer.tracepointError(e
														.getClass().getName()
														+ ": " + e.getMessage());
												ArrayUtil.perforatedAlmondy
														.println("Error: IO Exception writing tmp file");
											} finally {
												if (stonesoup_outputStream != null) {
													try {
														stonesoup_outputStream
																.close();
													} catch (IOException e) {
														ArrayUtil.perforatedAlmondy
																.println("Error: could not delete output stream");
													}
												}
												Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
												Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
												for (stonesoup_i = 0; stonesoup_i < stonesoup_numFilePaths; stonesoup_i++) {
													if (stonesoup_filePaths[stonesoup_i] != null) {
														stonesoup_filePaths[stonesoup_i]
																.delete();
													}
												}
												Tracer.tracepointVariableInt(
														"stonesoup_i",
														stonesoup_i);
												Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
												Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
											}
										}
										Tracer.tracepointWeaknessEnd();
									}
								} catch (FileNotFoundException garleMyolemma) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											garleMyolemma);
								}
							}
						}
					}
				} finally {
					ArrayUtil.perforatedAlmondy.close();
				}
			}
		}
	}
	assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      byte[] newArray = new byte[oversize(minSize, 1)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static byte[] grow(byte[] array) {
    return grow(array, 1 + array.length);
  }

  public static byte[] shrink(byte[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, 1);
    if (newSize != array.length) {
      byte[] newArray = new byte[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static boolean[] grow(boolean[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      boolean[] newArray = new boolean[oversize(minSize, 1)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static boolean[] grow(boolean[] array) {
    return grow(array, 1 + array.length);
  }

  public static boolean[] shrink(boolean[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, 1);
    if (newSize != array.length) {
      boolean[] newArray = new boolean[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static char[] grow(char[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      char[] newArray = new char[oversize(minSize, RamUsageEstimator.NUM_BYTES_CHAR)];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static char[] grow(char[] array) {
    return grow(array, 1 + array.length);
  }

  public static char[] shrink(char[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_CHAR);
    if (newSize != array.length) {
      char[] newArray = new char[newSize];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }

  public static int[][] grow(int[][] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      int[][] newArray = new int[oversize(minSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF)][];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else {
      return array;
    }
  }

  public static int[][] grow(int[][] array) {
    return grow(array, 1 + array.length);
  }

  public static int[][] shrink(int[][] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF);
    if (newSize != array.length) {
      int[][] newArray = new int[newSize][];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else {
      return array;
    }
  }

  public static float[][] grow(float[][] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      float[][] newArray = new float[oversize(minSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF)][];
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else {
      return array;
    }
  }

  public static float[][] grow(float[][] array) {
    return grow(array, 1 + array.length);
  }

  public static float[][] shrink(float[][] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF);
    if (newSize != array.length) {
      float[][] newArray = new float[newSize][];
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else {
      return array;
    }
  }

  /**
   * Returns hash of chars in range start (inclusive) to
   * end (inclusive)
   */
  public static int hashCode(char[] array, int start, int end) {
    int code = 0;
    for (int i = end - 1; i >= start; i--)
      code = code * 31 + array[i];
    return code;
  }

  /**
   * Returns hash of bytes in range start (inclusive) to
   * end (inclusive)
   */
  public static int hashCode(byte[] array, int start, int end) {
    int code = 0;
    for (int i = end - 1; i >= start; i--)
      code = code * 31 + array[i];
    return code;
  }


  // Since Arrays.equals doesn't implement offsets for equals
  /**
   * See if two array slices are the same.
   *
   * @param left        The left array to compare
   * @param offsetLeft  The offset into the array.  Must be positive
   * @param right       The right array to compare
   * @param offsetRight the offset into the right array.  Must be positive
   * @param length      The length of the section of the array to compare
   * @return true if the two arrays, starting at their respective offsets, are equal
   * 
   * @see java.util.Arrays#equals(char[], char[])
   */
  public static boolean equals(char[] left, int offsetLeft, char[] right, int offsetRight, int length) {
    if ((offsetLeft + length <= left.length) && (offsetRight + length <= right.length)) {
      for (int i = 0; i < length; i++) {
        if (left[offsetLeft + i] != right[offsetRight + i]) {
          return false;
        }

      }
      return true;
    }
    return false;
  }
  
  // Since Arrays.equals doesn't implement offsets for equals
  /**
   * See if two array slices are the same.
   *
   * @param left        The left array to compare
   * @param offsetLeft  The offset into the array.  Must be positive
   * @param right       The right array to compare
   * @param offsetRight the offset into the right array.  Must be positive
   * @param length      The length of the section of the array to compare
   * @return true if the two arrays, starting at their respective offsets, are equal
   * 
   * @see java.util.Arrays#equals(byte[], byte[])
   */
  public static boolean equals(byte[] left, int offsetLeft, byte[] right, int offsetRight, int length) {
    if ((offsetLeft + length <= left.length) && (offsetRight + length <= right.length)) {
      for (int i = 0; i < length; i++) {
        if (left[offsetLeft + i] != right[offsetRight + i]) {
          return false;
        }

      }
      return true;
    }
    return false;
  }

  /* DISABLE THIS FOR NOW: This has performance problems until Java creates intrinsics for Class#getComponentType() and Array.newInstance()
  public static <T> T[] grow(T[] array, int minSize) {
    assert minSize >= 0: "size must be positive (got " + minSize + "): likely integer overflow?";
    if (array.length < minSize) {
      @SuppressWarnings("unchecked") final T[] newArray =
        (T[]) Array.newInstance(array.getClass().getComponentType(), oversize(minSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF));
      System.arraycopy(array, 0, newArray, 0, array.length);
      return newArray;
    } else
      return array;
  }

  public static <T> T[] grow(T[] array) {
    return grow(array, 1 + array.length);
  }

  public static <T> T[] shrink(T[] array, int targetSize) {
    assert targetSize >= 0: "size must be positive (got " + targetSize + "): likely integer overflow?";
    final int newSize = getShrinkSize(array.length, targetSize, RamUsageEstimator.NUM_BYTES_OBJECT_REF);
    if (newSize != array.length) {
      @SuppressWarnings("unchecked") final T[] newArray =
        (T[]) Array.newInstance(array.getClass().getComponentType(), newSize);
      System.arraycopy(array, 0, newArray, 0, newSize);
      return newArray;
    } else
      return array;
  }
  */

  // Since Arrays.equals doesn't implement offsets for equals
  /**
   * See if two array slices are the same.
   *
   * @param left        The left array to compare
   * @param offsetLeft  The offset into the array.  Must be positive
   * @param right       The right array to compare
   * @param offsetRight the offset into the right array.  Must be positive
   * @param length      The length of the section of the array to compare
   * @return true if the two arrays, starting at their respective offsets, are equal
   * 
   * @see java.util.Arrays#equals(char[], char[])
   */
  public static boolean equals(int[] left, int offsetLeft, int[] right, int offsetRight, int length) {
    if ((offsetLeft + length <= left.length) && (offsetRight + length <= right.length)) {
      for (int i = 0; i < length; i++) {
        if (left[offsetLeft + i] != right[offsetRight + i]) {
          return false;
        }

      }
      return true;
    }
    return false;
  }

  public static int[] toIntArray(Collection<Integer> ints) {

    final int[] result = new int[ints.size()];
    int upto = 0;
    for(int v : ints) {
      result[upto++] = v;
    }

    // paranoia:
    assert upto == result.length;

    return result;
  }

  private static class NaturalComparator<T extends Comparable<? super T>> implements Comparator<T> {
    NaturalComparator() {}
    @Override
    public int compare(T o1, T o2) {
      return o1.compareTo(o2);
    }
  }

  @SuppressWarnings("rawtypes")
  private static final Comparator<?> NATURAL_COMPARATOR = new NaturalComparator();

  /** Get the natural {@link Comparator} for the provided object class. */
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<? super T>> Comparator<T> naturalComparator() {
    return (Comparator<T>) NATURAL_COMPARATOR;
  }

  /** Swap values stored in slots <code>i</code> and <code>j</code> */
  public static <T> void swap(T[] arr, int i, int j) {
    final T tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // intro-sorts
  
  /**
   * Sorts the given array slice using the {@link Comparator}. This method uses the intro sort
   * algorithm, but falls back to insertion sort for small arrays.
   * @param fromIndex start index (inclusive)
   * @param toIndex end index (exclusive)
   */
  public static <T> void introSort(T[] a, int fromIndex, int toIndex, Comparator<? super T> comp) {
    if (toIndex-fromIndex <= 1) return;
    new ArrayIntroSorter<T>(a, comp).sort(fromIndex, toIndex);
  }
  
  /**
   * Sorts the given array using the {@link Comparator}. This method uses the intro sort
   * algorithm, but falls back to insertion sort for small arrays.
   */
  public static <T> void introSort(T[] a, Comparator<? super T> comp) {
    introSort(a, 0, a.length, comp);
  }
  
  /**
   * Sorts the given array slice in natural order. This method uses the intro sort
   * algorithm, but falls back to insertion sort for small arrays.
   * @param fromIndex start index (inclusive)
   * @param toIndex end index (exclusive)
   */
  public static <T extends Comparable<? super T>> void introSort(T[] a, int fromIndex, int toIndex) {
    if (toIndex-fromIndex <= 1) return;
    introSort(a, fromIndex, toIndex, ArrayUtil.<T>naturalComparator());
  }
  
  /**
   * Sorts the given array in natural order. This method uses the intro sort
   * algorithm, but falls back to insertion sort for small arrays.
   */
  public static <T extends Comparable<? super T>> void introSort(T[] a) {
    introSort(a, 0, a.length);
  }

  // tim sorts:
  
  /**
   * Sorts the given array slice using the {@link Comparator}. This method uses the Tim sort
   * algorithm, but falls back to binary sort for small arrays.
   * @param fromIndex start index (inclusive)
   * @param toIndex end index (exclusive)
   */
  public static <T> void timSort(T[] a, int fromIndex, int toIndex, Comparator<? super T> comp) {
    if (toIndex-fromIndex <= 1) return;
    new ArrayTimSorter<T>(a, comp, a.length / 64).sort(fromIndex, toIndex);
  }
  
  /**
   * Sorts the given array using the {@link Comparator}. This method uses the Tim sort
   * algorithm, but falls back to binary sort for small arrays.
   */
  public static <T> void timSort(T[] a, Comparator<? super T> comp) {
    timSort(a, 0, a.length, comp);
  }
  
  /**
   * Sorts the given array slice in natural order. This method uses the Tim sort
   * algorithm, but falls back to binary sort for small arrays.
   * @param fromIndex start index (inclusive)
   * @param toIndex end index (exclusive)
   */
  public static <T extends Comparable<? super T>> void timSort(T[] a, int fromIndex, int toIndex) {
    if (toIndex-fromIndex <= 1) return;
    timSort(a, fromIndex, toIndex, ArrayUtil.<T>naturalComparator());
  }
  
  /**
   * Sorts the given array in natural order. This method uses the Tim sort
   * algorithm, but falls back to binary sort for small arrays.
   */
  public static <T extends Comparable<? super T>> void timSort(T[] a) {
    timSort(a, 0, a.length);
  }

}
