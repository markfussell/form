/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTFunctorResultType.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;


/*package*/ class ASTFunctorResultType extends SimpleNode implements FunctorInputParameterProt {
  ASTFunctorResultType(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTFunctorResultType(id);
  }


    //==========================================================
    //(P)=============== Manually Inserted  ====================
    //==========================================================


    public Token startNameToken = null;
    public Token endNameToken = null;

    //==========================================================
    //==========================================================

    public int parameterType() {return 3;};
    public String name() {return "";};
    public String initializationValue() {return null;};

    public String typeName() {
        if (typeName == null) {
            if (startNameToken == null) return "";
            typeName = newStringFromToken_toToken(startNameToken,endNameToken).trim();
        };
        return typeName;
    };

    //==========================================================
    //(P)================      PRIVATE      ====================
    //==========================================================

    protected String typeName = null;

}
