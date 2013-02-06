/*======================================================================
**
**  File: chimu/form/database/BasicColumnC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.form.database.support.ConverterLib;
import com.chimu.form.database.support.SqlBuilderLib;

import java.sql.Types;
import java.sql.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.meta.*;

class BasicColumnC extends SimpleColumnAbsC
                       implements BasicColumnPi {

    BasicColumnC(Table table, String name, int javaDataType) {
        super (table,name,javaDataType);
        this.tableColumnName = name;
    }

    public String toString() {
        return "<Column "+tableColumnName+" {"+javaDataType+","+sqlDataType+","+isNullable+"}>";
    }

    public String name() {
        return tableColumnName;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ public void addWhereParametersTo_in(StringBuffer stringB, TableSi table) {
        // table.addFirstColumnName_to(name,stringB);
        stringB.append(readExpressionForVar(null)+"=?");
    }


    protected String  writeExpressionValueFor(Object value) {
        return SqlBuilderLib.sqlStringFor_javaType(value,javaDataType());
    }
    
    protected String  readExpressionForVar(String varName) {
        if (varName == null) return tableColumnName;
        return varName+"."+tableColumnName;
    }
    

    //**********************************************************
    //(P)******************** SQL Building *********************
    //**********************************************************


    /*subsystem*/ public void addWithVarName_expressionSqlTo(String varName, StringBuffer sqlStringB) {
        sqlStringB.append(varName+"."+tableColumnName+" ");
    }

    /*subsystem*/ public void addWithVarName_selectSqlTo(String varName, StringBuffer sqlStringB) {
        sqlStringB.append(varName+"."+tableColumnName);
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String   tableColumnName = null;

}