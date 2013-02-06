/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3/DropExampleDatabase.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3;
import java.sql.Connection;
import com.chimu.formTools.examples.DatabaseSetupAbsC;

public class DropExampleDatabase extends DatabaseSetupAbsC {

    public void run(Connection jdbcConnection) {
        setupDatabaseConnection(jdbcConnection);
        setupDatabaseProductType();
        setupTypeStrings();

        dropScheme();
    }

    protected void dropScheme() {
        executeString_ignoreError("DROP TABLE GeneratorCounters");

        executeString_ignoreError("DROP TABLE Person");
        executeString_ignoreError("DROP TABLE Employee");
        executeString_ignoreError("DROP TABLE Company");

        executeString_ignoreError("DROP TABLE Project");
        executeString_ignoreError("DROP TABLE ProjectMembers");
    }

}
