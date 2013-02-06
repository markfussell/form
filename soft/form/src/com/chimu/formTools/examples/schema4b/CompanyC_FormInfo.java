/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4b/CompanyC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4b;
import java.math.BigDecimal;

/**
CompanyC_FormInfo creates the ObjectMapper for CompanyC.

@see com.chimu.form.description.FormInfo
**/
public class CompanyC_FormInfo extends DomainObjectAbsC_FormInfo {

    public void configureMappers() {
       super.configureMappers();


        myMapper.newDirectSlot_type("name", String.class);
        myMapper.newTransformSlot_column_type_decoder_encoder("revenue", "revenue", BigDecimal.class, USDollarC.decodeFunctor(), USDollarC.encodeFunctor());

        myMapper.newReverseSlot_partner_partnerSlot("employees",
            orm.retrieverNamed("Employee"),
            "employer");
   }
   public Class myC() {
       return CompanyC.class;
    }
}
