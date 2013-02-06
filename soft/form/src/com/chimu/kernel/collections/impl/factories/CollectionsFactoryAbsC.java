/*======================================================================
**
**  File: chimu/kernel/collections/impl/factories/CollectionsFactoryAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl.factories;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.collections.impl.jdk.*;

import com.chimu.kernel.functors.*;

import java.util.Vector;

/**

This defaults to JDK implementations

**/

public abstract class CollectionsFactoryAbsC implements CollectionsFactory {
    public boolean canSupportWeakCollections() {
        return false;
    }

    public String toString() {
        return super.toString()+"{"+canSupportWeakCollections()+"}";
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Set newSet() {
        return new JdkSetFromHashtableC(new java.util.Hashtable()); // new JdkWrapped
    }
    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Map newMap() {
        return new JdkHashtableWrapperC(new java.util.Hashtable()); // new JdkWrapped
    }

    //**************************************
    //**************************************
    //**************************************

    public List newList() {
        return new JdkVectorWrapperC(new java.util.Vector()); // new ??
    }

    public List newList(int size) {
        return new JdkVectorWrapperC(new java.util.Vector(size)); // new ??
    }

    public List newListEmptySize(int size) {
        Vector v = new Vector(size);
        v.setSize(size);
        return new JdkVectorWrapperC(v); // new ??
    }

    //**************************************
    //**************************************
    //**************************************

    public KeyedArray newKeyedArray(KeyedCollection keyToIndexMap) {
        return new KeyedArrayC(keyToIndexMap);
    }

    public KeyedArray newKeyedArray(KeyedCollection keyToIndexMap, Object[] values) {
        return new KeyedArrayC(keyToIndexMap, values);
    }

    //**************************************
    //**************************************
    //**************************************

    public List newListFuture(Function0Arg futureFunction) {
        return new FutureListWrapperC(futureFunction); // new ??
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Map newWeakMap() {
        return newMap();
    }

}

