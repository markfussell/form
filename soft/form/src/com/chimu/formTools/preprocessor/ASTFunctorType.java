/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTFunctorType.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;



/*package*/ class ASTFunctorType extends SimpleNode {
  ASTFunctorType(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTFunctorType(id);
  }

    //==========================================================
    //(P)=============== Manually Inserted  ====================
    //==========================================================

    public Token startNameToken = null;
    public Token endNameToken = null;

    public String typeName() {
        if (needToBuild) buildValues();
        return typeName;
    };

    //==========================================================
    //(P)================      PRIVATE      ====================
    //==========================================================

    protected String typeName = null;
    protected boolean needToBuild = true;

    protected void buildValues() {
        if (startNameToken != null) {
            typeName = newStringFromToken_toToken(startNameToken, endNameToken).trim();
        };
        needToBuild = false;
    };

}
