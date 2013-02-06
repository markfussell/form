/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1b/Ex1b_Insert_1.java
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
Ex1b_Insert_1 inserts a new person into the database.
**/
public class Ex1b_Insert_1 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);

        String  name   = "Mary Houdini";
        String  email  = "houdima";
        int     height = 60;

        final Person newPerson = new PersonC(name, email, height);

        newPerson.write();

        outputStream.println(newPerson);
    }
}