/*======================================================================
**
**  File: chimu/formTools/preprocessor/FormPreprocessorTokenManager.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;
import java.io.*;
import java.util.Vector;

public class FormPreprocessorTokenManager implements FormPreprocessorConstants
{
  protected static final String jjadd_escapes(String str) {
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
static private final void jjMoveNfa_0(int[] oldStates, int[] newStates)
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
               case 49:
                  if ((0x800000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates37;
                     break;
                  }
                  else continue;
               case 50:
                  if ((0xffffffffffffdbffL & l) != 0L)
                  {
                     jjnextStates = jjstates37;
                     break;
                  }
                  else continue;
               case 51:
                  if ((0x2400L & l) != 0L)
                  {
                     if (jjcurKind > 6)
                        jjcurKind = 6;
                     continue;
                  }
                  else continue;
               case 53:
                  if ((0x2000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 52;
                     jjnewStateCnt += 1;
                     continue;
                  }
                  else continue;
               case 52:
                  if ((0x400L & l) != 0L)
                  {
                     if (jjcurKind > 6)
                        jjcurKind = 6;
                     continue;
                  }
                  else continue;
               case 60:
                  if ((0x40000000000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 54;
                     jjnewStateCnt += 1;
                     continue;
                  }
                  else continue;
               case 54:
                  if ((0x40000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates40;
                     break;
                  }
                  else continue;
               case 55:
                  if ((0xfffffbffffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates40;
                     break;
                  }
                  else continue;
               case 56:
                  if ((0x40000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates39;
                     break;
                  }
                  else continue;
               case 57:
                  if ((0xffff7bffffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates38;
                     break;
                  }
                  else continue;
               case 58:
                  if ((0xfffffbffffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates38;
                     break;
                  }
                  else continue;
               case 59:
                  if ((0x800000000000L & l) != 0L)
                  {
                     if (jjcurKind > 7)
                        jjcurKind = 7;
                     continue;
                  }
                  else continue;
               case 61:
                  if ((0x40000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates44;
                     break;
                  }
                  else continue;
               case 62:
                  if ((0xfffffbffffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates44;
                     break;
                  }
                  else continue;
               case 63:
                  if ((0x40000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates43;
                     break;
                  }
                  else continue;
               case 64:
                  if ((0xffff7bffffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates42;
                     break;
                  }
                  else continue;
               case 65:
                  if ((0xfffffbffffffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates42;
                     break;
                  }
                  else continue;
               case 66:
                  if ((0x800000000000L & l) != 0L)
                  {
                     if (jjcurKind > 8)
                        jjcurKind = 8;
                     continue;
                  }
                  else continue;
               case 0:
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 59)
                        jjcurKind = 59;
                     jjnextStates = jjstates0;
                     break;
                  }
                  else continue;
               case 1:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 59)
                        jjcurKind = 59;
                     jjnextStates = jjstates0;
                     break;
                  }
                  else continue;
               case 46:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 59)
                        jjcurKind = 59;
                     jjnextStates = jjstates32;
                     break;
                  }
                  else continue;
               case 47:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 59)
                        jjcurKind = 59;
                     jjnextStates = jjstates34;
                     break;
                  }
                  else continue;
               case 30:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates22;
                     break;
                  }
                  else continue;
               case 31:
                  if ((0x400000000000L & l) != 0L)
                  {
                     if (jjcurKind > 63)
                        jjcurKind = 63;
                     jjnextStates = jjstates21;
                     break;
                  }
                  else continue;
               case 32:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 63)
                        jjcurKind = 63;
                     jjnextStates = jjstates21;
                     break;
                  }
                  else continue;
               case 34:
                  if ((0x280000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates19;
                     break;
                  }
                  else continue;
               case 35:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 63)
                        jjcurKind = 63;
                     jjnextStates = jjstates18;
                     break;
                  }
                  else continue;
               case 3:
                  if ((0x400000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates5;
                     break;
                  }
                  else continue;
               case 4:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 63)
                        jjcurKind = 63;
                     jjnextStates = jjstates4;
                     break;
                  }
                  else continue;
               case 6:
                  if ((0x280000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates2;
                     break;
                  }
                  else continue;
               case 7:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 63)
                        jjcurKind = 63;
                     jjnextStates = jjstates1;
                     break;
                  }
                  else continue;
               case 36:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates26;
                     break;
                  }
                  else continue;
               case 38:
                  if ((0x280000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates24;
                     break;
                  }
                  else continue;
               case 39:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 63)
                        jjcurKind = 63;
                     jjnextStates = jjstates23;
                     break;
                  }
                  else continue;
               case 40:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates30;
                     break;
                  }
                  else continue;
               case 42:
                  if ((0x280000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates28;
                     break;
                  }
                  else continue;
               case 43:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates27;
                     break;
                  }
                  else continue;
               case 9:
                  if ((0x8000000000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 10;
                     newStates[jjnewStateCnt + 1] = 12;
                     jjnewStateCnt += 2;
                     continue;
                  }
                  else continue;
               case 10:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                  {
                     jjnextStates = jjstates6;
                     break;
                  }
                  else continue;
               case 13:
                  if ((0x8400000000L & l) != 0L)
                  {
                     jjnextStates = jjstates6;
                     break;
                  }
                  else continue;
               case 14:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates7;
                     break;
                  }
                  else continue;
               case 15:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates6;
                     break;
                  }
                  else continue;
               case 16:
                  if ((0xf000000000000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 17;
                     jjnewStateCnt += 1;
                     continue;
                  }
                  else continue;
               case 17:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates8;
                     break;
                  }
                  else continue;
               case 11:
                  if ((0x8000000000L & l) != 0L)
                  {
                     if (jjcurKind > 65)
                        jjcurKind = 65;
                     continue;
                  }
                  else continue;
               case 18:
                  if ((0x400000000L & l) != 0L)
                  {
                     jjnextStates = jjstates12;
                     break;
                  }
                  else continue;
               case 19:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                  {
                     jjnextStates = jjstates12;
                     break;
                  }
                  else continue;
               case 21:
                  if ((0x8400000000L & l) != 0L)
                  {
                     jjnextStates = jjstates12;
                     break;
                  }
                  else continue;
               case 23:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates13;
                     break;
                  }
                  else continue;
               case 24:
                  if ((0xff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates12;
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
                     jjnextStates = jjstates14;
                     break;
                  }
                  else continue;
               case 22:
                  if ((0x400000000L & l) != 0L)
                  {
                     if (jjcurKind > 66)
                        jjcurKind = 66;
                     continue;
                  }
                  else continue;
               case 27:
                  if ((0x1000000000L & l) != 0L)
                  {
                     if (jjcurKind > 67)
                        jjcurKind = 67;
                     jjnextStates = jjstates17;
                     break;
                  }
                  else continue;
               case 28:
                  if ((0x3ff001000000000L & l) != 0L)
                  {
                     if (jjcurKind > 67)
                        jjcurKind = 67;
                     jjnextStates = jjstates17;
                     break;
                  }
                  else continue;
               case 29:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     jjnextStates = jjstates31;
                     break;
                  }
                  else continue;
               case 44:
                  if ((0x1000000000000L & l) != 0L)
                  {
                     if (jjcurKind > 59)
                        jjcurKind = 59;
                     jjnextStates = jjstates35;
                     break;
                  }
                  else continue;
               case 48:
                  if ((0x800000000000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 49;
                     newStates[jjnewStateCnt + 1] = 60;
                     newStates[jjnewStateCnt + 2] = 61;
                     jjnewStateCnt += 3;
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
               case 50:
                     newStates[jjnewStateCnt + 0] = 50;
                     newStates[jjnewStateCnt + 1] = 51;
                     newStates[jjnewStateCnt + 2] = 53;
                     jjnewStateCnt += 3;
                     continue;
               case 55:
                     jjnextStates = jjstates40;
                     break;
               case 57:
                     jjnextStates = jjstates38;
                     break;
               case 58:
                     jjnextStates = jjstates38;
                     break;
               case 62:
                     jjnextStates = jjstates44;
                     break;
               case 64:
                     jjnextStates = jjstates42;
                     break;
               case 65:
                     jjnextStates = jjstates42;
                     break;
               case 2:
                  if ((0x100000001000L & l) != 0L)
                  {
                     if (jjcurKind > 59)
                        jjcurKind = 59;
                     continue;
                  }
                  else continue;
               case 45:
                  if ((0x100000001000000L & l) != 0L)
                  {
                     jjnextStates = jjstates33;
                     break;
                  }
                  else continue;
               case 46:
                  if ((0x7e0000007eL & l) != 0L)
                  {
                     if (jjcurKind > 59)
                        jjcurKind = 59;
                     jjnextStates = jjstates32;
                     break;
                  }
                  else continue;
               case 33:
                  if ((0x2000000020L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 34;
                     newStates[jjnewStateCnt + 1] = 35;
                     jjnewStateCnt += 2;
                     continue;
                  }
                  else continue;
               case 5:
                  if ((0x2000000020L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 6;
                     newStates[jjnewStateCnt + 1] = 7;
                     jjnewStateCnt += 2;
                     continue;
                  }
                  else continue;
               case 8:
                  if ((0x5000000050L & l) != 0L)
                  {
                     if (jjcurKind > 63)
                        jjcurKind = 63;
                     continue;
                  }
                  else continue;
               case 37:
                  if ((0x2000000020L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 38;
                     newStates[jjnewStateCnt + 1] = 39;
                     jjnewStateCnt += 2;
                     continue;
                  }
                  else continue;
               case 41:
                  if ((0x2000000020L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 42;
                     newStates[jjnewStateCnt + 1] = 43;
                     jjnewStateCnt += 2;
                     continue;
                  }
                  else continue;
               case 10:
                  if ((0xffffffffefffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates6;
                     break;
                  }
                  else continue;
               case 12:
                  if ((0x10000000L & l) != 0L)
                  {
                     newStates[jjnewStateCnt + 0] = 13;
                     newStates[jjnewStateCnt + 1] = 14;
                     newStates[jjnewStateCnt + 2] = 16;
                     jjnewStateCnt += 3;
                     continue;
                  }
                  else continue;
               case 13:
                  if ((0x14404410000000L & l) != 0L)
                  {
                     jjnextStates = jjstates6;
                     break;
                  }
                  else continue;
               case 19:
                  if ((0xffffffffefffffffL & l) != 0L)
                  {
                     jjnextStates = jjstates12;
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
                     jjnextStates = jjstates12;
                     break;
                  }
                  else continue;
               case 27:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (jjcurKind > 67)
                        jjcurKind = 67;
                     jjnextStates = jjstates17;
                     break;
                  }
                  else continue;
               case 28:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (jjcurKind > 67)
                        jjcurKind = 67;
                     jjnextStates = jjstates17;
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
               case 50:
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
                     newStates[jjnewStateCnt + 0] = 50;
                     newStates[jjnewStateCnt + 1] = 51;
                     newStates[jjnewStateCnt + 2] = 53;
                     jjnewStateCnt += 3;
                     continue;
               case 55:
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
                     jjnextStates = jjstates38;
                     break;
               case 58:
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
                     jjnextStates = jjstates38;
                     break;
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
                     jjnextStates = jjstates44;
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
                     jjnextStates = jjstates42;
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
                     jjnextStates = jjstates42;
                     break;
               case 10:
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
                     newStates[jjnewStateCnt + 0] = 11;
                     jjnewStateCnt += 1;
                     continue;
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
               case 27:
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
                  if (jjcurKind > 67)
                     jjcurKind = 67;
                     jjnextStates = jjstates17;
                     break;
               case 28:
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
                  if (jjcurKind > 67)
                     jjcurKind = 67;
                     jjnextStates = jjstates17;
                     break;
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
static int[] StopStringLiteralDfa_0(int pos, long active0, long active1)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x7fffffffffffe00L) != 0L)
         {
            matchedToken.kind = 67;
            return jjstates17;
         }
         if ((active1 & 0x4000L) != 0L)
            return jjstates5;
         if ((active1 & 0x100200000000L) != 0L)
            return jjstates45;
         return null;
      case 1:
         if ((active0 & 0x7ffffffbfcffe00L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               matchedToken.kind = 67;
               jjmatchedPos = 1;
            }
            return jjstates17;
         }
         if ((active0 & 0x40300000L) != 0L)
            return jjstates17;
         return null;
      case 2:
         if ((active0 & 0x77fffb3afeffe00L) != 0L)
         {
            if (jjmatchedPos != 2)
            {
               matchedToken.kind = 67;
               jjmatchedPos = 2;
            }
            return jjstates17;
         }
         if ((active0 & 0x80004c10000000L) != 0L)
            return jjstates17;
         return null;
      case 3:
         if ((active0 & 0x63bff2b8faf4e00L) != 0L)
         {
            if (jjmatchedPos != 3)
            {
               matchedToken.kind = 67;
               jjmatchedPos = 3;
            }
            return jjstates17;
         }
         if ((active0 & 0x14400902040b000L) != 0L)
            return jjstates17;
         return null;
      case 4:
         if ((active0 & 0x2235f2b80ac0600L) != 0L)
         {
            if (jjmatchedPos != 4)
            {
               matchedToken.kind = 67;
               jjmatchedPos = 4;
            }
            return jjstates17;
         }
         if ((active0 & 0x418a0000f034800L) != 0L)
            return jjstates17;
         return null;
      case 5:
         if ((active0 & 0x222070a848c0600L) != 0L)
         {
            if (jjmatchedPos != 5)
            {
               matchedToken.kind = 67;
               jjmatchedPos = 5;
            }
            return jjstates17;
         }
         if ((active0 & 0x11582100200000L) != 0L)
            return jjstates17;
         return null;
      case 6:
         if ((active0 & 0x222040a80040200L) != 0L)
         {
            if (jjmatchedPos != 6)
            {
               matchedToken.kind = 67;
               jjmatchedPos = 6;
            }
            return jjstates17;
         }
         if ((active0 & 0x30004880400L) != 0L)
            return jjstates17;
         return null;
      case 7:
         if ((active0 & 0x22040a80000000L) != 0L)
         {
            if (jjmatchedPos != 7)
            {
               matchedToken.kind = 67;
               jjmatchedPos = 7;
            }
            return jjstates17;
         }
         if ((active0 & 0x200000000040200L) != 0L)
            return jjstates17;
         return null;
      case 8:
         if ((active0 & 0x2000280000000L) != 0L)
         {
            if (jjmatchedPos != 8)
            {
               matchedToken.kind = 67;
               jjmatchedPos = 8;
            }
            return jjstates17;
         }
         if ((active0 & 0x20040800000000L) != 0L)
            return jjstates17;
         return null;
      case 9:
         if ((active0 & 0x2000000000000L) != 0L)
         {
            if (jjmatchedPos != 9)
            {
               matchedToken.kind = 67;
               jjmatchedPos = 9;
            }
            return jjstates17;
         }
         if ((active0 & 0x280000000L) != 0L)
            return jjstates17;
         return null;
      case 10:
         if ((active0 & 0x2000000000000L) != 0L)
         {
            if (jjmatchedPos != 10)
            {
               matchedToken.kind = 67;
               jjmatchedPos = 10;
            }
            return jjstates17;
         }
         return null;
      case 11:
         if ((active0 & 0x2000000000000L) != 0L)
            return jjstates17;
         return null;
      default : 
         return null;
   }
}
static void jjStartNfa_0(int pos, long active0, long active1)
{
   if ((jjstartStateSet = StopStringLiteralDfa_0(pos, active0, active1)) != null)
   {
      jjnewStateCnt = jjstartStateSet.length;
      jjMoveNfa_0(jjstartStateSet, jjstateSet2);
      if (jjcurKind != 2147483647)
      {
         matchedToken.kind = jjcurKind;
         jjmatchedPos = pos + 1;
         jjcurKind = 2147483647;
      }
   }
   return;
}
static long jjnewActive_00 = 0L, jjoldActive_00 = 0L,
            jjnewActive_01 = 0L, jjoldActive_01 = 0L;
static private final void jjMoveStringLiteralDfa0_0() throws java.io.IOException
{
   ReturnLoop:
   for (;;)
   {
    MainLoop:
    for (;;)
    {
         switch(curChar)
         {
            case 100: 
               jjoldActive_00 = 0x380000L;
               jjoldActive_01 = 0L;
               break;
            case 99: 
               jjoldActive_00 = 0x7e000L;
               jjoldActive_01 = 0L;
               break;
            case 98: 
               jjoldActive_00 = 0x1c00L;
               jjoldActive_01 = 0L;
               break;
            case 97: 
               jjoldActive_00 = 0x200L;
               jjoldActive_01 = 0L;
               break;
            case 94: 
               matchedToken.kind = 100;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x800000000000L;
               break;
            case 93: 
               matchedToken.kind = 75;
               break ReturnLoop;
            case 91: 
               matchedToken.kind = 74;
               break ReturnLoop;
            case 63: 
               matchedToken.kind = 84;
               break ReturnLoop;
            case 62: 
               matchedToken.kind = 80;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0xc018001000000L;
               break;
            case 61: 
               matchedToken.kind = 79;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x400000L;
               break;
            case 60: 
               matchedToken.kind = 81;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x2004000800000L;
               break;
            case 59: 
               matchedToken.kind = 76;
               break ReturnLoop;
            case 58: 
               matchedToken.kind = 85;
               break ReturnLoop;
            case 47: 
               matchedToken.kind = 97;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x100000000000L;
               break;
            case 46: 
               matchedToken.kind = 78;
               jjstartStateSet = jjstates5;
               break MainLoop;
            case 45: 
               matchedToken.kind = 95;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x40020000000L;
               break;
            case 44: 
               matchedToken.kind = 77;
               break ReturnLoop;
            case 43: 
               matchedToken.kind = 94;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x20010000000L;
               break;
            case 42: 
               matchedToken.kind = 96;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x80000000000L;
               break;
            case 41: 
               matchedToken.kind = 71;
               break ReturnLoop;
            case 40: 
               matchedToken.kind = 70;
               break ReturnLoop;
            case 38: 
               matchedToken.kind = 98;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x200008000000L;
               break;
            case 37: 
               matchedToken.kind = 101;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x1000000000000L;
               break;
            case 33: 
               matchedToken.kind = 82;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x2000000L;
               break;
            case 32: 
               matchedToken.kind = 1;
               break ReturnLoop;
            case 126: 
               matchedToken.kind = 83;
               break ReturnLoop;
            case 125: 
               matchedToken.kind = 73;
               break ReturnLoop;
            case 124: 
               matchedToken.kind = 99;
               jjoldActive_00 = 0L;
               jjoldActive_01 = 0x400004000000L;
               break;
            case 123: 
               matchedToken.kind = 72;
               break ReturnLoop;
            case 119: 
               jjoldActive_00 = 0x400000000000000L;
               jjoldActive_01 = 0L;
               break;
            case 118: 
               jjoldActive_00 = 0x300000000000000L;
               jjoldActive_01 = 0L;
               break;
            case 116: 
               jjoldActive_00 = 0xfc000000000000L;
               jjoldActive_01 = 0L;
               break;
            case 115: 
               jjoldActive_00 = 0x3e00000000000L;
               jjoldActive_01 = 0L;
               break;
            case 114: 
               jjoldActive_00 = 0x100000000000L;
               jjoldActive_01 = 0L;
               break;
            case 13: 
               matchedToken.kind = 4;
               break ReturnLoop;
            case 12: 
               matchedToken.kind = 5;
               break ReturnLoop;
            case 112: 
               jjoldActive_00 = 0xf0000000000L;
               jjoldActive_01 = 0L;
               break;
            case 10: 
               matchedToken.kind = 3;
               break ReturnLoop;
            case 110: 
               jjoldActive_00 = 0xe000000000L;
               jjoldActive_01 = 0L;
               break;
            case 9: 
               matchedToken.kind = 2;
               break ReturnLoop;
            case 108: 
               jjoldActive_00 = 0x1000000000L;
               jjoldActive_01 = 0L;
               break;
            case 105: 
               jjoldActive_00 = 0xfc0000000L;
               jjoldActive_01 = 0L;
               break;
            case 103: 
               jjoldActive_00 = 0x20000000L;
               jjoldActive_01 = 0L;
               break;
            case 102: 
               jjoldActive_00 = 0x1f000000L;
               jjoldActive_01 = 0L;
               break;
            case 101: 
               jjoldActive_00 = 0xc00000L;
               jjoldActive_01 = 0L;
               break;
            default : 
               curPos =  1;
               if (curChar < 128)
                  if ((jjstartStateSet = jjinitStates_0[curChar]) == null)
                     return;
               else 
                  jjstartStateSet = jjallInitStates_0;
               jjnewStateCnt = jjstartStateSet.length;
               jjMoveNfa_0(jjstartStateSet, jjstateSet2);
               if (jjcurKind != 2147483647)
               {
                  matchedToken.kind = jjcurKind;
                  jjcurKind = 2147483647;
               }
               return;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(0, jjoldActive_00, jjoldActive_01); 
            curPos = 1;
            throw e;
         }
         jjMoveStringLiteralDfa1_0();
         return;
    }
    curPos = 1;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 1;
       jjcurKind = 2147483647;
    }
    curPos = 2;
    return;
   }
   curPos = 1;
   return;
}
static private final void jjMoveStringLiteralDfa1_0() throws java.io.IOException
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
            case 98: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x200L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 97: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x12001006000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 62: 
               if ((jjoldActive_01 & 0x8000000000L) != 0L)
               {
                  matchedToken.kind = 103;
                  jjmatchedPos = 1;
               }
               if (((jjnewActive_01 = jjoldActive_01 & 0xc010000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_00 = 0L;
               break;
            case 61: 
               if ((jjoldActive_01 & 0x400000L) != 0L)
               {
                  matchedToken.kind = 86;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x800000L) != 0L)
               {
                  matchedToken.kind = 87;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x1000000L) != 0L)
               {
                  matchedToken.kind = 88;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x2000000L) != 0L)
               {
                  matchedToken.kind = 89;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x20000000000L) != 0L)
               {
                  matchedToken.kind = 105;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x40000000000L) != 0L)
               {
                  matchedToken.kind = 106;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x80000000000L) != 0L)
               {
                  matchedToken.kind = 107;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x100000000000L) != 0L)
               {
                  matchedToken.kind = 108;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x200000000000L) != 0L)
               {
                  matchedToken.kind = 109;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x400000000000L) != 0L)
               {
                  matchedToken.kind = 110;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x800000000000L) != 0L)
               {
                  matchedToken.kind = 111;
                  break ReturnLoop;
               }
               else if ((jjoldActive_01 & 0x1000000000000L) != 0L)
               {
                  matchedToken.kind = 112;
                  break ReturnLoop;
               }
               break StartNfaLoop;
            case 60: 
               if ((jjoldActive_01 & 0x4000000000L) != 0L)
               {
                  matchedToken.kind = 102;
                  jjmatchedPos = 1;
               }
               if (((jjnewActive_01 = jjoldActive_01 & 0x2000000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_00 = 0L;
               break;
            case 45: 
               if ((jjoldActive_01 & 0x20000000L) != 0L)
               {
                  matchedToken.kind = 93;
                  break ReturnLoop;
               }
               break StartNfaLoop;
            case 43: 
               if ((jjoldActive_01 & 0x10000000L) != 0L)
               {
                  matchedToken.kind = 92;
                  break ReturnLoop;
               }
               break StartNfaLoop;
            case 38: 
               if ((jjoldActive_01 & 0x8000000L) != 0L)
               {
                  matchedToken.kind = 91;
                  break ReturnLoop;
               }
               break StartNfaLoop;
            case 124: 
               if ((jjoldActive_01 & 0x4000000L) != 0L)
               {
                  matchedToken.kind = 90;
                  break ReturnLoop;
               }
               break StartNfaLoop;
            case 121: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x2000000001000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 120: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x800000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 119: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x1000000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 117: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x888000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 116: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x400000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 114: 
               if (((jjnewActive_00 = jjoldActive_00 & 0xe0060000000800L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 111: 
               if ((jjoldActive_00 & 0x100000L) != 0L)
               {
                  matchedToken.kind = 20;
                  jjmatchedPos = 1;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x300001030260400L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 110: 
               if (((jjnewActive_00 = jjoldActive_00 & 0xe00000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 109: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x180000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 108: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x8410000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 105: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x6000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 104: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x41c200000008000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 102: 
               if ((jjoldActive_00 & 0x40000000L) != 0L)
               {
                  matchedToken.kind = 30;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 101: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x104000080000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            default : 
               break StartNfaLoop;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(1, jjnewActive_00, jjnewActive_01); 
            curPos = 2;
            throw e;
         }
         jjMoveStringLiteralDfa2_0();
         return;
      }
      jjStartNfa_0(0, jjoldActive_00, jjoldActive_01); 
      curPos = 2;
      return;
    }
    jjmatchedPos = 1;
    curPos = 2;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 2;
       jjcurKind = 2147483647;
    }
    curPos = 3;
    return;
   }
   jjmatchedPos = 1;
   curPos = 2;
   return;
}
static private final void jjMoveStringLiteralDfa2_0() throws java.io.IOException
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
               if (((jjoldActive_00 = jjnewActive_00 & 0x10000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 98: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x80000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 97: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x20400000018000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 62: 
               if ((jjnewActive_01 & 0x10000000000L) != 0L)
               {
                  matchedToken.kind = 104;
                  jjmatchedPos = 2;
               }
               if (((jjoldActive_01 = jjnewActive_01 & 0x8000000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_00 = 0L;
               break;
            case 61: 
               if ((jjnewActive_01 & 0x2000000000000L) != 0L)
               {
                  matchedToken.kind = 113;
                  break ReturnLoop;
               }
               else if ((jjnewActive_01 & 0x4000000000000L) != 0L)
               {
                  matchedToken.kind = 114;
                  break ReturnLoop;
               }
               break StartNfaLoop;
            case 121: 
               if ((jjnewActive_00 & 0x80000000000000L) != 0L)
               {
                  matchedToken.kind = 55;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 119: 
               if ((jjnewActive_00 & 0x4000000000L) != 0L)
               {
                  matchedToken.kind = 38;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 117: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x40000000200000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 116: 
               if ((jjnewActive_00 & 0x400000000L) != 0L)
               {
                  matchedToken.kind = 34;
                  jjmatchedPos = 2;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x102820805000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 115: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x200402200L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 114: 
               if ((jjnewActive_00 & 0x10000000L) != 0L)
               {
                  matchedToken.kind = 28;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x18000000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 112: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x800180000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 111: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x240008000400L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 110: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x2001006060000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 108: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x200008001000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 105: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x505020000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 102: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x80000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 101: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x800L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            default : 
               break StartNfaLoop;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(2, jjoldActive_00, jjoldActive_01); 
            curPos = 3;
            throw e;
         }
         jjMoveStringLiteralDfa3_0();
         return;
      }
      jjStartNfa_0(1, jjnewActive_00, jjnewActive_01); 
      curPos = 3;
      return;
    }
    jjmatchedPos = 2;
    curPos = 3;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 3;
       jjcurKind = 2147483647;
    }
    curPos = 4;
    return;
   }
   jjmatchedPos = 2;
   curPos = 3;
   return;
}
static private final void jjMoveStringLiteralDfa3_0() throws java.io.IOException
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
            case 100: 
               if ((jjoldActive_00 & 0x100000000000000L) != 0L)
               {
                  matchedToken.kind = 56;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 99: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x2000000004000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 98: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x200000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 97: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x20000000e080800L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 61: 
               if ((jjoldActive_01 & 0x8000000000000L) != 0L)
               {
                  matchedToken.kind = 115;
                  break ReturnLoop;
               }
               break StartNfaLoop;
            case 118: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x20000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 117: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x100000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 116: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x1440200040200L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 115: 
               if ((jjoldActive_00 & 0x4000000000000L) != 0L)
               {
                  matchedToken.kind = 50;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x1030000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 114: 
               if ((jjoldActive_00 & 0x8000L) != 0L)
               {
                  matchedToken.kind = 15;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x200000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 111: 
               if ((jjoldActive_00 & 0x20000000L) != 0L)
               {
                  matchedToken.kind = 29;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x18000100000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 110: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x20000000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 108: 
               if ((jjoldActive_00 & 0x8000000000L) != 0L)
               {
                  matchedToken.kind = 39;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x400080080000400L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 107: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x10000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 105: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x2000000000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            case 103: 
               if ((jjoldActive_00 & 0x1000000000L) != 0L)
               {
                  matchedToken.kind = 36;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 101: 
               if ((jjoldActive_00 & 0x1000L) != 0L)
               {
                  matchedToken.kind = 12;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               else if ((jjoldActive_00 & 0x2000L) != 0L)
               {
                  matchedToken.kind = 13;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               else if ((jjoldActive_00 & 0x400000L) != 0L)
               {
                  matchedToken.kind = 22;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               else if ((jjoldActive_00 & 0x40000000000000L) != 0L)
               {
                  matchedToken.kind = 54;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x800800800000L)) == 0L)
                  break StartNfaLoop;
               jjnewActive_01 = 0L;
               break;
            default : 
               break StartNfaLoop;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(3, jjnewActive_00, jjnewActive_01); 
            curPos = 4;
            throw e;
         }
         jjMoveStringLiteralDfa4_0();
         return;
      }
      jjStartNfa_0(2, jjoldActive_00, jjoldActive_01); 
      curPos = 4;
      return;
    }
    jjmatchedPos = 3;
    curPos = 4;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 4;
       jjcurKind = 2147483647;
    }
    curPos = 5;
    return;
   }
   jjmatchedPos = 3;
   curPos = 4;
   return;
}
static private final void jjMoveStringLiteralDfa4_0() throws java.io.IOException
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
               if (((jjoldActive_00 = jjnewActive_00 & 0x1000000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 97: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x30200000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 119: 
               if ((jjnewActive_00 & 0x8000000000000L) != 0L)
               {
                  matchedToken.kind = 51;
                  jjmatchedPos = 4;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x10000000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 118: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x2000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 117: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x80000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 116: 
               if ((jjnewActive_00 & 0x20000L) != 0L)
               {
                  matchedToken.kind = 17;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               else if ((jjnewActive_00 & 0x8000000L) != 0L)
               {
                  matchedToken.kind = 27;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               else if ((jjnewActive_00 & 0x200000000000L) != 0L)
               {
                  matchedToken.kind = 45;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x200000000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 115: 
               if ((jjnewActive_00 & 0x10000L) != 0L)
               {
                  matchedToken.kind = 16;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x20000000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 114: 
               if ((jjnewActive_00 & 0x800000000000L) != 0L)
               {
                  matchedToken.kind = 47;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x100900000200L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 110: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x800000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 108: 
               if ((jjnewActive_00 & 0x2000000L) != 0L)
               {
                  matchedToken.kind = 25;
                  jjmatchedPos = 4;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x4200000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 107: 
               if ((jjnewActive_00 & 0x800L) != 0L)
               {
                  matchedToken.kind = 11;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 105: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x480000040000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 104: 
               if ((jjnewActive_00 & 0x4000L) != 0L)
               {
                  matchedToken.kind = 14;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x2000000000000L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            case 101: 
               if ((jjnewActive_00 & 0x1000000L) != 0L)
               {
                  matchedToken.kind = 24;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               else if ((jjnewActive_00 & 0x400000000000000L) != 0L)
               {
                  matchedToken.kind = 58;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x40080000400L)) == 0L)
                  break StartNfaLoop;
               jjoldActive_01 = 0L;
               break;
            default : 
               break StartNfaLoop;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(4, jjoldActive_00, jjoldActive_01); 
            curPos = 5;
            throw e;
         }
         jjMoveStringLiteralDfa5_0();
         return;
      }
      jjStartNfa_0(3, jjnewActive_00, jjnewActive_01); 
      curPos = 5;
      return;
    }
    jjmatchedPos = 4;
    curPos = 5;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 5;
       jjcurKind = 2147483647;
    }
    curPos = 6;
    return;
   }
}
static private final void jjMoveStringLiteralDfa5_0() throws java.io.IOException
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
            case 100: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x800000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 99: 
               if ((jjoldActive_00 & 0x80000000000L) != 0L)
               {
                  matchedToken.kind = 43;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               else if ((jjoldActive_00 & 0x400000000000L) != 0L)
               {
                  matchedToken.kind = 46;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x40000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 97: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x600L)) == 0L)
                  break StartNfaLoop;
               break;
            case 116: 
               if ((jjoldActive_00 & 0x100000000L) != 0L)
               {
                  matchedToken.kind = 32;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x20000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 115: 
               if ((jjoldActive_00 & 0x10000000000000L) != 0L)
               {
                  matchedToken.kind = 52;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 114: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x2000000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 110: 
               if ((jjoldActive_00 & 0x100000000000L) != 0L)
               {
                  matchedToken.kind = 44;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x200040000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 109: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x80000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 108: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x4080000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 105: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x220000000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 104: 
               if ((jjoldActive_00 & 0x1000000000000L) != 0L)
               {
                  matchedToken.kind = 48;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 103: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x10000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 102: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x800000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 101: 
               if ((jjoldActive_00 & 0x200000L) != 0L)
               {
                  matchedToken.kind = 21;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               else if ((jjoldActive_00 & 0x2000000000L) != 0L)
               {
                  matchedToken.kind = 37;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            default : 
               break StartNfaLoop;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(5, jjnewActive_00, 0L);
            curPos = 6;
            throw e;
         }
         jjMoveStringLiteralDfa6_0();
         return;
      }
      jjStartNfa_0(4, jjoldActive_00, 0L);
      curPos = 6;
      return;
    }
    jjmatchedPos = 5;
    curPos = 6;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 6;
       jjcurKind = 2147483647;
    }
    curPos = 7;
    return;
   }
}
static private final void jjMoveStringLiteralDfa6_0() throws java.io.IOException
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
               if (((jjoldActive_00 = jjnewActive_00 & 0x200000200L)) == 0L)
                  break StartNfaLoop;
               break;
            case 97: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x800000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 121: 
               if ((jjnewActive_00 & 0x4000000L) != 0L)
               {
                  matchedToken.kind = 26;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 117: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x40000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 116: 
               if ((jjnewActive_00 & 0x80000L) != 0L)
               {
                  matchedToken.kind = 19;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x40000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 115: 
               if ((jjnewActive_00 & 0x800000L) != 0L)
               {
                  matchedToken.kind = 23;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 111: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x2000000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 110: 
               if ((jjnewActive_00 & 0x400L) != 0L)
               {
                  matchedToken.kind = 10;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 108: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x200000000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 101: 
               if ((jjnewActive_00 & 0x10000000000L) != 0L)
               {
                  matchedToken.kind = 40;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               else if ((jjnewActive_00 & 0x20000000000L) != 0L)
               {
                  matchedToken.kind = 41;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x20000080000000L)) == 0L)
                  break StartNfaLoop;
               break;
            default : 
               break StartNfaLoop;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(6, jjoldActive_00, 0L);
            curPos = 7;
            throw e;
         }
         jjMoveStringLiteralDfa7_0();
         return;
      }
      jjStartNfa_0(5, jjnewActive_00, 0L);
      curPos = 7;
      return;
    }
    jjmatchedPos = 6;
    curPos = 7;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 7;
       jjcurKind = 2147483647;
    }
    curPos = 8;
    return;
   }
}
static private final void jjMoveStringLiteralDfa7_0() throws java.io.IOException
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
               if (((jjnewActive_00 = jjoldActive_00 & 0x800000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 116: 
               if ((jjoldActive_00 & 0x200L) != 0L)
               {
                  matchedToken.kind = 9;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 110: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x22000080000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 101: 
               if ((jjoldActive_00 & 0x40000L) != 0L)
               {
                  matchedToken.kind = 18;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               else if ((jjoldActive_00 & 0x200000000000000L) != 0L)
               {
                  matchedToken.kind = 57;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjnewActive_00 = jjoldActive_00 & 0x40200000000L)) == 0L)
                  break StartNfaLoop;
               break;
            default : 
               break StartNfaLoop;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(7, jjnewActive_00, 0L);
            curPos = 8;
            throw e;
         }
         jjMoveStringLiteralDfa8_0();
         return;
      }
      jjStartNfa_0(6, jjoldActive_00, 0L);
      curPos = 8;
      return;
    }
    jjmatchedPos = 7;
    curPos = 8;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 8;
       jjcurKind = 2147483647;
    }
    curPos = 9;
    return;
   }
}
static private final void jjMoveStringLiteralDfa8_0() throws java.io.IOException
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
            case 100: 
               if ((jjnewActive_00 & 0x40000000000L) != 0L)
               {
                  matchedToken.kind = 42;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 116: 
               if ((jjnewActive_00 & 0x20000000000000L) != 0L)
               {
                  matchedToken.kind = 53;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               if (((jjoldActive_00 = jjnewActive_00 & 0x80000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 111: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x200000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 105: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x2000000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 101: 
               if ((jjnewActive_00 & 0x800000000L) != 0L)
               {
                  matchedToken.kind = 35;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            default : 
               break StartNfaLoop;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(8, jjoldActive_00, 0L);
            curPos = 9;
            throw e;
         }
         jjMoveStringLiteralDfa9_0();
         return;
      }
      jjStartNfa_0(7, jjnewActive_00, 0L);
      curPos = 9;
      return;
    }
    jjmatchedPos = 8;
    curPos = 9;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 9;
       jjcurKind = 2147483647;
    }
    curPos = 10;
    return;
   }
}
static private final void jjMoveStringLiteralDfa9_0() throws java.io.IOException
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
            case 122: 
               if (((jjnewActive_00 = jjoldActive_00 & 0x2000000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            case 115: 
               if ((jjoldActive_00 & 0x80000000L) != 0L)
               {
                  matchedToken.kind = 31;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            case 102: 
               if ((jjoldActive_00 & 0x200000000L) != 0L)
               {
                  matchedToken.kind = 33;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            default : 
               break StartNfaLoop;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(9, jjnewActive_00, 0L);
            curPos = 10;
            throw e;
         }
         jjMoveStringLiteralDfa10_0();
         return;
      }
      jjStartNfa_0(8, jjoldActive_00, 0L);
      curPos = 10;
      return;
    }
    jjmatchedPos = 9;
    curPos = 10;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 10;
       jjcurKind = 2147483647;
    }
    curPos = 11;
    return;
   }
}
static private final void jjMoveStringLiteralDfa10_0() throws java.io.IOException
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
            case 101: 
               if (((jjoldActive_00 = jjnewActive_00 & 0x2000000000000L)) == 0L)
                  break StartNfaLoop;
               break;
            default : 
               break StartNfaLoop;
         }
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) {
            StopStringLiteralDfa_0(10, jjoldActive_00, 0L);
            curPos = 11;
            throw e;
         }
         jjMoveStringLiteralDfa11_0();
         return;
      }
      jjStartNfa_0(9, jjnewActive_00, 0L);
      curPos = 11;
      return;
    }
   }
}
static private final void jjMoveStringLiteralDfa11_0() throws java.io.IOException
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
            case 100: 
               if ((jjoldActive_00 & 0x2000000000000L) != 0L)
               {
                  matchedToken.kind = 49;
                  jjstartStateSet = jjstates17;
                  break MainLoop;
               }
               break StartNfaLoop;
            default : 
               break StartNfaLoop;
         }
      }
      jjStartNfa_0(10, jjoldActive_00, 0L);
      curPos = 12;
      return;
    }
    jjmatchedPos = 11;
    curPos = 12;
    curChar = input_stream.readChar();
    jjnewStateCnt = jjstartStateSet.length;
    jjMoveNfa_0(jjstartStateSet, jjstateSet2);
    if (jjcurKind != 2147483647)
    {
       matchedToken.kind = jjcurKind;
       jjmatchedPos = 12;
       jjcurKind = 2147483647;
    }
    curPos = 13;
    return;
   }
}
static final int[] jjstates0 = { 1, 2, };
static final int[] jjstates1 = { 7, 8, };
static final int[] jjstates2 = { 7, };
static final int[] jjstates3 = { 6, 7, };
static final int[] jjstates4 = { 4, 5, 8, };
static final int[] jjstates5 = { 4, };
static final int[] jjstates6 = { 11, };
static final int[] jjstates7 = { 15, 11, };
static final int[] jjstates8 = { 15, };
static final int[] jjstates9 = { 17, };
static final int[] jjstates10 = { 13, 14, 16, };
static final int[] jjstates11 = { 10, 12, };
static final int[] jjstates12 = { 19, 20, 22, };
static final int[] jjstates13 = { 19, 20, 24, 22, };
static final int[] jjstates14 = { 24, };
static final int[] jjstates15 = { 26, };
static final int[] jjstates16 = { 21, 23, 25, };
static final int[] jjstates17 = { 28, };
static final int[] jjstates18 = { 35, 8, };
static final int[] jjstates19 = { 35, };
static final int[] jjstates20 = { 34, 35, };
static final int[] jjstates21 = { 32, 33, 8, };
static final int[] jjstates22 = { 30, 31, };
static final int[] jjstates23 = { 39, 8, };
static final int[] jjstates24 = { 39, };
static final int[] jjstates25 = { 38, 39, };
static final int[] jjstates26 = { 36, 37, };
static final int[] jjstates27 = { 43, 8, };
static final int[] jjstates28 = { 43, };
static final int[] jjstates29 = { 42, 43, };
static final int[] jjstates30 = { 40, 41, 8, };
static final int[] jjstates31 = { 30, 31, 36, 37, 40, 41, 8, };
static final int[] jjstates32 = { 46, 2, };
static final int[] jjstates33 = { 46, };
static final int[] jjstates34 = { 47, 2, };
static final int[] jjstates35 = { 45, 47, 2, };
static final int[] jjstates36 = { 52, };
static final int[] jjstates37 = { 50, 51, 53, };
static final int[] jjstates38 = { 58, 56, };
static final int[] jjstates39 = { 56, 57, 59, };
static final int[] jjstates40 = { 55, 56, };
static final int[] jjstates41 = { 54, };
static final int[] jjstates42 = { 65, 63, };
static final int[] jjstates43 = { 63, 64, 66, };
static final int[] jjstates44 = { 62, 63, };
static final int[] jjstates45 = { 49, 60, 61, };
static final int[] jjallInitStates_0 = { 0, 3, 9, 18, 27, 29, 44, 48, };
static final int[] jjstates46 = { 18, };
static final int[] jjstates47 = { 27, };
static final int[] jjstates48 = { 9, };
static final int[] jjstates49 = { 3, };
static final int[] jjstates50 = { 48, };
static final int[] jjstates51 = { 29, 44, };
static final int[] jjstates52 = { 0, 29, };
static final int[][] jjinitStates_0 = {
jjallInitStates_0, null, null, null, null, null, 
null, null, null, null, null, 
null, null, null, null, null, 
null, null, null, null, null, 
null, null, null, null, null, 
null, null, null, null, null, 
null, null, null, jjstates46, null, 
jjstates47, null, null, jjstates48, null, 
null, null, null, null, null, 
jjstates49, jjstates50, jjstates51, jjstates52, jjstates52, 
jjstates52, jjstates52, jjstates52, jjstates52, jjstates52, 
jjstates52, jjstates52, null, null, null, 
null, null, null, null, jjstates47, 
jjstates47, jjstates47, jjstates47, jjstates47, jjstates47, 
jjstates47, jjstates47, jjstates47, jjstates47, jjstates47, 
jjstates47, jjstates47, jjstates47, jjstates47, jjstates47, 
jjstates47, jjstates47, jjstates47, jjstates47, jjstates47, 
jjstates47, jjstates47, jjstates47, jjstates47, jjstates47, 
null, null, null, null, jjstates47, 
null, jjstates47, jjstates47, jjstates47, jjstates47, 
jjstates47, jjstates47, jjstates47, jjstates47, jjstates47, 
jjstates47, jjstates47, jjstates47, jjstates47, jjstates47, 
jjstates47, jjstates47, jjstates47, jjstates47, jjstates47, 
jjstates47, jjstates47, jjstates47, jjstates47, jjstates47, 
jjstates47, jjstates47, null, null, null, 
null, null, };

public static final String[] jjstrLiteralImages = {
null, null, null, null, null, null, null, null, null, 
"\141\142\163\164\162\141\143\164", "\142\157\157\154\145\141\156", "\142\162\145\141\153", "\142\171\164\145", 
"\143\141\163\145", "\143\141\164\143\150", "\143\150\141\162", "\143\154\141\163\163", 
"\143\157\156\163\164", "\143\157\156\164\151\156\165\145", "\144\145\146\141\165\154\164", 
"\144\157", "\144\157\165\142\154\145", "\145\154\163\145", 
"\145\170\164\145\156\144\163", "\146\141\154\163\145", "\146\151\156\141\154", 
"\146\151\156\141\154\154\171", "\146\154\157\141\164", "\146\157\162", "\147\157\164\157", "\151\146", 
"\151\155\160\154\145\155\145\156\164\163", "\151\155\160\157\162\164", "\151\156\163\164\141\156\143\145\157\146", 
"\151\156\164", "\151\156\164\145\162\146\141\143\145", "\154\157\156\147", 
"\156\141\164\151\166\145", "\156\145\167", "\156\165\154\154", "\160\141\143\153\141\147\145", 
"\160\162\151\166\141\164\145", "\160\162\157\164\145\143\164\145\144", "\160\165\142\154\151\143", 
"\162\145\164\165\162\156", "\163\150\157\162\164", "\163\164\141\164\151\143", "\163\165\160\145\162", 
"\163\167\151\164\143\150", "\163\171\156\143\150\162\157\156\151\172\145\144", "\164\150\151\163", 
"\164\150\162\157\167", "\164\150\162\157\167\163", "\164\162\141\156\163\151\145\156\164", 
"\164\162\165\145", "\164\162\171", "\166\157\151\144", "\166\157\154\141\164\151\154\145", 
"\167\150\151\154\145", null, null, null, null, null, null, null, null, null, null, null, "\50", 
"\51", "\173", "\175", "\133", "\135", "\73", "\54", "\56", "\75", "\76", "\74", 
"\41", "\176", "\77", "\72", "\75\75", "\74\75", "\76\75", "\41\75", "\174\174", 
"\46\46", "\53\53", "\55\55", "\53", "\55", "\52", "\57", "\46", "\174", "\136", "\45", 
"\74\74", "\76\76", "\76\76\76", "\53\75", "\55\75", "\52\75", "\57\75", "\46\75", 
"\174\75", "\136\75", "\45\75", "\74\74\75", "\76\76\75", "\76\76\76\75", };
public static final String[] lexStateNames = {
   "DEFAULT", 
};
static final long[] jjtoToken = 
{
0x8ffffffffffffe00L,
0xfffffffffffceL,
};
static final long[] jjtoSpecial = 
{
0x1feL,
0x0L,
};
static private ASCII_UCodeESC_CharStream input_stream;
static private int[] jjrounds = new int[67];
static private int[] jjstateSet1 = new int[67];
static private int[] jjstateSet2 = new int[67];
static private int[] jjnextStates;
static int curPos;
static protected char curChar;
static int jjcurKind = 2147483647;
static FormPreprocessorTokenManager jjme;
public FormPreprocessorTokenManager(ASCII_UCodeESC_CharStream stream)
{
if (input_stream != null)
{
   System.err.println("ERROR: Second call to constructor of static lexer.  You must");
   System.err.println("       either use ReInit() or set the JavaCC option STATIC to false");
   System.err.println("       during lexer generation.");
   throw new Error();
}
input_stream = stream;
jjme = this;
}
public FormPreprocessorTokenManager(ASCII_UCodeESC_CharStream stream, int lexState)
{
if (input_stream != null)
{
   System.err.println("ERROR: Second call to constructor of static lexer.  You must");
   System.err.println("       either use ReInit() or set the JavaCC option STATIC to false");
   System.err.println("       during lexer generation.");
   throw new Error();
}
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
static public void ReInit(ASCII_UCodeESC_CharStream stream)
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
static public void ReInit(ASCII_UCodeESC_CharStream stream, int lexState)
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
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      System.out.println("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.");
   else
      curLexState = lexState;
}


static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static private boolean jjEOFSeen = false;
static int[] jjtmpStates = jjstateSet1;
static int jjmatchedPos;
static Token matchedToken;
static Token jjspecialToken;
static private int[] jjstartStateSet;

static public Token getNextToken() throws ParseError
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

protected int error_line, error_column;
protected String error_after;
protected void LexicalError()
{
   System.err.println("Lexical error at line " +
        error_line + ", column " +
        error_column + ".  Encountered: " +
        (jjEOFSeen ? "<EOF>" : ("\"" + jjadd_escapes(String.valueOf(curChar)) + "\"") + " (" + (int)curChar + "), ") +
        "after : \"" + jjme.error_after + "\"");
}
}
