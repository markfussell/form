/*======================================================================
**
**  File: chimu/formTools/example/FormExampleAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.example;

import java.sql.Connection;
import java.io.PrintStream;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;

import com.chimu.formTools.test.*;

/**
This is the program that is used to drive the functionality for
the UsingFORM examples.

This is an abstract super class.  All test cases should inherit from
this class and implements the DatabaseTest interface.  See Example:


**/

public abstract class FormExampleAbsC implements DatabaseTest {

   public void setupTracing(PrintStream newTraceStream, int newTraceLevel) {
        this.traceStream = newTraceStream;
        this.traceLevel = newTraceLevel;
   }

   public abstract void run(Connection jdbcConnection);


    protected boolean tracing() {
        return traceStream != null;
    }

    protected PrintStream traceStream;
    protected int traceLevel;

}

