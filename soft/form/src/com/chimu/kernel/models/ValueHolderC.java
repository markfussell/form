/*======================================================================
**
**  File: chimu/kernel/models/ValueHolderC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.models;

import com.chimu.kernel.collections.*;


import com.chimu.kernel.exceptions.*;

/**
A ValueHolder is a simple implementation of a ValueModel that uses an
instance variable maintain the value state.
**/

public class ValueHolderC extends ObservableDelegeeAbsC implements ValueModel {
    public ValueHolderC() {}
    
    public Object value() {
        return value;
    }

    public void value(Object value) {
        this.value = value;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************
 
    protected Object value;
}
