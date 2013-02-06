/*======================================================================
**
**  File: chimu/kernel/collections/Map.java
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

/**
A Map is an extensible KeyedCollection.
**/


public interface Map extends KeyedCollection // What a Map is
                             //, com.chimu.kernel.collections.jdk.Dictionary // Other protocol a Map supports
                             {

        /**
         * Remove the pair with the given key
         * @param  key the key
         * @return condition:
         * <PRE>
         * !includesKey(key)
         * foreach (k in keys()) at(k).equals(PREV(this).at(k)) &&
         * foreach (k in PREV(this).keys()) (!k.equals(key)) --> at(k).equals(PREV(this).at(k))
         * (version() != PREV(this).version()) ==
         * includesKey(key) !=  PREV(this).includesKey(key))
         * </PRE>
        **/
    public void removeKey(Object key);

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
    public void addPair(Pair pair)
                 throws IllegalElementException;

    public void addKey_put(Object key, Object element)
                 throws IllegalElementException;

}


