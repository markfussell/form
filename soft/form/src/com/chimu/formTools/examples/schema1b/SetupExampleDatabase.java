/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1b/SetupExampleDatabase.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1b;
import com.chimu.formTools.examples.FormExampleDatabasesAbsC;


public class SetupExampleDatabase extends FormExampleDatabasesAbsC {

    protected void createScheme() {
        createCounter();
        createPerson();
    }

    protected void populateScheme() {
        populateCounter();
        populatePerson1();
    }

}