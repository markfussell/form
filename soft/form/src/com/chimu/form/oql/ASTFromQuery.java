/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTFromQuery.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


/*package*/ class ASTFromQuery extends SimpleNode {
  ASTFromQuery(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTFromQuery(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    //
    public void buildQueryFor_into(OqlQueryPi oql, QueryDescription query) {
        SimpleNode fromClause  = (SimpleNode) children.elementAt(0);

        fromClause.buildQueryFor_into(oql, query);

        if (children.size()>1) {
            SimpleNode whereClause = (SimpleNode) children.elementAt(1);
            whereClause.buildQueryFor_into(oql, query);
        }
    }


}
