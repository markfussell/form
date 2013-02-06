/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTArguments.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;


/*package*/ class ASTArguments extends SimpleNode {
  ASTArguments(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTArguments(id);
  }
}
