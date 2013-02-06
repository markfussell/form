/*======================================================================
**
**  File: chimu/form/database/SimpleColumnAbsC.java
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

abstract class SimpleColumnAbsC extends ColumnAbsC
                       implements BasicColumnPi {
                        

    SimpleColumnAbsC(Table table, String resultColName, int javaDataType) {
        this.table = table;
        this.resultColName = resultColName;
        this.javaDataType = javaDataType;
    }

    public String toString() {
        return "<SimpleColumn "+resultColName+" {"+javaDataType+","+sqlDataType+","+isNullable+"}>";
    }

    public Table  table() {
        return table;
    }

    public String name() {
        return resultColName();
    }

    public String resultColName() {
        return resultColName;
    }

    /*package*/ public boolean isCompound() {return false;}

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ public void setSqlDataType(int sqlDataType) {
        this.sqlDataType = sqlDataType;
    }

    /*package*/ public void setJavaDataType(int javaDataType) {
        this.javaDataType = javaDataType;
    }

    /*package*/ public void setIsNullable(boolean isNullable) {
        this.isNullable = isNullable;
    }

    /*package*/ public void setMaximumLength(int maximumLength) {
        this.maximumLength = maximumLength;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ public void doneSetup() {

    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public boolean hasSingleSubColumn() {return false;}
    public Column  firstSubColumn()     {return null;}

    public int numberOfSubColumns()     {return 0;}
    public String firstSubColumnName()  {return null;}
    public String nameForSubColumnAtIndex(int i) {
        return null;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public int sqlDataType() {
        return sqlDataType;
    }
    public int javaDataType() {return javaDataType;}
    public boolean isNullable() {return isNullable;}
    public int maximumLength() {return maximumLength;}

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected Object dbValueForRow(RowPi row) {
        return row.dbValueForBasicColumnNamed(resultColName);
    }

    protected void setDbValueForRow_to(RowPi row, Object value) {
        //throw new ShouldNotImplementException("Expression columns ("+name+") can not be set");
        row.setDbValueForBasicColumnNamed_to(resultColName, value);
    }

    /*package*/ public Object valueForRow(RowPi row) {
        return dbValueForRow(row);
        /*
        if (transformFromDb == null) {
        } else {
            return transformFromDb.valueWith(dbValueForRow(row));
        }
        */
    }

    /*package*/ public void setValueForRow_to(RowPi row, Object value) {
        setDbValueForRow_to(row,value);
        //throw new ShouldNotImplementException("Expression columns ("+name+") can not be set");
    }

    //**********************************************************
    //(P)********************** Pstms **************************
    //**********************************************************

    /*package*/ public int setPstmt_itemStart_to(PreparedStatement pstmt, int index, Object value)
            throws SQLException {
        ConverterLib.setIntoPreparedStatement_atIndex_sqlType_to_javaType(
            pstmt,
            index, sqlDataType,
            value, javaDataType);
        // setPstmt_item_to_ofType(pstmt,item,value,sqlDataType);
        return index+1;
    }

    /*package*/ public void setPstmt_item_to(PreparedStatement pstmt, int index, Object value)
            throws SQLException {
        ConverterLib.setIntoPreparedStatement_atIndex_sqlType_to_javaType(
            pstmt,
            index, sqlDataType,
            value, javaDataType);
    }

    protected void setPstmt_item_to_ofType(PreparedStatement pstmt, int item, Object value, int sqlType)
            throws SQLException {
        if (value == null) {
            pstmt.setNull(item+1,sqlType);
        } else {
            try {
                pstmt.setObject(item+1,value,sqlType);
            } catch (Exception e) {
                throw DatabasePackSi.newJdbcException("Could not set parameter("+item+") value: "+value+" class: "+value.getClass()+" to sqlType: "+sqlType,e);
            };
        };
    }


    /*package*/ public Object getFromResultSet_atIndex(ResultSet rs, int index)
            throws SQLException {
        Object value = ConverterLib.getFromResultSet_atIndex_javaType(
            rs, index, javaDataType);
// dbRw.getFromResultSet_atIndex(rs,index);
// System.out.println("GET: ["+index+"] type:"+javaDataType+" => "+value);
        return value;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ public void addPartialColumnMapTo(Map columnMap) {
        columnMap.atKey_put(resultColName,new Integer(columnMap.size()));
    }


    //**********************************************************
    //(P)******************** SQL Building *********************
    //**********************************************************


    /*subsystem*/ public void addWithVarName_equals_varName_sqlTo(String varName, Column toColumn, String toVarName, StringBuffer sqlStringB) {
        if (((ColumnPi) toColumn).isCompound()) {
            throw new NotImplementedYetException("Unexpected match in columns Basic and Compound");
        };
        addWithVarName_expressionSqlTo(varName,sqlStringB);
        sqlStringB.append(" = ");
        ((ColumnSi) toColumn).addWithVarName_expressionSqlTo(toVarName,sqlStringB);
    }

    /*subsystem*/ public void addWithVarName_expressionSqlTo(String varName, StringBuffer sqlStringB) {
        //sqlStringB.append(varName+"."+name+" ");
            //Can't decide between name/expression
        sqlStringB.append(readExpressionForVar(varName)+" ");
    }

    /*subsystem*/ public void addWithVarName_selectSqlTo(String varName, StringBuffer sqlStringB) {
        sqlStringB.append(readExpressionForVar(varName));
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void putInsertSqlInto_forValue(SqlStatementBuilder sqlStmtB, Object value) {
            //Override the value with whatever is in the expression
        sqlStmtB.appendSqlString(this.name());

        String dataString = writeExpressionValueFor(value);
        if (dataString == null) {
            sqlStmtB.appendSqlString2("?");  //Denormalized from SqlBuilderLib.sqlStringForBoundValue
            sqlStmtB.addBoundValue_column(value,this);
        } else {
            sqlStmtB.appendSqlString2(dataString);
        }
    }

    public void putUpdateSqlInto_forValue(SqlStatementBuilder sqlStmtB, Object value) {
            //Override the value with whatever is in the expression
        sqlStmtB.appendSqlString(this.name());
        sqlStmtB.appendSqlString(" = ");

        String dataString = writeExpressionValueFor(value);
        if (dataString == null) {
            sqlStmtB.appendSqlString("?");  //Denormalized from SqlBuilderLib.sqlStringForBoundValue
            sqlStmtB.addBoundValue_column(value,this);
        } else {
            sqlStmtB.appendSqlString(dataString);
        }
    }

    protected abstract String  writeExpressionValueFor(Object value);
    protected abstract String  readExpressionForVar(String varName);
    

    //**********************************************************
    //**********************************************************
    //**********************************************************
    
    public void setJavaTypeFromSqlType() {
        if (javaDataType != TypeConstants.TYPE_Object) return;
        javaDataType = ConverterLib.defaultJavaTypeForSqlType(sqlDataType);
    }
    
    /*subsystem*/ public boolean hasDatabaseInfo() {
        return sqlDataType != Types.NULL;
    }

    public boolean needsParameterAddedFor(Object value) {
        return hasColumnValueFor(value);
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Table    table = null;
    protected String   resultColName  = null;
    
    //protected String   expression = null;
    //protected String   readExpressionString  = null;
    //protected String   writeExpressionString = null;
    
    protected int      sqlDataType = Types.NULL;
    protected int      javaDataType = TypeConstants.TYPE_Object;
    protected boolean  isNullable;
    protected int      maximumLength;

//protected Class    javaType = Object.class;
//protected DatabaseReaderWriter dbRw = ConverterLib.databaseReaderWriterFor(Object.class);

}