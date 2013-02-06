/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTPackageDeclaration.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;


public class ASTPackageDeclaration extends SimpleNode {
  ASTPackageDeclaration(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTPackageDeclaration(id);
  }

    //==========================================================
    //(P)=================== Manual Code =======================
    //==========================================================

    public Token processNode (SourceConverterPrinter ostr, Token lastOutput) {
        ASTName node = (ASTName) jjtGetChild(0);

        ostr.setPackageName(node.name());
        if (hasFormIgnoreComment(lastOutput)) {
            ostr.ignoreFile();
            //System.out.println("Yes: "+lastOutput.image);
            //System.out.println("Yes: "+lastOutput.next.image);
        } else {
            //System.out.println("No: "+lastOutput.image);
            //System.out.println("No: "+lastOutput.next.image);
        }

        return lastOutput;
    }


    protected boolean hasFormIgnoreComment(Token t) {
        Token tt = t.specialToken;
        if (tt != null) {
          while (tt.specialToken != null) tt = tt.specialToken;
          while (tt != null) {
            String image = tt.image;
 //            System.out.println("Check: "+image);
            if (image.startsWith("//")) {
                if (image.startsWith("//<FormIgnore>")) {
                    return true;
                };
            };
            tt = tt.next;
          }
        };
        return false;
    };


}
