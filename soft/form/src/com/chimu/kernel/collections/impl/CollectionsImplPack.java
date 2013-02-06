/*======================================================================
**
**  File: chimu/kernel/collections/impl/CollectionsPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.collections.impl.factories.*;

import com.chimu.kernel.collections.impl.jdk.*;
import com.chimu.kernel.collections.impl.jgl.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.vm.*;

import java.util.Vector;

/**
CollectionsImplPack provides the functionality to build
**/

public class CollectionsImplPack {
    private CollectionsImplPack(){};

    //**********************************************************

    static public boolean canVmSupportWeakCollections() {
        return VmPack.theVm().canSupportWeakReferences();
    }

    public boolean canSupportWeakCollections() {
        return defaultFactory.canSupportWeakCollections();
    }

    static public boolean useWeakCollections() {
        return wantWeakCollections && canVmSupportWeakCollections();
    }

    //**********************************************************

    static public Map newMap() {
        return defaultFactory.newMap();
    }


    static public List newList() {
        return defaultFactory.newList();
    }

    static public List newList(int size) {
        return defaultFactory.newList(size);
    }

    static public List newListEmptySize(int size) {
        return defaultFactory.newListEmptySize(size);
    }


    static public Set newSet() {
        return defaultFactory.newSet();
    }

    static public KeyedArray newKeyedArray(KeyedCollection keyToIndexMap) {
        return defaultFactory.newKeyedArray(keyToIndexMap);
    }

    static public KeyedArray newKeyedArray(KeyedCollection keyToIndexMap, Object[] values) {
        return defaultFactory.newKeyedArray(keyToIndexMap, values);
    }

    static public List newListFuture(Function0Arg futureFunction) {
        return defaultFactory.newListFuture(futureFunction);
    }

        /**
         * Build a WeakMap if the VM supports it, otherwise build a normal
         * Map.  A WeakMap only has a "weak" hold on its values, which
         * allows them to be garbage collected and removed from the collection.
         * This is ideal for many types of caches because you only want to
         * keep track of objects that others are using. If they stop using
         * them, you would like to release the Objects.
         */
    static public Map newWeakMap() {
        return defaultFactory.newWeakMap();
    }


        /**
         * Return the default factory used to build collections when
         * you call CollectionsImplPack static methods
         */
    static public CollectionsFactory defaultFactory() {
        return defaultFactory;
    }

        /**
         * Setup the default factory used to build collections when
         * you call CollectionsImplPack static methods.  This should
         * be called very early in a programs execution or
         * programs may get confused (but probably not: the interfaces
         * supported are the same no matter what).
         */
    static public void setupDefaultFactory(CollectionsFactory newDefaultFactory) {
        defaultFactory = newDefaultFactory;
    }


    //**********************************************************
    //*********************** STARTUP **************************
    //**********************************************************

    static protected CollectionsFactory defaultFactory = null;

    static public void setupDefaultFactory() {
        VmSi theVm = (VmSi) VmPack.theVm();

        defaultFactory = theVm.defaultCollectionsFactory();
        if (defaultFactory == null) {
            defaultFactory = new JdkCollectionsFactoryC(false);
        }
    }

    static public void setWantWeakCollections(boolean want) {
        VmPack.theVm().setupUseWeakReferences(want);
    }

    static protected boolean wantWeakCollections = true;

    static {
        setupDefaultFactory();
    }
}

