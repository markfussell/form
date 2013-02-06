/*======================================================================
**
**  File: chimu/kernelTools/test/TestAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernelTools.test;

import java.io.PrintWriter;


/**
**/

public abstract class TestAbsC implements Test {

    public void setupTracing(PrintWriter newTraceStream, int newTraceLevel) {
        this.traceStream = newTraceStream;
        this.traceLevel = newTraceLevel;
    }

    public abstract void run();


    protected boolean tracing() {
        return traceStream != null;
    }

    protected PrintWriter traceStream;
    protected int traceLevel;

}

