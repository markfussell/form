/*======================================================================
**
**  File: chimu/form/query/UnboundExpressionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import java.util.Enumeration;

/**
This is a ValueHolder for an Expression
**/

class UnboundExpressionC implements UnboundExpression {
    UnboundExpressionC() {};

    UnboundExpressionC(QueryExpression value) {
        this.value(value);
    };

    public void putExpressionValueInto(SqlBuilder sqlB) {
        if (!isBound) {
            sqlB.appendUnboundParameter();
        } else {
            value.putExpressionValueInto(sqlB);
        };
    };

    public boolean isBound() {
        return isBound;
    };

    public QueryExpression value() {
        return value;
    };

    public void unbindValue() {
        this.value = null;
        isBound = false;
    };

    public void value(QueryExpression value) {
        this.value = value;
        isBound = true;
    };

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected boolean isBound = false;
    protected QueryExpression value = null;
};

