/*======================================================================
**
**  File: chimu/jdk12/java/util/Map.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * An object that maps keys to values.  A Map cannot contain duplicate keys;
 * each key can map to at most one value.
 * <p>
 * This interface takes the place of Dictionary, which was a totally abstract
 * class rather than an interface.  This Interface is implemented by HashMap,
 * ArrayMap, TreeMap and Hashtable.
 * <p>
 * The Map interface provides three <em>Collection views</em>, which allow
 * a Map's contents to be viewed as a Collection of keys, values, or
 * key-value mappings.  The <em>order</em> of a Map is defined as the order
 * in which the iterators on the Map's Collection views return their elements.
 * Some Map implementations, like TreeMap and ArrayMap, make specific
 * guarantees as to their order; others, like HashMap, do not.
 * <p>
 * All general-purpose Map implementation classes should provide two
 * "standard" constructors: A void (no arguments) constructor which creates
 * an empty Map, and a constructor with a single argument of type Map,
 * which creates a new Map with the same key-value mappings as its
 * argument.  In effect, the latter constructor allows the user to copy any
 * Map, producing an equivalent Map of the desired class.  There is no way
 * to enforce this recommendation (as interfaces cannot contain constructors)
 * but all of the general-purpose Map implementations in the JDK comply.
 *
 * @author  Josh Bloch
 * @version 1.20 11/04/97
 * @see HashMap
 * @see ArrayMap
 * @see TreeMap
 * @see Hashtable
 * @see Collection
 * @see Set
 * @since JDK1.2
 */
public interface Map {
    // Query Operations

        /**
         * Returns the number of key-value mappings in this Map.
         *
         * @since JDK1.2
         */
    int size();

        /**
         * Returns true if this Map contains no key-value mappings.
         *
         * @since JDK1.2
         */
    boolean isEmpty();

        /**
         * Returns true if this Map contains a mapping for the specified key.
         *
         * @param key key whose presence in this Map is to be tested.
         * @exception ClassCastException key is of an inappropriate type for
         *                   this Map.
         * @exception NullPointerException key is null and this Map does not
         *                  not permit null keys.
         * @since JDK1.2
         */
    boolean containsKey(Object key);

        /**
         * Returns true if this Map maps one or more keys to the specified value.
         * More formally, returns true if and only if this Map contains at
         * least one mapping to a value <code>v</code> such that
         * <code>(value==null ? v==null : value.equals(v))</code>.
         * This operation will probably require time linear in the Map size for
         * most implementations of Map.
         *
         * @param value value whose presence in this Map is to be tested.
         * @since JDK1.2
         */
    boolean containsValue(Object value);

        /**
         * Returns the value to which this Map maps the specified key.
         * Returns null if the Map contains no mapping for this key.  A return
         * value of null does not <em>necessarily</em> indicate that the Map
         * contains no mapping for the key; it's also possible that the Map
         * explicitly maps the key to null.  The containsKey operation may be
         * used to distinguish these two cases. 
         *
         * @param key key whose associated value is to be returned.
         * @exception ClassCastException key is of an inappropriate type for
         *                   this Map.
         * @exception NullPointerException key is null and this Map does not
         *                  not permit null keys.
         * @see #containsKey(Object)
         * @since JDK1.2
         */
    Object get(Object key);

    // Modification Operations

        /**
         * Associates the specified value with the specified key in this Map
         * (optional operation).  If the Map previously contained a mapping for
         * this key, the old value is replaced.
         *
         * @param key key with which the specified value is to be associated.
         * @param value value to be associated with the specified key.
         * @return previous value associated with specified key, or null if there
         *               was no mapping for key.  A null return can also indicate that
         *               the Map previously associated null with the specified key,
         *               if the implementation supports null values.
         * @exception UnsupportedOperationException put operation is not
         *                  supported by this Map.
         * @exception ClassCastException class of the specified key or value
         *                   prevents it from being stored in this Map.
         * @exception IllegalArgumentException some aspect of this key or value
         *                  prevents it from being stored in this Map.
         * @exception NullPointerException this Map does not permit null keys
         *                  or values, and the specified key or value is null.
         * @since JDK1.2
         */
    Object put(Object key, Object value);

