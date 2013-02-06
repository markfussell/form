/*======================================================================
**
**  File: chimu/form/database/driver/DatabaseDriver.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.driver;

import com.chimu.form.database.product.DatabaseProduct;

/**
**/
public interface DatabaseDriver  {
    // String  jdbcUrlPrefix();

    String key();
    String name();
    int    code();

    String  defaultDriverCName();
    String  driverCNameForDatabase(DatabaseProduct aProduct);
    String  jdbcUrlForProduct_database(DatabaseProduct aProduct, String databaseName);

    boolean canWorkWithDatabase(DatabaseProduct aProduct);
    boolean needsToKnowDatabaseProduct();
}