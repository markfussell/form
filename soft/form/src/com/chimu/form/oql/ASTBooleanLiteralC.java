/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTBooleanLiteralC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


class ASTBooleanLiteralC extends SimpleNode {
  ASTBooleanLiteralC(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTBooleanLiteralC(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    protected boolean isTrue = false;
    public void makeTrue() {
        isTrue = true;
    }

    public void makeFalse() {
        isTrue = false;
    }

    public QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription query) {
        return query.newConstant(isTrue ? Boolean.TRUE : Boolean.FALSE);
    }


}
