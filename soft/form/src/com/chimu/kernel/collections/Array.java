/*======================================================================
**
**  File: chimu/kernel/collections/Array.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
**  Portions of these collection classes were originally written by
**  Doug Lea (dl@cs.oswego.edu) and released into the public domain.
**  Doug Lea thanks the assistance and support of Sun Microsystems Labs,
**  Agorics Inc, Loral, and everyone contributing, testing, and using
**  this code.
**      ChiMu Corporation thanks Doug Lea and all of the above.
**
======================================================================*/

package com.chimu.kernel.collections;
import com.chimu.kernel.functors.*;

import java.util.Vector;

import java.util.NoSuchElementException;

/**
Arrays are fixed length (after creation), updatable, indexed
collections of objects
**/

public interface Array extends Collection {

        /**
         * Returns a ListIterator of the elements in this List (in proper
         * sequence).
         *
         * @since JDK1.2
         */
    com.chimu.jdk12.java.util.ListIterator listIterator();

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
    com.chimu.jdk12.java.util.ListIterator listIterator(int index);
    
//**************************************************************
//**************************************************************
//**************************************************************

        /**
         * Return the element at the indicated index
         * @exception NoSuchElementException if index is not in range 0..size()-1
        **/

    public Object      atIndex(int index)
                       throws  NoSuchElementException;

        /**
         * Return the first element, if it exists.
         * Behaviorally equivalent to atIndex(0)
         * @exception NoSuchElementException if isEmpty
        **/

    public Object      first()
                       throws NoSuchElementException;

        /**
         * Return the last element, if it exists.
         * Behaviorally equivalent to atIndex(size()-1)
         * @exception NoSuchElementException if isEmpty
        **/

    public Object      last()
                       throws NoSuchElementException;

        /**
         * Report the index of least-index occurrence of an element from a
         * given starting index, or -1 if there is no such index.
         * @param element the element to look for
         * @param startingIndex the index to start looking from. The startingIndex
         * need not be a valid index. If less than zero it is treated as 0.
         * If greater than or equal to size(), the result will always be -1.
         * @return index such that
         * <PRE>
         * let int si = max(0, startingIndex) in
         *  index == -1 &&
         *   foreach (int i in si .. size()-1) !at(index).equals(element)
         *  ||
         *  at(index).equals(element) &&
         *   foreach (int i in si .. index-1) !at(index).equals(element)
         * </PRE>
        **/

    public int         firstIndexOf(Object element, int startingIndex);
    public int         indexOf(Object element, int startingIndex);

        /**
         * Find the least-index occurrence of an element.
         * Behaviorally equivalent to firstIndexOf(element, 0)
        **/

    public int         firstIndexOf(Object element);
    public int         indexOf(Object value);

        /**
         * Report the index of righttmost occurrence of an element from a
         * given starting point, or -1 if there is no such index.
         * @param element the element to look for
         * @param startingIndex the index to start looking from. The startingIndex
         * need not be a valid index. If less than zero the result
         * will always be -1.
         * If greater than or equal to size(), it is treated as size()-1.
         * @return index such that
         * <PRE>
         * let int si = min(size()-1, startingIndex) in
         *  index == -1 &&
         *   foreach (int i in 0 .. si) !at(index).equals(element)
         *  ||
         *  at(index).equals(element) &&
         *   foreach (int i in index+1 .. si) !at(index).equals(element)
         * </PRE>
         *
        **/
    public int         lastIndexOf(Object element, int startingIndex);


        /**
         * Find the rightmost occurrence of an element.
         * Behaviorally equivalent to lastIndexOf(element, size()-1)
        **/
    public int         lastIndexOf(Object element);

    //**********************************************************
    //(P)******************* Converting ************************
    //**********************************************************

    public Object[] toJavaArray();

    public Vector toJdkVector();

//**************************************************************
//**************************************************************
//**************************************************************

 //   public Enumeration elements();

    public void sort();
    public void sort(Predicate2Arg greaterThanPredicate);

    public void atIndex_put (int index, Object value) throws ArrayIndexOutOfBoundsException;
    public void atAllPut (Object value);
    public void swapAtIndex_withIndex(int index1, int index2) throws ArrayIndexOutOfBoundsException;

    public void atAllPutNull();  // Put nulls into all the array slots

    //**********************************************************
    //********************* Covariant Overrides ****************
    //**********************************************************

        /**
         *Covariant override of inherited method
         *@return (Array)
         */
    public /*(Array)*/ Object copy();

};