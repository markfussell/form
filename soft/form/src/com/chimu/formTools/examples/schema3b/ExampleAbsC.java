/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3b/ExampleAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3b;
import com.chimu.formTools.test.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.transaction.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

import java.sql.Connection;

public abstract class ExampleAbsC extends FormDatabaseTestAbsC {

    protected void createAndConfigureOrm(Connection jdbcConnection) {
        createOrm(jdbcConnection);
            createTransactionCoordinator();
            DomainObjectAbsC_FormInfo.prepareForMapping(orm,dbConnection);
            orm.addInfoClass_withDb(EmployeeC_FormInfo.class,dbConnection);
            orm.addInfoClass_withDb(CompanyC_FormInfo.class,dbConnection);
            orm.addInfoClass_withDb(ProjectC_FormInfo.class,dbConnection);
            orm.addInfoClass_withDb(AssociationsFormInfo.class,dbConnection);
        orm.doneSetup();
    }

    protected void createTransactionCoordinator() {
        tc = dbConnection.newTransactionCoordinator();
            // The next line is disabled so we will use whatever is the default
            //     and database acceptable isolation level.
            // Otherwise we need to verify that the dbConnection supports the
            //     particular level.
        // tc.setupTransactionIsolationLevel(Connection.TRANSACTION_READ_UNCOMMITTED);
                //   TRANSACTION_READ_UNCOMMITTED, TRANSACTION_READ_COMMITTED,
                //   TRANSACTION_REPEATABLE_READ,  TRANSACTION_SERIALIZABLE
    }

    protected void handleRollback() {
        DomainObjectAbsC_FormInfo.resetAfterRollback();
    }

    //======================================
    //======================================
    //======================================

    protected void transactionWrite(final DomainObject object1) {
        try {
            tc.executeProcedure(
                new Procedure0Arg() {public void execute(){
                    if (object1 != null) object1.write();
                }}
            );
        } catch (TransactionException e) {
            handleRollback();
            throw new RuntimeWrappedException("Could not write "+object1,e);
        };
    }

}