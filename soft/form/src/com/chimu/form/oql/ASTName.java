/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTName.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

import com.chimu.form.query.*;
import com.chimu.form.mapping.*;


/*package*/ class ASTName extends SimpleNode {
  ASTName(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTName(id);
  }

    public String printString() {
        return name == null ? identifier : name;
    };

    protected String name = null;
    public void setName(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public void buildQueryVarNamed_for_into(String varName,OqlQueryPi oql, QueryDescription query) {
        if (varName == null) varName = name;

        ObjectRetriever or = query.orm().retrieverNamed(name);
        if (or == null) throw new MappingException("No ObjectRetriever named "+name);
        query.newExtentVar(varName,or);
    }

    public QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription query) {
        QueryVar qv = query.queryVarNamed(name);
        if (qv == null) {
            throw new MappingException("No query variable named "+name);
        }
        return qv;
    }


}
