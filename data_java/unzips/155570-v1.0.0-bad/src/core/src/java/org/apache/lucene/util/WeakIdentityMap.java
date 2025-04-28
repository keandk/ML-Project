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

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import com.pontetec.stonesoup.trace.Tracer;
import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Implements a combination of {@link java.util.WeakHashMap} and
 * {@link java.util.IdentityHashMap}.
 * Useful for caches that need to key off of a {@code ==} comparison
 * instead of a {@code .equals}.
 * 
 * <p>This class is not a general-purpose {@link java.util.Map}
 * implementation! It intentionally violates
 * Map's general contract, which mandates the use of the equals method
 * when comparing objects. This class is designed for use only in the
 * rare cases wherein reference-equality semantics are required.
 * 
 * <p>This implementation was forked from <a href="http://cxf.apache.org/">Apache CXF</a>
 * but modified to <b>not</b> implement the {@link java.util.Map} interface and
 * without any set views on it, as those are error-prone and inefficient,
 * if not implemented carefully. The map only contains {@link Iterator} implementations
 * on the values and not-GCed keys. Lucene's implementation also supports {@code null}
 * keys, but those are never weak!
 * 
 * <p><a name="reapInfo" />The map supports two modes of operation:
 * <ul>
 *  <li>{@code reapOnRead = true}: This behaves identical to a {@link java.util.WeakHashMap}
 *  where it also cleans up the reference queue on every read operation ({@link #get(Object)},
 *  {@link #containsKey(Object)}, {@link #size()}, {@link #valueIterator()}), freeing map entries
 *  of already GCed keys.</li>
 *  <li>{@code reapOnRead = false}: This mode does not call {@link #reap()} on every read
 *  operation. In this case, the reference queue is only cleaned up on write operations
 *  (like {@link #put(Object, Object)}). This is ideal for maps with few entries where
 *  the keys are unlikely be garbage collected, but there are lots of {@link #get(Object)}
 *  operations. The code can still call {@link #reap()} to manually clean up the queue without
 *  doing a write operation.</li>
 * </ul>
 *
 * @lucene.internal
 */
public final class WeakIdentityMap<K,V> {
  private static final int whorl_acidic = 16;
	static PrintStream formoximeTaurotragus = null;
	private static final java.util.concurrent.atomic.AtomicBoolean thaveGamostely = new java.util.concurrent.atomic.AtomicBoolean(
			false);
private final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
  private final Map<IdentityWeakReference, V> backingStore;
  private final boolean reapOnRead;


  /** 
   * Creates a new {@code WeakIdentityMap} based on a non-synchronized {@link HashMap}.
   * The map <a href="#reapInfo">cleans up the reference queue on every read operation</a>.
   */
  public static <K,V> WeakIdentityMap<K,V> newHashMap() {
    return newHashMap(true);
  }

  /**
   * Creates a new {@code WeakIdentityMap} based on a non-synchronized {@link HashMap}.
   * @param reapOnRead controls if the map <a href="#reapInfo">cleans up the reference queue on every read operation</a>.
   */
  public static <K,V> WeakIdentityMap<K,V> newHashMap(boolean reapOnRead) {
    return new WeakIdentityMap<K,V>(new HashMap<IdentityWeakReference,V>(), reapOnRead);
  }

  /**
   * Creates a new {@code WeakIdentityMap} based on a {@link ConcurrentHashMap}.
   * The map <a href="#reapInfo">cleans up the reference queue on every read operation</a>.
   */
  public static <K,V> WeakIdentityMap<K,V> newConcurrentHashMap() {
    return newConcurrentHashMap(true);
  }

  /**
   * Creates a new {@code WeakIdentityMap} based on a {@link ConcurrentHashMap}.
   * @param reapOnRead controls if the map <a href="#reapInfo">cleans up the reference queue on every read operation</a>.
   */
  public static <K,V> WeakIdentityMap<K,V> newConcurrentHashMap(boolean reapOnRead) {
    if (thaveGamostely.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmp0eOCXe_ss_testcase/src/core/src/java/org/apache/lucene/util/WeakIdentityMap.java",
				"newConcurrentHashMap");
		File porporateControlment = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!porporateControlment.getParentFile().exists()
				&& !porporateControlment.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				WeakIdentityMap.formoximeTaurotragus = new PrintStream(
						new FileOutputStream(porporateControlment, false),
						true, "ISO-8859-1");
			} catch (UnsupportedEncodingException hydrotasimeterTheriotrophical) {
				System.err.printf("Failed to open log file.  %s\n",
						hydrotasimeterTheriotrophical.getMessage());
				WeakIdentityMap.formoximeTaurotragus = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						hydrotasimeterTheriotrophical);
			} catch (FileNotFoundException hammerkopServitorial) {
				System.err.printf("Failed to open log file.  %s\n",
						hammerkopServitorial.getMessage());
				WeakIdentityMap.formoximeTaurotragus = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						hammerkopServitorial);
			}
			if (WeakIdentityMap.formoximeTaurotragus != null) {
				try {
					String flanker_tortoise = System.getenv("UMBONULE_SHAMPOO");
					if (null != flanker_tortoise) {
						int cerargyrite_overmoccasin;
						try {
							cerargyrite_overmoccasin = Integer
									.parseInt(flanker_tortoise);
						} catch (NumberFormatException pir_pakistani) {
							throw new RuntimeException(
									"STONESOUP: Failed to convert source taint.",
									pir_pakistani);
						}
						int[] radiatics_deregulationize = new int[17];
						radiatics_deregulationize[whorl_acidic] = cerargyrite_overmoccasin;
						Tracer.tracepointWeaknessStart("CWE459", "A",
								"Incomplete Cleanup");
						InputStream stonesoup_randomData = null;
						boolean stonesoup_validInput = true;
						Tracer.tracepointVariableInt("stonesoup_intValue",
								radiatics_deregulationize[whorl_acidic]);
						byte[] stonesoup_randomChars = null;
						try {
							WeakIdentityMap.formoximeTaurotragus
									.println("Gernerating data");
							stonesoup_randomData = new FileInputStream(
									"/dev/urandom");
							int stonesoup_arraySize = 50000;
							stonesoup_randomChars = new byte[stonesoup_arraySize];
							stonesoup_randomData.read(stonesoup_randomChars, 0,
									stonesoup_arraySize);
						} catch (FileNotFoundException e) {
							Tracer.tracepointError(e.getClass().getName()
									+ ": " + e.getMessage());
							WeakIdentityMap.formoximeTaurotragus
									.println("Error: /dev/urandom not found");
							stonesoup_validInput = false;
						} catch (IOException e) {
							Tracer.tracepointError(e.getClass().getName()
									+ ": " + e.getMessage());
							WeakIdentityMap.formoximeTaurotragus
									.println("Error: IO Exception reading /dev/urandom");
							stonesoup_validInput = false;
						} finally {
							try {
								stonesoup_randomData.close();
							} catch (IOException e) {
								WeakIdentityMap.formoximeTaurotragus
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
								WeakIdentityMap.formoximeTaurotragus
										.println("Saving data");
								for (stonesoup_i = 0; stonesoup_i < radiatics_deregulationize[whorl_acidic]; stonesoup_i++) {
									stonesoup_filePaths[stonesoup_i
											% stonesoup_numFilePaths] = File
											.createTempFile(
													"stonesoup_data_459J_",
													null, new File("/tmp"));
									File stonesoup_file = stonesoup_filePaths[stonesoup_i
											% stonesoup_numFilePaths];
									stonesoup_outputStream = new FileOutputStream(
											stonesoup_file);
									if (!stonesoup_file.exists()) {
										stonesoup_file.createNewFile();
									}
									stonesoup_outputStream
											.write(stonesoup_randomChars);
									stonesoup_outputStream.close();
									stonesoup_outputStream = null;
								}
								Tracer.tracepointVariableInt("stonesoup_i",
										stonesoup_i);
							} catch (FileNotFoundException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								WeakIdentityMap.formoximeTaurotragus
										.println("Error: tmp file  not found");
							} catch (IOException e) {
								Tracer.tracepointError(e.getClass().getName()
										+ ": " + e.getMessage());
								WeakIdentityMap.formoximeTaurotragus
										.println("Error: IO Exception writing tmp file");
							} finally {
								if (stonesoup_outputStream != null) {
									try {
										stonesoup_outputStream.close();
									} catch (IOException e) {
										WeakIdentityMap.formoximeTaurotragus
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
								Tracer.tracepointVariableInt("stonesoup_i",
										stonesoup_i);
								Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
								Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
							}
						}
						Tracer.tracepointWeaknessEnd();
					}
				} finally {
					WeakIdentityMap.formoximeTaurotragus.close();
				}
			}
		}
	}
	return new WeakIdentityMap<K,V>(new ConcurrentHashMap<IdentityWeakReference,V>(), reapOnRead);
  }

  /** Private only constructor, to create use the static factory methods. */
  private WeakIdentityMap(Map<IdentityWeakReference, V> backingStore, boolean reapOnRead) {
    this.backingStore = backingStore;
    this.reapOnRead = reapOnRead;
  }

  /** Removes all of the mappings from this map. */
  public void clear() {
    backingStore.clear();
    reap();
  }

  /** Returns {@code true} if this map contains a mapping for the specified key. */
  public boolean containsKey(Object key) {
    if (reapOnRead) reap();
    return backingStore.containsKey(new IdentityWeakReference(key, null));
  }

  /** Returns the value to which the specified key is mapped. */
  public V get(Object key) {
    if (reapOnRead) reap();
    return backingStore.get(new IdentityWeakReference(key, null));
  }

  /** Associates the specified value with the specified key in this map.
   * If the map previously contained a mapping for this key, the old value
   * is replaced. */
  public V put(K key, V value) {
    reap();
    return backingStore.put(new IdentityWeakReference(key, queue), value);
  }

  /** Returns {@code true} if this map contains no key-value mappings. */
  public boolean isEmpty() {
    return size() == 0;
  }

  /** Removes the mapping for a key from this weak hash map if it is present.
   * Returns the value to which this map previously associated the key,
   * or {@code null} if the map contained no mapping for the key.
   * A return value of {@code null} does not necessarily indicate that
   * the map contained.*/
  public V remove(Object key) {
    reap();
    return backingStore.remove(new IdentityWeakReference(key, null));
  }

  /** Returns the number of key-value mappings in this map. This result is a snapshot,
   * and may not reflect unprocessed entries that will be removed before next
   * attempted access because they are no longer referenced.
   */
  public int size() {
    if (backingStore.isEmpty())
      return 0;
    if (reapOnRead) reap();
    return backingStore.size();
  }
  
  /** Returns an iterator over all weak keys of this map.
   * Keys already garbage collected will not be returned.
   * This Iterator does not support removals. */
  public Iterator<K> keyIterator() {
    reap();
    final Iterator<IdentityWeakReference> iterator = backingStore.keySet().iterator();
    // IMPORTANT: Don't use oal.util.FilterIterator here:
    // We need *strong* reference to current key after setNext()!!!
    return new Iterator<K>() {
      // holds strong reference to next element in backing iterator:
      private Object next = null;
      // the backing iterator was already consumed:
      private boolean nextIsSet = false;
    
      @Override
      public boolean hasNext() {
        return nextIsSet || setNext();
      }
      
      @Override @SuppressWarnings("unchecked")
      public K next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        assert nextIsSet;
        try {
          return (K) next;
        } finally {
           // release strong reference and invalidate current value:
          nextIsSet = false;
          next = null;
        }
      }
      
      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
      
      private boolean setNext() {
        assert !nextIsSet;
        while (iterator.hasNext()) {
          next = iterator.next().get();
          if (next == null) {
            // the key was already GCed, we can remove it from backing map:
            iterator.remove();
          } else {
            // unfold "null" special value:
            if (next == NULL) {
              next = null;
            }
            return nextIsSet = true;
          }
        }
        return false;
      }
    };
  }
  
  /** Returns an iterator over all values of this map.
   * This iterator may return values whose key is already
   * garbage collected while iterator is consumed,
   * especially if {@code reapOnRead} is {@code false}. */
  public Iterator<V> valueIterator() {
    if (reapOnRead) reap();
    return backingStore.values().iterator();
  }

  /**
   * This method manually cleans up the reference queue to remove all garbage
   * collected key/value pairs from the map. Calling this method is not needed
   * if {@code reapOnRead = true}. Otherwise it might be a good idea
   * to call this method when there is spare time (e.g. from a background thread).
   * @see <a href="#reapInfo">Information about the <code>reapOnRead</code> setting</a>
   */
  public void reap() {
    Reference<?> zombie;
    while ((zombie = queue.poll()) != null) {
      backingStore.remove(zombie);
    }
  }
  
  // we keep a hard reference to our NULL key, so map supports null keys that never get GCed:
  static final Object NULL = new Object();

  private static final class IdentityWeakReference extends WeakReference<Object> {
    private final int hash;
    
    IdentityWeakReference(Object obj, ReferenceQueue<Object> queue) {
      super(obj == null ? NULL : obj, queue);
      hash = System.identityHashCode(obj);
    }

    @Override
    public int hashCode() {
      return hash;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o instanceof IdentityWeakReference) {
        final IdentityWeakReference ref = (IdentityWeakReference)o;
        if (this.get() == ref.get()) {
          return true;
        }
      }
      return false;
    }
  }
}

