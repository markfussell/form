/*======================================================================
**
**  File: chimu/form/query/ConstantStringC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

class ConstantStringC implements Constant {
    ConstantStringC(String value) {
        this.value = value;
    };

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.appendString(
            sqlB.sqlStringForString(value)
        );
    };


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String value;
};

