/*======================================================================
**
**  File: chimu/form/database/product/mssql/TableC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.mssql;


import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import com.chimu.form.query.*;
import com.chimu.form.mapping.*;

import java.sql.*;

import java.util.Enumeration;

import com.chimu.form.database.*;

/*subsystem*/ public class TableC extends TableAbsC{
    /*spackage*/ public TableC(String name, String fullName, Connection connection) {
        super(name,fullName,connection);
    }

    /*spackage*/ public TableC(String name, String fullName, DatabaseConnection dbConnection) {
        super(name,fullName,dbConnection);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************


    protected int convertSqlDataType(int value) {
        if (value == 11) return 93;  //SQLServerHack ?? Appears to be ODBC Hack
        if (value == 3) return 2;  //MLF970610:Decimal SQLServerHack
        return value;
    }

    //**********************************************************
    //********************** Changing **************************
    //**********************************************************

    //**************************************
    //(P)*********** Locking ***************
    //**************************************

    /*subsystem*/ public void lockTable() {
        try {
            Statement stmt = connection.createStatement();
            try {
                stmt.execute(newLockTableQString());
            } finally {
                stmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not lock table "+name,e);
        };
    }

        //Sybase
    protected String newLockTableQString() {
        return "SELECT count(*) FROM "+qualifiedName()+" (TABLOCKX UPDLOCK HOLDLOCK)";
    }

    /*subsystem*/ public void unlockTable() {
        try {
            connection.commit();
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not commit the transaction ",e);
        };
    }

    //**************************************
    //(P)********** InsertRow **************
    //**************************************


    /*subsystem*/ public Object insertRow_getIdentity(Row row)throws SQLException {
        Object result = null;
        PreparedStatement pstmt = null;

        pstmt = newInsertRowGetIdentityStatementFor(row);

        setPstmt_usingRow_withoutUnsetValues(pstmt,row);
        //if (i == 0) {
        //    throw new MappingException("Tried to insert an empty row into table "+name);
        //};

        pstmt.execute();

        ResultSet resultSet;
        // boolean lookingForResultSet = false;
        while(true)  {  //This will set result to the last result set returned
            resultSet = pstmt.getResultSet();
            if (resultSet != null) {
                if (resultSet.next()) {
                    result = resultSet.getObject(0+1); //, primaryKeyColumn.sqlDataType());
                };
                resultSet.close();
                break;

                //lookingForResultSet =
                //pstmt.getMoreResults();
                //continue;
            }

            int rowCount = pstmt.getUpdateCount();
            if (rowCount >= 0) {    //  this is an update count, ignore
                pstmt.getMoreResults();
                continue;
            }

            break;      // there are no more results
        }
        if (result == null) throw DatabasePackSi.newExecutionException("Unexpected lack of results for identity insert");

        result = new Integer(((Number) result).intValue());
if (ts != null) ts.println("Retrieved new identity:"+result+" from "+name);
        return result;
    }

    protected PreparedStatement newInsertRowGetIdentityStatementFor(Row row)
            throws SQLException {
        String sqlString = newInsertRowGetIdentityQStringFor(row);

        Connection connection = this.myConnection();
        return connection.prepareStatement(sqlString);
    }

    protected String newInsertRowGetIdentityQStringFor(Row row) {
        String sqlString;
        StringBuffer sqlValuesStringB = new StringBuffer();
        StringBuffer selectStringB = new StringBuffer();

        int size = row.size();

        int index = 0;
        for (int i=0; i < size; i++) {
            Object rowValue = row.atIndex(i);
            BasicColumnPi column = (BasicColumnPi) row.basicColumnAtIndex(i);
            if (column.hasColumnValueFor(rowValue)) {
                String basicColumnName = column.name();
                if (index > 0) {
                    selectStringB.append(",");
                    sqlValuesStringB.append(",");
                };
                selectStringB.append(basicColumnName);
                sqlValuesStringB.append("?");
                index++;
            };
        };

//dbConnection.inertInto_columns_values_getIdentity(tableName,columnCollection,valueCollection)
        if (index > 0) {
            sqlString =  "INSERT INTO "+qualifiedName()+" "
                        +"("+selectStringB.toString()+") "
                        +"VALUES ("+sqlValuesStringB.toString()+")";
        } else {
            sqlString =  "INSERT INTO "+qualifiedName()+" DEFAULT VALUES";
        };

        sqlString += "\n";
        sqlString += "SELECT @@IDENTITY";       // MLF: HACK until delegate to the translator

        return sqlString;
    }

}


