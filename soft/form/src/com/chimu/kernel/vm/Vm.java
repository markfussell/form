/*======================================================================
**
**  File: chimu/kernel/vm/Vm.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm;

public interface Vm {
    public String name();
    public int    code();

    public boolean       canSupportWeakReferences();
    public WeakReference newWeakReference(Object object);

    public void setupUseWeakReferences(boolean useWeak);

        /**
         * Install prepares the VM specific features in the
         * rest of Kernel
         */
    public void install();
}

