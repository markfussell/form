/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/ObjectBase_FormC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;

import java.sql.Connection;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;

/**
The ObjectBase provides...
**/
public class ObjectBase_FormC implements ObjectBase {

    public PersonCategory getPersonCategory() {
        return myPersonCategory;
    }


    public ObjectBase initJdbc(Connection jdbcConnection) {
        myOrm = createOrm(jdbcConnection);
        myPersonCategory = new PersonCategory_FormC().initRetriever(myOrm.retrieverNamed("Person"));
        return this;
    }

    protected Orm createOrm(Connection jdbcConnection) {
        Orm orm = FormPack.newOrm();
        DatabaseConnection dbConnection =
                FormPack.newDatabaseConnection(jdbcConnection);

            orm.addInfoClass_withDb(
                    PersonC_FormInfo.class, dbConnection);
        orm.doneSetup();
        return orm;
    }

    //==========================================================
    //==========================================================
    //==========================================================

    protected PersonCategory myPersonCategory;
    protected Orm myOrm;
}

