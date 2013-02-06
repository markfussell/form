/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Ex1_Query_3.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;
import java.sql.Connection;
import java.util.Enumeration;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import com.chimu.form.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

/**
Ex1_Query_3 retrieves all the people with a particular email address.
**/
public class Ex1_Query_3 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectRetriever personRetriever = orm.retrieverNamed("Person");

    //==========================================================
    //(P)================= Creating Query ======================
    //==========================================================

        QueryDescription query = orm.newQueryDescription();
        QueryVar personVar = query.newExtentVar(personRetriever);

        QueryVar  personEmailVar = personVar.slotNamed("email");
        Constant  anEmail        = query.newConstant("hhoudini");
        Condition condition      = query.newEqualTo(personEmailVar, anEmail);

        query.setCondition(condition);

    //==========================================================
    //(P)==================== Running ==========================
    //==========================================================

        Collection people = query.selectAll();
        for (Enumeration enum = people.elements(); enum.hasMoreElements();) {
            outputStream.println(((Person) enum.nextElement()).getInfo());
        };
    }
}