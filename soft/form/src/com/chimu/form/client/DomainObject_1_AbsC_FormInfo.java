/*======================================================================
**
**  File: chimu/form/client/DomainObject_1_AbsC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.client;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.mapping.ConfigurationException;
import com.chimu.form.description.*;

import com.chimu.kernel.meta.*;
import com.chimu.kernel.exceptions.*;

import java.lang.reflect.*;

/**
A FormInfo class provides the information to map a DomainC to FORM.

<P>DomainObject_1_FormInfo supports the MapperBuilder protocol
to directly create the mappers required by the Orm.  This
default implementation is oriented to a single mapper, single
table configuration.  FormInfos that have different characteristics may
want to extend from SimpleFormInfoAbsC directly.

<P>Subclasses should "extend" (override and call the superclass implementation)
the method #configureMappers to add the DomainC specific slots.  Subclasses
must implement #mapperName and #oidColumnName to specify the mapper to generate
and the oid column in the table.
**/

public abstract class DomainObject_1_AbsC_FormInfo extends SimpleFormInfoAbsC {


    //==========================================================
    //====================== MapperBuilder =====================
    //==========================================================

        /**
         * Create and add to the Orm all mappers used by this builder.
         * During this stage you can not assume any other mappers exist.
         */
    public void createMappers() {
        if (myMapper != null) throw new DevelopmentException("Mapper creation called multiple times");

        ObjectMapper myOrmMapper = orm.mapperNamed_orNull(mapperName());
        if (myOrmMapper != null) throw new ConfigurationException("Multiple mappers with the same name");

        myTable  = dbConnection.table(tableName());
        myMapper = orm.newObjectMapperNamed_table(mapperName(),myTable);
    }

        /**
         * Configure the created mappers.
         */
    public void configureMappers() {
        setupCreationFunction();
        myMapper.newIdentitySlot_column("oid",oidColumnName());
    }

        /**
         * Creation and configuration have been completed for all Mappers,
         * do any final activities and notifications required for the mapper.
         */
    public void configureCompleted() {
        linkCToMapper();
    }

    //==========================================================
    //==========================================================
    //==========================================================

        /**
         * Specifies the mapper this generator is for.  Subclasses
         * should override
         */
    protected abstract String mapperName();

        /**
         * Specifies the OID column in the table for this mapper.
         */
    protected abstract String oidColumnName();

        /**
         * Specifies the table name for the mapper.  This defaults
         * to the same name as the mapper, but can be overriden if
         * different.
         */
    protected String tableName() {
        return mapperName();
    }

    protected String creationFunctionName() {
        return "form_creationFunction";
    }

        /**
         *<P>Subclasses may want to override this to explicitly set
         *the desired DomainC, this allows the FormInfo to be
         *created without passing in an explicit class
         */
    protected Class myC() {
        return super.myC();
    }

    //==========================================================
    //==========================================================
    //==========================================================

    protected CreationFunction getCreationFunction() {
        CreationFunction result = null;

        MethodReference creationFunctionRef =
            MetaPack.newMethodReferenceInC_methodName(
                myC(),creationFunctionName());

        Method method  = creationFunctionRef.target();
        if (method == null) return null;
        try {
            result    = (CreationFunction) method.invoke(null,null);
        } catch (Exception e) {};
        return result;
    }

    protected void setupCreationFunction() {
        CreationFunction creationFunction = getCreationFunction();
        if (creationFunction == null) throw new ConfigurationException("Could not find creation function "+creationFunctionName()+" for "+myC());
        myMapper.setupCreationFunction(creationFunction);
    }

    protected void linkCToMapper() {
        DomainObject_1_AbsC.linkC_toMapper(myC(),myMapper);
    }

    //==========================================================
    //(P)================ Instance Variables ===================
    //==========================================================

    //======================================
    //===== Just cached information ========
    //======================================

    protected transient Table              myTable;
    protected transient ObjectMapper       myMapper;


}

