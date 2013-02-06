/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTRange.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;


/*package*/ class ASTRange extends SimpleNode {
  ASTRange(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTRange(id);
  }


    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************


    public void dump(String prefix) {
        SimpleNode range = (SimpleNode) children.elementAt(0);
        if (children.size() > 1) {
            SimpleNode variable = (SimpleNode) children.elementAt(1);
            System.out.print(prefix + "Var: "+variable.printString());
            System.out.println(" IN "+range.printString());
        } else {
            System.out.println(prefix + "Var: <var> IN "+range.printString());
        };
    }

    //
    public void buildQueryFor_into(OqlQueryPi oql, QueryDescription query) {
        SimpleNode range = (SimpleNode) children.elementAt(0);
        String variableName = null;
        if (children.size() > 1) {
            SimpleNode variable = (SimpleNode) children.elementAt(1);
            variableName = ((ASTName) variable).name();
        };
        range.buildQueryVarNamed_for_into(variableName,oql,query);
    }

}
