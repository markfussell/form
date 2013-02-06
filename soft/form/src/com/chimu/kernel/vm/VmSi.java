/*======================================================================
**
**  File: chimu/kernel/vm/VmSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm;

import com.chimu.kernel.collections.impl.CollectionsFactory;

public interface VmSi extends Vm{
    /*subsystem*/ public CollectionsFactory defaultCollectionsFactory();

}

