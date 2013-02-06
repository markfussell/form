/*======================================================================
**
**  File: chimu/jdk12/java/util/AbstractCollection.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * This class provides a skeletal implementation of the Collection
 * interface, to minimize the effort required to implement this interface.
 * <p>
 * To implement an unmodifiable Collection, the programmer needs only to
 * extend this class and provide implementations for the iterator and size
 * methods.  (The Iterator returned by the iterator method must implement
 * hasNext and next.)
 * <p>
 * To implement a modifiable Collection, the programmer must additionally
 * override this class's add method (which otherwise throws an
 * UnsupportedOperationException), and the Iterator returned by the
 * iterator method must additionally implement its remove method.
 * <p>
 * The programmer should generally provide a void (no argument) and
 * Collection constructor, as per the recommendation in the Collection
 * interface specification.
 * <p>
 * The documentation for each non-abstract methods in this class describes its
 * implementation in detail.  Each of these methods may be overridden if
 * the Collection being implemented admits a more efficient implementation.
 *
 * @author  Josh Bloch
 * @version 1.7 10/30/97
 * @see Collection
 * @since JDK1.2
 */

public abstract class AbstractCollection implements Collection {
    // Query Operations

    /**
     * Returns an Iterator over the elements contained in this Collection.
     *
     * @since JDK1.2
     */
    public abstract Iterator iterator();

    /**
     * Returns the number of elements in this Collection.
     *
     * @since JDK1.2
     */
    public abstract int size();

    /**
     * Returns true if this Collection contains no elements.
     *
     * This implementation returns <code>size() == 0</code>.
     *
     * @since JDK1.2
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this Collection contains the specified element.  More
     * formally, returns true if and only if this Collection contains at least
     * one element <code>e</code> such that <code>(o==null ? e==null :
     * o.equals(e))</code>.
     * <p>
     * This implementation iterates over the elements in the Collection,
     * checking each element in turn for equality with o.
     *
     * @since JDK1.2
     */
    public boolean contains(Object o) {
        Iterator e = iterator();
        if (o==null) {
            while (e.hasNext())
                if (e.next()==null)
                    return true;
        } else {
            while (e.hasNext())
                if (o.equals(e.next()))
                    return true;
        }
        return false;
    }

    /**
     * Returns an array containing all of the elements in this Collection.  If
     * the Collection makes any guarantees as to what order its elements are
     * returned by its Iterator, this method must return the elements in the
     * same order.  The returned array will be "safe" in that no references to
     * it are maintained by the Collection.  (In other words, this method must
     * allocate a new array even if the Collection is backed by an Array).
     * The caller is thus free to modify the returned array.
     * <p>
     * This implementation allocates the array to be returned, and iterates
     * over the elements in the Collection, storing each object reference in
     * the next consecutive element of the array, starting with element 0.
     *
     * @since JDK1.2
     */
    public Object[] toArray() {
        Object[] result = new Object[size()];
        Iterator e = iterator();
        for (int i=0; e.hasNext(); i++)
            result[i] = e.next();
        return result;
    }

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
     * <p>
     * This implementation always throws an UnsupportedOperationException.
     *
     * @param o element whose presence in this Collection is to be ensured.
     * @return true if the Collection changed as a result of the call.
     * @exception UnsupportedOperationException add is not supported by this
     *                  Collection.
     * @exception NullPointerException this Collection does not permit null
     *                   elements, and the specified element is null.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this Collection.
     * @exception IllegalArgumentException some aspect of this element prevents
     *                  it from being added to this Collection.
     * @since JDK1.2
     */
    public boolean add(Object o) {
        throw new com.chimu.jdk12.java.lang.UnsupportedOperationException(); //UnsupportedOperationException();
    }

        /**
         * Removes a single instance of the specified element from this Collection,
         * if it is present (optional operation).  More formally, removes an
         * element <code>e</code> such that <code>(o==null ? e==null :
         * o.equals(e))</code>, if the Collection contains one or more such
         * elements.  Returns true if the Collection contained the specified
         * element (or equivalently, if the Collection changed as a result of the
         * call).
         * <p>
         * This implementation iterates over the Collection looking for the
         * specified element.  If it finds the element, it removes the element
         * from the Collection using the iterator's remove method.
         * <p>
         * Note that this implementation will throw an
         * UnsupportedOperationException if the Iterator returned by this
         * Collection's iterator method does not implement the remove method.
         *
         * @param o element to be removed from this Collection, if present.
         * @return true if the Collection contained the specified element.
         * @exception UnsupportedOperationException remove is not supported
         *                   by this Collection.
         * @since JDK1.2
         */
    public boolean remove(Object o) {
        Iterator e = iterator();
        if (o==null) {
            while (e.hasNext()) {
                if (e.next()==null) {
                    e.remove();
                    return true;
                }
            }
        } else {
            while (e.hasNext()) {
                if (o.equals(e.next())) {
                    e.remove();
                    return true;
                }
            }
        }
        return false;
    }


