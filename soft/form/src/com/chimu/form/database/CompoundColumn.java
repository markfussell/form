/*======================================================================
**
**  File: chimu/form/database/CompoundColumn.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.kernel.collections.*;
/**
CompoundColumns allow you to treat multiple basic database columns as a
single entity.  They enable you to build more complex data types to use
for Slot columns than the database provides.

<P>A CompoundColumn is the "Composite" in a Composite Pattern.
@see Column
@see BasicColumn
**/
public interface CompoundColumn extends Column {

    public boolean hasSingleSubColumn();
    public Column  firstSubColumn();

    public List /*Column*/ subColumns();
    public int    numberOfSubColumns();
    public String firstSubColumnName();
    public Column subColumnAtIndex(int i);
    public String nameForSubColumnAtIndex(int i);

};