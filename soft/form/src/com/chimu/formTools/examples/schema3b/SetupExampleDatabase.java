/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3b/SetupExampleDatabase.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3b;
import com.chimu.formTools.examples.FormExampleDatabasesAbsC;


public class SetupExampleDatabase extends FormExampleDatabasesAbsC {

    protected void createScheme() {
        createCounter();

        createEmployee2();
        createCompany2();

        createProject3();
        createProjectMembers();
    }

    protected void populateScheme() {
        populateCounter();

        populateEmployee2();
        populateCompany2();

        populateProject3();
        populateProjectMembers();
    }


}