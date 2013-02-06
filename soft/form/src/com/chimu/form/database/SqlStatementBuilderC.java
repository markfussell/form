/*======================================================================
**
**  File: chimu/form/database/SqlStatementBuilderC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import java.sql.*;
//import java.util.StringBuffer;

/*subsystem*/ public class SqlStatementBuilderC implements SqlStatementBuilder {

    public void appendSqlString(String string) {
        sqlStringB.append(string);
    }

    public void replaceSqlString(String string) {
        sqlStringB = new StringBuffer();  //Fix this...
        sqlStringB.append(string);
    }

    public void appendSqlString2(String string) {
        sqlString2B.append(string);
    }

    public void addBoundValue_column(Object value, Column column) {
        boundValues.add(value);
        boundColumns.add(column);
    };

    public boolean hasBoundValues() {
        return boundValues.size() > 0;
    }

    public String sqlString() {
        return sqlStringB.toString();
    }

    public String sqlString2() {
        return sqlString2B.toString();
    }

    public int setBoundValues(PreparedStatement pstmt) {
        int size = boundValues.size();
        try {
            for (int i = 0; i<size; i++) {
                BasicColumnPi column = (BasicColumnPi) boundColumns.atIndex(i);
                Object value = boundValues.atIndex(i);
                column.setPstmt_item_to(pstmt,i,value);
            }
        } catch (Exception e) {
            throw new RuntimeWrappedException("Could not bind values",e);
        }
        return size;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    StringBuffer sqlStringB = new StringBuffer();
    StringBuffer sqlString2B = new StringBuffer();
    List boundValues = CollectionsPack.newList();
    List boundColumns = CollectionsPack.newList();
}
