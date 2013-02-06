/*======================================================================
**
**  File: chimu/form/database/driver/webLogic/DatabaseDriverC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.driver.webLogic;

import com.chimu.form.database.product.DatabaseProduct;
import com.chimu.form.database.product.DatabaseProductConstants;

import com.chimu.form.database.driver.DatabaseDriverAbsC;
import com.chimu.form.database.driver.DatabaseDriverConstants;

/*subsystem*/ public class DatabaseDriverC extends DatabaseDriverAbsC {
    public DatabaseDriverC() {
    }

    public String key() {
        return "weblogic";
    }


    public String name() {
        return "Web Logic Driver";
    }

    public int code() {
        return DatabaseDriverConstants.DR_WEBLOGIC;
    }

    public String defaultDriverCName() {
        return "NOT.IMPLEMENTED.YET ";
    }

    public String driverCNameForDatabase(DatabaseProduct aProduct) {
        if (aProduct.code()==DatabaseProductConstants.DB_MSSQL) {
            return defaultDriverCName();
        };
        return null;
    }

    public String  jdbcUrlForProduct_database(DatabaseProduct aProduct, String databaseName) {
        if (aProduct.code()==DatabaseProductConstants.DB_MSSQL) {
//            return "jdbc::"+databaseName;
            return "not implemented";

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