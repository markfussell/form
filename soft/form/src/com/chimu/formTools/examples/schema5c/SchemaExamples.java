/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/SchemaExamples.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5c;

import com.chimu.formTools.test.FormTestSchemaAbsC;

import com.chimu.kernel.collections.*;

public class SchemaExamples extends FormTestSchemaAbsC {
    public Array /*of String*/ testNames() {
        List testNames = CollectionsPack.newList();
        String prefix = "com.chimu.formTools.examples.schema5c.";
        testNames.add(prefix+"Ex5c_CommissionedRetrieval_1");
        testNames.add(prefix+"Ex5c_ReadingDescription");
        testNames.add(prefix+"Ex5c_WritingDescription");
        testNames.add(prefix+"Ex5c_WritingDescription_2");
        testNames.add(prefix+"Ex5c_WritingDescription_3");
        return testNames;
    }
}
