/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTMultiplicativeExpression.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


/*package*/ class ASTMultiplicativeExpression extends NamedNodeAbsC {
  ASTMultiplicativeExpression(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTMultiplicativeExpression(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription query) {
        QueryExpression left   = ((SimpleNode) children.elementAt(0)).newExpressionFor_using(oql,query);
        QueryExpression right  = ((SimpleNode) children.elementAt(1)).newExpressionFor_using(oql,query);

        return query.newGenericExpression_between_and(name,left,right);
    }

}
