/*======================================================================
**
**  File: chimu/kernel/functors/Predicate2Arg.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.functors;

public interface Predicate2Arg {
    public boolean isTrueWith_with(Object arg1, Object arg2);
};