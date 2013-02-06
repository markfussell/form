/*======================================================================
**
**  File: chimu/formTools/test/TestScheme.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.test;
import com.chimu.kernel.collections.*;

/**
**/
public interface TestSchema {
    public DatabaseTest createDatabaseTest();
    public DatabaseTest dropDatabaseTest();
    public Array /*of DatabaseTest*/ tests();
    public Array /*of String*/ testNames();
}

