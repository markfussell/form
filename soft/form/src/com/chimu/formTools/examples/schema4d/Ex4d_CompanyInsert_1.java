/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4d/Ex4d_CompanyInsert_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4d;
import java.sql.Connection;
import java.math.BigDecimal;

import com.chimu.kernel.collections.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.formTools.test.*;


public class Ex4d_CompanyInsert_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {
        createAndConfigureOrm(jdbcConnection);
        ObjectMapper companyMapper = orm.mapperNamed("Company");


    //==========================================================
    //(P)================== Running ============================
    //==========================================================

        Company company = (Company) companyMapper.findAny();
        Money revenue = new MoneyC("DEM", new BigDecimal("10560.067"));
        City city = new CityC("FelineVille", "CA");

        Company newCompany = new CompanyC("Scarty Kitty, LTD", revenue, city);
        outputStream.println(company.info());

        newCompany.write();
        outputStream.println("Saved to the Database a New Company " + newCompany.info());

    }
}