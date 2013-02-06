/*======================================================================
**
**  File: chimu/form/query/AndConditionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

class AndConditionC extends Condition2ValueAbsC {
    AndConditionC(Condition value1, Condition value2) {
        super(value1,value2);
    };

    protected void putMySqlInto(SqlBuilder sqlB) {
        sqlB.appendString("AND ");
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