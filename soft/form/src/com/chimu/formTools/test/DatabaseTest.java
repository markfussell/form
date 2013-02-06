/*======================================================================
**
**  File: chimu/formTools/test/DatabaseTest.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.test;

import java.sql.Connection;
import java.io.PrintWriter;

/**
A DatabaseTest can be run given a JDBC Connection.  It also supports
setting up tracing options for the run.  A DatabaseTest is just like
a com.chimu.kernelTools.test.Test but has an incompatible protocol because
of the database information.
@see com.chimu.kernelTools.test.Test
**/
public interface DatabaseTest {

        /**
         * Run executes the test
         */
    public void run(Connection jdbcConnection);
    public void setupTracing(PrintWriter newTraceStream, int newTraceLevel);
    public void setupOutput(PrintWriter newOutputStream);
    public String name();
}

