/*======================================================================
**
**  File: chimu/form/database/driver/msJdbcOdbc/DatabaseDriverC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.driver.msJdbcOdbc;

import com.chimu.form.database.product.DatabaseProduct;
import com.chimu.form.database.product.DatabaseProductConstants;

import com.chimu.form.database.driver.DatabaseDriverAbsC;
import com.chimu.form.database.driver.DatabaseDriverConstants;

/*subsystem*/ public class DatabaseDriverC extends DatabaseDriverAbsC {
    public DatabaseDriverC() {
    }

    public String key() {
        return "msjdbc";
    }

    public String name() {
        return "Microsoft JDBC Driver";
    }

    public int code() {
        return DatabaseDriverConstants.DR_MS_JDBC_ODBC;
    }

    public String defaultDriverCName() {
        return "com.ms.jdbc.odbc.JdbcOdbcDriver";
    }

    public String driverCNameForDatabase(DatabaseProduct aProduct) {
        return defaultDriverCName();
    }

    public String  jdbcUrlForProduct_database(DatabaseProduct aProduct, String databaseName) {
        return "jdbc:odbc:"+databaseName;
    }

    public boolean canWorkWithDatabase(DatabaseProduct aProduct) {
        return true;
    }

    public boolean needsToKnowDatabaseProduct() {return false;}

    //**********************************************************
    //**********************************************************
    //**********************************************************

}