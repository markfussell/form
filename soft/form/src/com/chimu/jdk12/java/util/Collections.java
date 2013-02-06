/*======================================================================
**
**  File: chimu/jdk12/java/util/Collections.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;
import java.io.Serializable;

/**
 * This class consists exclusively of static methods that operate on or
 * return Collections.  It contains polymorphic algorithms that operate
 * on collections, "views" and "wrappers", which return a new collection
 * backed by a specified collection, and a few other odds and ends.
 *
 * @author  Josh Bloch
 * @version 1.14 11/04/97
 * @see            Collection
 * @see            Set
 * @see            List
 * @see            Map
 * @since JDK1.2
 */

public class Collections {
    // Algorithms

    /**
     * Sorts the specified List into ascending order, according
     * to the <i>natural comparison method</i> of its elements.  All
     * elements in the List must implement the Comparable interface.
     * Furthermore, all elements in the List must be <i>mutually
     * comparable</i> (that is, e1.compareTo(e2) must not throw a
     * typeMismatchException for any elements e1 and e2 in the List).
     * <p>
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     * <p>
     * The specified List must be modifiable, but need not be resizable.
     * This implementation dumps the specified List into an List, sorts
     * the array, and iterates over the List resetting each element
     * from the corresponding position in the array.  This avoids the
     * n^2*log(n) performance that would result from attempting
     * to sort a LinkedList in place.
     *
     * @param          list the List to be sorted.
     * @exception ClassCastException List contains elements that are not
     *                  <i>mutually comparable</i> (for example, Strings and
     *                  Integers).
     * @exception UnsupportedOperationException The specified List's
     *                  ListIterator not support the set operation.
     * @see Comparable
     * @since JDK1.2
     */
    public static void sort(List list) {
        Object a[] = list.toArray();
        Arrays.sort(a);
        ListIterator i = list.listIterator();
        for (int j=0; j<a.length; j++) {
            i.next();
            i.set(a[j]);
        }
    }

    /**
     * Sorts the specified List according to the order induced by the
     * specified Comparator.  All elements in the List must be <i>mutually
     * comparable</i> by the specified comparator (that is,
     * comparator.compare(e1, e2) must not throw a typeMismatchException for
     * any elements e1 and e2 in the List).
     * <p>
     * The sorting algorithm is a tuned quicksort, adapted from Jon
     * L. Bentley and M. Douglas McIlroy's "Engineering a Sort Function",
     * Software-Practice and Experience, Vol. 23(11) P. 1249-1265 (November
     * 1993).  This algorithm offers n*log(n) performance on many data sets
     * that cause other quicksorts to degrade to quadratic performance.
     * <p>
     * The specified List must be modifiable, but need not be resizable.
     * This implementation dumps the specified List into an array, sorts
     * the array, and iterates over the List resetting each element
     * from the corresponding position in the array.  This avoids the
     * n^2*log(n) performance that would result from attempting
     * to sort a LinkedList in place.
     *
     * @param          list the List to be sorted.
     * @exception ClassCastException List contains elements that are not
     *                  <i>mutually comparable</i> with the specified Comparator.
     * @exception UnsupportedOperationException The specified List's did
     *                  ListIterator not support the set operation.
     * @see Comparator
     * @since JDK1.2
     */
    public static void sort(List list, Comparator c) {
        Object a[] = list.toArray();
        Arrays.sort(a, c);
        ListIterator i = list.listIterator();
        for (int j=0; j<a.length; j++) {
            i.next();
            i.set(a[j]);
        }
    }


