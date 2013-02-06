/*======================================================================
**
**  File: chimu/kernel/models/ValueFunctorSubjectAdaptorC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.models;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;


import com.chimu.kernel.exceptions.*;

/**
A ValueFunctorSubjectAdaptorC is a simple implementation of a ValueModel
that uses setters/getters to treat a subject object as a storage for a value.
**/

public class ValueFunctorSubjectAdaptorC extends ObservableDelegeeAbsC implements ValueModel {
    public ValueFunctorSubjectAdaptorC(Object subject, Function1Arg getter, Procedure2Arg setter) {
        this.subject = subject;
        this.getter = getter;
        this.setter = setter;
    }


    public Object value() {
        return getter.valueWith(subject);
    }

    public void value(Object value) {
        setter.executeWith_with(subject,value);
        this.changed();
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Object subject;
    protected Function1Arg getter;
    protected Procedure2Arg setter;
}
