/*======================================================================
**
**  File: chimu/form/database/product/db2/TableC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.db2;


import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import com.chimu.form.query.*;
import com.chimu.form.mapping.*;

import java.sql.*;

import java.util.Enumeration;

import com.chimu.form.database.*;

/*subsystem*/ public class TableC extends TableAbsC{
    /*spackage*/ public TableC(String name, String fullName, Connection connection) {
        super(name,fullName,connection);
    }

    /*spackage*/ public TableC(String name, String fullName, DatabaseConnection dbConnection) {
        super(name,fullName,dbConnection);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

       /**
        * This returns the name of this table capitalized appropriately for the databse
        */
    protected String databaseTableName() {
        return this.name.toUpperCase();
    }

    protected int convertSqlDataType(int value) {
        if (value == 11) return 93;  //SQLServerHack ?? Appears to be ODBC Hack
        //if (value == 3) return 2;  //MLF970610:Decimal SQLServerHack
        return value;
    }

    //**************************************
    //(P)*********** Locking ***************
    //**************************************

    /*subsystem*/ public void lockTable() {
        // Unknown
    }

    /*subsystem*/ public void unlockTable() {
        // Unknown
    }


    //**************************************
    //(P)********** InsertRow **************
    //**************************************


    /*subsystem*/ public Object insertRow_getIdentity(Row row) throws SQLException {
        throw new ShouldNotImplementException("Unknown whether DB-2 has identity generation columns");
    }

}


