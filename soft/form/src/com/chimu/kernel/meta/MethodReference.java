/*======================================================================
**
**  File: chimu/kernel/meta/MethodReference.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.meta;

import java.lang.reflect.*;

public interface MethodReference {
    public Method           target();
    public String           methodName();

    public ClassReference   declaringCRef();
    public Class            declaringC();
    public ClassReference[] parameterClassesRefs();
    public Class[]          parameterClasses();
}
