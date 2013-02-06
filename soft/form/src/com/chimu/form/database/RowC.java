/*======================================================================
**
**  File: chimu/form/database/RowC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.KeyedArrayC;

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
class RowC extends KeyedArrayC implements RowPi {

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public RowC () {
        super ();
    };

    public RowC (KeyedCollection indexes) {
        super (indexes);
    };

    public RowC (KeyedCollection indexes, Object[] values) {
        super (indexes, values);
    };

    public RowC (Table table, KeyedCollection indexes) {
        super (indexes);
        this.table = (TableSi) table;
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
        return table.sqlDataTypeForColumnAtIndex(i);
    }

    public Column columnAtIndex (int i) {
        return table.columnAtIndex(i);
    }

    public Column basicColumnAtIndex (int i) {
        return table.basicColumnAtIndex(i);
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected TableSi table = null;
};

