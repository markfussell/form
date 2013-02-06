/*======================================================================
**
**  File: chimu/form/query/NotTrueConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class NotTrueConditionC extends Condition1ValueAbsC {
    NotTrueConditionC(QueryExpression value1) {
        super(value1);
    };

    public void putMyPreSqlInto(SqlBuilder sqlB) {
        sqlB.appendString("NOT ");
    };
};
