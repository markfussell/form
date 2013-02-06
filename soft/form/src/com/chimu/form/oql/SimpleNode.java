/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/SimpleNode.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;

import com.chimu.kernel.exceptions.*;


/*package*/ class SimpleNode implements Node {
  protected Node parent;
  protected java.util.Vector children;
  protected String identifier;
  protected Object info;

  public SimpleNode(String id) {
    identifier = id;
  }

  public static Node jjtCreate(String id) {
    return new SimpleNode(id);
  }

  public void jjtOpen() {}
  public void jjtClose() {
    if (children != null) {
      children.trimToSize();
    }
  }

  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n) {
    if (children == null) {
      children = new java.util.Vector();
    }
    children.addElement(n);
  }
  public Node jjtGetChild(int i) {
    return (Node)children.elementAt(i);
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.size();
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public void setInfo(Object i) { info = i; }
    public Object getInfo() { return info; }

    public String printString() {
        return identifier;
    }

    public void dump(String prefix) {
        System.out.println(prefix + identifier);
        if (children != null) {
          for (java.util.Enumeration e = children.elements();
           e.hasMoreElements();) {
            	SimpleNode n = (SimpleNode)e.nextElement();
            	n.dump(prefix + "-");
          }
        }
    }


    public void buildQueryFor_into(OqlQueryPi oql, QueryDescription query) {
        throw new ShouldNotImplementException("This method is not supported by "+identifier);
    }

    public Condition newConditionFor_using(OqlQueryPi oql, QueryDescription query) {
        throw new ShouldNotImplementException("This method is not supported by "+identifier);
    }

    public QueryExpression newExpressionFor_using(OqlQueryPi oql, QueryDescription query) {
        throw new ShouldNotImplementException("This method is not supported by "+identifier);
    }

    public void buildQueryVarNamed_for_into(String varName,OqlQueryPi oql, QueryDescription query) {
        throw new ShouldNotImplementException("This method is not supported by "+identifier);
    }
}

