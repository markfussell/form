/*======================================================================
**
**  File: chimu/kernel/collections/impl/NotImplementedMapAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

import java.util.Vector;
import java.util.Enumeration;
import java.util.NoSuchElementException;


public abstract class NotImplementedMapAbsC
            implements Map {

    static boolean DEBUG = true;


    protected void notImplementedYet() {
        throw new NotImplementedYetException(getClass()+" does not implement this method yet");
    }

    public boolean canInclude(Object o) {if (DEBUG) notImplementedYet();return false;};


    public Collection copyAdding(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Collection copyAddingIfAbsent(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Collection copyCollect(Function1Arg transform) {if (DEBUG) notImplementedYet();return null;};
    public Collection copyEmpty() {if (DEBUG) notImplementedYet();return null;};
    public Collection copyExcluding(Object o) {if (DEBUG) notImplementedYet();return null;};
    public KeyedArray copyKeys(Array keys) {if (DEBUG) notImplementedYet();return null;};
    public Collection copyRemovingOneOf(Object o) {if (DEBUG) notImplementedYet();return null;};
    public KeyedCollection copyRemovingValue(Object o) {if (DEBUG) notImplementedYet();return null;};
    public Collection copyReplacingAllOf(Object o, Object with) {if (DEBUG) notImplementedYet();return null;};
    public Collection copyReplacingOneOf(Object o, Object with) {if (DEBUG) notImplementedYet();return null;};

    public Object inject_into(Object o, Function2Arg function) {if (DEBUG) notImplementedYet();return null;};

    public boolean sameStructure(Collection o) {if (DEBUG) notImplementedYet();return false;};




    public int         version() {if (DEBUG) notImplementedYet();return 0;};

    //**********************************************************
    //(P)********************* Collection **********************
    //**********************************************************

    public Object any() {if (DEBUG) notImplementedYet();return null;};
    public Object copy() {if (DEBUG) notImplementedYet();return null;};

    public int size() {if (DEBUG) notImplementedYet();return 0;};;
    public boolean isEmpty() {if (DEBUG) notImplementedYet();return false;};

    public Enumeration elements() {if (DEBUG) notImplementedYet();return null;};
    public boolean includes(Object o) {if (DEBUG) notImplementedYet();return false;};
    public int occurrencesOf(Object o) {if (DEBUG) notImplementedYet();return 0;};

    public boolean canSatisfy(Predicate1Arg o) {if (DEBUG) notImplementedYet();return false;};
    public Object detect(Predicate1Arg detect) {if (DEBUG) notImplementedYet();return null;};

    public Collection copyReject(Predicate1Arg reject) {if (DEBUG) notImplementedYet();return null;};
    public Collection copySelect(Predicate1Arg select) {if (DEBUG) notImplementedYet();return null;};

    //**********************************************************
    //(P)**************** KeyedCollection *****************
    //**********************************************************

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

    public java.util.Hashtable toJdkHashtable() {if (DEBUG) notImplementedYet();return null;};
    public java.util.Dictionary toJdkDictionary() {if (DEBUG) notImplementedYet();return null;};

    //**********************************************************
    //(P)******************* KeyedCollection *******************
    //**********************************************************

    public void atKey_put(Object o, Object value) {if (DEBUG) notImplementedYet();};
    public void updatePair(Pair o) {if (DEBUG) notImplementedYet();};
    public void replaceKey_value_with(Object o, Object v1, Object v2) {if (DEBUG) notImplementedYet();};

    //**********************************************************
    //(P)*********************** Map ***************************
    //**********************************************************

    public void addKey_put(Object o, Object value) {if (DEBUG) notImplementedYet();};
    public void addPair(Pair o) {if (DEBUG) notImplementedYet();};
    public void removeKey(Object o) {if (DEBUG) notImplementedYet();};

    //**********************************************************
    //(P)**************** Array ***************
    //**********************************************************

    public Object first() {if (DEBUG) notImplementedYet();return null;};
    public Object last() {if (DEBUG) notImplementedYet();return null;};

    public Object atIndex(int i) {if (DEBUG) notImplementedYet();return null;};

    public int firstIndexOf(Object o, int start) {if (DEBUG) notImplementedYet();return 0;};
    public int firstIndexOf(Object o) {if (DEBUG) notImplementedYet();return 0;};

    public int indexOf(Object o) {if (DEBUG) notImplementedYet();return 0;};

    public int lastIndexOf(Object o, int start) {if (DEBUG) notImplementedYet();return 0;};
    public int lastIndexOf(Object o) {if (DEBUG) notImplementedYet();return 0;};

    public List copyInsertingAtIndex_put(int i, Object o) {if (DEBUG) notImplementedYet();return null;};
    public List copyRemovingIndex(int i) {if (DEBUG) notImplementedYet();return null;};
    public List copyReplacingIndex_with(int index, Object o) {if (DEBUG) notImplementedYet();return null;};
    public List copySubseq(int start, int end) {if (DEBUG) notImplementedYet();return null;};

    public Object[]         toJavaArray() {if (DEBUG) notImplementedYet();return null;};
    public java.util.Vector toJdkVector() {if (DEBUG) notImplementedYet();return null;};

    //**********************************************************
    //(P)********************** Array **************************
    //**********************************************************

    public void atIndex_put(int i, Object value) {if (DEBUG) notImplementedYet();};
    public void atAllPut(Object o) {if (DEBUG) notImplementedYet();};
    public void atAllPutNull() {if (DEBUG) notImplementedYet();};
    public void swapAtIndex_withIndex(int index1, int index2)
            throws ArrayIndexOutOfBoundsException {if (DEBUG) notImplementedYet();};

    public void sort() {if (DEBUG) notImplementedYet();};
    public void sort(Predicate2Arg greaterThanPredicate) {if (DEBUG) notImplementedYet();};

    //**********************************************************
    //(P)**************** ExtensibleCollection *****************
    //**********************************************************

    public void clear() {if (DEBUG) notImplementedYet();};
    public void removeAll() {if (DEBUG) notImplementedYet();};

    public boolean add(Object element) throws IllegalElementException {if (DEBUG) notImplementedYet(); return false;};
    public void addAll(Collection elements) throws IllegalElementException {if (DEBUG) notImplementedYet();};

    public void addIfAbsent(Object element) throws IllegalElementException {if (DEBUG) notImplementedYet();};
    public void addIfAbsentAll(Collection elements) throws IllegalElementException {if (DEBUG) notImplementedYet();};
    public void addIfAbsentElements(Enumeration e)
            throws IllegalElementException, CorruptedEnumerationException {if (DEBUG) notImplementedYet();};

    public void addElements(Enumeration e)
            throws IllegalElementException, CorruptedEnumerationException {if (DEBUG) notImplementedYet();};

    public Object remove(Object element) {if (DEBUG) notImplementedYet(); return null;};
    public boolean removeEvery(Object element) {if (DEBUG) notImplementedYet(); return false;};
    public Object removeAny() throws NoSuchElementException {if (DEBUG) notImplementedYet();return null;};
    public boolean removeElements(Enumeration e)
            throws CorruptedEnumerationException {if (DEBUG) notImplementedYet(); return false;};
    public void excludeElements(Enumeration e)
            throws CorruptedEnumerationException {if (DEBUG) notImplementedYet();};
    public boolean removeEveryElements(Enumeration e)
            throws CorruptedEnumerationException {if (DEBUG) notImplementedYet(); return false;};

    //**********************************************************
    //(P)******************** List *************************
    //**********************************************************

    public void atIndex_insert(int index, Object value) {if (DEBUG) notImplementedYet();};

};



