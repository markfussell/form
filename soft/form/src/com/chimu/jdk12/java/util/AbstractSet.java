/*======================================================================
**
**  File: chimu/jdk12/java/util/AbstractSet.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * This class provides a skeletal implementation of the Set interface to
 * minimize the effort required to implement this interface.
 * <p>
 * The process of implementing a Set by extending this class is identical to
 * that of implementing a Collection by extending AbstractCollection, except
 * that all of the methods and constructors in subclasses of this class must
 * obey the additional constraints imposed by the Set interface (for
 * instance, the add method must not permit addition of multiple intances of
 * an object to a Set).
 * <p>
 * Note that this class does not override any of the implementations
 * from abstractCollection.  It merely adds implementations for 
 * equals and hashCode.
 *
 * @author  Josh Bloch
 * @version 1.4 09/11/97
 * @see Collection
 * @see AbstractCollection
 * @see Set
 * @since   JDK1.2
 */

public abstract class AbstractSet extends AbstractCollection implements Set {
    // Comparison and hashing

    /**
     * Compares the specified Object with this Set for equality.
     * Returns true if the given object is also a Set, the two Sets have the
     * same size, and every member of the given Set is contained in this Set.
     * This ensures that the equals method works properly accross different
     * implementations of the Set interface.
     * <p>
     * This implementation first checks if the specified Object is this Set;
     * if so it returns true.  Then, it checks if the specified Object is
     * a Set whose size is identical to the size of this Set; if not, it
     * it returns false.  If so, it returns
     * <code>containsAll((Collection) o)</code>.
     *
     * @param o Object to be compared for equality with this Set.
     * @return true if the specified Object is equal to this Set.
     * @since JDK1.2
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Set))
            return false;
        Collection c = (Collection) o;
        if (c.size() != size())
            return false;
        return containsAll(c);
    }

    /**
     * Returns the hash code value for this Set.  The hash code of a Set
     * is defined to be the sum of the hashCodes of the elements
     * in the Set.  This ensures that <code>s1.equals(s2)</code> implies that
     * <code>s1.hashCode()==s2.hashCode()</code> for any two Sets
     * <code>s1</code> and <code>s2</code>, as required by the general
     * contract of Object.hashCode.
     * <p>
     * This implementation enumerates over the Set, calling hashCode
     * on each element in the Collection, and adding up the results.
     *
     * @since JDK1.2
     */
    public int hashCode() {
        int h = 0;
        Iterator e = iterator();
        while (e.hasNext())
            h += e.next().hashCode();
        return h;
    }
}

