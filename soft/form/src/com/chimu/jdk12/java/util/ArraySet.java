/*======================================================================
**
**  File: chimu/jdk12/java/util/ArraySet.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * This class implements the Set interface, backed by an ArrayList.  It is
 * designed to offer good performance for very small Sets, especially those
 * that are frequently created and destroyed.  The performance will be
 * <em>extremely</em> bad for large Sets: ArraySet provides linear time
 * performance for the basic operations (add, remove and contains).  This Set
 * permit all elements, including null.
 * <p>
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an ArraySet concurrently, and at least one of the
 * threads modifies the ArraySet, it <em>must</em> be synchronized externally.
 * This is typically accomplished by synchronizing on some object that
 * naturally encapsulates the ArraySet.  If no such object exists, the ArraySet
 * should be "wrapped" using the Collections.synchronizedSet method.  This is
 * best done at creation time, to prevent accidental unsynchronized access to
 * the ArraySet:
 * <pre>
 *        Set s = Collections.synchronizedSet(new ArraySet(...));
 * </pre>
 * <p>
 * The Iterators returned by ArraySet's iterator method are
 * <em>fail-fast</em>: if the HashSet is modified at any time after the
 * Iterator is created, in any way except through the Iterator's own remove
 * method, the Iterator will throw a ConcurrentModificationException.  Thus,
 * in the face of concurrent modification, the Iterator fails quickly and
 * cleanly, rather than risking arbitrary, non-deterministic behavior at an
 * undetermined time in the future.
 *
 * @author  Josh Bloch
 * @version 1.7 10/13/97
 * @see            Collection
 * @see            Set
 * @see            HashSet
 * @see            Collections.synchronizedSet
 * @see            ArrayList
 * @since JDK1.2
 */

public class ArraySet extends AbstractSet
                      implements Set, Cloneable, java.io.Serializable {
    private ArrayList a;

    /**
     * Constructs a new, empty ArraySet; the backing ArrayList has default
     * initial capacity and capacity increment.
     *
     * @since JDK1.2
     */
    public ArraySet() {
        a = new ArrayList();
    }

    /**
     * Constructs a new ArraySet containing the elements in the specified
     * Collection.  The backing ArrayList has default initial capacity and
     * capacity increment. 
     *
     * @exception  NullPointerException the specified collection is null.
     * @since JDK1.2
     */
    public ArraySet(Collection c) {
        a = new ArrayList();

        // Add elements of c to a.  Don't check for dups if c is a Set.
        Iterator i = c.iterator();
        if(c instanceof Set) {
            while(i.hasNext())
                a.add(i.next());
        } else {
            while(i.hasNext())
                add(i.next());
        }
    }

    /**
     * Constructs a new, empty ArraySet; the backing ArrayList has the
     * specified initial capacity and default capacity increment.
     *
     * @param   initialCapacity     the initial capacity of the ArrayList.
     * @since   JDK1.2
     */
    public ArraySet(int initialCapacity) {
        a = new ArrayList(initialCapacity);
    }

    /**
     * Returns an Iterator over the elements in this ArraySet.  The elements
     * are returned in the order they were added.
     *
     * @since JDK1.2
     */
    public Iterator iterator() {
        return a.iterator();
    }

    /**
     * Returns the number of elements in this ArraySet (its cardinality).
     *
     * @since JDK1.2
     */
    public int size() {
        return a.size();
    }

    /**
     * Returns true if this ArraySet contains no elements.
     *
     * @since JDK1.2
     */
    public boolean isEmpty() {
        return a.size()==0;
    }

    /**
     * Returns true if this ArraySet contains the specified element.
     *
     * @since JDK1.2
     */
    public boolean contains(Object o) {
        return a.contains(o);
    }

    /**
     * Adds the specified element to this Set if it is not already present.
     *
     * @param o element to be added to this Set.
     * @return true if the Set did not already contain the specified element.
     * @since JDK1.2
     */
    public boolean add(Object o) {
        boolean modified;
        if (modified = !a.contains(o))
            a.add(o);
        return modified;
    }

    /**
     * Removes the given element from this Set if it is present.
     *
     * @param o object to be removed from this Set, if present.
     * @return true if the Set contained the specified element.
     * @since JDK1.2
     */
    public boolean remove(Object o) {
        return a.remove(o);
    }

    /**
     * Removes all of the elements from this Set.
     *
     * @since JDK1.2
     */
    public void clear() {
        a.clear();
    }

    /**
     * Returns a shallow copy of this ArraySet. (The elements themselves
     * are not cloned.)
     *
     * @since   JDK1.2
     */
    public Object clone() {
        try { 
            ArraySet newSet = (ArraySet)super.clone();
            newSet.a = (ArrayList)a.clone();
            return newSet;
        } catch (CloneNotSupportedException e) { 
            throw new InternalError();
        }
    }
}
