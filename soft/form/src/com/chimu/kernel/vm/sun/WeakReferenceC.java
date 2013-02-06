/*======================================================================
**
**  File: chimu/kernel/vm/sun/WeakReferenceC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm.sun;

public class WeakReferenceC extends    sun.misc.Ref
                                implements com.chimu.kernel.vm.WeakReference {
    public WeakReferenceC(Object value) {
        super();
        setThing(value);
    }

    public Object  value() {
        return check();
    }

    public void    setValue(Object value) {
        setThing(value);
    }

    public boolean hasValue() {
        return check() != null;
    }

    public void    release() {
        setThing(null);
    }

    public Object reconstitute() {
        return null;
    }

    public String toString() {return "(Weak->)"+value();}
}

