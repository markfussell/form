/*======================================================================
**
**  File: chimu/kernel/models/ValueFunctorChannelAdaptorC.java
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

public class ValueFunctorChannelAdaptorC extends ObservableDelegeeAbsC implements ValueModel {
    public ValueFunctorChannelAdaptorC(Function0Arg subjectChannel, Function1Arg getter, Procedure2Arg setter) {
        this.subjectChannel = subjectChannel;
        this.getter = getter;
        this.setter = setter;
    }


    public Object value() {
        return getter.valueWith(channelSubject());
    }

    public void value(Object value) {
        setter.executeWith_with(channelSubject(),value);
        this.changed();
    }

    protected Object channelSubject() {
        return subjectChannel.value();
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Function0Arg subjectChannel;
    protected Function1Arg getter;
    protected Procedure2Arg setter;
}
