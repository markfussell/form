/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/formTools/preprocessor/SimpleNode.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;
import java.io.*;

/*package*/ class SimpleNode implements Node {
  protected Node parent;
  protected java.util.Vector children;
  protected String identifier;
  protected Object info;

  public SimpleNode(String id) {
    identifier = id;
  }

  public static Node jjtCreate(String id) {
    return new SimpleNode(id);
  }

  public void jjtOpen() {}
  public void jjtClose() {
    if (children != null) {
      children.trimToSize();
    }
  }

  public void jjtSetParent(Node n) { parent = n; }
  public Node jjtGetParent() { return parent; }

  public void jjtAddChild(Node n) {
    if (children == null) {
      children = new java.util.Vector();
    }
    children.addElement(n);
  }
  public Node jjtGetChild(int i) {
    return (Node)children.elementAt(i);
  }

  public int jjtGetNumChildren() {
    return (children == null) ? 0 : children.size();
  }

  /* These two methods provide a very simple mechanism for attaching
     arbitrary data to the node. */

  public void setInfo(Object i) { info = i; }
  public Object getInfo() { return info; }

  /* You can override these two methods in subclasses of SimpleNode to
     customize the way the node appears when the tree is dumped.  If
     your output uses more than one line you should override
     toString(String), otherwise overriding toString() is probably all
     you need to do. */

  public String toString() { return identifier; }
  public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (java.util.Enumeration e = children.elements();
	   e.hasMoreElements();) {
	SimpleNode n = (SimpleNode)e.nextElement();
	n.dump(prefix + " ");
      }
    }
  }

    //==========================================================
    //(P)=============== Manually Inserted  ====================
    //==========================================================

    public Token firstToken, lastToken;

    //==========================================================
    //==========================================================

    public Token processNode (SourceConverterPrinter ostr, Token lastOutput) {
        return this.processChildren(ostr,lastOutput);
    //System.out.println("Error - this should not be called");
    //throw new Error();
    }

    //==========================================================
    //(P)================      PRIVATE      ====================
    //==========================================================

        /**
         * Suitable replacement for the above if you want to output your tokens.
         * Also the model for how to do your own processing.
         * Will output all the tokens up through the last recorded in for this node
         */
    protected Token processOutput(SourceConverterPrinter ostr, Token lastOutput) {
        Token t = lastOutput.next;
        while (t != firstToken) {
            printToken_on(t, ostr);
            t = t.next;
        };
        t = processChildren (ostr,t);
        if (t != lastToken) {
            do {
                printToken_on(t, ostr);
                t = t.next;
            } while (t != lastToken);
        };
        return t;
    };

    //Start at the token begin (which was the last token output)
    //move up to
   protected Token processChildren (SourceConverterPrinter ostr, Token lastOutput) {
       Token t = lastOutput;

       for (int i = 0; i < jjtGetNumChildren(); i++) {
            SimpleNode node = (SimpleNode) jjtGetChild(i);
//System.out.println(this+"["+i+"] ="+node);
            t = node.processNode(ostr,t);
       };

       return t;
   }


    //==========================================================
    //==========================================================
    //==========================================================


  // The following method prints token t, as well as all preceding
  // special tokens (essentially, white space and comments).
  // returns the next mode to use
  protected void printToken_on(Token t, SourceConverterPrinter ostr) {
    printTokenSpecials_on(t,ostr);
    ostr.print(t.image);
  }

  protected void printTokenSpecials_on(Token t, SourceConverterPrinter ostr) {
    Token tt = t.specialToken;
    if (tt != null) {
      while (tt.specialToken != null) tt = tt.specialToken;
      while (tt != null) {
        ostr.print(tt.image);
        tt = tt.next;
      }
    };
  }

    // This includes the specials before the startToken
    protected String newStringFromToken_toToken(Token startToken, Token endToken) {
        if (startToken == null) return "";

        StringBuffer stringB = new StringBuffer();

        Token t = startToken;
        while (t != endToken) {
            printToken_onBuffer(t,stringB);
            t = t.next;
        };
        printToken_onBuffer(t,stringB);
        return stringB.toString();
    };


  protected void printToken_onBuffer(Token t, StringBuffer buffer) {
    Token tt = t.specialToken;
    if (tt != null) {
      while (tt.specialToken != null) tt = tt.specialToken;
      while (tt != null) {
        buffer.append(tt.image);
        tt = tt.next;
      }
    };
    buffer.append(t.image);
  }


}
