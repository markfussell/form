/*======================================================================
**
**  File: chimu/form/database/ColumnPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.chimu.kernel.collections.Map;

/**
ColumnPi defines the Package interface of Column.  This interface is private to FORM
and should not be used by FORM clients.
**/
/*package*/ public interface ColumnPi extends ColumnSi {
    /*package*/ Object valueForRow(RowPi row);
    /*package*/ void   setValueForRow_to(RowPi row, Object value);
    /*package*/ public void addWhereParametersTo_in(StringBuffer stringB, TableSi table);
    /*package*/ public  int setPstmt_itemStart_to(PreparedStatement pstmt, int item, Object value)
            throws SQLException;

    /*package*/ public void addPartialColumnMapTo(Map columnMap);


    public void putInsertSqlInto_forValue(SqlStatementBuilder sqlStmtB, Object value);
    public void putUpdateSqlInto_forValue(SqlStatementBuilder sqlStmtB, Object value);
    public void putWhereParametersInto_forValue(SqlStatementBuilder sqlStmtB, Object value);
    
    public boolean hasColumnValueFor(Object value);
};