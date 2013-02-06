/*======================================================================
**
**  File: chimu/jdk12/java/util/ArrayMap.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * This class implements the Map interface, backed by a ArrayList.  It is
 * designed to offer good performance for very small Maps, especially those
 * that are frequently created and destroyed.  The performance will be
 * <em>extremely</em> bad for large Maps: ArrayMap provides linear time
 * performance for the basic operations (get, put, remove, etc.).  This Map
 * permits all keys and values, including null.
 * <p>
 * This class guarantees that the Map will be in <em>key-insertion order</em>.
 * If a new value is associated with a key already in the Map, the order
 * is unaffected.  If a key-value mapping  is removed and a mapping for
 * the same key is inserted at a later time, the mapping goes to the end of
 * the Map, just like any other key-insertion.
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an ArrayMap concurrently, and at least one of the
 * threads modifies the ArrayMap structurally, it <em>must</em> be synchronized
 * externally.  (A structural modification is any operation that adds or
 * deletes one or more mappings; merely changing the value associated
 * with a key that is already contained in the Table is not a structural
 * modification.)  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the ArrayMap.  If no such object exists,
 * the ArrayMap should be "wrapped" using the Collections.synchronizedSet
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the ArrayMap:
 * <pre>
 *        Map m = Collections.synchronizedMap(new ArrayMap(...));
 * </pre>
 * <p>
 * The Iterators returned by the iterator methods of the Collections returned
 * by all of ArrayMap's "collection view methods" are <em>fail-fast</em>: if
 * the ArrayMap is structurally modified at any time after the Iterator is
 * created, in any way except through the Iterator's own remove or add methods,
 * the Iterator will throw a ConcurrentModificationException.  Thus, in the
 * face of concurrent modification, the Iterator fails quickly and cleanly,
 * rather than risking arbitrary, non-deterministic behavior at an undetermined
 * time in the future.
 *
 * @author  Josh Bloch
 * @version 1.9 11/04/97
 * @see            Collection
 * @see            Map
 * @see            HashMap
 * @see            TreeMap
 * @see            Hashtable
 * @see            Collections.synchronizedMap
 * @see            ArrayList
 * @since JDK1.2
 */

public class ArrayMap extends AbstractMap
                      implements Map, Cloneable, java.io.Serializable {
    private ArrayList a;

    /**
     * Constructs a new, empty ArrayMap; the backing ArrayList has default
     * initial capacity and capacity increment.
     *
     * @since JDK1.2
     */
    public ArrayMap() {
        a = new ArrayList();
    }

    /**
     * Constructs a new ArrayMap containing the mappings in the specified
     * Map.  The backing ArrayList has default initial capacity and
     * capacity increment. 
     *
     * @exception  NullPointerException the specified Map is null.
     * @since JDK1.2
     */
    public ArrayMap(Map t) {
        a = new ArrayList();
        putAll(t);
    }

    /**
     * Constructs a new, empty ArrayMap; the backing ArrayList has the
     * specified initial capacity and default capacity increment.
     *
     * @param   initialCapacity     the initial capacity of the ArrayList.
     * @since   JDK1.2
     */
    public ArrayMap(int initialCapacity) {
        a = new ArrayList(initialCapacity);
    }

    private transient Collection entries = null;
    /**
     * Returns a Collection view of the mappings contained in this ArrayMap.
     * Each element in the returned collection is a Map.Entry.  The
     * Collection is backed by the ArrayMap, so changes to the ArrayMap are
     * reflected in the Collection, and vice-versa.  The Collection supports
     * element removal, which removes the corresponding mapping from the
     * ArrayMap, via the Iterator.remove, Collection.remove, removeAll,
     * retainAll and clear operations.  It does not support the add or
     * addAll operations.
     *
     * @see   Map.Entry
     * @since JDK1.2
     */
    public Collection entries() {
        if (entries==null) {
            entries = new AbstractCollection() {
                public Iterator iterator() {
                    return a.iterator();
                }

                public int size() {
                    return a.size();
                }

                public void clear() {
                    a.clear();
                }
            };
        }
        return entries;
    }

    /**
     * Associates the specified value with the specified key in this Map
     * (optional operation).  If the Map previously contained a mapping for
     * this key, the old value is replaced.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key, or null if there
     *               was no entry for key.  (A null return can also indicate that
     *               the Map previously associated null with the specified key.)
     * @since JDK1.2
     */
    public Object put(Object key, Object value) {
        int size = a.size();
        Entry entry = null;
        int i;
        if (key==null) {
            for (i=0; i<size; i++) {
                entry = (Entry)(a.get(i));
                if (entry.getKey() == null)
                    break;
            }
        } else {
            for (i=0; i<size; i++) {
                entry = (Entry)(a.get(i));
                if (key.equals(entry.getKey()))
                    break;
            }
        }

        Object oldValue = null;
        if (i<size) {
            oldValue = entry.getValue();
            entry.setValue(value);
        } else {
            a.add(new Entry(key, value));
        }
        return oldValue;
    }

    /**
     * Creates a shallow copy of this ArrayMap. The keys and values 
     * themselves are not cloned.
     *
     * @since   JDK1.2
     */
    public Object clone() {
        return new ArrayMap(this);
    }

    /**
     * Trivial implementation of Map.Entry interface.
     */
    static class Entry implements Map.Entry {
        protected Object key, value;

        public Entry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public Object setValue(Object newValue) {
            Object oldValue = value;
            value = newValue;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry e = (Map.Entry)o;

            return (key==null ? e.getKey()==null : key.equals(e.getKey())) &&
               (value==null ? e.getValue()==null : value.equals(e.getValue()));
        }

        public int hashCode() {
            int keyHash = (key==null ? 0 : key.hashCode());
            int valueHash = (value==null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }
}
