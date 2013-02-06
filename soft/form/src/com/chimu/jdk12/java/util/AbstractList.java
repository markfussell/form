/*======================================================================
**
**  File: chimu/jdk12/java/util/AbstractList.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * This class provides a skeletal implementation of the List interface to
 * minimize the effort required to implement this interface backed by a
 * "random access" data store (such as an array).  For sequential access
 * data (such as a linked list), AbstractSequentialList should be used in
 * preference to this Class.
 * <p>
 * To implement an unmodifiable List, the programmer needs only to extend
 * this class and provide implementations for the get(int index) and size()
 * methods.
 * <p>
 * To implement a modifiable List, the programmer must additionally override
 * the set(int index, Object element) method (which otherwise throws an
 * UnsupportedOperationException.  If the List is variable-size the
 * programmer must additionally override the add(int index, Object element)
 * and remove(int index) methods.
 * <p>
 * The programmer should generally provide a void (no argument) and
 * Collection constructor, as per the recommendation in the Collection
 * interface specification.
 * <p>
 * Unlike the other abstract Collection implementations, the programmer does
 * <i>not</i> have to provide an Iterator implementation; the iterator and
 * listIterator are implemented by this class, on top the "random access"
 * methods: get(int index), set(int index, Object element),
 * set(int index, Object element), add(int index, Object element) and
 * remove(int index).
 * <p>
 * The documentation for each non-abstract methods in this class describes its
 * implementation in detail.  Each of these methods may be overridden if the
 * Collection being implemented admits a more efficient implementation.
 *
 * @author  Josh Bloch
 * @version 1.11 10/08/97
 * @see Collection
 * @see List
 * @see AbstractSequentialList
 * @see AbstractCollection
 * @since JDK1.2
 */

public abstract class AbstractList extends AbstractCollection implements List {
    /**
     * Appends the specified element to the end of this List (optional
     * operation).
     * <p>
     * This implementation calls <code>add(size(), o)</code>.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException unless add(int, Object) is overridden.
     *
     * @param o element to be appended to this List.
     * @return true (as per the general contract of Collection.add).
     * @exception UnsupportedOperationException add is not supported
     *                   by this Set.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this Set.
     * @exception IllegalArgumentException some aspect of this element prevents
     *                  it from being added to this Collection.
     * @since JDK1.2
     */
    public boolean add(Object o) {
        add(size(), o);
        return true;
    }

    /**
     * Returns the element at the specified position in this List.
     *
     * @param index index of element to return.
     * @exception ArrayIndexOutOfBoundsException index is out of range (index
     *                   &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    abstract public Object get(int index);

    /**
     * Replaces the element at the specified position in this List with the
     * specified element (optional operation).
     * <p>
     * This implementation always throws an UnsupportedOperationException.
     *
     * @param index index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @exception UnsupportedOperationException set is not supported
     *                  by this List.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this List.
     * @exception IllegalArgumentException some aspect of the specified
     *                  element prevents it from being added to this List.
     * @exception ArrayIndexOutOfBoundsException index out of range
     *                  (index &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    public Object set(int index, Object element) {
        throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();
    }

    /**
     * Inserts the specified element at the specified position in this List
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     * <p>
     * This implementation always throws an UnsupportedOperationException.
     *
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @exception UnsupportedOperationException add is not supported
     *                  by this List.
     * @exception ClassCastException class of the specified element
     *                   prevents it from being added to this List.
     * @exception IllegalArgumentException some aspect of the specified
     *                  element prevents it from being added to this List.
     * @exception ArrayIndexOutOfBoundsException index is out of range
     *                  (index &lt; 0 || index &gt; size()).
     * @since JDK1.2
     */
    public void add(int index, Object element) {
        throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();
    }

