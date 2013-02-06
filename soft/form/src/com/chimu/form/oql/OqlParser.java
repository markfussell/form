/*======================================================================
**
**  File: chimu/form/oql/OqlParser.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

/**
FORM's OQL Parser.
**/
/*package*/ class OqlParser implements OqlParserConstants {
  /*package*/ static void main(String args[]) {
    OqlParser parser;
    if (args.length == 0) {
      System.out.println("OqlParser Parser:  Reading from standard input . . .");
      parser = new OqlParser(new java.io.DataInputStream(System.in));
    } else if (args.length == 1) {
      System.out.println("OqlParser Parser:  Reading from file " + args[0] + " . . .");
      try {
        parser = new OqlParser(new java.io.DataInputStream(new java.io.FileInputStream(args[0])));
      } catch (java.io.FileNotFoundException e) {
        System.out.println("OqlParser Parser Version 1.1:  File " + args[0] + " not found.");
        return;
      }
    } else {
      System.out.println("OqlParser Parser:  Usage is one of:");
      System.out.println("         java OqlParser < inputfile");
      System.out.println("OR");
      System.out.println("         java OqlParser inputfile");
      return;
    }
    try {
      parser.Query();
     ((SimpleNode) parser.jjtree.rootNode()).dump("");
      System.out.println("OqlParser Parser:  Oql program parsed successfully.");
    } catch (ParseError e) {
      System.out.println("OqlParser Parser:  Encountered errors during parse.");
    }
  }
  /*package*/ JJTOqlParserState jjtree = new JJTOqlParserState();

/*****************************************
 * THE JAVA LANGUAGE GRAMMAR STARTS HERE *
 *****************************************/

/*
 * Program structuring syntax follows.
 */
  final /*package*/ void Query() throws ParseError {
    if (jj_mask_2[getToken(1).kind]) {
      SelectQuery();
    } else {
      jj_expLA1[2] = jj_gen;
      if (jj_mask_1[getToken(1).kind]) {
        FromQuery();
      } else {
        jj_expLA1[1] = jj_gen;
        if (jj_mask_0[getToken(1).kind]) {
          WhereQuery();
        } else {
          jj_expLA1[0] = jj_gen;
          jj_consume_token(-1);
          throw new ParseError();
        }
      }
    }
    jj_consume_token(0);
  }

  static boolean[] jj_mask_0 = new boolean[52];
  static {
    jj_mask_0[WHERE] = true;
  }
  static boolean[] jj_mask_1 = new boolean[52];
  static {
    jj_mask_1[FROM] = true;
  }
  static boolean[] jj_mask_2 = new boolean[52];
  static {
    jj_mask_2[SELECT] = true;
  }

  final /*package*/ void SelectQuery() throws ParseError {
jjtree.openIndefiniteNode(ASTSelectQuery.jjtCreate("SelectQuery"));
    SelectClause();
    FromClause();
    if (jj_mask_3[getToken(1).kind]) {
      WhereClause();
    } else {
      jj_expLA1[3] = jj_gen;
      ;
    }
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_3 = new boolean[52];
  static {
    jj_mask_3[WHERE] = true;
  }

  final /*package*/ void FromQuery() throws ParseError {
jjtree.openIndefiniteNode(ASTFromQuery.jjtCreate("FromQuery"));
    FromClause();
    if (jj_mask_4[getToken(1).kind]) {
      WhereClause();
    } else {
      jj_expLA1[4] = jj_gen;
      ;
    }
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_4 = new boolean[52];
  static {
    jj_mask_4[WHERE] = true;
  }

  final /*package*/ void WhereQuery() throws ParseError {
jjtree.openIndefiniteNode(ASTWhereQuery.jjtCreate("WhereQuery"));
    WhereClause();
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  final /*package*/ void SelectClause() throws ParseError {
jjtree.openIndefiniteNode(ASTSelectClause.jjtCreate("SelectClause"));
    jj_consume_token(SELECT);
    ExpressionList();
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  final /*package*/ void FromClause() throws ParseError {
jjtree.openIndefiniteNode(ASTFromClause.jjtCreate("FromClause"));
    jj_consume_token(FROM);
    RangeList();
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  final /*package*/ void WhereClause() throws ParseError {
jjtree.openIndefiniteNode(ASTWhereClause.jjtCreate("WhereClause"));
    jj_consume_token(WHERE);
    Condition();
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

// **********************************************************
// **********************************************************
// **********************************************************
  final /*package*/ void RangeList() throws ParseError {
jjtree.openIndefiniteNode(ASTRangeList.jjtCreate("RangeList"));
    Range();
    label_1:
    while (true) {
      if (jj_mask_5[getToken(1).kind]) {
        ;
      } else {
        jj_expLA1[5] = jj_gen;
        break label_1;
      }
      jj_consume_token(COMMA);
      Range();
    }
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_5 = new boolean[52];
  static {
    jj_mask_5[COMMA] = true;
  }

  final /*package*/ void Range() throws ParseError {
jjtree.openIndefiniteNode(ASTRange.jjtCreate("Range"));
    MessageList();
    if (jj_mask_7[getToken(1).kind]) {
      if (jj_mask_6[getToken(1).kind]) {
        jj_consume_token(AS);
      } else {
        jj_expLA1[6] = jj_gen;
        ;
      }
      Name();
    } else {
      jj_expLA1[7] = jj_gen;
      ;
    }
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_6 = new boolean[52];
  static {
    jj_mask_6[AS] = true;
  }
  static boolean[] jj_mask_7 = new boolean[52];
  static {
    jj_mask_7[AS] =
    jj_mask_7[DOLLAR] =
    jj_mask_7[COLON] =
    jj_mask_7[IDENTIFIER] = true;
  }

//    Name()
//    (<IN> MessageList())?
// **********************************************************

// These are the condition expressions that can be in the where part of a query
  final /*package*/ void ExpressionList() throws ParseError {
    LabeledExpression();
    label_2:
    while (true) {
      if (jj_mask_8[getToken(1).kind]) {
        ;
      } else {
        jj_expLA1[8] = jj_gen;
        break label_2;
      }
      jj_consume_token(COMMA);
      LabeledExpression();
    }
  }

  static boolean[] jj_mask_8 = new boolean[52];
  static {
    jj_mask_8[COMMA] = true;
  }

  final /*package*/ void LabeledExpression() throws ParseError {
jjtree.openGTNode(ASTLabeledExpression.jjtCreate("LabeledExpression"));
    Expression();
    if (jj_mask_9[getToken(1).kind]) {
      jj_consume_token(AS);
      Name();
    } else {
      jj_expLA1[9] = jj_gen;
      ;
    }
jjtree.closeGTNode(1);
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_9 = new boolean[52];
  static {
    jj_mask_9[AS] = true;
  }

  final /*package*/ void Expression() throws ParseError {
    InclusiveOrCondition();
  }

  final /*package*/ void Condition() throws ParseError {
    InclusiveOrCondition();
  }

  final /*package*/ void InclusiveOrCondition() throws ParseError {
jjtree.openGTNode(ASTInclusiveOrCondition.jjtCreate("InclusiveOrCondition"));
    ExclusiveOrCondition();
    label_3:
    while (true) {
      if (jj_mask_10[getToken(1).kind]) {
        ;
      } else {
        jj_expLA1[10] = jj_gen;
        break label_3;
      }
      jj_consume_token(OR);
      ExclusiveOrCondition();
    }
jjtree.closeGTNode(1);
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_10 = new boolean[52];
  static {
    jj_mask_10[OR] = true;
  }

  final /*package*/ void ExclusiveOrCondition() throws ParseError {
jjtree.openGTNode(ASTExclusiveOrCondition.jjtCreate("ExclusiveOrCondition"));
    AndCondition();
    label_4:
    while (true) {
      if (jj_mask_11[getToken(1).kind]) {
        ;
      } else {
        jj_expLA1[11] = jj_gen;
        break label_4;
      }
      jj_consume_token(XOR);
      AndCondition();
    }
jjtree.closeGTNode(1);
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_11 = new boolean[52];
  static {
    jj_mask_11[XOR] = true;
  }

  final /*package*/ void AndCondition() throws ParseError {
jjtree.openGTNode(ASTAndCondition.jjtCreate("AndCondition"));
    ComparisonCondition();
    label_5:
    while (true) {
      if (jj_mask_12[getToken(1).kind]) {
        ;
      } else {
        jj_expLA1[12] = jj_gen;
        break label_5;
      }
      jj_consume_token(AND);
      ComparisonCondition();
    }
jjtree.closeGTNode(1);
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_12 = new boolean[52];
  static {
    jj_mask_12[AND] = true;
  }

  final /*package*/ void ComparisonCondition() throws ParseError {
Token t;
jjtree.openGTNode(ASTComparisonCondition.jjtCreate("ComparisonCondition"));
    AdditiveExpression();
    if (jj_mask_21[getToken(1).kind]) {
      if (jj_mask_20[getToken(1).kind]) {
        jj_consume_token(EQ);
      } else {
        jj_expLA1[20] = jj_gen;
        if (jj_mask_19[getToken(1).kind]) {
          jj_consume_token(EQ2);
        } else {
          jj_expLA1[19] = jj_gen;
          if (jj_mask_18[getToken(1).kind]) {
            jj_consume_token(NE);
          } else {
            jj_expLA1[18] = jj_gen;
            if (jj_mask_17[getToken(1).kind]) {
              jj_consume_token(NE2);
            } else {
              jj_expLA1[17] = jj_gen;
              if (jj_mask_16[getToken(1).kind]) {
                jj_consume_token(LT);
              } else {
                jj_expLA1[16] = jj_gen;
                if (jj_mask_15[getToken(1).kind]) {
                  jj_consume_token(GT);
                } else {
                  jj_expLA1[15] = jj_gen;
                  if (jj_mask_14[getToken(1).kind]) {
                    jj_consume_token(LE);
                  } else {
                    jj_expLA1[14] = jj_gen;
                    if (jj_mask_13[getToken(1).kind]) {
                      jj_consume_token(GE);
                    } else {
                      jj_expLA1[13] = jj_gen;
                      jj_consume_token(-1);
                      throw new ParseError();
                    }
                  }
                }
              }
            }
          }
        }
      }
                                                                                                try {
ASTComparisonCondition jjtThis = (ASTComparisonCondition)jjtree.currentNode();
t = getToken(0);
            jjtThis.setName(t.image);
} finally {
}
      AdditiveExpression();
    } else {
      jj_expLA1[21] = jj_gen;
      ;
    }
jjtree.closeGTNode(1);
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_13 = new boolean[52];
  static {
    jj_mask_13[GE] = true;
  }
  static boolean[] jj_mask_14 = new boolean[52];
  static {
    jj_mask_14[LE] = true;
  }
  static boolean[] jj_mask_15 = new boolean[52];
  static {
    jj_mask_15[GT] = true;
  }
  static boolean[] jj_mask_16 = new boolean[52];
  static {
    jj_mask_16[LT] = true;
  }
  static boolean[] jj_mask_17 = new boolean[52];
  static {
    jj_mask_17[NE2] = true;
  }
  static boolean[] jj_mask_18 = new boolean[52];
  static {
    jj_mask_18[NE] = true;
  }
  static boolean[] jj_mask_19 = new boolean[52];
  static {
    jj_mask_19[EQ2] = true;
  }
  static boolean[] jj_mask_20 = new boolean[52];
  static {
    jj_mask_20[EQ] = true;
  }
  static boolean[] jj_mask_21 = new boolean[52];
  static {
    jj_mask_21[EQ] =
    jj_mask_21[EQ2] =
    jj_mask_21[NE] =
    jj_mask_21[NE2] =
    jj_mask_21[GT] =
    jj_mask_21[LT] =
    jj_mask_21[LE] =
    jj_mask_21[GE] = true;
  }

  final /*package*/ void AdditiveExpression() throws ParseError {
Token t;
jjtree.openGTNode(ASTAdditiveExpression.jjtCreate("AdditiveExpression"));
    MultiplicativeExpression();
    if (jj_mask_24[getToken(1).kind]) {
      if (jj_mask_23[getToken(1).kind]) {
        jj_consume_token(PLUS);
      } else {
        jj_expLA1[23] = jj_gen;
        if (jj_mask_22[getToken(1).kind]) {
          jj_consume_token(MINUS);
        } else {
          jj_expLA1[22] = jj_gen;
          jj_consume_token(-1);
          throw new ParseError();
        }
      }
                                                         try {
ASTAdditiveExpression jjtThis = (ASTAdditiveExpression)jjtree.currentNode();
t = getToken(0);
            jjtThis.setName(t.image);
} finally {
}
      MultiplicativeExpression();
    } else {
      jj_expLA1[24] = jj_gen;
      ;
    }
jjtree.closeGTNode(1);
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_22 = new boolean[52];
  static {
    jj_mask_22[MINUS] = true;
  }
  static boolean[] jj_mask_23 = new boolean[52];
  static {
    jj_mask_23[PLUS] = true;
  }
  static boolean[] jj_mask_24 = new boolean[52];
  static {
    jj_mask_24[PLUS] =
    jj_mask_24[MINUS] = true;
  }

  final /*package*/ void MultiplicativeExpression() throws ParseError {
Token t;
jjtree.openGTNode(ASTMultiplicativeExpression.jjtCreate("MultiplicativeExpression"));
    UnarySignExpression();
    if (jj_mask_28[getToken(1).kind]) {
      if (jj_mask_27[getToken(1).kind]) {
        jj_consume_token(TIMES);
      } else {
        jj_expLA1[27] = jj_gen;
        if (jj_mask_26[getToken(1).kind]) {
          jj_consume_token(DIVIDE);
        } else {
          jj_expLA1[26] = jj_gen;
          if (jj_mask_25[getToken(1).kind]) {
            jj_consume_token(REM);
          } else {
            jj_expLA1[25] = jj_gen;
            jj_consume_token(-1);
            throw new ParseError();
          }
        }
      }
                                                               try {
ASTMultiplicativeExpression jjtThis = (ASTMultiplicativeExpression)jjtree.currentNode();
t = getToken(0);
            jjtThis.setName(t.image);
} finally {
}
      UnarySignExpression();
    } else {
      jj_expLA1[28] = jj_gen;
      ;
    }
jjtree.closeGTNode(1);
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_25 = new boolean[52];
  static {
    jj_mask_25[REM] = true;
  }
  static boolean[] jj_mask_26 = new boolean[52];
  static {
    jj_mask_26[DIVIDE] = true;
  }
  static boolean[] jj_mask_27 = new boolean[52];
  static {
    jj_mask_27[TIMES] = true;
  }
  static boolean[] jj_mask_28 = new boolean[52];
  static {
    jj_mask_28[TIMES] =
    jj_mask_28[DIVIDE] =
    jj_mask_28[REM] = true;
  }

  final /*package*/ void UnarySignExpression() throws ParseError {
Token t;
    if (jj_mask_32[getToken(1).kind]) {
jjtree.openIndefiniteNode(ASTUnarySignExpression.jjtCreate("UnarySignExpression"));
      if (jj_mask_31[getToken(1).kind]) {
        jj_consume_token(PLUS);
      } else {
        jj_expLA1[31] = jj_gen;
        if (jj_mask_30[getToken(1).kind]) {
          jj_consume_token(MINUS);
        } else {
          jj_expLA1[30] = jj_gen;
          jj_consume_token(-1);
          throw new ParseError();
        }
      }
                           try {
ASTUnarySignExpression jjtThis = (ASTUnarySignExpression)jjtree.currentNode();
t = getToken(0);
            jjtThis.setName(t.image);
} finally {
}
      UnaryNotCondition();
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
    } else {
      jj_expLA1[32] = jj_gen;
      if (jj_mask_29[getToken(1).kind]) {
        UnaryNotCondition();
      } else {
        jj_expLA1[29] = jj_gen;
        jj_consume_token(-1);
        throw new ParseError();
      }
    }
  }

  static boolean[] jj_mask_29 = new boolean[52];
  static {
    jj_mask_29[BANG] =
    jj_mask_29[TILDE] =
    jj_mask_29[NULL] =
    jj_mask_29[TRUE] =
    jj_mask_29[FALSE] =
    jj_mask_29[DOLLAR] =
    jj_mask_29[COLON] =
    jj_mask_29[IDENTIFIER] =
    jj_mask_29[LPAREN] =
    jj_mask_29[INTEGER_LITERAL] =
    jj_mask_29[FLOATING_POINT_LITERAL] =
    jj_mask_29[STRING_LITERAL2] =
    jj_mask_29[STRING_LITERAL] = true;
  }
  static boolean[] jj_mask_30 = new boolean[52];
  static {
    jj_mask_30[MINUS] = true;
  }
  static boolean[] jj_mask_31 = new boolean[52];
  static {
    jj_mask_31[PLUS] = true;
  }
  static boolean[] jj_mask_32 = new boolean[52];
  static {
    jj_mask_32[PLUS] =
    jj_mask_32[MINUS] = true;
  }

  final /*package*/ void UnaryNotCondition() throws ParseError {
Token t;
    if (jj_mask_36[getToken(1).kind]) {
jjtree.openIndefiniteNode(ASTUnaryNotCondition.jjtCreate("UnaryNotCondition"));
      if (jj_mask_35[getToken(1).kind]) {
        jj_consume_token(TILDE);
      } else {
        jj_expLA1[35] = jj_gen;
        if (jj_mask_34[getToken(1).kind]) {
          jj_consume_token(BANG);
        } else {
          jj_expLA1[34] = jj_gen;
          jj_consume_token(-1);
          throw new ParseError();
        }
      }
                           try {
ASTUnaryNotCondition jjtThis = (ASTUnaryNotCondition)jjtree.currentNode();
t = getToken(0);
            jjtThis.setName(t.image);
} finally {
}
      PrimaryPrefix();
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
    } else {
      jj_expLA1[36] = jj_gen;
      if (jj_mask_33[getToken(1).kind]) {
        PrimaryPrefix();
      } else {
        jj_expLA1[33] = jj_gen;
        jj_consume_token(-1);
        throw new ParseError();
      }
    }
  }

  static boolean[] jj_mask_33 = new boolean[52];
  static {
    jj_mask_33[NULL] =
    jj_mask_33[TRUE] =
    jj_mask_33[FALSE] =
    jj_mask_33[DOLLAR] =
    jj_mask_33[COLON] =
    jj_mask_33[IDENTIFIER] =
    jj_mask_33[LPAREN] =
    jj_mask_33[INTEGER_LITERAL] =
    jj_mask_33[FLOATING_POINT_LITERAL] =
    jj_mask_33[STRING_LITERAL2] =
    jj_mask_33[STRING_LITERAL] = true;
  }
  static boolean[] jj_mask_34 = new boolean[52];
  static {
    jj_mask_34[BANG] = true;
  }
  static boolean[] jj_mask_35 = new boolean[52];
  static {
    jj_mask_35[TILDE] = true;
  }
  static boolean[] jj_mask_36 = new boolean[52];
  static {
    jj_mask_36[BANG] =
    jj_mask_36[TILDE] = true;
  }

  final /*package*/ void PrimaryPrefix() throws ParseError {
Token t;
    if (jj_mask_39[getToken(1).kind]) {
      Literal();
    } else {
      jj_expLA1[39] = jj_gen;
      if (jj_mask_38[getToken(1).kind]) {
        MessageList();
      } else {
        jj_expLA1[38] = jj_gen;
        if (jj_mask_37[getToken(1).kind]) {
          jj_consume_token(LPAREN);
          Condition();
          jj_consume_token(RPAREN);
        } else {
          jj_expLA1[37] = jj_gen;
          jj_consume_token(-1);
          throw new ParseError();
        }
      }
    }
  }

  static boolean[] jj_mask_37 = new boolean[52];
  static {
    jj_mask_37[LPAREN] = true;
  }
  static boolean[] jj_mask_38 = new boolean[52];
  static {
    jj_mask_38[DOLLAR] =
    jj_mask_38[COLON] =
    jj_mask_38[IDENTIFIER] = true;
  }
  static boolean[] jj_mask_39 = new boolean[52];
  static {
    jj_mask_39[NULL] =
    jj_mask_39[TRUE] =
    jj_mask_39[FALSE] =
    jj_mask_39[INTEGER_LITERAL] =
    jj_mask_39[FLOATING_POINT_LITERAL] =
    jj_mask_39[STRING_LITERAL2] =
    jj_mask_39[STRING_LITERAL] = true;
  }

// **********************************************************
  final /*package*/ void MessageList() throws ParseError {
jjtree.openGTNode(ASTMessageList.jjtCreate("MessageList"));
    Name();
    label_6:
    while (true) {
      if (jj_mask_40[getToken(1).kind]) {
        ;
      } else {
        jj_expLA1[40] = jj_gen;
        break label_6;
      }
      Message();
    }
jjtree.closeGTNode(1);
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_40 = new boolean[52];
  static {
    jj_mask_40[DOT] =
    jj_mask_40[RARROW] = true;
  }

  final /*package*/ void Name() throws ParseError {
Token t;
    if (jj_mask_43[getToken(1).kind]) {
jjtree.openIndefiniteNode(ASTName.jjtCreate("Name"));
      t = jj_consume_token(IDENTIFIER);
jjtree.closeIndefiniteNode();
  try {
ASTName jjtThis = (ASTName)jjtree.currentNode();
jjtThis.setName(t.image);
} finally {
jjtree.updateCurrentNode(1);
}
    } else {
      jj_expLA1[43] = jj_gen;
      if (jj_mask_42[getToken(1).kind]) {
        UnboundName();
      } else {
        jj_expLA1[42] = jj_gen;
        if (jj_mask_41[getToken(1).kind]) {
          UnboundIndex();
        } else {
          jj_expLA1[41] = jj_gen;
          jj_consume_token(-1);
          throw new ParseError();
        }
      }
    }
  }

  static boolean[] jj_mask_41 = new boolean[52];
  static {
    jj_mask_41[DOLLAR] = true;
  }
  static boolean[] jj_mask_42 = new boolean[52];
  static {
    jj_mask_42[COLON] = true;
  }
  static boolean[] jj_mask_43 = new boolean[52];
  static {
    jj_mask_43[IDENTIFIER] = true;
  }

  final /*package*/ void UnboundName() throws ParseError {
Token t;
jjtree.openIndefiniteNode(ASTUnboundName.jjtCreate("UnboundName"));
    jj_consume_token(COLON);
    t = jj_consume_token(IDENTIFIER);
jjtree.closeIndefiniteNode();
  try {
ASTUnboundName jjtThis = (ASTUnboundName)jjtree.currentNode();
jjtThis.setName(t.image);
} finally {
jjtree.updateCurrentNode(1);
}
  }

  final /*package*/ void UnboundIndex() throws ParseError {
Token t;
jjtree.openIndefiniteNode(ASTUnboundIndex.jjtCreate("UnboundIndex"));
    jj_consume_token(DOLLAR);
    t = jj_consume_token(INTEGER_LITERAL);
jjtree.closeIndefiniteNode();
  try {
ASTUnboundIndex jjtThis = (ASTUnboundIndex)jjtree.currentNode();
jjtThis.setName(t.image);
} finally {
jjtree.updateCurrentNode(1);
}
  }

  final /*package*/ void Message() throws ParseError {
Token t;
jjtree.openIndefiniteNode(ASTMessage.jjtCreate("Message"));
    if (jj_mask_45[getToken(1).kind]) {
      jj_consume_token(DOT);
    } else {
      jj_expLA1[45] = jj_gen;
      if (jj_mask_44[getToken(1).kind]) {
        jj_consume_token(RARROW);
      } else {
        jj_expLA1[44] = jj_gen;
        jj_consume_token(-1);
        throw new ParseError();
      }
    }
    t = jj_consume_token(IDENTIFIER);
                                          try {
ASTMessage jjtThis = (ASTMessage)jjtree.currentNode();
jjtThis.setName(t.image);
} finally {
}
    if (jj_mask_46[getToken(1).kind]) {
      Arguments();
    } else {
      jj_expLA1[46] = jj_gen;
      ;
    }
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_44 = new boolean[52];
  static {
    jj_mask_44[RARROW] = true;
  }
  static boolean[] jj_mask_45 = new boolean[52];
  static {
    jj_mask_45[DOT] = true;
  }
  static boolean[] jj_mask_46 = new boolean[52];
  static {
    jj_mask_46[LPAREN] = true;
  }

  final /*package*/ void Arguments() throws ParseError {
jjtree.openIndefiniteNode(ASTArguments.jjtCreate("Arguments"));
    jj_consume_token(LPAREN);
    if (jj_mask_47[getToken(1).kind]) {
      ArgumentList();
    } else {
      jj_expLA1[47] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_47 = new boolean[52];
  static {
    jj_mask_47[BANG] =
    jj_mask_47[TILDE] =
    jj_mask_47[PLUS] =
    jj_mask_47[MINUS] =
    jj_mask_47[NULL] =
    jj_mask_47[TRUE] =
    jj_mask_47[FALSE] =
    jj_mask_47[DOLLAR] =
    jj_mask_47[COLON] =
    jj_mask_47[IDENTIFIER] =
    jj_mask_47[LPAREN] =
    jj_mask_47[INTEGER_LITERAL] =
    jj_mask_47[FLOATING_POINT_LITERAL] =
    jj_mask_47[STRING_LITERAL2] =
    jj_mask_47[STRING_LITERAL] = true;
  }

  final /*package*/ void ArgumentList() throws ParseError {
jjtree.openIndefiniteNode(ASTArgumentList.jjtCreate("ArgumentList"));
    Expression();
    label_7:
    while (true) {
      if (jj_mask_48[getToken(1).kind]) {
        ;
      } else {
        jj_expLA1[48] = jj_gen;
        break label_7;
      }
      jj_consume_token(COMMA);
      Expression();
    }
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  static boolean[] jj_mask_48 = new boolean[52];
  static {
    jj_mask_48[COMMA] = true;
  }

  final /*package*/ void Literal() throws ParseError {
Token t;
    if (jj_mask_54[getToken(1).kind]) {
jjtree.openIndefiniteNode(ASTIntegerLiteral.jjtCreate("IntegerLiteral"));
      t = jj_consume_token(INTEGER_LITERAL);
jjtree.closeIndefiniteNode();
  try {
ASTIntegerLiteral jjtThis = (ASTIntegerLiteral)jjtree.currentNode();
jjtThis.setName(t.image);
} finally {
jjtree.updateCurrentNode(1);
}
    } else {
      jj_expLA1[54] = jj_gen;
      if (jj_mask_53[getToken(1).kind]) {
jjtree.openIndefiniteNode(ASTFloatingPointLiteral.jjtCreate("FloatingPointLiteral"));
        t = jj_consume_token(FLOATING_POINT_LITERAL);
jjtree.closeIndefiniteNode();
  try {
ASTFloatingPointLiteral jjtThis = (ASTFloatingPointLiteral)jjtree.currentNode();
jjtThis.setName(t.image);
} finally {
jjtree.updateCurrentNode(1);
}
      } else {
        jj_expLA1[53] = jj_gen;
        if (jj_mask_52[getToken(1).kind]) {
jjtree.openIndefiniteNode(ASTStringLiteral.jjtCreate("StringLiteral"));
          t = jj_consume_token(STRING_LITERAL2);
jjtree.closeIndefiniteNode();
  try {
ASTStringLiteral jjtThis = (ASTStringLiteral)jjtree.currentNode();
jjtThis.setName(t.image);
} finally {
jjtree.updateCurrentNode(1);
}
        } else {
          jj_expLA1[52] = jj_gen;
          if (jj_mask_51[getToken(1).kind]) {
jjtree.openIndefiniteNode(ASTStringLiteral.jjtCreate("StringLiteral"));
            t = jj_consume_token(STRING_LITERAL);
jjtree.closeIndefiniteNode();
  try {
ASTStringLiteral jjtThis = (ASTStringLiteral)jjtree.currentNode();
jjtThis.setName(t.image);
} finally {
jjtree.updateCurrentNode(1);
}
          } else {
            jj_expLA1[51] = jj_gen;
            if (jj_mask_50[getToken(1).kind]) {
              BooleanLiteral();
            } else {
              jj_expLA1[50] = jj_gen;
              if (jj_mask_49[getToken(1).kind]) {
                NullLiteral();
              } else {
                jj_expLA1[49] = jj_gen;
                jj_consume_token(-1);
                throw new ParseError();
              }
            }
          }
        }
      }
    }
  }

  static boolean[] jj_mask_49 = new boolean[52];
  static {
    jj_mask_49[NULL] = true;
  }
  static boolean[] jj_mask_50 = new boolean[52];
  static {
    jj_mask_50[TRUE] =
    jj_mask_50[FALSE] = true;
  }
  static boolean[] jj_mask_51 = new boolean[52];
  static {
    jj_mask_51[STRING_LITERAL] = true;
  }
  static boolean[] jj_mask_52 = new boolean[52];
  static {
    jj_mask_52[STRING_LITERAL2] = true;
  }
  static boolean[] jj_mask_53 = new boolean[52];
  static {
    jj_mask_53[FLOATING_POINT_LITERAL] = true;
  }
  static boolean[] jj_mask_54 = new boolean[52];
  static {
    jj_mask_54[INTEGER_LITERAL] = true;
  }

  final /*package*/ void BooleanLiteral() throws ParseError {
Token t;
    if (jj_mask_56[getToken(1).kind]) {
jjtree.openIndefiniteNode(ASTBooleanLiteralC.jjtCreate("BooleanLiteralC"));
      t = jj_consume_token(TRUE);
jjtree.closeIndefiniteNode();
  try {
ASTBooleanLiteralC jjtThis = (ASTBooleanLiteralC)jjtree.currentNode();
jjtThis.makeTrue();
} finally {
jjtree.updateCurrentNode(1);
}
    } else {
      jj_expLA1[56] = jj_gen;
      if (jj_mask_55[getToken(1).kind]) {
jjtree.openIndefiniteNode(ASTBooleanLiteralC.jjtCreate("BooleanLiteralC"));
        t = jj_consume_token(FALSE);
jjtree.closeIndefiniteNode();
  try {
ASTBooleanLiteralC jjtThis = (ASTBooleanLiteralC)jjtree.currentNode();
jjtThis.makeFalse();
} finally {
jjtree.updateCurrentNode(1);
}
      } else {
        jj_expLA1[55] = jj_gen;
        jj_consume_token(-1);
        throw new ParseError();
      }
    }
  }

  static boolean[] jj_mask_55 = new boolean[52];
  static {
    jj_mask_55[FALSE] = true;
  }
  static boolean[] jj_mask_56 = new boolean[52];
  static {
    jj_mask_56[TRUE] = true;
  }

  final /*package*/ void NullLiteral() throws ParseError {
jjtree.openIndefiniteNode(ASTNullLiteralC.jjtCreate("NullLiteralC"));
    jj_consume_token(NULL);
jjtree.closeIndefiniteNode();
jjtree.updateCurrentNode(1);
  }

  /*package*/ OqlParserTokenManager token_source;
  /*package*/ Token token;
  private OqlParser jj_me;
  private int jj_gen;
  private int[] jj_expLA1 = new int[57];

  /*package*/ OqlParser(java.io.InputStream stream) {
    ASCII_UCodeESC_CharStream input_stream = new ASCII_UCodeESC_CharStream(stream, 1, 1);
    token_source = new OqlParserTokenManager(input_stream);
    token = new Token();
    jj_me = this;
    jj_gen = 0;
    for (int i = 0; i < 57; i++) jj_expLA1[i] = -1;
  }

  /*package*/ void ReInit(java.io.InputStream stream) {
    ASCII_UCodeESC_CharStream input_stream = new ASCII_UCodeESC_CharStream(stream, 1, 1);
    token_source.ReInit(input_stream);
    token = new Token();
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 57; i++) jj_expLA1[i] = -1;
  }

  /*package*/ OqlParser(OqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_me = this;
    jj_gen = 0;
    for (int i = 0; i < 57; i++) jj_expLA1[i] = -1;
  }

  /*package*/ void ReInit(OqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 57; i++) jj_expLA1[i] = -1;
  }

  final private Token jj_consume_token(int kind) throws ParseError {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    jj_token_error_setup(token, kind);
    throw new ParseError();
  }

  final /*package*/ Token getNextToken() throws ParseError {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_gen++;
    return token;
  }

  final /*package*/ Token getToken(int index) throws ParseError {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static final String jj_add_escapes(String str) {
    String retval = "";
    char ch;
    for (int i = 0; i < str.length(); i++) {
      ch = str.charAt(i);
      if (ch == '\b') {
        retval += "\\b";
      } else if (ch == '\t') {
        retval += "\\t";
      } else if (ch == '\n') {
        retval += "\\n";
      } else if (ch == '\f') {
        retval += "\\f";
      } else if (ch == '\r') {
        retval += "\\r";
      } else if (ch == '\"') {
        retval += "\\\"";
      } else if (ch == '\'') {
        retval += "\\\'";
      } else if (ch == '\\') {
        retval += "\\\\";
      } else if (ch < 0x20 || ch > 0x7e) {
        String s = "0000" + Integer.toString(ch, 16);
        retval += "\\u" + s.substring(s.length() - 4, s.length());
      } else {
        retval += ch;
      }
    }
    return retval;
  }

  /*package*/ int error_line;
  /*package*/ int error_column;
  /*package*/ String error_string;
  /*package*/ String[] expected_tokens;

  /*package*/ void token_error() {
    System.out.println("");
    System.out.println("Parse error at line " + error_line + ", column " + error_column + ".  Encountered:");
    System.out.println("    \"" + jj_add_escapes(error_string) + "\"");
    System.out.println("");
    if (expected_tokens.length == 1) {
      System.out.println("Was expecting:");
    } else {
      System.out.println("Was expecting one of:");
    }
    for (int i = 0; i < expected_tokens.length; i++) {
      System.out.println("    " + expected_tokens[i]);
    }
    System.out.println("");
  }

  private java.util.Vector jj_errortokens = new java.util.Vector();

  final private void jj_token_error_setup(Token current, int kind) throws ParseError {
    jj_errortokens.removeAllElements();
    boolean[] la1tokens = new boolean[52];
    boolean[] mask = null;
    for (int i = 0; i < 52; i++) {
      la1tokens[i] = false;
    }
    if (kind >= 0) la1tokens[kind] = true;
    for (int i = 0; i < 57; i++) {
      if (jj_expLA1[i] == jj_gen) {
        switch (i) {
          case 0: mask = jj_mask_0; break;
          case 1: mask = jj_mask_1; break;
          case 2: mask = jj_mask_2; break;
          case 3: mask = jj_mask_3; break;
          case 4: mask = jj_mask_4; break;
          case 5: mask = jj_mask_5; break;
          case 6: mask = jj_mask_6; break;
          case 7: mask = jj_mask_7; break;
          case 8: mask = jj_mask_8; break;
          case 9: mask = jj_mask_9; break;
          case 10: mask = jj_mask_10; break;
          case 11: mask = jj_mask_11; break;
          case 12: mask = jj_mask_12; break;
          case 13: mask = jj_mask_13; break;
          case 14: mask = jj_mask_14; break;
          case 15: mask = jj_mask_15; break;
          case 16: mask = jj_mask_16; break;
          case 17: mask = jj_mask_17; break;
          case 18: mask = jj_mask_18; break;
          case 19: mask = jj_mask_19; break;
          case 20: mask = jj_mask_20; break;
          case 21: mask = jj_mask_21; break;
          case 22: mask = jj_mask_22; break;
          case 23: mask = jj_mask_23; break;
          case 24: mask = jj_mask_24; break;
          case 25: mask = jj_mask_25; break;
          case 26: mask = jj_mask_26; break;
          case 27: mask = jj_mask_27; break;
          case 28: mask = jj_mask_28; break;
          case 29: mask = jj_mask_29; break;
          case 30: mask = jj_mask_30; break;
          case 31: mask = jj_mask_31; break;
          case 32: mask = jj_mask_32; break;
          case 33: mask = jj_mask_33; break;
          case 34: mask = jj_mask_34; break;
          case 35: mask = jj_mask_35; break;
          case 36: mask = jj_mask_36; break;
          case 37: mask = jj_mask_37; break;
          case 38: mask = jj_mask_38; break;
          case 39: mask = jj_mask_39; break;
          case 40: mask = jj_mask_40; break;
          case 41: mask = jj_mask_41; break;
          case 42: mask = jj_mask_42; break;
          case 43: mask = jj_mask_43; break;
          case 44: mask = jj_mask_44; break;
          case 45: mask = jj_mask_45; break;
          case 46: mask = jj_mask_46; break;
          case 47: mask = jj_mask_47; break;
          case 48: mask = jj_mask_48; break;
          case 49: mask = jj_mask_49; break;
          case 50: mask = jj_mask_50; break;
          case 51: mask = jj_mask_51; break;
          case 52: mask = jj_mask_52; break;
          case 53: mask = jj_mask_53; break;
          case 54: mask = jj_mask_54; break;
          case 55: mask = jj_mask_55; break;
          case 56: mask = jj_mask_56; break;
        }
        for (int j = 0; j < 52; j++) {
          if (mask[j]) la1tokens[j] = true;
        }
      }
    }
    if (la1tokens[0]) {
      jj_errortokens.addElement(tokenImage[0] + " ");
    }
    for (int i = 1; i < 52; i++) {
      if (la1tokens[i]) {
        jj_errortokens.addElement(tokenImage[i] + " ...");
      }
    }
    expected_tokens = new String[jj_errortokens.size()];
    for (int i = 0; i < jj_errortokens.size(); i++) {
      expected_tokens[i] = (String)(jj_errortokens.elementAt(i));
    }
    error_line = current.beginLine;
    error_column = current.beginColumn;
    if (current.kind == 0) {
      error_string = tokenImage[0];
    } else {
      error_string = current.image;
    }
    jj_me.token_error();
  }

  final /*package*/ void enable_tracing() {
  }

  final /*package*/ void disable_tracing() {
  }

}
final class JJTOqlParserState {
  /* JJTree builds the AST bottom up.  It constructs nodes and places
     them on a stack.  When all the children have been assembled, they
     are added to their parent and popped from the stack. */
  private JJTOqlParserNodeStack nodes;

  /* The current node is kept on the top of this stack so that user
     actions can always refer to it. */
  private java.util.Stack current_nodes;

  /* We keep track of whether a node was actually created.  Definite
     and indefinite nodes always are, but GT nodes are only closed and
     pushed on the stack if their conditions are true. */
  private boolean node_created;

  JJTOqlParserState() {
    nodes = new JJTOqlParserNodeStack();
    current_nodes = new java.util.Stack();
  }

  /* Determine whether the current node was actually closed and pushed */
  boolean nodeCreated() {
    return node_created;
  }

  /* Called when the current node has been completely finished with.
     Makes a new node the current node. */
  void updateCurrentNode(int n) {
    for (int i = 0; i < n; ++i) {
      current_nodes.pop();
    }
  }

  /* Call this to reinitialize the node stack.  */
  void reset() {
    nodes.empty();
    current_nodes = new java.util.Stack();
  }

  /* Return the root node of the AST. */
  Node rootNode() {
    return nodes.elementAt(0);
  }

  /* Return the most recently constructed node. */
  Node currentNode() {
    return (Node)current_nodes.peek();
  }

  /* Push a node on to the stack. */
  void pushNode(Node n) {
    nodes.push(n);
  }

  /* Return the node on the top of the stack, and remove it from the
     stack.  */
  Node popNode() {
    return nodes.pop();
  }

  /* Return the node currently on the top of the stack. */
  Node peekNode() {
    return nodes.peek();
  }

  /* An indefinite node has an unspecified number of children.  When
     it is closed it collects up all nodes that have been pushed since
     it was begun and becomes their parent, and then it is pushed on
     to the stack. */

  void openIndefiniteNode(Node n) {
    current_nodes.push(n);
    nodes.mark();
  }

  void closeIndefiniteNode() {
    Node n = currentNode();
    n.jjtOpen();
    for (JJTOqlParserNodeEnum e = nodes.elementsSinceLastMark();
          e.hasMoreElements(); ) {
      Node c = (Node)e.nextElement();
      c.jjtSetParent(n);
      n.jjtAddChild(c);
    }
    nodes.popToLastMark();
    n.jjtClose();
    nodes.push(n);
    node_created = true;
  }

  /* A definite node is constructed from a fixed number of children.
     That number of nodes are popped from the stack and made the
     children of the definite node.  Then the definite node is pushed
     on to the stack. */

  void openDefiniteNode(Node n) {
    current_nodes.push(n);
  }

  void closeDefiniteNode(int num) {
    Node n = currentNode();
    n.jjtOpen();
    for (JJTOqlParserNodeEnum e = nodes.elementsTop(num); e.hasMoreElements(); ) {
      Node c = (Node)e.nextElement();
      c.jjtSetParent(n);
      n.jjtAddChild(c);
    }
    nodes.popTop(num);
    n.jjtClose();
    nodes.push(n);
    node_created = true;
  }

  /* A GT (Greater Than) node is constructed if more than the
     specified number of nodes have been pushed since it was begun.
     All those nodes are made children of the the GT node, which is
     then pushed on to the stack.  If fewer have been pushed the node
     is not constructed and they are left on the stack. */

  void openGTNode(Node n) {
    current_nodes.push(n);
    nodes.mark();
  }

  void closeGTNode(int num) {
    if (nodes.numElementsSinceLastMark() > num) {
      closeIndefiniteNode();
    } else {
      nodes.removeLastMark();
      node_created = false;
    }
  }
}

final class JJTOqlParserNodeStack {
  private Node[] nodeStack;
  private int[] markStack;
  private int nodeSP;
  private int markSP;

  JJTOqlParserNodeStack() {
    nodeStack = new Node[500];
    markStack = new int[500];
    nodeSP = 0;
    markSP = 0;
  }

  void empty() {
    if (nodeSP > 0) {
      while (--nodeSP >= 0) {
         nodeStack[nodeSP] = null;
      }
    }
    nodeSP = 0;
    markSP = 0;
  }

  Node elementAt(int i) {
    return nodeStack[i];
  }

  Node elementFromTop(int i) {
    return nodeStack[nodeSP - i - 1];
  }

  void push(Node n) {
    if (nodeSP == nodeStack.length) {
      Node[] ns = new Node[nodeStack.length * 2];
      System.arraycopy(nodeStack, 0, ns, 0, nodeStack.length);
      nodeStack = ns;
    }
    nodeStack[nodeSP++] = n;
  }

  Node pop() {
    Node n = nodeStack[--nodeSP];
    nodeStack[nodeSP] = null;
    return n;
  }

  Node peek() {
    return nodeStack[nodeSP - 1];
  }

  void mark() {
    if (markSP == markStack.length) {
      int[] ms = new int[markStack.length * 2];
      System.arraycopy(markStack, 0, ms, 0, markStack.length);
      markStack = ms;
    }
    markStack[markSP++] = nodeSP;
  }

  void removeLastMark() {
    --markSP;
  }

  int numElementsSinceLastMark() {
    return nodeSP - markStack[markSP - 1];
  }

  JJTOqlParserNodeEnum elementsSinceLastMark() {
    return new JJTOqlParserNodeEnum(nodeStack, nodeSP, markStack[markSP - 1]);
  }

  void popToLastMark() {
    --markSP;
    while (nodeSP > markStack[markSP]) {
      nodeStack[--nodeSP] = null;
    }
  }

  JJTOqlParserNodeEnum elementsTop(int n) {
    return new JJTOqlParserNodeEnum(nodeStack, nodeSP, nodeSP - n);
  }

  void popTop(int n) {
    for (int i = 0; i < n; ++i) {
      nodeStack[--nodeSP] = null;
    }
  }
}

final class JJTOqlParserNodeEnum implements java.util.Enumeration {
  private Node[] nodes;
  private int topSP, index;

  JJTOqlParserNodeEnum(Node[] s, int top, int start) {
    nodes = s;
    topSP = top;
    index = start;
  }

  public boolean hasMoreElements() {
    return index < topSP;
  }

  public Object nextElement() {
    return nodes[index++];
  }
}
