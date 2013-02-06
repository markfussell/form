/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeVarCharC.java
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

public class SqlDataTypeVarCharC extends SqlDataTypeAbsC {


    public int defaultLength() {
        return 70;
    }

    public int integerRep() {
        return code();
    }
    public int code(){
        return Types.VARCHAR;
    }
    public String key(){
        return "VARCHAR";
    }


    public String stringRep(DatabaseProduct dbProduct, int length) {
        String aString = dbProduct.typeVarChar();
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
    protected SqlDataTypeVarCharC() {}

}