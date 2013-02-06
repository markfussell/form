/*======================================================================
**
**  File: chimu/kernel/models/ModelsPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.models;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

/**


**/

public class ModelsPack {
    private ModelsPack(){};

    static public ValueModel newValueHolder() {
        return new ValueHolderC();
    }
}

