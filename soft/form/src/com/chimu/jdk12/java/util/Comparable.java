/*======================================================================
**
**  File: chimu/jdk12/java/util/Comparable.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * This interface imposes a total ordering on the objects of each class
 * that implements it.  This ordering is referred to as the class's
 * <i>natural ordering</i>, and the class's compareTo function is
 * referred to as its <i>natural comparison method</i>.
 * <p>
 * Arrays of Objects that implement this interface can be sorted automatically
 * by List.sort.  Objects of these classes can also be used as keys in
 * TreeTables without the need to specify a Comparator.
 * <p>
 * Classes that implement this interface include String, Byte, Character,
 * Short, Integer, Long, Float, Double, BigInteger, BigDecimal, File, URL,
 * Date.
 * <p>
 * @author  Josh Bloch
 * @version 1.4 09/21/97
 * @see java.util.Comparator
 * @see java.util.Arrays#sort(Object[], Comparator)
 * @see TreeTable
 */

public interface Comparable {
    /**
     * Compares this Object with the specified Object for order.  Returns a
     * negative integer, zero, or a positive integer as this Object is less
     * than, equal to, or greater than the given Object.
     * <p>
     * The implementor must ensure sgn(x.compareTo(y)) == -sgn(y.compareTo(x))
     * for all x and y.  (This implies that x.compareTo(y) must throw an
     * exception iff y.compareTo(x) throws an exception.)
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * (x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0) implies
     * x.compareTo(z)&gt;0.
     * <p>
     * The implementer must also ensure that x.equals(y) implies that
     * x.compareTo(y)==0.  Note that the converse is not necessarily true
     * (e.g., BigDecimal).
     * <p>
     * Finally, the implementer must ensure that x.compareTo(y)==0 implies
     * that sgn(x.compareTo(z)) == sgn(y.compareTo(z)), for all z.
     *
     * @param   o the <code>Object</code> to be compared.
     * @return  a negative integer, zero, or a positive integer as this Object
     *		is less than, equal to, or greater than the given Object.
     * @exception ClassCastException the specified Object's type prevents it
     *		  from being compared to this Object.
     * @since JDK1.2
     */
    public int compareTo(Object o);
}
