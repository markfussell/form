/*======================================================================
**
**  File: chimu/form/query/QueryExpressionPi.java
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
@see QueryExpression
**/
public interface QueryExpressionPi extends QueryExpression {
    public void putExpressionValueInto(SqlBuilder sqlB);
}

