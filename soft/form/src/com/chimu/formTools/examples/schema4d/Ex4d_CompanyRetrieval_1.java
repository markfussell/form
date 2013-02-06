/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4d/Ex4d_CompanyRetrieval_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4d;

import java.sql.Connection;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.formTools.test.*;

public class Ex4d_CompanyRetrieval_1 extends ExampleAbsC {
    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper companyMapper = orm.mapperNamed("Company");

        List companies = (List) companyMapper.selectAll();
        outputStream.println(companies);
        outputStream.println();

        int size = companies.size();
        for (int i = 0; i<size; i++) {
            Company company = (Company) companies.atIndex(i);
            outputStream.println(company.info());
        };
    }
}