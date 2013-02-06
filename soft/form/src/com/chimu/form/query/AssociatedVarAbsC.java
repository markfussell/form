/*======================================================================
**
**  File: chimu/form/query/AssociatedVarAbsC.java
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
import java.util.Enumeration;


abstract class AssociatedVarAbsC
                extends QueryVarAbsC
                implements AssociatedVarPi {

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

        //I don't include the slot because I don't want to keep casting it and only the
        //subclasses know the right slot type

    public AssociatedVarAbsC(QueryVar qv) {
        this.qv = (QueryVarPi) qv;
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public QueryVar       associatedTo() {return qv;}
    public abstract Slot  associatedThrough();

    public String name() {
        if (name == null) name = createName();
        return name;
    }

    public QueryDescriptionPi queryDescription() {
        return qv.queryDescription();
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected String createName() {
        return queryDescription().variableNameFromPrefix(
            qv.name(),"",associatedThrough().name()
        );
    }


    //**********************************************************
    //(P)***************** QueryGeneration *********************
    //**********************************************************


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    QueryVarPi   qv   = null;
    String       name = null;
}

