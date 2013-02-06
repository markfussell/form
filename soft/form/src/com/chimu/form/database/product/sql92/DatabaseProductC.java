/*======================================================================
**
**  File: chimu/form/database/product/sql92/DatabaseProductC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.sql92;

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
    public String shortName()           {return "sql92";}
    public String name()                {return "SQL 92 Compatible";}
    public int    code()                {return DatabaseProductConstants.DB_SQL92;}
    public String demoLoginName()       {return null;   }
    public String demoLoginPassword()   {return null;   }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem*/ public Table newTableNamed_for(String tableName,DatabaseConnection dbConnection) {
        return new TableC(tableName,null,dbConnection);
    }

    public void setupTypeStrings() {
        super.setupTypeStrings();
        typeDouble = "DOUBLE";
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

}