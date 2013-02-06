/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Ex1_Query_4.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;
import java.sql.Connection;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import java.util.Enumeration;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.formTools.test.*;

/**
Ex1_Query_4 retrieves all the people between a particular height range
or who have a particular email address.
**/
public class Ex1_Query_4 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectRetriever personRetriever = orm.retrieverNamed("Person");

    //==========================================================
    //(P)================= Creating Query ======================
    //==========================================================

        QueryDescription query = orm.newQueryDescription();
        QueryVar personVar = query.newExtentVar(personRetriever);

        QueryVar personEmailVar  = personVar.slotNamed("email");
        Constant anEmail    = query.newConstant("hhoudini");

        QueryVar personHeightVar = personVar.slotNamed("height");
        Constant lowHeight  = query.newConstant(new Integer(72));  //height is in inches
        Constant highHeight = query.newConstant(new Integer(65));  //height is in inches

                Condition c1 = query.newGreaterThanEqualTo(personHeightVar , lowHeight);
                Condition c2 = query.newLessThanEqualTo   (personHeightVar , highHeight);
            Condition c3 = query.newAnd(c1 , c2 );
            Condition c4 = query.newEqualTo(personEmailVar, anEmail);
        Condition c5 = query.newOr(c3 , c4 );
        query.setCondition(c5);

    //==========================================================
    //(P)==================== Running ==========================
    //==========================================================

        Collection people = query.selectAll();
        for (Enumeration enum = people.elements(); enum.hasMoreElements();) {
            outputStream.println(((Person) enum.nextElement()).getInfo());
        };
    }
}