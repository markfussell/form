/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4c/SetupExampleDatabase.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4c;
import com.chimu.formTools.examples.FormExampleDatabasesAbsC;


public class SetupExampleDatabase extends FormExampleDatabasesAbsC {

    protected void createScheme() {
        createCounter();

        createEmployee2();
        createCompany4c();

        createProject4b();
        createProjectMembers();
    }

    protected void populateScheme() {
        populateCounter();

        populateEmployee2();
        populateCompany4c();

        populateProject4b();
        populateProjectMembers();
     }


}