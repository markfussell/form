/*======================================================================
**
**  File: chimu/kernel/collections/impl/KeyedArrayC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

import java.util.Hashtable;
import java.util.Enumeration;

import java.util.Vector;

public class KeyedArrayC
                extends ListAbsC
                implements KeyedArray {

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static private boolean DEBUG = false;

    public com.chimu.jdk12.java.util.Iterator     iterator() {if (DEBUG) notImplementedYet();return null;};
    public com.chimu.jdk12.java.util.ListIterator listIterator() {if (DEBUG) notImplementedYet();return null;};
    public com.chimu.jdk12.java.util.ListIterator listIterator(int index) {if (DEBUG) notImplementedYet();return null;};
    public void removeIndex(int index) {if (DEBUG) notImplementedYet();return;};
    public int  indexOf(Object o, int index) {if (DEBUG) notImplementedYet();return 0;};

/*
    public Object atKey(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Enumeration keys() {if (DEBUG) notImplementedYet();return null;};

    public Object aKeyFor(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Set allKeysFor(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Array atKeys(Array o) {if (DEBUG) notImplementedYet();return null;};
    public boolean canIncludeKey(Object o) {if (DEBUG) notImplementedYet();return false;};

    public KeyedCollection copyAddingPair(Pair o) {if (DEBUG) notImplementedYet();return null;};
    public KeyedCollection copyAtKey_put(Object key, Object value) {if (DEBUG) notImplementedYet();return null;};
    public KeyedCollection copyRemovingKey(Object o) {if (DEBUG) notImplementedYet();return null;};

    public boolean includesKey(Object o) {if (DEBUG) notImplementedYet();if (DEBUG) notImplementedYet(); return false;};
    public boolean includesKey_withValue(Object key, Object value) {if (DEBUG) notImplementedYet();return false;};
    public boolean includesValue(Object o) {if (DEBUG) notImplementedYet();return false;};
*/

    public boolean contains(Object o) {if (DEBUG) notImplementedYet();return false;};
    public boolean containsKey(Object o) {if (DEBUG) notImplementedYet();return false;};
    public boolean containsValue(Object o) {if (DEBUG) notImplementedYet();return false;};
    public boolean removeEvery(Object o) {if (DEBUG) notImplementedYet();return false;};

    public Collection values() {if (DEBUG) notImplementedYet();return null;};
    public Set keySet() {if (DEBUG) notImplementedYet();return null;};
    public com.chimu.jdk12.java.util.Map asJdkMap() {if (DEBUG) notImplementedYet();return null;};




    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    public KeyedArrayC () {
        this.columnIndexes = CollectionsPack.newMap();
        this.values = new Object[1];
    };

    public KeyedArrayC (KeyedCollection indexes) {
        this.columnIndexes = indexes;
        this.values = new Object[indexes.size()];
    };

    public KeyedArrayC (KeyedCollection indexes, Object[] values) {
        this.columnIndexes = indexes;
        this.values = values;
    };

    public KeyedArrayC (int size) {
        this.columnIndexes = CollectionsPack.newMap();
        this.values = new Object[size];
    };

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Object copy() {
        return new KeyedArrayC(columnIndexes,values);
    };

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public Enumeration keys2() {
        return columnIndexes.keys();
    };

    public int size() {return values.length;};

    public Object atIndex (int index) {
        return values[index];
    };

    public String toString() {
	    StringBuffer buf = new StringBuffer();
	    buf.append("[");
        for (int i=0; i < values.length; i++) {
            buf.append(values[i]);
            buf.append(" ");
        };
        buf.append("]");
        buf.append(columnIndexes.toString());
        return buf.toString();
    };

    public Object atKey   (Object key) {
        Object indexHolder = columnIndexes.atKey(key);
        if (indexHolder == null) {return null;};
        return this.atIndex(((Integer) indexHolder).intValue());
    };

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public void atIndex_put (int index, Object value) {
        //try {
            values[index] = value;
        //} catch (Exception e) {
        //    throw new RuntimeException("Index "+index+" out of bounds, self = "+this);
        //}
    };

    public void atKey_put (Object key, Object value) {
        Object indexHolder = columnIndexes.atKey(key);
        int index;
        if (indexHolder == null) {
            throw new FailedRequireException("This KeyedArray does not have a key: "+key);
            //index = values.length;
            //columnIndexes.atKey_put(key,new Integer(index));
            // values = values.clone+1slot
        } else {
            index = ((Integer) indexHolder).intValue();
        };
        this.atIndex_put(index,value);
    };

    public void addKey_put (Object key, Object value) {
        throw new NotImplementedYetException("Can not support adding keys yet");
        // copy values to slightly larger..
        // check to see if key is already in table...
        //
    };

    public String keyForIndex (int index) {
        for (Enumeration enum = columnIndexes.keys(); enum.hasMoreElements();) {
            String key = (String) enum.nextElement();
            if (((Integer) columnIndexes.atKey(key)).intValue() == index) {
                return key;
            };
        };
        return null;
    };

    //**********************************************************
    //(P)******************* Converting ************************
    //**********************************************************

    public Object[] toJavaArray() {
        return values;
    };

    public Vector   toJdkVector() {
        Vector result = new Vector(size());
        for (int i=0; i<values.length; i++) {
            result.addElement(values[i]);
        };
        return result;
    };


    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Object[] values;
    protected KeyedCollection columnIndexes;


};

