/*======================================================================
**
**  File: chimu/kernel/collections/impl/jgl/JglHashSetWrapperC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl.jgl;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.functors.*;

import com.objectspace.jgl.HashSet;
import com.objectspace.jgl.HashSetIterator;

import java.util.Enumeration;
import java.util.NoSuchElementException;


public final class JglHashSetWrapperC
            extends SetAbsC {

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static private boolean DEBUG = false;

    public com.chimu.jdk12.java.util.Iterator     iterator() {if (DEBUG) notImplementedYet();return null;};

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public JglHashSetWrapperC(HashSet hashSet) {
        this.hashSet = hashSet;
    }

    public void init(HashSet hashSet) {
        this.hashSet = hashSet;
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final Object copy() {
        return new JglHashSetWrapperC((HashSet) this.hashSet.clone());
    }

    public final Collection copyEmpty() {
        return new JglHashSetWrapperC(new HashSet());
    }


    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final int size() {return hashSet.size();}
    public final boolean isEmpty() {return hashSet.isEmpty();}
    public final String toString() {return hashSet.toString();}
    public final boolean equals(Object object) {return hashSet.equals(object);}
    public final boolean includes(Object object) {return hashSet.get(object) != null;}

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final Enumeration elements() {return hashSet.elements();}
    public final Object any() {
        HashSetIterator iter = hashSet.begin();
        Object result = null;
        if (iter.hasMoreElements()) {
            result = iter.nextElement();
        }
        return result;
    }

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public final boolean add (Object object) {return hashSet.add(object) != null;}

    public final boolean remove(Object object) {
        if (hashSet.remove(object) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public final boolean removeEvery(Object object) {return remove(object);}
    public final void removeAll() {hashSet.clear();}

    //**********************************************************
    //(P)******************* Converting ************************
    //**********************************************************

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected HashSet hashSet = null;

};