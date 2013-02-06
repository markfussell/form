/*======================================================================
**
**  File: chimu/form/database/typeConstants/SqlDataTypePack.java
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

import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.collections.*;


public class SqlDataTypePack {


    static public SqlDataType typeFromCode(int typeCode) {
        for (int i = 0; i < TYPE_SIZE; i++) {
            SqlDataType aDataType = (SqlDataType) types[i];
            if (aDataType.code() == typeCode) {

                return aDataType;
            };
        };
        throw new DevelopmentException("Could not find a SqlDataType for: "+typeCode);
    }


    static public SqlDataType typeFromKey(String key) {
        String aString = key.toUpperCase();
        SqlDataType dataType = (SqlDataType) keyToDataType.atKey(aString);
/*        
if (dataType == null) {
    System.err.println("No value found for key: "+key);
}
*/
        return dataType;
    }


    
    //**************************************
    //**************************************
    //**************************************


    static protected SqlDataType[] types = null;
    static protected Map keyToDataType = CollectionsPack.newMap();

    static final int TYPE_SIZE = 23;
    static int index = 0;
    static {
        types = new SqlDataType[TYPE_SIZE];
        
        //(Types.BIT,"BIT",dbProduct.typeBit(),hasLength)
        
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeCharC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeVarCharC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeLongVarCharC());

        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeDecimalC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeMoneyC());        
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeNumericC());

        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeBooleanC());        
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeBitC());
        
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeBinaryC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeLongVarBinaryC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeVarBinaryC());
        
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeBigIntC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeSmallIntC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeTinyIntC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeIntegerC());        
        
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeDoubleC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeFloatC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeRealC());        
        
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeDateC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeTimeC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeTimestampC());

        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeTextC());
        registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeImageC());

        //registerDataType(new com.chimu.form.database.typeConstants.SqlDataTypeNullC());
    }

    static void registerDataType(SqlDataType dataType) {
        types[index++]=dataType;
        keyToDataType.atKey_put(dataType.key(),dataType);
        //codeToDataType.atKey_put(new Integer(dataType.code()),dataType);

    }

    private SqlDataTypePack() {}

}