    // Bulk Operations

        /**
         * Returns true if this Collection contains all of the elements in the
         * specified Collection.
         * <p>
         * This implementation iterates over the specified Collection, checking
         * each element returned by the Iterator in turn to see if it's
         * contained in this Collection.  If all elements are so contained
         * true is returned, otherwise false.
         *
         * @see #contains(Object)
         * @since JDK1.2
         */
    public boolean containsAll(Collection c) {
        Iterator e = c.iterator();
        while (e.hasNext())
            if(!contains(e.next()))
                return false;

        return true;
    }

    /**
     * Adds all of the elements in the specified Collection to this Collection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified Collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the the
     * specified Collection is this Collection, and this Collection is
     * nonempty.)
     * <p>
     * This implementation iterates over the specified Collection,
     * and adds each object returned by the Iterator to this Collection,
     * in turn.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException unless add is overridden.
     *
     * @exception UnsupportedOperationException addAll is not supported
     *                   by this Collection.
     * @see #add(Object)
     * @since JDK1.2
     */
    public boolean addAll(Collection c) {
        boolean modified = false;
        Iterator e = c.iterator();
        while (e.hasNext()) {
            if(add(e.next()))
                modified = true;
        }
        return modified;
    }

    /**
     * Removes from this Collection all of its elements that are contained in
     * the specified Collection (optional operation).
     * <p>
     * This implementation iterates over this Collection, checking each
     * element returned by the Iterator in turn to see if it's contained
     * in the specified Collection.  If it's so contained, it's removed from
     * this Collection with the Iterator's remove method.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException if the Iterator returned by
     * iterator does not implement the remove method.
     *
     * @return true if this Collection changed as a result of the call.
     * @exception UnsupportedOperationException removeAll is not supported
     *                   by this Collection.
     * @see #remove(Object)
     * @see #contains(Object)
     * @since JDK1.2
     */
    public boolean removeAll(Collection c) {
        boolean modified = false;
        Iterator e = iterator();
        while (e.hasNext()) {
            if(c.contains(e.next())) {
                e.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains only the elements in this Collection that are contained in the
     * specified Collection (optional operation).  In other words, removes from
     * this Collection all of its elements that are not contained in the
     * specified Collection.
     * <p>
     * This implementation iterates over this Collection, checking each
     * element returned by the Iterator in turn to see if it's contained
     * in the specified Collection.  If it's not so contained, it's removed
     * from this Collection with the Iterator's remove method.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException if the Iterator returned by
     * iterator does not implement the remove method.
     *
     * @return true if this Collection changed as a result of the call.
     * @exception UnsupportedOperationException retainAll is not supported
     *                   by this Collection.
     * @see #remove(Object)
     * @see #contains(Object)
     * @since JDK1.2
     */
    public boolean retainAll(Collection c) {
        boolean modified = false;
        Iterator e = iterator();
        while (e.hasNext()) {
            if(!c.contains(e.next())) {
                e.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all of the elements from this Collection (optional operation).
     * The Collection will be empty after this call returns (unless it throws
     * an exception).
     * <p>
     * This implementation iterates over this Collection, removing each
     * element using the Iterator.remove operation.
     * Most implementations will probably choose to override this method
     * for efficiency.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException if the Iterator returned by this
     * Collection's iterator method does not implement the remove method.
     *
     * @exception UnsupportedOperationException remove is not supported
     *                   by this Collection.
     * @since JDK1.2
     */
    public void clear() {
        Iterator e = iterator();
        while (e.hasNext()) {
            e.next();
            e.remove();
        }
    }


    //  String conversion

    /**
     * Returns a string representation of this Collection, containing
     * the String representation of each element.
     *
     * @since JDK1.2
     */
    public String toString() {
        StringBuffer buf = new StringBuffer();
        Iterator e = iterator();
        buf.append("[");
        int maxIndex = size() - 1;
        for (int i = 0; i <= maxIndex; i++) {
            buf.append(String.valueOf(e.next()));
            if (i < maxIndex)
                buf.append(", ");
        }
        buf.append("]");
        return buf.toString();
    }
}
