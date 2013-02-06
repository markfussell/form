/*======================================================================
**
**  File: chimu/kernel/vm/VmConstants.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm;

public interface VmConstants {
    final int VM_UNKNOWN = -1;
    final int VM_GENERIC = 0;
    final int VM_SUN     = 1;
    final int VM_MS      = 2;
    final int VM_JGL     = 3;  //Just the base JDK plus JGL... This is for 100%

    final int VM_SIZE = 4;

};

