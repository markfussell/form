/*======================================================================
**
**  File: chimu/form/query/NullConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class NullConditionC extends Condition1ValueAbsC {
    NullConditionC(QueryExpression value1) {
        super(value1);
    };

    public void putMyPostSqlInto(SqlBuilder sqlB) {
        sqlB.appendIsNull();
    };
};
