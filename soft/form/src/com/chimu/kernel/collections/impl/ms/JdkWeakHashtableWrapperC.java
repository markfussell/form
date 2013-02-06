/*======================================================================
**
**  File: chimu/kernel/collections/impl/ms/JdkWeakHashtableWrapperC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl.ms;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.functors.*;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.NoSuchElementException;


public final class JdkWeakHashtableWrapperC
            extends MapAbsC
            implements Map {

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static private boolean DEBUG = false;

    public com.chimu.jdk12.java.util.Iterator     iterator() {if (DEBUG) notImplementedYet();return null;};
    public com.chimu.jdk12.java.util.ListIterator listIterator() {if (DEBUG) notImplementedYet();return null;};
    public com.chimu.jdk12.java.util.ListIterator listIterator(int index) {if (DEBUG) notImplementedYet();return null;};
    public void removeIndex(int index) {if (DEBUG) notImplementedYet();return;};
    public int  indexOf(Object o, int index) {if (DEBUG) notImplementedYet();return 0;};

    public boolean contains(Object o) {if (DEBUG) notImplementedYet();return false;};
    public boolean containsKey(Object o) {if (DEBUG) notImplementedYet();return false;};
    public boolean containsValue(Object o) {if (DEBUG) notImplementedYet();return false;};
    public boolean removeEvery(Object o) {if (DEBUG) notImplementedYet();return false;};

    public Collection values() {if (DEBUG) notImplementedYet();return null;};
    public Set keySet() {if (DEBUG) notImplementedYet();return null;};
    public com.chimu.jdk12.java.util.Map asJdkMap() {if (DEBUG) notImplementedYet();return null;};

    public Object[] toArray() {if (DEBUG) notImplementedYet();return null;};
    public Collection copyEmpty() {if (DEBUG) notImplementedYet();return null;};

    //**********************************************************
    //**********************************************************
    //**********************************************************


    public JdkWeakHashtableWrapperC(WeakHashtableC hashtable) {
        this.hashtable = hashtable;
    };

    public void init(WeakHashtableC hashtable) {
        this.hashtable = hashtable;
    };

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final int size() {return hashtable.size();};

    public final boolean isEmpty() {
        return size() == 0;
    };


    public final String toString() {
        return hashtable.toString();
    };

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final Object atKey (Object key) {
        return hashtable.get(key);
    };

    public final boolean includesKey(Object key) {
        return hashtable.containsKey(key);
    };

    public final Enumeration keys() {return hashtable.keys();};
    public final Enumeration elements() {return hashtable.elements();};

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************


    public final void atKey_put (Object key, Object value) {
        if (value == null) return;
        hashtable.put(key,value);
    };

    public final void addKey_put (Object key, Object value) {
        if (value == null) return;
        hashtable.put(key,value);
    };

    public final void removeKey(Object key) {
        hashtable.remove(key);
    };

    //**********************************************************
    //(P)******************* Converting ************************
    //**********************************************************

    public Dictionary   toJdkDictionary() {return hashtable;};

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected WeakHashtableC hashtable = null;

};