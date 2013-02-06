/*======================================================================
**
**  File: chimu/form/query/ForwardAssociatedVarC.java
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


class ForwardAssociatedVarC
                extends AssociatedVarAbsC {

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    public ForwardAssociatedVarC (QueryVarPi qv, ForwardAssociationSlotSi slot) {
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

    protected Column thereIdentityColumn() {
        return ((ObjectRetrieverSi) valuesRetriever()).identityKeyColumn();
    }

    protected String thereVarName() {
        return name();
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
         *
         *<P>This is the implementation for most AssociatedVars and ExtentVars
         */
    public void putSelectColumnsInto(SqlBuilder sqlB) {
        sqlB.addSelectColumns_varName(
                    ((ObjectRetrieverSi) valuesRetriever()).selectColumns(),thereVarName()
                );
    }

        /**
         *<P>This is the implementation for most AssociatedVars and ExtentVars.
         */
    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.addColumnAsExpression_varName(hereColumn(),hereVarName());
    }

    public void addNeededJoinsTo(SqlBuilder sqlB) {
        if (this.isObjectNeeded()) {
            sqlB.joinFrom_varName_to_varName(
                    hereColumn(),hereVarName(),
                    thereIdentityColumn(),thereVarName());
        };
    }


    public void addNeededExtentsTo(SqlBuilder sqlB) {
        if (this.isObjectNeeded()) {
            sqlB.addExtent_varName(valuesRetriever().table(),thereVarName());
        };
    }


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    ForwardAssociationSlotSi   slot = null;
};


