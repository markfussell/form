/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4e/SetupExampleDatabase.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4e;
import com.chimu.formTools.examples.FormExampleDatabasesAbsC;


public class SetupExampleDatabase extends FormExampleDatabasesAbsC {

    protected void createScheme() {
        createCounter();

        createEmployee2();
        createCompany4e();

        createProject4b();
        createProjectMembers();


        createCurrency4e();
    }

    protected void populateScheme() {
        populateCounter();

        populateEmployee2();
        populateCompany4e();

        populateProject4b();
        populateProjectMembers();

        populateCurrency4e();
     }


}