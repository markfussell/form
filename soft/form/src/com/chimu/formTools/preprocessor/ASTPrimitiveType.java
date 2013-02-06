/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTPrimitiveType.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;



/*package*/ class ASTPrimitiveType extends SimpleNode {
  ASTPrimitiveType(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTPrimitiveType(id);
  }
}
