/*======================================================================
**
**  File: chimu/jdk12/java/util/ArrayList.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * Resizable-array implementation of the List interface.  Implements all
 * optional List operations, and permits all elements, including null.  In
 * addition to implementing the List interface, ArrayList provides methods
 * to manipulate the size of the array that is used internally to store the
 * List.  (Arrayist is roughly equivalent to Vector, except that it is
 * unsynchronized.)
 * <p>
 * The size, isEmpty, get, set, clear, iterator, and listIterator
 * operations run in constant time.  The add() operation runs in constant
 * time unless it causes the ArrayList to exceed its capacity, in which case
 * it runs in linear time.  All of the other operations run in linear time
 * (roughly speaking).  The constant factor is low compared to that for
 * LinkedList.
 * <p>
 * Each ArrayList has a <em>capacity</em> and a <em>capacityIncrement</em>.
 * The <code>capacity</code> is the size of the array used to store the
 * elements in the List. It is always at least as large as the List size; it
 * is usually larger because as components are added to the ArrayList, the
 * ArrayList's storage increases in chunks the size of its
 * capacityIncrement.  (The capacityIncrement does not change over the
 * lifetime of the ArrayList.)  An application can increase the capacity of
 * an ArrayList before inserting a large number of components; this reduces
 * the amount of incremental reallocation.
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access an ArrayList concurrently, and at least one of
 * the threads modifies the ArrayList structurally, it <em>must</em> be
 * synchronized externally.  (A structural modification is any operation
 * that adds or deletes one or more elements, or explicitly resizes the
 * backing array; merely setting the value of an element is not a structural
 * modification.)  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the ArrayList.  If no such object
 * exists, the ArrayList should be "wrapped" using the
 * Collections.synchronizedSet method.  This is best done at creation time,
 * to prevent accidental unsynchronized access to the ArrayList:
 * <pre>
 *        List list = Collections.synchronizedList(new ArrayList(...));
 * </pre>
 * <p>
 * The Iterators returned by ArrayList's iterator and listIterator
 * methods are <em>fail-fast</em>: if the ArrayList is structurally modified
 * at any time after the Iterator is created, in any way except through the
 * Iterator's own remove or add methods, the Iterator will throw a
 * ConcurrentModificationException.  Thus, in the face of concurrent
 * modification, the Iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the future.
 *
 * @author  Josh Bloch
 * @version 1.5 10/28/97
 * @see            Collection
 * @see            List
 * @see            LinkedList
 * @see            Vector
 * @see            Collections.synchronizedList
 * @since JDK1.2
 */

