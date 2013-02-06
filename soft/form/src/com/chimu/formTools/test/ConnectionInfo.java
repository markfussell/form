/*======================================================================
**
**  File: chimu/formTools/test/ConnectionInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.test;
//New Line--1

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
//A different new line

/**
This is the program that is used to drive the functionality for
the UsingFORM examples.

**/
public class ConnectionInfo extends FormDatabaseTestAbsC {

    public void run(Connection jdbcConnection) {
        DatabaseMetaData metaData = null;
        String driverDescription = null;
        String productDescription = null;
        try {
            metaData = jdbcConnection.getMetaData();
            driverDescription = metaData.getDriverName();
            productDescription = metaData.getDatabaseProductName();

            outputStream.println("Driver Product   = "+driverDescription +" ["+metaData.getDriverVersion()+"]");
            outputStream.println("Database Product = "+productDescription+" ["+metaData.getDatabaseProductVersion()+"]");


            outputStream.println("\nCatalogs ("+metaData.getCatalogTerm()+")");
            try {
                ResultSet resultSet = metaData.getCatalogs();

                while (resultSet.next()) {
                    String name  = resultSet.getString(1);    //TABLE_CAT
                    outputStream.println(name);
                };
                resultSet.close();
            } catch (Exception e2) {
                outputStream.println("** Driver not capable **");
            }

            outputStream.println("\nSchemas ("+metaData.getSchemaTerm()+")");
            try {
                ResultSet resultSet = metaData.getSchemas();

                while (resultSet.next()) {
                    String name  = resultSet.getString(1);    //TABLE_SCHEM
                    outputStream.println(name);
                };
                resultSet.close();
            } catch (Exception e2) {
                outputStream.println("** Driver not capable **");
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}