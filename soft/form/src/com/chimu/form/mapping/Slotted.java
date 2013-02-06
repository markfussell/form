/*======================================================================
**
**  File: chimu/form/mapping/Slotted.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;
import com.chimu.form.query.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

/**
Slotted defines the minimal expected interface for objects that work
with slots.
**/
public interface Slotted {

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public Slot slotNamed(String name);
    public Array slots();

}