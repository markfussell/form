/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTCompilationUnit.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;

import java.io.*;

/*package*/ class ASTCompilationUnit extends SimpleNode {
  ASTCompilationUnit(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTCompilationUnit(id);
  }

    //==========================================================
    //(P)=================== Manual Code =======================
    //==========================================================

    public void process (SourceConverterPrinter ostr) {
        //return;

        Token t = firstToken;
        ASTSpecialBlock bnode;

        printToken_on(t,ostr);  //Output the first of the "lastOutputToken"s

        t = processChildren(ostr,t);

        //Now handle all the rest of the tokens that were not handled by others
        //If nobody handles anything, this will print the whole token chain.
        if (t != null) {
            t = t.next;
            while (t != null) {
              printToken_on(t, ostr);
              t = t.next;
            }
        };

    }

}
