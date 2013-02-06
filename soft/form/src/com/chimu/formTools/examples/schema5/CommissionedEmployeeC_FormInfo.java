/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5/CommissionedEmployeeC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5;

import com.chimu.form.database.*;
import com.chimu.form.mapping.*;


/**
CommissionedEmployeeC_FormInfo creates the ObjectMapper for CommissionedEmployeeC.

@see com.chimu.form.description.FormInfo
**/
public class CommissionedEmployeeC_FormInfo extends EmployeeAbsC_FormInfo {

    static private Class identityColumnType = Integer.class;

    public void createMappers() {
        if (myMapper != null) throw new RuntimeException("Mapper creation called multiple times");

        DistinguishingObjectMapper myOrmMapper =
                (DistinguishingObjectMapper) orm.mapperNamed_orNull(mapperName());

        if (myOrmMapper != null) throw new RuntimeException("Multiple mappers with the same name");

        myTable  = dbConnection.table("employee");
        myTable.newBasicColumnNamed_type("id",identityColumnType);

        myMapper = orm.newDistinguishingObjectMapperNamed_table("CommissionedEmployee",myTable);
    }

    public void configureMappers() {
        super.configureMappers();

        final DistinguishingObjectMapper mapper = (DistinguishingObjectMapper) myMapper;

        mapper.newDistinguishingSlot("distinguisher", "distinguisher", "c");

        mapper.newDirectSlot_column_type("base", "base", Integer.class);
        mapper.newDirectSlot_column_type("percentage", "percentage", Float.class);
    }

    public Class myC() {
        return CommissionedEmployeeC.class;
    }

    //==========================================================
    //==========================================================
    //==========================================================

    protected void setupCreationFunction() {
        CreationFunction creationFunction = getCreationFunction();
        if (creationFunction == null) throw new RuntimeException("Could not find creation function "+creationFunctionName()+" for "+myC());
        myMapper.setupCreationFunction(creationFunction);
    }
}
