/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1b/Ex1b_Write_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1b;
import java.sql.Connection;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.formTools.test.*;

/**
Ex1b_Write_1 shows both inserting and updating a person using the #write message.
**/
public class Ex1b_Write_1 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper personMapper = orm.mapperNamed("Person");

        Person newPerson = new PersonC();
        newPerson.setName("Kity Purrbox");
        newPerson.setEmail("purrboki");
        newPerson.setHeight(34);

        Person updatePerson = (Person) personMapper.findAny();
        outputStream.println("Found " + updatePerson.info());
        int newHeight = updatePerson.height() + 5;
        updatePerson.setHeight(newHeight);

        updatePerson.write();  //inserts to the database, since it's new.
        newPerson.write();  //updates the database, since the row already exists.

        outputStream.println("Updated Person is: "+updatePerson.info());
        outputStream.println("New Person is: "+newPerson.info());
    }
}