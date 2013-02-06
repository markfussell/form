/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeFloatC.java
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

public class SqlDataTypeFloatC extends SqlDataTypeAbsC {
    public boolean parameterRequired() {
        return false;
    }
    //**************************************
    //**************************************
    //**************************************

    public int integerRep() {
        return code();
    }
    public int code(){
        return Types.FLOAT;
    }
    public String key(){
        return "FLOAT";
    }

    public String stringRep(DatabaseProduct dbProduct, int length) {
        String aString = dbProduct.typeFloat();
        if (aString != null) {
            if (length > 0) {
                return aString + "(" + length + ")";
            };
System.err.println("length is less than 0.  Using default");
            return stringRep(dbProduct);
        };
        return null;
    }

    public String stringRep(DatabaseProduct dbProduct) {
        String aString = dbProduct.typeFloat();
        if (aString != null) {
            return aString;
        };
        return null;
    }

    public String defaultStringRep(DatabaseProduct dbProduct) {
        return stringRep(dbProduct);
    }

   //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************
    protected SqlDataTypeFloatC() {}

}