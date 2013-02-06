/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1b/Ex1b_Delete_1.java
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
Ex1b_Delete_1 deletes an existing person from the database.
**/

public class Ex1b_Delete_1 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper personMapper = orm.mapperNamed("Person");

        Collection people1 = personMapper.selectAll();
        outputStream.println("Currently have "+people1.size()+" people on the server");

        Person aPerson = (Person) personMapper.findAny();
        aPerson.delete();

        Collection people2 = personMapper.selectAll();
        outputStream.println("After delete, there are now "+people2.size()+" people on the server");

    }
}