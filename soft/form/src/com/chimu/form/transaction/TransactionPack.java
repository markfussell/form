/*======================================================================
**
**  File: chimu/form/transaction/TransactionPack.java
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
public class TransactionPack {

    //**********************************************************
    //(P)***************** Factory Methods ********************
    //**********************************************************

        /**
         *@deprecated Use DatabaseConnection.newTransactionCoordinator
         */
    static public TransactionCoordinator newTransactionCoordinator(Connection connection) {
        return new TransactionCoordinatorC(connection);
    }

        /**
         *@deprecated Use DatabaseConnection.newTransactionCoordinator
         */
    static public TransactionCoordinator newTransactionCoordinator(DatabaseConnection dbConnection) {
        return new TransactionCoordinatorC(dbConnection);
    }

    static public boolean valueProcedure_inConnection(Procedure0Arg procedure,Connection connection) {
        TransactionCoordinator tc = newTransactionCoordinator(connection);
        tc.setupSingleProcedure(procedure);
        return tc.value();
    }

    static public void executeProcedure_inConnection(Procedure0Arg procedure,Connection connection)
           throws TransactionException {
        TransactionCoordinator tc = newTransactionCoordinator(connection);
        tc.setupSingleProcedure(procedure);
        tc.execute();
    }


    static public boolean value2Procedure_inConnection(Procedure0Arg procedure,Connection connection) {
        try {
            procedure.execute();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception e2) {};
            return false;
        };
        try {
            connection.commit();
        } catch (Exception e) {};
        return true;
    }



    //**********************************************************
    //**********************************************************
    //**********************************************************

    private TransactionPack() {};

};
