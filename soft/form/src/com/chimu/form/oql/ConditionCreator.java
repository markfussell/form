/*======================================================================
**
**  File: chimu/form/oql/ConditionCreator.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.query.*;

interface ConditionCreator extends ExpressionCreator {
    public abstract Condition newConditionFor_using(OqlQueryPi oql, QueryDescription description);
}
