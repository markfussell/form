/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4b/Ex4b_ProjectQuery.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4b;
import java.sql.Connection;
import java.util.Enumeration;

import com.chimu.kernel.collections.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.oql.*;

import com.chimu.formTools.example.*;
import com.chimu.formTools.test.*;

/**
    This query retrieves one instance of salaried employee
**/

public class Ex4b_ProjectQuery extends ExampleAbsC {
    public void run (Connection jdbcConnection) {


    //==========================================================
    //(P)================ Configuration ========================
    //==========================================================

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper projectMapper = orm.mapperNamed("Project");

    //==========================================================
    //(P)=================== Running ===========================
    //==========================================================

    Project aProject = (Project) projectMapper.findAny();
    outputStream.println(aProject.info());

    Project newProject = (Project) aProject.copy();
    newProject.write();

    outputStream.println("New Project: " + newProject.info());



    }
}