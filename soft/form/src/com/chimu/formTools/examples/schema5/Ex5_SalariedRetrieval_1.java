/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5/Ex5_SalariedRetrieval_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5;

import java.sql.Connection;
import java.util.Enumeration;

import com.chimu.kernel.collections.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.formTools.test.*;

/**
    This query retrieves one instance of salaried employee
**/

public class Ex5_SalariedRetrieval_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

    //==========================================================
    //(P)================ Configuration ========================
    //==========================================================

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper mapper = orm.mapperNamed("SalariedEmployee");


    //==========================================================
    //(P)================== Running ============================
    //==========================================================

        Employee employee = (Employee) mapper.findAny();
        outputStream.println(employee);
    }
}