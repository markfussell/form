/*======================================================================
**
**  File: chimu/kernel/collections/impl/FutureListWrapperC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.protocols.Future;

import java.util.Vector;
import java.util.Enumeration;
import java.util.NoSuchElementException;


public class FutureListWrapperC
            extends ListAbsC
            implements List, Future {

    public FutureListWrapperC(Function0Arg futureFunction) {
        this.futureFunction = futureFunction;
        this.list = null;
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final Object copy() {unstub();
        return list.copy();
    }

        /*
        if (hasValue) {
            if (List == null) return null;
            return List.copy();
        } else {
            return new FutureListWrapperC(futureFunction);
        }
        */

    public final Collection copyEmpty() {unstub();
        return list.copyEmpty();
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final boolean hasValue() {
            //<DontAutoUnstub>
        return hasValue;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public final int size() {unstub();
        return list.size();
    }

    public final boolean isEmpty() {unstub();
        return list.isEmpty();
    }

    public final Object atIndex (int index) {unstub();
        return list.atIndex(index);
    }

    public final String toString() {unstub();
        return list.toString();
    }

    public final Enumeration elements() {unstub();return list.elements();}

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final boolean includes(Object element) {unstub();return list.includes(element);}
    public final int indexOf(Object element) {unstub();return list.indexOf(element);}
    public final int firstIndexOf(Object element) {unstub();return list.firstIndexOf(element);}
    public final int firstIndexOf(Object element, int startIndex) {unstub();return list.firstIndexOf(element,startIndex);}
    public final int indexOf(Object element, int startIndex)  {unstub();return list.indexOf(element,startIndex);}

    public final int lastIndexOf(Object element) {unstub();return list.lastIndexOf(element);}
    public final int lastIndexOf(Object element, int startIndex) {unstub();return list.lastIndexOf(element,startIndex);}

    public final Object first() {unstub();return list.first();}
    public final Object last() {unstub();return list.last();}
    public final Object any() {unstub();return list.any();}

    //**********************************************************

    public final void removeIndex(int index) {unstub(); list.removeIndex(index);}

    public com.chimu.jdk12.java.util.ListIterator listIterator() {unstub();return list.listIterator();}
    public com.chimu.jdk12.java.util.ListIterator listIterator(int index) {unstub();return list.listIterator(index);}
    public com.chimu.jdk12.java.util.Iterator     iterator() {unstub();return list.iterator();}

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public final boolean add(Object element) throws IllegalElementException {unstub();
        return list.add(element);
    }

    public final void atIndex_put (int index, Object value) {unstub();
        list.atIndex_put(index,value);
    }

    public final boolean remove(Object element) {unstub();
        return list.remove(element);
    }

    public final boolean removeEvery(Object element) {unstub();
        return list.removeEvery(element);
    }

    public final void removeAll() {unstub();
        list.removeAll();
    }

    public final synchronized void atIndex_insert(int index, Object value) {unstub();
        list.atIndex_insert(index,value);
    }

    //**********************************************************
    //(P)******************* Converting ************************
    //**********************************************************

    public Object[] toJavaArray() {unstub();
        return list.toJavaArray();
    }

    public Vector   toJdkVector() {unstub();return list.toJdkVector();}

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    protected final void unstub() {
        if (!hasValue()) buildValue();
    }

    protected void buildValue() {
        this.list = (List) futureFunction.value();
        hasValue = true;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Function0Arg futureFunction = null;
    protected List list = null;
    protected boolean hasValue = false;

}



