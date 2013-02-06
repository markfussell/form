/*======================================================================
**
**  File: chimu/form/database/BasicColumn.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

/**
A BasicColumn is a column that actually exists on the database.
BasicColumn has no added functionality over a Column because
Column is the "Component" in a Composite design pattern, and as
such defines all the protocol expected of the subtypes.

@see Column
@see CompoundColumn
**/
public interface BasicColumn extends Column {
};