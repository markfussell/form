/*======================================================================
**
**  File: chimu/formTools/test/TestSchemaAbsC.java
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
import java.io.File;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;

import com.chimu.kernel.collections.*;


/**
Subclasses must specify the directory to use and the path prefix.
**/

public abstract class TestSchemaAbsC implements TestSchema {
    public abstract DatabaseTest createDatabaseTest();
    public abstract DatabaseTest dropDatabaseTest();

    public Array /*of Class(DatabaseTest)*/ tests() {
        Array testNames = testNames();
        List tests = CollectionsPack.newList();
        int size = testNames.size();
        for (int i = 0; i < size; i++) {
            try {
                tests.add(Class.forName((String) testNames.atIndex(i)));
            } catch (ClassNotFoundException e) {};
        }
        return tests;
    }

    public Array /*of String*/ testNames() {
        List testNames = CollectionsPack.newList();

        File directory = directoryPath();
        if (!directory.isDirectory()) {
            throw new RuntimeException(directory.getAbsolutePath()+"is not a directory");
        };
        String[] fileNames = directory.list();
        for (int i=0; i < fileNames.length; i++) {
            String fileName = fileNames[i];
            if (isTestFile(fileName)) {
                fileName = fileName.substring(0,fileName.length()-6);  //6=Length(".class")
                testNames.add(fullJavaPath(fileName));
            };
        };
        if (tracing()) {
            traceStream.println("Tests in Suite: "+testNames);
        }
        return testNames;
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
    protected PrintWriter outputStream = new PrintWriter(System.out,true);

}

