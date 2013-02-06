/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5b/Ex5b_CommissionedRetrieval_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5b;
import java.sql.Connection;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.formTools.test.*;

/**
    This query retrieves an instance of commissioned employee.
**/

public class Ex5b_CommissionedRetrieval_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

    //==========================================================
    //(P)================ Configuration ======================
    //==========================================================

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper mapper = orm.mapperNamed("CommissionedEmployee");


    //==========================================================
    //(P)================== Running ============================
    //==========================================================

        Employee employee = (Employee) mapper.findAny();
        outputStream.println(employee.info());
    }
}