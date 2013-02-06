/*======================================================================
**
**  File: chimu/kernel/streams/GeneratorAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.streams;

/**
GeneratorAbsC provides implementations for the compatibility protocols
in Generator.
**/
public abstract class GeneratorAbsC
                    implements Generator {

    //**********************************************************
    //(P)****************** Compatibility **********************
    //**********************************************************

    //**************************************
    //************** Functor ***************
    //**************************************
        /**
         * Value is synonymous with nextValue
         */
    public final Object value() {
        return nextValue();
    }

    //**************************************
    //************ Enumeration *************
    //**************************************

    public final boolean hasMoreElements() {
        return true;
    }

    public Object nextElement() {
        return nextValue();
    }

}