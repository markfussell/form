/*======================================================================
**
**  File: chimu/kernel/collections/impl/sun/SunJglCollectionsFactoryC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl.sun;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.collections.impl.jdk.*;
import com.chimu.kernel.collections.impl.jgl.*;

import com.chimu.kernel.functors.*;

import java.util.Vector;

/**


**/

public class SunJglCollectionsFactoryC extends SunCollectionsFactoryC {
    public SunJglCollectionsFactoryC(boolean useWeakCollections) {
        this.useWeakCollections = useWeakCollections;
    }

    public SunJglCollectionsFactoryC() {}

    //**********************************************************
    //**********************************************************
    //**********************************************************


    public Set newSet() {
        return new JglHashSetWrapperC(new com.objectspace.jgl.HashSet()); // new JdkWrapped
    }

}

