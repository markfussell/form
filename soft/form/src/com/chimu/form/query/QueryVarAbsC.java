/*======================================================================
**
**  File: chimu/form/query/QueryVarAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;
import java.util.Enumeration;


abstract class QueryVarAbsC implements QueryVarPi {

    //**********************************************************
    //(P)***************** QueryVar Creation *******************
    //**********************************************************

    public AssociatedVar slot(Slot slot) {
        if (slot == null) throw new FailedRequireException("Slot must be non-null");
        //I haven't decided whether this will cross between Mapping and Query
        //by having the Slot create the appropriate AssociatedVar.  This makes sense
        //but makes compiling a bit more difficult.  For the moment I am "casing" it
        SlotSi slotSi = (SlotSi) slot;
        AssociatedVar av;
        if (slotSi.isAssociation()) {
            if (slotSi.isForwardAssociation()) {
                av = new ForwardAssociatedVarC(this, (ForwardAssociationSlotSi) slotSi);
            } else if (slotSi.isExternalAssociation()) {
                av = new ExternalAssociatedVarC(this, (ExternalAssociationSlotSi) slotSi);
            } else {//ReverseAssociation
                av = new ReverseAssociatedVarC(this, (ReverseAssociationSlotSi) slotSi);
            }
        } else {
            av = new DirectAssociatedVarC(this, slotSi);
        }
        queryDescription().addQueryVar(av);
        if (dependentVariables == null) dependentVariables = CollectionsPack.newList();
        dependentVariables.add(av);
        return av;
    }

    public AssociatedVar slotNamed(String slotName) {
        Slot slot = valuesRetriever().slotNamed(slotName);
        if (slot == null) throw new FailedRequireException("Query variable must have a slot with name \""+slotName+"\"");
        return slot(slot);
    }

    public AssociatedVar slot(String slotName) {
        return slotNamed(slotName);
    };


//    public AssociatedVar slotNamed_as(String slotName, String variableName);

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public String toString() {
        return "QueryVar("+name()+":"+valuesRetriever()+")";
    };

    public Array selectAll() {
        return ((ObjectRetriever) valuesRetriever()).selectWhereQuery(queryDescription());
    }

    public int countAll() {
        return ((ObjectRetriever) valuesRetriever()).countWhereQuery(queryDescription());
    }

    public Object findAny() {
        return ((ObjectRetriever) valuesRetriever()).findWhereQuery(queryDescription());
    }


    //**********************************************************
    //(P)******************    PRIVATE    **********************
    //**********************************************************

    /*package*/ public void makeNeededInResults() {
        isNeededInResult = true;
    }

    protected boolean isObjectNeeded() {
        return isNeededInResult || hasDependentVariables();
    }

    protected boolean hasDependentVariables() {
        if (dependentVariables == null) return false;
        return dependentVariables.size() > 0;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected boolean  isNeededInResult = false;
    protected List dependentVariables;
};
