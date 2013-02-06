/*======================================================================
**
**  File: chimu/kernel/collections/impl/sun/WeakHashtableC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl.sun;
import java.util.*;
import java.io.*;
import sun.misc.Ref;

/**
 *  A WeakReferenceDictionary allows a key to object mapping where the objects
 *  are held onto weakly.
 */

/**
 *  This class is implemented by converting the HashtableEntry to a weak form
 *  and then handling the case where the entry's value is nil
 */

/*
 * @(#)Hashtable.java   1.40 96/11/23
 *
 * Copyright (c) 1995, 1996 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 * CopyrightVersion 1.1_beta
 *
 */


/**
 * Hashtable collision list.
 */
class WeakHashtableEntryC extends Ref {
    int hash;
    Object key;
    WeakHashtableEntryC next;

    protected Object clone() {
        WeakHashtableEntryC entry = new WeakHashtableEntryC();
        entry.hash = hash;
        entry.key = key;
        entry.value(value());
        entry.next = (next != null) ? (WeakHashtableEntryC)next.clone() : null;
        return entry;
    }

    public Object value() {
        return check();
    };

    public void value(Object value) {
        this.setThing(value);
    };

    public boolean hasValue() {
        return check() != null;
    };

    public Object reconstitute() {
        return null;
    };

}

/**
 * Hashtable class. Maps keys to values. Any object can be used as
 * a key and/or value.<p>
 *
 * To sucessfully store and retrieve objects from a hash table, the
 * object used as the key must implement the hashCode() and equals()
 * methods.<p>
 *
 * This example creates a hashtable of numbers. It uses the names of
 * the numbers as keys:
 * <pre>
 *      Hashtable numbers = new Hashtable();
 *      numbers.put("one", new Integer(1));
 *      numbers.put("two", new Integer(2));
 *      numbers.put("three", new Integer(3));
 * </pre>
 * To retrieve a number use:
 * <pre>
 *      Integer n = (Integer)numbers.get("two");
 *      if (n != null) {
 *          System.out.println("two = " + n);
 *      }
 * </pre>
 *
 * @see java.lang.Object#hashCode
 * @see java.lang.Object#equals
 * @version     1.40, 23 Nov 1996
 * @author      Arthur van Hoff
 */
public
class WeakHashtableC extends Dictionary implements Cloneable, java.io.Serializable {
    /**
     * The hash table data.
     */
    private transient WeakHashtableEntryC table[];

    /**
     * The total number of entries in the hash table.
     */
    private transient int count;

    /**
     * Rehashes the table when count exceeds this threshold.
     */
    private int threshold;

    /**
     * The load factor for the hashtable.
     */
    private float loadFactor;

    /**
     * Constructs a new, empty hashtable with the specified initial
     * capacity and the specified load factor.
     * @param initialCapacity the initial number of buckets
     * @param loadFactor a number between 0.0 and 1.0, it defines
     *          the threshold for rehashing the hashtable into
     *          a bigger one.
     * @exception IllegalArgumentException If the initial capacity
     * is less than or equal to zero.
     * @exception IllegalArgumentException If the load factor is
     * less than or equal to zero.
     */
    public WeakHashtableC(int initialCapacity, float loadFactor) {
        if ((initialCapacity <= 0) || (loadFactor <= 0.0)) {
            throw new IllegalArgumentException();
        }
        this.loadFactor = loadFactor;
        table = new WeakHashtableEntryC[initialCapacity];
        threshold = (int)(initialCapacity * loadFactor);
    }

