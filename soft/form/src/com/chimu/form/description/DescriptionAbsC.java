/*======================================================================
**
**  File: chimu/form/description/DescriptionAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

import com.chimu.form.mapping.*;


import com.chimu.form.database.*;
import com.chimu.form.*;
import com.chimu.form.query.*;

import java.sql.*;
import com.chimu.kernel.exceptions.*;

public abstract class DescriptionAbsC {


    //**************************************
    //**************************************
    //**************************************


    protected Statement createSingleStmt(DatabaseConnection dbConnection) {
        Statement stmt = null;
        try {
            stmt = dbConnection.connection().createStatement();

        } catch (Exception e){
            throw new RuntimeWrappedException("Error",e);
        };
        return stmt;
    }

    protected void executeString(DatabaseConnection dbConnection, String sqlString) {
        try {
            Statement stmt = createSingleStmt(dbConnection);
            stmt.executeUpdate(sqlString);
        } catch (Exception e){
            throw new RuntimeWrappedException("Could not execute "+sqlString,e);
        };
    }

}