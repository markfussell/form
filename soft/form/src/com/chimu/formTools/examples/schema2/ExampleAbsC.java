/*======================================================================
**
**  File: com/chimu/formTools/examples/schema2/ExampleAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema2;
import com.chimu.formTools.test.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import java.sql.Connection;

public abstract class ExampleAbsC extends FormDatabaseTestAbsC {

    protected void createAndConfigureOrm(Connection jdbcConnection) {
        createOrm(jdbcConnection);
            DomainObjectAbsC_FormInfo.prepareForMapping(orm,dbConnection);
            orm.addInfoClass_withDb(EmployeeC_FormInfo.class,dbConnection);
            orm.addInfoClass_withDb(CompanyC_FormInfo.class,dbConnection);
        orm.doneSetup();
    }

}