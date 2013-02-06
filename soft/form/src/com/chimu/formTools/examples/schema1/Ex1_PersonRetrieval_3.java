/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Ex1_PersonRetrieval_3.java
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
Ex1_PersonRetrieval_3 finds a person with a particular name
**/
public class Ex1_PersonRetrieval_3 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectRetriever personRetriever = orm.retrieverNamed("Person");

        Person person = (Person) personRetriever.findWhereSlotNamed_equals("name", "Patricio Steel");
        outputStream.println(person.getInfo());
    }
}