/*======================================================================
**
**  File: chimu/kernel/vm/generic/VmC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm.generic;

import com.chimu.kernel.vm.*;

public class VmC extends VmAbsC {
    public String        name() {return "Generic";}
    public int           code() {return VmConstants.VM_GENERIC;}

    public boolean       canSupportWeakReferences() {return false;}
    public WeakReference newWeakReference(Object object) {return null;}
}

