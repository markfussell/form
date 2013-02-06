/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3/Ex3_ProjectMemberRetrieval_1.java
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

/*
==  This query asks for a project and all its members
*/

public class Ex3_ProjectMemberRetrieval_1 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper projectMapper = orm.mapperNamed("Project");
        ObjectMapper employeeMapper = orm.mapperNamed("Employee");

        Project project = (Project) projectMapper.findAny();

        QueryDescription query = orm.newQueryDescription();
        QueryVar employeeVar = query.newExtentVar(employeeMapper);

        query.and_equalsConstant(employeeVar.slotNamed("projects"), project);
        Employee employee = (Employee) query.findAny();

    //==========================================================
    //(P)==================== Running ==========================
    //==========================================================

        outputStream.println("This is project " + project.info());
        outputStream.println(project.employees());

        Collection projects = employee.projects();
        outputStream.println("Employee " + employee.name() + " is working on " + projects.size() + "projects");

    }
}