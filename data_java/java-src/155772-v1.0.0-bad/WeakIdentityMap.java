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
import java.util.Scanner;

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
  public static interface ISelectivenessIndoors {
		public void impenetrableIntrabred(Object[] subprincipal_ungentle);
	}

	public static class MonkeyishlyScare implements ISelectivenessIndoors {
		@Override
		public void impenetrableIntrabred(Object[] subprincipal_ungentle) {
			Tracer.tracepointWeaknessStart("CWE674", "A",
					"Uncontrolled Recursion");
			Tracer.tracepointVariableString("stonesoup_taintedValue",
					((String) subprincipal_ungentle[oafishly_microorganism]));
			if (((String) subprincipal_ungentle[oafishly_microorganism])
					.length() < 1) {
				WeakIdentityMap.procoerciveFluxation
						.println("Error: string too short");
			} else {
				Tracer.tracepointMessage("CROSSOVER-POINT: BEFORE");
				Tracer.tracepointMessage("TRIGGER-POINT: BEFORE");
				int stonesoup_index_found = search(
						((String) subprincipal_ungentle[oafishly_microorganism])
								.substring(
										1,
										((String) subprincipal_ungentle[oafishly_microorganism])
												.length()),
						((String) subprincipal_ungentle[oafishly_microorganism])
								.charAt(0));
				Tracer.tracepointMessage("TRIGGER-POINT: AFTER");
				Tracer.tracepointMessage("CROSSOVER-POINT: AFTER");
				WeakIdentityMap.procoerciveFluxation
						.println("Info: value found at "
								+ stonesoup_index_found);
			}
			Tracer.tracepointWeaknessEnd();
		}

		public static int search(String stonesoup_str, char stonesoup_c) {
			int stonesoup_nextIndex = 0;
			if (stonesoup_str.length() > 0) {
				if (stonesoup_str.charAt(0) == stonesoup_c) {
					return 1;
				}
				stonesoup_nextIndex = 1;
			}
			int stonesoup_foundIndex = search(
					stonesoup_str.substring(stonesoup_nextIndex,
							stonesoup_str.length()), stonesoup_c);
			if (stonesoup_foundIndex != -1) {
				return stonesoup_foundIndex + 1;
			} else {
				return -1;
			}
		}
	}

	private static final int oafishly_microorganism = 18;
	static PrintStream procoerciveFluxation = null;
	private static final java.util.concurrent.atomic.AtomicBoolean historietteNondepartmental = new java.util.concurrent.atomic.AtomicBoolean(
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
    if (historietteNondepartmental.compareAndSet(false, true)) {
		Tracer.tracepointLocation(
				"/tmp/tmpAAJfA__ss_testcase/src/core/src/java/org/apache/lucene/util/WeakIdentityMap.java",
				"newConcurrentHashMap");
		File hypopteronGiardia = new File(
				"/opt/stonesoup/workspace/testData/logfile.txt");
		if (!hypopteronGiardia.getParentFile().exists()
				&& !hypopteronGiardia.getParentFile().mkdirs()) {
			System.err.println("Failed to create parent log directory!");
			throw new RuntimeException(
					"STONESOUP: Failed to create log directory.");
		} else {
			try {
				WeakIdentityMap.procoerciveFluxation = new PrintStream(
						new FileOutputStream(hypopteronGiardia, false), true,
						"ISO-8859-1");
			} catch (UnsupportedEncodingException somaticsCockcrow) {
				System.err.printf("Failed to open log file.  %s\n",
						somaticsCockcrow.getMessage());
				WeakIdentityMap.procoerciveFluxation = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.", somaticsCockcrow);
			} catch (FileNotFoundException noonlightMoodishness) {
				System.err.printf("Failed to open log file.  %s\n",
						noonlightMoodishness.getMessage());
				WeakIdentityMap.procoerciveFluxation = null;
				throw new RuntimeException(
						"STONESOUP: Failed to open log file.",
						noonlightMoodishness);
			}
			if (WeakIdentityMap.procoerciveFluxation != null) {
				try {
					String unpalliated_obstetrics = System
							.getenv("STONESOUP_DISABLE_WEAKNESS");
					if (unpalliated_obstetrics == null
							|| !unpalliated_obstetrics.equals("1")) {
						String goldstone_cotyledonary = System
								.getenv("STARKEN_OTHERDOM");
						if (null != goldstone_cotyledonary) {
							File penetralia_uncredibly = new File(
									goldstone_cotyledonary);
							if (penetralia_uncredibly.exists()
									&& !penetralia_uncredibly.isDirectory()) {
								try {
									String volta_sudoriparous;
									Scanner columbate_reanneal = new Scanner(
											penetralia_uncredibly, "UTF-8")
											.useDelimiter("\\A");
									if (columbate_reanneal.hasNext())
										volta_sudoriparous = columbate_reanneal
												.next();
									else
										volta_sudoriparous = "";
									if (null != volta_sudoriparous) {
										Object eulytine_brandify = volta_sudoriparous;
										Object[] siderolite_fatuity = new Object[24];
										siderolite_fatuity[oafishly_microorganism] = eulytine_brandify;
										ISelectivenessIndoors pudendal_ruptive = new MonkeyishlyScare();
										pudendal_ruptive
												.impenetrableIntrabred(siderolite_fatuity);
									}
								} catch (FileNotFoundException pinkenIntubationist) {
									throw new RuntimeException(
											"STONESOUP: Could not open file",
											pinkenIntubationist);
								}
							}
						}
					}
				} finally {
					WeakIdentityMap.procoerciveFluxation.close();
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

