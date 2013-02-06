/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4d/SchemaExamples.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4d;

import com.chimu.formTools.test.FormTestSchemaAbsC;

import com.chimu.kernel.collections.*;

public class SchemaExamples extends FormTestSchemaAbsC {
    public Array /*of String*/ testNames() {
        List testNames = CollectionsPack.newList();
        String prefix = "com.chimu.formTools.examples.schema4d.";
        testNames.add(prefix+"Ex4d_CompanyInsert_1");
        testNames.add(prefix+"Ex4d_CompanyRetrieval_1");
        testNames.add(prefix+"Ex4d_ProjectInsert_1");
        return testNames;
    }
}
