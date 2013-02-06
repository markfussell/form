/*======================================================================
**
**  File: chimu/kernel/vm/ms/WeakReferenceC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm.ms;

public class WeakReferenceC extends    com.ms.vm.WeakReference
                                implements com.chimu.kernel.vm.WeakReference {
    public WeakReferenceC(Object value) {
        super(value);
    }

    public Object  value() {
        return getReferent();
    }

    public void    setValue(Object object) {
        setReferent(object);
    }

    public boolean hasValue() {
        return value() != null;
    }

    public void    release() {
        setValue(null);
    }

    public String toString() {return "(Weak->)"+value();}
}

