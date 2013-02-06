/*======================================================================
**
**  File: chimu/form/query/ConstantBooleanC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

class ConstantBooleanC implements Constant {
    ConstantBooleanC(Boolean value) {
        this.value = value;
    };

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.appendString(
            sqlB.sqlStringForBoolean(value)
        );
    };


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Boolean value;
};

