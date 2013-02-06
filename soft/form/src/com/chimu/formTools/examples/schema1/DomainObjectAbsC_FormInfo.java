/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/DomainObjectAbsC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;

import com.chimu.form.client.*;

public class DomainObjectAbsC_FormInfo extends DomainObject_1_AbsC_FormInfo {

        /**
         * We can use a very simple pattern for generating the appropriate mapperName
         * from the class name.
         */
    protected String mapperName() {
        String className = myC().getName();
        int dotIndex = className.lastIndexOf(".");
        if (dotIndex > 0) {
            className = className.substring(dotIndex+1);
        };
        if (className.endsWith("Class")) {
            return className.substring(0,className.length()-5);
        };
        return className;
    }

        /**
         * Specifies the OID column in the table for this mapper.
         */
    protected String oidColumnName() {
        return "id";
    }

    public void configureMappers() {
        super.configureMappers();
    }

}

