/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeMoneyC.java
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

public class SqlDataTypeMoneyC extends SqlDataTypeAbsC {

    /*
    Returns a database specific string representation of the data type.
    */
    public boolean parameterRequired() {
        return false;
    }


    public String stringRep(DatabaseProduct dbProduct) {
        return dbProduct.typeMoney();

    }
    public int integerRep() {
        return Types.DECIMAL;
    }

    public int code() {
        return Types.DECIMAL;
    }
    public String key(){
        return "MONEY";
    }

    public String defaultStringRep(DatabaseProduct dbProduct) {
        return stringRep(dbProduct);
    }

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************
    protected SqlDataTypeMoneyC() {}


}