/*======================================================================
**
**  File: chimu/kernel/models/ValueChannelC.java
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
A ValueChannelC implements a ValueModel by forwarding to another 
ValueModel (called the channel)
**/

public class ValueChannelC extends ObservableDelegeeAbsC implements ValueModel, Observer {
    public ValueChannelC(ValueModel channel) {
        this.channel = channel;
        channel.addObserver(this);
    }

    public void release() {
        channel.removeObserver(this);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Object value() {
        return channelSubject().value();
    }

    public void value(Object value) {
        channelSubject().value(value);
        this.changed();
    }
    
    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void noteChanged(Observable object) {
        if (object == channel) {
            this.changed();
        };
    }
    
    //**********************************************************

    public void note_changed(Observable object, Object changeIdentifier) {
        noteChanged(object);
    }
    public void note_changed_to(Observable object, Object changeIdentifier, Object newValue){
        note_changed(object,changeIdentifier);
    }
    public void note_changed_from_to(Observable object, Object changeIdentifier, Object oldValue, Object newValue){
        note_changed_to(object,changeIdentifier,newValue);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected ValueModel channelSubject() {
        return (ValueModel) channel.value();
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected ValueModel channel;
}
