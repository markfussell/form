/*======================================================================
**
**  File: chimu/form/database/RowSingleColumnC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.KeyedArrayC;

import com.chimu.kernel.exceptions.*;

import java.util.Hashtable;


/**
<P>RowC is now double-sided: You can either put/retrieve values into
a Row directly (from the database perspective) or you can put/retrieve
values from the desired (Virtual) database perspective.  Between these two
views there is a transformation (possibly null) which handles the
differences between the real database and the desired database.

<P>The standard KeyedArray operations #atIndex, #atKey are the database side.
The #valueForColumn methods are the virtual methods.
**/
class RowSingleColumnC extends KeyedArrayC implements RowPi {

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public RowSingleColumnC () {
        super ();
    };

    public RowSingleColumnC (KeyedCollection indexes) {
        super (indexes);
    };

    public RowSingleColumnC (KeyedCollection indexes, Object[] values) {
        super (indexes, values);
    };

    static Integer ZERO = new Integer(0);

    public RowSingleColumnC (Column column) {
        super(1);

        this.column = column;
        columnIndexes.atKey_put(column.name(),ZERO);
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ public Object dbValueForBasicColumnNamed (String basicColumnName) {
        return atKey(basicColumnName);
    }

    /*package*/ public void setDbValueForBasicColumnNamed_to (String basicColumnName, Object value) {
        atKey_put(basicColumnName, value);
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Object valueForColumn (Column cColumn) {
        return ((ColumnPi) cColumn).valueForRow(this);
    }

    public void setValueForColumn_to (Column cColumn, Object value) {
        ((ColumnPi) cColumn).setValueForRow_to(this,value);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

        //Just a convenience function
    public int sqlDataTypeAtIndex (int i) {
        if (i > 0) throw new FailedRequireException("Index "+i+" is larger than number of columns");
        return column.sqlDataType();
    }

    public Column columnAtIndex (int i) {
        if (i > 0) throw new FailedRequireException("Index "+i+" is larger than number of columns");
        return column;
    }

    public Column basicColumnAtIndex (int i) {
        if (i > 0) throw new FailedRequireException("Index "+i+" is larger than number of columns");
        return column;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Column column = null;
};

