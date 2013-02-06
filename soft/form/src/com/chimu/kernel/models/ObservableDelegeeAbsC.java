/*======================================================================
**
**  File: chimu/kernel/models/ObservableDelegeeAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.models;

import com.chimu.kernel.collections.*;


/**
An ObservableDelegeeAbsC provides an abstract superclass or an example
implementation of working with a delegate.
**/

public class ObservableDelegeeAbsC implements Observable {
    public void addObserver(Observer o) {
        delegate.addObserver(o);
    }

    public void removeObserver(Observer o) {
        delegate.removeObserver(o);
    }

    public void removeAllObservers(){
        delegate.removeAllObservers();
    }

    protected void changed() {
        delegate.noteDelegeeChanged(this);
    }


    protected ObservableDelegate delegate = new ObservableDelegateC();
}


