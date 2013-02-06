/*======================================================================
**
**  File: chimu/kernel/meta/ClassReference.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.meta;

import java.lang.reflect.*;

public interface ClassReference {
    public String   className();
    public Class    target();
    public boolean  hasTarget();
}
