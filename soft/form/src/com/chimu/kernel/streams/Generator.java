/*======================================================================
**
**  File: chimu/kernel/streams/Generator.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.streams;


import com.chimu.kernel.functors.*;

import java.util.Enumeration;

/**
A Generator produces objects either one at a time or in collections.

<P>Compatibility: #next and #nextElement are both compatibility
synonyms for #nextValue, #hasMoreElements is always true.
**/
public interface Generator extends Function0Arg, Enumeration {
        /**
         * Generate and return a new value for the generator
         */
    public Object nextValue();

        /**
         * Return the current value of the generator.
         */
    public Object currentValue();


//    public FixedIndexedCollection next_Values(int count);
//    public Object firstOfNext_Values(int count);

    //**********************************************************
    //(P)****************** Compatibility **********************
    //**********************************************************

    //**************************************
    //************** Functor ***************
    //**************************************
        /**
         * This is just to support using a Generator as a Function0Arg,
         * Generator clients should call "nextValue" (the synonym)
         * instead.
         */
    public Object value();

    //**************************************
    //************ Enumeration *************
    //**************************************
        /**
         * This will always return true
         */
    public boolean hasMoreElements();
    public Object nextElement();
}



