/*======================================================================
**
**  File: chimu/formTools/test/FormDatabaseTestAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.test;

import java.sql.Connection;
import java.sql.*;
import java.util.*;

import com.chimu.form.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.transaction.*;

import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;

/**
This is the program that is used to drive the functionality for
the UsingFORM examples.

This is an abstract super class.  All test cases should inherit from
this class and implements the DatabaseTest interface.  See Example:


**/

public abstract class FormDatabaseTestAbsC extends DatabaseTestAbsC implements FormDatabaseTest {

   public abstract void run(Connection connection);

   protected void createOrm(Connection jdbcConnection) {
        orm = FormPack.newOrm();
        dbConnection = FormPack.newDatabaseConnection_delayDoneSetup(jdbcConnection);
        if (tracing()) {
            orm.setupTraceStream_traceLevel(traceStream,traceLevel);
            dbConnection.setupTraceStream_traceLevel(traceStream,traceLevel);
        };
        dbConnection.doneSetup();
        
        if (catalogName != null) {
            dbConnection.defaultCatalog().setName(catalogName);
        }
        if (schemeName != null) {
            dbConnection.defaultCatalog().defaultScheme().setName(schemeName);
        }
        //setup schema...
    }
    
    
    protected String catalogName = null;
    protected String schemeName = null;
    
    public void setupCatalog_scheme(String catalog, String scheme) {
        catalogName = catalog;
        schemeName = scheme;
    }

    protected Table newTable(String tableName) {
        return dbConnection.newTable(tableName);
    }

    protected ObjectMapper newObjectMapper(Table newTable) {
        return orm.newObjectMapper(newTable);
    }

    protected DistinguishingObjectMapper newDistinguishingObjectMapper(Table newTable) {
        return orm.newDistinguishingObjectMapper(newTable);
    }

    protected AssociationConnector newAssociationConnector(Table newTable) {
        return orm.newAssociationConnector(newTable);
    }

    protected CollectiveObjectRetriever newCollectiveObjectRetriever() {
        return orm.newCollectiveObjectRetriever();
    }

    protected Orm orm;
    protected DatabaseConnection dbConnection;

    protected TransactionCoordinator tc;

    protected void createTransactionCoordinator(Connection jdbcConnection) {
        tc = dbConnection.newTransactionCoordinator();
    }

    protected void createTransactionCoordinator() {
        tc = dbConnection.newTransactionCoordinator();
    }

    protected void executeTransaction(Procedure0Arg procedure) {
        try {
            tc.executeProcedure(procedure);
        } catch (Exception e) {
            throw new RuntimeWrappedException("Woaaa",e);
        };
    }


    protected void shutdownTransactionCoordinator() {
        try {
            tc.connection().commit();
        } catch (SQLException e) {
            throw new RuntimeException("Could not commit transaction!!!");
        }
    }
}

