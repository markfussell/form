/*======================================================================
**
**  File: chimu/kernelTools/test/Test.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernelTools.test;

import java.sql.Connection;
import java.io.PrintWriter;

/**
A Test can be run given a JDBC Connection.
**/
public interface Test {// extends Test {

        /**
         * Run executes the test
         */
    public void run();
    public void setupTracing(PrintWriter newTraceStream, int newTraceLevel);
}

