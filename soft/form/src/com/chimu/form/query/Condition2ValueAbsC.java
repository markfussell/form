/*======================================================================
**
**  File: chimu/form/query/Condition2ValueAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

abstract class Condition2ValueAbsC implements Condition {
    Condition2ValueAbsC(QueryExpression value1,QueryExpression value2) {
        this.value1 = value1;
        this.value2 = value2;
    };

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.appendStartPrimary();
        value1.putExpressionValueInto(sqlB);
        this.putMySqlInto(sqlB);
        value2.putExpressionValueInto(sqlB);
        sqlB.appendEndPrimary();
    };

    protected abstract void putMySqlInto(SqlBuilder sqlB);
/*
    protected void putMySqlInto() {
        throw new RuntimeExection("Subclass Responsibility");
    };
*/

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected final QueryExpression value1;
    protected final QueryExpression value2;
};


/*
  ValueHolder

  SlotVariable
  DirectValueWrapper ('Foo');

    newSlot_equalsValue(Slot slot, Object value) {
        new EqualsConditionC(slot,newDirectValueWrapper(value))
    };


*/