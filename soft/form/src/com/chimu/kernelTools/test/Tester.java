/*======================================================================
**
**  File: chimu/kernelTools/test/Tester.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernelTools.test;

import java.util.Date;
import java.io.PrintWriter;

import java.sql.*;
import java.util.*;


import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import com.chimu.form.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.transaction.*;
import com.chimu.form.database.*;
import java.io.PrintWriter;

/**
Tester is a command line program for running tests.
**/
public class Tester {

    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         * RunTest is the main behavior of the Tester.
         */
    public static void runTests(List testCases) {
        testloop: for (int i = 0; i < testCases.size(); i++) {

            Test test = null;
            String testCName = (String) testCases.atIndex(i);
            // startTest("--" + (i + 1) + " :" + testCName + "--");

            try {
                test = (Test) Class.forName(testCName).newInstance();
            } catch (Exception e) {
                System.out.println("Could not create test named "+testCName+" because");
                e.printStackTrace();
                continue testloop;
            }

            if (tracing()) {
                if (!quiet) {
                    output.println("\nTest: ---- "+testCName+" ----");
                    traceStream.println("Test: **"+System.currentTimeMillis()+"**");
                };

                test.setupTracing(traceStream, traceLevel);
            } else {
                if (!quiet) System.out.println("\nTest: ---- "+testCName+" ----");
            }

            try {
                test.run();
            } catch (Exception e2) {
                System.out.println("Test "+testCName+" Failed with the following exception");
                e2.printStackTrace();
                continue testloop;
            }
        }
    }

    static protected boolean tracing() {
        return tracing;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static PrintWriter traceStream  = null;
    static PrintWriter output       = new PrintWriter(System.out);
    static boolean     tracing      = false;
    static int         traceLevel   = 1;
    static boolean     quiet        = false;
    static String      versionString = "1.0";

    static public void main(String[] args) {
        List testCases = CollectionsPack.newList();

        args = getArguments(args);
        if (tracing) {
            System.setErr(System.out);
            traceStream = output;
        };

        int argIndex = 0;
        if (args.length < 1) {
            System.out.println("Tester Version "+versionString);
            System.out.println("Usage:");
            System.out.println("    tester (-t | -tl traceLevel) [-q] (TestC)+");
            System.out.println("or");
            System.out.println("    java com.chimu.kernelTools.test.Tester ...");
            System.out.println();
            System.out.println("Options:");
            System.out.println("    -t                Enable Tracing at detail level 1");
            System.out.println("    -tl <level>       Enable Tracing at detail level <level>");
            System.out.println("    -q                Be quiet.  Do not include test name trace in output.");
            System.out.println();
            System.out.println("    TestC         The test class name or full path name");
            System.out.println();
            System.out.println("Examples:");
            System.out.println("    tester Test1 Test2 ");
            System.out.println("    tester -t com.chimu.kernelTools.test.NullTest");
            return;
        };

        while (argIndex < args.length) {
            testCases.add(args[argIndex++]);
        };

        runTests(testCases);
        output.flush();

        if (!quiet) {
            if (traceStream != null) traceStream.println("\nTest: Tests Done");
            if (traceStream != null) traceStream.println("Test: **"+System.currentTimeMillis()+"**");
        };

    }


    public static String[] getArguments(String[] args) {
        Vector files = new Vector();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            if (arg.equals("-t")) {
                tracing = true;
                traceLevel = 0;
            } else if (arg.equals("-tl")) {
                tracing = true;
                try {
                    traceLevel = Integer.parseInt(args[++i]);
                } catch(Exception e) {
                        //Not a number following the -tl
                    traceLevel = 1;
                    i--;
                }
            } else if (arg.equals("-q")) {
                quiet = true;
            } else {
                files.addElement(arg);
            }
        };
        String[] result = new String[files.size()];
        files.copyInto(result);
        return result;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

}