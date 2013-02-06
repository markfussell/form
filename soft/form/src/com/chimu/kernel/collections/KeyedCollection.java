/*======================================================================
**
**  File: chimu/kernel/collections/KeyedCollection.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
**  Portions of these collection classes were originally written by
**  Doug Lea (dl@cs.oswego.edu) and released into the public domain.
**  Doug Lea thanks the assistance and support of Sun Microsystems Labs,
**  Agorics Inc, Loral, and everyone contributing, testing, and using
**  this code.
**      ChiMu Corporation thanks Doug Lea and all of the above.
**
======================================================================*/

package com.chimu.kernel.collections;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import com.chimu.jdk12.java.util.Iterator;

//These next imports are just for the converting methods
import java.util.Hashtable;
import java.util.Dictionary;


/**
A KeyedCollection is a FixedKeyedCollection which allows values
to be updated for particular keys.
**/

public interface KeyedCollection extends CollectionBase {
  // ?? add UpdatableCollection (ExtensibleCollection)

        /**
         * Provide an interface on this collection that supports the JDK
         * map protocol.  This will not create a new object ('as' never
         * creates a new object).
         */
    public com.chimu.jdk12.java.util.Map asJdkMap();

//**************************************************************
//**************************************************************
//**************************************************************

        /**
         * public version of java.lang.Object.clone
         * All Collections implement clone. But this is a protected method.
         * Copy allows public access.
         * @see clone
        **/
    public Object       copy();

        /**
         * Report the number of elements in the collection.
         * No other spurious effects.
         * @return number of elements
        **/
    public int size();

        /**
         * Report whether this collection has no elements.
         * Behaviorally equivalent to <CODE>size() == 0</CODE>.
         * @return true iff size() == 0
        **/
    public boolean isEmpty();

        /**
         * Returns true if this Collection contains the specified element.  More
         * formally, returns true if and only if this Collection contains at least
         * one element <code>e</code> such that <code>(o==null ? e==null :
         * o.equals(e))</code>.
         *
         * @param o element whose presence in this Collection is to be tested.
         * @since JDK1.2
         */
    public boolean contains(Object o);

        /**
         * Returns an Iterator over the elements in this Collection.  There are
         * no guarantees concerning the order in which the elements are returned
         * (unless this Collection is an instance of some class that provides a
         * guarantee).
         *
         * @since JDK1.2
         */
    public Iterator iterator();

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


    public Object      any();
    
        /**
         * Return an enumeration that may be used to traverse through
         * the elements in the collection. Standard usage, for some
         * collection c, and some operation `use(Object obj)':
         * <PRE>
         * for (Enumeration e = c.elements(); e.hasMoreElements(); )
         *   use(e.nextElement());
         * </PRE>
         * (The values of nextElement very often need to
         * be coerced to types that you know they are.)
         * <P>
         * All Collections return instances
         * of Enumeration, that can report the number of remaining
         * elements, and also perform consistency checks so that
         * for UpdatableCollections, element enumerations may become
         * invalidated if the collection is modified during such a traversal
         * (which could in turn cause random effects on the collection.
         * TO prevent this,  Enumerations
         * raise CorruptedEnumerationException on attempts to access
         * nextElements of altered Collections.)
         * Note: Since all collection implementations are synchronizable,
         * you may be able to guarantee that element traversals will not be
         * corrupted by using the java <CODE>synchronized</CODE> construct
         * around code blocks that do traversals. (Use with care though,
         * since such constructs can cause deadlock.)
         * <P>
         * Guarantees about the nature of the elements returned by  nextElement of the
         * returned Enumeration may vary accross sub-interfaces.
         * In all cases, the enumerations provided by elements() are guaranteed to
         * step through (via nextElement) ALL elements in the collection.
         * Unless guaranteed otherwise (for example in Seq), elements() enumerations
         * need not have any particular nextElement() ordering so long as they
         * allow traversal of all of the elements. So, for example, two successive
         * calls to element() may produce enumerations with the same
         * elements but different nextElement() orderings.
         * Again, sub-interfaces may provide stronger guarantees. In
         * particular, Seqs produce enumerations with nextElements in
         * index order, ElementSortedCollections enumerations are in ascending
         * sorted order, and KeySortedCollections are in ascending order of keys.
         * @return an enumeration e such that
         * <PRE>
         *   e.numberOfRemainingElements() == size() &&
         *   foreach (v in e) includes(e)
         * </PRE>
        **/
    public Enumeration elements();
 
        /**
         * Cause the collection to become empty.
         * @return condition:
         * <PRE>
         * isEmpty() &&
         * Version change iff !PREV(this).isEmpty();
         * </PRE>
        **/
    public void clear();
    public void removeAll();

//**************************************************************
//**************************************************************
//**************************************************************

        /**
         * Returns true if this Map contains a mapping for the specified key.
         *
         * @param key key whose presence in this Map is to be tested.
         * @exception ClassCastException key is of an inappropriate type for
         *                   this Map.
         * @exception NullPointerException key is null and this Map does not
         *                  not permit null keys.
         * @since JDK1.2
         */
    public boolean containsKey(Object key);

        /**
         * Returns true if this Map maps one or more keys to the specified value.
         * More formally, returns true if and only if this Map contains at
         * least one mapping to a value <code>v</code> such that
         * <code>(value==null ? v==null : value.equals(v))</code>.
         * This operation will probably require time linear in the Map size for
         * most implementations of Map.
         *
         * @param value value whose presence in this Map is to be tested.
         * @since JDK1.2
         */
    public boolean containsValue(Object value);

