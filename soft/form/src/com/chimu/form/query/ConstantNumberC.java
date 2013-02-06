/*======================================================================
**
**  File: chimu/form/query/ConstantNumberC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

class ConstantNumberC implements Constant {
    ConstantNumberC(Number value) {
        this.value = value;
    };

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.appendString(
            sqlB.sqlStringForNumber(value)
        );
    };


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Number value;
};

