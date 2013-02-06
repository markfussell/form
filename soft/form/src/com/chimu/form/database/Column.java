/*======================================================================
**
**  File: chimu/form/database/Column.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

/**
A Column is either a Basic (real) or Compound (virtual) column on a
database table.  Either way a column represents the information about
a table and the rows from that table.

<P>Column is the "Component" in a Composite Pattern with CompoundColumn
as the Composite and BasicColumn as the Leaf.  As the root abstract
type it supports the protocol common to BasicColumn and CompoundColumn,
and any functionality that seems best to push upward and generalize
between the two component types.

@see BasicColumn
@see CompoundColumn
**/
public interface Column {
    //**********************************************************
    //******************* Truly Universal **********************
    //**********************************************************

    public String name();
    public Table  table();

    //**********************************************************
    //********************* BasicColumn ************************
    //**********************************************************
        /*
            These are methods that make "more" sense for
            BasicColumns
        */
    public int     sqlDataType();
    public int     javaDataType();
    public int     maximumLength();
    public boolean isNullable();

    public boolean isCompound();

    //**********************************************************
    //******************** CompoundColumn **********************
    //**********************************************************
        /*
            These are methods that make "more" sense for
            CompoundColumns
        */

};