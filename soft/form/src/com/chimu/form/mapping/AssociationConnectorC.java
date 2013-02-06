/*======================================================================
**
**  File: chimu/form/mapping/AssociationConnectorC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.query.*;
import com.chimu.form.database.*;

import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;


import java.util.Enumeration;

import java.sql.*;
import java.io.PrintWriter;


/**
This class is responsible for managing the rows that define an association
and collaborating with the slots to retrieve the object on the other side of the
association
**/

/*package*/ class AssociationConnectorC
                        implements  AssociationConnectorPi,
                                    AssociationMapper       /*for backward compatibility*/
                        {

    //**********************************************************
    //(P)**************** Not Implemented Yet ******************
    //**********************************************************


    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ AssociationConnectorC() {}

    //**********************************************************
    //********************* Initializers ***********************
    //**********************************************************

    /*package*/ AssociationConnectorC initTable(Table table) {
        this.table = (TableSi) table;
        this.name  = table.name();
        return this;
    }

    /*package*/ AssociationConnectorC initName_table(String name, Table table) {
        this.table = (TableSi) table;
        this.name  = name;
        return this;
    }

    public void setupTraceStream (PrintWriter traceStream) {
        setupTraceStream_traceLevel(traceStream, 1);
    }

    public void setupTraceStream_traceLevel(PrintWriter traceStream, int traceLevel) {
        this.ts = traceStream;
        this.traceLevel = traceLevel;
    }


    //(P) ***************** Public Setup ************************


    //**********************************************************
    //(P)******************* Slot Setup ************************
    //**********************************************************


    public AssociationConnectorSlot newConnectorSlot_column_retriever_retrieverSlot(
            String slotName,
            String  mappedColumnName,
            ObjectRetriever associationRetriever, String retrieverExternalSlotName) {
        return newAssociationSlot(slotName, table.newColumnNamed(mappedColumnName),associationRetriever,retrieverExternalSlotName);
    }

    public AssociationConnectorSlot newConnectorSlot_column(
            String slotName,
            String  mappedColumnName) {
        return newAssociationSlot(slotName, table.newColumnNamed(mappedColumnName));
    }

    public AssociationConnectorSlot newAssociationSlot(
            String slotName,
            String  mappedColumnName) {
        return newAssociationSlot(slotName, table.newColumnNamed(mappedColumnName));
    }

    protected AssociationConnectorSlot newAssociationSlot(
            String slotName,

            Column               mappedColumn,
            ObjectRetriever partnerRetriever, String partnerSlotName) {
        AssociationConnectorSlot slot =
            new AssociationConnectorSlotC(slotName, this, mappedColumn, (ObjectRetrieverPi) partnerRetriever, partnerSlotName);
        this.addSlot(slot);
        return slot;
    }

    public AssociationConnectorSlot newAssociationSlot(
            String slotName,

            Column               mappedColumn
            ) {
        AssociationConnectorSlot slot =
            new AssociationConnectorSlotC(slotName, this, mappedColumn);
        this.addSlot(slot);
        return slot;
    }

//--Distinguished--
//Switch,Parameterized,Abstract, Derived, switched,
//Derived,deferred, generic, dependent, selected,conditional

    public AssociationConnectorSlot newSelectedAssociationSlot(
            String slotName,

            Function1Arg    selectionDecoder,
            Function1Arg    selectionEncoder,
            Column          selectionColumn,


            ObjectMapperC[]  partnerRetrievers,
            Column          keyColumn
            ) {
        throw new NotImplementedYetException();
        /*
        ExtForwardAssociationSlotC slot =
            new ExtForwardAssociationSlotC(slotName,partnerRetriever,mappedColumn);
        this.addSlot(slot);
        return slot;
        */
    };

    /*package*/ public void setupRegisterExternalSlot(ExternalAssociationSlotPi exSlot, String associationSlotName) {
        List registeredExternalSlots = (List) slotToRegisteredExternalSlots.atKey(associationSlotName);
        if (registeredExternalSlots == null) {
            registeredExternalSlots = CollectionsPack.newList();
            slotToRegisteredExternalSlots.atKey_put(associationSlotName, registeredExternalSlots);
        };
        registeredExternalSlots.add(exSlot);

        //AssociationConnectorSlot slot = slotNamed(associationSlotName);
        //slot.setupRegisterPartner_slotName((ObjectRetrieverPi) exSlot.mapper(), exSlot.name());
    }

    protected AssociationConnectorSlot slotNamed(String slotName) {
        int slotNumber = ((Integer) slotNames.atKey(slotName)).intValue();
        return slotAtIndex(slotNumber);
    }

    // Setup == Initialization and configuration of an object before first use

    //**********************************************************
    //(P)****************** Public Setup ***********************
    //**********************************************************


    //**********************************************************
    //(P)******************* Done Setup ************************
    //**********************************************************

        /**
         *  Set the state of the mapper to be ready for use
         */
    public void doneSetup() {
        if (setupMode >= 2) return;
        validateSetup();
        try {
            table.doneSetup();
        } catch (Exception e) {
            System.out.println(table);
            throw new ConfigurationException("Bad table",e);
        };

        setupMode = 2;
    }

    protected void configTable() {
        Column pk = table.columnNamed("primaryKey");
        if (pk == null) {
            List columnNames = CollectionsPack.newList();
            columnNames.add(firstSlot().associationColumn().name());
            columnNames.add(secondSlot().associationColumn().name());
            pk = table.newCompoundColumnNamed("primaryKey",columnNames);
        }
        table.setPrimaryKeyColumn(pk);
    }

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
        configTable();               //This is a "prepareForValidation" method placed here temporarily
        Enumeration enum = slotToRegisteredExternalSlots.keys();
        while (enum.hasMoreElements()) {
            String associationSlotName = (String) enum.nextElement();
            AssociationConnectorSlot slot = slotNamed(associationSlotName);
            if (slot.partnerRetriever() == null) {
                List registeredExternalSlots = (List) slotToRegisteredExternalSlots.atKey(associationSlotName);
                int size = registeredExternalSlots.size();
                if (size > 1) {
                    throw new ConfigurationException("Multiple partners for associationSlot "+associationSlotName+" need to declare one to be the main retriever partner");
                };
                for (int i = 0; i<size; i++) {
                    ExternalAssociationSlotPi exSlot = (ExternalAssociationSlotPi) registeredExternalSlots.atIndex(i);
                    slot.setupRegisterPartner_slotName((ObjectRetrieverPi) exSlot.mapper(), exSlot.name());
                }
            }
        }
        if (table == null) throw new ConfigurationException("No table for mapper "+name());
        if (slots.size() < 1) throw new ConfigurationException("No slots in mapper "+validationName());
        table.validateSetup();

        int size = slots.size();
        for (int i = 0; i < size; i++) {
            String columnName = slotAtIndex(i).associationColumn().name();
            if (table.columnNamed(columnName) == null) throw new ConfigurationException(
                    "Slot "+slotAtIndex(i).name()+" needs a column named "+columnName+
                    " which is not declared for table "+tableName());
        };
        setupMode = 1;
    }

    public void crossValidate() {
        if (setupMode >= 3) return;
        if (setupMode < 2) throw new ConfigurationException("AssociationConnector "+validationName()+" was not setup before calling cross validation");
        int size = slots.size();

        boolean failed = false;
        for (int i = 0; i < size; i++) {
    	    AssociationConnectorSlot slot = slotAtIndex(i);
            try {
                slot.crossValidate();
            } catch (Exception e) {
                failed = true;
                System.err.println("Cross validation failed for slot "+slot.name()+" because: "+e);
                // if (isTracing()) e.printStackTrace();
            }
        };
        if (failed) throw new ConfigurationException("Some slots for mapper "+validationName()+" were invalid");
        setupMode = 3;
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public String toString() {
        return validationName();
    }


    public AssociationConnectorSlot associationSlotNamed(String slotName) {
        Number indexHolder = (Number) slotNames.atKey(slotName);
        if (indexHolder == null) throw new MappingException("No slot named: "+slotName+" for mapper "+tableName());
        return slotAtIndex(indexHolder.intValue());
    };

    public Table table() {
        return table;
    }

    //**********************************************************
    //*********************** Retrieving ***********************
    //**********************************************************

    //findPartnerSlotName_whereSlotNamed_equalsColumnValue

    public Object findWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue)
            throws SQLException {
        QueryDescription query = createQueryDescriptinWhereSlotNamed_equalsColumnValue(slotName, columnValue);
        return query.findAny();
    };



    protected QueryDescription createQueryDescriptinWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue) {
        int sourceSlotNumber = ((Integer) slotNames.atKey(slotName)).intValue();
        int targetSlotNumber = (sourceSlotNumber == FIRST_SLOT) ? SECOND_SLOT : FIRST_SLOT;
        AssociationConnectorSlot sourceSlot = slotAtIndex(sourceSlotNumber);
        AssociationConnectorSlot targetSlot = slotAtIndex(targetSlotNumber);

        String targetToSourceSlotName = targetSlot.partnerSlotName();

        QueryDescription query      = QueryPackSi.newQueryDescription();
        QueryVar         resultsVar = query.newExtentVar(targetSlot.partnerRetriever());
        QueryVar         sourceVar  = resultsVar.slotNamed(targetToSourceSlotName);
        query.and_equalsConstant(sourceVar,columnValue);
        query.setResultsVar(resultsVar);
        return query;
    }

    public Object selectWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue)
            throws SQLException {
        QueryDescription query = createQueryDescriptinWhereSlotNamed_equalsColumnValue(slotName, columnValue);
        return query.selectAll();
    };

