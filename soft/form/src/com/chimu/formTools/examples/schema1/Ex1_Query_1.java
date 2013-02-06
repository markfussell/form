/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Ex1_Query_1.java
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
import com.chimu.form.query.*;

import com.chimu.formTools.test.*;

/**
Ex1_Query_1 uses a QueryDescription to select all the people from an extent.
This is the same as Ex1_PersonRetrieval_2.
**/
public class Ex1_Query_1 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectRetriever personRetriever = orm.retrieverNamed("Person");

    //==========================================================
    //(P)================ Creating Query =======================
    //==========================================================

        QueryDescription query = orm.newQueryDescription();
        QueryVar personVar = query.newExtentVar(personRetriever);

    //==========================================================
    //(P)==================== Running ==========================
    //==========================================================

        Collection persons = (Collection) query.selectAll();
        outputStream.println(persons);

    }
}