/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3b/SchemaExamples.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3b;

import com.chimu.formTools.test.FormTestSchemaAbsC;

import com.chimu.kernel.collections.*;

public class SchemaExamples extends FormTestSchemaAbsC {
    public Array /*of String*/ testNames() {
        List testNames = CollectionsPack.newList();
        String prefix = "com.chimu.formTools.examples.schema3b.";
        testNames.add(prefix+"Ex3b_EmployeeInsert_1");
        testNames.add(prefix+"Ex3b_EmployeeInsert_2");
        testNames.add(prefix+"Ex3b_ProjectMemberUpdate_1");
        testNames.add(prefix+"Ex3b_ProjectMemberUpdate_2");
        testNames.add(prefix+"Ex3b_ProjectMemberUpdate_3");
        return testNames;
    }
}
