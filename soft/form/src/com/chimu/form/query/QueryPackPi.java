/*======================================================================
**
**  File: chimu/form/query/QueryPackPi.java
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
import java.util.Date;


/*package*/ class QueryPackPi {
    private QueryPackPi() {}; //Library class, not instanciable

    //**********************************************************
    //(P)***************** Factory Methods ********************
    //**********************************************************

    //**************************************
    //(P)******** QueryDescription *********
    //**************************************

        /**@deprecated*/
    static public QueryDescription newQueryDescription() {
        return new QueryDescriptionC();
    };

    //**************************************
    //(P)*********** Conditions ************
    //**************************************

    static public Condition newEqualTo(QueryExpression value1, QueryExpression value2) {
        return new EqualsConditionC(value1,value2);
    };

    static public Condition newNotEqualTo(QueryExpression value1, QueryExpression value2) {
        return new NotEqualsConditionC(value1,value2);
    };

    //**************************************

    static public Condition newNull(QueryExpression value1) {
        return new NullConditionC(value1);
    };

    static public Condition newNotNull(QueryExpression value1) {
        return new NotNullConditionC(value1);
    };

    //**************************************

    static public Condition newLike(QueryExpression value1, QueryExpression value2) {
        return new LikeConditionC(value1,value2);
    };

    static public Condition newNotLike(QueryExpression value1, QueryExpression value2) {
        return new NotLikeConditionC(value1,value2);
    };

    //**************************************

    static public Condition newGreaterThan(QueryExpression value1, QueryExpression value2) {
        return new GreaterThanConditionC(value1,value2);
    };

    static public Condition newGreaterThanEqualTo(QueryExpression value1, QueryExpression value2) {
        return new GreaterThanEqualsConditionC(value1,value2);
    };

    static public Condition newLessThan(QueryExpression value1, QueryExpression value2) {
        return new LessThanConditionC(value1,value2);
    };

    static public Condition newLessThanEqualTo(QueryExpression value1, QueryExpression value2) {
        return new LessThanEqualsConditionC(value1,value2);
    };

    //**************************************

    static public Condition newAnd(Condition value1, Condition value2) {
        return new AndConditionC(value1,value2);
    };

    static public Condition newOr(Condition value1, Condition value2) {
        return new OrConditionC(value1,value2);
    };

    //**************************************

    static public Condition newNotTrue(QueryExpression value1) {
        return new NotTrueConditionC(value1);
    };

    static public Condition newTrue(QueryExpression value1) {
        return new TrueConditionC(value1);
    };

    //**************************************
    //(P)*********** SqlBuilder ************
    //**************************************

    static public SqlBuilder newSqlBuilder() {
        return new SqlBuilderC();
    };

    //**************************************
    //(P)*********** ExtentVars?? ************
    //**************************************

    static public Constant newConstantString(String value) {return new ConstantStringC(value);}
    static public Constant newConstantBoolean(Boolean value) {return new ConstantBooleanC(value);}
    static public Constant newConstantDate(Date value) {return new ConstantDateC(value);}
    static public Constant newConstantTime(Date value) {return new ConstantTimeC(value);}
    static public Constant newConstantTimestamp(Date value) {return new ConstantTimestampC(value);}
    static public Constant newConstantNumber(Number value) {return new ConstantNumberC(value);}

    static public Constant newConstantMappedObject(MappedObject object) {
        return new ConstantObjectValueC(object);
    };

    static public UnboundExpression newUnboundExpression() {
        return new UnboundExpressionC();
    };
};



