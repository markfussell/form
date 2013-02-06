/*======================================================================
**
**  File: chimu/form/database/ExpressionColumnC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.form.database.support.ConverterLib;

import com.chimu.form.database.support.*;

import java.sql.Types;
import java.sql.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.meta.*;

class ExpressionColumnC extends SimpleColumnAbsC
                       implements BasicColumnPi {
                        

    ExpressionColumnC(Table table, String resultColName, int javaDataType, String readExpressionString, String writeExpressionString) {
        super (table,resultColName,javaDataType);
        
        this.readExpressionString = readExpressionString;
        this.writeExpressionString = writeExpressionString;
    }

        //TODO: This should change to read/write
    public String expression() {
        return readExpressionString;
    }

    /*package*/ public void doneSetup() {
        super.doneSetup();
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ public void addWhereParametersTo_in(StringBuffer stringB, TableSi table) {
        // table.addFirstColumnName_to(name,stringB);
        stringB.append(readExpressionForVar(null)+"=?");
    }


    protected String  writeExpressionValueFor(Object value) {
        //if (writeExpressionString != null) {
        //} else if (writeExpressionFunctor != null) {
        //} else {
        //    return SqlBuilderLib.sqlStringFor_javaType(value,javaDataType());
        return writeExpressionString; 
        
        // return writeExpressionFunctor.valueWith(
        //         SqlBuilderLib.sqlStringFor_javaType(value,javaDataType())
        //     )
        //
        // return expression; 
        // return SqlBuilderLib.sqlStringFor_javaType(value,javaDataType());
    }
    
    protected String  readExpressionForVar(String varName) {
        return readExpressionString; 
        
        // return varName+"."+name
        // return writeExpressionFunctor.valueWith(
        //         varName+"."+name
        //     )
    }
    

    public boolean hasColumnValueFor(Object value) {
        if (!requiresValueForWrite) return true;
        return super.hasColumnValueFor(value);
    }

    public boolean needsParameterAddedFor(Object value) {
        if (!requiresValueForWrite) return false;
        return super.hasColumnValueFor(value);
    }


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String   readExpressionString  = null;
    protected String   writeExpressionString = null;
    
    protected boolean  requiresValueForWrite = false;
    
}