    /**
     * Searches the specified List for the specified Object using the binary
     * search algorithm.  The List must be sorted into ascending order
     * according to the <i>natural comparison method</i> of its elements (as by
     * Sort(List), above) prior to making this call.  If it is not sorted,
     * the results are undefined: in particular, the call may enter an infinite
     * loop.  If the List contains multiple elements equal to the specified
     * Object, there is no guarantee which instance will be found.
     *<p>
     * This method will run in log(n) time for a "random access" List (which
     * provides near-constant-time positional access) like a Vector.  It may
     * run in n*log(n) time if it is called on a "sequential access" List
     * (which provides linear-time positional access).  If the specified List
     * is an instanceof AbstracSequentialList, this method will do a
     * sequential search instead of a binary search; this offers linear
     * performance instead of n*log(n) performance if this method is called on
     * a LinkedList.
     *
     * @param          list the List to be searched.
     * @param          key the key to be searched for.
     * @return index of the search key, if it is contained in the List;
     *               otherwise, (-(<i>insertion point</i>) - 1).  The <i>insertion
     *               point</i> is defined as the the point at which the value would
     *                be inserted into the List: the index of the first element
     *               greater than the value, or list.size(), if all elements in
     *               the List are less than the specified value.  Note that this
     *               guarantees that the return value will be &gt;= 0 if and only
     *               if the Object is found.
     * @exception ClassCastException List contains elements that are not
     *                  <i>mutually comparable</i> (for example, Strings and
     *                  Integers), or the search key in not mutually comparable
     *                  with the elements of the List.
     * @see Comparable
     * @see #sort(List)
     * @since JDK1.2
     */
    public static int binarySearch(List list, Object key) {
        // Do a sequential search if appropriate
        if (list instanceof AbstractSequentialList) {
            ListIterator i = list.listIterator();
            while (i.hasNext()) {
                int cmp = ((Comparable)(i.next())).compareTo(key);
                if (cmp == 0)
                    return i.previousIndex();
                else if (cmp > 0)
                    return -i.nextIndex();  // key not found.
            }
            return -i.nextIndex();  // key not found, list exhausted
        }

        // Otherwise, do a binary search
        int low = 0;
        int high = list.size()-1;

        while (low <= high) {
            int mid =(low + high)/2;
            Object midVal = list.get(mid);
            int cmp = ((Comparable)midVal).compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found
    }

    /**
     * Searches the specified List for the specified Object using the binary
     * search algorithm.  The List must be sorted into ascending order
     * according to the specified Comparator (as by Sort(List, Comparator),
     * above), prior to making this call.
     *<p>
     * This method will run in log(n) time for a "random access" List (which
     * provides near-constant-time positional access) like a Vector.  It may
     * run in n*log(n) time if it is called on a "sequential access" List
     * (which provides linear-time positional access).  If the specified List
     * is an instanceof AbstracSequentialList, this method will do a
     * sequential search instead of a binary search; this offers linear
     * performance instead of n*log(n) performance if this method is called on
     * a LinkedList.
     *
     * @param          list the List to be searched.
     * @param          key the key to be searched for.
     * @return index of the search key, if it is contained in the List;
     *               otherwise, (-(<i>insertion point</i>) - 1).  The <i>insertion
     *               point</i> is defined as the the point at which the value would
     *                be inserted into the List: the index of the first element
     *               greater than the value, or list.size(), if all elements in
     *               the List are less than the specified value.  Note that this
     *               guarantees that the return value will be &gt;= 0 if and only
     *               if the Object is found.
     * @exception ClassCastException List contains elements that are not
     *                  <i>mutually comparable</i> with the specified Comparator,
     *                  or the search key in not mutually comparable with the
     *                  elements of the List using this Comparator.
     * @see Comparable
     * @see #sort(List, Comparator)
     * @since JDK1.2
     */
    public static int binarySearch(List list, Object key, Comparator c) {
        // Do a sequential search if appropriate
        if (list instanceof AbstractSequentialList) {
            ListIterator i = list.listIterator();
            while (i.hasNext()) {
                int cmp = c.compare(i.next(), key);
                if (cmp == 0)
                    return i.previousIndex();
                else if (cmp > 0)
                    return -i.nextIndex();  // key not found.
            }
            return -i.nextIndex();  // key not found, list exhausted
        }

        // Otherwise, do a binary search
        int low = 0;
        int high = list.size()-1;

        while (low <= high) {
            int mid =(low + high)/2;
            Object midVal = list.get(mid);
            int cmp = c.compare(midVal, key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found
    }

    /**
     * Returns the minimum element of the given Collection, according
     * to the <i>natural comparison method</i> of its elements.  All
     * elements in the Collection must implement the Comparable interface.
     * Furthermore, all elements in the Collection must be <i>mutually
     * comparable</i> (that is, e1.compareTo(e2) must not throw a
     * typeMismatchException for any elements e1 and e2 in the Collection).
     * <p>
     * This method iterates over the entire Collection, hence it requires
     * time proportional to the size of the Collection.
     *
     * @param coll the collection whose minimum element is to be determined.
     * @exception ClassCastException Collection contains elements that are not
     *                  <i>mutually comparable</i> (for example, Strings and
     *                  Integers).
     * @exception NoSuchElementException Collection is empty.
     *
     * @see Comparable
     * @since JDK1.2
     */
    public static Object min(Collection coll) {
        Iterator i = coll.iterator();
        Comparable candidate = (Comparable)(i.next());
        while (i.hasNext()) {
            Comparable next = (Comparable)(i.next());
            if (next.compareTo(candidate) < 0)
                candidate = next;
        }
        return candidate;
    }

    /**
     * Returns the minimum element of the given Collection, according to the
     * order induced by the specified Comparator.  All elements in the
     * Collection must be <i>mutually comparable</i> by the specified
     * comparator (that is, comparator.compare(e1, e2) must not throw a
     * typeMismatchException for any elements e1 and e2 in the Collection).
     * <p>
     * This method iterates over the entire Collection, hence it requires
     * time proportional to the size of the Collection.
     *
     * @param coll the collection whose minimum element is to be determined.
     * @exception ClassCastException Collection contains elements that are not
     *                  <i>mutually comparable</i> with the specified Comparator.
     * @exception NoSuchElementException Collection is empty.
     *
     * @see Comparable
     * @since JDK1.2
     */
    public static Object min(Collection coll, Comparator comp) {
        Iterator i = coll.iterator();
        Object candidate = i.next();
        while (i.hasNext()) {
            Object next = i.next();
            if (comp.compare(next, candidate) < 0)
                candidate = next;
        }
        return candidate;
    }

    /**
     * Returns the maximum element of the given Collection, according
     * to the <i>natural comparison method</i> of its elements.  All
     * elements in the Collection must implement the Comparable interface.
     * Furthermore, all elements in the Collection must be <i>mutually
     * comparable</i> (that is, e1.compareTo(e2) must not throw a
     * typeMismatchException for any elements e1 and e2 in the Collection).
     * <p>
     * This method iterates over the entire Collection, hence it requires
     * time proportional to the size of the Collection.
     *
     * @param coll the collection whose maximum element is to be determined.
     * @exception ClassCastException Collection contains elements that are not
     *                  <i>mutually comparable</i> (for example, Strings and
     *                  Integers).
     * @exception NoSuchElementException Collection is empty.
     *
     * @see Comparable
     * @since JDK1.2
     */
    public static Object max(Collection coll) {
        Iterator i = coll.iterator();
        Comparable candidate = (Comparable)(i.next());
        while (i.hasNext()) {
            Comparable next = (Comparable)(i.next());
            if (next.compareTo(candidate) > 0)
                candidate = next;
        }
        return candidate;
    }


    /**
     * Returns the maximum element of the given Collection, according to the
     * order induced by the specified Comparator.  All elements in the
     * Collection must be <i>mutually comparable</i> by the specified
     * comparator (that is, comparator.compare(e1, e2) must not throw a
     * typeMismatchException for any elements e1 and e2 in the Collection).
     * <p>
     * This method iterates over the entire Collection, hence it requires
     * time proportional to the size of the Collection.
     *
     * @param coll the collection whose maximum element is to be determined.
     * @exception ClassCastException Collection contains elements that are not
     *                  <i>mutually comparable</i> with the specified Comparator.
     * @exception NoSuchElementException Collection is empty.
     *
     * @see Comparable
     * @since JDK1.2
     */
    public static Object max(Collection coll, Comparator comp) {
        Iterator i = coll.iterator();
        Object candidate = i.next();
        while (i.hasNext()) {
            Object next = i.next();
            if (comp.compare(next, candidate) > 0)
                candidate = next;
        }
        return candidate;
    }

    // List Views

    /**
     * Returns a List backed by the specified List that represents the portion
     * of the specified List whose index ranges from fromIndex (inclusive) to
     * toIndex (exclusive).  The returned List is not resizable.  (Its size
     * is fixed at (toIndex - fromIndex).)  The returned List is mutable iff
     * the specified List is mutable.  Changes to the returned List "write
     * through" to the specified List, and vice-versa.
     * <p>
     * If the caller wants a List that is independent of the input List,
     * and free of the restrictions noted above, he should immediately
     * copy the returned List into a new List, for example:
     * <pre>
     *     Vector v = new Vector(Collections.subList(myList, 17, 42));
     * </pre>
     *
     * @param list the List whose subList is to be returned.
     * @param fromIndex the index (in this List) of the first element to
     *              appear in the subList.
     * @param toIndex the index (in this List) following the last element to
     *              appear in the subList.
     * @exception ArrayIndexOutOfBoundsException fromIndex or toIndex is out
     *                  of range (fromIndex &lt; 0 || fromIndex &gt; size ||
     *                  toIndex &lt; 0 || toIndex &gt; size).
     * @exception IllegalArgumentException fromIndex &gt toIndex.
     * @since JDK1.2
     */
    public static List subList(List list, int fromIndex, int toIndex) {
        return new SubList(list, fromIndex, toIndex);
    }

    /**
     * SubList - Implements a SubList view backed by an arbitrary List.
     */
    static class SubList extends AbstractList {
        private List backer;
        private int offset;
        private int size;

        SubList(List list, int fromIndex, int toIndex) {
            backer = list;
            offset = fromIndex;
            size = toIndex - fromIndex;

            if (size<0)
                throw new IllegalArgumentException("fromIndex < toIndex");

            int backerSize = backer.size();
            if (fromIndex < 0 || fromIndex > backerSize ||
                toIndex   < 0 || toIndex   > backerSize)
                throw new ArrayIndexOutOfBoundsException();
        }

        public int size() {
            return size;
        }

        public Iterator iterator() {
            return listIterator();
        }

        public Object get(int index) {
            rangeCheck(index);
            return backer.get(index + offset);
        }

        public Object set(int index, Object element) {
            rangeCheck(index);
            return backer.set(index + offset, element);
        }

        public int indexOf(Object o, int index) {
            rangeCheck(index);
            ListIterator i = backer.listIterator(index + offset);

            if (o==null) {
                for(int j=index; j<size; j++)
                    if (i.next()==null)
                        return i.previousIndex() - offset;
            } else {
                for(int j=index; j<size; j++)
                    if (o.equals(i.next()))
                        return i.previousIndex() - offset;
            }
            return -1;
        }

        public int lastIndexOf(Object o, int index) {
            rangeCheck(index);
            ListIterator i = backer.listIterator(index + offset + 1);

            if (o==null) {
                for(int j=index; j>=0; j--)
                    if (i.previous()==null)
                        return i.nextIndex() - offset;
            } else {
                for(int j=index; j>=0; j--)
                    if (o.equals(i.previous()))
                        return i.nextIndex() - offset;
            }
            return -1;
        }

        public ListIterator listIterator(final int index) {
            if (index<0 || index>size)
                throw new ArrayIndexOutOfBoundsException(index);

            return new ListIterator() {
                private ListIterator i = backer.listIterator(index+offset);

                public boolean hasNext() {
                    return nextIndex() < size;
                }

                public Object next() {
                    if (hasNext())
                        return i.next();
                    else
                        throw new NoSuchElementException();
                }

                public boolean hasPrevious() {
                    return previousIndex() >= 0;
                }

                public Object previous() {
                    if (hasPrevious())
                        return i.previous();
                    else
                        throw new NoSuchElementException();
                }

                public int nextIndex() {
                    return i.nextIndex() - offset;
                }

                public int previousIndex() {
                    return i.previousIndex() - offset;
                }

                public void remove() {
                    throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();
                }

                public void set(Object o) {
                    i.set(o);
                }

                public void add(Object o) {
                    throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();
                }
            };
        }

        private void rangeCheck(int index) {
            if (index<0 || index>=size)
                throw new ArrayIndexOutOfBoundsException(index);
        }
    }


    // Wrappers

    /**
     * Returns an unmodifiable view of the specified Collection.  This method
     * allow modules to provide users with "read-only" access to internal
     * Collections. Query operations on the returned Collection "read through"
     * to the specified Collection, and attempts to modify the returned
     * Collection, whether direct or via its Iterator, result in an
     * UnsupportedOperationException.
     * <p>
     * The returned Collection does <em>not</em> pass the hashCode and
     * equals operations through to the backing Collection, but relies
     * on Object's equals and hashCode methods.  This is necessary to
     * preserve the contracts of these operations in case that the backing
     * Collection is a Set or a List.
     *
     * @param c the Collection for which an unmodifiable view is to be
     *                returned.
     * @since JDK1.2
     */
    public static Collection unmodifiableCollection(Collection c) {
        return new UnmodifiableCollection(c);
    }

    static class UnmodifiableCollection implements Collection {
        private Collection c;

        UnmodifiableCollection(Collection c) {this.c = c;}

        public int size() {return c.size();}
        public boolean isEmpty() {return c.isEmpty();}
        public boolean contains(Object o) {return c.contains(o);}
        public Object[] toArray() {return c.toArray();}

        public Iterator iterator() {
            return new Xxx();
        }
            class Xxx implements Iterator {
                Iterator i = c.iterator();

                public boolean hasNext() {return i.hasNext();}
                public Object next() {return i.next();}
                public void remove() {
                    throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
            }


        public boolean add(Object o){
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public boolean remove(Object o) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}

        public boolean containsAll(Collection coll) {
            return c.containsAll(coll);}
        public boolean addAll(Collection c) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public boolean removeAll(Collection c) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public boolean retainAll(Collection c) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public void clear() {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
    }

    /**
     * Returns an unmodifiable view of the specified Set.  This method allow
     * modules to provide users with "read-only" access to internal
     * Sets. Query operations on the returned Set "read through" to the
     * specified Set, and attempts to modify the returned Set, whether direct
     * or via its Iterator, result in an UnsupportedOperationException.
     *
     * @param s the Set for which an unmodifiable view is to be returned.
     * @since JDK1.2
     */

    public static Set unmodifiableSet(Set s) {
        return new UnmodifiableSet(s);
    }

    static class UnmodifiableSet extends UnmodifiableCollection
                                     implements Set {
        UnmodifiableSet(Set s) {super(s);}

        public boolean equals(Object o) {return c.equals(o);}
        public int hashCode()                 {return c.hashCode();}
    }

    /**
     * Returns an unmodifiable view of the specified List.  This method allow
     * modules to provide users with "read-only" access to internal
     * Lists. Query operations on the returned List "read through" to the
     * specified List, and attempts to modify the returned List, whether direct
     * or via its Iterator, result in an UnsupportedOperationException.
     *
     * @param list the List for which an unmodifiable view is to be returned.
     * @since JDK1.2
     */

    public static List unmodifiableList(List list) {
        return new UnmodifiableList(list);
    }

    static class UnmodifiableList extends UnmodifiableCollection
                                      implements List {
        private List list;

        UnmodifiableList(List list) {
            super(list);
            this.list = list;
        }

        public boolean equals(Object o) {return list.equals(o);}
        public int hashCode()                 {return list.hashCode();}

        public Object get(int index) {return list.get(index);}
        public Object set(int index, Object element) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public void add(int index, Object element) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public Object remove(int index) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public int indexOf(Object o) {return list.indexOf(o);}
        public int indexOf(Object o, int i) {return list.indexOf(o, i);}
        public int lastIndexOf(Object o) {return list.lastIndexOf(o);}
        public int lastIndexOf(Object o, int i) {return list.lastIndexOf(o,i);}
        public void removeRange(int fromIndex, int toIndex) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public boolean addAll(int index, Collection c) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public ListIterator listIterator() {return listIterator(0);}

        public ListIterator listIterator(int index) {
            return new ListIterator() {
                ListIterator i = list.listIterator();

                public boolean hasNext() {return i.hasNext();}
                public Object next() {return i.next();}
                public boolean hasPrevious() {return i.hasPrevious();}
                public Object previous() {return i.previous();}
                public int nextIndex() {return i.nextIndex();}
                public int previousIndex() {return i.previousIndex();}

                public void remove() {
                    throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
                public void set(Object o) {
                    throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
                public void add(Object o) {
                    throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
            };
        }
    }

    /**
     * Returns an unmodifiable view of the specified Map.  This method
     * allow modules to provide users with "read-only" access to internal
     * Maps. Query operations on the returned Map "read through"
     * to the specified Map, and attempts to modify the returned
     * Map, whether direct or via its Collection views, result in an
     * UnsupportedOperationException.
     *
     * @param m the Map for which an unmodifiable view is to be returned.
     * @since JDK1.2
     */
    public static Map unmodifiableMap(Map m) {
        return new UnmodifiableMap(m);
    }

    private static class UnmodifiableMap implements Map {
        private final Map m;

        UnmodifiableMap(Map m) {this.m = m;}

        public int size() {
            return m.size();}
        public boolean isEmpty() {
            return m.isEmpty();}
        public boolean containsKey(Object key) {
            return m.containsKey(key);}
        public boolean containsValue(Object value) {
            return m.containsValue(value);}
        public Object get(Object key) {
            return m.get(key);}

        public Object put(Object key, Object value) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public Object remove(Object key) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public void putAll(Map t) {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}
        public void clear() {
            throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();}

        private transient Set keySet = null;
        private transient Collection entries = null;
        private transient Collection values = null;

        public Set keySet() {
            if (keySet==null)
                keySet = unmodifiableSet(m.keySet());
            return keySet;
        }

        public Collection entries() {
            if (entries==null)
                entries = unmodifiableCollection(m.entries());
            return entries;
        }

        public Collection values() {
            if (values==null)
                values = unmodifiableCollection(m.values());
            return values;
        }

        public boolean equals(Object o) {return m.equals(o);}
        public int hashCode()           {return m.hashCode();}
    }

    /**
     * Returns a synchronized (thread-safe) Collection backed by the specified
     * Collection. In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing Collection is accomplished
     * through the returned Collection.
     * <p>
     * It is imperative that user manually synchronize on the returned
     * Collection when iterating over it:
     * <pre>
     *  Collection c = synchronizedCollection(myCollection);
     *     ...
     *  synchronized(c) {
     *      Iterator i = c.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *         foo(i.next();
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The returned Collection does <em>not</em> pass the hashCode and
     * equals operations through to the backing Collection, but relies
     * on Object's equals and hashCode methods.  This is necessary to
     * preserve the contracts of these operations in case that the backing
     * Collection is a Set or a List.
     * <p>
     * The returned Collection will be Serializable if the specified Collection
     * is Serializable.
     *
     * @param c the Collection to be "wrapped" in a synchronized Collection.
     * @since JDK1.2
     */
    public static Collection synchronizedCollection(Collection c) {
        return new SynchronizedCollection(c);
    }

    static class SynchronizedCollection implements Collection, Serializable {
        private Collection c;           // Backing Collection
        private Object           mutex;  // Object on which to synchronize

        SynchronizedCollection(Collection c) {
            this.c = c; mutex = this;}
        SynchronizedCollection(Collection c, Object mutex) {
            this.c = c; this.mutex = mutex;}

        public int size() {
            synchronized(mutex) {return c.size();}}
        public boolean isEmpty() {
            synchronized(mutex) {return c.isEmpty();}}
        public boolean contains(Object o) {
            synchronized(mutex) {return c.contains(o);}}
        public Object[] toArray() {
            synchronized(mutex) {return c.toArray();}}

        public Iterator iterator() {
            synchronized(mutex) {return new Xxx1();}
        }
        class Xxx1 implements Iterator {
            Iterator i = c.iterator();

            public boolean hasNext() {
                synchronized(mutex) {return i.hasNext();}}
            public Object next() {
                synchronized(mutex) {return i.next();}}
            public void remove() {
                synchronized(mutex) {i.remove();}}
        }

        public boolean add(Object o) {
            synchronized(mutex) {return c.add(o);}}
        public boolean remove(Object o) {
            synchronized(mutex) {return c.remove(o);}}

        public boolean containsAll(Collection coll) {
            synchronized(mutex) {return c.containsAll(coll);}}
        public boolean addAll(Collection c) {
            synchronized(mutex) {return c.addAll(c);}}
        public boolean removeAll(Collection c) {
            synchronized(mutex) {return c.removeAll(c);}}
        public boolean retainAll(Collection c) {
            synchronized(mutex) {return c.retainAll(c);}}
        public void clear() {
            synchronized(mutex) {c.clear();}}
    }

    /**
     * Returns a synchronized (thread-safe) Set backed by the specified
     * Set. In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing Set is accomplished
     * through the returned Set.
     * <p>
     * It is imperative that user manually synchronize on the returned
     * Set when iterating over it:
     * <pre>
     *  Set s = synchronizedSet(new HashSet());
     *      ...
     *  synchronized(s) {
     *      Iterator i = s.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *          foo(i.next();
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The returned Set will be Serializable if the specified Set is
     * Serializable.
     *
     * @param s the Set to be "wrapped" in a synchronized Set.
     * @since JDK1.2
     */
    public static Set synchronizedSet(Set s) {
        return new SynchronizedSet(s);
    }

    static class SynchronizedSet extends SynchronizedCollection
                                 implements Set, Serializable {
        SynchronizedSet(Set s)                      {super(s);}
        SynchronizedSet(Set s, Object mutex) {super(s, mutex);}

        public boolean equals(Object o) {
            synchronized(mutex) {return c.equals(o);}}
        public int hashCode(Object o) {
            synchronized(mutex) {return c.hashCode();}}
    }

    /**
     * Returns a synchronized (thread-safe) List backed by the specified
     * List. In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing List is accomplished
     * through the returned List.
     * <p>
     * It is imperative that user manually synchronize on the returned
     * List when iterating over it:
     * <pre>
     *  List list = synchronizedList(new Arraylist());
     *      ...
     *  synchronized(list) {
     *      Iterator i = list.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next();
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The returned List will be Serializable if the specified List is
     * Serializable.
     *
     * @param list the List to be "wrapped" in a synchronized List.
     * @since JDK1.2
     */
    public static List synchronizedList(List list) {
        return new SynchronizedList(list);
    }

    static class SynchronizedList extends SynchronizedCollection
                                      implements List, Serializable {
        private List list;

        SynchronizedList(List list) {
            super(list);
            this.list = list;
        }

        public boolean equals(Object o) {
            synchronized(mutex) {return list.equals(o);}}
        public int hashCode(Object o) {
            synchronized(mutex) {return list.hashCode();}}

        public Object get(int index) {
            synchronized(mutex) {return list.get(index);}}
        public Object set(int index, Object element) {
            synchronized(mutex) {return list.set(index, element);}}
        public void add(int index, Object element) {
            synchronized(mutex) {list.add(index, element);}}
        public Object remove(int index) {
            synchronized(mutex) {return list.remove(index);}}

        public int indexOf(Object o) {
            synchronized(mutex) {return list.indexOf(o);}}
        public int indexOf(Object o, int i) {
            synchronized(mutex) {return list.indexOf(o, i);}}
        public int lastIndexOf(Object o) {
            synchronized(mutex) {return list.lastIndexOf(o);}}
        public int lastIndexOf(Object o, int i) {
            synchronized(mutex) {return list.lastIndexOf(o,i);}}

        public void removeRange(int fromIndex, int toIndex) {
            synchronized(mutex) {list.removeRange(fromIndex, toIndex);}}
        public boolean addAll(int index, Collection c) {
            synchronized(mutex) {return list.addAll(index, c);}}

        public ListIterator listIterator() {
            synchronized(mutex) {return listIterator(0);}}

        public ListIterator listIterator(int index) {
            synchronized(mutex) {return new Xxx2();}
        }
            class Xxx2 implements ListIterator {
                ListIterator i = list.listIterator();

                public boolean hasNext() {
                    synchronized(mutex) {return i.hasNext();}}
                public Object next() {
                    synchronized(mutex) {return i.next();}}
                public boolean hasPrevious() {
                    synchronized(mutex) {return i.hasPrevious();}}
                public Object previous() {
                    synchronized(mutex) {return i.previous();}}
                public int nextIndex() {
                    synchronized(mutex) {return i.nextIndex();}}
                public int previousIndex() {
                    synchronized(mutex) {return i.previousIndex();}}
                public void remove() {
                    synchronized(mutex) {i.remove();}}
                public void set(Object o) {
                    synchronized(mutex) {i.set(o);}}
                public void add(Object o) {
                    synchronized(mutex) {i.add(o);}}
            }
    }

    /**
     * Returns a synchronized (thread-safe) Map backed by the specified
     * Map. In order to guarantee serial access, it is critical that
     * <strong>all</strong> access to the backing Map is accomplished
     * through the returned Map.
     * <p>
     * It is imperative that user manually synchronize on the returned
     * Map when iterating over any of its Collection views:
     * <pre>
     *  Map m = synchronizedMap(new HashMap());
     *      ...
     *  Set s = m.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized(m) {  // Synchronizing on m, not s!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next();
     *  }
     * </pre>
     * Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The returned Map will be Serializable if the specified Map is
     * Serializable.
     *
     * @param m the Map to be "wrapped" in a synchronized Map.
     * @since JDK1.2
     */
    public static Map synchronizedMap(Map m) {
        return new SynchronizedMap(m);
    }

    private static class SynchronizedMap implements Map {
        private final Map m;

        SynchronizedMap(Map m) {this.m = m;}

        public synchronized int size() {
            return m.size();}
        public synchronized boolean isEmpty(){
            return m.isEmpty();}
        public synchronized boolean containsKey(Object key) {
            return m.containsKey(key);}
        public synchronized boolean containsValue(Object value){
            return m.containsValue(value);}
        public synchronized Object get(Object key) {
            return m.get(key);}

        public synchronized Object put(Object key, Object value) {
            return m.put(key, value);}
        public synchronized Object remove(Object key) {
            return m.remove(key);}
        public synchronized void putAll(Map map) {
            m.putAll(map);}
        public synchronized void clear() {
            m.clear();}

        private transient Set keySet = null;
        private transient Collection entries = null;
        private transient Collection values = null;

        public Set keySet() {
            if (keySet==null)
                keySet = new SynchronizedSet(m.keySet(), this);
            return keySet;
        }

        public Collection entries() {
            if (entries==null)
                entries = new SynchronizedCollection(m.entries(), this);
            return entries;
        }

        public Collection values() {
            if (values==null)
                values = new SynchronizedCollection(m.values(), this);
            return values;
        }

        public synchronized boolean equals(Object o) {return m.equals(o);}
        public synchronized int hashCode()           {return m.hashCode();}
    }

    // Miscellaneous

    /**
     * Returns an immutable List consisting of n copies of the specified
     * Object.  The newly allocated data Object is tiny (it contains a single
     * reference to the data Object).  This method is useful in combination
     * with List.addAll to grow Lists.
     *
     * @param n the number of elements in the returned List.
     * @param o the element to appear repeatedly in the returned List.
     * @exception IllegalArgumentException n &lt; 0.
     * @see List#addAll(Collection)
     * @see List#addAll(int, Collection)
     * @since JDK1.2
     */
    public static List nCopies(final int n, final Object o) {
        if (n < 0)
            throw new IllegalArgumentException("List length = " + n);

        return new Xxx3(n, o);
    }
        static class Xxx3 extends AbstractList {
int n;
Object o;
Xxx3(int n, Object o) {
    this.n=n;
    this.o=o;
}
            public int size() {
                return n;
            }

            public boolean contains(Object obj) {
                return n!=0 && o.equals(obj);
            }

            public Object get(int index) {
                if (index<0 || index>n)
                    throw new ArrayIndexOutOfBoundsException(index);
                return o;
            }
        };

    /**
     * Returns an Enumeration over the specified Collection.  This provides
     * interoperatbility with legacy APIs that require an Enumeration
     * as input.
     *
     * @param c the Collection for which an Enumeration is to be returned.
     * @since JDK1.2
     */
    public static java.util.Enumeration enumeration(final Collection c) {
        return new Xxx4(c);
    }
static class Xxx4 implements java.util.Enumeration {
            Iterator i; // = c.iterator();
Xxx4(Collection c) {
    i = c.iterator();
}
            public boolean hasMoreElements() {
                return i.hasNext();
            }

            public Object nextElement() {
                return i.next();
            }
        }
}