    /**
     * Constructs a new, empty hashtable with the specified initial
     * capacity.
     * @param initialCapacity the initial number of buckets
     */
    public WeakHashtableC(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    /**
     * Constructs a new, empty hashtable. A default capacity and load factor
     * is used. Note that the hashtable will automatically grow when it gets
     * full.
     */
    public WeakHashtableC() {
        this(101, 0.75f);
    }

    /**
     * Returns the number of elements contained in the hashtable.
     */
    public int size() {
        return count;
    }

    /**
     * Returns true if the hashtable contains no elements.
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns an enumeration of the hashtable's keys.
     * @see Hashtable#elements
     * @see Enumeration
     */
    public synchronized Enumeration keys() {
        return new WeakHashtableEnumeratorC(table, true);
    }

    /**
     * Returns an enumeration of the elements. Use the Enumeration methods
     * on the returned object to fetch the elements sequentially.
     * @see Hashtable#keys
     * @see Enumeration
     */
    public synchronized Enumeration elements() {
        return new WeakHashtableEnumeratorC(table, false);
    }

    /**
     * Returns true if the specified object is an element of the hashtable.
     * This operation is more expensive than the containsKey() method.
     * @param value the value that we are looking for
     * @exception NullPointerException If the value being searched
     * for is equal to null.
     * @see Hashtable#containsKey
     */
    public synchronized boolean contains(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        WeakHashtableEntryC tab[] = table;
        for (int i = tab.length ; i-- > 0 ;) {
            for (WeakHashtableEntryC e = tab[i] ; e != null ; e = e.next) {
                if (e.hasValue()) {
                    if (e.value().equals(value)) {
                        return true;
                    }
                };
            }
        }
        return false;
    }

    /**
     * Returns true if the collection contains an element for the key.
     * @param key the key that we are looking for
     * @see Hashtable#contains
     */
    public synchronized boolean containsKey(Object key) {
        WeakHashtableEntryC tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (WeakHashtableEntryC e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return e.hasValue();
            }
        }
        return false;
    }

    /**
     * Gets the object associated with the specified key in the
     * hashtable.
     * @param key the specified key
     * @returns the element for the key or null if the key
     *          is not defined in the hash table.
     * @see Hashtable#put
     */
    public synchronized Object get(Object key) {
        WeakHashtableEntryC tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (WeakHashtableEntryC e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                if (e.hasValue()) {
                    return e.value();
                } else {
                    return null;
                };
            }
        }
        return null;
    }

    /**
     * Rehashes the content of the table into a bigger table.
     * This method is called automatically when the hashtable's
     * size exceeds the threshold.
     */
    protected void rehash() {
        int oldCapacity = table.length;
        WeakHashtableEntryC oldTable[] = table;

        int newCapacity = oldCapacity * 2 + 1;
        WeakHashtableEntryC newTable[] = new WeakHashtableEntryC[newCapacity];

        threshold = (int)(newCapacity * loadFactor);
        table = newTable;

        //System.out.println("rehash old=" + oldCapacity + ", new=" + newCapacity + ", thresh=" + threshold + ", count=" + count);

        //MLF: Reset the count so we know how many entries are really in the new table
        count = 0;
        for (int i = oldCapacity ; i-- > 0 ;) {
            WeakHashtableEntryC old = oldTable[i];
            while (old != null) {
                WeakHashtableEntryC e = old;
                old = old.next;

                if (e.hasValue()) {
                    int index = (e.hash & 0x7FFFFFFF) % newCapacity;
                    e.next = newTable[index];
                    newTable[index] = e;
                    count++;
                };
            }
        }
    }

    /**
     * Puts the specified element into the hashtable, using the specified
     * key.  The element may be retrieved by doing a get() with the same key.
     * The key and the element cannot be null.
     * @param key the specified key in the hashtable
     * @param value the specified element
     * @exception NullPointerException If the value of the element
     * is equal to null.
     * @see Hashtable#get
     * @return the old value of the key, or null if it did not have one.
     */
    public synchronized Object put(Object key, Object value) {
        // Make sure the value is not null
        if (value == null) {
            return remove(key); //MLF960323
        }

        // Makes sure the key is not already in the hashtable.
        WeakHashtableEntryC tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (WeakHashtableEntryC e = tab[index] ; e != null ; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                Object old = e.value();
                e.value(value);
                return old;
            }
        }

        if (count >= threshold) {
            // Rehash the table if the threshold is exceeded
            rehash();
            return put(key, value);
        }

        // Creates the new entry.
        WeakHashtableEntryC e = new WeakHashtableEntryC();
        e.hash = hash;
        e.key = key;
        e.value(value);
        e.next = tab[index];
        tab[index] = e;
        count++;
        return null;
    }

    /**
     * Removes the element corresponding to the key. Does nothing if the
     * key is not present.
     * @param key the key that needs to be removed
     * @return the value of key, or null if the key was not found.
     */
    public synchronized Object remove(Object key) {
        WeakHashtableEntryC tab[] = table;
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % tab.length;
        for (WeakHashtableEntryC e = tab[index], prev = null ; e != null ; prev = e, e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                if (prev != null) {
                    prev.next = e.next;
                } else {
                    tab[index] = e.next;
                }
                count--;
                return e.value();
            }
        }
        return null;
    }

    /**
     * Clears the hash table so that it has no more elements in it.
     */
    public synchronized void clear() {
        WeakHashtableEntryC tab[] = table;
        for (int index = tab.length; --index >= 0; )
            tab[index] = null;
        count = 0;
    }

    /**
     * Creates a clone of the hashtable. A shallow copy is made,
     * the keys and elements themselves are NOT cloned. This is a
     * relatively expensive operation.
     */
    public synchronized Object clone() {
        try {
            WeakHashtableC t = (WeakHashtableC) super.clone();
            t.table = new WeakHashtableEntryC[table.length];
            for (int i = table.length ; i-- > 0 ; ) {
                t.table[i] = (table[i] != null)
                    ? (WeakHashtableEntryC) table[i].clone() : null;
            }
            return t;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError();
        }
    }

    /**
     * Converts to a rather lengthy String.
     */
    public synchronized String toString() {
        int max = size() - 1;
        StringBuffer buf = new StringBuffer();
        Enumeration k = keys();
        Enumeration e = elements();
        buf.append("{");

        for (int i = 0; i <= max; i++) {
            String s1 = k.nextElement().toString();
            String s2 = e.nextElement().toString();
            buf.append(s1 + "=" + s2);
            if (i < max) {
                buf.append(", ");
            }
        }
        buf.append("}");
        return buf.toString();
    }

    static public void main(String[] args) {
        Dictionary test1 = new WeakHashtableC();
        Dictionary test2 = new Hashtable();

        for (int i = 1;i < 10000; i++) {
            test1.put(new Integer(i),(new Integer(i)).toString());
            if ((i % 200) == 0) {
                System.out.print(test1.size());
            };
        };

        for (Enumeration enum = test1.elements(); enum.hasMoreElements();) {
            System.out.print(":"+((String) enum.nextElement()));
        };
        System.out.println(test1.size());

        for (int i = 1;i < 10000; i++) {
            test2.put(new Integer(i),(new Integer(i)).toString());
            if ((i % 200) == 0) {
                System.out.print(test2.size());
            };
        };

        for (Enumeration enum = test2.elements(); enum.hasMoreElements();) {
            System.out.print(":"+((String) enum.nextElement()));
        };

    };
}

