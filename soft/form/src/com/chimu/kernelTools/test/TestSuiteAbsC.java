/*======================================================================
**
**  File: chimu/kernelTools/test/TestSuiteAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernelTools.test;

import java.io.PrintWriter;
import java.io.File;

import com.chimu.kernel.collections.*;


/**
Subclasses must specify the directory to use and the path prefix.
**/

public abstract class TestSuiteAbsC implements Test {
    public void run() {
        runTests(tests());
    }

    public void setupTracing(PrintWriter newTraceStream, int newTraceLevel) {
        this.traceStream = newTraceStream;
        this.traceLevel = newTraceLevel;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected void runTests(Array testCases) {
        PrintWriter output = new PrintWriter(System.out);
        testloop: for (int i = 0; i < testCases.size(); i++) {

            Test test = null;
            String testCName = (String) testCases.atIndex(i);

            try {
                test = (Test) Class.forName(testCName).newInstance();
            } catch (Exception e) {
                System.out.println("Could not create test named "+testCName+" because");
                e.printStackTrace();
                continue testloop;
            }

            if (tracing()) {
                traceStream.println("\n  Test: ---- "+testCName+" ----");
                traceStream.println("  Test: **"+System.currentTimeMillis()+"**");

                test.setupTracing(traceStream, traceLevel);
            } else {
                if (!quiet) System.out.println("\n  Test: ---- "+testCName+" ----");
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

    protected List tests() {
        List tests = CollectionsPack.newList();

        File directory = directoryPath();
        if (!directory.isDirectory()) {
            throw new RuntimeException(directory.getAbsolutePath()+"is not a directory");
        };
        String[] fileNames = directory.list();
        for (int i=0; i < fileNames.length; i++) {
            String fileName = fileNames[i];
            if (isTestFile(fileName)) {
                fileName = fileName.substring(0,fileName.length()-6);  //6=Length(".class")
                tests.add(fullJavaPath(fileName));
            };
        };
        if (tracing()) {
            traceStream.println("Tests in Suite: "+tests);
        }
        return tests;
    }

    protected boolean isTestFile(String fileName) {
        return fileName.endsWith(".class");
    };

    protected abstract File directoryPath();
    protected abstract String javaPath();

    protected String fullJavaPath(String className) {
        if (javaPath().length() > 0) {
            return javaPath()+"."+className;
        } else {
            return className;
        }
    }

    protected boolean tracing() {
        return traceStream != null;
    }

    protected boolean     quiet = false;
    protected PrintWriter traceStream;
    protected int         traceLevel;

}

