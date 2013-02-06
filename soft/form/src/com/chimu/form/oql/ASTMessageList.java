/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTMessageList.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;
import com.chimu.kernel.exceptions.*;


/*package*/ class ASTMessageList extends SimpleNode {
    ASTMessageList(String id) {
        super(id);
    }

    public static Node jjtCreate(String id) {
        return new ASTMessageList(id);
    }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public String printString() {
        StringBuffer stringB = new StringBuffer();

        SimpleNode variable = (SimpleNode) children.elementAt(0);

        stringB.append(variable.printString());
        int size = children.size();
        if (size > 1) {
            for (int i = 1; i<size; i++) {
                SimpleNode range = (SimpleNode) children.elementAt(i);
                stringB.append(range.printString());
            };
        } else {
            stringB.append(variable.printString());
        };

        return stringB.toString();
    };

    public void dump(String prefix) {
        System.out.println(prefix+printString());
    }

    public void buildQueryVarNamed_for_into(String varName,OqlQueryPi oql, QueryDescription query) {
        if (varName == null) {
            throw new FailedRequireException("Query Variable: "+printString()+" must have a name");
        };
        SimpleNode variable = (SimpleNode) children.elementAt(0);

        QueryVar qv = query.queryVarNamed(variable.printString());
        if (qv == null) {
            throw new RuntimeException("No query variable named "+variable.printString());
        }
        int size = children.size();
        for (int i = 1; i < size; i++) {
            ASTMessage slot = (ASTMessage) children.elementAt(i);
            qv = qv.slotNamed(slot.name());
        };
        query.registerQueryVar(qv,varName);
    }

    public QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription query) {
        SimpleNode variable = (SimpleNode) children.elementAt(0);

        QueryVar qv = (QueryVar) variable.newExpressionFor_using(oql,query);
        int size = children.size();
        for (int i = 1; i < size; i++) {
            ASTMessage slot = (ASTMessage) children.elementAt(i);
            qv = qv.slotNamed(slot.name());
        };
        return qv;
    }


}
