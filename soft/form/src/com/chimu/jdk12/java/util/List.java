/*======================================================================
**
**  File: chimu/jdk12/java/util/List.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * An ordered Collection (also known as a <i> sequence</i>).  The user of this
 * interface has precise control over where in the List each element is
 * inserted.  The user can access elements by their integer index (position) in
 * the List, and search for elements in the List.
 * <p>
 * Unlike Sets, Lists typically allow duplicate elements.  More formally,
 * Lists typically allow pairs of elements <code>e1</code> and <code>e2</code>
 * such that <code>e1.equals(e2)</code>, and they typically allow multiple
 * null elements if they allow null elements at all.  It is not inconceivable
 * that someone might wish to implement a List that prohibits duplicates,
 * by throwing runtime exceptions when the user attempts to insert them,
 * but we expect this usage to be rare.
 * <p>
 * The List interface places additional stipulations, beyond those specified
 * in the Collection Interface, on the contracts of the iterator, add,
 * remove, equals, and hashCode methods.  Declarations for other inherited
 * methods are also included here for convenience.
 * <p>
 * Lists provide four methods for positional (indexed) access to List
 * elements.  Lists (like Java arrays) are zero based.  Note that these
 * operations may execute in time proportional to the index value for some
 * implementations (LinkedList, for example). Thus, iterating over the
 * elements in a List is typically preferable to indexing through it if the
 * caller does not know the implementation.
 * <p>
 * List provides a special Iterator, called a ListIterator, that allows
 * element insertion and replacement, and bidirectional access in addition to
 * the normal Iterator operations.  A method is provided to obtain a List
 * iterator that starts at a specified position in the List.
 * <p>
 * List provides four methods to search for a specified object.  From a 
 * performance standpoint, these methods should be used with caution.
 * In many implementations, they will perform costly linear searches.
 * <p>
 * List provides two methods to efficiently insert and remove multiple
 * elements at an arbitrary point in the List.
 * <p>
 * Implemented by ArrayList, LinkedList, Vector, and returned by
 * Arrays.toList, Collection.subList and Collections.ncopies.
 *
 * @author  Josh Bloch
 * @version 1.15 10/13/97
 * @see Collection
 * @see Set
 * @see ArrayList
 * @see LinkedList
 * @see Vector
 * @see Arrays#toList(Object[])
 * @see Collections#subList(List, int, int)
 * @see Collections#nCopies(int, Object)
 * @see AbstractList
 * @see AbstractSequentialList
 * @since JDK1.2
 */

public interface List extends Collection {
    // Query Operations

    /**
     * Returns the number of elements in this List.
     *
     * @since JDK1.2
     */
    int size();

    /**
     * Returns true if this List contains no elements.
     *
     * @since JDK1.2
     */
    boolean isEmpty();

    /**
     * Returns true if this List contains the specified element.  More
     * formally, returns true if and only if this List contains at least
     * one element <code>e</code> such that  <code>(o==null ? e==null :
     * o.equals(e))</code>.
     *
     * @param o element whose presence in this List is to be tested.
     * @since JDK1.2
     */
    boolean contains(Object o);

    /**
     * Returns an Iterator over the elements in this List in proper sequence.
     *
     * @since JDK1.2
     */
    Iterator iterator();

    /**
     * Returns an array containing all of the elements in this List.
     * Obeys the general contract of Collection.toArray.
     *
     * @see Arrays#toList(Object[])
     * @since JDK1.2
     */
    Object[] toArray();


    // Modification Operations

    /**
     * Appends the specified element to the end of this List (optional
     * operation).
     *
     * @param o element to be appended to this List.
     * @return true (as per the general contract of Collection.add).
     * @exception UnsupportedOperationException add is not supported
     *                   by this Set.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this Set.
     * @exception IllegalArgumentException some aspect of this element prevents
     *                  it from being added to this Collection.
     * @since JDK1.2
     */
    boolean add(Object o);

    /**
     * Removes the first occurrence of the specified element in this List
     * (optional operation).  If the List does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index i
     * such that <code>(o==null ? get(i)==null : o.equals(get(i)))</code> (if
     * such an element exists).
     *
     * @param o element to be removed from this List, if present.
     * @return true if the List contained the specified element.
     * @exception UnsupportedOperationException remove is not supported
     *                  by this List.
     * @since JDK1.2
     */
    boolean remove(Object o);


    // Bulk Modification Operations

