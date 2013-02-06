/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/ASTFunctorBlock.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;


import java.util.Vector;
import java.util.Hashtable;

/*package*/ class ASTFunctorBlock extends SimpleNode {
  ASTFunctorBlock(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTFunctorBlock(id);
  }


    //==========================================================
    //(P)=============== Manually Inserted  ====================
    //==========================================================

    public Token processNode (SourceConverterPrinter ostr, Token lastOutput) {
        if ((lastToken != null) && (firstToken != null)) {
             Token t = lastOutput.next;
             while (t != firstToken) {
                 printToken_on(t, ostr);
                 t = t.next;
             };
             printTokenSpecials_on(t,ostr);

             //String x, String y, Special z
             ASTFunctorParameterList parameterList = null;
             try {
                parameterList = (ASTFunctorParameterList) jjtGetChild(0);
             } catch(Exception e) {};


             if (parameterList == null) {
                startFunctor(ostr,new Vector(),null,null,null);
             } else {
                Vector parameterNames = parameterList.parameterNames();
                String resultType = parameterList.resultType();
                String className = parameterList.functorCName();
                startFunctor(ostr,parameterNames,null,resultType,className);
             };

             ostr.print(blockStatements());
                endFunctor(ostr);
             //processChildren(ostr,t);   //change over into node mode



             return lastToken;
        };
        System.err.println("Null FunctorBlock?");
        return lastOutput;
    }

   protected Token processChildren (SourceConverterPrinter ostr, Token lastOutput) {
       Token t = lastOutput;

       for (int i = 0; i < jjtGetNumChildren(); i++) {
            SimpleNode node = (SimpleNode) jjtGetChild(i);
System.out.println(this+"["+i+"] ="+node);
            t = node.processNode(ostr,t);
       };

       return t;
   }



    static String[] withStrings = {"", "With", "With_with", "With_with_with"};
    static String[] classPrefixes = {"Function","Procedure","Predicate"};
    static String[] methodPrefixes = {"value","execute","isTrue"};
    static String[] methodReturns = {"Object","void","boolean"};
    static Hashtable returnTypeIndexes = new Hashtable();
    static {
        returnTypeIndexes.put("Object",new Integer(0));
        returnTypeIndexes.put("void",new Integer(1));
        returnTypeIndexes.put("boolean",new Integer(2));
    };

    static protected void startFunctor (SourceConverterPrinter ostr,
                Vector inputParameters, Vector creationParameters,
                String returnType, String className) {
        int n = inputParameters.size();

        int returnTypeIndex = 0;
        if (returnType != null) {
            if (returnType.equals("void")) {
                returnTypeIndex = 1;
            } else if (returnType.equals("boolean")) {
                returnTypeIndex = 2;
            };
        };
        String classPrefix = classPrefixes[returnTypeIndex];
        String methodPrefix = methodPrefixes[returnTypeIndex];
        String methodReturn = methodReturns[returnTypeIndex];
        StringBuffer typeConversion = new StringBuffer();
        if (className == null) {
            className = classPrefix+n+"Arg";
        };
        ostr.print("new "+className+"() {public "+methodReturn+" "+methodPrefix+withStrings[n]);

        ostr.print("(");
        int size = inputParameters.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                ostr.print(",");
            };
            Object[] pair = (Object[]) inputParameters.elementAt(i);
            String name = (String) pair[0];
            String type = (String) pair[1];
            //String name = (String) inputParameters.elementAt(i);
            //String type = null;

            ostr.print("Object ");
            if (type != null) {
                ostr.print(name+"ObjArg"+i);
                typeConversion.append(type+" "+name+" = ("+type+") "+name+"ObjArg"+i+"; ");
            } else {
                ostr.print(name);
            };
        };

        ostr.print(")");
        ostr.print("{");
        if (typeConversion.length() > 0) {
 //           ostr.print("\n");
            ostr.print(typeConversion.toString());
            ostr.print("\n                    ");
        };

    };
    static protected void endFunctor (SourceConverterPrinter ostr) {
        ostr.print("}}");
    };
        //return end; //this.processChildren(ostr,lastOutput);
    //System.out.println("Error - this should not be called");
    //throw new Error();

    public Token startBlockStatements = null;
    public Token endBlockStatements = null;

    protected String blockStatements = null;
    public String blockStatements() {
        if (blockStatements == null) {
            if (startBlockStatements == null) return "";
            blockStatements = newStringFromToken_toToken(startBlockStatements,endBlockStatements);
        };
        return blockStatements;
    };

}
