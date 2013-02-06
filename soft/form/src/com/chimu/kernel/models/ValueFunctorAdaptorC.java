/*======================================================================
**
**  File: chimu/kernel/models/ValueFunctorAdaptorC.java
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

public class ValueFunctorAdaptorC extends ObservableDelegeeAbsC implements ValueModel {
    public ValueFunctorAdaptorC(Function0Arg reader, Procedure1Arg writer) {
        this.reader = reader;
        this.writer = writer;
    }


    public Object value() {
        return reader.value();
    }

    public void value(Object value) {
        writer.executeWith(value);
        this.changed();
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Function0Arg reader;
    protected Procedure1Arg writer;
}
