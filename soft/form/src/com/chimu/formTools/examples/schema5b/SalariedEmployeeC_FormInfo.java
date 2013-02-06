/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5b/SalariedEmployeeC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5b;

import com.chimu.form.database.*;
import com.chimu.form.mapping.*;


/**
SalariedEmployeeC_FormInfo creates the ObjectMapper for SalariedEmployeeC.

@see com.chimu.form.description.FormInfo
**/
public class SalariedEmployeeC_FormInfo extends EmployeeAbsC_FormInfo {

    public void createMappers() {
        if (myMapper != null) throw new RuntimeException("Mapper creation called multiple times");

        DistinguishingObjectMapper myOrmMapper =
                (DistinguishingObjectMapper) orm.mapperNamed_orNull(mapperName());
        if (myOrmMapper != null) throw new RuntimeException("Multiple mappers with the same name");

        myTable  = dbConnection.table("employee");
        myMapper = orm.newDistinguishingObjectMapperNamed_table("SalariedEmployee",myTable);
    }

    public void configureMappers() {
        super.configureMappers();
        final DistinguishingObjectMapper mapper = (DistinguishingObjectMapper) myMapper;

        mapper.newDistinguishingSlot("distinguisher", "distinguisher", "s");
        mapper.newDirectSlot_column_type("salary", "annual_salary", Integer.class);

    }

    public Class myC() {
        return SalariedEmployeeC.class;
    }

}
