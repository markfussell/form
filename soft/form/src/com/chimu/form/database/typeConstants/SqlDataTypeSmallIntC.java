/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeSmallIntC.java
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

public class SqlDataTypeSmallIntC extends SqlDataTypeAbsC {
    public boolean parameterRequired() {
        return false;
    }

    public int integerRep() {
        return code();
    }
    public int code() {
        return Types.SMALLINT;
    }
    public String key() {
        return "SMALLINT";
    }

    public String stringRep(DatabaseProduct dbProduct) {
        String aString = dbProduct.typeString();
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
    protected SqlDataTypeSmallIntC() {}


}