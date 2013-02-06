/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTCharacterLiteral.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;


/*package*/ class ASTCharacterLiteral extends NamedNodeAbsC {
  ASTCharacterLiteral(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTCharacterLiteral(id);
  }
}
