/*======================================================================
**
**  File: chimu/form/database/TableSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.kernel.collections.*;
import com.chimu.form.query.QueryDescription;
import com.chimu.form.query.SqlBuilder;
import java.sql.SQLException;

import java.sql.Connection;
/**
TableSi is the subsystem interface of a table.

<P>This interface is private to FORM and should not be used by FORM clients.</P>
**/
/*subsystem*/ public interface TableSi extends Table {
    /*subsystem*/ public void setupReusePrimaryKeyStatement();

    /*subsystem*/ public String name();
    /*subsystem*/ public String querySelectStringPrefixedWith(String queryVarName);

    /*subsystem*/ public void setColumnJavaTypes();

        /**
         */
    public Column newBasicColumn_type(String name, int javaDataType);


    /*subsystem*/ public Column columnAtIndex(int i);

    /*subsystem*/ public Column basicColumnAtIndex(int i);
    /*subsystem*/ public int sqlDataTypeForColumnAtIndex(int index);

    /*subsystem*/ public void setPrimaryKeyColumn(Column column);
    /*subsystem*/ public SqlBuilder newSqlBuilder();

    //**********************************************************
    //********************** Searching *************************
    //**********************************************************

    /*subsystem*/ public Array selectAll();
    /*subsystem*/ public Row findPrimaryKey(Object primaryKey);
    /*subsystem*/ public Row findAny();
    /*subsystem*/ public int countAll();

//    /*subsystem*/ public PreparedStatement newSelectWhereQString_Statement(String qstring);
//    /*subsystem*/ public Array selectWhereQuery(QueryDescription queryD);
//    /*subsystem*/ public Row findWhereQuery(QueryDescription queryD);

    //(P)************ Query ****************
        /**
         * Returns -1 to indicate error (no results)
         */
    /*subsystem*/ public int countWhereSqlString(String sqlString);
    /*subsystem*/ public Array selectWhereSqlString(String sqlString);
    /*subsystem*/ public Row findWhereSqlString(String sqlString);
    /*subsystem*/ public Array selectColumn_whereSqlString(Column column, String sqlString);

    //(P)******* SelectWhereQString ********
    //Suspicious
    /*subsystem*/ public Row findWhereSqlString_hasValue(String qstring, Object value)
            throws SQLException;
    /*subsystem*/ public Array /*of Rows*/ selectWhereSqlString_hasValue(String qstring, Object value)
            throws SQLException;
    /*subsystem*/ public Array /*of Rows*/ selectWhereSqlString_hasValues(String qstring, Array values)
            throws SQLException;

    //(P)************ Column ***************
    /*subsystem*/ public Array selectWhereColumn_equals(Column column, Object value);
    /*subsystem*/ public Row findWhereColumn_equals(Column column, Object value);


    //**********************************************************
    //********************** Changing **************************
    //**********************************************************
    /*subsystem*/ public void lockTable();
    /*subsystem*/ public void unlockTable();
    /*subsystem*/ public void tryToUnlockTableWithoutCommit();
    /*subsystem*/ public Row newDbRow();


    //?? Which is it ?? Do we throw a SQL or a mapping or a DB exception ??
    /*subsystem*/ public void insertRow(Row row) throws SQLException;
    /*subsystem*/ public Object insertRow_getIdentity(Row row) throws SQLException;
    /*subsystem*/ public void updateRow(Row row, Object primaryKeyValue);
    /*subsystem*/ public void deleteRow(Object primaryKey);

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem*/ public void deleteWhereColumn_equalsColumnValue(Column column, Object columnValue)
            throws SQLException;

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem!!Special*/ public Connection connection();

};