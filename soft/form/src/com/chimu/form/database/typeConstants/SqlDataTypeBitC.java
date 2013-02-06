/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeBitC.java
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

public class SqlDataTypeBitC extends SqlDataTypeAbsC {
    public boolean parameterRequired() {
        return false;
    }

    public int integerRep() {
        return code();
    }
    
    public int code() {
        return Types.BIT;
    }
    public String key(){
        return "BIT";
    }
/*
    public String stringRep(DatabaseProduct dbProduct, int length) {
        String aString = dbProduct.typeBit();
        if (!(aString == null)) {
            if (length > 0) {
                return aString + "(" + length + ")";
            } else {
                return aString;
            };
        };
        return null;
    }
*/    
    
    public String stringRep(DatabaseProduct dbProduct) {
        String aString = dbProduct.typeBit();
        if (!(aString == null)) {
            return aString;
        }
        return null;
        
    }

    public String defaultStringRep(DatabaseProduct dbProduct) {
        return stringRep(dbProduct);
    }


    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************
    protected SqlDataTypeBitC() {}    



}