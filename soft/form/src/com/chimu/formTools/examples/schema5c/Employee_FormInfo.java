/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5c/Employee_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5c;

import com.chimu.form.mapping.*;
import com.chimu.form.database.*;
import com.chimu.form.description.*;


/**
Employee_FormInfo creates the retriever for all employees

@see com.chimu.form.description.FormInfo
**/
public class Employee_FormInfo extends SimpleFormInfoAbsC {

    protected CollectiveObjectRetriever myRetriever;

    public void createMappers() {
        if (myRetriever != null) throw new RuntimeException("Mapper creation called multiple times");

        CollectiveObjectRetriever myOrmRetriever =
                (CollectiveObjectRetriever) orm.retrieverNamed_orNull("Employee");

        if (myOrmRetriever != null) throw new RuntimeException("Multiple retrievers with the same name");

        myRetriever = orm.newCollectiveObjectRetrieverNamed("Employee");
    }

    public void configureMappers() {
        myRetriever.addDistinguishingMapper((DistinguishingObjectMapper) orm.mapperNamed("SalariedEmployee"));
        myRetriever.addDistinguishingMapper((DistinguishingObjectMapper) orm.mapperNamed("CommissionedEmployee"));

    }

    public void configureCompleted() {

    }

    public Class myC() {
        return Employee.class;
    }

}

/*

    static CollectiveObjectRetriever employeeRetriever = null;

    public static CollectiveObjectRetriever createTheRetriever(Orm orm, DatabaseConnection dbConnection) {

        if (employeeRetriever != null) return employeeRetriever;

        employeeRetriever  = orm.newCollectiveObjectRetrieverNamed("Employee");

        DistinguishingObjectMapper salariedMapper     = SalariedEmployeeC.createTheMapper(orm, dbConnection);
        DistinguishingObjectMapper commissionedMapper = CommissionedEmployeeC.createTheMapper(orm, dbConnection);

        AssociationConnector  connector      = AssociationsLib.createTheProjectToMemberConnector(orm, dbConnection);

        configEmployeeRetriever(employeeRetriever, commissionedMapper, salariedMapper);
        return employeeRetriever;
    }

    public static void configEmployeeRetriever(
                CollectiveObjectRetriever retriever,
                DistinguishingObjectMapper commissionedMapper,
                DistinguishingObjectMapper salariedMapper) {
        retriever.addDistinguishingMapper(commissionedMapper);
        retriever.addDistinguishingMapper(salariedMapper);
    }

*/
