/*======================================================================
**
**  File: chimu/kernel/meta/MethodReferenceC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.meta;

import com.chimu.kernel.exceptions.*;

import java.lang.reflect.*;
import java.lang.reflect.*;
import java.io.Serializable;

public class MethodReferenceC implements MethodReference, Serializable {
    public MethodReferenceC(Method aMethod) {
        methodName = aMethod.getName();
        declaringCReference = new ClassReferenceC(aMethod.getDeclaringClass());
        Class[] parameterClasses = aMethod.getParameterTypes();

        parameterClassesRefs = new ClassReference[parameterClasses.length];
        for (int i = 0; i< parameterClasses.length; i++) {
            parameterClassesRefs[i]=new ClassReferenceC(parameterClasses[i]);
        }
    }

    public MethodReferenceC(Class aC, String methodName) {
        this.methodName = methodName;
        declaringCReference = new ClassReferenceC(aC);

        parameterClassesRefs = new ClassReference[0];

        //NEED TO FIND ALL METHODS MATCHING METHOD NAME AND PICK ONE.
        //DO THIS AT RETRIEVAL TIME?
    }


    public String toString() {
        StringBuffer stringB = new StringBuffer();
        stringB.append("{->"+declaringCReference.toString()+"#"+methodName());
        stringB.append("(");
        for (int i = 0; i< parameterClassesRefs.length; i++) {
            if (i > 0) stringB.append(",");
            stringB.append(parameterClassesRefs[i].toString());
        }
        stringB.append(")");
        stringB.append("}");
        return stringB.toString();
    }

    public String methodName() {
        return methodName;
    }

    public Method target() {
        Class declaringC = declaringCReference.target();
        if (declaringC == null) return null;
        try {
            return declaringC.getDeclaredMethod(methodName,parameterClasses());
        } catch (Exception e) {
            throw new RuntimeException("Could not find method "+toString());
        }
    }

    public boolean hasTarget() {
        return (target() != null);
    }

    public Class declaringC() {
        return declaringCReference.target();
    }

    public ClassReference declaringCRef() {
        return declaringCReference;
    }

    public Class[] parameterClasses() {
        Class[] parameterClasses = new Class[parameterClassesRefs.length];
        for (int i = 0; i< parameterClassesRefs.length; i++) {
            parameterClasses[i]=parameterClassesRefs[i].target();
        }
        return parameterClasses;
    }

    public ClassReference[] parameterClassesRefs() {
        return parameterClassesRefs;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String methodName;
    protected ClassReference   declaringCReference      = null;
    protected ClassReference[] parameterClassesRefs = null;

    transient Method method;

}
