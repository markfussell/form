/*======================================================================
**
**  File: chimu/formTools/test/DatabaseTestAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.test;

import java.sql.Connection;
import java.sql.*;
import java.util.*;
import java.io.PrintWriter;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.transaction.*;

import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;

/**
This is the program that is used to drive the functionality for
the UsingFORM examples.

This is an abstract super class.  All test cases should inherit from
this class and implements the DatabaseTest interface.  See Example:


**/

public abstract class DatabaseTestAbsC implements DatabaseTest {

    public String name() {
        return getClass().toString();
    }

    public void setupOutput(PrintWriter newOutputStream) {
        this.outputStream = newOutputStream;
    }

    public void setupTracing(PrintWriter newTraceStream, int newTraceLevel) {
        this.traceStream = newTraceStream;
        this.traceLevel = newTraceLevel;
    }

    public abstract void run(Connection connection);

    protected boolean tracing() {
        return traceStream != null;
    }

    protected void tracePrint(String outString) {
        if (tracing()) {
            traceStream.print("trace: "+outString);
        };
    }

    protected void tracePrintln(String outString) {
        if (tracing()) {
            traceStream.println("trace: "+outString);
        };
    }

    protected PrintWriter traceStream;
    protected PrintWriter outputStream = new PrintWriter(System.out,true);
    protected int traceLevel = -1;

}

