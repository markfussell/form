/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTFunctorParameterList.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;

import java.util.Vector;

/*package*/ class ASTFunctorParameterList extends SimpleNode {
  ASTFunctorParameterList(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTFunctorParameterList(id);
  }

    //==========================================================
    //(P)=============== Manually Inserted  ====================
    //==========================================================

    public Vector parameterNames() {
        if (needToBuild) buildInformation();
        return parameterNames;
    };

    public String resultType() {
        if (needToBuild) buildInformation();
        return resultType;
    };

    public String functorCName() {
        if (needToBuild) buildInformation();
        return functorCName;
    };

    //==========================================================
    //(P)================      PRIVATE      ====================
    //==========================================================

    protected Vector parameterNames = new Vector();
    protected String resultType = null;
    protected String functorCName = null;
    protected boolean needToBuild = true;

    protected void buildInformation() {
        for (int i = 0; i < jjtGetNumChildren(); i++) {
            try {
                FunctorInputParameterProt node = (FunctorInputParameterProt) jjtGetChild(i);
                int parameterType = node.parameterType();
                if (parameterType == 1) {
                    parameterNames.addElement(new Object[] {node.name(),node.typeName()});
                } else if (parameterType == 2) {
                    functorCName = node.typeName();
                } else if (parameterType == 3) {
                    resultType = node.typeName();
                };
            } catch (Exception e) {};
        };
        needToBuild = false;
    };

/*
   protected Token processChildren (SourceConverterPrinter ostr, Token lastOutput) {
       Token t = lastOutput;

       for (int i = 0; i < jjtGetNumChildren(); i++) {
            SimpleNode node = (SimpleNode) jjtGetChild(i);
System.out.print(this+"["+i+"] ="+node);
            try {
                String name = ((FunctorInputParameterProt) node).name();
System.out.print(name);
            } catch (Exception e) {};
System.out.println();
            t = node.processNode(ostr,t);
       };

       return t;
   }
*/
}