    /**
     * Removes the element at the specified position in this List (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * List.
     * <p>
     * This implementation always throws an UnsupportedOperationException.
     *
     * @param index the index of the element to removed.
     * @return the element previously at the specified position.
     * @exception UnsupportedOperationException remove is not supported
     *                  by this List.
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *                   &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    public Object remove(int index) {
        throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();
    }


    // Search Operations

    /**
     * Returns the index in this List of the first occurence of the specified
     * element, or -1 if the List does not contain this element.
     * More formally, returns the lowest index i such that
     * <code>(o==null ? get(i)==null : o.equals(get(i)))</code>,
     * or -1 if there is no such index.
     * <p>
     * This implementation returns <code>indexOf(o, 0)</code>.
     *
     * @param o element to search for.
     * @since JDK1.2
     */
    public int indexOf(Object o) {
        return indexOf(o, 0);
    }

    /**
     * Returns the index in this List of the first occurence of the specified
     * element at or after the specified position, or -1 if the element is
     * not found.  More formally, returns the lowest index
     * i &gt;= index such that <code>(o==null ? get(i)==null :
     * o.equals(get(i)))</code>, or -1 if there is no such index.
     * <p>
     * This implementation first gets a ListIterator pointing to the
     * indexed element (with listIterator(index)).  Then, it iterates over
     * the remainder of the List until the specified element is found or the
     * end of the List is reached.
     *
     * @param o element to search for.
     * @param index initial position to search for the specified element.
     * @exception ArrayIndexOutOfBoundsException index out of range
     *                   (index &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    public int indexOf(Object o, int index) {
        ListIterator e = listIterator(index);
        if (o==null) {
            while (e.hasNext())
                if (e.next()==null)
                    return e.previousIndex();
        } else {
            while (e.hasNext())
                if (o.equals(e.next()))
                    return e.previousIndex();
        }
        return -1;
    }

    /**
     * Returns the index in this List of the last occurence of the specified
     * element, or -1 if the List does not contain this element.
     * More formally, returns the highest index i such that
     * <code>(o==null ? get(i)==null : o.equals(get(i)))</code>,
     * or -1 if there is no such index.
     * <p>
     * This implementation returns <code>lastIndexOf(o, size()-1)</code>.
     *
     * @param o element to search for.
     * @since JDK1.2
     */
    public int lastIndexOf(Object o) {
        return lastIndexOf(o, size()-1);
    }

    /**
     * Returns the index in this List of the last occurence of the specified
     * element at or before the specified position, or -1 if the List does not
     * contain this element.  More formally, returns the highest index
     * i &lt;= index such that <code>(o==null ? get(i)==null :
     * o.equals(get(i)))</code>, or -1 if there is no such index.
     * <p>
     * This implementation first gets a ListIterator pointing to the
     * element after the indexed element (with listIterator(index)).
     * Then, it iterates backwards over the list until the specified element
     * is found, or the beginning of the list is reached.
     *
     * @param o element to search for.
     * @param index initial position to search for the specified element.
     * @exception ArrayIndexOutOfBoundsException index out of range
     *                   (index &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    public int lastIndexOf(Object o, int index) {
        ListIterator e = listIterator(index+1);
        if (o==null) {
            while (e.hasPrevious())
                if (e.previous()==null)
                    return e.nextIndex();
        } else {
            while (e.hasPrevious())
                if (o.equals(e.previous()))
                    return e.nextIndex();
        }
        return -1;
    }


    // Range Operations

    /**
     * Removes from this List all of the elements whose index is between
     * fromIndex, inclusive and toIndex, exclusive (optional operation).
     * Shifts any succeeding elements to the left (reduces their index).
     * This call shortens the List by (toIndex - fromIndex) elements.  (If
     * toIndex==fromIndex, this operation has no effect.)
     * <p>
     * This implementation first checks to see that the indices are in
     * range.  Then, it calls remove(fromIndex) repeatedly,
     * toIndex-fromIndex times.  Many implementations will override
     * this method for efficiency.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException unless remove(int) is overridden.
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
    public void removeRange(int fromIndex, int toIndex) {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex > toIndex");
        if (toIndex > size())
            throw(new ArrayIndexOutOfBoundsException(toIndex));

        for (int i = fromIndex; i<toIndex; i++)
            remove(fromIndex);
    }

    /**
     * Inserts all of the elements in in the specified Collection into this
     * List at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in the List in the order that they are returned by the
     * specified Collection's iterator.  The behavior of this operation is
     * unspecified if the specified Collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * Collection is this List, and it's nonempty.)
     * <p>
     * This implementation gets an Iterator over the specified Collection and
     * iterates over it, inserting the elements obtained from the Iterator into
     * this List at the appropriate position, one at a time, using add(int,
     * Object).  Many implementations will override this method for efficiency.
     * <p>
     * Note that this implementation will throw an
     * UnsupportedOperationException unless add(int, Object) is overridden.
     *
     * @return true if this List changed as a result of the call.
     * @param index index at which to insert first element from the
     *                    specified collection.
     * @param c elements to be inserted into this List.
     * @exception UnsupportedOperationException addAll is not supported
     *                  by this List.
     * @exception ClassCastException class of one of elements of the specified
     *                   Collection prevents it from being added to this List.
     * @exception IllegalArgumentException some aspect of one of elements of
     *                  the specified Collection prevents it from being added to
     *                  this List.
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *                  &lt; 0 || index &gt; size()).
     * @since JDK1.2
     */
    public boolean addAll(int index, Collection c) {
        boolean modified = false;
        Iterator e = c.iterator();
        while (e.hasNext()) {
            add(index++, e.next());
            modified = true;
        }
        return modified;
    }


