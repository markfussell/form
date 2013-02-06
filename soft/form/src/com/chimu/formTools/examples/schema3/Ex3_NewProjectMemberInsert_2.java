/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3/Ex3_NewProjectMemberInsert_2.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Date;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.oql.*;


import com.chimu.formTools.test.*;

/*
    Adds a new member into the project, if the employee is not already
    working on the project.
*/
public class Ex3_NewProjectMemberInsert_2 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);

        ObjectMapper projectMapper = orm.mapperNamed("Project");
        ObjectRetriever employeeMapper = orm.retrieverNamed("Employee");

        Project aProject = (Project) projectMapper.findAny();
        Employee employee = (Employee) employeeMapper.findAny();

        aProject.addEmployee(employee);
        aProject.write();

        Project result = (Project) projectMapper.findAny();

        List aSeq = (List) result.employees();
        outputStream.println(result.info());
        int size = aSeq.size();
        for (int i = 0; i < size; i++) {
            outputStream.println("Project member " + (i+1) + " is " + aSeq.atIndex(i));
        };
    }
}
