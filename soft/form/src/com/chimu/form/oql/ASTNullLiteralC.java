/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTNullLiteralC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


class ASTNullLiteralC extends SimpleNode {
  ASTNullLiteralC(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTNullLiteralC(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription query) {
        return null;  //Should be interpretted by the condition
    }


}