    // Iterators

        /**
         * Returns an Iterator over the elements in this List in proper sequence.
         * <p>
         * This implementation returns a straightforward implementation of the
         * Iterator interface, relying on the the backing List's size(),
         * get(int), and remove(int) methods.
         * <p>
         * Note that the Iterator returned by this method will throw an
         * UnsupportedOperationException in response to its remove
         * method unless the List's remove(int) method is overridden.
         * <p>
         * This implementation can be made to throw runtime exceptions in the
         * face of concurrent modification, as described in the specification for
         * the (protected) modCount field.
         *
         * @see #modCount
         * @since JDK1.2
         */
    public Iterator iterator() {
        return new Itr();
    }

        /**
         * Returns an Iterator of the elements in this List (in proper sequence).
         *
         * This implementation returns <code>listIterator(0)</code>.
         *
         * @see #listIterator(int)
         * @since JDK1.2
         */
    public ListIterator listIterator() {
        return listIterator(0);
    }

        /**
         * Returns a ListIterator of the elements in this List (in proper
         * sequence), starting at the specified position in the List.  The
         * specified index indicates the first element that would be returned by
         * an initial call to nextElement.  An initial call to previousElement
         * would return the element with the specified index minus one.
         * <p>
         * This implementation returns a straightforward implementation of the
         * ListIterator interface that extends the implementation of the Iterator
         * interface returned by iterator().  The ListIterator implementation
         * relies on the the backing List's get(int), set(int, Object),
         * add(int, Object) and remove(int) methods.
         * <p>
         * Note that the ListIterator returned by this implementation will throw
         * an UnsupportedOperationException in response to its remove, set and
         * add methods unless the List's remove(int), set(int, Object),
         * and add(int, Object) methods are overridden (respectively).
         * <p>
         * This implementation can be made to throw runtime exceptions in the
         * face of concurrent modification, as described in the specification for
         * the (protected) modCount field.
         *
         * @param index index of first element to be returned from the
         *                    ListIterator (by a call to getNext).
         * @exception ArrayIndexOutOfBoundsException index is out of range
         *                  (index &lt; 0 || index &gt; size()).
         * @see #modCount
         * @since JDK1.2
         */
    public ListIterator listIterator(final int index) {
        if (index<0 || index>size())
            throw new ArrayIndexOutOfBoundsException(index);

        return new ListItr(index);
    }

    private class Itr implements Iterator {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        private int cursor = 0;

        /**
         * Index of element returned by most recent call to next or
         * previous.  Reset to -1 if this element is deleted by a call
         * to remove.
         */
        private int lastRet = -1;

        /**
         * The modCount value that the iterator believes that the backing
         * List should have.  If this expectation is violated, the iterator
         * has detected concurrent modification.
         */
        private int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size();
        }

