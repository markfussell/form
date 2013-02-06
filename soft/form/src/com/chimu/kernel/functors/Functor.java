/*======================================================================
**
**  File: chimu/kernel/functors/Functor.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.functors;

public interface Functor {
    int numberOfArguments();
    boolean returnsValue();
    boolean returnsBoolean();

    Object evaluate();
};