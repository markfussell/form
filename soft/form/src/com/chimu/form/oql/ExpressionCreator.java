/*======================================================================
**
**  File: chimu/form/oql/ExpressionCreator.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.query.*;

interface ExpressionCreator {
    public abstract QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription description);
}
