/*======================================================================
**
**  File: chimu/form/mapping/MappingTransformer.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

/**
A MappingTransformer can encode and decode objects from their
database values.  This is an interface that combines the decoder
and encoder Functors.

<P>This is currently not used.
**/
public interface MappingTransformer {
    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

        /**
         *@param columnValue(Object)
         *@return slotValue(Object)
         */
    /*package*/ public Object decode(Object columnValue);

        /**
         *@param slotValue(Object)
         *@return columnValue(Object)
         */
    /*package*/ public Object encode(Object slotValue);
};