public class ArrayList extends AbstractList implements List, Cloneable,
                                                    java.io.Serializable {
    /**
     * The array buffer into which the components of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
    private Object elementData[];

    /**
     * The number of valid components in the ArrayList. 
     */
    private int elementCount;

    /**
     * The amount by which the capacity of the ArrayList is automatically 
     * incremented when its size becomes greater than its capacity. If 
     * the capacity is <code>0</code>, the capacity of the ArrayList is 
     * doubled each time it needs to grow. 
     */
    private int capacityIncrement;

    /**
     * Constructs an empty ArrayList with the specified initial capacity and
     * capacity increment. 
     *
     * @param   initialCapacity     the initial capacity of the ArrayList.
     * @param   capacityIncrement   the amount by which the capacity is
     *                              increased when the ArrayList overflows.
     * @since   JDK1.2
     */
    public ArrayList(int initialCapacity, int capacityIncrement) {
        super();
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }

    /**
     * Constructs an empty ArrayList with the specified initial capacity.
     *
     * @param   initialCapacity   the initial capacity of the ArrayList.
     * @since   JDK1.2
     */
    public ArrayList(int initialCapacity) {
        this(initialCapacity, 0);
    }

    /**
     * Constructs an empty ArrayList. 
     *
     * @since   JDK1.2
     */
    public ArrayList() {
        this(10);
    }

    /**
     * Constructs an ArrayList containing the elements of the specified
     * Collection, in the order they are returned by the Collection's
     * iterator.  The ArrayList has initial capacity of 110% the size
     * of the specified Collection, and the default capacity increment.
     *
     * @since   JDK1.2
     */
    public ArrayList(Collection c) {
        this((c.size()*110)/100);        // Allow 10% room for growth

        Iterator i = c.iterator();
        while (i.hasNext())
            elementData[elementCount++] = i.next();
    }

    /**
     * Trims the capacity of this ArrayList to be the ArrayList's current 
     * size. An application can use this operation to minimize the 
     * storage of an ArrayList. 
     *
     * @since   JDK1.2
     */
    public void trimToSize() {
        modCount++;
        int oldCapacity = elementData.length;
        if (elementCount < oldCapacity) {
            Object oldData[] = elementData;
            elementData = new Object[elementCount];
            System.arraycopy(oldData, 0, elementData, 0, elementCount);
        }
    }

    /**
     * Increases the capacity of this ArrayList, if necessary, to ensure 
     * that it can hold at least the number of components specified by 
     * the minimum capacity argument. 
     *
     * @param   minCapacity   the desired minimum capacity.
     * @since   JDK1.2
     */
    public void ensureCapacity(int minCapacity) {
        modCount++;
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            Object oldData[] = elementData;
            int newCapacity = (capacityIncrement > 0) ?
                (oldCapacity + capacityIncrement) : (oldCapacity * 2);
                if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elementData = new Object[newCapacity];
            System.arraycopy(oldData, 0, elementData, 0, elementCount);
        }
    }

    /**
     * Returns the number of components in this ArrayList.
     *
     * @return  the number of components in this ArrayList.
     * @since   JDK1.2
     */
    public int size() {
        return elementCount;
    }

    /**
     * Tests if this ArrayList has no components.
     *
     * @return  <code>true</code> if this ArrayList has no components;
     *          <code>false</code> otherwise.
     * @since   JDK1.2
     */
    public boolean isEmpty() {
        return elementCount == 0;
    }

    /**
     * Returns true if this ArrayList contains the specified element.
     *
     * @param o element whose presence in this List is to be tested.
     * @since   JDK1.2
     */
    public boolean contains(Object elem) {
        return indexOf(elem, 0) >= 0;
    }

    /**
     * Searches for the first occurence of the given argument, testing 
     * for equality using the <code>equals</code> method. 
     *
     * @param   elem   an object.
     * @return  the index of the first occurrence of the argument in this
     *          ArrayList; returns <code>-1</code> if the object is not found.
     * @see     Object#equals(Object)
     * @since   JDK1.2
     */
    public int indexOf(Object elem) {
        return indexOf(elem, 0);
    }

    /**
     * Searches for the first occurence of the given argument, beginning 
     * the search at <code>index</code>, and testing for equality using 
     * the <code>equals</code> method. 
     *
     * @param   elem    an object.
     * @param   index   the index to start searching from.
     * @return  the index of the first occurrence of the object argument in
     *          this ArrayList at position <code>index</code> or later in the
     *          ArrayList; returns <code>-1</code> if the object is not found.
     * @see     Object#equals(Object)
     * @since   JDK1.2
     */
    public int indexOf(Object elem, int index) {
        if (elem == null) {
            for (int i = index ; i < elementCount ; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = index ; i < elementCount ; i++)
                if (elem.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified object in
     * this ArrayList.
     *
     * @param   elem   the desired component.
     * @return  the index of the last occurrence of the specified object in
     *          this ArrayList; returns -1 if the object is not found.
     * @since   JDK1.2
     */
    public int lastIndexOf(Object elem) {
        return lastIndexOf(elem, elementCount-1);
    }

    /**
     * Searches backwards for the specified object, starting from the 
     * specified index, and returns an index to it. 
     *
     * @param  elem    the desired component.
     * @param  index   the index to start searching from.
     * @return the index of the last occurrence of the specified object in this
     *          ArrayList at position less than index in the ArrayList;
     *          -1 if the object is not found.
     * @since   JDK1.2
     */
    public int lastIndexOf(Object elem, int index) {
        if (elem == null) {
            for (int i = index; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = index; i >= 0; i--)
                if (elem.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns a shallow copy of this ArrayList.  (The elements themselves
     * are not copied.)
     *
     * @return  a clone of this ArrayList.
     * @since   JDK1.2
     */
    public Object clone() {
        try { 
            ArrayList v = (ArrayList)super.clone();
            v.elementData = new Object[elementCount];
            System.arraycopy(elementData, 0, v.elementData, 0, elementCount);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) { 
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    /**
     * Returns an array containing all of the elements in this ArrayList
     * in the correct order.
     *
     * @since JDK1.2
     */
    public Object[] toArray() {
        Object[] result = new Object[elementCount];
        System.arraycopy(elementData, 0, result, 0, elementCount);
        return result;
    }


    // Positional Access Operations

    /**
     * Returns the element at the specified position in this ArrayList.
     *
     * @param index index of element to return.
     * @exception ArrayIndexOutOfBoundsException index is out of range (index
     *                   &lt; 0 || index &gt;= size()).
     * @since JDK1.2
     */
    public Object get(int index) {
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);

        return elementData[index];
    }

    /**
     * Replaces the element at the specified position in this ArrayList with
     * the specified element.
     *
     * @param index index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @exception ArrayIndexOutOfBoundsException index out of range
     *                  (index &lt; 0 || index &gt;= size()).
     * @exception IllegalArgumentException fromIndex &gt; toIndex.
     * @since JDK1.2
     */
    public Object set(int index, Object element) {
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);

        Object oldValue = elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * Appends the specified element to the end of this ArrayList.
     *
     * @param o element to be appended to this ArrayList.
     * @return true (as per the general contract of Collection.add).
     * @since JDK1.2
     */
    public boolean add(Object o) {
        ensureCapacity(elementCount + 1);  // Increments modCount!!
        elementData[elementCount++] = o;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this
     * ArrayList. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @exception ArrayIndexOutOfBoundsException index is out of range
     *                  (index &lt; 0 || index &gt; size()).
     * @since JDK1.2
     */
    public void add(int index, Object element) {
        if (index > elementCount)
            throw new ArrayIndexOutOfBoundsException(index);

        ensureCapacity(elementCount+1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                         elementCount - index);
        elementData[index] = element;
        elementCount++;
    }

    /**
     * Removes the element at the specified position in this ArrayList.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).  Returns the element that was removed from the ArrayList.
     *
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *                   &lt; 0 || index &gt;= size()).
     * @param index the index of the element to removed.
     * @since JDK1.2
     */
    public Object remove(int index) {
        modCount++;
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);
        Object oldValue = elementData[index];

        int numMoved = elementCount - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--elementCount] = null; // Let gc do its work

        return oldValue;
    }

    /**
     * Removes all of the elements from this ArrayList.  The ArrayList will
     * be empty after this call returns, unless it throws an exception.
     *
     * @exception UnsupportedOperationException clear is not supported
     *                   by this Set.
     * @since JDK1.2
     */
    public void clear() {
        modCount++;

        // Let gc do its work
        for (int i = 0; i < elementCount; i++)
            elementData[i] = null;

        elementCount = 0;
    }

    /**
     * Appends all of the elements in the specified Collection to the end of
     * this this ArrayList, in the order that they are returned by the
     * specified Collection's Iterator.  The behavior of this operation is
     * undefined if the specified Collection is modified while the operation
     * is in progress.  (This implies that the behavior of this call is
     * undefined if the the specified Collection is this ArrayList, and this
     * ArrayList is nonempty.)
     *
     * @param index index at which to insert first element
     *                          from the specified collection.
     * @param c elements to be inserted into this ArrayList.
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *                  &lt; 0 || index &gt; size()).
     * @since JDK1.2
     */
    public boolean addAll(Collection c) {
        modCount++;
        int numNew = c.size();
        ensureCapacity(elementCount + numNew);

        Iterator e = c.iterator();
        for (int i=0; i<numNew; i++)
            elementData[elementCount++] = e.next();

        return numNew != 0;
    }

    /**
     * Removes from this ArrayList all of the elements whose index is between
     * fromIndex, inclusive and toIndex, exclusive.  Shifts any succeeding
     * elements to the left (reduces their index).
     * This call shortens the ArrayList by (toIndex - fromIndex) elements.  (If
     * toIndex==fromIndex, this operation has no effect.)
     *
     * @param fromIndex index of first element to be removed.
     * @param fromIndex index after last element to be removed.
     * @exception ArrayIndexOutOfBoundsException fromIndex or toIndex out of
     *                  range (fromIndex &lt; 0 || fromIndex &gt;= size() || toIndex
     *                  &gt; size() || toIndex &lt; fromIndex).
     * @since JDK1.2
     */
    public void removeRange(int fromIndex, int toIndex) {
        modCount++;
        if (fromIndex < 0 || fromIndex >= elementCount ||
            toIndex > elementCount || toIndex < fromIndex)
            throw new ArrayIndexOutOfBoundsException();

        int numMoved = elementCount - toIndex;
        if (numMoved > 0)
            System.arraycopy(elementData, toIndex, elementData, fromIndex,
                             numMoved);

        // Let gc do its work
        int newElementCount = elementCount - (toIndex-fromIndex);
        while (elementCount != newElementCount)
            elementData[--elementCount] = null;
    }

    /**
     * Inserts all of the elements in the specified Collection into this
     * ArrayList, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the ArrayList in the order that they are returned by the
     * specified Collection's iterator.
     *
     * @param index index at which to insert first element
     *                    from the specified collection.
     * @param c elements to be inserted into this ArrayList.
     * @exception ArrayIndexOutOfBoundsException index out of range (index
     *                  &lt; 0 || index &gt; size()).
     * @since JDK1.2
     */
    public boolean addAll(int index, Collection c) {
        modCount++;
        if (index < 0 || index > elementCount)
            throw new ArrayIndexOutOfBoundsException(index);            

        int numNew = c.size();
        ensureCapacity(elementCount + numNew);

        int numMoved = elementCount - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                             numMoved);

        Iterator e = c.iterator();
        for (int i=0; i<numNew; i++)
            elementData[index++] = e.next();

        elementCount += numNew;
        return numNew != 0;
    }
}
