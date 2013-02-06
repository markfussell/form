/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Ex1_Query_2.java
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
Ex1_Query_2 retrieves all the email addresses of people.
**/
public class Ex1_Query_2 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectRetriever personRetriever = orm.retrieverNamed("Person");

    //==========================================================
    //(P)================ Creating Query =======================
    //==========================================================

        QueryDescription query = orm.newQueryDescription();
        QueryVar personVar = query.newExtentVar(personRetriever);

        QueryVar personEmailVar = personVar.slotNamed("email");
        query.setResultsVar(personEmailVar);


    //==========================================================
    //(P)==================== Running ==========================
    //==========================================================

        Collection names = query.selectAll();
        outputStream.println(names);

    }
}