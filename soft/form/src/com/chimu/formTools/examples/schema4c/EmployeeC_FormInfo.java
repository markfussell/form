/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4c/EmployeeC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4c;
import java.util.Date;

/**
EmployeeC_FormInfo creates the ObjectMapper for EmployeeC.

@see com.chimu.form.description.FormInfo
**/
public class EmployeeC_FormInfo extends PersonAbsC_FormInfo {

    public void configureMappers() {
        super.configureMappers();

        myMapper.newDirectSlot_column_type("hiredDate","hired_date",Date.class);

        myMapper.newForwardSlot_column_partner("employer",
                "employer_ID",
                orm.retrieverNamed("Company"));

        myMapper.newExternalSlot_connector_mySlot("projects",
                orm.associationConnectorNamed("projectAndEmployee"),
                "employee");
    }

    public Class myC() {
        return EmployeeC.class;
    }
}



