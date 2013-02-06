/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeNumericC.java
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

public class SqlDataTypeNumericC extends SqlDataTypeAbsC {


    public int defaultLength() {
        return 13;
    }

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
        return Types.NUMERIC;
    }

    public String key(){
        return "NUMERIC";
    }

    public String stringRep(DatabaseProduct dbProduct, int length, int precision) {
        String aString = dbProduct.typeNumeric();
        if (aString != null) {
            if ((length > 0) && (precision >0)) {
                return aString + "(" + length + ", " + precision + ")";
            } else {
                if (precision < 0) {
                    return aString + "(" + length + ")";
                };
            };
            return aString;
        };
        return null;
    }


    public String stringRep(DatabaseProduct dbProduct, int length) {
        String aString = dbProduct.typeNumeric();
        if (aString != null) {
            if (length > 0)  {
                return aString + "(" + length + ")";
            };
            return aString;
        };
        return null;
    }

    public String defaultStringRep(DatabaseProduct dbProduct) {
        return stringRep(dbProduct, defaultLength());
    }


    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************
    protected SqlDataTypeNumericC() {}

}