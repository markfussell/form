/*======================================================================
**
**  File: chimu/jdk12/java/util/AbstractSequentialList.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * This class provides a skeletal implementation of the List interface to
 * minimize the effort required to implement this interface backed by a
 * "sequential access" data store (such as a linked list).  For random access
 * data (such as an array), AbstractList should be used in preference to this
 * Class.
 * <p>
 * This class is the opposite of AbstractList in the sense that
 * it implements the "random access" methods (get(int index), set(int index,
 * Object element), set(int index, Object element), add(int index, Object
 * element) and remove(int index)) on top of List's listIterator, instead
 * of the other way around.
 * <p>
 * To implement a List the programmer needs only to extend this class and
 * provide implementations for the listIterator and size methods.  For an
 * unmodifiable List, the programmer need only implement the listIterator's
 * hasNext, next, hasPrevious, previous and index methods.
 * <p>
 * For a modifiable List the programmer should additionally implement the
 * listIterator's set method.  For a variable-size list the programmer
 * should additionally implement the listIterator's remove and add methods.
 * <p>
 * The programmer should generally provide a void (no argument) and
 * Collection constructor, as per the recommendation in the Collection
 * interface specification.
 *
 * @author  Josh Bloch
 * @version 1.6 10/08/97
 * @see Collection
 * @see List
 * @see AbstractList
 * @see AbstractCollection
 * @since JDK1.2
 */

public abstract class AbstractSequentialList extends AbstractList {
    /**
     * Returns the element at the specified position in this List.
     * <p>
     * This implementation first gets a ListIterator pointing to the
     * indexed element (with listIterator(index)).  Then, it gets
     * the element using ListIterator.next and returns it.
     *
     * @param index index of element to return.
     * @exception ArrayIndexOutOfBoundsException index is out of range (index
     *                   &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    public Object get(int index) {
        ListIterator e = listIterator(index);
        try {
            return(e.next());
        } catch(NoSuchElementException exc) {
            throw(new ArrayIndexOutOfBoundsException(index));
        }
    }

    /**
     * Replaces the element at the specified position in this List with the
     * specified element.
     * <p>
     * This implementation first gets a ListIterator pointing to the
     * indexed element (with listIterator(index)).  Then, it gets
     * the current element using ListIterator.next and replaces it
     * with ListIterator.set.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException if listIterator does not implement
     * the set operation.
     *
     * @param index index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @exception UnsupportedOperationException set is not supported
     *                  by this List.
     * @exception NullPointerException this List does not permit null
     *                   elements and one of the elements of <code>c</code> is null.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this List.
     * @exception IllegalArgumentException some aspect of the specified
     *                  element prevents it from being added to this List.
     * @exception ArrayIndexOutOfBoundsException index out of range
     *                  (index &lt; 0 || index &gt;= size()).
     * @exception IllegalArgumentException fromIndex &gt; toIndex.
     * @since JDK1.2
     */
    public Object set(int index, Object element) {
        ListIterator e = listIterator(index);
        try {
            Object oldVal = e.next();
            e.set(element);
            return oldVal;
        } catch(NoSuchElementException exc) {
            throw(new ArrayIndexOutOfBoundsException(index));
        }
    }

    /**
     * Inserts the specified element at the specified position in this List.
     * Shifts the element currently at that position (if any) and any
     * subsequent elements to the right (adds one to their indices).
     * <p>
     * This implementation first gets a ListIterator pointing to the
     * indexed element (with listIterator(index)).  Then, it inserts
     * the specified element with ListIterator.add.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException if listIterator does not implement
     * the add operation.
     *
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @exception UnsupportedOperationException add is not supported
     *                  by this List.
     * @exception NullPointerException this List does not permit null
     *                   elements and one of the elements of <code>c</code> is null.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this List.
     * @exception IllegalArgumentException some aspect of the specified
     *                  element prevents it from being added to this List.
     * @exception ArrayIndexOutOfBoundsException index is out of range
     *                  (index &lt; 0 || index &gt; size()).
     * @since JDK1.2
     */
    public void add(int index, Object element) {
        ListIterator e = listIterator(index);
        e.add(element);
    }

