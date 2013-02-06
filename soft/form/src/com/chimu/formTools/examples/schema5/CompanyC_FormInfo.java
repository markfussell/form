/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5/CompanyC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5;

import java.math.BigDecimal;

import com.chimu.form.database.*;
import com.chimu.kernel.collections.*;


/**
CompanyC_FormInfo creates the ObjectMapper for CompanyC.

@see com.chimu.form.description.FormInfo
**/
public class CompanyC_FormInfo extends DomainObjectAbsC_FormInfo {

    public void configureMappers() {
        super.configureMappers();

        Table table = dbConnection.table("Company");
        table.newBasicColumnNamed_type("revenue", BigDecimal.class);
        table.newCompoundColumnNamed("revenuePair", "revenue_cur", "revenue");

        myMapper.newDirectSlot_type("name", String.class);
        myMapper.newTransformSlot_column_decoder_encoder("revenue", "revenuePair", MoneyC.decodeFunctor(), MoneyC.encodeFunctor());
        myMapper.newTransformSlot_column_decoder_encoder("city", "city", CityC.columnToCityFunctor(), CityC.cityToColumnFunctor());
/*
        myMapper.newReverseSlot_partner_partnerSlot("employees",
            orm.retrieverNamed("Employee"),
            "employer");
*/
   }

   public Class myC() {
       return CompanyC.class;
    }

}
