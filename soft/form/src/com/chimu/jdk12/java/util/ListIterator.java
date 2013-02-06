/*======================================================================
**
**  File: chimu/jdk12/java/util/ListIterator.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * An Iterator for Lists that allows the programmer to traverse the
 * List in either direction and modify the list during iteration.
 *
 * @author  Josh Bloch
 * @version 1.5 10/13/97
 * @see Collection
 * @see List
 * @see Iterator
 * @see Enumeration
 * @since   JDK1.2
 */
public interface ListIterator extends Iterator {
    // Query Operations

    /**
     * Returns true if this ListIterator has more elements when traversing
     * the list in the forward direction. (In other words, returns true
     * if next would return an element rather than throwing an exception.)
     *
     * @since JDK1.2
     */
    boolean hasNext();

    /**
     * Returns the next element in the List.  This method may be called
     * repeatedly to iterate through the List, or intermixed with calls to
     * previous to go back and forth.  (Note that alternating calls to
     * next and previous will return the same element repeatedly.)
     *
     * @exception NoSuchElementException iteration has no next element.
     * @since JDK1.2
     */
    Object next();

    /**
     * Returns true if this ListIterator has more elements when traversing
     * the list in the reverse direction.   (In other words, returns true
     * if previous would return an element rather than throwing an exception.)
     *
     * @since JDK1.2
     */
    boolean hasPrevious();

    /**
     * Returns the previous element in the List.  This method may be
     * called repeatedly to iterate through the list backwards, or
     * intermixed with calls to next to go back and forth.  (Note that
     * alternating calls to next and previous will return the same
     * element repeatedly.)
     *
     * @exception NoSuchElementException iteration has no previous element.
     * @since JDK1.2
     */
    Object previous();

    /**
     * Returns the index of the element that would be returned by a subsequent
     * call to next. (Returns List size if the ListIterator is at the end of
     * the list.)
     *
     * @since JDK1.2
     */
    int nextIndex();

    /**
     * Returns the index of the element that would be returned by a subsequent
     * call to previous. (Returns -1 if the ListIterator is at the beginning
     * of the list.)
     *
     * @since JDK1.2
     */
    int previousIndex();


    // Modification Operations
    
    /**
     * Removes from the List the last element that was returned by next
     * or previous.  This call can only be made once per call to next
     * or previous.  Optional operation.
     *
     * @exception UnsupportedOperationException remove is not supported
     *                   by this ListIterator.
     * @exception NoSuchElementException neither next nor previous have been
     *                   called, or remove has already been called after the last call
     *                  to next or previous.
     * @since JDK1.2
     */
    void remove();

    /**
     * Replaces the last element returned by next or previous with the
     * specified element.  Optional operation.
     *
     * @exception UnsupportedOperationException set is not supported
     *                   by this ListIterator.
     * @exception NoSuchElementException neither next nor previous have been
     *                   called, or remove was called after the last call to next or
     *                  previous. 
     * @since JDK1.2
     */
    void set(Object o);

    /**
     * Inserts the specified element into the List.  The element is inserted
     * immediately before the next element that would be returned by
     * getNext, if any, and after the next element that would be returned by
     * getPrevious, if any. (If the List contains no elements, the new
     * element becomes the sole element on the List.)  This call does not
     * move the cursor: a subsequent call to next would return the element
     * that was added by the call, and a subsequent call to previous would
     * be unaffected.  Optional operation.
     *
     * @exception UnsupportedOperationException add is not supported
     *                   by this ListIterator.
     * @since JDK1.2
     */
    void add(Object o);
}
