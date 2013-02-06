/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Ex1_PersonRetrieval_2.java
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
import com.chimu.form.mapping.*;

import com.chimu.formTools.example.*;
import com.chimu.formTools.test.*;

/**
Ex1_PersonRetrieval_2 selects all the people from the Person Extent
and then displays those with a long name.
**/
public class Ex1_PersonRetrieval_2 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectRetriever personRetriever = orm.retrieverNamed("Person");

        List people = (List) personRetriever.selectAll();
        outputStream.println(people);

        int size = people.size();
        for (int i = 0; i<size; i++) {
            Person person = (Person) people.atIndex(i);
            if (person.getName().length() > 15) {
                outputStream.println(person.getInfo());
            }
        }
    }
}