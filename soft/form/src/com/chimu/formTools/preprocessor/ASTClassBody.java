/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTClassBody.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;
import com.chimu.kernel.functors.*;


public class ASTClassBody extends SimpleNode {
    ASTClassBody(String id) {
        super(id);
    }

    public static Node jjtCreate(String id) {
        return new ASTClassBody(id);
    }
  
    //==========================================================
    //(P)=============== Manually Inserted  ====================
    //==========================================================

    public Token processNode (SourceConverterPrinter ostr, Token lastOutput) {
        Token t = lastOutput.next;
        while (t != firstToken) {
            printToken_on(t, ostr);
            t = t.next;
        };
        // t corresponds to the "{" of the class block.
        printToken_on(t, ostr);
 
        t = processChildren (ostr,t);

        if (t != lastToken) {
            t = t.next;
            while (t != lastToken) {
                printToken_on(t, ostr);
                t = t.next;
            }
        };
        
        // t now corresponds to the last "}" of the class block.
        
        if (ostr.needsToOutputBindingBlock()) {
            t.image="";
            printToken_on(t, ostr);
            
            String tagName = "generateobjectbinding";
            //Next few lines are a bit of a hack... Direct access to protecteds
            StringBuffer buffer = (StringBuffer) ostr.buffers.get(tagName);
            if (buffer != null) {
                ostr.println("    //<GenerateObjectBinding{");
                ostr.streamPrint(buffer.toString());
                ostr.println("    //}GenerateObjectBinding>");
            } else {
                Procedure1Arg procedure = (Procedure1Arg) ostr.procedures.get(tagName);
                if (procedure != null) {
                    ostr.println("    //<GenerateObjectBinding{");
                    procedure.executeWith(ostr);
                    ostr.println("    //}GenerateObjectBinding>");
                };
            };
            ostr.print("}");
            t.image="}";
        } else {
            printToken_on(t, ostr);
        }
        return t;
    }

  
}
