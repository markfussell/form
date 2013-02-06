/*======================================================================
**
**  File: chimu/form/transaction/TransactionPackSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.transaction;

import com.chimu.form.database.*;

import com.chimu.kernel.functors.*;
import java.sql.Connection;

/**
The TransactionPack contains Transaction coordination classes
**/
public class TransactionPackSi {

    //**********************************************************
    //(P)***************** Factory Methods ********************
    //**********************************************************

    static public TransactionCoordinator newTransactionCoordinator(Connection connection) {
        return new TransactionCoordinatorC(connection);
    }

    static public TransactionCoordinator newTransactionCoordinator(DatabaseConnection dbConnection) {
        return new TransactionCoordinatorC(dbConnection);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    private TransactionPackSi() {};

};
