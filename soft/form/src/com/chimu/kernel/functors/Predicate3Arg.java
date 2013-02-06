/*======================================================================
**
**  File: chimu/kernel/functors/Predicate3Arg.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.functors;

public interface Predicate3Arg {
    public boolean isTrueWith_with_with(Object arg1, Object arg2, Object arg3);
};