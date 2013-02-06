/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5b/SetupExampleDatabase.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5b;
import com.chimu.formTools.examples.FormExampleDatabasesAbsC;

public class SetupExampleDatabase extends FormExampleDatabasesAbsC {

    protected void createScheme() {
        createCounter();

        createEmployee5();
        createCompany4d();

        createProject4b();
        createProjectMembers();
    }

    protected void populateScheme() {
        populateCounter();

        populateEmployee5();
        populateCompany4d();
        populateProject4b();
        populateProjectMembers();
     }


}