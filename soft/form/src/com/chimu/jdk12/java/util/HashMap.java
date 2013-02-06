/*======================================================================
**
**  File: chimu/jdk12/java/util/HashMap.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;
import java.io.*;

/**
 * Hash-table based implementation of the Map interface.  This implementation
 * provides all of the optional Map operations, and permits null values
 * but not the null key.  (HashMap is roughly equivalent to Hashtable, except
 * that it is unsynchronized and permits null values.)  This class makes
 * no guarantees as to the order of the Map; in particular, it does not
 * guarantee that the order will remain constant over time.
 * <p>
 * This implementation provides constant-time performance for the basic
 * operations (get and put), assuming the the hash function disperses
 * the elements properly among the buckets.  Iteration requires time
 * proportional to the number of buckets in the hash table plus
 * the size of the Map (the number of key-value mappings).
 * <p>
 * An instance of HashMap has two parameters that affect its efficiency: its
 * <i>capacity</i> and its <i>load factor</i>. The load factor should be
 * between 0.0 and 1.0. When the number of entries in the HashMap exceeds
 * the product of the load factor and the current capacity, the capacity is
 * increased by calling the rehash method. Larger load factors
 * use memory more efficiently, at the expense of larger expected time per
 * lookup.
 * <p>
 * If many entries are to be made into a HashMap, creating it with a
 * sufficiently large capacity will allow the entries to be inserted more
 * efficiently than letting it perform automatic rehashing as needed to grow
 * the table.
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access a HashMap concurrently, and at least one of the
 * threads modifies the HashMap structurally, it <em>must</em> be synchronized
 * externally.  (A structural modification is any operation that adds or
 * deletes one or more mappings; merely changing the value associated
 * with a key that is already contained in the Table is not a structural
 * modification.)  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the HashMap.  If no such object exists,
 * the HashMap should be "wrapped" using the Collections.synchronizedSet
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the HashMap:
 * <pre>
 *        Map m = Collections.synchronizedMap(new HashMap(...));
 * </pre>
 * <p>
 * The Iterators returned by the iterator methods of the Collections returned
 * by all of HashMap's "collection view methods" are <em>fail-fast</em>: if the
 * HashMap is structurally modified at any time after the Iterator is created,
 * in any way except through the Iterator's own remove or add methods, the
 * Iterator will throw a ConcurrentModificationException.  Thus, in the face of
 * concurrent modification, the Iterator fails quickly and cleanly, rather than
 * risking arbitrary, non-deterministic behavior at an undetermined time in the
 * future.
 *
 * @author  Josh Bloch
 * @author  Arthur van Hoff
 * @version 1.8, 11/04/97
 * @see     Object#hashCode()
 * @see     Collection
 * @see            Map
 * @see            TreeMap
 * @see            ArrayMap
 * @see            Hashtable
 * @since JDK1.2
 */
