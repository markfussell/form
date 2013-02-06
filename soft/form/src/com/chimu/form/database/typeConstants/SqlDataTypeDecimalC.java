/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeDecimalC.java
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

public class SqlDataTypeDecimalC extends SqlDataTypeAbsC {


    public int defaultScale() {
        return 13;
    }

    public int defaultPrecision() {
        return 4;
    }
    
    //**************************************
    //**************************************
    //**************************************

    public int integerRep() {
        return code();
    }
    public int code(){
        return Types.DECIMAL;
    }
    public String key(){
        return "DECIMAL";
    }

    public String stringRep(DatabaseProduct dbProduct, int scale, int precision) {
        String aString = dbProduct.typeDecimal();
        if (aString != null) {
            if ((scale >0) && (precision > 0)) {
                return aString + "("+ scale + ", " + precision + ")";
            };
            return aString;
        };
        return stringRep(dbProduct, defaultScale());
    }

    public String stringRep(DatabaseProduct dbProduct, int length) {
        String aString = dbProduct.typeDecimal();
        if (aString != null) {
            if (length >0) {
                return aString + "("+ length + ", " + defaultPrecision() + " )";
            };
System.err.println("length should be >0.  using default");
            return aString + "(" + defaultScale() + ", " + defaultPrecision() + ")";
        };
        return null;
    }

    public String defaultStringRep(DatabaseProduct dbProduct) {
        return stringRep(dbProduct, defaultScale());
    }



    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************
    protected SqlDataTypeDecimalC() {}



}