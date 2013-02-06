/*======================================================================
**
**  File: chimu/kernel/collections/impl/SetAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.collections.impl;

import com.chimu.kernel.collections.*;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/**
SetAbsC support operations to add multiple occurrences of elements
**/

public abstract class SetAbsC
                                    extends CollectionAbsC
                                    implements Set {


        /**
         * A set does not care if the object is absent previously...
         * it won't add it again
         */
    public final void addIfAbsent (Object object) {add(object);}
}

