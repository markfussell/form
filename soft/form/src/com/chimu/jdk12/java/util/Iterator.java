/*======================================================================
**
**  File: chimu/jdk12/java/util/Iterator.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * An iterator over a Collection.  Iterator takes the place of Enumeration in
 * the Java collection framework.  Iterators differ from Enumerations in two
 * ways: <ul> <li> Iterators allow the caller to remove elements from the
 * underlying collection during the iteration with well-defined
 * semantics. <li>Method names have been improved.</ul>
 *
 * @author  Josh Bloch
 * @version 1.4 08/07/97
 * @see Collection
 * @see ListIterator
 * @see Enumeration
 * @since JDK1.2
 */
public interface Iterator {
    /**
     * Returns true if the iteration has more elements.
     *
     * @since JDK1.2
     */
    boolean hasNext();

    /**
     * Returns the next element in the interation.
     *
     * @exception NoSuchElementException iteration has no more elements.
     * @since JDK1.2
     */
    Object next();

    /**
     * Removes from the underlying Collection the last element returned by the
     * Iterator .  This method can be called only once per call to next  The
     * behavior of an Iterator is unspecified if the underlying Collection is
     * modified while the iteration is in progress in any way other than by
     * calling this method.  Optional operation.
     *
     * @exception UnsupportedOperationException remove is not supported
     *                   by this Iterator.
     * @exception NoSuchElementException next has not yet been called,
     *                  or remove has already been called after the last call
     *                  to next.
     * @since JDK1.2
     */
    void remove();
}
