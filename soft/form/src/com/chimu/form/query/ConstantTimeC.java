/*======================================================================
**
**  File: chimu/form/query/ConstantTimeC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;
import java.util.Date;

class ConstantTimeC implements Constant {
    ConstantTimeC(Date value) {
        this.value = value;
    };

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.appendString(
            sqlB.sqlStringForTime(value)
        );
    };


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Date value;
};

