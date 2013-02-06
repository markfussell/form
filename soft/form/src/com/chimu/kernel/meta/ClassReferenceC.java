/*======================================================================
**
**  File: chimu/kernel/meta/ClassReferenceC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.meta;

import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import java.lang.reflect.*;
import java.io.Serializable;

public class ClassReferenceC implements ClassReference, TypeConstants, Serializable {
    public ClassReferenceC(Class aC) {
        this.className = aC.getName();
        if (aC.isPrimitive()) {
            primitiveType = MetaPack.typeConstantForPrimitiveCName(className);
        };
    }

    public ClassReferenceC(String className) {
        this.className = className;
    }

    public String toString() {
        return "{->"+className+"}";
    }

    public String className() {
        return className;
    }

    public Class target() {
        if (isPrimitive()) {
            return primitiveTarget();
        };

        try {
            return Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException("Could not find class "+className);
        }
    }

    protected Class primitiveTarget() {
        Class result = MetaPack.primitiveCForConstant(primitiveType);
        if (result != null) return result;

        throw new RuntimeException("Could not find primitive class "+className);
    }

    public boolean hasTarget() {
        return (target() != null);
    }

    public boolean isPrimitive() {
        return primitiveType >= 0;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String  className;
    protected int     primitiveType = -1;

/*
    primitiveType = NOT_PRIMITIVE;

    static protected nameToPrimitiveC;


    NOT_PRIMITIVE  = 0;
    PRIMITIVE_INT  = 1;
    PRIMITIVE_LONG = 2;
*/
}
