/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/SetupExampleDatabase.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;
import com.chimu.formTools.examples.FormExampleDatabasesAbsC;


public class SetupExampleDatabase extends FormExampleDatabasesAbsC {

    public void createScheme() {
        createPerson();
    }

    public void populateScheme() {
        populatePerson1();
    }

}