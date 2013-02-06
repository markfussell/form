/*======================================================================
**
**  File: chimu/form/database/ColumnAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.form.database.support.ConverterLib;

import java.sql.Types;
import java.sql.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.meta.*;

import com.chimu.form.database.support.SqlBuilderLib;

abstract class ColumnAbsC implements ColumnPi {

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void putInsertSqlInto_forValue(SqlStatementBuilder sqlStmtB, Object value) {
        sqlStmtB.appendSqlString(this.name());

        String dataString = SqlBuilderLib.sqlStringFor_javaType(value,javaDataType());
        if (dataString == null) {
            sqlStmtB.appendSqlString2("?");  //Denormalized from SqlBuilderLib.sqlStringForBoundValue
            sqlStmtB.addBoundValue_column(value,this);
        } else {
            sqlStmtB.appendSqlString2(dataString);
        }
    }

    public void putWhereParametersInto_forValue(SqlStatementBuilder sqlStmtB, Object value) {
        sqlStmtB.appendSqlString(this.name());
        if (value == null) {
            sqlStmtB.appendSqlString(" IS NULL ");//Denorm
            return;
        }
        sqlStmtB.appendSqlString(" = ");

        String dataString = SqlBuilderLib.sqlStringFor_javaType(value,javaDataType());
        if (dataString == null) {
            sqlStmtB.appendSqlString("?");  //Denormalized from SqlBuilderLib.sqlStringForBoundValue
            sqlStmtB.addBoundValue_column(value,this);
        } else {
            sqlStmtB.appendSqlString(dataString);
        }
    }


    public void putUpdateSqlInto_forValue(SqlStatementBuilder sqlStmtB, Object value) {
        sqlStmtB.appendSqlString(this.name());
        sqlStmtB.appendSqlString(" = ");

        String dataString = SqlBuilderLib.sqlStringFor_javaType(value,javaDataType());
        if (dataString == null) {
            sqlStmtB.appendSqlString("?");  //Denormalized from SqlBuilderLib.sqlStringForBoundValue
            sqlStmtB.addBoundValue_column(value,this);
        } else {
            sqlStmtB.appendSqlString(dataString);
        }
    }

    public boolean hasColumnValueFor(Object value) {
        return (value != DatabasePackSi.UNSET_COLUMN_VALUE);
    }

}