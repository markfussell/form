/*======================================================================
**
**  File: chimu/kernel/collections/KeyedArray.java
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

/**
A KeyedArray combines the functionality of a KeyedCollection and an Array.
It is an Array [non-extensible, updatable, indexed collection] which also
associates a Key with each index entry.
**/
public interface KeyedArray extends Array, KeyedCollection {
    public int size();
    
    
    public Object atIndex (int index);
    public Object atKey   (Object key);

    public String keyForIndex (int index);

    public Array atKeys (Array keys);
//    Array  atKeys  (Vector keys);
//    Array  atIndexes  (Vector keys);
//    Array  atIndexes  (Vector keys);

    public KeyedArray copyKeys (Array keys);
//    ArrayDictionary copyIndexes (Sequence indexes);

//    Array  atKeys  (Sequence keys);

    public void atIndex_put (int index, Object value);
    public void atKey_put   (Object key, Object value);

    /**
     * Add a new entry to the end of the array with the name...
     * <P>(NOTE: This may have to do a copy of the dictionary if we wanted it to share them normally)
     */

//   void addKey_put  (String key, Object value);  // Part of a MappedSequence

//    CompoundColumnValue valueForColumn (Dictionary column);

// ==================================

    public Enumeration keys2();
};

