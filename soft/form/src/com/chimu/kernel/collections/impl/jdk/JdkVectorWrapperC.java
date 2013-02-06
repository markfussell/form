/*======================================================================
**
**  File: chimu/kernel/collections/impl/jdk/JdkVectorWrapperC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl.jdk;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

import java.util.Vector;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import java.io.Serializable;

public class JdkVectorWrapperC
            extends ListAbsC
            implements List, Serializable {

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static private boolean DEBUG = false;

    public com.chimu.jdk12.java.util.Iterator     iterator() {if (DEBUG) notImplementedYet();return null;};
    public com.chimu.jdk12.java.util.ListIterator listIterator() {if (DEBUG) notImplementedYet();return null;};
    public com.chimu.jdk12.java.util.ListIterator listIterator(int index) {if (DEBUG) notImplementedYet();return null;};
    public void removeIndex(int index) {if (DEBUG) notImplementedYet();return;};
    public int  indexOf(Object o, int index) {if (DEBUG) notImplementedYet();return 0;};

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public JdkVectorWrapperC(Vector vector) {
        this.vector = vector;
    };

    public void init(Vector vector) {
        this.vector = vector;
    };

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final Object copy() {
        return new JdkVectorWrapperC((Vector) this.vector.clone());
    }

    public final Collection copyEmpty() {
        return new JdkVectorWrapperC(new Vector(this.vector.size()));
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final int size() {return vector.size();}

    public final boolean isEmpty() {
        return size() == 0;
    }

    public final Object atIndex (int index) {
        return vector.elementAt(index);
    }

    public final String toString() {
        return vector.toString();
    }

    public final Enumeration elements() {return vector.elements();};

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public final boolean includes(Object element) {return vector.contains(element);}
    public final int indexOf(Object element) {return vector.indexOf(element);}
    public final int firstIndexOf(Object element) {return vector.indexOf(element);}
    public final int firstIndexOf(Object element, int startIndex) {return vector.indexOf(element,startIndex);}

    public final int lastIndexOf(Object element) {return vector.lastIndexOf(element);}
    public final int lastIndexOf(Object element, int startIndex) {return vector.lastIndexOf(element,startIndex);}

    public final Object first() {return vector.firstElement();}
    public final Object last() {return vector.lastElement();}
    public final Object any() {return vector.lastElement();}

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public final boolean add(Object element) throws IllegalElementException {
        vector.addElement(element);
        return true;
    }

    public final void atIndex_put (int index, Object value) {
        vector.setElementAt(value,index);
    }

    public final boolean remove(Object element) {
        return vector.removeElement(element);
    }

    public final void removeAll() {
        vector.removeAllElements();
    }

    public final boolean removeEvery(Object element) {
        if (remove(element)) {
            while (remove(element));
            return true;
        };
        return false;
    }

    public final synchronized void atIndex_insert(int index, Object value) {
        vector.insertElementAt(value,index);
    }

    //**********************************************************
    //(P)******************* Converting ************************
    //**********************************************************

    public Object[] toJavaArray() {
        Object[] result = new Object[size()];
        vector.copyInto(result);
        return result;
    }

    public Vector   toJdkVector() {return vector;};

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Vector vector = null;

    //**********************************************************
    //**********************************************************
    //**********************************************************


}



