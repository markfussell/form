/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/Ex1_OqlQuery_1.java
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
import com.chimu.form.oql.*;

import com.chimu.formTools.example.*;
import com.chimu.formTools.test.*;

/**
Ex1_OqlQuery_1 shows a very simple OQL based query which finds a person.
This is equivalent to Ex1_PersonRetrieval_1.
**/

public class Ex1_OqlQuery_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);

        OqlQuery oql = orm.newOqlQuery("FROM Person person");
        Object person = oql.query().findAny();

        outputStream.println(person);
    }
}