/*======================================================================
**
**  File: chimu/kernel/collections/impl/jdk/JdkSetFromHashtableC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl.jdk;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.functors.*;

import java.util.Enumeration;
//import java.util.Enumeration;
import java.util.NoSuchElementException;

import java.util.Hashtable;

public final class JdkSetFromHashtableC
            extends SetAbsC {

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static private boolean DEBUG = false;

    public com.chimu.jdk12.java.util.Iterator     iterator() {if (DEBUG) notImplementedYet();return null;};

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public JdkSetFromHashtableC(Hashtable hashtable) {
        this.hashtable = hashtable;
    }

    public void init(Hashtable hashtable) {
        this.hashtable = hashtable;
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final Object copy() {
        return new JdkSetFromHashtableC((Hashtable) this.hashtable.clone());
    }

    public final Collection copyEmpty() {
        return new JdkSetFromHashtableC(new Hashtable());
    }


    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final int size() {return hashtable.size();}
    public final boolean isEmpty() {return hashtable.isEmpty();}
    public final String toString() {
        StringBuffer stringB = new StringBuffer();
        stringB.append("{");
        Enumeration enum = elements();
        if (enum.hasMoreElements()) {
            stringB.append(enum.nextElement());
            while (enum.hasMoreElements()) {
                stringB.append(", ");
                stringB.append(enum.nextElement());
            };
        };
        stringB.append("}");
        return stringB.toString();
    }

    public final boolean equals(Object object) {
        if (object instanceof JdkSetFromHashtableC) {
            return hashtable.equals(((JdkSetFromHashtableC) object).hashtable);
        };
        return false;
    }
    public final boolean includes(Object object) {return hashtable.containsKey(object);}

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final Enumeration elements() {return hashtable.keys();}
    public final Object any() {
        Enumeration enum = elements();
        Object result = null;
        if (enum.hasMoreElements()) {
            result = enum.nextElement();
        }
        return result;
    }

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public final boolean add (Object object) {hashtable.put(object,START_MARKER); return true;}

    public final boolean remove(Object object) {
        if (hashtable.remove(object) == null ) {
            return false;
        } else {
            return true;
        }
    }

    public final boolean removeEvery(Object object) {return remove(object);}
    public final void removeAll() {hashtable.clear();}

    //**********************************************************
    //(P)******************* Converting ************************
    //**********************************************************

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Hashtable hashtable = null;

    static final Integer START_MARKER = new Integer(1);

};