/*
        String partnerTableName = targetSlot.partnerTableName();
        String sqlString = "FROM "+tableName()+","+partnerTableName+
                           " WHERE "+tableName()+"."+targetSlot.associationColumnName()+"="+
                                     partnerTableName+"."+targetSlot.partnerColumnName()+
                           " AND "+tableName()+"."+sourceSlot.associationColumnName()+"=?";
        return sqlString;

        String sqlString = newWhereFromSlot_toSlot_equalsQString(sourceSlot,targetSlot);
        return targetSlot.partnerRetriever().selectWhereQString_hasColumnValue(sqlString,columnValue);
    public Object selectStubsWhereSlotNamed_equals(String slotName, Object value)
            throws SQLException {
         return selectWhereSlotNamed_equals(String slotName, Object value);

         // really want:  selectWhereQString

    };

    protected String newWhereFromSlot_toSlot_equalsQString(AssociationConnectorSlot sourceSlot, AssociationConnectorSlot targetSlot) {
        String partnerTableName = targetSlot.partnerTableName();
        String sqlString = "SELECT "+tableName+"."+targetSlot.associationColumnName()+
                           " FROM "+tableName+
                           " WHERE "+tableName+"."+sourceSlot.associationColumnName()+"=?";
        return sqlString;
    };

    //Need to performance test the query generator and offload some of the above.
    //Atleast now the slots should be able to put the prefix on for themselves
*/

