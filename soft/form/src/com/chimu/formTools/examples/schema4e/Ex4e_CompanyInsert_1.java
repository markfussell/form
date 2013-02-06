/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4e/Ex4e_CompanyInsert_1.java
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



public class Ex4e_CompanyInsert_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {
        createAndConfigureOrm(jdbcConnection);
        ObjectMapper companyMapper = orm.mapperNamed("Company");

    //==========================================================
    //(P)================== Running ============================
    //==========================================================
        setupCurrencies();

        Company company = (Company) companyMapper.findAny();
        Currency cur = new CurrencyC("DEM");
        Money revenue = new MoneyC(cur, new BigDecimal("40.01"));
        City city = new CityC("FelineVille", "CA");

        Company newCompany = new CompanyC("Scarty Kitty, LTD", revenue, city);
        outputStream.println(company.info());

        newCompany.write();
        outputStream.println("Saved to the Database a New Company " + newCompany.info());

    }
}