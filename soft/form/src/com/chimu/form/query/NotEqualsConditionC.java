/*======================================================================
**
**  File: chimu/form/query/NotEqualsConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

class NotEqualsConditionC extends Condition2ValueAbsC {
    NotEqualsConditionC(QueryExpression value1, QueryExpression value2) {
        super(value1,value2);
    };

    protected void putMySqlInto(SqlBuilder sqlB) {
        sqlB.appendString("<> ");
    };

};
