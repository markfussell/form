/*======================================================================
**
**  File: chimu/jdk12/java/util/Set.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * A Collection that contains no duplicate elements.  More formally, Sets
 * contain no pair of elements <code>e1</code> and <code>e2</code> such that
 * <code>e1.equals(e2)</code>, and at most one null element.  As implied by
 * its name, this interface models the mathematical <i>set</i> abstraction.
 * <p>
 * The Set interface places additional stipulations, beyond those inherited
 * from the Collection interface, on the contracts of all constructors and of
 * the add, equals and hashCode methods.  Declarations for other inherited
 * methods are also included here for convenience.  (The specifications
 * accompanying these declarations have been tailored to Set, but they do not
 * contain any additional stipulations.)
 * <p>
 * The additional stipulation on constructors is, not surprisingly,
 * that all constructors must create a Set that contain no duplicate elements
 * (as defined above).
 * <p>
 * Implemented by HashSet, ArraySet.
 *
 * @author  Josh Bloch
 * @version 1.13 10/13/97
 * @see Collection
 * @see List
 * @see HashSet
 * @see ArraySet
 * @see AbstractSet
 * @since JDK1.2
 */

public interface Set extends Collection {
    // Query Operations

        /**
         * Returns the number of elements in this Set (its cardinality).
         *
         * @since JDK1.2
         */
    int size();

        /**
         * Returns true if this Set contains no elements.
         *
         * @since JDK1.2
         */
    boolean isEmpty();

        /**
         * Returns true if this Set contains the specified element.  More
         * formally, returns true if and only if this Set contains an element
         * <code>e</code> such that <code>(o==null ? e==null : o.equals(e))</code>.
         *
         * @since JDK1.2
         */
    boolean contains(Object o);

        /**
         * Returns an Iterator over the elements in this Set.  The elements are
         * returned in no particular order (unless the Set is an instance of some
         * class that provides a guarantee).
         *
         * @since JDK1.2
         */
    Iterator iterator();

        /**
         * Returns an array containing all of the elements in this Set.
         * Obeys the general contract of Collection.toArray.
         *
         * @since JDK1.2
         */
    Object[] toArray();


    // Modification Operations

        /**
         * Adds the specified element to this Set if it is not already present
         * (optional operation).  More formally, adds the specified element,
         * <code>o</code>, to the Set if the Set contains no element
         * <code>e</code> such that <code>(o==null ? e==null :
         * o.equals(e))</code>.  If the Set already contains the specified
         * element, the call leaves the Set unchanged (and returns false).  In
         * combination with the restriction on constructors, this ensures that
         * Sets never contain duplicate elements.
         * <p>
         * This stipulation should not be construed to imply that Sets must
         * accept all elements; Sets have the option of refusing to add any
         * particular element, including null, and throwing an exception, as
         * described in the specification for Collection.add.  Individual
         * Set implementations should clearly document any restrictions on the
         * the elements that they may contain.
         *
         * @param o element to be added to this Set.
         * @return true if the Set did not already contain the specified element.
         * @exception UnsupportedOperationException add is not supported
         *                   by this Set.
         * @exception ClassCastException class of the specified element
         *                   prevents it from being added to this Set.
         * @exception IllegalArgumentException some aspect of this element prevents
         *                  it from being added to this Set.
         * @since JDK1.2
         */
    boolean add(Object o);


        /**
         * Removes the given element from this Set if it is present (optional
         * operation).  More formally, removes an element <code>e</code> such
         * that <code>(o==null ?  e==null : o.equals(e))</code>, if the Set
         * contains such an element.  Returns true if the Set contained the
         * specified element (or equivalently, if the Set changed as a result of
         * the call).  (The Set will not contain the specified element once the
         * call returns.)
         *
         * @param o object to be removed from this Set, if present.
         * @return true if the Set contained the specified element.
         * @exception UnsupportedOperationException remove is not supported
         *                  by this Set.
         * @since JDK1.2
         */
    boolean remove(Object o);


    // Bulk Operations

        /**
         * Returns true if this Set contains all of the elements of the
         * specified Collection.  If the specified Collection is also a Set,
         * this method returns true if it is a <i>subset</i> of this Set.
         *
         * @since JDK1.2
         */
    boolean containsAll(Collection c);

        /**
         * Adds all of the elements in the specified Collection to this Set if
         * they're not already present (optional operation).  If the specified
         * Collection is also a Set, this operation effectively modifies this Set
         * so that its value is the <em>union</em> of the two Sets.  The behavior
         * of this operation is unspecified if the specified Collection is
         * modified while the operation is in progress.
         *
         * @return true if this Set changed as a result of the call.
         * @exception UnsupportedOperationException addAll is not supported
         *                   by this Set.
         * @exception ClassCastException class of some element of the specified
         *                   Collection prevents it from being added to this Set.
         * @exception IllegalArgumentException some aspect of some element of the
         *                  specified Collection prevents it from being added to this
         *                  Set.
         * @see #add(Object)
         * @since JDK1.2
         */
    boolean addAll(Collection c);

        /**
         * Retains only the elements in this Set that are contained in the
         * specified Collection (optional operation).  In other words, removes
         * from this Set all of its elements that are not contained in the
         * specified Collection.  If the specified Collection is also a Set, this
         * operation effectively modifies this Set so that its value is the
         * <em>intersection</em> of the two Sets.
         *
         * @return true if this Collection changed as a result of the call.
         * @exception UnsupportedOperationException retainAll is not supported
         *                   by this Collection.
         * @see #remove(Object)
         * @since JDK1.2
         */
    boolean retainAll(Collection c);


        /**
         * Removes from this Set all of its elements that are contained in the
         * specified Collection (optional operation).  If the specified
         * Collection is also a Set, this operation effectively modifies this Set
         * so that its value is the <em>asymmetric set difference</em> of the two
         * Sets.
         *
         * @return true if this Set changed as a result of the call.
         * @exception UnsupportedOperationException removeAll is not supported
         *                   by this Collection.
         * @see #remove(Object)
         * @since JDK1.2
         */
    boolean removeAll(Collection c);

        /**
         * Removes all of the elements from this Set (optional operation).  The
         * Set will be empty after this call returns (unless it throws an
         * exception).
         *
         * @exception UnsupportedOperationException clear is not supported
         *                   by this Set.
         * @since JDK1.2
         */
    void clear();


    // Comparison and hashing

        /**
         * Compares the specified Object with this Set for equality.
         * Returns true if the given object is also a Set, the two Sets have the
         * same size, and every member of the given Set is contained in this Set.
         * This ensures that the equals method works properly across different
         * implementations of the Set interface.
         *
         * @param o Object to be compared for equality with this Set.
         * @return true if the specified Object is equal to this Set.
         * @since JDK1.2
         */
    boolean equals(Object o);

        /**
         * Returns the hash code value for this Set.  The hash code of a Set is
         * defined to be the sum of the hashCodes of the elements in the Set,
         * where the hashcode of a null element is defined to be zero.  This
         * ensures that <code>s1.equals(s2)</code> implies that
         * <code>s1.hashCode()==s2.hashCode()</code> for any two Sets
         * <code>s1</code> and <code>s2</code>, as required by the general
         * contract of Object.hashCode.
         *
         * @see Object#hashCode()
         * @see Object#equals(Object)
         * @see Set#equals(Object)
         * @since JDK1.2 
         */
    int hashCode();
}
