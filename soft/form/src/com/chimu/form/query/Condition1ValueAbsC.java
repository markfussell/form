/*======================================================================
**
**  File: chimu/form/query/Condition1ValueAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


abstract class Condition1ValueAbsC implements Condition {
    Condition1ValueAbsC(QueryExpression value1) {
        this.value1 = value1;
    };

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.appendStartPrimary();
        this.putMyPreSqlInto(sqlB);
        value1.putExpressionValueInto(sqlB);
        this.putMyPostSqlInto(sqlB);
        sqlB.appendEndPrimary();
    };

    protected void putMyPostSqlInto(SqlBuilder sqlB) {};
    protected void putMyPreSqlInto(SqlBuilder sqlB) {};

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected final QueryExpression value1;
};
