/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTSelectClause.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.query.*;
import com.chimu.form.mapping.*;



/*package*/ class ASTSelectClause extends SimpleNode {
  ASTSelectClause(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTSelectClause(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    //
    public void buildQueryFor_into(OqlQueryPi oql, QueryDescription query) {
        SimpleNode nameNode  = (SimpleNode) children.elementAt(0);
        if (nameNode instanceof ASTName) {
            String name = ((ASTName) nameNode).name();
            QueryVar qv = query.queryVarNamed(name);
            if (qv == null) throw new RuntimeException("SELECT uses an unknown variable name "+name);
            query.setResultsVar(qv);
        } else {
            throw new RuntimeException("SELECT uses something other than a name "+nameNode);
        }
    }


}
