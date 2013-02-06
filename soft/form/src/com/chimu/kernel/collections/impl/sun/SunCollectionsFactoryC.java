/*======================================================================
**
**  File: chimu/kernel/collections/impl/sun/SunCollectionsFactoryC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl.sun;

import com.chimu.kernel.collections.impl.factories.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.collections.impl.jdk.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

import java.util.Vector;

/**


**/

public class SunCollectionsFactoryC extends CollectionsFactoryAbsC {
    public SunCollectionsFactoryC(boolean useWeakCollections) {
        this.useWeakCollections = useWeakCollections;
    }

    public SunCollectionsFactoryC() {}

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

