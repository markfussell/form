/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTFromClause.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.query.*;

/*package*/ class ASTFromClause extends SimpleNode {
  ASTFromClause(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTFromClause(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    //
    public void buildQueryFor_into(OqlQueryPi oql, QueryDescription query) {

        SimpleNode rangeList  = (SimpleNode) children.elementAt(0);

        rangeList.buildQueryFor_into(oql, query);
    }


}
