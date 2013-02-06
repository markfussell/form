/*======================================================================
**
**  File: chimu/form/transaction/TransactionCoordinatorC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.transaction;

import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;

import java.util.Enumeration;

import java.sql.*;


/*package*/ class TransactionCoordinatorC implements TransactionCoordinator {
    // How does it know about the connection???
    //

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    TransactionCoordinatorC(DatabaseConnection dbConnection) {
        this.dbConnection = (DatabaseConnectionSi) dbConnection;
        this.connection   = dbConnection.connection();
        this.isolationLevel = USE_CURRENT_ISOLATION_LEVEL;
    };

    TransactionCoordinatorC(Connection connection) {
        this.connection = connection;
        this.isolationLevel = USE_CURRENT_ISOLATION_LEVEL;
    };

    /*
    public TransactionCoordinatorC(Connection connection, int isolationLevel) {
        this.connection = connection;
        this.isolationLevel = isolationLevel;
    };
    */

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************


    public boolean setupTransactionIsolationLevel(int isolationLevel) {
        if (dbConnection != null) {
            if (!dbConnection.supportsTransactionIsolationLevel(isolationLevel)) {
                throw new com.chimu.form.ConfigurationException("Database can not support the transaction isolation level of "+isolationLevel);
            }
        }
        this.isolationLevel = isolationLevel;

        return true;
    };


    public boolean forceSetupTransactionIsolationLevel(int isolationLevel) {
        this.isolationLevel = isolationLevel;

        if (dbConnection != null) {
            return dbConnection.supportsTransactionIsolationLevel(isolationLevel);
        }

        return true;
    };

    public void setupSingleProcedure(Procedure0Arg procedure) {
        this.currentCommand = procedure;
    };

	    /**
         * Turn off the feature of the transaction coordinator remembering
         * and restoring the previous state of the connection?
         */
    public void dontAutoRestoreConnectionState() {
	    this.shouldAutoRestoreConnectionState = false;
    }

    public void dontAutoPrepareConnectionState() {
        this.shouldAutoRestoreConnectionState = false;
	    this.shouldAutoPrepareConnectionState = false;
    }

    public void prepareConnectionState() throws TransactionException {
        rememberConnectionState();
	    internalPrepareConnectionState();
	}

    protected void internalPrepareConnectionState() throws TransactionException {
        try {
            if (connection.getAutoCommit()) {
                setAutoCommit(false);
            } else {
                connection.commit();  //Finish the current transaction...
            };

            if (  (isolationLevel != USE_CURRENT_ISOLATION_LEVEL) &&
                  (isolationLevel != previousIsolationLevel)          ) {
                connection.setTransactionIsolation(isolationLevel);
            };
        } catch (SQLException e) {
            throw new TransactionException("Could not open a transaction at level: "+isolationLevel,e);
        };
    }

    //**********************************************************
    //(P)********************* Execute *************************
    //**********************************************************

    public void executeProcedure(Procedure0Arg procedure) throws TransactionException {
        this.setupSingleProcedure(procedure);
        this.execute();
    }

    public void execute() throws TransactionException {
        triedToPerform = true;
        beginTransaction();

        try {
            // for all commands
            // fetch command
            currentCommand.execute();
            // completedCommand
//      } catch (Exception e) {  //Is this for a different type of exception
        } catch (Exception e) {
            wasSuccessful = false;
            rollbackTransaction();
     	    if (shouldAutoRestoreConnectionState) {
    	        releaseConnectionLock();
    	    };
            throw new TransactionException("Transaction failed",e);
//            exception = e;
        };
        commitTransaction();
        wasSuccessful = true;
     	if (shouldAutoRestoreConnectionState) {
    	    releaseConnectionLock();
    	};
    };

    public boolean value() {
        if (!triedToPerform) {
            try {
                execute();
            } catch (Exception e) {}; //Just care whether it was successful
        };

        return wasSuccessful;
    };

    // ********************************************



    protected void beginTransaction() throws TransactionException {
        // get a lock on the connection...
        // either actual or semantic

    	if (shouldAutoRestoreConnectionState) {
    	    getConnectionLock();
    	};

    	if (shouldAutoPrepareConnectionState) {
    	    internalPrepareConnectionState();
    	};

    };

    protected void rollbackTransaction() throws TransactionException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new TransactionException("Could not rollback transaction!!!");
        };
    };

    protected void commitTransaction() throws TransactionException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new TransactionException("Could not commit transaction!!!");
        };
    };



    protected void getConnectionLock() {
        rememberConnectionState();
    };

    protected void releaseConnectionLock() {
        restoreConnectionState();
    };


    protected void rememberConnectionState() {
        try {
            previousAutoCommit     = connection.getAutoCommit();
            previousIsolationLevel = connection.getTransactionIsolation();
        } catch (SQLException e) {
            previousAutoCommit = true;
            previousIsolationLevel = isolationLevel;
        };
    }

    protected void restoreConnectionState()  {
        try {
            if (  (isolationLevel != USE_CURRENT_ISOLATION_LEVEL) &&
                  (previousIsolationLevel != isolationLevel)          ) {
                connection.setTransactionIsolation(previousIsolationLevel);
                    //The also sets the autocommit to 'false' (usually) so we need to restore
            };
            setAutoCommit(previousAutoCommit);
        } catch (SQLException e) {
            throw new com.chimu.form.ExecutionException("Could not restore connection state");
        };
    }

    public Connection connection() {
        return connection;
    }

        /**
         * Release the TransactionCoordinator from duty: clear the
         * connection and preprare for finalization.
         */
    public void release() throws SQLException {
        connection.commit();
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected void setAutoCommit (boolean shouldAutoCommit) throws SQLException {
        if (dbConnection != null) {
            dbConnection.setAutoCommit(shouldAutoCommit);
        } else {
            connection.setAutoCommit(shouldAutoCommit);
        }
    }


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    /**
     * The connection that I am attached to and will operate through
     */
    protected Connection            connection;
    protected DatabaseConnectionSi  dbConnection;
    protected int                   isolationLevel;

    protected int         previousIsolationLevel;
    protected boolean     previousAutoCommit;

    protected boolean     shouldAutoRestoreConnectionState  = true;
    protected boolean     shouldAutoRememberConnectionState = true;
    protected boolean     shouldAutoPrepareConnectionState  = true;



    protected boolean wasSuccessful = false;
    protected boolean triedToPerform = false;

    protected Procedure0Arg currentCommand;

    protected List commands = CollectionsPack.newList();
    protected List commandsToDoQueue = CollectionsPack.newList();

    static final int USE_CURRENT_ISOLATION_LEVEL = -1;
};

/*

doSave
	"Open the transaction, error handlers, etc. and do all the object saves"

	| nextCommand |
	wasSuccessful := nil.
	self beginTransaction.
	[
		Signal noHandlerSignal handle: [:outex |
			(outex parameter signal inheritsFrom: Object haltSignal ) ifTrue: [outex reject].
			wasSuccessful isNil ifTrue: [
				self rollbackTransaction.
				wasSuccessful := false.
			].
			error := outex parameter.
			^true
		] do: [
			MckDomainModelBase domainDatabaseFailureSignal handle: [:ex |
				"Rollback the transaction, set status, rollback the operation on the objects, etc"
				self rollbackTransaction.
				wasSuccessful := false.
				error := ex.
				^nil
			] do: [
				self setupCommands.
				[
					nextCommand := self nextCommand.
					nextCommand notNil
				] whileTrue: [
					nextCommand doIt.
					self completedCommand: nextCommand.
				].
			].
			self commitTransaction.
			wasSuccessful := true.
		].
	] valueOnUnwindDo: [
		wasSuccessful isNil ifTrue: [
			self fullRollbackTransaction.
			wasSuccessful := false.
		].
	].


*/