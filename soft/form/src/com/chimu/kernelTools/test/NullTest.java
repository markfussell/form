/*======================================================================
**
**  File: chimu/kernelTools/test/NullTest.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernelTools.test;


import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;


/**
This is the program that is used to drive the functionality for
the UsingFORM examples.

**/
public class NullTest extends TestAbsC {

    public void run() {
        if (tracing()) {
            traceStream.println("Null Test was successful");
        }
    }

}