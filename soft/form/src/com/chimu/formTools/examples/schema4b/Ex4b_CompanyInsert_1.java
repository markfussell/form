/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4b/Ex4b_CompanyInsert_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4b;
import java.sql.Connection;
import java.math.BigDecimal;

import com.chimu.kernel.collections.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.formTools.test.*;


public class Ex4b_CompanyInsert_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper projectMapper = orm.mapperNamed("Project");
        ObjectMapper companyMapper = orm.mapperNamed("Company");

    //==========================================================
    //(P)================== Running ============================
    //==========================================================

        Company company = (Company) companyMapper.findAny();
        Money revenue = (Money) new USDollarC(new BigDecimal("1000.00"));
        Company newCompany = new CompanyC("Scarty Kitty, LTD", revenue);
        newCompany.write();

        outputStream.println(company.info());
        outputStream.println("New Company " + newCompany.info());

    }
}