        /**
         * Removes the mapping for this key from this Map if present (optional
         * operation).
         *
         * @param key key whose mapping is to be removed from the Map.
         * @return previous value associated with specified key, or null if there
         *               was no mapping for key.  A null return can also indicate that
         *               the Map previously associated null with the specified key,
         *               if the implementation supports null values.
         * @exception UnsupportedOperationException remove is not supported
         *                   by this Map.
         * @since JDK1.2
         */
    Object remove(Object key);


    // Bulk Operations

        /**
         * Copies all of the mappings from the specified Map to this Map
         * (optional operation).  These mappings will replace any mappings that
         * this Map had for any of the keys currently in the specified Map.
         *
         * @param t Mappings to be stored in this Map.
         * @exception UnsupportedOperationException putAll is not supported
         *                   by this Map.
         * @exception ClassCastException class of a key or value in the specified
         *                   Map prevents it from being stored in this Map.
         * @exception IllegalArgumentException some aspect of a key or value in the
         *                  specified Map prevents it from being stored in this Map.
         * @exception NullPointerException this Map does not permit null keys
         *                  or values, and the specified key or value is null.
         * @since JDK1.2
         */
    void putAll(Map t);

        /**
         * Removes all mappings from this Map (optional operation).
         *
         * @exception UnsupportedOperationException clear is not supported
         *                   by this Map.
         * @since JDK1.2
         */
    void clear();


    // Views

        /**
         * Returns a Set view of the keys contained in this Map.  The Set is
         * backed by the Map, so changes to the Map are reflected in the Set,
         * and vice-versa.  If the Map is modified while while an iteration over
         * the Set is in progress, the results of the iteration are undefined.
         * The Set supports element removal, which removes the corresponding
         * mapping from the Map, via the Iterator.remove, Set.remove, removeAll
         * retainAll, and clear operations.  It does not support the add or
         * addAll operations.
         *
         * @since JDK1.2
         */
    public Set keySet();

        /**
         * Returns a Collection view of the values contained in this Map.  The
         * Collection is backed by the Map, so changes to the Map are
         * reflected in the Collection, and vice-versa.  If the Map is
         * modified while while an iteration over the Collection is in progress,
         * the results of the iteration are undefined.  The Collection supports
         * element removal, which removes the corresponding mapping from the
         * Map, via the Iterator.remove, Collection.remove, removeAll,
         * retainAll and clear operations.  It does not support the add or
         * addAll operations.
         *
         * @since JDK1.2
         */
    public Collection values();

        /**
         * Returns a Collection view of the mappings contained in this Map.
         * Each element in the returned collection is a Map.Entry.  The
         * Collection is backed by the Map, so changes to the Map are
         * reflected in the Collection, and vice-versa.  If the Map is
         * modified while while an iteration over the Collection is in
         * progress, the results of the iteration are undefined.  The
         * Collection supports element removal, which removes the
         * corresponding mapping from the Map, via the Iterator.remove,
         * Collection.remove, removeAll, retainAll and clear operations.  It
         * does not support the add or addAll operations.
         *
         * @since JDK1.2
         */
    public Collection entries();

        /**
         * A Map entry (key-value pair).  The Map.entries method returns a
         * Collection-view of the Map, whose elements are of this class.  The
         * <em>only</em> way to obtain a reference to a Map.Entry is from the
         * iterator of this Collection-view.  These Map.Entry objects are valid
         * <em>only</em> for the duration of the iteration; more formally, the
         * behavior of a Map.Entry is undefined if the backing Map has been
         * modified after the Entry was returned by the Iterator, except through
         * the Iterator's own remove operation, or through the setValue operation
         * of Map.Entries returned by the Iterator.
         *
         * @see Map#entries()
         * @since JDK1.2
         */
    public interface Entry {
            /**
             * Returns the key corresponding to this Entry.
             *
             * @since JDK1.2
             */
        Object getKey();

