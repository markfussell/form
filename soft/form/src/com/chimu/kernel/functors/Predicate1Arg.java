/*======================================================================
**
**  File: chimu/kernel/functors/Predicate1Arg.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.functors;

public interface Predicate1Arg {
    boolean isTrueWith (Object arg1);
};