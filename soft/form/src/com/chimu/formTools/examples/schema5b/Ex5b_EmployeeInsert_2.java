/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5b/Ex5b_EmployeeInsert_2.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5b;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Date;

import com.chimu.kernel.collections.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.formTools.test.*;

/**
    This query inserts both types of employees with their appropriate identifiers
    into the table
**/

public class Ex5b_EmployeeInsert_2 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

    //==========================================================
    //(P)================== Configuration ======================
    //==========================================================

        createAndConfigureOrm(jdbcConnection);
        ObjectRetriever companyMapper = orm.retrieverNamed("Company");
        ObjectRetriever employeeMapper = orm.retrieverNamed("Employee");

    //==========================================================
    //(P)==================== Running ==========================
    //==========================================================
        Company aCompany = (Company) companyMapper.findAny();

        Employee salariedEmployee =
                new SalariedEmployeeC("Dr. Watson", "watson@privateeye.com",
                                68, aCompany, new java.sql.Timestamp((new Date()).getTime()), new Integer(80000));
        salariedEmployee.write();
        outputStream.println("Writing new salaried employee " + salariedEmployee.info());

        Float percentage = new Float("0.2345");
         Employee commissionedEmployee =
                new CommissionedEmployeeC("Sherlock Holmes", "sherlock@privateeye.com",
                                72, aCompany, new java.sql.Timestamp((new Date()).getTime()), new Integer(50000), percentage);

        commissionedEmployee.write();
        outputStream.println("Writing new commissioned employee " + commissionedEmployee.info());

    }
}