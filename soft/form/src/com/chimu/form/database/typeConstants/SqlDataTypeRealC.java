/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeRealC.java
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

public class SqlDataTypeRealC extends SqlDataTypeAbsC {
    public boolean parameterRequired() {
        return false;
    }

    public int integerRep() {
        return code();
    }
    public int code() {
        return Types.REAL;
    }
    public String key() {
        return "REAL";
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

    public String stringRep(DatabaseProduct dbProduct) {
            return dbProduct.typeFloat();

    }

    public String defaultStringRep(DatabaseProduct dbProduct) {
        return stringRep(dbProduct);
    }


    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************
    protected SqlDataTypeRealC() {}

}