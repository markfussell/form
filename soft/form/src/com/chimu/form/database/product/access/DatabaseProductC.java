/*======================================================================
**
**  File: chimu/form/database/product/access/DatabaseProductC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.access;

import com.chimu.form.database.DatabaseConnection;

import com.chimu.form.database.Table;
import com.chimu.form.database.product.DatabaseProductAbsC;
import com.chimu.form.database.product.DatabaseProductConstants;

public class DatabaseProductC extends     DatabaseProductAbsC
                           implements  DatabaseProductConstants {

    public DatabaseProductC() {
    }

    //**********************************************************
    //************************ Asking **************************
    //**********************************************************

    public String shortName()           {return "Access";}
    public String name()                {return "Microsoft Access";}
    public int    code()                {return DatabaseProductConstants.DB_ACCESS;}
    public String demoLoginName()       {return null;   }
    public String demoLoginPassword()   {return null;   }
    public boolean matchesDescription(String description) {
        return description.startsWith("access");
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

    public void setupTypeStrings() {
        super.setupTypeStrings();
        primaryKeyPrefix = ", CONSTRAINT PrimaryKey PRIMARY KEY (";
        typeDecimal    = "MONEY";
        typeChar     = "VARCHAR";
    }


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

}