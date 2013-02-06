/*======================================================================
**
**  File: chimu/form/mapping/ObjectMapperC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.*;
import com.chimu.form.query.*;
import com.chimu.form.database.*;

import com.chimu.kernel.streams.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.Array;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.meta.*;

import java.sql.*;
import java.lang.reflect.*;

import com.chimu.form.mapping.ExecutionException;

import java.util.Enumeration;

import java.io.PrintWriter;


/*package*/ class ObjectMapperC implements ObjectMapperPi {
    public void setupCreationFunctionFor_name(Class creationC,
                                              String creationFunctionName) {
        Class[] noClasses = {};
        try {
            Method method = creationC.getMethod(creationFunctionName,noClasses);
            creationFunctionRef = MetaPack.newMethodReference(method);
            creationFunction    = (CreationFunction) method.invoke(null,null);
        } catch (Exception e) {
            creationFunction    = null;
            creationFunctionRef = null;
            throw new RuntimeWrappedException("Could not find creationFunction "+creationFunctionName+" in class "+creationC,e);
        }
    }

    protected MethodReference creationFunctionRef = null;
    protected MethodReference callbackFunctionRef = null;

    /*subsystem*/ public MethodReference creationFunctionRef() {
        return creationFunctionRef;
    }

    /*subsystem*/ public void setupCreationFunctionRef(MethodReference creationFunctionRef) {
        this.creationFunctionRef = creationFunctionRef;
        Method method       = creationFunctionRef.target();
        if (method == null) return;
        try {
            creationFunction    = (CreationFunction) method.invoke(null,null);
        } catch (Exception e) {
            e.printStackTrace();
            creationFunction    = null;
        }
    }


    public void setupCallbackFunctionRef(MethodReference callbackFunctionRef)  {
        this.callbackFunctionRef = callbackFunctionRef;
    }

    public void setupCallbackFunctionFor_name(Class callbackC, String procedureName){
        Class[] objectMapperC = {ObjectMapper.class};
        try {
            Method method = callbackC.getMethod(procedureName,objectMapperC);
            this.callbackFunctionRef = MetaPack.newMethodReference(method);
        } catch (Exception e) {
            throw MappingPackSi.newConfigurationException("Could not find method "+procedureName+" in class "+callbackC);
        }
    }

    public MethodReference callbackFunctionRef() {
        return this.callbackFunctionRef ;
    }


    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ ObjectMapperC() {}

    //**********************************************************
    //********************* Initializers ***********************
    //**********************************************************

    /*package*/ ObjectMapperC initTable(Table table) {
        initName_table("Unnamed",table);
        return this;
    }

    /*package*/ ObjectMapperC initName_table(String name, Table table) {
        this.name = name;
        this.table = (TableSi) table;
        return this;
    }

    public void setupTraceStream (PrintWriter traceStream) {
        setupTraceStream_traceLevel(traceStream, 1);
    }

    public void setupTraceStream_traceLevel(PrintWriter traceStream, int traceLevel) {
        this.ts = traceStream;
        this.traceLevel = traceLevel;
    }

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

    public Table table() {
        return table;
    }

        /**
         * Use the extended MappedObject interface and notify the object
         * the automatic insert operations
         */
    public void setupUseExtendedInterface() {
        useExtendedInterface = true;
    }

    //First call on the standard database functionality (?)
    //If not mentioned must fetchIdentityDuringInsert (unless table can't do it)
    //Or should warn and make active

    public void setupFetchIdentityDuringInsert() {
        needToFetchIdentityDuringInsert     = true;
        needToGenerateIdentityBeforeInsert  = false;
    }

    public void setupPostInsertIdentityRetrieverFunction(Function2Arg retriever) {
        identityRetrieverFunction = retriever;
        identityRetrieverDecoder  = null;
    }

    public void setupPostInsertIdentityRetrieverFunction_decoder(Function2Arg retriever, Function1Arg decoder) {
        identityRetrieverFunction = retriever;
        identityRetrieverDecoder = decoder;
    }

    public void setupPreInsertIdentityGenerator(Generator generator) {
        identityGenerator = generator;
        needToGenerateIdentityBeforeInsert = true;
        needToFetchIdentityDuringInsert = false;
    }

    //**************************************
    //**************************************

    public void setupCreatorFunction(CreationFunction creationFunction) {
        this.creationFunction = creationFunction;
    }

    public void setupCreationFunction(CreationFunction creationFunction) {
        this.creationFunction = creationFunction;
    }

    //**************************************
    //**************************************
    //**************************************

    public void setupNeedToRefreshAfterUpdate() {
        needToRefreshAllAfterUpdate = true;
    }

    public void setupNeedToRefreshAfterInsert() {
        needToRefreshAllAfterInsert = true;
    }

    public void setupNeedToRefreshAfterInsertOrUpdate() {
        needToRefreshAllAfterInsert = true;
        needToRefreshAllAfterUpdate = true;
    }



    //**********************************************************
    //(P)******************** Setup Slots **********************
    //**********************************************************

    //**************************************
    //(P)********* Direct Slots ************
    //**************************************

    public Slot newDirectSlot_column_type(String slotName, String mappedColumnName, Class columnJavaType) {
        return newDirectSlot(slotName, table.newColumnNamed_type(mappedColumnName,columnJavaType));
    }

    public Slot newDirectSlot_type(String slotName, Class columnJavaType) {
        return newDirectSlot(slotName, table.newColumnNamed_type(slotName,columnJavaType));
    }

    public Slot newDirectSlot_column(String slotName, String mappedColumnName) {
        return newDirectSlot(slotName, table.newColumnNamed(mappedColumnName));
    }
    public Slot newDirectSlot(String slotName, String mappedColumnName) {
        return newDirectSlot(slotName, table.newColumnNamed(mappedColumnName));
    }
    public Slot newDirectSlot(String slotAndColumnName) {
        return newDirectSlot(slotAndColumnName, slotAndColumnName);
    }

    protected Slot newDirectSlot(String slotName,Column mappedColumn) {
        Slot slot = new SlotC(slotName,this,true,true,mappedColumn,null,null);
        this.addSlot(slot);
        return slot;
    }

    //**************************************
    //(P)******** Identity Slot ************
    //**************************************

    public IdentitySlot newIdentitySlot_type(String slotName, Class columnJavaType) {
        return this.newIdentitySlot(slotName,null,null,table.newColumnNamed_type(slotName,columnJavaType));
    }

    public IdentitySlot newIdentitySlot_column_type(String slotName, String mappedColumnName, Class columnJavaType) {
        return this.newIdentitySlot(slotName,null,null,table.newColumnNamed_type(mappedColumnName,columnJavaType));
    }

    public IdentitySlot newIdentitySlot_column(
            String slotName,

            String    mappedColumnName
            ) {
        return this.newIdentitySlot(slotName,null,null,table.newColumnNamed(mappedColumnName));
    }

    public IdentitySlot newIdentitySlot(
            String slotName,

            String    mappedColumnName
            ) {
        return this.newIdentitySlot(slotName,null,null,table.newColumnNamed(mappedColumnName));
    }


    public IdentitySlot newIdentitySlot(
            String slotName,

            Function1Arg     getter,
            Procedure2Arg    setter,

            String    mappedColumnName
            ) {
        return this.newIdentitySlot(slotName,getter,setter,table.newColumnNamed(mappedColumnName));
    }


    protected IdentitySlot newIdentitySlot(
            String slotName,

            Function1Arg     getter,
            Procedure2Arg    setter,

            Column    mappedColumn
            ) {
        return this.newIdentitySlot(slotName,getter,setter,mappedColumn,null,null);
    }

    protected IdentitySlot newIdentitySlot(
            String slotName,

            Function1Arg     getter,
            Procedure2Arg    setter,

            Column  mappedColumn,  // Must be a SimpleColumn
            Function1Arg    decoder,
            Function1Arg    encoder
            ) {
        IdentitySlotPi slot = new IdentitySlotC(slotName,this,getter,setter,mappedColumn,decoder,encoder);
        this.addIdentityKeySlot(slot);
        return slot;
    }

    protected IdentitySlot newIdentitySlot(
            String slotName,

            Function1Arg     getter,
            Procedure2Arg    setter,

            String          mappedColumnName,
            Function1Arg    decoder,
            Function1Arg    encoder
            ) {
        return this.newIdentitySlot(slotName,getter,setter,table.newColumnNamed(mappedColumnName),decoder,encoder);
    }



    //**************************************
    //(P)***** Transformation Slots ********
    //**************************************

    public Slot newTransformationSlot(
            String slotName,

            String          mappedColumnName,
            Function1Arg    decoder,
            Function1Arg    encoder
            ) {
        Slot slot = new SlotC(slotName,this,true,true,table.newColumnNamed(mappedColumnName),decoder,encoder);
        this.addSlot(slot);
        return slot;
    }

    public Slot newTransformSlot_column_decoder_encoder(
            String slotName,
            String          mappedColumnName,
            Function1Arg    decoder,
            Function1Arg    encoder
            ) {
        Slot slot = new SlotC(slotName,this,true,true,table.newColumnNamed(mappedColumnName),decoder,encoder);
        this.addSlot(slot);
        return slot;
    }

    public Slot newTransformSlot_column_type_decoder_encoder(
            String slotName,
            String          mappedColumnName,
            Class           columnJavaType,
            Function1Arg    decoder,
            Function1Arg    encoder
            ) {
        Slot slot = new SlotC(slotName,this,true,true,table.newColumnNamed_type(mappedColumnName,columnJavaType),decoder,encoder);
        this.addSlot(slot);
        return slot;
    }

    protected SetterSlot newTransformationSlot(
            String slotName,

            Function1Arg     getter,
            Procedure2Arg    setter,

            Column  mappedColumn,  // Must be a SimpleColumn
            Function1Arg    decoder,
            Function1Arg    encoder
            ) {
        SetterSlot slot = new SetterSlotC(slotName,this,getter,setter,mappedColumn,decoder,encoder);
        this.addSlot(slot);
        return slot;
    }

    //**************************************
    //(P)********* Constant Slot ***********
    //**************************************

    public Slot newConstantSlot(
            String    slotName,
            String    mappedColumnName,
            Object    slotValue
            ) {
        SlotPi slot = new ConstantSlotC(slotName, this, table.newColumnNamed(mappedColumnName), slotValue);
        this.addSlot(slot);
        return slot;
    }

    public Slot newConstantSlot_column_value(
            String slotName,

            String   mappedColumnName,
            Object   slotValue
            ) {
        SlotPi slot = new ConstantSlotC(slotName, this, table.newColumnNamed(mappedColumnName), slotValue);
        this.addSlot(slot);
        return slot;
    }

    public Slot newConstantSlot_column_type_value(
            String slotName,

            String   mappedColumnName,
            Class    columnJavaType,
            Object   slotValue
            ) {
        SlotPi slot = new ConstantSlotC(slotName, this, table.newColumnNamed_type(mappedColumnName,columnJavaType), slotValue);
        this.addSlot(slot);
        return slot;
    }

    //**************************************
    //(P)**** Forward Association Slots ****
    //**************************************

    public ForwardAssociationSlot newForwardSlot_column_partner(
            String slotName,
            String             mappedColumnName,
            ObjectRetriever    partnerMapper
            ) {
        return newForwardAssociationSlot(slotName,mappedColumnName,partnerMapper);
    }

    public ForwardAssociationSlot newForwardAssociationSlot(
            String slotName,
            String             mappedColumnName,
            ObjectRetriever    partnerMapper
            ) {
        return newForwardAssociationSlot(slotName,null,table.newColumnNamed(mappedColumnName),partnerMapper);
    }

    public ForwardAssociationSlot newForwardAssociationSlot(
            String slotName, Function1Arg  getter,
            String             mappedColumnName,
            ObjectRetriever    partnerMapper
            ) {
        return newForwardAssociationSlot(slotName,getter,table.newColumnNamed(mappedColumnName),partnerMapper);
    }

    protected ForwardAssociationSlot newForwardAssociationSlot(
            String slotName,

            Function1Arg     getter,

            Column  mappedColumn,
            ObjectRetriever    partnerMapper
            ) {
        return newForwardAssociationSlot(slotName,getter,null,mappedColumn,partnerMapper);
    }


    protected ForwardAssociationSlot newForwardAssociationSlot(
            String slotName,

            Function1Arg     getter,
            Procedure2Arg    setter,

            Column  mappedColumn,
            ObjectRetriever    partnerMapper
            ) {
        ForwardAssociationSlotC slot =
            new ForwardAssociationSlotC(slotName,this,getter,setter,mappedColumn,(ObjectRetrieverPi) partnerMapper);
        this.addSlot(slot);
        return slot;
    }

    protected ForwardAssociationSlot newForwardAssociationSlot(
            String slotName, Function1Arg  getter, Procedure2Arg setter,
            String          mappedColumnName,
            ObjectRetriever    partnerMapper
            ) {
        return newForwardAssociationSlot(slotName,getter,setter,table.newColumnNamed(mappedColumnName),partnerMapper);
    }

    //**************************************
    //(P)**** Reverse Association Slots ****
    //**************************************

    public ReverseAssociationSlot newReverseSlot_partner_partnerSlot(
            String slotName,

            ObjectRetriever    partnerMapper,
            String             partnerSlotName
            ) {
        return newReverseAssociationSlot(slotName,null,null,partnerMapper,partnerSlotName);
    }

    public ReverseAssociationSlot newReverseAssociationSlot(
            String slotName,

            ObjectRetriever    partnerMapper,
            String             partnerSlotName
            ) {
        return newReverseAssociationSlot(slotName,null,null,partnerMapper,partnerSlotName);
    }

    public ReverseAssociationSlot newReverseAssociationSlot(
            String slotName,
            Function1Arg    getter,

            ObjectRetriever    partnerMapper,
            String             partnerSlotName
            ) {
        return newReverseAssociationSlot(slotName,getter,null,partnerMapper,partnerSlotName);
    }

    protected ReverseAssociationSlot newReverseAssociationSlot(
            String slotName,

            Function1Arg     getter,
            Procedure2Arg    setter,

            ObjectRetriever    partnerMapper,
            String          partnerSlotName
            ) {
        ReverseAssociationSlot slot =  new ReverseAssociationSlotC(slotName,this,getter,setter,(ObjectRetrieverPi) partnerMapper,partnerSlotName);
        this.addSlot(slot);
        return slot;
    }

    //**************************************
    //(P)*** External Association Slots ****
    //**************************************

    public ExternalAssociationSlot newExternalSlot_connector_mySlot(
            String                 slotName,

            AssociationConnector   associationConnector,
            String                 myConnectorSlotName
            ) {
        Column mappedColumn = this.identityKeySlot.dbColumn();
        ExternalAssociationSlot slot = new ExternalAssociationSlotC(slotName,this,null,null,mappedColumn,(AssociationConnectorPi) associationConnector,myConnectorSlotName);
        this.addSlot(slot);
        return slot;
    }

    public ExternalAssociationSlot newExternalAssociationSlot(
            String                 slotName,

            AssociationConnector   associationConnector,
            String                 myConnectorSlotName
            ) {
        Column mappedColumn = this.identityKeySlot.dbColumn();
        ExternalAssociationSlot slot = new ExternalAssociationSlotC(slotName,this,null,null,mappedColumn,(AssociationConnectorPi) associationConnector,myConnectorSlotName);
        this.addSlot(slot);
        return slot;
    }

    public ExternalAssociationSlot newExternalSlot_connector_mySlot_partnerSlot(
            String                 slotName,

            AssociationConnector   associationConnector,
            String                 myConnectorSlotName,
            String                 partnerConnectorSlotName
            ) {
        Column mappedColumn = this.identityKeySlot.dbColumn();
        ExternalAssociationSlot slot = new ExternalAssociationSlotC(slotName,this,null,null,mappedColumn,(AssociationConnectorPi) associationConnector, myConnectorSlotName);
        this.addSlot(slot);
        return slot;
    }

    public ExternalAssociationSlot newExternalAssociationSlot(
            String                 slotName,

            AssociationConnector   associationConnector,
            String                 myConnectorSlotName,
            String                 partnerConnectorSlotName
            ) {
        Column mappedColumn = this.identityKeySlot.dbColumn();
        ExternalAssociationSlot slot = new ExternalAssociationSlotC(slotName,this,null,null,mappedColumn,(AssociationConnectorPi) associationConnector, myConnectorSlotName);
        this.addSlot(slot);
        return slot;
    }



    //**********************************************************
    //(P)******************* Done Setup ************************
    //**********************************************************

        /**
         * Prepare the mapper to be ready for use
         */
    public void doneSetup() {
        if (setupMode >= 2) return;
        validateSetup();
        int size = slots.size();
        this.needToRefreshAfterInsert = this.needToRefreshAllAfterInsert;
        this.needToRefreshAfterUpdate = this.needToRefreshAllAfterUpdate;
        for (int i = 0; i < size; i++) {
    	    SlotPi slot = slotAtIndex(i);
    	    this.needToRefreshAfterInsert |= slot.needToRefreshAfterInsert();
    	    this.needToRefreshAfterUpdate |= slot.needToRefreshAfterUpdate();
    	    this.hasOptimisticLockSlots   |= slot.isOptimisticLock();
        };
        table.doneSetup();
        setupMode = 2;
        if (callbackFunctionRef != null) {
            Object[] arguments = {this};
            try {
                callbackFunctionRef.target().invoke(null,arguments);
            } catch (Exception e) {
                throw new RuntimeWrappedException("Could not callback through method "+callbackFunctionRef,e);
            }
        }
        clearIdentityCache(); //Setup the identity cache
    }


        /**
         * Check whether the setup of this mapper appears
         * to be correct.  This can either be done before
         * calling "doneSetup" or it will be done during
         * that call.
         */
    public boolean isSetupValid() {
        if (setupMode >= 1) return true;
        try {
            validateSetup();
        } catch (Exception e) {
            return false;
        };
        return true;
    }


    protected String validationName() {
        return name()+":"+tableName();
    }

    public void validateSetup() {
        if (setupMode >= 1) return;
        if (table == null) throw MappingPackSi.newConfigurationException("No table for mapper "+name());
        if (identityKeySlot == null) throw MappingPackSi.newConfigurationException("No identity key slot for mapper "+name());
        if (slots.size() < 1) throw MappingPackSi.newConfigurationException("No slots in mapper "+validationName());
        if (creationFunction == null) throw MappingPackSi.newConfigurationException("No creationFunction in mapper "+validationName());
        table.validateSetup();

        int size = slots.size();
        for (int i = 0; i < size; i++) {
            String columnName = slotAtIndex(i).columnName();
            if (table.columnNamed(columnName) == null) throw MappingPackSi.newConfigurationException(
                    "Slot "+slotAtIndex(i).name()+" needs a column named "+columnName+
                    " which is not declared for table "+tableName());
        };
        setupMode = 1;
    }

    /*subsystem*/ public void crossValidate() {
        if (setupMode >= 3) return;
        if (setupMode < 2) throw MappingPackSi.newConfigurationException("Mapper "+validationName()+" was not setup before calling cross validation");
        int size = slots.size();

        boolean failed = false;
        for (int i = 0; i < size; i++) {
    	    SlotPi slot = slotAtIndex(i);
            try {
                slot.crossValidate();
            } catch (Exception e) {
                failed = true;
                System.err.println("Cross validation failed for slot "+slot.name()+" because: "+e);
                if (isTracing()) e.printStackTrace();
            }
        };
        if (failed) throw MappingPackSi.newConfigurationException("Some slots for mapper "+validationName()+" were invalid");
        setupMode = 3;
    }


    public boolean isConfigurationValid() {return isSetupValid();}
    public void configure() {}


    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public Slot slotNamed(String slotName) {
        return mySlotNamed(slotName);  //[1]

        //[1] Seperating these two methods is just to get the
        //    public method to match signature and not expose SlotPi
    }

    //**********************************************************
    //(P)************ Object To Mapper Interface ***************
    //**********************************************************

        /**
         * Unstub the passed in stub object by retrieving the information
         * from the database and then initializing the stub
         *
         *@access Friend(Replicate)
         */
    public void unstubObject(MappedObject object) {

        Object identityKey = this.getIdentityKeyFromObject(object);

if (ts != null) {
    ts.print("FORM: Unstub by: "+tableName()+"=");
    ts.print(object.getClass()+"=");
    ts.println(identityKey);
};


        Row row = table.findPrimaryKey(identityKey);
        if (row==null) {
            throw new MappingException("No row matching the {"+object.getClass()+"} StubObject's identity key: "+identityKey+" in table "+table);
        };
        this.buildObjectInto_fromRow(object, row);
    }

    public void deleteObject(MappedObject object) {
        if (!this.passesOptimisticLocks(object)) {
            throw MappingPackSi.newOptimisticLockException("Optimistic Version mismatch");
        };
        deleteObjectNoOptimisticCheck(object);
    }

    public void deleteObjectNoOptimisticCheck(MappedObject object) {
        Object identityKey = this.getIdentityKeyFromObject(object);
        if (identityKey == null) return;     // Object is not on database

        Enumeration enum = slots.elements();
        while (enum.hasMoreElements()) {
            SlotPi slot = (SlotPi) enum.nextElement();
            if (slot.isAssociation()) {
                AssociationSlotPi assocSlot = (AssociationSlotPi) slot;
                assocSlot.deleteCascadeFor(object);
            };
        };

        table.deleteRow(identityKey);
    }


    protected List forceIdentityOnForwardAssocationSlotValues(KeyedCollection slotMap) {
        Enumeration enum                  = slots.elements();
        List    identityWriteObjects  = CollectionsPack.newList();
        while (enum.hasMoreElements()) {
            SlotPi slot = (SlotPi) enum.nextElement();
            if (slot.isForwardAssociation()) {
if (ts != null) {ts.print("FORM: ForwardAssociation IdentityCheck:");};
                ForwardAssociationSlotPi fslot  = (ForwardAssociationSlotPi) slot;
                MappedObject             mo     = (MappedObject) slotMap.atKey(fslot.name());
                if (mo != null) {
                    ObjectMapperPi           mapper = (ObjectMapperPi) mo.form_objectMapper();
                    if (mapper.forceServerIdentityOn(mo)) {
                        identityWriteObjects.add(mo);
                    };
                };
if (ts != null) {ts.println("");};
            };
        };
        return identityWriteObjects;
    }


    //**********************************************************
    //(P)********************** UPDATE *************************
    //**********************************************************

    protected boolean passesOptimisticLocks(MappedObject object) {
        if (!hasOptimisticLockSlots) return true;

        Object identityKey = this.getIdentityKeyFromObject(object);
        Row row = table.findPrimaryKey(identityKey);
        if (row == null) return true;

        return areOptimisticSlotsFor_equalToRow(object,row);
    }

    protected boolean areOptimisticSlotsFor_equalToRow(MappedObject object, Row row) {
        KeyedArray   slotValues = slotValuesFor(object);

        int maxRead = slots.size();
        for (int i = 0; i < maxRead; i++) {
            SlotPi eachSlot = slotAtIndex(i);
            if (eachSlot.isOptimisticLock()) {
                Object slotValue = slotValues.atIndex(i);
        		Object rowSlotValue = eachSlot.newSlotValueFromRow(row);
//if (ts != null) {ts.println("Optimistic check["+i+"]: "+slotValue+"{"+slotValue.getClass()+"} ?= "+rowSlotValue+"{"+rowSlotValue.getClass()+"}");};
//MLF-980315: Can't exactly remember why I am doing the above vs. the below.  Would seem to be a race condition problem
if (ts != null) {ts.println("Optimistic check["+i+"]: "+slotValue+"{"+"} ?= "+rowSlotValue+"{"+"}");};
        		if (slotValue == null) {
        		    if (rowSlotValue != null) return false;
        		} else if (!slotValue.equals(rowSlotValue)) {
                    return false;
                }
            }
        };
        return true;
    }



    public void updateObject(MappedObject object) {
        if (!this.passesOptimisticLocks(object)) {
            throw MappingPackSi.newOptimisticLockException("Optimistic Version mismatch");
        };
        updateObjectNoOptimisticCheck(object);
    }

        /**
         * Assumes an object is unstubbed
         */
    protected void updateObjectNoOptimisticCheck(MappedObject object) {
        Object identityKey = this.getIdentityKeyFromObject(object);
        if (identityKey == null) {// Object is not on database
            throw new MappingException("Tried to update an object that has no identityKey to "+tableName());
        };

        KeyedArray slotValues = slotValuesFor(object);
        List identityWriteObjects = forceIdentityOnForwardAssocationSlotValues(slotValues);
        Row row = this.newDbRow(object);
        buildUpdateRowInto_fromSlotValues(row, slotValues);

        try {
            table.updateRow(row, identityKey);
        } catch (Exception e) {
            throw new MappingException("Row insertion for update failed "+identityKey,e);
        };

        if (!identityWriteObjects.isEmpty()) {
            Enumeration objectEnum = identityWriteObjects.elements();
            while (objectEnum.hasMoreElements()) {
                MappedObject             mo     = (MappedObject) objectEnum.nextElement();
                ObjectMapperPi           mapper = (ObjectMapperPi) mo.form_objectMapper();
                mapper.postIdentityInsertUpdateObject(mo);
            };
        };

        Enumeration enum = slots.elements();
        while (enum.hasMoreElements()) {
            SlotPi slot = (SlotPi) enum.nextElement();
            if (slot.isExternalAssociation()) {
                ExternalAssociationSlotPi exslot = (ExternalAssociationSlotPi) slot;
if (ts != null) {ts.println("FORM: External Association: "+exslot.name()+" for "+object);};
                exslot.updateAssociationsFor(object, slotValues);
            };
        };

            //Refresh...Must occur before?
        if (needToRefreshAfterUpdate) {
            this.refreshAfterUpdateObject_oldSlotValues(object,slotValues);
            //this.shallowRefreshObject(object);
            slotValues = slotValuesFor(object);
        };

        //object.form_doneUpdate();
    }

        //
        // This is an update if there were changes needed... for example, are there
        // any ForwardAssociations?, because those are not saved
        // but if there is nothing unusual, we don't actually have to do a second save
        //
    /*package*/ public void postIdentityInsertUpdateObject(MappedObject domainObject) {

        if (domainObject == null) return;  //This should be unnecessary (not called unless put an identity in), but is part of the standard Pi calls

        if (useExtendedInterface) {
                //Give the object a chance to do something different...
            ((MappedObjectXi) domainObject).form_updateAfterIdentityFor(this);
        } else {
            afterIdentityInsertUpdateObject(domainObject);
        }
        return;
    }

    /*friend:MappedObject*/ public void afterIdentityInsertUpdateObject(MappedObject domainObject) {
        updateObjectNoOptimisticCheck(domainObject);
    }

    protected void refreshAfterInsertObject_oldSlotValues(MappedObject object, KeyedArray oldSlotValues) {
        Object identityKey = this.getIdentityKeyFromObject(object);
        if (identityKey == null) return;     // Object is not on database
if (ts != null) ts.println("FORM: Refresh object "+identityKey+" after insert");

        Row row = table.findPrimaryKey(identityKey);
        if (row == null) return;

        if (needToRefreshAllAfterInsert) {
            this.buildObjectInto_fromRow(object, row);
        } else {
            this.buildRefreshAfterInsertObjectInto_fromRow_oldSlotValues(object, row, oldSlotValues);
        }
    }

    protected void refreshAfterUpdateObject_oldSlotValues(MappedObject object, KeyedArray oldSlotValues) {
        Object identityKey = this.getIdentityKeyFromObject(object);
        if (identityKey == null) return;     // Object is not on database
if (ts != null) ts.println("FORM: Refresh object "+identityKey+" after update");

        Row row = table.findPrimaryKey(identityKey);
        if (row == null) return;

        if (needToRefreshAllAfterUpdate) {
            this.buildObjectInto_fromRow(object, row);
        } else {
            this.buildRefreshAfterUpdateObjectInto_fromRow_oldSlotValues(object, row, oldSlotValues);
        }
    }

    public void shallowRefreshObject(MappedObject object) {
        Object identityKey = this.getIdentityKeyFromObject(object);
        if (identityKey == null) return;     // Object is not on database

        Row row = table.findPrimaryKey(identityKey);
        if (row == null) return;

        this.buildObjectInto_fromRow(object, row);
    }

    //**********************************************************
    //(P)********************** INSERT *************************
    //**********************************************************

    public void insertObject(MappedObject object) {

/*
    // Insertion precheck;
        if (!this.doesObjectVersionMatchDbForInsert(object)) {
            throw new Exception("Version mismatch");
        };

*/


        if (needToGenerateIdentityBeforeInsert) {
if (ts != null) {ts.println("FORM: Generate Identity");};
            generateIdentityInto(object);
        };

        KeyedArray slotValues = slotValuesFor(object);
        List identityWriteObjects = forceIdentityOnForwardAssocationSlotValues(slotValues);

        Row row = this.newDbRow(object);

        if (needToFetchIdentityDuringInsert) {
            this.buildUpdateRowInto_fromSlotValues(row, slotValues);  //Don't need the identitySlot
            Object identityKey = null;
            try {
                identityKey = table.insertRow_getIdentity(row);
            } catch (Exception e) {
                throw new MappingException("Row insertion failed",e);
            };
            if (identityKey == null) {
                new MappingException("Have no identity from insert operation");
            };
            setIdentityFor_to(object,identityKey);
        } else {
            this.buildRowInto_fromSlotValues(row, slotValues);
            try {
                table.insertRow(row);
            } catch (Exception e) {
                throw new MappingException("Row insertion failed",e);
            };
        };
        //slotValues could now be out of date for the identitySlot

        postInsertRegisterIdentity(object);

            // IdentityWritenObjects
        if (!identityWriteObjects.isEmpty()) {
            Enumeration objectEnum = identityWriteObjects.elements();
            while (objectEnum.hasMoreElements()) {
                MappedObject             mo     = (MappedObject) objectEnum.nextElement();

                ObjectMapperPi           mapper = (ObjectMapperPi) mo.form_objectMapper();
                mapper.postIdentityInsertUpdateObject(mo);
            };
        };

            // ExternalAssociations
        Enumeration enum = slots.elements();
        while (enum.hasMoreElements()) {
            SlotPi slot = (SlotPi) enum.nextElement();
            if (slot.isExternalAssociation()) {
                ExternalAssociationSlotPi exslot = (ExternalAssociationSlotPi) slot;
if (ts != null) {ts.println("FORM: External Association: "+exslot.name()+" for "+object);};
                exslot.insertAssociationsFor(object, slotValues);
            };
        };

            //Refresh...Must occur before?
        if (needToRefreshAfterInsert) {
            this.refreshAfterInsertObject_oldSlotValues(object,slotValues);
            // this.shallowRefreshObject(object);
            slotValues = slotValuesFor(object);
        };

        //Now we can do a deep refresh?

    }

    //**********************************************************
    //(P)******************** Searching ************************
    //**********************************************************

    //**************************************
    //(P)************* Simple **************
    //**************************************

    public Array selectAll() {
        Array rows = table.selectAll();
        return this.newObjectsFromRows(rows);
    }

    public Object findAny() {
        Row row = table.findAny();
        return newObjectFromRow(row);
    }

    public int countAll() {
        return table.countAll();
    }

        /**
         * Find the object with the given identity key
         * This will look into the cache before doing a database hit
         */
     public Object findIdentityKey(Object identityKey) {
        if (identityKey == null) return null;

        if (objectCache.includesKey(identityKey)){
            return objectCache.atKey(identityKey);
        };

        Row row = table.findPrimaryKey(identityKey);
        return newObjectFromRow(row);

     }

    /*package*/ public Object findWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue) {
        SlotPi slot = (SlotPi) slotNamed(slotName);

        Row row =  table.findWhereColumn_equals((Column) slot.column(), columnValue);
        return this.newObjectFromRow(row);
    }

    public Object findWhereSlotNamed_equals(String slotName, Object value) {
        SlotPi slot = (SlotPi) slotNamed(slotName);
        if ( (value == null) || (!slot.hasColumnValue()) ) {
            QueryDescription query = QueryPackSi.newQueryDescription();
            QueryVar ev = query.newExtentVar(this);
            if (value == null) {
                query.and(query.newNull(ev.slotNamed(slotName)));
            } else {
                query.and_equalsConstant(ev.slotNamed(slotName),value);
            };
            return query.findAny();
        };
        Object columnValue = encodeValue_forSlot(value,slot);

        Row row =  table.findWhereColumn_equals((Column) slot.column(), columnValue);
        return this.newObjectFromRow(row);
    }

    /*package*/ public Array selectWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue) {
        SlotPi slot = (SlotPi) slotNamed(slotName);

        Array rows = table.selectWhereColumn_equals((Column) slot.column(), columnValue);
        return this.newObjectsFromRows(rows);
    }


    protected Object encodeValue_forSlot(Object value, SlotPi slot) {
        return slot.encode(value);
    }

    public Array selectWhereSlotNamed_equals(String slotName, Object value) {
        SlotPi slot = (SlotPi) slotNamed(slotName);
        if ( (value == null) || (!slot.hasColumnValue()) ) {
            QueryDescription query = QueryPackSi.newQueryDescription();
            QueryVar ev = query.newExtentVar(this);
            if (value == null) {
                query.and(query.newNull(ev.slotNamed(slotName)));
            } else {
                query.and_equalsConstant(ev.slotNamed(slotName),value);
            };
            return query.selectAll();
        };
        Object columnValue = encodeValue_forSlot(value,slot);

        Array rows = table.selectWhereColumn_equals((Column) slot.column(), columnValue);
        return this.newObjectsFromRows(rows);
    }

    //**************************************
    //(P)************* Query ***************
    //**************************************

    public Object findWhereQuery(QueryDescription queryD) {// String slotName, Object value) {
            //Use our slots as values? What are the values?  Has to be our slots? no, it doesn't matter
            //it just matters the the select clause matches one of the ExtentVariables

        Row row = table.findWhereSqlString(queryStringFor(queryD));
        return this.newObjectFromRow(row);
    }

    protected String queryStringFor(QueryDescription queryD) {
        QueryVar resultsVar = queryD.resultsVar();
        if (resultsVar == null) {
            QueryVar qv = queryD.aQueryVarForRetriever(this);
            if (qv == null) {
                throw new MappingException("This mapper can't use this query, it does have any variables from our extent?");
            };
            queryD.setResultsVar(qv);  // just pick one of the query variables...
        } else {
            if (resultsVar.valuesRetriever() != this) {
                throw new MappingException("This mapper can't use this query, it is not the expected results mapper");
            };
        };

        SqlBuilder sqlB = aSqlBuilder();
        queryD.putSqlInto(sqlB);
if (ts != null) {ts.println("FORM: "+sqlB);};
        return sqlB.sqlString();
    }

    protected String countQueryStringFor(QueryDescription queryD) {
        SqlBuilder sqlB = aSqlBuilder();
        queryD.putCountSqlInto(sqlB);
if (ts != null) {ts.println("FORM: "+sqlB);};
        return sqlB.sqlString();
    }

    public int countWhereQuery(QueryDescription queryD) {
        return table.countWhereSqlString(countQueryStringFor(queryD));
    }

    public Array selectWhereQuery(QueryDescription queryD) {
        Array rows = table.selectWhereSqlString(queryStringFor(queryD));
if (ts != null) {ts.println("FORM: Rows = "+rows);};
        return this.newObjectsFromRows(rows);
    }

    public Array selectForSlot_whereQuery(Slot resultSlot, QueryDescription queryD) {
        Array rows = table.selectColumn_whereSqlString(((Column) ((SlotPi) resultSlot).dbColumn()),slotQueryStringFor(queryD));
        return this.newValuesForSlot_fromRows((SlotPi) resultSlot, rows);
    }

    public Object findForSlot_whereQuery(Slot resultSlot, QueryDescription queryD) {
        Array rows = table.selectColumn_whereSqlString(((Column) ((SlotPi) resultSlot).dbColumn()),slotQueryStringFor(queryD));
        return this.newValueForSlot_fromRows((SlotPi) resultSlot, rows);
    }

    protected String slotQueryStringFor(QueryDescription queryD) {
        SqlBuilder sqlB = aSqlBuilder();
        queryD.putSqlInto(sqlB);
if (ts != null && traceLevel > 2) {ts.print("FORM: "+sqlB);};
        return sqlB.sqlString();
    }

