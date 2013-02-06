/*======================================================================
**
**  File: chimu/jdk12/java/util/Comparator.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * A comparison function, which imposes a total ordering on some collection
 * of Objects.  Comparators can be passed to a sort method (such as
 * Arrays.sort) to allow precise control over the sort order. Comparators can
 * also be used to control the order of certain data structures (such as
 * TreeMap).
 *
 * @author  Josh Bloch
 * @version 1.4 09/22/97
 * @see java.lang.Comparable
 * @see Arrays#sort(Object[], Comparator)
 * @see TreeMap
 * @since   JDK1.2
 */

public interface Comparator {
    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     * <p>
     * The implementor must ensure that sgn(compare(x, y)) == -sgn(compare(y,
     * x)) for all x and y.  (This implies that compare(x, y) must throw
     * an exception if and only if compare(y, x) throws an exception.)
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * ((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0)) implies
     * compare(x, z)>0. 
     * <p>
     * The implementer must also ensure that x.equals(y) implies that 
     * compare(x, y) == 0.  Note that the converse is not necessarily true.
     * <p>
     * Finally, the implementer must ensure that compare(x, y) == 0 implies
     * that sgn(compare(x, z)) == sgn(compare(y, z)), for all z.
     * 
     * @return a negative integer, zero, or a positive integer as the
     *                first argument is less than, equal to, or greater than the
     *               second. 
     * @exception ClassCastException the arguments' types prevent them from
     *                   being compared by this Comparator.
     * @since   JDK1.2
     */
    public int compare(Object o1, Object o2);
}
