/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3b/Ex3b_EmployeeInsert_2.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3b;
import java.sql.Connection;

import com.chimu.form.query.*;
import com.chimu.form.transaction.*;
import com.chimu.form.oql.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

/**
Ex3b_EmployeeInsert_2 tests the transaction capabilities by
writing two objects together.
**/
public class Ex3b_EmployeeInsert_2 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);

    //==========================================================
    //(P)==================== Running ==========================
    //==========================================================

        // Find the starting number of NewEmployees so we can
        // see if the writes were successful or not

        OqlQuery oql = orm.newOqlQuery(
                "FROM Employee anEmployee "+
                "WHERE   anEmployee.name = 'NewEmployee'"
            );
        QueryDescription query = oql.query();

        int count = query.countAll();
        outputStream.println("Started with "+count+" NewEmployees on the database");

    //======================================
    //======================================
    //======================================

        Employee newEmployee = new EmployeeC();
        newEmployee.setName("NewEmployee");
        Employee newEmployee2 = (Employee) newEmployee.copy();
        newEmployee2.setName("NewEmployee2");

        outputStream.println("Writing to database: " + newEmployee.name() + " and " + newEmployee2.name());
        writeBoth(newEmployee, newEmployee2);

        count = query.countAll();
        outputStream.println("Now have "+count+" NewEmployees on the database");
    }

    protected void writeBoth(final DomainObject object1, final DomainObject object2) {
        try {
            tc.executeProcedure(
                new Procedure0Arg() {public void execute(){
                    if (object1 != null) object1.write();
                    if (object2 != null) object2.write();
                }}
            );
        } catch (TransactionException e) {
            handleRollback();
            throw new RuntimeWrappedException("Could not write both "+object1+" and "+object2,e);
        };
    }

}