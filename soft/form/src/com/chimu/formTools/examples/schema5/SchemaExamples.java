/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5/SchemaExamples.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5;

import com.chimu.formTools.test.FormTestSchemaAbsC;

import com.chimu.kernel.collections.*;

public class SchemaExamples extends FormTestSchemaAbsC {
    public Array /*of String*/ testNames() {
        List testNames = CollectionsPack.newList();
        String prefix = "com.chimu.formTools.examples.schema5.";
        testNames.add(prefix+"Ex5_CommissionedRetrieval_1");
        testNames.add(prefix+"Ex5_EmployeeInsert_2");
        testNames.add(prefix+"Ex5_EmployeeRetrieval_2");
        testNames.add(prefix+"Ex5_SalariedRetrieval_1");
        return testNames;
    }
}
