/*======================================================================
**
**  File: chimu/jdk12/java/util/TreeMap.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;

/**
 * Red-Black tree based implementation of the Map interface.  This class
 * guarantees that the Map will be in ascending key order, sorted according
 * to the <i>natural sort method</i> for the key Class (see Comparable), or
 * by the Comparator provided at TreeMap creation time, depending on which
 * constructor is used.
 * <p>
 * This implementation provides guaranteed log(n) time cost for the
 * containsKey, get, put and remove operations.  Algorithms are adaptations
 * of those in Corman, Leiserson, and Rivest's <EM>Introduction to
 * Algorithms</EM>.
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access a TreeMap concurrently, and at least one of the
 * threads modifies the TreeMap structurally, it <em>must</em> be synchronized
 * externally.  (A structural modification is any operation that adds or
 * deletes one or more mappings; merely changing the value associated
 * with a key that is already contained in the Table is not a structural
 * modification.)  This is typically accomplished by synchronizing on some
 * object that naturally encapsulates the TreeMap.  If no such object exists,
 * the TreeMap should be "wrapped" using the Collections.synchronizedSet
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the TreeMap:
 * <pre>
 *        Map m = Collections.synchronizedMap(new TreeMap(...));
 * </pre>
 * <p>
 * The Iterators returned by the iterator methods of the Collections returned
 * by all of TreeMap's "collection view methods" are <em>fail-fast</em>: if the
 * TreeMap is structurally modified at any time after the Iterator is created,
 * in any way except through the Iterator's own remove or add methods, the
 * Iterator will throw a ConcurrentModificationException.  Thus, in the face of
 * concurrent modification, the Iterator fails quickly and cleanly, rather than
 * risking arbitrary, non-deterministic behavior at an undetermined time in the
 * future.
 *
 * @author  Josh Bloch and Doug Lea
 * @version 1.13, 11/04/97
 * @see Map
 * @see HashMap
 * @see ArrayMap
 * @see Hashtable
 * @see Comparable
 * @see Comparator
 * @see Collection
 * @see Collections#synchronizedMap()
 * @since JDK1.2
 */

