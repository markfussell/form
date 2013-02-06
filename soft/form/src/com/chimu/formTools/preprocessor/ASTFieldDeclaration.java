/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTFieldDeclaration.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Date;
import java.io.PrintWriter;

/**
 *@version 0.9.1
 */
/*package*/ class ASTFieldDeclaration extends SimpleNode {
  ASTFieldDeclaration(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTFieldDeclaration(id);
  }

    //==========================================================
    //(P)=============== Manually Inserted  ====================
    //==========================================================

    public Token firstTypeVariableToken = null;
    public Token lastTypeVariableToken = null;

    public Token processNode (SourceConverterPrinter ostr, Token lastOutput) {
        return processOutput(ostr,lastOutput);
    //System.out.println("Error - this should not be called");
    //throw new Error();
    }
    
    static InfoBuilder ib = new InfoBuilderC();
    static public void setupInfoBuilder(InfoBuilder anIb) {
        ib = anIb;    
    }

    //==========================================================
    //(P)================      PRIVATE      ====================
    //==========================================================

    protected Token processOutput(SourceConverterPrinter ostr, Token lastOutput) {
        Token t = lastOutput;
 /*       if (t != firstToken) {
            do {
                t = t.next;
                printToken_on(t, ostr);
            } while (t != firstToken);
        };

        t = processChildren (ostr,t);*/

        if (t != lastToken) {
            do {
                t = t.next;
                printToken_on(t, ostr);
            } while (t != lastToken);
        };
        if (shouldOutput()) {
            ib.printDeclarations(ostr,typeName(),variableName());
        };
        return t;
    };


    public String variableName() {
        if (needToBuild) buildValues();
        return variableName;
    };

    public String typeName() {
        if (needToBuild) buildValues();
        return typeName;
    };

    public void setModifiers(Vector modifiers) {
        this.modifiers = modifiers;
    };

    protected boolean shouldOutput() {
        int size = modifiers.size();
        for (int i = 0; i < size; i++) {
            String modifier = (String) modifiers.elementAt(i);
            if (modifier.equals("transient")) return false;
            if (modifier.equals("static")) return false;
            if (modifier.equals("final")) return false;
        };
        return true;
    };

    protected String variableName = null;
    protected String typeName = null;
    protected Vector modifiers = null;

    protected boolean needToBuild = true;

    protected void buildValues() {
        if (firstTypeVariableToken != null) {
            variableName = newStringFromToken_toToken(firstTypeVariableToken, lastTypeVariableToken).trim();

            StringTokenizer st = new StringTokenizer(variableName);
            variableName = st.nextToken();
            if (st.hasMoreTokens()) {
                typeName = variableName;
                variableName = st.nextToken();
            } else {
                throw new NotImplementedYetException("Not implemented for interesting variables");
            };
        };
        needToBuild = false;
    };


}

