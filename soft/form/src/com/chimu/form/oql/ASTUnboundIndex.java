/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTUnboundIndex.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;




/*package*/ class ASTUnboundIndex extends SimpleNode {
  ASTUnboundIndex(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTUnboundIndex(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    public String printString() {
        return name == null ? identifier : ":"+name;
    };

    protected String name = null;
    public void setName(String name) {
        this.name = name;
    }



  public void dump(String prefix) {
    System.out.println(prefix + "We are here:"+identifier);
    if (children != null) {
      for (java.util.Enumeration e = children.elements();
	   e.hasMoreElements();) {
        	SimpleNode n = (SimpleNode)e.nextElement();
        	n.dump(prefix + "-");
      }
    }
  }

}
