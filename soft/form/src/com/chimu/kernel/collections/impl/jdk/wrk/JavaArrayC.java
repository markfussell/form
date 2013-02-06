/*======================================================================
**
**  File: chimu/kernel/collections/impl/jdk/wrk/JavaArrayC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.util.collections;

/**
 * Arrays are fixed length (after creation) [nonExtendible, updatable, indexible] collections
 * of objects
 */

public class JavaArrayC extends FixedCollectionAbsC implements Array {
    public JavaArrayC(int size) {
        array = new Object[size];
    };

    /**
     *@return (JavaArrayC) a copy of the receiver
     */

    public /*(R) JavaArrayC */ Collection copy() {
        JavaArrayC copy = new JavaArrayC(this.size());
        for (int i = 0; i<array.length; i++) {
            copy.array[i] = this.array[i];
        };
        return copy;
    };

    public int size() {
        return array.length;
    };

    public boolean includes(Object value) {
        for (int i = 0; i<array.length; i++) {
            if (array[i] == value) {
                return true;
            };
        };
        return false;
    };

    public Object atIndex(int index) {
        return array[index];
    };

    public Object first() {
        return array[0];
    };
    public Object last() {
        return array[array.length - 1];
    };

    public int indexOf(Object value) {
        for (int i = 0; i<array.length; i++) {
            if (array[i] == value) {
                return i;
            };
        };
        return -1;
    };
    public int lastIndexOf(Object value) {
        for (int i = array.length - 1; i>=0; i--) {
            if (array[i] == value) {
                return i;
            };
        };
        return -1;
    };

    public Enumeration elements() {
        return null;
    };

    public void atIndex_put (int index, Object value) throws ArrayIndexOutOfBoundsException {
        array[index] = value;
    };
    public void atAllPut (Object value) {
        for (int i = 0; i<array.length; i++) {
            array[i] = value;
        };
    };

    public void clear() {
        this.atAllPut(null);
    };


    static public void main(String[] args) {
        Array array = new JavaArrayC(5);
        array.atIndex_put(0,"Foo");
        System.out.print(array.atIndex(0));
    };


    //(P) *************** Instance Variables ********************
    private Object[] array = null;
};