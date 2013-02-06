/*======================================================================
**
**  File: chimu/form/database/DatabaseConnection.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.form.database.product.DatabaseProduct;

import com.chimu.form.transaction.TransactionCoordinator;

import java.sql.Connection;
import java.io.PrintWriter;

import com.chimu.kernel.collections.*;

/**
A DatabaseConnection represents both a connection to a specific database. This
includes knowing the driver type, product type, database scheme, and the current
state of that database.

<P>The primary important protocol for FORM clients is the #table method
which provides the ability to get table connections that can be used
in mapping to the database.
**/

public interface DatabaseConnection {

    //**************************************
    //(P)****** Development Tracing ********
    //**************************************

    public void setupTraceStream (PrintWriter traceStream);
    public void setupTraceStream_traceLevel(PrintWriter traceStream, int traceLevel);

        /**
         * Enabling performance features will turn on
         * optimizations that can make database performance
         * faster.  You should only enable this when you
         * are sure the application is stable and want to
         * see if you can improve the database layer.
         * Features that are known not to work with
         * a particular database or JDBC driver will not be
         * turned on, but compatibility problems can still
         * exist.
         */
    public void setupEnablePerformanceFeatures();

    public void doneSetup();

    //**************************************
    //**************************************
    //**************************************

        /**
         * Return the type of database product this connection
         * is connected to
         */
    public DatabaseProduct databaseProduct();

        /**
         * The default TransactionIsolationLevel for the database
         */
    public int defaultTransactionIsolationLevel();


        /**
         * Does the database support the given transaction isolation level?
         */
    public boolean supportsTransactionIsolationLevel(int level);

        /**
         * Return the JDBC Connection used with this DatabaseConnection
         */
    public Connection connection();

    public Collection tables();


    //**************************************
    //**************************************
    //**************************************

        /**
         * Get a Table from the DatabaseConnection.  If
         * a Table already exists, this will return it.
         */
    public Table table(String tableName);
        //this.defaultCatalog().defaultScheme().table(tableName);
        
        /**
         * Get a new TableConnection from the DatabaseConnection, even
         * if a TableConnection already exists.
         */
    public Table newTable(String tableName);

    //public Table table(String tableName, Scheme aScheme);
    //public Table table(String tableName, Catalog aCatalog);
    
    //public Scheme  defaultScheme();
    
    //**************************************
    //**************************************
    //**************************************
    
    public Collection  catalogs();
    public Catalog     defaultCatalog();
    
    public Catalog catalog(String catalogName);
    public void setDefaultCatalog(Catalog catalog);
    
    //**************************************
    //**************************************
    //**************************************

         /**
         * Return the index Table from this connection.
         */
//    public Table table(String tableName, int index);

        /**
         * Return the default transactionCoordinator for this DatabaseConnection
         *
         *@see com.chimu.form.transaction.TransactionCoordinator
         */
    public TransactionCoordinator transactionCoordinator();

        /**
         * Creates a new TransactionCoordinator even if one currently exists.
         *
         *@see com.chimu.form.transaction.TransactionCoordinator
         */
    public TransactionCoordinator newTransactionCoordinator();

        /**
         * Go through all tables in this connection and set the java column
         * types for them based on the SQL Data types.
         *
         *<P>This will not
         */
    public void setColumnJavaTypes();

//    public void addTable(Table table);

//    public DatabaseConnection newDatabaseConnection(Connection connection);
};

