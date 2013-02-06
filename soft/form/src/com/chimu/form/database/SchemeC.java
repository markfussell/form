/*======================================================================
**
**  File: chimu/form/database/SchemeC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.form.database.product.DatabaseProduct;

import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;
import java.sql.Types;

/**
A Scheme represents the first "grouping" within a Database.
represents a connection to a database Table and are
in the context (e.g. user privileges) of a DatabaseConnection.  A Table
knows its own scheme and can retrieve rows from the database and
write rows to the database.
**/
/*package*/ class SchemeC implements Scheme {

    /*package*/ protected SchemeC(String name, DatabaseConnection dbConnection, Catalog catalog) {
        this.name = name;
        this.dbConnection = dbConnection;
        this.catalog = catalog;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         *<P>To allow the default scheme to be changed.  Use sparingly
         */
    public void setName(String aName) {
        if (aName.equals(name)) return;  //Non-change


        boolean success = ((CatalogC) catalog).updateScheme_withName(this,aName);
        if (!success) throw new DevelopmentException("Tried to change the default scheme to an existing scheme name");
        this.name = aName;
    }

    public String              name() {
        return this.name;
    }

    public DatabaseConnection  databaseConnection() {
        return this.dbConnection;
    }

    public String              fullName() {
        return this.name();
    }

    public boolean isAnonymous() {
        return this.name.length()==0;
    }

        /**
         * The qualified name of a scheme includes the SQL-92 standard
         * prefixes of "<Catalog>." if this is known to
         * the table or database connection.
         */
    public String              qualifiedName() {
        String prefix = catalog().qualifiedName();

        if (prefix == null) {
            if (isAnonymous()) {
                return null;
            } else {
                return name();
            }
        } else {
            if (isAnonymous()) {
                return prefix + ".";
            } else {
                return prefix + "." + name();
            }
        }
    }

    public Catalog catalog() {
        return this.catalog;
    }

    public Collection tables() {
        return tables;
    }

    //**********************************************************
    //(P)***************** Factory Methods *********************
    //**********************************************************

    public Table table(String tableName) {
        Table result = (Table) tableNames.atKey(tableName);
        if (result == null) {
            result = privateNewTable(tableName);
            tables.add(result);
            tableNames.atKey_put(tableName,result);
        };
        return result;
    }

    public Table newTable(String tableName) {
        return table(tableName);
        // NotProperlyImplementedYet
        // return new TableC(tableName,null,this);
    }

    protected DatabaseProduct product() {
        return dbConnection.databaseProduct();
    }

    protected Table privateNewTable(String tableName) {
        TableSi table = (TableSi) product().newTableNamed_for(tableName,this);
        //if ((DatabaseConnectionsC) dbConnection).reusePrimaryKeyStatement) {
        //    table.setupReusePrimaryKeyStatement();
        //};
        return table;
    }

    public boolean equals(Scheme aScheme) {
        return ((this.name() == null) && (aScheme.name() == null)) 
            || (this.name()).equals(aScheme.name());
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String  name = null;
    protected Catalog catalog = null;

    protected Map   tableNames = CollectionsPack.newMap();
    protected List  tables     = CollectionsPack.newList();

    protected DatabaseConnection dbConnection;

};