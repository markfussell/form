/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4/Ex4_ProjectNullInsert_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4;
import java.sql.Connection;

import java.util.Date;

import com.chimu.kernel.collections.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.formTools.test.*;


public class Ex4_ProjectNullInsert_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper projectMapper = orm.mapperNamed("Project");

    //==========================================================
    //(P)================== Running ============================
    //==========================================================

        Project project = new ProjectC("Hoover Dam", null, new Date(), false, null);
        project.write();
        outputStream.println(project.info());
    }
}