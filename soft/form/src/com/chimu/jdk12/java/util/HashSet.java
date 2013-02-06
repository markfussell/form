/*======================================================================
**
**  File: chimu/jdk12/java/util/HashSet.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * This class implements the Set interface, backed by a hash table (actually a
 * HashMap).  It offers constant time performance for the basic operations
 * (add, remove, contains and size), assuming the the hash function disperses
 * the elements properly among the buckets.  This Set does not permit the null
 * element.
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access a HashSet concurrently, and at least one of the
 * threads modifies the HashSet, it <em>must</em> be synchronized externally.
 * This is typically accomplished by synchronizing on some object that
 * naturally encapsulates the HashSet.  If no such object exists, the HashSet
 * should be "wrapped" using the Collections.synchronizedSet method.  This is
 * best done at creation time, to prevent accidental unsynchronized access to
 * the HashSet:
 * <pre>
 *        Set s = Collections.synchronizedSet(new HashSet(...));
 * </pre>
 * <p>
 * The Iterators returned by HashSet's iterator method are <em>fail-fast</em>:
 * if the HashSet is modified at any time after the Iterator is created, in
 * any way except through the Iterator's own remove method, the Iterator will
 * throw a ConcurrentModificationException.  Thus, in the face of concurrent
 * modification, the Iterator fails quickly and cleanly, rather than
 * risking arbitrary, non-deterministic behavior at an undetermined time
 * in the future.
 * 
 * @author  Josh Bloch
 * @version 1.6 10/13/97
 * @see            Collection
 * @see            Set
 * @see            ArraySet
 * @see            Collections.synchronizedSet
 * @see            HashMap
 * @since JDK1.2
 */

public class HashSet extends AbstractSet
                     implements Set, Cloneable, java.io.Serializable {
    private HashMap map;

    /**
     * Constructs a new, empty HashSet; the backing HashMap has default
     * capacity and load factor.
     *
     * @since JDK1.2
     */
    public HashSet() {
        map = new HashMap();
    }

    /**
     * Constructs a new HashSet containing the elements in the specified
     * Collection.  The capacity of the backing HashMap is twice the
     * size of the specified Collection, and the default load factor is used.
     *
     * @exception  NullPointerException if the specified collection or one of
     *                   its elements is null.
     * @since JDK1.2
     */
    public HashSet(Collection c) {
        map = new HashMap(2*c.size());
        addAll(c);
    }

    /**
     * Constructs a new, empty HashSet; the backing HashMap has the
     * specified initial capacity and the specified load factor.
     *
     * @param      initialCapacity   the initial capacity of the hashtable.
     * @param      loadFactor        a number between 0.0 and 1.0.
     * @exception  IllegalArgumentException if the initial capacity is less
     *             than or equal to zero, or if the load factor is less than
     *             or equal to zero.
     * @since      JDK1.2
     */
    public HashSet(int initialCapacity, float loadFactor) {
        map = new HashMap(initialCapacity, loadFactor);
    }

    /**
     * Constructs a new, empty HashSet; the backing HashMap has the
     * specified initial capacity and default load factor.
     *
     * @param      initialCapacity   the initial capacity of the hashtable.
     * @exception  IllegalArgumentException if the initial capacity is less
     *             than or equal to zero, or if the load factor is less than
     *             or equal to zero.
     * @since      JDK1.2
     */
    public HashSet(int initialCapacity) {
        map = new HashMap(initialCapacity);
    }

    /**
     * Returns an Iterator over the elements in this HashSet.  The elements are
     * returned in no particular order.
     *
     * @see ConcurrentModificationException
     * @since JDK1.2
     */
    public Iterator iterator() {
        return map.keySet().iterator();
    }

    /**
     * Returns the number of elements in this HashSet (its cardinality).
     *
     * @since JDK1.2
     */
    public int size() {
        return map.size();
    }

    /**
     * Returns true if this HashSet contains no elements.
     *
     * @since JDK1.2
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Returns true if this HashSet contains the specified element.
     *
     * @exception  NullPointerException if the specified element is null.
     * @since JDK1.2
     */
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    /**
     * Adds the specified element to this HashSet if it is not already present.
     *
     * @param o element to be added to this HashSet.
     * @return true if the HashSet did not already contain the specified
     *               element. 
     * @exception  NullPointerException if the specified element is null.
     * @since JDK1.2
     */
    public boolean add(Object o) {
        return map.put(o, o)==null;
    }

    /**
     * Removes the given element from this HashSet if it is present.
     *
     * @param o object to be removed from this HashSet, if present.
     * @return true if the HashSet contained the specified element.
     * @exception  NullPointerException if the specified element is null.
     * @since JDK1.2
     */
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    /**
     * Removes all of the elements from this HashSet.
     *
     * @since JDK1.2
     */
    public void clear() {
        map.clear();
    }

    /**
     * Returns a shallow copy of this HashSet. (The elements themselves
     * are not cloned.)
     *
     * @since   JDK1.2
     */
    public Object clone() {
        try { 
            HashSet newSet = (HashSet)super.clone();
            newSet.map = (HashMap)map.clone();
            return newSet;
        } catch (CloneNotSupportedException e) { 
            throw new InternalError();
        }
    }
}
