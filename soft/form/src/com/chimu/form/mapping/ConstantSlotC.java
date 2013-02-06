/*======================================================================
**
**  File: chimu/form/mapping/ConstantSlotC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;

/*package*/ class ConstantSlotC extends SetterSlotC implements ConstantSlot {

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ ConstantSlotC(
            String          name,
            ObjectMapper    mapper,
            Column          databaseColumn,
            Object          constantValue
            ) {
        super(name,mapper,null,null,databaseColumn,null,null);
        this.constantValue = constantValue;
    };

    //**********************************************************
    //(P)*********************** Setup *************************
    //**********************************************************


    //**********************************************************
    //(P)******************* Done Setup ************************
    //**********************************************************

        /**
         *@param columnValue(Object)
         *@return slotValue(Object)
         */
    /*package*/ public Object decode(Object columnValue) {
        if (decoder == null) return columnValue;
        return decoder.valueWith(columnValue);   //This should return the same as the constantValue
    }

        /**
         *@param slotValue(Object)
         *@return columnValue(Object)
         */
    /*package*/ public Object encode(Object unusedValue) {
        if (encoder == null) return constantValue;
        return encoder.valueWith(constantValue);
    }

    public Object value() {
        return constantValue;
    }

    //(P) ************* Instance Variables **************

    protected Object constantValue;
};