/*======================================================================
**
**  File: chimu/form/database/RowPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;
import com.chimu.kernel.collections.KeyedArray;

/**
RowPi provides a Package (mostly Column) interface to Row.

<P>This interface is private to FORM and should not be used by FORM clients.</P>
**/
/*package*/ public interface RowPi extends Row {

    /*package*/ public Object dbValueForBasicColumnNamed (String basicColumnName);
    /*package*/ public void setDbValueForBasicColumnNamed_to (String basicColumnName, Object value);

}

