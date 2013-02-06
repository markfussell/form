/*======================================================================
**
**  File: chimu/form/database/product/mssql/DatabaseProductC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.mssql;

import com.chimu.form.database.DatabaseConnection;

import com.chimu.form.database.Table;
import com.chimu.form.database.product.DatabaseProductAbsC;
import com.chimu.form.database.product.DatabaseProductConstants;

public class DatabaseProductC extends DatabaseProductAbsC
                           implements  DatabaseProductConstants {

    public DatabaseProductC() {
    }

    //**********************************************************
    //************************ Asking **************************
    //**********************************************************
    public String shortName()           {return "MSSQL";}
    public String name()                {return "Microsoft SQL Server";}
    public int    code()                {return DatabaseProductConstants.DB_MSSQL;}
    public String demoLoginName()       {return "sa";   }
    public String demoLoginPassword()   {return null;   }
    public boolean matchesDescription(String description) {
        return description.startsWith("microsoft sql server");
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem*/ public Table newTableNamed_for(String tableName,DatabaseConnection dbConnection) {
        return new TableC(tableName,null,dbConnection);
    }

    public boolean canSupportBoolean() {
        return true;
    }
    /*  this sets up the defaults that are specific to MSSQL
    */
    public void setupTypeStrings() {
        super.setupTypeStrings();
        nullable = "NULL";
        typeDate = "DATETIME";
        typeTime = "DATETIME";
        typeTimestamp = "DATETIME";
        typeLongVarChar = "VARCHAR";
        typeMoney   = "MONEY";

        typeLongVarBinary = "VARBINARY";
        typeBigInt = "REAL";    //???

        typeSmallMoney = "SMALLMONEY";      //special to mssql

    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String typeSmallMoney = null;


}