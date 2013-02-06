/*======================================================================
**
**  File: chimu/form/query/UnboundExpression.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

/**
An UnboundValue is a place holder for another query value in a condition.
This allows you to build up an expression but leave some values unbound
and up to a latter part of the program to determine.

@see QueryPack#newUnboundValue();
**/
interface UnboundExpression extends QueryExpression {
// ValueHolder
// WritableHolder
// Have to determine

    public boolean isBound();

    public void value(QueryExpression value);
    public QueryExpression value();
    
    public void unbindValue();

//    public void setToValue(QueryExpression value);


        /**
         * Unbind (reset) the value of this UnboundValue.
         *
         *<P>&Ensure isBound
         */
//    public void unset();
//    public boolean isSet();
};

