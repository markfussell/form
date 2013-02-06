/*======================================================================
**
**  File: chimu/form/database/SqlStatementBuilder.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import java.sql.Connection;
import java.sql.PreparedStatement;

/*subsystem*/ public interface SqlStatementBuilder {
    public String sqlString();
    public String sqlString2();

    public void appendSqlString(String string);
    public void appendSqlString2(String string);
    public void replaceSqlString(String string);

    public void addBoundValue_column(Object value, Column column);
    public boolean hasBoundValues();
    public int setBoundValues(PreparedStatement pStatement);
}
