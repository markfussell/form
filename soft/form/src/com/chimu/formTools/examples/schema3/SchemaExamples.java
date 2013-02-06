/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3/SchemaExamples.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3;

import com.chimu.formTools.test.FormTestSchemaAbsC;

import com.chimu.kernel.collections.*;

public class SchemaExamples extends FormTestSchemaAbsC {
    public Array /*of String*/ testNames() {
        List testNames = CollectionsPack.newList();
        String prefix = "com.chimu.formTools.examples.schema3.";
        testNames.add(prefix+"Ex3_EmployeeInsert_1");
        testNames.add(prefix+"Ex3_EmployeeProject_1");
        testNames.add(prefix+"Ex3_NewProjectMemberInsert_1");
        testNames.add(prefix+"Ex3_NewProjectMemberInsert_2");
        testNames.add(prefix+"Ex3_ProjectMemberRetrieval_1");
        testNames.add(prefix+"Ex3_ProjectMemberUpdate_1");
        testNames.add(prefix+"Ex3_ProjectMemberUpdate_2");
        testNames.add(prefix+"Ex3_ProjectMemberUpdate_3");
        testNames.add(prefix+"Ex3_Query_1");
        return testNames;
    }
}
