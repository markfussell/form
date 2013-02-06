/*======================================================================
**
**  File: chimu/form/oql/OqlParserConstants.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

/*package*/ interface OqlParserConstants {

  int EOF = 0;
  int SINGLE_LINE_COMMENT = 6;
  int MULTI_LINE_COMMENT = 7;
  int BANG = 8;
  int TILDE = 9;
  int EQ = 10;
  int EQ2 = 11;
  int NE = 12;
  int NE2 = 13;
  int GT = 14;
  int LT = 15;
  int LE = 16;
  int GE = 17;
  int PLUS = 18;
  int MINUS = 19;
  int TIMES = 20;
  int DIVIDE = 21;
  int REM = 22;
  int AND = 23;
  int OR = 24;
  int XOR = 25;
  int IN = 26;
  int WHERE = 27;
  int AS = 28;
  int SELECT = 29;
  int FROM = 30;
  int NULL = 31;
  int TRUE = 32;
  int FALSE = 33;
  int DOLLAR = 34;
  int COLON = 35;
  int IDENTIFIER = 36;
  int LETTER = 37;
  int DIGIT = 38;
  int LPAREN = 39;
  int RPAREN = 40;
  int COMMA = 41;
  int DOT = 42;
  int RARROW = 43;
  int INTEGER_LITERAL = 44;
  int DECIMAL_LITERAL = 45;
  int HEX_LITERAL = 46;
  int OCTAL_LITERAL = 47;
  int FLOATING_POINT_LITERAL = 48;
  int EXPONENT = 49;
  int STRING_LITERAL2 = 50;
  int STRING_LITERAL = 51;

  int DEFAULT = 0;

  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "<SINGLE_LINE_COMMENT>",
    "<MULTI_LINE_COMMENT>",
    "\"!\"",
    "\"~\"",
    "\"=\"",
    "\"==\"",
    "\"!=\"",
    "\"<>\"",
    "\">\"",
    "\"<\"",
    "\"<=\"",
    "\">=\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"%\"",
    "<AND>",
    "<OR>",
    "\"^\"",
    "\"in\"",
    "\"where\"",
    "\"as\"",
    "\"select\"",
    "\"from\"",
    "\"null\"",
    "\"true\"",
    "\"false\"",
    "\"$\"",
    "\":\"",
    "<IDENTIFIER>",
    "<LETTER>",
    "<DIGIT>",
    "\"(\"",
    "\")\"",
    "\",\"",
    "\".\"",
    "\"->\"",
    "<INTEGER_LITERAL>",
    "<DECIMAL_LITERAL>",
    "<HEX_LITERAL>",
    "<OCTAL_LITERAL>",
    "<FLOATING_POINT_LITERAL>",
    "<EXPONENT>",
    "<STRING_LITERAL2>",
    "<STRING_LITERAL>",
  };

}