public class TreeMap extends AbstractMap
                     implements Map, java.io.Serializable {
    private Comparator comparator = null;
    private Entry root = null;

    /**
     * The number of entries in the tree
     */
    private int size = 0;

    /**
     * The number of structural modifications to the tree.
     */
    private transient int modCount = 0;

    private void incrementSize()   { modCount++; size++; }
    private void decrementSize()   { modCount++; size--; }

    /**
     * Constructs a new, empty TreeMap, sorted according to the keys'
     * natural sort method.  All keys inserted into the TreeMap must
     * implement the Comparable interface.  Furthermore, all such keys
     * must be <i>mutually comparable</i>: k1.compareTo(k2) must not
     * throw a typeMismatchException for any elements k1 and k2 in the
     * TreeMap.  If the user attempts to put a key into the TreeMap that
     * violates this constraint (for example, the user attempts to put a String
     * key into a TreeMap whose keys are Integers), the put(Object key,
     * Object value) call will throw a ClassCastException.
     *
     * @since JDK1.2
     * @see Comparable
     */
    public TreeMap() {
    }

    /**
     * Constructs a new, empty TreeMap, sorted according to the given
     * comparator.  All keys inserted into the TreeMap must be <i>mutually
     * comparable</i> by the given comparator: comparator.compare(k1,
     * k2) must not throw a typeMismatchException for any keys k1 and k2
     * in the TreeMap.  If the user attempts to put a key into the
     * TreeMap that violates this constraint, the put(Object key, Object
     * value) call will throw a ClassCastException.
     *
     * @since JDK1.2
     */
    public TreeMap(Comparator c) {
        this.comparator = c;
    }

    /**
     * Constructs a new TreeMap containing the same mappings as the given
     * Map, sorted according to the keys' <i>natural sort method</i>.  All
     * keys inserted into the TreeMap must implement the Comparable
     * interface.  Furthermore, all such keys must be <i>mutually
     * comparable</i>: k1.compareTo(k2) must not throw a
     * typeMismatchException for any elements k1 and k2 in the TreeMap.
     *
     * @exception ClassCastException the keys in t are not Comparable, or
     *                  are not mutually comparable.
     * @since JDK1.2
     */
    public TreeMap(Map t) {
        putAll(t);
    }

    // Query Operations

    /**
     * Returns the number of key-value mappings in this TreeMap.
     *
     * @since JDK1.2
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this TreeMap contains a mapping for the specified key.
     *
     * @param key key whose presence in this Map is to be tested.
     * @exception ClassCastException key cannot be compared with the keys
     *                  currently in the TreeMap.
     * @exception NullPointerException key is null and this TreeMap uses
     *                  natural sort method, or its comparator does not tolerate
     *                  null keys.
     * @since JDK1.2
     */
    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    /**
     * Returns the value to which this TreeMap maps the specified key.
     * Returns null if the TreeMap contains no mapping for this key.  A return
     * value of null does not <em>necessarily</em> indicate that the TreeMap
     * contains no mapping for the key; it's also possible that the TreeMap
     * explicitly maps the key to null.  The containsKey operation may be
     * used to distinguish these two cases.
     *
     * @param key key whose associated value is to be returned.
     * @exception ClassCastException key cannot be compared with the keys
     *                  currently in the TreeMap.
     * @exception NullPointerException key is null and this TreeMap uses
     *                  natural sort method, or its comparator does not tolerate
     *                  null keys.
     * @see #containsKey(Object)
     * @since JDK1.2
     */
    public Object get(Object key) {
        Entry p = getEntry(key);
        return (p==null ? null : p.value);
    }

    /**
     * Returns this TreeMap's Entry for the given key, or null if the TreeMap
     * does not contain an entry for the key.
     *
     * @exception ClassCastException key cannot be compared with the keys
     *                  currently in the TreeMap.
     * @exception NullPointerException key is null and this TreeMap uses
     *                  natural sort method, or its comparator does not tolerate
     *                  null keys.
     */
    private Entry getEntry(Object key) {
        Entry p = root;
        while (p != null) {
            int cmp = compare(key,p.key);
            if (cmp == 0)
                return p;
            else if (cmp < 0)
                p = p.left;
            else
                p = p.right;
        }
        return null;
    }

    /**
     * Gets the entry corresponding to the the specified key; if no such entry
     * exists, returns the entry for the least key greater than the specified
     * key; if no such entry exists (i.e., the greatest key in the Tree is less
     * than the specified key), returns null.
     */
    private Entry getCeilEntry(Object key) {
        Entry p = root;
        if (p==null)
            return null;

        while (true) {
            int cmp = compare(key, p.key);
            if (cmp == 0) {
                return p;
            } else if (cmp < 0) {
                if (p.left != null)
                    p = p.left;
                else
                    return p;
            } else {
                if (p.right != null) {
                    p = p.right;
                } else {
                    Entry parent = p.parent;
                    Entry ch = p;
                    while (parent != null && ch == parent.right) {
                        ch = parent;
                        parent = parent.parent;
                    }
                    return parent;
                }
            }
        }
    }

    /**
     * Associates the specified value with the specified key in this TreeMap.
     * If the TreeMap previously contained a mapping for this key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key, or null if there
     *               was no mapping for key.  A null return can also indicate that
     *               the TreeMap previously associated null with the specified key.
     * @exception ClassCastException key cannot be compared with the keys
     *                  currently in the TreeMap.
     * @exception NullPointerException key is null and this TreeMap uses
     *                  natural sort method, or its comparator does not tolerate
     *                  null keys.
     *
     * @since JDK1.2
     */
    public Object put(Object key, Object value) {
        Entry t = root;

        if (t == null) {
            incrementSize();
            root = new Entry(key, value, null);
            return null;
        }

        while (true) {
            int cmp = compare(key, t.key);
            if (cmp == 0) {
                return t.setValue(value);
            } else if (cmp < 0) {
                if (t.left != null) {
                    t = t.left;
                } else {
                    incrementSize();
                    t.left = new Entry(key, value, t);
                    fixAfterInsertion(t.left);
                    return null;
                }
            } else { // cmp > 0
                if (t.right != null) {
                    t = t.right;
                } else {
                    incrementSize();
                    t.right = new Entry(key, value, t);
                    fixAfterInsertion(t.right);
                    return null;
                }
            }
        }
    }

    /**
     * Removes the mapping for this key from this TreeMap if present.
     *
     * @return previous value associated with specified key, or null if there
     *               was no mapping for key.  A null return can also indicate that
     *               the TreeMap previously associated null with the specified key.
     * @exception ClassCastException key cannot be compared with the keys
     *                  currently in the TreeMap.
     * @exception NullPointerException key is null and this TreeMap uses
     *                  natural sort method, or its comparator does not tolerate
     *                  null keys.
     * @since JDK1.2
     */
    public Object remove(Object key) {
        Entry p = getEntry(key);
        if (p == null)
            return null;

        Object oldValue = p.value;
        decrementSize();
        deleteEntry(p);
        return oldValue;
    }

    /**
     * Removes all mappings from this TreeMap.
     *
     * @since JDK1.2
     */
    public void clear() {
        modCount++;
        size = 0;
        root = null;
    }


    // Views

    /**
     * These fields are initialized to contain an instance of the appropriate
     * view the first time this view is requested.  The views are stateless,
     * so there's no reason to create more than one of each.
     */
    private transient Set                keySet = null;
    private transient Collection        entries = null;
    private transient Collection        values = null;
    private transient Map                subMap = null;

    /**
     * Returns a Set view of the keys contained in this TreeMap.  The
     * Set's Iterator will return the keys in ascending order.  The Set is
     * backed by the TreeMap, so changes to the TreeMap are reflected in the
     * Set, and vice-versa.  The Set supports element removal, which removes
     * the corresponding mapping from the TreeMap, via the Iterator.remove,
     * Set.remove, removeAll retainAll, and clear operations.  It does not
     * support the add or addAll operations.
     *
     * @since JDK1.2
     */
    public Set keySet() {
        if (keySet == null) {
            keySet = new AbstractSet() {
                public com.chimu.jdk12.java.util.Iterator iterator() {
                    return new Iterator(KEYS);
                }

                public int size() {
                    return TreeMap.this.size();
                }

                public boolean remove(Object o) {
                    return TreeMap.this.remove(o) != null;
                }

                public void clear() {
                    TreeMap.this.clear();
                }
            };
        }
        return keySet;
    }

    /**
     * Returns a Collection view of the values contained in this TreeMap.
     * The Collection's iterator will return the values in the order that
     * their corresponding keys appear in the tree.  The Collection is
     * backed by the TreeMap, so changes to the TreeMap are reflected in
     * the Collection, and vice-versa.  The Collection supports element
     * removal, which removes the corresponding mapping from the TreeMap,
     * via the Iterator.remove, Collection.remove, removeAll, retainAll
     * and clear operations.  It does not support the add or addAll
     * operations.
     *
     * @since JDK1.2
     */
    public Collection values() {
        if (values == null)
            values = new CollectionView(VALUES);
        return values;
    }

    /**
     * Returns a Collection view of the mappings contained in this Map.
     * The Collection's Iterator will return the mappings in ascending Key
     * order.  Each element in the returned collection is a Map.Entry.
     * The Collection is backed by the TreeMap, so changes to the TreeMap
     * are reflected in the Collection, and vice-versa.  The Collection
     * supports element removal, which removes the corresponding mapping
     * from the TreeMap, via the Iterator.remove, Collection.remove,
     * removeAll, retainAll and clear operations.  It does not support the
     * add or addAll operations.
     *
     * @see   Map.Entry
     * @since JDK1.2
     */
    public Collection entries() {
        if (entries == null)
            entries = new CollectionView(ENTRIES);
        return entries;
    }

    private class CollectionView extends AbstractCollection {
        private int type;

        CollectionView(int type)             {this.type = type;}

        public com.chimu.jdk12.java.util.Iterator iterator() {return new Iterator(type);}

        public int size()                     {return TreeMap.this.size();}

        public void clear()                      {TreeMap.this.clear();}
    }

    /**
     * Returns a view of the portion of this TreeMap whose keys range
     * from fromKey, inclusive, to toKey, exclusive.  The returned Map
     * is backed by this TreeMap, so changes in the returned Map are
     * reflected in this TreeMap, and vice-versa.  The returned Map supports
     * all optional Map operations.  It does not support the subMap
     * operation, as it is not a TreeMap.
     *
     * @param fromKey low endpoint (inclusive) of the subMap.
     * @param fromKey high endpoint (exclusive) of the subMap.
     * @exception ClassCastException fromKey or toKey cannot be compared
     *                  with the keys currently in the TreeMap.
     * @exception NullPointerException fromKey or toKey is null and this
     *                  TreeMap uses natural sort method, or its comparator does
     *                  not tolerate null keys.
     * @exception IllegalArgumentException fromKey is greater than toKey.
     * @since JDK1.2
     */
    public Map subMap(Object fromKey, Object toKey) {
        return new SubMap(fromKey, toKey);
    }

    private class SubMap extends AbstractMap {
        private Object fromKey, toKey;

        private boolean inRange(Object key) {
            return compare(key, fromKey) >= 0 && compare(key, toKey) < 0;
        }

        SubMap(Object fromKey, Object toKey) {
            if (compare(fromKey, toKey) > 0)
                throw new IllegalArgumentException("fromKey > toKey");
            this.fromKey = fromKey;
            this.toKey = toKey;
        }

        public boolean isEmpty() {
            return entries().isEmpty();
        }

        public boolean containsKey(Object key) {
            return inRange(key) && TreeMap.this.containsKey(key);
        }

        public Object get(Object key) {
            if (!inRange(key))
                throw new IllegalArgumentException("Key out of range.");
            return TreeMap.this.get(key);
        }

        private transient Collection entries;

        public Collection entries() {
            if (entries==null)
                entries = new EntriesView();
            return entries;
        }

        private class EntriesView extends AbstractCollection {
            private transient int size = -1, sizeModCount;

            public int size() {
                if (size == -1 || sizeModCount != TreeMap.this.modCount) {
                    size = 0;  sizeModCount = TreeMap.this.modCount;
                    com.chimu.jdk12.java.util.Iterator i = iterator();
                    while (i.hasNext()) {
                        size++;
                        i.next();
                    }
                }
                return size;
            }

            public boolean isEmpty() {
                return !iterator().hasNext();
            }

            public boolean contains(Object o) {
                if (!(o instanceof Map.Entry))
                    return false;
                Map.Entry e = (Map.Entry)o;
                Object key = e.getKey();
                Object value = e.getValue();
                return inRange(key) &&
                    (value==null ?
                     TreeMap.this.get(key)==null && TreeMap.this.containsKey(key)
                     : value.equals(TreeMap.this.get(key)));
            }

            public boolean remove(Object o) {
                if (o==null || !(o instanceof Map.Entry))
                    return false;
                Map.Entry e = (Map.Entry)o;
                Entry node = getEntry(e.getKey());
                if (e.equals(node)) {
                    deleteEntry(node);
                    return true;
                } else {
                    return false;
                }
            }

            public com.chimu.jdk12.java.util.Iterator iterator() {
                return new Iterator(getCeilEntry(fromKey), getCeilEntry(toKey));
            }
        }
    }

    // Types of Iterators
    private static final int KEYS = 0;
    private static final int VALUES = 1;
    private static final int ENTRIES = 2;

    /**
     * TreeMap Iterator.
     */
    private class Iterator implements com.chimu.jdk12.java.util.Iterator {
        private int type;
        private int expectedModCount = TreeMap.this.modCount;
        private Entry lastReturned = null;
        private Entry next;
        private Entry firstExcluded = null;

        Iterator(int type) {
            this.type = type;
            next = firstEntry();
        }

        Iterator(Entry first, Entry firstExcluded) {
            this.type = ENTRIES;
            this.next = first;
            this.firstExcluded = firstExcluded;
        }

        public boolean hasNext() {
            return next != firstExcluded;
        }

        public Object next() {
            if (next == firstExcluded)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();

            lastReturned = next;
            next = successor(next);
            return (type == KEYS ? lastReturned.key :
                    (type == VALUES ? lastReturned.value : lastReturned));
        }

        public void remove() {
            if (lastReturned == null)
                throw new NoSuchElementException();
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();

            decrementSize();
            deleteEntry(lastReturned);
            expectedModCount++;
            lastReturned = null;
        }
    }

    /**
     * Compares two keys using the correct comparison method for this TreeMap.
     */
    private int compare(Object k1, Object k2) {
        return (comparator==null ? ((Comparable)k1).compareTo(k2)
                                 : comparator.compare(k1, k2));
    }

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    /**
     * Node in the Tree.  Doubles as a means to pass key-value pairs back to
     * user (see Map.Entry).
     */

    static class Entry implements Map.Entry {
        Object key;
        Object value;
        Entry left = null;
        Entry right = null;
        Entry parent;
        boolean color = BLACK;

        /**
         * Make a new cell with given key, value, and parent, and with null
         * child links, and BLACK color.
         */
        Entry(Object key, Object value, Entry parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Returns the key.
         */
        public Object getKey() {
            return key;
        }

        /**
         * Returns the value associated with the key.
         */
        public Object getValue() {
            return value;
        }

        /**
         * Replaces the value currently associated with the key with the given
         * value, and returns the old value.
         */
        public Object setValue(Object v)  {
            Object oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry e = (Map.Entry)o;

            return (key==null ? e.getKey()==null : key.equals(e.getKey())) &&
               (value==null ? e.getValue()==null : value.equals(e.getValue()));
        }

        public int hashCode() {
            int keyHash = (key==null ? 0 : key.hashCode());
            int valueHash = (value==null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }

    /**
     * Returns the first Entry in the TreeMap (according to the TreeMap's
     * key-sort function).  Returns null if the TreeMap is empty.
     */
    private Entry firstEntry() {
        Entry p = root;
        if (p != null)
            while (p.left != null)
                p = p.left;
        return p;
    }

    /**
     * Returns the successor of the specified Entry, or null if no such.
     */
    private Entry successor(Entry t) {
        if (t == null)
            return null;
        else if (t.right != null) {
            Entry p = t.right;
            while (p.left != null)
                p = p.left;
            return p;
        } else {
            Entry p = t.parent;
            Entry ch = t;
            while (p != null && ch == p.right) {
                ch = p;
                p = p.parent;
            }
            return p;
        }
    }

    /**
     * Balancing operations.
     *
     * Implementations of rebalancings during insertion and deletion are slightly
     * different than the CLR version.  Rather than using dummy nilnodes, we use
     * a set of accessors that deal properly with null.  They are used to avoid
     * messiness surrounding nullness checks in the main algorithms.
     */

    private static boolean colorOf(Entry p) {
        return (p == null ? BLACK : p.color);
    }

    private static Entry  parentOf(Entry p) {
        return (p == null ? null: p.parent);
    }

    private static void setColor(Entry p, boolean c) {
        if (p != null)  p.color = c;
    }

    private static Entry  leftOf(Entry p) {
        return (p == null)? null: p.left;
    }

    private static Entry  rightOf(Entry p) {
        return (p == null)? null: p.right;
    }

    /** From CLR **/
    private void rotateLeft(Entry p) {
        Entry r = p.right;
        p.right = r.left;
        if (r.left != null)
            r.left.parent = p;
        r.parent = p.parent;
        if (p.parent == null)
            root = r;
        else if (p.parent.left == p)
            p.parent.left = r;
        else
            p.parent.right = r;
        r.left = p;
        p.parent = r;
    }

    /** From CLR **/
    private void rotateRight(Entry p) {
        Entry l = p.left;
        p.left = l.right;
        if (l.right != null) l.right.parent = p;
        l.parent = p.parent;
        if (p.parent == null)
            root = l;
        else if (p.parent.right == p)
            p.parent.right = l;
        else p.parent.left = l;
        l.right = p;
        p.parent = l;
    }


    /** From CLR **/
    private void fixAfterInsertion(Entry x) {
        x.color = RED;

        while (x != null && x != root && x.parent.color == RED) {
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                Entry y = rightOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == rightOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    if (parentOf(parentOf(x)) != null)
                        rotateRight(parentOf(parentOf(x)));
                }
            } else {
                Entry y = leftOf(parentOf(parentOf(x)));
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);
                    }
                    setColor(parentOf(x),  BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    if (parentOf(parentOf(x)) != null)
                        rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }

    /**
     * Delete node p, and then rebalance the tree.
     */
    private void deleteEntry(Entry p) {
        // If strictly internal, first swap position with successor.
        if (p.left != null && p.right != null) {
            Entry s = successor(p);
            swapPosition(s, p);
        }

        // Start fixup at replacement node, if it exists.
        Entry replacement = (p.left != null ? p.left : p.right);

        if (replacement != null) {
            // Link replacement to parent
            replacement.parent = p.parent;
            if (p.parent == null)
                root = replacement;
            else if (p == p.parent.left)
                p.parent.left  = replacement;
            else
                p.parent.right = replacement;

            // Null out links so they are OK to use by fixAfterDeletion.
            p.left = p.right = p.parent = null;

            // Fix replacement
            if (p.color == BLACK)
                fixAfterDeletion(replacement);
        } else if (p.parent == null) { // return if we are the only node.
            root = null;
        } else { //  No children. Use self as phantom replacement and unlink.
            if (p.color == BLACK)
                fixAfterDeletion(p);

            if (p.parent != null) {
                if (p == p.parent.left)
                    p.parent.left = null;
                else if (p == p.parent.right)
                    p.parent.right = null;
                p.parent = null;
            }
        }
    }

    /** From CLR **/
    private void fixAfterDeletion(Entry x) {
        while (x != root && colorOf(x) == BLACK) {
            if (x == leftOf(parentOf(x))) {
                Entry sib = rightOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateLeft(parentOf(x));
                    sib = rightOf(parentOf(x));
                }

                if (colorOf(leftOf(sib))  == BLACK &&
                    colorOf(rightOf(sib)) == BLACK) {
                    setColor(sib,  RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(rightOf(sib)) == BLACK) {
                        setColor(leftOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = rightOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(rightOf(sib), BLACK);
                    rotateLeft(parentOf(x));
                    x = root;
                }
            } else { // symmetric
                Entry sib = leftOf(parentOf(x));

                if (colorOf(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parentOf(x), RED);
                    rotateRight(parentOf(x));
                    sib = leftOf(parentOf(x));
                }

                if (colorOf(rightOf(sib)) == BLACK &&
                    colorOf(leftOf(sib)) == BLACK) {
                    setColor(sib,  RED);
                    x = parentOf(x);
                } else {
                    if (colorOf(leftOf(sib)) == BLACK) {
                        setColor(rightOf(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = leftOf(parentOf(x));
                    }
                    setColor(sib, colorOf(parentOf(x)));
                    setColor(parentOf(x), BLACK);
                    setColor(leftOf(sib), BLACK);
                    rotateRight(parentOf(x));
                    x = root;
                }
            }
        }

        setColor(x, BLACK);
    }

    /**
     * Swap the linkages of two nodes in a tree.
     */
    private void swapPosition(Entry x, Entry y) {
        // Save initial values.
        Entry px = x.parent, lx = x.left, rx = x.right;
        Entry py = y.parent, ly = y.left, ry = y.right;
        boolean xWasLeftChild = px != null && x == px.left;
        boolean yWasLeftChild = py != null && y == py.left;

        // Swap, handling special cases of one being the other's parent.
        if (x == py) {  // x was y's parent
            x.parent = y;
            if (yWasLeftChild) {
                y.left = x;
                y.right = rx;
            } else {
                y.right = x;
                y.left = lx;
            }
        } else {
            x.parent = py;
            if (py != null) {
                if (yWasLeftChild)
                    py.left = x;
                else
                    py.right = x;
            }
            y.left = lx;
            y.right = rx;
        }

        if (y == px) { // y was x's parent
            y.parent = x;
            if (xWasLeftChild) {
                x.left = y;
                x.right = ry;
            } else {
                x.right = y;
                x.left = ly;
            }
        } else {
            y.parent = px;
            if (px != null) {
                if (xWasLeftChild)
                    px.left = y;
                else
                    px.right = y;
            }
            x.left = ly;
            x.right = ry;
        }

        // Fix children's parent pointers
        if (x.left != null)
            x.left.parent = x;
        if (x.right != null)
            x.right.parent = x;
        if (y.left != null)
            y.left.parent = y;
        if (y.right != null)
            y.right.parent = y;

        // Swap colors
        boolean c = x.color;
        x.color = y.color;
        y.color = c;

        // Check if root changed
        if (root == x)
            root = y;
        else if (root == y)
            root = x;
    }
}
