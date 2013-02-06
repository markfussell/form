/*======================================================================
**
**  File: chimu/form/query/OrConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;


class OrConditionC extends Condition2ValueAbsC {
    OrConditionC(QueryExpression value1, QueryExpression value2) {
        super(value1,value2);
    };

    protected void putMySqlInto(SqlBuilder sqlB) {
        sqlB.appendString("OR ");
    };

};




/*
  ValueHolder

  SlotVariable
  DirectValueWrapper ('Foo');

    newSlot_equalsValue(Slot slot, Object value) {
        new EqualsConditionC(slot,newDirectValueWrapper(value))
    };


*/