    /**
     * Removes the element at the specified position in this List.
     * shifts any subsequent elements to the left (subtracts one from their
     * indices).  Returns the element that was removed from the List.
     * <p>
     * This implementation first gets a ListIterator pointing to the
     * indexed element (with listIterator(index)).  Then, it removes
     * the element with ListIterator.remove.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException if listIterator does not implement
     * the remove operation.
     *
     * @exception UnsupportedOperationException remove is not supported
     *                  by this List.
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *                   &lt; 0 || index &gt;= size()).
     * @param index the index of the element to removed.
     * @since JDK1.2
     */
    public Object remove(int index) {
        ListIterator e = listIterator(index);
        Object outCast;
        try {
            outCast = e.next();
        } catch(NoSuchElementException exc) {
            throw(new ArrayIndexOutOfBoundsException(index));
        }
        e.remove();
        return(outCast);
    }


    // Range Operations

    /**
     * Removes from this List all of the elements whose index is between
     * fromIndex, inclusive and toIndex, exclusive.  Shifts any succeeding
     * elements to the left (reduces their index).
     * This call shortens the List by (toIndex - fromIndex) elements.  (If
     * toIndex==fromIndex, this operation has no effect.)
     * <p>
     * This implementation first checks to see that the indices are in
     * range.  Then, it gets a  ListIterator pointing to the
     * element at the beginning of the range (with listIterator(index)).
     * Finally, it iterates over the range, calling ListIterator's
     * remove operation for each element to be removed.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException if listIterator does not implement
     * the remove operation.
     *
     * @param fromIndex index of first element to be removed.
     * @param fromIndex index after last element to be removed.
     * @exception UnsupportedOperationException removeRange is not supported
     *                  by this List.
     * @exception ArrayIndexOutOfBoundsException fromIndex or toIndex out of
     *                  range (fromIndex &lt; 0 || fromIndex &gt;= size() || toIndex
     *                  &gt; size() || toIndex &lt; fromIndex).
     * @since JDK1.2
     */
    public void removeRange(int fromIndex, int toIndex) {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex > toIndex");
        if (toIndex > size())
            throw(new ArrayIndexOutOfBoundsException(toIndex));

        ListIterator e = listIterator(fromIndex);
        for (int i = fromIndex; i<toIndex; i++) {
            e.next();
            e.remove();
        }
    }

    /**
     * Inserts all of the elements in in the specified Collection into this List
     * at the specified position.  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (increases their
     * indices).  The new elements will appear in the List in the order that
     * they are returned by the specified Collection's iterator.  The behavior
     * of this operation is unspecified if the specified Collection is modified
     * while the operation is in progress.  (Note that this will occur if the
     * specified Collection is this List, and it's nonempty.)  Optional
     * operation.
     * <p>
     * This implementation gets an Iterator over the specified Collection
     * and a ListIterator over this List pointing to the indexed element (with
     * listIterator(index)).  Then, it iterates over the specified Collection,
     * inserting the elements obtained from the Iterator into this List, one at
     * a time, using ListIterator.add followed by  ListIterator.next (to skip
     * over the added element).
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException if the ListIterator returned by
     * listIterator does not implement the add operation.
     *
     * @return true if this List changed as a result of the call.
     * @param index index at which to insert first element
     *                    from the specified collection.
     * @param c elements to be inserted into this List.
     * @exception UnsupportedOperationException addAll is not supported
     *                  by this List.
     * @exception NullPointerException this List does not permit null
     *                   elements and one of the elements of <code>c</code> is null.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this List.
     * @exception IllegalArgumentException some aspect of the specified
     *                  element prevents it from being added to this List.
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *                  &lt; 0 || index &gt; size()).
     * @since JDK1.2
     */
    public boolean addAll(int index, Collection c) {
        boolean modified = false;
        ListIterator e1 = listIterator(index);
        Iterator e2 = c.iterator();
        while (e2.hasNext()) {
            e1.add(e2.next());
            e1.next();        // Skip over the element that we just added
            modified = true;
        }
        return modified;
    }


    // Iterators

    /**
     * Returns a ListIterator of the elements in this List (in proper
     * sequence).
     *
     * @since JDK1.2
     */
    public abstract ListIterator listIterator(int index);
}
