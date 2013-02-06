/*======================================================================
**
**  File: chimu/form/mapping/SetterSlotC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;

/*package*/ class SetterSlotC extends SlotC implements SetterSlotPi {

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ SetterSlotC(
            String          name,
            ObjectMapper    mapper,
            Function1Arg    getter,
            Procedure2Arg   setter,
            Column          databaseColumn,
            Function1Arg    decoder,
            Function1Arg    encoder
            ) {
        super(name,mapper,true,true,databaseColumn,decoder,encoder);
        this.setter         = setter;
        this.getter         = getter;
    };

    //**********************************************************
    //(P)*********************** Setup *************************
    //**********************************************************

         /**
         * Setup the getter and setter for the slot (this would actually cause a slot change)
         * but the client shouldn't have to know that.
         */
    public void setupGetterAndSetter(Function1Arg getter, Procedure2Arg setter) {
        this.getter = getter;
        this.setter = setter;
    }

    public void setupGetter(Function1Arg getter) {
        this.getter = getter;
    }

    public void setupSetter(Procedure2Arg setter) {
        this.setter = setter;
    }

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************


    public boolean hasSetter() {
        return setter != null;
    }

    public boolean hasGetter() {
        return getter != null;
    }


    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public void setObject_usingRow (Object object, Row row) {
        if (setter == null) return;

        Object value = this.extractValue(row);
        value = decode(value);
        setter.executeWith_with (object,value);
    }

    public void setRow_usingObject (Row row, Object object) {
        if (getter == null) return;

        Object value = getter.valueWith(object);
        value = encode(value);
        this.updateRow_withValue(row,value);
    }

   // --------------------------------

   public void setObject_usingSlotValue (Object object, Object value) {
        if (setter == null) return;

        setter.executeWith_with (object,value);
    }

    public Object newSlotValueFromObject (Object object) {
        if (getter == null) {return null;};

        return getter.valueWith(object);
    }


    // --------------------------------

    public Object newColumnValueFromObject (Object object) {
        if (getter == null) {return null;};

        Object value = getter.valueWith(object);
        return encode(value);
    }

    public void setObject_usingColumnValue (Object object, Object value) {
        if (setter == null) return;

        value = decode(value);
        setter.executeWith_with (object,value);
    }

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Function1Arg           getter;
    protected Procedure2Arg          setter;
};