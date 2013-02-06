/*======================================================================
**
**  File: chimu/form/Orm.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.form.database.*;
import com.chimu.form.description.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.oql.*;

import java.io.PrintWriter;

/**
An Orm (Object-Relational Mapper) is a root object that holds
onto all the information needed for mapping objects in a particular
application to the possibly-multiple databases involved.  ObjectMappers
are created within the context of an Orm and are uniquely named within
the Orms description.  Queries are also performed on the contents of
an Orm and so are created from the Orm methods listed below.
**/

public interface Orm {

    //**********************************************************
    //(P)******************** Setup ****************************
    //**********************************************************


    public void setupTraceStream (PrintWriter traceStream);
    public void setupTraceStream_traceLevel(PrintWriter traceStream, int traceLevel);

    public void setupDefaultDatabaseConnection(DatabaseConnection defaultDatabaseConnection);


    //**************************************
    //(P)************ FormInfo *************
    //**************************************

    public void addInfo(FormInfo formInfo);

        /**
         *@param formInfoC is a Class that creates FormInfo from newInstance().
         *The formInfo is created using the current Orm, and the defaultDatabaseConnection()
         *for this Orm.  The formInfo must also know its class.
         */
    public void addInfoClass(Class formInfoC);
    public void addInfoClass_withDb(Class formInfoC, DatabaseConnection dbConnection);

    public DatabaseConnection defaultDatabaseConnection();

    //**********************************************************
    //(P)******************** Asking ***************************
    //**********************************************************

    public ObjectMapper          mapperNamed(String name);
    public ObjectRetriever       retrieverNamed(String name);

    //**************************************
    //(P)*********** Subsystem *************
    //**************************************

    public ObjectMapper          mapperNamed_orNull(String name);
    public ObjectRetriever       retrieverNamed_orNull(String name);
    public AssociationConnector  associationConnectorNamed(String name);

    //**********************************************************
    //(P)***************** Factory Methods ********************
    //**********************************************************

    //**************************************
    //(P)************ Queries **************
    //**************************************

    public QueryDescription newQueryDescription();
    public OqlQuery newOqlQuery(String oqlString);

//    public QueryDescription newQueryDescription();


    //**************************************
    //(P)********** ObjectMapper ***********
    //**************************************

        /**
         * All Mappers need a Table to work with and this is
         * the way they interact with the database
         *
         * <P>Mappers must also be "setup" using the setup methods and the
         * slot creation methods.  After setup has been completed,
         * call "doneSetup()" to prepare the mapper for execution.
         *
         * <P>The methods to set a mapper's tracing are now part of setup
         *
         *@see ObjectMapper
         *@see ObjectMapper#doneSetup
         */
    public ObjectMapper newObjectMapper(Table table);
    public ObjectMapper newObjectMapperNamed_table(String name, Table table);

    //**************************************
    //(P)***** DistinguishingMapper ********
    //**************************************

    public DistinguishingObjectMapper newDistinguishingObjectMapper(Table table);
    public DistinguishingObjectMapper newDistinguishingObjectMapperNamed_table(String name, Table table);

    //**************************************
    //(P)**** CollectiveObjectRetriever ****
    //**************************************

    public CollectiveObjectRetriever newCollectiveObjectRetriever();
    public CollectiveObjectRetriever newCollectiveObjectRetrieverNamed(String name);

    //**************************************
    //(P)******* AssociationMapper *********
    //**************************************
        /**@deprecated AssociationMapper was renamed to AssociationConnector*/
    public AssociationMapper newAssociationMapper(Table table);

    //**************************************
    //(P)****** AssociationConnector *******
    //**************************************

    public AssociationConnector newAssociationConnector(Table table);
    public AssociationConnector newAssociationConnectorNamed_table(String name, Table table);

    //**********************************************************
    //(P)******************** Validation ***********************
    //**********************************************************

        /**
         * Notify the Orm that all setup has been complete and
         * it should now be prepared to run
         */
    public void doneSetup();


        /**
         * Explicitly removes all objects from all IdentityCaches
         * within this Orm.
         *
         * <P>This must be done with care because if any domain
         * objects are still in use, FORM will not know about
         * them (unless you subsequently add them to the cache
         * again).  If FORM does not know about a domain object
         * it will rebuild the object when it gets one from
         * the database.
         */
    public void clearIdentityCaches();

        /**
         * Return a map of ObjectMappers to their respective cache counts
         */
    public Map cacheCounts();

}
