/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3b/Ex3b_EmployeeInsert_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3b;
import java.sql.Connection;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import java.util.Enumeration;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.transaction.*;
import com.chimu.form.oql.*;


import com.chimu.formTools.test.*;

/**
Ex3b_EmployeeInsert_1 tests the transaction capabilities by
purposely generating an error within the transaction. Also
includes a successful write.
**/
public class Ex3b_EmployeeInsert_1 extends ExampleAbsC {

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

        final Employee newEmployee = new EmployeeC();
        newEmployee.setName("NewEmployee");

        try {
            tc.executeProcedure(
                new Procedure0Arg() {public void execute(){
                    newEmployee.write();
                    throw new RuntimeException("Fake Error");     //throw an error to test roll back
                }}
            );
        } catch (TransactionException e) {
            outputStream.println("Error enoucntered.  Could not write employee: "+newEmployee+" to the database");
            handleRollback();
        }

        count = query.countAll();
        outputStream.println("Now have "+count+" NewEmployees on the database");

    //======================================
    //======================================
    //======================================

        final Employee newEmployee2 = new EmployeeC();
        newEmployee2.setName("NewEmployee");

        try {
            outputStream.println("Now writing " + newEmployee2.name() + " to the database.");
            tc.executeProcedure(
                new Procedure0Arg() {public void execute(){
                    newEmployee2.write();
                }}
            );
        } catch (TransactionException e) {
            outputStream.println("Could not write employee: "+newEmployee2+" to the database");
            handleRollback();
        }

        count = query.countAll();
        outputStream.println("Now have "+count+" NewEmployees on the database");

    }
}