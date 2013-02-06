/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5/Ex5_EmployeeRetrieval_2.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5;
import java.sql.Connection;
import java.util.Enumeration;

import com.chimu.kernel.collections.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.formTools.test.*;

/**
This query retrieves all of the instances of commissioned employees and salaried employees
**/

public class Ex5_EmployeeRetrieval_2 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

    //==========================================================
    //(P)================ Configuration ========================
    //==========================================================

        createAndConfigureOrm(jdbcConnection);
        ObjectRetriever retriever = orm.retrieverNamed("Employee");

    //==========================================================
    //(P)================== Running ============================
    //==========================================================

       Collection employees = (Collection) retriever.selectAll();

       for (Enumeration enum = employees.elements(); enum.hasMoreElements();) {
            Employee employee = (Employee) enum.nextElement();
            outputStream.println(employee.info());
        };
    }
}