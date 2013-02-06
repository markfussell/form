/*======================================================================
**
**  File: chimu/kernel/collections/impl/CollectionsFactory.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.jdk.*;
import com.chimu.kernel.collections.impl.jgl.*;
import com.chimu.kernel.functors.*;

import java.util.Vector;

/**
This documents the Collections

**/
public interface CollectionsFactory {

    public boolean canSupportWeakCollections();

    //**************************************
    //**************************************
    //**************************************

    public Set newSet();

    //**************************************

    public List newList();
    public List newList(int size);
    public List newListEmptySize(int size);

    public List newListFuture(Function0Arg futureFunction);

    //**************************************

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
}

