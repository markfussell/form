/*======================================================================
**
**  File: chimu/form/database/Scheme.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.kernel.collections.*;
import java.sql.Types;

/**
A Scheme represents the first "grouping" within a Database.
represents a connection to a database Table and are
in the context (e.g. user privileges) of a DatabaseConnection.  A Table
knows its own scheme and can retrieve rows from the database and
write rows to the database.
**/
public interface Scheme {

    public String              name();
    public String              fullName();
    public DatabaseConnection  databaseConnection();
    public boolean isAnonymous();


        /**
         * The qualified name of a scheme includes the SQL-92 standard
         * prefixes of "<Catalog>." if this is known to
         * the table or database connection.
         */
    public String              qualifiedName();


    public Catalog             catalog();
    public Collection          tables();

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void setName(String aName);
    
        /**
         * Get a Table from this Scheme within this DbConnection.  If
         * a Table already exists, this will return it.
         */
    public Table table(String tableName);
    
    public boolean equals(Scheme aScheme);

};