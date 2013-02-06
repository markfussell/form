/*======================================================================
**
**  File: chimu/formTools/test/FormTestSchemeAbsC.java
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

public abstract class FormTestSchemaAbsC
                            extends TestSchemaAbsC
                            implements FormTestSchema
                            {

    protected boolean isTestFile(String fileName) {
        return fileName.startsWith("Ex") && (fileName.indexOf('_') > 0) &&
               (fileName.indexOf('$') < 0 ) && fileName.endsWith(".class");
    };

    //==========================================================
    //==========================================================
    //==========================================================

    protected String schemaName() {
        String name = getClass().getName();
        int index = name.lastIndexOf('.');
        if (index != -1) name = name.substring(0,index);
        return name;
    }

    public DatabaseTest createDatabaseTest() {
        try {
            Class createC;
            String name = getClass().getName();
            int index = name.lastIndexOf('.');
            if (index != -1) name = name.substring(index+1);
            createC = Class.forName(schemaName()+"."+"SetupExampleDatabase");
            return (DatabaseTest) createC.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public DatabaseTest dropDatabaseTest() {
        try {
            Class createC;
            createC = Class.forName(schemaName()+"."+"DropExampleDatabase");
            return (DatabaseTest) createC.newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    //==========================================================
    //==========================================================
    //==========================================================

    public void setupRootDirectory(File newRootDirectory) {
        rootDirectory = newRootDirectory ;
    }

    protected File directoryPath() {
        return new File(rootDirectory,schemaName());
    }
    protected String javaPath() {
        return schemaName();
    }

    public String toString() {
        return schemaName();
    }

    protected File rootDirectory=null;


}

