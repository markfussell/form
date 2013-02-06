/*======================================================================
**
**  File: chimu/form/query/Condition3ValueAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

abstract class Condition3ValueAbsC implements Condition {
    Condition3ValueAbsC(QueryExpression value1,QueryExpression value2,QueryExpression value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    };

    public void putExpressionValueInto(SqlBuilder sqlB) {
        sqlB.appendStartPrimary();
        value1.putExpressionValueInto(sqlB);
        this.putMyInner1SqlInto(sqlB);
        value2.putExpressionValueInto(sqlB);
        this.putMyInner2SqlInto(sqlB);
        value3.putExpressionValueInto(sqlB);
        sqlB.appendEndPrimary();
    };

    protected abstract void putMyInner1SqlInto(SqlBuilder sqlB);
    protected abstract void putMyInner2SqlInto(SqlBuilder sqlB);
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
    protected final QueryExpression value3;
};


/*
  ValueHolder

  SlotVariable
  DirectValueWrapper ('Foo');

    newSlot_equalsValue(Slot slot, Object value) {
        new EqualsConditionC(slot,newDirectValueWrapper(value))
    };


*/