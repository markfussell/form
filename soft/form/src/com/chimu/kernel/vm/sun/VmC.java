/*======================================================================
**
**  File: chimu/kernel/vm/sun/VmC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm.sun;
import com.chimu.kernel.vm.*;

import com.chimu.kernel.collections.impl.CollectionsFactory;
import com.chimu.kernel.collections.impl.sun.*;

public class VmC extends VmAbsC {
    public String        name() {return "SUN";}
    public int           code() {return VmConstants.VM_SUN;}

    public boolean       canSupportWeakReferences() {return true;}
    public WeakReference newWeakReference(Object object) {
        return new WeakReferenceC(object);
    }

    /*subsystem*/ public CollectionsFactory defaultCollectionsFactory() {
        CollectionsFactory defaultFactory = null;
        if (isJglAvailable()) {
            defaultFactory = new SunJglCollectionsFactoryC(useWeakReferences);
        } else {
            defaultFactory = new SunCollectionsFactoryC(useWeakReferences);
        }
        return defaultFactory;
    }

    public void setupUseWeakReferences(boolean useWeak) {
        useWeakReferences = useWeak;
    }

    protected boolean useWeakReferences = true;

}

