/*======================================================================
**
**  File: chimu/kernel/meta/TypeConstants.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.meta;

import java.lang.reflect.*;

public interface TypeConstants {
    final int TYPE_UNKNOWN     = 0;

    final int TYPE_boolean     = 2;
    final int TYPE_byte        = 3;
    final int TYPE_char        = 4;
    final int TYPE_short       = 5;
    final int TYPE_int         = 6;
    final int TYPE_long        = 7;
    final int TYPE_float       = 8;
    final int TYPE_double      = 9;

    final int TYPE_java_lang_Object      = 100;
    final int TYPE_java_lang_Void        = 101;
    final int TYPE_java_lang_C       = 102;

    /*
    final int TYPE_java_lang_Object      = 100;
    final int TYPE_java_lang_Object      = 100;
    final int TYPE_java_lang_Object      = 100;
    final int TYPE_java_lang_Object      = 100;
    final int TYPE_java_lang_Object      = 100;
    */

    final int TYPE_Object      = 100;
    final int TYPE_Void        = 101;
    final int TYPE_C       = 102;

    final int TYPE_Boolean     = 112;
    final int TYPE_Byte        = 113;
    final int TYPE_Character   = 114;
    final int TYPE_Short       = 115;
    final int TYPE_Integer     = 116;
    final int TYPE_Long        = 117;
    final int TYPE_Float       = 118;
    final int TYPE_Double      = 119;

    final int TYPE_String      = 130;

    final int TYPE_java_util_Date       = 201;

    final int TYPE_java_sql_Date        = 301;
    final int TYPE_java_sql_Time        = 302;
    final int TYPE_java_sql_Timestamp   = 303;

    final int TYPE_java_math_BigDecimal = 400;
}
