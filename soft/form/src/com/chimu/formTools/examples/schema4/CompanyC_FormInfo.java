/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4/CompanyC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4;
import java.math.BigDecimal;

/**
CompanyC_FormInfo creates the ObjectMapper for CompanyC.

@see com.chimu.form.description.FormInfo
**/
public class CompanyC_FormInfo extends DomainObjectAbsC_FormInfo {

    public void configureMappers() {
       super.configureMappers();

        myMapper.newDirectSlot_type("name",String.class);
        myMapper.newDirectSlot_type("revenue",BigDecimal.class);
        myMapper.newReverseSlot_partner_partnerSlot("employees",
                orm.retrieverNamed("Employee"),"employer");
    }

    public Class myC() {
       return CompanyC.class;
    }
}

