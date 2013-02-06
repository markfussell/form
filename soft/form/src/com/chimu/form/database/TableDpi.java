/*======================================================================
**
**  File: chimu/form/database/TableDpi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.kernel.collections.*;
import com.chimu.form.query.QueryDescription;
import com.chimu.form.query.SqlBuilder;
import java.sql.SQLException;

import java.sql.Connection;
/**
**/
/*profiling*/ public interface TableDpi extends TableSi {

    /*profiling*/ public void profileSelectAll();


};