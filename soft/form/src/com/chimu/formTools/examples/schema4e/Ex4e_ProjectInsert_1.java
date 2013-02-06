/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4e/Ex4e_ProjectInsert_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4e;
import java.sql.Connection;
import java.math.BigDecimal;

import com.chimu.kernel.collections.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.formTools.test.*;


public class Ex4e_ProjectInsert_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper projectMapper = orm.mapperNamed("Project");

        setupCurrencies();

    //==========================================================
    //(P)================== Running ============================
    //==========================================================

        Project project = (Project) projectMapper.findAny();
        Double profitMargin = new Double("0.2345");
        outputStream.println(project.info());

        Project newProject = (Project) project.copy();

        newProject.setName("Testing");
        newProject.setIsBudgeted(true);
        newProject.setProfitMargin(profitMargin);

        newProject.write();
        outputStream.println("inserting a new project: " + newProject.info());

        Project newProject2 = (Project) project.copy();

        newProject2.setName("Testing2");
        newProject2.setIsBudgeted(false);
        newProject2.setProfitMargin(profitMargin);

        newProject2.write();

        List projects = (List) projectMapper.selectAll();
        for (int i = 0; i < projects.size(); i++) {
            project = (Project) projects.atIndex(i);
            outputStream.println(project.info());
        }

    }
}