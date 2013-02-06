/*======================================================================
**
**  File: chimu/form/database/driver/fastForward/DatabaseDriverC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.driver.fastForward;

import com.chimu.form.database.product.DatabaseProduct;
import com.chimu.form.database.product.DatabaseProductConstants;

import com.chimu.form.database.driver.DatabaseDriverAbsC;
import com.chimu.form.database.driver.DatabaseDriverConstants;

/*subsystem*/ public class DatabaseDriverC extends DatabaseDriverAbsC {
    public DatabaseDriverC() {
    }

    public String key() {
        return "fastforward";
    }

    public String name() {
        return "Fast Forward Driver";
    }

    public int code() {
        return DatabaseDriverConstants.DR_FAST_FORWARD;
    }

    public String defaultDriverCName() {
        return "connect.microsoft.MicrosoftDriver";
    }

    public String driverCNameForDatabase(DatabaseProduct aProduct) {
        if (aProduct.code()==DatabaseProductConstants.DB_MSSQL) {
            return defaultDriverCName();
        };
        return null;
    }

    public String  jdbcUrlForProduct_database(DatabaseProduct aProduct, String databaseName) {
        if (aProduct.code()==DatabaseProductConstants.DB_MSSQL) {
            return "jdbc:ff-microsoft:"+databaseName;
        };
        return null;
    }

    public boolean canWorkWithDatabase(DatabaseProduct aProduct) {
        if (aProduct.code()==DatabaseProductConstants.DB_MSSQL) {
            return true;
        };
        return false;
    }

    public boolean needsToKnowDatabaseProduct() {return true;}


    //**********************************************************
    //**********************************************************
    //**********************************************************

}