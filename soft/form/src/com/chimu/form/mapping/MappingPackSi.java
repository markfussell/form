/*======================================================================
**
**  File: chimu/form/mapping/MappingPackSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.Table;
import com.chimu.form.FormRuntimeException;
import com.chimu.form.*;

/**
MappingPackSi is private to FORM and should not be used by FORM clients
**/
public class MappingPackSi {

    //**********************************************************
    //(P)***************** Factory Methods *********************
    //**********************************************************

    //**************************************
    //(P)********** ObjectMapper ***********
    //**************************************

    static public ObjectMapper newObjectMapper(Table table) {
        ObjectMapperC mapper = new ObjectMapperC();
        mapper.initTable(table);
        return mapper;
    }

    static public ObjectMapper newObjectMapperNamed_table(String name, Table table) {
        ObjectMapperC mapper = new ObjectMapperC();
        mapper.initName_table(name, table);
        return mapper;
    }

    //**************************************
    //(P)****** DistinguishingMapper ********
    //**************************************

    static public DistinguishingObjectMapper newDistinguishingObjectMapper(Table table) {
        DistinguishingObjectMapperC mapper = new DistinguishingObjectMapperC();
        mapper.initTable(table);
        return mapper;
    }

    static public DistinguishingObjectMapper newDistinguishingObjectMapperNamed_table(String name, Table table) {
        DistinguishingObjectMapperC mapper = new DistinguishingObjectMapperC();
        mapper.initName_table(name, table);
        return mapper;
    }

/*
    static public DistinguishingObjectMapper newDistinguishingObjectMapperNamed_objectRetriever(String name, SelectiveObjectRetriever or) {
        DistinguishingObjectMapperC mapper = new DistinguishingObjectMapperC();
        Table table = or.table();
        mapper.initName_table(name, table);
        mapper.
        return mapper;
    }
*/

    //**************************************
    //(P)**** SelectiveObjectRetriever *****
    //**************************************

    static public CollectiveObjectRetriever newCollectiveObjectRetriever() {
        return new CollectiveObjectRetrieverC();
    }

    static public CollectiveObjectRetriever newCollectiveObjectRetriever(String name) {
        return new CollectiveObjectRetrieverC(name);
    }

    //**************************************
    //(P)****** AssociationConnector *******
    //**************************************

    static public AssociationConnector newAssociationConnector(Table table) {
        AssociationConnectorC mapper = new AssociationConnectorC();
        mapper.initTable(table);
        return mapper;
    }

    static public AssociationConnector newAssociationConnectorNamed_table(String name, Table table) {
        AssociationConnectorC mapper = new AssociationConnectorC();
        mapper.initName_table(name,table);
        return mapper;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static public FormRuntimeException newOptimisticLockException(String description) {
        FormRuntimeException e = new FormRuntimeException(description);
        e.setupAnomaly(MappingPack.optimisticLockAnomaly());

        e.setupRecognizer("form.mapping");
        e.setupStage("Execution");
        return e;
    }

    static public FormRuntimeException newConfigurationException(String description) {
        FormRuntimeException e = new ConfigurationException(description);
        e.setupAnomaly(MappingPack.configurationAnomaly());

        e.setupRecognizer("form.mapping");
        e.setupStage("Execution");
        return e;
    }

/*
    static public FormRuntimeException newConfigurationException(String description, Throwable e) {
        FormRuntimeException e = new ConfigurationException(description,e);
        e.setupAnomaly(MappingPack.configurationAnomaly());

        e.setupRecognizer("form.mapping");
        e.setupStage("Execution");
        return e;
    }
*/

    //**********************************************************
    //**********************************************************
    //**********************************************************


    private MappingPackSi() {};

};
