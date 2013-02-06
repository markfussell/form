/*======================================================================
**
**  File: chimu/form/database/Table.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.kernel.collections.*;
import java.sql.Types;

/**
A Table represents a connection to a database Table and are
in the context (e.g. user privileges) of a DatabaseConnection.  A Table
knows its own scheme and can retrieve rows from the database and
write rows to the database.
**/
public interface Table {

        /**
         * Specify that the table includes a particular column
         */
    public Column newColumnNamed(String name);
    public Column newColumnNamed_type(String name, Class javaC);

        /**
         * Specify that the table includes a particular basic column
         */
    public Column newBasicColumnNamed(String name);
        /**
         * Specify that the table includes a particular basic column with
         * a desired javaDataType.
         *@param javaDataType specifies a specific java class, but it must
         *be among the basic datatype classes (boolean,numbers,dates, strings)
         *to represent the column as.
         *@see com.chimu.kernel.meta.TypeConstants
         */
    public Column newBasicColumnNamed_type(String name, Class javaC);


        /**
         *Create a compound column built out of subcolumns from subColumnNames
         *@param subColumnNames Is a sequence [OrderedCollection] of subColumns for
         *this table
         */
    public CompoundColumn newCompoundColumnNamed(String name, List subColumnNames);
    public CompoundColumn newCompoundColumnNamed(String name, String subColumnName1);
    public CompoundColumn newCompoundColumnNamed(String name, String subColumnName1, String subColumnName2);
    public CompoundColumn newCompoundColumnNamed(String name, String subColumnName1, String subColumnName2, String subColumnName3);

    public void setupPrimaryKeyColumn(Column column);
    public void setupScheme(Scheme scheme);

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void doneSetup(List errorsList);
    public void doneSetup();
        /**
         * Check whether the setup of this table appears
         * to be correct.  This can either be done before
         * calling "doneSetup" or it will be done during
         * that call.
         */
    public boolean isSetupValid();
    public void validateSetup();

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Column primaryKeyColumn();
    public Column columnNamed(String name);
    public String              name();
    public String              fullName();
    
        /**
         * The qualified name of a table includes the SQL-92 standard
         * prefixes of "<Catalog>.<Scheme>." if these are known to
         * the table or database connection.  If both of these
         * contextual qualifiers are NOT known then the qualified
         * name will not be different from the name.
         */
    public String              qualifiedName();


    public Scheme              scheme();
    //public Catalog             catalog();
    
    public Collection          columns();
    public Array  basicColumns();

    //**********************************************************
    
    //public void             setupScheme(Scheme aScheme);

    //**********************************************************
    //**********************************************************
    //**********************************************************

};