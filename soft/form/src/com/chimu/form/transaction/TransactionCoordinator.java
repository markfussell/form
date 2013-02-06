/*======================================================================
**
**  File: chimu/form/transaction/TransactionCoordinator.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.transaction;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import java.sql.*;

/**
A TransactionCoordinator coordinates doing multiple operations within
a begin-commit-rollback transaction environment for JDBC
**/
public interface TransactionCoordinator {

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

        /**
         * Set the isolation level to be used for the transaction.
         * If the level is not known to be supported, the method
         * returns false.
         * The IsolationLevel should be one of:
         * <UL>
         *   <LI> TRANSACTION_READ_UNCOMMITTED
         *   <LI> TRANSACTION_READ_COMMITTED
         *   <LI> TRANSACTION_REPEATABLE_READ
         *   <LI> TRANSACTION_SERIALIZABLE
         * </UL>
         *@see java.sql.Connection
         */
    public boolean setupTransactionIsolationLevel(int isolationLevel);

        /**
         * Give the TransactionCoordinator a single procedure to
         * use for "execute".
         *
         * <P>In the future this will be augmented by multiple "Commands"
         * that each are attempted with the transaction.
         */
    public void setupSingleProcedure(Procedure0Arg procedure);

	    /**
         * Turn off the feature of the transaction coordinator remembering
         * and restoring the previous state of the connection.
         */
    public void dontAutoRestoreConnectionState();

	    /**
         * Turn off the feature of the transaction coordinator automatically
         * preparing the state of the connection before using.
         */
    public void dontAutoPrepareConnectionState();


    //**********************************************************
    //(P)********************* Execute *************************
    //**********************************************************

    public Connection connection();


	    /**
         * Explicitly prepare the connection state
         */
    public void prepareConnectionState() throws TransactionException;

        /**
         * Execute the provided procedure which will either succeed
         * or throw a TransactionException.
         */
    public void executeProcedure(Procedure0Arg procedure) throws TransactionException;

        /**
         * Execute the transaction's procedure which will either succeed
         * or throw a TransactionException.
         */
    public void execute() throws TransactionException;
    public boolean value();

        /**
         * Release the TransactionCoordinator from duty: clear the
         * connection and preprare for finalization.
         */
    public void release() throws SQLException;
}