/*======================================================================
**
**  File: chimu/form/query/QueryDescription.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;
import com.chimu.form.*;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;
import java.util.Enumeration;


/**
A QueryDescription describes the information necessary for building a
query including what extent/s of objects will be searched and how to
determine what objects will be selected from those extent/s.

@see com.chimu.form.Orm#newQueryDescription
**/
/*

<P>Since the QueryDescription has to keep track of what variables are
still unbound,

<P>A QueryDescription has after-definition flexibility by not having its
ObjectVars attached to a mapper and by having unbound values.
*/
public interface QueryDescription {

    public Orm orm();

    public void putSqlInto(SqlBuilder sqlB);
    public void putCountSqlInto(SqlBuilder sqlB);

    //**********************************************************
    //****************** Variables and Values ******************
    //**********************************************************

    /*subsystem*/ public void registerQueryVar(QueryVar queryVar, String name);
    /*subsystem*/ public QueryVar queryVarNamed(String name);

    public ExtentVar            newExtentVar(String name, ObjectRetriever retriever);
    public ExtentVar            newExtentVar(ObjectRetriever retriever);
    public QueryVar             defaultQueryVar(ObjectRetriever retriever);

        /**
         *Require: 'qv' must have 'retriever' as its retriever
         */
    public void         setDefaultQueryVarForRetriever_to(ObjectRetriever retriever, QueryVar qv);
    public QueryVar     aQueryVarForRetriever(ObjectRetriever retriever);

    public Constant             newConstant(Object value);

        /**
         * Create and remember an unbound value so I can later set its value
         */
    public UnboundExpression    newUnboundExpression();


    //**********************************************************
    //*********************** Results **************************
    //**********************************************************

    public QueryVar resultsVar();
        /**
         * Set which object variable will be the return values of the query
         *
         * By default this is whatever is the only ObjectVar (including unnamed ones)
         */
    public void setResultsVar (QueryVar qv) ;
    public void addResultsVar (QueryVar qv) ;

        /**
         * Allow the results of the query to be non-distinct, so the same object
         * can be returned multiple times for a given query.  This is a violation
         * of predicate logic and relational theory, so the meaning of the multiple
         * objects is application dependent.
         */
    public void makeResultsNotDistinct();

    //**********************************************************
    //********************** Condition *************************
    //**********************************************************

    public Condition condition();

        /**
         *Require: this.condition() == null
         */
    public void setCondition(Condition newCondition);

        /**
         * Replace the current condition with another one.  Like setCondition but
         * this does not have a requirement that there be no condition yet.
         */
    public void replaceCondition(Condition newCondition);


    //**********************************************************
    //(P)*********** Common condition operations ***************
    //**********************************************************

    //**************************************
    //(P)************ OR *******************
    //**************************************

    public void or(Condition addedCondition);
    public void or_equals(QueryExpression writable1, QueryExpression writable2);
    public void or_equalsConstant(QueryExpression writable1, Object value);

    //**************************************
    //(P)************ AND ******************
    //**************************************

    public void and(Condition addedCondition);
    public void and_equals(QueryExpression writable1, QueryExpression writable2);
    public void and_equalsConstant(QueryExpression slot, Object value);

    //**********************************************************
    //********************** Evaluating ************************
    //**********************************************************

    public Array selectAll();
    public Object findAny();
    public int    countAll();

    //**********************************************************
    //**********************************************************
    //**********************************************************

    //**************************************
    //(P)*********** Conditions ************
    //**************************************

    public Condition newEqualTo(QueryExpression value1, QueryExpression value2);
    public Condition newNotEqualTo(QueryExpression value1, QueryExpression value2);

    //**************************************

    public Condition newNull(QueryExpression value1);
    public Condition newNotNull(QueryExpression value1);

    //**************************************

    public Condition newLike(QueryExpression value1, QueryExpression value2);
    public Condition newNotLike(QueryExpression value1, QueryExpression value2);

    public Condition newLikeConstant(QueryExpression value1, Object value2);
    public Condition newNotLikeConstant(QueryExpression value1, Object value2);

    //**************************************

    public Condition newGreaterThan(QueryExpression value1, QueryExpression value2);
    public Condition newGreaterThanEqualTo(QueryExpression value1, QueryExpression value2);
    public Condition newLessThan(QueryExpression value1, QueryExpression value2);
    public Condition newLessThanEqualTo(QueryExpression value1, QueryExpression value2);

    //**************************************

    public Condition newAnd(Condition value1, Condition value2);
    public Condition newOr(Condition value1, Condition value2);

    //**************************************

    public Condition newNotTrue(QueryExpression value1);
    public Condition newTrue(QueryExpression value1);


    //**************************************

    public QueryExpression newGenericExpression_between_and(String name,QueryExpression left, QueryExpression right);
    public QueryExpression newGenericExpression_prefixing(String name,QueryExpression value1);
};

