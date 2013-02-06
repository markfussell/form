/*======================================================================
**
**  File: chimu/form/database/product/sqla/DatabaseProductC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product.sqla;

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
    public String shortName()           {return "SQLA";}
    public String name()                {return "Sybase SQL Anywhere";}
    public int    code()                {return DatabaseProductConstants.DB_SQLA;}
    public String demoLoginName()       {return "dba";   }
    public String demoLoginPassword()   {return "dba";   }
    public boolean matchesDescription(String description) {
        return description.startsWith("sybase sql anywhere");
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem*/ public Table newTableNamed_for(String tableName,DatabaseConnection dbConnection) {
        return new TableC(tableName,null,dbConnection);
    }


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

}