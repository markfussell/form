/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/ExampleAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5c;
import com.chimu.formTools.test.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import java.sql.Connection;

public abstract class ExampleAbsC extends ExampleDescriptionAbsC{
    protected void createAndConfigureOrm(Connection jdbcConnection) {
        setupDatabaseConnection(jdbcConnection);       

        createOrm(jdbcConnection);

            DomainObjectAbsC_FormInfo.prepareForMapping(orm,dbConnection);
            orm.addInfoClass_withDb(CommissionedEmployeeC_FormInfo.class,dbConnection);
            orm.addInfoClass_withDb(SalariedEmployeeC_FormInfo.class,dbConnection);
            orm.addInfoClass_withDb(Employee_FormInfo.class, dbConnection);

            orm.addInfoClass_withDb(CompanyC_FormInfo.class,dbConnection);
            orm.addInfoClass_withDb(ProjectC_FormInfo.class,dbConnection);
            orm.addInfoClass_withDb(AssociationsFormInfo.class,dbConnection);
        orm.doneSetup();
    }

    protected void setupTypeStrings() {
        databaseProduct.setupTypeStrings();
       
    }
    
    protected void setupDatabaseConnection(Connection connection) {     
        dbConnection = FormPack.newDatabaseConnection(connection);
        databaseProduct = dbConnection.databaseProduct();
        databaseProduct.setupTypeStrings();
    }
    



}