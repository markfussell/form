/*======================================================================
**
**  File: chimu/form/mapping/CollectiveObjectRetrieverC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.query.*;
import com.chimu.form.database.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;
import com.chimu.kernel.exceptions.*;

import java.sql.*;

import java.util.Enumeration;

import java.io.PrintWriter;


/*package*/ class CollectiveObjectRetrieverC implements CollectiveObjectRetriever, ObjectRetrieverPi {

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ CollectiveObjectRetrieverC() {
    }

    /*package*/ CollectiveObjectRetrieverC(String name) {
        this.name = name;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

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


    public void addDistinguishingMapper(DistinguishingObjectMapper mapper) {
        retrievers.add(mapper);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected Array retrievers() {
        return retrievers;
    }


    protected void useAllSlotsFromRetriever(ObjectRetriever retriever){
        Array retrieverSlots = retriever.slots();
        int size = retrieverSlots.size();
        for (int i = 0; i < size; i++) {
            SlotPi retrieverSlot = (SlotPi) retrieverSlots.atIndex(i);
            if (!slotNames.includesKey(retrieverSlot.name())) {
                newSlot(retrieverSlot);
            }
        }
    }

    protected ObjectRetrieverPi retrieverAtIndex(int i) {
        return (ObjectRetrieverPi) retrievers.atIndex(i);
    }


    protected SlotPi newSlot(SlotPi retrieverSlot) {
        SlotPi selectiveSlot = new SelectiveSlotC(this,retrieverSlot);
        slots.add(selectiveSlot);
        slotNames.atKey_put(selectiveSlot.name(),new Integer(slots.size()-1));
        return selectiveSlot;
    }

    protected ObjectRetrieverPi aRetriever() {
        return retrieverAtIndex(0);
    }

    //**********************************************************
    //(P)******************* Done Setup ************************
    //**********************************************************

        /**
         * Prepare the retriever to be ready for use
         * This goes through all the inserted ObjectRetrievers
         * and takes their
         */
    public void doneSetup() {
        int size = retrievers.size();
        if (size == 0) throw new ConfigurationException("Collective Retriever "+name()+" has no members ");
        DistinguishingObjectMapperPi aRetriever = (DistinguishingObjectMapperPi) aRetriever();

        table             = (TableSi) aRetriever.table();
        identityKeySlot   = newSlot(aRetriever.identityKeySlot());
        distinguisherSlot = newSlot(aRetriever.distinguishingSlot());

        String distinguisherSlotName = distinguisherSlot.name();

        for (int i=0; i< size; i++) {
            DistinguishingObjectMapperPi mapper = (DistinguishingObjectMapperPi) retrievers.atIndex(i);
            String mapperDistinguishingSlotName = mapper.distinguishingSlot().name();
            if ( !(distinguisherSlotName.equals(mapperDistinguishingSlotName)) ) {
                throw new ConfigurationException("Mapper "+mapper.name()+" uses a distinguishing slot name "+mapperDistinguishingSlotName+" which is different from "+distinguisherSlotName);
            };
            Object distinguishingValue = mapper.distinguishingValue();
            if (distinguisherToRetriever.includesKey(distinguishingValue)) {
                throw new ConfigurationException("Already have a distinguishingValue of "+distinguishingValue);
            };
            distinguisherToRetriever.atKey_put(distinguishingValue,mapper);
            useAllSlotsFromRetriever(mapper);
        }
    }

        /**
         * Check whether the setup was done properly.
         * Whether the client has set values that we know
         * have to be set
         */
    protected void validateSetup() {

    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public String name() {
        return name;
    }

    public Table table() {
        return table;
    }


    public Slot slotNamed(String slotName) {
        Number indexHolder = (Number) slotNames.atKey(slotName);
        if (indexHolder == null) return null;
        return (Slot) slots.atIndex(indexHolder.intValue());
    };


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
        return this.newObjectFromRow(row);
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
        Object distinguisher;

        if (isDistinguishedKey(identityKey)) {
            distinguisher = distinguisherFromIdentityKey(identityKey);
        } else {
            //have to do a database hit:
            distinguisher = retrieveDistinguisherFor(identityKey);
            if (distinguisher == null) {
                throw new MappingException("Couldn't find a distinguisher for the identityKey:"+identityKey);
            };
        };
        ObjectRetrieverPi objectRetriever = retrieverForDistinguisher(distinguisher);
        if (objectRetriever == null) {
            throw new MappingException("Couldn't find a retriever for the identityKey:"+identityKey);
        };

        return objectRetriever.findIdentityKey(identityKey);
    }

    /*package*/ public MappedObject newStubObject(Object identityKey) {
        if (identityKey == null) return null;
        Object distinguisher;

        if (isDistinguishedKey(identityKey)) {
            distinguisher = distinguisherFromIdentityKey(identityKey);
        } else {
            //have to do a database hit:
            distinguisher = retrieveDistinguisherFor(identityKey);
            if (distinguisher == null) {
                throw new MappingException("Couldn't find a distinguisher for the identityKey:"+identityKey);
            };
        };
        ObjectRetrieverPi objectRetriever = retrieverForDistinguisher(distinguisher);
        if (objectRetriever == null) {
            throw new MappingException("Couldn't find a retriever for the identityKey:"+identityKey);
        };

        return objectRetriever.newStubObject(identityKey);
    }


    /*package*/ public Object findWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue) {
        SlotPi slot = (SlotPi) slotNamed(slotName);

        Row row =  table.findWhereColumn_equals((Column) slot.column(), columnValue);
        return this.newObjectFromRow(row);
    }

    public Object findWhereSlotNamed_equals(String slotName, Object value) {
        SlotPi slot = (SlotPi) slotNamed(slotName);
        if (!slot.hasColumnValue()) {
//            throw new NotImplementedYetException("Can't do a simple search on a slot without a column, use the Query subsystem");
            QueryDescription query = QueryPackSi.newQueryDescription();
            QueryVar ev = query.newExtentVar(this);
            query.and_equalsConstant(ev.slotNamed(slotName),value);
            return query.findAny();
        };
        Object columnValue = slot.encode(value);

        Row row =  table.findWhereColumn_equals((Column) slot.column(), columnValue);
        return this.newObjectFromRow(row);
    };

    /*package*/ public Array selectWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue) {
        SlotPi slot = (SlotPi) slotNamed(slotName);

        Array rows = table.selectWhereColumn_equals((Column) slot.column(), columnValue);
        return this.newObjectsFromRows(rows);
    }


    public Array selectWhereSlotNamed_equals(String slotName, Object value) {
        SlotPi slot = (SlotPi) slotNamed(slotName);
        if (!slot.hasColumnValue()) {
//            throw new NotImplementedYetException("Can't do a simple search on a slot without a column, use the Query subsystem");
            QueryDescription query = QueryPackSi.newQueryDescription();
            QueryVar ev = query.newExtentVar(this);
            query.and_equalsConstant(ev.slotNamed(slotName),value);
            return query.selectAll();
        };
        Object columnValue = slot.encode(value);

        Array rows = table.selectWhereColumn_equals((Column) slot.column(), columnValue);
        return this.newObjectsFromRows(rows);
    }


    protected boolean isDistinguishedKey(Object identityKey) {
        return false;
    }

    protected Object distinguisherFromIdentityKey(Object identityKey) {
        return null;
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

    public int countWhereQuery(QueryDescription queryD) {
        return table.countWhereSqlString(queryStringFor(queryD));
    }


    protected String queryStringFor(QueryDescription queryD) {
        QueryVar resultsVar = queryD.resultsVar();
        if (resultsVar == null) {
            QueryVar qv = queryD.aQueryVarForRetriever(this);
            if (qv == null) {
                throw new ExecutionException("This retriever can't use this query, it does have any variables from our extent?");
            };
            queryD.setResultsVar(qv);  // just pick one of the query variables...
        } else {
            if (resultsVar.valuesRetriever() != this) {
                throw new ExecutionException("This retriever can't use this query, it is not the expected results retriever");
            };
        };

        SqlBuilder sqlB = aSqlBuilder();
        queryD.putSqlInto(sqlB);
if (ts != null) {ts.println(sqlB);};
        return sqlB.sqlString();
    }

    public Array selectWhereQuery(QueryDescription queryD) {
        Array rows = table.selectWhereSqlString(queryStringFor(queryD));
        return this.newObjectsFromRows(rows);
    }

    public Array selectForSlot_whereQuery(Slot resultSlot, QueryDescription queryD) {
        Array rows = table.selectColumn_whereSqlString(((Column) ((SlotPi) resultSlot).dbColumn()),slotQueryStringFor(queryD));
        return this.newValuesForSlot_fromRows((SlotPi) resultSlot, rows);
    };

    public Object findForSlot_whereQuery(Slot resultSlot, QueryDescription queryD) {
        Array rows = table.selectColumn_whereSqlString(((Column) ((SlotPi) resultSlot).dbColumn()),slotQueryStringFor(queryD));
        return this.newValueForSlot_fromRows((SlotPi) resultSlot, rows);
    };


    protected String slotQueryStringFor(QueryDescription queryD) {
        SqlBuilder sqlB = aSqlBuilder();
        queryD.putSqlInto(sqlB);
if (ts != null) {ts.println(sqlB);};
        return sqlB.sqlString();
    };


    //**************************************
    //(P)************ Support **************
    //**************************************

        //This is obsolete??
    /*subsystem(Query)*/ public String querySelectStringPrefixedWith(String queryVarName) {
        return table.querySelectStringPrefixedWith(queryVarName);
    }

    protected SqlBuilder aSqlBuilder() {
        return table.newSqlBuilder();
    };



    //**********************************************************
    //(P)****************      PACKAGE      ********************
    //**********************************************************

    //package + Query
    /*subsystem*/ public String tableName() {
        return table.name();
    };

    /*package*/ public String identityKeyColumnName() {
        return identityKeySlot.columnName();
    };

    /*package*/ public Column identityKeyColumn() {
        return identityKeySlot.dbColumn();
    };



    //**********************************************************
    //(P)************* Row to Object conversions ***************
    //**********************************************************

    /*package*/ public MappedObject newObjectFromRow(Row row) {
        if (row == null) return null; //null indicates that this row does not generate an object for this mapper
        // if useIdenticalRows (whether in a single table or via a multi-table join)
        ObjectRetrieverPi retriever = retrieverForRow(row);
        if (retriever == null) throw new MappingException("No retriever for row:"+row);
        return retriever.newObjectFromRow(row);
        // else return retrieverForRow(row).findIdentityKey(getIdentityKeyFromRow(row))
    };

    protected Array /*of MappedObject*/ newObjectsFromRows(Array rows) {
        if (rows == null) return CollectionsPack.newList();
        int rowCount = rows.size();
        List results = CollectionsPack.newList(rowCount);

        for (int i=0; i <  rowCount; ++i) {
            Row row = (Row) rows.atIndex(i);
            MappedObject object =  newObjectFromRow(row);
            if (object != null) {
                results.add(object);
            };
        };

        return results;
    };

    protected Array /*of SlotValue Objects*/ newValuesForSlot_fromRows(SlotPi slot, Array rows) {
if (ts != null && traceLevel > 1) {ts.print("Build SlotValue for "+slot+" from Rows: "+rows);};
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
    };

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
    //(P)****************      PRIVATE      ********************
    //**********************************************************



    //**********************************************************
    //(P)*************  Row to slot conversions  ***************
    //**********************************************************

    protected Object getIdentityKeyFromRow(Row row) {
        return identityKeySlot.newSlotValueFromRow(row);
    };

    protected Object getDistinguisherFromRow(Row row) {
        return distinguisherSlot.newSlotValueFromRow(row);
    };

    //**********************************************************
    //**********************************************************
    //**********************************************************

        //Complex: Iterate through each retriever and ask whether the row belongs
        //to it.
    protected ObjectRetrieverPi retrieverForRow(Row row) {
        Object distinguisher = getDistinguisherFromRow(row);
        if (distinguisher == null) {
            throw new MappingException("Couldn't find a distinguisher for the row:"+row);
        };
        return (ObjectRetrieverPi) distinguisherToRetriever.atKey(distinguisher);
    }

    //**********************************************************
    //(P)******************** Retrieving ***********************
    //**********************************************************

    /*package*/ public Object retrieveDistinguisherFor(Object identityKey) {
        Row row = table.findPrimaryKey(identityKey);
        if (row == null) return null;

        return getDistinguisherFromRow(row);
    };

    //**********************************************************
    //(P)********************* Utilities ***********************
    //**********************************************************


    protected ObjectRetrieverPi retrieverForDistinguisher(Object distinguisher) {
        return (ObjectRetrieverPi) distinguisherToRetriever.atKey(distinguisher);
    }

        //Only applicable if valuesExtractor is not nill (i.e. we are not using getters)
/*
    protected KeyedCollection slotValuesFor(Object object) {
        return (KeyedCollection) valuesExtractor.valueWith(object);
    };
*/
    public Array slots() {
        return slots;
    }

    public Array selectColumns() {
        return table.basicColumns();
    }


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String          name;

    protected TableSi         table;

    protected Map             distinguisherToRetriever = CollectionsPack.newMap();
    protected List        retrievers = CollectionsPack.newList();

    protected Map             slotNames = CollectionsPack.newMap();        // The following are cached information
    protected List        slots = CollectionsPack.newList(); // = new Dynarray();
    protected SlotPi          identityKeySlot;
    protected SlotPi          distinguisherSlot;

    protected PrintWriter ts = null;
    protected int traceLevel = 1;     //0 = minimal tracing, 1= normal (and slowing), 2=detailed


    //**********************************************************
    //**********************************************************
    //**********************************************************
    //**********************************************************
    //**********************************************************
    //**********************************************************


    //**********************************************************
    //(P)******************** Searching ************************
    //**********************************************************

        /**
         * Take a partially formed query (no select statement) and select all the objects
         * that match the string
         *@access Friend(AssociationRetriever, AssociationSlot)
         */
    /*package*/ public Object findWhereQString_hasColumnValue(String qstring, Object columnValue)
            throws SQLException {

        Row row = table.findWhereSqlString_hasValue(qstring,columnValue);
        return this.newObjectFromRow(row);
    };

        /**
         * Take a partially formed query (no select statement) and select all the objects
         * that match the string
         *@access Friend(AssociationRetriever, AssociationSlot)
         */
    /*package*/ public Array selectWhereQString_hasColumnValue(String qstring, Object columnValue)
            throws SQLException {

        Array rows = table.selectWhereSqlString_hasValue(qstring,columnValue);
        return  this.newObjectsFromRows(rows);
    };

        /**
         *@access Friend(AssociationRetriever, AssociationSlot)
         */
    /*package*/ public Array selectWhereQString_hasColumnValues(String qstring, Array columnValues)
            throws SQLException {

        Array rows = table.selectWhereSqlString_hasValues(qstring,columnValues);
        return  this.newObjectsFromRows(rows);
    };

}


//TargetSlot
//Pair of an ObjectMapper (or ObjectRetriever)

