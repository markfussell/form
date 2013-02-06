/*======================================================================
**
**  File: chimu/kernel/models/Observer.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.models;

import com.chimu.kernel.collections.*;

/**
An ObservableDelegateC provides support for observable behavior
**/

public interface Observer  {
    public void noteChanged(Observable object);
    public void note_changed(Observable object, Object changeIdentifier);
    public void note_changed_to(Observable object, Object changeIdentifier, Object newValue);
    public void note_changed_from_to(Observable object, Object changeIdentifier, Object oldValue, Object newValue);
    
    public void release();

    // note_changed_from_to
    // noteChangeTo_of_from_to
    // noteChanged(object)
    // note_changed(object,value)
}

