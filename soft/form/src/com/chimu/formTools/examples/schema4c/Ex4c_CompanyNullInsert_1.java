/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4c/Ex4c_CompanyNullInsert_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4c;
import java.sql.Connection;
import java.math.BigDecimal;

import java.util.Date;

import com.chimu.kernel.collections.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.formTools.test.*;


/**
 == This query tests to see if Company fields can be null
**/

public class Ex4c_CompanyNullInsert_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper companyMapper = orm.mapperNamed("Company");

    //==========================================================
    //(P)================== Running ============================
    //==========================================================

        Company company = new CompanyC("Acme Bread, Inc", null);
        company.write();
        outputStream.println(company.info());

    }
}