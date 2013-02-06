/*======================================================================
**
**  File: chimu/kernel/collections/impl/MapAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/**


**/

public abstract class MapAbsC
                                    extends NotImplementedMapAbsC
                                    implements  Map {

    //**********************************************************
    //(P)*************** FixedKeyedCollection ******************
    //**********************************************************

    public KeyedCollection
            copyRemovingKey(Object key) {
        Map result = (Map) copy();
        result.removeKey(key);
        return result;
    }

}
