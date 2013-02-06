/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTIntegerLiteral.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


/*package*/ class ASTIntegerLiteral extends NamedNodeAbsC {
  ASTIntegerLiteral(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTIntegerLiteral(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public void setName(String name) {
        this.name = name;
    }

    public QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription query) {
        return query.newConstant(new Long(name));
    }



}
