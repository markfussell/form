/*======================================================================
**
**  File: chimu/formTools/examples/ExampleAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.examples;

import com.chimu.formTools.test.*;

public abstract class ExampleAbsC extends DescriptionGenC {
    public String description() {
        return name();
    }
}

