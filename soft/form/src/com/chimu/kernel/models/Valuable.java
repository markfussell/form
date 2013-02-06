/*======================================================================
**
**  File: chimu/kernel/models/Valuable.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.models;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;

/**
A Valuable object responds to value and value(Object).
**/

public interface Valuable extends Function0Arg {
    public void value(Object value);
}