public class HashMap extends AbstractMap implements Map, Cloneable,
                                         java.io.Serializable {
    /**
     * The hash table data.
     */
    private transient Entry table[];

    /**
     * The total number of entries in the hash table.
     */
    private transient int count;

    /**
     * Rehashes the table when count exceeds this threshold.
     */
    private int threshold;

    /**
     * The load factor for the HashMap.
     */
    private float loadFactor;

    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of entries in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     */
    private transient int modCount = 0;

    /**
     * Constructs a new, empty HashMap with the specified initial 
     * capacity and the specified load factor. 
     *
     * @param      initialCapacity   the initial capacity of the HashMap.
     * @param      loadFactor        a number between 0.0 and 1.0.
     * @exception  IllegalArgumentException  if the initial capacity is less
     *               than or equal to zero, or if the load factor is less than
     *               or equal to zero.
     * @since      JDK1.2
     */
    public HashMap(int initialCapacity, float loadFactor) {
        if ((initialCapacity <= 0) || (loadFactor <= 0.0)) {
            throw new IllegalArgumentException();
        }
        this.loadFactor = loadFactor;
        table = new Entry[initialCapacity];
        threshold = (int)(initialCapacity * loadFactor);
    }

    /**
     * Constructs a new, empty HashMap with the specified initial capacity
     * and default load factor.
     *
     * @param   initialCapacity   the initial capacity of the HashMap.
     * @since   JDK1.2
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    /**
     * Constructs a new, empty HashMap with a default capacity and load
     * factor. 
     *
     * @since JDK1.2
     */
    public HashMap() {
        this(101, 0.75f);
    }

    /**
     * Constructs a new HashMap with the same mappings as the given 
     * Map.  The HashMap is created with a capacity of twice the number
     * of entries in the given Map, and a default load factor.
     *
     * @since JDK1.2
     */
    public HashMap(Map t) {
        this(2*t.size(), 0.75f);
        putAll(t);
    }

    /**
     * Returns the number of key-value mappings in this Map.
     *
     * @since JDK1.2
     */
    public int size() {
        return count;
    }

    /**
     * Returns true if this Map contains no key-value mappings.
     *
     * @since JDK1.2
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns true if this HashMap maps one or more keys to the specified
     * value.
     *
     * @param value value whose presence in this Map is to be tested.
     * @since JDK1.2
     */
    public boolean containsValue(Object value) {
        Entry tab[] = table;

        if (value==null) {
            for (int i = tab.length ; i-- > 0 ;)
                for (Entry e = tab[i] ; e != null ; e = e.next)
                    if (e.value==null)
                        return true;
        } else {
            for (int i = tab.length ; i-- > 0 ;)
                for (Entry e = tab[i] ; e != null ; e = e.next)
                    if (value.equals(e.value))
                        return true;
        }

        return false;
    }

    /**
     * Returns true if this HashMap contains a mapping for the specified key.
     * 
     * @param key key whose presence in this Map is to be tested.
     * @exception NullPointerException the specified key is null.
     * @since   JDK1.2
     */
    public boolean containsKey(Object key) {
        Entry tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which this HashMap maps the specified key.
     * Returns null if the HashMap contains no mapping for this key.  A return
     * value of null does not <em>necessarily</em> indicate that the HashMap
     * contains no mapping for the key; it's also possible that the HashMap
     * explicitly maps the key to null.  The containsKey operation may be
     * used to distinguish these two cases.
     *
     * @param key key whose associated value is to be returned.
     * @exception NullPointerException the specified key is null.
     * @since   JDK1.2
     */
    public Object get(Object key) {
        Entry tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    /**
     * Rehashes the contents of the HashMap into a HashMap with a 
     * larger capacity. This method is called automatically when the 
     * number of keys in the HashMap exceeds this HashMap's capacity 
     * and load factor. 
     */
    private void rehash() {
        int oldCapacity = table.length;
        Entry oldMap[] = table;

        int newCapacity = oldCapacity * 2 + 1;
        Entry newMap[] = new Entry[newCapacity];

        modCount++;
        threshold = (int)(newCapacity * loadFactor);
        table = newMap;

        for (int i = oldCapacity ; i-- > 0 ;) {
            for (Entry old = oldMap[i] ; old != null ; ) {
                Entry e = old;
                old = old.next;

                int index = (e.hash & 0x7FFFFFFF) % newCapacity;
                e.next = newMap[index];
                newMap[index] = e;
            }
        }
    }

    /**
     * Associates the specified value with the specified key in this HashMap.
     * If the HashMap previously contained a mapping forthis key, the old value
     * is replaced.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key, or null if there
     *               was no mapping for key.  A null return can also indicate that
     *               the Map previously associated null with the specified key,
     *               if the implementation supports null values.
     * @exception NullPointerException the specified key is null.
     * @since   JDK1.2
     */
    public Object put(Object key, Object value) {
        // Make sure the value is not null
        if (value == null) {
            throw new NullPointerException();
        }

        // Makes sure the key is not already in the HashMap.
        Entry tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                Object old = e.value;
                e.value = value;
                return old;
            }
        }

        modCount++;
        if (count >= threshold) {
            // Rehash the table if the threshold is exceeded
            rehash();
            return put(key, value);
        } 

        // Creates the new entry.
        Entry e = new Entry(hash, key, value, tab[index]);
        tab[index] = e;
        count++;
        return null;
    }

    /**
     * Removes the mapping for this key from this HashMap if present.
     *
     * @param key key whose mapping is to be removed from the Map.
     * @return previous value associated with specified key, or null if there
     *               was no mapping for key.  A null return can also indicate that
     *               the HashMap previously associated null with the specified key.
     * @since   JDK1.2
     */
    public Object remove(Object key) {
        Entry tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (Entry e = tab[index], prev = null ; e != null ; prev = e, e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                modCount++;
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tab[index] = e.next;
                }
                count--;
                Object oldValue = e.value;
                e.value = null;
                return oldValue;
            }
        }
        return null;
    }

    /**
     * Copies all of the mappings from the specified Map to this HashMap
     * These mappings will replace any mappings that this HashMap had for any
     * of the keys currently in the specified Map. 
     *
     * @param t Mappings to be stored in this Map.
     * @exception NullPointerException a key in the mapping specified by t is
     *                  null.
     * @since JDK1.2
     */
    public void putAll(Map t) {
        Iterator i = t.entries().iterator();
        while (i.hasNext()) {
            Map.Entry e = (Map.Entry) i.next();
            put(e.getKey(), e.getValue());
        }
    }

    /**
     * Removes all mappings from this HashMap.
     *
     * @since   JDK1.2
     */
    public void clear() {
        Entry tab[] = table;
        modCount++;
        for (int index = tab.length; --index >= 0; )
            tab[index] = null;
        count = 0;
    }

    /**
     * Returns a shallow copy of this HashMap. The keys and values 
     * themselves are not cloned. 
     *
     * @since   JDK1.2
     */
    public Object clone() {
        try { 
            HashMap t = (HashMap)super.clone();
            t.table = new Entry[table.length];
            for (int i = table.length ; i-- > 0 ; ) {
                t.table[i] = (table[i] != null) 
                    ? (Entry)table[i].clone() : null;
            }
            t.keySet = null;
            t.entries = t.values = null;
            t.modCount = 0;
            return t;
        } catch (CloneNotSupportedException e) { 
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    // Views

    private transient Set keySet = null;
    private transient Collection entries = null;
    private transient Collection values = null;

    /**
     * Returns a Set view of the keys contained in this HashMap.  The Set is
     * backed by the HashMap, so changes to the HashMap are reflected in the
     * Set, and vice-versa.  The Set supports element removal, which removes
     * the corresponding mapping from the HashMap, via the Iterator.remove,
     * Set.remove, removeAll retainAll, and clear operations.  It does not
     * support the add or addAll operations.
     *
     * @since JDK1.2
     */
    public Set keySet() {
        if (keySet == null) {
            keySet = new AbstractSet() {
                public Iterator iterator() {
                    return new HashIterator(KEYS);
                }

                public int size() {
                    return count;
                }

                public boolean remove(Object o) {
                    return HashMap.this.remove(o) != null;
                }

                public void clear() {
                    HashMap.this.clear();
                }
            };
        }
        return keySet;
    }

    /**
     * Returns a Collection view of the values contained in this HashMap.
     * The Collection is backed by the HashMap, so changes to the HashMap are
     * reflected in the Collection, and vice-versa.  The Collection supports
     * element removal, which removes the corresponding mapping from the
     * HashMap, via the Iterator.remove, Collection.remove, removeAll,
     * retainAll and clear operations.  It does not support the add or addAll
     * operations.
     *
     * @since JDK1.2
     */
    public Collection values() {
        if (values==null)
            values =new CollectionView(VALUES);
        return values;
    }

    /**
     * Returns a Collection view of the mappings contained in this HashMap.
     * Each element in the returned collection is a Map.Entry.  The Collection
     * is backed by the HashMap, so changes to the HashMap are reflected in the
     * Collection, and vice-versa.  The Collection supports element removal,
     * which removes the corresponding mapping from the HashMap, via the
     * Iterator.remove, Collection.remove, removeAll, retainAll and clear
     * operations.  It does not support the add or addAll operations.
     *
     * @see   Map.Entry
     * @since JDK1.2
     */
    public Collection entries() {
        if (entries==null)
            entries = new CollectionView(ENTRIES);
        return entries;
    }

    private class CollectionView extends AbstractCollection {
        private int type;

        CollectionView(int type) {
            this.type = type;
        }

        public Iterator iterator() {
            return new HashIterator(type);
        }

        public int size() {
            return count;
        }

        public void clear() {
            HashMap.this.clear();
        }
    }

    /**
     * WriteObject is called to save the state of the HashMap to a stream.
     * Only the keys and values are serialized since the hash values may be
     * different when the contents are restored.
     * iterate over the contents and write out the keys and values.
     */
    private void writeObject(java.io.ObjectOutputStream s)
        throws IOException
    {
        // Write out the length, threshold, loadfactor
        s.defaultWriteObject();

        // Write out length, count of elements and then the key/value objects
        s.writeInt(table.length);
        s.writeInt(count);
        for (int index = table.length-1; index >= 0; index--) {
            Entry entry = table[index];

            while (entry != null) {
                s.writeObject(entry.key);
                s.writeObject(entry.value);
                entry = entry.next;
            }
        }
    }

    /**
     * readObject is called to restore the state of the HashMap from
     * a stream.  Only the keys and values are serialized since the
     * hash values may be different when the contents are restored.
     * Read count elements and insert into the HashMap. 
     */
    private void readObject(java.io.ObjectInputStream s)
         throws IOException, ClassNotFoundException
    {
        // Read in the length, threshold, and loadfactor
        s.defaultReadObject();

        // Read the original length of the array and number of elements
        int origlength = s.readInt();
        int elements = s.readInt();

        // Compute new size with a bit of room 5% to grow but
        // No larger than the original size.  Make the length
        // odd if it's large enough, this helps distribute the entries.
        // Guard against the length ending up zero, that's not valid.
        int length = (int)(elements * loadFactor) + (elements / 20) + 3;
        if (length > elements && (length & 1) == 0)
            length--;
        if (origlength > 0 && length > origlength)
            length = origlength;

        table = new Entry[length];
        count = 0;

        // Read the number of elements and then all the key/value objects
        for (; elements > 0; elements--) {
            Object key = s.readObject();
            Object value = s.readObject();
            put(key, value);
        }
    }


    /**
     * HashMap collision list.
     */
    private static class Entry implements Map.Entry {
        int hash;
        Object key;
        Object value;
        Entry next;

        Entry(int hash, Object key, Object value, Entry next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        protected Object clone() {
            return new Entry(hash, key, value,
                             (next==null ? null : (Entry)next.clone()));
        }

        // Map.Entry Ops 

        public Object getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public Object setValue(Object value) {
            if (value == null)
                throw new NullPointerException();

            Object oldValue = this.value;
            this.value = value;
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
            return hash ^ (value==null ? 0 : value.hashCode());
        }

        public String toString() {
            return key.toString()+"="+value.toString();
        }
    }

    // Types of Enumerations/Iterations
    private static final int KEYS = 0;
    private static final int VALUES = 1;
    private static final int ENTRIES = 2;

    /**
     * A HashMap enumerator class.  This class implements both the
     * Enumeration and Iterator interfaces, but individual instances
     * can be created with the Iterator methods disabled.  This is necessary
     * to avoid unintentionally increasing the capabilities granted a user
     * by passing an Enumeration.
     */
    private class HashIterator implements Iterator {
        Entry[] table = HashMap.this.table;
        int index = table.length;
        Entry entry = null;
        Entry lastReturned = null;
        int type;

        /**
         * The modCount value that the iterator believes that the backing
         * List should have.  If this expectation is violated, the iterator
         * has detected concurrent modification.
         */
        private int expectedModCount = modCount;

        HashIterator(int type) {
            this.type = type;
        }

        public boolean hasNext() {
            if (entry != null) {
                return true;
            }
            while (index-- > 0) {
                if ((entry = table[index]) != null) {
                    return true;
                }
            }
            return false;
        }

        public Object next() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
            if (entry == null) {
                while ((index-- > 0) && ((entry = table[index]) == null));
            }
            if (entry != null) {
                Entry e = lastReturned = entry;
                entry = e.next;
                return type == KEYS ? e.key : (type == VALUES ? e.value : e);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (lastReturned == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();

            Entry[] tab = HashMap.this.table;
            int index = (lastReturned.hash & 0x7FFFFFFF) % tab.length;

            for (Entry e = tab[index], prev = null; e != null;
                 prev = e, e = e.next) {
                if (e == lastReturned) {
                    modCount++;
                    expectedModCount++;
                    if (prev == null)
                        tab[index] = e.next;
                    else
                        prev.next = e.next;
                    count--;
                    lastReturned = null;
                    return;
                }
            }
            throw new ConcurrentModificationException();
        }
    }
}