    /**
     * Returns true if this List contains all of the elements of the specified
     * Collection.
     *
     * @see #contains(Object)
     * @since JDK1.2
     */
    boolean containsAll(Collection c);

    /**
     * Appends all of the elements in the specified Collection to the end of
     * this this List, in the order that they are returned by the specified
     * Collection's Iterator (optional operation).  The behavior of this
     * operation is unspecified if the specified Collection is modified while
     * the operation is in progress.  (Note that this will occur if the
     * specified Collection is this List, and it's nonempty.)
     *
     * @return true if this List changed as a result of the call.
     * @exception UnsupportedOperationException addAll is not supported
     *                   by this List.
     * @exception ClassCastException class of an element in the specified
     *                   Collection prevents it from being added to this List.
     * @exception IllegalArgumentException some aspect of an element in the
     *                  specified Collection prevents it from being added to this
     *                  List.
     * @see #add(Object)
     * @since JDK1.2
     */
    boolean addAll(Collection c);

    /**
     * Removes from this List all of its elements that are contained in the
     * specified Collection (optional operation).
     *
     * @return true if this List changed as a result of the call.
     * @exception UnsupportedOperationException removeAll is not supported
     *                   by this List.
     * @see #remove(Object)
     * @see #contains(Object)
     * @since JDK1.2
     */
    boolean removeAll(Collection c);

    /**
     * Retains only the elements in this List that are contained in the
     * specified Collection (optional operation).  In other words, removes from
     * this List all of its elements that are not contained in the specified
     * Collection. 
     *
     * @return true if this List changed as a result of the call.
     * @exception UnsupportedOperationException retainAll is not supported
     *                   by this List.
     * @see #remove(Object)
     * @see #contains(Object)
     * @since JDK1.2
     */
    boolean retainAll(Collection c);

    /**
     * Removes all of the elements from this List (optional operation).  The
     * List will be empty after this call returns (unless it throws an
     * exception).
     *
     * @exception UnsupportedOperationException clear is not supported
     *                   by this List.
     * @since JDK1.2
     */
    void clear();


    // Comparison and hashing

    /**
     * Compares the specified Object with this List for equality.  Returns true
     * if and only if the specified Object is also a List, both Lists have the
     * same size, and all corresponding pairs of elements in the two Lists are
     * <em>equal</em>.  (Two elements <code>e1</code> and <code>e2</code> are
     * <em>equal</em> if <code>(e1==null ? e2==null : e1.equals(e2))</code>.)
     * In other words, two Lists are defined to be equal if they contain the
     * same elements in the same order.
     *
     * @param o the Object to be compared for equality with this List.
     * @return true if the specified Object is equal to this List.
     * @since JDK1.2
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this List.  The hash code of a List
     * is defined to be the result of the following calculation:
     * <pre>
     *  hashCode = 0;
     *  Iterator i = list.iterator();
     *  while (i.hasNext()) {
     *      Object obj = i.next();
     *      hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     *  }
     * </pre>
     * This ensures that <code>list1.equals(list2)</code> implies that
     * <code>list1.hashCode()==list2.hashCode()</code> for any two Lists,
     * <code>list1</code> and <code>list2</code>, as required by the general
     * contract of Object.hashCode.
     *
     * @see Object#hashCode()
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     * @since JDK1.2
     */
    int hashCode();


    // Positional Access Operations

    /**
     * Returns the element at the specified position in this List.
     *
     * @param index index of element to return.
     * @exception ArrayIndexOutOfBoundsException index is out of range (index
     *                   &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    Object get(int index);

    /**
     * Replaces the element at the specified position in this List with the
     * specified element (optional operation).
     *
     * @param index index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @exception UnsupportedOperationException set is not supported
     *                  by this List.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this List.
     * @exception IllegalArgumentException some aspect of the specified
     *                  element prevents it from being added to this List.
     * @exception ArrayIndexOutOfBoundsException index out of range
     *                  (index &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    Object set(int index, Object element);

    /**
     * Inserts the specified element at the specified position in this List
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @exception UnsupportedOperationException add is not supported
     *                  by this List.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this List.
     * @exception IllegalArgumentException some aspect of the specified
     *                  element prevents it from being added to this List.
     * @exception ArrayIndexOutOfBoundsException index is out of range
     *                  (index &lt; 0 || index &gt; size()).
     * @since JDK1.2
     */
    void add(int index, Object element);

