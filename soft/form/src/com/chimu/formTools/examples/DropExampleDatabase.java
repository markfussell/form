/*======================================================================
**
**  File: chimu/formTools/examples/DropExampleDatabase.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.examples;

import java.sql.Connection;

public class DropExampleDatabase extends DatabaseSetupAbsC {

    public void run(Connection jdbcConnection) {
        setupDatabaseConnection(jdbcConnection);
        setupDatabaseProductType();
        setupTypeStrings();

        dropScheme();
        outputStream.println("Database Dropped");
    }

    protected void dropScheme() {
        executeString_ignoreError("DROP TABLE GeneratorCounters");

        executeString_ignoreError("DROP TABLE Person");
        executeString_ignoreError("DROP TABLE Employee");
        executeString_ignoreError("DROP TABLE Company");

        executeString_ignoreError("DROP TABLE Project");
        executeString_ignoreError("DROP TABLE ProjectMembers");
        executeString_ignoreError("DROP TABLE Currency");        
    }

}
