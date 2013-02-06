/*======================================================================
**
**  File: chimu/form/query/Generic2ValueExpressionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

class Generic2ValueExpressionC extends Condition2ValueAbsC {
    Generic2ValueExpressionC(String operatorString, QueryExpression value1,QueryExpression value2) {
        super(value1,value2);
        this.operatorString = operatorString;
    };

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.appendStartPrimary();
        value1.putExpressionValueInto(sqlB);
        this.putMySqlInto(sqlB);
        value2.putExpressionValueInto(sqlB);
        sqlB.appendEndPrimary();
    };

    protected void putMySqlInto(SqlBuilder sqlB) {
        sqlB.appendString(operatorString);
    };

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected final String operatorString;
};


