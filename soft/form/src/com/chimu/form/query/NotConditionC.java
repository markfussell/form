/*======================================================================
**
**  File: chimu/form/query/NotConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class NotConditionC extends Condition1ValueAbsC {
    NotConditionC(QueryExpression value1) {
        super(value1);
    };

    public void putMyPreSqlInto(SqlBuilder sqlB) {
        sqlB.appendString("NOT ");
    };
};
