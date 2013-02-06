/*======================================================================
**
**  File: chimu/kernel/collections/CollectionBase.java
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
import com.chimu.jdk12.java.util.Iterator;

import com.chimu.kernel.functors.*;

/**
Collection is the base abstraction of a "collection of objects".  It is read-only,
and unordered.  Subtypes allow ordering, indexing, keying, and other capabilities
on a collection.

**/
public interface CollectionBase { // extends ImplementationCheckable, Cloneable {

        /**
         * public version of java.lang.Object.clone
         * All Collections implement clone. But this is a protected method.
         * Copy allows public access.
         * @see clone
        **/
    public Object       copy();

        /**
         * Report the number of elements in the collection.
         * No other spurious effects.
         * @return number of elements
        **/
    public int size();

        /**
         * Report whether this collection has no elements.
         * Behaviorally equivalent to <CODE>size() == 0</CODE>.
         * @return true iff size() == 0
        **/
    public boolean isEmpty();

        /**
         * Returns true if this Collection contains the specified element.  More
         * formally, returns true if and only if this Collection contains at least
         * one element <code>e</code> such that <code>(o==null ? e==null :
         * o.equals(e))</code>.
         *
         * @param o element whose presence in this Collection is to be tested.
         * @since JDK1.2
         */
    public boolean contains(Object o);

        /**
         * Returns an Iterator over the elements in this Collection.  There are
         * no guarantees concerning the order in which the elements are returned
         * (unless this Collection is an instance of some class that provides a
         * guarantee).
         *
         * @since JDK1.2
         */
    public Iterator iterator();

        /**
         * Returns an array containing all of the elements in this Collection.  If
         * the Collection makes any guarantees as to what order its elements are
         * returned by its Iterator, this method must return the elements in the
         * same order.  The returned array will be "safe" in that no references to
         * it are maintained by the Collection.  (In other words, this method must
         * allocate a new array even if the Collection is backed by an Array).
         * The caller is thus free to modify the returned array.
         * <p>
         * This method acts as bridge between array-based and Collection-based
         * APIs.
         *
         * @since JDK1.2
         */
    Object[] toArray();


    public Object      any();
    
        /**
         * Return an enumeration that may be used to traverse through
         * the elements in the collection. Standard usage, for some
         * collection c, and some operation `use(Object obj)':
         * <PRE>
         * for (Enumeration e = c.elements(); e.hasMoreElements(); )
         *   use(e.nextElement());
         * </PRE>
         * (The values of nextElement very often need to
         * be coerced to types that you know they are.)
         * <P>
         * All Collections return instances
         * of Enumeration, that can report the number of remaining
         * elements, and also perform consistency checks so that
         * for UpdatableCollections, element enumerations may become
         * invalidated if the collection is modified during such a traversal
         * (which could in turn cause random effects on the collection.
         * TO prevent this,  Enumerations
         * raise CorruptedEnumerationException on attempts to access
         * nextElements of altered Collections.)
         * Note: Since all collection implementations are synchronizable,
         * you may be able to guarantee that element traversals will not be
         * corrupted by using the java <CODE>synchronized</CODE> construct
         * around code blocks that do traversals. (Use with care though,
         * since such constructs can cause deadlock.)
         * <P>
         * Guarantees about the nature of the elements returned by  nextElement of the
         * returned Enumeration may vary accross sub-interfaces.
         * In all cases, the enumerations provided by elements() are guaranteed to
         * step through (via nextElement) ALL elements in the collection.
         * Unless guaranteed otherwise (for example in Seq), elements() enumerations
         * need not have any particular nextElement() ordering so long as they
         * allow traversal of all of the elements. So, for example, two successive
         * calls to element() may produce enumerations with the same
         * elements but different nextElement() orderings.
         * Again, sub-interfaces may provide stronger guarantees. In
         * particular, Seqs produce enumerations with nextElements in
         * index order, ElementSortedCollections enumerations are in ascending
         * sorted order, and KeySortedCollections are in ascending order of keys.
         * @return an enumeration e such that
         * <PRE>
         *   e.numberOfRemainingElements() == size() &&
         *   foreach (v in e) includes(e)
         * </PRE>
        **/

    public Enumeration elements();
 
        /**
         * Cause the collection to become empty.
         * @return condition:
         * <PRE>
         * isEmpty() &&
         * Version change iff !PREV(this).isEmpty();
         * </PRE>
        **/
    public void clear();
    public void removeAll();
}
