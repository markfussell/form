/*======================================================================
**
**  File: chimu/kernel/meta/ClassInformationRegistry.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.meta;

import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;


public class ClassInformationRegistry {
    private ClassInformationRegistry() {}; //LibraryOnly

    static private Map registry = CollectionsPack.newMap();

    static public Map newInformationForC (Class aC) {
        Map classInfo = (Map) registry.atKey(aC);
        if (classInfo == null) {
            classInfo = CollectionsPack.newMap();
            registry.atKey_put(aC,classInfo);
        };
        return classInfo;
    };

    static public Map getInformationForC(Class aC) {
        return (Map) registry.atKey(aC);
    };

    static public void clearInformationForC(Class aC) {
        registry.removeKey(aC);
    };
};
