/*======================================================================
**
**  File: chimu/form/query/ReverseAssociatedVarC.java
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
import java.util.Enumeration;


class ReverseAssociatedVarC
                extends AssociatedVarAbsC {

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    public ReverseAssociatedVarC (QueryVarPi qv, ReverseAssociationSlotSi slot) {
        super(qv);
        this.slot = slot;
    };


    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public Slot associatedThrough() {return slot;};

    public ObjectRetriever valuesRetriever() {
        return slot.partnerRetriever();
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

    protected Column joinToThereColumn() {
        return partnerSlot().column();
    }

    protected Column thereIdentityColumn() {
        return ((ObjectRetrieverSi) valuesRetriever()).identityKeyColumn();
    }

    protected String thereVarName() {
        return name();
    }

    //**************************************
    //**************************************
    //**************************************

    protected SlotSi partnerSlot() {
        return slot.partnerSlot();
    }

    //**************************************
    //**************************************
    //**************************************

    public void putObjectIdentityColumnInto(SqlBuilder sqlB) {
        sqlB.addSelectColumn_varName(
                thereIdentityColumn(),thereVarName()
            );
    }

       /**
         * Put the columns required to select on this variable into
         * the SqlBuilder.
         */
    public void putSelectColumnsInto(SqlBuilder sqlB) {
        sqlB.addSelectColumns_varName(
                    ((ObjectRetrieverSi) valuesRetriever()).selectColumns(),thereVarName()
                );
    }

        /**
         * We have a different implementation than the default in QueryVarAbsC.
         * This is where our "IDENTITY" is stored.
         */
    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.addColumnAsExpression_varName(joinToThereColumn(),thereVarName());
    }

    public void addNeededJoinsTo(SqlBuilder sqlB) {
        sqlB.joinFrom_varName_to_varName(
                hereColumn(),hereVarName(),
                joinToThereColumn(),thereVarName());
    }

    public void addNeededExtentsTo(SqlBuilder sqlB) {
            //This I have to do no matter what
        sqlB.addExtent_varName(valuesRetriever().table(),thereVarName());
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    ReverseAssociationSlotSi   slot = null;
}

