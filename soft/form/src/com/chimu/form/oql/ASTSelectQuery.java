/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTSelectQuery.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.query.*;
import com.chimu.form.mapping.*;



/*package*/ class ASTSelectQuery extends SimpleNode {
  ASTSelectQuery(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTSelectQuery(id);
  }



    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    //
    public void buildQueryFor_into(OqlQueryPi oql, QueryDescription query) {
        SimpleNode selectClause  = (SimpleNode) children.elementAt(0);
        SimpleNode fromClause  = (SimpleNode) children.elementAt(1);

        fromClause.buildQueryFor_into(oql, query);
        selectClause.buildQueryFor_into(oql, query);  //Needs to occur after the fromClause

        if (children.size()>2) {
            SimpleNode whereClause = (SimpleNode) children.elementAt(2);
            whereClause.buildQueryFor_into(oql, query);
        }

    }


}