        /**
         * Returns a Collection view of the values contained in this Map.  The
         * Collection is backed by the Map, so changes to the Map are
         * reflected in the Collection, and vice-versa.  If the Map is
         * modified while while an iteration over the Collection is in progress,
         * the results of the iteration are undefined.  The Collection supports
         * element removal, which removes the corresponding mapping from the
         * Map, via the Iterator.remove, Collection.remove, removeAll,
         * retainAll and clear operations.  It does not support the add or
         * addAll operations.
         *
         * @since JDK1.2
         */
    public Collection values();


        /**
         * Report whether the Map COULD include k as a key
         * Always returns false if k is null
        **/
    public boolean     canIncludeKey(Object k);

        /**
         * Report whether there exists any element with Key key.
         * @return true if there is such an element
        **/
    public boolean     includesKey(Object key);
    public boolean     includesValue(Object value);

        /**
         * Report whether there exists a (key, value) pair
         * @return true if there is such an element
        **/
    public boolean     includesKey_withValue(Object key, Object value);


        /**
         * Return an enumeration that may be used to traverse through
         * the keys (not elements) of the collection. The corresponding
         * elements can be looked at by using at(k) for each key k. For example:
         * <PRE>
         * Enumeration keys = amap.keys();
         * while (keys.hasMoreElements()) {
         *   Object key = keys.nextElement();
         *   Object value = amap.at(key)
         * // ...
         * }
         * </PRE>
         * @return the enumeration
        **/
    public Enumeration keys();
/*
    public void forEachKeyPerform(Procedure1Arg procedure);
    public void forEachPairPerform(Procedure1Arg procedure);
    public void forEachKeyAndValuePerform(Procedure1Arg procedure);
    // eachDo()
    // eachKeyDo()


    public void doWithKeys(Procedure1Arg procedure);
    public void doWithKeysAndValues (Procedure2Arg procedure);
    public void doWithPairs (Procedure1Arg procedure);
*/

        /**
         * Returns a Set view of the keys contained in this Map.  The Set is
         * backed by the Map, so changes to the Map are reflected in the Set,
         * and vice-versa.  If the Map is modified while while an iteration over
         * the Set is in progress, the results of the iteration are undefined.
         * The Set supports element removal, which removes the corresponding
         * mapping from the Map, via the Iterator.remove, Set.remove, removeAll
         * retainAll, and clear operations.  It does not support the add or
         * addAll operations.
         *
         * @since JDK1.2
         */
    public Set keySet();


        /**
         * Return the element associated with Key key.
         * @param key a key
         * @return element such that includesAt(key, element)
         * @exception NoSuchElementException if !includesKey(key)
        **/
    public Object atKey(Object key)
                       throws NoSuchElementException;

        /**
         * Return a key associated with element. There may be any
         * number of keys associated with any element, but this returns only
         * one of them (any arbitrary one), or null if no such key exists.
         * @param element, a value to try to find a key for.
         * @return key, such that
         * <PRE>
         * (key == null && !includes(element)) ||  includesKey_withValue(key, element)
         * </PRE>
        **/
    public Object             aKeyFor(Object element);
    
    public Set allKeysFor(Object element);

        /**
         * Construct a new Map that is a clone of self except
         * that it includes the new pair. If there already exists
         * another pair with the same key, the new collection will
         * instead have one with the new elment.
         * @param the key for element to add
         * @param the element to add
         * @return the new Map c, for which:
         * <PRE>
         * c.at(key).equals(element) &&
         * foreach (k in keys()) c.at(v).equals(at(k))
         * foreach (k in c.keys()) (!k.equals(key)) --> c.at(v).equals(at(k))
         * </PRE>
        **/
    public KeyedCollection
            copyAtKey_put(Object key, Object element)
                          throws IllegalElementException;

        /**
         * Construct a new Map that is a clone of self except
         * that it does not include the given key.
         * It is NOT an error to exclude a non-existent key.
         * @param key the key for the par to remove
         * @param element the element for the par to remove
         * @return the new Map c, for which:
         * <PRE>
         * foreach (v in c.keys()) includesAt(v, at(v)) &&
         * !c.includesKey(key)
         * </PRE>
        **/
    public KeyedCollection
            copyRemovingKey(Object key);

    public KeyedCollection
            copyRemovingValue(Object value);

    public KeyedCollection
            copyAddingPair(Pair value);


    //**********************************************************
    //(P)******************* Converting ************************
    //**********************************************************

    public Hashtable toJdkHashtable();
    public Dictionary toJdkDictionary();


//**************************************************************
//**************************************************************
//**************************************************************

        /**
         * Include the indicated pair in the Map
         * If a different pair
         * with the same key was previously held, it is replaced by the
         * new pair.
         *
         * @param key the key for element to include
         * @param element the element to include
         * @return condition:
         * <PRE>
         * includes(key, element) &&
         * no spurious effects &&
         * Version change iff !PREV(this).includesAt(key, element))
         * </PRE>
        **/
    public void atKey_put(Object key, Object element)
                 throws IllegalElementException;



    public void updatePair(Pair pair)
                 throws IllegalElementException;
                 
        /**
         * Replace old pair with new pair with same key.
         * No effect if pair not held. (This includes the case of
         * having no effect if the key exists but is bound to a different value.)
         * @param key the key for the pair to remove
         * @param oldElement the existing element
         * @param newElement the value to replace it with
         * @return condition:
         * <PRE>
         * !includesAt(key, oldElement) || includesAt(key, newElement);
         * no spurious effects &&
         * Version change iff PREV(this).includesAt(key, oldElement))
         * </PRE>
        **/
    public void replaceKey_value_with(Object key, Object oldElement, Object newElement)
                throws IllegalElementException;


}


