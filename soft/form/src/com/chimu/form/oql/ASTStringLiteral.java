/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTStringLiteral.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


/*package*/ class ASTStringLiteral extends NamedNodeAbsC {
  ASTStringLiteral(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTStringLiteral(id);
  }


    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public void setName(String name) {
        //String literal has quotes on both ends
        this.name = name.substring(1,name.length()-1);
    }

    public QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription query) {
        return query.newConstant(name);
    }



}
