/*======================================================================
**
**  File: chimu/kernel/collections/CollectionsMill.java
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

import com.chimu.kernel.functors.*;

/**
CollectionsMill provides the ability to create all the major different collections type.
This is preferable to using the direct 'static' function on CollectionsPack when you
may need to swap out the implementations.  The default CollectionsMill implementation
provided by CollectionsPack is equivalent to calling the static method, but other
implementations could be optimized to have special capabilities.

@see CollectionsPack
**/
public interface CollectionsMill  {

    //======================================

    public List newList();
    public List newList(int size);
    public List newListEmptySize(int size);

    public List newListFuture(Function0Arg futureFunction);

    //======================================

    public KeyedArray newKeyedArray(KeyedCollection keyToIndexMap);
    public KeyedArray newKeyedArray(KeyedCollection keyToIndexMap, Object[] values);


    public Map newMap();

        /**
         * Build a WeakMap if the VM supports it, otherwise build a normal
         * Map.  A WeakMap only has a "weak" hold on its values, which
         * allows them to be garbage collected and removed from the collection.
         * This is ideal for many types of caches because you only want to
         * keep track of objects that others are using. If they stop using
         * them, you would like to release the Objects.
         */
    public Map newWeakMap();
};
