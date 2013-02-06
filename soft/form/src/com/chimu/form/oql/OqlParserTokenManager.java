/*======================================================================
**
**  File: chimu/form/oql/OqlParserTokenManager.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;

/*package*/ class OqlParserTokenManager implements OqlParserConstants
{
  /*package*/ static final String jjadd_escapes(String str) {
    String retval = "";
    char ch;
    for (int i = 0; i < str.length(); i++) {
      if ((ch = str.charAt(i)) == 0) continue;
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

static final long[] jjbitVec0 = { 0xfffffffffffffffeL, 0xffffffffffffffffL,
              0xffffffffffffffffL, 0xffffffffffffffffL };
static final long[] jjbitVec2 = { 0x1L, 0x0L,
              0xffffffffffffffffL, 0xffffffffffffffffL };
static final long[] jjbitVec3 = { 0x1ff00000fffffffeL, 0xffffffffffffc000L,
              0xffffffffL, 0x600000000000000L };
static final long[] jjbitVec4 = { 0x1L, 0x0L,
              0x0L, 0xff7fffffff7fffffL };
static final long[] jjbitVec5 = { 0x0L, 0xffffffffffffffffL,
              0xffffffffffffffffL, 0xffffffffffffffffL };
static final long[] jjbitVec6 = { 0xffffffffffffffffL, 0xffffffffffffffffL,
              0xffffL, 0x0L };
static final long[] jjbitVec7 = { 0xffffffffffffffffL, 0xffffffffffffffffL,
              0x0L, 0x0L };
static final long[] jjbitVec8 = { 0x3fffffffffffL, 0x0L,
              0x0L, 0x0L };
private final void jjMoveNfa_0(int[] oldStates, int[] newStates)
{
   int j, k;
   MainLoop :
   for (;;)
   {
      if (curChar < 64)
      {
         int i = jjnewStateCnt;
         jjnewStateCnt = 0;
         jjround++;
         long l = 1L << curChar;
         do
         {
            switch(oldStates[--i])
            {
               case 56:
                  if ((0x800000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates39;
                     break;
                  }
                  else continue;
               case 57:
                  if ((0xffffffffffffdbffL & l) != 0L)
                  {
                     jjnextStates = jjstates39;
                     break;
                  }
                  else continue;
               case 58:
                  if ((0x2400L & l) != 0L)
                  {
                     if (jjcurKind > 6)
                        jjcurKind = 6;
                     continue;
                  }
                  else continue;
               case 60:
                  if ((0x2000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 59;
                     jjnewStateCnt += 1;
                     continue;
                  }
                  else continue;
               case 59:
                  if ((0x400L & l) != 0L)
                  {
                     if (jjcurKind > 6)
                        jjcurKind = 6;
                     continue;
                  }
                  else continue;
               case 61:
                  if ((0x40000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates42;
                     break;
                  }
                  else continue;
               case 62:
                  if ((0xfffffbffffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates42;
                     break;
                  }
                  else continue;
               case 63:
                  if ((0x40000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates41;
                     break;
                  }
                  else continue;
               case 64:
                  if ((0xffff7bffffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates40;
                     break;
                  }
                  else continue;
               case 65:
                  if ((0xfffffbffffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates40;
                     break;
                  }
                  else continue;
               case 66:
                  if ((0x800000000000L & l) != 0L)
                  {
                     if (jjcurKind > 7)
                        jjcurKind = 7;
                     continue;
                  }
                  else continue;
               case 0:
                  if ((0x4000000000L & l) != 0L)
                  {
                     if (jjcurKind > 23)
                        jjcurKind = 23;
                     continue;
                  }
                  else continue;
               case 8:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 36)
                        jjcurKind = 36;
                     newStates[jjnewStateCnt + 0] = 8;
                     jjnewStateCnt += 1;
                     continue;
                  }
                  else continue;
               case 9:
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 44)
                        jjcurKind = 44;
                     jjnextStates = jjstates4;
                     break;
                  }
                  else continue;
               case 10:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 44)
                        jjcurKind = 44;
                     jjnextStates = jjstates4;
                     break;
                  }
                  else continue;
               case 53:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 44)
                        jjcurKind = 44;
                     jjnextStates = jjstates34;
                     break;
                  }
                  else continue;
               case 54:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 44)
                        jjcurKind = 44;
                     jjnextStates = jjstates36;
                     break;
                  }
                  else continue;
               case 37:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates24;
                     break;
                  }
                  else continue;
               case 38:
                  if ((0x400000000000L & l) != 0L)
                  {
                     if (jjcurKind > 48)
                        jjcurKind = 48;
                     jjnextStates = jjstates23;
                     break;
                  }
                  else continue;
               case 39:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 48)
                        jjcurKind = 48;
                     jjnextStates = jjstates23;
                     break;
                  }
                  else continue;
               case 41:
                  if ((0x280000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates21;
                     break;
                  }
                  else continue;
               case 42:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 48)
                        jjcurKind = 48;
                     jjnextStates = jjstates20;
                     break;
                  }
                  else continue;
               case 12:
                  if ((0x400000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates9;
                     break;
                  }
                  else continue;
               case 13:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 48)
                        jjcurKind = 48;
                     jjnextStates = jjstates8;
                     break;
                  }
                  else continue;
               case 15:
                  if ((0x280000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates6;
                     break;
                  }
                  else continue;
               case 16:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 48)
                        jjcurKind = 48;
                     jjnextStates = jjstates5;
                     break;
                  }
                  else continue;
               case 43:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates28;
                     break;
                  }
                  else continue;
               case 45:
                  if ((0x280000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates26;
                     break;
                  }
                  else continue;
               case 46:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 48)
                        jjcurKind = 48;
                     jjnextStates = jjstates25;
                     break;
                  }
                  else continue;
               case 47:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates32;
                     break;
                  }
                  else continue;
               case 49:
                  if ((0x280000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates30;
                     break;
                  }
                  else continue;
               case 50:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates29;
                     break;
                  }
                  else continue;
               case 18:
                  if ((0x8000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates10;
                     break;
                  }
                  else continue;
               case 19:
                  if ((0xffffff7fffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates10;
                     break;
                  }
                  else continue;
               case 21:
                  if ((0x8400000000L & l) != 0L)
                  {
                     jjnextStates = jjstates10;
                     break;
                  }
                  else continue;
               case 23:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates11;
                     break;
                  }
                  else continue;
               case 24:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates10;
                     break;
                  }
                  else continue;
               case 25:
                  if ((0xf000000000000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 26;
                     jjnewStateCnt += 1;
                     continue;
                  }
                  else continue;
               case 26:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates12;
                     break;
                  }
                  else continue;
               case 22:
                  if ((0x8000000000L & l) != 0L)
                  {
                     if (jjcurKind > 50)
                        jjcurKind = 50;
                     continue;
                  }
                  else continue;
               case 27:
                  if ((0x400000000L & l) != 0L)
                  {
                     jjnextStates = jjstates15;
                     break;
                  }
                  else continue;
               case 28:
                  if ((0xfffffffbffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates15;
                     break;
                  }
                  else continue;
               case 30:
                  if ((0x8400000000L & l) != 0L)
                  {
                     jjnextStates = jjstates15;
                     break;
                  }
                  else continue;
               case 32:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates16;
                     break;
                  }
                  else continue;
               case 33:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates15;
                     break;
                  }
                  else continue;
               case 34:
                  if ((0xf000000000000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 35;
                     jjnewStateCnt += 1;
                     continue;
                  }
                  else continue;
               case 35:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates17;
                     break;
                  }
                  else continue;
               case 31:
                  if ((0x400000000L & l) != 0L)
                  {
                     if (jjcurKind > 51)
                        jjcurKind = 51;
                     continue;
                  }
                  else continue;
               case 36:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates33;
                     break;
                  }
                  else continue;
               case 51:
                  if ((0x1000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 44)
                        jjcurKind = 44;
                     jjnextStates = jjstates37;
                     break;
                  }
                  else continue;
               case 55:
                  if ((0x800000000000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 56;
                     newStates[jjnewStateCnt + 1] = 61;
                     jjnewStateCnt += 2;
                     continue;
                  }
                  else continue;
               default : continue;
            }
            j = jjnextStates.length;
            do
            {
               if (jjrounds[k = jjnextStates[--j]] != jjround)
               {
                  jjrounds[k] = jjround;
                  newStates[jjnewStateCnt++] = k;
               }
            } while (j != 0);
         } while(i != 0);
      }
      else if (curChar < 128)
      {
         int i = jjnewStateCnt;
         jjnewStateCnt = 0;
         jjround++;
         long l = 1L << (curChar & 077);
         do
         {
            switch(oldStates[--i])
            {
               case 57:
                     newStates[jjnewStateCnt + 0] = 57;
                     newStates[jjnewStateCnt + 1] = 58;
                     newStates[jjnewStateCnt + 2] = 60;
                     jjnewStateCnt += 3;
                     continue;
               case 62:
                     jjnextStates = jjstates42;
                     break;
               case 64:
                     jjnextStates = jjstates40;
                     break;
               case 65:
                     jjnextStates = jjstates40;
                     break;
               case 3:
                  if ((0x200000002L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 2;
                     jjnewStateCnt += 1;
                     continue;
                  }
                  else continue;
               case 2:
                  if ((0x400000004000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 1;
                     jjnewStateCnt += 1;
                     continue;
                  }
                  else continue;
               case 1:
                  if ((0x1000000010L & l) != 0L)
                  {
                     if (jjcurKind > 23)
                        jjcurKind = 23;
                     continue;
                  }
                  else continue;
               case 4:
                  if ((0x1000000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 24)
                        jjcurKind = 24;
                     continue;
                  }
                  else continue;
               case 6:
                  if ((0x800000008000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 5;
                     jjnewStateCnt += 1;
                     continue;
                  }
                  else continue;
               case 5:
                  if ((0x4000000040000L & l) != 0L)
                  {
                     if (jjcurKind > 24)
                        jjcurKind = 24;
                     continue;
                  }
                  else continue;
               case 7:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (jjcurKind > 36)
                        jjcurKind = 36;
                     jjnextStates = jjstates3;
                     break;
                  }
                  else continue;
               case 8:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (jjcurKind > 36)
                        jjcurKind = 36;
                     jjnextStates = jjstates3;
                     break;
                  }
                  else continue;
               case 11:
                  if ((0x100000001000L & l) != 0L)
                  {
                     if (jjcurKind > 44)
                        jjcurKind = 44;
                     continue;
                  }
                  else continue;
               case 52:
                  if ((0x100000001000000L & l) != 0L)
                  {
                     jjnextStates = jjstates35;
                     break;
                  }
                  else continue;
               case 53:
                  if ((0x7e0000007eL & l) != 0L)
                  {
                     if (jjcurKind > 44)
                        jjcurKind = 44;
                     jjnextStates = jjstates34;
                     break;
                  }
                  else continue;
               case 40:
                  if ((0x2000000020L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 41;
                     newStates[jjnewStateCnt + 1] = 42;
                     jjnewStateCnt += 2;
                     continue;
                  }
                  else continue;
               case 14:
                  if ((0x2000000020L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 15;
                     newStates[jjnewStateCnt + 1] = 16;
                     jjnewStateCnt += 2;
                     continue;
                  }
                  else continue;
               case 17:
                  if ((0x5000000050L & l) != 0L)
                  {
                     if (jjcurKind > 48)
                        jjcurKind = 48;
                     continue;
                  }
                  else continue;
               case 44:
                  if ((0x2000000020L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 45;
                     newStates[jjnewStateCnt + 1] = 46;
                     jjnewStateCnt += 2;
                     continue;
                  }
                  else continue;
               case 48:
                  if ((0x2000000020L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 49;
                     newStates[jjnewStateCnt + 1] = 50;
                     jjnewStateCnt += 2;
                     continue;
                  }
                  else continue;
               case 19:
                  if ((0xffffffffefffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates10;
                     break;
                  }
                  else continue;
               case 20:
                  if ((0x10000000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 21;
                     newStates[jjnewStateCnt + 1] = 23;
                     newStates[jjnewStateCnt + 2] = 25;
                     jjnewStateCnt += 3;
                     continue;
                  }
                  else continue;
               case 21:
                  if ((0x14404410000000L & l) != 0L)
                  {
                     jjnextStates = jjstates10;
                     break;
                  }
                  else continue;
               case 28:
                  if ((0xffffffffefffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates15;
                     break;
                  }
                  else continue;
               case 29:
                  if ((0x10000000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 30;
                     newStates[jjnewStateCnt + 1] = 32;
                     newStates[jjnewStateCnt + 2] = 34;
                     jjnewStateCnt += 3;
                     continue;
                  }
                  else continue;
               case 30:
                  if ((0x14404410000000L & l) != 0L)
                  {
                     jjnextStates = jjstates15;
                     break;
                  }
                  else continue;
               default : continue;
            }
            j = jjnextStates.length;
            do
            {
               if (jjrounds[k = jjnextStates[--j]] != jjround)
               {
                  jjrounds[k] = jjround;
                  newStates[jjnewStateCnt++] = k;
               }
            } while (j != 0);
         } while(i != 0);
      }
      else
      {
         int i = jjnewStateCnt;
         jjnewStateCnt = 0;
         jjround++;
         int i2 = (curChar & 0xff) >> 6;
         int i1 = ((curChar >> 8) & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         long l1 = 1L << ((curChar >> 8) & 077);
         Outer:
         do
         {
            switch(oldStates[--i])
            {
               case 57:
                  MatchLoop:
                  for(;;)
                  {
                     switch((curChar >> 8))
                     {
                        case 0:
                           if ((jjbitVec2[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        default : break;
                     }
                     if ((jjbitVec0[i1] & l1) != 0L)
                           break MatchLoop;
                     continue Outer;
                  }
                     newStates[jjnewStateCnt + 0] = 57;
                     newStates[jjnewStateCnt + 1] = 58;
                     newStates[jjnewStateCnt + 2] = 60;
                     jjnewStateCnt += 3;
                     continue;
               case 62:
                  MatchLoop:
                  for(;;)
                  {
                     switch((curChar >> 8))
                     {
                        case 0:
                           if ((jjbitVec2[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        default : break;
                     }
                     if ((jjbitVec0[i1] & l1) != 0L)
                           break MatchLoop;
                     continue Outer;
                  }
                     jjnextStates = jjstates42;
                     break;
               case 64:
                  MatchLoop:
                  for(;;)
                  {
                     switch((curChar >> 8))
                     {
                        case 0:
                           if ((jjbitVec2[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        default : break;
                     }
                     if ((jjbitVec0[i1] & l1) != 0L)
                           break MatchLoop;
                     continue Outer;
                  }
                     jjnextStates = jjstates40;
                     break;
               case 65:
                  MatchLoop:
                  for(;;)
                  {
                     switch((curChar >> 8))
                     {
                        case 0:
                           if ((jjbitVec2[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        default : break;
                     }
                     if ((jjbitVec0[i1] & l1) != 0L)
                           break MatchLoop;
                     continue Outer;
                  }
                     jjnextStates = jjstates40;
                     break;
               case 7:
                  MatchLoop:
                  for(;;)
                  {
                     switch((curChar >> 8))
                     {
                        case 0:
                           if ((jjbitVec4[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        case 48:
                           if ((jjbitVec5[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        case 49:
                           if ((jjbitVec6[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        case 51:
                           if ((jjbitVec7[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        case 61:
                           if ((jjbitVec8[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        default : break;
                     }
                     if ((jjbitVec3[i1] & l1) != 0L)
                           break MatchLoop;
                     continue Outer;
                  }
                  if (jjcurKind > 36)
                     jjcurKind = 36;
                     jjnextStates = jjstates3;
                     break;
               case 8:
                  MatchLoop:
                  for(;;)
                  {
                     switch((curChar >> 8))
                     {
                        case 0:
                           if ((jjbitVec4[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        case 48:
                           if ((jjbitVec5[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        case 49:
                           if ((jjbitVec6[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        case 51:
                           if ((jjbitVec7[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        case 61:
                           if ((jjbitVec8[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        default : break;
                     }
                     if ((jjbitVec3[i1] & l1) != 0L)
                           break MatchLoop;
                     continue Outer;
                  }
                  if (jjcurKind > 36)
                     jjcurKind = 36;
                     jjnextStates = jjstates3;
                     break;
               case 19:
                  MatchLoop:
                  for(;;)
                  {
                     switch((curChar >> 8))
                     {
                        case 0:
                           if ((jjbitVec2[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        default : break;
                     }
                     if ((jjbitVec0[i1] & l1) != 0L)
                           break MatchLoop;
                     continue Outer;
                  }
                     newStates[jjnewStateCnt + 0] = 19;
                     newStates[jjnewStateCnt + 1] = 20;
                     newStates[jjnewStateCnt + 2] = 22;
                     jjnewStateCnt += 3;
                     continue;
               case 28:
                  MatchLoop:
                  for(;;)
                  {
                     switch((curChar >> 8))
                     {
                        case 0:
                           if ((jjbitVec2[i2] & l2) == 0L)
                              continue Outer;
                           break MatchLoop;
                        default : break;
                     }
                     if ((jjbitVec0[i1] & l1) != 0L)
                           break MatchLoop;
                     continue Outer;
                  }
                     newStates[jjnewStateCnt + 0] = 28;
                     newStates[jjnewStateCnt + 1] = 29;
                     newStates[jjnewStateCnt + 2] = 31;
                     jjnewStateCnt += 3;
                     continue;
               default : continue;
            }
            j = jjnextStates.length;
            do
            {
               if (jjrounds[k = jjnextStates[--j]] != jjround)
               {
                  jjrounds[k] = jjround;
                  newStates[jjnewStateCnt++] = k;
               }
            } while (j != 0);
         } while(i != 0);
      }
      return;
   }
}
long jjnewActive_00 = 0L, jjoldActive_00 = 0L;
private final void jjMoveStringLiteralDfa0_0() throws java.io.IOException
{
   ReturnLoop:
   for (;;)
   {
    MainLoop:
    for (;;)
    {
         switch(curChar)
         {
            case 97:
               jjoldActive_00 = 0x10000000L;
               break;
            case 94:
               matchedToken.kind = 25;
               curPos = 1;
               return;
            case 87:
               jjoldActive_00 = 0x8000000L;
               break;
            case 84:
               jjoldActive_00 = 0x100000000L;
               break;
            case 83:
               jjoldActive_00 = 0x20000000L;
               break;
            case 78:
               jjoldActive_00 = 0x80000000L;
               break;
            case 73:
               jjoldActive_00 = 0x4000000L;
               break;
            case 70:
               jjoldActive_00 = 0x240000000L;
               break;
            case 65:
               jjoldActive_00 = 0x10000000L;
               break;
            case 62:
               matchedToken.kind = 14;
               jjoldActive_00 = 0x20000L;
               break;
            case 61:
               matchedToken.kind = 10;
               jjoldActive_00 = 0x800L;
               break;
            case 60:
               matchedToken.kind = 15;
               jjoldActive_00 = 0x12000L;
               break;
            case 58:
               matchedToken.kind = 35;
               curPos = 1;
               return;
            case 47:
               matchedToken.kind = 21;
               curPos = 1;
               return;
            case 46:
               matchedToken.kind = 42;
               curPos = 1;
               return;
            case 45:
               matchedToken.kind = 19;
               jjoldActive_00 = 0x80000000000L;
               break;
            case 44:
               matchedToken.kind = 41;
               curPos = 1;
               return;
            case 43:
               matchedToken.kind = 18;
               curPos = 1;
               return;
            case 42:
               matchedToken.kind = 20;
               curPos = 1;
               return;
            case 41:
               matchedToken.kind = 40;
               curPos = 1;
               return;
            case 40:
               matchedToken.kind = 39;
               curPos = 1;
               return;
            case 37:
               matchedToken.kind = 22;
               curPos = 1;
               return;
            case 36:
               matchedToken.kind = 34;
               curPos = 1;
               return;
            case 33:
               matchedToken.kind = 8;
               jjoldActive_00 = 0x1000L;
               break;
            case 32:
               matchedToken.kind = 1;
               curPos = 1;
               return;
            case 126:
               matchedToken.kind = 9;
               curPos = 1;
               return;
            case 119:
               jjoldActive_00 = 0x8000000L;
               break;
            case 116:
               jjoldActive_00 = 0x100000000L;
               break;
            case 115:
               jjoldActive_00 = 0x20000000L;
               break;
            case 13:
               matchedToken.kind = 4;
               curPos = 1;
               return;
            case 12:
               matchedToken.kind = 5;
               curPos = 1;
               return;
            case 10:
               matchedToken.kind = 3;
               curPos = 1;
               return;
            case 110:
               jjoldActive_00 = 0x80000000L;
               break;
            case 9:
               matchedToken.kind = 2;
               curPos = 1;
               return;
            case 105:
               jjoldActive_00 = 0x4000000L;
               break;
            case 102:
               jjoldActive_00 = 0x240000000L;
               break;
            default :
               curPos =  1;
               return;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            curPos = 1;
            return;
         }
         jjMoveStringLiteralDfa1_0();
         return;
    }
   }
}
private final void jjMoveStringLiteralDfa1_0() throws java.io.IOException
{
   ReturnLoop:
   for (;;)
   {
    MainLoop:
    for (;;)
    {
      StartNfaLoop:
      for (;;)
      {
         switch(curChar)
         {
            case 97:
               if (((jjnewActive_00 = jjoldActive_00 & 0x200000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 85:
               if (((jjnewActive_00 = jjoldActive_00 & 0x80000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 83:
               if ((jjoldActive_00 & 0x10000000L) != 0L)
               {
                  matchedToken.kind = 28;
                  jjmatchedPos = 1;
               }
               break StartNfaLoop;
            case 82:
               if (((jjnewActive_00 = jjoldActive_00 & 0x140000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 78:
               if ((jjoldActive_00 & 0x4000000L) != 0L)
               {
                  matchedToken.kind = 26;
                  jjmatchedPos = 1;
               }
               break StartNfaLoop;
            case 72:
               if (((jjnewActive_00 = jjoldActive_00 & 0x8000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 69:
               if (((jjnewActive_00 = jjoldActive_00 & 0x20000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 65:
               if (((jjnewActive_00 = jjoldActive_00 & 0x200000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 62:
               if ((jjoldActive_00 & 0x2000L) != 0L)
               {
                  matchedToken.kind = 13;
                  jjmatchedPos = 1;
               }
               else if ((jjoldActive_00 & 0x80000000000L) != 0L)
               {
                  matchedToken.kind = 43;
                  jjmatchedPos = 1;
               }
               break StartNfaLoop;
            case 61:
               if ((jjoldActive_00 & 0x800L) != 0L)
               {
                  matchedToken.kind = 11;
                  jjmatchedPos = 1;
               }
               else if ((jjoldActive_00 & 0x1000L) != 0L)
               {
                  matchedToken.kind = 12;
                  jjmatchedPos = 1;
               }
               else if ((jjoldActive_00 & 0x10000L) != 0L)
               {
                  matchedToken.kind = 16;
                  jjmatchedPos = 1;
               }
               else if ((jjoldActive_00 & 0x20000L) != 0L)
               {
                  matchedToken.kind = 17;
                  jjmatchedPos = 1;
               }
               break StartNfaLoop;
            case 117:
               if (((jjnewActive_00 = jjoldActive_00 & 0x80000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 115:
               if ((jjoldActive_00 & 0x10000000L) != 0L)
               {
                  matchedToken.kind = 28;
                  jjmatchedPos = 1;
               }
               break StartNfaLoop;
            case 114:
               if (((jjnewActive_00 = jjoldActive_00 & 0x140000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 110:
               if ((jjoldActive_00 & 0x4000000L) != 0L)
               {
                  matchedToken.kind = 26;
                  jjmatchedPos = 1;
               }
               break StartNfaLoop;
            case 104:
               if (((jjnewActive_00 = jjoldActive_00 & 0x8000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 101:
               if (((jjnewActive_00 = jjoldActive_00 & 0x20000000L)) == 0L)
                  break StartNfaLoop;
               break;
            default :
               curPos =  2;
               return;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            curPos = 2;
            return;
         }
         jjMoveStringLiteralDfa2_0();
         return;
      }
      curPos = 2;
      return;
    }
   }
}
private final void jjMoveStringLiteralDfa2_0() throws java.io.IOException
{
   ReturnLoop:
   for (;;)
   {
    MainLoop:
    for (;;)
    {
      StartNfaLoop:
      for (;;)
      {
         switch(curChar)
         {
            case 85:
               if (((jjoldActive_00 = jjnewActive_00 & 0x100000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 79:
               if (((jjoldActive_00 = jjnewActive_00 & 0x40000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 76:
               if (((jjoldActive_00 = jjnewActive_00 & 0x2a0000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 69:
               if (((jjoldActive_00 = jjnewActive_00 & 0x8000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 117:
               if (((jjoldActive_00 = jjnewActive_00 & 0x100000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 111:
               if (((jjoldActive_00 = jjnewActive_00 & 0x40000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 108:
               if (((jjoldActive_00 = jjnewActive_00 & 0x2a0000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 101:
               if (((jjoldActive_00 = jjnewActive_00 & 0x8000000L)) == 0L)
                  break StartNfaLoop;
               break;
            default :
               curPos =  3;
               return;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            curPos = 3;
            return;
         }
         jjMoveStringLiteralDfa3_0();
         return;
      }
      curPos = 3;
      return;
    }
   }
}
private final void jjMoveStringLiteralDfa3_0() throws java.io.IOException
{
   ReturnLoop:
   for (;;)
   {
    MainLoop:
    for (;;)
    {
      StartNfaLoop:
      for (;;)
      {
         switch(curChar)
         {
            case 83:
               if (((jjnewActive_00 = jjoldActive_00 & 0x200000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 82:
               if (((jjnewActive_00 = jjoldActive_00 & 0x8000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 77:
               if ((jjoldActive_00 & 0x40000000L) != 0L)
               {
                  matchedToken.kind = 30;
                  jjmatchedPos = 3;
               }
               break StartNfaLoop;
            case 76:
               if ((jjoldActive_00 & 0x80000000L) != 0L)
               {
                  matchedToken.kind = 31;
                  jjmatchedPos = 3;
               }
               break StartNfaLoop;
            case 69:
               if ((jjoldActive_00 & 0x100000000L) != 0L)
               {
                  matchedToken.kind = 32;
                  jjmatchedPos = 3;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x20000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 115:
               if (((jjnewActive_00 = jjoldActive_00 & 0x200000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 114:
               if (((jjnewActive_00 = jjoldActive_00 & 0x8000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 109:
               if ((jjoldActive_00 & 0x40000000L) != 0L)
               {
                  matchedToken.kind = 30;
                  jjmatchedPos = 3;
               }
               break StartNfaLoop;
            case 108:
               if ((jjoldActive_00 & 0x80000000L) != 0L)
               {
                  matchedToken.kind = 31;
                  jjmatchedPos = 3;
               }
               break StartNfaLoop;
            case 101:
               if ((jjoldActive_00 & 0x100000000L) != 0L)
               {
                  matchedToken.kind = 32;
                  jjmatchedPos = 3;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x20000000L)) == 0L)
                  break StartNfaLoop;
               break;
            default :
               curPos =  4;
               return;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            curPos = 4;
            return;
         }
         jjMoveStringLiteralDfa4_0();
         return;
      }
      curPos = 4;
      return;
    }
   }
}
private final void jjMoveStringLiteralDfa4_0() throws java.io.IOException
{
   ReturnLoop:
   for (;;)
   {
    MainLoop:
    for (;;)
    {
      StartNfaLoop:
      for (;;)
      {
         switch(curChar)
         {
            case 99:
               if (((jjoldActive_00 = jjnewActive_00 & 0x20000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 69:
               if ((jjnewActive_00 & 0x8000000L) != 0L)
               {
                  matchedToken.kind = 27;
                  jjmatchedPos = 4;
               }
               else if ((jjnewActive_00 & 0x200000000L) != 0L)
               {
                  matchedToken.kind = 33;
                  jjmatchedPos = 4;
               }
               break StartNfaLoop;
            case 67:
               if (((jjoldActive_00 = jjnewActive_00 & 0x20000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 101:
               if ((jjnewActive_00 & 0x8000000L) != 0L)
               {
                  matchedToken.kind = 27;
                  jjmatchedPos = 4;
               }
               else if ((jjnewActive_00 & 0x200000000L) != 0L)
               {
                  matchedToken.kind = 33;
                  jjmatchedPos = 4;
               }
               break StartNfaLoop;
            default :
               curPos =  5;
               return;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            curPos = 5;
            return;
         }
         jjMoveStringLiteralDfa5_0();
         return;
      }
      curPos = 5;
      return;
    }
   }
}
private final void jjMoveStringLiteralDfa5_0() throws java.io.IOException
{
   ReturnLoop:
   for (;;)
   {
    MainLoop:
    for (;;)
    {
      StartNfaLoop:
      for (;;)
      {
         switch(curChar)
         {
            case 84:
               if ((jjoldActive_00 & 0x20000000L) != 0L)
               {
                  matchedToken.kind = 29;
                  jjmatchedPos = 5;
               }
               break StartNfaLoop;
            case 116:
               if ((jjoldActive_00 & 0x20000000L) != 0L)
               {
                  matchedToken.kind = 29;
                  jjmatchedPos = 5;
               }
               break StartNfaLoop;
            default :
               curPos =  6;
               return;
         }
      }
      curPos = 6;
      return;
    }
   }
}
static final int[] jjstates0 = { 1, };
static final int[] jjstates1 = { 2, };
static final int[] jjstates2 = { 5, };
static final int[] jjstates3 = { 8, };
static final int[] jjstates4 = { 10, 11, };
static final int[] jjstates5 = { 16, 17, };
static final int[] jjstates6 = { 16, };
static final int[] jjstates7 = { 15, 16, };
static final int[] jjstates8 = { 13, 14, 17, };
static final int[] jjstates9 = { 13, };
static final int[] jjstates10 = { 19, 20, 22, };
static final int[] jjstates11 = { 19, 20, 24, 22, };
static final int[] jjstates12 = { 24, };
static final int[] jjstates13 = { 26, };
static final int[] jjstates14 = { 21, 23, 25, };
static final int[] jjstates15 = { 28, 29, 31, };
static final int[] jjstates16 = { 28, 29, 33, 31, };
static final int[] jjstates17 = { 33, };
static final int[] jjstates18 = { 35, };
static final int[] jjstates19 = { 30, 32, 34, };
static final int[] jjstates20 = { 42, 17, };
static final int[] jjstates21 = { 42, };
static final int[] jjstates22 = { 41, 42, };
static final int[] jjstates23 = { 39, 40, 17, };
static final int[] jjstates24 = { 37, 38, };
static final int[] jjstates25 = { 46, 17, };
static final int[] jjstates26 = { 46, };
static final int[] jjstates27 = { 45, 46, };
static final int[] jjstates28 = { 43, 44, };
static final int[] jjstates29 = { 50, 17, };
static final int[] jjstates30 = { 50, };
static final int[] jjstates31 = { 49, 50, };
static final int[] jjstates32 = { 47, 48, 17, };
static final int[] jjstates33 = { 37, 38, 43, 44, 47, 48, 17, };
static final int[] jjstates34 = { 53, 11, };
static final int[] jjstates35 = { 53, };
static final int[] jjstates36 = { 54, 11, };
static final int[] jjstates37 = { 52, 54, 11, };
static final int[] jjstates38 = { 59, };
static final int[] jjstates39 = { 57, 58, 60, };
static final int[] jjstates40 = { 65, 63, };
static final int[] jjstates41 = { 63, 64, 66, };
static final int[] jjstates42 = { 62, 63, };
static final int[] jjstates43 = { 56, 61, };
static final int[] jjallInitStates_0 = { 0, 3, 4, 6, 7, 9, 12, 18, 27, 36, 51, 55, };
static final int[] jjstates44 = { 27, };
static final int[] jjstates45 = { 0, };
static final int[] jjstates46 = { 18, };
static final int[] jjstates47 = { 12, };
static final int[] jjstates48 = { 55, };
static final int[] jjstates49 = { 36, 51, };
static final int[] jjstates50 = { 9, 36, };
static final int[] jjstates51 = { 3, 7, };
static final int[] jjstates52 = { 7, };
static final int[] jjstates53 = { 6, 7, };
static final int[] jjstates54 = { 4, };
static final int[][] jjinitStates_0 = {
jjallInitStates_0, null, null, null, null, null,
null, null, null, null, null,
null, null, null, null, null,
null, null, null, null, null,
null, null, null, null, null,
null, null, null, null, null,
null, null, null, jjstates44, null,
null, null, jjstates45, jjstates46, null,
null, null, null, null, null,
jjstates47, jjstates48, jjstates49, jjstates50, jjstates50,
jjstates50, jjstates50, jjstates50, jjstates50, jjstates50,
jjstates50, jjstates50, null, null, null,
null, null, null, null, jjstates51,
jjstates52, jjstates52, jjstates52, jjstates52, jjstates52,
jjstates52, jjstates52, jjstates52, jjstates52, jjstates52,
jjstates52, jjstates52, jjstates52, jjstates53, jjstates52,
jjstates52, jjstates52, jjstates52, jjstates52, jjstates52,
jjstates52, jjstates52, jjstates52, jjstates52, jjstates52,
null, null, null, null, jjstates52,
null, jjstates51, jjstates52, jjstates52, jjstates52,
jjstates52, jjstates52, jjstates52, jjstates52, jjstates52,
jjstates52, jjstates52, jjstates52, jjstates52, jjstates52,
jjstates53, jjstates52, jjstates52, jjstates52, jjstates52,
jjstates52, jjstates52, jjstates52, jjstates52, jjstates52,
jjstates52, jjstates52, null, jjstates54, null,
null, null, };

/*package*/ static final String[] jjstrLiteralImages = {
null, null, null, null, null, null, null, null, "\41", "\176", "\75",
"\75\75", "\41\75", "\74\76", "\76", "\74", "\74\75", "\76\75", "\53", "\55", "\52",
"\57", "\45", null, null, "\136", null, null, null, null, null, null, null, null,
"\44", "\72", null, null, null, "\50", "\51", "\54", "\56", "\55\76", null, null,
null, null, null, null, null, null, };
/*package*/ static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken =
{
0xd1f9fffffff00L,
};
static final long[] jjtoSkip =
{
0xfeL,
};
static final long[] jjtoSpecial =
{
0xc0L,
};
private ASCII_UCodeESC_CharStream input_stream;
private int[] jjrounds = new int[67];
private int[] jjstateSet1 = new int[67];
private int[] jjstateSet2 = new int[67];
private int[] jjnextStates;
int curPos;
/*package*/ char curChar;
int jjcurKind = 2147483647;
OqlParserTokenManager jjme;
/*package*/ OqlParserTokenManager(ASCII_UCodeESC_CharStream stream)
{
input_stream = stream;
jjme = this;
}
/*package*/ OqlParserTokenManager(ASCII_UCodeESC_CharStream stream, int lexState)
{
input_stream = stream;
jjme = this;
   if (lexState >= 1 || lexState < 0)
   {
      System.out.println("Error: Ignoring invalid lexical state : " + lexState + ". Starting lexer in DEFAULT state.");
      curLexState = defaultLexState;
   }
   else
      curLexState = lexState;
}
/*package*/ void ReInit(ASCII_UCodeESC_CharStream stream)
{
int i;
jjcurKind = 2147483647;
curPos = jjmatchedPos = jjnewStateCnt = jjround = 0;
jjstartStateSet = null;
curChar = (char)0;
jjEOFSeen = false;
curLexState = defaultLexState;
input_stream = stream;
for (i = 67; i-- > 0;)
   jjrounds[i] = 0;
}
/*package*/ void ReInit(ASCII_UCodeESC_CharStream stream, int lexState)
{
int i;
jjcurKind = 2147483647;
curPos = jjmatchedPos = jjnewStateCnt = jjround = 0;
jjstartStateSet = null;
curChar = (char)0;
jjEOFSeen = false;
   if (lexState >= 1 || lexState < 0)
   {
      System.out.println("Error: Ignoring invalid lexical state : " + lexState + ". Reinitialzing lexer in DEFAULT state.");
      curLexState = defaultLexState;
   }
   else
      curLexState = lexState;
input_stream = stream;
for (i = 67; i-- > 0;)
   jjrounds[i] = 0;
}
/*package*/ void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      System.out.println("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.");
   else
      curLexState = lexState;
}


int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
private boolean jjEOFSeen = false;
int[] jjtmpStates = jjstateSet1;
int jjmatchedPos;
Token matchedToken;
Token jjspecialToken;
private int[] jjstartStateSet;

/*package*/ Token getNextToken() throws ParseError
{
  matchedToken = new Token();

  EOFLoop : for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      matchedToken.endLine = matchedToken.beginLine = input_stream.getBeginLine();
      matchedToken.endColumn = matchedToken.beginColumn = input_stream.getBeginColumn();
      matchedToken.kind = 0;
      matchedToken.image = "";
      matchedToken.specialToken = jjspecialToken;
      jjspecialToken = null;
      return matchedToken;
   }

   try
   {
    for (;;)
    {
      matchedToken.kind = 2147483647;
      jjmatchedPos = 0;
      jjMoveStringLiteralDfa0_0();
      int tmpInd = 0;
      char[] tmpBuff = input_stream.GetSuffix(curPos);
      jjtmpStates = jjstateSet1;
      System.arraycopy(jjallInitStates_0, 0, jjstateSet2, 0, jjnewStateCnt = jjallInitStates_0.length);
      do
      {
         curChar = tmpBuff[tmpInd];
         jjMoveNfa_0(jjstateSet1 = jjstateSet2, jjstateSet2 = jjtmpStates);
         jjtmpStates = jjstateSet1;
         if (jjcurKind != 2147483647)
         {
            if (tmpInd > jjmatchedPos)
            {
               matchedToken.kind = jjcurKind;
               jjmatchedPos = tmpInd;
            }
            else if (tmpInd == jjmatchedPos && matchedToken.kind > jjcurKind)
               matchedToken.kind = jjcurKind;
            jjcurKind = 2147483647;
         }
      }
      while (jjnewStateCnt != 0 && ++tmpInd < curPos);
      while (jjnewStateCnt != 0)
      {
         curChar = input_stream.readChar();
         jjMoveNfa_0(jjstateSet1 = jjstateSet2, jjstateSet2 = jjtmpStates);
         jjtmpStates = jjstateSet1;
         if (jjcurKind != 2147483647)
         {
            matchedToken.kind = jjcurKind;
            jjcurKind = 2147483647;
            jjmatchedPos = curPos++;
         }
         else
            curPos++;
      }

      if (matchedToken.kind != 2147483647)
      {
         if (jjmatchedPos + 1 < curPos)
            input_stream.backup(curPos - jjmatchedPos - 1);

         if ((jjtoToken[matchedToken.kind >> 6] &
             (1L << (matchedToken.kind & 077))) != 0L)
         {
            if ((matchedToken.image = jjstrLiteralImages[matchedToken.kind]) == null)
               matchedToken.image = input_stream.GetImage();
            matchedToken.beginLine = input_stream.getBeginLine();
            matchedToken.beginColumn = input_stream.getBeginColumn();
            matchedToken.endLine = input_stream.getEndLine();
            matchedToken.endColumn = input_stream.getEndColumn();
            matchedToken.specialToken = jjspecialToken;
            jjspecialToken = null;
            return matchedToken;
         }
         else
         {
            if ((jjtoSpecial[matchedToken.kind >> 6] &
                (1L << (matchedToken.kind & 077))) != 0L)
            {
               if ((matchedToken.image = jjstrLiteralImages[matchedToken.kind]) == null)
                  matchedToken.image = input_stream.GetImage();
               matchedToken.beginLine = input_stream.getBeginLine();
               matchedToken.beginColumn = input_stream.getBeginColumn();
               matchedToken.endLine = input_stream.getEndLine();
               matchedToken.endColumn = input_stream.getEndColumn();
               if (jjspecialToken == null)
                  jjspecialToken = matchedToken;
               else
               {
                  matchedToken.specialToken = jjspecialToken;
                  jjspecialToken = (jjspecialToken.next = matchedToken);
               }
               matchedToken = new Token();
            }
            continue EOFLoop;
         }
      }
      else
      {
         jjme.error_line = input_stream.getEndLine();
         jjme.error_column = input_stream.getEndColumn();
         input_stream.backup(1);
         jjme.error_after = curPos <= 1 ? "" : jjadd_escapes(input_stream.GetImage());
         jjme.LexicalError();

         throw new ParseError();
      }
    }
   }
   catch (java.io.IOException e)
   {
      switch(curLexState)
      {
         default : break;
      }
      if (matchedToken.kind != 2147483647 && matchedToken.kind != 0)
      {
         if (jjmatchedPos + 1 < curPos)
            input_stream.backup(curPos - jjmatchedPos - 1);

         if ((jjtoToken[matchedToken.kind >> 6] &
             (1L << (matchedToken.kind & 077))) != 0L)
         {
            if ((matchedToken.image = jjstrLiteralImages[matchedToken.kind]) == null)
               matchedToken.image = input_stream.GetImage();
            matchedToken.beginLine = input_stream.getBeginLine();
            matchedToken.beginColumn = input_stream.getBeginColumn();
            matchedToken.endLine = input_stream.getEndLine();
            matchedToken.endColumn = input_stream.getEndColumn();
            matchedToken.specialToken = jjspecialToken;
            jjspecialToken = null;
            return matchedToken;
         }
         else
         {
            if ((jjtoSpecial[matchedToken.kind >> 6] &
                (1L << (matchedToken.kind & 077))) != 0L)
            {
               if ((matchedToken.image = jjstrLiteralImages[matchedToken.kind]) == null)
                  matchedToken.image = input_stream.GetImage();
               matchedToken.beginLine = input_stream.getBeginLine();
               matchedToken.beginColumn = input_stream.getBeginColumn();
               matchedToken.endLine = input_stream.getEndLine();
               matchedToken.endColumn = input_stream.getEndColumn();
               if (jjspecialToken == null)
                  jjspecialToken = matchedToken;
               else
               {
                  matchedToken.specialToken = jjspecialToken;
                  jjspecialToken = (jjspecialToken.next = matchedToken);
               }
               matchedToken = new Token();
            }
            continue EOFLoop;
         }
      }

      jjme.error_line = input_stream.getEndLine();
      jjme.error_column = input_stream.getEndColumn();
      input_stream.backup(1);
      jjEOFSeen = true;
      jjme.error_after = curPos <= 1 ? "" : jjadd_escapes(input_stream.GetImage());
      jjme.LexicalError();

      throw new ParseError();
   }
  }
}

/*package*/ int error_line, error_column;
/*package*/ String error_after;
/*package*/ void LexicalError()
{
   System.err.println("Lexical error at line " +
        error_line + ", column " +
        error_column + ".  Encountered: " +
        (jjEOFSeen ? "<EOF>" : ("\"" + jjadd_escapes(String.valueOf(curChar)) + "\"") + " (" + (int)curChar + "), ") +
        "after : \"" + jjme.error_after + "\"");
}
}
