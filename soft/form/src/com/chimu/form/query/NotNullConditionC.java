/*======================================================================
**
**  File: chimu/form/query/NotNullConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class NotNullConditionC extends Condition1ValueAbsC {
    NotNullConditionC(QueryExpression value1) {
        super(value1);
    };

    public void putMyPostSqlInto(SqlBuilder sqlB) {
        sqlB.appendNotNull();
    };
};
