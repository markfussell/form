/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTRangeList.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


import java.util.Enumeration;

/*package*/ class ASTRangeList extends SimpleNode {
  ASTRangeList(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTRangeList(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    //
    public void buildQueryFor_into(OqlQueryPi oql, QueryDescription query) {
        Enumeration e = children.elements();
        while (e.hasMoreElements()) {
        	SimpleNode n = (SimpleNode) e.nextElement();
        	n.buildQueryFor_into(oql,query);
        }
    }

}
