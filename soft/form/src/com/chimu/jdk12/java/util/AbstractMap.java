/*======================================================================
**
**  File: chimu/jdk12/java/util/AbstractMap.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.jdk12.java.util;
import com.chimu.jdk12.java.util.Map.Entry;

/**
 * This class provides a skeletal implementation of the Map interface,
 * to minimize the effort required to implement this interface.
 * <p>
 * To implement an unmodifiable Map, the programmer needs only to
 * extend this class and provide an implementation for the entries()
 * method, which returns a Collection-view of the Map's mappings.
 * Typically, the returned Collection will, in turn, be implemented atop
 * AbstractCollection.  This Collection should not support the add or
 * remove methods, and its Iterator should not support the remove method.
 * <p>
 * To implement a modifiable Map, the programmer must additionally
 * override this class's put method (which otherwise throws an
 * UnsupportedOperationException), and the Iterator returned by
 * entries().iterator() must additionally implement its remove method.
 * <p>
 * The programmer should generally provide a void (no argument) and
 * Map constructor, as per the recommendation in the Map interface
 * specification.
 * <p>
 * The documentation for each non-abstract methods in this class describes its
 * implementation in detail.  Each of these methods may be overridden if
 * the Collection being implemented admits a more efficient implementation.
 *
 * @author  Josh Bloch
 * @version 1.9 10/28/97
 * @see Map
 * @see Collection
 * @since JDK1.2
 */

public abstract class AbstractMap implements Map {
    // Query Operations

    /**
     * Returns the number of key-value mappings in this Map.
     * <p>
     * This implementation returns <code>entries().size()</code>.
     *
     * @since JDK1.2
     */
    public int size() {
        return entries().size();
    }

