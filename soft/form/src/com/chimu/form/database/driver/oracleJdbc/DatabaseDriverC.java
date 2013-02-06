/*======================================================================
**
**  File: chimu/form/database/driver/oracleJdbc/DatabaseDriverC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.driver.oracleJdbc;

import com.chimu.form.database.product.DatabaseProduct;
import com.chimu.form.database.product.DatabaseProductConstants;

import com.chimu.form.database.driver.DatabaseDriverAbsC;
import com.chimu.form.database.driver.DatabaseDriverConstants;

/*subsystem*/ public class DatabaseDriverC extends DatabaseDriverAbsC {
    public DatabaseDriverC() {
    }

    public String key() {
        return "oraclejdbc";
    }

    public String name() {
        return "Oracle JDBC Driver";
    }

    public int code() {
        return DatabaseDriverConstants.DR_ORACLE_JDBC;
    }

    public String defaultDriverCName() {
        return "oracle.jdbc.driver.OracleDriver";
    }

    public String driverCNameForDatabase(DatabaseProduct aProduct) {
        if (aProduct.code()==DatabaseProductConstants.DB_ORACLE) {
            return defaultDriverCName();
        };
        return null;
    }

    public String  jdbcUrlForProduct_database(DatabaseProduct aProduct, String databaseName) {
        if (aProduct.code()==DatabaseProductConstants.DB_ORACLE) {
            return "jdbc:oracle:oci7:"+databaseName;
        };
        return null;
    }

    public boolean canWorkWithDatabase(DatabaseProduct aProduct) {
        if (aProduct.code()==DatabaseProductConstants.DB_ORACLE) {
            return true;
        };
        return false;
    }

    public boolean needsToKnowDatabaseProduct() {return true;}


    //**********************************************************
    //**********************************************************
    //**********************************************************

}