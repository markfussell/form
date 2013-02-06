/*======================================================================
**
**  File: chimu/form/query/Generic1ValueExpressionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class Generic1ValueExpressionC extends Condition1ValueAbsC {
    Generic1ValueExpressionC(String operatorString, QueryExpression value1) {
        super(value1);
        this.operatorString = operatorString;
    };

    protected void putMyPreSqlInto(SqlBuilder sqlB) {
        sqlB.appendString(operatorString);
    };

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected final String operatorString;
};
