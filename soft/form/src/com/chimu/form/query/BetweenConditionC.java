/*======================================================================
**
**  File: chimu/form/query/BetweenConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class BetweenConditionC extends Condition3ValueAbsC {
    BetweenConditionC(QueryExpression value1, QueryExpression value2, QueryExpression value3) {
        super(value1,value2,value3);
    };

    protected void putMyInner1SqlInto(SqlBuilder sqlB) {
        sqlB.appendString("BETWEEN ");
    };

    protected void putMyInner2SqlInto(SqlBuilder sqlB) {
        sqlB.appendString("AND ");
    };

};