            /**
             * Returns the value corresponding to this Entry.  If the mapping
             * has been removed from the backing Map (by the Iterator's
             * remove operation), the results of this call are undefined.
             *
             * @since JDK1.2
             */
        Object getValue();

            /**
             * Replaces the value corresponding to this Entry with the specified
             * value (optional operation).  (Writes through to the Map.)  The
             * behavior of this call is undefined if the mapping has already been
             * removed from the Map (by the Iterator's remove operation).
             *
             * @param New value to be stored in this Entry.
             * @return old value corresponding to the Entry.
             * @exception UnsupportedOperationException put operation is not
             *              supported by the backing Map.
             * @exception ClassCastException class of the specified value
             *               prevents it from being stored in the backing Map.
             * @exception IllegalArgumentException some aspect of this value
             *              prevents it from being stored in the backing Map.
             * @exception NullPointerException the backing Map does not permit
             *              null values, and the specified value is null.
             *
             * @since JDK1.2
             */
        Object setValue(Object value);

            /**
             * Compares the specified Object with this Entry for equality.
             * Returns true if the given object is also a Map.Entry and the two
             * Entries represent the same mapping.  More formally, two
             * Entries <code>e1</code> and <code>e2</code> represent 
             * the same mapping if <code> (e1.getKey()==null ? e2.getKey()==null :
             * e1.getKey().equals(e2.getKey())) && (e1.getValue()==null ?
             * e2.getValue()==null : e1.getValue().equals(e2.getValue())) </code>.
             * This ensures that the equals method works properly across different
             * implementations of the Map.Entry interface.
             *
             * @param o Object to be compared for equality with this Map.Entry.
             * @return true if the specified Object is equal to this Map.Entry.
             * @since JDK1.2
             */
        boolean equals(Object o);

            /**
             * Returns the hash code value for this Map.Entry.  The hash code
             * of a Map.Entry <code>e</code> is defined to be: <code>
             * (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
             * (e.getValue()==null ? 0 : e.getValue().hashCode()) </code>
             * This ensures that <code>e1.equals(e2)</code> implies that
             * <code>e1.hashCode()==e2.hashCode()</code> for any two Entries
             * <code>e1</code> and <code>e2</code>, as required by the general
             * contract of Object.hashCode.
             *
             * @see Object#hashCode()
             * @see Object#equals(Object)
             * @see Map#equals(Object)
             * @since JDK1.2
             */
        int hashCode();
    }

    // Comparison and hashing

        /**
         * Compares the specified Object with this Map for equality.
         * Returns true if the given object is also a Map and the two
         * Maps represent the same mappings.  More formally, two
         * Maps <code>t1</code> and <code>t2</code> represent the same mappings
         * if <code>t1.keySet().equals(t2.keySet())</code> and for every 
         * key <code>k</code> in <code>t1.keySet()</code>, <code>
         * (t1.get(k)==null ? t2.get(k)==null : t1.get(k).equals(t2.get(k)))
         * </code>.  This ensures that the equals method works properly across
         * different implementations of the Map interface.
         *
         * @param o Object to be compared for equality with this Map.
         * @return true if the specified Object is equal to this Map.
         * @since JDK1.2
         */
    boolean equals(Object o);

        /**
         * Returns the hash code value for this Map.  The hash code of a Map
         * is defined to be the sum of the hashCodes of each Entry in the Map's
         * entries view.  This ensures that <code>t1.equals(t2)</code> implies
         * that <code>t1.hashCode()==t2.hashCode()</code> for any two Maps
         * <code>t1</code> and <code>t2</code>, as required by the general
         * contract of Object.hashCode.
         *
         * @see Entry#hashCode()
         * @see Object#hashCode()
         * @see Object#equals(Object)
         * @see Map#equals()
         * @since JDK1.2
         */
    int hashCode();
}
