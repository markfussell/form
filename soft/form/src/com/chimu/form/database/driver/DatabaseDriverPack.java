/*======================================================================
**
**  File: chimu/form/database/driver/DatabaseDriverPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.driver;

import java.sql.Connection;
import com.chimu.kernel.collections.*;



/**
DatabaseProductPack
**/
public class DatabaseDriverPack {

    static public DatabaseDriver driverFromCode(int productCode) {
        if ((productCode > 0) && (productCode < DatabaseDriverConstants.DR_SIZE)) {
            return drivers[productCode];
        };
        return null;
    }

    static public DatabaseDriver driverFromKey(String keyString) {
        return (DatabaseDriver) keyToDriver.atKey(keyString);
    }

    static public List drivers() {
        List driversSeq = CollectionsPack.newList();
        for (int i = 0; i < drivers.length; i++) {
            if (drivers[i] != null) driversSeq.add(drivers[i]);
        }
        return driversSeq;
    }

    static public List availableDrivers() {
        List driversSeq = CollectionsPack.newList();
        for (int i = 0; i < drivers.length; i++) {
            DatabaseDriver driver = drivers[i];
            if ( (driver != null) &&
                 (findCOrNull(driver.defaultDriverCName()) != null) ) {
                driversSeq.add(driver);
            }
        }
        return driversSeq;
    }

    static public void main(String[] args) {
        System.out.println(drivers());
    }

    //**************************************
    //**************************************
    //**************************************

    static protected DatabaseDriver[] drivers = null;
    static protected Map keyToDriver = CollectionsPack.newMap();

    static {
        drivers = new DatabaseDriver[DatabaseDriverConstants.DR_SIZE];

        registerDriver(new com.chimu.form.database.driver.sunJdbcOdbc.DatabaseDriverC());
        registerDriver(new com.chimu.form.database.driver.fastForward.DatabaseDriverC());
        registerDriver(new com.chimu.form.database.driver.webLogic.DatabaseDriverC());
        registerDriver(new com.chimu.form.database.driver.msJdbcOdbc.DatabaseDriverC());
        registerDriver(new com.chimu.form.database.driver.oracleJdbc.DatabaseDriverC());
    }

    static void registerDriver(DatabaseDriver driver) {
        drivers[driver.code()]=driver;
        keyToDriver.atKey_put(driver.key(),driver);
    }

    private DatabaseDriverPack() {};

    static protected Class findCOrNull(String className) {
        try {
            return Class.forName(className);
        } catch (Exception e) {};
        return null;
    }


};