/*************
    public int countForSlot_whereQuery(Slot resultSlot, QueryDescription queryD) {
        return table.countWhereSqlString(slot_CountQueryStringFor(resultSlot,queryD));
    }

    protected String slotQueryStringFor(QueryDescription queryD) {
        SqlBuilder sqlB = aSqlBuilder();
        queryD.putSqlInto(sqlB);
if (ts != null && traceLevel > 2) {ts.print("FORM: "+sqlB);};
        return sqlB.sqlString();
    }

slotCountQueryStringFor
****************/

    //**************************************
    //(P)************ Support **************
    //**************************************

    /*subsystem(Query)*/ public String querySelectStringPrefixedWith(String queryVarName) {
        return table.querySelectStringPrefixedWith(queryVarName);
    }

    protected SqlBuilder aSqlBuilder() {
        return table.newSqlBuilder();
    }

    //**********************************************************
    //(P)****************      PACKAGE      ********************
    //**********************************************************

    //package + Query
    /*subsystem*/ public String tableName() {
        return table.name();
    }

    /*package*/ public String identityKeyColumnName() {
        return identityKeySlot.columnName();
    }

    /*package*/ public Column identityKeyColumn() {
        return identityKeySlot.dbColumn();
    }

    /*package*/ public IdentitySlotPi identityKeySlot() {
        return identityKeySlot;
    }



    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    //**********************************************************
    //(P)******************** Identity *************************
    //**********************************************************

    //**************************************
    //(P)*********** Generation ************
    //**************************************

    /*package*/ public Object retrieveInsertedIdentityFor(Object object) {
        Object columnValue = identityRetrieverFunction.valueWith_with(object,this.myConnection());
        Object slotValue;

        if (identityRetrieverDecoder == null) {
            slotValue = identityKeySlot.decode(columnValue);
        } else {
            slotValue = identityRetrieverDecoder.valueWith(columnValue);
        };
        return slotValue;
    }

    /*package*/ public void generateIdentityInto(MappedObject object) {
        if (identityGenerator == null) throw new DevelopmentException("No identity generator but tried to generate identity");

        Object identityKey = identityGenerator.nextValue();
        if (identityKey == null) {
            new MappingException("Could not generate an identity key for a "+object.getClass());
        };

        setIdentityFor_to(object,identityKey);
    }

    protected void postInsertFetchIdentityForObject(MappedObject object) {
        Object identityKey = retrieveInsertedIdentityFor(object);

        if (identityKey == null) {
            new MappingException("Could not fetch the post-insert identity key for a "+object.getClass());
        };

        setIdentityFor_to(object,identityKey);
    }

    //**************************************
    //**************************************
    //**************************************

    /*friend:MappedObject*/ public void insertIdentityForObject(MappedObject object) {
        if (needToGenerateIdentityBeforeInsert) {
            generateIdentityInto(object);
        };
        Row row = this.newDbRow(object);
        if (needToFetchIdentityDuringInsert) {
            this.buildPreIdentityRowInto_fromObject(row, object);
            Object identityKey = null;
            try {
                identityKey = table.insertRow_getIdentity(row);
            } catch (Exception e) {
                throw new MappingException("Could not insert a pre-identity row",e);
            };
            if (identityKey == null) {
                new MappingException("Have no identity from insert operation");
            };
            setIdentityFor_to(object,identityKey);
        } else {
            this.buildIdentityRowInto_fromObject(row, object);
            try {
                table.insertRow(row);
            } catch (Exception e) {
                throw new MappingException("Could not insert an identity row",e);
            };
        };
        postInsertRegisterIdentity (object);
    }

    protected void postInsertRegisterIdentity(MappedObject object) {
        if (needToFetchIdentityAfterInsert && !hasIdentity(object)) {
            postInsertFetchIdentityForObject(object);
        };

        Object identityKey = getIdentityKeyFromObject(object);
        if (identityKey == null) {
            throw new MappingException("Object in table:"+tableName()+" has no identity after identity insertion!!! ");
        };
        objectCache.atKey_put(identityKey,object);
    }


    /**
     * Make sure the object has an identity both locally and on the server
     *@return True if we had to force an identity on this object
     */

    /*package*/ public boolean forceServerIdentityOn(MappedObject domainObject) {
        if (domainObject == null) return false;
        if (useExtendedInterface) {
            return ((MappedObjectXi) domainObject).form_forceIdentityFor(this);
        };

        if (hasIdentity(domainObject)) return false;
if (ts != null) {ts.println("FORM: Found an object without identity!");};
        this.insertIdentityForObject(domainObject);
        return true;
    }

    //**********************************************************
    //(P)************* Row to Object conversions ***************
    //**********************************************************

    /*package(SelectiveObjectMapper)*/ public MappedObject newObjectFromRow(Row row) {
        if (row == null) return null; //null indicates that this row does not generate an object for this mapper

        Object identityKey = getIdentityKeyFromRow(row);
        MappedObject object;

            //This is to prevent WeakMaps from losing the object between the
            //#includes test and the actual retrieval of the object
        object = (MappedObject) objectCache.atKey(identityKey);
        if (object == null) {
            object = this.newObjectForReplicate();
            objectCache.atKey_put(identityKey,object);
            this.buildObjectInto_fromRow(object,row);
        } else if (object.form_isStub()) {
                //We have the information to unstub it:
            this.buildObjectInto_fromRow(object,row);
        }
           
        return object;
    }
    
    //public boolean          form_isStub();

    protected Array /*of Object*/ newObjectsFromRows(Array rows) {
if (ts != null && traceLevel > 1) {ts.print("FORM: Build Objects from Rows: "+rows);};
        if (rows == null) return CollectionsPack.newList();
        int rowCount = rows.size();
        List results = CollectionsPack.newList(rowCount);

        for (int i=0; i <  rowCount; ++i) {
            Row row = (Row) rows.atIndex(i);
            MappedObject object = newObjectFromRow(row);
            if (object != null) {
                results.add(object);
            };
        };

        return results;
    }

    protected void buildRefreshAfterInsertObjectInto_fromRow_oldSlotValues(MappedObject object, Row row, KeyedArray oldSlotValues) {
        if (this.usesSetters) {
            for (Enumeration enum = slots.elements(); enum.hasMoreElements();) {
                SetterSlotPi eachSlot = (SetterSlotPi) enum.nextElement();
                if (eachSlot.isObjectWriter() && eachSlot.needToRefreshAfterInsert()) {
                    eachSlot.setObject_usingRow(object, row);
                };
            };
        } else { // We use an initialization method
            KeyedArray slotValues = this.newSlotRowKeyedArray(object);

            int maxRead = slots.size();
            for (int i = 0; i < maxRead; i++) {
                SlotPi eachSlot = slotAtIndex(i);
                if (eachSlot.isObjectWriter() && eachSlot.needToRefreshAfterInsert()) {
            		Object slotValue = eachSlot.newSlotValueFromRow(row);
                    slotValues.atIndex_put(i,slotValue);
                } else {
                    slotValues.atIndex_put(i,oldSlotValues.atIndex(i));
                };
            };
            initializeObject_with(object,slotValues);
        };
    }

    protected void buildRefreshAfterUpdateObjectInto_fromRow_oldSlotValues(MappedObject object, Row row, KeyedArray oldSlotValues) {
        if (this.usesSetters) {
            for (Enumeration enum = slots.elements(); enum.hasMoreElements();) {
                SetterSlotPi eachSlot = (SetterSlotPi) enum.nextElement();
                if (eachSlot.isObjectWriter() && eachSlot.needToRefreshAfterUpdate()) {
                    eachSlot.setObject_usingRow(object, row);
                };
            };
        } else { // We use an initialization method
            KeyedArray slotValues = this.newSlotRowKeyedArray(object);

            int maxRead = slots.size();
            for (int i = 0; i < maxRead; i++) {
                SlotPi eachSlot = slotAtIndex(i);
                if (eachSlot.isObjectWriter() && eachSlot.needToRefreshAfterUpdate()) {
            		Object slotValue = eachSlot.newSlotValueFromRow(row);
                    slotValues.atIndex_put(i,slotValue);
                } else {
                    slotValues.atIndex_put(i,oldSlotValues.atIndex(i));
                };
            };
            initializeObject_with(object,slotValues);
        };
    }

    protected void buildObjectInto_fromRow(MappedObject object, Row row) {
        if (this.usesSetters) {
            for (Enumeration enum = slots.elements(); enum.hasMoreElements();) {
                SetterSlotPi eachSlot = (SetterSlotPi) enum.nextElement();
                if (eachSlot.isObjectWriter()) {
                    eachSlot.setObject_usingRow(object, row);
                };
            };
        } else { // We use an initialization method
            KeyedArray slotValues = this.newSlotRowKeyedArray(object);
if (ts != null && traceLevel > 0) {ts.print("FORM: Build ObjectInitializer From slots: ");};

            int maxRead = slots.size();
            for (int i = 0; i < maxRead; i++) {
                SlotPi eachSlot = slotAtIndex(i);
        		Object slotValue = eachSlot.newSlotValueFromRow(row);
if (ts != null && traceLevel > 0) {ts.print("["+i+":"+eachSlot.name()+"]");};
if (ts != null && traceLevel > 1) {ts.print("="+slotValue+" ");};
                slotValues.atIndex_put(i,slotValue);
            };
if (ts != null && traceLevel > 0) {ts.println("done.");};
            initializeObject_with(object,slotValues);
if (ts != null && traceLevel > 0) {ts.println("Initialized: "+object);};
        };
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected Array /*of SlotValue Objects*/ newValuesForSlot_fromRows(SlotPi slot, Array rows) {
if (ts != null && traceLevel > 1) {ts.print("FORM: Build SlotValues for "+slot+" from Rows: "+rows);};
        if (rows == null) return CollectionsPack.newList();
        int rowCount = rows.size();
        List results = CollectionsPack.newList(rowCount);

        for (int i=0; i <  rowCount; ++i) {
            Row row = (Row) rows.atIndex(i);
            Object object = slot.newSlotValueFromRow(row);
            if (object != null) {
                results.add(object);
            };
        };

        return results;
    }

    protected Object newValueForSlot_fromRows(SlotPi slot, Array rows) {
if (ts != null && traceLevel > 1) {ts.print("FORM: Build SlotValue for "+slot+" from Rows: "+rows);};
        if (rows == null) return null;
        int rowCount = rows.size();

        for (int i=0; i <  rowCount; ++i) {
            Row row = (Row) rows.atIndex(i);
            Object object = slot.newSlotValueFromRow(row);
            if (object != null) {
                return object;
            };
        };

        return null;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected final Predicate1Arg isRowWriterPredicate = new Predicate1Arg() {
            public boolean isTrueWith(Object o) {SlotPi slot = (SlotPi) o;
                return slot.isRowWriter();
            }
        };

    protected final Predicate1Arg isNonIdentityRowWriterPredicate = new Predicate1Arg() {
            public boolean isTrueWith(Object o) {SlotPi slot = (SlotPi) o;
                return (slot != identityKeySlot) && slot.isRowWriter();
            }
        };

    protected final Predicate1Arg isNonIdentityPreIdentityRowWriterPredicate = new Predicate1Arg() {
            public boolean isTrueWith(Object o) {SlotPi slot = (SlotPi) o;
                return (slot != identityKeySlot) && slot.isRowWriter() && !slot.isAssociation();
            }
        };

    protected final Predicate1Arg isPreIdentityRowWriterPredicate = new Predicate1Arg() {
            public boolean isTrueWith(Object o) {SlotPi slot = (SlotPi) o;
                return slot.isRowWriter() && !slot.isAssociation();
            }
        };

    //**************************************
    //**************************************
    //**************************************

    protected void buildRowInto_fromObject_selectSlot(Row row, MappedObject object, Predicate1Arg isPartOfRow) {
        if (this.usesGetters) {
            for (Enumeration enum = slots.elements(); enum.hasMoreElements();) {
                SetterSlotPi eachSlot = (SetterSlotPi) enum.nextElement();
                if (isPartOfRow.isTrueWith(eachSlot)) {   // identityWriter vs. updateWriter
                    eachSlot.setRow_usingObject(row, object);
                };
            };
        } else { // We use an initialization method
            KeyedCollection   slotValues = slotValuesFor(object);
            buildRowInto_fromSlotValues_selectSlot(row,slotValues,isPartOfRow);
        };
    }

    protected void buildRowInto_fromSlotValues_selectSlot(Row row, KeyedCollection slotValues, Predicate1Arg isPartOfRow) {
if (ts != null && traceLevel > 0) {ts.print("FORM: Build Row From slots: ");};
        int maxRead = slots.size();
        for (int i = 0; i < maxRead; i++) {
            SlotPi eachSlot = slotAtIndex(i);
    		Object slotValue = slotValues.atKey(eachSlot.name());
            if (isPartOfRow.isTrueWith(eachSlot)) {
if (ts != null && traceLevel > 0) {ts.print("["+i+":"+eachSlot.name()+"]="+slotValue+" ");};
                eachSlot.setRow_usingSlotValue(row, slotValue);
            };
        };
if (ts != null && traceLevel > 0) {ts.println("done:"+row);};
    }

    //**************************************
    //**************************************
    //**************************************

    protected void buildRowInto_fromObject(Row row, MappedObject object) {
        buildRowInto_fromObject_selectSlot(row,object,isRowWriterPredicate);
    }

    protected void buildUpdateRowInto_fromObject(Row row, MappedObject object) {
        buildRowInto_fromObject_selectSlot(row,object,isNonIdentityRowWriterPredicate);
    }

    protected void buildPreIdentityRowInto_fromObject(Row row, MappedObject object) {
        buildRowInto_fromObject_selectSlot(row,object,isNonIdentityPreIdentityRowWriterPredicate);
    }

    protected void buildIdentityRowInto_fromObject(Row row, MappedObject object) {
        buildRowInto_fromObject_selectSlot(row,object,isPreIdentityRowWriterPredicate);
    }

    //**************************************
    //**************************************
    //**************************************

    protected void buildRowInto_fromSlotValues(Row row, KeyedCollection slotValues) {
        buildRowInto_fromSlotValues_selectSlot(row,slotValues,isRowWriterPredicate);
    }

    protected void buildUpdateRowInto_fromSlotValues(Row row, KeyedCollection slotValues) {
        buildRowInto_fromSlotValues_selectSlot(row,slotValues,isNonIdentityRowWriterPredicate);
    }

    protected void buildPreIdentityRowInto_fromSlotValues(Row row, KeyedCollection slotValues) {
        buildRowInto_fromSlotValues_selectSlot(row,slotValues,isNonIdentityPreIdentityRowWriterPredicate);
    }

    protected void buildIdentityRowInto_fromSlotValues(Row row, KeyedCollection slotValues) {
        buildRowInto_fromSlotValues_selectSlot(row,slotValues,isPreIdentityRowWriterPredicate);
    }


    //**********************************************************
    //(P)************* Object to slot conversions **************
    //**********************************************************

    protected Object getIdentityKeyFromRow(Row row) {
        return identityKeySlot.newSlotValueFromRow(row);
    }

    public MappedObject newStubObject(Object identityKey) { //
        if (identityKey == null) return null;

        if (objectCache.includesKey(identityKey)){
            return (MappedObject) objectCache.atKey(identityKey);
        };

if (ts != null) {
    ts.print("FORM: Stub built: "+tableName()+"=");
    ts.println(identityKey);
}

        MappedObject object = this.newObjectForStub();
        objectCache.atKey_put(identityKey,object);
        setIdentityFor_to(object, identityKey);

        return object;
    }

        /**
         * Note that this is where a null object is said to have a
         * null identityKey.  See the pairing with newStubObject
         *@see #newStubObject
         */
    /*package*/ public Object encodeObject(MappedObject domainObject) {
        if (domainObject == null) return null;

        return getIdentityKeyFromObject(domainObject);
    }

    /*subsystem(Query)*/ public int encodeType(MappedObject domainObject) {
        return ((Column) identityKeySlot.dbColumn()).sqlDataType();
    }

    /*subsystem(Query)*/ public int encodeJavaType(MappedObject domainObject) {
        return ((Column) identityKeySlot.dbColumn()).javaDataType();
    }

    //**********************************************************
    //(P)**************** Binding Utilities ********************
    //**********************************************************



    protected void setIdentityFor_to(MappedObject object, Object identityKey) {
        object.form_initIdentity(identityKey);
    }

    protected Object getIdentityKeyFromObject(MappedObject object) {
        return object.form_identityKey();
    }

    protected void initializeObject_with(MappedObject object, KeyedArray slotValues) {
        object.form_initState(slotValues);
    }

    protected KeyedArray slotValuesFor(MappedObject object) {
        KeyedArray slotValues = CollectionsPack.newKeyedArray(slotNames);  // KeyedArray
        object.form_extractStateInto(slotValues);
        return slotValues;
    }

    protected boolean hasIdentity(MappedObject object) {
        return object.form_identityKey() != null;
    }

    protected MappedObject newObjectForStub() {
        return creationFunction.valueWith(STUB_CREATION_OBJECT);
    }

    protected MappedObject newObjectForReplicate() {
        return creationFunction.valueWith(REPLICATE_CREATION_OBJECT);
    }

    static protected CreationInfo STUB_CREATION_OBJECT      = CreationInfoC.newForStub();
    static protected CreationInfo REPLICATE_CREATION_OBJECT = CreationInfoC.newForReplicate();

    //**********************************************************
    //(P)******************** Utilities ************************
    //**********************************************************

    protected SlotPi mySlotNamed(String slotName) {
        Number indexHolder = (Number) slotNames.atKey(slotName);
        if (indexHolder == null) return null;
        return (SlotPi) slots.atIndex(indexHolder.intValue());
    }

    protected final SlotPi slotAtIndex(int i) {
        return (SlotPi) slots.atIndex(i);
    }

    protected Column columnForSlotNamed(String slotName) {
        return mySlotNamed(slotName).column();
    }

    /*package*/ public void replaceSlot_withSlot(Slot oldSlot, Slot newSlot) {
        int slotIndex = ((Integer) slotNames.atKey(oldSlot.name())).intValue();
        slots.atIndex_put(slotIndex, newSlot);
        if (identityKeySlot == oldSlot) {
            identityKeySlot = (IdentitySlotPi) newSlot;
            table.setPrimaryKeyColumn(identityKeySlot.column());
        };
    }

    protected void addSlot(Slot slot) {
        if (slotNames.includesKey(slot.name())) {
            throw MappingPackSi.newConfigurationException("Duplicate slot name added: "+slot.name());
        };
        slots.add(slot);
        slotNames.atKey_put(slot.name(),new Integer(slots.size()-1));
    }

    protected void addIdentityKeySlot(IdentitySlotPi slot) {
        if (slotNames.includesKey(slot.name())) {
            throw MappingPackSi.newConfigurationException("Duplicate slot name added: "+slot.name());
        };
        if (identityKeySlot != null) {
            throw MappingPackSi.newConfigurationException("Multiple identity slots added: "+slot.name()+" & "+identityKeySlot.name());
        };
        identityKeySlot = slot;
        slots.add(slot);
        slotNames.atKey_put(slot.name(),new Integer(slots.size()-1));
        table.setPrimaryKeyColumn(slot.column());
    }

    protected Row newDbRow(MappedObject forObject) {
        return table.newDbRow();
    }

    protected Row newDbRow() {
        return table.newDbRow();
    }


    public KeyedArray newSlotRowKeyedArray(MappedObject forObject) {
        return CollectionsPack.newKeyedArray(slotNames);
    }

    //**********************************************************
    //**************** Explicit Cache Management ***************
    //**********************************************************

    public void addToIdentityCache(MappedObject domainObject) {
        Object identityKey = getIdentityKeyFromObject(domainObject);
        if (identityKey == null) throw new FailedRequireException(
                "'domainObject' must have a non-null identity.  Mapper = "+name()
            );

        if (objectCache.includesKey(identityKey)) {
            Object oldObject = objectCache.atKey(identityKey);
            if (oldObject == domainObject) return; //Ignore multiple adding

            throw new FailedRequireException(
                "'domainObject' must have a unique identity within cache of "+name()+".  Old object = "+oldObject+" new object = "+domainObject
            );
        }
        objectCache.atKey_put(identityKey,domainObject);
    }

    public void removeFromIdentityCache(MappedObject domainObject) {
        Object identityKey = getIdentityKeyFromObject(domainObject);
        if (identityKey == null) return;
        objectCache.removeKey(identityKey);
    }

    public void clearIdentityCache() {
        objectCache = CollectionsPack.newWeakMap();
        /*
if ((ts != null) && (traceLevel > 1)) {
        objectCache = CollectionsPack.newWeakMap();
}
        */
    }

    public int cacheCount() {
        return objectCache.size();
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Connection myConnection() {
        return table.connection();
    }

    public Array slots() {
        return slots;
    }

    public String name() {
        if (name == null) {return tableName();};
        if (name.length() == 0) {return tableName();};
        if (name.equals("Unnamed")) {return tableName();};
        return name;
    }

    public Array selectColumns() {
        return table.basicColumns();
    }

    protected boolean isTracing() {
        return ts != null;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String          name;

    protected TableSi         table;

    protected Map             slotNames = CollectionsPack.newMap();        // The following are cached information
    protected List        slots     = CollectionsPack.newList(); // = new Dynarray();
    protected IdentitySlotPi  identityKeySlot;

    protected boolean         usesSetters = false;
    protected boolean         usesGetters = false;

    protected boolean         mayNeedToRefresh         = false;
    protected boolean         needToRefreshAfterInsert = false;
    protected boolean         needToRefreshAfterUpdate = false;

    protected boolean         needToRefreshAllAfterInsert = false;
    protected boolean         needToRefreshAllAfterUpdate = false;

    protected boolean         needToGenerateIdentityBeforeInsert = false;
    protected boolean         needToFetchIdentityAfterInsert     = false;
    protected boolean         needToFetchIdentityDuringInsert    = false;

    protected boolean         hasOptimisticLockSlots = false;

    protected boolean         useExtendedInterface = false;

    protected Map             objectCache = CollectionsPack.newWeakMap();


    protected CreationFunction creationFunction = null;

    protected Function2Arg identityRetrieverFunction = null;
    protected Function1Arg identityRetrieverDecoder = null;

    protected Generator    identityGenerator = null;


    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected PrintWriter ts = null;
    protected int traceLevel = 1;     //0 = minimal tracing, 1= normal (and slowing), 2=detailed


    protected int setupMode = 0;      //0 = not setup, 1 = validatedForSetup, 2 = setup, 3 = validatedForConfigure


    //**********************************************************
    //**********************************************************
    //**********************************************************
    //**********************************************************
    //**********************************************************
    //**********************************************************


    //**********************************************************
    //************************ OLD *****************************
    //**********************************************************

    protected Function0Arg    creatorFunction;

    protected Procedure2Arg   objectInitializer;
    protected Function1Arg    valuesExtractor;
    protected Predicate1Arg   isNewObjectPredicate;

    protected Procedure1Arg   postInitializationFunction;


    //**********************************************************
    //(P)****************       STATIC       *******************
    //**********************************************************
    // Move this into functors?

/*
    static abstract class Function1ArgWithPropertyAbsC implements Function1Arg {
        Function1ArgWithPropertyAbsC(Object property) {
            this.property = property;
        };
        public Object property;
    };
*/

    //**********************************************************
    //(P)****************   STATIC PRIVATE    ******************
    //**********************************************************


    //**********************************************************
    //(P)******************** Searching ************************
    //**********************************************************

        /**
         * Take a partially formed query (no select statement) and select all the objects
         * that match the string
         *@access Friend(AssociationConnector, AssociationSlot)
         */
    /*package*/ public Object findWhereQString_hasColumnValue(String qstring, Object columnValue)
            throws SQLException {

        Row row = table.findWhereSqlString_hasValue(qstring,columnValue);
        return this.newObjectFromRow(row);
    }

        /**
         * Take a partially formed query (no select statement) and select all the objects
         * that match the string
         *@access Friend(AssociationConnector, AssociationSlot)
         */
    /*package*/ public Array selectWhereQString_hasColumnValue(String qstring, Object columnValue)
            throws SQLException {

        Array rows = table.selectWhereSqlString_hasValue(qstring,columnValue);
        return  this.newObjectsFromRows(rows);
    }

        /**
         *@access Friend(AssociationConnector, AssociationSlot)
         */
    /*package*/ public Array selectWhereQString_hasColumnValues(String qstring, Array columnValues)
            throws SQLException {

        Array rows = table.selectWhereSqlString_hasValues(qstring,columnValues);
        return  this.newObjectsFromRows(rows);
    }

/**
    public void setupMayNeedToRefresh() {
        mayNeedToRefresh = true;
    }

    public void setupNeedToFetchIdentityAfterInsert() {
        needToFetchIdentityAfterInsert = true;
    }

    public void setupFetchIdentityAfterInsert() {
        needToFetchIdentityAfterInsert = true;
    }
**/

        // ForceServerIdentityForForwardReferences()
        // forEach(Slot that isForwardAssociation) getSlotValue(object) and mapper(??) [from slot or from object]
        //      forceServerIdentity...
        //      hasServerIdentity()
        //      generateServerIdentity()
        //          (?can recurse? No, because just trying to get identity)
        //          (?could try for more than identity?  Yes, but then would be more complicated)
        //          (Does object know identity state?
        //              Yes if server generated
        //              Yes if client generated but automatically server written
        //              No if client generated significantly before server written
        //


        // self, getStateFor(object,TransactionManager)  -> preIdentity, postIdentity, finished
        // if(rollback) what do you do?
        //      refetch from database (nothing if local, but blank out identity?)
        //      restore to before the transaction write
        //


        // if hasIdentity then continue as an update (first update)




 /*
    protected KeyedCollection slotValuesFor(MappedObject object) {
        return (KeyedCollection) valuesExtractor.valueWith(object);
    }

    protected void initializeObject_with(MappedObject object, KeyedArray slotValues) {
        objectInitializer.executeWith_with(object,slotValues);
    }

    protected MappedObject newObjectForStub() {
        return (MappedObject) creatorFunction.value();
    }

    protected MappedObject newObjectForReplicate() {
        return (MappedObject) creatorFunction.value();
    }

    protected boolean hasIdentity(MappedObject object) {
        return !isNewObjectPredicate.isTrueWith(object);
    }
*/

    //**********************************************************
    //**********************************************************
    //**********************************************************
   /*valueWith(...)
    CreationFunction [^MappedObject, CreationInfo ci | ...]
    valueWith()

    */
    //**************************************
    //(P)******** Object Binding ***********
    //**************************************

    public void setupBasicConstructor(Function0Arg constructor) {
        this.creatorFunction = constructor;
    }

    public void setupObjectInitializer(Procedure2Arg objectInitializer) {
        this.objectInitializer = objectInitializer;
        this.usesSetters = false;
    }

    public void setupValuesExtractor(Function1Arg valuesExtractor) {
        this.valuesExtractor = valuesExtractor;
        this.usesGetters = false;
    }

    public void setupIsNewObject(Predicate1Arg isNewObjectPredicate) {
        this.isNewObjectPredicate = isNewObjectPredicate;
    }

    public void setupPostInitialization(Procedure1Arg postInitializationProcedure) {
        this.postInitializationFunction = postInitializationProcedure;
    }


    //**************************************

    public void setupUseObjectInitializer(Procedure2Arg objectInitializer) {
        this.objectInitializer = objectInitializer;
        this.usesSetters = false;
    }

    public void setupUseValuesExtractor(Function1Arg valuesExtractor) {
        this.valuesExtractor = valuesExtractor;
        this.usesGetters = false;
    }

    public void setupIsNewObjectPredicate(Predicate1Arg isNewObjectPredicate) {
        this.isNewObjectPredicate = isNewObjectPredicate;
    }

    public void setupPostInitializationFunction(Procedure1Arg postInitializationFunction) {
        this.postInitializationFunction = postInitializationFunction;
    }

/*
    protected void setIdentityFor_to(MappedObject object, Object identityKey) {
        identityKeySlot.setObject_usingSlotValue(object,identityKey);
    }

    protected Object getIdentityKeyFromObject(MappedObject object) {
        return identityKeySlot.newSlotValueFromObject(object);
    }
*/

    //**********************************************************
    //**********************************************************
    //**********************************************************

/*
    protected void buildRowInto_fromObject(Row row, Object object) {
        if (this.usesGetters) {
            for (Enumeration enum = slots.elements(); enum.hasMoreElements();) {
                SetterSlotPi eachSlot = (SetterSlotPi) enum.nextElement();
                if (eachSlot.isRowWriter()) {   // identityWriter vs. updateWriter
                    eachSlot.setRow_usingObject(row, object);
                };
            };
        } else { // We use an initialization method
            KeyedCollection   slotValues = slotValuesFor(object);
if (ts != null && traceLevel > 0) {ts.print("Build Row From slots: ");};
            int maxRead = slots.size();
            for (int i = 0; i < maxRead; i++) {
                SlotPi eachSlot = (SlotPi) slots.atIndex(i);
        		Object slotValue = slotValues.atKey(eachSlot.name());
                if (eachSlot.isRowWriter()) {
if (ts != null && traceLevel > 0) {ts.print("["+i+":"+eachSlot.name()+"]="+slotValue+" ");};
                    eachSlot.setRow_usingSlotValue(row, slotValue);
                };
            };
if (ts != null && traceLevel > 0) {ts.println("done:"+row);};
        };
    }

    protected void buildUpdateRowInto_fromObject(Row row, Object object) {
        if (this.usesGetters) {
            for (Enumeration enum = slots.elements(); enum.hasMoreElements();) {
                SetterSlotPi eachSlot = (SetterSlotPi) enum.nextElement();
                if ((eachSlot != identityKeySlot) && (eachSlot.isRowWriter())) {
                    eachSlot.setRow_usingObject(row, object);
                };
            };
        } else { // We use an initialization method
            KeyedCollection   slotValues = slotValuesFor(object);
if (ts != null && traceLevel > 0) {ts.print("Build Row From slots: ");};
            int maxRead = slots.size();
            for (int i = 0; i < maxRead; i++) {
                SlotPi eachSlot = (SlotPi) slots.atIndex(i);
        		Object slotValue = slotValues.atKey(eachSlot.name());
                if ((eachSlot != identityKeySlot) && eachSlot.isRowWriter()) {
if (ts != null && traceLevel > 0) {ts.print("["+i+":"+eachSlot.name()+"]="+slotValue+" ");};
                    eachSlot.setRow_usingSlotValue(row, slotValue);
                };
            };
if (ts != null && traceLevel > 0) {ts.println("done:"+row);};
        };
    }

        // This should not be needed if the isRowWriter status is set correctly for the identitySlot
        // Probably no harm in forcing it though
    protected void buildPreIdentityRowInto_fromObject(Row row, Object object) {
        if (this.usesGetters) {
            for (Enumeration enum = slots.elements(); enum.hasMoreElements();) {
                SetterSlotPi eachSlot = (SetterSlotPi) enum.nextElement();
                if ((eachSlot != identityKeySlot) && eachSlot.isRowWriter() && !eachSlot.isAssociation()) {
                    eachSlot.setRow_usingObject(row, object);
                };
            };
        } else { // We use an initialization method
            KeyedCollection   slotValues = slotValuesFor(object);
if (ts != null && traceLevel > 0) {ts.print("Build PreIdentity Row From slots: ");};
            int maxRead = slots.size();
            for (int i = 0; i < maxRead; i++) {
                SlotPi eachSlot = (SlotPi) slots.atIndex(i);
        		Object slotValue = slotValues.atKey(eachSlot.name());
                if ((eachSlot != identityKeySlot) && eachSlot.isRowWriter() && !eachSlot.isAssociation()) {
if (ts != null && traceLevel > 0) {ts.print("["+i+":"+eachSlot.name()+"]="+slotValue+" ");};
                    eachSlot.setRow_usingSlotValue(row, slotValue);
                };
            };
if (ts != null && traceLevel > 0) {ts.println("done:"+row);};
        };
    }

    protected void buildIdentityRowInto_fromObject(Row row, Object object) {
        if (this.usesGetters) {
            for (Enumeration enum = slots.elements(); enum.hasMoreElements();) {
                SetterSlotPi eachSlot = (SetterSlotPi) enum.nextElement();
                if (eachSlot.isRowWriter() && !eachSlot.isAssociation()) {
                    eachSlot.setRow_usingObject(row, object);
                };
            };
        } else { // We use an initialization method
            KeyedCollection   slotValues = slotValuesFor(object);
if (ts != null && traceLevel > 0) {ts.print("Build Identity Row From slots: ");};
            int maxRead = slots.size();
            for (int i = 0; i < maxRead; i++) {
                SlotPi eachSlot = (SlotPi) slots.atIndex(i);
        		Object slotValue = slotValues.atKey(eachSlot.name());
                if (eachSlot.isRowWriter() && !eachSlot.isAssociation()) {
if (ts != null && traceLevel > 0) {ts.print("["+i+":"+eachSlot.name()+"]="+slotValue+" ");};
                    eachSlot.setRow_usingSlotValue(row, slotValue);
                };
            };
if (ts != null && traceLevel > 0) {ts.println("done:"+row);};
        };
    }
*/

};
