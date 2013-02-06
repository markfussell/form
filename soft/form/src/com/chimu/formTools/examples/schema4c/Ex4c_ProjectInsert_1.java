/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4c/Ex4c_ProjectInsert_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4c;
import java.sql.Connection;
import java.util.Date;

import com.chimu.kernel.collections.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.formTools.test.*;


public class Ex4c_ProjectInsert_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper companyMapper = orm.mapperNamed("Company");
        ObjectMapper projectMapper = orm.mapperNamed("Project");

    //==========================================================
    //(P)================== Running ============================
    //==========================================================
        Company aCompany = (Company) companyMapper.findAny();

        Project project = new ProjectC();
        Project newProject = (Project) project.copy();
        Double profitMargin = new Double("0.2345");
        newProject.setName("Testing");
        newProject.setIsBudgeted(true);
        newProject.setProfitMargin(profitMargin);
        newProject.setCompany(aCompany);
        newProject.setStartDate(new Date());

        newProject.write();
        outputStream.println("inserting a new project: " + newProject);

        List projects = (List) projectMapper.selectAll();
        for (int i = 0; i < projects.size(); i++) {
            project = (Project) projects.atIndex(i);
            outputStream.println(project.info());
        }

    }
}