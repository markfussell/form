/*======================================================================
**
**  File: chimu/form/database/BasicColumnPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import java.sql.*;

/**
BasicColumnPi provides an interface to functionality that only makes
sense for BasicColumns and is only available within the package
**/
public /*package*/ interface BasicColumnPi extends BasicColumn, ColumnPi {

    //  SUBSYSTEM ACCESS FOR set... //
    /*package*/ public void setSqlDataType(int sqlDataType);
    /*package*/ public void setIsNullable(boolean isNullable);
    /*package*/ public void setMaximumLength(int maximumLength);
    /*package*/ public void doneSetup();

    /*package*/ public void setJavaTypeFromSqlType();
    /*package*/ public void setJavaDataType(int javaDataType);

    /*package*/ public void setPstmt_item_to(PreparedStatement pstmt, int item, Object value)            throws SQLException;
    /*package*/ public Object getFromResultSet_atIndex(ResultSet rs, int index)
            throws SQLException;

    /*package*/ public boolean hasDatabaseInfo();
    // /*package*/ Object encode(Object value);
    // /*package*/ Object decode(Object value);
};