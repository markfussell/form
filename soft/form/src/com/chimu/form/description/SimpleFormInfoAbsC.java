/*======================================================================
**
**  File: chimu/form/description/SimpleFormInfoAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.mapping.ConfigurationException;

/**
SimpleFormInfoAbsCFormInfoAbsC provides an abstract implementation
of the FormInfo protocol combined with the MapperBuilder protocol.  This class
serves as both a FormInfo object and a MapperBuilder object, so it
return itselfs when asked for its mapperBuilder.

<P>Subclasses must implement the MapperBuilder protocol: #createMappers(),
#configureMappers(), #configureCompleted.  These methods are called in
subsequent stages of mapper building: first the #createMappers of all
MapperBuilder objects are called, then the #configureMappers of all the
MapperBuilder objects, and then #configureCompleted.  During each stage
the order of calling MapperBuilders is indeterminate.

<P>Subclasses have two main instance variables set at creation time:
'orm' and 'dbConnection'.  They can also have 'myC' set at creation time
which allows parameterized class-dependent behavior instead of pure
subclassing behavior.  For subclassing behavior, override 'myC()' to
return the appropriate class object.


**/

public abstract class SimpleFormInfoAbsC implements FormInfo, MapperBuilder {

    //**********************************************************
    //************************ FormInfo ************************
    //**********************************************************

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
        this.myC = myC;
    }

    //**********************************************************
    //********************** MapperBuilder *********************
    //**********************************************************

        /**
         * Create and add to the Orm all mappers used by this builder.
         * During this stage you can not assume any other mappers exist.
         */
    public abstract void createMappers();

        /**
         * Configure the created mappers.
         */
    public abstract void configureMappers();

        /**
         * Creation and configuration have been completed for all Mappers,
         * do any final activities and notifications required for the mapper.
         */
    public abstract void configureCompleted();

    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         *<P>Subclasses may want to override this to explicitly set
         *the desired DomainC, this allows the FormInfo to be
         *created without passing in an explicit class
         */
    protected Class myC() {
        if (myC == null) throw new ConfigurationException("This FormInfo does not know its DomainC");
        return myC;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Orm                orm;
    protected DatabaseConnection dbConnection;

    protected Class              myC;

}

