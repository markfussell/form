/*======================================================================
**
**  File: chimu/jdk12/java/util/Collection.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * The root interface in the <i>collection hierarchy</i>.  A Collection
 * represents a group of Objects, known as its <i>elements</i>.  <em>Bags</em>
 * (unordered collections that may contain duplicate elements) implement this
 * interface directly, rather than implementing one of its "subinterfaces",
 * List or Set.
 * <p>
 * The interfaces that comprise the collection hierarchy are designed to allow
 * manipulation of collections in an implementation-independent fashion.  This
 * allows for interoperability among unrelated APIs that take collections as
 * input or return them as output.  It reduces the effort in learning new APIs
 * that would otherwise have their own collection interfaces, reduces the
 * effort in designing such APIs, and fosters software reuse.
 * <p>
 * The collection hierarchy consists of four interfaces, the <i>core
 * collection intefaces</i>.  Two of these interfaces, Set and List, are
 * children of the Collection interface; they add further constraints on the
 * contracts imposed by the methods in this interface, as well as adding new
 * methods.  The final core collection interface, Map, is not a child of
 * Collection, as it represents a mapping rather than a true collection.
 * <p>
 * All of the methods in Collection and the other core interfaces that modify
 * the collection are labeled <i>optional</i>.  Some implementations may not
 * perform one or more of these operations, throwing a runtime exception,n
 * UnsupportedOperationException, if they are attempted.  Implementations
 * should specify in their documentation which optional operations they
 * support.  Several terms are introduced to aid in this specification:
 * <ul>
 *   <li>Collections that do not support any modification operations (such
 *   as add, remove, clear) are referred to as <i>unmodifiable</i>.
 *   Collections that are not unmodifiable are referred to <i>modifiable.</i>
 *
 *   <li>Collections that additionally guarantee that no change in the
 *   Collection will ever be observable via "query" operations (such as
 *   iterator, size, contains) are referred to as <i>immutable</i>.
 *   Collections that are not immutable are referred to as <i>mutable</i>.
 *
 *   <li>Lists that guarantee that their size will remain constant even though
 *   the elements may change are referred to as <i>fixed-size</i>.  Lists that
 *   are not fixed-size are referred to as <i>variable-size</i>.
 * </ul><p>
 * Some implementations may restrict what elements (or in the case of Maps,
 * keys and values) may be stored.  Possible restrictions include requiring
 * elements to:<ul>
 *      <li>be of a particular type.
 *      <li>be comparable to other elements in the collection.
 *      <li>be non-null.
 *      <li>obey some arbitrary predicate.
 * </ul>Attempting to add an element that violates an implementation's
 * restrictions will result in a runtime exception, typically a
 * ClassCastException, an IllegalArgumentException or a NullPointerException.
 * Attempting to remove or test for such an element may result in such an
 * exception, though some "restricted collections" may permit this usage.
 * <p>
 * All general-purpose Collection implementation classes should provide two
 * "standard" constructors:  a void (no arguments) constructor, which creates
 * an empty Collection, and a constructor with a single argument of type
 * Collection, which creates a new Collection with the same elements as its
 * argument.  In effect, the latter constructor allows the user to copy any
 * Collection, producing an equivalent Collection of the desired
 * implementation type.  Similarly, all general-purpose Map implementations
 * should provide a void (no arguments) constructor and a constructor that
 * takes a single argument of type Map.  There is no way to enforce these
 * recommendations (as interfaces cannot contain constructors) but all of the
 * general-purpose Collection and Map implementations in the JDK comply.
 * <p>
 * Collection implementation classes typically have names of the form
 * &lt;<em>Implementation</em>&gt;&lt;<em>Interface</em>&gt;.
 * Set implementations in the JDK include HashSet and ArraySet.  List
 * implementations include ArrayList, LinkedList and Vector.  Map
 * implementations include HashMap, TreeMap, ArrayMap, and Hashtable.
 * (The JDK contains no class that implements Collection directly.)
 * All of the JDK implementations with "new style"
 * (&lt;<em>Implementation</em>&gt;&lt;<em>Interface</em>&gt;) names
 * are unsynchronized.  The Collections class contains static factories
 * that may be used to add synchronization to any unsynchronized
 * collection.
 * <p>
 * The AbstractCollection, AbstractSet, AbstractList and
 * AbstractSequentialList classes provide skeletal implementations of the
 * core collection interfaces, to minimize the effort required to implement
 * them.  The Iterator, ListIterator, Comparable and Comparator interfaces
 * provide the required "infrastructure." 
 *
 * @author  Josh Bloch
 * @version 1.18 10/13/97
 * @see            Set
 * @see            List
 * @see            Map
 * @see            ArrayList
 * @see            LinkedList
 * @see            Vector
 * @see            HashSet
 * @see            ArraySet
 * @see            HashMap
 * @see            TreeMap
 * @see            ArrayMap
 * @see            Hashtable
 * @see     Collections
 * @see            AbstractCollection
 * @see            AbstractSet
 * @see            AbstractList
 * @see            AbstractSequentialList
 * @see            AbstractMap
 * @see            Iterator
 * @see            ListIterator
 * @see            Comparable
 * @see            Comparator
 * @see            UnsupportedOperationException
 * @see            ConcurrentModificationException
 * @since JDK1.2
 */

public interface Collection {
    // Query Operations

    /**
     * Returns the number of elements in this Collection.
     *
     * @since JDK1.2
     */
    int size();

    /**
     * Returns true if this Collection contains no elements.
     *
     * @since JDK1.2
     */
    boolean isEmpty();

    /**
     * Returns true if this Collection contains the specified element.  More
     * formally, returns true if and only if this Collection contains at least
     * one element <code>e</code> such that <code>(o==null ? e==null :
     * o.equals(e))</code>.
     *
     * @param o element whose presence in this Collection is to be tested.
     * @since JDK1.2
     */
    boolean contains(Object o);

