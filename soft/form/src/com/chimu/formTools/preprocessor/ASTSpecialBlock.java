/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTSpecialBlock.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;

import java.io.*;
import java.util.Vector;

/*package*/ class ASTSpecialBlock extends SimpleNode {
    static protected int nestLevel = 0;


  ASTSpecialBlock(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTSpecialBlock(id);
  }

// Manually inserted code begins here


  protected boolean isProtected = false;
  protected boolean hasSpecialFlag = false;
  protected Vector modifiers = null;

  public void setIsProtected(boolean isProtected) {
      this.isProtected = isProtected;
  };

  public void setModifiers(Vector modifiers) {
      this.modifiers = modifiers;
  };

  public void setHasSpecialFlag(boolean hasSpecialFlag) {
      this.hasSpecialFlag = hasSpecialFlag;
  };

  protected boolean hasDontAutoUnstubComment(Token t) {
    Token tt = t.specialToken;
    if (tt != null) {
      while (tt.specialToken != null) tt = tt.specialToken;
      while (tt != null) {
        String image = tt.image;
        if (image.startsWith("//")) {
            if (image.startsWith("//<DontAutoUnstub>")) {
                return true;
            };
        };
        tt = tt.next;
      }
    };
    return false;
  };

    protected boolean hasUnstub(Token t) {
        return t.image.equals("unstub");
    };

    protected boolean isProtected() {
        if (modifiers == null) return false;
//        System.out.print(modifiers);
        int size = modifiers.size();
        for (int i = 0; i<size; i++) {
            String modifier = (String) modifiers.elementAt(i);
            if (modifier.equals("protected")) return true;
            if (modifier.equals("private")) return true;
            if (modifier.equals("static")) return true;
        };
        return false;
    };

    public Token processNode (SourceConverterPrinter ostr, Token lastOutput) {
        nestLevel++;

        Token t = lastOutput.next;
//System.out.println(lastOutput.image+" compared to "+begin.image);
        while (t != firstToken) {
            printToken_on(t, ostr);
            t = t.next;
        };
        // t corresponds to the "{" of the special block.


        if (hasSpecialFlag) {
            t.image = "{ try {";
        };
        printToken_on(t, ostr);

        if (nestLevel == 1) {
            boolean dontAutoUnstub = false;
            dontAutoUnstub |= ostr.ignoredFile();
            Token lookAheadToken = t.next;  //first statement within the block
            dontAutoUnstub |= hasDontAutoUnstubComment(lookAheadToken);
            dontAutoUnstub |= hasUnstub(lookAheadToken);
            if (!isProtected() && !dontAutoUnstub) {
                ostr.print("unstub();");
            };
        }

        t = processChildren (ostr,t);

//System.out.println(t.image+" compared to "+end.image);

        if (t != lastToken) {
            t = t.next;
            while (t != lastToken) {
                printToken_on(t, ostr);
                t = t.next;
            }
        };
        // t now corresponds to the last "}" of the special block.
        if (hasSpecialFlag) {
            t.image = "} }";
        };
        printToken_on(t, ostr);
        nestLevel--;
        return t;
    }

}
