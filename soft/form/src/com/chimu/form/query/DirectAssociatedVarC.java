/*======================================================================
**
**  File: chimu/form/query/DirectAssociatedVarC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;
import com.chimu.form.database.Column;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;
import java.util.Enumeration;

/**
DirectAssociatedVars are for SlotValues that produce a terminal slot value
**/
class DirectAssociatedVarC
                extends AssociatedVarAbsC {

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    public DirectAssociatedVarC (QueryVarPi qv, SlotSi slot) {
        super(qv);
        this.slot = slot;
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public Slot associatedThrough() {return slot;}

    public ObjectRetriever sourceRetriever() {
        return qv.valuesRetriever();
    }

    public ObjectRetriever valuesRetriever() {
        return null;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Array selectAll() {
        return ((ObjectRetrieverSi) sourceRetriever()).selectForSlot_whereQuery(slot, queryDescription());
    }

    public Object findAny() {
        return ((ObjectRetrieverSi) sourceRetriever()).findForSlot_whereQuery(slot, queryDescription());
    }

    public int countAll() {
        return ((ObjectRetriever) sourceRetriever()).countWhereQuery(queryDescription());
    }

    //**********************************************************
    //(P)***************** QueryGeneration *********************
    //**********************************************************


    protected Column hereColumn() {
        return slot.column();
    }

    protected String hereVarName() {
        return qv.name();
    }


    //**************************************
    //**************************************
    //**************************************

    public void putObjectIdentityColumnInto(SqlBuilder sqlB) {
        sqlB.addSelectColumn_varName(
                    hereColumn(),hereVarName()
                );
    }

    public void putSelectColumnsInto(SqlBuilder sqlB) {
        sqlB.addSelectColumn_varName(
                    hereColumn(),hereVarName()
                );
    }

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.addColumnAsExpression_varName(hereColumn(),hereVarName());
    }

    public void addNeededExtentsTo(SqlBuilder sqlB) {
    }

    public void addNeededJoinsTo(SqlBuilder sqlB) {
    }


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    SlotSi   slot = null;
}
