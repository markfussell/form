/*======================================================================
**
**  File: chimu/kernel/vm/WeakReferenceAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm;

public abstract class WeakReferenceAbsC implements WeakReference {
    public String toString() {return "(Weak->)"+value();}
}

