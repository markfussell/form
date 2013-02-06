/*======================================================================
**
**  File: chimu/form/mapping/DistinguishingObjectMapperC.java
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



/*package*/ class DistinguishingObjectMapperC
                    extends ObjectMapperC
                    implements DistinguishingObjectMapperPi,DistinguishedObjectMapper {
    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ DistinguishingObjectMapperC() {};

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************


    //**************************************
    //(P)****** Distinguishing Slot ********
    //**************************************

    public Slot newDistinguishingSlot(
            String    slotName,
            String    mappedColumnName,
            final Object    distinguishingValue
            ) {
        this.distinguishingValue = distinguishingValue;
//        Function1Arg encoder = [object | return distinguishingValue;];
        SlotPi slot = (SlotPi) newConstantSlot(slotName, mappedColumnName, distinguishingValue);
            // slot.setupDisableObjectInitializing(); //No reason for us to determine if they want the value
        distinguishingSlot = slot;
        return slot;
    }

    //**********************************************************
    //(P)******************* Done Setup ************************
    //**********************************************************

    public Array selectAll() {
        Object columnValue = encodeValue_forSlot(distinguishingValue,distinguishingSlot);

        Array rows = table.selectWhereColumn_equals((Column) distinguishingSlot.column(), columnValue);
        return this.newObjectsFromRows(rows);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Object findAny() {
        Array rows = table.selectAll();
        return newFirstObjectFromRows(rows);
    }

    /*package*/ public Object findWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue) {
        SlotPi slot = (SlotPi) slotNamed(slotName);

        Array rows =  table.selectWhereColumn_equals((Column) slot.column(), columnValue);
        return newFirstObjectFromRows(rows);
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

        Array rows =  table.selectWhereColumn_equals((Column) slot.column(), columnValue);
        return newFirstObjectFromRows(rows);
    }

    public Object findWhereQuery(QueryDescription queryD) {// String slotName, Object value) {
            //Use our slots as values? What are the values?  Has to be our slots? no, it doesn't matter
            //it just matters the the select clause matches one of the ExtentVariables

        Array rows =  table.selectWhereSqlString(queryStringFor(queryD));
        return newFirstObjectFromRows(rows);
    }

    public int countWhereQuery(QueryDescription queryD) {
        return ((List) selectWhereQuery(queryD)).size();
    }

    //**********************************************************
    //(P)************* Row to Object conversions ***************
    //**********************************************************

    /*package(SelectiveObjectRetriever)*/ public MappedObject newObjectFromRow(Row row) {
        if (row == null) return null; //throw exception...no, now allow null to indicate not an object for this row.

        Object distinguisher = getDistinguisherFromRow(row);
if ((ts != null) && (traceLevel > 1)) {ts.println("Distinguisher: "+distinguisher+"="+distinguishingValue);};
        if (!distinguisher.equals(distinguishingValue)) return null;

        Object identityKey = getIdentityKeyFromRow(row);
        MappedObject object;

        if (objectCache.includesKey(identityKey)){
            object = (MappedObject) objectCache.atKey(identityKey);
        } else {
            object = this.newObjectForReplicate();
            objectCache.atKey_put(identityKey,object);
            this.buildObjectInto_fromRow(object,row);
        };
        return object;
    }

    protected MappedObject newFirstObjectFromRows(Array rows) {
if (ts != null && traceLevel > 1) {ts.print("FORM: Build Objects from Rows: "+rows);};
        if (rows == null) return null;
        int rowCount = rows.size();

        for (int i=0; i <  rowCount; ++i) {
            Row row = (Row) rows.atIndex(i);
            MappedObject object = newObjectFromRow(row);
            if (object != null) {
                return object;
            };
        };
        return null;
    }


    protected Object getDistinguisherFromRow(Row row) {
        return distinguishingSlot.newSlotValueFromRow(row);
    }

    /*package*/ public SlotPi distinguishingSlot() {
        return distinguishingSlot;
    }

    /*package*/ public Object distinguishingValue() {
        return distinguishingValue;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************


    //parent table is really just a view.
    protected TableSi   realTable            = null; //
    protected SlotPi    distinguishingSlot;
    protected Object    distinguishingValue;

}

