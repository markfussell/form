/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/EmployeeAbsC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5c;

import java.util.Date;

/**
EmployeeAbsC_FormInfo creates the ObjectMapper for EmployeeAbsC.

@see com.chimu.form.description.FormInfo
**/
public class EmployeeAbsC_FormInfo extends PersonAbsC_FormInfo {

    public void configureMappers() {
       super.configureMappers();

       myMapper.newForwardSlot_column_partner("employer",
            "employer_ID",
            orm.retrieverNamed("Company"));
       myMapper.newDirectSlot_column_type("hiredDate", "hired_date", Date.class);
       myMapper.newExternalSlot_connector_mySlot("projects",
                orm.associationConnectorNamed("projectAndEmployee"),
                "employee");
    }

   public Class myC() {
       return EmployeeAbsC.class;
    }

}

