/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/NamedNodeAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;



/*package*/ class NamedNodeAbsC extends SimpleNode {
  NamedNodeAbsC(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new NamedNodeAbsC(id);
  }

    public String printString() {
        return name == null ? identifier : name;
    };

    protected String name = null;
    public void setName(String name) {
        this.name = name;
    }


}
