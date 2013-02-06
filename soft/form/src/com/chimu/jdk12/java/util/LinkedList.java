/*======================================================================
**
**  File: chimu/jdk12/java/util/LinkedList.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * Resizable array implementation of the List interface.  Implements all
 * optional List operations, and permits all elements (including null).  In
 * addition to implementing the List interface, LinkedList provides
 * uniformly named methods to get, remove and insert an element at the
 * beginning and end of the List.  These operations allow LinkedList to be
 * used as a stack, queue, or double-ended queue (deque).
 * <p>
 * All of the stack/queue/deque operations could be easily recast in terms
 * of the standard List operations.  They're included here primarily for
 * convenience, though they may run slightly faster than the equivalent
 * List operations.
 * <p>
 * All of the operations perform as could be expected for a doubly-linked
 * list.  Operations that index into the list will traverse the list from
 * the begining or the end, whichever is closer to the specified index.
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access a LinkedList concurrently, and at least one of the
 * threads modifies the LinkedList structurally, it <em>must</em> be
 * synchronized externally.  (A structural modification is any operation that
 * adds or deletes one or more elements; merely setting the value of an
 * element is not a structural modification.)  This is typically accomplished
 * by synchronizing on some object that naturally encapsulates the
 * LinkedList.  If no such object exists, the LinkedList should be "wrapped"
 * using the Collections.synchronizedSet method.  This is best done at
 * creation time, to prevent accidental unsynchronized access to the
 * LinkedList:
 * <pre>
 *        List list = Collections.synchronizedList(new LinkedList(...));
 * </pre>
 * <p>
 * The Iterators returned by LinkedList's iterator and listIterator
 * methods are <em>fail-fast</em>: if the LinkedList is structurally modified
 * at any time after the Iterator is created, in any way except through the
 * Iterator's own remove or add methods, the Iterator will throw a
 * ConcurrentModificationException.  Thus, in the face of concurrent
 * modification, the Iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the future.
 *
 * @author  Josh Bloch
 * @version 1.13 10/13/97
 * @see            List
 * @see            ArrayList
 * @see            Vector
 * @see            Collections.synchronizedList
 * @since JDK1.2
 */

