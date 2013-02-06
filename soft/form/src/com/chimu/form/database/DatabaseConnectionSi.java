/*======================================================================
**
**  File: chimu/form/database/DatabaseConnectionSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import java.sql.SQLException;

/**
DatabaseConnectionSi is a Subsystem Interface for a DatabaseConnection.  This
interface is private to the FORM subsystem and should not be used by FORM clients.
**/
/*subsystem*/ public interface DatabaseConnectionSi extends DatabaseConnection {
    public void setAutoCommit(boolean shouldAutoCommit) throws SQLException;
}

