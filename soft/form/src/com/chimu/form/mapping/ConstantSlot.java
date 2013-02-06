/*======================================================================
**
**  File: chimu/form/mapping/ConstantSlot.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;

/**
A ConstantSlot has a constant value that is used for database writes and expected
to be the value for database reads.  It is basically a simple TransformationSlot
**/
public interface ConstantSlot extends SetterSlot {
    Object value();  //ConstantSlotValue
};