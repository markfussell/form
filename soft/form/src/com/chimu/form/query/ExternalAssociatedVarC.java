/*======================================================================
**
**  File: chimu/form/query/ExternalAssociatedVarC.java
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


class ExternalAssociatedVarC
                extends AssociatedVarAbsC {

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    public ExternalAssociatedVarC (QueryVarPi qv, ExternalAssociationSlotSi slot) {
        super(qv);
        this.slot = slot;
    }


    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public Slot associatedThrough() {return slot;}

    public ObjectRetriever valuesRetriever() {
        return slot.partnerRetriever();
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String associationName() {
        if (associationName == null) {
            associationName = queryDescription().variableNameFromPrefix(
                qv.name(),"To",associatedThrough().name()
            );
        }
        return associationName;
    };

    protected AssociationConnectorSi associationConnector() {
        return (AssociationConnectorSi) slot.associationConnector();
    }

    protected AssociationConnectorSlot associationSlot() {
        return slot.associationSlot();
    }

    protected AssociationConnectorSlot associationPairSlot() {
        return associationSlot().pairedAssociationSlot();
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


    protected Column associationColumn() {
        return associationSlot().associationColumn();
    }

    protected Column associationPairColumn() {
        return associationPairSlot().associationColumn();
    }

    protected String associationVarName() {
        return associationName();
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
         */
    public void putSelectColumnsInto(SqlBuilder sqlB) {
        sqlB.addSelectColumns_varName(
                    ((ObjectRetrieverSi) valuesRetriever()).selectColumns(),thereVarName()
                );
    }

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.addColumnAsExpression_varName(associationPairColumn(),associationVarName());
    }


    public void addNeededJoinsTo(SqlBuilder sqlB) {
        sqlB.joinFrom_varName_to_varName(
                hereColumn(),hereVarName(),
                associationColumn(),associationVarName());

        if (this.isObjectNeeded()) {
            sqlB.joinFrom_varName_to_varName(
                    associationPairColumn(),associationVarName(),
                    thereIdentityColumn(),thereVarName());
        };
    }

    public void addNeededExtentsTo(SqlBuilder sqlB) {
            //This I have to do no matter what
        sqlB.addExtent_varName(associationConnector().table(),associationVarName());
        if (this.isObjectNeeded()) {
            sqlB.addExtent_varName(valuesRetriever().table(),thereVarName());
        };
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected ExternalAssociationSlotSi   slot = null;
    protected String associationName = null;
}


/*
    public boolean isAttached() {
        return qv != null;
    };

    public void attachTo(QueryVar qv) {
        this.qv = (QueryVarPi) qv;
    };

        /**
         * Use an unspecified object variable, which will be set/bound later?
    public AssociatedVarC(Slot slot) {
        this.slot = (SlotSi) slot;
        this.qv = null;
    };

*/