public class LinkedList extends AbstractSequentialList
                        implements List, Cloneable, java.io.Serializable {
    private Entry header = new Entry(null, null, null);
    private int size = 0;

    /**
     * Constructs an empty Linked List.
     *
     * @since JDK1.2
     */
     public LinkedList() {
        header.next = header;
        header.previous = header;
    }

    /**
     * Constructs a LinkedList containing the elements of the specified
     * Collection, in the order they are returned by the Collection's
     * iterator.
     *
     * @since   JDK1.2
     */
     public LinkedList(Collection c) {
         this();
         addAll(c);
    }

    /**
     * Returns the first Element in this List.
     *
     * @since JDK1.2
     */
    public Object getFirst() {
        if (size==0)
            throw new NoSuchElementException();

        return header.next.element;
    }

    /**
     * Returns the last Element in this List.
     *
     * @exception NoSuchElementException List is empty.
     * @since JDK1.2
     */
    public Object getLast()  {
        if (size==0)
            throw new NoSuchElementException();

        return header.previous.element;
    }

    /**
     * Removes and returns the first Element from this List.
     *
     * @return the first Element from this List.
     * @exception NoSuchElementException List is empty.
     * @since JDK1.2
     */
    public Object removeFirst() {
        Object first = header.next.element;
        remove(header.next);
        return first;
    }

    /**
     * Removes and returns the last Element from this List.
     *
     * @return the last Element from this List.
     * @exception NoSuchElementException List is empty.
     * @since JDK1.2
     */
    public Object removeLast() {
        Object last = header.previous.element;
        remove(header.previous);
        return last;
    }

    /**
     * Inserts the given element at the beginning of this List.
     *
     * @since JDK1.2
     */
    public void addFirst(Object o) {
        addBefore(o, header.next);
    }

    /**
     * Appends the given element to the end of this List.  (Identical
     * in function to add(); included only for consistency.)
     *
     * @since JDK1.2
     */
    public void addLast(Object o) {
        addBefore(o, header);
    }

    /**
     * Returns the number of elements in this List.
     *
     * @since JDK1.2
     */
    public int size() {
        return size;
    }

    /**
     * Removes all of the elements from this List.
     *
     * @since JDK1.2
     */
    public void clear() {
        modCount++;
        header = new Entry(null, null, null);
        size = 0;
    }

    /**
     * Returns a ListIterator of the elements in this List (in proper
     * sequence), starting at the specified position in the list.
     * Obeys the general contract of List.listIterator(int).
     * <p>
     * The ListIterator is <em>fail-fast</em>: if the LinkedList is
     * structurally modified at any time after the Iterator is created, in
     * any way except through the ListIterator's own remove or add methods,
     * the Iterator will throw a ConcurrentModificationException.  Thus, in
     * the face of concurrent modification, the Iterator fails quickly and
     * cleanly, rather than risking arbitrary, non-deterministic behavior at
     * an undetermined time in the future.
     *
     * @param index index of first element to be returned from the
     *                    ListIterator (by a call to getNext).
     * @exception ArrayIndexOutOfBoundsException index is out of range
     *                  (index &lt; 0 || index &gt; size()).
     * @see  List#listIterator(int)
     * @since JDK1.2
     */
    public ListIterator listIterator(int index) {
        return new ListItr(index);
    }

    private class ListItr implements ListIterator {
        private Entry lastReturned = header;
        private Entry next;
        private int nextIndex;
        private boolean forward;
        private int expectedModCount = modCount;

        ListItr(int index) {
            if (index < 0 || index > size)
                throw(new ArrayIndexOutOfBoundsException(index));

            if (index < size/2) {
                next = header.next;
                for (nextIndex=0; nextIndex<index; nextIndex++)
                    next = next.next;
            } else {
                next = header;
                for (nextIndex=size; nextIndex>index; nextIndex--)
                    next = next.previous;
            }
        }

        public boolean hasNext() {
            return nextIndex != size;
        }

        public Object next() {
            checkForComodification();
            if (nextIndex == size)
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            forward = true;
            return lastReturned.element;
        }

        public boolean hasPrevious() {
            return nextIndex != 0;
        }

        public Object previous() {
            if (nextIndex == 0)
                throw new NoSuchElementException();

            lastReturned = next = next.previous;
            nextIndex--;
            checkForComodification();
            forward = false;
            return lastReturned.element;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex-1;
        }

        public void remove() {
            LinkedList.this.remove(lastReturned);
            lastReturned = header;
            if (forward)
                nextIndex--;
            expectedModCount++;
        }

        public void set(Object o) {
            if (lastReturned == header)
                throw new NoSuchElementException();
            checkForComodification();
            lastReturned.element = o;
        }

        public void add(Object o) {
            checkForComodification();
            next = addBefore(o, next);
            expectedModCount++;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    private static class Entry {
        Object element;
        Entry next;
        Entry previous;

        Entry(Object element, Entry next, Entry previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    private Entry addBefore(Object o, Entry e) {
        Entry newEntry = new Entry(o, e, e.previous);
        newEntry.previous.next = newEntry;
        newEntry.next.previous = newEntry;
        size++;
        modCount++;
        return newEntry;
    }

    private void remove(Entry e) {
        if (e == header)
            throw new NoSuchElementException();

        e.previous.next = e.next;
        e.next.previous = e.previous;
        size--;
        modCount++;
    }

    /**
     * Returns a shallow copy of this LinkedList. (The elements themselves
     * are not cloned.)
     *
     * @since   JDK1.2
     */
    public Object clone() {
        return new LinkedList(this);
    }
}
