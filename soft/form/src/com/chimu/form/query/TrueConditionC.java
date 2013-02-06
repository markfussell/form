/*======================================================================
**
**  File: chimu/form/query/TrueConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class TrueConditionC extends Condition1ValueAbsC {
    TrueConditionC(QueryExpression value1) {
        super(value1);
    };

    public void putMyPreSqlInto(SqlBuilder sqlB) {
        sqlB.appendString(" ");
    };
};
