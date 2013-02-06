/*======================================================================
**
**  File: chimu/form/query/GreaterThanEqualsConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class GreaterThanEqualsConditionC extends Condition2ValueAbsC {
    GreaterThanEqualsConditionC(QueryExpression value1, QueryExpression value2) {
        super(value1,value2);
    };

    protected void putMySqlInto(SqlBuilder sqlB) {
        sqlB.appendString(">= ");
    };

};