//QueryVar assoc = query.newExtentVar(this);
//QueryVar source = assoc.slot(slot1);
//QueryVar target = assoc.slot(slot2);
//query.setResultVar(target);
//query.and_equals(source,query.newUnboundValue());
//
/*
    protected String newWhereFromSlot_toSlot_equalsQString(AssociationConnectorSlot sourceSlot, AssociationConnectorSlot targetSlot) {
        String partnerTableName = targetSlot.partnerTableName();
        String sqlString = "FROM "+tableName()+","+partnerTableName+
                           " WHERE "+tableName()+"."+targetSlot.associationColumnName()+"="+
                                     partnerTableName+"."+targetSlot.partnerColumnName()+
                           " AND "+tableName()+"."+sourceSlot.associationColumnName()+"=?";
        return sqlString;
    };
*/
    //**********************************************************
    //************************ Deleting ************************
    //**********************************************************

    /*package*/ public void deleteWhereSlotNamed_equals(String slotName, MappedObject value)
            throws SQLException {
        int slotNumber = ((Integer) slotNames.atKey(slotName)).intValue();
        deleteWhereSlot_equals(slotNumber, value);
    };

    /*package*/ public void deleteWhereSlot_equals(int slotNumber, MappedObject slotValue)
            throws SQLException {
        Object columnValue = encodeObject(slotValue);   //Old argument: ,slotAtIndex(slotNumber).partnerRetriever());
        Column column = slotAtIndex(slotNumber).associationColumn();
        table.deleteWhereColumn_equalsColumnValue(column, columnValue);
    };


    //**********************************************************
    //*********************** Updating *************************
    //**********************************************************

    //findPartnerSlotName_whereSlotNamed_equalsColumnValue
    //Can't do this here because don't have all the needed information

    public void updateCollection_for_fromSlotNamed(Collection collection, MappedObject object, String slotName)
            throws SQLException {
if (ts != null) ts.println("FORM: Update Assoc "+collection.size()+" for "+slotName);
        int slotNumber = ((Integer) slotNames.atKey(slotName)).intValue();
        updateCollection_for_fromSlot(collection, object, slotNumber);
    };

    protected void updateCollection_for_fromSlot(Collection collection, MappedObject object, int slotNumber)
            throws SQLException {
        //Force the database to be in the same state as us:
        //First check whether we have touched the association.  If not then don't worry
        if (collection instanceof com.chimu.kernel.protocols.Future) {
            if (((com.chimu.kernel.protocols.Future) collection).hasValue()) {
                deleteWhereSlot_equals(slotNumber,object);
                insertCollection_for_fromSlot(collection, object, slotNumber);
            }
        } else {
            deleteWhereSlot_equals(slotNumber,object);
            insertCollection_for_fromSlot(collection, object, slotNumber);
        }
    };

    /*package*/ public void updateObject_for_fromSlotNamed(MappedObject partnerObject, MappedObject object, String slotName)
            throws SQLException {
        int slotNumber = ((Integer) slotNames.atKey(slotName)).intValue();
        updateObject_for_fromSlot(partnerObject, object, slotNumber);
    };

    protected void updateObject_for_fromSlot(MappedObject partnerObject, MappedObject object, int slotNumber)
            throws SQLException {
        //Force the database to be in the same state as us:
        deleteWhereSlot_equals(slotNumber,object);
        insertObject_for_fromSlot(partnerObject, object, slotNumber);
    };

    //**********************************************************
    //*********************** Inserting ************************
    //**********************************************************

    /*package*/ public Object encode(MappedObject slotValue) {
        if (slotValue == null) return null;
        //alt:  if (slotValue instanceof MappedObject) {
        try {
            return ((ObjectMapperPi) slotValue.form_objectMapper()).encodeObject(slotValue);
        } catch (ClassCastException e) {
            throw new MappingException("Tried to encode object "+slotValue+" but it is not a MappedObject",e);
        };
    }


    /*package*/ public void insertCollection_for_fromSlotNamed(Collection collection, MappedObject object, String slotName)
            throws SQLException {
if (ts != null) ts.println("FORM: Update Assoc "+collection.size()+" for "+slotName);
        int slotNumber = ((Integer) slotNames.atKey(slotName)).intValue();
        insertCollection_for_fromSlot(collection, object, slotNumber);
    };

    protected void insertCollection_for_fromSlot(Collection collection, MappedObject object, int slotNumber)
            throws SQLException {
// create statement
        Enumeration enum = collection.elements();
        List forcedObjects = CollectionsPack.newList();
        Object objectColumnValue = encode(object);
        if (slotNumber == FIRST_SLOT) {
            AssociationConnectorSlot partnerSlot = slotAtIndex(SECOND_SLOT);
            while (enum.hasMoreElements()) {
                MappedObject partnerObject = (MappedObject) enum.nextElement();
                boolean forced = forceServerIdentityOn(partnerObject);
                insertPair(objectColumnValue, encode(partnerObject));
                if (forced) forcedObjects.add(partnerObject);
            };
            int size = forcedObjects.size();
            for (int i=0; i< size; i++) {
                postIdentityInsertUpdate((MappedObject) forcedObjects.atIndex(i));
            }
        } else {
            AssociationConnectorSlot partnerSlot = slotAtIndex(FIRST_SLOT);
            while (enum.hasMoreElements()) {
                MappedObject partnerObject = (MappedObject) enum.nextElement();
                boolean forced = forceServerIdentityOn(partnerObject);
                insertPair(encode(partnerObject),objectColumnValue);
//                if (forced) postIdentityInsertUpdate(partnerObject);
                if (forced) forcedObjects.add(partnerObject);
            };
            int size = forcedObjects.size();
            for (int i=0; i < size; i++) {
                postIdentityInsertUpdate((MappedObject) forcedObjects.atIndex(i));
            }
        };
    };

    /*package*/ public void insertObject_for_fromSlotNamed(MappedObject partnerObject, MappedObject object, String slotName)
            throws SQLException {
        int slotNumber = ((Integer) slotNames.atKey(slotName)).intValue();
        insertObject_for_fromSlot(partnerObject, object, slotNumber);
    };

    protected void insertObject_for_fromSlot(MappedObject partnerObject, MappedObject object, int slotNumber)
            throws SQLException {
        Object objectColumnValue = encode(object);
        if (slotNumber == FIRST_SLOT) {
            AssociationConnectorSlot partnerSlot = slotAtIndex(SECOND_SLOT);
            boolean forced = forceServerIdentityOn(partnerObject);
            insertPair(objectColumnValue, encode(partnerObject));
            if (forced) postIdentityInsertUpdate(partnerObject);
        } else {
            AssociationConnectorSlot partnerSlot = slotAtIndex(FIRST_SLOT);
            boolean forced = forceServerIdentityOn(partnerObject);
            insertPair(encode(partnerObject),objectColumnValue);
            if (forced) postIdentityInsertUpdate(partnerObject);
        };
    }

    protected boolean forceServerIdentityOn(MappedObject domainObject) {
        ObjectMapperPi mapper = (ObjectMapperPi) domainObject.form_objectMapper();
        return mapper.forceServerIdentityOn(domainObject);

    }

    protected void postIdentityInsertUpdate(MappedObject domainObject) {
        ObjectMapperPi mapper = (ObjectMapperPi) domainObject.form_objectMapper();
        mapper.postIdentityInsertUpdateObject(domainObject);

    }

    protected Object encodeObject(MappedObject slotValue) {
        try {
            return ((ObjectMapperPi) slotValue.form_objectMapper()).encodeObject(slotValue);
        } catch (ClassCastException e) {
            throw new MappingException("Tried to encode object "+slotValue+" but it is not a MappedObject",e);
        };
    }

    /*
    protected Object encodeObject_inPartner(Object slotValue, ObjectRetriever partnerRetriever) {
        try {
            MappedObject mo = (MappedObject) slotValue;
            return ((ObjectMapperPi) mo.form_objectMapper()).encodeObject(slotValue);
        } catch (ClassCastException e) {
            try {
                return ((ObjectMapperPi) partnerRetriever).encodeObject(slotValue);
            } catch (ClassCastException e2) {
                throw new MappingException("Tried to encode object "+slotValue+" but it is not a MappedObject and the slot's mapper is not an ObjectMapper",e2);
            };
        };
    }
    */

    protected void insertPair(Object columnValue1, Object columnValue2) throws SQLException {
        Row row = newDbRow();
        buildRowInto_first_second(row, columnValue1, columnValue2);
        table.insertRow(row);
    };
        /*
            Object columnValue1 = encodeObject_inPartner(obj1,slotAtIndex(0).partnerRetriever());
            Object columnValue2 = encodeObject_inPartner(obj2,slotAtIndex(1).partnerRetriever());
        */

    //**********************************************************
    //(P)****************      PACKAGE      ********************
    //**********************************************************

    //package + Query
    public String tableName() {
        return table.name();
    };

    /*friend(AssociationConnectorSlot)*/ public AssociationConnectorSlot otherSlotFor(AssociationConnectorSlot aSlot) {
        if (aSlot == slotAtIndex(FIRST_SLOT)) {
            return slotAtIndex(SECOND_SLOT);
        } else if (aSlot == slotAtIndex(SECOND_SLOT)) {
            return slotAtIndex(FIRST_SLOT);
        } else {
            throw new DevelopmentException("A slot not belonging to the association mapper access otherSlotFor"+aSlot.name());
        }

    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected void buildRowInto_first_second(Row row, Object firstValue, Object secondValue) {
            //Can we really be sure these are column values??
        slotAtIndex(FIRST_SLOT).setRow_usingColumnValue(row, firstValue);
        slotAtIndex(SECOND_SLOT).setRow_usingColumnValue(row, secondValue);
    }

    //**********************************************************
    //*********************** Utilities ************************
    //**********************************************************


    protected Row newDbRow(MappedObject forObject) {
        return table.newDbRow();
    }

    protected Row newDbRow() {
        return table.newDbRow();
    }


    protected KeyedArray newSlotRowKeyedArray(MappedObject forObject) {
        return CollectionsPack.newKeyedArray(slotNames);
    }

    protected final AssociationConnectorSlot slotAtIndex(int i) {
        return (AssociationConnectorSlot) slots.atIndex(i);
    }

    protected final AssociationConnectorSlot firstSlot() {
        return slotAtIndex(FIRST_SLOT);
    }

    protected final AssociationConnectorSlot secondSlot() {
        return slotAtIndex(SECOND_SLOT);
    }

   //**********************************************************
    //**********************************************************
    //**********************************************************

    protected void addSlot(AssociationConnectorSlot slot) {
        slots.add(slot);
        slotNames.atKey_put(slot.name(),new Integer(slots.size()-1));
    };

    public Array slots() {
        return slots;
    }

    public String name() {
        return name;
    }

    //**********************************************************
    //(P)******************** Constants ************************
    //**********************************************************

    protected static final int FIRST_SLOT = 0;
    protected static final int SECOND_SLOT = 1;

    //**********************************************************
    //(P)*************** Instance Variables ********************
    //**********************************************************

    protected TableSi         table;
    protected String          name;

    protected List        slots = CollectionsPack.newList(); //new Vector(); // = new Dynarray();
    protected Map             slotNames = CollectionsPack.newMap(); // The following are cached information

    protected int setupMode = 0;      //0 = not setup, 1 = validatedForSetup, 2 = setup

    protected PrintWriter ts = null;
    protected int traceLevel = 1;     //0 = minimal tracing, 1= normal (and slowing), 2=detailed

    protected Map slotToRegisteredExternalSlots = CollectionsPack.newMap();



//    SetterSlot      identityKeySlot;  // no single identityKeySlot

};

