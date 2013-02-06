/*======================================================================
**
**  File: chimu/form/query/LessThanConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class LessThanConditionC extends Condition2ValueAbsC {
    LessThanConditionC(QueryExpression value1, QueryExpression value2) {
        super(value1,value2);
    };

    protected void putMySqlInto(SqlBuilder sqlB) {
        sqlB.appendString("< ");
    };

};


