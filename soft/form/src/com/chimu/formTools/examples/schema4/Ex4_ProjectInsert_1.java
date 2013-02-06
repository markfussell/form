/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4/Ex4_ProjectInsert_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4;
import java.sql.Connection;
import java.math.BigDecimal;

import com.chimu.kernel.collections.*;


import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.formTools.test.*;


public class Ex4_ProjectInsert_1 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper projectMapper = orm.mapperNamed("Project");
        ObjectMapper employeeMapper = orm.mapperNamed("Employee");

        Project project = (Project) projectMapper.findAny();
        Employee employee = (Employee) employeeMapper.findAny();

        outputStream.println(project.info());
        Project newProject = (Project) project.copy();

        newProject.setName("Testing");
        newProject.setIsBudgeted(true);

        List aCollection = CollectionsPack.newList();
        aCollection.add(employee);
        newProject.setEmployees(aCollection);

        newProject.write();
        outputStream.println("A new project named " + newProject.name() + " has been added.");
        outputStream.println(newProject.info());

    }
}