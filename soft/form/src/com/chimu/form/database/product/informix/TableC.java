/*======================================================================
**
**  File: chimu/form/database/product/informix/TableC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.informix;


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


    protected int convertSqlDataType(int value) {
        if (value == 11) return 93;  //SQLServerHack ?? Appears to be ODBC Hack
        if (value == 9) return 91;  //InformixHack   ?? Or ODBC Hack
        //if (value == 3) return 2;  //MLF970610:Decimal SQLServerHack
        return value;
    }

    //**********************************************************
    //********************** Changing **************************
    //**********************************************************

    //**************************************
    //(P)*********** Locking ***************
    //**************************************

    /*subsystem*/ public void lockTable() {
        //simpleLockTable();
    }

    protected String simpleLockTableQString() {
        return "LOCK TABLE "+qualifiedName()+" IN EXCLUSIVE MODE";
    }

    /*subsystem*/ public void unlockTable() {
        //simpleUnlockTable();
    }

    //**************************************
    //(P)********** InsertRow **************
    //**************************************


    /*subsystem*/ public Object insertRow_getIdentity(Row row) throws SQLException {
        throw new ShouldNotImplementException("INFORMIX does not have identity generation columns");
    }

}


