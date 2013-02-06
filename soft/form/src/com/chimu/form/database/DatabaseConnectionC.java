/*======================================================================
**
**  File: chimu/form/database/DatabaseConnectionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;
import com.chimu.form.database.product.*;
import com.chimu.form.transaction.*;

import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import java.io.PrintWriter;

import java.sql.*;

/*package*/ class DatabaseConnectionC implements DatabaseConnectionSi  {

    DatabaseConnectionC(Connection connection) {
        this.connection = connection;
        this.defaultCatalog = catalog("");
    }

    public void setupTraceStream (PrintWriter traceStream) {
        setupTraceStream_traceLevel(traceStream, 1);
    }

    public void setupTraceStream_traceLevel(PrintWriter traceStream, int traceLevel) {
        this.ts = traceStream;
        this.traceLevel = traceLevel;
    }

        /**
         *<Require>This must come after setup is complete
         */
    /*subsystem*/ public void setColumnJavaTypes() {
        int size = tables.size();
        for (int i=0; i<size; i++) {
            TableSi table = (TableSi) tables.atIndex(i);
            table.setColumnJavaTypes();
        }
    }

    public boolean supportsTransactionIsolationLevel(int level) {
        Object result = supportedIsolationLevels.atKey(new Integer(level));
        if (result == null) return false;
        return ((Boolean) result).booleanValue();
    }

    public void doneSetup() {
        if (doneSetup) return;
        DatabaseMetaData metaData = null;
        String driverDescription = "";
        String productDescription = "";
        try {
            metaData = connection.getMetaData();
            driverDescription = metaData.getDriverName();
            productDescription = metaData.getDatabaseProductName().toLowerCase();
if (ts != null) {
    ts.println("Driver Product   = "+driverDescription +" ["+metaData.getDriverVersion()+"]");
    ts.println("Database Product = "+productDescription+" ["+metaData.getDatabaseProductVersion()+"]");
}

            defaultTransactionIsolationLevel = metaData.getDefaultTransactionIsolation();
            getSupportedIsolationLevels(metaData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        product = DatabaseProductPack.productFromProductDescription(productDescription);
        if (product == null) {
            product = DatabaseProductPack.productFromCode(
                            DatabaseProductConstants.DB_SQL92);
        };

if (ts != null) {
    ts.println("Recognized Database Product is: "+product.name());
}

        //<ToDo> Should initialize TABLES
        //But the DatabaseConnection is "doneSetup" immediately
/*
        int size = tables.size();
        for (int i=0; i<size; i++) {
            TableSi table = (TableSi) tables.atIndex(i);
            table.doneSetup();
        }
*/

        doneSetup = true;
    }

    public Connection connection() {
        return connection;
    }

    public DatabaseProduct databaseProduct() {
        return product;
    }

    public int defaultTransactionIsolationLevel() {
        return defaultTransactionIsolationLevel;
    }

    public Collection  catalogs() {
        return catalogs;
    }
    
    public Catalog     defaultCatalog() {
        return defaultCatalog;
    }

    public void setDefaultCatalog(Catalog catalog) {
        defaultCatalog = catalog;
    }

    public Collection tables() {
        //throw new NotImplementedYetException("Need to collect over the catalogs");
        
        return defaultCatalog().defaultScheme().tables();
    }

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
    public void setupEnablePerformanceFeatures() {
        reusePrimaryKeyStatement = true;
    }


    //**********************************************************
    //(P)***************** Connection Methods ******************
    //**********************************************************

    public void setAutoCommit(boolean shouldAutoCommit) throws SQLException {
        connection.setAutoCommit(shouldAutoCommit);
    }

    //**********************************************************
    //(P)***************** Factory Methods *********************
    //**********************************************************

    public Table table(String tableName) {
        return defaultCatalog().defaultScheme().table(tableName);
        /*
            Table result = (Table) tableNames.atKey(tableName);
            if (result == null) {
                result = privateNewTable(tableName);
                tables.add(result);
                tableNames.atKey_put(tableName,result);
            };
            return result;
        */
    }

    public Table newTable(String tableName) {
        return table(tableName);
        // NotProperlyImplementedYet
        // return new TableC(tableName,null,this);
    }

    public TransactionCoordinator transactionCoordinator() {
        if (transactionCoordinator == null) {
            transactionCoordinator = newTransactionCoordinator();
        };
        return transactionCoordinator;
    }

    public TransactionCoordinator newTransactionCoordinator() {
        return TransactionPackSi.newTransactionCoordinator(this);
    }


    public Catalog catalog(String catalogName) {
        Catalog result = (Catalog) catalogNames.atKey(catalogName);
        if (result == null) {
            result = privateNewCatalog(catalogName);
            catalogs.add(result);
            catalogNames.atKey_put(catalogName,result);
        };
        return result;
    }

    protected Catalog privateNewCatalog(String catalogName) {
        return new CatalogC(catalogName, this);
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected void getSupportedIsolationLevels(DatabaseMetaData metaData) {
        record_supportsIsolationLevel(metaData,Connection.TRANSACTION_NONE);
        record_supportsIsolationLevel(metaData,Connection.TRANSACTION_READ_UNCOMMITTED);
        record_supportsIsolationLevel(metaData,Connection.TRANSACTION_READ_COMMITTED);
        record_supportsIsolationLevel(metaData,Connection.TRANSACTION_REPEATABLE_READ);
        record_supportsIsolationLevel(metaData,Connection.TRANSACTION_SERIALIZABLE);

    }

    protected void record_supportsIsolationLevel(DatabaseMetaData metaData, int level) {
        try {
            supportedIsolationLevels.atKey_put(
                    new Integer(level),
                    new Boolean(metaData.supportsTransactionIsolationLevel(level))
                );
        } catch (SQLException e) {}; //Just ignore the exception and don't record the level
    }


    //protected Table privateNewTable(String tableName) {
        //return defaultCatalog().defaultScheme().privateNewTable(tableName);
        
        //TableSi table = (TableSi) product.newTableNamed_for(tableName,this);
        //if (reusePrimaryKeyStatement) {
        //    table.setupReusePrimaryKeyStatement();
        //};
        //return table;
    //}

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected final Connection connection;
    
        /** These table variables are just backward-compatibility or performance caches **/
    protected Map   tableNames = CollectionsPack.newMap();
    protected List  tables    = CollectionsPack.newList();
    
    //**************************************
    //**************************************
    //**************************************
    
    protected List  catalogs  = CollectionsPack.newList();
    protected Map   catalogNames = CollectionsPack.newMap();
    protected Catalog defaultCatalog = null;

    protected TransactionCoordinator   transactionCoordinator = null;

    protected DatabaseProduct product = null;

    protected boolean doneSetup = false;  //Have we done our setup yet?

    protected int defaultTransactionIsolationLevel = -1;
    protected Map supportedIsolationLevels = CollectionsPack.newMap();

    protected boolean reusePrimaryKeyStatement = false;

    //**************************************
    //**************************************
    //**************************************

    protected PrintWriter ts = null;
    protected int traceLevel = 1;     //0 = minimal tracing, 1= normal (and slowing), 2=detailed


};

