/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTFloatingPointLiteral.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


/*package*/ class ASTFloatingPointLiteral extends NamedNodeAbsC {
  ASTFloatingPointLiteral(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTFloatingPointLiteral(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription query) {
        return query.newConstant(new Double(name));
    }

}
