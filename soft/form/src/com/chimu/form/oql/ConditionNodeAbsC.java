/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ConditionNodeAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.query.*;


/*package*/ abstract class ConditionNodeAbsC
                        extends SimpleNode
                        implements ConditionCreator
                        {
    ConditionNodeAbsC(String id) {
        super(id);
    }


    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public abstract Condition newConditionFor_using(OqlQueryPi oql, QueryDescription description);
    public QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription query) {
        return newConditionFor_using(oql,query);
    }
}
