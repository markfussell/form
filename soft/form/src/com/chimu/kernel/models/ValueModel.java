/*======================================================================
**
**  File: chimu/kernel/models/ValueModel.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.models;

import com.chimu.kernel.collections.*;

/**
A ValueModel is an Observable that responds to the Value protocol.  This 
means that any value, value(Object) methods will cause a notification to
all the ValueModels Observers
**/

public interface ValueModel extends Observable, 
                                    Valuable //, SubjectValuable 
        {
    
}
