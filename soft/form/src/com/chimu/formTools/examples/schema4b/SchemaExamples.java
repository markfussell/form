/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4b/SchemaExamples.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4b;

import com.chimu.formTools.test.FormTestSchemaAbsC;

import com.chimu.kernel.collections.*;

public class SchemaExamples extends FormTestSchemaAbsC {
    public Array /*of String*/ testNames() {
        List testNames = CollectionsPack.newList();
        String prefix = "com.chimu.formTools.examples.schema4b.";
        testNames.add(prefix+"Ex4b_CompanyInsert_1");
        testNames.add(prefix+"Ex4b_CompanyNullInsert_1");
        testNames.add(prefix+"Ex4b_ProjectInsert_1");
        testNames.add(prefix+"Ex4b_ProjectNullInsert_1");
        testNames.add(prefix+"Ex4b_ProjectQuery");
        testNames.add(prefix+"Ex4b_ProjectRetrieval_1");
        return testNames;
    }
}