    /**
     * Returns an Iterator over the elements in this Collection.  There are
     * no guarantees concerning the order in which the elements are returned
     * (unless this Collection is an instance of some class that provides a
     * guarantee).
     *
     * @since JDK1.2
     */
    Iterator iterator();

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


    // Modification Operations

    /**
     * Ensures that this Collection contains the specified element (optional
     * operation).  Returns true if the Collection changed as a result of the
     * call.  (Returns false if this Collection does not permit duplicates and
     * already contains the specified element.)  Collections that support this
     * operation may place limitations on what elements may be added to the
     * Collection.  In particular, some Collections will refuse to add null
     * elements, and others will impose restrictions on the type of elements
     * that may be added.  Collection classes should clearly specify in their
     * documentation any restrictions on what elements may be added.
     *
     * @param o element whose presence in this Collection is to be ensured.
     * @return true if the Collection changed as a result of the call.
     * @exception UnsupportedOperationException add is not supported by this
     *                  Collection.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this Collection.
     * @exception IllegalArgumentException some aspect of this element prevents
     *                  it from being added to this Collection.
     * @since JDK1.2
     */
    boolean add(Object o);

    /**
     * Removes a single instance of the specified element from this Collection,
     * if it is present (optional operation).  More formally, removes an
     * element <code>e</code> such that <code>(o==null ? e==null :
     * o.equals(e))</code>, if the Collection contains one or more such
     * elements.  Returns true if the Collection contained the specified
     * element (or equivalently, if the Collection changed as a result of the
     * call).
     *
     * @param o element to be removed from this Collection, if present.
     * @return true if the Collection changed as a result of the call.
     * @exception UnsupportedOperationException remove is not supported
     *                  by this Collection.
     * @since JDK1.2
     */
    boolean remove(Object o);


    // Bulk Operations

    /**
     * Returns true if this Collection contains all of the elements in the
     * specified Collection.
     *
     * @see #contains(Object)
     * @since JDK1.2
     */
    boolean containsAll(Collection c);

    /**
     * Adds all of the elements in the specified Collection to this Collection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified Collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the the
     * specified Collection is this Collection, and this Collection is
     * nonempty.) 
     *
     * @param c elements to be inserted into this Collection.
     * @return true if this Collection changed as a result of the call.
     * @exception UnsupportedOperationException addAll is not supported
     *                   by this Collection.
     * @exception ClassCastException class of an element of the specified
     *                   Collection prevents it from being added to this Collection.
     * @exception IllegalArgumentException some aspect of an element of
     *                  the specified Collection prevents it from being added to
     *                  this Collection. 
     * @see #add(Object)
     * @since JDK1.2
     */
    boolean addAll(Collection c);

    /**
     * Removes from this Collection all of its elements that are contained in
     * the specified Collection (optional operation).  After this call returns,
     * this Collection will contains no elements in common with the specified
     * Collection.
     *
     * @param c elements to be removed from this Collection.
     * @return true if this Collection changed as a result of the call.
     * @exception UnsupportedOperationException removeAll is not supported
     *                   by this Collection.
     * @see #remove(Object)
     * @see #contains(Object)
     * @since JDK1.2
     */
    boolean removeAll(Collection c);

    /**
     * Retains only the elements in this Collection that are contained in the
     * specified Collection (optional operation).  In other words, removes from
     * this Collection all of its elements that are not contained in the
     * specified Collection. 
     *
     * @param c elements to be retained in this Collection.
     * @return true if this Collection changed as a result of the call.
     * @exception UnsupportedOperationException retainAll is not supported
     *                   by this Collection.
     * @see #remove(Object)
     * @see #contains(Object)
     * @since JDK1.2
     */
    boolean retainAll(Collection c);

    /**
     * Removes all of the elements from this Collection (optional operation).
     * The Collection will be empty after this call returns (unless it throws
     * an exception).
     *
     * @exception UnsupportedOperationException clear is not supported
     *                  by this Collection.
     * @since JDK1.2
     */
    void clear();


    // Comparison and hashing

    /**
     * Compares the specified Object with this Collection for equality.
     * <p>
     * While Collection adds no stipulations to the general contract for
     * Object.equals, programmers who implement Collection "directly" (in
     * other words, create a class that is a Collection but is not a Set or a
     * List) must exercise care if they choose to override Object.equals.  It
     * is not necessary to do so, and the simplest course of action is to rely
     * on Object's implementation, but the implementer may wish to implement
     * a "value comparison" in place of the default "reference comparison."
     * (Lists and Sets mandate such value comparisons.)
     * <p>
     * The general contract for Object.equals states that equals
     * must be reflexive (in other words, <code>a.equals(b)</code> if and only
     * if <code>b.equals(a)</code>).  The contracts for List.equals and
     * Set.equals state that Lists are only equal to other Lists, and
     * Sets to other Sets.  Thus, a custom equals method for a Collection
     * that is neither a List nor a Set must return false when this
     * Collection is compared to any List or Set.
     *
     * @param o Object to be compared for equality with this Collection.
     * @return true if the specified Object is equal to this Collection.
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     * @see List#equals(Object)
     * @since JDK1.2
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this Collection.  While Collection
     * adds no stipulations to the general contract for Object.hashCode,
     * programmers should take note that any class that overrides
     * Object.equals must also override Object.hashCode, in order to satisfy
     * the general contract for Object.hashCode.  In particular,
     * <code>c1.equals(c2)</code> implies that
     * <code>c1.hashCode()==c2.hashCode()</code>.
     *
     * @see Object#hashCode()
     * @see Object#equals(Object)
     * @since JDK1.2
     */
    int hashCode();
}
