/*======================================================================
**
**  File: com/chimu/formTools/examples/schema5b/AssociationsFormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema5b;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.description.*;

/**
AssociationsFormInfo has all the assocations connectors in one place
**/

public class AssociationsFormInfo implements FormInfo, MapperBuilder {

    //==========================================================
    //======================== FormInfo ========================
    //==========================================================

        /**
         * I function as my own mapperBuilder
         */
    public MapperBuilder mapperBuilder() {
        return this;
    }

        /**
         * This method can only be used if the FormInfo subclass
         * explicitly knows its DomainC object
         */
    public void initOrm_db(Orm orm, DatabaseConnection dbConnection) {
        this.orm = orm;
        this.dbConnection = dbConnection;
    }

    public void initOrm_db_class(Orm orm, DatabaseConnection dbConnection, Class myC) {
        this.orm = orm;
        this.dbConnection = dbConnection;
    }

    //==========================================================
    //====================== MapperBuilder =====================
    //==========================================================

        /**
         * Create and add to the Orm all mappers that will be used by this builder.
         * During this stage you can not assume any other mappers exist.
         */
    public void createMappers() {
        AssociationConnector connector = orm.associationConnectorNamed("projectAndEmployee");
        if (connector != null) throw new RuntimeException("Association creation called multiple times");

        orm.newAssociationConnectorNamed_table("projectAndEmployee",dbConnection.table("ProjectMembers"));
    }

        /**
         * Configure the created mappers.
         */
    public void configureMappers() {
        AssociationConnector connector = orm.associationConnectorNamed("projectAndEmployee");

        ObjectRetriever employeeRetriever = orm.retrieverNamed("Employee");
        connector.newConnectorSlot_column_retriever_retrieverSlot(
                "employee", "employee_ID", employeeRetriever, "projects");

        ObjectRetriever projectRetriever = orm.retrieverNamed("Project");
        connector.newConnectorSlot_column_retriever_retrieverSlot(
                "project", "project_ID", projectRetriever, "employees");
    }

        /**
         * Creation and configuration have been completed for all Mappers,
         * do any final activities and notifications required for the mapper.
         */
    public void configureCompleted() {
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    protected Orm                orm;
    protected DatabaseConnection dbConnection;

}