        public Object next() {
            try {
                Object next = get(cursor);
                checkForComodification();
                lastRet = cursor++;
                return next;
            } catch(ArrayIndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (lastRet == -1)
                throw new NoSuchElementException();

            try {
                List.this.remove(lastRet);
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;

                int newModCount = modCount;
                if (newModCount - expectedModCount > 1)
                    throw new ConcurrentModificationException();
                expectedModCount = newModCount;
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private class ListItr extends Itr implements ListIterator {
        ListItr(int index) {
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public Object previous() {
            try {
                Object previous = get(--cursor);
                checkForComodification();
                lastRet = cursor;
                return previous;
            } catch(ArrayIndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        public void set(Object o) {
            if (lastRet == -1)
                throw new NoSuchElementException();

            try {
                List.this.set(lastRet, o);

                int newModCount = modCount;
                if (newModCount - expectedModCount > 1)
                    throw new ConcurrentModificationException();
                expectedModCount = newModCount;
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(Object o) {
            try {
                List.this.add(cursor, o);
                if (lastRet >= cursor)
                    lastRet++;

                int newModCount = modCount;
                if (newModCount - expectedModCount > 1)
                    throw new ConcurrentModificationException();
                expectedModCount = newModCount;
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }
    }


    // Comparison and hashing

        /**
         * Compares the specified Object with this List for equality.  Returns true
         * if and only if the specified Object is also a List, both Lists have the
         * same size, and all corresponding pairs of elements in the two Lists are
         * <em>equal</em>.  (Two elements <code>e1</code> and <code>e2</code> are
         * <em>equal</em> if <code>(e1==null ? e2==null : e1.equals(e2))</code>.)
         * In other words, two Lists are defined to be equal if they contain the
         * same elements in the same order.
         * <p>
         * This implementation first checks if the specified object is this
         * List. If so, it returns true; if not, it checks if the specified
         * object is a List. If not, it returns false; if so, it iterates over
         * both lists, comparing corresponding pairs of elements.  If any
         * comparison returns false, this method returns false.  If either
         * Iterator runs out of elements before before the other it returns false
         * (as the Lists are of unequal length); otherwise it returns true when
         * the iterations complete.
         *
         * @param o the Object to be compared for equality with this List.
         * @return true if the specified Object is equal to this List.
         * @since JDK1.2
         */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof List))
            return false;

        ListIterator e1 = listIterator();
        ListIterator e2 = ((List) o).listIterator();
        while(e1.hasNext() && e2.hasNext()) {
            Object o1 = e1.next();
            Object o2 = e2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }

    /**
     * Returns the hash code value for this List.
     * <p>
     * This implementation uses exactly the code that is used to define
     * the List hash function in the documentation for List.hashCode.
     *
     * @since JDK1.2
     */
    public int hashCode() {
        int hashCode = 0;
        Iterator i = iterator();
             while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

        /**
         * The number of times this List has been <em>structurally modified</em>.
         * Structural modifications are those that change the size of the
         * List, or otherwise perturb it in such a fashion that iterations in
         * progress may yield incorrect results.
         * <p>
         * This field is used by the the Iterator and ListIterator implementation
         * returned by the iterator and listIterator methods.  If the value of this
         * field changes unexpectedly, the Iterator (or ListIterator) will
         * throw a ConcurrentModificationDetected exception in response to the
         * next, remove, previous, set or add operations.  This provides
         * <em>fail-fast</em> behavior, rather than non-deterministic behavior
         * in the face of concurrent modification during iteration.
         * <p>
         * <strong>Use of this field by subclasses is optional.</strong> If a
         * subclass wishses to provide fail-fast Iterators (and ListIterators),
         * then it merely has to increment this field in its add(int, Object) and
         * remove(int) methods (and any other methods that it overrides that
         * result in structural modifications to the List).  If an implementation
         * does not wish to provide fail-fast Iterators, this field may be
         * ignored.
         *
         * @since JDK1.2
         */
    protected transient int modCount = 0;
}
