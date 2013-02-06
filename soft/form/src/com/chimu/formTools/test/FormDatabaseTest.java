/*======================================================================
**
**  File: chimu/formTools/test/FormDatabaseTest.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.test;

import java.sql.Connection;
import java.io.PrintWriter;

/**
A FormDatabaseTest can be run given a JDBC Connection.  It also supports
setting up tracing options for the run.  A DatabaseTest is just like
a com.chimu.kernelTools.test.Test but has an incompatible protocol because
of the database information.
@see com.chimu.kernelTools.test.Test
**/
public interface FormDatabaseTest extends DatabaseTest{
    public void setupCatalog_scheme(String catalog, String scheme);
}

