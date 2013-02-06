/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeDoubleC.java
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

public class SqlDataTypeDoubleC extends SqlDataTypeAbsC {

    public int defaultLength() {
        return 5;
    }
    //**************************************
    //**************************************
    //**************************************


    public int integerRep() {
        return code();
    }
    public int code(){
        return Types.DOUBLE;
    }
    public String key(){
        return "DOUBLE";
    }

    public String stringRep(DatabaseProduct dbProduct, int length) {
        String aString = dbProduct.typeDouble();
        if (aString != null) {
            if (length >0) {
                return aString + "(" + length + ")";
            };
            return aString;
        }
        return null;

    }

    public String defaultStringRep(DatabaseProduct dbProduct) {
        return stringRep(dbProduct, defaultLength());
    }


    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************
    protected SqlDataTypeDoubleC() {}

}