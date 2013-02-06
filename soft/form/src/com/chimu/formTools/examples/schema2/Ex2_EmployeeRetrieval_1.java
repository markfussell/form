/*======================================================================
**
**  File: com/chimu/formTools/examples/schema2/Ex2_EmployeeRetrieval_1.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema2;
import com.chimu.formTools.test.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import java.sql.Connection;

public class Ex2_EmployeeRetrieval_1 extends ExampleAbsC {

    public void run (Connection jdbcConnection) {

        createAndConfigureOrm(jdbcConnection);
        ObjectMapper employeeMapper = orm.mapperNamed("Employee");

        Employee employee = (Employee) employeeMapper.findAny();
        outputStream.println(employee.info() + " is employed by " + employee.employer().name());
    }
}