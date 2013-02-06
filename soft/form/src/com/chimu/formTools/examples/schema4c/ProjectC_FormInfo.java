/*======================================================================
**
**  File: com/chimu/formTools/examples/schema4c/ProjectC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema4c;

import java.util.Date;
import com.chimu.kernel.utilities.TranslationLib;
import com.chimu.kernel.functors.*;


/**
ProjectC_FormInfo creates the ObjectMapper for ProjectC.

@see com.chimu.form.description.FormInfo
**/
public class ProjectC_FormInfo extends DomainObjectAbsC_FormInfo {

    public void configureMappers() {
       super.configureMappers();

        myMapper.newDirectSlot_type("name", String.class);
        myMapper.newDirectSlot_column_type("startDate", "start_date", Date.class);

        myMapper.newTransformSlot_column_type_decoder_encoder("profitMargin",
            "profit_margin", Double.class,
            TranslationLib.numberToDoubleFunction(), TranslationLib.numberToBigDecimalFunction());

        buildBooleanSlotInto_named_column( myMapper, "isBudgeted", "budgeted");

        myMapper.newForwardSlot_column_partner("company",
            "company_ID",
            orm.retrieverNamed("Company"));

        myMapper.newExternalSlot_connector_mySlot("employees",
                orm.associationConnectorNamed("projectAndEmployee"),
                "project");

    }

    public Class myC() {
        return ProjectC.class;
    }

}
