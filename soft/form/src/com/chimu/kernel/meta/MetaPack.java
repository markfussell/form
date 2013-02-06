/*======================================================================
**
**  File: chimu/kernel/meta/MetaPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.meta;

import com.chimu.kernel.collections.*;



import java.lang.reflect.*;
/**
**/
public class MetaPack implements TypeConstants {

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static public MethodReference newMethodReference(Method aMethod) {
        return new MethodReferenceC(aMethod);
    }

    static public MethodReference newMethodReferenceInC_methodName(Class aC, String methodName) {
        return new MethodReferenceC(aC,methodName);
    }

    static public ClassReference newCReference(Class aC) {
        return new ClassReferenceC(aC);
    }

    static public ClassReference newCReference(String fullCName) {
        try {
            return new ClassReferenceC(fullCName);
        } catch (Exception e) {
            return null;
        }
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static public int typeConstantForC(Class aC) {
        Integer wrappedValue = (Integer) classToConstants.atKey(aC);
        if (wrappedValue != null) return wrappedValue.intValue();
        return -1;
    }

    static public int typeConstantForCName(String className) {
        Integer wrappedValue = (Integer) primitiveNamesToConstants.atKey(className);
        if (wrappedValue != null) return wrappedValue.intValue();
        try {
            Class aC = Class.forName(className);
            wrappedValue = (Integer) classToConstants.atKey(aC);
            if (wrappedValue != null) return wrappedValue.intValue();
        } catch (Exception e) {};
        return -1;
    }

    static public int typeConstantForPrimitiveCName(String className) {
        Integer wrappedValue = (Integer) primitiveNamesToConstants.atKey(className);
        if (wrappedValue != null) return wrappedValue.intValue();
        return -1;
    }

    static public Class primitiveCForConstant(int typeConstant) {
        switch (typeConstant) {
            case TYPE_boolean:  return Boolean.TYPE;
            case TYPE_byte:     return Byte.TYPE;
            case TYPE_char:     return Character.TYPE;
            case TYPE_short:    return Short.TYPE;
            case TYPE_int:      return Integer.TYPE;
            case TYPE_long:     return Long.TYPE;
            case TYPE_float:    return Float.TYPE;
            case TYPE_double:   return Double.TYPE;
            case TYPE_Void:     return Void.TYPE;
        }
        return null;
    }

    static protected Map primitiveNamesToConstants = CollectionsPack.newMap();
    static protected Map primitiveConstantsToNames = CollectionsPack.newMap();
    static protected Map classToConstants = CollectionsPack.newMap();

    static {
        primitiveNamesToConstants.atKey_put("boolean",  new Integer(TYPE_boolean));
        primitiveNamesToConstants.atKey_put("byte",     new Integer(TYPE_byte));
        primitiveNamesToConstants.atKey_put("char",     new Integer(TYPE_char));
        primitiveNamesToConstants.atKey_put("short",    new Integer(TYPE_short));
        primitiveNamesToConstants.atKey_put("int",      new Integer(TYPE_int));
        primitiveNamesToConstants.atKey_put("long",     new Integer(TYPE_long));
        primitiveNamesToConstants.atKey_put("float",    new Integer(TYPE_float));
        primitiveNamesToConstants.atKey_put("double",   new Integer(TYPE_double));
    }

    static {
        classToConstants.atKey_put(Boolean.TYPE,       new Integer(TYPE_boolean));
        classToConstants.atKey_put(Byte.TYPE,          new Integer(TYPE_byte));
        classToConstants.atKey_put(Character.TYPE,     new Integer(TYPE_char));
        classToConstants.atKey_put(Short.TYPE,         new Integer(TYPE_short));
        classToConstants.atKey_put(Integer.TYPE,       new Integer(TYPE_int));
        classToConstants.atKey_put(Long.TYPE,          new Integer(TYPE_long));
        classToConstants.atKey_put(Float.TYPE,         new Integer(TYPE_float));
        classToConstants.atKey_put(Double.TYPE,        new Integer(TYPE_double));

        classToConstants.atKey_put(Object.class,        new Integer(TYPE_Object));
        classToConstants.atKey_put(Void.class,          new Integer(TYPE_Void));
        classToConstants.atKey_put(Class.class,         new Integer(TYPE_C));


        classToConstants.atKey_put(Boolean.class,       new Integer(TYPE_Boolean));
        classToConstants.atKey_put(Byte.class,          new Integer(TYPE_Byte));
        classToConstants.atKey_put(Character.class,     new Integer(TYPE_Character));
        classToConstants.atKey_put(Short.class,         new Integer(TYPE_Short));
        classToConstants.atKey_put(Integer.class,       new Integer(TYPE_Integer));
        classToConstants.atKey_put(Long.class,          new Integer(TYPE_Long));
        classToConstants.atKey_put(Float.class,         new Integer(TYPE_Float));
        classToConstants.atKey_put(Double.class,        new Integer(TYPE_Double));

        classToConstants.atKey_put(String.class,                new Integer(TYPE_String));
        classToConstants.atKey_put(java.util.Date.class,        new Integer(TYPE_java_util_Date));

        classToConstants.atKey_put(java.sql.Date.class,         new Integer(TYPE_java_sql_Date));
        classToConstants.atKey_put(java.sql.Time.class,         new Integer(TYPE_java_sql_Time));
        classToConstants.atKey_put(java.sql.Timestamp.class,    new Integer(TYPE_java_sql_Timestamp));

        classToConstants.atKey_put(java.math.BigDecimal.class,  new Integer(TYPE_java_math_BigDecimal));
    }

/*
    static {
        primitiveConstantsToNames.atKey_put("boolean",  new Integer(TYPE_boolean));
        primitiveConstantsToNames.atKey_put("byte",     new Integer(TYPE_byte));
        primitiveConstantsToNames.atKey_put("char",     new Integer(TYPE_char));
        primitiveConstantsToNames.atKey_put("short",    new Integer(TYPE_short));
        primitiveConstantsToNames.atKey_put("int",      new Integer(TYPE_int));
        primitiveConstantsToNames.atKey_put("long",     new Integer(TYPE_long));
        primitiveConstantsToNames.atKey_put("float",    new Integer(TYPE_float));
        primitiveConstantsToNames.atKey_put("double",   new Integer(TYPE_double));
    }
*/

    //**********************************************************
    //**********************************************************
    //**********************************************************

    private MetaPack() {};

};