/**
 * A hashtable enumerator class.  This class should remain opaque
 * to the client. It will use the Enumeration interface.
 */
class WeakHashtableEnumeratorC implements Enumeration {
    boolean keys;
    int index;
    WeakHashtableEntryC table[];
    WeakHashtableEntryC entry;
    Object entryValue;  // This makes sure that the value isn't GC while the enumerator is pointing to the element

    WeakHashtableEnumeratorC(WeakHashtableEntryC table[], boolean keys) {
        this.table = table;
        this.keys = keys;
        this.index = table.length;
        unlockEntryValue();
    }

    public boolean hasMoreElements() {
        if (entry != null) {
            return true;
        };
        this.skipToNextElement();
        return (entry != null);
    }

    private void skipToNextElement() {
        Object value;
        while (index-- > 0) {
            entry = table[index];
            while (entry != null) {
                if ((value = entry.value()) != null) {
                    lockEntryValue(value);
                    return;
                };
                entry = entry.next;
            };
        };
        unlockEntryValue();
    };

    private void skipToNextElementInBucket() {
        Object value;
        entry = entry.next;
        while (entry != null) {
            if ((value = entry.value()) != null) {
                lockEntryValue(value);
                return;
            };
            entry = entry.next;
        };
        unlockEntryValue();  // we are now pointing to null, so release the old value
    };


     private void lockEntryValue(Object value) {entryValue = value;};
     private void unlockEntryValue() {entryValue = null;};

    // Note that I am relying that entry is set to non-null whenever an external person
    // asks about the state of the enumeration.  This means that the only time the
    // enumeration will return null is when the last object is GCd.  I could change
    // this by holding onto the value of the last entry so it can't change underneath me.
    // Actually, I can't see the harm in doing that...

    public Object nextElement() {
        Object result;

        if (entry == null) {
            this.skipToNextElement();
        };
        if (entry == null) {
            throw new NoSuchElementException("WeakHashtableEnumerator");
        };

        if (keys) {
            result = entry.key;
        } else {
            result = entry.value();
        };
        this.skipToNextElementInBucket();
        return result;
    }

}
