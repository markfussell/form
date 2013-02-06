/*======================================================================
**
**  File: chimu/jdk12/java/util/ConcurrentModificationException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * This exception may be thrown by methods that have detected concurrent
 * modification of a backing object when such modification is not permissible.
 * <p>
 * For example, it is not permssible for one thread to modify a Collection
 * while another thread is iterating over it.  In general, the results of the
 * iteration are undefined under these circumstances.  Some Iterator
 * implementations (including those of all the collection implementations
 * provided by the JDK) may choose to throw this exception if this behavior is
 * detected.  Iterators that do this are known as <em>fail-fast</em>
 * iterators, as they fail quickly and cleanly, rather that risking arbitrary,
 * non-deterministic behavior at an undetermined time in the future.
 *
 * @author  Josh Bloch
 * @version 1.2, 09/22/97
 * @see            Collection
 * @see     Iterator
 * @see     ListIterator
 * @see            Vector
 * @see            LinkedList
 * @see            HashSet
 * @see            ArraySet
 * @see            Hashtable
 * @see            TreeMap
 * @see            ArrayMap
 * @see            AbstractList
 * @since   JDK1.2
 */
public class ConcurrentModificationException extends RuntimeException {
    /**
     * Constructs a ConcurrentModificationException with no
     * detail message.
     *
     * @since   JDK1.2
     */
    public ConcurrentModificationException() {
    }

    /**
     * Constructs a <code>ConcurrentModificationException</code> with the
     * specified detail message.
     *
     * @since   JDK1.2
     */
    public ConcurrentModificationException(String message) {
        super(message);
    }
}
