/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTUnaryNotCondition.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;


/*package*/ class ASTUnaryNotCondition extends NamedNodeAbsC {
  ASTUnaryNotCondition(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTUnaryNotCondition(id);
  }
}
