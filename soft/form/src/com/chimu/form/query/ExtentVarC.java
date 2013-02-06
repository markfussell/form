/*======================================================================
**
**  File: chimu/form/query/ExtentVarC.java
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

/**
Simple class that holds onto all the variables directly
**/
class ExtentVarC
                extends QueryVarAbsC
                implements ExtentVarPi {

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ ExtentVarC(QueryDescriptionPi queryDescription, String name, ObjectRetriever retriever) {
        this.queryDescription = queryDescription;
        this.name = name;
        this.retriever = (ObjectRetrieverSi) retriever;
        if (this.name == null) {
            this.name = queryDescription.variableNameFromPrefix(retriever.tableName());
        };
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public ObjectRetriever retriever() {
        return retriever;
    }

    public ObjectRetriever valuesRetriever() {
        return retriever;
    }

    public String name() {
        return name;
    }

    public String toString() {
        return "ExtentVar("+name+":"+retriever+")";
    }

    /*package*/ public QueryDescriptionPi queryDescription() {
        return queryDescription;
    }

    //**********************************************************
    //(P)***************** QueryGeneration *********************
    //**********************************************************

        /**
         * I can be treated as a simple object by using my identity key?
         */
    protected Column hereColumn() {
        return retriever.identityKeyColumn();
    }

    protected String hereVarName() {
        return name();
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
        sqlB.addSelectColumns_varName(
                    retriever.selectColumns(),hereVarName()
                );
    }

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.addColumnAsExpression_varName(hereColumn(),hereVarName());
    }

    public void addNeededExtentsTo(SqlBuilder sqlB) {
        sqlB.addExtent_varName(retriever().table(),hereVarName());
        //if retriever is restrictive then also have to restrict sqlB for that VarName
        //sqlB.addRestriction(hereVarName(), restrictionSlot, value);
        //or restrictionSlot.addRestriction(sqlB)...

    }

        /**
         * I don't need to do a join
         */
    public void addNeededJoinsTo(SqlBuilder sqlB) {}

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************


    protected String             name             = null;
    protected ObjectRetrieverSi  retriever        = null;
    protected QueryDescriptionPi queryDescription = null;

};
