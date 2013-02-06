/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1b/SchemaExamples.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1b;

import com.chimu.formTools.test.FormTestSchemaAbsC;

import com.chimu.kernel.collections.*;

public class SchemaExamples extends FormTestSchemaAbsC {
    public Array /*of String*/ testNames() {
        List testNames = CollectionsPack.newList();
        String prefix = "com.chimu.formTools.examples.schema1b.";
        testNames.add(prefix+"Ex1b_Delete_1");
        testNames.add(prefix+"Ex1b_Insert_1");
        testNames.add(prefix+"Ex1b_Update_1");
        testNames.add(prefix+"Ex1b_Write_1");
        return testNames;
    }
}
