/*======================================================================
**
**  File: chimu/kernel/vm/jgl/VmC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm.jgl;

import com.chimu.kernel.vm.*;
import com.chimu.kernel.collections.impl.CollectionsFactory;
import com.chimu.kernel.collections.impl.jgl.JglCollectionsFactoryC;

public class VmC extends VmAbsC {
    public String        name() {return "JDK + JGL";}
    public int           code() {return VmConstants.VM_JGL;}

    public boolean       canSupportWeakReferences() {return false;}
    public WeakReference newWeakReference(Object object) {return null;}

    /*subsystem*/ public CollectionsFactory defaultCollectionsFactory() {
        if (isJglAvailable()) return new JglCollectionsFactoryC(false);
        return null;
    }

}

