/*======================================================================
**
**  File: chimu/kernel/collections/List.java
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

import java.util.Enumeration;
import java.util.Vector;

import java.util.NoSuchElementException;

import com.chimu.kernel.functors.*;

/**
Lists are Extensible Indexed Collections and allow new elements to be
added to the collection either at the end or at arbitrary indexes within the
collection
**/

public interface List extends Array {
    
    
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

        /**
         * Construct a new Seq that is a clone of self except
         * that it does not contain the elements before index or
         * after index+length. If length is less than or equal to zero,
         * return an empty Seq.
         * @param index of the element that will be the 0th index in new Seq
         * @param length the number of elements in the new Seq
         * @return new seq such that
         * <PRE>
         * s.size() == max(0, length) &&
         * foreach (int i in 0 .. s.size()-1) s.at(i).equals(at(i+index));
         * </PRE>
         * @exception NoSuchElementException if index is not in range 0..size()-1
        **/
    public List    copySubseq(int index, int length)
                        throws NoSuchElementException;

        /**
         * Construct a new Seq that is a clone of self except
         * that it adds (inserts) the indicated element at the
         * indicated index.
         * @param index the index at which the new element will be placed
         * @param element The element to insert in the new collection
         * @return new seq s, such that
         * <PRE>
         *  s.at(index) == element &&
         *  foreach (int i in 1 .. s.size()-1) s.at(i).equals(at(i-1));
         * </PRE>
         * @exception NoSuchElementException if index is not in range 0..size()-1
        **/

    public List  copyInsertingAtIndex_put(int index, Object element)
                        throws IllegalElementException,
                               NoSuchElementException;

        /**
         * Construct a new Seq that is a clone of self except
         * that the indicated element is placed at the indicated index.
         * @param index the index at which to replace the element
         * @param element The new value of at(index)
         * @return new seq, s, such that
         * <PRE>
         *  s.at(index) == element &&
         *  foreach (int i in 0 .. s.size()-1)
         *     (i != index) --&gt; s.at(i).equals(at(i));
         * </PRE>
         * @exception NoSuchElementException if index is not in range 0..size()-1
        **/

    public List   copyReplacingIndex_with(int index, Object element)
                        throws IllegalElementException,
                               NoSuchElementException;

        /**
         * Construct a new Seq that is a clone of self except
         * that it does not contain the element at the indeicated index; all
         * elements to its right are slided left by one.
         *
         * @param index the index at which to remove an element
         * @return new seq such that
         * <PRE>
         *  foreach (int i in 0.. index-1) s.at(i).equals(at(i)); &&
         *  foreach (int i in index .. s.size()-1) s.at(i).equals(at(i+1));
         * </PRE>
         * @exception NoSuchElementException if index is not in range 0..size()-1
        **/
    public List   copyRemovingIndex(int index)
                        throws NoSuchElementException;

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
    public void removeFrom_to(int fromIndex, int toIndex);
    public void removeIndex(int index);
    public void remove(int index);
    
//**************************************************************
//**************************************************************
//**************************************************************
    
    public void atIndex_insert(int index, Object value);
    public void atIndex_insertAll(int index, Collection c);

    // ************************* Covariant Overrides ******************************

    /**
     *Covariant override of inherited method
     *@return (List)
     */
    public /*(List)*/ Object copy();
}


