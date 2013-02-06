/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Ex1_PersonRetrieval_1c.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;
import java.sql.Connection;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;

import com.chimu.formTools.example.*;
import com.chimu.formTools.test.*;

/**
Ex1_PersonRetrieval_1 finds a single Person from the Person Extent.
**/

public class Ex1_PersonRetrieval_1c extends com.chimu.formTools.examples.ExampleAbsC {
    public void run (Connection jdbcConnection) {

        //==========================================================
        //======================= Configuring ======================
        //==========================================================

        Orm orm = FormPack.newOrm();
        DatabaseConnection dbConnection =
                FormPack.newDatabaseConnection(jdbcConnection);

            orm.addInfoClass_withDb(
                    PersonC_FormInfo.class, dbConnection);
        orm.doneSetup();

        //==========================================================
        //========================= Running ========================
        //==========================================================

        ObjectRetriever personRetriever = orm.retrieverNamed("Person");

        Person person = (Person) personRetriever.findAny();
        outputStream.println(person);
        outputStream.println(person.getInfo());
    }

    public String description() {
        return "Find a single Person from the Person Extent";
    }
}