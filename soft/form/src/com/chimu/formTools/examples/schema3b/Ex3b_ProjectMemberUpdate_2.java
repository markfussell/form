/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3b/Ex3b_ProjectMemberUpdate_2.java
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
import com.chimu.form.oql.*;

import com.chimu.formTools.test.*;

/**
    Replaces the entire collection of project members
**/

public class Ex3b_ProjectMemberUpdate_2 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper projectMapper = orm.mapperNamed("Project");
        ObjectMapper employeeMapper = orm.mapperNamed("Employee");

        final Project project = (Project) projectMapper.findAny();

        outputStream.println("Prior to updating project: ");
        List aSeq = (List) project.employees();
        int size = aSeq.size();
        for (int i = 0; i < size; i++) {
            outputStream.println("Project Member " + (i+1) + " is " + aSeq.atIndex(i));
        }

        OqlQuery oql = orm.newOqlQuery(
        "FROM Employee employee " +
        "WHERE employee.height <= 60 "
        );
        List employees = (List) oql.query().selectAll();

        project.setEmployees(employees);

        transactionWrite(project);

        outputStream.println("After updating project: ");
        List projEmployees = (List) project.employees();
        size = projEmployees.size();

        for (int i = 0; i < size; i++) {
            outputStream.println("Project Member " + (i+1) + " is " + projEmployees.atIndex(i));
        };
    }
}
