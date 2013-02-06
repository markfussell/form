/*======================================================================
**
**  File: chimu/kernel/collections/impl/ms/MsCollectionsFactoryC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl.ms;

import com.chimu.kernel.collections.impl.factories.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.collections.impl.ms.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

import java.util.Vector;

/**


**/

public class MsCollectionsFactoryC extends CollectionsFactoryAbsC {
    public MsCollectionsFactoryC(boolean useWeakCollections) {
        this.useWeakCollections = useWeakCollections;
    }

    public MsCollectionsFactoryC() {}

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public boolean canSupportWeakCollections() {
        return useWeakCollections;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Map newWeakMap() {
        if (useWeakCollections) {
            return new JdkWeakHashtableWrapperC (
                    new WeakHashtableC()
                );
        } else {
            return newMap();
        };
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected boolean useWeakCollections = false;
}

