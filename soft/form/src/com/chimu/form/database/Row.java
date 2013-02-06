/*======================================================================
**
**  File: chimu/form/database/Row.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;
import com.chimu.kernel.collections.KeyedArray;

/**
A Row represents a row retrieved from the database.  Rows are generally
not needed outside of FORM and this interface should be considered private
to FORM.
**/
public interface Row extends KeyedArray {

    public Object valueForColumn (Column column);
    public void setValueForColumn_to (Column column, Object value);

    public int sqlDataTypeAtIndex (int i);
    public Column columnAtIndex(int i);

    public Column basicColumnAtIndex (int i);

    // CompoundColumn??
    // Array valueForNames(Array keyNames)
    // KeyedArray copyNames(Array keyNames)

};

