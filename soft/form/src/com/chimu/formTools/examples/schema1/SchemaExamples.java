/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/SchemaExamples.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;

import com.chimu.formTools.test.FormTestSchemaAbsC;

import com.chimu.kernel.collections.*;

public class SchemaExamples extends FormTestSchemaAbsC {
    public Array /*of String*/ testNames() {
        List testNames = CollectionsPack.newList();
        String prefix = "com.chimu.formTools.examples.schema1.";
        testNames.add(prefix+"Ex1_OqlQuery_1");
        testNames.add(prefix+"Ex1_OqlQuery_2");
        testNames.add(prefix+"Ex1_OqlQuery_3");
        testNames.add(prefix+"Ex1_OqlQuery_Temp1");
        testNames.add(prefix+"Ex1_PersonRetrieval_1a");
        testNames.add(prefix+"Ex1_PersonRetrieval_1b");
        testNames.add(prefix+"Ex1_PersonRetrieval_1c");
        testNames.add(prefix+"Ex1_PersonRetrieval_2");
        testNames.add(prefix+"Ex1_PersonRetrieval_3");
        testNames.add(prefix+"Ex1_PersonRetrieval_4");
        testNames.add(prefix+"Ex1_PersonRetrieval_4b");
        testNames.add(prefix+"Ex1_Query_1");
        testNames.add(prefix+"Ex1_Query_2");
        testNames.add(prefix+"Ex1_Query_3");
        testNames.add(prefix+"Ex1_Query_4");
        return testNames;
    }
}
