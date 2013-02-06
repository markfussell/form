/*======================================================================
**
**  File: chimu/form/query/QueryExpression.java
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
A QueryExpression is anything that can be evaluated during the
evaluation of a Query.  This falls into primarily the following
types:
<UL>
    <LI>Conditions
    <LI>Constants
    <LI>Variables
</UL>
@see Condition
@see Constant
@see QueryVar
**/
public interface QueryExpression {
    //This should be in the subclass only?
        /**
         * Add your query part to the SqlBuilder
         */
    public void putExpressionValueInto(SqlBuilder sqlB);
    //putExpressionValueInto
//    public void putSqlInto(SqlBuilder sqlB);
}

