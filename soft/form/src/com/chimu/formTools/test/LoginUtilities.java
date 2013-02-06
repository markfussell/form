/*======================================================================
**
**  File: chimu/formTools/test/LoginUtilities.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.test;

import com.chimu.form.database.*;
import com.chimu.form.database.product.*;
import com.chimu.form.database.driver.*;

/**
This class contains all the static functions that may be used
to set up connections, login strings, etc.
**/
public class LoginUtilities {

    static protected int codeForDatabase(String databaseType) {
        if (databaseType.equals("oracle"))          return DatabaseProductConstants.DB_ORACLE;
        if (databaseType.equals("db2"))             return DatabaseProductConstants.DB_DB2;
        if (databaseType.equals("access"))          return DatabaseProductConstants.DB_ACCESS;
        if (databaseType.equals("mssql"))           return DatabaseProductConstants.DB_MSSQL;
        if (databaseType.equals("mssqlserver"))     return DatabaseProductConstants.DB_MSSQL;
        if (databaseType.equals("sybase"))          return DatabaseProductConstants.DB_SYBASE;
        if (databaseType.equals("informix"))        return DatabaseProductConstants.DB_INFORMIX;
        if (databaseType.equals("sqla"))            return DatabaseProductConstants.DB_SQLA;
        return DatabaseProductConstants.DB_UNKNOWN;
    }

    static protected int codeForDriver(String driverType) {
        if (driverType.equals("jdbcodbc"))     return DatabaseDriverConstants.DR_SUN_JDBC_ODBC;
        if (driverType.equals("fastforward"))  return DatabaseDriverConstants.DR_FAST_FORWARD;
        if (driverType.equals("weblogic"))     return DatabaseDriverConstants.DR_WEBLOGIC;
        return DatabaseDriverConstants.DR_UNKNOWN;
    }




}
