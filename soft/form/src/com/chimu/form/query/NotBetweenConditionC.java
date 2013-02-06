/*======================================================================
**
**  File: chimu/form/query/NotBetweenConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class NotBetweenConditionC extends Condition3ValueAbsC {
    NotBetweenConditionC(QueryExpression value1, QueryExpression value2, QueryExpression value3) {
        super(value1,value2,value3);
    };

    protected void putMyInner1SqlInto(SqlBuilder sqlB) {
        sqlB.appendString("NOT BETWEEN ");
    };

    protected void putMyInner2SqlInto(SqlBuilder sqlB) {
        sqlB.appendString("AND ");
    };

};


