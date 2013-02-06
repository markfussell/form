/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4e/CurrencyC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4e;

import com.chimu.form.database.*;
import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;

public class CurrencyC_FormInfo extends DomainObjectAbsC_FormInfo {

    public void configureMappers() {
        super.configureMappers();

        Table table = dbConnection.table("Currency");
        myMapper.newDirectSlot_column_type("name","name",String.class);

   }

   public Class myC() {
       return CurrencyC.class;
    }

}

