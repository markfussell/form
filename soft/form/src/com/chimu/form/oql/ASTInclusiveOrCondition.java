/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTInclusiveOrCondition.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


/*package*/ class ASTInclusiveOrCondition extends SimpleNode {
  ASTInclusiveOrCondition(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTInclusiveOrCondition(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    //
    public Condition newConditionFor_using(OqlQueryPi oql, QueryDescription query) {
        Condition result = ((SimpleNode) children.elementAt(0)).newConditionFor_using(oql,query);
        int size = children.size();
        for (int i = 1; i < size; i++) {
            result = query.newOr(
                    result,
                    ((SimpleNode) children.elementAt(i)).newConditionFor_using(oql,query)
                );
        };
        return result;

    }

}
