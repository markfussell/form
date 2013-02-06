/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTWhereClause.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


/*package*/ class ASTWhereClause extends SimpleNode {
  ASTWhereClause(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTWhereClause(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public void buildQueryFor_into(OqlQueryPi oql, QueryDescription query) {
        SimpleNode conditionNode  = (SimpleNode) children.elementAt(0);

        Condition condition = conditionNode.newConditionFor_using(oql,query);
        query.setCondition(condition);
    }


}
