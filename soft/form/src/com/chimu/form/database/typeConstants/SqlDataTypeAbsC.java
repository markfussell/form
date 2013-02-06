/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.typeConstants;

import com.chimu.form.*;
import com.chimu.form.mapping.*;
import com.chimu.form.database.*;
import com.chimu.form.database.product.*;

import java.sql.Types;

/*  This class implements a default (basically an error condition), and subclass will override with its appropriate
implementation
*/
public abstract class SqlDataTypeAbsC implements SqlDataType{
    public boolean parameterRequired() {
        return true;
    }

    /*  All data type that requires parameters must implment and over ride
    ** these methods.
    **/

    public int defaultLength() {
        return 0;
    }

    public int defaultScale() {
        return 0;
    }

    public int defaultPrecision() {
        return 0;
    }

    //**************************************
    //**************************************
    //**************************************


    public String stringRep() {
        return key();
    }

    public int integerRep() {
        return code();
    }

    public int code() {
        return 0;

    }

    public String key() {
        return "UNKNOWN";

    }

    public String stringRep(DatabaseProduct dbProduct, int length, int precision) {
        System.err.println("dbProduct, length, precision: This method does not apply to the current datatype");
        System.err.println("dbProduct " + dbProduct.name() + " name: " + this.key());
        
        
        return null;
    }

    public String stringRep(DatabaseProduct dbProduct, int length) {
        System.err.println("dbProduct " + dbProduct.name() + " name: " + this.key());
        System.err.println("dbProduct, length: This method does not apply to the current datatype");

        return null;
    }

    public String stringRep(DatabaseProduct dbProduct) {
        System.err.println("dbProduct " + dbProduct.name() + " name: " + this.key());
        System.err.println("dbProduct: This method does not apply to the current datatype");
        return null;
    }

    public String defaultStringRep(DatabaseProduct dbProduct) {
        System.err.println("dbProduct " + dbProduct.name() + " name: " + this.key());
        System.err.println("defaultStringRep not implemented in specific subclass yet");
        return null;
    }

}