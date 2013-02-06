/*======================================================================
**
**  File: chimu/formTools/examples/DatabaseSetupAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.examples;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.streams.*;
import com.chimu.kernel.exceptions.*;

import com.chimu.form.database.*;
import com.chimu.form.database.product.*;
import com.chimu.form.FormPack;

import java.sql.*;

public abstract class DatabaseSetupAbsC extends DescriptionGenC {

    protected DatabaseProduct databaseProduct;

    protected DatabaseConnection dbConnection = null;
    protected Statement  stmt = null;

    protected void setupTypeStrings() {
        databaseProduct.setupTypeStrings();
//  if there are over rides, here is the spot to override.....??
        
    }

    protected void setupDatabaseProductType() {
// This is a no-op because the database product is already determined by the time 
// #setupDatabaseConnection() is called and cached.
// we are keeping this method because someone may be calling this method.
        
    }
    protected void setupDatabaseConnection(Connection connection) {     
        dbConnection = FormPack.newDatabaseConnection(connection);
        databaseProduct = dbConnection.databaseProduct();
        createSingleStmt();
    }

    protected void createSingleStmt() {
        try {
            stmt = dbConnection.connection().createStatement();
        } catch (Exception e){
            throw new RuntimeWrappedException("Error",e);
        };
    }

    protected void executeString(String sqlString) {
        try {
            stmt.executeUpdate(sqlString);
        } catch (Exception e){
            throw new RuntimeWrappedException("Could not execute "+sqlString,e);
        };
    }

    protected void executeString_ignoreError(String sqlString) {
        try {
            stmt.executeUpdate(sqlString);
        } catch (Exception e){
            tracePrintln(e.toString());
            if (traceLevel > 0) {
                e.printStackTrace(traceStream);          }
        };
    }

}
