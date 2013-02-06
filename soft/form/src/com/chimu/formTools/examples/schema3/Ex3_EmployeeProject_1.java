/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3/Ex3_EmployeeProject_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3;
import java.sql.Connection;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import java.util.Enumeration;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.formTools.test.*;

public class Ex3_EmployeeProject_1 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper projectMapper = orm.mapperNamed("Project");
        ObjectMapper employeeMapper = orm.mapperNamed("Employee");

        Project project = (Project) projectMapper.findAny();
        outputStream.println("Looking for employees on " + project);

        QueryDescription query = orm.newQueryDescription();
        QueryVar employeeVar = query.newExtentVar(employeeMapper);

        query.and_equalsConstant(employeeVar.slotNamed("projects"), project);

    //==========================================================
    //(P)==================== Running ==========================
    //==========================================================

        Collection employees = query.selectAll();
        for (Enumeration enum = employees.elements(); enum.hasMoreElements();) {
            Employee employee = (Employee) enum.nextElement();
            outputStream.println(employee + " is working on " + employee.projects());
        };

    }
}