    /**
     * Returns true if this Map contains no key-value mappings.
     * <p>
     * This implementation returns <code>size() == 0</code>.
     *
     * @since JDK1.2
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this Map maps one or more keys to this value.
     * More formally, returns true if and only if this Map contains at
     * least one mapping to a value <code>v</code> such that
     * <code>(value==null ? v==null : value.equals(v))</code>.
     * This operation will probably require time linear in the Map size for
     * most implementations of Map.
     * <p>
     * This implementation iterates over entries() searching for an Entry
     * with the specified value.  If such an Entry is found, true is
     * returned.  If the iteration terminates without finding such an
     * Entry, false is returned.  Note that this implementation requires
     * linear time in the size of the Map.
     *
     * @param value value whose presence in this Map is to be tested.
     * @since JDK1.2
     */
    public boolean containsValue(Object value) {
        Iterator i = entries().iterator();
        if (value==null) {
            while (i.hasNext()) {
                Entry e = (Entry) i.next();
                if (e.getValue()==null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry e = (Entry) i.next();
                if (value.equals(e.getValue()))
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns true if this Map contains a mapping for the specified key.
     * <p>
     * This implementation iterates over entries() searching for an Entry
     * with the specified key.  If such an Entry is found, true is
     * returned.  If the iteration terminates without finding such an
     * Entry, false is returned.  Note that this implementation requires
     * linear time in the size of the Map; many implementations will
     * override this method.
     *
     * @param key key whose presence in this Map is to be tested.
     * @exception ClassCastException key is of an inappropriate type for
     *                   this Map.
     * @exception NullPointerException key is null and this Map does not
     *                  not permit null keys.
     * @since JDK1.2
     */
    public boolean containsKey(Object key) {
        Iterator i = entries().iterator();
        if (key==null) {
            while (i.hasNext()) {
                Entry e = (Entry) i.next();
                if (e.getKey()==null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry e = (Entry) i.next();
                if (key.equals(e.getKey()))
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which this Map maps the specified key.
     * Returns null if the Map contains no mapping for this key.  A return
     * value of null does not <em>necessarily</em> indicate that the Map
     * contains no mapping for the key; it's also possible that the Map
     * explicitly maps the key to null.  The containsKey operation may be
     * used to distinguish these two cases.
     * <p>
     * This implementation iterates over entries() searching for an Entry
     * with the specified key.  If such an Entry is found, the Entry's
     * value is returned.  If the iteration terminates without finding such
     * an Entry, null is returned.  Note that this implementation requires
     * linear time in the size of the Map; many implementations will
     * override this method.
     *
     * @param key key whose associated value is to be returned.
     * @exception ClassCastException key is of an inappropriate type for
     *                   this Map.
     * @exception NullPointerException key is null and this Map does not
     *                  not permit null keys.
     * @see #containsKey(Object)
     * @since JDK1.2
     */
    public Object get(Object key) {
        Iterator i = entries().iterator();
        if (key==null) {
            while (i.hasNext()) {
                Entry e = (Entry) i.next();
                if (e.getKey()==null)
                    return e.getValue();
            }
        } else {
            while (i.hasNext()) {
                Entry e = (Entry) i.next();
                if (key.equals(e.getKey()))
                    return e.getValue();
            }
        }
        return null;
    }


    // Modification Operations

    /**
     * Associates the specified value with the specified key in this Map
     * (optional operation).  If the Map previously contained a mapping for
     * this key, the old value is replaced.
     * <p>
     * This implementation always throws an UnsupportedOperationException.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     * @return previous value associated with specified key, or null if there
     *               was no mapping for key.  (A null return can also indicate that
     *               the Map previously associated null with the specified key,
     *               if the implementation supports null values.)
     * @exception UnsupportedOperationException put operation is not
     *                  supported by this Map.
     * @exception ClassCastException class of the specified key or value
     *                   prevents it from being stored in this Map.
     * @exception IllegalArgumentException some aspect of this key or value
     *                  prevents it from being stored in this Map.
     * @exception NullPointerException this Map does not permit null keys
     *                  or values, and the specified key or value is null.
     * @since JDK1.2
     */
    public Object put(Object key, Object value) {
        throw new com.chimu.jdk12.java.lang.UnsupportedOperationException();
    }

    /**
     * Removes the mapping for this key from this Map if present (optional
     * operation).
     * <p>
     * This implementation iterates over entries() searching for an Entry
     * with the specified key.  If such an Entry is found, its value is
     * obtained with its getValue operation, the Entry is is removed from the
     * Collection (and the backing Map) with the Iterator's remove
     * operation, and the saved value is returned.  If the iteration
     * terminates without finding such an Entry, null is returned.  Note that
     * this implementation requires linear time in the size of the Map;
     * many implementations will override this method.
     *
     * @param key key whose mapping is to be removed from the Map.
     * @return previous value associated with specified key, or null if there
     *               was no entry for key.  (A null return can also indicate that
     *               the Map previously associated null with the specified key,
     *               if the implementation supports null values.)
     * @exception UnsupportedOperationException remove is not supported
     *                   by this Map.
     * @since JDK1.2
     */
    public Object remove(Object key) {
        Iterator i = entries().iterator();
        Entry correctEntry = null;
        if (key==null) {
            while (correctEntry==null && i.hasNext()) {
                Entry e = (Entry) i.next();
                if (e.getKey()==null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry==null && i.hasNext()) {
                Entry e = (Entry) i.next();
                if (key.equals(e.getKey()))
                    correctEntry = e;
            }
        }

        Object oldValue = null;
        if (correctEntry !=null) {
            oldValue = correctEntry.getValue();
            i.remove();
        }
        return oldValue;
    }


    // Bulk Operations

    /**
     * Copies all of the mappings from the specified Map to this Map
     * (optional operation).  These mappings will replace any mappings that
     * this Map had for any of the keys currently in the specified Map.
     * <p>
     * This implementation iterates over the specified Map's entries()
     * Collection, and calls this Map's put operation once for each
     * Entry returned by the iteration.
     *
     * @param t Mappings to be stored in this Map.
     * @exception UnsupportedOperationException putAll is not supported
     *                   by this Map.
     * @exception ClassCastException class of a key or value in the specified
     *                   Map prevents it from being stored in this Map.
     * @exception IllegalArgumentException some aspect of a key or value in the
     *                  specified Map prevents it from being stored in this Map.
     * @exception NullPointerException this Map does not permit null keys
     *                  or values, and the specified key or value is null.
     * @since JDK1.2
     */
    public void putAll(Map t) {
        Iterator i = t.entries().iterator();
        while (i.hasNext()) {
            Entry e = (Entry) i.next();
            put(e.getKey(), e.getValue());
        }
    }

    /**
     * Removes all mappings from this Map (optional operation).
     * <p>
     * This implementation calls <code>entries().clear()</code>.
     *
     * @exception UnsupportedOperationException clear is not supported
     *                   by this Map.
     * @since JDK1.2
     */
    public void clear() {
        entries().clear();
    }


    // Views

    private transient Set keySet = null;
    /**
     * Returns a Set view of the keys contained in this Map.  The Set is
     * backed by the Map, so changes to the Map are reflected in the Set,
     * and vice-versa.  (If the Map is modified while while an iteration over
     * the Set is in progress, the results of the iteration are undefined.)
     * The Set supports element removal, which removes the corresponding entry
     * from the Map, via the Iterator.remove, Set.remove,  removeAll
     * retainAll, and clear operations.  It does not support the add or
     * addAll operations.
     * <p>
     * This implementation returns a Set that subclasses
     * AbstractSet.  The subclass's iterator method returns a "wrapper
     * object" over this Map's entries() Iterator.  The size method delegates
     * to this Map's size method.
     * <p>
     * The Set is created the first time this method is called,
     * and returned in response to all subsequent calls.  No synchronization
     * is performed, so there is a slight chance that multiple calls to this
     * method will not all return the same Set.
     *
     * @since JDK1.2
     */
    public Set keySet() {
        if (keySet == null) {
            keySet = new AbstractSet() {
                public Iterator iterator() {
                    return new Xxx();//COMPILER BUG
                }

                public int size() {
                    return Map.this.size();
                }
            };
        }
        return keySet;
    }
                    class Xxx implements Iterator {
                        private Iterator i = entries().iterator();

                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        public Object next() {
                            return ((Entry)i.next()).getKey();
                        }

                        public void remove() {
                            i.remove();
                        }
                    }

    private transient Collection values = null;
    /**
     * Returns a Collection view of the values contained in this Map.  The
     * Collection is backed by the Map, so changes to the Map are
     * reflected in the Collection, and vice-versa.  (If the Map is
     * modified while while an iteration over the Collection is in progress,
     * the results of the iteration are undefined.)  The Collection supports
     * element removal, which removes the corresponding entry from the
     * Map, via the Iterator.remove, Collection.remove, removeAll,
     * retainAll and clear operations.  It does not support the add or
     * addAll operations.
     * <p>
     * This implementation returns a Collection that subclasses
     * AbstractCollection.  The subclass's iterator method returns a "wrapper
     * object" over this Map's entries() Iterator.  The size method delegates
     * to this Map's size method.
     * <p>
     * The Collection is created the first time this method is called,
     * and returned in response to all subsequent calls.  No synchronization
     * is performed, so there is a slight chance that multiple calls to this
     * method will not all return the same Collection.
     *
     * @since JDK1.2
     */
    public Collection values() {
        if (values == null) {
            values = new AbstractCollection() {
                public Iterator iterator() {
                    return new Xxxx();//COMPILER BUG
                }

                public int size() {
                    return Map.this.size();
                }
            };
        }
        return values;
    }
                    class Xxxx implements Iterator {
                        private Iterator i = entries().iterator();

                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        public Object next() {
                            return ((Entry)i.next()).getValue();
                        }

                        public void remove() {
                            i.remove();
                        }
                    }

    /**
     * Returns a Collection view of the mappings contained in this Map.
     * Each element in this collection is a Map.Entry.  The Collection is
     * backed by the Map, so changes to the Map are reflected in the
     * Collection, and vice-versa.  (If the Map is modified while while an
     * iteration over the Collection is in progress, the results of the
     * iteration are undefined.)  The Collection supports element removal,
     * which removes the corresponding entry from the Map, via the
     * Iterator.remove, Collection.remove, removeAll, retainAll and clear
     * operations.  It does not support the add or addAll operations.
     *
     * @since JDK1.2
     */
    public abstract Collection entries();


    // Comparison and hashing

    /**
     * Compares the specified Object with this Map for equality.
     * Returns true if the given object is also a Map and the two
     * Maps represent the same mappings.  More formally, two
     * Maps <code>t1</code> and <code>t2</code> represent the same mappings
     * if <code>t1.keySet().equals(t2.keySet())</code> and for every
     * key <code>k</code> in <code>t1.keySet()</code>, <code>
     * (t1.get(k)==null ? t2.get(k)==null : t1.get(k).equals(t2.get(k)))
     * </code>.  This ensures that the equals method works properly across
     * different implementations of the Map interface.
     * <p>
     * This implementation first checks if the specified Object is this Map;
     * if so it returns true.  Then, it checks if the specified Object is
     * a Map whose size is identical to the size of this Set; if not, it
     * it returns false.  If so, it iterates over this Map's entries()
     * Collection, and checks that the specified Map contains each
     * mapping that this Map contains.  If the specified Map fails
     * to contain such a mapping, false is returned.  If the iteration
     * completes, true is returned.
     *
     * @param o Object to be compared for equality with this Map.
     * @return true if the specified Object is equal to this Map.
     * @since JDK1.2
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Map))
            return false;
        Map t = (Map) o;
        if (t.size() != size())
            return false;

        Iterator i = entries().iterator();
        while (i.hasNext()) {
            Entry e = (Entry) i.next();
            Object key = e.getKey();
            Object value = e.getValue();
            if (value == null) {
                if (!(t.get(key)==null && t.containsKey(key)))
                    return false;
            } else {
                if (!value.equals(t.get(key)))
                    return false;
            }
        }
        return true;
    }

    /**
     * Returns the hash code value for this Map.  The hash code of a Map
     * is defined to be the sum of the hashCodes of each Entry in the Map's
     * entries() view.  This ensures that <code>t1.equals(t2)</code> implies
     * that <code>t1.hashCode()==t2.hashCode()</code> for any two Maps
     * <code>t1</code> and <code>t2</code>, as required by the general
     * contract of Object.hashCode.
     * <p>
     * This implementation iterates over entries(), calling hashCode
     * on each element (Entry) in the Collection, and adding up the results.
     *
     * @see Entry#hashCode()
     * @see Object#hashCode()
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     * @since JDK1.2
     */
    public int hashCode() {
        int h = 0;
        Iterator i = entries().iterator();
        while (i.hasNext())
            h += i.next().hashCode();
        return h;
    }

    /**
     * Returns a String representation of this Map.
     *
     * @since   JDK1.2
     */
    public String toString() {
        int max = size() - 1;
        StringBuffer buf = new StringBuffer();
        Iterator i = entries().iterator();

        buf.append("{");
        for (int j = 0; j <= max; j++) {
            Entry e = (Entry) (i.next());
            buf.append(e.getKey() + "=" + e.getValue());
            if (j < max)
                buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }
}
