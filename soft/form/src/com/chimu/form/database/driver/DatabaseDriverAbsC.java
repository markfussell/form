/*======================================================================
**
**  File: chimu/form/database/driver/DatabaseDriverAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.driver;

import com.chimu.form.database.product.DatabaseProduct;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.meta.*;

public abstract class DatabaseDriverAbsC implements DatabaseDriver {
    protected DatabaseDriverAbsC() {
    }

    public abstract String name();
    public String toString() {
        return name();
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

}