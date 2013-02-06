/*======================================================================
**
**  File: chimu/formTools/test/NullDatabaseTest.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.test;

import java.sql.Connection;
import java.sql.*;
import java.util.*;


import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;

import com.chimu.form.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.transaction.*;
import com.chimu.form.database.*;

/**
This is the program that is used to drive the functionality for
the UsingFORM examples.

**/
public class NullDatabaseTest extends FormDatabaseTestAbsC {

    public void run(Connection jdbcConnection) {
        if (tracing()) {
            traceStream.println("Null Test was successful");
        }
    }

}