    /**
     * Removes the element at the specified position in this List (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * List.
     *
     * @param index the index of the element to removed.
     * @return the element previously at the specified position.
     * @exception UnsupportedOperationException remove is not supported
     *                  by this List.
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *                   &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    Object remove(int index);


    // Search Operations

    /**
     * Returns the index in this List of the first occurrence of the specified
     * element, or -1 if the List does not contain this element.
     * More formally, returns the lowest index i such that
     * <code>(o==null ? get(i)==null : o.equals(get(i)))</code>,
     * or -1 if there is no such index.
     *
     * @param o element to search for.
     * @since JDK1.2
     */
    int indexOf(Object o);

    /**
     * Returns the index in this List of the first occurrence of the specified
     * element at or after the specified position, or -1 if the element is
     * not found.  More formally, returns the lowest index
     * i &gt;= index such that <code>(o==null ? get(i)==null :
     * o.equals(get(i)))</code>, or -1 if there is no such index.
     *
     * @param o element to search for.
     * @param index initial position to search for the specified element.
     * @exception ArrayIndexOutOfBoundsException index out of range
     *                   (index &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    int indexOf(Object o, int index);

    /**
     * Returns the index in this List of the last occurrence of the specified
     * element, or -1 if the List does not contain this element.
     * More formally, returns the highest index i such that
     * <code>(o==null ? get(i)==null : o.equals(get(i)))</code>,
     * or -1 if there is no such index.
     *
     * @param o element to search for.
     * @since JDK1.2
     */
    int lastIndexOf(Object o);

    /**
     * Returns the index in this List of the last occurrence of the specified
     * element at or before the specified position, or -1 if the List does not
     * contain this element.  More formally, returns the highest index
     * i &lt;= index such that <code>(o==null ? get(i)==null :
     * o.equals(get(i)))</code>, or -1 if there is no such index.
     *
     * @param o element to search for.
     * @param index initial position to search for the specified element.
     * @exception ArrayIndexOutOfBoundsException index out of range
     *                   (index &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    int lastIndexOf(Object o, int index);


    // Range Operations

        /**
         * Removes from this List all of the elements whose index is between
         * fromIndex, inclusive and toIndex, exclusive (optional operation).
         * Shifts any succeeding elements to the left (reduces their index).
         * This call shortens the List by (toIndex - fromIndex) elements.  (If
         * toIndex==fromIndex, this operation has no effect.)
         *
         * @param fromIndex index of first element to be removed.
         * @param toIndex index after last element to be removed.
         * @exception UnsupportedOperationException removeRange is not supported
         *                  by this List.
         * @exception ArrayIndexOutOfBoundsException fromIndex or toIndex out of
         *                  range (fromIndex &lt; 0 || fromIndex &gt;= size() || toIndex
         *                  &gt; size() || toIndex &lt; fromIndex).
         * @since JDK1.2
         */
    void removeRange(int fromIndex, int toIndex);

    /**
     * Inserts all of the elements in the specified Collection into this
     * List at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in the List in the order that they are returned by the
     * specified Collection's iterator.  The behavior of this operation is
     * unspecified if the specified Collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * Collection is this List, and it's nonempty.)
     *
     * @param index index at which to insert first element from the specified
     *                    Collection. 
     * @param c elements to be inserted into this List.
     * @return true if this List changed as a result of the call.
     * @exception UnsupportedOperationException addAll is not supported
     *                  by this List.
     * @exception ClassCastException class of one of elements of the specified
     *                   Collection prevents it from being added to this List.
     * @exception IllegalArgumentException some aspect of one of elements of
     *                  the specified Collection prevents it from being added to
     *                  this List.
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *                  &lt; 0 || index &gt; size()).
     * @since JDK1.2
     */
    boolean addAll(int index, Collection c);


    // List Iterators

    /**
     * Returns a ListIterator of the elements in this List (in proper
     * sequence).
     *
     * @since JDK1.2
     */
    ListIterator listIterator();

    /**
     * Returns a ListIterator of the elements in this List (in proper
     * sequence), starting at the specified position in the List.  The
     * specified index indicates the first element that would be returned by
     * an initial call to nextElement.  An initial call to previousElement
     * would return the element with the specified index minus one.
     *
     * @param index index of first element to be returned from the
     *                    ListIterator (by a call to getNext).
     * @exception ArrayIndexOutOfBoundsException index is out of range
     *                  (index &lt; 0 || index &gt; size()).
     * @since JDK1.2
     */
    ListIterator listIterator(int index);
}
