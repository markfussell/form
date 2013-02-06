/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypeBigIntC.java
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

public class SqlDataTypeBigIntC extends SqlDataTypeAbsC {

    /* 
    Returns a database specific string representation of the data type.
    */
    public boolean parameterRequired() {
        return false;
    }

    
    public String stringRep(DatabaseProduct dbProduct) {
        return dbProduct.typeBigInt();

    }
    public int integerRep() {
        return Types.BIGINT;
    }
    
    public int code() {
        return Types.BIGINT;   
    }
    public String key(){
        return "BIGINT";
    }
    
    public String defaultStringRep(DatabaseProduct dbProduct) {
        return stringRep(dbProduct);
    }
    
    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************
    protected SqlDataTypeBigIntC() {}    


}