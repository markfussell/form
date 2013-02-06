/*======================================================================
**
**  File: chimu/form/database/DatabasePackSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import java.sql.Connection;
import com.chimu.kernel.collections.*;

import com.chimu.form.FormRuntimeException;

/**
**/


public class DatabasePackSi {

    //**********************************************************
    //(P)***************** Factory Methods *********************
    //**********************************************************

    //**************************************
    //(P)************  Rows  ***************
    //**************************************

    static public Row newRow() {
        return new RowC ();
    };

    static public Row newRow(KeyedCollection indexes) {
        return new RowC (indexes);
    };

    static public Row newRow(KeyedCollection indexes, Object[] values) {
        return new RowC (indexes, values);
    };

    static public Row newRow(Table table, KeyedCollection indexes) {
        return new RowC (table, indexes);
    };

    static public Row newRow(Column column) {
        return new RowSingleColumnC(column);
    };

    static public final Object UNSET_COLUMN_VALUE = "<UnsetValue>";


    //**************************************
    //(P)**********  Database  *************
    //**************************************

    static public DatabaseConnection newDatabaseConnection(Connection connection) {
        DatabaseConnectionC dbConnection = new DatabaseConnectionC(connection);
        dbConnection.doneSetup();
        return dbConnection;
    };

    static public DatabaseConnection newDatabaseConnection_delayDoneSetup(Connection connection) {
        DatabaseConnectionC dbConnection = new DatabaseConnectionC(connection);
        return dbConnection;
    };

    //**************************************
    //**************************************
    //**************************************

    static public FormRuntimeException newConfigurationException(String description) {
        FormRuntimeException e = new FormRuntimeException(description);
        e.setupRecognizer(DatabasePack.EXCEPTION_RECOGNIZER);
        e.setupStage("Configuration");
        e.setupCause("Usage");
        return e;
    }

    static public FormRuntimeException newConfigurationException(String description, Throwable causingException) {
        FormRuntimeException e = new FormRuntimeException(description,causingException);
        e.setupRecognizer("Database");
        e.setupStage("Configuration");
        e.setupCause("Usage");
        return e;
    }

    static public FormRuntimeException newExecutionException(String description) {
        FormRuntimeException e = new FormRuntimeException(description);
        e.setupRecognizer("Database");
        e.setupStage("Execution");
        e.setupCause("Internal");
        return e;
    }

    static public FormRuntimeException newExecutionException(String description, Throwable causingException) {
        FormRuntimeException e = new FormRuntimeException(description,causingException);
        e.setupRecognizer("Database");
        e.setupStage("Execution");
        e.setupCause("Internal");
        return e;
    }

    static public FormRuntimeException newJdbcException(String description, Throwable causingException) {
        FormRuntimeException e = new FormRuntimeException(description,causingException);
        e.setupAnomaly(DatabasePack.jdbcAnomaly());

        e.setupRecognizer("Database");
        e.setupStage("Execution");
        e.setupCause("JDBC");
        return e;
    }

    //**************************************
    //**************************************
    //**************************************

    private DatabasePackSi() {};

};
