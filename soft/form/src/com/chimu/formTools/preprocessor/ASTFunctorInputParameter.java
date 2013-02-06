/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTFunctorInputParameter.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;


import java.util.StringTokenizer;

/*package*/ class ASTFunctorInputParameter extends SimpleNode implements FunctorInputParameterProt {
  ASTFunctorInputParameter(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTFunctorInputParameter(id);
  }


    //==========================================================
    //(P)=============== Manually Inserted  ====================
    //==========================================================

    public Token startNameToken = null;
    public Token endNameToken = null;

    public Token startPrimaryPrefix = null;
    public Token endPrimaryPrefix = null;

    public int parameterType() {return 1;};

    public String initializationValue() {
        if (needToBuild) buildValues();
        return initializationValue;
    };
    public String name() {
        if (needToBuild) buildValues();
        return name;
    };
    public String typeName() {
        if (needToBuild) buildValues();
        return typeName;
    };

    public String toString() { if (needToBuild) buildValues(); return identifier+"("+name()+":"+hasEquals+")"; }

    public void setHasEquals(boolean hasEquals) {
        this.hasEquals = hasEquals;
    };

    //==========================================================
    //(P)================      PRIVATE      ====================
    //==========================================================


    protected boolean hasEquals = false;

    protected Token processChildren (SourceConverterPrinter ostr, Token lastOutput) {
       Token t = lastOutput;

       for (int i = 0; i < jjtGetNumChildren(); i++) {
            SimpleNode node = (SimpleNode) jjtGetChild(i);
System.out.println(this+"["+i+"] ="+node);
            t = node.processNode(ostr,t);
       };

       return t;
    }



    protected String name = null;
    protected String typeName = null;
    protected String initializationValue = null;

    protected boolean needToBuild = true;

    protected void buildValues() {
        if (startPrimaryPrefix != null) {
            initializationValue = newStringFromToken_toToken(startPrimaryPrefix,endPrimaryPrefix);
        };
        if (startNameToken != null) {
            name = newStringFromToken_toToken(startNameToken, endNameToken).trim();

            StringTokenizer st = new StringTokenizer(name);
            name = st.nextToken();
            if (st.hasMoreTokens()) {
                typeName = name;
                name=st.nextToken();
            };
        };
        needToBuild = false;
    };




}
