/*======================================================================
**
**  File: chimu/kernel/vm/VmAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.vm;
import com.chimu.kernel.collections.impl.CollectionsFactory;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.collections.impl.jgl.JglCollectionsFactoryC;

public abstract class VmAbsC implements VmSi {
    public String toString() {return name();}

    /*subsystem*/ public void install() {
        CollectionsPack.setupDefaultFactory();
    };

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static protected boolean isJglAvailable() {
        Class aC = null;
        try {
            aC = Class.forName("com.objectspace.jgl.HashSet");
        } catch (Exception e) {};
        return aC != null;
    }

    //**********************************************************

        /**
         * Return a defaultCollectionsFactory for this VM.
         * This allows VM specific features within the
         * collection functionality: specifically WeakReferences.
         */
    /*subsystem*/ public CollectionsFactory defaultCollectionsFactory() {
        return null;
    }

    public void setupUseWeakReferences(boolean useWeak) {};
}

