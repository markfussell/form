/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTName.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;




/*package*/ class ASTName extends SimpleNode {
  ASTName(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTName(id);
  }

  public String toString() { return identifier+"("+name+")"; }

  public String name() {
    return name;
  }

  protected String name = "";
  public void appendName(String string) {
    name = name + string;
  };
}
